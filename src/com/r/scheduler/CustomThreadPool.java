package com.r.scheduler;

public class CustomThreadPool {
    private final int concurrency;


    private final TaskQueue<Runnable> taskQueue;

    public CustomThreadPool(int concurrency, TaskQueue taskQueue) {
        this.concurrency = concurrency;
        this.taskQueue = taskQueue;
    }


    public void submitTask(Runnable runnable){
        this.taskQueue.offer(runnable);
    }

    public void start(){
        for(int i=0;i<concurrency;i++){
            new Thread(new TaskExecutor(taskQueue) ).start();
        }


    }


}
