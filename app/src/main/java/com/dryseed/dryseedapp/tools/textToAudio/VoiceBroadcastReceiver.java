package com.dryseed.dryseedapp.tools.textToAudio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.Voice;
import android.util.Log;

import java.util.List;
import java.util.Random;

public class VoiceBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String money = intent.getStringExtra("money");
        final List<String> list = new VoiceTemplate()
                .prefix("success")
                .numString(money)
                .suffix("yuan")
                .gen();

        VoiceSpeaker.getInstance().speak(list);
    }


}
