package beans;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Vector;

import util.commonUTIL;
import util.common.CDate;
import util.common.UtilDate;
 

/**
 * This class represents a rule used to generate a holiday calendar for a given
 * holiday. A single holiday rule may be used across many 
 * cities.<BR>
 * A rule may be:<BR>
 * 1.FIXED , it occurs on the same day each year.<BR>
 * 2.FLOATING,  A floating  holiday has a floating date but always occurs on the same
 * weekday (Monday, Tuesday, etc.<BR>
 * 3.EASTER, it is relative to easter.<BR>
 * 
 * for example to create a rule for christmas day, you need to create
 * a Fixed holiday with a month of DEC and a day of 25.<BR>
 * Based on this rule, one can generate the holiday 
 * calendar for any length of time.
 */
public class HolidayRule implements Externalizable {
	
	/**
     * String code indicating an Easter-related holiday that
     * is a fixed number of days from Easter of the current 
     * year.  For example, Easter Monday is one day after 
     * Easter.
     */
    final static public String EASTER="EASTER";

   

    final static  int I_EASTER=3;
   
    /**
     * String code indicating a fixed holiday. A fixed holiday occurs
     * on the same day of the month and the same month of the year every 
     * year.
     * 
     */

    final static public String FIXED="FIXED";
    final static  int I_FIXED=1;
    /**
     * String code indicating a floating holiday.  A floating 
     * holiday has a floating date but always occurs on the same
     * weekday (Monday, Tuesday, etc.) and is always the <i>n</i>th 
     * occurence or the last occurence of that weekday in the 
     * specified month.  For example, Labor Day in the U.S. is the
     * first Monday in September.  Another example is Memorial Day
     * in the U.S., which is the last Monday in May.
     */
    final static public String FLOAT="FLOAT";
    final static  int I_FLOAT=2;
    
    
    protected int    _id;
    protected String _name;
    protected int    _type;
    protected int    _easterOffset;
    protected int    _month; //start at 1
    protected int    _day; //1-31; -1 means it is not fixed
    protected int    _rank; //First, Second, Third, Fourth, Last; -1 means it is fixed
    protected int    _dayOfWeek; //1 is Mon, 2 is Tues...; -1 means it is fixed
    protected int    _saturdayRoll;
    protected int    _sundayRoll;
    protected String __user;
    protected int    __version;
    /**
     * Allocates a new <code>HolidayRule</code> object.
     */  
    public HolidayRule () {}

    /**
     * Allocates a new <code>HolidayRule</code> object for a
     * fixed holiday, and sets the day and month of this
     * holiday as well as the holiday's name.
     *
     * @param  day    the integer day (day of the month, 1-31) on 
     * which the holiday falls
     * @param  month   the <code>CDate</code> integer constant 
     * indicating the month in which the holiday falls
     * @param  name   the String name of the holiday
     */
    public HolidayRule (int day, int month, String name) {
	_type = I_FIXED;
	_day = day;
	_name = name;
	_month = month;
	_saturdayRoll=0;
	_sundayRoll=0;
    }

    /**
     * Allocates a new <code>HolidayRule</code> object for a 
     * floating holiday, and sets the month, the weekday, and the
     * ordinal rank indicating which occurence of the named weekday
     * is the holiday. Also sets the name of this holiday.
     *
     * @param  rank       the integer ordinal rank indicating which 
     * occurence of the named weekday is the holiday.  1 means first,
     * 2 means second, 3 means third, 4 means fourth, and 5 means last.
     * For example, for the US Memorial Day, you would set rank to 5,
     * indicating that Memorial Day will fall on the fifth Monday of 
     * the month or, if there are only four Mondays in May of the 
     * current year, on the fourth Monday of the month.
     *
     * @param  dayOfWeek  the <code>CDate</code> integer constant 
     * indicating the weekday on which the holiday falls.  Typically,
     * you would pass a value like <code>CDate.MONDAY</code>.
     *
     * @param  month      the <code>CDate</code> integer constant 
     * indicating the month in which the holiday falls
     *
     * @param  name       the String name of the holiday
     */
    public HolidayRule (int rank, int dayOfWeek, int month, String name) {
	_type = I_FLOAT;
	_rank = rank;
	_dayOfWeek = dayOfWeek;
	_month = month;
	_name = name;
	_saturdayRoll=0;
	_sundayRoll=0;
    }

    /**
     * Allocates a new <code>HolidayRule</code> object for an Easter 
     * fixed-offset holiday, and sets the name of the holiday and its 
     * offset in days from Easter.
     *
     * @param  offset offset in days from Easter.  A positive
     * value indicates that this holiday falls after Easter.
     * @param  name   the String name of the holiday
     */
    public HolidayRule (int offset,  String name) {
	_type = I_EASTER;
	_easterOffset = offset;
	_name = name;
	_saturdayRoll=0;
	_sundayRoll=0;
    }

    /**
     * Returns the integer id number of the holiday.
     *
     * @return the integer id number of the holiday
     */
    final public int getId() { return _id;}

    /**
     * Returns the String name of the holiday.
     *
     * @return the String name of the holiday
     */
    final public String getName() { return  _name;}

    /**
     * Returns the int type of the holiday.  
     *
     * @return the int type of the holiday
     */
    final public int getType() { return _type;   }

    /**
     * Returns the String type of the holiday.  This will be
     * FIXED, FLOAT, EASTER, or FIXED_CLOSE_WEED_END.
     *
     * @return the String type of the holiday
     */

    final public String getTypeAsString() { 
	switch(_type)     {
	case I_FLOAT: return FLOAT;
	case I_EASTER: return EASTER;
	default: return FIXED;
	}
    }

    /**
     * Returns this holiday's offset in days from Easter, if 
     * this holiday is an Easter fixed-offset holiday.
     *
     * @return an integer indicating this holiday's offset in days 
     * from Easter
     */
    final public int getEasterOffset() { return _easterOffset;}

    /**
     * Returns the integer month constant (as established in <code>
     * CDate</code>) indicating the month in which this holiday falls.
     *
     * @return the integer month constant (as established in <code>
     * CDate</code>) indicating the month in which this holiday falls
     */
    final public int getMonth() { return     _month;}

    /**
     * Returns the integer day of the month on which this holiday
     * falls, if this holiday is fixed-date holiday.  This will be a
     * value between 1 and 31.
     *
     * @return the integer day of the month on which this holiday
     * falls, if this holiday is fixed-date holiday
     */
    final public int getDay() { return  _day;}

    /**
     * For floating holidays, returns the integer ordinal rank 
     * indicating which occurence of the named weekday is this holiday.  
     * 1 means first, 2 means second, 3 means third, 4 means fourth, 
     * and 5 means last.
     * <p>
     * For example, for the US Memorial Day, <code>getRank()</code> 
     * would return a rank of 5, indicating that Memorial Day will 
     * fall on the fifth Monday of the month or, if there are only 
     * four Mondays in May of the current year, on the fourth Monday 
     * of the month.
     *
     * @return the integer ordinal rank indicating which occurence 
     * of the named weekday is this holiday.  1 means first, 2 means 
     * second, 3 means third, 4 means fourth, and 5 means last
     */
    final public int getRank() { return _rank;} 

    /**
     * For floating holidays, returns the integer weekday constant 
     * (as established in <code>CDate</code>) indicating the weekday 
     * on which this holiday falls.  1 means Sunday, 2 means Monday, 
     * and so on.
     *
     * @return the integer weekday constant (as established in <code>
     * CDate</code>) indicating the weekday on which this holiday falls.
     */
    final public int getDayOfWeek() {return _dayOfWeek; } 
  

    /**
     * Sets the integer id number of the holiday.
     *
     * @param  s  the integer id number of the holiday
     */
    final public void setId(int s) {  _id = s; __key = null;}

    /**
     * Sets the String name of the holiday.
     *
     * @param  s  the String name of the holiday
     */
    final public void setName(String s) {    _name = s;}

    /**
     * Sets the String type code indicating the type of this holiday.  
     * This code will be FIXED, FLOAT, EASTER, or FIXED_CLOSE_WEEK_END.
     *
     * @param  s  the String type code indicating the type of this 
     * holiday
     */
    final public void setType(int s) {   _type=s;}
    final public void setTypeAsString(String s) {
       
        if (s.equals(FIXED))
            _type = I_FIXED;
        else if (s.equals(FLOAT))
            _type = I_FLOAT;
        else if (s.equals(EASTER))
            _type = I_EASTER;
    }

    /**
     * Sets this holiday's offset in days from Easter, if 
     * this holiday is an Easter fixed-offset holiday.
     *
     * @param  s  an integer indicating this holiday's offset in days 
     * from Easter
     */
    final public void setEasterOffset(int s) {   _easterOffset =s;}

    /**
     * Sets the integer month constant (as established in <code>
     * CDate</code>) indicating the month in which this holiday falls.
     *
     * @param  s  the integer month constant (as established in <code>
     * CDate</code>) indicating the month in which this holiday falls
     */
    final public void setMonth(int s) {       _month = s;}

    /**
     * Sets the integer day of the month on which this holiday
     * falls, if this holiday is fixed-date holiday.  This will be a
     * value between 1 and 31.
     *
     * @param  s  the integer day of the month on which this holiday
     * falls, if this holiday is fixed-date holiday
     */
    final public void setDay(int s) {    _day = s;}

    /**
     * For floating holidays, sets the integer ordinal rank 
     * indicating which occurence of the named weekday is this holiday.  
     * 1 means first, 2 means second, 3 means third, 4 means fourth, 
     * and 5 means last.
     * <p>
     * For example, for the US Memorial Day, <code>getRank()</code> 
     * would return a rank of 5, indicating that Memorial Day will 
     * fall on the fifth Monday of the month or, if there are only 
     * four Mondays in May of the current year, on the fourth Monday 
     * of the month.
     *
     * @param  s  the integer ordinal rank indicating which occurence 
     * of the named weekday is this holiday.  1 means first, 2 means 
     * second, 3 means third, 4 means fourth, and 5 means last
     */
    final public void setRank(int s) {   _rank = s;} 

    /**
     * For floating holidays, sets the integer weekday constant 
     * (as established in <code>CDate</code>) indicating the weekday 
     * on which this holiday falls.  1 means Sunday, 2 means Monday, 
     * and so on.
     *
     * @param  s  the integer weekday constant (as established in <code>
     * CDate</code>) indicating the weekday on which this holiday falls.
     */
    final public void setDayOfWeek(int s) {  _dayOfWeek = s; } 

    /**
     * Generates the calendar of holidays according to this holiday 
     * rule.  The calendar will extend from the specified starting year 
     * through the specified ending year.
     *
     * @param  yearFrom  the first year for which the calendar of 
     * holidays will be generated
     * @param  yearTo    the last year for which the calendar of 
     * holidays will be generated
     */  
    public Vector generate(int yearFrom,int yearTo) {
	Vector v = new Vector();
	switch(_type) {
	case I_EASTER: {
	    for(int y=yearFrom;y<=yearTo;y++) {
		CDate jdate = UtilDate.computeEaster(y);
		jdate = jdate.addDays(_easterOffset);
		//if(!jdate.isWeekEndDay()) 
		v.addElement(jdate);
	    }
	    break;
	}
	case I_FLOAT: {
	    for(int y=yearFrom;y<=yearTo;y++) {
		CDate jdate = UtilDate.getJDate(y,_month,_dayOfWeek,_rank);
		//if(!jdate.isWeekEndDay()) 
		v.addElement(jdate);
	    }
	    break;
	}
	default:   {
	    for(int y=yearFrom;y<=yearTo;y++) {
		CDate jdate = CDate.valueOf(y,_month,_day);
		jdate = rollWeekEnd(jdate,_saturdayRoll,_sundayRoll);
		//if(!jdate.isWeekEndDay()) 
		v.addElement(jdate);
	    }
	}
	}
	return v;
    }
    
    /**
     * Returns a hash code value for this object.
     * 
     * @return a hash code value to enable quick lookup of 
     * this object
     */
    public int hashCode() {
	return _name.hashCode();
    }

    /**
     * Compares a holiday rule to this one.  The result is true if 
     * and only if the argument is not <code>null</code> and is a 
     * <code>HolidayRule</code> object that represents the same rule 
     * as this object.
     *
     * @param o   the <code>HolidayRule</code> object to be compared
     * @return <code>true</code> if the objects are the same; <code>false
     * </code> otherwise
     */
    public boolean equals(Object o) {
	HolidayRule hr=(HolidayRule)o;
	return _name.equals(hr.getName());
    }

    /**
     * Returns a string containing the name of this 
     * holiday rule object.
     *
     * @return a String containing the name of this 
     * holiday rule object
     */
    public String toString() {
	return "Holiday Rule : " + _name ;
    }
    
    /**
     * Clones this object
     * @return A copy of this object.
     */ 
    public Object clone() throws CloneNotSupportedException {
	return super.clone();
    }
    /**
     * determine if a date is a good business day or not.
     * @param date the date to check
     * @return a <code>boolean</code> true or false.
     */
    public boolean isBusinessDay(CDate date) {
	switch(_type) {
	case I_EASTER: {
	    CDate jdate = UtilDate.computeEaster(date.getYear());
	    jdate = jdate.addDays(_easterOffset);
	    return !jdate.equals(date);
	}
	case I_FLOAT: {
	    CDate jdate = UtilDate.getJDate(date.getYear(),
					    _month,_dayOfWeek,_rank);
	    return !jdate.equals(date);
	}
	default: {
	    if(_saturdayRoll==0 && _sundayRoll==0) {
		if(date.getMonth()==_month &&
		   date.getDayOfMonth()==_day) return false;
		else return true;
	    }
	    int year=date.getYear();
	    CDate jdate = CDate.valueOf(year,_month,_day);
	    jdate = rollWeekEnd(jdate,_saturdayRoll,_sundayRoll);
	    int mm=jdate.getMonth();
	    if(mm> 1 && mm < 12)  return !jdate.equals(date);
	    else {
		if(jdate.equals(date)) return false;
		//need to try date.getYear() +1 and date.getYear()-1
		//in case we are at the end ot begining of the year
		//very hard to find BUG
		jdate=CDate.valueOf(year+1,_month,_day);
		jdate= rollWeekEnd(jdate,_saturdayRoll,_sundayRoll);
		if(jdate.equals(date)) return false;
		jdate=CDate.valueOf(year,_month,_day);
		jdate= rollWeekEnd(jdate,_saturdayRoll,_sundayRoll);
		if(jdate.equals(date)) return false;
		return true;
	    }
	}
	}
    }
    /**
     * Set the number of days to roll a fixed holiday forward
     * in the event that this holiday happens to fall on a 
     * Saturday
     * @param days an <code>int</code>, number of days to roll forward.
     */
    final public void setSaturdayRoll(int days) {
      
        _saturdayRoll= days;
    }

    /**
     * Set the number of days to roll a fixed holiday forward
     * in the event that this holiday happens to fall on a 
     * Sunday
     * @param days an <code>int</code>, number of days to roll forward.
     */

    final public void setSundayRoll(int days) {
      
        _sundayRoll= days;
    }

    /**
     * get the number of days to roll a fixed holiday forward
     * in the event that this holiday happens to fall on a 
     * Saturday
     * @return an <code>int</code>, number of days to roll forward.
     */
    final public int getSaturdayRoll() {
	return _saturdayRoll;
    }
    /**
     * get the number of days to roll a fixed holiday forward
     * in the event that this holiday happens to fall on a 
     * Sunday
     * @return an <code>int</code>, number of days to roll forward.
     */
    final public int getSundayRoll() {
	return _sundayRoll;
    }

    static protected CDate  rollWeekEnd(CDate jdate,int saturdayRoll,
					int sundayRoll) {
	switch(jdate.getDayOfWeek()) {
	case CDate.SATURDAY: 
	    if(saturdayRoll==0) return jdate;
	    else return jdate.addDays(saturdayRoll);
	case CDate.SUNDAY:
	    if(sundayRoll==0) return jdate;
	    else return jdate.addDays(sundayRoll);
	default: return jdate;
	}
    }
    /**
     *Writes the specified <code>HolidayRule</code> object to the ObjectOutput
     *@param out the <code>ObjectOutput</code> to write to.
     *@throws IOException Signals that an exceptional condition has occurred during
     *input or output.
     */
      
    public void writeExternal(ObjectOutput out) 
	throws IOException {
	commonUTIL.writeUTF(_name,out);
	out.writeInt(_id);
	out.writeInt(_type);
	out.writeInt(_easterOffset);
	out.writeInt(_month); 
	out.writeInt(_day); 
	out.writeInt(_rank); 
	out.writeInt(_dayOfWeek);
	out.writeInt(_saturdayRoll);
	out.writeInt(_sundayRoll);
	out.writeInt(__version);
	commonUTIL.writeUTF(__user,out);
    }
  /**
   *Read the specified <code>HolidayRule</code> from the <code>ObjectInput</code>
   *@param in the <code>ObjectInput</code> from which to read.
   *@throws IOException Signals that an exceptional condition has occurred during
   *input or output.
   *@throws ClassNotFoundException Thrown when no definition for a class is found.
   */
    public void readExternal(ObjectInput in) 
	throws IOException,ClassNotFoundException {
	_name=commonUTIL.readUTF(in);
	_id=in.readInt();
	_type=in.readInt();
	_easterOffset=in.readInt();
	_month=in.readInt(); 
	_day=in.readInt(); 
	_rank=in.readInt(); 
	_dayOfWeek=in.readInt();
	_saturdayRoll=in.readInt();
	_sundayRoll=in.readInt();
	__version =in.readInt();
	__user = commonUTIL.readUTF(in);
    }
     

    public String getAuthName(){
	return _name ;
    }

    private transient Integer __key; 
    public Object getKey() {
        if(__key == null)
            __key = Integer.valueOf(getId());
        return __key;
    }

    public String cacheDescription() {
        return getName();
    }

    public boolean isValid(Vector errors) {
	boolean ret = true;
        if (getName().length() > 32) {
	    errors.add("Holiday Rule name has a maximum length of 32 positions");
	    ret = false;
        }
	if (getType() == I_FIXED && 
	    (getDay() <= 0 || getDay() > 31)) {
	    errors.add("Invalid Day of Month " + getDay());
	    ret = false;
	} 
	return ret;
    }

}
