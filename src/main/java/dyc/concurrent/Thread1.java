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

import java.util.concurrent.ExecutionException;

/**
 * @author dyc
 * @ClassName Thread1
 * @Description
 * @date 2016年6月16日
 * 
 * @version 1.0.0
 * 
 */
public class Thread1 extends Thread {
	MyFutureTask f3;
	String key;

	Thread1(MyFutureTask f3, String key) {
		this.f3 = f3;
		this.key = key;
	}

	@Override
	public void run() {
		try {
			System.out.println(f3.getInt(key));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MyFutureTask f3 = new MyFutureTask();
		Thread1 t1 = new Thread1(f3, "1");
		Thread1 t2 = new Thread1(f3, "1");
		Thread1 t3 = new Thread1(f3, "2");
		Thread1 t4 = new Thread1(f3, "2");
		Thread1 t5 = new Thread1(f3, "2");
		Thread1 t6 = new Thread1(f3, "6");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}
}
