package 线程池.mypool2;

import java.util.concurrent.Callable;

public class TestPool {
    public static void main(String[] args) {
        Callable<String> task1 = new MyThreradPool_kh1.TaskCall(); // 内部类要在static中new时，为什么要static？
        Callable<String> task2 = new MyThreradPool_kh1.TaskCall(); // 内部类要在static中new时，为什么要static？
        Callable<String> task3 = new MyThreradPool_kh1.TaskCall(); // 内部类要在static中new时，为什么要static？
        MyThreradPool_kh1 pool =new MyThreradPool_kh1();
        pool.execute(task1);
        pool.execute(task2);
        pool.execute(task3);
        pool.destroyPool();
    }
}
