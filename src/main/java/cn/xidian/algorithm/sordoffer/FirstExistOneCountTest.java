package cn.xidian.algorithm.sordoffer;

/**
 * 文件描述：第一次只出现一次的字符
 * 创建作者：陈苗
 * 创建时间：2016/9/4 19:26
 */
public class FirstExistOneCountTest {
    private int[] counter;
    public FirstExistOneCountTest() {
        counter = new int[256];
    }

    /**
     * 获取在一个字符串中只出现一次的字符
     * @param message 给定的字符串
     * @return
     */
    public char getOneCountChar(String message) {
        if (message == null)
            return '\n';
        for (char c : message.toCharArray()) {
            counter[c] += 1;
        }
        for (char c : message.toCharArray()) {
            if (counter[c] == 1)
                return c;
        }
        return '\n';
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        String message = "abaccdeff";
        System.out.printf("在字符串%s中，第一个只出现过一次的字符为%c", message, new FirstExistOneCountTest().getOneCountChar(message));
    }
}
