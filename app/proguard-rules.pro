# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\android-sdk\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


## ----------------------------------
##   ########## GreenDao3 ##########
## ----------------------------------
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use RxJava:
-dontwarn rx.**


## ----------------------------------
##   ########## Retrofit ##########
## ----------------------------------
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

-keep class retrofit2.adapter.rxjava2.HttpException
-dontwarn retrofit2.adapter.rxjava2.HttpException


## ----------------------------------
##   ########## Okio ##########
## ----------------------------------
-dontwarn okio.**


## ----------------------------------
##   ########## okhttp3 ##########
## ----------------------------------
-dontwarn okhttp3.**
#-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase


## ----------------------------------
##   ########## glide ##########
## ----------------------------------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-dontwarn  com.bumptech.glide.load.**
-dontwarn com.bumptech.glide.manager.**


## ----------------------------------
##   ########## 高德定位SDK ##########
## ----------------------------------
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}


## ----------------------------------
##   ########## junit ##########
## ----------------------------------
-keep class org.junit.** { *; }
-dontwarn org.junit.**


## ----------------------------------
##   ########## Rxdownload ##########
## ----------------------------------
-keep class zlc.season.rxdownload3.core.**{*;}
-dontwarn zlc.season.rxdownload3.core.**
-keep class zlc.season.rxdownload3.notification.**{*;}
-dontwarn zlc.season.rxdownload3.notification.**


#Warning:library class android.test.AndroidTestCase extends or implements program class junit.framework.TestCase
-dontwarn android.test.**


## ----------------------------------
##   ########## Tencent X5 ##########
## ----------------------------------
-keep class com.tencent.smtt.export.external.**{*;}
-dontwarn com.tencent.smtt.**


## ----------------------------------
##   ########## 关闭日志 ##########
## ----------------------------------
-assumenosideeffects class android.util.Log{
    public static *** v(...);
    public static *** i(...);
    public static *** d(...);
    public static *** w(...);
    public static *** e(...);
}