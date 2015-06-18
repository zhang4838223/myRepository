package zxj.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyServerHandler extends ChannelInboundHandlerAdapter {


	 @Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		 ByteBuf buf = (ByteBuf)msg;
		 byte [] req = new byte[buf.readableBytes()];
		 buf.readBytes(req);                    //将收到的消息读到字节数组中
	    String message = new String(req,"UTF-8");
	    System.out.println("Netty-Server:Receive Message:"+ message);
	    
	    byte[] reply = "the your message is recerived!!".getBytes();
		 buf = Unpooled.buffer(reply.length);
		 buf.writeBytes(reply);                    //将要回复的消息写到buf中
		 ctx.writeAndFlush(buf);
	}
	 
	 @Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		 cause.printStackTrace();
		 ctx.close();
	}
	 

}
