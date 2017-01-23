package com.dryseed.dryseedapp.designPattern.factory.simpleFactory;

/**
 * Created by caiminming on 2017/1/23.
 */
public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory();
        IProduct productA = factory.create("A");
        productA.productName();

        IProduct productB = factory.create("B");
        productB.productName();
    }
}
