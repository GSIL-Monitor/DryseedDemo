package com.dryseed.dryseedapp.designPattern.proxy.dynamic;

/**
 * Created by caiminming on 2017/9/26.
 */

public class Main {

    public static void main(String[] args) {
        LogHandler logHandler = new LogHandler();
        UserManager userManager = (UserManager) logHandler.newProxyInstance(new UserManagerImpl());
        //UserManager userManager=new UserManagerImpl();
        userManager.addUser("1111", "张三");

        userManager.delUser("李四");
    }
}
