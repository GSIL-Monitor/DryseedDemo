package com.dryseed.dryseedapp.tools.share;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dryseed.dryseedapp.R;


/**
 * Created by wanghao2 on 2017/9/14.
 */

public class ShareDialog extends Dialog {

    private LinearLayout mContentLayout;
    private LinearLayout mShareQQBtn;

    private CallBack mCallBack;

    public ShareDialog(Context context) {
        this(context, R.style.Dialog_Common);
    }

    public ShareDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView();
        initDialog();
    }

    public void show() {
        if (isShowing()) return;
        super.show();

        // 设置相关位置，一定要在 show()之后
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);
    }

    private void initView() {
        mContentLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.app_share_dialog, null);
        mShareQQBtn = (LinearLayout) mContentLayout.findViewById(R.id.share_qq);
        mShareQQBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mCallBack) {
                    mCallBack.onShareToQQ();
                }
            }
        });
        setContentView(mContentLayout);
    }

    private void initDialog() {
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BottomDialog_AnimationStyle_Anim);
    }

    public void setCallBack(CallBack callBack) {
        this.mCallBack = callBack;
    }

    public interface CallBack {
        void onShareToWeixin();

        void onShareToQQ();
    }
}
