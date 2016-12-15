package com.dryseed.dryseedapp.designPattern.observer;

import com.dryseed.dryseedapp.designPattern.observer.javautil.Observer1;
import com.dryseed.dryseedapp.designPattern.observer.javautil.SubjectFor3d;
import com.dryseed.dryseedapp.designPattern.observer.javautil.SubjectForSSQ;

/**
 * Created by User on 2016/12/14.
 */
public class TestJavaUtil
{
    public static void main(String[] args)
    {
        SubjectFor3d subjectFor3d = new SubjectFor3d() ;
        SubjectForSSQ subjectForSSQ = new SubjectForSSQ() ;

        Observer1 observer1 = new Observer1();
        observer1.registerSubject(subjectFor3d);
        observer1.registerSubject(subjectForSSQ);


        subjectFor3d.setMsg("hello 3d'nums : 110 ");
        subjectForSSQ.setMsg("ssq'nums : 12,13,31,5,4,3 15");

    }
}
