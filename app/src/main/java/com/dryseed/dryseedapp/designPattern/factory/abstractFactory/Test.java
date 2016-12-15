package com.dryseed.dryseedapp.designPattern.factory.abstractFactory;

import com.dryseed.dryseedapp.designPattern.factory.factory.XianRoujiaMoStore;
import com.dryseed.dryseedapp.designPattern.factory.factory.XianSimpleRoujiaMoFactory;

/**
 * Created by User on 2016/12/14.
 */
public class Test {
    public static void main(String[] args)
    {
        // 4.抽象工厂模式 (使用官方提供的原料)
        // 定义：提供一个接口，用于创建相关的或依赖对象的家族，而不需要明确指定具体类。
        XianRoujiaMoTeSeStore xianRoujiaMoTeSeStore = new XianRoujiaMoTeSeStore(new XianSimpleRoujiaMoTeSeFactory());
        xianRoujiaMoTeSeStore.sellRoujiaMo("suan");
    }
}
