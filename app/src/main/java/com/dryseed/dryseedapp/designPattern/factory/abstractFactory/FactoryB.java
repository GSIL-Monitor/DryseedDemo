package com.dryseed.dryseedapp.designPattern.factory.abstractFactory;

/**
 * Created by caiminming on 2017/1/23.
 */
public class FactoryB implements AbstractFactory {
    @Override
    public IProductA createProductA() {
        return new ProductA();
    }

    @Override
    public IProductB createProductB() {
        return new ProductB();
    }
}
