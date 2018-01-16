package com.dryseed.dryseedapp.practice.answer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;

public class QuizChoiceItemView extends FrameLayout {

    public static final int QUIZ_CHOICE_ITEM_TYPE_SELECT = 0;
    public static final int QUIZ_CHOICE_ITEM_TYPE_ANSWER = 1;
    public static final int QUIZ_CHOICE_ITEM_TYPE_VIEWER = 2;

    private ProgressBar mProgressBar;
    private TextView mText;
    private int mType = QUIZ_CHOICE_ITEM_TYPE_VIEWER;
    private Callback mCallback;

    public QuizChoiceItemView(@NonNull Context context, int type) {
        super(context);
        this.mType = type;
        initView(context);
    }

    public QuizChoiceItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mType = QUIZ_CHOICE_ITEM_TYPE_VIEWER;
    }

    private void initView(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_quiz_choice_view, this);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        mProgressBar.setMax(100);
        mText = (TextView) rootView.findViewById(R.id.textview);

        switch (mType) {
            case QUIZ_CHOICE_ITEM_TYPE_SELECT:
                mProgressBar.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != mCallback) {
                            mCallback.onItemSelect();
                        }
                    }
                });
                break;
            case QUIZ_CHOICE_ITEM_TYPE_ANSWER:
                mProgressBar.setProgress(0);
                mProgressBar.setClickable(false);
                break;
            case QUIZ_CHOICE_ITEM_TYPE_VIEWER:
                mProgressBar.setClickable(false);
                break;
            default:
                break;
        }


    }

    public void setText(String s) {
        mText.setText(s);
    }

    public void setMax(int max) {
        mProgressBar.setMax(max);
    }

    public void setProgress(int progress) {
        mProgressBar.setProgress(progress);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    interface Callback {
        void onItemSelect();
    }
}
