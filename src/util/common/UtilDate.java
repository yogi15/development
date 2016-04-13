package util.common;

import java.util.TimeZone;
import java.util.Vector;
 

import util.CosmosException;
import util.commonUTIL;
import util.common.holiday.Holiday;
 


public class UtilDate {
	/**
	 * The date utility class that performs counting, addition, and other
	 * operations on <code>CDate</code> objects.
	 */
	
	
	/**
     * a String constant for Sunday
     */
    public final static String S_SUNDAY = "SUN";
    /**
     * a String constant for Monday
     */
    public final static String S_MONDAY = "MON";
    /**
     * a String constant for Tuesday
     */
    public final static String S_TUESDAY = "TUE";
    /**
     * a String constant for Wednesday
     */
    public final static String S_WEDNESDAY = "WED";
    /**
     * a String constant for Thursday
     */
    public final static String S_THURSDAY = "THU";
    /**
     * a String constant for Friday
     */
    public final static String S_FRIDAY = "FRI";
    /**
     * a String constant for Saturday
     */
    public final static String S_SATURDAY = "SAT";

    /**
     * Returns the <code>CDate</code> corresponding to the specified day in
     * month. Weekday is SUNDAY,...,SATURDAY.
     * Rank is 1,2,3,4,5 . 5 means the last weekday of the month.
     * @param year the year of the date to be found.
     * @param month the month of the date to be found.
     * @param weekDay the weekday of the date to be found.
     * @param rank the rank in month of the weekday of the date to be found.
     * @return the corresponding <code>CDate</code>.
     */
    static public CDate getJDate(int year, int month, int weekDay, int rank) 
    {
	CDate d = CDate.valueOf(year,month,1);
	if(rank == 5) {
	    d = d.getEOM();
	    return 
		d.addDays(-UtilDate.getNumberOfDaysToPreviousWeekDay(d,
								     weekDay));
	}
	else {
	    return 
		d.addDays(UtilDate.getNumberOfDaysToNextWeekDay(d,weekDay) +
			  (rank-1)*7);
	}
    }

    /**
     * Returns the number of days between this date and the next specified
     * weekday. For instance, if your date is a monday, and you want to know
     * how many days are left until next wednesday, it will return 2. If the
     * date falls on the same weekday as the one specified, it will return 0.
     * @param d the <code>CDate</code> from which to count the days.
     * @param weekDay the weekday to reach.
     * @return the number of days between the date and the next specified weekday.
     */
    static public int getNumberOfDaysToNextWeekDay(CDate d,int weekDay) 
    {
	int dw = d.getDayOfWeek();
	return ((weekDay >= dw) ? weekDay - dw : 7 - dw + weekDay);
    }

    /**
     * Returns the number of days between this date and the last specified
     * weekday. See the {@link #getNumberOfDaysToNextWeekDay(CDate, int)
     * getNumberOfDaysToNextWeekDay} for more details.
     * @param d the <code>CDate</code> from which to count the days.
     * @param weekDay the weekday to reach.
     * @return the number of days between the date and the previous specified weekday.
     */
    static public int getNumberOfDaysToPreviousWeekDay(CDate d,int weekDay) 
    {
	int dw = d.getDayOfWeek();
	return ((weekDay <= dw) ? dw - weekDay : 7 + dw - weekDay);
    }

    /**
     * Returns the 3rd specified weekday of the month and year of the specified date.
     * @param date the date which we will take the month and year.
     * @param weekday the weekday we want to find.
     * @return the 3rd specified weekday of the month and year of the specified date.
     */
    static public CDate getIMMDate(CDate date,int weekday)
    {
	return getJDate(date.getYear(),date.getMonth(), weekday, 3);
	//	return getJDate(date.getYear(),date.getMonth(), date.getDayOfWeek(), 3);
    }

    /**
     * Returns the 3rd specified weekday of the month and year of the specified date.
     * @param date the date which we will take the month and year.
     * @param weekday the weekday we want to find.
     * @return the 3rd specified weekday of the month and year of the specified date.
     */
    static public CDate getIMMDate(CDate date,int num, int weekday)
    {
	CDate d = date.addMonths(num);
	return getIMMDate(d,weekday);
    }

    /**
     * Returns true if there is a February 29 in the date interval, false
     * otherwise. The interval is inclusive.
     * @param start the start date of the interval.
     * @param end the end date of the interval.
     * @return true if interval contains at least one Feb29, false otherwise.
     */
    static public boolean containsFeb29(CDate start, CDate end) 
    {
	if (start.isLeapYear()) {
	    CDate feb29 = CDate.valueOf(start.getYear(), CDate.FEBRUARY, 29);
	    if (start.lte(feb29) && end.gte(feb29)) return true;
	}
	int year = start.getYear() + 1;
	while (year < end.getYear())  {
	    if (CDate.isLeapYear(year)) return true;
	    year++;
	}
	if ((start.getYear() != end.getYear()) && CDate.isLeapYear(end.getYear())) {
	    CDate feb29 = CDate.valueOf(end.getYear(), CDate.FEBRUARY, 29);
	    if (end.gte(feb29)) return true;
	}
	return false;
    }

    /**
     * Returns the number of times February 29 is found within in the specified date
     * interval. The interval is inclusive.
     * @param start the start date of the interval.
     * @param end the end date of the interval.
     * @return the number of Feb29 contained in the interval.
     */
    static public int numberOfFeb29(CDate start, CDate end) 
    {
	int count = 0;
	if (start.isLeapYear())  {
	    CDate feb29 = CDate.valueOf(start.getYear(), CDate.FEBRUARY, 29);
	    if (start.lte(feb29) && end.gte(feb29)) count++;
	}
	int year = start.getYear() + 1;
	while (year < end.getYear())  {
	    if (CDate.isLeapYear(year)) count++;
	    year++;
	}
	if ((start.getYear() != end.getYear()) && CDate.isLeapYear(end.getYear())) {
	    CDate feb29 = CDate.valueOf(end.getYear(), CDate.FEBRUARY, 29);
	    if (end.gte(feb29)) count++;
	}
	return count;
    }
			
    /**
     * Adds a number of months to the passed <code>CDate</code> 
     * and, if the passed date is the last day of the month,
     * ensures that the returned date is the last day of the month.
     * If the passed date is not the last day of the month, then
     * this mehtod works just like <code>addMonths()</code>.
     * 
     * @param  d    the <code>CDate</code> to which you wish to 
     * add months
     * @param  num  the integer number of months to add
     *
     * @return the resulting CDate, with months added
     */
    static public CDate addMonthsObserveEOM(CDate d, int num)
    {
	// If the date passed in is EOM, date returned is also EOM.
	// Otherwise is same as addMonths.
	if (d.getEOM().equals(d)) return d.getEOM(num);
	else return d.addMonths(num);
    }

    /**
     * Adds a number of months to the passed <code>CDate</code> 
     * and, if the passed date is the last business day of the month,
     * ensures that the returned date is the last day business of the month.
     * If the passed date is not the last business day of the month, then
     * this mehtod works just like <code>addMonths()</code>.
     * 
     * @param  d    the <code>CDate</code> to which you wish to 
     * add months
     * @param  num  the integer number of months to add
     *
     * @param holidays Vector containing holiday cities
     *
     * @return the resulting CDate, with months added
     */
    static public CDate addMonthsObserveEOM(CDate d, int num, Vector holidays)
    {
	// If the date passed in is EOM, date returned is also EOM.
	// Otherwise is same as addMonths.
	if (d.isEOM(holidays))  return d.getEOM(num);
	else return d.addMonths(num);
    }
    
    /**
     * calculates the date that easter falls on.
     * This algorithm is based on the algorithm 
     * of Oudin (1940) and quoted in"Explanatory Supplement to 
     * the Astronomical Almanac", P. Kenneth Seidelmann, editor.
     * @param year the year to calculate easter for
     * @return a {@link com.calypso.tk.core.CDate} that contains the date
     * for Easter.
     */
    static public CDate computeEaster(int year) {
	int century = year/100;
	int G = year % 19;
	int K = (century - 17)/25;
	int I = (century - century/4 - (century - K)/3 + 19*G + 15) % 30;
	I = I - (I/28)*(1 - (I/28)*(29/(I + 1))*((21 - G)/11));
	int J = (year + year/4 + I + 2 - century + century/4) % 7;
	int L = I - J;
	int EasterMonth = 3 + (L + 40)/44;
	int EasterDay = L + 28 - 31*(EasterMonth/4);
    
	return CDate.valueOf(year,EasterMonth,EasterDay);
    }

    /**
     * Returns a <code>Vector</code> containing all valid Strings that
     * can be used to represent a week day.
     * @return a <code>Vector</code> containing all valid Strings that
     * can be used to represent a week day.
     */
    static public Vector getWeekDayDomain() {
	Vector v = new Vector();
	v.addElement(new String(S_SUNDAY));
	v.addElement(new String(S_MONDAY));
	v.addElement(new String(S_TUESDAY));
	v.addElement(new String(S_WEDNESDAY));
	v.addElement(new String(S_THURSDAY));
	v.addElement(new String(S_FRIDAY));
	v.addElement(new String(S_SATURDAY));

	return v;
    }

    /**
     * Returns the String corresponding to the passed week day code.
     * @return the String corresponding to the passed week day code.
     */
    static public String getWeekDayString(int weekday){
	switch (weekday) {
	case CDate.SUNDAY:  return  S_SUNDAY;
	case CDate.MONDAY:  return S_MONDAY;
	case CDate.TUESDAY:  return S_TUESDAY;
	case CDate.WEDNESDAY:  return S_WEDNESDAY;
	case CDate.THURSDAY:  return S_THURSDAY;
	case CDate.FRIDAY:  return S_FRIDAY;
	case CDate.SATURDAY:  return S_SATURDAY;
	default : return null;
	}
    }

    /**
     * Returns the int code corresponding to the passed String representation of the week day.
     * @return the int code corresponding to the passed String representation of the week day.
     */
    static public int getWeekDayCode(String weekday) throws CosmosException {
	if (weekday.equals(S_SUNDAY)) return CDate.SUNDAY;
	else if (weekday.equals(S_MONDAY)) return CDate.MONDAY;
	else if (weekday.equals(S_TUESDAY)) return CDate.TUESDAY;
	else if (weekday.equals(S_WEDNESDAY)) return CDate.WEDNESDAY;
	else if (weekday.equals(S_THURSDAY)) return CDate.THURSDAY;
	else if (weekday.equals(S_FRIDAY)) return CDate.FRIDAY;
	else if (weekday.equals(S_SATURDAY)) return CDate.SATURDAY;
	else throw new CosmosException(weekday);
    }
    static public void main(String args[]) {	
	CDate d = CDate.getNow();
//	System.out.println("Today : " + d);
//	System.out.println("IMM Day 1 : " +  getIMMDate(d,3));
//	System.out.println("IMM Day 2 : " +  getIMMDate(d,4));
    }

    /*
     *  @deprecated
     */
    static public CDate getCDSDate(CDate d) {
	return getCDSDate(d, Frequency.QUARTERLY);
    }

    /**
     * Returns the following 3/20, 6/20, 9/20 or 12/20 for quarterly and
     * 3/20 and 9/20 for semi-annual after the specified date.
     * @param d the date which we will take the month and year.
     * @param frequency Frequency.QUARTERLY or Frequency.SEMI_ANNUAL
     * @return following 3/20, 6/20, 9/20 or 12/20 for quarterly and
     * 3/20 and 9/20 for semi-annual after the specified date.
     */
    static public CDate getCDSDate(CDate d, int frequency) {
	int mth = d.getMonth();
	int day = d.getDayOfMonth();
	int year = d.getYear();
	if (frequency == Frequency.QUARTERLY) {
	    if (mth < 3 || (mth == 3 && day <= 20)) mth = 3;
	    else if (mth < 6 || (mth == 6 && day <= 20)) mth = 6;
	    else if (mth < 9 || (mth == 9 && day <= 20)) mth = 9;
	    else if (mth < 12 || (mth == 12 && day <= 20)) mth = 12;
	    else {
		mth = 3;
		year++;
	    }
	} else if (frequency == Frequency.SEMI_ANNUAL) {
	    if (mth < 3 || (mth == 3 && day <= 20)) mth = 3;
	    else if (mth < 9 || (mth == 9 && day <= 20)) mth = 9;
	    else {
		mth = 3;
		year++;
	    }
	} else if (frequency == Frequency.MONTHLY) {
	    if (mth < 12 && day > 20) mth++;
            else if (mth == 12 && day > 20){
                mth = 1;
		year++;
	    }
	}
	else {
	   commonUTIL.display("UtilDate", "invalid arg: " + frequency);
	    return d;
	}
	return CDate.valueOf(year, mth, 20);
    }
    
    static public CDate getPrevCDSDate(CDate d, int frequency) {
    	return getPrevCDSDate(d, frequency,null,null);
    }
    /**
     * Returns the following 3/20, 6/20, 9/20 or 12/20 for quarterly and
     * 3/20 and 9/20 for semi-annual prior to the specified date.
     * @param d the date which we will take the month and year.
     * @param frequency Frequency.QUARTERLY or Frequency.SEMI_ANNUAL
     * @return following 3/20, 6/20, 9/20 or 12/20 for quarterly and
     * 3/20 and 9/20 for semi-annual prior to the specified date.
     */
    static public CDate getPrevCDSDate(CDate d, int frequency,RollDate dateRoll,Vector holidays) {
        if (d == null) return null;
        int mth = d.getMonth();
        int day = d.getDayOfMonth();
        int year = d.getYear();
        if (frequency == Frequency.QUARTERLY) {
            if (mth > 12 || (mth == 12 && day >= 20)) mth = 12; //dec 20-31 gives dec 20
            else if (mth > 9 || (mth == 9 && day >= 20)) mth = 9; //sep 20-dec19 gives sep 20
            else if (mth > 6 || (mth == 6 && day >= 20)) mth = 6; //jun 20-sep19 gives jun 20
            else if (mth > 3 || (mth == 3 && day >= 20)) mth = 3; //mar20-jun19 gives mar 20
            else {
                mth = 12;//jan1-mar19 gives dec20 of prior year
                year--;
            }
        } else if (frequency == Frequency.SEMI_ANNUAL) {
            if (mth > 9 || (mth == 9 && day >= 20)) mth = 9; //sep 20-dec31 gives sep 20
            else if (mth > 3 || (mth == 3 && day >= 20)) mth = 3; //mar20-sep19 gives mar 20
            else {
                mth = 9; //jan1-mar19 gives sep20 of prior year
                year--;
            }
        } else if (frequency == Frequency.MONTHLY) {
            if (mth > 1 && day < 20) mth--; //covers feb-dec 1-19 -> need prev month
                                            //nothing required for jan-dec 20-31 => same month.
            else if (mth == 1 && day < 20) {
                mth = 12;
                year--;
            }
        }
        else {
            commonUTIL.display("UtilDate", "invalid arg: " + frequency);
            return d;
        }
        if(dateRoll != null && !commonUTIL.isEmpty(holidays)){
        	return null;// ResetRateCalcUtil.rollDate(CDate.valueOf(year, mth, 20),dateRoll,holidays);
        }
        return CDate.valueOf(year, mth, 20);
    }

    /**
     * Finds the pairs of tenors in the passed list of Tenors that most closely enclose
     * the passed in number of days.  For example, if the tenorList contains 30, 60, 90, 180
     * and the passed in days is 45 then the enclosing tenors would be 30 and 60.
     * If there is no enclosing tenors for the given number of days passed in the method
     * will return the closest tenor.  For example, if number of days is 200 then the method
     * will return 180 in both elements of the return array.
     */
    static public Period[] findEnclosingTenors(int days, Vector tenorList) {
        Period[] result = new Period[2];

        if (tenorList.size() == 0)
            return result;
        if (tenorList.size() == 1) {
            result[0] = (Period)tenorList.elementAt(0);
            result[1] = (Period)tenorList.elementAt(0);
            return result;
        }

        int closestIdx = 0;
        int minDayDiff = -1;
        
        for (int i=0; i<tenorList.size(); i++) {
            int dayDiff = Math.abs(days - ((Period)tenorList.elementAt(i)).getCode());
            
            if (minDayDiff == -1) 
                minDayDiff = dayDiff;
            else if (dayDiff < minDayDiff) {
                minDayDiff = dayDiff;
                closestIdx = i;
            }
            else
                break;
        }

        if (((Period)tenorList.elementAt(closestIdx)).getCode() == days) {
            result[0] = (Period)tenorList.elementAt(closestIdx);
            result[1] = (Period)tenorList.elementAt(closestIdx);
            return result;
        }
        
        if (closestIdx == 0) {
            if (((Period)tenorList.elementAt(0)).getCode() > days) {
                result[0] = (Period)tenorList.elementAt(0);
                result[1] = (Period)tenorList.elementAt(0);
            }
            else {
                result[0] = (Period)tenorList.elementAt(0);
                result[1] = (Period)tenorList.elementAt(1);
            }
        }
        else if (closestIdx == tenorList.size() - 1) {
            if (((Period)tenorList.elementAt(closestIdx)).getCode() < days) {
                result[0] = (Period)tenorList.elementAt(closestIdx);
                result[1] = (Period)tenorList.elementAt(closestIdx);
            }
            else {
                result[0] = (Period)tenorList.elementAt(closestIdx-1);
                result[1] = (Period)tenorList.elementAt(closestIdx);
            }
        }
        else {
            if (((Period)tenorList.elementAt(closestIdx)).getCode() < days) {
                result[0] = (Period)tenorList.elementAt(closestIdx);
                result[1] = (Period)tenorList.elementAt(closestIdx+1);
            }
            else {
                result[0] = (Period)tenorList.elementAt(closestIdx-1);
                result[1] = (Period)tenorList.elementAt(closestIdx);
            }
        }
        
        return result;
    }
    
    /**
     *  Provides an approximate stub period end date given the passed in start date,
     *  end date, length of each period (tenor) and period date roll.  The result
     *  returned is only an approximate as it does not take into consideration
     *  of holidays, roll day and other information.
     *  
     *  @return CDate approx stub period end date.
     *  @deprecated As of 6.0 use {@link com.calypso.tk.product.util.PeriodGenerator PeriodGenerator}
     */
    static public CDate calcApproxStubDate(CDate startDate, CDate endDate,
                                           Period tenor, RollDate periodDateRoll) {
        int period = tenor.getCode();
        if (period == 0) {
            return startDate;
        }
        
        // Added special handling for SFE, IMM_MON and IMM_WED, MONTH_END date roll.
        // For those date rolls it is necessary to check the rolled date
        // for stub.  For example 2/10/02 - 2/10/04 with frq = QTR would result
        // in a stub if any of the above daterolls is used.
        // We will roll the test date with the passed date roll and an empty holiday list.
        // An empty holiay list is sufficient here as the method attempts to provide a 'guess'
        // of whether a stub exist.  


        CDate date=endDate;
        Vector v = new Vector();
        int i=0;

        while (endDate.substractTenor(period*(i+1)).after(startDate)) {
            i++;
	}

        date = periodDateRoll.roll(endDate.substractTenor(period*i), v);

        // Special handling for SFE - bug 6477
        if (periodDateRoll.getCode() == RollDate.SFE) {
            CDate pDate = periodDateRoll.roll(endDate.substractTenor(period*i+1), v);
            if (pDate.after(startDate))
                date = pDate;
        }


        return date;
    }
    
    /**
     * 
     * @deprecated Since 6.0 Use PeriodGenerator().hasStubPeriod() instead.
     * This version of the method does not handle structures with date roll IMM, SFE and
     * MTH_END.
     */
    static public int hasStub(CDate startDate, CDate endDate,
                              Period tenor, RollDate periodDateRoll,
                              int daysTolerance) {
        
        CDate date = calcApproxStubDate(startDate, endDate, tenor, periodDateRoll);

        int period = tenor.getCode();
        int nbdays=(int)CDate.diff(startDate, date);
        if(nbdays==0) return 0;

         CDate cmpDate = date.substractTenor(period);
         boolean hasStub = (Math.abs(CDate.diff(date, startDate)) > daysTolerance) &&
             (Math.abs(CDate.diff(cmpDate, startDate)) > daysTolerance);

        int result = 0;
        if (hasStub) {
            int stubLength = CountDay.get(CountDay.DC_30_360).dayDiff(startDate, date);
            result = ((stubLength < period) ? -1 : 1);
        }
        return result;
    }

    /**
     * @deprecated Since 5.56 Use hasStub(CDate, CDate, Period, RollDate, int) instead.
     * This version of the method does not handle structures with date roll IMM, SFE and
     * MTH_END.
     */
    static public boolean hasStub(CDate startDate, CDate endDate,
				  Period tenor,int daysTolerance) {
	// Old Code
	//Did not handle Monthly
	/*
	if (tenor.getCode() < 30) return false;

	int period= - tenor.getCode()/30;
	CDate date=endDate;
	while(date.addMonths(period).after(startDate)) {
	    date=date.addMonths(period);
	}
	int nbdays=(int)CDate.diff(startDate, date);
	if(nbdays==0) return false;

	date = date.addMonths(period);
	int newnbdays=(int)CDate.diff(date, startDate);
	if(newnbdays==0) return false;
	nbdays=Math.min(nbdays,newnbdays);

	return (nbdays > daysTolerance);
	*/	
    int period = tenor.getCode();
    if (period == 0) {
        return false;
    }

    CDate date=endDate;
	while(date.substractTenor(period).after(startDate)) {
	    date=date.substractTenor(period);
	}
	int nbdays=(int)CDate.diff(startDate, date);
	if(nbdays==0) return false;
	date = date.substractTenor(period);
	int newnbdays=(int)CDate.diff(date, startDate);
	if(newnbdays==0) return false;
	nbdays=Math.min(nbdays,newnbdays);
	return (nbdays > daysTolerance);
    }
    
    /**
     * Get the previous business date time using the given time ("today") as the reference
     * time.  "eodTime" is end of day time expressed as a string (1700 for 5pm), and tz is
     * the time zone.  The previous business date is constructed using the following 
     * algorithm:
     * 
     *     1. Use the given time ("today") to construct the time during today where 
     *        it becomes the eod of day.  For example, if today is 2/7/08 18:00:00, 
     *        and the "eodTime" is 1700, the end of business date time
     *        for today is 2/7/08 17:00:00.
     *     2. Now compare "today" with the eod date time for today.  If today is after
     *        the end of day for today, we're technically already on the next business day, 
     *        and the previous day is considered the eod time for today or 2/7/08 17:00:00.
     *        
     *        If today is before the end of day for today, then the day before today is 
     *        truly the previous calendar day at 1700.  So if today is 2/7/08 15:30, then
     *        the previous day is considered to be 2/6/08 17:00.
     *        
     * @param today a <code>CDateTime</code> specifying the reference date time.
     * @param tz a <code>TimeZone</code> indicating time zone to use in date and time calculations.
     * @param eodTime a <code>String</code> used to express the end of day time. (e.g. 170002 which 
     * will be interpreted as 5:00:02 PM). 
     * @param holidays a <code>Vector</code> of holidays to consider when calculating the 
     * business day.
     * 
     * @return The previous business day, taking into consideration the end of day time and 
     * the holidays. With the end of day time, the previous business day may be on the same
     * day as "today".
     */
    static public CDateTime getPrevBusDatetime(CDateTime today, TimeZone tz, String eodTime, Vector holidays, boolean alwaysRollB) {
    
		CDate todayDate = today.getJDate(tz); // this is correct. We do not need to do env.getJDate(CDateTime) here 
		CDateTime todayEndOfBusDatetime =  convertToJDatetime(todayDate, eodTime, tz);
		// TODO: Original stmt = 
		// boolean afterEndOfBusinessDayButStillCurrentDay = now.after(endOfBusinessDayDatetime) && valDate.equals(endOfBusinessDayDate);
		// may not need (&& valDate.equals(endOfBusinessDayDate)).
		boolean afterEndOfBusinessDayButStillCurrentDay = today.after(todayEndOfBusDatetime);

		CDate yesterdayDate = null;
		if(afterEndOfBusinessDayButStillCurrentDay && !alwaysRollB) {
			// for example if eob is defined in fx spot blotter parameters as 17:00:00 
			// and right now is 10/28/2007 17:00:01 then we should return 10/28/2007 17:00:00 timestamp 
			// to load positions for "yesterday"
			// the similar logic applies for month end and year end.
			yesterdayDate = todayDate;
		}
		else {
			yesterdayDate = todayDate.addDays(-1);
		}

		if (!Holiday.getCurrent().isBusinessDay(yesterdayDate, holidays))
			yesterdayDate = RollDate.R_PRECEDING.roll(yesterdayDate, holidays);

		CDateTime yesterdayDatetime =  convertToJDatetime(yesterdayDate, eodTime, tz);
		return yesterdayDatetime;
    }
    public static CDateTime convertToJDatetime(CDate valDate, String time, TimeZone tz){
		int hour = 0;
		int min = 0;
		int sec = 0;
		time = time.trim();
		if (time.length() == 4) {
			// time is in the format of HHMM
			int timeNumber = commonUTIL.stringToInteger(time);
			hour = timeNumber / 100;
			min = timeNumber % 100;
		} else if (time.length() == 6) {
			// time is in the format of HHMMSS
			int timeNumber = commonUTIL.stringToInteger(time);
			hour = timeNumber  / 10000;
			min = (timeNumber - hour * 10000) / 100;
			sec = timeNumber - hour * 10000 - min * 100;
		} else {
			// if time format is not recognized we raise an exception. Otherwise
			// bucket times are defaulted to midnight and position data becomes unusable.
			throw new IllegalArgumentException("Cannot recognize time format for string \'" + time + "\'. " +
					"Currently two formats are supported HHMM and HHMMSS. " +
					"Please verify your settings in admin monitor/engine thread " +
					"for position engine in column VALUATION_TIMES");
		}
	    return new CDateTime(valDate,
	                             hour,
	                             min,
	                             sec,
	                             0,
	                             tz);
    }
    /**
     * Get the last business date time of the previous month using the given time ("today") 
     * as the reference time.  "eodTime" is end of day time expressed as a string (1700 for 5pm), 
     * and tz is the time zone.   
     * 
     * @param today a <code>CDateTime</code> specifying the reference date time.
     * @param tz a <code>TimeZone</code> indicating time zone to use in date and time calculations.
     * @param eodTime a <code>String</code> used to express the end of day time. (e.g. 170002 which 
     * will be interpreted as 5:00:02 PM). 
     * @param holidays a <code>Vector</code> of holidays to consider when calculating the 
     * business day.
     */
    static public CDateTime getLastBusDatetimeOfPrevMonth(CDateTime today, TimeZone tz, String eodTime, Vector holidays) {
		CDate todayDate = today.getJDate(tz); // this is correct. We do not need to do env.getJDate(CDateTime) here 
		CDateTime todayEndOfBusDay =  convertToJDatetime(todayDate, eodTime, tz);
		boolean afterEndOfBusinessDayButStillCurrentDay = today.after(todayEndOfBusDay);
		
		CDate lastBusDateOfPrevMonth = null;
		if(afterEndOfBusinessDayButStillCurrentDay && todayDate.isEOM(holidays)) {
			lastBusDateOfPrevMonth = todayDate;
		}
		else {
			lastBusDateOfPrevMonth = todayDate.addDays(-todayDate.getDayOfMonth());
		}
		
		if (!Holiday.getCurrent().isBusinessDay(lastBusDateOfPrevMonth, holidays))
			lastBusDateOfPrevMonth = RollDate.R_PRECEDING.roll(lastBusDateOfPrevMonth, holidays);

		CDateTime lastBusDatetimeOfPrevMonth =  convertToJDatetime(lastBusDateOfPrevMonth, eodTime, tz);
		return lastBusDatetimeOfPrevMonth;

    }
    
	/**
	 * Get the previous business date time using the given time ("today") as the
	 * reference time. "eodTime" is end of day time expressed as a string (1700
	 * for 5pm), and tz is the time zone.
	 * 
	 * @param today
	 *            a <code>CDateTime</code> specifying the reference date time.
	 * @param tz
	 *            a <code>TimeZone</code> indicating time zone to use in date
	 *            and time calculations.
	 * @param eodTime
	 *            a <code>String</code> used to express the end of day time.
	 *            (e.g. 170002 which will be interpreted as 5:00:02 PM).
	 * @param holidays
	 *            a <code>Vector</code> of holidays to consider when calculating
	 *            the business day.
	 * 
	 * @return The previous business day, taking into consideration the end of
	 *         day time and the holidays. By default, the previous business day 
	 *         may be the same date as today (for backward compatibility).
	 */
	static public CDateTime getPrevBusDatetime(CDateTime today, TimeZone tz,
			String eodTime, Vector holidays) {
		return getPrevBusDatetime(today, tz, eodTime, holidays, false);  
	}
	

    
    /**
     * Get the last business date time of the previous year using the given time ("today") 
     * as the reference time.  "eodTime" is end of day time expressed as a string (1700 for 5pm), 
     * and tz is the time zone.   The beginning of the year may not always be 1/1 of the 
     * calendar year.  If not, yearStartMonth/yearStartDay is the way in which the user can 
     * specify a different beginning of the year.
     * 
     * @param today a <code>CDateTime</code> specifying the reference date time.
     * @param tz a <code>TimeZone</code> indicating time zone to use in date and time calculations.
     * @param eodTime a <code>String</code> used to express the end of day time. (e.g. 170002 which 
     * will be interpreted as 5:00:02 PM). 
     * @param yearStartMonth an <code>int</code> specifying the month considered the beginning of
     * the year.  If -1, use 1 (for January).  Months are specified as 1 (January) thru 12 (December). 
     * @param yearStartDay an <code>int</code> specifying the day of the month. Valid values are 1 thru 
     * 31, if -1, use 1. 
     * @param holidays a <code>Vector</code> of holidays to consider when calculating the 
     * business day.
     */
    static public CDateTime getLastBusDatetimeOfPrevYear(CDateTime today, TimeZone tz, String eodTime, int yearStartDay, int yearStartMonth, Vector holidays) {
		CDate todayDate = today.getJDate(tz); // this is correct. We do not need to do env.getJDate(CDateTime) here 
		CDateTime todayEndOfBusDay =  convertToJDatetime(todayDate, eodTime, tz);
		boolean afterEndOfBusinessDayButStillCurrentDay = today.after(todayEndOfBusDay);
		
		yearStartDay   = ((yearStartDay >0) ? yearStartDay : 1);
		yearStartMonth = ((yearStartMonth > 0) ? yearStartMonth : 1);
		
		CDate lastBusinessDayOfThePrevYear = getLastBusinessDayOfThePrevYear(todayDate, yearStartDay, yearStartMonth, holidays);
		CDate lastBusinessDayOfTheCurrentYear = getLastBusinessDayOfTheCurrentYear(todayDate, yearStartDay, yearStartMonth, holidays);
		boolean isLastBusinessDayOfTheCurrentYear = todayDate.equals(lastBusinessDayOfTheCurrentYear);
		
		CDate lastBusinessDateOfThePrevYear = null;
		if(afterEndOfBusinessDayButStillCurrentDay && isLastBusinessDayOfTheCurrentYear){
			lastBusinessDateOfThePrevYear = todayDate;
		} else {
			lastBusinessDateOfThePrevYear = lastBusinessDayOfThePrevYear; 
		}
		if (!Holiday.getCurrent().isBusinessDay(lastBusinessDateOfThePrevYear, holidays))
			lastBusinessDateOfThePrevYear = RollDate.R_PRECEDING.roll(lastBusinessDateOfThePrevYear, holidays);
		CDateTime lastBusDatetimeOfPrevYear = convertToJDatetime(lastBusinessDateOfThePrevYear, eodTime, tz);

		return lastBusDatetimeOfPrevYear;

    }
    
	static protected CDate getLastBusinessDayOfTheCurrentYear(CDate todaysDate, int yearEndDay, int yearEndMonth, Vector holidays){
        Holiday hol = Holiday.getCurrent();
		int year = todaysDate.getYear(); // get the current year
		CDate startOfTheYearDate = CDate.valueOf(year, yearEndMonth, yearEndDay);
		if(startOfTheYearDate.lte(todaysDate)){
			startOfTheYearDate = startOfTheYearDate.addYears(+1);
		}
        CDate lastBusinessDayOfThePrevYear = hol.previousBusinessDay(startOfTheYearDate, holidays);
		return lastBusinessDayOfThePrevYear;
	}
    
	static protected CDate getLastBusinessDayOfThePrevYear(CDate todaysDate, int yearEndDay, int yearEndMonth, Vector holidays){
        Holiday hol = Holiday.getCurrent();
		int year = todaysDate.getYear(); // get the current year
		CDate startOfTheYearDate = CDate.valueOf(year, yearEndMonth, yearEndDay);
		if(startOfTheYearDate.after(todaysDate)){
			startOfTheYearDate = startOfTheYearDate.addYears(-1);
		}
        CDate lastBusinessDayOfThePrevYear = hol.previousBusinessDay(startOfTheYearDate, holidays);
		return lastBusinessDayOfThePrevYear;
	}

    /**
     * Returns the third Wednesday of March, June, September, December
     * after the specified date.
     * @param d the date after which the quarterly IMM date will be returned
     * @return the third Wednesday of March, June, September, December
     * after the specified date.
     */
    static public CDate getNextQuarterlyIMMDate(CDate d) {
	// "quarterly month" = March, June, September, December (3, 6, 9, 12)
	// Wednesday is weekday #4
	int year = d.getYear();
	int mth = d.getMonth();

	// if we are on a quarterly month
	if (mth == 3 || mth == 6 || mth == 9 || mth == 12) {
	    CDate thisMonthsThirdWed = getIMMDate(d, 4);
	    if (thisMonthsThirdWed.after(d)) {
		// and before the 3rd Wed, then return this month's 3rd Wed
		return thisMonthsThirdWed;
	    }
	    else {
		// otherwise return the next quarterly month's 3rd Wed
		mth = mth + 3;
		if (mth > 12) {
		    mth = 3;
		    year++;
		}
		return getJDate(year, mth, 4, 3);
	    }
	}
	// so we are not on a quarterly month -> go to the next quarterly month
	if (mth < 3) mth = 3;
	else if (mth < 6) mth = 6;
	else if (mth < 9) mth = 9;
	else mth = 12;
	return getJDate(year, mth, 4, 3);
    }
    
}
