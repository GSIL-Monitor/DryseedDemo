package com.dryseed.dryseedapp.LeetCode.string;

import java.util.Stack;

/**
 * Created by User on 2017/6/15.
 */
public class P20ValidParentheses {
    /**
     * https://leetcode.com/problems/valid-parentheses/#/description
     * <p/>
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     * <p/>
     * 参考：http://www.cnblogs.com/springfor/p/3869420.html
     * 这道题是一道很常见的老题，记得当时看严蔚敏的数据结构还有数据结构课上面都见过这道题，一道训练栈的基础题。
     * 解题方法是：
     * 一个个检查给的characters，如果是左括号都入栈；如果是右括号，检查栈如果为空，证明不能匹配，如果栈不空，弹出top，与当前扫描的括号检查是否匹配。
     * 全部字符都检查完了以后，判断栈是否为空，空则正确都匹配，不空则证明有没匹配的。
     * 注意：
     * 检查字符是用==，检查String是用.isEqual()，因为String是引用类型，值相等但是地址可能不等。
     */
    public static void main(String[] args) {
        String input = "(({})]";
        System.out.println(isValid(input));
    }

    private static boolean isValid(String s) {
        if (s.length() == 0) return true;
        if (s.length() == 1) return false;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else {
                if (stack.size() == 0) return false;
                char top = stack.pop();
                if (top == '{' && s.charAt(i) != '}') {
                    return false;
                } else if (top == '(' && s.charAt(i) != ')') {
                    return false;
                } else if (top == '[' && s.charAt(i) != ']') {
                    return false;
                }
            }
        }

        return stack.size() == 0;
    }
}
