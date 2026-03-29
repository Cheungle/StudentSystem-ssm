package org.config;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class MyThreadFactory implements ThreadFactory {
    // 线程编号
    private final AtomicInteger threadNum = new AtomicInteger(1);
    // 线程名前缀
    private final String namePrefix;

    // 构造方法传入业务名称
    public MyThreadFactory(String threadName) {
        namePrefix = threadName + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        // 创建线程，并自定义名字
        Thread thread = new Thread(r, namePrefix + threadNum.getAndIncrement());
        log.info("创建线程：" + thread.getName());
        return thread;
    }
}
