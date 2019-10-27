package 线程池;

import javafx.concurrent.Worker;

import java.util.Random;
import java.util.concurrent.*;

public class UserThreadPool {
    static class Worker implements Runnable{

        private String name;
        private Random r = new Random();

        public Worker(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try{
                Thread.sleep(r.nextInt(1000) + 2000);
            }catch (Exception e){
                System.out.println(Thread.currentThread().getId() + " sleep Interrup"+Thread.currentThread().isInterrupted());
            }
            System.out.println("任务"+name+"完成");
        }
    }
    static class CallWorker implements Callable<String> {

        private String taskName;
        private Random r = new Random();

        public CallWorker(String taskName){
            this.taskName = taskName;
        }

        public String getTaskName() {
            return taskName;
        }

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName()+" process the task: "+taskName);
            return Thread.currentThread().getName()+":"+r.nextInt(100)*5;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
/*        ExecutorService pool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy());*/
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i=0;i<6;i++){
            Worker worker = new Worker("worker_" + i);
            pool.execute(worker);
        }

        for(int i=0;i<6;i++){
            CallWorker callWorker = new CallWorker("callWorker_" + i);
            Future<String> result = pool.submit(callWorker);
            System.out.println(result.get());
        }

        pool.shutdown();
    }
}
