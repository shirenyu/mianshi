package 线程池.mypool;

import org.omg.SendingContext.RunTime;

import java.util.Random;

public class TestMyThreadPool {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("cpu核心数:"+ Runtime.getRuntime().availableProcessors());
        //线程池里初始化3个线程，任务通过new Mytask设置，默认任务数100
        MyThreadPool2 t = new MyThreadPool2(3,0);
        // 把任务加入到任务队列，线程的阻塞队列拿到任务进入运行状态
        t.execute(new MyTask("testA"));
        t.execute(new MyTask("testB"));
        t.execute(new MyTask("testC"));
        t.execute(new MyTask("testD"));
        t.execute(new MyTask("testE"));
        System.out.println(t);
        Thread.sleep(10000);
        // 任务完成销毁线程池
        t.destroy();
        System.out.println(t);
    }
    static class MyTask implements Runnable{

        private String name;
        private Random r = new Random();

        public MyTask(String name){
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
}
