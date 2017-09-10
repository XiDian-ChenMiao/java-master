package cn.xidian.algorithm.course;

import java.text.MessageFormat;

/**
 * 文件描述：从数组中查找第K大的数
 * 创建作者：陈苗
 * 创建时间：2016年月日 9:26
 */
public class FindKthNumberTest {
    private int[] number;

    /**
     * 构造函数
     * @param number
     */
    public FindKthNumberTest(int[] number) {
        this.number = number;
    }

    /**
     * 划分函数，查找到索引满足数字已经确定的位置
     * @param start
     * @param end
     * @return
     */
    private int partition(int start,int end) {
        int priot = number[start];
        while(start < end) {
            while(start < end && number[end] >= priot)
                end--;
            if(start < end)
                number[start++] = number[end];
            while (start < end && number[start] <= priot)
                start++;
            if(start < end)
                number[end--] = number[start];
        }
        number[start] = priot;
        return start;
    }
    /**
     * 查找函数
     * @param start 起始索引位置
     * @param end 结束索引位置
     * @param k 查找的第k小数字
     * @return 查找到的值
     */
    public int findKthNumber(int start,int end,int k) {
        if(start >= end)
            return number[start];
        else{
            int mid = partition(start,end);
            if(mid + 1 > k) {
                return findKthNumber(start,mid - 1,k);
            } else if(mid + 1 < k){
                return findKthNumber(mid + 1,end,k);
            } else {
                return number[mid];
            }
        }
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {1,2,-3,-5,4,7};
        FindKthNumberTest findKthNumberTest = new FindKthNumberTest(data);
        int k = 6;
        int result = findKthNumberTest.findKthNumber(0,data.length - 1,k);
        System.out.println(MessageFormat.format("数组中第{0}小的数为：{1}",k,result));
    }
}
