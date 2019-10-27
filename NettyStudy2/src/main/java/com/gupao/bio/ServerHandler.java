package com.gupao.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class ServerHandler implements Runnable {
    Socket socket;
    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            String expression;
            String result;
            while (true){
                if ((expression=in.readLine())==null)break;
                log.info("服务器收到的信息"+expression);
                result = Calulator.cal(expression);
                out.print(result);
            }
        }catch (Exception e){

        }finally {

        }
    }
}
