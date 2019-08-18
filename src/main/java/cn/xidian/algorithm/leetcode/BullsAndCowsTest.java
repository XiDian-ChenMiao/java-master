package cn.xidian.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: BullsAndCowsTest
 * @description: Bulls And Cows (from leetcode No.299)
 * @date 2019-08-18 10:42
 */
public class BullsAndCowsTest {

    public String getHint(String secret, String guess) {
        int bullsCnt = 0, cowsCnt = 0;
        int[] digitsSNum = new int[10];
        int[] digitsGNum = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bullsCnt++;
            } else {
                digitsGNum[guess.charAt(i) - '0']++;
                digitsSNum[secret.charAt(i) - '0']++;
            }
        }
        for (int i = 0; i < digitsSNum.length; i++) {
            cowsCnt += Math.min(digitsSNum[i], digitsGNum[i]);
        }
        return String.format("%dA%dB", bullsCnt, cowsCnt);
    }

    public static void main(String[] args) {
        System.out.println(new BullsAndCowsTest().getHint("1807", "7810"));
        System.out.println(new BullsAndCowsTest().getHint("1123", "0111"));
        System.out.println(new BullsAndCowsTest().getHint("1122", "2211"));
    }
}
