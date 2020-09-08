package pers.east.learning.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static final int PORT = 8752;

    public static void main(String[] args) {
        /**
         * 首先创建两个NioEventLoopGroup线程组，一个用于接收客服端的链接，另一个用于进行SocketChannel的网络读写。
         * 创建一个服务器帮助类ServerBootstrap，将boss,worker两个线程组加入
         * 通过ServerBootstrap流式设置服务器
         * 设置通道为NioServerSocketChannel
         * 通过ChannelInitializer设置自定义处理器NettyServerHandler，并将他加入ChannelPipeline，这里用内部类简易实现，真实线上环境我们应该提取为相应的类。
         * 通过option和childOption设置TCP相关参数。
         * 异步地绑定服务器；调用 sync()方法阻塞等待直到绑定完成
         * 最后关闭相关资源
         */
        Server.connect();
    }

    public static void connect(){
        //配置两个服务端的NIO线程组，一个用于接收客服端的链接，另一个用于进行SocketChannel的网络读写。
        //NioEventLoopGroup是一个处理I/O操作的多线程事件循环
        //"boss"：接收一个传入连接
        EventLoopGroup boss = new NioEventLoopGroup();
        //"worker" : 当boss接收连接并把接收的连接注册给worker，work就开始处理
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            //ServerBootstrap是一个帮助类，可以设置服务器
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,worker)
                    //NioServerSocketChannel用于实例化新通道来接收传入的连接
                    .channel(NioServerSocketChannel.class)
                    //配置日志
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //ChannelInitializer用于配置新通道
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            //通过ChannelPipeline添加处理类ChannelHandler
                            //通常有很多处理类，可以将这个内部类new ChannelInitializer提为一个独立的类
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new ServerHandler());
                        }
                    })
                    //ChannelOption和ChannelConfig可以设置各种参数
                    .option(ChannelOption.SO_BACKLOG,128)
                    //option()用于接受传入连接的NioServerSocketChannel,childOption()用于父ServerChannel接受的通道
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            //异步地绑定服务器；调用 sync()方法阻塞等待直到绑定完成
            ChannelFuture f = bootstrap.bind(PORT).sync();
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
