/**
 * @author dyc
 * dyc.algorithm
 * MaxSum.java
 * 
 * 2016年6月27日-下午4:48:12
 *  2016XX公司-版权所有
 * 
 */
package dyc.algorithm;

/**
 * @author dyc
 * @ClassName MaxSum
 * @Description
 * @date 2016年6月27日
 * 
 * @version 1.0.0
 * 
 */
public class MaxSum {
	static int[] n = { 5, 1, -11, 22, -4, 5, -2, 6, 5, -6 };

	public static void scan(int[] n) {
		System.out.println("-------------line");

		int sofar = 0;
		int maxend = 0;
		for (int i = 0; i < n.length; i++) {
			System.out.println("i=" + i);
			maxend = Math.max(maxend + n[i], 0);
			System.out.println("maxend=" + maxend);
			sofar = Math.max(sofar, maxend);
			System.out.println("sofar=" + sofar);
		}
		System.out.println(sofar);
		System.out.println(maxend);
		System.out.println("-------------line end");

	}
	public static void n3(int[] n) {
		int it = -1;
		int jt = -1;
		System.out.println("-------------n3");
		int maxsofar = 0;
		for (int i = 0; i < n.length; i++) {
			for (int j = i; j < n.length; j++) {
				int sum = 0;
				for (int k = i; k <= j; k++) {
					sum += n[k];
				}
				System.out.println("i=" + i + ",j=" + j);
				System.out.println("sum=" + sum);
				if (maxsofar < sum) {
					maxsofar = sum;
					it = i;
					jt = j;
				}
			}
		}
		System.out.println("it=" + it + ",jt=" + jt);
		System.out.println(maxsofar);
		System.out.println("-------------n3 end");
	}

	public static void n2_1(int[] n) {
		int it = -1;
		int jt = -1;
		System.out.println("-------------n2_1");
		int maxsofar = 0;
		for (int i = 0; i < n.length; i++) {
			int sum = 0;
			for (int j = i; j < n.length; j++) {
				sum += n[j];
				System.out.println("i=" + i + ",j=" + j);
				System.out.println("sum=" + sum);
				if (maxsofar < sum) {
					maxsofar = sum;
					it = i;
					jt = j;
				}
			}
		}
		System.out.println("it=" + it + ",jt=" + jt);
		System.out.println(maxsofar);
		System.out.println("-------------n2_1 end");
	}

	public static int ma(int l, int h) {
		if (l > h)
			return 0;
		if (l == h)
			return Math.max(0, n[l]);
		int m = (l + h) / 2;
		int sum = 0, lmax = 0;
		for (int i = m; i >= l; i--) {
			sum += n[i];
			lmax = Math.max(sum, lmax);
		}
		sum = 0;
		int rmax = 0;
		for (int i = m + 1; i <= h; i++) {
			sum += n[i];
			rmax = Math.max(sum, rmax);
		}
		int t = lmax + rmax;
		System.out.println("lmax=" + lmax + ",rmax=" + rmax + ",l=" + l + ",h=" + h + ",m=" + m);

		int r1 = Math.max(t, Math.max(ma(l, m), ma(m + 1, h)));
		System.out.println("r1=" + r1);

		return r1;
	}

	public static void main(String[] args) {
		n3(n);
		n2_1(n);
		System.out.println(ma(0, n.length - 1));
		scan(n);
	}
}
