package com.dryseed.dryseedapp.designPattern.mediator;

/**
 * Created by caiminming on 2017/1/24.
 */
public abstract class Colleague {
    protected Mediator mediator;

    public Colleague( Mediator mediator) {
        this.mediator = mediator;
    }
}
