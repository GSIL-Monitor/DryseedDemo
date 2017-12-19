package com.dryseed.dryseedapp.widget.badgeView.redDotDemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dryseed.dryseedapp.MyApplication;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by caiminming on 2017/12/19.
 */

public class RedDotManager {
    private RedDotManager() {

    }

    private static class RedDotManagerHolder {
        private static RedDotManager mInstance = new RedDotManager();
    }

    public static RedDotManager getInstance() {
        return RedDotManagerHolder.mInstance;
    }

    private HashMap<String, HashMap<String, BadgeModel.OnUpdateListener>> mDotsHashMap = new HashMap<>(); // <linkTag, HashMap<dotTag, OnUpdateListener>>

    public void setDot(BadgeModel badgeModel) {
        String linkTag = badgeModel.getLinkTag();
        String dotTag = badgeModel.getDotTag();

        HashMap<String, BadgeModel.OnUpdateListener> dotHashMap = mDotsHashMap.get(linkTag);
        if (dotHashMap == null) {
            dotHashMap = new HashMap<>();
            mDotsHashMap.put(linkTag, dotHashMap);
        }
        dotHashMap.put(dotTag, badgeModel.getOnUpdateListener());
    }

    /**
     * 刷新link上的元素
     *
     * @param linkTag
     */
    public void updateDot(String linkTag) {
        //检查更新，遍历所有元素刷新
        int ret = checkUpdate(linkTag);
        HashMap<String, BadgeModel.OnUpdateListener> mDotHashMap = mDotsHashMap.get(linkTag);
        if (null == mDotHashMap || mDotHashMap.isEmpty()) return;
        Iterator iter = mDotHashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            BadgeModel.OnUpdateListener l = (BadgeModel.OnUpdateListener) entry.getValue();
            if (null != l) {
                l.onUpdate(ret);
            } else {
                Log.d("MMM", "OnUpdateListener = null");
            }
        }
    }

    /**
     * 是否可更新
     *
     * @param linkTag
     * @return
     */
    private int checkUpdate(String linkTag) {
        switch (linkTag) {
            case RedDotConstant.LINK_TYPE_CONSERVATION:
                SharedPreferences sp = MyApplication.getInstance().getSharedPreferences(RedDotConstant.LINK_TYPE_CONSERVATION, Context.MODE_PRIVATE);
                return sp.getInt("number", 0);
            default:
                return 0;
        }

    }
}
