package com.dryseed.dryseedapp.LeetCode.search;

/**
 * Created by User on 2017/7/15.
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] list = {2, 4, 7, 10, 11, 45, 50, 59, 60, 66, 69, 70, 79};
        int i = BinarySearch.binarySearch(list, 2); // Returns 0
        System.out.println(i);

        int j = BinarySearch.binarySearch(list, 11); // Returns 4
        System.out.println(j);
        int k = BinarySearch.binarySearch(list, 12); // Returns –1
        System.out.println(k);
        int l = BinarySearch.binarySearch(list, 1); // Returns –1
        System.out.println(l);
        int m = BinarySearch.binarySearch(list, 3); // Returns –2
        System.out.println(l);
    }

    /**
     * Use binary search to find the key in the list
     */
    public static int binarySearch(int[] list, int key) {
        int low = 0;
        int high = list.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (list[mid] > key) {
                high = mid - 1;
            } else if (list[mid] < key) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
