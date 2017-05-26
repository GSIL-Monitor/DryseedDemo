package com.dryseed.dryseedapp.test.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by caiminming on 2017/5/26.
 */

public class PersonBySerializable implements Serializable {
    private String username;
    private String nickname;
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PersonBySerializable(String username, String nickname, int age) {
        super();
        this.username = username;
        this.nickname = nickname;
        this.age = age;
    }

    public PersonBySerializable() {
        super();
    }


}
