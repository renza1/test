package com.justinmobile;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author grant.yu byte工具类，用于处理byte及byte数组的复制、搜索、截取，与其他类型的相互转换等
 * 
 */
public class ByteUtils {
	private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 长度为4的byte数组合成一个int
	 * 
	 * @param b
	 * @return
	 */
	public static int bytes2int(byte[] b, int size) {
		int mask = 0xff;
		int temp = 0;
		int res = 0;
		for (int i = 0; i < size; i++) {
			res <<= 8;
			temp = b[i] & mask;
			res |= temp;
		}
		return res;
	}

	/**
	 * int转换成长度为4的byte数组
	 * 
	 * @param num
	 * @return
	 */
	public static byte[] int2bytes(int num, int size) {
		byte[] b = new byte[size];
		for (int i = 0; i < size; i++) {
			b[i] = (byte) (num >>> ((size - 1) * 8 - i * 8));
		}
		return b;
	}

	/**
	 * 将指定byte数组以16进制的形式
	 * 
	 * @param b
	 * @return
	 */
	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	/**
	 * 使用0补充指定长度的byte数组
	 * 
	 * @param data
	 * @param length
	 * @return
	 */
	public static byte[] bytesFill(byte[] data, int length) {
		if (data == null || data.length > length) {
			throw new RuntimeException("输入data错误.");
		}
		int p = length - data.length;
		for (int pos = 0; pos < p; pos++) {
			data = append(data, (byte) 0);
		}
		return data;

	}

	public static byte[] contactArray(byte[] src1, byte[] src2) {
		if (src1 == null || src1 == null) {
			throw new IllegalArgumentException();
		}
		byte[] dest = new byte[src1.length + src2.length];
		System.arraycopy(src1, 0, dest, 0, src1.length);
		System.arraycopy(src2, 0, dest, src1.length, src2.length);
		return dest;
	}

	public static byte[] append(byte[] src, byte b) {
		return contactArray(src, new byte[] { b });
	}

	public static void splitArray(byte[] src, byte[] dest1, byte[] dest2, int pos) {
		if (src == null || dest1 == null || dest2 == null) {
			throw new IllegalArgumentException();
		}
		if (src.length == 0 || pos > src.length) {
			throw new IllegalArgumentException();
		}
		dest1 = new byte[pos];
		dest2 = new byte[src.length - pos];
		System.arraycopy(src, 0, dest1, 0, pos);
		System.arraycopy(src, pos, dest2, 0, src.length - pos);
	}

	public static byte[] subArray(byte[] src, int start, int end) {
		if (start < 0 || start > end || end > src.length) {
			throw new IllegalArgumentException();
		}
		byte[] subBytes = new byte[end - start];
		System.arraycopy(src, start, subBytes, 0, subBytes.length);
		return subBytes;
	}

	public static byte[] leftSubArray(byte[] src, int pos) {
		return subArray(src, 0, pos);
	}

	public static byte[] rightSubArray(byte[] src, int pos) {
		return subArray(src, pos, src.length);
	}

	public static int arrayIndexOf(byte[] src, byte toFind) {
		int index = -1;
		for (int i = 0; i < src.length; i++) {
			if (src[i] == toFind) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static String hexString(byte byteNumber) {
		int toStr = byteNumber;
		if (byteNumber < 0) {
			toStr = byteNumber + 256;
		}
		String byteStr = Integer.toHexString(toStr);
		if (byteStr.length() == 1) {
			byteStr = "0" + byteStr;
		}
		return "0x" + byteStr.toUpperCase();
	}

	public static byte[] hexStringToBytes(String hexStr) {
		int length = hexStr.length();
		if (length % 2 != 0) {
			throw new IllegalArgumentException();
		}
		hexStr = hexStr.toUpperCase();
		byte[] outArray = new byte[length / 2];
		for (int i = 0; i < length; i += 2) {
			char li = hexStr.charAt(i);
			char lo = hexStr.charAt(i + 1);
			if (li < '0' || li > 'F' || lo < '0' || lo > 'F') {
				throw new IllegalArgumentException();
			}
			outArray[i / 2] = (byte) Integer.parseInt(String.valueOf(new char[] { li, lo }), 16);
		}
		return outArray;
	}

	public static byte[] asciiToBcd(String input) {
		byte[] ascii = null, bcd = null;
		try {
			ascii = input.getBytes("US-ASCII");
			if (ascii.length % 2 != 0) {
				throw new IllegalArgumentException();
			}
			bcd = new byte[ascii.length / 2];
			for (int i = 0; i < ascii.length; i += 2) {
				if (ascii[i] < 0x30 || ascii[i] > 0x39) {
					throw new IllegalArgumentException();
				}
				int temp = (ascii[i] & 0x0F) << 4;
				if (temp > 127) {
					temp -= 256;
				}
				byte hi = (byte) temp;
				byte lo = (byte) (ascii[i + 1] & 0x0F);
				bcd[i / 2] = (byte) (hi | lo);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return bcd;
	}

	public static byte[] asciiToCnBcd(String input, int bcdBytesLength) {
		if (input.length() > 2 * bcdBytesLength) {
			throw new IllegalArgumentException("the input String is too long");
		}
		input = input.toUpperCase();
		int paddingBytesCount = 2 * bcdBytesLength - input.length();
		for (int i = 0; i < paddingBytesCount; i++) {
			input += 'F';
		}

		byte[] cnBcd = null;
		int index = input.indexOf('F');
		if (index != -1) {
			if (index < input.length() - 1) {
				String forCheck = input.substring(index + 1);
				for (int i = 0; i < forCheck.length(); i++) {
					if (forCheck.charAt(i) != 'F') {
						throw new IllegalArgumentException("the input String is not valid to convert to CnBcd");
					}
				}
			}
			String left = null, right = null;
			if (index % 2 == 1) {
				left = input.substring(0, index - 1);
				right = input.substring(index - 1);
			} else {
				left = input.substring(0, index);
				right = input.substring(index);
			}
			byte[] leftBytes = asciiToBcd(left);
			byte[] rightBytes = hexStringToBytes(right);
			cnBcd = contactArray(leftBytes, rightBytes);
		} else {
			cnBcd = asciiToBcd(input);
		}
		return cnBcd;
	}

	public static String bcdToAscii(byte[] bcd) {
		byte[] ascii = new byte[2 * bcd.length];
		for (int i = 0; i < bcd.length; i++) {
			byte hi = (byte) (bcd[i] >> 4);
			byte lo = (byte) (bcd[i] & 0x0F);
			if (hi < 0x00 || hi > 0x09 || lo < 0x00 || lo > 0x09) {
				throw new IllegalArgumentException();
			}
			ascii[2 * i] = ((byte) (hi + 0x30));
			ascii[2 * i + 1] = ((byte) (lo + 0x30));
		}
		return new String(ascii);
	}

	public static byte[] intToCnBcd(int srcInt, int bcdByteCount) {
		if (srcInt < 0) {
			throw new IllegalArgumentException("srcInt should not be less than 0");
		}
		String numStr = String.valueOf(srcInt);
		int digitCount = numStr.length();
		byte[] bcd = null;
		if (digitCount % 2 == 0) {
			if (digitCount / 2 > bcdByteCount) {
				throw new IllegalArgumentException("The digit count of srcInt should not be larger than bcdByteCount");
			} else {
				bcd = asciiToBcd(numStr);
			}
		} else {
			if ((digitCount + 1) / 2 > bcdByteCount) {
				throw new IllegalArgumentException("The digit count of srcInt should not be larger than bcdByteCount");
			} else {
				String leftDigitStr = numStr.substring(0, digitCount - 1);
				bcd = asciiToBcd(leftDigitStr);
				int lowestDigit = Integer.parseInt(numStr.substring(digitCount - 1));
				int temp = (lowestDigit & 0x0F) << 4;
				if (temp > 127) {
					temp -= 256;
				}
				byte hi = (byte) temp;
				byte lo = (byte) 0x0F;
				byte last = (byte) (hi | lo);
				bcd = contactArray(bcd, new byte[] { last });
			}
		}
		if (bcd == null) {
			throw new IllegalStateException();
		}
		if (bcd.length < bcdByteCount) {
			byte[] supplement = new byte[bcdByteCount - bcd.length];
			for (int i = 0; i < supplement.length; i++) {
				supplement[i] = (byte) 0xFF;
			}
			bcd = contactArray(bcd, supplement);
		}
		return bcd;
	}

	public static int cnBcdToInt(byte[] cnBcd) {
		String toIntStr = cnBcdToAscii(cnBcd);
		return Integer.parseInt(toIntStr);
	}

	public static String cnBcdToAscii(byte[] cnBcd) {
		byte[] splitedBytes = new byte[2 * cnBcd.length];
		for (int i = 0; i < cnBcd.length; i++) {
			int temp = cnBcd[i];
			if (temp < 0) {
				temp += 256;
			}
			splitedBytes[2 * i] = (byte) (temp >> 4);
			splitedBytes[2 * i + 1] = (byte) (temp & 0x0F);
		}
		byte[] headArray = null;
		int splitIndex = arrayIndexOf(splitedBytes, (byte) 0x0F);
		if (splitIndex == 0) {
			throw new IllegalArgumentException("error CN BCD head");
		} else if (splitIndex == -1) {
			headArray = splitedBytes;
		} else {
			headArray = leftSubArray(splitedBytes, splitIndex);
			byte[] tailArray = rightSubArray(splitedBytes, splitIndex);
			for (int i = 0; i < tailArray.length; i++) {
				if (tailArray[i] != 0x0F) {
					throw new IllegalArgumentException("error CN BCD tail");
				}
			}
		}
		byte[] toStrBytes = new byte[headArray.length];
		for (int i = 0; i < headArray.length; i++) {
			if (headArray[i] < 0x00 || headArray[i] > 0x09) {
				throw new IllegalArgumentException("error CN BCD head");
			}
			toStrBytes[i] = (byte) (headArray[i] + 0x30);
		}
		String toIntStr = null;
		try {
			toIntStr = new String(toStrBytes, "US-ASCII");
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		return toIntStr;
	}

	public static String generateHexString(int byteLength) {
		char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteLength; i++) {
			Random radom = new Random();
			char hi = hexChars[radom.nextInt(16)];
			char lo = hexChars[radom.nextInt(16)];
			sb.append(hi);
			sb.append(lo);
		}
		return sb.toString();
	}

	public static String generateTransIdStr(long input, int outputLength) {
		String output = String.valueOf(input);
		if (output.length() > outputLength) {
			throw new IllegalArgumentException();
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < outputLength - output.length(); i++) {
			sb.append('0');
		}
		return sb.append(output).toString();
	}

	public static String intToKoalFormat(int srcInt) {
		if (srcInt < 0) {
			throw new IllegalArgumentException("the input int should not be less than 0");
		}
		String temp = String.valueOf(srcInt);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10 - temp.length(); i++) {
			sb.append('0');
		}
		sb.append(temp);
		return sb.toString();
	}

	public static byte[] bitComplement(byte[] src) {
		byte[] dest = new byte[src.length];
		for (int i = 0; i < src.length; i++) {
			dest[i] = (byte) (~src[i]);
		}
		return dest;
	}

	public static byte[] arrayXOR(byte[] a, byte[] b) {
		if (a.length != b.length) {
			throw new IllegalArgumentException();
		}
		byte[] c = new byte[a.length];
		for (int i = 0; i < a.length; i++) {
			c[i] = (byte) (a[i] ^ b[i]);
		}
		return c;
	}

	public static int binaryToInt(byte[] binary) {
		String hexStr = bytes2HexString(binary);
		return Integer.parseInt(hexStr, 16);
	}

	public static String intToBinaryString(int srcInt, int bitCount) {

		if (bitCount < 1 || bitCount > 32) {
			throw new IllegalArgumentException("bitCount should not be less than 1 or larger than 32");
		}
		int max = (int) (2 << bitCount - 1) - 1;
		if (srcInt < 0 || srcInt > max) {
			throw new IllegalArgumentException("srcInt should not be less than 0 or larger than " + max);
		}
		String binaryStr = Integer.toBinaryString(srcInt);
		int paddingBitCount = bitCount - binaryStr.length();
		if (paddingBitCount < 0) {
			throw new IllegalArgumentException("srcInt is larger than " + max);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < paddingBitCount; i++) {
			sb.append('0');
		}
		sb.append(binaryStr);
		return sb.toString();
	}

	/**
	 * 直接使用的byte没有根据实际数据换算成K/M（即如果<1024byte则显示为byte；
	 * 如果>=1024byte且<1048576byte,则显示为KB；如果>=1048576byte则换算显示成MB）
	 * 
	 * @param space
	 * @return
	 */
	public static String spaceToString(long space) {
		DecimalFormat df = new DecimalFormat("#.##");

		if (space >= 1024 && 1048576 > space) {
			double div = 1024;
			String avgStr = df.format(space / div);
			return avgStr + "KB";
		} else if (space >= 1048576) {
			double div = 1048576;
			String avgStr = df.format(space / div);
			return avgStr + "MB";
		} else
			return space + "byte";
	}

	public static String long2HexString(long l, int stringLength) {
		if ((stringLength < 1) || (stringLength > 16) || (0 != (stringLength % 2))) {
			throw new IllegalArgumentException("(stringLength < 1) || (stringLength > 16) || (0 != (stringLength % 2))");
		}

		long maxValue = (long) Math.pow(16, stringLength) - 1;
		if ((l < 0) || (l > maxValue)) {
			throw new IllegalArgumentException("(l < 0) || (l > maxValue)");
		}

		String hex = long2HexStingWithNecessaryEvenLength(l);

		StringBuffer sb = new StringBuffer();
		for (int loop = hex.length(); loop < stringLength; loop++) {
			sb.append('0');
		}
		return sb.append(hex).toString().toUpperCase();
	}

	public static String long2HexStingWithNecessaryEvenLength(long l) {
		String hex = Long.toHexString(l);
		if (0 != (hex.length() % 2)) {
			hex = "0" + hex;
		}
		return hex;
	}

	public static int hexString2Int(String src) {
		validate(src);

		if (8 < src.length()) {
			throw new IllegalArgumentException("invaild hex string");
		} else if (8 == src.length()) {
			char c = src.charAt(0);
			int i = hexString2Int("0" + c);
			if (8 <= i) {
				throw new IllegalArgumentException("invaild hex string");
			}
		}
		return Integer.parseInt(src, 16);
	}

	public static void validate(String src) {
		// 待验证的字符串不能为空
		if (null == src) {
			throw new IllegalArgumentException("invaild hex string");
		}

		// 长度为偶数
		if (0 != (src.length() % 2)) {
			throw new IllegalArgumentException("invaild hex string");
		}

		// 所有字符都在集合{0、1、2、3、4、5、6、7、8、9、a、b、c、d、e、f、A、B、C、D、E、F}中
		for (int i = 0; i < src.length(); i++) {
			if (!isHexChar(src.charAt(i))) {
				throw new IllegalArgumentException("invaild hex string");
			}
		}

	}

	public static boolean isHexChar(char hexChar) {
		// 字符是否在集合{0、1、2、3、4、5、6、7、8、9、a、b、c、d、e、f、A、B、C、D、E、F}中
		hexChar = Character.toUpperCase(hexChar);
		for (char c : HEX_CHARS) {
			if (c == hexChar) {
				return true;
			}
		}
		return false;
	}

}
