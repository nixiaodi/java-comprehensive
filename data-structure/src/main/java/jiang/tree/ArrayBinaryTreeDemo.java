package jiang.tree;

import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/3/26
 * @Version 1.0
 */
@Slf4j
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }

    static class ArrayBinaryTree {
        private int[] arr;

        public ArrayBinaryTree(int[] arr) {
            this.arr = arr;
        }

        public void preOrder() {
            this.preOrder(0);
        }

        /**
         * 前序遍历
         * @param index 数组下标
         */
        public void preOrder(int index) {
            if (ArrayUtil.isEmpty(arr)) {
                return;
            }
            // 前序，先将中心节点输出
            log.info("{}",arr[index]);
            // 开始递归左子树
            if (2 * index + 1 < arr.length) {
                preOrder(2 * index + 1);
            }
            // 开始递归右子树
            if (2 * index + 2 < arr.length) {
                preOrder(2 * index + 2);
            }
        }
    }
}
