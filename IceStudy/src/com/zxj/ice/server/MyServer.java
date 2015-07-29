package com.zxj.ice.server;

import com.zxj.ice.servant.MyPrinterI;

public class MyServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int status = 0;
		Ice.Communicator ic = null;
		try{
			//初始化ICE容器
			ic = Ice.Util.initialize();
			//创建适配器，绑定对应的ip、端口
			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("DemoAdapter", "default -h localhost -p 10000");
			//实例化ICE定义
			Ice.Object obj = new MyPrinterI();
			//适配器中添加ICE实体
			adapter.add(obj, ic.stringToIdentity("Demo"));
			//激活适配器
			adapter.activate();
			//执行结束后等待关闭
			ic.waitForShutdown();
		}catch(Ice.LocalException e){
			e.printStackTrace();
			status = 1;
		}catch(Exception e){
			System.out.println(e.getMessage());
			status = 1;
		}finally{
			try{
				if(null != ic){
					ic.destroy();
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
				status = 1;
			}
		}
		
		System.exit(status);
		
		
	}

}
