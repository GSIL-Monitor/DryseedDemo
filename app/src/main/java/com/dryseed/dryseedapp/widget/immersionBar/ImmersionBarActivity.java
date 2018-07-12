package com.dryseed.dryseedapp.widget.immersionBar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.immersionBar.lib.ImmersionBar;

/**
 * @author caiminming
 * <p>
 * https://github.com/gyf-dev/ImmersionBar
 */
public class ImmersionBarActivity extends BaseActivity {
    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersion_bar_layout);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();

        mImmersionBar.statusBarView(R.id.status_bar)
                .fullScreen(true)
                .addTag("PicAndColor")  //给上面参数打标记，以后可以通过标记恢复
                .init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mImmersionBar) {
            mImmersionBar.destroy();
        }
    }
}
