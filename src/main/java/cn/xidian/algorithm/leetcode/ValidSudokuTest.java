package cn.xidian.algorithm.leetcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件描述：验证ValidSudo是否合法
 * 创建作者：陈苗
 * 创建时间：2016/10/24 16:54
 */
public class ValidSudokuTest {
    private static char[][] board = new char[9][9];
    static {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(ValidSudokuTest.class.getClassLoader().getResourceAsStream("config.txt")));
            String data;
            int line = 0;
            while ((data = reader.readLine()) != null) {
                String[] numbers = data.split(" ");
                for (int i = 0; i < 9; i++) {
                    board[line][i] = numbers[i].toCharArray()[0];
                }
                ++line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 验证函数
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Map<Character, Boolean> rowMap = new HashMap<Character, Boolean>();
            Map<Character, Boolean> columnMap = new HashMap<Character, Boolean>();
            Map<Character, Boolean> subMap = new HashMap<Character, Boolean>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (rowMap.containsKey(board[i][j]) && rowMap.get(board[i][j]) == true)
                        return false;
                    rowMap.put(board[i][j], true);
                }
                if (board[j][i] != '.') {
                    if (columnMap.containsKey(board[j][i]) && columnMap.get(board[j][i]) == true)
                        return false;
                    columnMap.put(board[j][i], true);
                }
                if (board[i / 3 * 3 + j / 3][i % 3 * 3 + j % 3] != '.') {
                    if (subMap.containsKey(board[i / 3 * 3 + j / 3][i % 3 * 3 + j % 3]) && subMap.get(board[i / 3 * 3 + j / 3][i % 3 * 3 + j % 3]) == true)
                        return false;
                    subMap.put(board[i / 3 * 3 + j / 3][i % 3 * 3 + j % 3], true);
                }
            }
        }
        return true;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println(new ValidSudokuTest().isValidSudoku(board));
    }
}
