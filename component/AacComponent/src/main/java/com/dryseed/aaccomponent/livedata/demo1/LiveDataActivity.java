package com.dryseed.aaccomponent.livedata.demo1;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.luojilab.router.facade.annotation.RouteNode;

@RouteNode(path = "/livedatademo1", desc = "livedatademo1")
public class LiveDataActivity extends AppCompatActivity {
    private NameViewModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set content view
        Button button = new Button(this);
        button.setText("SetName");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anotherName = "John Doe";
                mModel.getCurrentName().setValue(anotherName);
                //mModel.getCurrentName().postValue(anotherName); //ui thread
            }
        });
        setContentView(button);

        // Get the ViewModel.
        mModel = ViewModelProviders.of(this).get(NameViewModel.class);

        // Create the observer which updates the UI.
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                ToastUtils.showShort(newName);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.getCurrentName().observe(this, nameObserver);
    }

}
