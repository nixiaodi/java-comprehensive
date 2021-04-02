package jiang.tree;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/3/26
 * @Version 1.0
 */
@Slf4j
public class BinaryTreeDemo {

    public static void main(String[] args) {
        // 创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建节点
        Employee root = new Employee(1L, "jiang");
        Employee ni = new Employee(2L, "ni");
        Employee zhao = new Employee(3L,"zhao");
        Employee liu = new Employee(4L,"liu");
        Employee ma = new Employee(5L,"ma");
        Employee wang = new Employee(6L,"wang");

        // 设置根节点
        binaryTree.setRoot(root);

        /**
         * 暂时手动创建，之后递归创建
         */
        root.setLeft(ni);
        root.setRight(zhao);
        zhao.setRight(liu);
        liu.setLeft(ma);
        liu.setRight(wang);

        //binaryTree.proOrder();
        //binaryTree.midOrder();
        //binaryTree.postOrder();

        //binaryTree.preOrderSearch(4);

        boolean flag = binaryTree.remove(7);
        log.info("删除确认:{},删除之后的节点树...",flag);
        binaryTree.preOrder();
    }


    /**
     * 定义二叉树
     */
    static class BinaryTree {

        public BinaryTree() {
        }

        /**
         * 定义根节点
         */
        private Employee root;

        /**
         * 设置根节点
         */
        public void setRoot(Employee root) {
            this.root = root;
        }

        /**
         * 前序遍历
         */
        public void preOrder() {
            Assert.state(root != null,"当前二叉树为空,无法遍历");
            this.root.preOrder();
        }

        /**
         * 中序遍历
         */
        public void midOrder() {
            Assert.state(root != null,"当前二叉树为空,无法遍历");
            this.root.midOrder();
        }

        /**
         * 后序遍历
         */
        public void postOrder() {
            Assert.state(root != null,"当前二叉树为空,无法遍历");
            this.root.postOrder();
        }

        /**
         * 前序查找
         * @return
         */
        public Employee preOrderSearch(int id) {
            if (this.root != null) {
                Employee resEmployee = this.root.preOrderSearch(id);
                log.info("{}",resEmployee);
                return resEmployee;
            }
            return null;
        }

        /**
         * 中序查找
         * @return
         */
        public Employee midOrderSearch(int id) {
            if (this.root != null) {
                Employee resEmployee = this.root.midOrderSearch(id);
                log.info("{}",resEmployee);
                return resEmployee;
            }
            return null;
        }

        /**
         * 后序查找
         * @return
         */
        public Employee postOrderSearch(int id) {
            if (this.root != null) {
                Employee resEmployee = this.root.postOrderSearch(id);
                log.info("{}",resEmployee);
                return resEmployee;
            }
            return null;
        }

        /**
         * 删除节点
         */
        public boolean remove(int id) {
            if (root != null) {
                return this.root.remove(id);
            } else {
                throw new RuntimeException("没有根节点...");
            }
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
