package cn.algorithm.sordoffer;

import java.util.Arrays;

/**
 * 文件描述：判断给定序列是否为二叉搜索树序列的类
 * 创建作者：陈苗
 * 创建时间：2016年6月6日 20:21
 */
public class BinarySearchTreeSeqTest {
    /**
     * 判断函数
     *
     * @return 若判断是，则返回true；反之，返回false；
     */
    public boolean verifySequenceOfBST(int[] array) {
        if (array == null || array.length <= 0)
            return false;
        int root = array[array.length - 1];
        int cut = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > root) {
                cut = i + 1;
                break;
            }
        }
        if (cut == 0)
            verifySequenceOfBST(Arrays.copyOfRange(array, 0, array.length - 1));
        else {
            for (int i = cut; i < array.length - 1; i++) {
                if (array[i] <= root)
                    return false;
            }
            verifySequenceOfBST(Arrays.copyOfRange(array, 0, cut));
            verifySequenceOfBST(Arrays.copyOfRange(array, cut, array.length - 1));
        }
        return true;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int data[] = {5, 7, 6, 9, 11, 10};
        BinarySearchTreeSeqTest test = new BinarySearchTreeSeqTest();
        System.out.println("判断后的结果为：" + test.verifySequenceOfBST(data));
    }
}
