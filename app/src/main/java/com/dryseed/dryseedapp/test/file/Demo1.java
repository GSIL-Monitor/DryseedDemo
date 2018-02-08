package com.dryseed.dryseedapp.test.file;

import java.io.File;

public class Demo1 {
    public static void main(String[] args) {
        System.out.println(File.pathSeparator); //路径分隔符  ;
        System.out.println(File.separator); //名称分隔符 \(windows)  /(linux 等)

        String path = "E:\\xp\\test\\2.jpg";
        path = "E:" + File.separator + "xp" + File.separator + "test" + File.separator + "2.jpg";
        path = "E:/xp/test/2.jpg"; //推荐方式

        /*
            log:
                ;
                \
         */
    }
}
