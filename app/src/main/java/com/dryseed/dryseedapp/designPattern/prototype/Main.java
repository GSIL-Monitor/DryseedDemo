package com.dryseed.dryseedapp.designPattern.prototype;

/**
 * Created by caiminming on 2017/1/23.
 */
public class Main {
    public static void main(String[] args){
        Resume a = new Resume();
        a.setName("dryseed");
        a.setAge("27");
        a.setWork("2017-2018", "JD");

        Resume b = (Resume) a.clone();
        b.setWork("2015-2017", "Tencent");

        System.out.println(a.toString() + "\n" + b.toString());

        ResumeDeepCopy c = new ResumeDeepCopy();
        c.setName("dryseed");
        c.setAge("27");
        c.setWork("2017-2018", "JD");

        ResumeDeepCopy d = (ResumeDeepCopy) c.clone();
        d.setWork("2015-2017", "Tencent");

        System.out.println(c.toString() + "\n" + d.toString());
    }
}
