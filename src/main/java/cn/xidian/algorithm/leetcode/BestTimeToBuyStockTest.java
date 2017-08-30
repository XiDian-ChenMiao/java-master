package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：股票的买入与卖出利益最大
 * 创建作者：陈苗
 * 创建时间：2017/8/30 9:29
 */
public class BestTimeToBuyStockTest {
    /**
     * 外部调用函数（只允许至多一笔交易）
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int result = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min)
                min = prices[i];
            if (prices[i] - min > result)
                result = prices[i] - min;
        }
        return result;
    }

    /**
     * 可允许多笔交易
     *
     * @param prices
     * @return
     */
    public int maxProfitMulti(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) sum += prices[i] - prices[i - 1];
        }
        return sum;
    }

    /**
     * 可允许至多两笔交易
     *
     * @param prices
     * @return
     */
    public int maxProfitTwo(int[] prices) {
        if (prices.length == 0) return 0;
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];
        int leftMin = prices[0];
        int rightMax = prices[prices.length - 1];
        int sum = 0;
        /*计算左半段最大收益*/
        for (int i = 1; i < prices.length; i++) {
            leftMin = Math.min(prices[i], leftMin);
            left[i] = Math.max(prices[i] - leftMin, left[i - 1]);
        }
        /*计算右半段最大收益*/
        for (int i = prices.length - 2; i >= 0; i--) {
            rightMax = Math.max(prices[i], rightMax);
            right[i] = Math.max(rightMax - prices[i], right[i + 1]);
        }
        /*找出两次交易最大收益组合*/
        for (int i = 0; i < prices.length; i++) {
            if ((left[i] + right[i]) > sum) sum = left[i] + right[i];
        }
        return sum;
    }

    /**
     * 至多k笔交易
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfitK(int k, int[] prices) {
        if (k > prices.length / 2) {
            int sum = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) sum += prices[i] - prices[i - 1];
            }
            return sum;
        }
        /*初始化买卖股票后剩余金钱的数组*/
        int[] release = new int[k + 1];
        int[] hold = new int[k + 1];
        for (int i = 0; i < k + 1; i++) {
            hold[i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j < k + 1; j++) {
                /*卖出第j笔交易，所剩余的钱*/
                release[j] = Math.max(release[j], hold[j] + prices[i]);
                /*买入第j笔交易，所剩余的钱*/
                hold[j] = Math.max(hold[j], release[j - 1] - prices[i]);
            }
        }
        return release[k];
    }

    public static void main(String[] args) {
        int[] data = {7, 1, 5, 3, 6, 4};
        System.out.println(new BestTimeToBuyStockTest().maxProfit(data));
    }
}
