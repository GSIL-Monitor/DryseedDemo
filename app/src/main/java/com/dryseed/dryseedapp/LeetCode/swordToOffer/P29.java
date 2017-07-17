package com.dryseed.dryseedapp.LeetCode.swordToOffer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by caiminming on 2017/7/17.
 */

public class P29 {
    /**
     * 题目：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
     *
     * @param numbers 输入数组
     * @return 找到的数字
     */
    public static int moreThanHalfNum(int[] numbers) {
        if (null == numbers) throw new RuntimeException("error");

        Map<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (maps.containsKey(numbers[i])) {
                maps.put(numbers[i], maps.get(numbers[i]) + 1);
            } else {
                maps.put(numbers[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
            if (entry.getValue() > numbers.length / 2) {
                return entry.getKey();
            }
        }

        throw new RuntimeException("error");
    }

    public static void main(String[] args) {
        // 存在出现次数超过数组长度一半的数字
        int numbers[] = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(moreThanHalfNum(numbers));

        // 出现次数超过数组长度一半的数字都出现在数组的前半部分
        int numbers2[] = {2, 2, 2, 2, 2, 1, 3, 4, 5};
        System.out.println(moreThanHalfNum(numbers2));

        // 出现次数超过数组长度一半的数字都出现在数组的后半部分
        int numbers3[] = {1, 3, 4, 5, 2, 2, 2, 2, 2};
        System.out.println(moreThanHalfNum(numbers3));

        // 只有一个数
        int numbers4[] = {1};
        System.out.println(moreThanHalfNum(numbers4));

        // 输入空指针
        //moreThanHalfNum(null);
        // 不存在出现次数超过数组长度一半的数字
        int numbers5[] = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        moreThanHalfNum(numbers5);

    }
}
