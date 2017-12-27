package com.dryseed.dryseedapp.tools.downloadManager;

/**
 * Created by caiminming on 2017/12/27.
 */

public class UpdateInfo {
    public String getVersionName() {
        return versionName;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public boolean isForce() {
        return force;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public String getMd5() {
        return md5;
    }

    public boolean isAbort() {
        return abort;
    }

    public UpdateInfo(String versionName, boolean force, String desc, String url, int versionCode, String md5) {
        this.versionName = versionName;
        this.force = force;
        this.desc = desc;
        this.url = url;
        this.versionCode = versionCode;
        this.md5 = md5;
    }

    private String versionName;
    private boolean force = false;
    private String desc;
    private String url;
    private int versionCode;
    private String md5;

    public void setAbort(boolean abort) {
        this.abort = abort;
    }

    private boolean abort = false;
}
