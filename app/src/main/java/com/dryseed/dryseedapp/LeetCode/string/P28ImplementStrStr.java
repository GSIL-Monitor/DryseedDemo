package com.dryseed.dryseedapp.LeetCode.string;

import java.util.Stack;

/**
 * Created by User on 2017/6/15.
 */
public class P28ImplementStrStr {
    /**
     * https://leetcode.com/problems/implement-strstr/#/description
     * <p/>
     * 28. Implement strStr()
     * Implement strStr().
     * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * <p/>
     * 参考：http://m.blog.csdn.net/u012848330/article/details/51620294
     * 这道题，首先我使用的是暴力查找法，用两个指针分别指向needle和haystack，然后移动比较即可，
     * 我自己写了一个版本，感觉写的不够好，然后就看了算法书上的版本，感觉同样是暴力查找，差距还是有的呀，于是贴上书上的代码
     */
    public static void main(String[] args) {
        System.out.println(strStr("123456", "45"));
    }

    public static int strStr(String haystack, String needle) {
        int parentSize = haystack.length();
        int childSize = needle.length();

        if (childSize > parentSize) return -1;

        for (int i = 0; i <= parentSize - childSize; i++) {
            int j;
            for (j = 0; j < childSize; j++) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    break;
                }
            }
            if (j == childSize) {
                return i;
            }
        }

        return -1;
    }
}
