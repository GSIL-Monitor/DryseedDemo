package com.dryseed.dryseedapp.designPattern.memento;

/**
 * Created by caiminming on 2017/1/23.
 *
 * 备忘录角色类，备忘录对象将发起人对象传入的状态存储起来。
 */
public class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
