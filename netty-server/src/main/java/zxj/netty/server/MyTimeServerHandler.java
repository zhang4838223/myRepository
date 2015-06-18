package zxj.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyTimeServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {

		ByteBuf buf = ctx.alloc().buffer(4);
		buf.writeInt((int)(System.currentTimeMillis()/1000L+2208988800L));
		
		final ChannelFuture f = ctx.writeAndFlush(buf);
		f.addListener(new ChannelFutureListener() {
			
			public void operationComplete(ChannelFuture cf) throws Exception {
				assert f == cf;
				ctx.close();
			}
		});
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
