package com.luojilab.component.basiclib.utils;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.text.TextUtils;

import com.luojilab.component.basiclib.BaseApplication;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Executors;

public class FileUtil {

    public static byte[] file2byte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            bos.flush();
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void byte2file(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file = new File(filePath, fileName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static File saveFile(String filePath, String fileName, InputStream in) {

        File file = new File(filePath);
        if (!file.exists()) {
            if (!file.mkdirs()) {//若创建文件夹不成功
                System.out.println("Unable to create external cache directory");
            }
        }
        filePath = filePath + "/" + fileName;

        File dir = new File(filePath);
        OutputStream os = null;
        try {
            os = new FileOutputStream(dir);
            int ch = 0;
            while ((ch = in.read()) != -1) {
                os.write(ch);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dir;
    }

    public static File saveFile(File file, InputStream in) {
        if (!file.exists()) {
            if (!file.mkdirs()) {//若创建文件夹不成功
                System.out.println("Unable to create external cache directory");
            }
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int ch = 0;
            while ((ch = in.read()) != -1) {
                os.write(ch);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static void deleteFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        File fileOrDirectory = new File(filePath);
        deleteFile(fileOrDirectory);
        return;
    }

    private static void deleteFile(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File file : fileOrDirectory.listFiles()) {
                deleteFile(file);
            }
        }
        fileOrDirectory.delete();
    }

    public static void copyAssetsToFile(final String assetFilename, final String dstName) {
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                FileOutputStream fos = null;
                InputStream is = null;
                AssetManager assetManager = BaseApplication.getInstance().getAssets();
                String newFileName = dstName + "/" + assetFilename;
                File dstFile = new File(newFileName);
                try {
                    is = assetManager.open(assetFilename);
                    fos = new FileOutputStream(dstFile);
                    byte[] buffer = new byte[1024 * 2];
                    int byteCount;
                    while ((byteCount = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, byteCount);
                    }
                    fos.flush();
                } catch (IOException e) {
                } finally {
                    if (fos != null) {
                        try {
                            fos.flush();
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

    }

    public static AssetFileDescriptor getAssetFileDescription(String filename) throws IOException {
        AssetManager manager = BaseApplication.getInstance().getAssets();
        return manager.openFd(filename);
    }

}
