package src.util.common.holiday;

import java.util.Collection;
import java.util.Vector;

import util.common.CDate;
 

public class BusinessHolidayCacheFactory {
private BusinessHolidayCacheFactory() {
		
	}
	
	static public BusinessHolidayCache makeConcurrent(BusinessHolidayCache chc){
		return new BusinessHolidayBoundedCacheRW(chc);
	}
	
	static public BusinessHolidayCache makeWesternWeekend(BusinessHolidayCache chc){
		chc.clear();
		CDate current = chc.getFirstDateInCacheRange();
		CDate end = chc.getLastDateInCacheRange();
		while (current.lte(end)){
			int dayOfWeek = current.getDayOfWeek();
			if (dayOfWeek!=CDate.SATURDAY && dayOfWeek!=CDate.SUNDAY){
				chc.setGoodBusinessDay(current);
			}
			current = current.addDays(1);
		}
		return chc;
	}


	static public BusinessHolidayCache makeEmptyCache(){
		BusinessHolidayBoundedCacheMonthlyBits chc = new BusinessHolidayBoundedCacheMonthlyBits();
		return chc;
	}
	
	/**
	 * Provides a cache on holidays centers in the holidayList
	 * @param hol
	 * @param holidayList
	 * @return
	 */
	static public BusinessHolidayCache make(Holiday hol, Collection<String> holidayList, BusinessHolidayCache chc){
	
		chc.clear();
		if (hol==null) return chc;
		
		CDate current = chc.getFirstDateInCacheRange();
		CDate end = chc.getLastDateInCacheRange();
		Vector v = new Vector(holidayList);
	
		while (current.lte(end)){
			if (NonCachedBusinessDayCalculator.getInstance().isBusinessDay(hol,current, v)) {
				chc.setGoodBusinessDay(current);
		    }
			current = current.addDays(1);
		}
		return chc;

	}

	/**
	 * Typically used to compare performance without good caching implementation.
	 * @param hol
	 * @param holidayList
	 * @return
	 */
	static public BusinessHolidayCache makeNullCache(final Holiday hol, Collection<String> holidayList){
		final Vector v = new Vector(holidayList);

		final BusinessHolidayBoundedCacheMonthlyBits chc = new BusinessHolidayBoundedCacheMonthlyBits();
		if (hol==null) throw new IllegalArgumentException("No holidays");
		
		BusinessHolidayCache x = new BusinessHolidayCache(){

			public void addBusinessDays(BusinessHolidayCache chc) {
				throw new UnsupportedOperationException();
			}

			public int calcBusinessDaysBetween(CDate x, CDate y) {
				CDate current = x;
			
				int count = 0;
				while (current.before(y)){
					if (NonCachedBusinessDayCalculator.getInstance().isBusinessDay(hol, current, v)) {
						count++;
					}
					current = current.addDays(1);
				}
				return count;
			}

			public CDate getFirstDateInCacheRange() {
				return chc.getFirstDateInCacheRange();
			}

			public CDate getLastDateInCacheRange() {
				return chc.getLastDateInCacheRange();
			}

			public boolean isGoodBusinessDay(CDate d) {
				return NonCachedBusinessDayCalculator.getInstance().isBusinessDay(hol, d, v);
			}

			public void setGoodBusinessDay(CDate d) {
				throw new UnsupportedOperationException();
			}

			public void clear() {
				throw new UnsupportedOperationException();
			}
			
		};
		
		return x;
	}
}
