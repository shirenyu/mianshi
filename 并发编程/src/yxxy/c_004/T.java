package yxxy.c_004;

public class T {
    private static int count = 10;

    public synchronized static void m(){//这里等同
        count--;
        System.out.println(Thread.currentThread().getName()+"count="+count);
    }

    public static void mm(){
        synchronized (T.class){
            count--;
        }
    }

}