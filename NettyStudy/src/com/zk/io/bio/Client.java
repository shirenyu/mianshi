package com.zk.io.bio;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Thread[] threads = new Thread[1000];
        Object obj = new Object();
        for (int i=0;i<1000;i++){
            threads[i] = new Thread(()->{
//                synchronized (obj)
                { // 多线程，每个线程都重连了socket，串行
                    Socket socket = null;
                    try {
                        // 进程连到socket
                        socket = new Socket("127.0.0.1", 8888);
                        socket.getOutputStream().write("Hello Server".getBytes());
                        socket.getOutputStream().flush();

                        System.out.println("write over,waiting for msg back....");
                        byte[] bytes = new byte[1024];
                        int len = socket.getInputStream().read(bytes);//读取从服务器端中写回的数据
                        System.out.println(new String(bytes,0,len));
//                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        int i =0;
        for (Thread thread : threads) {
            thread.start();
            thread.join();
            System.out.println(i++);
        }
    }
}
