package com.zxj.ice.servant;

import Ice.Current;
import MyDemo._MyPrinterDisp;
/**
 * 该类主要是用Java实现ICE的slice定义，用ice指令生成该类的父类
 * @author xiaojun.zhang
 *
 */
public class MyPrinterI extends _MyPrinterDisp {

	@Override
	public void printString(String s, Current __current) {

		System.out.println(s);
	}

}
