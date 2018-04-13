/**
 * @author dyc
 * com.justinmobile.thread
 * ConnectPool.java
 * 
 * 2016年6月16日-下午4:19:33
 *  2016XX公司-版权所有
 * 
 */
package dyc.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Future1 {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Task task = new Task();
		Future<Integer> result = executor.submit(task);
		executor.shutdown();

		System.out.println("主线程在执行任务");

		try {
			System.out.println("task运行结果" + result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("所有任务执行完毕");
	}
}

class Task implements Callable<Integer> {
	String key;
	/**
	 * 创建一个新的实例 Task.
	 *
	 * @param key
	 */
	public Task(String key) {
		this.key = key;
	}

	public Task() {
	}
	public Integer call() throws Exception {
		System.out.println(key + "子线程在进行计算");
		Thread.sleep(4000);
		return Integer.parseInt(key);
	}
}
