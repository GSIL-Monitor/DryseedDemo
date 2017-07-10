package com.dryseed.dryseedapp.widget.coordinatorLayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.dryseed.dryseedapp.R;

public class WithFloatingActionButton extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout_with_floatingactionbutton);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(findViewById(R.id.contentView), "Snackbar", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
