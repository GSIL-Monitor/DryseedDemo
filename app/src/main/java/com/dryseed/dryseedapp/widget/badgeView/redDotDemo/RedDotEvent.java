package com.dryseed.dryseedapp.widget.badgeView.redDotDemo;

/**
 * Created by caiminming on 2017/12/21.
 */

public class RedDotEvent {
    public RedDotEvent(int id, int number) {
        this.id = id;
        this.number = number;
    }

    int id;
    int number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
