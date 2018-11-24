package org.rango.core;

import java.util.LinkedList;

public class TaskQueue {

    /**
     * 任务队列
     */
    private static final LinkedList<Runnable> taskQueue = new LinkedList<>();


    public void addTaskToQueue(Runnable task){
        synchronized (taskQueue){
            taskQueue.add(task);
        }
    }
}
