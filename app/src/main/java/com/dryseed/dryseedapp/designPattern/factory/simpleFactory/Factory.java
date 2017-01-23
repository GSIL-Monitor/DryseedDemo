package com.dryseed.dryseedapp.designPattern.factory.simpleFactory;


/**
 * Created by caiminming on 2017/1/23.
 */
public class Factory {
    public IProduct create(String productName) {
        switch (productName) {
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
            default:
                return null;
        }
    }
}
