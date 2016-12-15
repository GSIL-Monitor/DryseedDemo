package com.dryseed.dryseedapp.designPattern.factory.factory;

import com.dryseed.dryseedapp.designPattern.factory.simpleFactory.RoujiaMoStore;
import com.dryseed.dryseedapp.designPattern.factory.simpleFactory.SimpleRoujiaMoFactory;

/**
 * Created by User on 2016/12/14.
 */
public class Test {
    public static void main(String[] args)
    {
        // 3.工厂方法模式 (开分店)
        // 定义：定义一个创建对象的接口，但由子类决定要实例化的类是哪一个。工厂方法模式把类实例化的过程推迟到子类。
        XianRoujiaMoStore xianRoujiaMoStore = new XianRoujiaMoStore(new XianSimpleRoujiaMoFactory());// 分店简单工厂
        xianRoujiaMoStore.sellRoujiaMo("Suan");
        xianRoujiaMoStore.sellRoujiaMo("Tian");
        xianRoujiaMoStore.sellRoujiaMo("La");
    }
}
