package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：通过中序遍历和后序遍历构造二叉树
 * 创建作者：陈苗
 * 创建时间：2017/8/21 9:29
 */
public class ConstructTreeByInAndPostOrderTest {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null || postorder.length == 0
                || inorder.length == 0 || postorder.length != inorder.length)
            return null;
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int ifrom, int ito, int[] postorder, int pfrom, int pto) {
        if (ifrom > ito || pfrom > pto)
            return null;
        int root_val = postorder[pto];/*获取根节点的值*/
        int root_index = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root_val) {
                root_index = i;
                break;
            }
        }
        int len = root_index - ifrom;
        TreeNode root = new TreeNode(root_val);
        root.left = buildTree(inorder, ifrom, ifrom + len - 1, postorder, pfrom, pfrom + len - 1);
        root.right = buildTree(inorder, ifrom + len + 1, ito, postorder, pfrom + len, pto - 1);
        return root;
    }

    public void in(TreeNode root) {
        if (root == null)
            return;
        in(root.left);
        System.out.println(root.val);
        in(root.right);
    }

    public static void main(String[] args) {
        ConstructTreeByInAndPostOrderTest test = new ConstructTreeByInAndPostOrderTest();
        test.in(test.buildTree(new int[]{1,2,3,4}, new int[]{3,4,2,1}));
    }
}
