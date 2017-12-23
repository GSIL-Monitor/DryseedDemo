package com.dryseed.dryseedapp.tools.bytebuffer;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * Created by caiminming on 2017/12/23.
 */

public class ByteBufferMain {
    private static void printMarkPositionLimitCapacity(String msg, ByteBuffer byteBuffer) {
        System.out.println(
                String.format(
                        "%s || mark:%s,position:%s,limit:%s,capacity:%s",
                        msg,
                        getMark(byteBuffer),
                        byteBuffer.position(),
                        byteBuffer.limit(),
                        byteBuffer.capacity()
                )
        );
        System.out.println();
    }

    private static int getMark(ByteBuffer byteBuffer) {
        try {
            Field field = byteBuffer.getClass().getSuperclass().getSuperclass().getDeclaredField("mark");
            field.setAccessible(true);
            return field.getInt(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getMark error : " + e.toString());
        }
        return -100;
    }

    private static void printBufferContent(ByteBuffer byteBuffer) {
        for (int i = byteBuffer.position(); i < byteBuffer.limit(); i++) {
            System.out.print(byteBuffer.get());
        }
        System.out.println();
    }

    /**
     * 基础操作
     */
    private static void test1() {
        //开始分配了10个大小的数组内存
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        printMarkPositionLimitCapacity("allocate 10", byteBuffer);

        //存了5个数据
        for (int i = 0; i < 5; i++) {
            byteBuffer.put((byte) i);
        }
        printMarkPositionLimitCapacity("put 5 data", byteBuffer);

        //flip方法准备下一个状态， posiont=0,mark=-1,limit=position;
        byteBuffer.flip();
        printMarkPositionLimitCapacity("flip", byteBuffer);

        //读取4个数据
        for (int i = 0; i < 4; i++) {
            System.out.print(byteBuffer.get());
        }
        System.out.println();
        printMarkPositionLimitCapacity("get 4 data", byteBuffer);

        //clear 回到初始方法， position=0,mark=-1,limit=capacity
        byteBuffer.clear();
        printMarkPositionLimitCapacity("clear", byteBuffer);

        //put方法一次性写入5个数据
        byte[] byteArray = new byte[5];
        for (int i = 0; i < 5; i++) {
            byteArray[i] = (byte) (i + 3);
        }
        byteBuffer.put(byteArray);
        printMarkPositionLimitCapacity("put new 5 data", byteBuffer);

        //rewind 清零，想从起始位置开始读取数据的情形 position=0
        byteBuffer.rewind();
        printMarkPositionLimitCapacity("rewind", byteBuffer);

        //get方法一次性读取4个数据
        byte[] data = new byte[4];
        byteBuffer.get(data);
        System.out.println("get new data");
        for (int i = 0; i < 4; i++) {
            System.out.print(data[i]);
        }
        System.out.println();
        printMarkPositionLimitCapacity("put new 5 data", byteBuffer);

        //hasRemaining remaining : limit-position
        System.out.println("byteBuffer.hasRemaining() : " + byteBuffer.hasRemaining());
        System.out.println("byteBuffer.remaining() : " + byteBuffer.remaining());
    }

    /**
     * 子Buffer的方法大致是四个：slice，duplicate，array，get
     */
    private static void test2() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < 10; i++) {
            byteBuffer.put((byte) i);
        }
        byteBuffer.position(2);
        byteBuffer.limit(4);

        // slice方法
        // slice方法获取的是源ByteBuffer的position-limit之间的内容，和源内容相互影响，源内容的position和limit不受影响
        System.out.println("=============slice============");
        ByteBuffer sliceBuffer = byteBuffer.slice();
        printBufferContent(sliceBuffer);
        printMarkPositionLimitCapacity("get data", byteBuffer);
        printMarkPositionLimitCapacity("slice", sliceBuffer);

        // duplicate方法
        // duplicate方法获取的是源ByteBuffer所有的内容，包括源ByteBuffer的mark,position,limit,capacity值，和源内容相互影响，源内容的position和limit不受影响
        System.out.println("=============duplicate============");
        ByteBuffer duplicateBuffer = byteBuffer.duplicate();
        printBufferContent(duplicateBuffer);
        printMarkPositionLimitCapacity("duplicate", duplicateBuffer);

        // array方法
        // array方法获取的是源ByteBuffer的所有内容，只是存放到一个字节数组中，和源内容相互影响，源position和limit不受影响
        System.out.println("=============array============");
        byte[] array = byteBuffer.array();
        System.out.println("array size : " + array.length);

        // get方法
        // get方法获取的是源ByteBuffer的position-limit之间的内容，存放到目标字节数组中，和源内容不影响，源position会发生变化等于limit,limit不受影响
        System.out.println("=============get============");
        byte[] getArray = new byte[byteBuffer.remaining()];
        byteBuffer.get(getArray);
        System.out.println("array size : " + getArray.length);
    }


    /**
     * mark <= position <= limit <= capacity
     *
     * @param args
     */
    public static void main(String args[]) {
        test1();
        System.out.println("=================================");
        test2();
    }


}


















