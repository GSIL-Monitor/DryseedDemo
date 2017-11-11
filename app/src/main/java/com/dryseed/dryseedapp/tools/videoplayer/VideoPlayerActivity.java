package com.dryseed.dryseedapp.tools.videoplayer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.dryseed.dryseedapp.R;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2017/11/11.
 */
public class VideoPlayerActivity extends AppCompatActivity {

    @Bind(R.id.video_view)
    VideoView mVideoView;

    MediaController mMediaController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_view_layout);
        ButterKnife.bind(this);

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/11.mp4";
        Log.d("MMM", "" + fileIsExists(path));


        /**
         * 本地视频播放
         */
        //mVideoView.setVideoPath(path);

        /**
         * 网络播放
         */
        mVideoView.setVideoURI(Uri.parse("http://flv2.bn.netease.com/videolib3/1604/28/fVobI0704/SD/fVobI0704-mobile.mp4"));

        //使用MediaController控制视频播放
        mMediaController = new MediaController(this);
        //设置VideoView与MediaController关联
        mVideoView.setMediaController(mMediaController);
        mMediaController.setMediaPlayer(mVideoView);

    }

    //判断文件是否存在
    public boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
