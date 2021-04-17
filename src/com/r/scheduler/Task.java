package com.r.scheduler;

public class Task {
    private final Runnable runnable;
    private final long executeAt;

    public Task(Runnable runnable, long executeAt) {
        this.runnable = runnable;
        this.executeAt = executeAt;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public long getExecuteAt() {
        return executeAt;
    }
}
