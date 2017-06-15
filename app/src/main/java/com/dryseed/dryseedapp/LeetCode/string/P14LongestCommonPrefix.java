package com.dryseed.dryseedapp.LeetCode.string;

/**
 * Created by User on 2017/6/15.
 */
public class P14LongestCommonPrefix {
    /**
     * https://leetcode.com/problems/longest-common-prefix/#/description
     * <p>
     * Write a function to find the longest common prefix string amongst an array of strings.
     * <p>
     * 参考：http://blog.csdn.net/u014629433/article/details/51680523
     * 这题的大概意思就是说给你一组字符串找出其中最长的哪个通用的前缀出来。
     * 首先使用前两个对比，得出通用前缀prefix。O(prefix.length)
     * 使用通用前缀根接下来的遍历，得出跟prefix的通用前缀并赋值给他自己O(n)
     * 最后返回prefix
     * 她的时间复杂度是O(prefix.length*n)
     */
    public static void main(String[] args) {
        String[] strs = {"aa", "a", "aaa"};
        System.out.println(method(strs));
    }

    private static String method(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (prefix.length() >= strs[i].length()) {
                prefix = prefix.substring(0, strs[i].length());
            }
            for (int j = 0; j < prefix.length(); j++) {
                if (prefix.charAt(j) != strs[i].charAt(j)) {
                    prefix = prefix.substring(0, j);
                }
            }
        }

        return prefix;
    }
}
