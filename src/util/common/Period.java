package util.common;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import apps.window.tradewindow.BackOfficePanel;

import util.ClassInstantiateUtil;
import util.commonUTIL;


public class Period implements Comparable, Cloneable {

	

	 
	    /**
	     * map of the string representation of a Period to the numeric representation of the Period
	     */
	    static private Hashtable _name2code = null;
	    /**
	     * map of the numeric representation to the string representation of the Period
	     */
	    static private Hashtable _code2name = null;
	    /**
	     * The names of the Periods in increasing numeric order
	     */
	    static private String _names[] = null;
	    /**
	     * Lock for controlling access to the three caches.
	     */
	    static private final Object __lock = new Object();

	    /**
	     * The numeric representation of this Period
	     */
	    protected int _code;
	    /**
	     * The string representation of this Period
	     */
	    protected String _name;

	    /**
	     * Allocates a new Period object.
	     */
	    public Period() {}

	    /**
	     * Create a copy of the given Period instance.
	     *
	     * @param t
	     */
	    public Period(Period t) {
	        _code = t._code;
	        _name = t._name;
	    }

	    /**
	     * Creates a new Period with the numeric representation given by the code parameter
	     *
	     * @param code the number of days for this Period
	     */
	    public Period(int code) {
	        _code = code;
	        _name = code2String(_code);
	    }

	    /**
	     * Creates a new Period which value is defined by the parameter.
	     *
	     * @param name a String representation of this Period
	     */
	    public Period(String name) {
	        _name = name;
	        _code = string2Code(_name);
	    }

	    /**
	     * Return the numeric representation of the Period. This is the number of
	     * days for the Period.
	     *
	     * @return the numeric representation of the product
	     */
	    final public int getCode() {
	        return _code;
	    }

	    /**
	     * Return the string representation of the Period.
	     *
	     * @return the string representation of the Period.
	     */
	    final public String getName() {
	        return _name;
	    }

	    /**
	     * Returns true if this Period is equal to the passed in parameter,
	     * or false otherwise.
	     *
	     * @param o an Object to compare to
	     */
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Period Period = (Period) o;
	        return _code == Period._code;
	    }

	    /**
	     * Returns the hashCode of the value of this Period.
	     */
	    public int hashCode() {
	        return _code;
	    }

	    /**
	     * Returns an integer describing the comparison between this Period and
	     * the passed in parameter.
	     *
	     * @param obj an Object to compare to
	     *
	     * @return -1 if smaller, 0 if equal, or +1 if greater
	     */
	    final public int compareTo(Object  obj) {
	        Period j = (Period) obj;
	        if (_code < j._code) {
	            return -1;
	        } else if (_code == j._code) {
	            return 0;
	        } else {
	            return 1;
	        }
	    }    

	    /**
	     * Returns the numeric representation for the string representation, <code>name</code>.
	     *
	     * @param name a String representation of a Period
	     * @return the numeric representation of the string representaion.
	     */
	    public static int string2Code(String name) {
	        
	        return convertToCode(name);
	    }

	    public static Period valueOf(String code) {
	    	return new Period(code);
	    }
	    
	    public static Period valueOf(int code) {
	    	return new Period(code);
	    }
	    
	 /*   public Frequency toFrequency() {
	        if (getCode()%30==0){
	            int numMonths = getCode()/30;
	            switch(numMonths){
	            case 1:
	                return Frequency.F_MONTHLY;
	            case 3:
	                return Frequency.F_QUARTERLY;
	            case 6:
	                return Frequency.F_SEMI_ANNUAL;
	            case 12:
	                return Frequency.F_ANNUAL;
	                default:
	                    return null;
	            }
	        }
	        return null;
	    } */
	    
	    public static String returnFrequencyType(String name) {
	        int idx = name.indexOf('Y');
	        if (idx > 0) {
	            return "Y";
	        }
	        idx = name.indexOf('M');
	        if (idx > 0) {
	            return "M";
	        }
	        idx = name.indexOf('W');
	        if (idx > 0) {
	            return "W";
	        }
	        idx = name.indexOf('D');
	        if (idx > 0) {
	            return "D";
	        }
	        return null;
	    }

	    
	    /**
	     * Return the code equivalent of the Period name supplied. The Period should
	     * be of the form nnn[YMWD]. Examples would be 10Y or 1W.
	     *
	     * @param name
	     *            The string representation of the Period
	     * @return The numeric representation of the Period.
	     */
	    private static int convertToCode(String name) {
	        int idx = name.indexOf('Y');
	        if (idx > 0) {
	            return convertYearPeriodToCode(name, 0, idx);
	        }
	        idx = name.indexOf('M');
	        if (idx > 0) {
	            return convertMonthPeriodToCode(name, 0, idx);
	        }
	        idx = name.indexOf('W');
	        if (idx > 0) {
	            return convertWeekPeriodToCode(name, 0, idx);
	        }
	        idx = name.indexOf('D');
	        if (idx > 0) {
	            return convertDayPeriodToCode(name, 0, idx);
	        }
	        return -1;
	    }

	    /**
	     * Convert the text representation of a year Period to the numeric
	     * representation. The startIndex and endIndex delimit the number part of
	     * the string representation. The endIndex would usually be the result of
	     * text.indexOf("Y").
	     *
	     * @param yearPeriod
	     *            The string representation of the year Period.
	     * @param startIndex
	     *            The index where the number part of the Period begins.
	     * @param endIndex
	     *            The index immediately after the last number of the Period
	     * @return The numeric representation of the Period.
	     */
	    private static int convertYearPeriodToCode(String yearPeriod, int startIndex, int endIndex) {
	        // Because Util.getDateDiffInYearsString(start, end) generates
	        // a Period as a fractional part of the year we must be able to
	        // parse such a string.
	        String PeriodText = yearPeriod.substring(startIndex, endIndex);
	        int idx2 = PeriodText.indexOf(".");
	        if (idx2 > 0) {
	            return Math.round(Float.parseFloat((PeriodText)) * 365);
	        } else {
	            return Integer.parseInt(PeriodText) * 365;
	        }
	    }

	    /**
	     * Convert the text representation of a month Period to the numeric
	     * representation. The startIndex and endIndex delimit the number part of
	     * the string representation. The endIndex would usually be the result of
	     * text.indexOf("M").
	     *
	     * @param monthPeriod
	     *            The string representation of the month Period.
	     * @param startIndex
	     *            The index where the number part of the Period begins.
	     * @param endIndex
	     *            The index immediately after the last number of the Period
	     * @return The numeric representation of the Period.
	     */
	    private static int convertMonthPeriodToCode(String monthPeriod, int startIndex, int endIndex) {
	        String PeriodText = monthPeriod.substring(startIndex, endIndex);
	        return Integer.parseInt(PeriodText) * 30;
	    }

	    /**
	     * Convert the text representation of a week Period to the numeric
	     * representation. The startIndex and endIndex delimit the number part of
	     * the string representation. The endIndex would usually be the result of
	     * text.indexOf("W").
	     *
	     * @param weekPeriod
	     *            The string representation of the week Period.
	     * @param startIndex
	     *            The index where the number part of the Period begins.
	     * @param endIndex
	     *            The index immediately after the last number of the Period
	     * @return The numeric representation of the Period.
	     */
	    private static int convertWeekPeriodToCode(String weekPeriod, int startIndex, int endIndex) {
	        String PeriodText = weekPeriod.substring(startIndex, endIndex);
	        return Integer.parseInt(PeriodText) * 7;
	    }

	    /**
	     * Convert the text representation of a day Period to the numeric
	     * representation. The startIndex and endIndex delimit the number part of
	     * the string representation. The endIndex would usually be the result of
	     * text.indexOf("D").
	     *
	     * @param dayPeriod
	     *            The string representation of the day Period.
	     * @param startIndex
	     *            The index where the number part of the Period begins.
	     * @param endIndex
	     *            The index immediately after the last number of the Period
	     * @return The numeric representation of the Period.
	     */
	    private static int convertDayPeriodToCode(String dayPeriod, int startIndex, int endIndex) {
	        String PeriodText = dayPeriod.substring(startIndex, endIndex);
	        return Integer.parseInt(PeriodText);
	    }
	    /**
	     * Returns the String representation of the Period defined by the parameter.
	     *
	     * @param code a code of a Period
	     */
	    public static String code2String(int code) {
	        if (_code2name == null) {
	            buildCodes();
	        }
	        String s = (String)_code2name.get(Integer.valueOf(code));
	        if (s != null) {
	            return s;
	        } else {
	            if (code %360 == 0) {
	                return "" + (code/360) +  "Y";
	            } else if (code %30 == 0) {
	                return "" + (code/30) +  "M";
	            } else if (code %7 == 0) {
	                return "" + (code/7) +  "W";
	            } else {
	                return "" + code +  "D";
	            }
	        }
	    }
	    
	    /**
	     * Returns the market convention string representation of this Period.
	     */
	    public String toMarketConventionString() {
	    	if (_name != null){
	    		String marketConvention = _name.substring(0, _name.length()-1);
	    		if(_name.endsWith("Y")){
	    			marketConvention += " YEAR";    			
	    		}
	    		else if(_name.endsWith("M")){
	    			marketConvention += " MONTH";    			
	    		}
	    		else if(_name.endsWith("W")){
	    			marketConvention += " WEEK";    			
	    		}
	    		else if(_name.endsWith("D")){
	    			marketConvention += " DAY";    			
	    		}
	    		else{
	    			marketConvention = _name;
	    		}
	    		
	    		return marketConvention;
	    	}
	        return "";
	    }
	    
	    /**
	     * Returns the String representation of this Period.
	     */
	    final public String toString() {
	        return _name;
	    }
	    
	    public static final String MARKET_CONVENTION = "MARKET_CONVENTION";
	    
	    /**
	     * Returns the String representation of this Period.
	     * If representation parameter equals "MARKET_CONVENTION", it returns 
	     * the market convention string ('3 Months'). Otherwise it 
	     * returns the Period name ('3M'); 
	     */
	    final public String toString(String representation) {
	    	if (MARKET_CONVENTION.equals(representation))
	    		return toMarketConventionString();
	    	else
	    		return toString();
	    }


	    /**
	     * Returns a Vector containing all Periods as String.
	     */
	    public static Vector<String> getDomain() {
	        if (_names == null) {
	            buildCodes();
	        }
	        Vector v = new Vector();
	        for (int i=0; i<_names.length; i++) {
	            v.addElement(_names[i]);
	        }
	        return v;
	    }
	    
	    /**
	     * Returns a Vector containing all Periods as String.
	     */
	    public static Vector<String> getDomainValues() {
	        if (_names == null) {
	            buildCodes();
	        }
	        Vector v = new Vector();
	        for (int i=0; i<_names.length; i++) {
	            v.addElement(_names[i]);
	        }
	        v.add(0," ");
	        return v;
	    }

	    /**
	     * Returns a Vector containing all Month-Periods as String.
	     */
	    public static Vector getDomainMonths() {
	        if (_names == null) {
	            buildCodes();
	        }
	        Vector v = new Vector();
	        for(int i=0;i<_names.length;i++) {
	            if (_names[i].indexOf('M') >= 0) {
	                v.addElement(_names[i]);
	            }
	        }
	        v.insertElementAt("0D",0);
	        return v;
	    }

	    /**
	     * Returns a Vector containing all Year-Periods as String.
	     */
	    public static Vector getDomainYears() {
	        if (_names == null) {
	            buildCodes();
	        }
	        Vector v = new Vector();
	        for(int i=0;i<_names.length;i++) {
	            if (_names[i].indexOf('Y') >= 0) {
	                v.addElement(_names[i]);
	            }
	        }
	        v.insertElementAt("0D",0);
	        return v;
	    }

	    /**
	     * Returns a Vector containing all Month&Year-Periods as String.
	     */
	    public static Vector getDomainMonthYears() {
	        if (_names == null) {
	            buildCodes();
	        }
	        Vector v = new Vector();
	        for(int i=0; i<_names.length; i++) {
	            if((_names[i].indexOf('Y') >= 0) || (_names[i].indexOf('M') >= 0)) {
	                v.addElement(_names[i]);
	            }
	        }
	        v.insertElementAt("0D",0);
	        return v;
	    }

	    static final int[] dayPeriods = { 0, 1, 2, 3, 4, 5, 6, 15 };
	    static final int[] weekPeriods = { 1, 2, 3, 4 };
	    static final int[] monthPeriods = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 18, 21, 27, 30, 33, 39, 42, 45, 51, 54, 57 };
	    static final int[] yearPeriods = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 30, 35, 40, 45, 50 };

	    static private void buildCodes(int[] Periods, String PeriodType, int PeriodMultiplier, Map codeToString, Map stringToCode) {
	        for(int i = 0; i < Periods.length; i++) {
	            String str = Periods[i] + PeriodType;
	            Integer code = Integer.valueOf(Periods[i] * PeriodMultiplier);
	            codeToString.put(code,str);
	            stringToCode.put(str,code);
	        }
	    }

	    static private void  buildCodes() {
	        if (_name2code != null) {
	            return;
	        }
	        Hashtable codeToString= new Hashtable();
	        Hashtable stringToCode= new Hashtable();

	        getStaticPeriods(codeToString, stringToCode);

	        addPeriods(getPeriodsFromDomain(), stringToCode, codeToString);
	        _name2code = stringToCode;
	        _code2name = codeToString;
	        _names = buildNames(codeToString);
	    }
	    
	    /**
	     * Returns the static Periods codes and days, adding them to two maps.
	     *  Eq.:
	     *  
	     *      code    days 
	     *  
	     *      1W      7
	     *      2W      14
	     *      ...     ...
	     *      3M      90
	     *      4M      120
	     *      ...     ...
	     *      1Y      360
	     *      2Y      720
	     *      ...     ...
	     *  
	     * @param codeToString Map with Period code as key
	     * @param stringToCode Map with Period days as key
	     */
	    static public void getStaticPeriods(Hashtable codeToString, Hashtable stringToCode)
	    {
	    	 /* PeriodCalculator calc=getCalculator();
	    	  
	          if (calc == null || !calc.overrideDefaultPeriods()) {
	              buildCodes(dayPeriods,   "D",   1, codeToString, stringToCode);
	              buildCodes(weekPeriods,  "W",   7, codeToString, stringToCode);
	              buildCodes(monthPeriods, "M",  30, codeToString, stringToCode);
	              buildCodes(yearPeriods,  "Y", 360, codeToString, stringToCode);
	          }

	          if(calc != null) {
	              calc.addPeriods(stringToCode,codeToString);
	          } */
	    }

	    static protected Collection getPeriodsFromDomain() {
	      //  if (DSConnection.getDefault()==null) return new Vector();
	        
	        return null;// LocalCache.getDomainValues(DSConnection.getDefault(), "Period");
	        
	    }

	    static protected String[] buildNames(Map codeToString) {
	        String[] names = new String[codeToString.size()];
	       /* Vector codes = SortShell.sort(codeToString.keySet());
	        for(int i=0; i < codes.size(); i++) {
	            names[i]= (String)codeToString.get(codes.get(i));
	        } */
	        return names;
	    }

	    /**
	     * Returns a clone of this Period.
	     */
	    public Object clone() throws CloneNotSupportedException {
	        return super.clone();
	    }

	    public void writeExternal(ObjectOutput out)
	        throws IOException {
	        out.writeInt(_code);
	        out.writeUTF(_name);
	    }
	    public void readExternal(ObjectInput in)
	        throws IOException ,ClassNotFoundException{
	        _code=in.readInt();
	        _name=in.readUTF();
	    }

	    static private boolean _notDefined=false;
	    
	    /**
	     *
	     * @deprecated only keep to avoid re-compilation of dependent code,
	     * cast your Vector to Collection to use the new method.
	     * @see #addPeriods(java.util.Collection)
	     */
	    static public void addPeriods(Vector v) {
	        // do NOT forget to cast!
	        addPeriods((Collection)v);
	    }
	    
	    static public void addPeriods(Collection v) {
	     if (v == null || v.size() == 0)
	            return;

	        if (_code2name == null) {
	            buildCodes();
	        }
	        addPeriods(v, _name2code, _code2name);
	        _names = buildNames(_code2name);
	    }

	    static private void addPeriods(Collection newPeriods, Map stringToCode, Map codeToString) {
	      if(newPeriods == null || newPeriods.size() == 0)
	            return;

	        for(Iterator i = newPeriods.iterator(); i.hasNext();) {
	            String name=(String)i.next();
	            name = name.toUpperCase();
	            if(stringToCode.containsKey(name))
	                continue;
	            int code = convertToCode(name);
	            if(code <0) {
	          //      Log.error("Period","Invalid Period " + name);
	                continue;
	            }
	            Integer icode = Integer.valueOf(code);
	            if(codeToString.containsKey(icode)) {
	                continue;
	            }

	            stringToCode.put(name, icode);
	            codeToString.put(icode,name);
	        }
	    }

	    public static String datesToPeriod(DateU dt1, DateU dt2, char car) {
	        int n=0,nY=0,nM=0,nD=0;
	        n = 0;//Math.abs((int) DateU.diff(dt1, dt2));
	        if (car == 'D') {
	            return (n + "D");
	        }
	        if (n >= 360) {nY = (int) (n/360); n = Math.abs(n - nY*360);}
	        if (n >= 30) {
	            nM = (int) (n/30);
	            double dnM = (double) ((double)n/30.0) - (double) nM;
	            if (dnM >= 0.9) nM += 1;
	            n = Math.abs(n - nM*30);
	            }
	        nD = n;
	        if (car == 'M') {
	            if (nM > 0) {nM += nY*12; return new String(nM + "M");}
	            else if (nY > 0) {return new String(nY + "Y");}
	            else return new String("0D");
	            }
	        // else if (car == 'D') {nD += (nM*30+nY*360); return( new String(nD+"D") );}
	        else return new String(nY + "Y" + nM + "M" + nD + "D");
	    }

	    public double getChartableValue() {
	        return (double)this.getCode();
	    }

	    public void readCustom(ObjectInput in) throws IOException {
	        _code=in.readInt();
	        _name = code2String(_code);
	    }
	    
	    public void writeCustom(ObjectOutput out) throws IOException {
	    	out.writeInt(_code);
	    }
	    
	    public static void main(String args[]) {
	    	Period period = new Period("10Y");
	    	System.out.println(period._code);
	    	Period month = new Period("12M");
	    	System.out.println(month._code);
	    	Period days = new Period("90D");
	    	System.out.println(days._code);
	    	
	    	
	    	
	    	
	    	
	    	
	    }
}
