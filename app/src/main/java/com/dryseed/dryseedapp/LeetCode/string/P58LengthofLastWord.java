package com.dryseed.dryseedapp.LeetCode.string;

/**
 * Created by User on 2017/6/15.
 */
public class P58LengthofLastWord {
    /**
     * https://leetcode.com/problems/length-of-last-word/#/description
     * <p/>
     * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
     * <p/>
     * If the last word does not exist, return 0.
     * <p/>
     * Note: A word is defined as a character sequence consists of non-space characters only.
     * <p/>
     * For example,
     * Given s = "Hello World",
     * return 5.
     * <p/>
     * 参考：http://www.cnblogs.com/springfor/p/3872326.html
     * 这道题主要是考虑一下最后是不是空格，方法是倒着找不是空格的字符并计数，如果遇到空格且计数不是0，说明最后一个单词已经被计数了，所以可以返回了。
     */
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("123 4567 dry "));
    }

    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                count++;
            }
            if (s.charAt(i) == ' ' && count != 0) {
                return count;
            }
        }

        return 0;
    }
}
