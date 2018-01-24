package com.dryseed.dryseedapp.framework.glide;

import com.alibaba.fastjson.annotation.JSONField;

public class ImageEntity {
    @JSONField(name = "image_url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
