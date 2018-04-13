/**
 * @author dyc
 * com.justinmobile.thread
 * CountOperate.java
 * 
 * 2016年5月12日-下午2:25:05
 *  2016XX公司-版权所有
 * 
 */
package dyc.concurrent;

/**
 * @author dyc
 * @ClassName CountOperate
 * @Description
 * @date 2016年5月12日
 * 
 * @version 1.0.0
 * 
 */
public class CountOperate extends Thread {
	public CountOperate() {
		this.setName("cc");
		System.out.println("CountOperate--name=" + this.getName());
		System.out.println("CountOperate--currentThreadname=" + Thread.currentThread().getName());
	}

	@Override
	public void run() {
		super.run();

		System.out.println("run--name=" + this.getName());
		System.out.println("run--currentThreadname=" + Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		CountOperate countOperate = new CountOperate();
		countOperate.start();
	}
}
