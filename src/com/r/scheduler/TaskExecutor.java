package com.r.scheduler;

public class TaskExecutor implements Runnable {
    private final TaskQueue<Runnable> taskQueue;
    private volatile boolean shouldStop = false;

    public TaskExecutor(TaskQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (true ){
            Runnable task = taskQueue.poll();
            if(task != null){
                task.run();
            }
            else {
                synchronized (taskQueue) {
                    try {
                        taskQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ;
                }
            }

        }
    }


}
