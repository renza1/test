/**
 * @author dyc
 * com.justinmobile.thread
 * Future2.java
 * 
 * 2016年6月16日-下午4:46:20
 *  2016XX公司-版权所有
 * 
 */
package dyc.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author dyc
 * @ClassName Future2
 * @Description
 * @date 2016年6月16日
 * 
 * @version 1.0.0
 * 
 */
public class Future2 {
	public static void main(String[] args) {
		// 第一种方式
		ExecutorService executor = Executors.newCachedThreadPool();
		Task task = new Task();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
		executor.submit(futureTask);
		executor.shutdown();
		// 第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
		/*
		 * Task task = new Task(); FutureTask<Integer> futureTask = new FutureTask<Integer>(task); Thread thread = new
		 * Thread(futureTask); thread.start();
		 */

		System.out.println("主线程在执行任务");

		try {
			System.out.println("task运行结果" + futureTask.get());
			System.out.println("task运行结果2--" + futureTask.get());

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("所有任务执行完毕");
	}
}
