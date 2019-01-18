package cn.zcclj.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;


/**
 * 〈〉
 *
 * @author 22902
 * @create 2019/1/18
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext context, String msg) throws Exception {
        System.out.println(context.channel().remoteAddress());
        System.out.println("client output : " + msg);
        context.writeAndFlush("from client : "+new Date());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("channelActive");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
