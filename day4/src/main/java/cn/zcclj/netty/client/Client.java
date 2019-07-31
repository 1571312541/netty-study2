package cn.zcclj.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 〈〉
 *
 * @author 22902
 * @create 2019/1/18
 */
public class Client {

    public static void main(String[] args) throws Exception {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors).channel(NioSocketChannel.class);
            bootstrap.handler(new ClientInitalizer());
            ChannelFuture future = bootstrap.connect("localhost", 8899).sync();
            Channel channel = future.channel();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                channel.writeAndFlush(br.readLine()+"\r\n");
            }

        }finally {
            eventExecutors.shutdownGracefully();
        }


    }


}
