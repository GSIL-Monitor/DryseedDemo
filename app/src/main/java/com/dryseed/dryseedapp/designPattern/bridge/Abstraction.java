package com.dryseed.dryseedapp.designPattern.bridge;

/**
 * Created by caiminming on 2017/1/24.
 */
public abstract class Abstraction {

    protected Implementor impl;

    public void setImplementor(Implementor implementor){
        impl = implementor;
    }

    //示例方法
    public void operation(){

        impl.operationImpl();
    }
}
