package org.common;

public class RedisKeyConstant {

    // 用户信息
    public static final String USER_INFO = "user:info:";

    // 选课列表总数
    public static final String COURSE_LIST_TOTAL = "course:list:total";

    // 课程基础信息
    public static final String COURSE_LIST_BASE = "course:list:base:";

    // 课程的分布式锁
    public static final String COURSE_SELECT_LOCK = "course:select:lock:";

    // 课程库存（永久不过期）
    public static final String COURSE_STOCK = "course:stock:";

    // 课程的已选学生列表
    public static final String COURSE_SELECTED_STUDENT = "course:selected:student:";

    // 异步选课的结果
    public static final String COURSE_SELECTED_RESULT = "course:selected:result:";
}
