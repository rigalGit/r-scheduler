package com.r.scheduler;

import java.util.PriorityQueue;

public class ScheduleWorker implements Runnable{
    private PriorityQueue<Task> priorityQueue;
    private TaskQueue<Runnable> taskQueue;

    public ScheduleWorker(PriorityQueue<Task> priorityQueue, TaskQueue<Runnable> taskQueue) {
        this.priorityQueue = priorityQueue;
        this.taskQueue = taskQueue;
    }




    // 2:00 pm , 2:01 pm
    // 2:01
    // 2:02 , 3:00 pm
    public void start(){
        while (true){
            Task task = priorityQueue.peek();
            if(task == null){
                synchronized (priorityQueue){
                    wait(priorityQueue);
                }
            }
            if( task.getExecuteAt() <= System.currentTimeMillis()){
                task = priorityQueue.poll();
                taskQueue.offer(task.getRunnable());
                taskQueue.notifyAll();
            }
            else {
                long waitTime = task.getExecuteAt() - System.currentTimeMillis();
                synchronized (priorityQueue){
                    waitWithtime(priorityQueue,waitTime);
                }
            }
        }
    }

    private void wait(Object obj){
        try {
            obj.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitWithtime(Object obj, long ms){
        try {
            obj.wait(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        start();
    }
}
