package xyz.comfyz.learning.socket.batch;

/**
 * @author : comfy create at 2018-04-10 16:32
 */
public class ThreadPoolTester {

    public static void main(String[] args) {
        int numTasks = 20;       //任务的数目
        int poolSize = 5;       //线程池中的线程数目
        ThreadPool threadPool = new ThreadPool(poolSize); //创建线程池
        // 运行任务
        for (int i = 0; i < numTasks; i++)
            threadPool.execute(createTask(i));

        threadPool.join(); //等待工作线程完成所有的任务
//        threadPool.close(); //关闭线程池
    }

    /**
     * 定义了一个简单的任务(打印 ID)
     */
    private static Runnable createTask(final int taskID) {
        return new Runnable() {
            public void run() {
//                System.out.println(Thread.currentThread().getThreadGroup().getName() + " " + Thread.currentThread().getName() + " Task" + taskID + ": start");
                try {
                    Thread.sleep(500); //增加执行一个任务的时间
                } catch (InterruptedException e) {
                    System.out.println("#task" + taskID + " : " + Thread.currentThread().getThreadGroup().getName() + " " + Thread.currentThread().getName() + ": interrupted");
                }
                System.out.println(Thread.currentThread().getThreadGroup().getName() + " " + Thread.currentThread().getName() + " Task" + taskID + ": end");
            }
        };
    }

}
