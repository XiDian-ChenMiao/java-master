package cn.xidian.algorithm.guigu;

import java.util.Arrays;

/**
 * 文件描述：生成括号对测试
 * 创建作者：陈苗
 * 创建时间：2017/8/8 18:40
 */
public class GenerateBracketTest {

    public void generate(int n) {
        char[] data = new char[n * 2];
        this.generate(n, n, data, 0);
    }

    private void generate(int left, int right, char[] data, int i) {
        if (left < 0 || right < left)
            return;
        if (left == 0 && right == 0)
            System.out.println(Arrays.toString(data));
        else {
            if (left > 0) {
                data[i] = '(';
                generate(left - 1, right, data, i + 1);
            }
            if (right > left) {
                data[i] = ')';
                generate(left, right - 1, data, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        new GenerateBracketTest().generate(3);
    }
}
