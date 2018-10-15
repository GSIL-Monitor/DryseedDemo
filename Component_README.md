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












