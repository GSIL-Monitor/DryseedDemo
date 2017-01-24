package com.dryseed.dryseedapp.designPattern.command;

/**
 * Created by caiminming on 2017/1/24.
 */
public class Main {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Invoker invoker = new Invoker();
        Command command = new ConcreteCommand(receiver);

        invoker.setCommand(command);
        invoker.action();
    }
}
