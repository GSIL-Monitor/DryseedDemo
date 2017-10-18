

package com.dryseed.dryseedapp.LeetCode.sortAlgorithm;

/**
 * Created by caiminming on 2017/10/18.
 * <p>
 * 归并排序
 * 简介:将两个（或两个以上）有序表合并成一个新的有序表 即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列
 * 一趟归并需要将数组 a[]中相邻的长度为h的有序序列进行两两归并.并将结果放到temp[]中，这需要将待排序列中的所有记录扫描一遍，因此耗费O(n)，而又完全二叉树的深度可知，整个归并排序需要进行（这里写图片描述）次，因此总的时间复杂度为O(nlogn)，而且这是归并排序算法中最好、最坏、平均的时间性能。
 * 由于归并排序在归并过程中需要与原始序列同样数量的存储空间存放归并结果以及递归时深度为这里写图片描述的栈空间，因此空间复杂度为O(n+logn).
 * 另外，对代码进行仔细研究，发现merge函数中有if (a[i] < a[j]) 的语句，说明它需要两两比较，不存在跳跃，因此归并排序是一种稳定的排序算法。
 * 也就是说，归并排序是一种比较占内存，但却效率高且稳定的算法。
 * <p>
 * <p>
 * 最差时间分析	平均时间复杂度	稳定度	空间复杂度
 * 冒泡排序	O(n2)	O(n2)	         稳定	O(1)
 * 快速排序	O(n2)	O(n*log2n)	    不稳定	O(log2n)~O(n)
 * 选择排序	O(n2)	O(n2)	         稳定	O(1)
 * 二叉树排序	O(n2)	O(n*log2n)	    不一定	O(n)
 * 插入排序    O(n2)	O(n2)	         稳定	O(1)
 * 堆排序	O(n*log2n)	O(n*log2n)	    不稳定	O(1)
 * 希尔排序	O	    O	            不稳定	O(1)
 * 归并排序  O(n*log2n)	O(n*log2n)	  稳定	O(n+logn)
 */

public class TestMergeSort {
    public static void main(String[] args) {
        int[] a = {3, 2, 9, 2, 6, 7, 1, 5};
        sort(a, 0, a.length - 1);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i] + " ");
        }
        System.out.println(sb);
    }

    public static int[] sort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边
            sort(nums, low, mid);
            // 右边
            sort(nums, mid + 1, high);
            // 左右归并
            merge(nums, low, mid, high);
        }
        return nums;
    }

    public static void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;

        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = nums[j++];
        }

        // 把新数组中的数覆盖nums数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            nums[k2 + low] = temp[k2];
        }
    }
}










