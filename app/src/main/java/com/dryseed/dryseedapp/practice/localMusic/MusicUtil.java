package com.dryseed.dryseedapp.practice.localMusic;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by caiminming on 2017/12/28.
 */

public class MusicUtil {

    public static final int FILTER_SIZE = 1 * 1024 * 1024;// 1MB
    public static final int FILTER_DURATION = 1 * 60 * 1000;// 1分钟

    private static String[] proj_music = new String[]{
            Media._ID, Media.TITLE,
            Media.DATA, Media.ALBUM_ID,
            Media.ALBUM, Media.ARTIST,
            Media.ARTIST_ID, Media.DURATION, Media.SIZE};

    public static ArrayList<MusicInfo> queryMusic(Context context) {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        ContentResolver cr = context.getContentResolver();

        StringBuilder select = new StringBuilder(" 1=1 and title != ''");
        // 查询语句：检索出时长大于1分钟，文件大小大于1MB的媒体文件
        select.append(" and " + MediaStore.Audio.Media.SIZE + " > " + FILTER_SIZE);
        select.append(" and " + MediaStore.Audio.Media.DURATION + " > " + FILTER_DURATION);

        String songSortOrder = MediaStore.Audio.Media.DATA;
        return getMusicListCursor(cr.query(uri, proj_music, select.toString(), null, songSortOrder));
    }

    public static ArrayList<MusicInfo> getMusicListCursor(Cursor cursor) {
        if (cursor == null) {
            return null;
        }

        ArrayList<MusicInfo> musicList = new ArrayList<>();
        while (cursor.moveToNext()) {
            MusicInfo music = new MusicInfo();
            music.songId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            music.albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            music.albumName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM));
            music.duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            music.musicName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            music.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            music.artistId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
            String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            music.data = filePath;
            music.folder = filePath.substring(0, filePath.lastIndexOf(File.separator));
            music.size = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
            music.islocal = true;
            musicList.add(music);
        }
        cursor.close();
        return musicList;
    }
}
