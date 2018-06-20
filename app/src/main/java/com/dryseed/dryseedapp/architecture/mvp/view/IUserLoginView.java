package com.dryseed.dryseedapp.architecture.mvp.view;


import com.dryseed.dryseedapp.architecture.mvp.IBaseUI;
import com.dryseed.dryseedapp.architecture.mvp.bean.User;

public interface IUserLoginView extends IBaseUI {
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();

}
