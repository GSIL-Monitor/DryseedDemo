package com.dryseed.dryseedapp.practice.localMusic;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by caiminming on 2017/12/28.
 */

@Entity
public class MusicDB {
    @Id
    private Long id;
    private String name;
    private String artist;
    private int duration;
    private int size;
    private int back;
    @Generated(hash = 285691324)
    public MusicDB(Long id, String name, String artist, int duration, int size,
            int back) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.size = size;
        this.back = back;
    }
    @Generated(hash = 1072602783)
    public MusicDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getArtist() {
        return this.artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public int getDuration() {
        return this.duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getSize() {
        return this.size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getBack() {
        return this.back;
    }
    public void setBack(int back) {
        this.back = back;
    }

}
