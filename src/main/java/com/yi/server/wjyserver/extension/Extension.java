package com.yi.server.wjyserver.extension;

import com.yi.server.wjyserver.command.Command;

import java.util.List;

/**
 * @author wujingyi
 */
public class Extension extends Command {

    private int id;
    private List<Extension> subExtensionList;

    public Extension(int id) {
        this.id = id;
    }

    @Override
    protected void executeOnce() {

    }

    @Override
    protected void rollbackOnce() {

    }
}
