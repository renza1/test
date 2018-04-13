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

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
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
public class Thread4 extends Thread {
	CyclicBarrier s;

	Thread4(CyclicBarrier s) {
		this.s = s;
	}

	@Override
	public void run() {
		System.out.println("getNumberWaiting=" + s.getNumberWaiting());
		System.out.println(s.getParties());
		try {
			s.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("run end");

	}

	public static void main(String[] args) throws InterruptedException {
		CyclicBarrier s = new CyclicBarrier(3);
		Thread4 t1 = new Thread4(s);
		Thread4 t2 = new Thread4(s);
		Thread4 t3 = new Thread4(s);
		Thread4 t4 = new Thread4(s);
		Thread4 t5 = new Thread4(s);
		Thread4 t6 = new Thread4(s);
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
