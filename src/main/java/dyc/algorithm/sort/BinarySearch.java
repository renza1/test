package dyc.algorithm.sort;

import java.util.Arrays;
import java.util.List;

public class BinarySearch {
	static Integer[] a = { 1, 2, 4, 5, 11, 21, 44, 55 };

	public static void main(String[] args) {
		// System.out.println(binarySearch(a, 55));
		// System.out.println(5.00 - 4.90);
		int x = 4;

		// System.gc();
		List<Integer> asList = Arrays.asList(a);
		System.out.println(asList.indexOf(5));
	}

	/*
	 * 非递归二分查找算法 参数:整型数组,需要比较的数.
	 */
	public static int binarySearch(Integer[] srcArray, int des) {
		// 第一个位置.
		int low = 0;
		// 最高位置.数组长度-1,因为下标是从0开始的.
		int high = srcArray.length - 1;
		// 当low"指针"和high不重复的时候.
		while (low <= high) {
			// 中间位置计算,low+ 最高位置减去最低位置,右移一位,相当于除2.也可以用(high+low)/2
			int middle = low + ((high - low) >> 1);
			// 与最中间的数字进行判断,是否相等,相等的话就返回对应的数组下标.
			if (des == srcArray[middle]) {
				return middle;
				// 如果小于的话则移动最高层的"指针"
			} else if (des < srcArray[middle]) {
				high = middle - 1;
				// 移动最低的"指针"
			} else {
				low = middle + 1;
			}
		}
		return -1;
	}

}
