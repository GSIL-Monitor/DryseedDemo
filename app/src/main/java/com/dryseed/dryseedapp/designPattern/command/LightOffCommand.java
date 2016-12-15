package com.dryseed.dryseedapp.designPattern.command;

/**
 * Created by jingbin on 2016/10/31.
 * 关灯的命令
 */

public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
