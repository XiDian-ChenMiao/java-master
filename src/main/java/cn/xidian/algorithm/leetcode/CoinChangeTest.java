package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: CoinChangeTest
 * @description: 322. Coin Change
 * @date 2020-03-07 21:03
 */
public class CoinChangeTest {

    /**
     * solve coin change problem
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int[] need_coins_cnt = new int[amount + 1];
        for (int i = 1; i < need_coins_cnt.length; i++) {
            need_coins_cnt[i] = amount + 1;
        }
        need_coins_cnt[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    need_coins_cnt[i] = Math.min(need_coins_cnt[i], need_coins_cnt[i - coin] + 1);
                }
            }
        }
        return need_coins_cnt[amount] == (amount + 1) ? -1 : need_coins_cnt[amount];
    }

    public static void main(String[] args) {
        System.out.println(new CoinChangeTest().coinChange(new int[]{186, 419, 83, 408}, 6249));
    }
}
