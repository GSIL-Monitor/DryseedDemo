package com.dryseed.dryseedapp.frameStructure.mvp.view;


import com.dryseed.dryseedapp.frameStructure.mvp.bean.User;

public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();

}
