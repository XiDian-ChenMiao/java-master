package cn.algorithm.leetcode;

/**
 * 文件描述：求给定字符串中最长的不重复子串
 * 创建作者：陈苗
 * 创建时间：2016/7/10 16:21
 */
public class LongSubStringTest {
    /**
     * 获取不重复的最长子串的长度
     * @param s 给定的字符串
     * @return 字串长度
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || "".equals(s.trim()))
            return 0;
        boolean[] flag = new boolean[256];/*用来判断字符串中是否已经出现了相对ASCALL码位置的字符*/
        int temp,max = -1,length = s.length(),start = 0;
        int location;
        for (int i = 0; i < length; i++) {
            char character = s.charAt(i);/*获取指定位置的字符*/
            location = character;/*获取ASCALL码中该字符的相对位置*/
            if(!flag[location]) {/*如果未出现重复情况*/
                flag[location] = true;/*如果未出现此字符，则将其标志位设置为当前已经出现*/
                continue;
            }
            else {/*如果出现重复情况*/
                int j = i - 1;
                /*从当前元素的上一个元素开始查找，直到找到与当前元素冲突的元素的位置*/
                for (; j >= 0; j--) {
                    if (s.charAt(j) == s.charAt(i)) {
                        break;
                    }
                }
                temp = i - start;
                if (j >= start)/*只有当所查找的字符位置在开始位置上或者之后的位置，才能重新设置起始标志*/
                    start = j + 1;
                max = max < temp ? temp : max;/*获取子串中满足题目要求的最大长度*/
            }
        }
        return max > length - start ? max : length - start;/*排除全部内容都不重复，或者只重复前面后面不重复的情况，如abcde或者aabcdf*/
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new LongSubStringTest().lengthOfLongestSubstring("add"));
    }
}
