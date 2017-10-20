package com.dryseed.dryseedapp.LeetCode.sortAlgorithm;

/**
 * Created by caiminming on 2017/1/3.
 * <p/>
 * <p/>
 * 快速排序
 * <p/>
 * 背景介绍：
 * 又称划分交换排序（partition-exchange sort），一种排序算法，最早由东尼·霍尔提出。
 * 在平均状况下，排序n个项目要Ο(n log n)次比较。在最坏状况下则需要Ο(n2)次比较，但这种状况并不常见。
 * 事实上，快速排序通常明显比其他Ο(n log n)算法更快，因为它的内部循环（inner loop）可以在大部分的架构上很有效率地被实现出来 —– 来自 wikipedia **
 * <p/>
 * <p/>
 * 算法规则：
 * 本质来说，快速排序的过程就是不断地将无序元素集递归分割，一直到所有的分区只包含一个元素为止。
 * 由于快速排序是一种分治算法，我们可以用分治思想将快排分为三个步骤：
 * 1.分：设定一个分割值，并根据它将数据分为两部分
 * 2.治：分别在两部分用递归的方式，继续使用快速排序法
 * 3.合：对分割的部分排序直到完成
 * <p>
 * 算法分析：
 * <p>
 * 平均时间复杂度O（nlogn），最坏时间复杂度O（n*n）,辅助空间O（logn）<每次都要分给一个额外空间，而总共有logn次>
 * 每次分成两段，那么分的次数就是logn了，每一次处理需要n次计算，那么时间复杂度就是nlogn了！
 * 根据平均情况来说是O(nlogn),因为在数据分布等概率的情况下对于单个数据来说在logn次移动后就会被放到正确的位置上了。
 * 最坏是O(n^2).这种情况就是数组刚好的倒序，然后每次去中间元的时候都是取最大或者最小。
 * <p>
 * 稳定性：不稳定。
 * <p>
 * 最差时间分析	平均时间复杂度	稳定度	空间复杂度
 * 冒泡排序	O(n2)	O(n2)	         稳定	O(1)
 * 快速排序	O(n2)	O(n*log2n)	    不稳定	O(log2n)~O(n)
 * 选择排序	O(n2)	O(n2)	         稳定	O(1)
 * 二叉树排序	O(n2)	O(n*log2n)	    不一定	O(n)
 * 插入排序    O(n2)	O(n2)	         稳定	O(1)
 * 堆排序	O(n*log2n)	O(n*log2n)	    不稳定	O(1)
 * 希尔排序	O	    O	            不稳定	O(1)
 */
public class TestQuickSort {

    public static void main(String[] args) {
        int[] a = {3, 2, 9, 2, 6, 7, 1, 5};
        sort(a, 0, a.length - 1);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i] + " ");
        }
        System.out.println(sb);
    }

    public static void sort(int[] args, int start, int end) {
        if (end - start > 0) {
            int mid = dividerAndChange(args, start, end);
            sort(args, start, mid - 1);
            sort(args, mid + 1, end);
        }
    }

    public static int dividerAndChange(int[] args, int start, int end) {
        int piovt = args[start];
        while (start < end) {
            while (start < end && args[end] > piovt) {
                end--;
            }
            if (start < end) {
                args[start] = args[end];
                start++;
            }

            while (start < end && args[start] <= piovt) {
                start++;
            }
            if (start < end) {
                args[end] = args[start];
                end--;
            }
        }
        args[start] = piovt;
        return start;
    }


}
