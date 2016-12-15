package com.dryseed.dryseedapp.designPattern.strategy.skill.impl;


import com.dryseed.dryseedapp.designPattern.strategy.skill.IAttackBehavior;

public class AttackXL implements IAttackBehavior
{

	@Override
	public void attack()
	{
		System.out.println("降龙十八掌！");
	}

}
