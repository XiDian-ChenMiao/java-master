package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：给定一个数字，生成构成所有1到n的二叉搜索树
 * 创建作者：陈苗
 * 创建时间：2017/5/2 16:05
 */
public class UniqueBinarySearchTreesIITest {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 生成函数（动态规划）
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new ArrayList<TreeNode>();
        return generateTrees(1, n);/*根节点分别从1到n进行选择*/
    }

    /**
     * 表示以i为根节点的二叉搜索树
     * @param left
     * @param right
     */
    private List<TreeNode> generateTrees(int left, int right) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        if (left > right) {
            result.add(null);
            return result;
        }
        for (int i = left; i <= right; ++i) {
            List<TreeNode> lefts = generateTrees(left, i - 1);
            List<TreeNode> rights = generateTrees(i + 1, right);
            for (int j = 0; j < lefts.size(); ++j) {
                for (int k = 0; k < rights.size(); ++k) {
                    TreeNode root = new TreeNode(i);
                    root.left = lefts.get(j);
                    root.right = rights.get(k);
                    result.add(root);
                }
            }
        }
        return result;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        List<TreeNode> result = new UniqueBinarySearchTreesIITest().generateTrees(3);
        System.out.println(result.size());
    }
}
