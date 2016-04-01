package productPricing.pricingUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

import util.HolidayUtil;
import util.commonUTIL;

public class CashFlowUtil {
	
	/**
	 * Returns a list of dates substracted by frequency from last date till the date is less then or
	 * eqaul to start date.
	 * used to get cashflow dates. Please set the fistCashFlow coupon start date to the issue date at the calling method.
	 * 
	 * @author yogesh
	 * @param dateInString
	 * @return int
	 */	
	static public LinkedHashMap<Date, Date> getCashFlowDates(Date startDate, Date endDate, int addSubtratcNo,
			String criteria, String businessDayConvention, String issueCurrency) {
		LinkedHashMap<Date, Date> dateHash = new LinkedHashMap<Date, Date>();
		
		Calendar newDate = Calendar.getInstance();
		newDate.setTime(endDate);
		
		int lastDayOfMonth = newDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		int day = newDate.get(Calendar.DAY_OF_MONTH);
		int i = 0;
		Date previousBdcEndDate = null;
		
		while(startDate.before(endDate)) {
			
			switch (criteria) {
			
			case "day":
				
				newDate.add(Calendar.DATE, addSubtratcNo);
				break;
			
			case "week":
				
				newDate.add(Calendar.DATE, addSubtratcNo);
				break;
				
			case "month":
				
				if (lastDayOfMonth == day) {
					newDate.add(Calendar.DATE,
							-newDate.getActualMaximum(Calendar.DAY_OF_MONTH));
				} else {
					newDate.add(Calendar.MONTH, addSubtratcNo);
				}
				break;
				
			case "quarter":
				
				if (lastDayOfMonth == day) {
					newDate.add(Calendar.MONTH, addSubtratcNo);
					newDate.set(Calendar.DATE, newDate.getActualMaximum(Calendar.DAY_OF_MONTH));
				} else {
					newDate.add(Calendar.MONTH, addSubtratcNo);
				}
				break;
				
			case "semiAnnual":
				
				if (lastDayOfMonth == day) {
					newDate.add(Calendar.MONTH, addSubtratcNo);
					newDate.set(Calendar.DATE, newDate.getActualMaximum(Calendar.DAY_OF_MONTH));
				} else {
					newDate.add(Calendar.MONTH, addSubtratcNo);
				}
				break;
				
			case "year":
				
				if (lastDayOfMonth == day) {
					newDate.add(Calendar.MONTH, addSubtratcNo);
					newDate.set(Calendar.DATE, newDate.getActualMaximum(Calendar.DAY_OF_MONTH));
				} else {
					newDate.add(Calendar.MONTH, addSubtratcNo);
				}
				break;
				
			default:
				break;
			} 				
			
			Date startDateCal = newDate.getTime();
			Date bdcdate = newDate.getTime();
						
			if (!businessDayConvention.equals("NO_ADJUST")) {
				String stringStartDate = HolidayUtil
						.applyBusinessDayConvention(issueCurrency, 
								commonUTIL.dateToString(startDateCal), businessDayConvention);
				
				bdcdate= commonUTIL.stringToDate(stringStartDate, true);

			}
			if (i != 0) {				
				if(startDateCal.before(startDate) || startDateCal.equals(startDate)) {
					bdcdate = startDate;
				}
				dateHash.put(bdcdate, previousBdcEndDate);
			} else {
				dateHash.put(bdcdate, endDate);
			}
			previousBdcEndDate = bdcdate;
			endDate = startDateCal;
			i++;
		}

		return dateHash;

	}
}
