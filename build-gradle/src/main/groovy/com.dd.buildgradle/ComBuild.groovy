package com.dd.buildgradle

import com.dd.buildgradle.exten.ComExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.dd.buildgradle.util.StringUtil

class ComBuild implements Plugin<Project> {
    final String TAG = "=========> build-gradle : "

    //默认是app，直接运行assembleRelease的时候，等同于运行app:assembleRelease
    String compilemodule = "app"

    void apply(Project project) {
        project.extensions.create('combuild', ComExtension)

        String taskNames = project.gradle.startParameter.taskNames.toString()
        myPrintln("taskNames is " + taskNames) //[assemble] || [assemble]
        myPrintln("project.path : " + project.path) //:app || :aac-component
        String module = project.path.replace(":", "")
        myPrintln("current module is " + module) //app || aac-component
        AssembleTask assembleTask = getTaskInfo(project.gradle.startParameter.taskNames)
        myPrintln("assembleTask [isAssemble:${assembleTask.isAssemble}][isDebug:${assembleTask.isDebug}][modules:${assembleTask.modules.get(0)}]")
        //[isAssemble:true][isDebug:false][modules:all] || [isAssemble:true][isDebug:false][modules:all]

        if (assembleTask.isAssemble) {
            fetchMainModulename(project, assembleTask) // set compilemodule
            myPrintln("compilemodule  is " + compilemodule) //app || app
        }

        if (!project.hasProperty("isRunAlone")) {
            throw new RuntimeException("you should set isRunAlone in " + module + "'s gradle.properties")
        }

        // 对于isRunAlone==true的情况需要根据实际情况修改其值，
        // 但如果是false，则不用修改
        boolean isRunAlone = Boolean.parseBoolean((project.properties.get("isRunAlone")))
        String mainmodulename = project.rootProject.property("mainmodulename")
        if (isRunAlone && assembleTask.isAssemble) {
            //对于要编译的组件和主项目，isRunAlone修改为true，其他组件都强制修改为false
            //这就意味着组件不能引用主项目，这在层级结构里面也是这么规定的
            // compilemodule：编译的主项目
            // module：当前项目
            if (module.equals(compilemodule) || module.equals(mainmodulename)) {
                isRunAlone = true
            } else {
                isRunAlone = false
            }
        }
        myPrintln("isRunAlone  is ${isRunAlone}")
        project.setProperty("isRunAlone", isRunAlone)

        //根据配置添加各种组件依赖，并且自动化生成组件加载代码
        if (isRunAlone) {
            project.apply plugin: 'com.android.application'
            if (!module.equals(mainmodulename)) {
                project.android.sourceSets {
                    main {
                        manifest.srcFile 'src/main/runalone/AndroidManifest.xml'
                        java.srcDirs = ['src/main/java', 'src/main/runalone/java']
                        res.srcDirs = ['src/main/res', 'src/main/runalone/res']
                        assets.srcDirs = ['src/main/assets', 'src/main/runalone/assets']
                        jniLibs.srcDirs = ['src/main/jniLibs', 'src/main/runalone/jniLibs']
                    }
                }
            }
            myPrintln("apply plugin is " + 'com.android.application')
            if (assembleTask.isAssemble && module.equals(compilemodule)) {
                compileComponents(assembleTask, project)
                project.android.registerTransform(new ComCodeTransform(project)) //注册Transform
            }
        } else {
            project.apply plugin: 'com.android.library'
            myPrintln("apply plugin is " + 'com.android.library')
        }

    }

    /**
     * 根据当前的task，获取要运行的组件，规则如下：
     * assembleRelease ---app
     * app:assembleRelease :app:assembleRelease ---app
     * sharecomponent:assembleRelease :sharecomponent:assembleRelease ---sharecomponent
     * @param assembleTask
     */
    private void fetchMainModulename(Project project, AssembleTask assembleTask) {
        if (!project.rootProject.hasProperty("mainmodulename")) {
            throw new RuntimeException("you should set compilemodule in rootproject's gradle.properties")
        }
        if (assembleTask.modules.size() > 0 && assembleTask.modules.get(0) != null
                && assembleTask.modules.get(0).trim().length() > 0
                && !assembleTask.modules.get(0).equals("all")) {
            compilemodule = assembleTask.modules.get(0)
        } else {
            compilemodule = project.rootProject.property("mainmodulename")
        }
        if (compilemodule == null || compilemodule.trim().length() <= 0) {
            compilemodule = "app"
        }
    }

    /**
     *
     * @param taskNames
     * @return 自定义对象
     */
    private AssembleTask getTaskInfo(List<String> taskNames) {
        AssembleTask assembleTask = new AssembleTask()
        for (String task : taskNames) {
            if (task.toUpperCase().contains("ASSEMBLE")
                    || task.contains("aR")
                    || task.toUpperCase().contains("TINKER")
                    || task.toUpperCase().contains("INSTALL")
                    || task.toUpperCase().contains("RESGUARD")) {
                if (task.toUpperCase().contains("DEBUG")) {
                    assembleTask.isDebug = true
                }
                assembleTask.isAssemble = true
                String[] strs = task.split(":")
                assembleTask.modules.add(strs.length > 1 ? strs[strs.length - 2] : "all")
                break
            }
        }
        return assembleTask
    }

    /**
     * 自动添加依赖，只在运行assemble任务的才会添加依赖，因此在开发期间组件之间是完全感知不到的，这是做到完全隔离的关键
     * 支持两种语法：module或者groupId:artifactId:version(@aar),前者之间引用module工程，后者使用maven中已经发布的aar
     * @param assembleTask
     * @param project
     */
    private void compileComponents(AssembleTask assembleTask, Project project) {
        String components
        if (assembleTask.isDebug) {
            components = (String) project.properties.get("debugComponent")
        } else {
            components = (String) project.properties.get("compileComponent")
        }

        if (components == null || components.length() == 0) {
            myPrintln("there is no add dependencies ")
            return
        }
        String[] compileComponents = components.split(",")
        if (compileComponents == null || compileComponents.length == 0) {
            myPrintln("there is no add dependencies ")
            return
        }
        for (String str : compileComponents) {
            myPrintln()
            System.out.println("comp is " + str)
            str = str.trim()
            if (str.startsWith(":")) {
                str = str.substring(1)
            }
            // 是否是maven 坐标
            if (StringUtil.isMavenArtifact(str)) {
                /**
                 * 示例语法:groupId:artifactId:version(@aar)
                 * compileComponent=com.luojilab.reader:readercomponent:1.0.0
                 * 注意，前提是已经将组件aar文件发布到maven上，并配置了相应的repositories
                 */
                project.dependencies.add("compile", str)
                myPrintln("add dependencies lib  : " + str)
            } else {
                /**
                 * 示例语法:module
                 * compileComponent=readercomponent,sharecomponent
                 */
                project.dependencies.add("compile", project.project(':' + str))
                myPrintln("add dependencies project : " + str)
            }
        }
    }

    private class AssembleTask {
        boolean isAssemble = false
        boolean isDebug = false
        List<String> modules = new ArrayList<>()
    }


    private void myPrintln(String str) {
        System.out.println(TAG + str)
    }
}