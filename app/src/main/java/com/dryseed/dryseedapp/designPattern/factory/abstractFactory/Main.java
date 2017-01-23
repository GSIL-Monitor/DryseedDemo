package com.dryseed.dryseedapp.designPattern.factory.abstractFactory;

/**
 * Created by caiminming on 2017/1/23.
 */
public class Main {
    public static void main(String[] args) {
        AbstractFactory factoryA = new FactoryA();
        ProductA productA = (ProductA) factoryA.createProductA();
        productA.productName();

        AbstractFactory factoryB = new FactoryB();
        ProductB productB = (ProductB) factoryB.createProductB();
        productB.productName();

    }
}
