package com.yi.server.wjyserver.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerTest {


    private Runnable r1 = () -> System.out.println("r1");
    private Runnable r2 = () -> System.out.println("r2");
    private Runnable r3 = () -> System.out.println("r3");
    private Runnable r4 = () -> {
        int i = 0;
        while (i < 1000000) {
            i++;
        }
    };
    private Runnable r5 = () -> System.out.println("r5");
    private Runnable r6 = () -> System.out.println("r6");

    /**
     * 创建一个Worker，
     * 0.容量正确
     * 1.往里面塞数据。能够正常跑完；
     * 2.往里面塞数据，中断，无限等待，能跑完
     * 3.往里面塞数据，中断，立刻中断，成功
     */
    @Test
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

    @Test
    public void testWorkerRun() {
        Worker worker = new Worker("TestWorker", 5);
        worker.addTask(r1);
        worker.addTask(r2);
        worker.addTask(r3);
        worker.start();
        worker.addTask(r4);
        worker.addTask(r5);
    }



}