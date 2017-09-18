// IRemoteService.aidl
package com.dryseed.dryseedapp.test.AIDL;

// Declare any non-default types here with import statements
import com.dryseed.dryseedapp.test.AIDL.MyProcess;

interface IRemoteService {
    /**
     * Request the process ID of this service, to do evil things with it.
     * */
    int getPid();

    MyProcess getProcess(in MyProcess clientProcess);

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     *
     * basicTypes方法中给我们展示了AIDL支持的基本数据类型，除此之外，
     * AIDL还支持：CharSequence, List & Map（List和Map中的所有元素都必须是AIDL支持的数据类型、其他AIDL生成的接口或您声明的可打包类型。
     *
     * Android SDK Tool会根据我们的.aidl文件自动生成一个同名的.java文件，如：AIDLTest/app/build/generated/source/aidl/debug/com/dryseed/dryseedapp/IRemoteService.java
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
