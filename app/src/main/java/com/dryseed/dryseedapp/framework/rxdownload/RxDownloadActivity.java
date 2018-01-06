package com.dryseed.dryseedapp.framework.rxdownload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.StorageDirectoryUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import zlc.season.rxdownload3.RxDownload;
import zlc.season.rxdownload3.core.Mission;
import zlc.season.rxdownload3.core.Status;
import zlc.season.rxdownload3.extension.ApkInstallExtension;

import static zlc.season.rxdownload3.helper.UtilsKt.dispose;

public class RxDownloadActivity extends BaseActivity {
    private static final String url = "http://shouji.360tpcdn.com/170918/a01da193400dd5ffd42811db28effd53/com.tencent.mobileqq_730.apk";

    @Bind(R.id.progress)
    ProgressBar mProgressBar;

    @Bind(R.id.percent)
    TextView mPercentText;

    private Disposable disposable;
    private Mission mMission;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rxdownload_layout);
        ButterKnife.bind(this);

        Log.d("MMM", "StorageDirectoryUtil.getDownloadDirectory():" + StorageDirectoryUtil.getDownloadDirectory());
//        disposable = RxDownload.INSTANCE.create(url)
        mMission = new Mission(url, "dryseed.apk", StorageDirectoryUtil.getDownloadDirectory());
        disposable = RxDownload.INSTANCE.create(mMission)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Status>() {
                    @Override
                    public void accept(Status status) throws Exception {
                        Log.d("MMM", "Status : " + status.getDownloadSize() + " - " + status.getTotalSize());
                        if (null != mProgressBar) {
                            mProgressBar.setMax((int) status.getTotalSize());
                            mProgressBar.setProgress((int) status.getDownloadSize());
                        }
                        if (null != mPercentText) {
                            mPercentText.setText(status.percent());
                        }
                    }
                });
    }

    @OnClick(R.id.start)
    void start() {
        RxDownload.INSTANCE.start(mMission).subscribe();
    }

    @OnClick(R.id.stop)
    void stop() {
        RxDownload.INSTANCE.stop(mMission).subscribe();
    }

    @OnClick(R.id.install)
    void install() {
        RxDownload.INSTANCE.extension(mMission, ApkInstallExtension.class).subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose(disposable);
    }
}
