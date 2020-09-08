package pers.east.learning.netty.basic;

public class Test {

    /**
     * EventLoopGroup 相当于Reactor线程组,常用NioEventLoopGroup
     * Channel 连接到网络套接字或能够进行I/O操作(如读、写、连接和绑定)的组件的连接。常用NioServerSocketChannel，NioSocketChannel，SocketChannel
     * Bootstrap/ServerBootstrap 辅助类
     * ChannelPipeline 处理或截取通道的入站事件和出站操作的通道处理程序列表
     * ChannelHandler 处理I/O事件或拦截I/O操作，并将其转发到其ChannelPipeline中的下一个处理程序。
     *                  常用ChannelInboundHandlerAdapter和SimpleChannelInboundHandler，编码器，解码器。
     * ChannelFuture 异步操作使用。
     */

    public static void main(String[] args) {


    }
}
