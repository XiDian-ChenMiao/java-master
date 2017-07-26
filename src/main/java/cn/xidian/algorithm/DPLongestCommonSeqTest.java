package cn.xidian.algorithm;

/**
 * 文件描述：动态规划解决最长公共子序列的问题
 * NOTE：
 *  最长公共子序列存在的最优子结构性质如下：
 *                |- 0，若i=0或j=0
 *      c[i][j] = |- c[i-1][j-1] + 1，若i，j大于0且Xi=Yj
 *                |- max(c[i-1][j],c[i][j-1])，若i，j大于0且Xi!=Yj
 * 创建作者：陈苗
 * 创建时间：2016年6月14日 15:57
 */
public class DPLongestCommonSeqTest {
    private int[][] result;//存储子序列长度结果的数组
    private char[][] solution;//存储最优解结果的数组

    /**
     * 查找公共子序列结果函数
     * @param X 序列X
     * @param Y 序列Y
     */
    private void longestCommonSeq(char[] X,char[] Y) {
        int m = X.length;
        int n = Y.length;
        initize(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n ; j++) {
                if (X[i] == Y[j]) {
                    result[i + 1][j + 1] = result[i][j] + 1;
                    solution[i + 1][j + 1] = '↖';
                } else if (result[i][j + 1] > result[i + 1][j]) {
                    result[i + 1][j + 1] = result[i][j + 1];
                    solution[i + 1][j + 1] = '↑';
                } else {
                    result[i + 1][j + 1] = result[i + 1][j];
                    solution[i + 1][j + 1] = '←';
                }
            }
        }
    }

    /**
     * 初始化数组函数
     * @param m
     * @param n
     */
    private void initize(int m, int n) {
        result = new int[m + 1][];
        solution = new char[m + 1][];
        for (int i = 0;i < m + 1;i++) {
            result[i] = new int[n + 1];
            solution[i] = new char[n + 1];
        }
        for (int i = 1; i <= m; i++)
            result[i][0] = 0;
        for (int i = 0;i <= n;i++)
            result[0][i] = 0;
    }

    /**
     * 打印结果函数
     * @param X
     * @param i
     * @param j
     */
    private void printLCS(char[] X,int i,int j) {
        if (i == 0 || j == 0)
            return;
        if (solution[i][j] == '↖') {
            printLCS(X,i - 1,j - 1);
            System.out.print(X[i - 1]);
        } else if (solution[i][j] == '↑') {
            printLCS(X,i - 1,j);
        } else {
            printLCS(X,i, j - 1);
        }
    }

    /**
     * 获取最长公共子序列的调用接口函数
     * @param X
     * @param Y
     */
    public void getLongestCommonSeq(char[] X,char[] Y) {
        longestCommonSeq(X,Y);
        printLCS(X,X.length,Y.length);
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        char[] X = "ACG".toCharArray();
        char[] Y = "CG".toCharArray();
        new DPLongestCommonSeqTest().getLongestCommonSeq(X,Y);
    }
}
