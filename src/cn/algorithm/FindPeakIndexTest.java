package cn.algorithm;

import java.text.MessageFormat;

/**
 * 文件描述：从一个基本有序的数组（前一段逐渐增大，后一段逐渐缩小）中找出位于极值处的索引
 * 创建作者：陈苗
 * 创建时间：2016年5月19日 21:36
 */
public class FindPeakIndexTest {
    private int[] numbers;

    /**
     * 构造器
     * @param numbers
     */
    public FindPeakIndexTest(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * 寻找极值索引位置函数
     * @param start 起始位置索引
     * @param end 结束位置索引
     * @return 极值索引
     */
    public int findPeakIndex(int start,int end) {
        //如果数组中只出现一个元素，那么这个元素就是极值点
        if(start == end && end == 0)
            return 0;
        int mid = (start + end) / 2;
        if(numbers[mid] > numbers[mid - 1] && numbers[mid] > numbers[mid + 1])
            return mid;
        else {
            if(numbers[mid] > numbers[mid - 1] && numbers[mid] < numbers[mid + 1])
                return findPeakIndex(mid,end);
            else
                return findPeakIndex(start,mid);
        }
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] numbers = {1,8,6,5,4,3,2};
        FindPeakIndexTest findPeakObj = new FindPeakIndexTest(numbers);
        int peakIndex = findPeakObj.findPeakIndex(0,numbers.length - 1);
        System.out.println(MessageFormat.format("数组的极值位于第{0}个元素处，其值为{1}。",peakIndex + 1,numbers[peakIndex]));
    }
}
