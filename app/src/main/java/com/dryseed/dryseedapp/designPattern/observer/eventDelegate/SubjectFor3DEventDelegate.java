package com.dryseed.dryseedapp.designPattern.observer.eventDelegate;


import com.dryseed.dryseedapp.designPattern.observer.interfaces.Observer;
import com.dryseed.dryseedapp.designPattern.observer.interfaces.Subject;
import com.dryseed.dryseedapp.designPattern.observer.interfaces.SubjectEventDelegate;

import java.util.ArrayList;

/**
 * Created by jingbin on 2016/10/21.
 * 接下来3D服务号的实现类：
 */

public class SubjectFor3DEventDelegate implements SubjectEventDelegate {

    private ArrayList<Observer> observers = new ArrayList<>();

    /**
     * 3D 彩票的号码
     */
    private String msg;

    @Override
    public void notifyObservers() {
        try {
            this.getEventHandler().notifyX();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addObserver(Object object, String methodName, Object... args) {
        this.getEventHandler().addEvent(object, methodName, args);
    }

    /**
     * 主题更新信息
     */
    public void setMsg(String msg) {
        this.msg = msg;
        notifyObservers();
    }

    EventHandler eventHandler = new EventHandler();

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

}
