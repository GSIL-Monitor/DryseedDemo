package com.dryseed.dryseedapp.tools.textToAudio;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TextToAudioActivity extends BaseActivity {
    private final static String ACTION = "com.dryseed.dryseedapp.tools.textToAudio.localBroadcast";

    private VoiceBroadcastReceiver mBroadcastReceiver;
    private IntentFilter mFilter;
    TextToSpeech mTextToSpeech;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_audio_layout);
        ButterKnife.bind(this);

        mBroadcastReceiver = new VoiceBroadcastReceiver();
        mFilter = new IntentFilter();
        mFilter.addAction(ACTION);

        mTextToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    //初始化成功
                    int result = mTextToSpeech.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(TextToAudioActivity.this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TextToAudioActivity.this, "初始化失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.button1)
    void onBtn1Click(View view) {
        //播放单一音频文件
        MediaPlayer player = MediaPlayer.create(this, R.raw.tts_success);
        player.start();
    }

    @OnClick(R.id.button2)
    void onBtn2Click(View view) {
        //连续播放多个音频文件
        Intent intent = new Intent(ACTION);
        intent.putExtra("money", "15693.23");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @OnClick(R.id.button3)
    void onBtn3Click(View view) {
        //TextToSpeech
        mTextToSpeech.speak("To be or not to be.That is a question. 15693.23", TextToSpeech.QUEUE_ADD, null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, mFilter);//官方建议使用
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);//官方建议使用
        super.onDestroy();
    }
}
