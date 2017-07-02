package com.dryseed.dryseedapp.listui;

/**
 * 和{@link AListItem 配合使用，传递额外的参数}
 *
 * @author: zhanwei
 * @date: 2016-07-28 11:03
 */
public class WrapBundle {

    public String pageName;

    public String event_id;

    public String event_param;

    public boolean isLastPosition;

    public static WrapBundle build() {
        return new WrapBundle();
    }

    public WrapBundle setPageName(String pageName) {
        this.pageName = pageName;
        return this;
    }

    public WrapBundle setEvent_id(String event_id) {
        this.event_id = event_id;
        return this;
    }

    public WrapBundle setEvent_param(String event_param) {
        this.event_param = event_param;
        return this;
    }

    public WrapBundle setLastPosition(boolean lastPosition) {
        isLastPosition = lastPosition;
        return this;
    }

    public String getPageName() {
        return pageName == null ? "" : pageName;
    }

    public String getEventId() {
        return event_id == null ? "" : event_id;
    }

    public interface ItemHook {
        boolean itemHook();
    }

    public WrapBundle setItemHook(ItemHook itemHook) {
        this.itemHook = itemHook;
        return this;
    }

    private ItemHook itemHook;

    public boolean itemClickHook() {
        if (itemHook == null) return false;
        return itemHook.itemHook();
    }
}
