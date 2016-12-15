package com.dryseed.dryseedapp.designPattern.observer.classs;

import android.util.Log;

import com.dryseed.dryseedapp.designPattern.observer.interfaces.Observer;
import com.dryseed.dryseedapp.designPattern.observer.interfaces.Subject;


/**
 * Created by jingbin on 2016/10/21.
 * 模拟第二个使用者
 */

public class ObserverUser2 implements Observer {

    public ObserverUser2(Subject subject) {
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        System.out.println("ObserverUser2 得到 3D 号码:" + msg + ", 我要告诉舍友们。");
//        Toast.makeText(PatternApplication.getInstance(), "-----ObserverUser2 得到 3D 号码:" + msg, Toast.LENGTH_SHORT).show();
    }
}
