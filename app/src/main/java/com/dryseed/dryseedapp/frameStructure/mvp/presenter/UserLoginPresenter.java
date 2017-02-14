package com.dryseed.dryseedapp.frameStructure.mvp.presenter;

import android.os.Handler;

import com.dryseed.dryseedapp.frameStructure.mvp.bean.User;
import com.dryseed.dryseedapp.frameStructure.mvp.biz.IUserBiz;
import com.dryseed.dryseedapp.frameStructure.mvp.biz.OnLoginListener;
import com.dryseed.dryseedapp.frameStructure.mvp.biz.UserBiz;
import com.dryseed.dryseedapp.frameStructure.mvp.view.IUserLoginView;


public class UserLoginPresenter {
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }


}
