package cn.xidian.algorithm.leetcode;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: VerifyPreorderSerializationBTTest
 * @description: 331. Verify Preorder Serialization of a Binary Tree
 * @date 2020-03-08 09:23
 */
public class VerifyPreorderSerializationBTTest {

    /**
     * verify the preorder serialization of a binary tree
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        int length = preorder.length();
        int degree = 1;/*root node*/
        for (int i = 0; i < length; i++) {
            if (degree == 0) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                continue;
            } else if (preorder.charAt(i) == '#') {
                degree -= 1;
            } else {
                while (i < length && preorder.charAt(i) != ',') {
                    i++;
                }
                degree += 1;
            }
        }
        return degree == 0;
    }

    public static void main(String[] args) {
        System.out.println(new VerifyPreorderSerializationBTTest().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }
}
