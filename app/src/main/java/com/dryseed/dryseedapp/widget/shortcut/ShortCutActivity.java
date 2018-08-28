package com.dryseed.dryseedapp.widget.shortcut;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

/**
 * @author caiminming
 */
public class ShortCutActivity extends BaseActivity {
    private final String SHORT_CUT_NAME = "ShortCut";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortcut_layout);
    }

    public void installShortCut(View view) {
        Intent launchIntent = new Intent(this, ShortCutActivity.class);
        launchIntent.setClassName(this, this.getClass().getName());

        ShortCutUtils.installShortCut(this, SHORT_CUT_NAME, R.drawable.city3, launchIntent);
    }

    public void delShortCut(View view) {
        Intent launchIntent = new Intent(this, ShortCutActivity.class);
        launchIntent.setClassName(this, this.getClass().getName());

        ShortCutUtils.deleteShortCut(this, SHORT_CUT_NAME, launchIntent, false);
    }

    public void isShortcutExist(View view) {
        boolean isExist = ShortCutUtils.isShortcutExist(this, SHORT_CUT_NAME);
        ToastUtils.showShort("isShortcutExist : " + isExist);
    }
}
