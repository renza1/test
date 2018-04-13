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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
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
public class MyFutureTask {
	public ConcurrentHashMap<String, FutureTask<Integer>> taskMap = new ConcurrentHashMap<String, FutureTask<Integer>>();

	public   Integer getInt(String key) throws InterruptedException, ExecutionException {
		System.out.println("getint.key=" + key);
		FutureTask<Integer> futureTask = taskMap.get(key);
		if (futureTask != null) {
			return futureTask.get();
		} else {
			Task task = new Task(key);
			FutureTask<Integer> newTask = new FutureTask<Integer>(task);
			futureTask = taskMap.putIfAbsent(key, newTask);
			if (futureTask == null) {
				futureTask = newTask;
				futureTask.run();
			}
			return futureTask.get();
		}
	}
}
