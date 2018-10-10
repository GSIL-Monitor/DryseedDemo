package com.dryseed.dryseedapp.architecture.simpleMvp.base;

import android.os.Bundle;
import android.view.View;

/**
 * Base Presenter (MVP Presenter) for all Business Presenter Impl
 * Created by maoxiang on 2016/8/10.
 */
public interface IBasePresenter {

    void onCreate(Bundle args);

    void onViewCreated(View view, Bundle savedInstanceState);

    void onCreateView(Bundle savedInstanceState);

    void onDestroy();

    void onResume();

    void onPause();

}
