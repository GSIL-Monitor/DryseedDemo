package com.dryseed.dryseedapp.LeetCode.swordToOffer;

/**
 * Created by caiminming on 2017/7/14.
 * <p>
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * <p>
 * 分析：
 * 思路一：从左向右扫描字符串替换
 * 一个字符替换为三个
 * 每遇到一个空格，空格后面所有字符向右移动两个位置
 * 字符串长度为n， 对每个空格而言，需要移动后面O(n)个字符
 * 算法的时间复杂度为O(n^2)
 * 思路二：从右到左扫描字符串替换
 * 统计空格数，字符串长度增加 空格数 * 2
 * 维持两个指针p1, p2
 * p1指向原字符串长度末尾， p2指向新字符串长度末尾
 * 当p1遇到空格时， p2 向前移动并替换字符为 '0' '2' '%'
 * 把p1指针指向的字符拷贝到p2指针指向的字符
 * 所有字符都只移动一次，时间复杂度为O(n)
 */

public class P04 {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer(" e  re  a p ");

        replaceBlank(sb);
        System.out.println(sb.toString());
    }

    private static void replaceBlank(StringBuffer sb) {
        if (sb == null || sb.length() == 0) {
            return;
        }
        int spaceNum = 0;
        for(int i=0; i<sb.length(); i++){
            if(sb.charAt(i) == ' '){
                spaceNum++;
            }
        }
        int oldIndex = sb.length() - 1;
        int newIndex = sb.length() + spaceNum * 2 - 1;
        sb.setLength(newIndex + 1);

        while (oldIndex >= 0){
            if(sb.charAt(oldIndex) == ' '){
                sb.setCharAt(newIndex--, '0');
                sb.setCharAt(newIndex--, '2');
                sb.setCharAt(newIndex--, '%');
            } else {
                sb.setCharAt(newIndex--, sb.charAt(oldIndex));
            }
            oldIndex--;
        }
    }
}
