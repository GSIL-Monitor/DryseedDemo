package com.dryseed.aaccomponent.runalone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dryseed.aaccomponent.lifecycle.LifeCycleActivity;
import com.luojilab.component.componentlib.router.ui.UIRouter;

/**
 * @author caiminming
 */
public class AacTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, LifeCycleActivity.class);
        startActivity(intent);
    }
}
