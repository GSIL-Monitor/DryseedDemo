package com.dryseed.dryseedapp.tools.downloadManager;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dryseed.dryseedapp.MyApplication;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.StorageDirectoryUtil;
import com.dryseed.dryseedapp.utils.ToastUtil;

import java.io.File;

/**
 * Created by caiminming on 2017/12/27.
 */

public class UpdateDialog extends Dialog {
    private TextView confirmBtn = null;
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (mInfo != null) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (ApkDownloadHelper.getInstance().getDownloadIds().contains(id)) {
                    updateConfirmBtn(mInfo);
                }
            }
        }
    };

    public UpdateDialog(@NonNull Context context) {
        super(context, R.style.Dialog_Common);
        setContentView(R.layout.update_dialog_layout);
        confirmBtn = (TextView) findViewById(R.id.confirm);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.getInstance().registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getInstance().unregisterReceiver(receiver);
    }

    private UpdateInfo mInfo;
    private long clickTime;

    public void setUpdateInfo(final UpdateInfo info) {
        mInfo = info;
        TextView title = (TextView) findViewById(R.id.title);
        TextView summary = (TextView) findViewById(R.id.summary);
        CheckBox abort = (CheckBox) findViewById(R.id.checkbox);
        if (info.isForce()) {
            abort.setVisibility(View.GONE);
            findViewById(R.id.cancel).setVisibility(View.GONE);
            findViewById(R.id.gap).setVisibility(View.GONE);
        } else {
            abort.setVisibility(View.VISIBLE);
        }
        abort.setChecked(info.isAbort());
        abort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                info.setAbort(isChecked);
                //AppPreferences.get().setUpdateInfo(info);
            }
        });
        title.setText("发现新版本:" + info.getVersionName());
        summary.setText(Html.fromHtml(info.getDesc()));
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        updateConfirmBtn(info);
//        isDownloading
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                try {
                    doClick(v, info);
                } catch (Exception e) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    Uri content_url = Uri.parse(info.getUrl());
                    intent.setData(content_url);
                    getContext().startActivity(intent);
                }

            }
        });
    }

    private void doClick(View v, UpdateInfo info) {
//        if (AppConfig.DEBUG) {
//            LogUtil.i("yaocheng", ""+PermissionUtils.checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE));
//        }
//
//        if (AppConfig.DEBUG) {
//            LogUtil.i("yaocheng", ""+PermissionUtils.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)+"  "+getOwnerActivity()+"  "+getContext());
//        }
//
//
//        if (!PermissionUtils.checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ||!
//                PermissionUtils.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            PermissionUtils.requestPermissions(getOwnerActivity(),
//                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                    }, 0);
//            return;
//        }


        long curTime = System.currentTimeMillis();
        if (Math.abs(curTime - clickTime) < 1000) {
            return;
        }
        clickTime = curTime;

        int status = ApkDownloadHelper.getInstance().getDownloadStatus(getContext(), getDownloadPath(info));
        Log.d("MMM", "status : " + status);
        switch (status) {
            case DownloadManager.STATUS_PAUSED:
            case DownloadManager.STATUS_PENDING:
            case DownloadManager.STATUS_RUNNING:
                return;

            case DownloadManager.STATUS_SUCCESSFUL: {
                File f = new File(getDownloadPath(info));
                if (f != null && f.exists()) {
                    ApkDownloadHelper.startInstallActivity(v.getContext(), new File(getDownloadPath(info)));
                } else {
                    //ApkDownloadHelper.getInstance().startDownLoad(v.getContext(), getDownloadPath(info), info.getUrl());
                    updateConfirmBtn(ApkDownloadHelper.getInstance().startDownLoad(v.getContext(), getDownloadPath(info), info.getUrl()));
                    ToastUtil.showToast("下载中，完成后将自动安装。");
                }
                break;
            }
            case DownloadManager.STATUS_FAILED: {
                updateConfirmBtn(ApkDownloadHelper.getInstance().startDownLoad(v.getContext(), getDownloadPath(info), info.getUrl()));
                break;
            }
        }
        if (!info.isForce()) {
            dismiss();
        }
    }

    private void updateConfirmBtn(long id) {
        int status = ApkDownloadHelper.getInstance().getDownloadStatus(getContext(), id);
        updateConfirmBtn(status);
    }

    private void updateConfirmBtn(UpdateInfo info) {
        int status = ApkDownloadHelper.getInstance().getDownloadStatus(getContext(), getDownloadPath(info));
        updateConfirmBtn(status);
    }

    private void updateConfirmBtn(int status) {

        switch (status) {
            case DownloadManager.STATUS_PAUSED:
            case DownloadManager.STATUS_PENDING:
            case DownloadManager.STATUS_RUNNING:
                confirmBtn.setText("下载中");

                break;
            case DownloadManager.STATUS_SUCCESSFUL:
                confirmBtn.setText("安装");
                break;
            case DownloadManager.STATUS_FAILED:
            default:
                confirmBtn.setText("更新");
                break;
        }
    }

    private String getDownloadName(UpdateInfo info) {
        return getContext().getPackageName() + info.getVersionName() + ".apk";
    }

    private String getDownloadPath(UpdateInfo info) {
        File f = new File(StorageDirectoryUtil.getDownloadDirectory(), getDownloadName(info));
        return f.getAbsolutePath();
    }
}
