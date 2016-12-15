package com.dryseed.dryseedapp.designPattern.strategy.skill.impl;


import com.dryseed.dryseedapp.designPattern.strategy.skill.IDefendBehavior;

public class DefendTBS implements IDefendBehavior
{

	@Override
	public void defend()
	{
		System.out.println("铁布衫");
	}

}
