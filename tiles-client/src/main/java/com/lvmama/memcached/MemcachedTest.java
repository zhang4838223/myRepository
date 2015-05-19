package com.lvmama.memcached;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;




public class MemcachedTest {
	public static void main(String[] args) {
		try {
			MemcachedClient mc = new MemcachedClient(new InetSocketAddress("127.0.0.1",11211));
			//OperationFuture<Boolean> b = mc.add("name1", 900, "xiaoming");
			System.out.println(getValue("name",mc)+"======================");
			mc.shutdown();
//			if(b.get().booleanValue()){
//				mc.shutdown();
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static Object getValue(String key, MemcachedClient mc) {

		return mc.get(key);
	}
	
	
}
