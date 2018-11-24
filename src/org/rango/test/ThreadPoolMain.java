package org.rango.test;

import org.rango.core.CoreThreadPool;
import org.rango.core.TaskQueue;

public class ThreadPoolMain {

    public static void main(String[] args) {
        CoreThreadPool pool = new CoreThreadPool();
        for (int i = 0; i < 400; i++) {
            int finali = i;
            TaskQueue.addTaskToQueue(()->{
                System.out.println(Thread.currentThread().getName() + "task"+ finali);
            });
        }
    }

}
