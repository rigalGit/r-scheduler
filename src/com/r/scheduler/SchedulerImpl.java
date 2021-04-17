package com.r.scheduler;

import java.util.PriorityQueue;

public class SchedulerImpl implements Scheduler {

    private PriorityQueue<Task> priorityQueue;
    private TaskQueue<Runnable> taskQueue;
    private int concurrency;
    private CustomThreadPool threadPool;
    private ScheduleWorker scheduleWorker;

    public SchedulerImpl(int concurrency) {
        this.concurrency = concurrency;
        this.taskQueue = new TaskQueue<>();
        this.priorityQueue = new PriorityQueue<>((t1,t2)-> (int) (t1.getExecuteAt()-t2.getExecuteAt()));
        this.threadPool = new CustomThreadPool(concurrency,taskQueue);
        this.scheduleWorker = new ScheduleWorker(priorityQueue,taskQueue);
        new Thread(this.scheduleWorker).start();

    }





    @Override
    public void schedule(Runnable task, long delayInMs) {
        // notify ScheduleWorker
        Task task1 = new Task(task,System.currentTimeMillis()+delayInMs);
        this.priorityQueue.add(task1);
        synchronized (priorityQueue){
            priorityQueue.notifyAll();
        }
    }
}
