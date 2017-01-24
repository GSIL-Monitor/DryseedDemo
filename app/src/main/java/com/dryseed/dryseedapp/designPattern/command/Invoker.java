package com.dryseed.dryseedapp.designPattern.command;

/**
 * Created by caiminming on 2017/1/24.
 */
public class Invoker {
    /**
     * 持有命令对象
     */
    private Command command = null;

    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * 行动方法
     */
    public void action() {

        command.execute();
    }
}
