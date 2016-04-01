package constants;

import java.util.HashMap;
import java.util.Map;

import util.common.CountDay;

public class DateConstants {
	
	final public static int ANNUAL = 1;
	final public static int SEMIANNUAL = 2;
	final public static int QUARTER = 3;
	final public static int MONTH = 4;
	final public static int WEEKLY = 5;
	final public static int DAILY = 6;
	final public static int ZEROCOUPON = 0;
	
	final public static int COMPARE_ANNUAL = 6;
	final public static int COMPARE_SEMIANNUAL = 5;
	final public static int COMPARE_QUARTER = 4;
	final public static int COMPARE_MONTH = 3;
	final public static int COMPARE_WEEKLY = 2;
	final public static int COMPARE_DAILY = 1;
	final public static int COMPARE_ZC = 0;
	
	final public static String PA = "PA";
	final public static String SA = "SA";
	final public static String QTR = "QTR";
	final public static String MON = "MON";
	final public static String WKL = "WKL";
	final public static String DAIL = "DAIL";
	final public static String ZC = "ZC";
	
	private static Map<String, Integer> frequenyConstants = new HashMap<String, Integer>();
	private static Map<String, Integer> compareFrequency = new HashMap<String, Integer>();
	private static Map<String, Integer> deductionNo = new HashMap<String, Integer>();
	
	static {

		frequenyConstants.put(PA, ANNUAL);
		frequenyConstants.put(SA, SEMIANNUAL);
		frequenyConstants.put(QTR, QUARTER);
		frequenyConstants.put(MON, MONTH);
		frequenyConstants.put(WKL, WEEKLY);
		frequenyConstants.put(DAIL, DAILY);
		frequenyConstants.put(ZC, ZEROCOUPON);
		
		
		compareFrequency.put(PA, COMPARE_ANNUAL);
		compareFrequency.put(SA, COMPARE_SEMIANNUAL);
		compareFrequency.put(QTR, COMPARE_QUARTER);
		compareFrequency.put(MON, COMPARE_MONTH);
		compareFrequency.put(WKL, COMPARE_WEEKLY);
		compareFrequency.put(DAIL, COMPARE_DAILY);
		compareFrequency.put(ZC, COMPARE_ZC);
		
		deductionNo.put(ZC, 0);
		deductionNo.put(PA, 1);
		deductionNo.put(SA, 6);
		deductionNo.put(QTR, 3);
		deductionNo.put(MON, 1);
		deductionNo.put(WKL, 7);
		deductionNo.put(DAIL, 1);
		
	}
	
	/**
	 * Returns an integer value assign for each frequency
	 * 
	 * @author yogesh
	 * @param frequency
	 * @return
	 */
	public static int getFrequenyConstantCode(String frequency) {
		
		return  frequenyConstants.get(frequency).intValue();
	}
	
	/**
	 * Returns an integer value assign for each frequency
	 * 
	 * @author yogesh
	 * @param frequency
	 * @return
	 */
	public static int getCompareFrequencyCode(String frequency) {
		
		return  compareFrequency.get(frequency).intValue();
	}
	
	/**
	 * Returns a default value thats needs to be deducted or added for each freuqency
	 * 
	 * @author yogesh
	 * @param frequency
	 * @return
	 * 
	 */
	public static int getDeductionNumber(String frequency) {
		
		return  frequenyConstants.get(frequency).intValue();
	}
}
