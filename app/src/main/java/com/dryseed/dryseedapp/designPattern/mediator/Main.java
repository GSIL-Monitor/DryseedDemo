package com.dryseed.dryseedapp.designPattern.mediator;

/**
 * Created by caiminming on 2017/1/24.
 */
public class Main {
    public static void main(String args[]){
        ConcreteMediator concreteMediator = new ConcreteMediator();

        ConcreteColleague1 colleague1 = new ConcreteColleague1(concreteMediator);
        ConcreteColleague2 colleague2 = new ConcreteColleague2(concreteMediator);

        concreteMediator.setConcreteColleague1(colleague1);
        concreteMediator.setConcreteColleague2(colleague2);

        colleague1.send("吃过饭了吗？");
        colleague2.send("没有呢");
    }
}
