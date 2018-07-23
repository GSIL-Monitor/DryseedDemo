package com.dryseed.aaccomponent.demo.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.dryseed.aaccomponent.demo.entity.UserEntity;
import com.dryseed.aaccomponent.demo.livedata.MyLiveData;
import com.dryseed.aaccomponent.demo.repository.UserRepository;

public class UserViewModel extends ViewModel {
    private String mUserId;
    private MyLiveData<UserEntity> mUser;
    private UserRepository mUserRepository;

    //初始化传递uid进来
    public void init(String userId) {
        this.mUserId = userId;
        mUser = new MyLiveData<>();
        mUserRepository = new UserRepository();
    }

    public MyLiveData<UserEntity> getUser() {
        return mUserRepository.getUser(mUserId);
    }

}
