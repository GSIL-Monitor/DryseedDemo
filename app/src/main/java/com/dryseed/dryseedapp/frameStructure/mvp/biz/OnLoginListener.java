package com.dryseed.dryseedapp.frameStructure.mvp.biz;


import com.dryseed.dryseedapp.frameStructure.mvp.bean.User;

public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
