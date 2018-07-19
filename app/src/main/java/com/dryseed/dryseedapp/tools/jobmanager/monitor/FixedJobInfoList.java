package com.dryseed.dryseedapp.tools.jobmanager.monitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 固定长度并排序的Job信息列表
 */
public class FixedJobInfoList<E extends JobInfo<E>> {

    private final List<E> list;
    private final int fixedSize;
    private E last;

    public FixedJobInfoList(int fixedSize) {
        this.fixedSize = fixedSize;
        this.list = new ArrayList<>(fixedSize);
    }

    public Collection<E> getCollection() {
        synchronized (list) {
            return new ArrayList<>(list);
        }
    }

    public void add(E e) {
        synchronized (list) {
            if (list.size() >= fixedSize) {
                if (e.compareTo(last) > 0) {
                    list.remove(last);
                    addAndRecordLastLocked(e);
                }
            } else {
                addAndRecordLastLocked(e);
            }
        }
    }

    private void addAndRecordLastLocked(E e) {
        boolean notAddToLast = false;
        for (int i = 0; i < list.size(); i++) {
            if (e.compareTo(list.get(i)) > 0) {
                list.add(i, e);
                last = list.get(list.size() - 1);
                notAddToLast = true;
                break;
            }
        }
        if (!notAddToLast) {
            list.add(e);
            last = e;
        }
    }

}
