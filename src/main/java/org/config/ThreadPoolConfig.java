package org.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfig {
    /**
     * 核心线程池：只处理 选课、扣库存、写入操作
     */
    @Bean("corePool")
    public Executor corePool() {
        return new ThreadPoolExecutor(
                10,          // 核心线程：稳定处理选课
                30,          // 最大线程：保护数据库不被打崩
                60L, // 非核心线程空闲时间
                TimeUnit.SECONDS, //时间单位
                new ArrayBlockingQueue<>(200),  // 排队选课
                new MyThreadFactory("core-pool"),
                (r, pool) -> {
                    throw new RejectedExecutionException("线程池已满");
                }
        );
    }

}
