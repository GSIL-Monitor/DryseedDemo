package com.dryseed.dryseedapp.customView.recordAudioView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.DPIUtil;


public class RecordAudioViewActivity extends BaseActivity {
    private int mBtnSize = (int) (DPIUtil.getWidth() / 4.5f);

    private RecordAudioButton mRecordAudioBtn;
    private TextView mRecordAudioText;
    private TypeButton mCancelBtn;
    private TypeButton mConfirmBtn;
    private FrameLayout mRecordAudioRootLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_audio_view_layout);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initViews();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initData() {
    }

    private void initViews() {
        mRecordAudioRootLayout = (FrameLayout) findViewById(R.id.audio_record_root_layout);
        mRecordAudioText = (TextView) findViewById(R.id.audio_record_desc);

        //取消按钮
        mCancelBtn = new TypeButton(this, TypeButton.TYPE_CANCEL, mBtnSize);
        final FrameLayout.LayoutParams btn_cancel_param = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        btn_cancel_param.gravity = Gravity.BOTTOM;
        btn_cancel_param.setMargins((DPIUtil.getWidth() / 4) - mBtnSize / 2, 0, 0, DPIUtil.dip2px(40));
        mCancelBtn.setLayoutParams(btn_cancel_param);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (typeLisenter != null) {
//                    typeLisenter.cancel();
//                }
//                startAlphaAnimation();
            }
        });
        mCancelBtn.setVisibility(View.GONE);
        mRecordAudioRootLayout.addView(mCancelBtn);

        //确认按钮
        mConfirmBtn = new TypeButton(RecordAudioViewActivity.this, TypeButton.TYPE_CONFIRM, mBtnSize);
        FrameLayout.LayoutParams btn_confirm_param = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        btn_confirm_param.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        btn_confirm_param.setMargins(0, 0, (DPIUtil.getWidth() / 4) - mBtnSize / 2, DPIUtil.dip2px(40));
        mConfirmBtn.setLayoutParams(btn_confirm_param);
        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (typeLisenter != null) {
//                    typeLisenter.confirm();
//                }
//                startAlphaAnimation();
            }
        });
        mConfirmBtn.setVisibility(View.GONE);
        mRecordAudioRootLayout.addView(mConfirmBtn);

        //录制按钮
        mRecordAudioBtn = new RecordAudioButton(RecordAudioViewActivity.this, mBtnSize);
        FrameLayout.LayoutParams recordFlp = new FrameLayout.LayoutParams(mBtnSize, mBtnSize);
        recordFlp.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        recordFlp.setMargins(0, 0, 0, DPIUtil.dip2px(40));
        mRecordAudioBtn.setLayoutParams(recordFlp);
        mRecordAudioBtn.setImageResource(R.drawable.aa);
        mRecordAudioRootLayout.addView(mRecordAudioBtn);
        mRecordAudioBtn.setRecordAudioListener(new RecordAudioListener() {
            @Override
            public void recordShort(long time) {

            }

            @Override
            public void recordStart() {
            }

            @Override
            public void recordEnd(long time) {
                startTypeBtnAnimator();
            }

            @Override
            public void recordError() {

            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.slide_out_to_bottom);
    }

    public void startTypeBtnAnimator() {
        //拍照录制结果后的动画
        mRecordAudioBtn.setVisibility(View.GONE);
        mCancelBtn.setVisibility(View.VISIBLE);
        mConfirmBtn.setVisibility(View.VISIBLE);
        mCancelBtn.setClickable(false);
        mConfirmBtn.setClickable(false);
        ObjectAnimator animator_cancel = ObjectAnimator.ofFloat(mCancelBtn, "translationX", DPIUtil.getWidth() / 4, 0);
        ObjectAnimator animator_confirm = ObjectAnimator.ofFloat(mConfirmBtn, "translationX", -DPIUtil.getWidth() / 4, 0);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator_cancel, animator_confirm);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mCancelBtn.setClickable(true);
                mConfirmBtn.setClickable(true);
            }
        });
        set.setDuration(200);
        set.start();
    }
}
