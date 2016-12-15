package com.dryseed.dryseedapp.designPattern.decorator.equip;


import com.dryseed.dryseedapp.designPattern.decorator.IEquip;

/**
 * Created by jingbin on 2016/11/1.
 * 鞋子
 * 攻击力: 5
 */

public class ShoeEquip implements IEquip {

    @Override
    public int caculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "圣战靴子";
    }
}
