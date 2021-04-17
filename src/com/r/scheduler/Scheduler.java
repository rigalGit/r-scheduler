package com.r.scheduler;

public interface Scheduler {
    void schedule(Runnable task, long delayInMs);
}
