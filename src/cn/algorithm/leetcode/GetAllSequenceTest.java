package cn.algorithm.leetcode;

import java.util.*;

/**
 * 文件描述：给定一个数组，求所有可能出入栈的次序
 * 创建作者：陈苗
 * 创建时间：2016/10/19 16:07
 */
public class GetAllSequenceTest {
    /**
     * 获取出入栈序列的外部调用函数
     * @param data
     * @return
     */
    public List<Deque<Integer>> getAllSequence(int[] data) {
        List<Deque<Integer>> result = new ArrayList<Deque<Integer>>();
        Deque<Integer> q = new LinkedList<Integer>();
        Deque<Integer> seq = new LinkedList<Integer>();
        Stack<Integer> s = new Stack<Integer>();
        for (int i = 0; i < data.length; i++) {
            seq.addLast(data[i]);
        }
        generate(data.length, data.length, q, s, seq, result);
        return result;
    }

    /**
     *
     * @param in
     * @param out
     * @param q
     * @param s
     * @param seq
     * @param result
     */
    private void generate(int in, int out, Deque<Integer> q, Stack<Integer> s, Deque<Integer> seq, List<Deque<Integer>> result) {
        if (in == 0 && out == 0) {
            result.add(seq);
            return;
        }
        if (in > 0) {
            s.push(seq.pollFirst());
            generate(in - 1, out, q, s, seq, result);
            seq.addFirst(s.pop());
        }
        if (out > 0 && in < out) {
            q.addLast(s.pop());
            generate(in, out - 1, q, s, seq, result);
            s.push(q.pollLast());
        }
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {1, 2, 4};
        List<Deque<Integer>> result = new GetAllSequenceTest().getAllSequence(data);
        for (Deque<Integer> line : result) {
            for (Integer value : line) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}
