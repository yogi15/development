package util.common;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import util.commonUTIL;



public class DateU {
	
	public static int Year = 0;
	public static int Day = 0;
	public static  int Month = 0;
	public int _year =0;
	public int get_year() {
		return _year;
	}



	public void set_year(int _year) {
		this._year = _year;
	}



	public int get_day() {
		return _day;
	}



	public void set_day(int _day) {
		this._day = _day;
	}



	public int get_month() {
		return _month;
	}



	public void set_month(int _month) {
		this._month = _month;
	}



	public int get_dayOfMonth() {
		return _dayOfMonth;
	}



	public void set_dayOfMonth(int _dayOfMonth) {
		this._dayOfMonth = _dayOfMonth;
	}



	public long get_nbJulian() {
		return _nbJulian;
	}



	public void set_nbJulian(long _nbJulian) {
		this._nbJulian = _nbJulian;
	}



	public int _day =0;
	public int _month =0;
	public int _dayOfMonth = 0;
	
	public static int dayOfMonth = 0;
	  /** 1 January 1970 */
    public final static long EPOCH = 2440588L;
    private long    _nbJulian =  EPOCH;
	 public static int getDayOfMonth() {
		return dayOfMonth;
	}



	public static void setDayOfMonth(int dayOfMonth) {
		DateU.dayOfMonth = dayOfMonth;
	}



	private static DateU  _today = null;

	  
	protected static final String DAYS_OF_WEEK[]={
        "SUN","MON","TUE","WED","THU","FRI","SAT"};
	protected static final String MONTHS[]={
        "JAN","FEB","MAR","APR","MAY","JUN","JUL", "AUG","SEP","OCT","NOV","DEC"};
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
    Date date = null;
    public Calendar getCal() {
		return cal;
	}



	public void setCal(Date date) {
		this.cal = Calendar.getInstance();
		cal.setTime(date);
	}



	Calendar cal = null;
    
	public DateU(Date date) {
		// TODO Auto-generated constructor stub
		createDate(date);
		this.date = date;
		this._year = getYear();
		this._month = getMonth();
		this._dayOfMonth = getDayOfMonth();
		this._nbJulian = toJulian( getYear(), getMonth(), getDayOfMonth());
		setCal(date);
	}

	final public boolean before(DateU date) {
        if (date==null) {
            return false;
        }
        return this._nbJulian < date._nbJulian;
    }

	final public boolean isWeekEndDay()
    {
        int dayOfWeek = getDayOfWeek();
        if (dayOfWeek == SATURDAY || dayOfWeek == SUNDAY) {
            return true;
        } else {
            return false;
        }
    }
	
	final public  static Date getlastDayofYear() 
	{
		Calendar lcalendar =CALENDAR;
		lcalendar.set(Calendar.YEAR,getYear() );
		lcalendar.set(Calendar.MONTH,11 );
	//	lcalendar.add(Calendar.YEAR,-1);
		
		return lcalendar.getTime();

	}
	 final public int getDayOfWeek()
	    {
	      return CALENDAR.get(CALENDAR.DAY_OF_WEEK);
	    }


	 
	 
	 
	public static DateU valueOf(Date date) {
		
		return new DateU(date);
	}

	
	static void createDate(Date d) {
	synchronized (CALENDAR) {
       // CALENDAR.setTimeZone(tz == null ? TimeZone.getDefault() : tz);
        CALENDAR.setTime(d);
        setYear(CALENDAR.get(Calendar.YEAR));
        setMonth(CALENDAR.get(Calendar.MONTH)+1);
        setDay(CALENDAR.get(Calendar.DATE));
        setDayOfMonth( CALENDAR.get(Calendar.DAY_OF_MONTH));
       
       
      
       // toJulian(Year,Month,dayOfMonth);
	}
    }
	
	
	public   void convertToCode(String name) {
	        int idx = name.indexOf('Y');
	        if (idx > 0) {
	             addYears(name, 0, idx);
	        }
	        idx = name.indexOf('M');
	        if (idx > 0) {
	             addMonths(name, 0, idx);
	        }
	        idx = name.indexOf('W');
	        if (idx > 0) {
	             addWeeks(name, 0, idx);
	        }
	        idx = name.indexOf('D');
	        if (idx > 0) {
	             addDays(name, 0, idx);
	        }
	       
	    }
	  
	
	public   void convertFrequecyCode(String name) {
        int idx = name.indexOf('P');
        if (idx == 0) {
             addYears("1Y", 0, 1);
        }
        idx = name.indexOf('S');
        if (idx == 0) {
             addMonths("6M", 0, 1);
        }
        idx = name.indexOf('Q');
        if (idx  == 0) {
             addMonths("3M", 0, 1);
        }
        idx = name.indexOf('M');
        if (idx == 0) {
             addMonths("1M", 0, 1);
        }
        idx = name.indexOf('W');
        if (idx == 0) {
             addDays("1W", 0, 1);
        }
        idx = name.indexOf('D');
        if (idx == 0) {
             addDays("1D", 0, 1);
        }
       
    }
  public static String getMonthName(int i) {
	  return MONTHS[i];
  }
	public void deductFrequecyCode(String name) {
		int idx = name.indexOf('P');
		if (idx == 0) {
			addYears("-1Y", 0, 2);
		}
		idx = name.indexOf('S');
		if (idx == 0) {
			addMonths("-6M", 0, 2);
		}
		idx = name.indexOf('Q');
		if (idx == 0) {
			addMonths("-3M", 0, 2);
		}
		idx = name.indexOf('M');
		if (idx == 0) {
			addMonths("-1M", 0, 2);
		}
		idx = name.indexOf('W');
		if (idx == 0) {
			addDays("-1W", 0, 2);
		}
		idx = name.indexOf('D');
		if (idx == 0) {
			addDays("-1D", 0, 2);
		}

	}
	  
	  void addYears(String yearPeriod, int startIndex, int endIndex) {
		  String PeriodText = yearPeriod.substring(startIndex, endIndex);
		  
		  CALENDAR.add(CALENDAR.YEAR, Integer.parseInt(PeriodText));
		  setYear(CALENDAR.get(Calendar.YEAR));
	        setMonth(CALENDAR.get(Calendar.MONTH)+1);
	        setDay(CALENDAR.get(Calendar.DATE));
	        setDate(CALENDAR.getTime());
	  }
	  
	  void addMonths(String yearPeriod, int startIndex, int endIndex) {
		  String PeriodText = yearPeriod.substring(startIndex, endIndex);
		  
		  CALENDAR.add(CALENDAR.MONTH, Integer.parseInt(PeriodText));
		  setYear(CALENDAR.get(Calendar.YEAR));
	        setMonth(CALENDAR.get(Calendar.MONTH)+1);
	        setDay(CALENDAR.get(Calendar.DATE));
	        setDate(CALENDAR.getTime());
	  }
	  
	  void addWeeks(String yearPeriod, int startIndex, int endIndex) {
		  String PeriodText = yearPeriod.substring(startIndex, endIndex);
		  
		  CALENDAR.add(CALENDAR.DAY_OF_MONTH, Integer.parseInt(PeriodText) *7 );
		  setYear(CALENDAR.get(Calendar.YEAR));
	        setMonth(CALENDAR.get(Calendar.MONTH)+1);
	        setDay(CALENDAR.get(Calendar.DATE));
	        setDate(CALENDAR.getTime());
	  }
	  void addDays(String yearPeriod, int startIndex, int endIndex) {
		  String PeriodText = yearPeriod.substring(startIndex, endIndex);
		  
		  CALENDAR.add(CALENDAR.DAY_OF_MONTH, Integer.parseInt(PeriodText) );
		  setYear(CALENDAR.get(Calendar.YEAR));
	        setMonth(CALENDAR.get(Calendar.MONTH)+1);
	        setDay(CALENDAR.get(Calendar.DATE));
	        setDate(CALENDAR.getTime());
	  }
	
	public void addDays(int days) {
		synchronized (CALENDAR) {
		       // CALENDAR.setTimeZone(tz == null ? TimeZone.getDefault() : tz);
		//	  System.out.println(CALENDAR.getTime());
		    //    CALENDAR.add(CALENDAR.DAY_OF_MONTH, days);
		    //    System.out.println(CALENDAR.getTime());
		    //    setYear(CALENDAR.get(Calendar.YEAR));
		     ///   setMonth(CALENDAR.get(Calendar.MONTH)+1);
		     //   setDay(CALENDAR.get(Calendar.DATE));
		     //   setDate(CALENDAR.getTime());
		        getCal().add(CALENDAR.DAY_OF_MONTH, days);
		        setYear(getCal().get(Calendar.YEAR));
		          setMonth(getCal().get(Calendar.MONTH)+1);
			       setDay(getCal().get(Calendar.DATE));
			       setDate(getCal().getTime());
		        
			}
	
	}
	public void addMonths(int months) {
		synchronized (CALENDAR) {
		       // CALENDAR.setTimeZone(tz == null ? TimeZone.getDefault() : tz);
		       
			/*CALENDAR.add(Calendar.MONTH,months);
			setYear(CALENDAR.get(Calendar.YEAR));
	        setMonth(CALENDAR.get(Calendar.MONTH)+1);
	        setDay(CALENDAR.get(Calendar.DATE));
	      //  set CALENDAR.getTime()
	        setDate(CALENDAR.getTime());*/
	        getCal().add(CALENDAR.MONTH,months);
	        setYear(getCal().get(Calendar.YEAR));
	          setMonth(getCal().get(Calendar.MONTH)+1);
		       setDay(getCal().get(Calendar.DATE));
		       setDate(getCal().getTime());
			}
	
	}
	 final public DateU addMonthsC(int months)
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
	        return valueOf(y, m, d);
	    }

	public void addYears(int years) {
		synchronized (CALENDAR) {
		       // CALENDAR.setTimeZone(tz == null ? TimeZone.getDefault() : tz);
		       
			/*CALENDAR.add(Calendar.YEAR,years);
			setYear(CALENDAR.get(Calendar.YEAR));
	        setMonth(CALENDAR.get(Calendar.MONTH)+1);
	        setDay(CALENDAR.get(Calendar.DATE));
	        setDate(CALENDAR.getTime()); */
	        getCal().add(CALENDAR.YEAR,years);
	        setYear(getCal().get(Calendar.YEAR));
	          setMonth(getCal().get(Calendar.MONTH)+1);
		       setDay(getCal().get(Calendar.DATE));
		       setDate(getCal().getTime());
			}
	
	}
	public int subractDates(Date newDate) {
		//System.out.println(" newDate  " + commonUTIL.getDateFormat(newDate) + " oldDate " + commonUTIL.getDateFormat(date) );
		
		
	//	return (currentDay - forgivenDateDays);
		int diffInDays = (int)( (date.getTime() - newDate.getTime())      / (1000 * 60 * 60 * 24));
		return diffInDays;
	}
	public static int getYear() {
		return Year;
	}
	public static void setYear(int Year) {
		DateU.Year = Year;
	}
	public static int getDay() {
		return Day;
	}
	public static void setDay(int Day) {
		DateU.Day = Day;
	}
	public static int getMonth() {
		return Month;
	}
	public static void setMonth(int Month) {
		DateU.Month = Month;
	}
	
	public static int yearDiff(Date newDate) {
		//System.out.println(" newDate  " + commonUTIL.getDateFormat(newDate) + " oldDate " + commonUTIL.getDateFormat(date) );
		
		
		Calendar calnewDate = Calendar.getInstance();
		calnewDate.setTime(newDate);
		int year = calnewDate.get(Calendar.YEAR);
		return year - getYear();
		
	}
	public static double diff(DateU startDate, DateU endDate) {
		//System.out.println(" newDate  " + commonUTIL.getDateFormat(newDate) + " oldDate " + commonUTIL.getDateFormat(date) );
		
		//System.out.println("ppp" );
		
		return endDate.get_nbJulian() - startDate.get_nbJulian();
		//return syear - eyear;
		
		
	}
	
	public static int getdayOftheYear() {
	
		return CALENDAR.get(Calendar.DAY_OF_YEAR);
		
	}
	
	
    




	public Date getDate() {
		return date;
	}

	 static public boolean isLeapYear(int year)
	    {
	        return year%4 == 0 && (year%100 != 0 || year%400 == 0);
	    }
	 static public int getDaysInYear(int year) {
	        return isLeapYear(year) ? 366 : 365;
	    }
   

	 final public int getMonthLength()
	    {
	        complete();
	        int months[] = isLeapYear() ? LEAP_MONTH_LENGTH : MONTH_LENGTH;
	        return  months[this.Month];
	    }
	    static final public int getMonthLength(int year, int month)
	    {
	        int months[] = isLeapYear(year) ? LEAP_MONTH_LENGTH : MONTH_LENGTH;
	        return  months[month];
	    }


	public void setDate(Date date) {
		this.date = date;
	}
	 public boolean isLeapYear()
	    {
	       
	        return isLeapYear(this.Year);
	    }


	 public static DateU valueOf(int y, int m, int d) {
	        return valueOf(toJulian(y, m, d));
	    }
	 protected DateU(long julian) {
	        this._nbJulian = julian;
	        CALENDAR.setTimeInMillis(_nbJulian);
	        setDate(CALENDAR.getTime());
	        setYear(CALENDAR.get(Calendar.YEAR));
	        setMonth(CALENDAR.get(Calendar.MONTH)+1);
	        setDay(CALENDAR.get(Calendar.DATE));
	        setDayOfMonth( CALENDAR.get(Calendar.DAY_OF_MONTH));
	      
	    }
	 public static  DateU valueOf(long julian) {
	        return new DateU(julian);
	    }
	public void nextBussinessDay() {
		// TODO Auto-generated method stub
		if(CALENDAR.get(CALENDAR.DAY_OF_WEEK) == SATURDAY) {
			addDays(2);
	} else if(CALENDAR.get(CALENDAR.DAY_OF_WEEK) == SUNDAY)
		addDays(1);
	}
	 final static private long IGREG=15+31*(10+12*1582);
	 private boolean _needComplete = true;

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
	
	 
	/**
	    * Compares this <code>DateU</code> to another and return
	    * true if this JDate is less than or equal to the passed one.
	    * @param date the date to be compared
	    * @return True or False
	    */
	    final public boolean lte(DateU date) {
	        return this._nbJulian < date._nbJulian;
	    }

	        /**
	    * Compares this <code>DateU</code> to another and return
	    * true if this JDate is greater than or equal to the passed one.
	    * @param date the date to be compared
	    * @return True or False
	    */
	    final public boolean gte(DateU date) {
	        return this._nbJulian > date._nbJulian;
	    }
	
}
