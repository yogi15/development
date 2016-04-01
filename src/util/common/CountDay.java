package util.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.commonUTIL;

public class CountDay {

	final public static int DC_30_360 = 1;
	final public static int DC_30E_360 = 2;
	final public static int DC_30EP_360 = 3;
	final public static int DC_ACT_360 = 4;
	final public static int DC_ACT_365 = 5;
	final public static int DC_ACT_365_ISDA = 6;
	final public static int DC_ACT_ACT = 7;
	final public static int DC_ACT_ACT29 = 8;
	final public static int DC_ACTB_ACTB = 9;
	final public static int DC_ACT_365_25 = 11;
	final public static int DC_ACT_365_JPY = 12;
	final public static int DC_ACT_1_360 = 13;
	final public static int DC_ACT_1_365 = 14;
	final public static int DC_30_365 = 15;
	final public static int DC_30E_365 = 16;
	final public static int DC_30EM_360 = 17;
	final public static int DC_ACT_365CM = 18;
	final public static int DC_BU_252 = 19;
	final public static int DC_1_1 = 20;
	final public static int DC_NL_365 = 21;
	final public static int DC_ACT_1_365_JPY = 22;
	final public static int DC_30E_360_FINAL = 23;
	final public static int DC_ACT_366 = 24;
	final public static int DC_30_360_US_BOND = 25;
	final public static int DC_30_360_ISDA = 26;
	
	final public static String S_30_360 = "30/360";
	final public static String S_30E_360 = "30E/360";
	final public static String S_30EP_360 = "30E+/360";
	final public static String S_ACT_360 = "ACT/360";
	final public static String S_ACT_365 = "ACT/365";
	final public static String S_ACT_365_ISDA = "ACT/365I";
	final public static String S_ACT_ACT = "ACT/ACT";
	final public static String S_ACT_ACT29 = "ACT/ACT29";
	final public static String S_ACTB_ACTB = "ACTB/ACTB";
	// final public static String S_ACT_nACT = "ACT/nACT";
	final public static String S_ACT_365_25 = "ACT/365.25";
	final public static String S_ACT_365_JPY = "ACT/365JPY";
	final public static String S_ACT_1_365_JPY = "ACT+1/365JPY";
	final public static String S_ACT_1_360 = "ACT+1/360";
	final public static String S_ACT_1_365 = "ACT+1/365";
	final public static String S_30_365 = "30/365";
	final public static String S_30E_365 = "30E/365";
	final public static String S_30EM_360 = "30E*/360";
	final public static String S_ACT_365CM = "ACT_365CM";
	final public static String S_BU_252 = "BU/252";
	final public static String S_1_1 = "1/1";
	final public static String S_NL_365 = "NL/365";
	final public static String S_30E_360_FINAL = "30E/360 Final";
	final public static String S_ACT_366 = "ACT/366";
	final public static String S_DC_30_360_US = "30/360US";
	final public static String S_DC_30_360_ISDA = "30/360ISDA";	

	final public static CountDay D_30_360 = new CountDay(DC_30_360);
	final public static CountDay D_30E_360 = new CountDay(DC_30E_360);
	final public static CountDay D_30EP_360 = new CountDay(DC_30EP_360);
	final public static CountDay D_ACT_360 = new CountDay(DC_ACT_360);
	final public static CountDay D_ACT_365 = new CountDay(DC_ACT_365);
	final public static CountDay D_ACT_365_ISDA = new CountDay(DC_ACT_365_ISDA);
	final public static CountDay D_ACT_ACT = new CountDay(DC_ACT_ACT);
	final public static CountDay D_ACT_ACT29 = new CountDay(DC_ACT_ACT29);
	final public static CountDay D_ACTB_ACTB = new CountDay(DC_ACTB_ACTB);
	final public static CountDay D_ACT_365_25 = new CountDay(DC_ACT_365_25);
	final public static CountDay D_ACT_365_JPY = new CountDay(DC_ACT_365_JPY);
	final public static CountDay D_ACT_1_365_JPY = new CountDay(
			DC_ACT_1_365_JPY);
	final public static CountDay D_ACT_1_360 = new CountDay(DC_ACT_1_360);
	final public static CountDay D_ACT_1_365 = new CountDay(DC_ACT_1_365);
	final public static CountDay D_30_365 = new CountDay(DC_30_365);
	final public static CountDay D_30E_365 = new CountDay(DC_30E_365);
	final public static CountDay D_30EM_360 = new CountDay(DC_30EM_360);
	final public static CountDay D_ACT_365CM = new CountDay(DC_ACT_365CM);
	final public static CountDay D_BU_252 = new CountDay(DC_BU_252);
	final public static CountDay D_1_1 = new CountDay(DC_1_1);
	final public static CountDay D_NL_365 = new CountDay(DC_NL_365);
	final public static CountDay D_30E_360_FINAL = new CountDay(
			DC_30E_360_FINAL);
	final public static CountDay D_ACT_366 = new CountDay(DC_ACT_366);
	final public static CountDay D_30_360_US = new CountDay(DC_30_360_US_BOND);
	final public static CountDay D_30_360_ISDA = new CountDay(DC_30_360_ISDA);	

	private static Map<String, CountDay> dayCountsByName = new HashMap<String, CountDay>();
	private static Map<Integer, CountDay> dayCountsByCode = new HashMap<Integer, CountDay>();
	private static Map<Integer, String> dayCountCodeByName = new HashMap<Integer, String>();
	private static List<String> domainValues = new ArrayList<String>();
	private static Map<String, Integer> daysinYearsCountsByCode = new HashMap<String, Integer>();

	static {

		dayCountsByName.put(S_30_360, D_30_360);
		dayCountsByName.put(S_30E_360, D_30E_360);
		dayCountsByName.put(S_30EP_360, D_30EP_360);
		dayCountsByName.put(S_ACT_360, D_ACT_360);
		dayCountsByName.put(S_ACT_365, D_ACT_365);
		dayCountsByName.put(S_ACT_365_ISDA, D_ACT_365_ISDA);
		dayCountsByName.put(S_ACT_ACT, D_ACT_ACT);
		dayCountsByName.put(S_ACT_ACT29, D_ACT_ACT29);
		dayCountsByName.put(S_ACTB_ACTB, D_ACTB_ACTB);
		dayCountsByName.put(S_ACT_365_25, D_ACT_365_25);
		dayCountsByName.put(S_ACT_365_JPY, D_ACT_365_JPY);
		dayCountsByName.put(S_ACT_1_365_JPY, D_ACT_1_365_JPY);
		dayCountsByName.put(S_ACT_1_360, D_ACT_1_360);
		dayCountsByName.put(S_ACT_1_365, D_ACT_1_365);
		dayCountsByName.put(S_30_365, D_30_365);
		dayCountsByName.put(S_30E_365, D_30E_365);
		dayCountsByName.put(S_30EM_360, D_30EM_360);
		dayCountsByName.put(S_ACT_365CM, D_ACT_365CM);
		dayCountsByName.put(S_BU_252, D_BU_252);
		dayCountsByName.put(S_1_1, D_1_1);
		dayCountsByName.put(S_NL_365, D_NL_365);
		dayCountsByName.put(S_30E_360_FINAL, D_30E_360_FINAL);
		dayCountsByName.put(S_ACT_366, D_ACT_366);
		dayCountsByName.put(S_DC_30_360_US, D_30_360_US);
		dayCountsByName.put(S_DC_30_360_ISDA, D_30_360_ISDA);		

		dayCountsByCode.put(Integer.valueOf(DC_30_360), D_30_360);
		dayCountsByCode.put(Integer.valueOf(DC_30E_360), D_30E_360);
		dayCountsByCode.put(Integer.valueOf(DC_30EP_360), D_30EP_360);
		dayCountsByCode.put(Integer.valueOf(DC_ACT_360), D_ACT_360);
		dayCountsByCode.put(Integer.valueOf(DC_ACT_365), D_ACT_365);
		dayCountsByCode.put(Integer.valueOf(DC_ACT_365_ISDA), D_ACT_365_ISDA);
		dayCountsByCode.put(Integer.valueOf(DC_ACT_ACT), D_ACT_ACT);
		dayCountsByCode.put(Integer.valueOf(DC_ACT_ACT29), D_ACT_ACT29);
		dayCountsByCode.put(Integer.valueOf(DC_ACTB_ACTB), D_ACTB_ACTB);
		dayCountsByCode.put(Integer.valueOf(DC_ACT_365_25), D_ACT_365_25);
		dayCountsByCode.put(Integer.valueOf(DC_ACT_365_JPY), D_ACT_365_JPY);
		dayCountsByCode.put(Integer.valueOf(DC_ACT_1_365_JPY), D_ACT_1_365_JPY);
		dayCountsByCode.put(Integer.valueOf(DC_ACT_1_360), D_ACT_1_360);
		dayCountsByCode.put(Integer.valueOf(DC_ACT_1_365), D_ACT_1_365);
		dayCountsByCode.put(Integer.valueOf(DC_30_365), D_30_365);
		dayCountsByCode.put(Integer.valueOf(DC_30E_365), D_30E_365);
		dayCountsByCode.put(Integer.valueOf(DC_30EM_360), D_30EM_360);
		dayCountsByCode.put(Integer.valueOf(DC_ACT_365CM), D_ACT_365CM);
		dayCountsByCode.put(Integer.valueOf(DC_BU_252), D_BU_252);
		dayCountsByCode.put(Integer.valueOf(DC_1_1), D_1_1);
		dayCountsByCode.put(Integer.valueOf(DC_NL_365), D_NL_365);
		dayCountsByCode.put(Integer.valueOf(DC_30E_360_FINAL), D_30E_360_FINAL);
		dayCountsByCode.put(Integer.valueOf(DC_ACT_366), D_ACT_366);
		dayCountsByCode.put(Integer.valueOf(DC_30_360_US_BOND), D_30_360_US);
		dayCountsByCode.put(Integer.valueOf(DC_30_360_ISDA), D_30_360_ISDA);		

		dayCountCodeByName.put(Integer.valueOf(DC_30_360), S_30_360);
		dayCountCodeByName.put(Integer.valueOf(DC_30E_360), S_30E_360);
		dayCountCodeByName.put(Integer.valueOf(DC_30EP_360), S_30EP_360);
		dayCountCodeByName.put(Integer.valueOf(DC_ACT_360), S_ACT_360);
		dayCountCodeByName.put(Integer.valueOf(DC_ACT_365), S_ACT_365);
		dayCountCodeByName
				.put(Integer.valueOf(DC_ACT_365_ISDA), S_ACT_365_ISDA);
		dayCountCodeByName.put(Integer.valueOf(DC_ACT_ACT), S_ACT_ACT);
		dayCountCodeByName.put(Integer.valueOf(DC_ACT_ACT29), S_ACT_ACT29);
		dayCountCodeByName.put(Integer.valueOf(DC_ACTB_ACTB), S_ACTB_ACTB);
		dayCountCodeByName.put(Integer.valueOf(DC_ACT_365_25), S_ACT_365_25);
		dayCountCodeByName.put(Integer.valueOf(DC_ACT_365_JPY), S_ACT_365_JPY);
		dayCountCodeByName.put(Integer.valueOf(DC_ACT_1_365_JPY),
				S_ACT_1_365_JPY);
		dayCountCodeByName.put(Integer.valueOf(DC_ACT_1_360), S_ACT_1_360);
		dayCountCodeByName.put(Integer.valueOf(DC_ACT_1_365), S_ACT_1_365);
		dayCountCodeByName.put(Integer.valueOf(DC_30_365), S_30_365);
		dayCountCodeByName.put(Integer.valueOf(DC_30E_365), S_30E_365);
		dayCountCodeByName.put(Integer.valueOf(DC_30EM_360), S_30EM_360);
		dayCountCodeByName.put(Integer.valueOf(DC_ACT_365CM), S_ACT_365CM);
		dayCountCodeByName.put(Integer.valueOf(DC_BU_252), S_BU_252);
		dayCountCodeByName.put(Integer.valueOf(DC_1_1), S_1_1);
		dayCountCodeByName.put(Integer.valueOf(DC_NL_365), S_NL_365);
		dayCountCodeByName.put(Integer.valueOf(DC_30E_360_FINAL),
				S_30E_360_FINAL);
		dayCountCodeByName.put(Integer.valueOf(DC_ACT_366), S_ACT_366);
		dayCountCodeByName.put(Integer.valueOf(DC_30_360_US_BOND),
				S_DC_30_360_US);
		dayCountCodeByName.put(Integer.valueOf(DC_30_360_ISDA),
				S_DC_30_360_ISDA);		

		daysinYearsCountsByCode.put(S_ACT_366, new Integer(366));
		daysinYearsCountsByCode.put(S_30_360, new Integer(360));
		daysinYearsCountsByCode.put(S_30E_360, new Integer(360));
		daysinYearsCountsByCode.put(S_30EP_360, new Integer(360));
		daysinYearsCountsByCode.put(S_ACT_360, new Integer(360));
		daysinYearsCountsByCode.put(S_ACT_365, new Integer(365));
		daysinYearsCountsByCode.put(S_ACT_365_ISDA, new Integer(365));

		daysinYearsCountsByCode.put(S_30_365, new Integer(365));
		daysinYearsCountsByCode.put(S_30E_365, new Integer(365));
		daysinYearsCountsByCode.put(S_30EM_360, new Integer(360));

		daysinYearsCountsByCode.put(S_ACT_366, new Integer(366));
		daysinYearsCountsByCode.put(S_DC_30_360_US, new Integer(360));
		daysinYearsCountsByCode.put(S_DC_30_360_ISDA, new Integer(360));		
	}
	private int _dc;

	private CountDay(int daycount) {
		_dc = daycount;
	}

	static public CountDay valueOf(int code) {
		return dayCountsByCode.get(Integer.valueOf(code));
	}

	static public CountDay get(int code) {
		return dayCountsByCode.get(Integer.valueOf(code));
	}

	/**
	 * Returns the code of this <code>DayCount</code>.
	 * 
	 * @return the code of this <code>DayCount</code>.
	 */
	final public int getCode() {
		return _dc;
	}

	static private int dayDiff30_360(DateU start, DateU end) {
		int y2 = end.get_year();
		int m2 = end.get_month();
		int d2 = end.get_dayOfMonth();
		int y1 = start.get_year();
		int m1 = start.get_month();
		int d1 = start.get_dayOfMonth();

		if (d1 == 30 || d1 == 31) {
			if (d2 == 31)
				d2 = 30;
		}
		if (d1 == 31)
			d1 = 30;

		return ((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1));
	}

	static private double yearDiff30EP_360(DateU start, DateU end) {
		int y2 = end.get_year();
		int m2 = end.get_month();
		int d2 = end.get_dayOfMonth();
		int y1 = start.get_year();
		int m1 = start.get_month();
		int d1 = start.get_dayOfMonth();

		if (d1 == 31)
			d1 = 30;
		if (d2 == 31) {
			d2 = 1;
			if (m2 != 12) {
				m2 += 1;
			} else {
				m2 = 1;
				y2 += 1;
			}
		}

		return ((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1)) / 360.0;
	}

	static private double yearDiff30EP_360(DateU start, DateU end, double amount) {
		int y2 = end.get_year();
		int m2 = end.get_month();
		int d2 = end.get_dayOfMonth();
		int y1 = start.get_year();
		int m1 = start.get_month();
		int d1 = start.get_dayOfMonth();

		if (d1 == 31)
			d1 = 30;
		if (d2 == 31) {
			d2 = 1;
			if (m2 != 12) {
				m2 += 1;
			} else {
				m2 = 1;
				y2 += 1;
			}
		}

		return ((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1)) * amount / 360.0;
	}

	static private int dayDiff30EP_360(DateU start, DateU end) {
		int y2 = end.get_year();
		int m2 = end.get_month();
		int d2 = end.get_dayOfMonth();
		int y1 = start.get_year();
		int m1 = start.get_month();
		int d1 = start.get_dayOfMonth();

		if (d1 == 31)
			d1 = 30;
		if (d2 == 31) {
			d2 = 1;
			if (m2 != 12) {
				m2 += 1;
			} else {
				m2 = 1;
				y2 += 1;
			}
		}

		return ((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1));
	}

	static private double yearDiffACT_365_25(DateU start, DateU end) {
		return (DateU.diff(start, end) / 365.25);
	}

	static private double yearDiffACT_365_25(DateU start, DateU end,
			double amount) {
		return (DateU.diff(start, end) * amount / 365.25);
	}

	/*
* 
*/
	static private double yearDiffACT_1_360(DateU start, DateU end) {
		return (DateU.diff(start, end) + 1) / 360.0;
	}

	static private double yearDiffACT_1_360(DateU start, DateU end,
			double amount) {
		return (DateU.diff(start, end) + 1) * amount / 360.0;
	}

	/*
* 
*/
	static private double yearDiffACT_1_365(DateU start, DateU end) {
		return (DateU.diff(start, end) + 1) / 365.0;
	}

	static private double yearDiffACT_1_365(DateU start, DateU end,
			double amount) {
		return (DateU.diff(start, end) + 1) * amount / 365.0;
	}

	static private int dayDiffACT_365_JPY(DateU start, DateU end) {
		return (int) (DateU.diff(start, end) - numberOfFeb29(start, end));
	}

	static private int dayDiffACT_366(DateU start, DateU end) {
		return (int) DateU.diff(start, end);
	}

	static private int dayDiffACT_1_360(DateU start, DateU end) {
		return (int) DateU.diff(start, end) + 1;
	}

	static private double yearDiff30_365(DateU start, DateU end) {
		return dayDiff30_365(start, end) / 365.;
	}

	static private double yearDiff30_365(DateU start, DateU end, double amount) {
		return dayDiff30_365(start, end) * amount / 365.;
	}

	static private int dayDiff30_365(DateU start, DateU end) {
		int d1 = start.get_dayOfMonth();
		int m1 = start.get_month();
		int y1 = start.get_year();

		int d2 = end.get_dayOfMonth();
		int m2 = end.get_month();
		int y2 = end.get_year();

		if (d1 == 31)
			d1 = 30;
		if (d2 == 31 && d1 == 30)
			d2 = 30;
		return 360 * (y2 - y1) + 30 * (m2 - m1) + (d2 - d1);

	}

	static private double yearDiff30E_365(DateU start, DateU end) {
		return dayDiff30E_365(start, end) / 365.;
	}

	static private double yearDiff30E_365(DateU start, DateU end, double amount) {
		return dayDiff30E_365(start, end) * amount / 365.;
	}

	static private int dayDiff30E_365(DateU start, DateU end) {
		int d1 = start.get_dayOfMonth();
		int m1 = start.get_month();
		int y1 = start.get_year();

		int d2 = end.get_dayOfMonth();
		int m2 = end.get_month();
		int y2 = end.get_year();
		if (d1 == 31)
			d1 = 30;
		if (d2 == 31)
			d2 = 30;
		return 360 * (y2 - y1) + 30 * (m2 - m1) + (d2 - d1);
	}

	static private double yearDiff30EM_360(DateU start, DateU end) {
		int d1 = start.get_dayOfMonth();
		int m1 = start.get_month();
		int y1 = start.get_year();

		int d2 = end.get_dayOfMonth();
		int m2 = end.get_month();
		int y2 = end.get_year();
		if (d1 == 31)
			d1 = 30;
		if (d2 == 31)
			d2 = 30;
		return ((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1)) / 360.0;
	}

	static private double yearDiff30EM_360(DateU start, DateU end, double amount) {
		int d1 = start.get_dayOfMonth();
		int m1 = start.get_month();
		int y1 = start.get_year();

		int d2 = end.get_dayOfMonth();
		int m2 = end.get_month();
		int y2 = end.get_year();
		if (d1 == 31)
			d1 = 30;
		if (d2 == 31)
			d2 = 30;
		return ((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1)) * amount / 360.0;
	}

	static private int dayDiff30EM_360(DateU start, DateU end) {
		int d1 = start.get_dayOfMonth();
		int m1 = start.get_month();
		int y1 = start.get_year();

		int d2 = end.get_dayOfMonth();
		int m2 = end.get_month();
		int y2 = end.get_year();
		if (d1 == 31)
			d1 = 30;
		if (d2 == 31)
			d2 = 30;

		return ((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1));
	}

	static private int dayDiffACT_365CM(DateU start, DateU end) {
		return (int) (DateU.diff(start, end));
	}

	static private double yearDiff30_360(DateU start, DateU end) {
		int d1 = start.get_dayOfMonth();
		int m1 = start.get_month();
		int y1 = start.get_year();

		int d2 = end.get_dayOfMonth();
		int m2 = end.get_month();
		int y2 = end.get_year();

		if (d1 == 30 || d1 == 31) {
			if (d2 == 31)
				d2 = 30;
		}
		if (d1 == 31)
			d1 = 30;

		return ((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1)) / 360.0;
	}

	static private double yearDiff30_360(DateU start, DateU end, double amount) {
		int d1 = start.get_dayOfMonth();
		int m1 = start.get_month();
		int y1 = start.get_year();

		int d2 = end.get_dayOfMonth();
		int m2 = end.get_month();
		int y2 = end.get_year();

		if (d1 == 30 || d1 == 31) {
			if (d2 == 31)
				d2 = 30;
		}
		if (d1 == 31)
			d1 = 30;

		return ((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1)) * amount / 360.0;
	}

	static private double yearDiff30E_360(DateU start, DateU end) {
		return dayDiff30E_360(start, end) / 360.0;
	}

	static private int dayDiff30E_360(DateU start, DateU end) {
		int d1 = start.get_dayOfMonth();
		int m1 = start.get_month();
		int y1 = start.get_year();

		int d2 = end.get_dayOfMonth();
		int m2 = end.get_month();
		int y2 = end.get_year();

		if (d1 == start.getMonthLength()) {
			d1 = 30;
		}
		if (d2 == end.getMonthLength()) {
			d2 = 30;
		}
		return ((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1));
	}
	
	/**
	 * Returns Day Count Factor for 30/360 US Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	static private int dayDiff30_360_US(DateU startDate, DateU endDate) {

		int y2 = endDate.get_year();
		int m2 = endDate.get_month();
		int d2 = endDate.get_dayOfMonth();

		int y1 = startDate.get_year();
		int m1 = startDate.get_month();
		int d1 = startDate.get_dayOfMonth();

		if (d1 == 30 || d1 == 31) {
			if (d2 == 31)
				d2 = 30;
		}
		if (d1 == 31)
			d1 = 30;

		return (((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1))) ;

	}

	/**
	 * Returns Day Count Factor for 30/360 ISDA Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	static private int dayDiff30_360_ISDA(DateU startDate,
			DateU endDate) {

		int y2 = endDate.get_year();
		int m2 = endDate.get_month();
		int d2 = endDate.get_dayOfMonth();

		int y1 = startDate.get_year();
		int m1 = startDate.get_month();
		int d1 = startDate.get_dayOfMonth();

		int d11 = commonUTIL.getLastDayofMonth(startDate.getDate());
		int d21 = commonUTIL.getLastDayofMonth(endDate.getDate());

		if (d1 == d11) {

			d1 = 30;
		}

		if (d2 == d21) {

			d2 = 30;

		}

		return (((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1)));

	}
	
	static private double yearDiffACT_360(DateU start, DateU end) {
		return DateU.diff(start, end) / 360.0;
	}

	static private double yearDiffACT_365(DateU start, DateU end) {
		return DateU.diff(start, end) / 365.0;
	}

	static private double yearDiffACT_366(DateU start, DateU end) {
		return DateU.diff(start, end) / 366.0;
	}

	static private double yearDiffACT_366(DateU start, DateU end, double amount) {
		return DateU.diff(start, end) * amount / 366.0;
	}

	static private double yearDiffACT_365CM(DateU start, DateU end) {
		return DateU.diff(start, end) / 365.0;
	}

	static private double yearDiffACT_365(DateU start, DateU end, double amount) {
		return DateU.diff(start, end) * amount / 365.0;
	}

	static private double yearDiffACT_ACT(DateU start, DateU end) {
		return yearDiffACT_365_ISDA(start, end);
	}

	static private double yearDiffACT_365_ISDA(DateU start, DateU end) {
		DateU d1, d2;
		int sign;
		if (start.before(end)) {
			d1 = start;
			d2 = end;
			sign = 1;
		} else {
			d1 = end;
			d2 = start;
			sign = -1;
		}

		int y1 = d1.get_year();
		int y2 = d2.get_year();

		double yearDiff = 0.0;
		if (y1 == y2) {
			double dr = (DateU.isLeapYear(y1)) ? 366.0 : 365.0;
			yearDiff = DateU.diff(d1, d2) / dr;
		} else {
			double dr = (DateU.isLeapYear(y1)) ? 366.0 : 365.0;
			yearDiff = DateU
					.diff(d1, DateU.valueOf((y1 + 1), DateU.JANUARY, 1)) / dr;
			// yearDiff = DateU.diff(d1, DateU.valueOf(y1, DateU.DECEMBER,
			// 31)) / dr;

			for (int i = y1 + 1; i < y2; i++)
				yearDiff += 1.0;

			dr = (DateU.isLeapYear(y2)) ? 366.0 : 365.0;
			yearDiff += DateU.diff(DateU.valueOf(y2, DateU.JANUARY, 1), d2)
					/ dr;
		}

		return yearDiff * sign;
	}

	static private double yearDiffACT_ACT29(DateU start, DateU end) {

		// return JDate.diff(start,end) / (365.+
		// DateUtil.numberOfFeb29(start,end));
		if (containsFeb29(start, end)) {
			return DateU.diff(start, end) / 366.;
		} else {
			return DateU.diff(start, end) / 365.;
		}

	}

	static public boolean containsFeb29(DateU start, DateU end) {
		if (start.isLeapYear()) {
			DateU feb29 = DateU.valueOf(start.get_year(), DateU.FEBRUARY, 29);
			if (start.lte(feb29) && end.gte(feb29))
				return true;
		}
		int year = start.get_year() + 1;
		while (year < end.get_year()) {
			if (DateU.isLeapYear(year))
				return true;
			year++;
		}
		if ((start.get_year() != end.get_year())
				&& DateU.isLeapYear(end.get_year())) {
			DateU feb29 = DateU.valueOf(end.get_year(), DateU.FEBRUARY, 29);
			if (end.gte(feb29))
				return true;
		}
		return false;
	}

	static private double yearDiff30E_360_FINAL(DateU start, DateU end) {
		return dayDiff30E_360_FINAL(start, end) / 360.0;
	}

	static private int dayDiff30E_360_FINAL(DateU start, DateU end) {
		int d1 = start.get_dayOfMonth();
		int m1 = start.get_month();
		int y1 = start.get_year();

		int d2 = end.get_dayOfMonth();
		int m2 = end.get_month();
		int y2 = end.get_year();

		if (d1 == start.getMonthLength()) {
			d1 = 30;
		}
		if (d2 == 31) {
			d2 = 30;
		}
		return ((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1));
	}

	/**
	 * Computes the fraction of a year between the two dates start and end. This
	 * is done according to the day count that is set for this object.
	 * 
	 * @param start
	 *            the start date of the interval.
	 * @param end
	 *            the end date of the interval.
	 * @return the fraction of a year between the two dates.
	 */
	public double yearDiff(DateU start, DateU end) {
		switch (_dc) {
		case DC_30_360:
			return (yearDiff30_360(start, end));
		case DC_30E_360:
			return (yearDiff30E_360(start, end));
		case DC_30EP_360:
			return (yearDiff30EP_360(start, end));
		case DC_ACT_360:
			return (yearDiffACT_360(start, end));
		case DC_ACT_365:
			return (yearDiffACT_365(start, end));
		case DC_ACT_365_ISDA:
			return (yearDiffACT_365_ISDA(start, end));
		case DC_ACT_ACT:
			return (yearDiffACT_ACT(start, end));
		case DC_ACT_ACT29:
			return (yearDiffACT_ACT29(start, end));
		case DC_ACTB_ACTB:
			// return (yearDiffACTB_ACTB(start, end));
			// case DC_ACT_nACT: return (yearDiffACT_nACT(start,end));
		case DC_ACT_365_25:
			return (yearDiffACT_365_25(start, end));
		case DC_ACT_365_JPY:
		case DC_NL_365:
			// return (yearDiffACT_365_JPY(start, end));
		case DC_ACT_1_365_JPY:
			// return (yearDiffACT_1_365_JPY(start, end));
		case DC_ACT_1_360:
			return (yearDiffACT_1_360(start, end));
		case DC_ACT_1_365:
			return (yearDiffACT_1_365(start, end));
		case DC_30_365:
			return (yearDiff30_365(start, end));
		case DC_30E_365:
			return (yearDiff30E_365(start, end));
		case DC_30EM_360:
			return (yearDiff30EM_360(start, end));
		case DC_ACT_365CM:
			return (yearDiffACT_365CM(start, end));
		case DC_BU_252:
			// return (yearDiffBU_252(start, end));
		case DC_1_1:
			return (yearDiff1_1(start, end));
		case DC_30E_360_FINAL:
			return yearDiff30E_360_FINAL(start, end);
		case DC_ACT_366:
			return yearDiffACT_366(start, end);
		default: {

			throw new Error("DayCount corrupted");
		}
		}
	}

	/**
	 * Computes the difference in days between the two dates start and end. This
	 * is done according to the day count that is set for this object.
	 * 
	 * @param start
	 *            the start date of the interval.
	 * @param end
	 *            the end of the interval.
	 * @return the number of days between the 2 dates.
	 */
	public int dayDiff(DateU start, DateU end) {
		DateU d1, d2;
		if (start.before(end)) {
			d1 = start;
			d2 = end;
		} else {
			d1 = end;
			d2 = start;
		}

		switch (_dc) {
		case DC_30_360:
			return dayDiff30_360(d1, d2);
		case DC_30E_360:
			return dayDiff30E_360(d1, d2);
		case DC_30EP_360:
			return dayDiff30EP_360(d1, d2);
		case DC_ACT_360:
			return (int) DateU.diff(d1, d2);
		case DC_ACT_365:
			return (int) DateU.diff(d1, d2);
		case DC_ACT_365_ISDA:
			return (int) DateU.diff(d1, d2);
		case DC_ACT_ACT:
			return (int) DateU.diff(d1, d2);
		case DC_ACT_ACT29:
			return (int) DateU.diff(d1, d2);
		case DC_ACTB_ACTB:
			return (int) DateU.diff(d1, d2);
			// case DC_ACT_nACT : return (int) JDate.diff(d1,d2);
		case DC_ACT_365_25:
			return (int) DateU.diff(d1, d2);
		case DC_ACT_365_JPY:
		case DC_NL_365:
			return (dayDiffACT_365_JPY(start, end));
		case DC_ACT_1_365_JPY:
			// return (dayDiffACT_1_365_JPY(start, end));
		case DC_ACT_1_360:
			return (dayDiffACT_1_360(start, end));
		case DC_ACT_1_365:
			return (dayDiffACT_1_360(start, end));
		case DC_30_365:
			return (dayDiff30_365(start, end));
		case DC_30E_365:
			return (dayDiff30E_365(start, end));
		case DC_30EM_360:
			return (dayDiff30EM_360(start, end));
		case DC_ACT_365CM:
			return (dayDiffACT_365CM(start, end));
		case DC_BU_252:
			// return (dayDiffBU_252(start, end));
		case DC_1_1:
			return (int) DateU.diff(d1, d2);
		case DC_30E_360_FINAL:
			return dayDiff30E_360_FINAL(start, end);
		case DC_ACT_366:
			return dayDiffACT_366(start, end);
		case DC_30_360_ISDA:
			return dayDiff30_360_ISDA(d1, d2);
		case DC_30_360_US_BOND:
			return dayDiff30_360_US(d1, d2);
		default: {
			return -1;
		}
		}
	}

	static private double yearDiff1_1(DateU start, DateU end) {
		double yeardiff = end.get_year() - start.get_year();
		double monthdiff = (end.get_month() - start.get_month()) / 12.;
		if (yeardiff == 0.)
			return monthdiff;
		else
			monthdiff = ((end.get_month() + 12) - start.get_month()) / 12.;

		return (yeardiff - 1) + monthdiff;
	}

	static public CountDay valueOf(String s) {
		return dayCountsByName.get(s);
	}

	static public int getYearInDay(String s) {
		//System.out.println(s + " " + daysinYearsCountsByCode.get(s));
		return daysinYearsCountsByCode.get(s).intValue();
	}

	static public double yearDiff(DateU start, DateU end, String dc) {
		CountDay d = CountDay.valueOf(dc);
		return d.yearDiff(start, end);
	}

	/**
	 * Returns the number of times February 29 is found within in the specified
	 * date interval. The interval is inclusive.
	 * 
	 * @param start
	 *            the start date of the interval.
	 * @param end
	 *            the end date of the interval.
	 * @return the number of Feb29 contained in the interval.
	 */
	static public int numberOfFeb29(DateU start, DateU end) {
		int count = 0;
		if (start.isLeapYear()) {
			DateU feb29 = DateU.valueOf(start.get_year(), DateU.FEBRUARY, 29);
			if (start.lte(feb29) && end.gte(feb29))
				count++;
		}
		int year = start.get_year() + 1;
		while (year < end.get_year()) {
			if (DateU.isLeapYear(year))
				count++;
			year++;
		}
		if ((start.get_year() != end.get_year())
				&& DateU.isLeapYear(end.get_year())) {
			DateU feb29 = DateU.valueOf(end.get_year(), DateU.FEBRUARY, 29);
			if (end.gte(feb29))
				count++;
		}
		return count;
	}

	public double getDayCountFactor(DateU start, DateU end) {
		DateU d1, d2;
		if (start.before(end)) {
			d1 = start;
			d2 = end;
		} else {
			d1 = end;
			d2 = start;
		}

		switch (_dc) {
		case DC_30_360:
			return dayCountFactor30_360(d1, d2);
		case DC_30E_360:
			return dayCountFactor30E_360(d1, d2);
		case DC_30EP_360:
			return dayCountFactor30EP_360(d1, d2);
		case DC_ACT_360:
			return dayCountFactorACT_360(d1, d2);
		case DC_ACT_365:
			return dayCountFactorACT_365(d1, d2);
		case DC_ACT_365_ISDA:
			return (int) DateU.diff(d1, d2);
		case DC_ACT_ACT:
			return (int) DateU.diff(d1, d2);
		case DC_ACT_ACT29:
			return (int) DateU.diff(d1, d2);
		case DC_ACTB_ACTB:
			return (int) DateU.diff(d1, d2);
			// case DC_ACT_nACT : return (int) JDate.diff(d1,d2);
		case DC_ACT_365_25:
			return (int) DateU.diff(d1, d2);
		case DC_ACT_365CM:
			return (dayDiffACT_365CM(start, end));
		case DC_BU_252:
			// return (dayCountFactorBU_252(start, end));
		case DC_1_1:
			return (int) DateU.diff(d1, d2);
		case DC_ACT_366:
			return dayCountFactorACT_366(d1, d2);
		case DC_30_360_ISDA:
			return dayCountFactor30_360_ISDA(d1, d2);
		case DC_30_360_US_BOND:
			return dayCountFactor30_360_US(d1, d2);
		default: {
			return -1;
		}
		}
	}
	
	/**
	 * Returns Day Count Factor for 30/360 Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double dayCountFactor30_360(DateU startDate, DateU endDate) {

		int y2 = endDate.get_year();
		int m2 = endDate.get_month();
		int d2 = endDate.get_dayOfMonth();

		int y1 = startDate.get_year();
		int m1 = startDate.get_month();
		int d1 = startDate.get_dayOfMonth();

		if (d1 == 30 || d1 == 31) {
			if (d2 == 31)
				d2 = 30;
		}
		if (d1 == 31)
			d1 = 30;

		if (m1 == 2 && commonUTIL.isLeapYear(y1) && d1 == 29) {

			d1 = 30;

		} else if (m1 == 2 && !commonUTIL.isLeapYear(y1) && d1 == 28) {

			d1 = 30;
		}

		double dayCountFactor = (((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1))) / 360.0;

		return Math.round(dayCountFactor * 1000000.0) / 1000000.0;

	}

	/**
	 * Returns Day Count Factor for 30/360 US Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double days30_360_US(DateU startDate, DateU endDate) {

		int y2 = endDate.get_year();
		int m2 = endDate.get_month();
		int d2 = endDate.get_dayOfMonth();

		int y1 = startDate.get_year();
		int m1 = startDate.get_month();
		int d1 = startDate.get_dayOfMonth();

		if (d1 == 30 || d1 == 31) {
			if (d2 == 31)
				d2 = 30;
		}
		if (d1 == 31)
			d1 = 30;

		double days = (((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1)));

		return days;

	}

	/**
	 * Returns Day Count Factor for 30/360 ISDA Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double days30_360_ISDA(DateU startDate,
			DateU endDate) {

		int y2 = endDate.get_year();
		int m2 = endDate.get_month();
		int d2 = endDate.get_dayOfMonth();

		int y1 = startDate.get_year();
		int m1 = startDate.get_month();
		int d1 = startDate.get_dayOfMonth();

		int d11 = commonUTIL.getLastDayofMonth(startDate.getDate());
		int d21 = commonUTIL.getLastDayofMonth(endDate.getDate());

		if (d1 == d11) {

			d1 = 30;
		}

		if (d2 == d21) {

			d2 = 30;

		}

		double days = (((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1)));

		return days;

	}

	/**
	 * Returns Day Count Factor for 30E/360 Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double dayCountFactor30E_360(DateU startDate, DateU endDate) {

		int y2 = endDate.get_year();
		int m2 = endDate.get_month();
		int d2 = endDate.get_dayOfMonth();

		int y1 = startDate.get_year();
		int m1 = startDate.get_month();
		int d1 = startDate.get_dayOfMonth();

		if (d1 == 31) {

			d1 = 30;

		}

		if (d2 == 31) {

			d2 = 30;

		}

		double dayCountFactor = (((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1))) / 360.0;

		return Math.round(dayCountFactor * 1000000.0) / 1000000.0;

	}

	/**
	 * Returns Day Count Factor for 30EP/360 Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double dayCountFactor30EP_360(DateU startDate, DateU endDate) {

		int y2 = endDate.get_year();
		int m2 = endDate.get_month();
		int d2 = endDate.get_dayOfMonth();

		int y1 = startDate.get_year();
		int m1 = startDate.get_month();
		int d1 = startDate.get_dayOfMonth();

		if (d1 == 31) {

			d1 = 30;

		}

		if (d2 == 31 && m2 == 12) {

			d2 = 1;
			m2 = 1;
			y2 = y2 + 1;

		} else if (d2 == 31) {

			d2 = 1;
			m2 = m2 + 1;

		}

		double dayCountFactor = (((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1))) / 360.0;

		return Math.round(dayCountFactor * 1000000.0) / 1000000.0;

	}

	/**
	 * Returns Day Count Factor for ACT/360 Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double dayCountFactorACT_360(DateU startDate, DateU endDate) {

		long diffInDays = commonUTIL.jodaDiffInDays(startDate.getDate(), endDate.getDate());

		double dayCountFactor  = diffInDays / 360.0;
		
		return Math.round(dayCountFactor * 1000000.0) / 1000000.0;

	}

	/**
	 * Returns Day Count Factor for ACT/365 Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double dayCountFactorACT_365(DateU startDate, DateU endDate) {

		long diffInDays = commonUTIL.jodaDiffInDays(startDate.getDate(), endDate.getDate());

		double dayCountFactor  = diffInDays / 365.0;
		
		return Math.floor(dayCountFactor * 1000000.0) / 1000000.0;

	}

	/**
	 * Returns Day Count Factor for ACT/366 Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double dayCountFactorACT_366(DateU startDate, DateU endDate) {

		long diffInDays = commonUTIL.jodaDiffInDays(startDate.getDate(), endDate.getDate());

		double dayCountFactor  = diffInDays / 366.0;
		
		return Math.round(dayCountFactor * 1000000.0) / 1000000.0;

	}
	
	/**
	 * Returns number of days between two days according to Count Factor
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	
	public double getNumberofDaysBasedOnCountFactor(DateU start, DateU end) {
		DateU d1, d2;
		if (start.before(end)) {
			d1 = start;
			d2 = end;
		} else {
			d1 = end;
			d2 = start;
		}

		switch (_dc) {
		case DC_30_360:
			return days30_360(d1, d2);
		case DC_30E_360:
			return days30E_360(d1, d2);
		case DC_30EP_360:
			return days30EP_360(d1, d2);
		case DC_ACT_360:
			return daysACT_360(d1, d2);
		case DC_ACT_365:
			return daysACT_365(d1, d2);
		case DC_ACT_365_ISDA:
			return (int) DateU.diff(d1, d2);
		case DC_ACT_ACT:
			return (int) DateU.diff(d1, d2);
		case DC_ACT_ACT29:
			return (int) DateU.diff(d1, d2);
		case DC_ACTB_ACTB:
			return (int) DateU.diff(d1, d2);
			// case DC_ACT_nACT : return (int) JDate.diff(d1,d2);
		case DC_ACT_365_25:
			return (int) DateU.diff(d1, d2);
		case DC_ACT_365CM:
			return (dayDiffACT_365CM(start, end));
		case DC_BU_252:
			// return (dayCountFactorBU_252(start, end));
		case DC_1_1:
			return (int) DateU.diff(d1, d2);
		case DC_ACT_366:
			return daysACT_366(d1, d2);
		case DC_30_360_ISDA:
			return days30_360_ISDA(d1, d2);
		case DC_30_360_US_BOND:
			return days30_360_US(d1, d2);
		default: {
			return -1;
		}
		}
	}
	
	
	/**
	 * Returns  number of days  for 30/360 Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double days30_360(DateU startDate, DateU endDate) {

		int y2 = endDate.get_year();
		int m2 = endDate.get_month();
		int d2 = endDate.get_dayOfMonth();

		int y1 = startDate.get_year();
		int m1 = startDate.get_month();
		int d1 = startDate.get_dayOfMonth();

		if (d1 == 30 || d1 == 31) {
			if (d2 == 31)
				d2 = 30;
		}
		if (d1 == 31)
			d1 = 30;

		if (m1 == 2 && commonUTIL.isLeapYear(y1) && d1 == 29) {

			d1 = 30;

		} else if (m1 == 2 && !commonUTIL.isLeapYear(y1) && d1 == 28) {

			d1 = 30;
		}

		double days = (((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1)));

		return days;

	}

	/**
	 * Returns number of days for 30/360 US Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double dayCountFactor30_360_US(DateU startDate, DateU endDate) {

		int y2 = endDate.get_year();
		int m2 = endDate.get_month();
		int d2 = endDate.get_dayOfMonth();

		int y1 = startDate.get_year();
		int m1 = startDate.get_month();
		int d1 = startDate.get_dayOfMonth();

		if (d1 == 30 || d1 == 31) {
			if (d2 == 31)
				d2 = 30;
		}
		if (d1 == 31)
			d1 = 30;

		double dayCountFactor = (((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1))) / 360.0;

		return Math.round(dayCountFactor * 1000000.0) / 1000000.0;

	}

	/**
	 * Returns Day Count factor for 30/360 ISDA Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double dayCountFactor30_360_ISDA(DateU startDate,
			DateU endDate) {

		int y2 = endDate.get_year();
		int m2 = endDate.get_month();
		int d2 = endDate.get_dayOfMonth();

		int y1 = startDate.get_year();
		int m1 = startDate.get_month();
		int d1 = startDate.get_dayOfMonth();

		int d11 = commonUTIL.getLastDayofMonth(startDate.getDate());
		int d21 = commonUTIL.getLastDayofMonth(endDate.getDate());

		if (d1 == d11) {

			d1 = 30;
		}

		if (d2 == d21) {

			d2 = 30;

		}

		double dayCountFactor = (((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1))) / 360.0;

		return Math.floor(dayCountFactor * 1000000.0) / 1000000.0;

	}

	/**
	 * Returns number of days for 30E/360 Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double days30E_360(DateU startDate, DateU endDate) {

		int y2 = endDate.get_year();
		int m2 = endDate.get_month();
		int d2 = endDate.get_dayOfMonth();

		int y1 = startDate.get_year();
		int m1 = startDate.get_month();
		int d1 = startDate.get_dayOfMonth();

		if (d1 == 31) {

			d1 = 30;

		}

		if (d2 == 31) {

			d2 = 30;

		}

		double days = (((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1))) / 360.0;

		return days;

	}

	/**
	 * Returns number of days for 30EP/360 Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double days30EP_360(DateU startDate, DateU endDate) {

		int y2 = endDate.get_year();
		int m2 = endDate.get_month();
		int d2 = endDate.get_dayOfMonth();

		int y1 = startDate.get_year();
		int m1 = startDate.get_month();
		int d1 = startDate.get_dayOfMonth();

		if (d1 == 31) {

			d1 = 30;

		}

		if (d2 == 31 && m2 == 12) {

			d2 = 1;
			m2 = 1;
			y2 = y2 + 1;

		} else if (d2 == 31) {

			d2 = 1;
			m2 = m2 + 1;

		}

		double days = (((d2 - d1) + 30 * (m2 - m1) + 360 * (y2 - y1)));

		return days;

	}

	/**
	 * Returns number of days for ACT/360 Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double daysACT_360(DateU startDate, DateU endDate) {

		long diffInDays = commonUTIL.jodaDiffInDays(startDate.getDate(), endDate.getDate());

		double days  = diffInDays;
		
		return days;

	}

	/**
	 * Returns number of days for ACT/365 Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double daysACT_365(DateU startDate, DateU endDate) {

		long diffInDays = commonUTIL.jodaDiffInDays(startDate.getDate(), endDate.getDate());

		double days  = diffInDays;
		
		return days;

	}

	/**
	 * Returns number of days for ACT/366 Day Count Convention
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return double
	 */
	private double daysACT_366(DateU startDate, DateU endDate) {

		long diffInDays = commonUTIL.jodaDiffInDays(startDate.getDate(), endDate.getDate());

		double days  = diffInDays;
		
		return days;

	}
	
	static public void main(String args[]) {
		DateU start = DateU
				.valueOf(commonUTIL.stringToDate("25/03/2004", true));

		DateU end = DateU.valueOf(commonUTIL.stringToDate("25/06/2004", true));
		int periods = 12;

		System.out.println(DateU.diff(start, end));
		CountDay cda = CountDay.valueOf(S_30_360);
		CountDay.getYearInDay(S_30_360);
		System.out.println(cda.getCode() + " " + cda._dc);
		System.out.println(cda.dayDiff(start, end));
		CountDay cda1 = CountDay.valueOf(S_30_365);
		System.out.println(cda1.dayDiff(start, end));

		CountDay S_ACT_36012 = CountDay.valueOf(S_ACT_360);
		CountDay.getYearInDay(S_ACT_360);
		CountDay.getYearInDay(S_ACT_365);
		CountDay.getYearInDay(S_30_365);
		System.out.println("S_ACT_36012" + S_ACT_36012.dayDiff(start, end));
		CountDay S_ACT_36512 = CountDay.valueOf(S_ACT_365);
		System.out.println("S_ACT_36512" + S_ACT_36512.dayDiff(start, end));

		// JDate date = DateUtil.addMonthsObserveEOM(end, -periods);
		// JDate.diff(start,end)/JDate.diff(date,end);

	}

}
