package src.util.common;


import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector; 
    

import util.commonUTIL;  
 


/**
 * Cosmos's lightweight date class that carries no timestamp.
 * <code>CDate</code>s are used as financial dates such as period
 * dates, payment dates, and so on.  The absence of a timestamp
 * ensures that day counting and rolling are done based on whole days
 * and are not rendered inaccurate due to differences in time of day.
 * <p>
 * Note that <code>CDate</code>'s integer constants for months
 * begin with 1 to represent January.  (If you are accustomed
 * to using the standard Java <code>Date</code> class, you will
 * recall that the <code>Date</code> constant for January is
 * 0.)
 * <p>
 * <code>Date</code>s can be converted to <code>CDate</code>s
 * using the <code>CDate(Date d)</code> constructor, and <code>
 * CDate</code>s can be converted to <code>Date</code>s using
 * the <code>getDate()</code> method (the timestamp will be set to
 * 23:59 local time).  A <code>CDate</code> can
 * be converted to a <code>JDatetime</code> using the <code>
 * getJDatetime()</code> method (again, the timestamp will be set to
 * 23:59 local time). */

public class CDate implements Comparable, Cloneable {
	protected static final String DAYS_OF_WEEK[]={
        "SUN","MON","TUE","WED","THU","FRI","SAT"};
    public final static int SUNDAY = 1;
    public final static int MONDAY = 2;
    public final static int TUESDAY = 3;
    public final static int WEDNESDAY = 4;
    public final static int THURSDAY = 5;
    public final static int FRIDAY = 6;
    public final static int SATURDAY = 7;

    public final static int JANUARY = 1;
    public final static int FEBRUARY = 2;
    public final static int MARCH = 3;
    public final static int APRIL = 4;
    public final static int MAY = 5;
    public final static int JUNE = 6;
    public final static int JULY = 7;
    public final static int AUGUST = 8;
    public final static int SEPTEMBER = 9;
    public final static int OCTOBER = 10;
    public final static int NOVEMBER = 11;
    public final static int DECEMBER = 12;

    private static final int NUM_DAYS[]
        = {0,0,31,59,90,120,151,181,212,243,273,304,334}; // 0-based, for day-in-year
    private static final int LEAP_NUM_DAYS[]
        = {0,0,31,60,91,121,152,182,213,244,274,305,335}; // 0-based, for day-in-year
    private static final int MONTH_LENGTH[]
        = {0,31,28,31,30,31,30,31,31,30,31,30,31}; // 0-based
    private static final int LEAP_MONTH_LENGTH[]
        = {0,31,29,31,30,31,30,31,31,30,31,30,31}; // 0-based

    public final static Calendar CALENDAR = Calendar.getInstance();
    
    /** 1 January 1970 */
    public final static long EPOCH = 2440588L;
    /** a CDate object initialized to the EPOCH */ 
    private final static CDate EPOCH_DATE = new CDate();

    /** 
     * The CDate class allow to set an artificial Today for simulation purpose
     * If set, the _today variable will be returned by the static method getNow()
     */
    /** 
     * The CDate class allow to set an artificial Today for simulation purpose
     * If set, the _today variable will be returned by the static method getNow()
     */
    private static CDate _today = null;

    private int     _year = 0;
    private int     _month = 0;
    private int     _dayOfMonth = 0;
    private long    _nbJulian = EPOCH;
    private boolean _needComplete = true;

    /**
    * Allocates a new <code>CDate</code> object representing 
    * the EPOCH (01/01/1970)
    * In order to avoid creating unnecessary CDate objects use CDate.valueOf() 
    * method instead.
    * @see CDate#valueOf()
    */
    public CDate() {
        this._nbJulian = EPOCH;
        this._needComplete = true;
    }

    /**
     * Returns a <code>CDate</code> instance representing the EPOCH (01/01/1970). 
     * If a new CDate instance is not required, this method should generally 
     * be used in preference to the constructor CDate(), as this method is 
     * likely to yield significantly better space and time performance by 
     * making use of a cache.     
     */
    public static CDate valueOf() {
        return EPOCH_DATE;
    }

    /**
    * Allocates a new <code>CDate</code> object and sets its julian
    * date to the passed julian date.
    * In order to avoid creating unnecessary CDate objects 
    * use CDate.valueOf(long julian) method instead.
    * @see CDate#valueOf(long julian)
    * @param julian A long indicating the value to set the julian date.
    */
    protected CDate(long julian) {
        this._nbJulian = julian;
        this._needComplete = true;
    }

    /**
     * Returns a <code>CDate</code> instance representing a date initialized to 
     * the julian calendar value passed a parameter. 
     * If a new <code>CDate</code> instance is not required, this method should 
     * generally be used in preference to the constructor CDate(long julian), 
     * as this method is likely to yield significantly better space and time 
     * performance by making use of a cache.     
     */
    public static  CDate valueOf(long julian) {
        return getCachedDate(julian);
    }
    /**
     * Returns a <code>CDate</code> instance representing a date initialized to 
     * the string value passed as parameter. This method is intended to be the
     * reciprocal of the <code>toString()</code> method.
     * @see CDate#toString()
     * @param s a date's string representation.
     * @return the corresponding date.
     */
    public static CDate valueOf(String s) {
        return commonUTIL.stringToCDate(s);
    }

    /**
     * Allocates a new <code>CDate</code> object and sets it to the
     * date of the <code>java.util.Date</code> argument.
     * In order to avoid creating unnecessary CDate objects use 
     * CDate.valueOf(java.util.Date date) method instead.
     * @see CDate#valueOf(Date date)
     * @param  date  a <code>java.util.Date</code> indicating the
     *               date to which this <code>CDate</code>
     *               should be set.
     */
    protected CDate(Date date) {
        this(date,null);
    }

    /**
     * Returns a <code>CDate</code> instance representing a date corresponding
     * to the date of the <code>java.util.Date</code> argument.
     * 
     * @param date
     *            a <code>java.util.Date</code> indicating the date to which
     *            this <code>CDate</code> should be set.
     */
    public static CDate valueOf(Date date) {
        return valueOf(toJulian(date, null));
    }

    /**
     * Allocates a new <code>CDate</code> object and sets its value to the value 
     * of the given CDate parameter.
     * In order to avoid creating unnecessary CDate objects use 
     * CDate.valueOf(CDate d) method instead.
     * @see CDate#valueOf(CDate d)
     * @param d A CDate which value to use to initialize the new object.
     */
    protected CDate(CDate d)
    {
        d.complete();
        this._year = d._year; 
        this._month = d._month; 
        this._dayOfMonth = d._dayOfMonth;
        this._needComplete = d._needComplete;
        this._nbJulian = d._nbJulian;
    }

    /**
     * Returns a <code>CDate</code> instance representing a date corresponding
     * to the given CDate argument. 
     * If a new <code>CDate</code> instance is not required, this method should 
     * generally be used in preference to the constructor CDate(long julian), 
     * as this method is likely to yield significantly better space and time 
     * performance by making use of a cache.     
     */
    public static CDate valueOf(CDate d) {
        return valueOf(d.getJulian());
    }

    /**
     * Allocates a new <code>CDate</code> object and sets its value using the 
     * given year, month and day arguments.
     * In order to avoid creating unnecessary CDate objects use 
     * CDate.valueOf(int y, int m, int d) method instead.
     * @see CDate#valueOf(int y, int m, int d)
     *@param y The year portion of the date. For example 1999.
     *@param m The month portion of the date. For example 1 for the
     *         first month of the year.
     *@param d The day of the month portion of the date. For Example 31.
     */
    protected CDate(int y, int m, int d)
    {
        if(m < 0 || m > 12 || d<0 || d> 31) {
          // commonUTIL.displayError(name, methodName, e).error("Date","Invalid Date " + y + "/" + m + "/" +d,new Throwable());
        }
        int ml=getMonthLength(y,m);
        // don't set past the end of the month
        if (d > ml) {
            d = ml;
        }
        // or before the beginning of the month
        if (d < 1) {
            d = 1;
        }
        this._year = y; this._month = m; this._dayOfMonth = d;
        this._nbJulian = toJulian(y, m, d);
        this._needComplete = false;
    }

    /**
     * Returns a <code>CDate</code> instance representing a date corresponding 
     * to the given year, month and day arguments.
     *@param y The year portion of the date. For example 1999.
     *@param m The month portion of the date. For example 1 for the
     *         first month of the year.
     *@param d The day of the month portion of the date. For Example 31.
     */
    public static CDate valueOf(int y, int m, int d) {
        return valueOf(toJulian(y, m, d));
    }

    /**
     * Allocates a new <code>CDate</code> object and sets it to the
     * date of the <code>java.util.Date</code> and <code>java.util.TimeZone</code>
     * arguments.
     * In order to avoid creating unnecessary CDate objects use 
     * CDate.valueOf(java.util.Date date, TimeZone tz) method instead.
     * @see CDate#valueOf(Date date, TimeZone tz)
     * @param  date  a <code>java.util.Date</code> indicating the
     *               date to which this <code>CDate</code>
     *               should be set.
     * @param tz
     *            a <code>java.util.Timezone</code> indicating the timezone
     *            to use for this date.
     */
    protected CDate(Date date, TimeZone tz)
    {
        this._nbJulian = toJulian(date, tz);
        this._needComplete = true;
    }
    
    /**
     * Returns a <code>CDate</code> instance representing a date corresponding
     * to the date of the <code>java.util.Date</code> and 
     * <code>java.util.TimeZone</code> arguments.
     * @param date
     *            a <code>java.util.Date</code> indicating the date to which
     *            this <code>CDate</code> should be set.
     * @param tz
     *            a <code>java.util.Timezone</code> indicating the timezone
     *            to use for this date.
     */
    public static CDate valueOf(Date date, TimeZone tz) {
        return valueOf(toJulian(date, tz));
    }

    /**
     * Returns a hashcode representation of this <Code>CDate</Code>.
     * @return an <code>int</code> representing the hashcode of this <Code>CDate</code>
     */

    public int hashCode()
    {
        return (int) this._nbJulian ^ (int) (this._nbJulian >> 32);
    }
    /**
         * Compares this Date to another, and returns
         * true if the passed Date is the same date
         * as this one.
         * @param o the <code>Object</code> to be compared to.
         * @return true if dates are equal, false otherwise.
     */

    public boolean equals(Object o)
    {
        if (o == null || !(o.getClass().equals(this.getClass()))) {
            return false;
        }
        return this._nbJulian == ((CDate) o)._nbJulian;
    }
    
    /**
     * Compares this <code>CDate</code> to another and return
     * true if this CDate is less than the passed one.
     * @param date the date to be compared
     * @return True or False
     */

     final public boolean before(CDate date) {
         if (date==null) {
             return false;
         }
         return this._nbJulian < date._nbJulian;
     }
     /**
          * Compares this <code>CDate</code> to another and return
          * true if this CDate is greater than the passed one.
          * @param date the date to be compared
          * @return True or False
          */


     final public boolean after(CDate date) {
         if (date==null) {
             return false;
         }
         return this._nbJulian > date._nbJulian;
     }
     /**
     * Compares this <code>CDate</code> to another and return
     * true if this CDate is less than or equal to the passed one.
     * @param date the date to be compared
     * @return True or False
     */
     final public boolean lte(CDate date) {
         return this._nbJulian <= date._nbJulian;
     }

         /**
     * Compares this <code>CDate</code> to another and return
     * true if this CDate is greater than or equal to the passed one.
     * @param date the date to be compared
     * @return True or False
     */
     final public boolean gte(CDate date) {
         return this._nbJulian >= date._nbJulian;
     }

     /**
      * Compares this CDate with the passed CDate and returns a
      * positive int if this CDate comes after that CDate (obj).
      * @param obj the CDate to be compared
      * @return an int, positive if this CDate is later than obj,
      * negative if obj is later than this CDate, and zero if the two
      * dates coincide
      */
     final public int compareTo(Object obj)
     {
         CDate j = (CDate) obj;
         if (this._nbJulian < j._nbJulian) {
             return -1;
         } else if (this._nbJulian == j._nbJulian) {
             return 0;
         } else {
             return 1;
         }
     }

         /**
         * Compares two <Code>CDate</code> objects and returns
         * the number of days between the two dates.
         * End Date minus Start date.
         * @param start The start date.
         * @param end The end date.
         * @return A <code>double</code> which represents the difference
         * between the two dates.
         */
     static public double diff(CDate start, CDate end)
     {
         return end._nbJulian - start._nbJulian;
     }

         /**
         * Returns a string representation of this Date.
         *
         * @return a string representation of this Date.
         *
         */
     public String toString()
     {
         return commonUTIL.dateToString(this);
     }
     /**
     * Returns a string representation of a date in the form
     * yyyy-mm-dd
     * @return a string representation of a date in the form
     * yyyy-mm-dd
     */
     public String toSQLString()
     {
         complete();
         return "" + this._year + "-" + this._month + "-" + this._dayOfMonth;
     }

         /**
         *Returns True if the day of the week of this <code>CDate</code>
         *is a SATURDAY or A SUNDAY. OtherWise returns False
         *@return Boolean true or false.
         */
     final public boolean isWeekEndDay()
     {
         int dayOfWeek = getDayOfWeek();
         if (dayOfWeek == SATURDAY || dayOfWeek == SUNDAY) {
             return true;
         } else {
             return false;
         }
     }
         /**
         *Returns true if the passed year is a leap year.
         *@param year <code>integer</code> the year to check
         *@return True if the year is a leap year otherwise false.
         */
     static public boolean isLeapYear(int year)
     {
         return year%4 == 0 && (year%100 != 0 || year%400 == 0);
     }

     /**
     *Returns the number of days in the passed year
     *366 for a leap year, otherwise 365.
     *@param year <code>integer</code> the year to check
     *@return the number of days in the passed year.
     */

     static public int getDaysInYear(int year) {
         return isLeapYear(year) ? 366 : 365;
     }
     /**
         *Returns true if this objects year is a leap year.
         *@return True if the year is a leap year otherwise false.
         */
     public boolean isLeapYear()
     {
         complete();
         return isLeapYear(this._year);
     }

     /**
      * Returns a <code>Date</code> equivalent to this <code>CDate
      * </code>, with the timestamp set to 23:59 local time.
      * @param tz The timezone for the date.
      * @return  the <code>Date</code> equivalent to this <code>CDate
      * </code>
      */

     final public Date getDate(TimeZone tz) {
         complete();
         Date result = null;
         synchronized (CALENDAR) {
             CALENDAR.setTimeZone(tz == null ? TimeZone.getDefault() : tz);
             CALENDAR.set(Calendar.YEAR, this._year);
             CALENDAR.set(Calendar.MONTH, this._month - 1);
             CALENDAR.set(Calendar.DATE, this._dayOfMonth);
             CALENDAR.set(Calendar.HOUR_OF_DAY, 23);
             CALENDAR.set(Calendar.MINUTE, 59);
             CALENDAR.set(Calendar.SECOND, 0);
             CALENDAR.set(Calendar.MILLISECOND, 0);
             result = CALENDAR.getTime();
         }
         return result;
     }

     /**
      * Returns a <code>CDatime</code> equivalent to this <code>CDate
      * </code>, with the timestamp set to 23:59 local time.
      * @param tz The timezone for the date.
      * @return  the <code>CDatetime</code> equivalent to this <code>
      * CDate</code>
      */
     final public CDateTime getCDatetime(TimeZone tz)
     {
         return new CDateTime(getDate(tz));
     }
     
     /**
      *Returns the Julian Day of this <code>CDate</code> object
      *
      * @return The Julian Day of this <code>CDate</code> object
      */
  final public long getJulian() { return this._nbJulian; }
  /**
      *Returns the Year portion of this <Code>CDate</Code> object
      * @return The Year portion of this <Code>CDate</Code> object
      */
  final public int getYear() { complete(); return this._year; }
  /**
      *Returns the Month portion of this <Code>CDate</Code> object
      * @return The Month portion of this <Code>CDate</Code> object
      */

  final public int getMonth() { complete(); return this._month; }
  /**
      *Returns the Day of the Month portion of this <Code>CDate</Code> object
      * @return The Day of the Month portion of this <Code>CDate</Code> object
      */

  final public int getDayOfMonth() { complete(); return this._dayOfMonth; }

      /**
      * Returns the day of the week as an integer for this <code>CDate</Code> object
      * @return An integer that represents the day of the week.
      * For Example. Sunday equals 1.
      */

  final public int getDayOfWeek()
  {
      return julianDayToDayOfWeek(this._nbJulian);
  }

      /**
      * Returns a String representation of the day of the week for this
      * <code>CDate</code> object.
      *
      * @return A string Day of the week. For example "MON" for Monday.
      */
  final public String getDayOfWeekAsString()
  {
      return DAYS_OF_WEEK[getDayOfWeek()-1];
  }

      /**
      * Returns the Day of the year for this <Code>CDate</code>
      * For Example March 15th 2001 Returns 74
      *
      * @return An <Code>Int</code> That represents the day of the year for
      * this date.
      */
  final public int getDayOfYear()
  {
      complete();
      if (isLeapYear(this._year)) {
          return LEAP_NUM_DAYS[this._month] + this._dayOfMonth;
      } else {
          return NUM_DAYS[this._month] + this._dayOfMonth;
      }
  }

      /**
      *
      * Returns the number of days in the month for this <code>CDate</code>
      * @return The number of days in the month for this <code>CDate</code>
      */
  final public int getMonthLength()
  {
      complete();
      int months[] = isLeapYear() ? LEAP_MONTH_LENGTH : MONTH_LENGTH;
      return  months[this._month];
  }
  static final public int getMonthLength(int year, int month)
  {
      int months[] = isLeapYear(year) ? LEAP_MONTH_LENGTH : MONTH_LENGTH;
      return  months[month];
  }

      /**
      * Returns the last day of the month for this <code>CDate</code>
      * @return The last day of the month for this <code>CDate</code>
      */
  final public CDate getEOM()
  {
      complete();
      int d;
      if (this._month == FEBRUARY) {
          d = isLeapYear() ? 29 : 28;
      } else {
          d = MONTH_LENGTH[this._month];
      }

      return CDate.valueOf(this._year, this._month, d);
  }
      /**
      * Adds a number of months to a date and returns the last day of
      * the new month.
      * @param numberOfMonthsToAdd The number of months to add to the <code>CDate</Code> object.
      * @return The last day of the newly calculated month portion of the
      * <code>CDate</Code> object.
      */
      final public CDate getEOM(int numberOfMonthsToAdd)
  {
      return addMonths(numberOfMonthsToAdd).getEOM();
  }

      /**
      * Returns True if the day portion of this <code>CDate</Code> is equal to
      * the last day of the month. Otherwise Returns false.
      * @return A Boolean true or false.
      */
  final public boolean isEOM() {
      return getDayOfMonth() == getMonthLength();
  }

  /**
   * Returns true if this is the last business day of the month.
   * @param vHol Vector containing holiday cities.
   * @return boolean
   */
  /**
   * Returns true if this is the last business day of the month.
   * @param vHol Vector containing holiday cities.
   * @return boolean
   */
  final public boolean isEOM(Vector vHol) {
    /*  Holiday hol = Holiday.getCurrent();
      CDate eom = getEOM();
      if (hol.isBusinessDay(eom, vHol)) {
          return this._nbJulian == eom._nbJulian;
      }
      CDate eomBus = hol.previousBusinessDay(eom, vHol);*/
      return true; //this._nbJulian == eomBus._nbJulian;
  }

  /**
   * Add a specified number of days to a <code>CDate</code> object.
   * @param days The number of days to add.
   * @return A new <code>CDate</code> object.
   */
final public CDate addDays(int days)
{
   return valueOf(this._nbJulian + days);
}


/**
* Add A specified number of months to a<code>CDate</code> object.
* 
* @param months
*            The number of months to add.
* @return A new <code>CDate</code> object.
*/
final public CDate addMonths(int months)
{
   if(months == 0) {
       return this;
   }
   complete();
   int y = this._year; 
   int m = this._month; 
   int d = this._dayOfMonth;
   y += months / 12; m += months % 12;
   if (m < JANUARY) {
       while(m<JANUARY) {
           y -= 1; m += DECEMBER;
       }
   }
   else if (m > DECEMBER) {
       while(m > DECEMBER) {
           y += 1; m -= DECEMBER;
       }
   }

   if (d > 28)     {
       int maxDaysInMonth;
       if (m == FEBRUARY && isLeapYear(y)) {
           maxDaysInMonth = 29;
       } else {
           maxDaysInMonth = MONTH_LENGTH[m];
       }
       if (d > maxDaysInMonth) {
           d = maxDaysInMonth;
       }
   }
   return valueOf(toJulian(y, m, d));
}

/**
* Add a specified number of months to a <code>CDate</code> object and
* ensure the result falls on a the passed rolling day or the last day
* of the month if the rolling day is greater than the number of days
* in the month.
* 
* @param months The number of months to add.
* @param rollingDay The day on which the result should fall
* @return A new <code>CDate</code> object.
*/
final public CDate addMonths(int months, int rollingDay) {
   // By default, do the same as addMonths(int months)
   CDate result = this.addMonths(months);
   if (rollingDay > 0) {
       if (result.getDayOfMonth() != rollingDay) {
           // Adjust to the rollingDay
           int y = result._year; 
           int m = result._month; 
           int d = rollingDay;
           int daysInMonth=isLeapYear(y) ? LEAP_MONTH_LENGTH[m] : MONTH_LENGTH[m];
           if (d > daysInMonth) {
               d = daysInMonth;
           }
           result = valueOf(y, m, d);
       }
   }
   return result;
}

   /**
   * Add a specified number of years to a <code>CDate</code> object
   * @param years The number of years to add
   * @return A new <code>CDate</code> object
   */

final public CDate addYears(int years)
{
   if(years == 0) {
       return CDate.valueOf(this._nbJulian);
   }
   complete();
   int y = this._year; int m = this._month; int d = this._dayOfMonth;
   y += years;
   if (m == FEBRUARY && d == 29 && !isLeapYear(y)) {
       d = 28;
   }

   return valueOf(y, m, d);
}

/**
* Adds a number of days to a date and assumes we have
* 30 days in each month. Adjusts the calculated date with
* respect to the end of the month.
* For example
* From 12/31 if we add 60 days we get the last day in
* February.
* @param ndays the number of days to add
* @return the adjusted date.
*/
final public CDate addTenor(int ndays)
{
   if (ndays == 0) {
       return CDate.valueOf(this);
   }
   if(ndays < 30) {
       return addDays(ndays);
   }
   complete();
   boolean multipleOfMonths = ndays % 30 == 0;
   int y = this._year + ndays / 360; ndays = ndays % 360;
   int m = this._month + ndays / 30;
   int d = this._dayOfMonth + ndays % 30;
   while(m > DECEMBER) { y++; m -= DECEMBER; }
   int daysInMonth=isLeapYear(y) ? LEAP_MONTH_LENGTH[m] :
                    MONTH_LENGTH[m];
   if (d > daysInMonth) 	    {
       //if it we are adding a multiple number of months
       if(multipleOfMonths) {
           d = daysInMonth;
       }
       else {
           d -= daysInMonth;
           m++;
           if(m > DECEMBER) { y ++; m -= DECEMBER; }
       }
   }
   return valueOf(y, m, d);
}
/**
* Subtracts a number of days from a date and assumes we have
* 30 days in each month.
* @param ndays the number of days to subtract.
* @return the adjusted date.
*/
final public CDate subtractTenor(int ndays)
{
   if (ndays == 0) {
       return CDate.valueOf(this);
   }
   complete();
   boolean multipleOfMonths = ndays % 30 == 0;
   int y = this._year - ndays / 360; ndays = ndays % 360;
   int m = this._month  - ndays / 30;
   int d = this._dayOfMonth - ndays % 30;
   //The line below was missing the test for '=' before
   if(m <= 0) { y-- ; m += DECEMBER; }
   int daysInMonth=isLeapYear(y) ? LEAP_MONTH_LENGTH[m] :
                    MONTH_LENGTH[m];
   if (d > daysInMonth)
       {
           //if it we are substracting a multiple number of months
           if(multipleOfMonths) {
               d = daysInMonth;
           }
           else {
               d -= daysInMonth;
               m++;
               if(m > DECEMBER) { y++; m -= DECEMBER; }
           }
       }
   // same comment as above. test on "=" is missing
   if(d <= 0) {
       m--;
       // same comment as above. test on "=" is missing
       if(m <= 0) { y-- ; m += DECEMBER; }
       daysInMonth=isLeapYear(y) ? LEAP_MONTH_LENGTH[m] :
                    MONTH_LENGTH[m];
       if(multipleOfMonths) {
           d = daysInMonth;
       } else {
           d += daysInMonth;
       }
   }
   return valueOf(y, m, d);
}
/**
 * Subtracts a number of days from a date and assumes we have
 * 30 days in each month.
 * @param ndays the number of days to subtract.
 * @return the adjusted date.
 */
final public CDate substractTenor(int ndays)
{
    return subtractTenor(ndays);
}

/**
 * Adds a tenor to a date and assumes we have
 * 30 days in each month. Adjusts the calculated date with
 * respect to the end of the month.
 * For example
 * From 12/31 if we add 60 days we get the last day in
 * February.
 * @param t the tenor to add
 * @return the adjusted date.
 */
final public CDate addTenor(Period t)
{
    return    addTenor(t.getCode());
}
/**
 * Subtracts a tenor from a date and assumes we have
 * 30 days in each month.
 * @param t the tenor to subtract.
 * @return the adjusted date.
 */
final public CDate substractTenor(Period t)
{
    return subtractTenor(t.getCode());
}

/**
 * Subtracts a tenor from a date and assumes we have
 * 30 days in each month.
 * @param t the tenor to subtract.
 * @return the adjusted date.
 */
final public CDate subtractTenor(Period t)
{
    return subtractTenor(t.getCode());
}

/**
 * Adds a number of days (in the form of a Frequency object)
 * to a date and assumes that each month contains 30 days.
 * <p>
 * Adjusts the calculated date with
 * respect to the end of the month. For example,
 * adding 60 days to 12/31 gives the last day of February.
 *
 * @param frq the number of days to add
 *
 * @return the adjusted date (CDate)
 */
final public CDate addFrequency(Frequency frq)
{
    return addTenor(frq.getTenor());
}

private static int julianDayToDayOfWeek(long julian)
{
    // If julian is negative, then julian%7 will be negative, so we adjust
    // accordingly.  We add 1 because Julian day 0 is Monday.
    int dayOfWeek = (int)((julian + 1) % 7);
    return dayOfWeek + (dayOfWeek < 0 ? 7 + SUNDAY : SUNDAY);
}

synchronized final private void complete()
{
    if (this._needComplete)
        {
            this._needComplete = false;
            int ymdw[] = new int[3];
            fromJulian(this._nbJulian, ymdw);
            this._year = ymdw[0]; this._month = ymdw[1]; this._dayOfMonth = ymdw[2];
        }
}

// from Numerical Recipies
final static private long IGREG=15+31*(10+12*1582);

protected static long toJulian(Date d, TimeZone tz) {
    int year = 0;
    int month = 0;
    int dayOfMonth = 0;
    synchronized (CALENDAR) {
        CALENDAR.setTimeZone(tz == null ? TimeZone.getDefault() : tz);
        CALENDAR.setTime(d);
        year = CALENDAR.get(Calendar.YEAR);
        month = CALENDAR.get(Calendar.MONTH)+1;
        dayOfMonth = CALENDAR.get(Calendar.DATE);
    }
    return toJulian(year, month, dayOfMonth);
}

static protected long toJulian(int year, int month, int dayOfMonth)
{
    int id = dayOfMonth; int mm = month; int iyyy = year;
    long jul;
    int ja, jy, jm;

    if (iyyy < 0) {
        iyyy++;
    }
    if (mm > 2) { jy = iyyy; jm = mm+1; }
    else { jy = iyyy - 1; jm = mm + 13; }

    jul = (long)(Math.floor(365.25*jy)+Math.floor(30.6001*jm) +id+1720995);
    if (id + 31*(mm+12*iyyy) >= IGREG)
        {
            ja = (int)(0.01*jy);
            jul += 2-ja+(int)(0.25*ja);
        }
    return jul;
}

static protected void fromJulian(long julian, int ymd[])
{
    long ja,jalpha,jb,jc,jd,je;
    int mm,id,iyyy;
    if (julian >= IGREG)
        {
            jalpha = (long)((julian-1867216-0.25)/36524.25);
            ja=julian+1+jalpha-(long)(0.25*jalpha);
        }
    else { ja = julian; }
    jb = ja + 1524;
    jc = (long)(6680.0 + (jb-2439870-122.1)/365.25);
    jd = (long)(365*jc+0.25*jc);
    je = (long)((jb-jd)/30.6001);
    id = (int)(jb-jd-(int)(30.6001*je));
    mm = (int)(je -1);
    if (mm > 12) {
        mm -= 12;
    }
    iyyy = (int)(jc - 4715);
    if (mm > 2) {
        --iyyy;
    }
    if (iyyy <= 0) {
        --iyyy;
    }
    ymd[0] = iyyy;
    ymd[1] = mm;
    ymd[2] = id;
}
    /**
     * Returns a copy of this <code>CDate</code>.
     * @return a copy of this <code>CDate</code>.
     * @throws CloneNotSupportedException if <code>CDate</code> cannot be cloned.
     */

public Object clone() throws CloneNotSupportedException {
    return super.clone();
}

/**
 * Returns a <code>CDate</Code> object with the current date.
 */
static public CDate getNow() {
    if (_today == null) {
        return valueOf(new Date());
    }
    return _today;
}

/**
 * Return true if the AsOfDate has been set for the application.
 * @return
 */
static public boolean isAsOfDateSet() {
	return (_today != null);
}

public void writeExternal(ObjectOutput out)
    throws IOException {
    out.writeLong(this._nbJulian);
}
public void readExternal(ObjectInput in)
    throws IOException,ClassNotFoundException {
    this._nbJulian=in.readLong();
    this._needComplete = true;
}
/**
 * Basic idea is to maintain the common instance of CDate object because
 * we are using shared/unshared stuff
 * @return the cached instance of this date
 * @throws ObjectStreamException
 */
private Object readResolve() throws ObjectStreamException {
  return CDate.getCachedDate(this._nbJulian);
}
/**
    * Returns the Difference between the julian day for this
    * <CDate> object and the Julian Day for Jan 1st 1970.
    * @return An integer Julian Day.
    */
final public int getJulianOffset() {
    return (int)(getJulian()-EPOCH);
}
    /**
    * Returns true if the year portion of the date is greater than
    * the current year plus 1000 or less than the current year minus 1000
    * @return  A boolean true or false.
    */
public boolean isBad() {
    CDate today= CDate.getNow();
    int thisYear=today.getYear();
    int year=getYear();
    if( year > thisYear +1000  ||
        year < thisYear -1000) {
        return true;
    } else {
        return false;
    }
}

static public int getTenor(CDate start, CDate end)
{
	if (start.after(end)) {
        return -1;
    }
    if (start.equals(end)) {
        return 0;
    }
    
    int startYear = start.getYear();
    int startMonth = start.getMonth();
    int startDay = start.getDayOfMonth();
    int endYear = end.getYear();
    int endMonth = end.getMonth();
    int endDay = end.getDayOfMonth();
    
    int daysInStartMonth=isLeapYear(startYear) ? LEAP_MONTH_LENGTH[startMonth] :
        MONTH_LENGTH[startMonth];
    int daysInEndMonth=isLeapYear(endYear) ? LEAP_MONTH_LENGTH[endMonth] :
        MONTH_LENGTH[endMonth];
    
    if (startDay == endDay || ((startDay == daysInStartMonth) && (endDay == daysInEndMonth))) 
    	return (360 * (endYear - startYear)) + (30 * (endMonth - startMonth));
    else {
    	int diff = (int)diff(start, end);
        if (diff % 7 == 0 || diff == 15 || diff < 7 )
            return diff;
        else 
        	return -1;
    }
}

/**
 * @deprecated
 */
final public Date getDate() {
    return getDate((TimeZone)null);
}

/**
 * @deprecated
 */
final public CDateTime getJDatetime() {
    return new CDateTime(getDate());
}

/**
    * Return a vector of strings that contains the days of the week.
    *
    * @return A vector of strings that contains the days of the week.
    */
static final public Vector getDayOfWeekAsStringDomain()
{
    return new Vector(Arrays.asList(DAYS_OF_WEEK));
}
/**
    * For a given Integer return the corresponding Day of the week as a string.
    * @param dayOfWeek An integer value that represents a day of the week.
    * @return  A string value that represents the day of the week.
    */
static final public String getDayOfWeekAsString(int dayOfWeek)
{
    return DAYS_OF_WEEK[dayOfWeek-1];
}

/**
    * Set the Class Field _today to the passed date
    * @param d A <code>CDate<code> object.
    */
public static void setToday(CDate d) {
    _today = d;
    commonUTIL.displayError("CDate", "Today set to " + d, null);
}

public CDate addOneDayFixed() {
    CDate dt=new CDate(this);
    dt.incrementJulianDate();
    return dt;
  }
private void incrementJulianDate(){
  this._nbJulian++;
  this._needComplete = true;
}
public CDate subOneDayFixed() {
  CDate dt=new CDate(this);
  dt.decrementJulianDate();
  return dt;
}
private void decrementJulianDate() {
    this._nbJulian--;
    this._needComplete = true;
}
/**
 * returns this date plus 'nb' business days
 * uses _PRECEDING or _FOLLOWING date rolling depending on the sign of 'nb'...
 * @param nb - number of business days (positive or not)
 * @param vHol - vector of city-holidays
 * @return - CDate. 
 *          In certain cases, this method may return a week-end day.
 *          Please consider using Holiday.getCurrent().addBusinessDays() which does not return week-end days.
 */
 public CDate addBusinessDays(int nb, Vector vHol) {
 CDate date = CDate.valueOf(this);
 if (commonUTIL.isEmpty(vHol)) {return date.addDays(nb);}
 if (nb == 0) {
     return date;
 } else if (nb < 0) {
        RollDate dtRollPre = RollDate.valueOf(RollDate.S_PRECEDING);
        int nDaysAdded = nb;
        do {
          date = date.addDays(-1); nDaysAdded++;
          date = dtRollPre.roll(date,vHol);
          }
        while (nDaysAdded < 0);
        }
      else {
        RollDate dtRollFol = RollDate.valueOf(RollDate.S_FOLLOWING);
        int nDaysAdded = 0;
        do {
          date = date.addDays(1); nDaysAdded++;
          date = dtRollFol.roll(date,vHol);
          }
        while (nDaysAdded < nb);
        }
 return date;
 }

 /**
 * returns this date plus 'tenor'
 * @param tenor - the tenor
 * @param vHol - vector of city-holidays
 * @param dtRoll - date roll object - in order to roll date accordingly
 * @return - CDate
 */
 /**
  * returns this date plus 'tenor'
  * @param tenor - the tenor
  * @param vHol - vector of city-holidays
  * @param dtRoll - date roll object - in order to roll date accordingly
  * @return - CDate
  */
  public CDate addTenor(Period tenor,Vector vHol,RollDate dtRoll) {
      CDate date = CDate.valueOf(this);
      date = date.addTenor(tenor);
      if (!commonUTIL.isEmpty(vHol)) {date = dtRoll.roll(date,vHol);}
      return date;
  }
  /**
   * returns this date plus 'tenor' assuming a date roll object FOLLOWING
   * @param tenor - the tenor
   * @param vHol - vector of city-holidays
   * @return - CDate
   */
   public CDate addTenor(Period tenor,Vector vHol) {
       return addTenor(tenor,vHol,RollDate.valueOf(RollDate.S_FOLLOWING));
   }

   public CDate subtractTenor(Period tenor, Vector vHol, RollDate dtRoll) {
       CDate date = CDate.valueOf(this);
       date = date.subtractTenor(tenor);
       if (!commonUTIL.isEmpty(vHol)) {
           date = dtRoll.roll(date, vHol);
       }

       return date;
   }

   public CDate subtractTenor(Period tenor, Vector vHol) {
       return subtractTenor(tenor, vHol, RollDate.valueOf(RollDate.S_FOLLOWING));
   }

   /**
    * This method returns a <code>java.util.Date</code> object initialized 
    * with the current time of the day and the year month and day of this 
    * CDate object taking into account the given TimeZone
    * @param tz the TimeZone to use to generate the resulting Date
    * @return a java.util.Date object
    */
   final public Date getNow(TimeZone tz)
   {
       Date result = null;
       complete();
       synchronized (CALENDAR) {
           CALENDAR.setTimeZone(tz == null ? TimeZone.getDefault() : tz);
           CALENDAR.setTime(new Date());
           CALENDAR.set(Calendar.YEAR,this._year);
           CALENDAR.set(Calendar.MONTH,this._month-1);
           CALENDAR.set(Calendar.DATE,this._dayOfMonth);
           result = CALENDAR.getTime();
       }
       return result;
   }
   /**
    * Returns the last day of the quarter for this <code>CDate</code>
    * @return The last day of the quarter for this <code>CDate</code>
    */
   final public CDate getEOQ()
   {
       complete();
       int month = 0;
       if (this._month <= MARCH) {
           month = MARCH;
       } else if (this._month <= JUNE) {
           month = JUNE;
       } else if (this._month <= SEPTEMBER) {
           month = SEPTEMBER;
       } else {
           month = DECEMBER;
       }

       int day = MONTH_LENGTH[month];

       return CDate.valueOf(this._year, month, day);
   }
   /**
    * Returns the first day of the quarter for this <code>CDate</code>
    * @return The first day of the quarter for this <code>CDate</code>
    */
   final public CDate getBOQ()
   {
       complete();
       int month = 0;
       if (this._month <= MARCH) {
           month = JANUARY;
       } else if (this._month <= JUNE) {
           month = APRIL;
       } else if (this._month <= SEPTEMBER) {
           month = JULY;
       } else {
           month = OCTOBER;
       }

       return CDate.valueOf(this._year, month, 1);
   }

   private static long _baseJulian;
   private static CDate _cachedDatesAfter[];
   private static CDate _cachedDatesBefore[];
   private static final int HALF_CACHE_SIZE=5000;
   private static long _baseJulianMax;
   private static long _baseJulianMin;

   private static synchronized void makeCache() {
       if(_cachedDatesAfter != null) {
           return ;
       }
       _cachedDatesBefore= new CDate[HALF_CACHE_SIZE];
       _cachedDatesAfter= new CDate[HALF_CACHE_SIZE];
       CDate today= CDate.getNow();
       _baseJulian=today.getJulian();
       for(int i=0;i<HALF_CACHE_SIZE;i++) {
           _cachedDatesBefore[i]= new CDate(_baseJulian-i);
           _cachedDatesAfter[i]= new CDate(_baseJulian+i);
       }
       _baseJulianMax=_baseJulian+HALF_CACHE_SIZE;
       _baseJulianMin=_baseJulian-HALF_CACHE_SIZE;
   }
   
   private static CDate getCachedDate(long julian) {
       makeCache();
       if(julian >= _baseJulian && julian < _baseJulianMax) {
           return _cachedDatesAfter[(int)(julian-_baseJulian)];
       } else if(julian < _baseJulian && julian > _baseJulianMin) {
           return _cachedDatesBefore[(int)(_baseJulian-julian)];
       } else {
           return new CDate(julian);
       }
   }

}
