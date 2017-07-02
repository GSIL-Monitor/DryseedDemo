package com.dryseed.dryseedapp.listui.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.listui.Observable;

import java.security.AccessControlContext;

/**
 * Created by User on 2017/6/28.
 */
public class TestListUIActivity extends Activity {

    Observable mObservable;
    ListUIPresenter mListUIPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listui_layout);
        mListUIPresenter = new ListUIPresenter();
        mListUIPresenter.getData(getObservable());

    }

    private Observable getObservable() {
        if (mObservable != null) return mObservable;
        mObservable = new Observable()
                .subscribe("refresh", new Observable.Action<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Toast.makeText(TestListUIActivity.this, integer + "", Toast.LENGTH_SHORT).show();
                    }

                });
        return mObservable;
    }
}
