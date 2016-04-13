package util.common;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Vector;

import util.commonUTIL;

public class NumberRoundingMethod   implements Cloneable {
	public static final int MAX_DEC_PLACES = 15;

    static final double[] POWS = {1., 10., 100., 1000., 10000., 100000.,
                                  1000000., 10000000., 100000000., 1000000000.,
                                  10000000000., 100000000000., 1000000000000.,
                                  10000000000000., 100000000000000.,
                                  1000000000000000.};
    static final double[] IPOWS = {1.e-1, 1.e-2, 1.e-3, 1.e-4, 1.e-5, 1.e-6,
                                   1.e-7, 1.e-8, 1.e-9, 1.e-10, 1.e-11, 1.e-12,
                                   1.e-13, 1.e-14, 1.e-15, 1.e-16};

    // This is used to decide on the number of significant digits are
    // represented by the
    // binary exponent. The binary exponent is biased by 1023 so after
    // subtracting 1023
    // we have a number that is the binary exponent. Every 10 in that number
    // represents
    // 3 significant digits. The breakdown of the significant digits within that
    // range of 10
    // is given by the table below. So (including the biasing) the exponent
    // values match
    // as follows.
    //
    // 1023 - 1032 1-3 significant digits
    // 1033 - 1042 4-6
    // 1043 - 1052 7-9
    // 1053 - 1062 10-12
    // 1063 - 1072 13-15
    // 1073 - 1082 16-18 - this is outside of 80 bit resolution. Here there are
    // no
    //                                  significant digits after the decimal place. Above this and there
    //                                  are more and more zeros in front of the decimal place.
    // Within these ranges you would have something like:
    // 1023 - 1025 1 significant digit
    // 1026 - 1029 2 significant digits
    // 1030 - 1032 3 significant digits
    //
    // so if x = binary exponent then the number if significant digits is:
    //
    // significantDigits[(x - 1023) % 10] + ((x - 1023) / 10) * 3
    //
    static final int significantDigits[] = {1, 1, 1, 2, 2, 2, 2, 3, 3, 3};


    // Sometimes when rounding a number the final operation results in a
    // rounding error.
    // We can use BigDecimal to round the final number correctly. However this
    // is slow
    // and so we check to see if we should do so.
    static private boolean _loadedBigDecimalFlag = false;
    static private boolean _useBigDecimal = false;

    static private String logCategory = "NumberRoundingMethod";

    static private void loadBigDecimalFlag() {
        _loadedBigDecimalFlag = true;
        _useBigDecimal = false;// this must be coming propery file "USE_BIGDECIMAL_FOR_ROUNDING";
    }

    /**
     * Rounding code specifying that the number should be rounded to the nearest
     * value at the specified decimal place. The next subsequent decimal place
     * is evaluated to determine whether to round up or down. A value of five or
     * more at that next subsequent decimal place will result in upwards
     * rounding.
     */
    final public static int RM_NEAREST = 0;

    /**
     * Rounding code specifying that the value at the specified decimal place
     * should be raised by one and all subsequent decimals should be truncated.
     */
    final public static int RM_UP = 1;

    /**
     * Rounding code specifying that the value at the specified decimal place
     * should be lowered by one and all subsequent decimals should be truncated.
     */
    final public static int RM_DOWN = 2;

    /**
     * Rounding SecCode name used to store the rounding method in the product's
     * SecCode table.
     */
    final public static String ROUNDING = "ROUNDING";

    final public static String S_NEAREST = "NEAREST";
    final public static String S_UP = "UP";
    final public static String S_DOWN = "DOWN";

    final public static NumberRoundingMethod R_NEAREST = new NumberRoundingMethod(RM_NEAREST);
    final public static NumberRoundingMethod R_UP = new NumberRoundingMethod(RM_UP);
    final public static NumberRoundingMethod R_DOWN = new NumberRoundingMethod(RM_DOWN);

    private int _rm;

    /**
     * Constructs a <code>RoundingMethod</code> object with nearest rounding
     * method by default.
     */
    public NumberRoundingMethod() {
        _rm = RM_NEAREST;
    }

    /**
     * Constructs a RoundingMethod with specified rounding method. Can be one of
     * the following
     * <UL>
     * <LI>RM_NEAREST
     * <LI>RM_UP
     * <LI>RM_DOWN
     * </UL>
     * 
     * @param RoundingMethod
     *            a rounding method
     */
    public NumberRoundingMethod(int NumberRoundingMethod) //throws DomainViolationException
    {
        switch (NumberRoundingMethod) {
            case RM_NEAREST :
            case RM_UP :
            case RM_DOWN :
                {
                    _rm = NumberRoundingMethod;
                    break;
                }
            default :
                _rm = -1;
        //throw new Error("RoundingMethod corrupted");
        }
    }

    /**
     * Constructs a <code>RoundingMethod</code> object with specified rounding
     * method. Can be one of the following
     * <UL>
     * <LI>S_NEAREST
     * <LI>S_UP
     * <LI>S_DOWN
     * </UL>
     */
    public NumberRoundingMethod(String s) //throws DomainViolationException
    {
        try {
            _rm = fromString(s);
        } catch (Exception e) {
            commonUTIL.displayError(logCategory, "", e);
            _rm = -1;
        }
    }

    /**
     * Returns the code corresponding to the rounding method.
     * 
     * @return the code corresponding to the rounding method
     */
    final public int getCode() {
        return _rm;
    }

    /**
     * Returns all possible values for rounding methods.
     * 
     * @return a <code>Vector</code> of <code>String</code> s representing
     *         all possible values for rounding methods.
     */
    static public Vector getDomain() {
        Vector v = new Vector();
        v.addElement(S_NEAREST);
        v.addElement(S_UP);
        v.addElement(S_DOWN);
        return v;
    }

    /**
     * Returns the <code>int</code> code corresponding to the specified
     * rounding method <code>String</code> name.
     * 
     * @param s
     *            the <code>String</code> of which we want to have the
     *            <code>int</code> code.
     * @return the corresponding <code>int</code>.
     * @throws DomainViolationException
     *             if s isn't a valid value.
     */
    static public int fromString(String s) throws Exception {
        if (s == null)
            throw new Exception("null rounding string");
        if (s.equals(S_NEAREST))
            return RM_NEAREST;
        if (s.equals(S_UP))
            return RM_UP;
        if (s.equals(S_DOWN))
            return RM_DOWN;
        throw new Exception(s);
    }

    /**
     * Returns a <code>String</code> representation for this rounding method.
     * 
     * @return a <code>String</code> representation for this rounding method.
     */
    public String toString() {
        switch (_rm) {
            case RM_NEAREST :
                return S_NEAREST;
            case RM_UP :
                return S_UP;
            case RM_DOWN :
                return S_DOWN;
            default :
                throw new Error("RoundingMethod corrupted");
        }
    }

    static public double round(double value, int decPlaces, int rm) {
    	return round(value, decPlaces, rm, _useBigDecimal);
    }
    
    static public double round(double value, int decPlaces, int rm, boolean useBigDecimal) {
        if (!_loadedBigDecimalFlag)
            loadBigDecimalFlag();

        if (value == 0)
            return 0;
        if (Double.isNaN(value))
            return value;
        if (Double.isInfinite(value))
            return value;
        if (value < 0) {
            return -round(-value, decPlaces, rm);
        }

        boolean logRounding = true;// Log.isCategoryLogged(logCategory);
        NumberFormat format = null;
        if (decPlaces > MAX_DEC_PLACES)
            decPlaces = MAX_DEC_PLACES;
        if (logRounding) {
            format = NumberFormat.getInstance();
            commonUTIL.display(logCategory, "round(" + format.format(value) + ", "
                                   + decPlaces + ", "
                                   + NumberRoundingMethod.get(rm).toString() + ")");
        }

        if(decPlaces < 0) {
            throw new IllegalArgumentException("Number of decimal places cannot be less than zero.");
        }
        
        double precision = POWS[decPlaces];
        double v_integer = Math.floor(value);
        if (logRounding)
            commonUTIL.display(logCategory, "v_integer = " + format.format(v_integer));
        // Ok, time to close the eyes and not peek. We are going to look at the
        // exponent
        // and from that take a stab at the significant digits in the integer
        // component. We
        // can only rely on about 15 significant digits so only go looking that
        // far down the
        // number. Any lower and we may get into the ulps (units [of error] in
        // the last
        // place).
        long sigDigitsInInteger = Double.doubleToLongBits(v_integer); // Get the
                                                                      // IEEE
                                                                      // 754 bit
                                                                      // pattern
        sigDigitsInInteger &= 0x7ff0000000000000L; // Mask out the exponent
        sigDigitsInInteger >>= 52; // Get it where we can use it
        sigDigitsInInteger = sigDigitsInInteger - 1023;
        if (sigDigitsInInteger < 0)
            sigDigitsInInteger = 0; // If the number has no integer component
                                    // then the
        // number will be zero. Normally you would expect a
        // zero to have zero exponent but it seems that if the
        // number is .001 then the integer part of that is 0.0E-3.
        else {
            // This is a rough count of the sig digits in number. See the
            // description for the
            // array significantDigits above.
            sigDigitsInInteger = significantDigits[((int) sigDigitsInInteger) % 10]
                                 + (sigDigitsInInteger / 10) * 3;
        }
        if (logRounding)
        	commonUTIL.display(logCategory, "sigDigitsInInteger = " + sigDigitsInInteger);
        // We can count on (15 - sigDigitsInInteger) significant digits in the
        // fraction. This is used to determine, for instance, how much precision
        // to use when trying to round up.
        int places = 15 - (int) sigDigitsInInteger;
        if (logRounding)
        	commonUTIL.display(logCategory, "places in fraction = " + places);
        // Some sanity checks
        if (places > MAX_DEC_PLACES)
            places = MAX_DEC_PLACES;
        // Now we want to make sure that we are checking at least as far as the
        // user
        // wants. If we have to adjust here then it actually means that the user
        // is asking
        // the impossible. There are only 15 significant digits and the user is
        // asking for
        // more
        if (places < decPlaces)
            places = decPlaces;
        // This is the fraction portion rounded to a large number. Large enough
        // that the last significant digit is at the decimal point. This allows
        // us to round any trailing digits correctly
        double v_fraction = Math.round((value - v_integer) * POWS[places]);
        if (logRounding)
        	commonUTIL.display(logCategory, "v_fraction = " + format.format(v_fraction));

        double v_rounded_fraction = 0.0;

        switch (rm) {
            case RM_NEAREST :
                v_rounded_fraction = Math.round(v_fraction
                                                / POWS[places - decPlaces]);
                break;
            case RM_UP :
                // Calculate the value so that the precision amount is
                // before the decimal point
                double tmp = v_fraction / POWS[places - decPlaces];
                // Subtract the error amount.
                // The error amount will be the smallest decrement that we can
                // support. Currently tmp will have the fractional part scaled
                // so that it is all before the decimal place. Therefore there
                // will be decPlaces before the decimal point allowing only
                // MAX_DEC_PLACES - decPlaces after the decimal point before we
                // drop below the significance level for the double
                // representation. However, there will come times that we are
                // being asked to round up numbers right at the edge and for
                // these we will do the best we can. The largest error that we
                // will use is 0.001. After that we will perturb the number by
                // too much.

                v_rounded_fraction = Math.floor(tmp
                                                - IPOWS[Math.max(MAX_DEC_PLACES
                                                                 - decPlaces, 3)]) + 1.0;

                break;
            case RM_DOWN :
                // This convoluted code is trying to work out the error present
                // in the number
                // and if it is less than a certain amount assume that this is
                // really an error
                // amount and that the number is really the next integer higher.
                // That is we have
                // met x.99999999 which should be x+1. We are looking for
                // numbers that are
                // 9s as far as the location of the error position - places.
                tmp = v_fraction / POWS[places - decPlaces];
                v_rounded_fraction = Math.round(tmp - (0.5 - IPOWS[places]));
                break;
        }

        double v_round = v_integer + Math.floor(v_rounded_fraction) / precision;

        // Make one last attempt to round to a reasonable value.
        if (useBigDecimal)
            v_round = new BigDecimal(v_round).setScale(decPlaces + 1,
                                                       BigDecimal.ROUND_HALF_UP)
                    .doubleValue();
        if (logRounding)
        	commonUTIL.display(logCategory, "v_round = " + format.format(v_round));
        return v_round;
    }
    
    /**
     * created from {@link #round(double, int, int, boolean)}, but this methoods returns a BigDecimal 
     * used for the BalancePosition.
     * @param value
     * @param decPlaces
     * @param rm
     * @return
     */
    static public BigDecimal roundToBigDecimal(double value, int decPlaces, int rm) {
        
        if (value == 0)
            return BigDecimal.ZERO;
        if (Double.isNaN(value))
            throw new IllegalArgumentException("NaN value");
        if (Double.isInfinite(value))
            throw new IllegalArgumentException("infinite value");
        if (value < 0) {
            return roundToBigDecimal(-value, decPlaces, rm).negate();
        }

        boolean logRounding = true;//Log.isCategoryLogged(logCategory);
        NumberFormat format = null;
        if (decPlaces > MAX_DEC_PLACES)
            decPlaces = MAX_DEC_PLACES;
        if (logRounding) {
            format = NumberFormat.getInstance();
            commonUTIL.display(logCategory, "round(" + format.format(value) + ", "
                                   + decPlaces + ", "
                                   + NumberRoundingMethod.get(rm).toString() + ")");
        }

        if(decPlaces < 0) {
            throw new IllegalArgumentException("Number of decimal places cannot be less than zero.");
        }
        
        double precision = POWS[decPlaces];
        double v_integer = Math.floor(value);
        if (logRounding)
        	commonUTIL.display(logCategory, "v_integer = " + format.format(v_integer));
        // Ok, time to close the eyes and not peek. We are going to look at the exponent
        // and from that take a stab at the significant digits in the integer component. We
        // can only rely on about 15 significant digits so only go looking that far down the
        // number. Any lower and we may get into the ulps (units [of error] in the last
        // place).
        long sigDigitsInInteger = Double.doubleToLongBits(v_integer); // Get the IEEE 754 bit pattern
        sigDigitsInInteger &= 0x7ff0000000000000L; // Mask out the exponent
        sigDigitsInInteger >>= 52; // Get it where we can use it
        sigDigitsInInteger = sigDigitsInInteger - 1023;
        if (sigDigitsInInteger < 0)
            sigDigitsInInteger = 0; // If the number has no integer component
                                    // then the
        // number will be zero. Normally you would expect a
        // zero to have zero exponent but it seems that if the
        // number is .001 then the integer part of that is 0.0E-3.
        else {
            // This is a rough count of the sig digits in number. See the
            // description for the array significantDigits above.
            sigDigitsInInteger = significantDigits[((int) sigDigitsInInteger) % 10]
                                 + (sigDigitsInInteger / 10) * 3;
        }
        if (logRounding)
            commonUTIL.display(logCategory, "sigDigitsInInteger = " + sigDigitsInInteger);
        // We can count on (15 - sigDigitsInInteger) significant digits in the
        // fraction. This is used to determine, for instance, how much precision
        // to use when trying to round up.
        int places = 15 - (int) sigDigitsInInteger;
        if (logRounding)
        	commonUTIL.display(logCategory, "places in fraction = " + places);
        // Some sanity checks
        if (places > MAX_DEC_PLACES)
            places = MAX_DEC_PLACES;
        // Now we want to make sure that we are checking at least as far as the user
        // wants. If we have to adjust here then it actually means that the user is asking
        // the impossible. There are only 15 significant digits and the user is asking for
        // more
        if (places < decPlaces)
            places = decPlaces;
        // This is the fraction portion rounded to a large number. Large enough
        // that the last significant digit is at the decimal point. This allows
        // us to round any trailing digits correctly
        double v_fraction = Math.round((value - v_integer) * POWS[places]);
        if (logRounding)
            commonUTIL.display(logCategory, "v_fraction = " + format.format(v_fraction));

        double v_rounded_fraction = 0.0;

        switch (rm) {
            case RM_NEAREST :
                v_rounded_fraction = Math.round(v_fraction
                                                / POWS[places - decPlaces]);
                break;
            case RM_UP :
                // Calculate the value so that the precision amount is
                // before the decimal point
                double tmp = v_fraction / POWS[places - decPlaces];
                // Subtract the error amount.
                // The error amount will be the smallest decrement that we can
                // support. Currently tmp will have the fractional part scaled
                // so that it is all before the decimal place. Therefore there
                // will be decPlaces before the decimal point allowing only
                // MAX_DEC_PLACES - decPlaces after the decimal point before we
                // drop below the significance level for the double
                // representation. However, there will come times that we are
                // being asked to round up numbers right at the edge and for
                // these we will do the best we can. The largest error that we
                // will use is 0.001. After that we will perturb the number by
                // too much.

                v_rounded_fraction = Math.floor(tmp
                                                - IPOWS[Math.max(MAX_DEC_PLACES
                                                                 - decPlaces, 3)]) + 1.0;

                break;
            case RM_DOWN :
                // This convoluted code is trying to work out the error present
                // in the number
                // and if it is less than a certain amount assume that this is
                // really an error
                // amount and that the number is really the next integer higher.
                // That is we have
                // met x.99999999 which should be x+1. We are looking for
                // numbers that are
                // 9s as far as the location of the error position - places.
                tmp = v_fraction / POWS[places - decPlaces];
                v_rounded_fraction = Math.round(tmp - (0.5 - IPOWS[places]));
                break;
        }

        int roundingMode = 0;
        switch (rm) { 
            case RM_NEAREST : roundingMode = BigDecimal.ROUND_HALF_UP; break;
            case RM_DOWN : roundingMode = BigDecimal.ROUND_DOWN; break; 
            case RM_UP : roundingMode = BigDecimal.ROUND_UP; break;
            default: throw new IllegalArgumentException("Illegal rounding mode "+rm);
        }
      
        return new BigDecimal(v_integer*precision+Math.floor(v_rounded_fraction)).divide(new BigDecimal(precision), decPlaces, roundingMode);        
    }
    /**
     * Rounds the specified value using decPlaces decimals with the 'nearest'
     * rounding method.
     * 
     * @param value
     *            the value to be rounded
     * @param decPlaces
     *            the number of decimals to be used when rounding
     * @return the rounded value
     */
    static public double roundNearest(double value, int decPlaces) {
        return round(value, decPlaces, RM_NEAREST);
    }

    /**
     * Rounds the specified value using decPlaces decimals with the 'up'
     * rounding method.
     * 
     * @param value
     *            the value to be rounded
     * @param decPlaces
     *            the number of decimals to be used when rounding
     * @return the rounded value
     */
    static public double roundUp(double value, int decPlaces) {
        return round(value, decPlaces, RM_UP);
    }

    /**
     * Rounds the specified value using decPlaces decimals with the 'down'
     * rounding method.
     * 
     * @param value
     *            the value to be rounded
     * @param decPlaces
     *            the number of decimals to be used when rounding
     * @return the rounded value
     */
    static public double roundDown(double value, int decPlaces) {
        return round(value, decPlaces, RM_DOWN);
    }

    /**
     * Rounds the specified value using decPlaces decimals with the rounding
     * method used to construct this <code>RoundingMethod</code> object.
     * 
     * @param value
     *            the value to be rounded
     * @param decPlaces
     *            the number of decimals to be used when rounding
     * @return the rounded value
     */
    public double round(double value, int decPlaces) {
        return round(value, decPlaces, _rm);
    }

//    public static void main(String[] args) {
//        double x = 5555.445;
//        double y = RoundingMethod.round(x, -4, RM_DOWN);
//        System.out.println(y + " Rounded From " + x);
//    }

    /**
     * Returns a copy of this object.
     * 
     * @return a copy of this object.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Compares this object to the one specified
     * 
     * @param o
     *            the object to be compared to
     * @return true if objects are equals, false otherwise.
     */
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof NumberRoundingMethod) ) return false;

        NumberRoundingMethod p = (NumberRoundingMethod) o;
        if (_rm == p.getCode())
            return true;
        else
            return false;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(_rm);
    }

    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        _rm = in.readInt();
    }

    public Object readResolve() {
    	return NumberRoundingMethod.valueOf(_rm);
    }
    
    /*
     * Round amount to roundTo where roundTo can be any number like 500
     * 
     * @since 4.16 @param amount the number to be rounded @param roundTo the
     *        precision to which 'amount' is to be rounded @param rm the
     *        rounding method @return the rounded number
     */
    static public double round2(double amount, double roundTo, int rm) {
        //roundTo must be > 0
        if (roundTo == 0)
            return amount;
        roundTo = Math.abs(roundTo);
        if (amount == 0)
            return 0;
        if (amount < 0) {
            return -round2(-amount, roundTo, rm);
        }
        long v = (long) (amount / roundTo);
        double rem = (amount - (v * roundTo));
        double ret = v * roundTo;
        switch (rm) {
            case RM_NEAREST :
                if (rem >= roundTo / 2.)
                    ret += roundTo;
                break;
            case RM_UP :
                if (rem >= 0)
                    ret += roundTo;
                break;
            case RM_DOWN :
                break;
            default :
                throw new Error("RoundingMethod corrupted");
        }
        return ret;
    }

    static public NumberRoundingMethod get(int code) {
    	return valueOf(code);
    }

    static public NumberRoundingMethod get(String code) {
    	return valueOf(code);
    }

    public static NumberRoundingMethod valueOf(String code) {
    	if (code == null)
    		return null;
    	if (code.equals(S_NEAREST))
    		return R_NEAREST;
    	else if (code.equals(S_UP))
    		return R_UP;
    	else if (code.equals(S_DOWN))
    		return R_DOWN;
    	else
    		return null;
    }

    public static NumberRoundingMethod valueOf(int code) {
    	switch (code) {
    	case RM_NEAREST :
    		return R_NEAREST;
    	case RM_UP :
    		return R_UP;
    	case RM_DOWN :
    		return R_DOWN;
    	default :
    		return null;
    	}
    }
}
