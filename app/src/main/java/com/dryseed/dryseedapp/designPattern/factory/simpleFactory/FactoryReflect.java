package com.dryseed.dryseedapp.designPattern.factory.simpleFactory;


/**
 * Created by caiminming on 2017/1/23.
 */
public class FactoryReflect {
    public IProduct create(String className) {
        System.out.println(className);
        try {
            return (IProduct) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
