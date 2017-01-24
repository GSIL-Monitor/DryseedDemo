package com.dryseed.dryseedapp.designPattern.mediator;

/**
 * Created by caiminming on 2017/1/24.
 */
public class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void send(String message){
        mediator.send(message, this);
    }

    public void notify(String message){
        System.out.println("同事1得到消息："+ message);
    }
}
