package com.zk.netty;

public class HelloNetty {
    public static void main(String[] args) {

    }
}

class NettyServer{
    int port = 8888;

    public NettyServer(int port) {
        this.port = port;
    }

    public void serverStart(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup wokerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();

    }

}
