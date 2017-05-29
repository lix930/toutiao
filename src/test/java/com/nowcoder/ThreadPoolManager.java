package com.nowcoder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */
/*
一般一个简单线程池至少包含下列组成部分

    线程池管理器（ThreadPoolManager）:用于创建并管理线程池
    工作线程（WorkThread）: 线程池中线程
    任务接口（Task）:每个任务必须实现的接口，以供工作线程调度任务的执行。
    任务队列:用于存放没有处理的任务。提供一种缓冲机制。

    线程池管理器至少有下列功能：创建线程池，销毁线程池，添加新任务。
*/
public class ThreadPoolManager {

    private static ThreadPoolManager instance = null;

    private List<String> taskQueue = Collections.synchronizedList(new LinkedList<String>());

    //private WorkThread[] workQueue;


}
