package com.dryseed.dryseedapp.framework.rxBus.event;

/**
 * Created by wanghao2 on 2017/5/16.
 */

public class MessageEvent {

    private String id;
    private String name;

    public MessageEvent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
