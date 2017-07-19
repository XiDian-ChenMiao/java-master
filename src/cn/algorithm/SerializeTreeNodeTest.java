package cn.algorithm;

import java.util.*;

/**
 * 文件描述：序列化一个二叉树
 * 创建作者：陈苗
 * 创建时间：2016/10/21 17:43
 */
public class SerializeTreeNodeTest {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    /**
     * 串行化操作
     *
     * @param root
     * @return
     */
    public List<Integer> serialize(TreeNode root) {
        if (root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> result = new ArrayList<Integer>();
        if (root.left == null && root.right == null) {
            result.add(root.val);
            return result;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if (top != null) {
                result.add(top.val);
                queue.add(top.left);
                queue.add(top.right);
            } else
                result.add(null);
        }
        return result;
    }

    /**
     * 反串行化操作
     *
     * @param data
     * @return
     */
    public TreeNode deserialize(List<Integer> data) {
        if (data == null || data.size() == 0)
            return null;
        TreeNode root = null;
        TreeNode[] queue = new TreeNode[data.size() + 1];
        Integer[] values = new Integer[data.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = data.get(i);
        }
        TreeNode temp;
        int front = 1, rear = 0;
        for (int i = 0; i < values.length; i++) {
            temp = null;
            if (values[i] != null) {/*如果值中节点非空*/
                temp = new TreeNode();
                temp.val = values[i];
                temp.left = temp.right = null;
            }
            rear++;/*队尾指针自增*/
            queue[rear] = temp;/*将新节点地址或空节点地址入队*/
            if (rear == 1)/*若rear为1，则此节点为根节点*/
                root = temp;
            else {
                if (temp != null && queue[front] != null) {/*如果当前节点及其双亲节点都不是空节点*/
                    if (rear % 2 == 0)/*rear为偶数，则新节点应作为左孩子节点*/
                        queue[front].left = temp;
                    else
                        queue[front].right = temp;/*rear为奇数，则新节点应作为右孩子节点*/
                }
                if (rear % 2 == 1)/*rear为奇数，说明两个孩子已经处理完，front指向下一个双亲*/
                    front++;
            }
        }
        return root;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.val = 1;
        TreeNode one = new TreeNode();
        one.val = 2;
        TreeNode two = new TreeNode();
        two.val = 3;
        TreeNode four = new TreeNode();
        four.val = 4;
        TreeNode five = new TreeNode();
        five.val = 5;
        root.left = one;
        root.right = two;
        two.left = four;
        two.right = five;
        SerializeTreeNodeTest test = new SerializeTreeNodeTest();
        List<Integer> result = test.serialize(root);
        System.out.println("串行化结果为：" + result);
        TreeNode main = test.deserialize(Arrays.asList(1, null, 2, null, 3, 4, null, null, null));
        result = test.serialize(main);
        System.out.println("串行化结果为：" + result);
    }
}
