package com.dryseed.dryseedapp.LeetCode.sortAlgorithm;

/**
 * Created by caiminming on 2017/10/18.
 * <p>
 * 堆排序是一种树形选择排序方法，它的特点是：在排序的过程中，将array[0，...，n-1]看成是一颗完全二叉树的顺序存储结构，利用完全二叉树中双亲节点和孩子结点之间的内在关系，在当前无序区中选择关键字最大（最小）的元素。
 * <p>
 * 1. 若array[0，...，n-1]表示一颗完全二叉树的顺序存储模式，则双亲节点指针和孩子结点指针之间的内在关系如下：
 * <p>
 * 　　任意一节点指针 i：父节点：i==0 ? null : (i-1)/2
 * <p>
 * 　　　　　　　　　　  左孩子：2*i + 1
 * <p>
 * 　　　　　　　　　　  右孩子：2*i + 2
 * <p>
 * 2. 堆的定义：n个关键字序列array[0，...，n-1]，当且仅当满足下列要求：(0 <= i <= (n-1)/2)
 * <p>
 * 　　　　　　① array[i] <= array[2*i + 1] 且 array[i] <= array[2*i + 2]； 称为小根堆；
 * <p>
 * 　　　　　　② array[i] >= array[2*i + 1] 且 array[i] >= array[2*i + 2]； 称为大根堆；
 * <p>
 * 3. 建立大根堆：
 * <p>
 * 　　n个节点的完全二叉树array[0，...，n-1]，最后一个节点n-1是第(n-1-1)/2个节点的孩子。对第(n-1-1)/2个节点为根的子树调整，使该子树称为堆。
 * <p>
 * 　　对于大根堆，调整方法为：若【根节点的关键字】小于【左右子女中关键字较大者】，则交换。
 * <p>
 * 　　之后向前依次对各节点（(n-2)/2 - 1）~ 0为根的子树进行调整，看该节点值是否大于其左右子节点的值，若不是，将左右子节点中较大值与之交换，交换后可能会破坏下一级堆，于是继续采用上述方法构建下一级的堆，直到以该节点为根的子树构成堆为止。
 * <p>
 * 　　反复利用上述调整堆的方法建堆，直到根节点。
 * <p>
 * 4.堆排序：（大根堆）
 * <p>
 * 　　①将存放在array[0，...，n-1]中的n个元素建成初始堆；
 * <p>
 * 　　②将堆顶元素与堆底元素进行交换，则序列的最大值即已放到正确的位置；
 * <p>
 * 　　③但此时堆被破坏，将堆顶元素向下调整使其继续保持大根堆的性质，再重复第②③步，直到堆中仅剩下一个元素为止。
 * <p>
 * 堆排序算法的性能分析：
 * <p>
 * 　　空间复杂度:o(1)；
 * <p>
 * 　　时间复杂度:建堆：o(n)，每次调整o(log n)，故最好、最坏、平均情况下：o(n*logn);
 * <p>
 * 　　稳定性：不稳定
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

public class TestHeapSort {
    public static void main(String[] args) {
        int[] a = {3, 2, 9, 2, 6, 7, 1, 5};
        sort(a);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i] + " ");
        }
        System.out.println(sb);
    }

    public static void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            createMaxdHeap(nums, nums.length - 1 - i);
            swap(nums, 0, nums.length - 1 - i);
        }
    }

    public static void createMaxdHeap(int[] data, int lastIndex) {
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            // 保存当前正在判断的节点
            int k = i;
            // 若当前节点的子节点存在
            while (2 * k + 1 <= lastIndex) {
                // biggerIndex总是记录较大节点的值,先赋值为当前判断节点的左子节点
                int biggerIndex = 2 * k + 1;
                if (biggerIndex < lastIndex) {
                    // 存在右子节点
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        // 若右子节点值比左子节点值大，则biggerIndex记录的是右子节点的值
                        biggerIndex++;
                    }
                }
                if (data[k] < data[biggerIndex]) {
                    // 若当前节点值比子节点最大值小，则交换2者得值，交换后将biggerIndex值赋值给k
                    swap(data, k, biggerIndex);
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    public static void swap(int[] data, int i, int j) {
        if (i == j) {
            return;
        }
        data[i] = data[i] + data[j];
        data[j] = data[i] - data[j];
        data[i] = data[i] - data[j];
    }
}
