package com.dryseed.dryseedapp.designPattern.adapter;

import com.dryseed.dryseedapp.designPattern.command.Command;
import com.dryseed.dryseedapp.designPattern.command.Computer;
import com.dryseed.dryseedapp.designPattern.command.ComputerOffCommand;
import com.dryseed.dryseedapp.designPattern.command.ComputerOnCommand;
import com.dryseed.dryseedapp.designPattern.command.ControlPanel;
import com.dryseed.dryseedapp.designPattern.command.Door;
import com.dryseed.dryseedapp.designPattern.command.DoorCloseCommand;
import com.dryseed.dryseedapp.designPattern.command.DoorOpenCommand;
import com.dryseed.dryseedapp.designPattern.command.Light;
import com.dryseed.dryseedapp.designPattern.command.LightOffCommand;
import com.dryseed.dryseedapp.designPattern.command.LightOnCommand;
import com.dryseed.dryseedapp.designPattern.command.QuickCommand;

/**
 * Created by User on 2016/12/14.
 */
/**
 * 适配器模式:
 * 定义：将一个类的接口转换成客户期望的另一个接口，适配器让原本接口不兼容的类可以相互合作。
 * 这个定义还好，说适配器的功能就是把一个接口转成另一个接口。
 * 如题目，手机充电器一般都是5V左右吧，咱天朝的家用交流电压220V，所以手机充电需要一个适配器（降压器）
 */
public class Test {
    public static void main(String[] args)
    {

        /**
         * 给手机充电
         */
        Mobile mobile = new Mobile();
        V5Power v5Power = new V5PowerAdapter(new V220Power());
        mobile.inputPower(v5Power);
    }
}