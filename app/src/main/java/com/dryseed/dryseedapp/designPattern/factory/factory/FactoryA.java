package com.dryseed.dryseedapp.designPattern.factory.factory;

/**
 * Created by caiminming on 2017/1/23.
 */
public class FactoryA implements IFactory {
    @Override
    public IProduct create() {
        return new ProductA();
    }
}
