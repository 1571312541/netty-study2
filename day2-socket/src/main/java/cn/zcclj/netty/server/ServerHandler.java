package cn.zcclj.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 〈〉
 *
 * @author 22902
 * @create 2019/1/18
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext context, String msg) throws Exception {
        System.out.println(context.channel().remoteAddress() + " , " + msg);
        context.channel().writeAndFlush("from server : " + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();

    }
}
