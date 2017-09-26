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
        System.out.println(sb); //1 3 5 6 8 9
    }

    public static void sort(int[] args, int start, int end) {
        if (end - start > 1) {
            int mid = dividerAndChange(args, start, end);
            sort(args, start, mid);
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





























    /*public static int dividerAndChange(int[] args, int start, int end) {
        //标准值
        int pivot = args[start];
        while (start < end) {
            // 从右向左寻找，一直找到比参照值还小的数值，进行替换
            // 这里要注意，循环条件必须是 当后面的数 小于 参照值的时候
            // 我们才跳出这一层循环
            while (start < end && args[end] >= pivot)
                end--;
            if (start < end) {
                swap(args, start, end); // end 换到 start
                start++;
            }
            // 从左向右寻找，一直找到比参照值还大的数组，进行替换
            while (start < end && args[start] < pivot)
                start++;
            if (start < end) {
                swap(args, end, start); // start 换到 end
                end--;
            }
        }
        args[start] = pivot;
        return start;
    }

    public static void sort(int[] args, int start, int end) {
        //当分治的元素大于1个的时候，才有意义
        if (end - start > 1) {
            int mid = 0;
            mid = dividerAndChange(args, start, end);

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < args.length; i++) {
                sb.append(args[i] + " ");
            }
            System.out.println(args[mid]);
            System.out.println(sb);

            // 对左部分排序
            sort(args, start, mid);
            // 对右部分排序
            sort(args, mid + 1, end);
        }
    }

    private static void swap(int[] args, int fromIndex, int toIndex) {
        args[fromIndex] = args[toIndex];
    }*/
}
