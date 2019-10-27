package com.gupao.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class Server {
    private static int DEFAULT_PORT = 7777;

    private static ServerSocket serverSocket;
    public static void start() throws IOException{
        start(DEFAULT_PORT);
    }

    public synchronized static void start(int port) throws IOException{
        if (serverSocket!=null)
            return;
        try{
            serverSocket = new ServerSocket(port);
            log.info("服务端已开启，端口号："+port);

            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        }finally {
            if (serverSocket!=null){
                serverSocket.close();
                serverSocket = null;
            }
        }
    }

}
