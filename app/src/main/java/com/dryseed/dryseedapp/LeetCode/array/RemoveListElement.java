package com.dryseed.dryseedapp.LeetCode.array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by caiminming on 2017/7/14.
 *
 * 写一段代码，遍历ArrayList，并删除其中所有值为"a"的字符串元素。
 */

public class RemoveListElement {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("a");
        list.add("c");

        removeList(list, "a");
        for (String s : list) {
            System.out.println(s);
        }

    }

    private static void removeList(List list, String target) {
        if (list == null || list.size() == 0) {
            return;
        }

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.equals(target)) {
                iterator.remove();
            }
        }
    }
}
