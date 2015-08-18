package com.zxj.tdcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.common.BitMatrix;

public class TDImageGenerator {

   private static final int BLACK = 0xFF000000; 
   private static final int WHITE = 0xFFFFFFFF;
   
	public TDImageGenerator() {}
	
	public static BufferedImage generateImage(BitMatrix matrix){
		int height = matrix.getHeight();
		int width = matrix.getWidth();
		
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				img.setRGB(i, j, matrix.get(i, j)?BLACK:WHITE);
			}
		}
		return img;
	}
	
	public static void generateForFile(BitMatrix matrix,String type,File file) throws IOException{
		BufferedImage img = generateImage(matrix);
		if(!ImageIO.write(img, type, file)){
			throw new RuntimeException("fail to generate the tdImage with type "+type +" to "+file);
		}
	}
	
	public static void generateForStream(BitMatrix matrix,String type,OutputStream os) throws IOException{
		BufferedImage img = generateImage(matrix);
		if(!ImageIO.write(img, type, os)){
			throw new RuntimeException("fail to generate the tdImage with type "+type);
		}
	}
	
	
   
   
   
}
