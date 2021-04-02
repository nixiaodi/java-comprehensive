package jiang.regularQueue;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/3/24
 * @Version 1.0
 */
@Slf4j
public class ArrayQueueDemo {


    class ArrayQueue<T> {
        /**
         * 最大容量
         */
        private int maxSize;
        /**
         * 队列头a
         */
        private int front;
        /**
         * 队列尾
         */
        private int real;
        /**
         * 存储空间
         */
        private Object[] array;

        /**
         * 创建队列构造器
         */
        public ArrayQueue(int maxSize) {
            this.maxSize = maxSize;
            this.array = new Object[maxSize];
            // 指向队列头部的前一个位置
            this.front = -1;
            // 指向队列尾
            this.real = -1;
        }

        /**
         * 判断队列是否满
         */
        public boolean isFull() {
            return front == maxSize - 1;
        }

        /**
         * 判断队列是否空
         */
        public boolean isEmpty() {
            return front == real;
        }

        /**
         * 添加元素
         */
        public void add(T t) {
            if (this.isFull()) {
                log.error("队列满,无法添加数据");
                return;
            }
            array[++real] = t;
        }

        /**
         * 移除元素
         */
        public T remove() {
            if (this.isEmpty()) {
                log.error("队列空，无法移除元素");
                return null;
            }
            return (T) array[front++];
        }

        /**
         * 显示队列所有元素
         */
        public void show() {
            if (isEmpty()) {
                log.error("队列空，没有元素");
                return;
            }
            Arrays.asList(array).stream().forEach(System.out::println);
        }

        /**
         * 显示队列头
         */
        public T head() {
            if (isEmpty()) {
                log.error("队列空，没有元素");
                return null;
            }
            return (T) array[front + 1];
        }
    }
}
