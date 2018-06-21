package com.dryseed.dryseedapp.architecture.mvp.biz;


import com.dryseed.dryseedapp.architecture.mvp.bean.User;

public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
