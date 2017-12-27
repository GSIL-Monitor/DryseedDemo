package com.dryseed.dryseedapp.designPattern.adapter;

/**
 * Created by User on 2016/12/14.
 */

/**
 * 适配器模式:
 * 定义：将一个类的接口转换成客户期望的另一个接口，适配器让原本接口不兼容的类可以相互合作。
 * 这个定义还好，说适配器的功能就是把一个接口转成另一个接口。
 * 如题目，手机充电器一般都是5V左右吧，咱天朝的家用交流电压220V，所以手机充电需要一个适配器（降压器）
 * <p>
 * <p>
 * 适配器模式的三个特点：
 * 1    适配器对象实现原有接口[重点]
 * 2    适配器对象组合一个实现新接口的对象（这个对象也可以不实现一个接口，只是一个单纯的对象）
 * 3    对适配器原有接口方法的调用被委托给新接口的实例的特定方法
 */
public class Test {
    public static void main(String[] args) {

        /**
         * 给手机充电
         */
        Mobile mobile = new Mobile();
        V5Power v5Power = new V5PowerAdapter(new V220Power());
        mobile.inputPower(v5Power);
    }
}
