package com.zxj.timer.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DemoTimerTask {

	public static void main(String[] args) throws InterruptedException {

//		timerTest();
		while(true){
			
			System.out.println(Calendar.getInstance().get(Calendar.SECOND));
			Thread.sleep(1000L);
		}
	}

	private static void timerTest() {
		Timer timer = new Timer(false);
		
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {

				Calendar c = Calendar.getInstance();
				int second = c.get(Calendar.SECOND);
				System.out.println(second);
			}
		};
		long delay = new Date().getTime();
		long period = 1000L;
		timer.schedule(task , delay, period);
		
		System.out.println("test is over!!");
	}

}
