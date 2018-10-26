package com.dryseed.dryseedapp.framework.volley.dsvolley.entity;

import java.io.Serializable;

/**
 * @author caiminming
 */
public class VersionEntity implements Serializable {
    private String result;
    private String updateUrl;
    private String versionDesc;
    private String versionCode;
    private String packageSize;
    private String updateType;
    private String versionName;

    public String getResult() {
        return result == null ? "" : result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUpdateUrl() {
        return updateUrl == null ? "" : updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public String getVersionDesc() {
        return versionDesc == null ? "" : versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public String getVersionCode() {
        return versionCode == null ? "" : versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getPackageSize() {
        return packageSize == null ? "" : packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getUpdateType() {
        return updateType == null ? "" : updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public String getVersionName() {
        return versionName == null ? "" : versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Override
    public String toString() {
        return "VersionEntity{" +
                "result='" + result + '\'' +
                ", updateUrl='" + updateUrl + '\'' +
                ", versionDesc='" + versionDesc + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", packageSize='" + packageSize + '\'' +
                ", updateType='" + updateType + '\'' +
                ", versionName='" + versionName + '\'' +
                '}';
    }
}
