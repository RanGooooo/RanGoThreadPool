package org.rango.core;

import java.util.LinkedList;

public class CoreThread extends Thread {


    private CoreThreadState threadState = CoreThreadState.READY;

    enum CoreThreadState{
        READY, RUNNING, BLOCKED, DEAD
    }

    public CoreThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        START:
        while (threadState != CoreThreadState.DEAD){
            Runnable runnable;
            LinkedList<Runnable> taskQueue = TaskQueue.getTaskQueue();
            synchronized (taskQueue){
                while(taskQueue.isEmpty()){
                    try {
                        threadState = CoreThreadState.BLOCKED;
                        taskQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break START;
                    }
                }
                runnable = taskQueue.removeFirst();
            }
            threadState = CoreThreadState.RUNNING;
            runnable.run();
            threadState = CoreThreadState.READY;
        }
        super.run();
    }
}
