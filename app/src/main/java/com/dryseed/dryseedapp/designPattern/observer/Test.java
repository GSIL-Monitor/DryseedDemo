package com.dryseed.dryseedapp.designPattern.observer;

import com.dryseed.dryseedapp.designPattern.observer.classs.ObjectFor3D;
import com.dryseed.dryseedapp.designPattern.observer.classs.ObserverUser1;
import com.dryseed.dryseedapp.designPattern.observer.classs.ObserverUser2;
import com.dryseed.dryseedapp.designPattern.observer.interfaces.Observer;

/**
 * Created by User on 2016/12/14.
 */
public class Test
{
    public static void main(String[] args)
    {
        //模拟一个3D的服务号
        ObjectFor3D subjectFor3d = new ObjectFor3D();
        //客户1
        Observer observer1 = new ObserverUser1(subjectFor3d);
        Observer observer2 = new ObserverUser2(subjectFor3d);

        subjectFor3d.setMsg("20140420的3D号码是：127" );
        subjectFor3d.setMsg("20140421的3D号码是：333" );

    }
}
