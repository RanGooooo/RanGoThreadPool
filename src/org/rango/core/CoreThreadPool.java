package org.rango.core;

import org.rango.annotation.Value;
import org.rango.core.basics.ThreadPool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CoreThreadPool extends ThreadPool {

    /**
     * 线程数量
     */
    @Value("${threadPoolSize}")
    private static int threadPoolSize = 10;

    /**
     * 线程前缀
     */
    private static final String THREAD_PREFIX = "RANGO_THREAD_POOL-";

    /**
     * 线程索引数
     */
    private static volatile int thread_num = 0;

    private static final ThreadGroup threadGroup = new ThreadGroup("CoreThreadGroup");

    private static final List<CoreThread> codeThreadList = new ArrayList<>();

    /**
     * 创建一个线程
     * @return
     */
    private static CoreThread createCoreThread(){
        return new CoreThread(threadGroup, THREAD_PREFIX + (++thread_num));
    }


    static {

    }

    /**
     * 初始化线程池
     */
    private void initPool(){
        for (int i = 0; i < threadPoolSize; i++) {
            CoreThread coreThread = createCoreThread();
            codeThreadList.add(coreThread);
            coreThread.start();
        }
    }

}
