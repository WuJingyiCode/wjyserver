package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.logger.WJYServerLogger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 工作者，用于处理任务
 *
 * @author wujingyi
 */
public class Worker implements Runnable {
    private Thread workThread;
    private BlockingQueue<Runnable> taskQueue;
    private boolean isRunning;
    private boolean forceStop;

    public Worker(String name, int queueLength) {
        workThread = new Thread(this, name);
        workThread.setDaemon(false);
        taskQueue = new ArrayBlockingQueue<>(queueLength);
    }

    void start() {
        isRunning = true;
        forceStop = false;
        workThread.start();
    }

    @Override
    public void run() {
        while (isRunning || (!forceStop && !taskQueue.isEmpty())) {
            Runnable task = taskQueue.poll();
            if (task != null) {
                task.run();
            }
        }
    }

    public void join(long shuntDownBeginMs, long awaitShutDownTimeOutMs) {
        stopRunningState();
        try {
            if (awaitShutDownTimeOutMs <= 0) {
                workThread.join();
            } else {
                long waitMs = awaitShutDownTimeOutMs - (System.currentTimeMillis() - shuntDownBeginMs);
                workThread.join(waitMs);
            }
        } catch (InterruptedException e) {
            WJYServerLogger.LOGGER.error("<Worker> Shutdown worker error: ", e);
        }
        // 如果依然存活，强制杀掉
        if (workThread.isAlive()) {
            forceStop = true;
            long escapeMs = System.currentTimeMillis() - shuntDownBeginMs;
            WJYServerLogger.LOGGER.warn(String.format("<Worker> Worker is still alive. We will interrupt it forcefully. workerName=%s, escapeMs=%s", workThread.getName(), escapeMs));
            workThread.interrupt();
        }
    }

    /**
     * 不在向该Worker加入新的Task
     */
    private void stopRunningState() {
        isRunning = false;
    }

    void addTask(Runnable task) {
        if (!taskQueue.offer(task)) {
            WJYServerLogger.LOGGER.error(String.format("<Worker> taskQueue is full. threadName=%s, queueSize=%s", workThread.getName(), taskQueue.size()));
        }
    }

    public void join() {
        try {
            workThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
