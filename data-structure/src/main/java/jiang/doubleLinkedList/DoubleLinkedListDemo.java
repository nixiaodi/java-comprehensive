package jiang.doubleLinkedList;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/3/24
 * @Version 1.0
 */
public class DoubleLinkedListDemo {


    class DoubleLinkedList {
        /**
         * 头结点,初始化整个链表，不存放具体数据
         */
        private final Hero HEAD = new Hero("head",0);

        /**
         * 返回头结点
         */
        public Hero head() {
            return HEAD;
        }

        
    }

    class Hero {
        private String name;
        private int age;
        private Hero next;
        private Hero pre;

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
