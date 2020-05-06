package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.WJYServer;
import com.yi.server.wjyserver.config.ServerSystemProperty;

public class WorkerTest {


    private static Runnable r1 = () -> System.out.println("r1");
    private static Runnable r2 = () -> System.out.println("r2");
    private static Runnable r3 = () -> System.out.println("r3");
    private static Runnable r4 = () -> {
        int i = 0;
        while (i < 1000000) {
            i++;
        }
    };
    private static Runnable r5 = () -> System.out.println("r5");
    private static Runnable r6 = () -> System.out.println("r6");

    /**
     * 创建一个Worker，
     * 0.容量正确
     * 1.往里面塞数据。能够正常跑完；
     * 2.往里面塞数据，中断，无限等待，能跑完
     * 3.往里面塞数据，中断，立刻中断，成功
     */
    public void testWorkerCapacity() {
        Worker worker = new Worker("TestWorker", 5);
        worker.addTask(r1);
        worker.addTask(r2);
        worker.addTask(r3);
        worker.addTask(r4);
        worker.addTask(r5);
        worker.addTask(r6);
        worker.start();
    }

    private static void testWorkerRun() {
        Worker worker = new Worker("TestWorker", 5);
        worker.addTask(r1);
        worker.addTask(r2);
        worker.addTask(r3);
        worker.start();
        worker.addTask(r4);
        worker.addTask(r5);
    }

    private static void testTCPSelectorAndAcceptor() {
        WJYServer server = new WJYServer();
        server.start();
    }

    public static void main(String[] args) {
        System.setProperty(ServerSystemProperty.SERVER_CONFIG_PATH, "E:\\Study\\MyCode\\wjyserver\\src\\main\\resources");
        System.setProperty(ServerSystemProperty.SERVER_WORKSPACE_CONFIG_PATH, "E:\\Study\\MyCode\\wjyserver\\src\\main\\resources");
        testTCPSelectorAndAcceptor();
    }

}