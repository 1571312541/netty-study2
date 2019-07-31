package cn.zcclj.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 〈〉
 *
 * @author 22902
 * @create 2019/1/18
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 保存channel对象
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext context, String msg) throws Exception {

        Channel channel = context.channel();
        channelGroup.forEach(ch->{
            if (channel!=ch){
                ch.writeAndFlush(channel.remoteAddress() + "发送消息 : " + msg + "\n");
            }else {
                ch.writeAndFlush("自己 发送消息 ：" + msg + "\n");
            }
        });

    }

    /**
     * 服务器和客户端已经建立关系
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + " - 加入\n");
        channelGroup.add(channel);
    }

    /**
     * 连接断掉后
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + " - 离开\n");
        //断掉后channeGroup会自动移除channel，所以下行代码不需要
        //channelGroup.remove(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 上线\n ");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线 \n");
    }

    /**
     * 异常时
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();

    }
}
