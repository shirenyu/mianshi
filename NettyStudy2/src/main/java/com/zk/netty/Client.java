package com.zk.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

public class Client {
    public static void main(String[] args) {

    }

    private void clientStart(){
        NioEventLoopGroup wokers = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(wokers)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                   @Override
                   protected void initChannel(SocketChannel ch) throws Exception{
                       System.out.println("channel initialized ");
                       ch.pipeline().addLast(new ClientHanler());
                   }
                });
        try {
            System.out.println("start to connect...");
            ChannelFuture f = b.connect("127.0.0.1",8888).sync();

            f.channel().closeFuture().sync();

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            wokers.shutdownGracefully();
        }
    }
}

class ClientHanler extends SimpleChannelInboundHandler{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        System.out.println("channel is activated");

        final ChannelFuture f = ctx.writeAndFlush(Unpooled.compositeBuffer(Integer.parseInt("HelloNetty")));
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                System.out.println("msg send");
            }
        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        try{
            ByteBuf buf = (ByteBuf) msg;
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        
    }

}