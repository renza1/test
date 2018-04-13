/**
 * @author dyc
 * dyc.algorithm.sort
 * ArrayMove.java
 * 
 * 2016年6月23日-下午3:22:17
 *  2016XX公司-版权所有
 * 
 */
package dyc.algorithm.sort;

import java.util.Arrays;

/**
 * @author dyc
 * @ClassName ArrayMove
 * @Description
 * @date 2016年6月23日
 * 
 * @version 1.0.0
 * 
 */
public class ArrayMove {

	public static void reverse(String[] a) {
		int count = a.length/2;
		for (int i = 0; i < count; i++) {
			String temp = a[i];
			a[i] = a[a.length - i - 1];
			a[a.length - i - 1] = temp;
		}
	}

	public static void main(String[] args) {
		String[] a = { "a", "b", "c", "d", "e", "f", "g" };
		String[] a1 = Arrays.copyOfRange(a, 0, 3);
		reverse(a1);
		String[] a2 = Arrays.copyOfRange(a, 3, 7);
		reverse(a2);
		a[0] = a1[0];
		a[1] = a1[1];
		a[2] = a1[2];
		a[3] = a2[0];
		a[4] = a2[1];
		a[5] = a2[2];
		a[6] = a2[3];
		for (String b : a) {
			System.out.print(b);
		}
		System.out.println();
		reverse(a);
		for (String b : a) {
			System.out.print(b);
		}
	}
}
