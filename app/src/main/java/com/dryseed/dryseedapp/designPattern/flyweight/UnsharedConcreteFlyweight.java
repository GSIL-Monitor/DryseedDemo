package com.dryseed.dryseedapp.designPattern.flyweight;

/**
 * Created by caiminming on 2017/1/24.
 */
public class UnsharedConcreteFlyweight implements Flyweight {
    private Character intrinsicState = null;

    /**
     * 构造函数，内蕴状态作为参数传入
     *
     * @param state
     */
    public UnsharedConcreteFlyweight(Character state) {
        this.intrinsicState = state;
    }

    /**
     * 外蕴状态作为参数传入方法中，改变方法的行为， 但是并不改变对象的内蕴状态。
     */
    @Override
    public void operation(String state) {
        System.out.println("UnsharedConcreteFlyweight Intrinsic State = " + this.intrinsicState);
        System.out.println("UnsharedConcreteFlyweight Extrinsic State = " + state);
    }

}