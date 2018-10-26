package com.dryseed.dryseedapp.framework.volley.dsvolley.entity;

import java.io.Serializable;

/**
 * @author caiminming
 */
public class EasyMockReturnEntity implements Serializable {
    private int returnCode;
    private int type;
    private String returnMsg;
    private String content;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReturnMsg() {
        return returnMsg == null ? "" : returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EasyMockReturn{" +
                "returnCode=" + returnCode +
                ", type=" + type +
                ", returnMsg='" + returnMsg + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
