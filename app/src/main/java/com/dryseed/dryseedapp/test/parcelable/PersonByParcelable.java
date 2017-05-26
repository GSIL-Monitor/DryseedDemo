package com.dryseed.dryseedapp.test.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by caiminming on 2017/5/26.
 */

public class PersonByParcelable implements Parcelable {
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

    public PersonByParcelable(String username, String nickname, int age) {
        super();
        this.username = username;
        this.nickname = nickname;
        this.age = age;
    }

    public PersonByParcelable() {
        super();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.nickname);
        dest.writeInt(this.age);
    }

    private PersonByParcelable(Parcel in) {
        this.username = in.readString();
        this.nickname = in.readString();
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<PersonByParcelable> CREATOR = new Parcelable.Creator<PersonByParcelable>() {
        public PersonByParcelable createFromParcel(Parcel source) {
            return new PersonByParcelable(source);
        }

        public PersonByParcelable[] newArray(int size) {
            return new PersonByParcelable[size];
        }
    };
}
