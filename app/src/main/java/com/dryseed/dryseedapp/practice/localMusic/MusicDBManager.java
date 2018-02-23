package com.dryseed.dryseedapp.practice.localMusic;


import com.dryseed.dryseedapp.MyApplication;

import java.util.ArrayList;
import java.util.List;

import greendao.MusicDBDao;
import greendao.UserMusicDBDao;

/**
 * Created by caiminming on 2017/12/28.
 */

public class MusicDBManager {
    public static MusicDBDao getMusicDBDao() {
        return DaoManager.getDaoSession(MyApplication.getInstance()).getMusicDBDao();
    }

    public static UserMusicDBDao getUserMusicDBDao() {
        return DaoManager.getDaoSession(MyApplication.getInstance()).getUserMusicDBDao();
    }

    /**
     * 添加歌曲
     *
     * @param userId
     * @param musicDB
     * @return
     */
    public static long insertOrReplaceMusic(long userId, MusicDB musicDB) {
        long musicId = getMusicDBDao().insertOrReplace(musicDB);
        try {
            UserMusicDB userMusicDB1 = getUserMusicDBDao().queryBuilder()
                    .where(UserMusicDBDao.Properties.UserId.eq(userId), UserMusicDBDao.Properties.MusicId.eq(musicId))
                    .unique();
            if (userMusicDB1 == null) {
                UserMusicDB userMusicDB = new UserMusicDB(null, userId, musicId, false);
                getUserMusicDBDao().insertOrReplace(userMusicDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return musicId;
    }

    /**
     * 查询UserId下的所有歌曲
     *
     * @param id
     * @return
     */
    public static List<MusicDB> queryMusicByUserId(long id) {
        List<UserMusicDB> userMusicDBList = getUserMusicDBDao().queryBuilder().where(UserMusicDBDao.Properties.UserId.eq(id)).list();
        ArrayList<MusicDB> musicDBList = new ArrayList<>();

        int size = userMusicDBList.size();
        for (int i = 0; i < size; i++) {
            long musicId = userMusicDBList.get(i).getMusicId();
            MusicDB musicDB = queryMusicByMusicId(musicId);
            if (null != musicDB) {
                musicDBList.add(musicDB);
            }
        }
        return musicDBList;
    }

    /**
     * 选中某首歌
     * @param userId
     * @param musicId
     */
    public static void selectMusic(long userId, long musicId){
        UserMusicDB userMusicDB = new UserMusicDB(null, userId, musicId, true);
        getUserMusicDBDao().insertOrReplace(userMusicDB);
    }


    /**
     * 删除歌曲
     *
     * @param userId
     * @param musicId
     */
    public static void deleteMusic(long userId, long musicId) {
        UserMusicDB userMusicDB = getUserMusicDBDao().queryBuilder()
                .where(UserMusicDBDao.Properties.UserId.eq(userId), UserMusicDBDao.Properties.MusicId.eq(musicId))
                .unique();
        if (null != userMusicDB) {
            getUserMusicDBDao().delete(userMusicDB);
        }
    }

    /**
     * 删除所有歌曲
     */
    public static void deleteAllMusic() {
        deleteAllUserMusic();
        getMusicDBDao().deleteAll();
    }

    /**
     * 删除所有用户歌曲映射数据
     */
    public static void deleteAllUserMusic() {
        getUserMusicDBDao().deleteAll();
    }

    private static MusicDB queryMusicByMusicId(long musicId) {
        return getMusicDBDao().queryBuilder().where(MusicDBDao.Properties.Id.eq(musicId)).unique();
    }
}
