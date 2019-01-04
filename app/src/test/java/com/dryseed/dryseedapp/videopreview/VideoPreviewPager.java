package com.dryseed.dryseedapp.videopreview;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caiminming
 */
public class VideoPreviewPager {
    private static final int PAGE_SIZE = 10;

    /**
     * 用户看到的第一个视频index
     */
    private int mFirstIndex = 0;
    /**
     * 用户看到的最后一个视频index
     */
    private int mLastIndex = 0;

    private List<AbsRowModel> mDatas = new ArrayList<>();

    public void setOriginIndex(int index) {
        mFirstIndex = mLastIndex = index;
    }

    public void pushData(List<AbsRowModel> list) {
        if (null != list && !list.isEmpty()) {
            mDatas.addAll(list);
        }
    }

    public List<AbsRowModel> getNextPageData() {
        if (mDatas.size() < mLastIndex + PAGE_SIZE) {
            return null;
        }

        List<AbsRowModel> list = mDatas.subList(mLastIndex + 1, mLastIndex + PAGE_SIZE + 1);
        mLastIndex = mLastIndex + PAGE_SIZE;
        return list;
    }

    public List<AbsRowModel> getPrePageData() {
        if (mFirstIndex - PAGE_SIZE < 0) {
            return null;
        }

        List<AbsRowModel> list = mDatas.subList(mFirstIndex - PAGE_SIZE, mFirstIndex);
        mFirstIndex = mFirstIndex - PAGE_SIZE;
        return list;
    }

    public int getFirstIndex() {
        return mFirstIndex;
    }

    public int getLastIndex() {
        return mLastIndex;
    }
}
