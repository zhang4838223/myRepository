package zxj.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyTimeClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {

		ByteBuf buf = (ByteBuf)msg;
		try{
			byte[] mes = new byte[buf.readableBytes()];
			buf.readBytes(mes);
			String message = new String(mes,"utf-8");
			
			System.out.println("Netty-Client: Received message: "+ message);
		}finally{
			buf.release();
		}
		
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf buf = ctx.alloc().buffer();
		byte[] bytes = "hello netty!!".getBytes();   //要发送的消息
		buf = Unpooled.buffer(bytes.length);         //生成指定大小的buf
		buf.writeBytes(bytes);                       //将要发送的消息写到buf中
		ctx.writeAndFlush(buf);						 //将buf发送给server
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
