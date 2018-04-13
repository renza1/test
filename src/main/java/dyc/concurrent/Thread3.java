/**
 * @author dyc
 * com.justinmobile.thread
 * Thread1.java
 * 
 * 2016年6月16日-下午5:30:15
 *  2016XX公司-版权所有
 * 
 */
package dyc.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author dyc
 * @ClassName Thread1
 * @Description
 * @date 2016年6月16日
 * 
 * @version 1.0.0
 * 
 */
public class Thread3 extends Thread {
	CountDownLatch s;

	Thread3(CountDownLatch s) {
		this.s = s;
	}

	@Override
	public void run() {
		System.out.println("runbegin" + s.getCount());
		s.countDown();
		try {
			s.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("runend" + s.getCount());
	}

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch s = new CountDownLatch(3);
		Thread3 t1 = new Thread3(s);
		Thread3 t2 = new Thread3(s);
		Thread3 t3 = new Thread3(s);
		Thread3 t4 = new Thread3(s);
		Thread3 t5 = new Thread3(s);
		Thread3 t6 = new Thread3(s);
		t1.start();
		Thread.sleep(1000);
		t2.start();
		Thread.sleep(1000);
		t3.start();
		Thread.sleep(1000);
		t4.start();
		Thread.sleep(1000);
		t5.start();
		Thread.sleep(1000);
		t6.start();
	}
}
