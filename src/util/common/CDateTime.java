package src.util.common;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Vector; 

import util.commonUTIL;
import util.common.holiday.Holiday;
/**
 * Cosmos implementation of the standard java Date class that adds
 * methods to provide some functionality parallel to that of <code>
 * CDate</code>.
 * <p>
 * A <code>CDatetime</code> can be converted to a <code>CDate</code>s
 * using the <code>getJDate()</code> method. <code>Date</code>s
 * and <code>CDate</code>s can be converted to <code>
 * CDateTime</code>s using the appropriate <code>CDateTime</code>
 * constructors. When you pass a <code>CDate</code> to the
 * constructor, the timestamp of this CDateTime will be set to
 * zero hours local time.
 */
public class CDateTime extends Date implements Externalizable {
	
	  private static GregorianCalendar _staticCal= new GregorianCalendar();
	    private static int _calendarCount=0;
	    private static int _calendarMax=100000;

	    /**
	 * Allocates a new <code>CDateTime</code> object and sets it to the
	 * date and time of the current date and time
	 *
	 *
	 */
	    public CDateTime() {
	        /** use the (potentially) artificial today's date */
	        CDate today = CDate.getNow();
	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.YEAR, today.getYear());
	        cal.set(Calendar.MONTH, today.getMonth() - 1);
	        cal.set(Calendar.DATE, today.getDayOfMonth());
	        setTime(cal.getTime().getTime());
	    }


	    public boolean equals(Object otherObj)
	    {
	    	if(otherObj!=null && otherObj instanceof CDateTime)
	    	{
	    		CDateTime otherDT=(CDateTime)otherObj;
	    		return(compareTo(otherDT)==0);
	    	}
	    	return false;
	    }
	    
	    /**
	     * Allocates a new <code>CDateTime</code> object and sets it to the
	     * date and time of the passed <code>java.util.Date</code>.
	     *
	     * @param  d  a <code>java.util.Date</code> indicating the
	     *            date and time to which this <code>CDate</code>
	     *            should be set
	     */
	    public CDateTime(Date d)
	    {
	        super(d.getTime());
	    }
	    /**
		 * Allocates a CDateTime object and initializes it to represent the
		 * specified number of milliseconds since the standard base time
		 * known as "the epoch", namely January 1, 1970, 00:00:00 GMT.
		 *
		 * @param  time the milliseconds since January 1, 1970, 00:00:00 GMT
	     */
	    public CDateTime(long time)
	    {
	        super(time);
	    }

	    /**
	     * Allocates a new <code>CDateTime</code> object and sets it to
	     * the date of the passed <code>CDate</code>.  The timestamp of
	     * this CDateTime will be set to zero hours local time.
	     *
	     * @param  d  a <code>CDate</code> indicating the
	     *            date to which this <code>CDate</code>
	     *            should be set.
	     */

	    public CDateTime(CDate d,TimeZone tz)
	    {
	        super(d.getDate(tz).getTime());
	    }

	    /**
	     * Returns a <code>CDate</code> equivalent to the date portion
	     * of this <code>CDateTime</code>. (The timestamp is ignored.)
	     *
	     * @return  the <code>CDate</code> equivalent to the date portion
	     * of this <code>CDateTime</code>
	     */


	     public final CDate getJDate(TimeZone tz)
	    {
	        return CDate.valueOf(this,tz);
	    }

	    /**
	     * Returns True if this objects Date and time is less than
	     * or equal to the passed objects Date and time. OtherWise
	     * returns false.
	     * @param other A <CDateTime> object to compare.
	     * @return a Boolean true or false.
	     */

	    final public boolean lte(CDateTime other) {
		return getTime() <= other.getTime();
	    }

	    /**
		 * Returns True if this objects date and time is greater than
		 * or equal to the passed objects Date and time. OtherWise
		 * returns false.
		 * @param other A <CDateTime> object to compare.
		 * @return a Boolean true or false.
	     */

	    final public boolean gte(CDateTime other) {
		return getTime() >= other.getTime();
	    }

	    /**
		* Returns a string representation of this Date.
		*
		* @param timeZone a TimeZone used for the conversion
		* @return a string representation of this Date.
		*
		*/

	    public String format(TimeZone timeZone) {
		//return Util.datetimeToString(this);
		return commonUTIL.timestampToString2(this, timeZone);   
	    }

	    /**
	     * Returns a string representation of this Date.
	     *
	     * @return a string representation of this Date.
	     *
	     */
	    public String toString() {
	        return format(TimeZone.getDefault());
	    }
	    
	    /**
	     * Returns a <code>CDateTime</code> instance representing a date and time
	     * initialized to the string value passed as parameter. This method is
	     * intended to be the reciprocal of the <code>toString()</code> method.
	     * @see CDateTime#toString()
	     * @param s a date and time string representation.
	     * @return the corresponding CDateTime.
	     */
	    public static CDateTime valueOf(String s) {
	        return commonUTIL.stringToJDatetime2(s);
	    }
	    
	    /**
	     * Returns the value of this <code>CDateTime</code> converted in the given unit.
	     * Currenlty the supported units are <code>Calendar.MILLISECOND</code>,
	     * <code>Calendar.SECOND</code>, <code>Calendar.MINUTE</code>,
	     * <code>Calendar.HOUR</code> and <code>Calendar.DATE</code>.
	     * The result is rounded down to an integer.
	     * @see java.util.Calendar
	     * @param unit a time unit.
	     * @return the converted value.
	     */
	    public long valueIn(int unit) {
	        return valueIn(unit, NumberRoundingMethod.RM_DOWN);
	    }
	    
	    /**
	     * Returns the value of this <code>CDateTime</code> converted in the given unit.
	     * Currenlty the supported units are <code>Calendar.MILLISECOND</code>,
	     * <code>Calendar.SECOND</code>, <code>Calendar.MINUTE</code>,
	     * <code>Calendar.HOUR</code> and <code>Calendar.DATE</code>.
	     * The result is rounded using the provided rounding method
	     * (e.g. <code>NumberRoundingMethod.RM_DOWN</code>,
	     * <code>NumberRoundingMethod.RM_NEAREST</code>,
	     * <code>NumberRoundingMethod.RM_UP</code> etc.).
	     * @see java.util.Calendar
	     * @see com.calypso.tk.core.NumberRoundingMethod
	     * @param unit a time unit.
	     * @param rounding a rounding method.
	     * @return the converted value.
	     */
	    public long valueIn(int unit, int rounding) {
	        long factor = 1;
	        switch (unit) {
	            case Calendar.DATE:
	                factor *= 24;
	            case Calendar.HOUR:
	                factor *= 60;
	            case Calendar.MINUTE:
	                factor *= 60;
	            case Calendar.SECOND:
	                factor *= 1000;
	            case Calendar.MILLISECOND:
	                break;
	            default:
	                return 0;
	        }
	        return (long)NumberRoundingMethod.round((double)getTime() / factor, 0, rounding);
	    }

	    /**
		* Allocates a new <Code>JDateTime</code> and sets
		* its Year,Month and Day of the month portions to that
		* of the passed {@link CDate}, it also sets HOUR_OF_DAY,
		* MINUTE and SECOND fields.
		* @param jdate a CDate
		* @param hour  an hour
		* @param minute a minute
		* @param second a second
		*
		*/
	    public CDateTime(CDate jdate,
			     int hour,int minute,int second,TimeZone ts) {
		
		GregorianCalendar cal = getCalendar();
		synchronized(cal) {	
		    if(ts != null) cal.setTimeZone(ts);
		    else cal.setTimeZone(TimeZone.getDefault());
		    cal.set(Calendar.YEAR,jdate.getYear());
		    cal.set(Calendar.MONTH,jdate.getMonth()-1);
		    cal.set(Calendar.DATE,jdate.getDayOfMonth());
		    cal.set(Calendar.HOUR_OF_DAY,hour);
		    cal.set(Calendar.MINUTE,minute);
		    cal.set(Calendar.SECOND,second);
		    cal.set(Calendar.MILLISECOND,999);
		    setTime(cal.getTime().getTime());
		}
		}
		/**
		 * Allocates a new <Code>JDateTime</code> and sets
		 * its Year,Month and Day of the month portions to that
		 * of the passed {@link CDate}, it also sets HOUR_OF_DAY,
		 * MINUTE, SECOND and MILLISECOND fields.
		 * @param jdate a CDate
		 * @param hour  an hour
		 * @param minute a minute
		 * @param second a second
		 * @param millis a millisecond
		 */

	    public CDateTime(CDate jdate,
			     int hour,int minute,int second,int millis,TimeZone ts) {
		GregorianCalendar cal = getCalendar();
		synchronized(cal) {	
		    if(ts != null) cal.setTimeZone(ts);
		    else cal.setTimeZone(TimeZone.getDefault());
		    cal.set(Calendar.YEAR,jdate.getYear());
		    cal.set(Calendar.MONTH,jdate.getMonth()-1);
		    cal.set(Calendar.DATE,jdate.getDayOfMonth());
		    cal.set(Calendar.HOUR_OF_DAY,hour);
		    cal.set(Calendar.MINUTE,minute);
		    cal.set(Calendar.SECOND,second);
		    cal.set(Calendar.MILLISECOND,millis);
		    setTime(cal.getTime().getTime());
		}
	    }

	    static public void main(String args[]) {
		CDate date = CDate.getNow();

		TimeZone tz=TimeZone.getTimeZone("America/New_York");
	    }
	    public void writeExternal(ObjectOutput out)
		throws IOException {
		out.writeLong(getTime());
	    }
	    public void readExternal(ObjectInput in)
		throws IOException,ClassNotFoundException {
		setTime(in.readLong());
	    }

		/**
		* Adds the specified number of days, hours, minutes,
		* seconds and milliseconds to this <Code>CDateTime</Code>
		* object.
		* @param days The number of Days
		* @param hours The number of hours
		* @param minutes The number of minutes
		* @param seconds The number of seconds
		* @param millis The number of milliseconds
		* @return a New JDateTime Object.
		*/

	    final public CDateTime add(int days,
				       int hours,int minutes,int seconds,
				       int millis) {	
		GregorianCalendar cal = getCalendar();
		synchronized(cal) {	
		    cal.setTimeZone(TimeZone.getDefault());
		    cal.setTime(this);
		    cal.add(Calendar.DATE,days);
		    cal.add(Calendar.HOUR,hours);
		    cal.add(Calendar.MINUTE,minutes);
		    cal.add(Calendar.SECOND,seconds);
		    cal.add(Calendar.MILLISECOND,millis);
		    return new CDateTime(cal.getTime().getTime());
		}
	    }

		/**
		* Add a specified number of days,hours and minutes to a
		* date taking into account business days and holidays.
		*
		* @param dt A JDateTime to adjust.
		* @param days The number of days to adjust by.
		* @param hours The number of hours to adjust by.
		* @param minutes The number of minutes to adjust by.
		* @param holidays A vector of holiday centers. For Example
		* 'NYC' for New York holidays.
		* @param tz A <Code>TimeZone</Code> object.
		* @return A <Code>CDateTime</code> adjusted in accordance with the
		* specified parameters.
		*/
	    final public CDateTime addBusiness(CDateTime dt , int days,
					       int hours,int minutes,
					       Vector holidays , TimeZone tz ) {
		
		if (holidays != null) {
		    Holiday hol = Holiday.getCurrent();
		    CDate d = dt.getJDate(tz);
		    CDate newDate = d;
		    if (days != 0)
			newDate = hol.addBusinessDays(newDate,holidays,days,false);
		    days = (int)CDate.diff(d,newDate);
		}        


		CDateTime dtt = dt.add(days, hours, minutes,0,0);
		CDate date = dtt.getJDate(tz);
		CDate newDate =null;
		int newDays=0;
		
		int sign = (dtt.before(dt)) ? -1: 1;
		
		//if(Log.isCategoryLogged(Log.OLD_TRACE))
		//System.out.println("In " + dt + " days " + dtt +
		//" " + date);
		CDate origDate=date;
		if (holidays != null) {
		    Holiday hol = Holiday.getCurrent();
		    if(!hol.isBusinessDay(date,holidays)) {
		    	if(sign < 0) 
		    		newDate= hol.previousBusinessDay(date, holidays);
		    	else 
		    		newDate= hol.nextBusinessDay(date, holidays);
		    	newDays=(int)CDate.diff(date,newDate);
		    	if(newDays != 0) 
		    		dtt=dtt.add(newDays,0,0,0,0);
		    }
		}
		//Log.system("JDATETIME","addBusinessDaye " +
		//dt + " Found " + dtt + " " + origDate +
		//" " + newDays + " " + holidays,null);	
		return  dtt;
	    }

		/**
		*Adds a number of milliseconds to a CDateTime object.
		*@param millis The number of milliseconds to adjust by.
		*@return A CDateTime object adjusted by the specified number of milliseconds.
		*/

	    final public CDateTime add(int millis) {
		GregorianCalendar cal = getCalendar();
		synchronized(cal) {	
		    cal.setTimeZone(TimeZone.getDefault());
		    cal.setTime(this);
		    cal.add(Calendar.MILLISECOND,millis);
		    return new CDateTime(cal.getTime().getTime());
		}
	    }

	    /**
		* Returns true if the year portion of the date is greater than
		* the current year plus 1000 or less than the current year minus 1000
		*@return  A boolean true or false.
		*/
	    public boolean isBad() {
		return getJDate(null).isBad();
	    }
	    /**
	     * deprecated
	     */
	    public CDateTime(CDate d)
	    {
		super(d.getDate().getTime());
	    }
	    /**
	     * deprecated
	     */
	    public final CDate getJDate()
	    {
		return CDate.valueOf(this,null);
	    }
	    
	    /**
	     * Add a specified number of days,hours and minutes to a
	     * date taking into account business days and holidays.
	     *
	     * 
	     * The logic is the following:
	     *
	      * <ul>
	     * <li>The Date time is converted into a CDate using the 
	     * Time Zone specified in the parameter.
	     * <li>If Holidays is set, the Date is adjusted based on the RollDate
	     * convention if the Date falls on a Holiday.
	     * <li>The number of business days are added to the Date, and then
	     * the hours and minutes.
	     * <li> If the computed date falls on a Holiday due to the addition
	     * of Hours and Minutes, the date is adjusted with the FOLLOWING convention
	     * if we add Hours/Minutes and adjusted with the PRECEEDING convention
	     * if we substract Hours/Minute.     
	     * </ul>
	     *
	     * @param dt    the CDateTime to be used as reference.
	     * @param days  the number of days (signed)to add.
	     * @param hours the number of hours (signed) to add.
	     * @param minutes the number of minutes (signed) to add.
	     * @param holidays a Vector of holidays.
	     * @param tz      Time Zone in which the date must be expressed.
	     * @param dateRoll RollDate convention.
	     *
	     * @return a CDateTime.
	     */
	    static public CDateTime addBusiness(CDateTime dt , int days,
					       int hours,int minutes,
						Vector holidays , TimeZone tz,
						RollDate dateRoll) {
		Holiday hol = Holiday.getCurrent();
		CDate origDate= dt.getJDate(tz);
		CDate adjustedDate = origDate;
		if (dateRoll == null) dateRoll = RollDate.R_NONE;
		//Roll if Holidays was specified
		//Note that holidays can be an empty vector
		if(holidays != null) 
		    adjustedDate=dateRoll.roll(adjustedDate,holidays);
		//Now add/substract Business Days
		if(days != 0) {
		    if(holidays != null)
			adjustedDate  = hol.addBusinessDays(adjustedDate,
							    holidays,days,
							    days > 0 ?
							    true : false);
		    else adjustedDate=adjustedDate.addDays(days);
		}
		int daysDiff= (int)CDate.diff(origDate,adjustedDate);
		//Compute Datetime
		CDateTime dtt = dt.add(daysDiff, hours, minutes,0,0);
		CDate date = dtt.getJDate(tz);
		if(holidays  != null) {
		    CDate newDate = date;
		    while(!hol.isBusinessDay(date,holidays)) {
			if (hours < 0 || minutes < 0)
			    newDate=RollDate.R_PRECEDING.roll(date,holidays);
			else
			    newDate=RollDate.R_FOLLOWING.roll(date,holidays);
			int newDays=(int)CDate.diff(date,newDate);
			if(newDays == 0) break;
			dtt=dtt.add(newDays,0,0,0,0);
			date = dtt.getJDate(tz);
		    }	
		}	
		return  dtt;
	    }

	    private static synchronized GregorianCalendar getCalendar() {
	        _calendarCount++;
	        if(_calendarCount==_calendarMax) {
	            _staticCal= new GregorianCalendar();
	            _calendarCount=0;
	        }
	        return _staticCal;
	    }
	    
	    /**
	     * Get the value in the default time zone of
	     * a field in this CDateTime. The field is
	     * designated by it's integer id as defined
	     * in the Calendar class.
	     *
	     * @param field  the id of the CDateTime's field requested.
	     *
	     * @return the value of the CDateTime's field requested.
	     * 
	     * @see java.util.Calendar
	     */
	    final public int getField(int field) {
	        return getField(field, null);
	    }
	    
	    /**
	     * Get the value in the provided time zone of
	     * a field in this CDateTime. The field is
	     * designated by it's integer id as defined
	     * in the Calendar class.
	     *
	     * @param field  the id of the CDateTime's field requested.
	     * @param tz     Time Zone in which the date must be expressed.
	     *
	     * @return the value of the CDateTime's field requested.
	     * 
	     * @see java.util.Calendar
	     */
	    final public int getField(int field, TimeZone tz) {
	        GregorianCalendar cal = getCalendar();
	        synchronized(cal) {
	            if (tz == null)
	                cal.setTimeZone(TimeZone.getDefault());
	            else
	                cal.setTimeZone(tz);
	            cal.setTime(this);
	            return cal.get(field);
	        }
	    }
	    
	    /**
	     * Get the value in the default time zone of
	     * some fields in this CDateTime. The fields are
	     * designated by integer ids as defined in the
	     * Calendar class. These ids are provided in a
	     * container array where the will be replaced
	     * by there corresponding values.
	     *
	     * @param fields the ids of the CDateTime's fields requested.
	     *
	     * @see java.util.Calendar
	     */
	    final public void getFields(int[] fields) {
	        getFields(fields, null);
	    }
	        
	    /**
	     * Get the value in the provided time zone of
	     * some fields in this CDateTime. The fields are
	     * designated by integer ids as defined in the
	     * Calendar class. These ids are provided in a
	     * container array where the will be replaced
	     * by there corresponding values.
	     *
	     * @param fields the ids of the CDateTime's fields requested.
	     * @param tz     Time Zone in which the date must be expressed.
	     *
	     * @see java.util.Calendar
	     */
	    final public void getFields(int[] fields, TimeZone tz) {
	        GregorianCalendar cal = getCalendar();
	        synchronized(cal) { 
	            if (tz == null)
	                cal.setTimeZone(TimeZone.getDefault());
	            else
	                cal.setTimeZone(tz);
	            cal.setTime(this);
	            for (int i = 0; i < fields.length; i++)
	                fields[i] = cal.get(fields[i]);
	        }
	    }

	    /**
	    * Compares this CDateTime to another and returns
	    * true if this CDateTime is less than the passed one.
	    * Also does the null checking.
	    * @param date a CDateTime to be compared to
	    * @return True or False
	    */

	    final public boolean before(CDateTime date) {
		if (date==null)return false;
		return super.before(date);
	    }
	    /**
		 * Compares this CDateTime to another and returns
		 * true if this CDateTime is greater than the passed one.
		 * Also does the null checking.
		 * @param date a CDateTime to be compared to
		 * @return True or False
		 */

	    final public boolean after(CDateTime date) {
		if (date==null) return false;
		return super.after(date);
	    }

	    final public CDateTime cleanMillis() {
		
		GregorianCalendar cal = getCalendar();
		synchronized(cal) {	
		    cal.setTimeZone(TimeZone.getDefault());
		    cal.setTime(this);	    
		    cal.set(Calendar.MILLISECOND,0);	 
		    return new CDateTime(cal.getTime().getTime());
		}
	    }


}
