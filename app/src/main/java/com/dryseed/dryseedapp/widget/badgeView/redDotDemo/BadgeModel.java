package com.dryseed.dryseedapp.widget.badgeView.redDotDemo;

import java.lang.ref.SoftReference;

/**
 * Created by caiminming on 2017/12/18.
 */

public class BadgeModel {
    public static final String DOT_TAG_NO_LINK = "DOT_TAG_NO_LINK";

    private String linkTag;
    private String dotTag;
    private SoftReference<OnUpdateListener> onUpdateListenerSoftReference;

    public BadgeModel(String dotTag, OnUpdateListener onUpdateListener) {
        this(dotTag, DOT_TAG_NO_LINK, onUpdateListener);
    }

    public BadgeModel(String dotTag, String linkTag, OnUpdateListener onUpdateListener) {
        this.dotTag = dotTag;
        this.linkTag = linkTag;
        this.onUpdateListenerSoftReference = new SoftReference<>(onUpdateListener);
    }

    public String getLinkTag() {
        return linkTag;
    }

    public void setLinkTag(String linkTag) {
        this.linkTag = linkTag;
    }

    public String getDotTag() {
        return dotTag;
    }

    public void setDotTag(String dotTag) {
        this.dotTag = dotTag;
    }

    public OnUpdateListener getOnUpdateListener() {
        if(null == onUpdateListenerSoftReference) return null;
        return onUpdateListenerSoftReference.get();
    }

    public interface OnUpdateListener {
        void onUpdate(int ret);
    }
}
