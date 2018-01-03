package com.dryseed.dryseedapp.practice.localMusic;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by caiminming on 2017/12/28.
 */

@Entity
public class UserMusicDB {
    @Id
    private Long id;
    private Long userId;
    private Long musicId;
    private boolean isSelected;
    @Generated(hash = 2114206970)
    public UserMusicDB(Long id, Long userId, Long musicId, boolean isSelected) {
        this.id = id;
        this.userId = userId;
        this.musicId = musicId;
        this.isSelected = isSelected;
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
    public boolean getIsSelected() {
        return this.isSelected;
    }
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    
}
