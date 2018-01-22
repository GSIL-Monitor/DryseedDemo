package com.dryseed.dryseedapp.practice.answer.widget;

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
import com.dryseed.dryseedapp.practice.answer.constant.AnswerConstants;
import com.dryseed.dryseedapp.practice.answer.entity.QuizChoiceEntity;

public class QuizChoiceItemView extends FrameLayout {

    private ProgressBar mProgressBar;
    private TextView mAnswerText;
    private TextView mCountText;
    private int mType = AnswerConstants.QUIZ_CHOICE_ITEM_TYPE_VIEWER;
    private Callback mCallback;
    private int mTotalCount;

    public QuizChoiceItemView(@NonNull Context context, int type) {
        super(context);
        this.mType = type;
        initView(context);
    }

    public QuizChoiceItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mType = AnswerConstants.QUIZ_CHOICE_ITEM_TYPE_VIEWER;
    }

    private void initView(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.answer_quiz_choice_item_view, this);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        mAnswerText = (TextView) rootView.findViewById(R.id.answer_textview);
        mCountText = (TextView) rootView.findViewById(R.id.count_textview);

        switch (mType) {
            case AnswerConstants.QUIZ_CHOICE_ITEM_TYPE_SELECT:
                mProgressBar.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != mCallback) {
                            mCallback.onItemSelect();
                        }
                        mProgressBar.setSelected(true);
                        mProgressBar.setClickable(false);
                    }
                });
                mProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.quiz_choice_progress_selector));
                break;
            case AnswerConstants.QUIZ_CHOICE_ITEM_TYPE_ANSWER:
                mProgressBar.setProgress(0);
                mProgressBar.setClickable(false);
                break;
            case AnswerConstants.QUIZ_CHOICE_ITEM_TYPE_VIEWER:
                mProgressBar.setClickable(false);
                mProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.quiz_choice_progress_selector));
                break;
            default:
                break;
        }
    }

    public void setChoice(QuizChoiceEntity quizChoiceEntity) {
        if (null == quizChoiceEntity) return;
        if (mType == AnswerConstants.QUIZ_CHOICE_ITEM_TYPE_ANSWER) {
            switch (quizChoiceEntity.getState()) {
                case AnswerConstants.QUIZ_CHOICE_STATE_RIGHT:
                    mProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.quiz_choice_right_progress));
                    break;
                case AnswerConstants.QUIZ_CHOICE_STATE_WRONG:
                    mProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.quiz_choice_wrong_progress));
                    break;
                case AnswerConstants.QUIZ_CHOICE_STATE_DEFAULT:
                    mProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.quiz_choice_other_progress));
                    break;
                default:
                    break;
            }
            setMax(mTotalCount);
            setProgress(quizChoiceEntity.getCount());
            setCountText(quizChoiceEntity.getCount() + "");
        }

        setAnswerText(quizChoiceEntity.getAnswer());
    }

    private void setAnswerText(String s) {
        if (null != mAnswerText) {
            mAnswerText.setText(s);
        }
    }

    private void setCountText(String s) {
        if (null != mCountText) {
            mCountText.setText(s);
        }
    }

    private void setMax(int max) {
        if (null != mProgressBar) {
            mProgressBar.setMax(max);
        }
    }

    private void setProgress(int progress) {
        if (null != mProgressBar) {
            mProgressBar.setProgress(progress);
        }
    }

    public void setProgressClickable(boolean b) {
        if (null != mProgressBar) {
            mProgressBar.setClickable(b);
        }
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onItemSelect();
    }

    public int getTotalCount() {
        return mTotalCount;
    }

    public void setTotalCount(int mTotalCount) {
        this.mTotalCount = mTotalCount;
    }
}
