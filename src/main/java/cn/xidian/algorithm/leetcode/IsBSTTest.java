package cn.xidian.algorithm.leetcode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 文件描述：判断给定的树是否为二叉搜索树
 * 创建作者：陈苗
 * 创建时间：2017/8/18 19:44
 */
public class IsBSTTest {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root) {
        List<Integer> temp = new ArrayList<Integer>();
        this.preOrder(root, temp);
        if (temp.size() == 1)
            return true;
        Iterator<Integer> iter = temp.iterator();
        long current_value = Long.MIN_VALUE;
        while (iter.hasNext()) {
            int data = iter.next();
            if (current_value >= data)
                return false;
            current_value = data;
        }
        return true;
    }

    private void preOrder(TreeNode root, List<Integer> temp) {
        if (root == null)
            return;
        preOrder(root.left, temp);
        temp.add(root.val);
        preOrder(root.right, temp);
    }
}
