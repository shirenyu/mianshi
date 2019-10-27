package yxxy.c_007;

/**
 * 同步方法和非同步方法是否可以同时调用
 */
public class T {
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName()+"m1 start");
        try{
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m1 end");
    }

    /**
     *
     * String OK = jedis.set("sku:" + skuId + ":lock", "1", "nx", "px", 10000);
     * redis缓存分布式锁，只有一个线程可以使用redis，通过set方法得到返回值，返回ok说明得到这把锁，没有返回ok说明没有set成功。
     */

    public /*synchronized*/ void m2() {//在m1()加锁运行被运行时，其他线程也可以运行m2();锁的是对象，但是m2()不需要锁
        try {               //如果m2也加锁，那么只有先进来的线程能获得这把锁，即使先进来的线程没有调用m2()。
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m2");
    }

    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        new Thread(() -> {t.m1();}).start();
        Thread.sleep(1000);
        new Thread(() -> {
            t.m2();
//            t.m1();
        }).start();
    }
    /**
     * m1加锁时，m2可以被其他线程使用
     * 锁的是对象，对象的某个方法加了synchronized关键字在被其他线程占用期间，其他线程不能访问。可以访问没有加锁的。
     * 虽然没有运行，但是可以没有释放锁
     */
}
