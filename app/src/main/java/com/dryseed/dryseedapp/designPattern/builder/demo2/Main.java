package com.dryseed.dryseedapp.designPattern.builder.demo2;

/**
 * Created by caiminming on 2017/1/23.
 */
public class Main {
    public static void main(String[] args) {
        Person person = new Person.Builder().setName("dryseed").setAge(28).build();
        System.out.println(person.name + " " + person.age);
    }

}
