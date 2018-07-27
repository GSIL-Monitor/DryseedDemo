package com.dryseed.dryseedapp.widget.badgeView.redDotDemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.application.MyApplication;
import com.dryseed.dryseedapp.framework.rxBus.RxBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 红点管理
 * <p>
 * Created by caiminming on 2017/12/19.
 */

public class RedDotManager {
    private RedDotManager() {
        mSingleThreadExecutor = Executors.newSingleThreadExecutor();
    }

    private static class RedDotManagerHolder {
        private static RedDotManager mInstance = new RedDotManager();
    }

    public static RedDotManager getInstance() {
        return RedDotManagerHolder.mInstance;
    }

    private HashMap<String, HashSet<Integer>> mDotsHashMap = new HashMap<>(); // <linkTag, HashSet<dotTag>>
    private ExecutorService mSingleThreadExecutor;

    /**
     * 设置红点路径并显示
     */
    public void setDot(int dotTag, String linkTag) {
        //设置
        HashSet<Integer> dotHashSet = mDotsHashMap.get(linkTag);
        if (dotHashSet == null) {
            dotHashSet = new HashSet<>();
            mDotsHashMap.put(linkTag, dotHashSet);
        }
        dotHashSet.add(dotTag);
    }

    /**
     * 刷新link上的元素
     *
     * @param linkTag
     */
    public void updateDot(final String linkTag) {
        if (null == mSingleThreadExecutor) return;
        mSingleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("MMM", "updateDot : " + Thread.currentThread().getName());
                //检查更新，遍历所有元素刷新
                int ret = checkUpdate(linkTag);
                HashSet<Integer> mDotHashSet = mDotsHashMap.get(linkTag);
                if (null == mDotHashSet || mDotHashSet.isEmpty()) return;
                Iterator iter = mDotHashSet.iterator();
                while (iter.hasNext()) {
                    int dotTag = (int) iter.next();
                    RxBus.getDefault().post(new RedDotEvent(dotTag, ret));
                }
            }
        });
    }

    /**
     * 是否可更新，处理各个linkTag的业务逻辑
     *
     * @param linkTag
     * @return
     */
    private int checkUpdate(String linkTag) {
        switch (linkTag) {
            case RedDotConstant.LINK_TYPE_CONSERVATION:
                SharedPreferences sp = MyApplication.getInstance().getSharedPreferences(RedDotConstant.LINK_TYPE_CONSERVATION, Context.MODE_PRIVATE);
                return sp.getInt("number", RedDotConstant.RED_DOT_TYPE_HIDE);
            default:
                return 0;
        }
    }

    /**
     * 刷新link上的元素，ret直接透传
     *
     * @param linkTag
     * @param ret
     */
    public void updateDotWithRet(String linkTag, int ret) {
        //检查更新，遍历所有元素刷新
        HashSet<Integer> mDotHashSet = mDotsHashMap.get(linkTag);
        if (null == mDotHashSet || mDotHashSet.isEmpty()) return;
        Iterator iter = mDotHashSet.iterator();
        while (iter.hasNext()) {
            int dotTag = (int) iter.next();
            RxBus.getDefault().post(new RedDotEvent(dotTag, ret));
        }
    }

    /**
     * 根据tag获取root下的viewid
     * 注意：提供此方法是为了提高效率，在确定根布局下只有一个红点tag时，可以使用此方法。
     *
     * @param root
     * @param tag
     * @return
     */
    public int getViewIdByTag(ViewGroup root, String tag) {
        View view = root.findViewWithTag(tag);
        return view.getId();
    }

    /**
     * 根据tag获取root下所有viewid
     *
     * @param root
     * @param tag
     * @return
     */
    public ArrayList<Integer> getViewIdsByTag(ViewGroup root, String tag) {
        ArrayList<Integer> redDotViews = new ArrayList<>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                redDotViews.addAll(getViewIdsByTag((ViewGroup) child, tag));
            }

            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                redDotViews.add(child.getId());
            }

        }
        return redDotViews;
    }
}
