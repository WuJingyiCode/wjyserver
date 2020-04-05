package com.yi.server.wjyserver.command;

import org.junit.Test;

public class ListCommandTest {
    @Test
    public void testListCommand() {
        ListCommand listCommand = new ListCommand();
        listCommand.addCommand(new LogoCommand());
        listCommand.addCommand(new AvoidRepeatStartCommand());
        try {
            listCommand.execute();
            throw new RuntimeException("Test rollback");
        } catch (Throwable t) {
            listCommand.rollback();
        }
    }
}