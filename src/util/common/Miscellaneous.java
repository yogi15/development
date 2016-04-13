package util.common;

import java.util.Calendar;
import java.util.Date;

import beans.Coupon;
import beans.Flows;
import beans.Product;
import beans.Trade;

import util.commonUTIL;

/**
 
 * @author yogesh
 *
 */
public class Miscellaneous {
	
	/**
	 * Returns number of months 
	 * 
	 * @author yogesh
	 * @param date1
	 * @param date2
	 * @param frequency
	 * @return int
	 */
	static public int getCouponRemainingMonths(String date1, String date2,
			int frequency) {

		Date startDate = commonUTIL.stringToDate(date1, true);
		Date endDate = commonUTIL.stringToDate(date2, true);

		int diffInMonths = commonUTIL.diffInMonths(startDate, endDate);

		return (diffInMonths % frequency);

	}
	
/**
 * Returns amount for principal flow
 * 
 * @param trade
 * @param product
 * @param coupon
 * @param flow
 * @param daycount
 * @return
 */
		
	public static double getPaymentAmount(Trade trade, Product product,
			Coupon coupon, Flows flow, CountDay daycount) {
		
		double cleanPrice = 0.0;
		
		Date startDate = flow.getStartDate();

		DateU productStartDate = DateU.valueOf(startDate);

		DateU tradeSttleDate = DateU.valueOf(commonUTIL.stringToDate(
				trade.getDelivertyDate(), true));

		if (commonUTIL.checkGreaterDate(
				commonUTIL.stringToDate(product.getIssueDate(), true),
				productStartDate.getDate())) {

			productStartDate = DateU.valueOf(commonUTIL.stringToDate(
					product.getIssueDate(), true));

		}

		double dayCountFactor = daycount.getDayCountFactor(productStartDate,
				tradeSttleDate);


		double accrualAmount = trade.getTradeAmount()
				* (coupon.getFixedRate() / 100) * dayCountFactor;

		cleanPrice = product.getProductType().equalsIgnoreCase("MM")?(trade.getTradeAmount()/ 100):((trade.getTradeAmount() * trade.getPrice()) / 100);

		return (cleanPrice + accrualAmount);

	}
	
	

	/**
	 *  @author yogesh
	 *
	 * @param amortizationData
	 * @param key
	 * @return Amortization attribute(Key) value
	 */
	public static String getAmortizationVal(String amortizationData, String key) {
		
		String amortSubString =  amortizationData.substring( amortizationData.indexOf(key) + (key.length()) + 1);
				
		if ( amortSubString.indexOf(";") == -1 ) {
			
			return  amortSubString;
		
		} else {
			
			return amortSubString.substring(0, amortSubString.indexOf(";"));
		}
	}
	
}


