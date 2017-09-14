package com.dryseed.dryseedapp.tools.xml;

/**
 * Created by caiminming on 2017/9/14.
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Xml;

public class ParserByPull {

    //采用XmlPullParser来解析XML文件
    public static List<Student> getStudents(InputStream inStream) throws Throwable {
        List<Student> students = null;
        Student mStudent = null;

        //========创建XmlPullParser,有两种方式=======
        //方式一:使用工厂类XmlPullParserFactory
        XmlPullParserFactory pullFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = pullFactory.newPullParser();
        //方式二:使用Android提供的实用工具类android.util.Xml
        //XmlPullParser parser = Xml.newPullParser();

        //解析文件输入流
        parser.setInput(inStream, "UTF-8");
        //产生第一个事件
        int eventType = parser.getEventType();
        //只要不是文档结束事件，就一直循环
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                //触发开始文档事件
                case XmlPullParser.START_DOCUMENT:
                    students = new ArrayList<Student>();
                    break;
                //触发开始元素事件
                case XmlPullParser.START_TAG:
                    //获取解析器当前指向的元素的名称
                    String name = parser.getName();
                    if ("student".equals(name)) {
                        //通过解析器获取id的元素值，并设置student的id
                        mStudent = new Student();
                        mStudent.setId(parser.getAttributeValue(0));
                    }
                    if (mStudent != null) {
                        if ("name".equals(name)) {
                            //获取解析器当前指向元素的下一个文本节点的值
                            mStudent.setName(parser.nextText());
                        }
                        if ("age".equals(name)) {
                            //获取解析器当前指向元素的下一个文本节点的值
                            mStudent.setAge(new Short(parser.nextText()));
                        }
                        if ("sex".equals(name)) {
                            //获取解析器当前指向元素的下一个文本节点的值
                            mStudent.setSex(parser.nextText());
                        }
                    }
                    break;
                //触发结束元素事件
                case XmlPullParser.END_TAG:
                    if ("student".equals(parser.getName())) {
                        students.add(mStudent);
                        mStudent = null;
                    }
                    break;
                default:
                    break;
            }
            eventType = parser.next();
        }
        return students;
    }

}
