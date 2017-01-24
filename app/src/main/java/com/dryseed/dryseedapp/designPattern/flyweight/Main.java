package com.dryseed.dryseedapp.designPattern.flyweight;

/**
 * Created by caiminming on 2017/1/24.
 */
public class Main {
    public static void main(String[] args) {

        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.factory(new Character('a'));
        fly.operation("First Call");

        fly = factory.factory(new Character('b'));
        fly.operation("Second Call");

        fly = factory.factory(new Character('a'));
        fly.operation("Third Call");

        fly = new UnsharedConcreteFlyweight(new Character('b'));
        fly.operation("Fourth call");
    }
}
