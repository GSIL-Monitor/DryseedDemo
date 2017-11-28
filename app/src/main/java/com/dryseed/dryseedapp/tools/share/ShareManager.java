package com.dryseed.dryseedapp.tools.share;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.dryseed.dryseedapp.BuildConfig;
import com.dryseed.dryseedapp.R;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.Tencent;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by caiminming on 2017/11/28.
 */

public class ShareManager {

    private ShareManager() {

    }

    private static class ShareManagerHolder {
        private static ShareManager instance = new ShareManager();
    }

    public static ShareManager getInstance() {
        return ShareManagerHolder.instance;
    }

    public void shareQQ(Activity activity) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);

        String title = "我正在minming的房间房间里玩，快来一起连麦嗨~";
        String summary = "HeyHey，全新语音交友软件，游戏玩家开黑最爱";
        String targetUrl = "http://api.heyheytalk.com/Share?heyID=21871&roomID=21871";
        String imageUrl = "http://file.battleofballs.com/heyhey_10140_1511769010_1.jpg";

        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);//经测试，有些手机和电脑上不显示摘要
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl);
        try {
            Tencent.createInstance(BuildConfig.TENCENT_ID, activity).shareToQQ(activity, params, null);
        } catch (Exception e) {
            Toast.makeText(activity, R.string.shared_failed, Toast.LENGTH_SHORT).show();
        }
    }
}
