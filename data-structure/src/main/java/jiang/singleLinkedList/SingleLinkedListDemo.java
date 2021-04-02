package jiang.singleLinkedList;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/3/24
 * @Version 1.0
 */
@Slf4j
public class SingleLinkedListDemo {



    class SingleLinkedList {
        /**
         * 头结点,初始化整个链表，不存放具体数据
         */
        private final Hero HEAD = new Hero("head",0);

        /**
         * 添加元素
         * 1、先找到链表的尾结点
         * 2、将尾结点的next域赋值为添加的元素
         * @apiNote 类似于责任链中在尾部添加filter
         */
        public void add(Hero hero) {
            // 不要动头结点
            // 这里的temp相当于指针，后面需要它一个个去搜索
            Hero temp = HEAD;
            // 寻找尾结点
            while (true) {
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = hero;
        }

        /**
         * 显示链表
         */
        public void list() {
            // 首先判断链表是否只有头结点
            if (HEAD.next == null) {
                log.info("链表中没有元素");
                return;
            }
            Hero temp = HEAD.next;
            while (true) {
                System.out.println(temp);
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
        }

        /**
         * 添加某个元素到某个节点之后
         * 1、遍历整个链表，先找到对应的节点
         * 2、让前一个节点的next域为添加元素，而添加元素的next域为指定节点
         */
        public void insert(Hero hero,Hero destHero) {
            // 首先判断链表是否只有头结点
            if (HEAD.next == null) {
                log.info("链表中没有元素");
                return;
            }
            Hero temp = HEAD.next;
            while (true) {
                if (temp == null) {
                    log.error("已达到链表末尾，未找到元素");
                    break;
                }
                if (temp.name.equals(destHero.name)) {
                    Hero tempNext = temp.next;
                    temp.next = hero;
                    hero.next = tempNext;
                    break;
                }
                temp = temp.next;
            }
        }
    }

    class Hero {
        private String name;
        private int age;
        private Hero next;

        public Hero(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Hero{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", next=" + next +
                    '}';
        }
    }
}
