package com.dryseed.dryseedapp.designPattern.proxy.dynamic;

/**
 * Created by caiminming on 2017/9/26.
 */

public interface UserManager {
    public void addUser(String userId, String userName);
    public void delUser(String userId);
    public String findUser(String userId);
    public void modifyUser(String userId, String userName);
}
