package com.dryseed.dryseedapp.designPattern.builder.demo1;

/**
 * Created by caiminming on 2017/1/23.
 */
public class Main {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
        Product product = builder.retrieveResult();
        System.out.println(product.getPart1());
        System.out.println(product.getPart2());
    }

}
