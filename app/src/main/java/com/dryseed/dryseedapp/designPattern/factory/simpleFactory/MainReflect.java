package com.dryseed.dryseedapp.designPattern.factory.simpleFactory;

/**
 * Created by caiminming on 2017/1/23.
 */
public class MainReflect {
    public static void main(String[] args) {
        FactoryReflect factoryReflect = new FactoryReflect();
        IProduct productA = factoryReflect.create(ProductA.class.getName());
        productA.productName();

        IProduct productB = factoryReflect.create(ProductB.class.getName());
        productB.productName();
    }
}
