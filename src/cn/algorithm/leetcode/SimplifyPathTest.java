package cn.algorithm.leetcode;

/**
 * 文件描述：简化路径测试
 * 创建作者：陈苗
 * 创建时间：2016/12/5 11:20
 */
public class SimplifyPathTest {
    /**
     * 简化函数
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        if (path == null)
            return null;
        if ("/../".equals(path))
            return "/";

        return null;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new SimplifyPathTest().simplifyPath("/home/"));
    }
}
