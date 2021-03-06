package com.dryseed.dryseedapp.designPattern.decorator.demo2;

/**
 * Created by caiminming on 2017/12/27.
 */

public class SpicyNoodleDecorator extends NoodleDecorator {

    public SpicyNoodleDecorator(Noodle noodle) {
        super(noodle);
    }

    @Override
    public void cook() {
        noodle.cook();
        System.out.println("add spicy");
    }
}
