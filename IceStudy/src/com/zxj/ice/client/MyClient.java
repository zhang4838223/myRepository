package com.zxj.ice.client;


public class MyClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				int status = 0;
				Ice.Communicator ic = null;
				try{
					//初始化ICE
					ic = Ice.Util.initialize();
					//获取远程代理，这里的字符串参数包括了服务器包含的对象标识和端口号
					Ice.ObjectPrx base = ic.stringToProxy("Demo:default -p 10000");
					//将获取的远程代理向下转型为具体的实际代理类
					MyDemo.MyPrinterPrx printer = MyDemo.MyPrinterPrxHelper.checkedCast(base);
					
					if(null == printer){
						throw new Error("Invalid proxy!!");
					}
					//执行代理类的操作
					printer.printString("hello World!!");
					
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
