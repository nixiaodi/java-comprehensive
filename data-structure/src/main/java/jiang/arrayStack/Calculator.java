package jiang.arrayStack;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/3/25
 * @Version 1.0
 */
@Slf4j
public class Calculator {
    public static void main(String[] args) {
        String expression = "3+235*6-5";
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);
        /**
         * 下面的这些类似于JVM中的操作数栈
         */
        // 用于扫描的变量
        int index = 0;
        // 用于从数栈中弹出计算的临时变量
        int num1 = 0;
        int num2 = 0;
        // 用于弹出操作数栈中的操作符
        int operation = 0;
        // 存放结果的临时变量
        int res = 0;
        // 保存每次扫描得到的char
        char ch = ' ';
        /**
         * 开始循环扫描
         */
        while (true) {
            ch = expression.charAt(index++);
            // 判断ch是符号还是数字
            if (operStack.isOpreation(ch)) {
                /**
                 * 先判断操作符栈是否为空，如果为空就直接添加
                 * 如果不为空，则判断当前的操作符和栈顶操作符的优先级
                 * 当前优先级大则加入，当前优先级小或等于则弹出两个数并从操作栈中弹出一个操作符进行计算，将计算结果放入数栈，将当前操作符放入操作栈
                 */
                if (!operStack.isEmpty()) {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operation = operStack.pop();
                        res = operStack.calculate(num1,num2,operation);
                        // 将结果保存回数栈
                        numStack.push(res);
                        // 将操作符放入操作栈
                        operStack.push(ch);
                    } else {
                        // 如果当前操作符优先级大于操作栈栈顶的优先级
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }
            } else {
                // 可以直接用numStack.push(ch - 48)
                //numStack.push(Integer.parseInt(String.valueOf(ch)));
                /**
                 * 上面这种方式只能处理单位数，不能处理多位数,因此需要修改逻辑
                 */
                int temp = index;
                int tempRes = ch - 48;
                while (true) {
                    if (!operStack.isOpreation(expression.charAt(temp))) {
                        tempRes = 10 * tempRes + (expression.charAt(temp) - 48);
                        temp++;
                    } else {
                        break;
                    }
                }
                numStack.push(tempRes);
            }

            if (index == expression.length()) {
                break;
            }
        }

        // 表达式扫描完毕，就需要从数栈中弹出两个数,操作栈中弹出一个操作符计算,计算结果放入数栈，依次操作直到数栈只有一个数返回
        // 如果符号栈为空也就代表循环结束
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operation = operStack.pop();
            res = numStack.calculate(num1,num2,operation);
            numStack.push(res);
        }
        System.out.println(numStack.pop());
    }


    static class ArrayStack {
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
         * 获取栈顶元素,不出栈
         */
        public Integer peek() {
            if (isEmpty()) {
                log.error("栈已空");
                return null;
            }
            return stack[top];
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

        /**
         * 返回运算符的优先级
         */
        public int priority(int operation) {
            if (operation == '*' || operation == '/') {
                return 1;
            }
            if (operation == '+' || operation == '-') {
                return 0;
            }
            throw new RuntimeException("参数不是操作符...");
        }

        /**
         * 判断参数是否为操作符
         */
        public boolean isOpreation(char value) {
            return value == '*' || value == '/' || value == '+' || value == '-';
        }

        /**
         * 计算
         */
        public int calculate(int num1,int num2,int operation) {
            int res = 0;
            switch (operation) {
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num2 - num1;
                    break;
                case '*':
                    res = num1 * num2;
                    break;
                case '/':
                    res = num2 / num1;
                    break;
            }
            return res;
        }
    }
}
