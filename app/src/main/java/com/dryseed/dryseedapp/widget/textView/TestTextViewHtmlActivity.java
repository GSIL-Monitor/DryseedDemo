package com.dryseed.dryseedapp.widget.textView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.DPIUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by caiminming on 2017/11/13.
 */

public class TestTextViewHtmlActivity extends AppCompatActivity {

    @Bind(R.id.html_string)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview_html_layout);
        ButterKnife.bind(this);

        textView.setText(generateText());
        textView.setMovementMethod(LinkMovementMethod.getInstance()); // 让链接可点击
    }

    private CharSequence generateText() {
        String text = getDescription();
        if (TextUtils.isEmpty(text)) return "";
        Html.ImageGetter imgGetter = new Html.ImageGetter() {

            @Override
            public Drawable getDrawable(String source) {
                int id = Integer.parseInt(source);
                Drawable d = getResources().getDrawable(id);
                d.setBounds(DPIUtil.dip2px(0), DPIUtil.dip2px(-5), d.getIntrinsicWidth() + DPIUtil.dip2px(2), d.getIntrinsicHeight() - DPIUtil.dip2px(3));
                return d;
            }
        };
        return Html.fromHtml(text, imgGetter, null);
    }


    private String getDescription() {
        StringBuffer sb = new StringBuffer();
        sb.append("连续观看60s得5根 <img src='" + R.drawable.leplayer_reward_bone_small + "'/> ");
        sb.append(" ，分享直播间得10根 <img src='" + R.drawable.leplayer_reward_bone_small + "'/> ");
        sb.append("<a href=\"http://www.baidu.com\">link</a>");
        return sb.toString();
    }
}
