package com.dryseed.dryseedapp.designPattern.bridge;

/**
 * Created by caiminming on 2017/1/24.
 */
public class ConcreteImplementorA extends Implementor {

    @Override
    public void operationImpl() {
        //具体操作
        System.out.println("operationImpl A");
    }

}
