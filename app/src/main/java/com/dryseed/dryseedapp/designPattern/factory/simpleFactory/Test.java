package com.dryseed.dryseedapp.designPattern.factory.simpleFactory;

/**
 * Created by User on 2016/12/14.
 */
public class Test {
    public static void main(String[] args)
    {
        //简单工厂模式 (店里卖肉夹馍)
        RoujiaMoStore roujiaMoStore = new RoujiaMoStore(new SimpleRoujiaMoFactory());
        roujiaMoStore.sellRoujiaMo("Suan");
        roujiaMoStore.sellRoujiaMo("Tian");
        roujiaMoStore.sellRoujiaMo("La");
    }
}
