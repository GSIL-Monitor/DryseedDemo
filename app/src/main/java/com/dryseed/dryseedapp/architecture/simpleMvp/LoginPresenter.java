package com.dryseed.dryseedapp.architecture.simpleMvp;

import android.os.Bundle;
import android.view.View;

/**
 * @author caiminming
 */
public class LoginPresenter implements ILoginContract.ILoginPresenter {

    private ILoginContract.ILoginView mView;

    public LoginPresenter(ILoginContract.ILoginView view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void login(String userName, String password) {

    }

    @Override
    public void onCreate(Bundle args) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
