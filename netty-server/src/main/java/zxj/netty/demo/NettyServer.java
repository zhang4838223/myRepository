package zxj.netty.demo;

import org.junit.Test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

	 private final int port = 8989;
	  
	  public void start(){
	    
	    EventLoopGroup bossGroup = new NioEventLoopGroup();
	    EventLoopGroup workerGroup = new NioEventLoopGroup();
	    
	    try {
	      ServerBootstrap serverBootstrap = new ServerBootstrap();
	      serverBootstrap.group(bossGroup,workerGroup)
	        .channel(NioServerSocketChannel.class)
	        .option(ChannelOption.SO_BACKLOG, 1024)
	        .childHandler(new ChildChannelHandler());
	      
	      //绑定端口、同步等待
	      ChannelFuture futrue = serverBootstrap.bind(port).sync();
	      
	      //等待服务监听端口关闭
	      futrue.channel().closeFuture().sync();
	    } catch (InterruptedException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }finally{
	      //退出，释放线程等相关资源
	      bossGroup.shutdownGracefully();
	      workerGroup.shutdownGracefully();
	    }

	  }
	  
	  public static void main(String[] args) {
		new NettyServer().start();
	}
}
