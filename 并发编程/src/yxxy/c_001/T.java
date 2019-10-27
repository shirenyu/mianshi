package yxxy.c_001;

public class T {
    private int count = 0;
    private Object o = new Object();
    public void m(){
        /**
         * // 申请o这把锁，在堆内存中的对象，只有一个。当一个线程拿到这把锁后，另外一个线程是拿不到o的，锁的信息已经写在了堆内存o中，联想到LeetCode按序打印
         * 也就是只有一把锁,那么如果三个打印方法不加锁，那么三个线程都会一直请求打印
         */
        synchronized (o){
           count++;
           System.out.println(Thread.currentThread().getName()+"count="+count);
        }
        //不加锁多个线程会，造成读写不一致
/*       count++;
        System.out.println(Thread.currentThread().getName()+"count="+count);*/
    }

    public static void main(String[] args) throws InterruptedException {
        T t = new T();

        Thread[] threads = new Thread[10000];
/*        for(int i=0;i<100;i++){
            threads[i] = new Thread(){
                @Override
                public void run(){
                    t.m();
                }
            };
        }*/
        for (int i=0;i<10000;i++){
            threads[i] = new Thread(()->{
                t.m();
            });
        }



        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(t.count);
    }

}
