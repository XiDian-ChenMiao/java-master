package cn.algorithm;

import java.util.Arrays;

/**
 * 文件描述：红黑树
 * NOTE:
 * 1.每个节点不是红色就是黑色的；
 * 2.根节点总是黑色的；
 * 3.如果节点是红色的，则它的子节点必须是黑色的（反之不一定）；
 * 4.从根节点到叶节点或空子节点的每条路径，必须包含相同数目的黑色节点（即相同的黑色高度）。
 * 创建作者：陈苗
 * 创建时间：2016/10/24 20:35
 */
public class RedBlackTreeTest<T extends Comparable<T>> {
    /**
     * 红黑树节点颜色枚举
     */
    private enum Color {
        RED, BLACK
    }

    /**
     * 红黑树节点结构
     *
     * @param <T>
     */
    private static class RBNode<T extends Comparable<T>> {
        Color color;/*节点颜色*/
        T key;/*节点关键字*/
        RBNode<T> left;/*左孩子节点*/
        RBNode<T> right;/*右孩子节点*/
        RBNode<T> parent;/*父节点*/

        /**
         * 构造函数
         *
         * @param color
         * @param key
         * @param left
         * @param right
         * @param parent
         */
        public RBNode(Color color, T key, RBNode<T> left, RBNode<T> right, RBNode<T> parent) {
            this.color = color;
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        /**
         * 获取关键字的方法
         *
         * @return
         */
        public T getKey() {
            return key;
        }

        @Override
        public String toString() {
            return String.valueOf(key + ":" + (this.color == Color.BLACK ? "B" : "R"));
        }
    }

    /**
     * 根节点
     */
    private RBNode<T> root;

    /**
     * 构造函数
     */
    public RedBlackTreeTest() {
        this.root = null;
    }

    /**
     * 左旋示意图：对节点x进行左旋
     * p                       p
     * /                       /
     * x                       y
     * / \                     / \
     * lx  y      ----->       x  ry
     * / \                 / \
     * ly ry               lx ly
     * 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     */
    private void leftRotate(RBNode<T> x) {
        /*将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)*/
        RBNode<T> y = x.right;
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;
        /*将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)*/
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;/*如果父节点为空，则将y设置为根节点*/
        } else {
            if (x == x.parent.left)
                x.parent.left = y;
            else
                x.parent.right = y;
        }
        /*将y的左子节点设为x，将x的父节点设为y*/
        y.left = x;
        x.parent = y;
    }

    /**
     * 查询函数
     *
     * @param x
     * @param key
     * @return
     */
    private RBNode<T> search(RBNode<T> x, T key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x;
        }
        return x;
    }

    /**
     * 左旋示意图：对节点y进行右旋
     * p                   p
     * /                   /
     * y                   x
     * / \                 / \
     * x  ry   ----->      lx  y
     * / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    private void rightRotate(RBNode<T> y) {
        /*将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)*/
        RBNode<T> x = y.left;
        y.left = x.right;
        if (x.right != null)
            x.right.parent = y;
        /*将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)*/
        x.parent = y.parent;
        if (y.parent == null)
            this.root = x;
        else {
            if (y == y.parent.left)
                y.parent.left = x;
            else
                y.parent.right = x;
        }
        /*将x的右子节点设为y，将y的父节点设为x*/
        x.right = y;
        y.parent = x;
    }

    /**
     * 向红黑树中插入关键字
     *
     * @param key
     */
    public void insert(T key) {
        RBNode<T> node = new RBNode<T>(Color.RED, key, null, null, null);
        if (node != null)
            insert(node);
    }

    /**
     * 向红黑树中插入节点
     *
     * @param node
     */
    private void insert(RBNode<T> node) {
        RBNode<T> current = null;/*用来表示最后node的父节点*/
        RBNode<T> x = this.root;/*用来向下搜索用*/
        /*找到插入的位置*/
        while (x != null) {
            current = x;
            if (node.key.compareTo(x.key) < 0)
                x = x.left;
            else
                x = x.right;
        }
        node.parent = current;/*找到位置，设置当前的current节点为插入节点的父节点*/
        if (current != null) {
            if (node.key.compareTo(current.key) < 0)
                current.left = node;
            else
                current.right = node;
        } else
            this.root = node;
        insertFixUp(node);
    }

    /**
     * 移除关键字
     *
     * @param key
     */
    public void remove(T key) {
        RBNode<T> node;
        if ((node = search(root, key)) != null)
            remove(node);
    }

    /**
     * 移除节点
     *
     * @param node
     */
    private void remove(RBNode<T> node) {
        RBNode<T> child, parent;
        Color color;
        /*如果被删除节点两孩子节点都不为空*/
        if (node.left != null && node.right != null) {
            /*先找到被删除节点的后继节点，用它来取代被删除节点的位置*/
            RBNode<T> replace = node;
            /*获取后继节点*/
            replace = replace.right;
            while (replace.left != null)
                replace = replace.left;
            /*处理后继节点与被删除节点的父节点之间的关系*/
            if (parentOf(node) != null) {/*要删除的节点不是根节点*/
                if (node == parentOf(node).left)
                    parentOf(node).left = replace;
                else
                    parentOf(node).right = replace;
            } else
                this.root = replace;
            /*处理后继节点的子节点与删除节点的子节点的关系*/
            child = replace.right;
            parent = parentOf(replace);
            color = replace.color;/*保存后继节点的颜色*/
            if (parent == node)/*如果后继节点是被删除节点的子节点*/
                parent = replace;
            else {
                if (child != null)
                    child.parent = parent;
                parent.left = child;
                replace.right = node.right;
                node.right.parent = replace;
            }
            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;
            if (color == Color.BLACK)/*如果移走的后继节点颜色为黑色，则重新修正红黑树*/
                removeFixUp(child, parent);
            node = null;
            return;
        }
    }

    /**
     * 移除节点修正函数
     *
     * @param node
     * @param parent
     */
    private void removeFixUp(RBNode<T> node, RBNode<T> parent) {
        RBNode<T> other;
        while ((node == null || isBlack(node)) && (node != this.root)) {
            if (parent.left == node) { /*node是左子节点，下面else与这里的刚好相反*/
                other = parent.right; /*node的兄弟节点*/
                if (isRed(other)) { /*node的兄弟节点other是红色的*/
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }
                /*node的兄弟节点other是黑色的，且other的两个子节点也都是黑色的*/
                if ((other.left == null || isBlack(other.left)) &&
                        (other.right == null || isBlack(other.right))) {
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {
                    /*node的兄弟节点other是黑色的，且other的左子节点是红色，右子节点是黑色*/
                    if (other.right == null || isBlack(other.right)) {
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    /*node的兄弟节点other是黑色的，且other的右子节点是红色，左子节点任意颜色*/
                    other.color = parent.color;
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.root;
                    break;
                }
            } else {
                other = parent.left;
                if (isRed(other)) {
                    /*node的兄弟other是红色的*/
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }
                if ((other.left == null || isBlack(other.left)) &&
                        (other.right == null || isBlack(other.right))) {
                    /*node的兄弟other是黑色，且other的俩个子节点都是黑色的*/
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {
                    if (other.left == null || isBlack(other.left)) {
                        /*node的兄弟other是黑色的，并且other的左子节点是红色，右子节点为黑色*/
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }
                    /*node的兄弟other是黑色的；并且other的左子节点是红色的，右子节点任意颜色*/
                    other.color = parent.color;
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.root;
                    break;
                }
            }
        }
        if (node != null)
            setBlack(node);
    }

    /**
     * 修正红黑树的函数
     *
     * @param node
     */
    private void insertFixUp(RBNode<T> node) {
        RBNode<T> parent, gparent;/*定义父节点和祖父节点*/
        /*需要修正的条件就是父节点存在，且父节点的颜色是红色*/
        while ((parent = parentOf(node)) != null && isRed(parent)) {
            gparent = parentOf(parent);/*获取祖父节点*/
            if (parent == gparent.left) {
                RBNode<T> uncle = gparent.right;/*获取叔叔节点*/
                if (uncle != null && isRed(uncle)) {/*如果叔叔节点也是红色*/
                    setBlack(parent);
                    setBlack(uncle);/*将父节点与叔叔节点颜色涂黑*/
                    setRed(gparent);/*将祖父节点涂红*/
                    node = gparent;/*将当前节点位置设置到祖父节点处*/
                    continue;
                }
                if (uncle == parent.right) {/*如果叔叔节点为黑色，且当前节点为右子节点*/
                    leftRotate(parent);/*从父节点处左旋*/
                    RBNode<T> temp = parent;/*将父节点与自己调换一下，为右旋做准备*/
                    parent = node;
                    node = temp;
                }
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            } else {
                RBNode<T> uncle = gparent.left;
                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }
                if (node == parent.left) {
                    rightRotate(parent);
                    RBNode<T> temp = parent;
                    parent = node;
                    node = temp;
                }
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }
        setBlack(this.root);/*将根节点设置为黑色*/
    }

    /**
     * 将给定节点颜色设置为黑色
     *
     * @param node
     */
    private void setBlack(RBNode<T> node) {
        node.color = Color.BLACK;
    }

    /**
     * 将给定节点颜色设置为红色
     *
     * @param node
     */
    private void setRed(RBNode<T> node) {
        node.color = Color.RED;
    }

    /**
     * 求给定节点父节点
     *
     * @param node
     * @return
     */
    public RBNode<T> parentOf(RBNode<T> node) {
        return node.parent;
    }

    /**
     * 判断给定节点的颜色是否为红色
     *
     * @param node
     * @return
     */
    public boolean isRed(RBNode<T> node) {
        return node.color == Color.RED;
    }

    /**
     * 判断给定节点颜色是否为黑色
     *
     * @param node
     * @return
     */
    public boolean isBlack(RBNode<T> node) {
        return node.color == Color.BLACK;
    }

    /**
     * 打印红黑树
     */
    public void print() {
        if (root != null) {
            print(root, root.key, 0);
        }
    }

    /**
     * @param key       节点的键值
     * @param direction 0:表示该节点是根节点
     *                  1:表示该节点是它的父节点的左子节点
     *                  2:表示该节点是它的父节点的右子节点
     */
    private void print(RBNode<T> tree, T key, int direction) {
        if (tree != null) {
            if (0 == direction)
                System.out.printf("%2d(黑)是根节点\n", tree.key);
            else
                System.out.printf("%2d(%s)是节点%2d的%s孩子\n", tree.key, isRed(tree) ? "红色" : "黑色", key, direction == 1 ? "右" : "左");
            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(RBNode<T> tree) {
        if (tree != null) {
            System.out.print(tree.key + "\t");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /**
     * 获取最小值
     * @return
     */
    public T getMinValue() {
        RBNode<T> node = getMinNode(root);
        if (node != null)
            return node.key;
        return null;
    }

    /**
     * 获取值最小的节点
     * @param tree
     * @return
     */
    private RBNode<T> getMinNode(RBNode<T> tree) {
        if(tree == null)
            return null;
        while(tree.left != null) {
            tree = tree.left;
        }
        return tree;
    }

    /**
     * 获取最大值
     * @return
     */
    public T getMaxValue() {
        RBNode<T> node = getMaxNode(root);
        if (node != null)
            return node.key;
        return null;
    }

    /**
     * 获取值最大的节点
     * @param tree
     * @return
     */
    private RBNode<T> getMaxNode(RBNode<T> tree) {
        if(tree == null)
            return null;
        while(tree.right != null) {
            tree = tree.right;
        }
        return tree;
    }

    /**
     * 获取指定节点的前驱节点
     * @param node
     * @return
     */
    public RBNode<T> getProcessor(RBNode<T> node) {
        if (node.left != null)
            return node.left;
        RBNode<T> p = node.parent;
        while (p != null && node == p.left) {
            node = p;
            p = node.parent;
        }
        return p;
    }

    /**
     * 获取指定键值的前驱值
     * @param key
     * @return
     */
    public T getProcessorValue(T key) {
        RBNode<T> node = search(root, key);
        if (node != null)
            return getProcessor(node).key;
        else
            return null;
    }
    /**
     * 获取指定节点的后继节点
     * @param node
     * @return
     */
    public RBNode<T> getSuccessor(RBNode<T> node) {
        if (node.right != null)
            return node.right;
        RBNode<T> p = node.parent;
        while (p != null && node == p.right) {
            node = p;
            p = node.parent;
        }
        return p;
    }

    /**
     * 获取指定键值的后继值
     * @param key
     * @return
     */
    public T getSuccessorValue(T key) {
        RBNode<T> node = search(root, key);
        if (node != null)
            return getSuccessor(node).key;
        else
            return null;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {10, 40, 30, 60, 90, 70, 20, 50, 80};
        RedBlackTreeTest<Integer> treeTest = new RedBlackTreeTest<Integer>();
        System.out.println("原始数据：" + Arrays.toString(data));
        for (int i = 0; i < data.length; i++) {
            treeTest.insert(data[i]);
            System.out.println("添加节点：" + data[i]);
            System.out.println("红黑树的详细信息：");
            treeTest.print();
            System.out.println();
        }
        System.out.println("最小值为：" + treeTest.getMinValue());
        System.out.println("最大值为：" + treeTest.getMaxValue());
        System.out.println("键值为80的前驱值为：" + treeTest.getProcessorValue(80));
        System.out.println("键值为80的后继值为：" + treeTest.getSuccessorValue(80));
        System.out.print("前序遍历结果为：");
        treeTest.preOrder();
        treeTest.remove(80);
        System.out.println();
        System.out.println("删除元素80后的前序遍历结果为：");
        treeTest.preOrder();
    }
}
