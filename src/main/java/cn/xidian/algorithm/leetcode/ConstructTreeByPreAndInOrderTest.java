package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：通过前序遍历和中序遍历构造二叉树
 * 创建作者：陈苗
 * 创建时间：2017/8/19 9:02
 */
public class ConstructTreeByPreAndInOrderTest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0
                || inorder.length == 0 || preorder.length != inorder.length)
            return null;
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int pfrom, int pto, int[] inorder, int ifrom, int ito) {
        if (pfrom > pto || ifrom > ito)
            return null;
        int root_val = preorder[pfrom];
        int root_index = 0;
        for (int i = ifrom; i <= ito; i++) {
            if (inorder[i] == root_val) {
                root_index = i;
                break;
            }
        }
        int len = root_index - ifrom;
        TreeNode root = new TreeNode(root_val);
        root.left = buildTree(preorder, pfrom + 1, pfrom + len, inorder, ifrom, root_index - 1);
        root.right = buildTree(preorder, pfrom + len + 1, pto, inorder, root_index + 1, ito);
        return root;
    }
}
