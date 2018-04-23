package com.luck.picture.serviceimpl;

import android.app.Activity;
import android.content.Context;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luojilab.componentservice.pictureselector.PictureSelectorService;

/**
 * Created by mrzhang on 2017/6/15.
 */

public class PictureselectorServiceImpl implements PictureSelectorService {
    @Override
    public void showPictureSelector(Activity activity) {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(0)// 最小选择数量
                .imageSpanCount(3)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选
                .isCamera(true)// 是否显示拍照按钮
                .previewImage(true)// 是否可预览图片
                .enableCrop(false)// 是否裁剪
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }
}
