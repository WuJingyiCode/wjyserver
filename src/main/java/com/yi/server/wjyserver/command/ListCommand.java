package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.logger.WJYServerLogger;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command {
    private List<Command> commandList;

    public ListCommand() {
        this.commandList = new ArrayList<>();
    }

    @Override
    protected void beforeExecuteOnce() {
        WJYServerLogger.LOGGER.info("<ListCommand> Start execute ListCommand.");
    }

    @Override
    protected void afterExecuteOnce() {
        WJYServerLogger.LOGGER.info("<ListCommand> Execute ListCommand finished.");
    }

    @Override
    protected void beforeRollbackOnce() {
        WJYServerLogger.LOGGER.info("<ListCommand> Start rollback ListCommand.");
    }

    @Override
    protected void afterRollbackOnce() {
        WJYServerLogger.LOGGER.info("<ListCommand> Rollback ListCommand finished.");
    }

    @Override
    protected void executeOnce() {
        List<Command> commandList = this.commandList;
        commandList.forEach(Command::execute);
    }

    @Override
    protected void rollbackOnce() {
        List<Command> commandList = this.commandList;
        int size = commandList.size();
        for (int i = size - 1; i >= 0; i--) {
            Command command = commandList.get(i);
            command.rollback();
        }
    }

    public void addCommand(Command command) {
        this.commandList.add(command);
    }

}
