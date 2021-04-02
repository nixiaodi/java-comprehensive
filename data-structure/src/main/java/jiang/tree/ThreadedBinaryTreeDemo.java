package jiang.tree;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/3/26
 * @Version 1.0
 */
@Slf4j
public class ThreadedBinaryTreeDemo {

    /**
     * 实现线索化功能的二叉树
     */
    static class ThreadedBinaryTree {
        private Employee root;

        /**
         * 为了实现线索化，必须创建一个当前节点的前驱节点的指针
         * 当在递归调用时pre必须总是保留其上一层节点
         * 对于树来说其实是单向的，只能通过当前节点找到对应的左右子节点，而不可能通过当前节点找到其上一层节点
         */
        private Employee pre;

        /**
         *
         * @param root
         */

        public void setRoot(Employee root) {
            this.root = root;
        }

        /**
         * 对二叉树进行中序线索化
         * @param node 当前需要线索化的节点
         */
        public void threadedNodes(Employee node) {
            // node 为null,不能线索化
            if (node == null) {
                return;
            }
            // 先线索化左子树
            threadedNodes(node.getLeft());
            // 线索化当前节点

            // 线索化右子树
            threadedNodes(node.getRight());
        }
    }

    /**
     * 节点
     */
    @Data
    static class Employee {
        private Long id;
        private String name;
        private Employee left;
        private Employee right;
        /**
         * leftType 当为0时表示左子树，为1时表示前驱节点
         */
        private int leftType;
        /**
         * rightType 当为0是表示右子树，为1时表示后置节点
         */
        private int rightType;

        public Employee(Long id,String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        /**
         * 前序遍历
         */
        public void preOrder() {
            log.info("{}",this);
            // 如果左子节点不为空,则左子节点递归前序遍历
            if (this.left != null) {
                left.preOrder();
            }
            if (this.right != null) {
                right.preOrder();
            }
        }

        /**
         * 中序遍历
         */
        public void midOrder() {
            if (this.left != null) {
                left.midOrder();
            }
            log.info("{}",this);
            if (this.right != null) {
                right.midOrder();
            }
        }

        /**
         * 后序遍历
         */
        public void postOrder() {
            if (this.left != null) {
                left.postOrder();
            }
            if (this.right != null) {
                right.postOrder();
            }
            log.info("{}",this);
        }

        /**
         * 前序查找
         */
        public Employee preOrderSearch(int id) {
            if (this.id == id) {
                return this;
            }
            // 设置变量接收结果
            Employee resEmployee = null;
            // 递归遍历左子树
            if (this.left != null) {
                resEmployee = this.left.preOrderSearch(id);
            }
            if (resEmployee != null) {
                return resEmployee;
            }
            // 递归遍历右子树
            if (this.right != null) {
                resEmployee = this.right.preOrderSearch(id);
            }
            return resEmployee;
        }

        /**
         * 中序查找
         * @return
         */
        public Employee midOrderSearch(int id) {
            // 设置变量接收结果
            Employee resEmployee = null;
            // 递归遍历左子树
            if (this.left != null) {
                resEmployee = this.left.preOrderSearch(id);
            }
            if (resEmployee != null) {
                return resEmployee;
            }
            if (this.id == id) {
                return this;
            }
            // 递归遍历右子树
            if (this.right != null) {
                resEmployee = this.right.preOrderSearch(id);
            }
            return resEmployee;
        }

        /**
         * 右序查找
         */
        public Employee postOrderSearch(int id) {
            // 设置变量接收结果
            Employee resEmployee = null;
            if (this.left != null) {
                resEmployee = this.left.preOrderSearch(id);
            }
            if (resEmployee != null) {
                return resEmployee;
            }
            // 递归遍历右子树
            if (this.right != null) {
                resEmployee = this.right.preOrderSearch(id);
            }
            // 如果右子树查找为null，这里需要继续查看根节点，故和之前的情况不同，需要继续遍历
            if (resEmployee != null) {
                return resEmployee;
            }
            if (this.id == id) {
                return this;
            }
            return null;
        }

        /**
         * 删除子节点
         * 1、先判断根节点的左右子节点是否为需要删除的节点，如果是就删除
         * 2、如果不是，就先对左子节点进行递归，如果左子节点没找到
         * 3、就继续对右子节点进行递归，如果右子节点也没找到，就直接返回false
         */
        public boolean remove(int id) {
            if (this.left != null && this.left.id == id) {
                this.left = null;
                return true;
            }
            if (this.right != null && this.right.id == id) {
                this.right = null;
                return true;
            }

            // 对左子树进行递归
            if (this.left != null) {
                boolean flag = this.left.remove(id);
                if (flag) {
                    return true;
                }
            }
            // 对右子树进行递归
            if (this.right != null) {
                boolean flag = this.right.remove(id);
                if (flag) {
                    return true;
                }
            }

            return false;
        }
    }
}
