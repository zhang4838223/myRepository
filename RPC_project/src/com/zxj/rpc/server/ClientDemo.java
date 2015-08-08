package com.zxj.rpc.server;

public class ClientDemo {

	public static void main(String[] args) {

		RpcDemoI service = ServiceCenter.excuteService(RpcDemoI.class, "127.0.0.1", 8888);
		
		String name = service.getName("lisi");
		System.out.println(name);
	}

}
