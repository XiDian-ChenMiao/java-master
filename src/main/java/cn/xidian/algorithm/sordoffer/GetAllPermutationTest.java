package cn.xidian.algorithm.sordoffer;

/**
 * 文件描述：获取给定字符串的全排列
 * 创建作者：陈苗
 * 创建时间：2016年6月9日 20:44
 */
public class GetAllPermutationTest {
    private static int count = 1;

    /**
     * 判断是否可交换
     * @param data
     * @param start
     * @param end
     * @return
     */
    private boolean isSwap(char[] data,int start,int end) {
        for (int i = start;i < end;i++)
            if (data[start] == data[end])
                return false;
        return true;
    }

    /**
     * 反转指定字符序列
     * @param data
     * @param start
     * @param end
     */
    private void reverse(char[] data,int start,int end) {
        while (start < end) {
            swap(data,start++,end--);
        }
    }

    /**
     * 交换函数
     * @param data
     * @param m
     * @param n
     */
    private void swap(char[] data,int m,int n) {
        char temp = data[m];
        data[m] = data[n];
        data[n] = temp;
    }

    /**
     * 求排列的函数
     * @param data 字符数组
     * @param start 表示从当前选到第几个数
     * @param length 表示共有多少个数
     */
    public void permutation(char[] data,int start,int length) {
        if (start == length) {
            System.out.println("第" + (count++) + "个排列数：" + new String(data));
        } else {
            for (int i = start;i <= length;i++) {
                if (isSwap(data,i,start)) {
                    swap(data,i,start);
                    permutation(data,start + 1,length);
                    swap(data,i,start);
                }
            }
        }
    }

    /**
     * 是否存在下一个排列
     * @param data
     * @return
     */
    public boolean nextPermutation(char[] data) {
        int p,q,find,end = data.length - 1;
        if (end == 0)
            return false;
        p = end;
        while (p != 0) {
            q = p;
            p--;
            if (data[p] < data[q]) {//找降序的相邻两数，前一个数即替换数
                int index = end;
                int min = Integer.MAX_VALUE;
                find = end;
                while (index > p) {//从后向前找比替换点大的第一个最小的数
                    if (data[index] > data[p]) {
                        if (data[index] < min) {
                            min = data[index];
                            find = index;
                        }
                    }
                    index--;
                }
                swap(data,p,find);
                reverse(data,p,end);//替换点后的数全部反转
                return true;
            }
        }
        reverse(data,0,end);//如果不存在下一个排列，则将其全部反转并返回false
        return false;
    }
    /**
     * 主函数
     * NOTE:
     * 1、全排列就是从第一个数字起每个数分别与它后面的数字交换。
     * 2、去重的全排列就是从第一个数字起每个数分别与它后面非重复出现的数字交换。
     * 3、全排列的非递归就是由后向前找替换数和替换点，然后由后向前找第一个比替换数大的数与替换数交换，最后颠倒替换点后的所有数据。
     * @param args
     */
    public static void main(String[] args) {
        GetAllPermutationTest test = new GetAllPermutationTest();
        String data = "aabc";
        test.permutation(data.toCharArray(),0,data.length() - 1);
    }
}
