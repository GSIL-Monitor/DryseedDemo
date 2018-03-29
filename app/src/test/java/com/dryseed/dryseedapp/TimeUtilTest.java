package com.dryseed.dryseedapp;

import com.dryseed.dryseedapp.utils.TimeUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class) //指定该测试类使用某个运行器
public class TimeUtilTest {

    private Date mDate;
    private long mTimeStamp = 1521618270L;
    private String mDateStr = "2018年03月21日";

    public TimeUtilTest(String dateStr) {
        mDateStr = dateStr;
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("TimeUtilTest start...");
        mDate = new Date();
    }

    @Test
    public void testTimeStampToDate() throws Exception {
        System.out.println("testTimeStampToDate : " + mDateStr);
        assertEquals(mDateStr, TimeUtils.timeStamp2Date(mTimeStamp));
    }

    @Test
    public void testDateToTimeStamp() throws Exception {
        System.out.println("testDateToTimeStamp");
        assertEquals(1521618270L, TimeUtils.dateToTimeStamp(mDateStr));
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("TimeUtilTest end...");
    }

    @Parameterized.Parameters //指定测试类的测试数据集合
    public static Collection primeNumbers() {
        return Arrays.asList(new String[]{"2018年03月21日", "2018年3月21日"});
    }
}
