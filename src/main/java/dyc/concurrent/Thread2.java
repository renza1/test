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
public class Thread2 extends Thread {
	Semaphore s;

	Thread2(Semaphore s) {
		this.s = s;
	}

	@Override
	public void run() {
		boolean tryAcquire = s.tryAcquire();
		System.out.println(tryAcquire + "--" + s.availablePermits());
		// Thread.sleep(1000);
		// s.release();
		// s.release();
	}

	public static void main(String[] args) throws InterruptedException {
		Semaphore s = new Semaphore(3);
		Thread2 t1 = new Thread2(s);
		Thread2 t2 = new Thread2(s);
		Thread2 t3 = new Thread2(s);
		Thread2 t4 = new Thread2(s);
		Thread2 t5 = new Thread2(s);
		Thread2 t6 = new Thread2(s);
		System.out.println(Runtime.getRuntime().availableProcessors());
		t1.start();
		// Thread.sleep(1000);
		t2.start();
		// Thread.sleep(1000);
		t3.start();
		// Thread.sleep(1000);
		t4.start();
		t5.start();
		t6.start();
	}
}
