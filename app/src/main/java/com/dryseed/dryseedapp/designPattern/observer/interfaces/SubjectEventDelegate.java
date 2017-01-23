package com.dryseed.dryseedapp.designPattern.observer.interfaces;


import com.dryseed.dryseedapp.designPattern.observer.eventDelegate.EventHandler;

/**
 * Created by jingbin on 2016/10/21.
 * 专题接口,所有的主题必须实现此接口
 */
public interface SubjectEventDelegate {

    /**
     * 通知所有观察者
     */
    public void notifyObservers();


    public void addObserver(Object object, String methodName, Object... args);

}
