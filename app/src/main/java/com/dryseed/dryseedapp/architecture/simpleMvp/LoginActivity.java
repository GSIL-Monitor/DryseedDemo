package com.dryseed.dryseedapp.architecture.simpleMvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author caiminming
 */
public class LoginActivity extends Activity implements ILoginContract.ILoginView {

    private ILoginContract.ILoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new LoginPresenter(this);
    }

    @Override
    public void showLoginDialog() {
        mPresenter.login("ds", "ds");
    }

    @Override
    public void setPresenter(ILoginContract.ILoginPresenter presenter) {
        mPresenter = presenter;
    }
}
