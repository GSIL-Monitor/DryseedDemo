package com.dryseed.dryseedapp.LeetCode.array;

/**
 * Created by User on 2017/6/10.
 */
public class P26RemoveDuplicatesfromSortedArray {
    /*
        Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

        Do not allocate extra space for another array, you must do this in place with constant memory.

        For example,
        Given input array nums = [1,1,2],

        Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.

        我们可以将不重复的序列存到数列前面，因为不重复序列的长度一定小于等于总序列，所以不用担心覆盖的问题。
        具体来说，用两个指针，快指针指向当前数组遍历到的位置，慢指针指向不重复序列下一个存放的位置，这样我们一旦遍历到一个不重复的元素，就把它复制到不重复序列的末尾。
        判断重复的方法是记录上一个遍历到的数字，看是否一样。
     */
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 3, 3};
        System.out.print(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int dup = nums[0];
        int end = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != dup) {
                nums[end] = nums[i];
                dup = nums[i];
                end++;
            }
        }
        return end;
    }
}
