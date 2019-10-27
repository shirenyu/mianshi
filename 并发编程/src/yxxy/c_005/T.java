package yxxy.c_005;

public class T implements Runnable{
    private int count = 5000;
    @Override
    /**
     * 线程重入问题，run代码块不是原子操作，一个线程减了1，未打印，然后轮到其他线程运行，其他线程对count-1轮到刚才的线程打印时打印的是其他线程的减过后count的值
     */
    public /* synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+"count="+count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i=0;i<5000;i++){
            new Thread(t,"THREAD"+i).start();
        }
    }
    //会有线程重入的问题
}
