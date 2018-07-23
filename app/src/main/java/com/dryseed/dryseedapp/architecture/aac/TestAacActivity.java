package com.dryseed.dryseedapp.architecture.aac;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.luojilab.component.componentlib.router.ui.UIRouter;

/**
 * 详见aac-component
 *
 * @author caiminming
 */
public class TestAacActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = new Bundle();
        bundle.putString("com.dryseed.dryseedapp.Path", "Architecture/AAC");
        UIRouter.getInstance().openUri(this, "dryseed://aac/main", bundle);
        finish();
    }
}
