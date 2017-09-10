package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：求二叉树所有根节点到叶子节点的总和
 * 创建作者：陈苗
 * 创建时间：2017/9/9 10:48
 */
public class SumRootToLeafNumbersTest {
    private int sum = 0;

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        sumNumbers(root, 0);
        return sum;
    }

    private void sumNumbers(TreeNode root, int sumOfRootToLeaf) {
        sumOfRootToLeaf = sumOfRootToLeaf * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += sumOfRootToLeaf;
            return;
        }
        if (root.left != null)
            sumNumbers(root.left, sumOfRootToLeaf);
        if (root.right != null)
            sumNumbers(root.right, sumOfRootToLeaf);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(1);


        root.left = left;
        root.right = right;


        System.out.println(new SumRootToLeafNumbersTest().sumNumbers(root));
    }
}
