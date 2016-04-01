package productPricing.pricingUtil;

import util.common.Period;

public class frequencyUtil {

	final public static int ZERO_COUPON = 0;
	final public static int NONE = 2;
	final public static int CONTINUOUS = 3;
	final public static int DAILY = 1;
	final public static int WEEKLY = 7;
	final public static int BIWEEKLY = 15;
	final public static int MONTHLY = 30;
	final public static int MON = 30;
	final public static int BMONTHLY = 60;
	final public static int QUARTERLY = 90;
	final public static int I_91D = 91;
	final public static int SEMI_ANNUAL = 180;
	final public static int I_182D = 182;
	final public static int ANNUAL = 360;
	final public static int I_364D = 364;
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

	final public static String months = "month";
	final public static String quarters = "quarter";
	final public static String semiAnnual = "semiAnnual";
	final public static String years = "year";
	final public static String days = "day";
	final public static String weeks = "week";

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
		if (s.equals(S_MON))
			return MON;
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

	public static int monthsForFrequency(String s) {

		if (s.equals(S_SEMI_ANNUAL))
			return 6;
		if (s.equals(S_MONTHLY))
			return 12;
		if (s.equals(S_MON))
			return 12;
		if (s.equals(S_QUARTERLY))
			return 4;
		if (s.equals(S_ANNUAL))
			return 12;
		if (s.equals(S_DAILY))
			return 1;
		if (s.equals(S_WEEKLY))
			return 7;
		if (s.equals(S_ZERO_COUPON))
			return 0;
		return -1;

	}

	public static int frequencyNumberToSubtract(String s) {

		if (s.equals(S_SEMI_ANNUAL))
			return 6;
		if (s.equals(S_MONTHLY))
			return 1;
		if (s.equals(S_MON))
			return 1;
		if (s.equals(S_QUARTERLY))
			return 3;
		if (s.equals(S_ANNUAL))
			return 12;
		if (s.equals(S_ZERO_COUPON))
			return 12;
		if (s.equals(S_DAILY))
			return 1;
		if (s.equals(S_WEEKLY))
			return 7;
		if (s.equals(S_ZERO_COUPON))
			return 0;
		return -1;

	}

	public static int noOfCompoundingCashflows(String s) {

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
			return 1;
		if (s.equals(S_WEEKLY))
			return 7;
		if (s.equals(S_ZERO_COUPON))
			return 0;
		return -1;

	}

	public static String getCriteria(String s) {

		if (s.equals(S_SEMI_ANNUAL))
			return semiAnnual;
		if (s.equals(S_MONTHLY))
			return months;
		if (s.equals(S_MON))
			return months;
		if (s.equals(S_QUARTERLY))
			return quarters;
		if (s.equals(S_ANNUAL))
			return years;
		if (s.equals(S_DAILY))
			return days;
		if (s.equals(S_WEEKLY))
			return weeks;
		if (s.equals(S_ZERO_COUPON))
			return years;
		return "none";

	}

	protected int _frequency;
	protected transient Period _tenor;

	public Period getTenor() {
		if (_tenor != null)
			return _tenor;
		switch (_frequency) {
		case ZERO_COUPON:
			_tenor = new Period(360);
			break;
		case NONE:
			_tenor = new Period(0);
			break;
		case DAILY:
			_tenor = new Period(1);
			break;
		case WEEKLY:
			_tenor = new Period(7);
			break;
		case BIWEEKLY:
			_tenor = new Period(15);
			break;
		case MONTHLY:
			_tenor = new Period(30);
			break;
		case BMONTHLY:
			_tenor = new Period(60);
			break;
		case QUARTERLY:
			_tenor = new Period(90);
			break;
		case I_91D:
			_tenor = new Period(91);
			break;
		case SEMI_ANNUAL:
			_tenor = new Period(180);
			break;
		case I_182D:
			_tenor = new Period(182);
			break;
		case ANNUAL:
			_tenor = new Period(360);
			break;
		case I_364D:
			_tenor = new Period(364);
			break;
		case CONTINUOUS:
			_tenor = new Period(0);
			break;
		case LUNAR:
			_tenor = new Period(28);
			break;
		default:

			_tenor = new Period(_frequency);
			break;
		}
		return _tenor;
	}
}
