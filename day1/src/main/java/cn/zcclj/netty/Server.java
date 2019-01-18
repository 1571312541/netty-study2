package cn.zcclj.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 〈〉
 *
 * @author 22902
 * @create 2019/1/17
 */
public class Server {

    public static void main(String[] args) {

        //两个线程组，实际是两个死循环
        //boss负责不断从客户端接收连接，把连接交给worker，由worker做操作
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //服务类
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer());
            //绑定端口，sync表示一直等待
            ChannelFuture future = bootstrap.bind(8899).sync();

            future.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


}
