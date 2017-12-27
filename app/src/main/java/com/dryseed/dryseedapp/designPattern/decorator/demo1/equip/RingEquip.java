package com.dryseed.dryseedapp.designPattern.decorator.demo1.equip;


import com.dryseed.dryseedapp.designPattern.decorator.demo1.IEquip;

/**
 * Created by jingbin on 2016/11/1.
 * 戒指
 * 攻击力 5
 */

public class RingEquip implements IEquip {

    @Override
    public int caculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "圣战戒指";
    }
}
