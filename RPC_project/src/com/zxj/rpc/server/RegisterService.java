package com.zxj.rpc.server;

public class RegisterService {

	public static void main(String[] args) {
		RpcDemoI inter = new RpcDemoImpl();
		ServiceCenter.registerService(inter, 8888);
	}

}
