package 线程池.mypool2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

public class MyThreradPool_kh1 {
    private static int WORKERNUM = 5;
    private static int MAXTASK = 10;
    private int workerNum;
    ArrayBlockingQueue<Callable> taskQueue;
    WokerThread[] threads;
    //默认线程大小
    public MyThreradPool_kh1(){
        this(WORKERNUM,MAXTASK); // 可能实例化时调用的不是这个构造器，推出WORKERNUM/MAXTASK没有被实例化
    }

    //自定义线程池大小
    public MyThreradPool_kh1(int workerNum,int maxTask){
        this.workerNum = workerNum>WORKERNUM?WORKERNUM:workerNum;
        taskQueue = new ArrayBlockingQueue<>(maxTask);
        threads = new WokerThread[WORKERNUM];
        for (WokerThread thread : threads) {
            thread = new WokerThread();
            thread.start();
        }
    }

    public static class TaskCall implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("print"+(int)100*Math.random());
            return Thread.currentThread().getName();
        }
    }

    public void execute(Callable<String> call){
        taskQueue.add(call);
    }

    public void destroyPool(){
        for (WokerThread thread : threads) {
            thread.stopWorker();
        }
        taskQueue.clear();
    }


    class WokerThread extends Thread{
        @Override
        public void run() {
            Callable task = null;
            try {
                task = taskQueue.take();
                if (task!=null){
                    String str = (String) task.call();
                    System.out.println("retrun:"+str);
                }
                task=null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 线程执行完毕销毁
        public void stopWorker(){
            interrupt();
        }
    }

}
