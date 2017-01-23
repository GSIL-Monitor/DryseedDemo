package com.dryseed.dryseedapp.designPattern.observer;

import com.dryseed.dryseedapp.designPattern.observer.eventDelegate.ObserverUserByComputer;
import com.dryseed.dryseedapp.designPattern.observer.eventDelegate.ObserverUserByTV;
import com.dryseed.dryseedapp.designPattern.observer.eventDelegate.SubjectFor3DEventDelegate;

/**
 * Created by User on 2016/12/14.
 */
public class TestEventDelegate
{
    public static void main(String[] args)
    {
        //事件委托方式实现
        SubjectFor3DEventDelegate subjectFor3DEventDelegate = new SubjectFor3DEventDelegate();

        ObserverUserByComputer observerUserByComputer = new ObserverUserByComputer();
        ObserverUserByTV observerUserByTV = new ObserverUserByTV();

        subjectFor3DEventDelegate.addObserver(observerUserByComputer, "watchComputer");
        subjectFor3DEventDelegate.addObserver(observerUserByTV, "watchTV");

        subjectFor3DEventDelegate.setMsg("20140420的3D号码是：127");
    }
}
