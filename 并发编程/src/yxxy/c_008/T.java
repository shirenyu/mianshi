package yxxy.c_008;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 容易产生脏读问题
 */
public class T {
    String name;
    double balance;

    public synchronized void setBalance(double balance) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public double getBalance() {

        return balance;
    }

    public static void main(String[] args){
        T t = new T();
        Thread thread = new Thread(() -> {t.setBalance(100.0);});
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getBalance());
    }
}
