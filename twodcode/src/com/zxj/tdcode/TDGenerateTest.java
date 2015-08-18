package com.zxj.tdcode;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class TDGenerateTest {

	public static void main(String[] args) throws WriterException, IOException {

		//设置二维码大小
		int width = 300;
		int height = 300;
		//设置二维码内容
		String text = "hello world!!!!";
		//设置图片格式
		String type = "gif";
		//设置编码
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		//生成矩阵
		BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, 
				width, height, hints);
		
		//生成二维码
		File file = new File("D:"+File.separator+"my.gif");
		
		TDImageGenerator.generateForFile(matrix, type, file);
	}

}
