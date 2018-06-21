package com.dryseed.dryseedapp.architecture.aac.demo.repository;

import com.dryseed.dryseedapp.architecture.aac.demo.entity.UserEntity;
import com.dryseed.dryseedapp.architecture.aac.demo.livedata.MyLiveData;

public class UserRepository {
    public MyLiveData<UserEntity> getUser(String userId) {
        MyLiveData<UserEntity> data = new MyLiveData<>();
        data.setValue(new UserEntity(userId));
        return data;
    }
}
