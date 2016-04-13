package util.common;

public class Frequency {
	
	    protected transient Period  _tenor;
	   
	    
	  
	
	 final public static int ZERO_COUPON = 0;
	    final public static int NONE = 2;
	    final public static int CONTINUOUS = 3;
	    final public static int DAILY = 1;
	    final public static int WEEKLY = 7;
	    final public static int BIWEEKLY = 15;
	    final public static int MONTHLY = 30;
	    final public static int BMONTHLY = 60;
	    final public static int QUARTERLY = 90;
	    final public static int I_91D = 91; // BZ 59073
	    final public static int SEMI_ANNUAL = 180;
	    final public static int I_182D = 182; // BZ 59073
	    final public static int ANNUAL = 360;
	    final public static int I_364D = 364; // BZ 59073
	    final public static int LUNAR = 28;
	    final public static String S_MON = "MON";
	    final public static String S_NONE = "NON";
	    final public static String S_DAILY = "DLY";
	    final public static String S_WEEKLY = "WK";
	    final public static String S_BIWEEKLY = "BIWK";
	    final public static String S_MONTHLY = "MTH";
	    final public static String S_BMONTHLY = "BIM";
	    final public static String S_QUARTERLY = "QTR";
	    final public static String S_91D = "91D";
	    final public static String S_SEMI_ANNUAL = "SA";
	    final public static String S_182D = "182D";
	    final public static String S_ANNUAL = "PA";
	    final public static String S_364D = "364D";
	    final public static String S_ZERO_COUPON = "ZC";
	    final public static String S_CONTINUOUS = "CNT";
	    final public static String S_LUNAR = "LUN";
	    protected int _frequency;
	    final public static Frequency F_NONE = new Frequency(NONE);
	    final public static Frequency F_DAILY = new Frequency(DAILY);
	    final public static Frequency F_WEEKLY = new Frequency(WEEKLY);
	    final public static Frequency F_BIWEEKLY = new Frequency(BIWEEKLY);
	    final public static Frequency F_MONTHLY = new Frequency(MONTHLY);
	    final public static Frequency F_BMONTHLY = new Frequency(BMONTHLY);
	    final public static Frequency F_QUARTERLY = new Frequency(QUARTERLY);
	    final public static Frequency F_91D = new Frequency(I_91D);
	    final public static Frequency F_SEMI_ANNUAL = new Frequency(SEMI_ANNUAL);
	    final public static Frequency F_182D = new Frequency(I_182D);
	    final public static Frequency F_ANNUAL = new Frequency(ANNUAL);
	    final public static Frequency F_364D = new Frequency(I_364D);
	    final public static Frequency F_ZERO_COUPON = new Frequency(ZERO_COUPON);
	    final public static Frequency F_CONTINUOUS = new Frequency(CONTINUOUS);
	    final public static Frequency F_LUNAR = new Frequency(LUNAR);

	    public static int fromString(String s) {
	        if (s.equals(S_ZERO_COUPON))
	            return ZERO_COUPON;
	        if (s.equals(S_NONE))
	            return NONE;
	        if (s.equals(S_DAILY))
	            return DAILY;
	        if (s.equals(S_WEEKLY))
	            return WEEKLY;
	        if (s.equals(S_BIWEEKLY))
	            return BIWEEKLY;
	        if (s.equals(S_MONTHLY))
	            return MONTHLY;
	        if (s.equals(S_BMONTHLY))
	            return BMONTHLY;
	        if (s.equals(S_QUARTERLY))
	            return QUARTERLY;
	        if (s.equals(S_91D))
	            return I_91D;
	        if (s.equals(S_SEMI_ANNUAL))
	            return SEMI_ANNUAL;
	        if (s.equals(S_182D))
	            return I_182D;
	        if (s.equals(S_ANNUAL))
	            return ANNUAL;
	        if (s.equals(S_364D))
	            return I_364D;
	        if (s.equals(S_CONTINUOUS))
	            return CONTINUOUS;
	        if (s.equals(S_LUNAR))
	            return LUNAR;
	        return -1;
	    }
	    private Frequency(int f) {
	        _frequency = f;
	    }
	    
	    public int getCode() {
	        return _frequency;
	    }
	    
	    /**
	     * Returns a Tenor indicating the frequency, expressed in months
	     */

	    public int getMonths() {
	        return getTenor().getCode() / MONTHLY;
	    }
	    
	    
	    /**
	     * Returns a Tenor indicating the frequency, expressed in weeks. Mthly = 4;
	     * annual = 52, semi-annual = 26, qtr = 12 (3mths)
	     */
	    public int getWeeks() {
	        if (getCode() == WEEKLY || getCode() == LUNAR) {
	            return getCode() / WEEKLY;
	        }
	        if (getCode() == BIWEEKLY) {  
	            return 2;
	        }

	        int mths = getMonths();
	        int wks = 0;
	        if (mths == 12) { // annual
	            wks = 52;
	        } else if (mths == 6) { 
	            wks = 26;
	        } else {
	            wks = mths * 4;
	        }
	        return wks;
	    }

	    
	    
	    /**
	     * Returns the frequency, expressed as times per year.
	     * <p>
	     * For Example DAILY equals 365.
	     */
	    public int getTimesPerYr() {
	        switch (_frequency) {
	            case ZERO_COUPON :
	                return 1;
	            case NONE :
	                return 1;
	            case CONTINUOUS :
	                return 1; // dummy - user needs to check
	            case DAILY :
	                return 365; // should this use daycount?
	            case WEEKLY :
	                return 52;
	            case BIWEEKLY :
	                return 26;
	            case LUNAR :
	                return 13;
	            default :
	                return 360 / getTenor().getCode();
	        }
	    }
	    
	    /**
	     * Returns a Frequency object with the specified frequency.
	     * 
	     * @param s
	     *            a String that represents the Frequency
	     */
	    public static Frequency get(String s) {
	        if(s==null) return null;
	        if (s.equals(S_ZERO_COUPON))
	            return F_ZERO_COUPON;
	        if (s.equals(S_NONE))
	            return F_NONE;
	        if (s.equals(S_DAILY))
	            return F_DAILY;
	        if (s.equals(S_WEEKLY))
	            return F_WEEKLY;
	        if (s.equals(S_BIWEEKLY))
	            return F_BIWEEKLY;
	        if (s.equals(S_MONTHLY))
	            return F_MONTHLY;
	        if (s.equals(S_BMONTHLY))
	            return F_BMONTHLY;
	        if (s.equals(S_QUARTERLY))
	            return F_QUARTERLY;
	        if (s.equals(S_91D))
	            return F_91D;
	        if (s.equals(S_SEMI_ANNUAL))
	            return F_SEMI_ANNUAL;
	        if (s.equals(S_182D))
	            return F_182D;
	        if (s.equals(S_ANNUAL))
	            return F_ANNUAL;
	        if (s.equals(S_364D))
	            return F_364D;
	        if (s.equals(S_CONTINUOUS))
	            return F_CONTINUOUS;
	        if (s.equals(S_LUNAR))
	            return F_LUNAR;
	        
	        else
	            return null;
	    }
	    
	    public static int frequencyInyear(String s) {
	    	
	    	if (s.equals(S_SEMI_ANNUAL))
	            return 2;
	    	if (s.equals(S_MONTHLY))
	            return 12;
	        if (s.equals(S_MON))
	            return 12;
	        if (s.equals(S_QUARTERLY))
	            return 4;
	        if (s.equals(S_ANNUAL))
	            return 1;
	        if (s.equals(S_DAILY))
	            return 360;
	        if (s.equals(S_WEEKLY))
	            return 52;
	        if (s.equals(S_ZERO_COUPON))
	            return 0;
	    	return -1;
	    	
	    }
	    
	    public Period getTenor() {
	    if (_tenor != null)
            return _tenor;
        switch (_frequency) {
            case ZERO_COUPON :
                _tenor = new Period(360);
                break;
            case NONE :
                _tenor = new Period(0);
                break;
            case DAILY :
                _tenor = new Period(1);
                break;
            case WEEKLY :
                _tenor = new Period(7);
                break;
            case BIWEEKLY :
                _tenor = new Period(15);
                break;
            case MONTHLY :
                _tenor = new Period(30);
                break;
            case BMONTHLY :
                _tenor = new Period(60);
                break;
            case QUARTERLY :
                _tenor = new Period(90);
                break;
            case I_91D :
                _tenor = new Period(91);
                break;
            case SEMI_ANNUAL :
                _tenor = new Period(180);
                break;
            case I_182D :
                _tenor = new Period(182);
                break;
            case ANNUAL :
                _tenor = new Period(360);
                break;
            case I_364D :
                _tenor = new Period(364);
                break;
            case CONTINUOUS :
                _tenor = new Period(0);
                break;
            case LUNAR :
                _tenor = new Period(28);
                break;
            default :
              
                _tenor = new Period(_frequency);
                break;
        }
        return _tenor;
    }
	    public Frequency() {
	        _frequency = NONE;
	    }
	    private Frequency(String f) {
	        _frequency = fromString(f);
	    }
	    
	    public static Frequency valueOf(String s) {
	        return get(s);
	    }

	    public static Frequency valueOf(int i) {
	        return get(i);
	    }

	    public static Frequency valueOf() {
	        return F_NONE;
	    }
	    

	    /**
	     * Returns a Frequency object with the specified frequency.
	     * 
	     * @param code
	     *            an integer that represents the Frequency
	     */
	    public static Frequency get(int code) {
	        switch (code) {
	            case ZERO_COUPON :
	                return F_ZERO_COUPON;
	            case NONE :
	                return F_NONE;
	            case DAILY :
	                return F_DAILY;
	            case WEEKLY :
	                return F_WEEKLY;
	            case BIWEEKLY :
	                return F_BIWEEKLY;
	            case MONTHLY :
	                return F_MONTHLY;
	            case BMONTHLY :
	                return F_BMONTHLY;
	            case QUARTERLY :
	                return F_QUARTERLY;
	            case I_91D:
	                return F_91D;
	            case SEMI_ANNUAL :
	                return F_SEMI_ANNUAL;
	            case I_182D:
	                return F_182D;
	            case ANNUAL :
	                return F_ANNUAL;
	            case I_364D:
	                return F_364D;
	            case CONTINUOUS :
	                return F_CONTINUOUS;
	            case LUNAR :
	                return F_LUNAR;
	            default :
	               
	                return new Frequency(code);
	        }
	    }
	    /**
	     * Returns true if the frequency is monthly based, ie monthly, bimonthly,
	     * quarterly...
	     */
	    public boolean isMonthlyBased() {
	        if (getTenor() != null
	                && getTenor().getCode() % MONTHLY == 0)
	            return true;
	        return false;
	    }

}
