package xyz.comfyz.learning.socket.batch;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : comfy create at 2018-04-09 14:23
 */
public class ThreadPool extends ThreadGroup {
    private final static AtomicInteger threadPoolId = new AtomicInteger(0);         //线程池ID
    private final static AtomicInteger threadId = new AtomicInteger(0);             //工作线程ID
    private final static AtomicBoolean isClosed = new AtomicBoolean(false);         //线程池状态
    private final static ConcurrentLinkedQueue<Runnable> workQueue = new ConcurrentLinkedQueue<Runnable>();     //工作队列

    public ThreadPool(int poolSize) {
        super("ThreadPool-" + threadPoolId.incrementAndGet());
        setDaemon(true);
        for (int i = 0; i < poolSize; i++)
            new Worker().start();
    }

    /**
     * 向工作队列中加入一个新任务，由工作线程去执行该任务
     */
    public synchronized void execute(Runnable task) {
        if (isClosed.get()) {                   //线程池被关则抛出 IllegalStateException 异常
            throw new IllegalStateException();
        }
        if (task != null) {
            workQueue.add(task);
            notify();                           //唤醒正在 getTask()方法中等待任务的工作线程
        }
    }

    /**
     * 从工作队列中取出一个任务，工作线程会调用此方法
     */
    private synchronized Runnable getTask() throws InterruptedException {
        Runnable task = workQueue.poll();
        if (task == null) {
            if (isClosed.get())
                return null;
            wait();                         //如果工作队列中没有任务，就等待任务
            return workQueue.poll();
        }
        return task;
    }

    /**
     * 关闭线程池
     */
    public synchronized void close() {
        if (isClosed.compareAndSet(false, true)) {
            workQueue.clear();          //清空工作队列
            interrupt();                //中断所有的工作线程，该方法继承自 ThreadGroup 类
        }
    }

    /**
     * 等待工作线程把所有任务执行完
     */
    public void join() {
        synchronized (this) {
            isClosed.set(true);
            notifyAll();                //唤醒还在 getTask()方法中等待任务的工作线程
        }

        Thread[] threads = new Thread[activeCount()];
        //enumerate()方法继承自 ThreadGroup 类，获得线程组中当前所有活着的工作线程
        int count = enumerate(threads);
        for (int i = 0; i < count; i++) {       //等待所有工作线程运行结束
            try {
                threads[i].join();              //等待工作线程运行结束
            } catch (InterruptedException e) {
                System.out.println("#join : " + Thread.currentThread().getThreadGroup().getName() + " " + Thread.currentThread().getName() + ": interrupted");
            }
        }
    }

    /**
     * 内部类：工作线程
     */
    private class Worker extends Thread {
        Worker() {
            //加入到当前 ThreadPool 线程组中
            super(ThreadPool.this, "Worker-" + threadId.incrementAndGet());
        }

        @Override
        public void run() {
            while (!isInterrupted()) {      //isInterrupted()方法继承自 Thread 类，判断线程是否被中断
                Runnable task = null;
                try {
                    task = getTask();              //取出任务
                } catch (InterruptedException e) {
                    System.out.println("#run : " + Thread.currentThread().getThreadGroup().getName() + " " + Thread.currentThread().getName() + ": interrupted");
                }

                //如果 getTask()返回 null 或者线程执行 getTask()时被中断，则结束此线程
                if (task == null)
                    return;
                else {
                    task.run();
                }
            }
        }

    }

}
