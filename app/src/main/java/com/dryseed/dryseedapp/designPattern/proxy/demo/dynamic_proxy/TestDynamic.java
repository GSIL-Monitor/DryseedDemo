package com.dryseed.dryseedapp.designPattern.proxy.demo.dynamic_proxy;


import com.dryseed.dryseedapp.designPattern.proxy.demo.Shopping;
import com.dryseed.dryseedapp.designPattern.proxy.demo.ShoppingImpl;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author weishu
 * @date 16/1/28
 */
public class TestDynamic {
    public static void main(String[] args) {
        Shopping women = new ShoppingImpl();

        // 正常购物
        System.out.println(Arrays.toString(women.doShopping(100)));

        // 招代理
        women = (Shopping) Proxy.newProxyInstance(
                Shopping.class.getClassLoader(),
                women.getClass().getInterfaces(),
                new ShoppingHandler(women)
        );

        System.out.println(Arrays.toString(women.doShopping(100)));
    }
}
