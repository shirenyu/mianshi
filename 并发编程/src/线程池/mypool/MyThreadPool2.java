package 线程池.mypool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPool2 {
    // 线程池中默认的线程个数
    private static int WORK_NUM = 5;
    // 队列默认的任务个数
    private static int TASK_COUNT = 100;
    // 工作线程组
    private WorkThread[] workThreads;
    // 任务队列，作为一个缓冲
    private final BlockingQueue<Runnable> taskQueue;
    private final int worker_num;
    // 创建具有默认线程个数的线程池
    public MyThreadPool2(){
        this(WORK_NUM,TASK_COUNT);//让构造方法调用构造方法
    }

    // 创建线程池，woker_num为线程中工作线程的个数
    public MyThreadPool2(int worker_num,int taskCount){
        if(worker_num<=0) worker_num = WORK_NUM;
        if(taskCount<=0) taskCount = TASK_COUNT;
        this.worker_num = worker_num;
        this.taskQueue = new ArrayBlockingQueue<>(taskCount); //指定数组长度
        workThreads = new WorkThread[worker_num];
        //new出线程池的是否就把线程全部启动，免去线程启动的时间，后面的destroy()方法销毁，集中销毁，免去线程自己去销毁的时间
        for(int i=0;i<worker_num;i++){
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }
    }
    // 执行任务，其实是把任务加入任务队列，什么时候执行由线程池管理器决定
    public void execute(Runnable task){
        try{
            taskQueue.put(task);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 销毁线程池，该方法在保证所有任务都完成的情况下才销毁所有线程，否则等待任务完成
    public void destroy(){
        System.out.println("ready to close pool");
        for (int i=0;i<worker_num;i++){
            workThreads[i].stopWorker();
            workThreads[i] = null; //帮助gc
        }
        taskQueue.clear();
    }

    // 重写toString方法，返回线程池信息：工作线程池的个数和已完成任务的个数
    public String toString(){
        return "WorkThread number:" + worker_num
                 + "  wait task number:" + taskQueue.size();
    }

    /**
     * 内部类：工作线程
     * 线程从任务队列中取出任务运行
     */
    private class WorkThread extends Thread{
        @Override
        public void run(){
            Runnable r = null;
            try {
                while(!isInterrupted()){
                    r = taskQueue.take(); //一开始队列是空，阻塞状态，当有传入Rannable任务的时候，线程拿到任务就会去执行
                    if(r!=null){
                        System.out.println("线程"+getId()+" ready exec : " + r);
                        r.run();
                    }
                    r = null;
                }
            }catch (Exception e){
//                e.printStackTrace();
            }
        }
        // 线程执行完毕销毁
        public void stopWorker(){
            interrupt();
        }
    }

}
