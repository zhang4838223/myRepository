package com.zxj.rpc.server;

public class RpcDemoImpl implements RpcDemoI {

	@Override
	public String getName(String name) {
		// TODO Auto-generated method stub
		return "hello, "+name+"!!";
	}

}
