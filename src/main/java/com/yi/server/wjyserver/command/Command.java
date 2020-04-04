package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.logger.WJYServerLogger;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wujingyi
 * 命令，可执行一次，可回滚。
 */
public abstract class Command {
    /** 是否已经执行 */
    private AtomicBoolean hadExecuted = new AtomicBoolean(false);
    /** 是否已经回滚 */
    private AtomicBoolean hadRollback = new AtomicBoolean(false);

    public final void execute() {
        if (hadExecuted.compareAndSet(false, true)) {
            beforeExecuteOnce();
            executeOnce();
            afterExecuteOnce();
        }
    }

    public final void rollback() {
        if (!hadExecuted.compareAndSet(true, false)) {
            WJYServerLogger.LOGGER.info("<Command> Rollback failed. The command had not been executed.");
            return;
        }
        if (hadRollback.compareAndSet(false, true)) {
            beforeRollbackOnce();
            rollbackOnce();
            afterRollbackOnce();
            return;
        }
        WJYServerLogger.LOGGER.warn("<Command> Rollback failed. The command had been rollback.");
    }

    protected void beforeExecuteOnce() {

    }

    protected void afterExecuteOnce() {

    }

    protected abstract void executeOnce();

    protected void beforeRollbackOnce() {

    }

    protected void afterRollbackOnce() {

    }

    protected abstract void rollbackOnce();
}
