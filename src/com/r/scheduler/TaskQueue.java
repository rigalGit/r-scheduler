package com.r.scheduler;

import java.util.ArrayDeque;
import java.util.Deque;

public class TaskQueue<T> {
    private final Deque<T> taskQueue;

    public TaskQueue() {
        taskQueue = new ArrayDeque<>();
    }

    public synchronized void offer(T runnable){
        this.taskQueue.offer(runnable);
    }

    public synchronized T poll(){
        return taskQueue.poll();
    }
}
