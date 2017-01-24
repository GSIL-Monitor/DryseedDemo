package com.dryseed.dryseedapp.designPattern.mediator;

/**
 * Created by caiminming on 2017/1/24.
 */
//定义具体中介者ConcreteMediator,具体中介者通过协调各同事对象实现协作行为，了解并维护它的各个同事。
public class ConcreteMediator extends Mediator {
    ConcreteColleague1 concreteColleague1;
    ConcreteColleague2 concreteColleague2;

    @Override
    public void send(String message, Colleague colleague) {
        if (colleague==concreteColleague1) {
            concreteColleague2.notify(message);
        } else {
            concreteColleague1.notify(message);
        }
    }

    public ConcreteColleague1 getConcreteColleague1() {
        return concreteColleague1;
    }

    public void setConcreteColleague1(ConcreteColleague1 concreteColleague1) {
        this.concreteColleague1 = concreteColleague1;
    }

    public ConcreteColleague2 getConcreteColleague2() {
        return concreteColleague2;
    }

    public void setConcreteColleague2(ConcreteColleague2 concreteColleague2) {
        this.concreteColleague2 = concreteColleague2;
    }
}
