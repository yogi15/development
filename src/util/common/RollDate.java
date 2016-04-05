package src.util.common;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import util.common.holiday.Holiday;
  
public class RollDate implements  Cloneable, Externalizable {
	

/**
 * This class is used to adjust relevant dates that would otherwise
 * fall on a day that is not a business day.
 * Valid business day conventions are
 * NONE, Preceding , Following, Modified Following, Modified Preceding,
 * IMM Mondays, IMM Wednesdays, Month End, and Modified Succeeding
 */
	

    final public  static int NONE=0;
    final public  static int PRECEDING=1;
    final public  static int FOLLOWING=2;
    final public  static int MOD_FOLLOWING=3;
    final public  static int MOD_PRECEDING=4;
    final public  static int IMM_MON=5;
    final public  static int IMM_WED=6;
    final public  static int END_MONTH=7;
    final public  static int MOD_SUC=8;
    final public  static int SFE=9;


    final public  static String S_NONE="NO_CHANGE";
    final public  static String S_PRECEDING="PRECEDING";
    final public  static String S_FOLLOWING="FOLLOWING";
    final public  static String S_MOD_FOLLOWING="MOD_FOLLOW";
    final public  static String S_MOD_PRECEDING="MOD_PRECED";
    final public  static String S_END_MONTH="END_MONTH";
    final public  static String S_IMM_MON="IMM_MON";
    final public  static String S_IMM_WED="IMM_WED";
    final public  static String S_MOD_SUC="MOD_SUCC";
    final public  static String S_SFE="SFE";

    final public  static RollDate R_NONE =new RollDate(NONE);
    final public  static RollDate R_PRECEDING=new RollDate(PRECEDING);
    final public  static RollDate R_FOLLOWING=new RollDate(FOLLOWING);
    final public  static RollDate R_MOD_FOLLOWING=new RollDate(MOD_FOLLOWING);
    final public  static RollDate R_MOD_PRECEDING=new RollDate(MOD_PRECEDING);
    final public  static RollDate R_END_MONTH=new RollDate(END_MONTH);
    final public  static RollDate R_IMM_MON=new RollDate(IMM_MON);
    final public  static RollDate R_IMM_WED=new RollDate(IMM_WED);
    final public  static RollDate R_MOD_SUC=new RollDate(MOD_SUC);
    final public  static RollDate R_SFE=new RollDate(SFE);

    private int _dr;
/**
 *Returns the int that represents the business day convention
 *@return The business day convention
 */
    final public int getCode() { return _dr;}
/**
 *Returns a vector of the business day convention values
 *@return A vector of the business day convention values
 */
    static public Vector getDomain()
    {
	Vector v = new Vector();
	v.addElement(S_NONE);
	v.addElement(S_PRECEDING);
	v.addElement(S_FOLLOWING);
	v.addElement(S_MOD_FOLLOWING);
	v.addElement(S_MOD_PRECEDING);
	v.addElement(S_IMM_MON);
	v.addElement(S_IMM_WED);
	v.addElement(S_END_MONTH);
	v.addElement(S_MOD_SUC);
	v.addElement(S_SFE);

	return v;
    }

    /**
     * @param date the CDate to use for the roll
     * @param cityList the Vector whose entries contain cities
     * @return the CDate requested
     * @throws IllegalArgumentException if the Parameter CDate is null.
     */
    static private CDate rollPreceeding(CDate date, Vector cityList) throws IllegalArgumentException
    {
      if(date == null) {
        IllegalArgumentException e = new IllegalArgumentException("Parameter CDate is null");
        // Log.error("RollDate", e);
        throw e;
      }
        Holiday hol = Holiday.getCurrent();
        if(hol == null) {
           // Log.error("RollDate", "Holiday is null, Holiday.setCurrent() may be called to set this.");
           //original date returned which is the same as assuming that
           //all days are business days when no Holiday is available
           return date;
        }
        if (hol.isBusinessDay(date, cityList)) return CDate.valueOf(date);
        else return hol.previousBusinessDay(date, cityList);

    }

    /**
     * @param date the CDate to use for the roll
     * @param cityList the Vector whose entries contain cities
     * @return the CDate requested
     * @throws IllegalArgumentException if the Parameter CDate is null.
     */
    static private CDate rollFollowing(CDate date, Vector cityList) throws IllegalArgumentException
    {
      if(date == null) {
        IllegalArgumentException e = new IllegalArgumentException("Parameter CDate is null");
        // Log.error("RollDate", e);
        throw e;
      }
        Holiday hol = Holiday.getCurrent();
        if(hol == null) {
           // Log.error("RollDate", "Holiday is null, Holiday.setCurrent() may be called to set this.");
           //original date returned which is the same as assuming that
           //all days are business days when no Holiday is available
           return date;
        }
        if (hol.isBusinessDay(date, cityList)) return CDate.valueOf(date);
        else return hol.nextBusinessDay(date, cityList);
    }

    /**
     * @param date the CDate to use for the roll
     * @param cityList the Vector whose entries contain cities
     * @return the CDate requested
     * @throws IllegalArgumentException if the Parameter CDate is null.
     */
    static private CDate rollModFollowing(CDate date, Vector cityList) throws IllegalArgumentException
    {
      if(date == null) {
        IllegalArgumentException e = new IllegalArgumentException("Parameter CDate is null");
        // Log.error("RollDate", e);
        throw e;
      }
        Holiday hol = Holiday.getCurrent();
        if(hol == null) {
           // Log.error("RollDate", "Holiday is null, Holiday.setCurrent() may be called to set this.");
           //original date returned which is the same as assuming that
           //all days are business days when no Holiday is available
           return date;
        }
        if (hol.isBusinessDay(date, cityList)) return CDate.valueOf(date);

        CDate dt = hol.nextBusinessDay(date, cityList);
        if (date.getMonth() != dt.getMonth()) // must be in same month
	    {
		dt = hol.previousBusinessDay(date, cityList);
	    }

        return dt;
    }

    private interface IMMDateable {
    	public CDate getIMMDate(CDate date);
    }
    
    static private CDate rollIMM(CDate date, Vector cityList, IMMDateable immDate) {
    	CDate rolledDate = immDate.getIMMDate(date);
    	if (rolledDate.getDayOfMonth() < date.getDayOfMonth()) {
    	    rolledDate = immDate.getIMMDate(date.addMonths(1));
    	}
    	RollDate dr = new RollDate(RollDate.PRECEDING);
    	rolledDate = dr.roll(rolledDate, cityList);
    	return rolledDate;
    }
    
    static private CDate rollIMMMon(CDate date, Vector cityList) {
    	return rollIMM(date, cityList, new IMMDateable() {
    		public CDate getIMMDate(CDate date){
    			CDate rolledDate = (UtilDate.getIMMDate(date, CDate.WEDNESDAY));
    	    	return rolledDate.addDays(-2);
    		}
    	});
    }
    
    static private CDate rollIMMWed(CDate date, Vector cityList) {
    	return rollIMM(date, cityList, new IMMDateable() {
    		public CDate getIMMDate(CDate date){
    			return UtilDate.getIMMDate(date, CDate.WEDNESDAY);
    		}
    	});
    }
/**
 * Allocates a new <code>RollDate</code> object with a
 * Business day convention of NONE.
 *
 */
    public RollDate() { _dr = NONE; }
/**
 * Allocates a new <code>RollDate</code> object and specifies the
 * business day convention.
 * Valid Codes for the business day convention are NONE, PRECEDING,
 * FOLLOWING, MOD_FOLLOWING, MOD_PRECEDING, END_MONTH, IMM_MON, IMM_WED,
 * MOD_SUC
 * @param dateRoll the Business day convention.
 *
 */
    private RollDate(int dateRoll) //throws DomainViolationException
    {
        switch (dateRoll)
	    {
	    case NONE:
	    case PRECEDING:
	    case FOLLOWING:
            case MOD_FOLLOWING:
            case MOD_PRECEDING:
            case END_MONTH:
  	    case IMM_MON:
	    case IMM_WED:
	    case MOD_SUC:
	    case SFE:
		{
		    _dr = dateRoll;
		    break;
		}
            default:
    	        throw new Error("RollDate corrupted");
	    }
    }

    public static RollDate valueOf(int dateRoll) //throws DomainViolationException
    {
      return get(dateRoll);
    }
    public static RollDate valueOf(String dateRoll) //throws DomainViolationException
    {
      return get(dateRoll);
    }
    public static RollDate valueOf() //throws DomainViolationException
    {
      return R_NONE;
    }

/**
 * Returns an <code>integer</code> representation of the Business day convention.
 * Returns -1 if an invalid Business day convention is specified.
 * @return an <code>integer</code> representation of the Business day convention.
 * @param s The Business day convention to convert.
 */
    static public int fromString(String s) //throws DomainViolationException
    {   if (s==null) return NONE;
        if (s.equals(S_NONE)) return NONE;
        if (s.equals(S_PRECEDING)) return  PRECEDING;
        if (s.equals(S_FOLLOWING)) return FOLLOWING;
        if (s.equals(S_MOD_FOLLOWING)) return  MOD_FOLLOWING;
        if (s.equals(S_MOD_PRECEDING)) return  MOD_PRECEDING;
        if (s.equals(S_END_MONTH)) return  END_MONTH;
	if (s.equals(S_IMM_MON)) return IMM_MON;
	if (s.equals(S_IMM_WED)) return IMM_WED;
	if (s.equals(S_MOD_SUC)) return MOD_SUC;
	if (s.equals(S_SFE)) return SFE;
	// Log.error("Bad RollDate name " + s, new Throwable());
        //throw new DomainViolationException(s);
        return NONE;
    }
/**
 * Returns a <code>String</code> representation of the Business day convention.
 * @return a <code>String</code> representation of the Business day convention.
 */
    public String toString()
    {
        switch(_dr)
	    {
            case NONE: return S_NONE;
            case PRECEDING: return S_PRECEDING;
            case FOLLOWING: return S_FOLLOWING;
            case MOD_FOLLOWING: return S_MOD_FOLLOWING;
            case MOD_PRECEDING: return S_MOD_PRECEDING;
            case END_MONTH: return S_END_MONTH;
   	    case IMM_MON: return S_IMM_MON;
	    case IMM_WED: return S_IMM_WED;
	    case MOD_SUC: return S_MOD_SUC;
	    case SFE: return S_SFE;
            default: throw new Error("RollDate corrupted");
	    }
    }
/**
 * Returns a date adjusted in accordance with the business
 * day convention and the specified holidays.
 *
 * @param date The date to be adjusted.
 * @param cityList a Vector of holiday centers.
 * @throws IllegalArgumentException if the Parameter CDate is null.
 * @return the CDate requested
 */
    public CDate roll(CDate date, Vector cityList)
    {
      if(date == null) {
        IllegalArgumentException e = new IllegalArgumentException("Parameter CDate is null");
        // Log.error("RollDate", e);
        throw e;
      }
        switch(_dr)
	    {
            case NONE: return CDate.valueOf(date);
            case PRECEDING: return rollPreceeding(date, cityList);
            case FOLLOWING: return rollFollowing(date, cityList);
            case MOD_FOLLOWING: return rollModFollowing(date, cityList);
            case MOD_PRECEDING: return rollModPreceding(date, cityList);
            case END_MONTH: return rollEndMonth(date, cityList);
            case IMM_MON: return rollIMMMon(date, cityList);
            case IMM_WED: return rollIMMWed(date, cityList);
	    case MOD_SUC: return rollModSucceeding(date, cityList);
	    case SFE: return rollSydneyFutureExchange(date, cityList);
            default: throw new Error("RollDate corrupted");
	    }
    }

/**
 * Returns a RollDate object with the specified
 * Business Day convention
 * @param s A String that represents the business day convention
 */
    static public RollDate get(String s)
    {
	if (s==null) return R_NONE;
        if (s.equals(S_NONE)) return R_NONE;
        if (s.equals(S_PRECEDING)) return  R_PRECEDING;
        if (s.equals(S_FOLLOWING)) return R_FOLLOWING;
        if (s.equals(S_MOD_FOLLOWING)) return  R_MOD_FOLLOWING;
        if (s.equals(S_MOD_PRECEDING)) return  R_MOD_PRECEDING;
        if (s.equals(S_END_MONTH)) return  R_END_MONTH;
	if (s.equals(S_IMM_MON)) return R_IMM_MON;
	if (s.equals(S_IMM_WED)) return R_IMM_WED;
	if (s.equals(S_MOD_SUC)) return R_MOD_SUC;
	if (s.equals(S_SFE)) return R_SFE;
        return null;
    }
/**
 * Returns a RollDate object with the specified
 * Business Day convention.
 *
 * @param code an integer that represents the business day convention
 */
    static public RollDate get(int code)
    {
	switch(code) {
        case NONE:  return R_NONE;
	case PRECEDING:  return  R_PRECEDING;
	case FOLLOWING:  return R_FOLLOWING;
	case MOD_FOLLOWING:  return  R_MOD_FOLLOWING;
	case MOD_PRECEDING:  return  R_MOD_PRECEDING;
	case END_MONTH:  return  R_END_MONTH;
	case IMM_MON:  return R_IMM_MON;
	case IMM_WED:  return R_IMM_WED;
	case MOD_SUC: return R_MOD_SUC;
	case SFE: return R_SFE;
	default: return null;
	}
    }
    /**
     * @param date the CDate to use for the roll
     * @param cityList the Vector whose entries contain cities
     * @return the CDate requested
     * @throws IllegalArgumentException if the Parameter CDate is null.
     */
    static private CDate rollModPreceding(CDate date, Vector cityList) throws IllegalArgumentException
    {
      if(date == null) {
        IllegalArgumentException e = new IllegalArgumentException("Parameter CDate is null");
        // Log.error("RollDate", e);
        throw e;
      }
        Holiday hol = Holiday.getCurrent();
        if(hol == null) {
           // Log.error("RollDate", "Holiday is null, Holiday.setCurrent() may be called to set this.");
           //original date returned which is the same as assuming that
           //all days are business days when no Holiday is available
           return date;
        }
	CDate rolledDate;
        if (hol.isBusinessDay(date, cityList))  rolledDate = CDate.valueOf(date);
        else rolledDate= hol.previousBusinessDay(date, cityList);
	if(rolledDate.getMonth() != date.getMonth()) {
	    return hol.nextBusinessDay(date, cityList);
	}
	else return rolledDate;
    }
    static private CDate rollEndMonth(CDate date, Vector cityList)
    {
	//to be checked
	CDate rdate= date.getEOM();
	rdate = rollPreceeding(rdate, cityList);

	return rdate;
    }
/**
 * Returns a copy of this object.
 */
    public Object clone() throws CloneNotSupportedException {
	return super.clone();
    }

    /**
     * Compares this object to another and returns true if they
     * are the same.
     *
     * @param o the <code>Object</code> to be compared to.
     * @return true if objects are equals, false otherwise.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RollDate dateRoll = (RollDate) o;

        if (_dr != dateRoll._dr) return false;

        return true;
    }

    public int hashCode() {
        return _dr;
    }

    public void writeExternal(ObjectOutput out)
	throws IOException {
	out.writeInt(_dr);
    }
    public void readExternal(ObjectInput in)
	throws IOException,ClassNotFoundException {
	_dr=in.readInt();
    }
    public Object readResolve() {
    	return RollDate.valueOf(_dr);
    }
    /**
     * this is for Australian
     * Bank bills are typically quoted in early or late month tranches
     * and hence the actual maturity date of the tenor is determined on
     * a modified succeeding basis (i.e. if the straight date for a tenor
     * falls on a weekend or public holiday in Sydney, the date will be
     * the next business day unless that day crosses the mid-month (15th),
     * or end of a month then it will be the preceding business day).
     * @param date the CDate to use for the roll
     * @param cityList the Vector whose entries contain cities
     * @return the CDate requested
     * @throws IllegalArgumentException if the Parameter CDate is null.
     */
    static private CDate rollModSucceeding(CDate date, Vector cityList) throws IllegalArgumentException
    {
      if(date == null) {
        IllegalArgumentException e = new IllegalArgumentException("Parameter CDate is null");
        // Log.error("RollDate", e);
        throw e;
      }
        Holiday hol = Holiday.getCurrent();
        if(hol == null) {
           // Log.error("RollDate", "Holiday is null, Holiday.setCurrent() may be called to set this.");
           //original date returned which is the same as assuming that
           //all days are business days when no Holiday is available
           return date;
        }
        if (!hol.isBusinessDay(date, cityList))  {
	    CDate dt = hol.nextBusinessDay(date, cityList);
	    if (date.getMonth() != dt.getMonth() ||
		(date.getDayOfMonth() <= 15 && dt.getDayOfMonth() >= 15)) {
		return hol.previousBusinessDay(date, cityList);
	    }
	    else return dt;
	}
	else return date;
    }
    private static CDate thursdayBeforeSecondFriday(CDate date,int month) {
	CDate tmp= CDate.valueOf(date.getYear(),month,1);
	int days=UtilDate.getNumberOfDaysToNextWeekDay(tmp,CDate.FRIDAY);
	tmp=tmp.addDays(days + 6);
	return tmp;
    }
    private static CDate calcSFE(CDate date) {
	//returns the Thursday prior to second friday of this month
	//if date is before this date
	//Try March
	CDate tmp= thursdayBeforeSecondFriday(date,CDate.MARCH);
	if(date.lte(tmp)) return tmp;
	//Try June
	 tmp= thursdayBeforeSecondFriday(date,CDate.JUNE);
	if(date.lte(tmp)) return tmp;
	//Try June
	 tmp= thursdayBeforeSecondFriday(date,CDate.SEPTEMBER);
	if(date.lte(tmp)) return tmp;
	//Try December
	tmp= thursdayBeforeSecondFriday(date,CDate.DECEMBER);
	if(date.lte(tmp)) return tmp;
	return calcSFE(date.addMonths(1));
    }
    
    private static CDate calcSFE(CDate date, int month){
    	switch (month) {
		case CDate.MARCH :
			return thursdayBeforeSecondFriday(date,CDate.MARCH);
		case CDate.JUNE :
			return thursdayBeforeSecondFriday(date,CDate.JUNE);
		case CDate.SEPTEMBER :
			return thursdayBeforeSecondFriday(date,CDate.SEPTEMBER);
		case CDate.DECEMBER :
			return thursdayBeforeSecondFriday(date,CDate.DECEMBER);
		default:
			return calcSFE(date.addMonths(1), month);
		}
    }
    static private CDate rollSydneyFutureExchange(CDate date, Vector cityList)
    {
    	CDate dt = null;
	//thursday prior to the second friday of march, june, sept and dec.
    	if(date.getMonth() == CDate.MARCH ||date.getMonth() == CDate.JUNE ||
    			date.getMonth() == CDate.SEPTEMBER || date.getMonth() == CDate.DECEMBER)
    		dt = calcSFE(date, date.getMonth());
    	else
    		dt=calcSFE(date);
	return rollPreceeding(dt,cityList);
    }
    
    /**
     * @return a List of RollDate in same order as {@link #getDomain()}
     */
    public static List<RollDate> values() {
        List<RollDate> drList = new ArrayList<RollDate>();
        List<String> domain = getDomain();
        for (String domainValue: domain)
            drList.add(valueOf(domainValue));
        return drList;
    }

 

}
