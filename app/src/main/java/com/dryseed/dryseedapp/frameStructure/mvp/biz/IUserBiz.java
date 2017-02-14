package com.dryseed.dryseedapp.frameStructure.mvp.biz;

public interface IUserBiz {
    public void login(String username, String password, OnLoginListener loginListener);
}
