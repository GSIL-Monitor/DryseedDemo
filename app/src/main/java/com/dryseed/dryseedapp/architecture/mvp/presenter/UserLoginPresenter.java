package com.dryseed.dryseedapp.architecture.mvp.presenter;

import android.os.Handler;
import android.util.Log;

import com.dryseed.dryseedapp.architecture.mvp.BasePresenter;
import com.dryseed.dryseedapp.architecture.mvp.bean.User;
import com.dryseed.dryseedapp.architecture.mvp.biz.IUserBiz;
import com.dryseed.dryseedapp.architecture.mvp.biz.OnLoginListener;
import com.dryseed.dryseedapp.architecture.mvp.biz.UserBiz;
import com.dryseed.dryseedapp.architecture.mvp.view.IUserLoginView;

/**
 * Presenter层
 * 为了解决内存泄露问题，增加了attach/detach方法
 */
public class UserLoginPresenter extends BasePresenter<IUserLoginView> {
    private IUserBiz mUserBiz;
    private Handler mHandler = new Handler();

    public UserLoginPresenter() {
        this.mUserBiz = new UserBiz();
    }

    public void login() {
        getUI().showLoading();
        mUserBiz.login(getUI().getUserName(), getUI().getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getUI().toMainActivity(user);
                        getUI().hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getUI().showFailedError();
                        getUI().hideLoading();
                    }
                });

            }
        });
    }

    public void clear() {
        getUI().clearUserName();
        getUI().clearPassword();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MMM", "UserLoginPresenter onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MMM", "UserLoginPresenter onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MMM", "UserLoginPresenter onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("MMM", "UserLoginPresenter onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("MMM", "UserLoginPresenter onStop");
    }
}
