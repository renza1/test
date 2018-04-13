/**
 * @author dyc
 * com.justinmobile.thread
 * Test.java
 * 
 * 2016年6月21日-上午9:47:12
 *  2016XX公司-版权所有
 * 
 */
package dyc.concurrent;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
	static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

	public static void main(String[] args) {
//		fixedThread();
		 ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);  
		  scheduledThreadPool.scheduleAtFixedRate(new Runnable() {  
		   public void run() {  
		    System.out.println(Calendar.getInstance().getTime().getSeconds()+"delay 3 seconds");  
		   }  
		  },1, 3, TimeUnit.SECONDS);  
		System.out.println("end");

	}

	private static void fixedThread() {
		for (int i = 0; i < 10111; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					try {
						System.out.println(index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
