package cn.xidian.algorithm.course;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 活动类
 */
class Activity implements Comparable<Activity>{
    private int startTime;
    private int finishTime;

    /**
     * 构造函数
     * @param startTime 活动起始时间
     * @param finishTime 活动结束时间
     */
    public Activity(int startTime, int finishTime) {
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    @Override
    public int compareTo(Activity o) {
        return this.finishTime - o.finishTime;
    }
}
/**
 * 文件描述：利用贪心算法解决活动选择问题
 * 创建作者：陈苗
 * 创建时间：2016年月日 16:26
 */
public class ActivitySelectorTest {
    private boolean[] isSelected;
    private Activity[] activities;

    /**
     * 构造函数
     * @param startTime 开始时间集合
     * @param endTime 结束时间集合
     */
    public ActivitySelectorTest(int[] startTime,int[] endTime,int length) {
        Assert.assertEquals(length, startTime.length);
        Assert.assertEquals(length, endTime.length);
        isSelected = new boolean[length];
        activities = new Activity[length];
        for (int i = 0; i < length; i++) {
            activities[i] = new Activity(startTime[i],endTime[i]);
        }
    }

    /**
     * 获取最终选择的活动的结果
     */
    public void getSelectorResult() {
        Arrays.sort(activities);
        greedyActivitySelector();
        printActivities();
    }

    /**
     * 利用贪心算法通过迭代方式求解活动选择
     */
    private void greedyActivitySelector() {
        isSelected[0] = true;//第一个活动一定会被选择
        int k = 0;
        int activitiesCount = activities.length;
        for (int i = 1; i < activitiesCount; i++) {
            if (activities[i].getStartTime() > activities[k].getFinishTime()) {
                isSelected[i] = true;
                k = i;
            }
        }
    }
    /**
     * 打印活动结果
     */
    private void printActivities() {
        for (int i = 0; i < activities.length; i++) {
            if (isSelected[i])
                System.out.println("活动" + (i + 1) + "的起始时间为：" + activities[i].getStartTime() + "，结束时间为：" + activities[i].getFinishTime());
        }
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] startTime = {3,1,0,5,3,5,6,8,8,2,12};
        int[] finishTime = {5,4,6,7,9,9,10,11,12,14,16};

        ActivitySelectorTest test = new ActivitySelectorTest(startTime,finishTime,startTime.length);
        test.getSelectorResult();
    }
}
