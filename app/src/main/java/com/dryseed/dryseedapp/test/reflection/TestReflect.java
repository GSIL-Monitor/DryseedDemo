package com.dryseed.dryseedapp.test.reflection;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by caiminming on 2017/7/12.
 */

public class TestReflect implements Serializable {
    public static void main(String[] args) throws Exception {
        test1();

        test2();

        test3();

        test4();
    }

    private static void test4() throws Exception {
        System.out.println("===========test4=================");
        Class<?> clazz = Class.forName("java.lang.Integer");
        Method method[] = clazz.getMethods();
        for (int i = 0; i < method.length; ++i) {
            Class<?> returnType = method[i].getReturnType();
            Class<?> para[] = method[i].getParameterTypes();
            int temp = method[i].getModifiers();
            System.out.print(Modifier.toString(temp) + "|");
            System.out.print(returnType.getName() + "|");
            System.out.print(method[i].getName() + "|");
            System.out.print("(");
            for (int j = 0; j < para.length; ++j) {
                System.out.print(para[j].getName() + " " + "arg" + j);
                if (j < para.length - 1) {
                    System.out.print(",");
                }
            }
            Class<?> exce[] = method[i].getExceptionTypes();
            if (exce.length > 0) {
                System.out.print(") throws ");
                for (int k = 0; k < exce.length; ++k) {
                    System.out.print(exce[k].getName() + " ");
                    if (k < exce.length - 1) {
                        System.out.print(",");
                    }
                }
            } else {
                System.out.print(")");
            }
            System.out.println();
        }
    }

    /**
     * 获取某个类的全部属性
     * getDeclaredFields()获得某个类的所有申明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
     * getFields()获得某个类的所有的公共（public）的字段，包括父类。
     */
    private static void test3() throws Exception {
        System.out.println("===========test3=================");
        Class<?> clazz = Class.forName("java.lang.Integer");
        System.out.println("===============本类属性===============");
        // 取得本类的全部属性
        Field[] field = clazz.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // 权限修饰符
            int mo = field[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field[i].getType();
            System.out.println(priv + "|" + type.getName() + "|" + field[i].getName() + ";");
        }

        System.out.println("==========实现的接口或者父类的属性==========");
        // 取得实现的接口或者父类的属性
        Field[] filed1 = clazz.getFields();
        for (int j = 0; j < filed1.length; j++) {
            // 权限修饰符
            int mo = filed1[j].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = filed1[j].getType();
            System.out.println(priv + "|" + type.getName() + "|" + filed1[j].getName() + ";");
        }
    }

    /**
     * 获取一个对象的父类与实现的接口
     */
    private static void test2() throws Exception {
        System.out.println("===========test2=================");
        Class<?> clazz = Class.forName("com.dryseed.dryseedapp.test.reflection.TestReflect");
        // 取得父类
        Class<?> parentClass = clazz.getSuperclass();
        System.out.println("clazz的父类为：" + parentClass.getName());
        // clazz的父类为： java.lang.Object
        // 获取所有的接口
        Class<?> intes[] = clazz.getInterfaces();
        System.out.println("clazz实现的接口有：");
        for (int i = 0; i < intes.length; i++) {
            System.out.println((i + 1) + "：" + intes[i].getName());
        }
        // clazz实现的接口有：
        // 1：java.io.Serializable
    }

    /**
     * 实例化Class类对象
     */
    private static void test1() throws Exception {
        System.out.println("===========test1=================");
        Class class1 = null;
        Class class2 = null;
        Class class3 = null;
        class1 = Class.forName("com.dryseed.dryseedapp.test.reflection.TestReflect");
        class2 = new TestReflect().getClass();
        class3 = TestReflect.class;
        System.out.println("类名称   " + class1.getName());
        System.out.println("类名称   " + class2.getName());
        System.out.println("类名称   " + class3.getName());
    }


}