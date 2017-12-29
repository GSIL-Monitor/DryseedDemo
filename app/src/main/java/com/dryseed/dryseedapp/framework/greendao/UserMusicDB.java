package com.dryseed.dryseedapp.framework.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by caiminming on 2017/12/28.
 */

@Entity
public class UserMusicDB {
    @Id
    private Long id;
    private Long userId;
    private Long musicId;

    @Generated(hash = 1826655111)
    public UserMusicDB(Long id, Long userId, Long musicId) {
        this.id = id;
        this.userId = userId;
        this.musicId = musicId;
    }
    @Generated(hash = 483010982)
    public UserMusicDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getMusicId() {
        return this.musicId;
    }
    public void setMusicId(Long musicId) {
        this.musicId = musicId;
    }
}
