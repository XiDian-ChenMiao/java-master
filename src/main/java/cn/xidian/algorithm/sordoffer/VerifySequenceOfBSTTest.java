package cn.xidian.algorithm.sordoffer;

/**
 * 文件描述：给定整数数组，判断该数组是否为某二叉搜索树的后序遍历结果
 * 创建作者：陈苗
 * 创建时间：2017/2/18 21:50
 */
public class VerifySequenceOfBSTTest {
    /**
     * 判断函数
     * @param seq
     * @param from
     * @param to
     * @return
     */
    public boolean verifySequenceOfBST(int[] seq, int from, int to) {
        if (seq == null)
            return false;
        int root = seq[to];
        int i = from;
        /*在二叉搜索树中左子树的节点小于根节点*/
        for (; i < to; ++i) {
            if (seq[i] > root)
                break;
        }
        /*在二叉搜索树中右子树的节点小于根节点*/
        int j = i;
        for (; j < to; ++j) {
            if (seq[j] < root)
                return false;/*如果存在仍然小于根节点的值，则确定此序列不是某二叉搜索树的后序遍历*/
        }
        boolean left = true;
        if (i > from)
            left = verifySequenceOfBST(seq, from, i - 1);
        boolean right = true;
        if (i < to)
            right = verifySequenceOfBST(seq, i, to - 1);
        return left && right;
    }

    /**
     * 主程序调用
     * @param args
     */
    public static void main(String[] args) {
        int[] seq = {7, 4, 6, 5};
        System.out.println(new VerifySequenceOfBSTTest().verifySequenceOfBST(seq, 0, seq.length - 1));
    }
}
