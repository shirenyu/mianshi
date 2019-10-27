package com.zk.io.nio;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolServer {
    ExecutorService pool = Executors.newFixedThreadPool(50);

    private Selector selector;

    public static void main(String[] args) {
        PoolServer server = new PoolServer();
        try{
            server.initServer(8000);
            server.listen();
        }catch (IOException e){

        }
    }

    public void initServer(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));

        this.selector = Selector.open();

        serverChannel.register(selector,SelectionKey.OP_ACCEPT);
        System.out.println("服务端连接成功");
    }

    public void listen() throws IOException{
        while (true){
            selector.select();

            Iterator it = this.selector.selectedKeys().iterator();
            while (it.hasNext()){
                 SelectionKey key = (SelectionKey)it.next();
                 it.remove();

                 // 连接
                 if (key.isAcceptable()){
                     ServerSocketChannel server = (ServerSocketChannel)key.channel();
                     SocketChannel channel = server.accept();
                     channel.configureBlocking(false);
                     channel.register(this.selector,SelectionKey.OP_READ);
                 }else if(key.isReadable()){
                     key.interestOps(key.interestOps()&(~SelectionKey.OP_READ));
                    pool.execute(new ThreadHandlerChannel(key));
                 }
            }
        }
    }
}

class ThreadHandlerChannel extends Thread{
    private SelectionKey key;
    public ThreadHandlerChannel(SelectionKey key){
        this.key = key;
    }

    @Override
    public void run(){
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

    }

}

