package jiang.hashtable;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/3/25
 * @Version 1.0
 */
@Slf4j
public class HashTableDemo {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(8);
        Employee jiang = new Employee(10L, "jiang");
        Employee ni = new Employee(15L, "ni");
        Employee zhao = new Employee(18L,"zhao");
        Employee liu = new Employee(26L,"liu");
        hashTab.add(jiang);
        hashTab.add(ni);
        hashTab.add(zhao);
        hashTab.add(liu);
        //hashTab.list();
        hashTab.get(2);
    }

    /**
     * 创建hashTable
     */
    static class HashTab {
        /**
         * hashtable中的链表
         */
        private EmployeeLinkedList[] employeeLinkedListArray;

        /**
         * 链表条数
         */
        private int maxSize;

        public HashTab(int maxSize) {
            this.maxSize = maxSize;
            this.employeeLinkedListArray = new EmployeeLinkedList[maxSize];
            /**
             * 这里需要注意，数组初始化时每个元素的默认值为null、0
             * 因此需要对链表中的每个元素进行初始化
             */
            for (int i = 0; i < employeeLinkedListArray.length; i++) {
                employeeLinkedListArray[i] = new EmployeeLinkedList();
            }
        }

        /**
         * 添加雇员
         */
        public void add(Employee employee) {
            int LinkedListIndex = hashFuc(employee);
            /**
             * 将当前员工加入到当前索引链表中
             */
            this.employeeLinkedListArray[LinkedListIndex].add(employee);
        }

        /**
         * 遍历hashTable获取所有的员工信息
         */
        public void list() {
            //Arrays.stream(employeeLinkedListArray).forEach(EmployeeLinkedList::list);
            for (int i = 0; i < employeeLinkedListArray.length; i++) {
                log.info("正在打印第{}个链表",i);
                employeeLinkedListArray[i].list();
            }
        }

        /**
         * 根据id查找员工
         */
        public Employee get(int id) {
            int index = hashFuc(id);
            return employeeLinkedListArray[index].get(id);
        }


        /**
         * 散列函数
         * 1、作用就是根据员工id选择出应当加入到哪条链表中
         * 2、这里就是简单取模
         * @return 链表在数组中的index
         */
        public int hashFuc(Employee employee) {
            return (int) (employee.getId() % this.maxSize);
        }

        /**
         * 散列函数
         * 1、作用就是根据员工id选择出应当加入到哪条链表中
         * 2、这里就是简单取模
         * @return 链表在数组中的index
         */
        public int hashFuc(int id) {
            return id % this.maxSize;
        }

    }


    /**
     * 关于雇员的链表
     */
    static class EmployeeLinkedList {
        // 头指针直接指向第一个元素,头指针有效
        private Employee head = null;

        /**
         * 添加雇员
         */
        public void add(Employee employee) {
            // 如果是第一个元素
            if (head == null) {
                head = employee;
                return;
            }
            // 使用临时指针来检索链表末尾
            Employee temp = head;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = employee;
        }

        /**
         * 遍历链表员工信息
         */
        public void list() {
            if (head == null) {
                log.info("当前链表没有员工");
                return;
            }
            // 临时指针检索
            Employee temp = head;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                log.info("{}",temp);
                temp = temp.next;
            }
        }

        /**
         * 根据id查找员工
         */
        public Employee get(int id) {
            if (head == null) {
                log.error("该链表没有员工");
                return null;
            }
            Employee temp = head;
            while (true) {
                if (id == temp.id) {
                    log.info("查找到的员工:{}",temp);
                    return temp;
                }
                if (temp.next == null) {
                    log.error("没有找到对应员工");
                    break;
                }
                temp = temp.next;
            }
            return null;
        }
    }

    /**
     * 具体数据
     */
    @Data
    static class Employee {
        private Long id;
        private String name;
        private Employee next;

        public Employee(Long id,String name) {
            this.id = id;
            this.name = name;
        }
    }
}
