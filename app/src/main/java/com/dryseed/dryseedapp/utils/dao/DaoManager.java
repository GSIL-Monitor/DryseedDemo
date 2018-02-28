package com.dryseed.dryseedapp.utils.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;

import com.dryseed.dryseedapp.widget.dialog.DsAlertDialog;
import com.dryseed.dryseedapp.widget.dialog.DsDialogFactory;

import org.greenrobot.greendao.database.Database;

import greendao.DaoMaster;
import greendao.DaoSession;
import greendao.MusicDBDao;
import greendao.UserMusicDBDao;

/**
 * Created by caiminming on 2017/12/28.
 */

public class DaoManager {

    private static final String DB_NAME = "dryseed.db";
    private static DaoManager mDbManager;
    private static DsOpenHelper mDevOpenHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    public static boolean isUpdating = false;

    private Context mContext;

    private DaoManager(Context context) {
        this.mContext = context;
        // 初始化数据库信息
        mDevOpenHelper = new DsOpenHelper(context, DB_NAME);
        getDaoMaster(context);
        getDaoSession(context);
    }

    public static DaoManager getInstance(Context context) {
        if (null == mDbManager) {
            synchronized (DaoManager.class) {
                if (null == mDbManager) {
                    mDbManager = new DaoManager(context);
                }
            }
        }
        return mDbManager;
    }

    public static SQLiteDatabase getReadableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context);
        }
        return mDevOpenHelper.getReadableDatabase();
    }

    public static SQLiteDatabase getWritableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context);
        }
        return mDevOpenHelper.getWritableDatabase();
    }

    public static DaoMaster getDaoMaster(Context context) {
        if (null == mDaoMaster) {
            synchronized (DaoManager.class) {
                if (null == mDaoMaster) {
                    mDaoMaster = new DaoMaster(getReadableDatabase(context));
                }
            }
        }
        return mDaoMaster;
    }

    public static DaoSession getDaoSession(Context context) {
        if (null == mDaoSession) {
            synchronized (DaoManager.class) {
                mDaoSession = getDaoMaster(context).newSession();
            }
        }
        return mDaoSession;
    }

    public static void deleteAll(Context context) {
        mDevOpenHelper.deleteAllTables();
    }

    public static class DsOpenHelper extends DaoMaster.OpenHelper {
        private Context context;

        public DsOpenHelper(Context context, String name) {
            super(context, name);
            this.context = context;
        }

        public DsOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
            this.context = context;
        }

        @Override
        public void onUpgrade(final Database db, int oldVersion, int newVersion) {
            Log.d("MMM", String.format("greendao onUpgrade : oldVersion - %d | newVersion - %d", oldVersion, newVersion));
            Log.d("MMM", "onUpgrade thread : " + Thread.currentThread().getName());
            /*if (oldVersion <= 11) {
                //暂时不做升级兼容，直接删掉旧数据，重新建表
                DaoMaster.dropAllTables(db, true);
                onCreate(db);
            }*/


            new Thread(new Runnable() {
                @Override
                public void run() {
                    isUpdating = true;

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    GreenDaoUpgradeHelper.DEBUG = true;
                    GreenDaoUpgradeHelper.migrate(db, new GreenDaoUpgradeHelper.ReCreateAllTableListener() {
                        @Override
                        public void onCreateAllTables(Database db, boolean ifNotExists) {
                            DaoMaster.createAllTables(db, ifNotExists);
                        }

                        @Override
                        public void onDropAllTables(Database db, boolean ifExists) {
                            DaoMaster.dropAllTables(db, ifExists);
                        }
                    }, MusicDBDao.class, UserMusicDBDao.class);

                    isUpdating = false;

                }
            }).start();

        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            deleteAllTables();
        }

        public void deleteAllTables() {
            Database db = getReadableDb();
            DaoMaster.dropAllTables(db, true);
            DaoMaster.createAllTables(db, true);
        }

        @Override
        public void onCreate(Database db) {
            super.onCreate(db);
        }

    }

}
