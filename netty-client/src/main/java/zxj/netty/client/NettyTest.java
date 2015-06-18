package zxj.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String host = "localhost";
		int port = 8989;
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		try{
			Bootstrap b = new Bootstrap();
			b.group(workGroup)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel sc) throws Exception {
					sc.pipeline().addLast(new MyTimeClientHandler());
				}
				
			});
			ChannelFuture cf = b.connect(host,port).sync();
			cf.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			workGroup.shutdownGracefully();
		}
	}

}
