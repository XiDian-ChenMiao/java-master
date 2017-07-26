package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 文件描述：
 * 创建作者：陈苗
 * 创建时间：2016/11/29 15:00
 */
public class InsertIntervalTest {

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

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null)
            return null;
        List<Interval> result = new ArrayList<Interval>();
        if (intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }

        Object[] objects = intervals.toArray();
        Interval[] datas = new Interval[objects.length + 1];
        for (int i = 0; i < objects.length; i++) {
            datas[i] = (Interval) objects[i];
        }
        datas[objects.length] = newInterval;
        Arrays.sort(datas, 0, datas.length, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
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
        interval = new Interval(5, 9);
        data.add(interval);
        interval = new Interval(15, 18);
        data.add(interval);
        Interval insertInterval = new Interval(2, 8);
        System.out.println(Arrays.toString(new InsertIntervalTest().insert(data, insertInterval).toArray()));
    }
}
