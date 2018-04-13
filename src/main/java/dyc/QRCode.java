/**
 * @author dyc
 * dyc
 * QRCode.java
 * 
 * 2016年7月1日-上午10:10:46
 *  2016XX公司-版权所有
 * 
 */
package dyc;

import java.io.File;

/**
 * @author dyc
 * @ClassName QRCode
 * @Description
 * @date 2016年7月1日
 * 
 * @version 1.0.0
 * 
 */
public class QRCode {
	public static void fddQrCodeEncode(String encodeddata, File destFile) {/*
		if (!destFile.exists())
			destFile.mkdirs();

		Qrcode qrcode = new Qrcode();
		// 错误修正容量
		// L水平 7%的字码可被修正
		// M水平 15%的字码可被修正
		// Q水平 25%的字码可被修正
		// H水平 30%的字码可被修正
		// QR码有容错能力，QR码图形如果有破损，仍然可以被机器读取内容，最高可以到7%~30%面积破损仍可被读取。
		// 相对而言，容错率愈高，QR码图形面积愈大。所以一般折衷使用15%容错能力。
		qrcode.setQrcodeErrorCorrect('M');
		qrcode.setQrcodeEncodeMode('B');
		qrcode.setQrcodeVersion(20);
		byte[] d;
		try {
			d = encodeddata.getBytes("UTF-8");
			BufferedImage bi = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
			// createGraphics
			Graphics2D g = bi.createGraphics();
			// set background
			g.setBackground(Color.WHITE);
			g.clearRect(0, 0, 300, 300);
			// 设置二维码图片颜色
			g.setColor(Color.BLACK);

			boolean[][] b = qrcode.calQrcode(d);
			for (int i = 0; i < b.length; i++) {
				for (int j = 0; j < b.length; j++) {
					if (b[j][i]) {
						g.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
					}
				}
			}

			g.dispose();
			bi.flush();

			ImageIO.write(bi, "png", destFile);
			System.out.println("Input Encoded data is：" + encodeddata);
		} catch (IOException e) {
			e.printStackTrace();
		}
	*/}

	public static void main(String[] args) {
		fddQrCodeEncode(
				"http://test.api.fabigbig.com:8888/api/extsign.action?timestamp=20160701103502&app_id=400080&v=2.0&msg_digest=ODdEOTY3MTFFNTUzRUYyMjY2RjBFOT"
						+ "AwQzhBMkI5RDlFQzNDMDAyMAAwQzhBMkI5RDlFQzNDMDAyMAAwQzhBMkI5RDlFQzNDMDAyMA==&transaction_id=46010201606300015011035025864601020160630001501103502586460102016063000150110350258646010201606300015011035025864601020160630001501103502586&contract_id=46010201606300015&client_type=1&customer_id=0B72AA3"
						+ "15D73AAA1&doc_title=%E5%90%88%E5%90%8C%E7%AD%BE%E7%AB%A0&doc_url=&doc_type=&sign_keyword=&return_url=http%3A%2F%2Fwx.pohoocredit.com%3A80"
						+ "%2Fdaiyc%2Fjsp%2Fsuccess.jsp&notify_url=http%3A%2F%2Fwx.pohoocredit.com%3A80%2Fdaiyc%2FfddNotify%3Ftype%3Dmanual",
				new File("e:\\1.png"));
	}
}
