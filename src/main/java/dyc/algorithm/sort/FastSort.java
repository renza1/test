/**
 * @author dyc
 * com.sort
 * FastSort.java
 * 
 * 2016年5月17日-上午9:46:31
 *  2016XX公司-版权所有
 * 
 */
package dyc.algorithm.sort;

/**
 * @author dyc
 * @ClassName FastSort
 * @Description
 * @date 2016年5月17日
 * 
 * @version 1.0.0
 * 
 */

// 不稳定
public class FastSort {
	public static void main(String[] args) {
		int[] a = { 6, 5, 4, 3, 2, 1 };
		System.out.println("排序之前：");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		// 快速排序
		quick(a);
		System.out.println();
		System.out.println("排序之后：");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	private static void quick(int[] a) {
		if (a.length > 0) {
			quickSort(a, 0, a.length - 1);
		}
	}

	private static void quickSort(int[] a, int low, int high) {
		if (low < high) { // 如果不加这个判断递归会无法退出导致堆栈溢出异常
			int middle = getMiddle(a, low, high);
			quickSort(a, 0, middle - 1);
			quickSort(a, middle + 1, high);
		}
	}

	private static int getMiddle(int[] a, int low, int high) {
		int temp = a[low];// 基准元素
		while (low < high) {
			// 找到比基准元素小的元素位置
			while (low < high && a[high] >= temp) {
				high--;
			}
			a[low] = a[high];
			while (low < high && a[low] <= temp) {
				low++;
			}
			a[high] = a[low];
		}
		a[low] = temp;
		return low;
	}
}
