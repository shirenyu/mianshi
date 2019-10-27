package com.zk.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args){
        try{
            //建立套接字
            ServerSocket server = new ServerSocket(4700);
            //监听
            Socket socket = server.accept();
            //建立连接
            InputStreamReader Sysin = new InputStreamReader(System.in);
            BufferedReader SysBuf = new BufferedReader(Sysin);

            InputStreamReader Socin = new InputStreamReader(socket.getInputStream());
            BufferedReader SocBuf = new BufferedReader(Socin);

            PrintWriter Socout = new PrintWriter(socket.getOutputStream());

            //通信
            System.out.println("Client:" + SocBuf.readLine());
            String readline = SysBuf.readLine();
            while(!readline.equals("bye")){
                Socout.println(readline);
                Socout.flush();
                System.out.println("Server:" + readline);

                String rl = SocBuf.readLine();
                if(!rl.equals("ok"))
                    System.out.println("Client:" + rl);
                else
                    break;

                readline = SysBuf.readLine();
            }
            //关闭IO、Socket
            Socout.close();
            Socin.close();
            server.close();
        }catch (Exception e){
            System.out.println("Error" + e);
        }
    }
}