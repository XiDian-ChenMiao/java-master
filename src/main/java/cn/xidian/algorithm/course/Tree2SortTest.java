package cn.xidian.algorithm.course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件描述：二叉树排序
 * 创建作者：陈苗
 * 创建时间：2016/10/23 13:55
 */
public class Tree2SortTest {
    private static class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int value) {
            this.val = value;
        }
    }

    /**
     * 将数组插入形成二叉排序树
     * @param source
     */
    private TreeNode insertSort(int[] source) {
        TreeNode root = null;
        for (int i = 0; i < source.length; i++) {
            int value = source[i];
            TreeNode node = new TreeNode(value);
            if (root == null)
                root = node;
            else {
                TreeNode current = root;
                TreeNode parent;
                boolean is_ok = false;
                while (!is_ok) {
                    parent = current;
                    if (value < current.val) {
                        current = current.left;
                        if (current == null) {
                            parent.left = node;
                            is_ok = true;
                        }
                    } else {
                        current = current.right;
                        if (current == null) {
                            parent.right = node;
                            is_ok = true;
                        }
                    }
                }
            }
        }
        return root;
    }

    /**
     * 中序遍历输出有序数据
     * @param root
     */
    private void inOrder(TreeNode root, List<Integer> result) {
        if (root != null) {
            inOrder(root.left, result);
            result.add(root.val);
            inOrder(root.right, result);
        }
    }

    /**
     * 外部调用排序函数
     * @param source
     * @return
     */
    public List<Integer> getSortedResult(int[] source) {
        List<Integer> result = new ArrayList<Integer>();
        inOrder(insertSort(source), result);
        return result;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {3, 5, 2, 4, 1};
        System.out.printf("排序前元数据为%s，排序后结果为%s", Arrays.toString(data), new Tree2SortTest().getSortedResult(data));
    }
}
