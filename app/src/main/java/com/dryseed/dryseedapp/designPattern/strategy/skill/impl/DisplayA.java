package com.dryseed.dryseedapp.designPattern.strategy.skill.impl;


import com.dryseed.dryseedapp.designPattern.strategy.skill.IDisplayBehavior;

public class DisplayA implements IDisplayBehavior
{

	@Override
	public void display()
	{
		System.out.println("样子1");
	}

}
