package cn.xidian.java8;

import java.util.Arrays;

/**
 * 文件描述：Java新特性之Lambda表达式
 * 创建作者：陈苗
 * 创建时间：2017/3/7 17:12
 */
public class LambdaTest {
    public static void main(String[] args) {
        Arrays.asList("DAQINZHIDI".toCharArray()).forEach(e -> System.out.println(e));
    }
}
