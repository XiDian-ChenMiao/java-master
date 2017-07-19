package cn.algorithm.sordoffer;

import cn.algorithm.sordoffer.common.BinaryTreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 文件描述：二叉树的序列化与反序列化
 * 创建作者：陈苗
 * 创建时间：2017/2/22 16:42
 */
public class SerializeBinaryTreeTest {
    /**
     * 序列化操作
     * @param root
     */
    public String[] serialize(BinaryTreeNode<Integer> root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString().split(",");
    }

    /**
     * 序列化重载函数
     * @param root
     * @param sb
     */
    private void serialize(BinaryTreeNode<Integer> root, StringBuilder sb) {
        if (root == null) {
            sb.append("$,");
            return;
        }
        sb.append(root.getData() + ",");
        serialize(root.getLeftChild(), sb);
        serialize(root.getRightChild(), sb);
    }

    /**
     * 反序列化函数
     * @param serials
     * @return
     */
    public BinaryTreeNode<Integer> deserialize(String[] serials) {
        Queue<String> queue = new LinkedList<String>();
        queue.addAll(Arrays.asList(serials));
        return deserialize(queue);
    }

    /**
     * 反序列化重载函数
     * @param queue
     * @return
     */
    private BinaryTreeNode<Integer> deserialize(Queue<String> queue) {
        String val = queue.poll();
        if ("$".equals(val))
            return null;
        BinaryTreeNode<Integer> node = new BinaryTreeNode<Integer>(Integer.parseInt(val), null, null);
        node.setLeftChild(deserialize(queue));
        node.setRightChild(deserialize(queue));
        return node;
    }


    /**
     * 主程序调用
     * @param args
     */
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1, null, null);
        BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>(2, null, null);
        BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>(3, null, null);
        root.setLeftChild(left);
        root.setRightChild(right);

        SerializeBinaryTreeTest test = new SerializeBinaryTreeTest();
        System.out.println(Arrays.toString(test.serialize(root)));
        BinaryTreeNode<Integer> result = test.deserialize(test.serialize(root));
        System.out.println(result.getData());
    }
}
