package com.dryseed.aaccomponent.demo.repository;


import com.dryseed.aaccomponent.demo.entity.UserEntity;
import com.dryseed.aaccomponent.demo.livedata.MyLiveData;

public class UserRepository {
    public MyLiveData<UserEntity> getUser(String userId) {
        MyLiveData<UserEntity> data = new MyLiveData<>();
        data.setValue(new UserEntity(userId));
        return data;
    }
}
