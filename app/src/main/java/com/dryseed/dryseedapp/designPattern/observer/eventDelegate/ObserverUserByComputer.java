package com.dryseed.dryseedapp.designPattern.observer.eventDelegate;

import com.dryseed.dryseedapp.designPattern.observer.interfaces.Observer;
import com.dryseed.dryseedapp.designPattern.observer.interfaces.Subject;


/**
 * Created by jingbin on 2016/10/21.
 * 模拟第一个使用者
 */

public class ObserverUserByComputer {

    public void watchComputer() {
        System.out.println("ObserverUserByComputer watchComputer...");
    }
}
