package com.dryseed.dryseedapp.listui.demo;

import com.dryseed.dryseedapp.listui.Observable;

/**
 * Created by User on 2017/6/28.
 */
public class ListUIPresenter {
    public void getData(final Observable observable){
        observable.postMainThread("refresh", 1);
    }
}
