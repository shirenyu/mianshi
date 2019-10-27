package leedcode;


public class Foo {

    public Foo() {

    }
    int flag = 1;
    public void first(Runnable printFirst) throws InterruptedException {
        synchronized(this){
            while(flag!=1)
                this.wait();

            // printSecond.run() outputs "second". Do not change or remove this line.
            printFirst.run();
            flag=2;
            this.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized(this){
            while(flag!=2)
                this.wait();

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            flag=3;
            this.notifyAll();
        }

    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized(this){
            while(flag!=3)
                this.wait();

            // printSecond.run() outputs "second". Do not change or remove this line.
            printThird.run();
            this.notifyAll();
        }
    }

    public void one() {
        System.out.println("one");
    }
    public void two() {
        System.out.println("two");
    }
    public void third() {
        System.out.println("third");
    }

    public static void main(String[] args){
        /**
         *      创建线程的4种方式
         * synchronized加锁的几种方式
         * java接受键盘输入的几种方式
         * java声明数组的几种方式 https://blog.csdn.net/mrbacker/article/details/81638331
         */
        Foo foo = new Foo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.first(this);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.second(this);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.third(this);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

}