package com.dryseed.dryseedapp.LeetCode.array;

import java.util.HashMap;

/**
 * Created by User on 2017/6/10.
 */
public class P01TwoSum {
    /*
        Given an array of integers, return indices of the two numbers such that they add up to a specific target.

        You may assume that each input would have exactly one solution, and you may not use the same element twice.

        Example:
        Given nums = [2, 7, 11, 15], target = 9,

        Because nums[0] + nums[1] = 2 + 7 = 9,
        return [0, 1].
     */

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        try {
            int[] i = twoSum(nums, 10);
            System.out.println(i[0] + " " + i[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[] twoSum(int[] nums, int target) throws Exception {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{i, hashMap.get(target - nums[i])};
            }
            hashMap.put(nums[i], i);
        }
        throw new Exception("no answer");
    }
}
