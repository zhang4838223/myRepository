package com.zxj.rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceCenter {

	//socket接收请求，socket响应请求
	public static void registerService(final Object service, int port){
		
		if(null == service){
			throw new IllegalArgumentException("the service exposed " + service.getClass().getName()+" is null") ;
		}
		
		if(port<=10 || port >=66666){
			throw new IllegalArgumentException("the exposed port " + port+" is invalid") ;
		}
		
		System.out.println("expose service " + service.getClass().getName() + " on port "+ port);
		
			ServerSocket ss = null;
			try {
				ss = new ServerSocket(port);
				while(true){
					final Socket socket = ss.accept();
					
					new Thread(new Runnable(){
						ObjectInputStream ois = null;
						ObjectOutputStream oos = null;
						
						@Override
						public void run() {
							
								try {
									ois = new ObjectInputStream(socket.getInputStream());
									//获取注册接口的方法名称，需要与引用方顺序一致
									String methodName = ois.readUTF();
									//获取注册接口的方法参数类型，需要与引用方顺序一致
									Class<?>[] paramType = (Class<?>[])ois.readObject();
									//获取注册接口的方法参数类型，需要与引用方顺序一致
									Object[] arguments = (Object[])ois.readObject();
									//反射获取注册服务方法
									Method method = service.getClass().getMethod(methodName, paramType);
									//调用方法执行结果
									Object results = method.invoke(service, arguments);
									//将结果返回
									
									oos = new ObjectOutputStream(socket.getOutputStream());
									oos.writeObject(results);
								} catch (IOException e) {
									e.printStackTrace();
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								} catch (NoSuchMethodException e) {
									e.printStackTrace();
								} catch (SecurityException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								}finally{
									if(null != oos){
										try {
											oos.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
									
									if(null != ois){
										try {
											ois.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
									
									if(socket != null){
										try {
											socket.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
								}
						}
						
					}).start();;
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
	
	/**
	 * 客户端用于调用服务
	 * @param interfaceClass  服务接口
	 * @param host	服务主机
	 * @param port	服务端口
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static<T> T excuteService(final Class<T> interfaceClass,final String host,final int port){
		
		if(null == interfaceClass){
			throw new IllegalArgumentException("parameter interfaceClass is null");
		}
		
		if(!interfaceClass.isInterface()){
			throw new IllegalArgumentException("parameter "+ interfaceClass.getName() +" is not interface");
		}
		
		if(host == null || host.length()<=0){
			throw new IllegalArgumentException("parameter "+host+" is invalid");
		}
		
		if(port<=10 || port >=66666){
			throw new IllegalArgumentException("the exposed port " + port+" is invalid") ;
		}
		
		System.out.println("service "+ interfaceClass.getName() +" is invoked from server "+ host+":" +port);
		
		//调用代理并执行返回
		return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(),
				new Class<?>[]{interfaceClass}, new InvocationHandler() {
					
					@Override
					public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
						Socket socket = null;
						ObjectOutputStream oos =  null;
						ObjectInputStream ois = null;
						Object result = null;
						try{
							socket = new Socket(host, port);
						
						//向服务端通信，发起调用请求，通信内容顺序分别为方法名称、参数类型、参数
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeUTF(method.getName());
						oos.writeObject(method.getParameterTypes());
						oos.writeObject(args);
						//获取服务端返回的结果
						ois = new ObjectInputStream(socket.getInputStream());
						result = ois.readObject();
						
						}catch(Exception e){
							e.printStackTrace();
							throw new RuntimeException("service "+interfaceClass.getName()+"."+method.getName()+" request failed!!");
						}finally{
							if(null != ois){
								ois.close();
							}
							if(null != oos){
								oos.close();
							}
							if(socket!= null){
								socket.close();
							}
						}
						
						
						
						if(result instanceof Throwable){
							throw (Throwable)result;
						}
						return result;
					}
				});
		
	}
	}
			
		
		
