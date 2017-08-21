package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：将已按照升序排列的数组转换为高度平衡的二叉搜索树
 * 创建作者：陈苗
 * 创建时间：2017/8/21 10:32
 */
public class ConvertSortedArrayToBSTTest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int from, int to) {
        if (from > to)
            return null;
        int mid = (from + to) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, from, mid - 1);
        root.right = build(nums, mid + 1, to);
        return root;
    }
}
