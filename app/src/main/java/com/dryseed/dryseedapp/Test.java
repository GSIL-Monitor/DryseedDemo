package com.dryseed.dryseedapp;


import com.dryseed.dryseedapp.designPattern.state.better.VendingMachineBetter;
import com.dryseed.dryseedapp.designPattern.state.old.VendingMachine;

import java.text.NumberFormat;

public class Test
{
	public static void main(String[] args)
	{
		// -0.5 ~ -0.7
		for(int i=0; i<15; i++){
			NumberFormat n = NumberFormat.getInstance();
			n.setMaximumFractionDigits(1);
			float f = Float.parseFloat(n.format(Math.random())) * 0.2f + 0.5f;
			System.out.println(f);
		}
	}
}
