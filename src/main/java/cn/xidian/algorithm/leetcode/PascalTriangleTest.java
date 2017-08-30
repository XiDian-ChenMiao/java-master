package cn.xidian.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：杨辉三角
 * 创建作者：陈苗
 * 创建时间：2017/8/28 9:35
 */
public class PascalTriangleTest {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0)
            return result;
        List<Integer> temp = new ArrayList<>();
        temp.add(1);
        result.add(temp);
        if (numRows == 1)
            return result;
        temp = new ArrayList<>();
        temp.add(1);
        temp.add(1);
        result.add(temp);
        if (numRows == 2)
            return result;
        for (int i = 2; i < numRows; i++) {
            List<Integer> pre = result.get(i - 1);
            result.add(generateLine(pre));
        }
        return result;
    }

    /**
     * 根据行索引生成行，要求空间复杂度为O（k）
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        if (rowIndex < 0)
            return result;
        result.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = result.size() - 2; j >= 0; j--) {
                result.set(j + 1, result.get(j) + result.get(j + 1));
            }
            result.add(1);
        }
        return result;
    }

    /**
     * 根据前面的一行生成新的一行
     * @param pre
     * @return
     */
    private List<Integer> generateLine(List<Integer> pre) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        for (int i = 0; i < pre.size() - 1; i++) {
            result.add(pre.get(i) + pre.get(i + 1));
        }
        result.add(1);
        return result;
    }
}
