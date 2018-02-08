package com.dryseed.dryseedapp.test.io.byteIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 1、建立联系   File对象   源头 目的地
 * 2、选择流
 * 文件输入流  InputStream FileInputStream
 * 文件输出流  OutputStream FileOutputStream
 * 3、操作  :  拷贝
 * byte[] flush =new byte[1024];
 * int len =0;
 * while(-1!=(len=输入流.read(flush))){
 * 输出流.write(flush,0,len)
 * }
 * 输出流.flush
 * 4、释放资源 :关闭 两个流
 *
 * @author Administrator
 */
public class CopyFileDemo {

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) {
        String src = "E:/xp/test";
        String dest = "e:/xp/test/4.jpg";
        copyFile(src, dest);
    }

    /**
     * 文件的拷贝
     *
     * @param srcPath  源文件路径
     * @param destPath 目录文件路径
     * @return
     * @throws FileNotFoundException,IOException
     */
    public static void copyFile(String srcPath, String destPath) {
        //1、建立联系 源(存在且为文件) +目的地(文件可以不存在)
        File src = new File(srcPath);
        File dest = new File(destPath);
        copyFile(src, dest);
    }


    /**
     * 文件的拷贝
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @return
     * @throws FileNotFoundException,IOException
     */
    public static void copyFile(File srcFile, File destFile) {
        InputStream is = null;
        OutputStream os = null;
        try {
            if (!srcFile.isFile()) { //不是文件或者为null
                System.out.println("只能拷贝文件");
                return;
            }
            //2、选择流
            is = new FileInputStream(srcFile);
            os = new FileOutputStream(destFile);
            //3、文件拷贝   循环+读取+写出
            byte[] flush = new byte[1024];
            int len = 0;
            //读取
            while (-1 != (len = is.read(flush))) {
                //写出
                os.write(flush, 0, len);
            }
            os.flush(); //强制刷出
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}