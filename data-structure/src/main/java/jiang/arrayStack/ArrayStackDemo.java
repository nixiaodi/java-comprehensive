package jiang.arrayStack;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/3/24
 * @Version 1.0
 */
@Slf4j
public class ArrayStackDemo {


    class ArrayStack {
        private int maxSize;
        private int[] stack;
        private int top;

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            this.stack = new int[maxSize];
            this.top = -1;
        }

        /**
         * 是否为满
         */
        public boolean isFull() {
            return top == maxSize - 1;
        }

        /**
         * 是否为空
         */
        public boolean isEmpty() {
            return top == -1;
        }

        /**
         * 压栈
         */
        public void push(int num) {
            if (isFull()) {
                log.error("栈已满");
                return;
            }
            stack[++top] = num;
        }

        /**
         * 弹栈
         */
        public Integer pop() {
            if (isEmpty()) {
                log.error("栈已空");
                return null;
            }
            return stack[top--];
        }

        /**
         * 遍历栈中所有数据
         */
        public void list() {
            if (isEmpty()) {
                log.error("栈已空");
                return;
            }

            for (int i = top;i > 0; i--) {
                System.out.println(i);
            }
        }
    }
}
