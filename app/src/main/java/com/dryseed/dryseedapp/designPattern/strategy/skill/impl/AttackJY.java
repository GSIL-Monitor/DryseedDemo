package com.dryseed.dryseedapp.designPattern.strategy.skill.impl;


import com.dryseed.dryseedapp.designPattern.strategy.skill.IAttackBehavior;

public class AttackJY implements IAttackBehavior
{

	@Override
	public void attack()
	{
		System.out.println("九阳神功！");
	}

}
