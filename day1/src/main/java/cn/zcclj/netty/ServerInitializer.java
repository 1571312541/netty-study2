package cn.zcclj.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 〈〉
 *
 * @author 22902
 * @create 2019/1/17
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();
        // new HttpServerCodec() 是addLast(new RequestDecoder())和addLast(new ResponseEncoder())的组合;
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());

        pipeline.addLast("ServerHandler",new ServerHandler());


    }
}
