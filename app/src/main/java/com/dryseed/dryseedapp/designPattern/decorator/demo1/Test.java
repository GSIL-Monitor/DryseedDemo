package com.dryseed.dryseedapp.designPattern.decorator.demo1;


import com.dryseed.dryseedapp.designPattern.decorator.demo1.equip.ArmEquip;
import com.dryseed.dryseedapp.designPattern.decorator.demo1.equip.ShoeEquip;
import com.dryseed.dryseedapp.designPattern.decorator.demo1.gem.BlueGemDecorator;
import com.dryseed.dryseedapp.designPattern.decorator.demo1.gem.RedGemDecorator;
import com.dryseed.dryseedapp.designPattern.decorator.demo1.gem.YellowGemDecorator;

/**
 * Created by User on 2016/12/14.
 * <p>
 * 定义：动态给一个对象添加一些额外的职责,就象在墙上刷油漆.使用Decorator模式相比用生成子类方式达到功能的扩充显得更为灵活。
 * 设计初衷:通常可以使用继承来实现功能的拓展,如果这些需要拓展的功能的种类很繁多,
 * 那么势必生成很多子类,增加系统的复杂性,同时,使用继承实现功能拓展,我们必须可预见这些拓展功能,这些功能是编译时就确定了,是静态的。
 */
public class Test {
    /*
        一个镶嵌2颗红宝石，1颗蓝宝石的靴子
        攻击力  : 40
        描述 :圣战靴子+ 蓝宝石+ 红宝石+ 红宝石
        -------
         一个镶嵌1颗红宝石，1颗蓝宝石,1颗黄宝石的武器
        攻击力  : 50
        描述 :屠龙宝刀+ 黄宝石+ 蓝宝石+ 红宝石
        -------
     */
    public static void main(String[] args) {
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
