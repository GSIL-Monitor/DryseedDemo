package com.dryseed.dryseedapp.test.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by caiminming on 2017/7/12.
 */

public class TestReflect2 {

    private String proprety = null;

    public static void main(String[] args) throws Exception {

        test1();

        test2();
    }

    /**
     * 通过反射机制操作某个类的属性
     */
    private static void test2() throws Exception {
        Class<?> clazz = Class.forName("com.dryseed.dryseedapp.test.reflection.TestReflect2");
        Object obj = clazz.newInstance();
        // 可以直接对 private 的属性赋值
        Field field = clazz.getDeclaredField("proprety");
        field.setAccessible(true);
        field.set(obj, "Java反射机制");
        System.out.println(field.get(obj));
    }

    /**
     * 通过反射机制调用某个类的方法
     */
    private static void test1() throws Exception {
        Class<?> clazz = Class.forName("com.dryseed.dryseedapp.test.reflection.TestReflect2");

        Method method = clazz.getMethod("reflect1");
        method.invoke(clazz.newInstance());

        method = clazz.getMethod("reflect2", int.class, String.class);
        method.invoke(clazz.newInstance(), 20, "张三");
    }

    public void reflect1() {
        System.out.println("Java 反射机制 - 调用某个类的方法1.");
    }

    public void reflect2(int age, String name) {
        System.out.println("Java 反射机制 - 调用某个类的方法2.");
        System.out.println("age -> " + age + ". name -> " + name);
    }
}
