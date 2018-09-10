package com.dryseed.dryseedapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    public static final String GIF_URL = "https://loading.io/spinners/ball/lg.bouncing-circle-loading-icon.gif";

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(GIF_URL.endsWith(".gif"), true);
    }
}