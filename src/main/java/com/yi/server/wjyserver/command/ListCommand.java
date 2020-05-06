package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.logger.WJYServerLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wujingyi
 */
public class ListCommand extends Command {
    private List<Command> commandList;

    public ListCommand() {
        this.commandList = new ArrayList<>();
    }

    protected void beforeExecuteOnce() {
        WJYServerLogger.LOGGER.info("<ListCommand> Start execute ListCommand.");
    }

    protected void afterExecuteOnce() {
        WJYServerLogger.LOGGER.info("<ListCommand> Execute ListCommand finished.");
    }

    protected void beforeRollbackOnce() {
        WJYServerLogger.LOGGER.info("<ListCommand> Start rollback ListCommand.");
    }

    protected void afterRollbackOnce() {
        WJYServerLogger.LOGGER.info("<ListCommand> Rollback ListCommand finished.");
    }

    @Override
    protected void executeOnce() throws Throwable {
        List<Command> commandList = this.commandList;
        for (Command command : commandList) {
            command.execute();
        }
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
