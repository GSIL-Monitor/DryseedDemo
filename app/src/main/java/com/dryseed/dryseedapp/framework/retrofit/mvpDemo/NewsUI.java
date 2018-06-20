package com.dryseed.dryseedapp.framework.retrofit.mvpDemo;

import com.dryseed.dryseedapp.architecture.mvp.IBaseUI;
import com.dryseed.dryseedapp.framework.retrofit.demo2.entity.NewsEntity;

/**
 * Created by caiminming on 2017/12/8.
 */

public interface NewsUI extends IBaseUI {
    void getDataSuccess(NewsEntity newsEntity);

    void getDataError(String error);
}
