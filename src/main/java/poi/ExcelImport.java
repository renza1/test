package poi;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelImport {

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream(new File("f:\\cash2.xlsx"));
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		int count = 1;
		int endRow = 277;

		Set<String> prods = new HashSet<String>();
		for (; count <= endRow; count++) {
			System.out.println("--"+count);
			XSSFRow xssfRow = xssfSheet.getRow(count);
			String mthRateStr = xssfRow.getCell(6) == null ? "" : xssfRow.getCell(6).toString().trim();
			if (StringUtils.isEmpty(mthRateStr))
				continue;

			double RATE_YEAR = 0l;
			try {
				RATE_YEAR = new BigDecimal(mthRateStr).multiply(new BigDecimal(100)).doubleValue();
			} catch (Exception e) {
				continue;
			}
			String prod_no = new BigDecimal(xssfRow.getCell(0).toString()).intValue()+"-1";
			String PP_VALUE = xssfRow.getCell(1).toString();

			String prod_name = xssfRow.getCell(2).toString();
			String chancode = xssfRow.getCell(3).toString();

			String LOAN_MIN = xssfRow.getCell(4).toString();

			String LOAN_MAX = xssfRow.getCell(5).toString();
			String CHAN_SOURCE = "wechat";
			if (chancode.equals("002")) {
				CHAN_SOURCE = "sale";
			}
			
			if (!prods.contains(prod_no)){
				System.out
				.println("INSERT INTO PCL.T_TEMP_PROD_NOR_INFO"
						+ "(PROD_NO, PROD_NAME, CHAN_NO, RATE_YEAR, LOAN_MIN, LOAN_MAX, IS_INSUUANCE, IS_ATTACH, DOWN_PAY_RULE, IS_CUSTOMIZED, BEGIN_DATE, END_DATE, IS_PROM, CHAN_SOURCE, PROD_TYPE)"
						+ "VALUES('" + prod_no+ "', '" + prod_name + "(A)" + "', '" + chancode + "', " + RATE_YEAR + ", "
						+ LOAN_MIN + ", " + LOAN_MAX + ", NULL, NULL, NULL, NULL, '2017-12-04', '2099-04-15', '0', '" + CHAN_SOURCE
						+ "', 'T004');");
				prods.add(prod_no);
			}

			String PP_RATE = new BigDecimal(xssfRow.getCell(7) == null ? "0" : xssfRow.getCell(7).toString().trim()).multiply(
					new BigDecimal(100)).toString();

			String AM_RATE = new BigDecimal(xssfRow.getCell(8) == null ? "0" : xssfRow.getCell(8).toString().trim()).multiply(
					new BigDecimal(100)).toString();

			String INS_FEE_RATE = new BigDecimal(xssfRow.getCell(9) == null ? "0" : xssfRow.getCell(9).toString().trim()).multiply(
					new BigDecimal(100)).toString();

			String rate1 = xssfRow.getCell(12).toString().trim();
			String rate2 = xssfRow.getCell(13).toString().trim();

			String RATE_MONTH = "0";
			String P2P_PP_RATE = "0";
			String P2P_LOAN_AMT = "10000";
			String P2P_AMT = new BigDecimal(rate1).setScale(4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("10000")).add(new BigDecimal("10000")).toString();

			String RATE_MONTH_2 = "0";
			String P2P_PP_RATE_2 = "0";
			String P2P_LOAN_AMT_2 = "10000";
			String P2P_AMT_2 = new BigDecimal(rate2).setScale(4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("10000")).add(new BigDecimal("10000")).toString();

			StringBuffer sql1 = new StringBuffer(
					"INSERT INTO PCL.T_TEMP_PERIOD_PROD (PP_NO, PP_VALUE, PP_RATE, PROD_NO, AM_RATE, INS_FEE_RATE) VALUES(sys_guid(),");
			sql1.append(PP_VALUE + ",");
			sql1.append(PP_RATE + ",");
			sql1.append("'" + prod_no + "',");
			sql1.append(AM_RATE + ",");
			sql1.append(INS_FEE_RATE + ");");
			System.out.println(sql1);
			StringBuffer sql2 = new StringBuffer(
					"INSERT INTO PCL.BUS_T_P2P_RATE(PROD_NO, CHAN_NO, INSTALL_NUM, RATE_MONTH, PP_RATE, AM_RATE, INS_FEE_RATE, IS_INSUUANCE, LOAN_AMT, P2P_AMT) VALUES (");
			sql2.append("'" + prod_no + "',");
			sql2.append("'"+chancode+"',");
			sql2.append(PP_VALUE + ",");
			sql2.append(RATE_MONTH + ",");
			sql2.append(P2P_PP_RATE + ",");
			sql2.append("0,0,'000001',");
			sql2.append(P2P_LOAN_AMT + ",");
			sql2.append(P2P_AMT + ");");
			System.out.println(sql2);
			StringBuffer sql3 = new StringBuffer(
					"INSERT INTO PCL.BUS_T_P2P_RATE(PROD_NO, CHAN_NO, INSTALL_NUM, RATE_MONTH, PP_RATE, AM_RATE, INS_FEE_RATE, IS_INSUUANCE, LOAN_AMT, P2P_AMT) VALUES (");
			sql3.append("'" + prod_no + "',");
			sql3.append("'"+chancode+"',");
			sql3.append(PP_VALUE + ",");
			sql3.append(RATE_MONTH_2 + ",");
			sql3.append(P2P_PP_RATE_2 + ",");
			sql3.append("0,0,'000002',");
			sql3.append(P2P_LOAN_AMT_2 + ",");
			sql3.append(P2P_AMT_2 + ");");
			System.out.println(sql3);
		}
	}
}
