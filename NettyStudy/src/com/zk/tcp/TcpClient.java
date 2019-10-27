package com.zk.tcp;
import java.net.*;
import java.io.*;



public class TcpClient {
    public static void main(String[] args)throws Exception{
        try{
            //创建Socket
            Socket socket = new Socket("127.0.0.1",4700);

            //建立连接
            InputStreamReader Sysin = new InputStreamReader(System.in);
            BufferedReader SysBuf = new BufferedReader(Sysin);

            InputStreamReader Socin = new InputStreamReader(socket.getInputStream());
            BufferedReader SocBuf = new BufferedReader(Socin);

            PrintWriter Socout = new PrintWriter(socket.getOutputStream());

            //进行通信
            String readline = SysBuf.readLine();
            while(!readline.equals("bye")){
                Socout.println(readline);
                Socout.flush();
                System.out.println("Client:" + readline);

                System.out.println("Server:" + SocBuf.readLine());
                readline = SysBuf.readLine();
            }
            //关闭IO和Socket
            Socout.close();
            Socin.close();
            socket.close();
        }catch(Exception e){
            System.out.println("Error:" + e);
        }
    }
}
