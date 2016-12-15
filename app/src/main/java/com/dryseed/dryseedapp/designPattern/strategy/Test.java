package com.dryseed.dryseedapp.designPattern.strategy;


import com.dryseed.dryseedapp.designPattern.strategy.skill.impl.AttackXL;
import com.dryseed.dryseedapp.designPattern.strategy.skill.impl.DefendTBS;
import com.dryseed.dryseedapp.designPattern.strategy.skill.impl.DisplayA;
import com.dryseed.dryseedapp.designPattern.strategy.skill.impl.RunJCTQ;

public class Test
{
	public static void main(String[] args)
	{

		Role roleA = new RoleA("A");

		roleA.setAttackBehavior(new AttackXL())
				.setDefendBehavior(new DefendTBS())
				.setDisplayBehavior(new DisplayA())
				.setRunBehavior(new RunJCTQ());
		System.out.println(roleA.name + ":");
		roleA.run();
		roleA.attack();
		roleA.defend();
		roleA.display();
	}
}
