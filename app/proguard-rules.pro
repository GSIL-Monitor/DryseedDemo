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

#代码混淆压缩比，在0~7之间，默认为5，一般不做修改
#-optimizationpasses 5

# 混淆时不使用大小写混合，混淆后的类名为小写（包名不使用大小写混合 aA Aa）
-dontusemixedcaseclassnames

# 指定不去忽略非public的library classes。从Proguard 4.5开始，是默认的设置。
-dontskipnonpubliclibraryclasses

# 指定不去忽略非public的library classmembers。
-dontskipnonpubliclibraryclassmembers

#指定不去输出打印该类产生的错误或遗漏
-dontnote

# 混淆后生产映射文件 map 类名->转化后类名的映射；存放在app\build\outputs\mapping\release中
-verbose

#方法同名混淆后亦同名，方法不同名混淆后亦不同名。不使用该选项时，类成员可被映射到相同的名称。因此该选项会增加些许输出文件的大小。
-useuniqueclassmembernames

#默认optimize和preverify选项是关闭的，因为Android的dex并不像Java虚拟机需要optimize(优化)和previrify(预检)两个步骤。
#-dontoptimize
#-dontpreverify

#不要使用-ignorewarnings语句，这个会忽略所有警告，会有很多潜在的风险。
#-ignorewarnings

 # 避免混淆Annotation、内部类、泛型、匿名类
-keepattributes *Annotation*,InnerClasses,Signature,EnclosingMethod

# 保护注解
-keep class * extends java.lang.annotation.Annotation {*;}

 # 重命名抛出异常时的文件名称
-renamesourcefileattribute SourceFile

 # 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

# 处理support包，避免二次混淆
-keep public class * extends android.support.**
-keep class android.support.v4.** {*;}
-keep class android.support.v7.** {*;}
-dontnote android.support.**
-dontwarn android.support.**

# 保留四大组件，自定义的Application等这些类不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.app.Fragment
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.view.View
-keep public class * extends android.widget.ImageView
-keep public class * extends android.view.ViewGroup
-keep public class * extends android.widget.LinearLayout
-keep public class * extends android.widget.TextView
-keep public class * extends android.widget.Gallery
-keep public class * extends android.widget.ListView
-keep public class * extends android.widget.ScrollView
-keep public class * extends android.widget.RelativeLayout
-keep public class * extends android.widget.FrameLayout
-keep public class * extends android.widget.ImageSwitcher
-keep public class * extends android.widget.AppWidgetProvider

# 保留本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保留枚举类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# view初始化方法不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保留Parcelable序列化类不被混淆
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

#删除android.util.Log输出的日志
-assumenosideeffects class android.util.Log{
    public static *** v(...);
    public static *** i(...);
    public static *** d(...);
    public static *** w(...);
    public static *** e(...);
}

# WebView使用javascript功能则需要开启
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
}

# 对于R（资源）下的所有类及其方法，都不能被混淆
-keep class **.R
-keep class **.R$* {
    *;
}


## ----------------------------------
##   ########## Proguard Test ##########
## ----------------------------------
# ?     matches any single character in a name.(匹配一个字符)
# *     matches any part of a name not containing the directory separator.（匹配一个名字，除了目录分隔符外的任意部分）
# **    matches any part of a name, possibly containing any number of directory separators.(匹配任意名,可能包含任意路径分隔符)
# !     exclude
# <field>     匹配类中的所有字段
# <method>    匹配类中所有的方法
# <init>      匹配类中所有的构造函数
#
#保留类名和成员函数名：保留native的方法的方法名 和 包含native方法的类的类名不变
#-keepclasseswithmembernames class * {
#    native <methods>;
#}
##保留继承于View类中的set*和get*方法名不变
#-keepclassmembers public class * extends android.view.View {
#    void set*(***);
#    *** get*();
#}
#-keepclassmembers class * extends android.app.Activity {
#    public void *(android.view.View);
#}
##保留实现了Parcelable接口的类
#-keep class * implements android.os.Parcelable {
#    public static final android.os.Parcelable$Creator *;
#}


## ----------------------------------
##   ########## EventBus ##########
## ----------------------------------
-keep class de.greenrobot.event.** {*;}
-keep class de.greenrobot.event.util.** {*;}
# onEvent**方法不混淆
-keepclassmembers class ** {
    public void onEvent*(**);
    public void onEventMainThread*(**);
}


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


