package com.dryseed.dryseedapp.designPattern.factory.factory;

/**
 * Created by caiminming on 2017/1/23.
 */
public class Main {
    public static void main(String[] args) {
        IFactory factoryA = new FactoryA();
        IProduct productA = factoryA.create();
        productA.productName();

        IFactory factoryB = new FactoryB();
        IProduct productB = factoryB.create();
        productB.productName();
    }
}
