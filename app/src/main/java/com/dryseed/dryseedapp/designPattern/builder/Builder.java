package com.dryseed.dryseedapp.designPattern.builder;

/**
 * Created by caiminming on 2017/1/23.
 */
public interface Builder {
    public void buildPart1();

    public void buildPart2();

    public Product retrieveResult();
}
