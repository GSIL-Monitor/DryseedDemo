package com.dryseed.dryseedapp.designPattern.command.eg;

/**
 * Created by jingbin on 2016/10/31.
 * 关门的命令
 */

public class DoorCloseCommand implements Command {

    private Door door;

    public DoorCloseCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }
}
