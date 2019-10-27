package com.zk.io.bio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress("127.0.0.1",8888));
        while (true){
            Socket s = ss.accept(); //阻塞方法
            String name="["+s.getInetAddress().getHostAddress()+":"+s.getPort()+"]";// 在多线程中socket还标记不是一个端口号？
            System.out.println(name);
            new Thread(()->{
               handle(s);
            }).start();
        }
    }
    static void handle(Socket socket){
        try {
            byte[] bytes = new byte[1024];
            int len = socket.getInputStream().read(bytes); //read()方法是阻塞的.那么有时候就会有问题：如果客户端只是连上来没有给你写，那么这个线程，就停在这里了。当有数据了cpu把你唤醒，你才能继续走。
            System.out.println(new String(bytes,0,len));

            socket.getOutputStream().write(bytes,0,len); //write()方法也是阻塞的，如果客户端不接收那么，同理这个线程也停在这了。
            socket.getOutputStream().flush();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
