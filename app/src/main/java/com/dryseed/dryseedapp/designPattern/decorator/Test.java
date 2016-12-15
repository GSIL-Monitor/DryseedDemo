package com.dryseed.dryseedapp.designPattern.decorator;

import com.dryseed.dryseedapp.designPattern.decorator.equip.ArmEquip;
import com.dryseed.dryseedapp.designPattern.decorator.equip.ShoeEquip;
import com.dryseed.dryseedapp.designPattern.decorator.gem.BlueGemDecorator;
import com.dryseed.dryseedapp.designPattern.decorator.gem.RedGemDecorator;
import com.dryseed.dryseedapp.designPattern.decorator.gem.YellowGemDecorator;

/**
 * Created by User on 2016/12/14.
 */
public class Test
{
    public static void main(String[] args)
    {
        // 一个镶嵌2颗红宝石，1颗蓝宝石的靴子
        System.out.println(" 一个镶嵌2颗红宝石，1颗蓝宝石的靴子");
        IEquip equip = new RedGemDecorator(new RedGemDecorator(new BlueGemDecorator(new ShoeEquip())));
        System.out.println("攻击力  : " + equip.caculateAttack());
        System.out.println("描述 :" + equip.description());
        System.out.println("-------");
        // 一个镶嵌1颗红宝石，1颗蓝宝石的武器
        System.out.println(" 一个镶嵌1颗红宝石，1颗蓝宝石,1颗黄宝石的武器");
        equip = new RedGemDecorator(new BlueGemDecorator(new YellowGemDecorator(new ArmEquip())));
        System.out.println("攻击力  : " + equip.caculateAttack());
        System.out.println("描述 :" + equip.description());
        System.out.println("-------");
    }
}
