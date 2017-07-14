package com.dryseed.dryseedapp.LeetCode.sortAlgorithm;

/**
 * Created by caiminming on 2017/1/3.
 *
 * 插入排序（Insertion Sort）
 * 是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 * 时间复杂度:O(n^2);
 *

        最差时间分析	平均时间复杂度	稳定度	空间复杂度
 冒泡排序	O(n2)	O(n2)	         稳定	O(1)
 快速排序	O(n2)	O(n*log2n)	    不稳定	O(log2n)~O(n)
 选择排序	O(n2)	O(n2)	         稳定	O(1)
 二叉树排序	O(n2)	O(n*log2n)	    不一定	O(n)
 插入排序    O(n2)	O(n2)	         稳定	O(1)
 堆排序	O(n*log2n)	O(n*log2n)	    不稳定	O(1)
 希尔排序	O	    O	            不稳定	O(1)

 */
public class TestInsertSort {
    public static void main(String[] args) {
        int[] a = {5, 6, 9, 3, 1, 8};
        insertSort(a);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i] + " ");
        }
        System.out.println(sb);
    }

    public static void insertSort(int[] args) {
        if (args == null || args.length < 2) {
            return;
        }
        for (int i = 1; i < args.length; i++) {
            for (int j = i; j > 0; j--) {
                if (args[j] < args[j - 1]) {
                    int temp = args[j];
                    args[j] = args[j - 1];
                    args[j - 1] = temp;
                } else {
                    //接下来是无用功
                    break;
                }
            }
        }
    }
}
