package com.dryseed.dryseedapp.designPattern.mediator;

/**
 * Created by caiminming on 2017/1/24.
 */
public abstract class Mediator {
    public abstract void send(String message, Colleague colleague);
}
