package com.dryseed.dryseedapp.designPattern.decorator.demo2;

/**
 * Created by caiminming on 2017/12/27.
 */

public class Test {
    /*
        Log:
            RiceNoodle
            add beef
            add spicy

        执行顺序： spicyNoodleDecorator.cook() -> beefNoodleDecorator.cook() -> riceNoodle.cook() -> RiceNoodle -> add beef -> add spicy
     */
    public static void main(String[] args) {
        RiceNoodle riceNoodle = new RiceNoodle();
        SpicyNoodleDecorator spicyNoodleDecorator = new SpicyNoodleDecorator(new BeefNoodleDecorator(riceNoodle));
        spicyNoodleDecorator.cook();
    }
}
