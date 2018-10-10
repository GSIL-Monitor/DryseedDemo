package com.dryseed.dryseedapp.architecture.simpleMvp;

import com.dryseed.dryseedapp.architecture.simpleMvp.base.IBasePresenter;
import com.dryseed.dryseedapp.architecture.simpleMvp.base.IBaseView;

/**
 * @author caiminming
 */
public interface ILoginContract {

    interface ILoginView extends IBaseView<ILoginPresenter> {
        void showLoginDialog();
    }

    interface ILoginPresenter extends IBasePresenter {
        void login(String userName, String password);
    }
}
