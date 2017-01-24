package com.dryseed.dryseedapp.designPattern.bridge;

/**
 * Created by caiminming on 2017/1/24.
 */
public class Main {
    public static void main(String[] args) {
        Abstraction abstraction = new RefinedAbstraction();
        abstraction.setImplementor(new ConcreteImplementorA());
        abstraction.operation();
        abstraction.setImplementor(new ConcreteImplementorB());
        abstraction.operation();
    }
}
