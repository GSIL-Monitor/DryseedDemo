package com.dryseed.dryseedapp.sortAlgorithm;

/**
 * Created by caiminming on 2017/1/3.
 *
 * 选择排序
 *
 * 背景介绍：
 * 选择排序（Selection sort）是一种简单直观的排序算法。它的工作原理如下。
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 以此类推，直到所有元素均排序完毕。 —– 来自 wikipedia
 *
 * 算法规则：
 * 将待排序集合(0…n)看成两部分，在起始状态中，一部分为(k..n)的待排序unsorted集合，另一部分为(0…k)的已排序sorted集合,
 * 在待排序集合中挑选出最小元素并且记录下标i，若该下标不等于k，那么 unsorted[i] 与 sorted[k]交换 ，一直重复这个过程，直到unsorted集合中元素为空为止。
 *
 *
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
public class TestSelectionSort {
    public static void main(String[] args) {
        int[] a = {5, 6, 9, 3, 1, 8};
        selectSort(a);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i] + " ");
        }
        System.out.println(sb);
    }

    public static void selectSort(int[] a) {
        int minIndex = 0;
        int temp = 0;
        if ((a == null) || (a.length == 0))
            return;
        for (int i = 0; i < a.length - 1; i++) {
            minIndex = i;//无序区的最小数据数组下标
            for (int j = i + 1; j < a.length; j++) {
                //在无序区中找到最小数据并保存其数组下标
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                //如果不是无序区的最小值位置不是默认的第一个数据，则交换之。
                temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
    }
}
