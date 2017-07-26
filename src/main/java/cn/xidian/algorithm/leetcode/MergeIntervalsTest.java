package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 文件描述：给定范围集合，合并区间有重复的集合
 * 创建作者：陈苗
 * 创建时间：2016/11/29 11:08
 */
public class MergeIntervalsTest {

    static class Interval {
        int start, end;
        Interval() {
            start = 0;
            end = 0;
        }
        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }

    /**
     * 合并函数
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null)
            return null;
        if (intervals.size() == 0)
            return new ArrayList<Interval>();
        Object[] objects = intervals.toArray();
        Interval[] datas = new Interval[objects.length];
        for (int i = 0; i < objects.length; i++) {
            datas[i] = (Interval) objects[i];
        }
        Arrays.sort(datas, 0, datas.length, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        List<Interval> result = new ArrayList<Interval>();
        int i = 0, cur;
        while (i < datas.length) {
            Interval data = datas[i];
            for (cur = i + 1; cur < datas.length; cur++) {
                 if (data.end >= datas[cur].start && data.end <= datas[cur].end) {
                     data.end = datas[cur].end;
                 } else if (data.end > datas[cur].end) {
                     continue;
                 } else {
                     break;
                 }
            }
            result.add(data);
            i = cur;
        }
        return result;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        List<Interval> data = new ArrayList<Interval>();
        Interval interval = new Interval(1, 4);
        data.add(interval);
        interval = new Interval(2, 3);
        data.add(interval);
        interval = new Interval(5, 6);
        data.add(interval);
        interval = new Interval(15, 18);
        data.add(interval);
        System.out.println(Arrays.toString(new MergeIntervalsTest().merge(data).toArray()));
    }
}
