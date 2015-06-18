package zxj.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyNettyServer {

	private int port;
	private String host;

	public MyNettyServer() {}

	public MyNettyServer(String host,int port) {
		this.host = host;
		this.port = port;
	}
	public MyNettyServer(int port) {
		this.port = port;
	}
	
	public void run(){
		EventLoopGroup boosGroup   = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(boosGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel sc) throws Exception {

					sc.pipeline().addLast(new MyServerHandler());
				}
			});
			
			ChannelFuture cf = b.bind(port).sync();
			//System.out.println("NettyServer is started an listened on "+cf.channel().localAddress());
			cf.channel().closeFuture().sync();
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			workerGroup.shutdownGracefully();
			boosGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		new MyNettyServer(8989).run();
	}
	
}
