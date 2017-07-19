package cn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件描述：给出数字字符串，求可能的IP地址构成
 * 创建作者：陈苗
 * 创建时间：2017/5/2 11:26
 */
public class RestoreIPAddressesTest {

    /**
     * 获取构造的可能IP地址外部调用方法
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        if (s.length() < 4 || s.length() > 12)
            return result;
        addDot(s, 3, result, "");
        return result;
    }

    /**
     * 向给定字符串中添加指定数量的小数点
     * @param source
     * @param k
     * @param resultList
     * @param result
     */
    private void addDot(String source, int k, List<String> resultList, String result) {
        if (source.length() <= k)
            return;
        if (source.length() > 3 * (k + 1))
            return;
        if (k == 0) {
            if ((source.charAt(0) == '0' && source.length() > 1) || Integer.parseInt(source) >= 256)
                return;
            resultList.add(result + "." + source);
            return;
        }
        for (int j = 1; j <= source.length(); j++) {
            String temp = source.substring(0, j);
            if (Integer.parseInt(temp) < 256) {
                String str = source.substring(j);
                if (result.length() > 0)
                    temp = result + "." + temp;
                addDot(str, k - 1, resultList, temp);
                if (source.charAt(0) == '0')
                    break;
            } else {
                break;
            }
        }
    }


    /**
     * 主程序
     * @param args
     */
    public static void main(String[] args) {
        String digits = "25525511135";
        System.out.println(Arrays.toString(new RestoreIPAddressesTest().restoreIpAddresses(digits).toArray()));
    }
}
