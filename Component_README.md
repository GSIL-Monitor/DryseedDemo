### Component
* project/build.gradle
    ```
    buildscript {
        dependencies {
            classpath 'com.github.jimu:build-gradle:A.B.C'
        }
    }
    ```

* 每个组件引入依赖库
    ```
    compile project(':componentservice')
    annotationProcessor project(':router-anno-compiler')
    ```

### 添加Component
* 创建Module
* 添加gradle.properties文件
    * isRunAlone=true
* settrings.gradle添加module路径
* 修改root/build.gradle
* 创建runalone目录
* componentservice module中创建对应业务Service接口
* src/main/java/packagename/serviceimpl 下创建业务接口实现类
* src/main/java/packagename/applike 下创建IApplicationLike实现类
* 主工程MainApplication添加相关代码
* app/gradle.properties添加相关代码



















