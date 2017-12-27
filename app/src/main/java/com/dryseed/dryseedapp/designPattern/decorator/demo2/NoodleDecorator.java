package com.dryseed.dryseedapp.designPattern.decorator.demo2;

/**
 * Created by caiminming on 2017/12/27.
 */

public abstract class NoodleDecorator implements Noodle {

    protected Noodle noodle;

    public NoodleDecorator(Noodle noodle) {
        this.noodle = noodle;
    }

    @Override
    public void cook() {
        noodle.cook();
    }
}
