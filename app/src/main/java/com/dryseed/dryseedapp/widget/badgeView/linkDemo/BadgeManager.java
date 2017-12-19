package com.dryseed.dryseedapp.widget.badgeView.linkDemo;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by caiminming on 2017/12/18.
 */

public class BadgeManager {
    private BadgeManager() {

    }

    private static class BadgemanagerHolder {
        private static BadgeManager mInstance = new BadgeManager();
    }

    public static BadgeManager getInstance() {
        return BadgemanagerHolder.mInstance;
    }

    private HashMap<String, ArrayList<String>> mDotsHashMap = new LinkedHashMap<>(); // <linkTag, List<dotTag>>
    private HashMap<String, HashMap<String, BadgeModel.OnUpdateListener>> mDotLinkHashMap = new HashMap<>(); //<dotTag, HashMap<linkTag, OnUpdateListener>>

    public void setDot(BadgeModel badgeModel) {
        String linkTag = badgeModel.getLinkTag();
        String dotTag = badgeModel.getDotTag();

        HashMap<String, BadgeModel.OnUpdateListener> links = mDotLinkHashMap.get(dotTag);
        if (links == null) {
            links = new HashMap<>();
            mDotLinkHashMap.put(dotTag, links);
        }
        links.put(linkTag, badgeModel.getOnUpdateListener());

        ArrayList<String> dotTags = mDotsHashMap.get(linkTag);
        if (dotTags == null) {
            dotTags = new ArrayList<>();
            mDotsHashMap.put(linkTag, dotTags);
        }
        if (!dotTags.contains(dotTag)) {
            dotTags.add(dotTag);
        }
    }

    private void deleteDot(BadgeModel badgeModel) {
        deleteDot(badgeModel.getDotTag(), badgeModel.getLinkTag(), badgeModel.getOnUpdateListener(), false);
    }

    private void deleteDot(String dotTag, String linkTag, BadgeModel.OnUpdateListener onUpdateListener, boolean isForce) {
        if (mDotLinkHashMap == null || mDotLinkHashMap.isEmpty()) return;
        if (mDotsHashMap == null || mDotsHashMap.isEmpty()) return;

        ArrayList<String> dotsInLink = mDotsHashMap.get(linkTag);

        if (dotsInLink == null) return;

        //是否是link中元素
        boolean isLinkType = true;
        if (linkTag.equals(BadgeModel.DOT_TAG_NO_LINK)) {
            isLinkType = false;
        }

        //如果是link中元素，则判断是否是link中最后一个元素
        boolean isLastTag = true;
        if (!isForce && isLinkType) {
            if (dotsInLink.lastIndexOf(dotTag) != dotsInLink.size() - 1) {
                //不是最后一个元素
                isLastTag = false;
            }
        }

        //判断是否在其他link中存在
        ArrayList<String> otherLinks = new ArrayList<>(); //key:linkTag
        HashMap<String, BadgeModel.OnUpdateListener> linksByDot = mDotLinkHashMap.get(dotTag);
        if (linksByDot != null && !linksByDot.isEmpty()) {
            Iterator iter = linksByDot.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                if (key == linkTag) continue;
                otherLinks.add(key);
            }
        }

        //其他link中没有此元素
        //isLastTag : 是最后一个元素才会去删掉自己
        if (isLastTag && otherLinks.isEmpty()) {
            if (isForce) {
                //直接删除的，说明此元素在其他link中不存在，则删除自己
                if (null != onUpdateListener) onUpdateListener.onUpdate();
                removeDotTagInLinks(dotTag, linkTag);
                return;
            }
            //其他link不存在此元素
            if (isLinkType) {
                //删除整个链表元素
                deleteDotsInLink(dotsInLink, linkTag);
            } else {
                //删除这个无link元素
                if (null != onUpdateListener) onUpdateListener.onUpdate();
                removeDotTagInLinks(dotTag, linkTag);
            }
            return;
        }

        //其他link中有此元素
        for (int i = 0; i < otherLinks.size(); i++) {

            String link = otherLinks.get(i);
            ArrayList list = mDotsHashMap.get(link);

            if (link.equals(BadgeModel.DOT_TAG_NO_LINK)) {
                //删除这个无link元素
                BadgeModel.OnUpdateListener l = mDotLinkHashMap.get(dotTag).get(link);
                if (null != l) l.onUpdate();
                removeDotTagInLinks(dotTag, link);
            } else {
                //是否是link中的最后一个
                if (list.lastIndexOf(dotTag) == list.size() - 1) {
                    //是最后一个元素，删除这条链表
                    deleteDotsInLink(list, link);
                } else {
                    //不是最后一个元素，保留
                }
            }
        }

        //其他link中有此元素判断后，还需把自己删掉
        if (isForce) {
            //删除自己
            if (null != onUpdateListener) onUpdateListener.onUpdate();
            removeDotTagInLinks(dotTag, linkTag);
        } else if (isLastTag) {
            if (isLinkType) {
                //删除整个链表元素
                deleteDotsInLink(dotsInLink, linkTag);
            } else {
                //删除这个无link元素
                if (null != onUpdateListener) onUpdateListener.onUpdate();
                removeDotTagInLinks(dotTag, linkTag);
            }
        }
    }

    /**
     * 删除某个link下的dot
     *
     * @param dotTag
     */
    private void removeDotTagInLinks(String dotTag, String link) {
        HashMap<String, BadgeModel.OnUpdateListener> dots = mDotLinkHashMap.get(dotTag);
        dots.remove(link);
        if (dots.isEmpty()) {
            mDotLinkHashMap.remove(dotTag);
        }
    }

    private void deleteDotsInLink(ArrayList<String> dotsInLink, String linkTag) {
        for (int i = 0; i < dotsInLink.size(); i++) {
            String dotTag = dotsInLink.get(i);
            BadgeModel.OnUpdateListener onUpdateListener = mDotLinkHashMap.get(dotTag).get(linkTag);
            if (i != dotsInLink.size() - 1) {
                deleteDot(dotTag, linkTag, onUpdateListener, true);
            } else {
                if (null != onUpdateListener) onUpdateListener.onUpdate();
                removeDotTagInLinks(dotTag, linkTag);
                mDotsHashMap.remove(linkTag);
            }
        }
    }

    public void updateDot(BadgeModel badgeModel) {
        deleteDot(badgeModel);
    }

    public void print() {
        StringBuffer sb = new StringBuffer("mDotsHashMap : \n");
        Iterator iter = mDotsHashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            ArrayList<String> val = (ArrayList) entry.getValue();
            sb.append(key + " : [");
            for (String i : val) {
                sb.append(i + " ");
            }
            sb.append("]\n");
        }
        sb.append("mDotLinkHashMap size :").append(mDotLinkHashMap.size()).append("\n");
        Iterator iter2 = mDotLinkHashMap.entrySet().iterator();
        while (iter2.hasNext()) {
            Map.Entry entry = (Map.Entry) iter2.next();
            String key = (String) entry.getKey();
            HashMap val = (HashMap<String, BadgeModel.OnUpdateListener>) entry.getValue();
            sb.append(key + " : [");
            sb.append(val.size());
            sb.append("] : ");
            Iterator iter3 = val.entrySet().iterator();
            while (iter3.hasNext()) {
                Map.Entry entry3 = (Map.Entry) iter3.next();
                String key3 = (String) entry3.getKey();
                BadgeModel.OnUpdateListener val3 = (BadgeModel.OnUpdateListener) entry3.getValue();
                sb.append(key3 + " : " + val3 + " | ");
            }
            sb.append("\n");
        }

        Log.d("MMM", sb.toString());
    }
}
