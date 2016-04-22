package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import apps.newDealInvoker;
import apps.window.tradewindow.util.StaticDataCacheUtil;
import beans.Attribute;
import beans.CurrencyDefault;
import beans.CurrencyPair;
import beans.HolidayCode;
import beans.StartUPData;
import beans.TransferRule;
import util.common.CDateTime;
import util.common.DateU;

import util.common.CDate;
import util.common.NumberFormatUtil;
import util.common.NumberRoundingMethod;
import org.joda.time.DateTime;
import org.joda.time.Days;

import constants.CommonConstants;
import dsEventProcessor.EventProcessor;

//import oracle.sql.DATE;

public class commonUTIL {
	public final static String ENCODING = "UTF-8";
	static final String NULL_NUMBER_STRING = "";
	static protected String USER_HOME = null;
	static protected String USER_NAME = null;
	static Map<Locale, NumberFormat> _numberFormat = new HashMap<Locale, NumberFormat>();
	static Map<Locale, NumberFormat> _numberFormatNoGrouping = new HashMap<Locale, NumberFormat>();
	static Map<String, DecimalFormat> _decimalFormat = new HashMap<String, DecimalFormat>();
	static Map<String, DecimalFormat> _decimalFormatNoGrouping = new HashMap<String, DecimalFormat>();
	static Map<String, DecimalFormat> _patternDecimalFormat = new HashMap<String, DecimalFormat>();

	static final int MAX_YEAR_ALLOWED_IN_DATES = 9999;
	static final Map<Object, DateFormat> _dateFormat = new HashMap<Object, DateFormat>();
	static final Map<Object, DateFormat> _dateFormatPattern = new HashMap<Object, DateFormat>();
	static final Map<Object, DateFormat> _dateFormatSepPattern = new HashMap<Object, DateFormat>();
	static final Map<Object, DateFormat> _datetimeFormatFull = new HashMap<Object, DateFormat>();
	static final Map<Object, DateFormat> _datetimeFormatMedium = new HashMap<Object, DateFormat>();
	static final Map<Object, DateFormat> _datetimeFormatMediumShort = new HashMap<Object, DateFormat>();
	static final Map<Object, DateFormat> _datetimeFormatShortMedium = new HashMap<Object, DateFormat>();
	static final Map<Object, DateFormat> _twoDigDateFormat = new HashMap<Object, DateFormat>();
	static private Locale _fxLocale = new Locale("en", "US", "FIN_FX");

	static final Map<Object, DateFormat> _dateFormatCustom = new HashMap<Object, DateFormat>();
	static final Map<Object, DateFormat> _datetimeFormatCustom = new HashMap<Object, DateFormat>();

	static public void displayError(String name, String methodName, Exception e) {
		System.err.println("Classname : " + name + " : MethodName : "
				+ methodName + " :: " + e.getClass());
		new CosmosException("Classname : " + name + " : MethodName : "
				+ methodName + " :: " +e.getMessage(), e);
		return;

	}

	static public void displayError(String name, String methodName, Throwable e) {
		System.err.println("Classname : " + name + " : MethodName : "
				+ methodName + " :: " + e.getClass());
		new CosmosException("Classname : " + name + " : MethodName : "
				+ methodName + " :: "+e.getMessage(), e);
		return;

	}

	static public void publishEvent(EventProcessor event) {
		RemoteServiceUtil.publishEvent(event);
	}

	static public boolean getBooleanValue(int value) {

		if (value == 0) {
			return true;
		} else {
			return false;
		}
	}

	static public Color getColors() {
		// TODO Auto-generated method stub
		return new Color(232, 230, 215);
	}

	static public String[] convertVectortoSringArray(Vector v) {
		String name[] = null;
		int i = 0;
		if (v != null) {
			name = new String[v.size()];
			Iterator its = v.iterator();
			while (its.hasNext()) {
				name[i] = ((StartUPData) its.next()).getName();
				i++;
			}
		}
		return name;
		// TODO add your handling code here:
	}

	static public void convertSringArrayToVector(String[] s, Vector<String> data) {

		for (int i = 0; i < s.length; i++) {
			data.addElement(s[i]);

		}

		// TODO add your handling code here:
	}

	static public String[] convertStartupVectortoStringArray(Vector v) {

		if (isEmpty(v))
			return null;
		String values[] = new String[v.size()];
		return (String[]) v.toArray(values);

		// TODO add your handling code here:
	}

	/**
	 * Returns current date in the passed format
	 * 
	 * @author yogesh
	 * @overloaded
	 * @param dateFormatStr
	 * @return date
	 */
	static public String getCurrentDateTime(String dateFormatStr) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		Date date = new Date();

		return dateFormat.format(date);
	}

	/**
	 * return the contents of this file as a byte[]
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static byte[] readBinaryFile(InputStream is) throws IOException {

		if (is == null) {
			throw new IllegalArgumentException("InputStream is null");
		}

		byte[] buf = new byte[2048];
		ByteArrayOutputStream tmp = new ByteArrayOutputStream();

		for (int read; (read = is.read(buf, 0, buf.length)) != -1;) {
			tmp.write(buf, 0, read);
		}

		return tmp.toByteArray();
	}

	/**
	 * Returns the <code>String</code> pathname of the user's home directory
	 * where this <code>Defaults</code> object will be saved.
	 * 
	 * @return the <code>String</code> pathname of the user's home directory
	 */

	static public String getUserHome() {
		if (USER_HOME == null)
			USER_HOME = System.getProperty("user.home");
		return USER_HOME;
	}

	static public String replaceString(String str, String oldString,
			String newString) {
		if (str == null || oldString == null)
			return str;
		int index = str.indexOf(oldString);
		if (index < 0)
			return str;
		String newStr = "";
		String currentStr = str;
		while (index >= 0) {
			String before = "";
			if (index > 0) {
				before = currentStr.substring(0, index);
			}
			String after = currentStr.substring(index + oldString.length());
			newStr += before + newString;
			currentStr = after;
			index = currentStr.indexOf(oldString);
			if (index < 0)
				newStr += currentStr;
		}
		return newStr;
	}

	static public double converStringToDouble(String doubleValue) {
		if (commonUTIL.isEmpty(doubleValue))
			return 0.0;
		if (!checkCharInDouble(doubleValue))
			return 0.0;
		String dValue = doubleValue;
		Double doub = null;

		if (doubleValue.contains(",")) {
			doub = NumberFormatUtil.stringToNumber(doubleValue,
					Locale.getDefault());
		} else {
			doub = new Double(doubleValue);
		}
		return doub.doubleValue();
	}

	static public boolean checkCharInDouble(String doubleValue) {
		if (doubleValue == null || doubleValue.isEmpty()) {
			return false;
		}
		int len = doubleValue.length();
		
		for (int i = 0; i < len; ++i) {
			if (!(doubleValue.charAt(i) == '.'))  
			if (!Character.isDigit(doubleValue.charAt(i))) {
				{
					return false;
				}
			}

		}

		return true;

	}

	static public String converDoubleToStringWithCommas(String doubleValue) {
		if (doubleValue.equals("") || Double.parseDouble(doubleValue) == 0)
			return "0";

		return NumberFormatUtil.numberToString(Double.parseDouble(doubleValue),
				Locale.getDefault());
	}

	static public int converStringToInteger(String intValue) {
		Integer doub = new Integer(intValue.trim());
		return doub.intValue();
	}

	static public String converDoubleToString(double doubleValue) {
		Double doub = new Double(doubleValue);
		return doub.toString();

	}

	static public String numberToString(double amt, int numberOfDec) {
		return numberToString(amt, numberOfDec, null);
	}

	static public String numberToString(double amt, int numberOfdec,
			Locale locale) {
		return numberToString(amt, numberOfdec, locale, true);
	}

	/**
	 * Returns the String representation (with 10 decimals) of a given double.
	 * 
	 * @param rate
	 *            a double to convert
	 */
	static public String numberToString(double rate) {
		return numberToString(rate, Locale.getDefault());
	}

	static public String numberToString(double rate, Locale locale) {
		return numberToString(rate, locale, true);
	}

	static public String numberToString(double rate, Locale locale,
			boolean useGrouping) {

		if (Double.isNaN(rate)) {
			return NULL_NUMBER_STRING;
		}
		NumberFormat f = getNumberFormat(locale, useGrouping);
		synchronized (f) {
			f.setMaximumFractionDigits(getMaxDecimals(rate));
			return f.format(rate);
		}
	}

	/**
	 * The maximum number of digits JVM can handle is limited to 15
	 * (MAX_DEC_PLACES). The following code examines the total number of digits
	 * in the number and returns the appropriate number of decimals that JVM can
	 * reliably handle for this number.
	 * 
	 * @param number
	 * @return the maximum number of decimals the JVM can handle for the given
	 *         number.
	 */
	static public int getMaxDecimals(double number) {
		double absValue = Math.abs(number);
		int decimals = 0;
		if (absValue < 1) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES;
		} else if (absValue < 10) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 1;
		} else if (absValue < 100) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 2;
		} else if (absValue < 1000) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 3;
		} else if (absValue < 10000) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 4;
		} else if (absValue < 100000) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 5;
		} else if (absValue < 1000000) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 6;
		} else if (absValue < 10000000) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 7;
		} else if (absValue < 100000000) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 8;
		} else if (absValue < 1000000000) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 9;
		} else if (absValue < 10000000000.0) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 10;
		} else if (absValue < 100000000000.0) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 11;
		} else if (absValue < 1000000000000.0) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 12;
		} else if (absValue < 10000000000000.0) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 13;
		} else if (absValue < 100000000000000.0) {
			decimals = NumberRoundingMethod.MAX_DEC_PLACES - 14;
		}

		if (decimals < 0) {
			decimals = 0;
		}
		return decimals;
	}

	static public String numberToString(double amt, int numberOfdec,
			Locale locale, boolean useGrouping) {
		if (Double.isNaN(amt)) {
			return NULL_NUMBER_STRING;
		}

		if (numberOfdec < 0)
			return numberToString(amt);
		DecimalFormat df = getDecimalFormat(numberOfdec, locale, useGrouping);
		synchronized (df) {
			return df.format(amt);
		}
	}

	synchronized static DecimalFormat getDecimalFormat(int numberOfdec,
			Locale loc, boolean useGrouping) {

		if (loc == null)
			loc = Locale.getDefault();
		DecimalFormat f = useGrouping ? (DecimalFormat) _decimalFormat.get(loc
				.toString() + numberOfdec)
				: (DecimalFormat) _decimalFormatNoGrouping.get(loc.toString()
						+ numberOfdec);
		if (f == null) {
			NumberFormat numberFormat = getNumberFormat(loc, useGrouping);
			if (numberFormat instanceof DecimalFormat)
				f = (DecimalFormat) numberFormat.clone();
			else
				f = new DecimalFormat();
			f.setGroupingUsed(useGrouping);
			f.setMinimumFractionDigits(numberOfdec);
			f.setMaximumFractionDigits(numberOfdec);
			if (useGrouping) {
				_decimalFormat.put(loc.toString() + numberOfdec, f);
			} else {
				_decimalFormatNoGrouping.put(loc.toString() + numberOfdec, f);
			}
		}
		return f;
	}

	/**
	 * Returns the integer value of a given String.
	 * <p>
	 * Returns -1 if the string passed in is null or if an error is encountered
	 * when parsing the string to an integer.
	 * 
	 * @param s
	 *            a String to decode
	 */
	static public int stringToInteger(String s) {
		return stringToInteger(s, true);
	}

	static public int stringToInteger(String s, boolean useGrouping) {
		int value = -1;
		if (isEmpty(s))
			return value;

		NumberFormat f = getNumberFormat(Locale.getDefault(), useGrouping);
		synchronized (f) {
			f.setMaximumFractionDigits(DECIMALS);
			try {
				value = f.parse(s.trim()).intValue();
			} catch (Exception e) {
				displayError("CommonUtil", "stringToInteger", e);
			}
		}
		return value;
	}

	static DecimalFormat getDecimalFormat(int numberOfDec, boolean useGrouping) {

		Locale loc = Locale.getDefault();
		return getDecimalFormat(numberOfDec, loc, useGrouping);
	}

	static public boolean validatePhoneNumberField(String value) {
		String sPhoneNumber = "605-8889999";
		boolean flag = false;
		Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
		Matcher matcher = pattern.matcher(sPhoneNumber);

		if (matcher.matches()) {
			flag = true;
		}
		return flag;
	}

	static public Timestamp getTimeStamp(String date) {
		String NEW_FORMAT = CommonConstants.SDF_DATE_TIME_FORMAT;

		SimpleDateFormat formats = new SimpleDateFormat(NEW_FORMAT);

		return Timestamp.valueOf(formats.format(stringToDate(date, true)));
	}
	
	

	static public String getTimeStampToString(Timestamp date) {
		if (date == null) {
			commonUTIL
					.display("CommonUtil",
							"getTimeStampToString  timestamp getting null <<<<<<<<<<<<<<<<<<<<<<");
			return "";
		}
		String NEW_FORMAT = CommonConstants.SDF_DATE_TIME_FORMAT;
		SimpleDateFormat formats = new SimpleDateFormat(NEW_FORMAT);
		String dateF = formats.format(date.getTime());
		return dateF.toString();
	}
	static public String getTimeStampToString(Date date) {
		if (date == null) {
			commonUTIL
					.display("CommonUtil",
							"getTimeStampToString  timestamp getting null <<<<<<<<<<<<<<<<<<<<<<");
			return "";
		}
		String NEW_FORMAT = CommonConstants.SDF_DATE_TIME_FORMAT;
		SimpleDateFormat formats = new SimpleDateFormat(NEW_FORMAT);
		String dateF = formats.format(date.getTime());
		return dateF.toString();
	}

	static public String getStringFromDoubleExp(double amount) {
		double dd = NumberRoundingMethod.round(amount, 2,
				NumberRoundingMethod.RM_NEAREST);
		String value = BigDecimal.valueOf(dd).toPlainString();
		int len = value.length() - value.indexOf(".");
		/*
		 * if(value.contains(".")) { if(len == 2) value = value.substring(0,
		 * value.indexOf(".")+2); if(len >= 3) value = value.substring(0,
		 * value.indexOf(".")+3); }
		 */
		return value;
	}

	static public String getStringFromDoubleExp(double amount, int decimal) {
		double dd = NumberRoundingMethod.round(amount, decimal,
				NumberRoundingMethod.RM_NEAREST);
		String value = BigDecimal.valueOf(dd).toPlainString();
		int len = value.length() - value.indexOf(".");
		/*
		 * if(value.contains(".")) { if(len == 2) value = value.substring(0,
		 * value.indexOf(".")+2); if(len >= 3) value = value.substring(0,
		 * value.indexOf(".")+3); }
		 */
		return value;
	}

	static public String getStringFromDoubleExp(double amount, String currency) {
		CurrencyDefault currencyD = StaticDataCacheUtil
				.getCurrencyDefault(currency);
		int decimal = currencyD.getCurrencyDecimal();
		double dd = NumberRoundingMethod.round(amount, decimal,
				NumberRoundingMethod.RM_NEAREST);
		String value = BigDecimal.valueOf(dd).toPlainString();
		int len = value.length() - value.indexOf(".");
		/*
		 * if(value.contains(".")) { if(len == 2) value = value.substring(0,
		 * value.indexOf(".")+2); if(len >= 3) value = value.substring(0,
		 * value.indexOf(".")+3); }
		 */
		return value;
	}

	// this is requred for Rate columns only.
	static public String getStringFromDoubleExpRates(double amount) {
		String value = BigDecimal.valueOf(amount).toPlainString();
		int len = value.length() - value.indexOf(".");
		if (value.contains(".")) {
			if (len == 2)
				value = value.substring(0, value.indexOf(".") + 2);
			if (len >= 3)
				value = value.substring(0, value.indexOf(".") + 3);
		}
		return value;
	}

	static public void setLabelFont(JComponent label) {
		label.setFont(new Font("Arial", Font.BOLD, 13));

	}

	static public void setBackGroundColor(JComponent label) {
		// label.setBackground(getColors());

	}

	static public DateU getDate(Date date) {

		return DateU.valueOf(date);
	}

	public static String doubleFormat(double value) {
		DecimalFormat df = new DecimalFormat("############.##");
		return df.format(value);

	}

	public static double roundToZero(double value) {
		if (Math.abs(value) < 1E-100) {
			return 0.0;
		}

		return value;
	}

	static public StringBuffer unzipBytes(byte bytes[]) throws IOException,
			InterruptedException {
		try {
			ByteArrayInputStream is = new ByteArrayInputStream(bytes);
			GZIPInputStream gzin = new GZIPInputStream(is);
			InputStreamReader reader = new InputStreamReader(gzin);
			BufferedReader buf = new BufferedReader(reader);
			String line = null;
			StringBuffer log = new StringBuffer(1024);
			while ((line = buf.readLine()) != null) {
				log.append(line).append(System.getProperty("line.separator"));
			}
			return log;
		} catch (Exception e) {
			commonUTIL.displayError("CommonUtil", "unzipBytes", e);
			return null;
		}
	}

	static public int parseInt(String s) {
		try {
			return isEmpty(s) ? Integer.MIN_VALUE : Integer.parseInt(s
					.replaceAll("\\+|,|\\s", ""));
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}

	static public boolean isEmpty(String s) {
		return ((s == null) || (s.trim().length() == 0) || s
				.equalsIgnoreCase("null"));
	}

	static public boolean isEmpty(String[] s) {
		return ((s == null) || (s.length == 0));
	}

	/**
	 * @param collection
	 * @return true if map is not null and size not equal to zero
	 * @see java.util.Map#size()
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return (map == null || map.size() == 0);
	}

	static public java.sql.Date convertStringtoSQLDate(String date) {
		if (isEmpty(date))
			return null;
		return convertSQLDate(stringToDate(date, true));

	}

	static public void showAlertMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, null,
				JOptionPane.INFORMATION_MESSAGE);
	}

	static public void showPopuUP(String msg) {
		JLabel comp = new JLabel(msg);
		JOptionPane.showInternalInputDialog(comp, new JTextField());
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	static public boolean isStringDate(String date) {
		boolean isdate = true;
		if (!isEmpty(date)) {
			java.util.Date date1 = stringToDate(date, false);
			if (date1 == null)
				isdate = false;
		} else {
			isdate = false;
		}
		return isdate;

	}

	static public java.sql.Timestamp getSQLTimeStamp() {
		Calendar currentDate = Calendar.getInstance();
		return new java.sql.Timestamp(currentDate.getTimeInMillis());

	}

	static public java.sql.Timestamp convertStringtoSQLTimeStamp(String date) {
		return new java.sql.Timestamp(stringToDate(date, true).getTime());

	}

	/*
	 * static public String convertSQLDatetoString(java.sql.Timestamp ts) { long
	 * timeValue = ts.getTime(); long fractionSeconds = timeValue % 1000; // The
	 * millisecond component if (fractionSeconds == 0) {
	 * 
	 * timeValue += ts.getNanos() / 1000000; // convert to milliseconds } Date d
	 * = new Date(timeValue); return dateToString(d); }
	 */
	static public String convertSQLDatetoString(java.sql.Timestamp ts) {
	 
		return getTimeStampToString(ts);

	}

	static public java.sql.Date convertSQLDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	static public String getCurrentDate(java.util.Date date) {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(
				CommonConstants.SDF_DATE_FORMAT);
		String dateNow = formatter.format(currentDate.getTime());

		return dateNow;
	}

	static public String getDateDefaultFormat(java.util.Date date) {

		SimpleDateFormat formatter = new SimpleDateFormat(
				CommonConstants.SDF_DATE_FORMAT);
		String dateNow = formatter.format(date);

		return dateNow;
	}

	static public java.util.Date getCurrentDate() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = getDateFormat();
		new SimpleDateFormat(CommonConstants.SDF_DATE_FORMAT);
		return (java.util.Date) currentDate.getTime();
	}

	static public String getCurrentDateTime() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(
				CommonConstants.SDF_DATE_TIME_FORMAT);
		String dateNow = formatter.format(currentDate.getTime());

		return dateNow;
	}

	static public Date getCurrentTimeDate() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(
				CommonConstants.SDF_DATE_TIME_FORMAT);
		String dateNow = formatter.format(currentDate.getTime());
		return formatter.getCalendar().getTime();
	}

	static public Date getCurrentTimeDate(String date) {

		SimpleDateFormat formatter = new SimpleDateFormat(
				CommonConstants.SDF_DATE_TIME_FORMAT);
		Date parsedDate;
		try {
			parsedDate = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		formatter.format(parsedDate.getTime());
		return formatter.getCalendar().getTime();
	}

	static public java.sql.Timestamp getStringToTimestamp(String dateTime) {
		if (isEmpty(dateTime))
			return null;
		if (dateTime.contains("-"))
			dateTime = dateTime.replace("-", "/");
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				CommonConstants.SDF_DATE_TIME_FORMAT);
		Timestamp timestamp = null;
		Date parsedDate;
		try {
			parsedDate = dateFormat.parse(dateTime);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return timestamp;
	}

	static public String getAddDays(int days) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, days);
		SimpleDateFormat formatter = new SimpleDateFormat(
				CommonConstants.SDF_DATE_FORMAT);
		String dateNow = formatter.format(currentDate.getTime());

		return dateNow;
	}

	static public java.util.Date stringToDate(String dateStr, boolean isLenient) {
		if (dateStr.contains("-"))
			dateStr = dateStr.replace("-", "/");
		SimpleDateFormat format = new SimpleDateFormat(
				CommonConstants.SDF_DATE_FORMAT);
		if (dateStr.contains("-")) {
			format = new SimpleDateFormat(CommonConstants.SDF_DATE_FORMAT);
		}
		format.setLenient(isLenient);
		java.util.Date date = null;
		boolean flag = false;
		try {
			date = format.parse(dateStr);
			if (date != null)
				return date;
			else
				return null;
		} catch (Exception e) {
			displayError("CommonUTIL", "stringTOdate", e);
			return date;
		}

	}

	static public synchronized void display(String name, String message) {
		// if(!name.contains("SQL"))
		// LogPublishUtil.addMessage("Classname : " + name + " : MethodName : "
		// +message + " :: ");
		System.out.println("Classname : " + name + " : MethodName : " + message
				+ " :: ");
		// DebugEventProcessor deb = new DebugEventProcessor();
		// deb.setComments("Classname : " + name + " : MethodName : " +message +
		// " :: ");
		// publishEvent(deb);
		// deb = null;
	}

	static public synchronized void display(String logName, String name,
			String message) {
		// if(!name.contains("SQL"))
		// LogPublishUtil.addMessage("logName : " + logName + " : ClassName " +
		// name + "  message : " +message + " :: ");
		System.out.println("Classname : " + name + " : MethodName : " + message
				+ " :: ");
		// DebugEventProcessor deb = new DebugEventProcessor();
		// deb.setComments("Classname : " + name + " : MethodName : " +message +
		// " :: ");
		// publishEvent(deb);
		// deb = null;
	}

	static SimpleDateFormat formator = new SimpleDateFormat(
			CommonConstants.SDF_DATE_FORMAT);

	/**
	 * Returns the IP address of the host we're running on.
	 */
	public static String getLocalHost() {
		InetAddress addr = null;
		String strAddr = null;
		try {
			addr = InetAddress.getLocalHost();
			strAddr = addr.getHostAddress();
		} catch (Exception e) {
			commonUTIL.displayError("CommonUTIL", "getLocalHost() ", e);
		}
		return strAddr;
	}

	public static String getLocalHostName() {
		InetAddress addr = null;
		String strAddr = null;
		try {
			addr = InetAddress.getLocalHost();

		} catch (Exception e) {
			commonUTIL.displayError("CommonUTIL", "getLocalHostName() ", e);
		}
		return addr.getHostName();
	}

	public static String getServerIP() {
		return getLocalHost();
	}

	/**
	 * Answer true if the specified host names the one we're running on.
	 * 
	 * @param hostNameOrIPAddr
	 *            The host to test: either it's string name, or the string form
	 *            of its IP address (e.g. "166.24.219.1").
	 */

	public static boolean isLocalHost(String hostNameOrIPAddr) {
		InetAddress localAddr, argAddr;
		// Following InetAddress calls may throw UnknownHostException
		try {
			localAddr = InetAddress.getLocalHost();
			argAddr = InetAddress.getByName(hostNameOrIPAddr);
		} catch (UnknownHostException ex) {
			commonUTIL.displayError("CommonUTIL", "isLocalHost() ", ex);
			// Either we can't even this this host's IP address (!!), or
			// we can't get the IP address for the given host
			return false; // Either way, best guess is that this host is not the
							// given host
		}
		return localAddr.equals(argAddr);
	}

	public boolean checkDateValidation(Date startDate, Date endDate) {
		if (startDate.after(endDate))
			return false;
		else
			return true;

	}

	public static String getDateFormat(Date date) {
		// System.out.println(date.getDay() + " " + date.getYear() + " " +
		// date.getMonth());
		// System.out.println(date.get);
		String dd = "";
		if (date == null)
			return dd;
		SimpleDateFormat sdf = new SimpleDateFormat(
				CommonConstants.SDF_DATE_FORMAT);
		dd = sdf.format(date);
		return dd;

	}

	public static SimpleDateFormat getDateFormat() {
		// System.out.println(date.getDay() + " " + date.getYear() + " " +
		// date.getMonth());
		// System.out.println(date.get);
		String dd = "";

		SimpleDateFormat sdf = new SimpleDateFormat(
				CommonConstants.SDF_DATE_FORMAT);
		return sdf;

	}

	public static SimpleDateFormat getDateTimeFormat() {
		// System.out.println(date.getDay() + " " + date.getYear() + " " +
		// date.getMonth());
		// System.out.println(date.get);
		String dd = "";

		SimpleDateFormat sdf = new SimpleDateFormat(
				CommonConstants.SDF_DATE_TIME_FORMAT);
		return sdf;

	}

	public static String getOnlyDate(Date fromDate1) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				CommonConstants.SDF_DATE_FORMAT);
		String sfromValue = sdf.format(fromDate1);
		return sfromValue;
	}

	public static String getOnlyDateFromStringDate(String fromDate1) {

		SimpleDateFormat sdf = new SimpleDateFormat(
				CommonConstants.SDF_DATE_FORMAT);
		String sfromValue = sdf
				.format(commonUTIL.stringToDate(fromDate1, true));
		return sfromValue;
	}

	public static boolean between2dates(Date fromDate1, Date toDate2,
			Date valueDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				CommonConstants.SDF_DATE_FORMAT);
		String sfromValue = sdf.format(fromDate1);
		String svalueDate = sdf.format(valueDate);
		String stoDate2 = sdf.format(toDate2);

		if (valueDate.compareTo(fromDate1) == 0)
			return true;
		if (valueDate.after(fromDate1) && valueDate.before(toDate2)) {
			return true;
		} else {
			boolean flag = false;
			if (sfromValue.equalsIgnoreCase(svalueDate)
					&& valueDate.before(toDate2)) {
				return true;
			}
			if (stoDate2.equalsIgnoreCase(svalueDate)
					&& valueDate.after(fromDate1)) {
				return true;
			}
		}
		return false;
	}

	public static void setPath(String path) {
		// this.path = path;
		// TODO Auto-generated method stub

	}

	// String path = "";
	/**
	 * 
	 * @author yogesh
	 * @return current date in string format
	 */
	static public String getCurrentDateInString() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(
				CommonConstants.SDF_DATE_TIME_FORMAT);
		return formatter.format(currentDate.getTime());
	}

	public static String convertDateTimeTOString(Date date) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat(
				CommonConstants.SDF_DATE_TIME_FORMAT);
		return formatter.format(date.getTime());
	}

	/*
	 * @author yogesh
	 * 
	 * @return date in string format
	 */

	public static String convertDateTOString(Date date) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat(
				CommonConstants.SDF_DATE_FORMAT);
		return formatter.format(date.getTime());
	}

	/**
	 * Returns true if date1 is greater than date2
	 * 
	 * @author yogesh
	 * @param date1
	 * @param date2
	 * @return
	 */
	static public boolean checkGreaterDate(Date date1, Date date2) {

		if (date1.after(date2)) {

			return true;

		} else {

			return false;
		}

	}

	/**
	 * Returns a date object for a day, month and year provided
	 * 
	 * @author yogesh
	 * @param day
	 * @param month
	 * @param year
	 * @return Date
	 */

	static public Date setDate(int day, int month, int year) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);

		return cal.getTime();

	}

	/**
	 * Returns true if the year is a leap year
	 * 
	 * @author yogesh
	 * @param year
	 * @return boolean
	 */
	static public boolean isLeapYear(int year) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;

	}

	/**
	 * Returns number of months between two dates
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return integer
	 */
	static public int diffInMonths(Date startDate, Date endDate) {

		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);

		int diffYear = endCalendar.get(Calendar.YEAR)
				- startCalendar.get(Calendar.YEAR);
		return (diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar
				.get(Calendar.MONTH));

	}

	/**
	 * Returns number of days between two dates
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return long
	 */
	static public long diffInDays(Date startDate, Date endDate) {

		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);

		long miliSecondForStartCalendar = startCalendar.getTimeInMillis();
		long miliSecondEndCalendar = endCalendar.getTimeInMillis();

		long diffInMilis = miliSecondEndCalendar - miliSecondForStartCalendar;

		return diffInMilis / (24 * 60 * 60 * 1000);

	}

	/**
	 * Returns number of days between two dates using Joda DateTime Library
	 * 
	 * @author yogesh
	 * @param startDate
	 * @param endDate
	 * @return long
	 */

	static public long jodaDiffInDays(Date startDate, Date endDate) {

		DateTime startTime = null;
		DateTime endTime = null;

		startTime = new DateTime(startDate);

		endTime = new DateTime(endDate);

		long diffInDays = Days.daysBetween(startTime, endTime).getDays();

		return diffInDays;

	}

	/**
	 * Adds/Substract the no. of days to a date
	 * 
	 * @author yogesh
	 * @param updateDate
	 * @param noOfDays
	 * @return Date
	 */

	static public Date addSubtractDate(Date updateDate, int noOfDays) {

		Calendar newDate = Calendar.getInstance();
		newDate.setTime(updateDate);
		newDate.add(Calendar.DATE, noOfDays);

		return newDate.getTime();

	}

	/**
	 * Adds/Substract the no. of days to a date which is in string format
	 * 
	 * @author yogesh
	 * @param dateInString
	 * @param noOfDays
	 * @return date in string format
	 */
	static public String addSubtractDate(String dateInString, int noOfDays) {

		Calendar newDate = Calendar.getInstance();
		newDate.setTime(stringToDate(dateInString, true));
		newDate.add(Calendar.DATE, noOfDays);

		SimpleDateFormat formatter = new SimpleDateFormat(
				CommonConstants.SDF_DATE_FORMAT);
		String dateNow = formatter.format(newDate.getTime());

		return dateNow;

	}

	/**
	 * Returns a date after subtracting any one of the following: Day, Month,
	 * Quarter, SemiAnnual or a Year
	 * 
	 * @author yogesh
	 * @param updateDate
	 * @param deductionNo
	 * @param criteria
	 * @return Date
	 */
	static public Date addSubtractDate(Date updateDate, int addSubtratcNo,
			String criteria) {

		Calendar newDate = Calendar.getInstance();
		newDate.setTime(updateDate);
		int lastDayOfMonth = newDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		int year = newDate.get(Calendar.YEAR);
		int month = newDate.get(Calendar.MONTH);
		int day = newDate.get(Calendar.DAY_OF_MONTH);

		/*
		 * if ( ( month == 1 && ( lastDayOfMonth == 29 && isLeapYear(year) ) ||
		 * ( lastDayOfMonth == 28 && !isLeapYear(year) ) ) ) {
		 * 
		 * criteria = "skip"; newDate.set(Calendar.MONTH, 0); int lastDay =
		 * newDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		 * newDate.set(Calendar.DAY_OF_MONTH, lastDay);
		 * 
		 * 
		 * }
		 */

		if (criteria.equals("day")) {

			newDate.add(Calendar.DATE, addSubtratcNo);

		} else if (criteria.equals("week")) {

			newDate.add(Calendar.DATE, addSubtratcNo);

		} else if (criteria.equals("month")) {

			if (lastDayOfMonth == day) {

				newDate.add(Calendar.DATE,
						-newDate.getActualMaximum(Calendar.DAY_OF_MONTH));

			} else {

				newDate.add(Calendar.MONTH, addSubtratcNo);

			}

		} else if (criteria.equals("quarter")) {

			if (lastDayOfMonth == day) {

				newDate.add(Calendar.MONTH, addSubtratcNo);
				newDate.set(Calendar.DATE,
						newDate.getActualMaximum(Calendar.DAY_OF_MONTH));

			} else {

				newDate.add(Calendar.MONTH, addSubtratcNo);

			}

		} else if (criteria.equals("semiAnnual")) {

			if (lastDayOfMonth == day) {

				newDate.add(Calendar.MONTH, addSubtratcNo);
				newDate.set(Calendar.DATE,
						newDate.getActualMaximum(Calendar.DAY_OF_MONTH));

			} else {

				newDate.add(Calendar.MONTH, addSubtratcNo);

			}

		} else if (criteria.equals("year")) {

			if (lastDayOfMonth == day) {

				newDate.add(Calendar.MONTH, addSubtratcNo);
				newDate.set(Calendar.DATE,
						newDate.getActualMaximum(Calendar.DAY_OF_MONTH));

			} else {

				newDate.add(Calendar.MONTH, addSubtratcNo);

			}

		}

		return newDate.getTime();

	}

	/**
	 * Returns an integer indicating day in a week. Sunday is 1 whereas Saturday
	 * has integer value of 7
	 * 
	 * @param dateInString
	 * @param dateFormat
	 * @return int
	 */
	static int getWeekDay(String dateInString, String dateFormat) {

		Calendar c = Calendar.getInstance();
		c.setTime(stringToDate(dateInString, true));

		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

		return dayOfWeek;
	}

	/**
	 * Returns the last day of a month for a given date of String format
	 * 
	 * @author yogesh
	 * @param dateInString
	 * @return int
	 */

	static public int getLastDayofMonth(String dateInString) {

		Calendar c = Calendar.getInstance();
		c.setTime(stringToDate(dateInString, true));
		int lastDayofMonth = c.getActualMaximum(c.DAY_OF_MONTH);

		return lastDayofMonth;

	}

	/**
	 * Returns the last day of a month for a given date of Date format
	 * 
	 * @author yogesh
	 * @param checkDate
	 * @return
	 */

	static public int getLastDayofMonth(Date checkDate) {

		Calendar c = Calendar.getInstance();
		c.setTime(checkDate);
		int lastDayofMonth = c.getActualMaximum(c.DAY_OF_MONTH);

		return lastDayofMonth;

	}

	/*
	 * public static boolean isEmpty(Vector rules) { // TODO Auto-generated
	 * method stub if(rules == null || rules.isEmpty()) return true; return
	 * false; }
	 */

	/**
	 * Compares two given objects.
	 * <p>
	 * If both are null, returns true. If one of them is null, but not the other
	 * one, returns false. Otherwise, it uses the <code>equals()</code> method
	 * of the first Object.
	 * 
	 * @param v1
	 *            an Object
	 * @param v2
	 *            an Object
	 * 
	 * @return A boolean, true if both objects are identical, or false otherwise
	 */
	static public boolean isSame(Object v1, Object v2) {
		if (v1 == null && v2 == null)
			return true;
		if (v1 == null && v2 != null)
			return false;
		if (v2 == null && v1 != null)
			return false;
		if (v1.getClass().isArray() && v2.getClass().isArray()) {
			return Arrays.deepEquals((Object[]) v1, (Object[]) v2);
		}
		return v1.equals(v2);
	}

	public static String lpad(String str, int len, char padChar) {
		return pad(str, len, padChar, false);
	}

	public static String rpad(String str, int len, char padChar) {
		return pad(str, len, padChar, true);
	}

	protected static String pad(String str, int len, char padChar, boolean rpad) {
		if (str.length() == len)
			return str;
		else if (str.length() > len)
			return str.substring(0, len);
		else {
			int size = len - str.length();
			char[] chars = new char[size];
			for (int i = 0; i < size; i++)
				chars[i] = padChar;
			if (rpad)
				return str.concat(String.valueOf(chars));
			else
				return String.valueOf(chars).concat(str);
		}
	}

	static public final String DEFAULT_LIST_SEPARATOR_CHAR = ",";

	public static Collection<String> stringToCollection(Collection<String> c,
			String s, String separator, boolean trim) {
		return stringToCollection(c, s, separator, trim, false);
	}

	public static Vector<String> string2Vector(String s, String separator,
			boolean trim) {
		return (Vector<String>) stringToCollection(new Vector<String>(), s,
				separator, trim);
	}

	public static Vector<String> string2Vector(String s) {
		return (Vector<String>) stringToCollection(new Vector<String>(), s,
				DEFAULT_LIST_SEPARATOR_CHAR, false);
	}

	/**
	 * Add the components of the string <code>s</code> to the collection
	 * <code>c</code>. Each component of the string is separated by the string
	 * <code>separator</code>.
	 * 
	 * @param c
	 *            The collection to add the components to.
	 * @param s
	 *            The string that contains the components
	 * @param separator
	 *            The separator between components
	 * @param trim
	 *            True indicates that the components of the string should be
	 *            trimmed of leading and trailing whitespace.
	 * @param skipEmptyStrings
	 *            Do not include any empty strings in the resulting collection.
	 * 
	 * @return The collection <code>c</code> or null if there were some error
	 *         parsing the initial string <code>s</code>.
	 */
	public static Collection<String> stringToCollection(Collection<String> c,
			String s, String separator, boolean trim, boolean skipEmptyStrings) {
		try {
			if (isEmpty(s)) {
				return c;
			}
			int index;
			int prevIndex = 0;
			String str;
			while ((index = s.indexOf(separator, prevIndex)) != -1) {
				str = s.substring(prevIndex, index);
				if (trim) {
					str = str.trim();
				}
				prevIndex = index + separator.length();

				if (skipEmptyStrings && commonUTIL.isEmpty(str)) {
					continue;
				}
				c.add(str);
			}
			str = s.substring(prevIndex, s.length());

			if (trim) {
				str = str.trim();
			}

			if (skipEmptyStrings && commonUTIL.isEmpty(str)) {
				return c;
			}

			c.add(str);

			return c;
		} catch (Exception x) {
			commonUTIL.displayError("", "", x);
		}
		return null;
	}

	public static String getOnlyDate(String settleDate) {
		// TODO Auto-generated method stub
		if (settleDate == null || settleDate.length() < 10)
			return null;
		return settleDate.substring(0, 10).trim();
	}

	/**
	 * Returns the String representation of a CDate using a given locale.
	 * <p>
	 * The DateFormat is LONG.
	 * 
	 * @param CDate
	 *            a CDate to convert
	 * @param loc
	 *            a Locale
	 */
	public static String dateToLongString(Date date, Locale loc) {
		if (date == null)
			return "";

		DateFormat dateformat = DateFormat
				.getDateInstance(DateFormat.LONG, loc);
		dateformat.setTimeZone(TimeZone.getDefault());
		return dateformat.format(date);
	}

	/**
	 * Returns the String representation of a Date using a given Locale.
	 * <p>
	 * The DateFormat is SHORT for Date, and the DateFormat is MEDIUM for Time.
	 * 
	 * @param date
	 *            a Date to convert
	 * @param loc
	 *            a Locale
	 */
	public static String timestampToString(Date date, Locale loc) {
		if (date == null)
			return "";
		DateFormat dateformat = DateFormat.getDateTimeInstance(
				DateFormat.SHORT, DateFormat.MEDIUM, loc);
		dateformat.setTimeZone(TimeZone.getDefault());
		String s = dateformat.format(date);
		// Now replace the characters which are non-
		// standard to calypso
		if (s != null && s.indexOf(".") > 0 && s.indexOf(".") < 10) {
			s = s.replace('.', '/');
		}
		if (s != null && s.indexOf("-") > 0 && s.indexOf("-") < 10) {
			s = s.replace('-', '/');
		}
		return s;
	}

	/**
	 * @param collection
	 * @return true if collection is not null and not empty
	 * @see java.util.Collection#isEmpty()
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}

	static public final int DECIMALS = NumberRoundingMethod.MAX_DEC_PLACES - 2;

	synchronized static NumberFormat getNumberFormat(Locale loc,
			boolean useGrouping) {
		NumberFormat f = useGrouping ? (NumberFormat) _numberFormat.get(loc)
				: (NumberFormat) _numberFormatNoGrouping.get(loc);
		if (f == null) {
			f = NumberFormat.getInstance(loc);
			f.setMaximumFractionDigits(DECIMALS);
			if (useGrouping) {
				_numberFormat.put(loc, f);
			} else {
				f.setGroupingUsed(false);
				_numberFormatNoGrouping.put(loc, f);
			}
		}
		return f;
	}

	/*
	 * Replaces a common from a string format number
	 * 
	 * @author yogesh
	 * 
	 * @param replaceCommaInNumber
	 * 
	 * @return double
	 */

	static public double replaceCommaInNumber(String stringNumber) {

		// stringNumber.replaceAll("^\"|\"$", "");
		// stringNumber.replaceAll(",","");
		stringNumber.replaceAll(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", "");
		stringNumber.replaceAll("[^0-9.]", "");
		stringNumber.replaceAll(",", "");
		System.out.println(stringNumber);
		return Double.parseDouble(stringNumber.trim());
	}

	/**
	 * <p>
	 * Escapes the HTML characters in {@code s} so that the string will be
	 * presented as is in an HTML renderer such as a web browser. The characters
	 * &amp;, &lt;, &gt; and &quot; are replaced by their HTML escape character
	 * equivalents of &amp;amp;, &amp;lt;, &amp;gt; and &amp;quot;.
	 * </p>
	 * <p>
	 * If s is null then null will be returned.
	 * 
	 * @param s
	 *            the string to convert
	 * @return the string argument with the HTML specific characters replaced
	 *         with their HTML escape equivalents.
	 */
	public static String htmlEncode(String s) {
		return replaceCharacters(s, Arrays.asList('&', '<', '>', '"'),
				Arrays.asList("&amp;", "&lt;", "&gt;", "&quot;"));
	}

	/**
	 * <p>
	 * Replace each occurrence of a character in {@code source} that is
	 * currently in {@code search} and replace the character with string in the
	 * same position in the {@code replacement} list.
	 * </p>
	 * <p>
	 * {@code source} may be null, in which case null will be returned.
	 * {@code search} and {@code replacement} must not be null and must be of
	 * the same length. The characters in the {@code source} should not contain
	 * duplicates. If there is a duplicate then the last occurrence will be
	 * used.
	 * </p>
	 * <p>
	 * The substituted strings are not re-searched for replacements. Only the
	 * original string, {@code source}, is used to finding characters to be
	 * replaced.
	 * </p>
	 * <p>
	 * The original string is untouched.
	 * </p>
	 * 
	 * @param source
	 *            the string to replace characters in.
	 * @param search
	 *            the list of characters that should be replaced.
	 * @param replacement
	 *            the list of strings to replace characters contained in
	 *            {@code search}.
	 * @return The original string with each character that is contained in
	 *         {@code search} replaced by the text in {@code replacement} at the
	 *         corresponding position.
	 * @throws NullPointerException
	 *             if {@code search} or {@replacement} are null.
	 * @throws IllegalArgumentException
	 *             if the length of {@code search} is not the same as the length
	 *             of {@code replacement}.
	 */
	public static String replaceCharacters(String source,
			List<Character> search, List<String> replacement) {
		if (search == null) {
			throw new NullPointerException("Search set is null");
		}
		if (replacement == null) {
			throw new NullPointerException("replacement set is null");
		}
		if (search.size() != replacement.size()) {
			throw new IllegalArgumentException(
					"Lengths of search and replacement sets are different");
		}
		if (source == null) {
			return null;
		}
		Map<Character, Integer> candidates = new HashMap<Character, Integer>();
		int index = 0;
		for (Character c : search) {
			candidates.put(c, Integer.valueOf(index++));
		}
		String[] replacementStrings = replacement
				.toArray(new String[replacement.size()]);
		StringBuilder finalString = new StringBuilder();
		for (char c : source.toCharArray()) {
			// Character character = c;
			Integer candidateIndex = candidates.get(c);
			if (candidateIndex == null) {
				finalString.append(c);
			} else {
				finalString
						.append(replacementStrings[candidateIndex.intValue()]);
			}
		}
		return finalString.toString();
	}

	static public Properties getPropertiesFromFile(String fileName) {
		return baseGetPropertiesFromFile(fileName);
	}

	/**
	 * Load the properties in the property file attached to the Stream into
	 * props. If any massaging has to happen then this will be done as well. For
	 * instance only allowing Client Password when that is approved.
	 * 
	 * @param stream
	 *            Stream that is connected to a Property stream.
	 * @param props
	 *            The properties instance to add the properties in the stream
	 *            to.
	 * @throws IOException
	 *             Thrown when there is an exception loading the properties from
	 *             the stream.
	 */
	static private void loadPropertiesFromStream(InputStream stream,
			Properties props) throws IOException {
		props.load(stream);

	}

	/**
	 * Returns the login default settings that are stored in the specified Env
	 * file.
	 * 
	 * @param fileName
	 *            the <code>String</code> name of the saved Env file
	 * @return the requested <code>Properties</code> object
	 */
	static public Properties baseGetPropertiesFromFile(String fileName) {
		Properties props = new Properties();
		try {
			File file = new File(fileName);
			if (file.exists()) {
				InputStream stream = new FileInputStream(file);
				if (stream != null) {
					loadPropertiesFromStream(stream, props);
					try {
						stream.close();
					} catch (Exception ee) {
					}
				}
			}
		} catch (Exception ae) {
			// Log.error("Defaults", ae);
		}
		return props;
	}

	/**
	 * Saves the passed <code>Properties</code> object as a file, giving it the
	 * specified name and recording a comment on it.
	 * 
	 * @param p
	 *            The <code>Properties</code> object to be saved as a file.
	 * @param fileName
	 *            The <code>String</code> name of the file to be saved.
	 * @param comment
	 *            The <code>String</code> comment to be saved in this file.
	 */
	static public void saveToFile(Properties p, String fileName, String comment) {
		try {
			// cipherDBPassword(p);
			// cipherClientPassword(p);
			// cipherCLSIOSwapPassword(p);
			File file = new File(fileName);
			FileOutputStream stream = new FileOutputStream(file);
			p.store(stream, comment);
		} catch (Exception e) {
			// Log.error("Defaults", e);
		}
	}

	static public String getUserDirName() {
		createDir();
		return getUserHome() + File.separator + "Cosmos";
	}

	static public void createDir() {
		try {
			File file = new File(getUserHome() + File.separator + "Cosmos");
			if (file.exists())
				return;
			file.mkdir();
			// Copy everything from Calypso dir to Calypso/releaseNumber
			// Copy appstarter.* calypso.resources.*
			// calypso_app_starter.*
			// calypsouser.*
		} catch (Exception e) {
			// Log.error("Defaults", e);
		}
	}

	/**
	 * Returns date part as string from date Time string
	 * 
	 * @author yogesh
	 * 
	 * @String
	 * @param dateTime
	 * @return String
	 */
	static public String separteDateTime(String dateTime) {

		return dateTime.substring(0, 10);
	}

	/**
	 * Returns date as a string in a required format
	 * 
	 * @param String
	 * @return String date converted in a given format
	 */

	static public String convertStringDateInFormat(Date date, String format) {

		SimpleDateFormat formatter = null;

		formatter = new SimpleDateFormat(format);

		return formatter.format(date);

	}

	/**
	 * Returns date converted from a string in a required format no isLeneint
	 * use
	 * 
	 * @param Date
	 *            date
	 * @return String date converted in a given format
	 */

	static public java.util.Date stringToDate(String dateStr, String format) {
		if (dateStr.contains("-"))
			dateStr = dateStr.replace("-", "/");

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date returnDate = null;

		try {
			returnDate = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}

	/**
	 * Returns dateString converted from a string of dateTime in a required
	 * format no isLeneint use
	 * 
	 * @param String
	 *            dateTime
	 * @return String
	 */

	static public String stringDateTimeToDate(String dateTime, String format) {
		if (dateTime.contains("-"))
			dateTime = dateTime.replace("-", "/");

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date returnDate = null;
		try {
			returnDate = formatter.parse(dateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return convertStringDateInFormat(returnDate, format);

	}

	public static Object convertToFinanceFormate(double quantity, int dec) {
		// TODO Auto-generated method stub
		String value = getStringFromDoubleExp(quantity, dec);
		if (value.contains("-")) {
			value = value.replace("-", "(") + ")";
		}

		return value;
	}

	public static Object convertToFinanceFormate(double quantity) {
		// TODO Auto-generated method stub
		String value = getStringFromDoubleExp(quantity);
		if (value.contains("-")) {
			value = value.replace("-", "(") + ")";
		}

		return value;
	}

	static public double stringToNumber(String s) {
		return NumberFormatUtil.stringToNumber(s, Locale.getDefault());
	}

	static public String checkAmount(String s, int dig, boolean absoluteValue) {
		int idx = s.indexOf("k");
		if (idx == -1)
			idx = s.indexOf("K");
		if (idx > 0) {
			double m = commonUTIL.converStringToDouble(s.substring(0, idx));
			if (absoluteValue)
				m = Math.abs(m);

			return NumberFormatUtil.numberToString(m * 1000., dig);
		}
		idx = s.indexOf("l");
		if (idx == -1)
			idx = s.indexOf("L");
		if (idx > 0) {
			double m = commonUTIL.converStringToDouble(s.substring(0, idx));
			if (absoluteValue)
				m = Math.abs(m);

			return NumberFormatUtil.numberToString(m * 100000., dig);
		}
		idx = s.indexOf("m");
		if (idx == -1)
			idx = s.indexOf("M");
		if (idx > 0) {
			double m = commonUTIL.converStringToDouble(s.substring(0, idx));
			if (absoluteValue)
				m = Math.abs(m);
			return NumberFormatUtil.numberToString(m * 1000000., dig);
		}
		idx = s.indexOf("b");
		if (idx == -1)
			idx = s.indexOf("B");
		if (idx > 0) {
			double m = commonUTIL.converStringToDouble(s.substring(0, idx));
			if (absoluteValue)
				m = Math.abs(m);
			return NumberFormatUtil.numberToString(m * 1000000000., dig);
		}
		idx = s.indexOf("t");
		if (idx == -1)
			idx = s.indexOf("T");
		if (idx > 0) {
			double m = commonUTIL.converStringToDouble(s.substring(0, idx));
			if (absoluteValue)
				m = Math.abs(m);
			return NumberFormatUtil.numberToString(m * 1000000000000., dig);
		}
		double m = commonUTIL.converStringToDouble(s);
		if (absoluteValue)
			m = Math.abs(m);
		return NumberFormatUtil.numberToString(m, dig);
	}

	public static Object getMethodValueFromReflect(Object object,
			String methodName) {
		Method me = ReflectionUtil.getMethodGet(object, methodName);
		try {
			if (me != null)
				return me.invoke(object, null);
			return null;
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodError
				| NullPointerException e) {
			// TODO Auto-generated catch block
			commonUTIL
					.displayError("CommonUtil",
							"getMethodValueFromReflect try to invoke Reflection invoke on method "
									+ methodName + " of "
									+ object.getClass().getName(), e);
			return null;
		}

	}

	/**
	 * Returns true if a String contains YES, TRUE, Y, or T. Returns
	 * valueIfMissing otherwise.
	 * 
	 * @param s
	 *            a String
	 */
	static public boolean isTrue(String s, boolean valueIfMissing) {
		if (isEmpty(s))
			return valueIfMissing;

		String val = s.toUpperCase();
		return val.equals("YES") || val.equals("Y") || val.equals("TRUE")
				|| val.equals("T");
	}

	/**
	 * Returns true if a String contains YES, TRUE, Y, or T. Returns false
	 * otherwise.
	 * 
	 * @param s
	 *            a String
	 */
	static public boolean isTrue(String s) {
		return isTrue(s, false);
	}

	public static String mapToString(Map<?, ?> m) {
		return mapToString(m, "=", ",");
	}

	/**
	 * Converts a Map to a string-based list of Key/Value Pairs where separator
	 * between key and value(usually "=") is specified, and delimiter between
	 * key/value pairs(usually ",") is specified. If betweenKeyAndValueString=
	 * "=" and keyValueDelimiter ="," and map contains 3 key/value pairs, it
	 * will produce as string such as "Key1=Value1,Key2=Value2,Key3=Value3"
	 * 
	 * @param m
	 *            map
	 * @param betweenKeyAndValueString
	 *            separator between key and value
	 * @param keyValueDelimiter
	 *            delimiter between key/value pairs
	 * @return
	 */
	public static String mapToString(Map<?, ?> m,
			String betweenKeyAndValueString, String keyValueDelimiter) {
		StringBuilder s = new StringBuilder();
		if (m != null) {
			Iterator<?> iter = m.entrySet().iterator();
			if (iter.hasNext()) {
				Map.Entry<?, ?> entry = (Map.Entry<?, ?>) iter.next();
				s.append(entry.getKey());
				s.append(betweenKeyAndValueString);
				s.append(entry.getValue());
			}
			while (iter.hasNext()) {
				Map.Entry<?, ?> entry = (Map.Entry<?, ?>) iter.next();
				s.append(keyValueDelimiter);
				s.append(entry.getKey());
				s.append(betweenKeyAndValueString);
				s.append(entry.getValue());
			}
		}
		return s.toString();
	}

	public static Map<String, String> stringToMap(String s) {
		return stringToMap(s, "=", ",");
	}

	/**
	 * Converts a String of Key/Value Pairs to a string-based Map where
	 * separator between key and value(usually "=") is specified, and delimiter
	 * between key/value pairs(usually ",") is specified. If the input string is
	 * "Key1=Value1,Key2=Value2,Key3=Value3" and betweenKeyAndValueString is "="
	 * and keyValueDelimiter is ",", it will produce a map containing the 3
	 * string pairs.
	 * 
	 * @param s
	 *            a string
	 * @param betweenKeyAndValueString
	 *            separator between key and value
	 * @param keyValueDelimiter
	 *            delimiter between key/value pairs
	 * @return the resulting map
	 */
	public static Map<String, String> stringToMap(String s,
			String betweenKeyAndValueString, String keyValueDelimiter) {
		Map<String, String> map = null;
		if (!isEmpty(s)) {
			String[] v = s.split(keyValueDelimiter);
			if (v.length > 0) {
				map = new HashMap<String, String>(v.length);
				for (int i = 0; i < v.length; i++) {
					String[] w = v[i].split(betweenKeyAndValueString, 2);
					map.put(w[0].trim(), w[1].trim());
				}
			}
		}
		return map;
	}

	/**
	 * Use the DEFAULT_LIST_SEPARATOR_CHAR
	 * 
	 * @param c
	 * @return
	 * @see #DEFAULT_LIST_SEPARATOR_CHAR
	 * @see #collectionToString(java.util.Collection,String)
	 */
	public static String collectionToString(Collection<?> c) {
		return collectionToString(c, DEFAULT_LIST_SEPARATOR_CHAR);
	}

	/**
	 * Converts a given collection into a string, where each item in the is
	 * separated by <code>separator</code>.
	 * <p>
	 * Calls <code>toString()</code> on all objects contained in the collection,
	 * and separates consecutive elements with a separator. If an element is
	 * null, then it is not included in the string.
	 * 
	 * @param c
	 *            collection to convert
	 * @param separator
	 *            a String to separate the elements
	 * @return String representation of the collection.
	 */
	public static String collectionToString(Collection<?> c, String separator) {
		if (isEmpty(c))
			return "";
		else {
			StringBuilder s = new StringBuilder(c.size() * 20);
			Iterator<?> iterator = c.iterator();
			while (iterator.hasNext()) {
				Object item = iterator.next();
				if (item != null) {
					s.append(item.toString());
					if (iterator.hasNext()) {
						s.append(separator);
					}
				}
			}
			return s.toString();
		}
	}

	public static void convertStringToCollection(String s, List<String> c) {
		if (isEmpty(s))
			return;

		else {
			StringTokenizer token = new StringTokenizer(s,
					DEFAULT_LIST_SEPARATOR_CHAR);
			if (token.countTokens() == 0)
				c.add(s);
			while (token.hasMoreTokens()) {
				String item = (String) token.nextElement();
				c.add(item);
			}
		}
	}

	/**
	 * Returns true if a String contains YES, TRUE, Y, or T. Returns false
	 * otherwise.
	 * 
	 * @param s
	 *            a String
	 */
	static public boolean isTrue(Boolean s, boolean valueIfNull) {
		if (s == null)
			return valueIfNull;
		return s;
	}

	/**
	 * Converts a given Date into a String, and returns the String.
	 * <p>
	 * The DateFormat is SHORT for Date, and the DateFormat is LONG for Time.
	 * 
	 * @param date
	 *            a Date to convert
	 * @param timeZone
	 *            a TimeZone used for the conversion
	 */
	public static String timestampToString2(Date date, TimeZone timeZone) {
		if (date == null)
			return "";
		DateFormat dateformat = getDatetimeFormatFull(Locale.getDefault(),
				timeZone);
		String s;
		synchronized (dateformat) {
			s = dateformat.format(date);
		}
		// Now replace the characters which are non-
		// standard to calypso
		if (s != null && s.indexOf(".") > 0 && s.indexOf(".") < 10) {
			s = s.replace('.', '/');
		}
		if (s != null && s.indexOf("-") > 0 && s.indexOf("-") < 10) {
			s = s.replace('-', '/');
		}
		return s;
	}

	/**
	 * Converts a given Date into a String, and returns the String.
	 * <p>
	 * The DateFormat is SHORT for Date, and the DateFormat is LONG for Time.
	 * 
	 * @param date
	 *            a Date to convert
	 */
	public static String timestampToString2(Date date) {
		return timestampToString2(date, TimeZone.getDefault());
	}

	/**
	 * Converts a given CDateTime into a String, and returns the String.
	 * <p>
	 * The DateFormat is SHORT for Date, and the DateFormat is LONG for Time.
	 * 
	 * @param date
	 *            a CDateTime to convert
	 */
	public static String datetimeToString2(CDateTime date) {
		return timestampToString2(date);
	}

	/**
	 * Converts a given String into a CDateTime, and returns the CDateTime.
	 * <p>
	 * The DateFormat is SHORT for Date, and the DateFormat is LONG for Time.
	 * 
	 * @param s
	 *            a String to convert
	 */
	public static CDateTime stringToCDateTime2(String s) {
		Date date = stringToTimestamp2(s);
		if (date != null)
			return new CDateTime(date);
		else
			return null;
	}

	/**
	 * Converts a given String into a Date, and returns the Date.
	 * <p>
	 * The DateFormat is SHORT for Date, and the DateFormat is LONG for Time.
	 * 
	 * @param s
	 *            a String to convert
	 */
	public static Date stringToTimestamp2(String s) {
		try {
			if ((s == null) || (s.length() == 0))
				return null;
			DateFormat dateformat = DateFormat.getDateTimeInstance(
					DateFormat.SHORT, DateFormat.FULL, Locale.getDefault());
			dateformat.setTimeZone(TimeZone.getDefault());
			try {
				SimpleDateFormat sd = (SimpleDateFormat) dateformat;
				String pattern = sd.toPattern();
				int idx = pattern.indexOf("ss");
				if (idx >= 0)
					sd.applyLocalizedPattern(pattern.substring(0, idx + 2)
							+ ".SSS" + pattern.substring(idx + 2));
			} catch (Exception ex) {
			}
			Date date = null;
			try {
				date = dateformat.parse(s);
			} catch (Exception e) {
				displayError("CommonUtil", "stringToTimestamp2", e);
			}
			return date;

		} catch (Throwable x) {
			displayError("CommonUtil", "stringToTimestamp2", x);
		}
		return null;
	}

	/**
	 * Returns the String representation of a Date using the default locale.
	 * <p>
	 * The DateFormat is LONG.
	 * 
	 * @param date
	 *            a Date to convert
	 */
	public static String dateToLongString(Date date) {
		return dateToLongString(date, Locale.getDefault());
	}

	static DateFormat getDatetimeFormatFull(Locale loc, TimeZone tz) {
		Object key = new util.common.LocalTimeZone(loc, tz);
		synchronized (_datetimeFormatFull) {
			Object o = _datetimeFormatFull.get(key);
			if (o != null)
				return (DateFormat) o;
		}
		DateFormat dateformat = DateFormat.getDateTimeInstance(
				DateFormat.SHORT, DateFormat.FULL, loc);
		try {
			SimpleDateFormat sd = (SimpleDateFormat) dateformat;
			set2DigitYearStart(sd, tz);
			String pattern = sd.toPattern();
			int idx = pattern.indexOf("ss");
			if (idx >= 0)
				sd.applyLocalizedPattern(pattern.substring(0, idx + 2) + ".SSS"
						+ pattern.substring(idx + 2));
			// sd=sd.replace("ss","ss.SSS");
		} catch (Exception ex) {
		}
		dateformat.setTimeZone(tz);
		synchronized (_datetimeFormatFull) {
			_datetimeFormatFull.put(key, dateformat);
		}
		return dateformat;
	}

	private static void set2DigitYearStart(SimpleDateFormat sm, TimeZone tz) {
		String s = "99";// Defaults.get2DigitYearStart();
		int start = 50;
		if (!isEmpty(s)) {
			try {
				start = Integer.parseInt(s);
			} catch (Exception e) {
			}
		}
		sm.set2DigitYearStart((util.common.CDate.getNow().addYears(-start))
				.getDate(tz));
	}

	/**
	 * Returns the Date represented by a given String using a given Locale.
	 * <p>
	 * If the String is null or empty then a null is returned for the date. When
	 * the date is being converted, it is assumed to be in the short date
	 * format.
	 * <p>
	 * Note that this is <em><b>not</b></em> the inverse of
	 * <code>dateToString(CDate, ...)</code>. If your String was created with
	 * <code>dateToString(CDate, ...)</code> it must be parsed with
	 * <code>stringToCDate(...)</code>.
	 * 
	 * @param dateText
	 *            a String to decode
	 * @param locale
	 *            a Locale
	 */
	public static Date stringToDate(String dateText, Locale locale) {
		if ((dateText == null) || (dateText.length() == 0))
			return null;
		if (locale == null)
			locale = Locale.getDefault();
		DateFormat dateformat = getDateFormat(locale, TimeZone.getDefault());

		synchronized (dateformat) {
			Date date = null;
			try {
				date = dateformat.parse(dateText);
			} catch (Exception e) {
				displayError("CommonUtil", "stringToDate", e);
			}
			return date;
		}
	}

	/**
	 * Returns the Date represented by a String using the default locale.
	 * <p>
	 * If the string is null or empty then a null is returned for the date. When
	 * the date is being converted, it is assumed to be in the short date format
	 * <p>
	 * Note that this is <em><b>not</b></em> the inverse of
	 * <code>dateToString(CDate, ...)</code>. If your String was created with
	 * <code>dateToString(CDate, ...)</code> it must be parsed with
	 * <code>stringToCDate(...)</code>.
	 * 
	 * @param dateText
	 *            a String to decode
	 * @return a Date
	 */
	public static Date stringToDate(String dateText) {
		return stringToDate(dateText, Locale.getDefault());
	}

	/**
	 * Returns the Date represented by a String formatted as returned by
	 * Date.toString().
	 * 
	 * @param dateText
	 *            a String to decode
	 * @return a Date
	 */
	public static Date stringToDate2(String dateText) {
		try {
			return (new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"))
					.parse(dateText);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	static DateFormat getDatetimeFormatMedium(Locale loc, TimeZone tz) {
		Object key = new util.common.LocalTimeZone(loc, tz);
		synchronized (_datetimeFormatMedium) {
			Object o = _datetimeFormatMedium.get(key);
			if (o != null)
				return (DateFormat) o;
		}
		DateFormat dateformat = DateFormat.getDateTimeInstance(
				DateFormat.SHORT, DateFormat.MEDIUM, loc);
		dateformat.setTimeZone(tz);
		SimpleDateFormat sd = (SimpleDateFormat) dateformat;
		set2DigitYearStart(sd, tz);
		synchronized (_datetimeFormatMedium) {
			_datetimeFormatMedium.put(key, dateformat);
		}
		return dateformat;
	}

	static DateFormat getDatetimeFormatMediumShort(Locale loc, TimeZone tz) {
		Object key = new util.common.LocalTimeZone(loc, tz);
		synchronized (_datetimeFormatMediumShort) {
			Object o = _datetimeFormatMediumShort.get(key);
			if (o != null)
				return (DateFormat) o;
		}
		DateFormat dateformat = DateFormat.getDateTimeInstance(
				DateFormat.MEDIUM, DateFormat.SHORT, loc);

		// Bug 21024
		if (loc.equals(Locale.JAPAN) || loc.equals(Locale.JAPANESE))
			((SimpleDateFormat) dateformat).applyPattern("yyyy/MM/dd HH:mm");
		else
			((SimpleDateFormat) dateformat)
					.applyPattern("MMM dd, yyyy hh:mm a");
		dateformat.setTimeZone(tz);
		SimpleDateFormat sd = (SimpleDateFormat) dateformat;
		set2DigitYearStart(sd, tz);
		synchronized (_datetimeFormatMediumShort) {
			_datetimeFormatMediumShort.put(key, dateformat);
		}
		return dateformat;
	}

	static DateFormat getDatetimeFormatShortMedium(Locale loc, TimeZone tz) {
		Object key = new util.common.LocalTimeZone(loc, tz);
		synchronized (_datetimeFormatShortMedium) {
			Object o = _datetimeFormatShortMedium.get(key);
			if (o != null)
				return (DateFormat) o;
		}
		DateFormat dateformat = DateFormat.getDateTimeInstance(
				DateFormat.SHORT, DateFormat.MEDIUM, loc);

		// Bug 21024
		if (loc.equals(Locale.JAPAN) || loc.equals(Locale.JAPANESE))
			((SimpleDateFormat) dateformat).applyPattern("yyyy/MM/dd HH:mm:ss");
		else
			((SimpleDateFormat) dateformat)
					.applyPattern("MM/dd/yyyy hh:mm:ss a");
		dateformat.setTimeZone(tz);
		SimpleDateFormat sd = (SimpleDateFormat) dateformat;
		synchronized (_datetimeFormatShortMedium) {
			_datetimeFormatShortMedium.put(key, dateformat);
		}
		return dateformat;
	}

	/**
	 * Converts a given String into a JDatetime, and returns the JDatetime.
	 * <p>
	 * The DateFormat is SHORT for Date, and the DateFormat is LONG for Time.
	 * 
	 * @param s
	 *            a String to convert
	 */
	public static CDateTime stringToJDatetime2(String s) {
		Date date = stringToTimestamp2(s);
		if (date != null)
			return new CDateTime(date);
		else
			return null;
	}

	static DateFormat get2DigDateFormat(Locale loc, TimeZone tz) {
		Object key = new util.common.LocalTimeZone(loc, tz);
		synchronized (_twoDigDateFormat) {
			Object o = _twoDigDateFormat.get(key);
			if (o != null)
				return (DateFormat) o;
		}
		DateFormat dateformat = DateFormat.getDateInstance(DateFormat.MEDIUM,
				loc);
		// Bug 21024
		if (!loc.equals(Locale.JAPAN) && !loc.equals(Locale.JAPANESE))
			((SimpleDateFormat) dateformat).applyPattern("MMM dd,yyyy");
		dateformat.setTimeZone(tz);
		synchronized (_twoDigDateFormat) {
			_twoDigDateFormat.put(key, dateformat);
		}
		return dateformat;
	}

	/**
	 * @return default SimpleDateFormat, for Locale.getDefault(),
	 *         TimeZone.getDefault(). DateFormat is cloned to avoid possible
	 *         mutation of the Util locally cached DateFormat. <br>
	 *         This DateFormat(DateFormat.MEDIUM, DateFormat.SHORT) is the one
	 *         used by com.calypso.tk.core.DisplayDatetime
	 * @see com.calypso.tk.core.DisplayDatetime
	 */
	public static SimpleDateFormat getDatetimeFormatMediumShort() {
		return (SimpleDateFormat) getDatetimeFormatMediumShort(
				Locale.getDefault(), TimeZone.getDefault());
	}

	/**
	 * Default SimpleDateFormat, for Locale.getDefault(), TimeZone.getDefault().
	 * DateFormat is cloned to avoid possible mutation of the Util locally
	 * cached DateFormat. <br>
	 * Equivalent to DateFormat(DateFormat.SHORT, DateFormat.MEDIUM) but with
	 * four digit years
	 * 
	 * @return a SimpleDateFormat object
	 */
	public static SimpleDateFormat getDatetimeFormatShortMedium() {
		return (SimpleDateFormat) getDatetimeFormatShortMedium(
				Locale.getDefault(), TimeZone.getDefault());
	}

	static DateFormat getDateFormat(Locale loc, TimeZone tz) {
		Object key = new util.common.LocalTimeZone(loc, tz);
		synchronized (_dateFormat) {
			Object o = _dateFormat.get(key);
			if (o != null)
				return (DateFormat) o;
		}
		DateFormat dateformat = DateFormat.getDateInstance(DateFormat.SHORT,
				loc);
		dateformat.setTimeZone(tz);
		SimpleDateFormat sd = (SimpleDateFormat) dateformat;
		set2DigitYearStart(sd, tz);
		synchronized (_dateFormat) {
			_dateFormat.put(key, dateformat);
		}
		return dateformat;

	}

	/**
	 * This method is used only to for dateToString(params) method where you
	 * expect 2 digit month,day and 4 digit year in the date's string
	 * representation.
	 */
	public static DateFormat getDateFormatWithAppliedPattern(Locale loc,
			TimeZone tz) {
		Object key = new util.common.LocalTimeZone(loc, tz);
		synchronized (_dateFormatPattern) {
			Object o = _dateFormatPattern.get(key);
			if (o != null)
				return (DateFormat) o;
		}
		DateFormat dateformat = DateFormat.getDateInstance(DateFormat.SHORT,
				loc);
		dateformat.setTimeZone(tz);
		SimpleDateFormat sm = (SimpleDateFormat) dateformat;
		set2DigitYearStart(sm, tz);
		String pattern = checkPattern(sm.toPattern(), false);
		sm.applyPattern(pattern);
		pattern = checkSeparatorPattern(sm.toPattern());
		sm.applyPattern(pattern);
		synchronized (_dateFormatPattern) {
			_dateFormatPattern.put(key, dateformat);
		}
		return dateformat;
	}

	/*
	 * Converts a given String to a standard date pattern, and returns the
	 * String. <p> The standard date pattern is as follows: <ul type=square>
	 * <li>All days are made 2 digit days</li> <li>All months are made 2 digit
	 * months</li> <li>All years are made 4 digit years, or 2 digit years based
	 * on the twoDigYear boolean</li> <li>Separators are '/'</li> </ul>
	 * 
	 * @param s a String to convert
	 * 
	 * @param twoDigYear if true, all years are made 2 digit years, or 4 digit
	 * otherwise
	 */
	static String checkPattern(String s, boolean twoDigYear) {
		// We need to cache this otherwise its very expensive
		// Needs regression testing..

		// Make month 2 digit
		if (s.indexOf("MM") < 0) {
			if (s.indexOf("M") >= 0) {
				int idx1 = s.indexOf("M");
				StringBuilder sb = new StringBuilder(s);
				sb = sb.deleteCharAt(idx1);
				sb = sb.insert(idx1, "MM");
				s = sb.toString();
			}
		}
		// Make day 2 digit
		if (s.indexOf("dd") < 0) {
			if (s.indexOf("d") >= 0) {
				int idx1 = s.indexOf("d");
				StringBuilder sb = new StringBuilder(s);
				sb = sb.deleteCharAt(idx1);
				sb = sb.insert(idx1, "dd");
				s = sb.toString();
			}
		}
		// Make year 4 digit
		if (!twoDigYear) {
			if (s.indexOf("yyyy") < 0) {
				if (s.indexOf("yy") >= 0) {
					int idx1 = s.indexOf("yy");
					StringBuilder sb = new StringBuilder(s);
					sb = sb.deleteCharAt(idx1);
					sb = sb.deleteCharAt(idx1);
					sb = sb.insert(idx1, "yyyy");
					s = sb.toString();
				}
			}
		} else {
			if (s.indexOf("yyyy") > 0) {
				int idx1 = s.indexOf("yy");
				StringBuilder sb = new StringBuilder(s);
				sb = sb.deleteCharAt(idx1);
				sb = sb.deleteCharAt(idx1);
				s = sb.toString();
			}
		}
		String finalS = s;
		// Replace the separator with '/' if its '.'
		if (finalS.indexOf('.') >= 0) {
			finalS = finalS.replace('.', '/');
		}
		// Replace the separator with '/' if its '-'
		if (finalS.indexOf('-') >= 0) {
			finalS = finalS.replace('-', '/');
		}
		return finalS;
	}

	/**
	 * Replaces all the Locale specific separators with fixed "/"
	 */
	static String checkSeparatorPattern(String s) {
		String finalS = s;
		// Replace the separator with '/' if its '.'
		if (finalS.indexOf('.') >= 0) {
			finalS = finalS.replace('.', '/');
		}
		// Replace the separator with '/' if its '-'
		if (finalS.indexOf('-') >= 0) {
			finalS = finalS.replace('-', '/');
		}
		return finalS;
	}

	/**
	 * This method is used only to for stringToDate(params) method The purpose
	 * of this method is when parsing the date from string We cannot expect 4
	 * digit year and 2 digit month but the separator remains same "/"
	 */
	public static DateFormat getDateFormatWithSeparatorPattern(Locale loc,
			TimeZone tz) {
		Object key = new util.common.LocalTimeZone(loc, tz);
		synchronized (_dateFormatSepPattern) {
			Object o = _dateFormatSepPattern.get(key);
			if (o != null)
				return (DateFormat) o;
		}
		DateFormat dateformat = DateFormat.getDateInstance(DateFormat.SHORT,
				loc);
		dateformat.setTimeZone(tz);
		SimpleDateFormat sm = (SimpleDateFormat) dateformat;
		set2DigitYearStart(sm, tz);
		String pattern = checkSeparatorPattern(sm.toPattern());
		sm.applyPattern(pattern);
		synchronized (_dateFormatSepPattern) {
			_dateFormatSepPattern.put(key, dateformat);
		}
		return dateformat;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Custom pattern
	 */

	public static DateFormat getDateFormatCustom(String pattern, Locale loc,
			TimeZone tz) {
		Object key = pattern + loc.toString() + tz.getID();
		synchronized (_dateFormatCustom) {
			Object o = _dateFormatCustom.get(key);
			if (o != null)
				return (DateFormat) o;
		}
		DateFormat sm;
		try {
			sm = new SimpleDateFormat(pattern, loc);
			sm.setTimeZone(tz);
		} catch (Throwable x) {
			displayError("CommonUtil", "Util", x);
			sm = getDateFormat(loc, tz);
		}
		synchronized (_dateFormatCustom) {
			_dateFormatCustom.put(key, sm);
		}
		return sm;
	}

	static DateFormat getDatetimeFormatCustom(String pattern, Locale loc,
			TimeZone tz) {
		Object key = pattern + loc.toString() + tz.getID();
		synchronized (_datetimeFormatCustom) {
			Object o = _datetimeFormatCustom.get(key);
			if (o != null)
				return (DateFormat) o;
		}
		DateFormat sm = null;
		try {
			sm = new SimpleDateFormat(pattern, loc);
			sm.setTimeZone(tz);
		} catch (Throwable x) {
			displayError("CommonUtil", "Util", x);
			sm = getDatetimeFormatMedium(loc, tz);
		}
		synchronized (_datetimeFormatCustom) {
			_datetimeFormatCustom.put(key, sm);
		}
		return sm;
	}

	public static String dateToString(CDate CDate, String customPattern) {
		/* LOCALE USE */
		if (CDate == null)
			return "";
		String s = "";
		DateFormat dateformat = getDateFormatCustom(customPattern,
				Locale.getDefault(), TimeZone.getDefault());
		Date date = CDate.getDate(TimeZone.getDefault());
		synchronized (dateformat) {
			s = dateformat.format(date);
		}
		return s;
	}
	 
    public static String convertHashTableKeyValuesToString(Hashtable<String,String> values) {
    	String hashstring= "";
        for (Map.Entry<String, String> entry : values.entrySet()) {
            hashstring += entry.getKey() + "=" + entry.getValue() + "|";
        }
		return hashstring;
    }
	public static String datetimeToString(CDateTime date, String customPattern) {
		return datetimeToString(date, customPattern, TimeZone.getDefault());
	}

	public static String datetimeToString(CDateTime date, String customPattern,
			TimeZone tz) {
		if (date == null)
			return "";
		String s = "";
		DateFormat dateformat = getDateFormatCustom(customPattern,
				Locale.getDefault(), tz);
		synchronized (dateformat) {
			s = dateformat.format(date);
		}
		return s;
	}

	public static String dateArrayToString(util.common.CDate[] arr) {
		if (arr == null || arr.length == 0)
			return "";

		String s = "[";
		for (int i = 0; i < arr.length; i++) {
			if (i != 0)
				s += ",";
			s += arr[i].toString();
		}
		s += "]";
		return s;
	}

	/**
	 * Returns the String representation of a given CDate using the short date
	 * format of a given Locale.
	 * <p>
	 * Note that this differs from {@link #dateToString(CDate)} in that there is
	 * no changing of the format used. The date format from the supplied locale
	 * will be used as is.
	 * 
	 * @param CDate
	 *            a CDate to convert
	 * @param locale
	 *            a Locale
	 */
	public static String dateToString(CDate CDate, Locale locale) {
		if (CDate == null)
			return "";
		return dateToString(CDate.getDate(TimeZone.getDefault()), locale);
	}

	/**
	 * Returns the String representation of a given Date in a given Locale.
	 * <p>
	 * The date is formatted using the short format. If the locale is null then
	 * the default locale is used. If the date is null then an empty string is
	 * returned.
	 * 
	 * @param date
	 *            a Date to convert
	 * @param locale
	 *            a Locale
	 */
	public static String dateToString(Date date, Locale locale) {
		if (date == null)
			return "";
		if (locale == null)
			locale = Locale.getDefault();
		DateFormat dateformat = getDateFormat(locale, TimeZone.getDefault());
		synchronized (dateformat) {
			return dateformat.format(date);
		}
	}

	/**
	 * Returns the String representation of a Date using the default locale.
	 * <p>
	 * The date is formatted using the short format. If the date is null then an
	 * empty string is returned.
	 * 
	 * @param date
	 *            a Date to convert
	 */
	public static String dateToString(Date date) {
		return dateToString(date, Locale.getDefault());
	}

	/**
	 * Returns the String representation (like MM/DD/YY or DD/MM/YY) of a given
	 * CDate using the default Locale.
	 * 
	 * @param CDate
	 *            a CDate to convert
	 */

	public static String dateToString(CDate CDate) {
		/* LOCALE USE */
		if (CDate == null)
			return "";
		String s;
		DateFormat dateformat = getDateFormatWithAppliedPattern(
				Locale.getDefault(), TimeZone.getDefault());
		Date date = CDate.getDate(TimeZone.getDefault());
		synchronized (dateformat) {
			s = dateformat.format(date);
		}
		return s;
	}

	/**
	 * Converts a given String into a CDate, and returns the CDate.
	 * 
	 * @param s
	 *            a String to decode
	 */
	public static CDate stringToCDatePlus(String s) {
		try {
			/* LOCALE USE */
			return stringToCDate(s);
		} catch (Throwable x) {
			display("CommonUtil stringToCDatePlus", s);
		}
		return null;
	}

	public static CDate stringToCDate(String s) {
		return stringToCDate(s, true);
	}

	public static CDate stringToCDate(String s, boolean isLenient) {
		return stringToCDate(s, isLenient, true);
	}

	public static CDate stringToCDate(String s, boolean isLenient,
			boolean logError) {

		try {
			if (isEmpty(s))
				return null;
			DateFormat dateformat = getDateFormatWithSeparatorPattern(
					Locale.getDefault(), TimeZone.getDefault());
			dateformat.setLenient(isLenient);
			Calendar cal;
			Date date;
			synchronized (dateformat) {
				try {
					date = dateformat.parse(s);
				} catch (Exception e) {
					date = null;
				}
				if (date == null)
					return null;
				cal = dateformat.getCalendar();
				cal.setTime(date);
				int y = cal.get(Calendar.YEAR);
				if (y > MAX_YEAR_ALLOWED_IN_DATES) {
					// shunt for abnormally large years since they are not
					// allowed
					// on sybase and oracle, BZ 22883
					return null;
				}
				if (y < 50) { // This is NEW and should be checked !!!
					y += 2000;
					cal.set(Calendar.YEAR, y);
				}
				if (y < 100 && y >= 50) {
					y += 1900;
					cal.set(Calendar.YEAR, y);
				}
				date = cal.getTime();
			}
			return CDate.valueOf(date, TimeZone.getDefault());
		} catch (Throwable x) {
			if (logError)
				display("CommonUTil", "stringToCDate");
		}
		return null;
	}

	/**
	 * Converts a given set of String object into a CDateTime, and returns the
	 * CDateTime.
	 * 
	 * @param ds
	 *            a date (String)
	 * @param ts
	 *            a time (String)
	 */
	public static CDateTime stringToCDateTime(String ds, String ts) {
		return stringToCDateTime(ds, ts, DateFormat.MEDIUM);
	}

	/**
	 * Converts a given set of String objects into a CDateTime, and returns the
	 * CDateTime.
	 * 
	 * @param ds
	 *            a date (String)
	 * @param ts
	 *            a time (String)
	 * @param df
	 *            a DateFormat (SHORT, MEDIUM, LONG, or FULL)
	 */
	public static CDateTime stringToCDateTime(String ds, String ts, int df) {
		return stringToCDateTime(ds, ts, df, TimeZone.getDefault());
	}

	public static CDateTime stringToCDateTime(String ds, String ts, int df,
			TimeZone tz) {
		if ((ds == null) || (ds.length() == 0)) {
			return null;
		}
		CDate CDate = stringToCDate(ds);
		return stringToCDateTime(CDate, ts, df, tz);
	}

	/**
	 * Converts a given String into a Date, and returns the Date.
	 * <p>
	 * The DateFormat is SHORT for Date, and the DateFormat is MEDIUM for Time.
	 * 
	 * @param s
	 *            a String to convert
	 */
	public static Date stringToTimestamp(String s) {
		if ((s == null) || (s.length() == 0))
			return null;
		DateFormat dateformat = DateFormat.getDateTimeInstance(
				DateFormat.SHORT, DateFormat.MEDIUM, Locale.getDefault());
		dateformat.setTimeZone(TimeZone.getDefault());
		Date date = null;
		try {
			date = dateformat.parse(s);
		} catch (Exception e) {

			displayError(
					"CommonUtil ",
					"stringToTimestamp  Error parsing string to CDateTime (\"MM/dd/yy h:mm a\")",
					e);
		}
		return date;
	}

	public static CDateTime stringToCDateTime(CDate CDate, String ts, int df,
			TimeZone tz) {
		Date time = stringToTime(ts, df, tz);

		if (CDate == null || time == null) {
			return null;
		}
		Calendar cal = new GregorianCalendar();
		cal.setTimeZone(tz);
		cal.setTime(time);
		cal.set(Calendar.YEAR, CDate.getYear());
		cal.set(Calendar.MONTH, CDate.getMonth() - 1);
		cal.set(Calendar.DATE, CDate.getDayOfMonth());
		return new CDateTime(cal.getTime());
	}

	/**
	 * Converts a given String into a Date using a given DateFormat, and returns
	 * the Date.
	 * 
	 * @param s
	 *            a String to convert
	 * @param df
	 *            a DateFormat (SHORT, MEDIUM, LONG, or FULL)
	 */
	public static Date stringToTime(String s, int df) {
		return stringToTime(s, df, TimeZone.getDefault());
	}

	public static Date stringToTime(String s, int df, TimeZone tz) {
		return stringToTime(s, df, tz, Locale.getDefault());
	}

	public static Date stringToTime(String s, int df, TimeZone tz, Locale loc) {
		if ((s == null) || (s.length() == 0))
			return null;
		DateFormat dateformat = DateFormat.getTimeInstance(df, loc);
		dateformat.setTimeZone(tz);
		Date date = null;
		try {
			date = dateformat.parse(s);
		} catch (Exception e) {

			displayError("CommonUtil ", "stringToTime  ", e);
		}
		return date;
	}

	/**
	 * Converts a given Date into a String, and returns the String.
	 * <p>
	 * The DateFormat is SHORT for Date.
	 * 
	 * @param date
	 *            a Date to convert
	 */
	public static String timeToShortString(Date date) {
		if (date == null)
			return "";
		DateFormat dateformat = DateFormat.getTimeInstance(DateFormat.SHORT,
				Locale.getDefault());
		dateformat.setTimeZone(TimeZone.getDefault());
		return dateformat.format(date);
	}

	public static boolean isNumber(String string) {
		return string.matches("^\\d+$");
	}

	/**
	 * Converts a given String into a Date, and returns the Date.
	 * <p>
	 * The DateFormat is SHORT for Date.
	 * 
	 * @param s
	 *            a String to decode
	 */
	public static Date shortStringToTime(String s) {
		if ((s == null) || (s.length() == 0))
			return null;
		DateFormat dateformat = DateFormat.getTimeInstance(DateFormat.SHORT,
				Locale.getDefault());
		dateformat.setTimeZone(TimeZone.getDefault());
		Date date = null;
		try {
			date = dateformat.parse(s);
		} catch (Exception e) {
			displayError("CommonUtil ", "shortStringToTime  ", e);
		}
		return date;
	}

	/**
	 * Parses a string into a CDateTime object. Expects a string matching
	 * "MM/dd/yy h:mm a" (i.e. "1/12/11 8:45 PM" is 12 January 2011 at 8:45 in
	 * the evening).
	 * 
	 * @param datetime
	 * @return a CDateTime object that representing the date and time from the
	 *         string
	 */
	public static CDateTime stringToDateWithTime(String datetime) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy h:mm a");
		try {
			return new CDateTime(format.parse(datetime));
		} catch (ParseException e) {
			displayError(
					"CommonUtil ",
					"stringToDateWithTime  Error parsing string to CDateTime (\"MM/dd/yy h:mm a\")",
					e);
			return null;
		}
	}

	/**
	 * Converts a given Date into a String using a given TimeZone, and returns
	 * the String.
	 * 
	 * @param date
	 *            a Date to convert
	 * @param df
	 *            a DateFormat (SHORT, MEDIUM, LONG, or FULL)
	 * @param tz
	 *            a TimeZone
	 * @param loc
	 *            Locale
	 * @return
	 */
	public static String timeToString(Date date, int df, TimeZone tz, Locale loc) {
		if (date == null)
			return "";
		DateFormat dateformat = DateFormat.getTimeInstance(df, loc);
		dateformat.setTimeZone(tz);
		return dateformat.format(date);
	}

	/**
	 * Converts a given String into a Date, and returns the Date.
	 * <p>
	 * The DateFormat is MEDIUM for Date.
	 * 
	 * @param s
	 *            a String to convert
	 */
	public static Date stringToTime(String s) {
		return stringToTime(s, DateFormat.MEDIUM);
	}

	/**
	 * Attempt to convert a User input String into a Date
	 * <p>
	 * User input may not exactly match a pre-defined DateFormat style.
	 * Conversion here attempts interpretation of
	 * <li> {@link DateFormat#MEDIUM}
	 * <li> {@link DateFormat#SHORT}
	 * <li>digit input: 2233 or 1033
	 * 
	 * @param s
	 *            a String to convert
	 */
	// from AppUtil
	public static CDateTime stringInputToTime(String s) {
		if (isEmpty(s))
			return null;

		s = s.toUpperCase().trim();
		Locale locale = Locale.getDefault();
		if (s.matches(".*[A|P]M")) {
			String pattern = ((SimpleDateFormat) DateFormat.getTimeInstance(
					DateFormat.SHORT, locale)).toPattern();
			if (pattern.toUpperCase().startsWith("H")) {
				// overriding default Locale in case of US-like formatted input
				// time in non US default locale
				locale = Locale.US;
			}
			// BZ 54287
			if (s.indexOf(':') == -1) {
				s = s.replaceAll("[ \t]*AM", ":00 AM").replaceAll("[ \t]*PM",
						":00 PM");
			}
			s = s.replaceAll("PM", " PM").replaceAll("AM", " AM")
					.replaceAll("[ \t]+", " ");
		}

		// try MEDIUM 12:20:34 AM or 22:33:44 depending on Locale
		Date date = stringToTime(s, DateFormat.MEDIUM, TimeZone.getDefault(),
				locale);

		// try SHORT 10:33 PM or 22:33 depending on Locale
		if (date == null)
			date = stringToTime(s, DateFormat.SHORT, TimeZone.getDefault(),
					locale);

		// try number
		if (date == null && isNumber(s) && s.length() < 7)
			date = integerToTime((int) stringToNumber(s), locale);

		return date == null ? null : new CDateTime(date);
	}

	// 22 -> [10:00:00 pm|22:00:00]
	// 223 -> [10:03:00 pm|22:03:00]
	// 2233 -> [10:33:00 pm|22:33:00]
	// 22334 -> [10:33:04 pm|22:33:04]
	// 223344 -> [10:33:44 pm|22:33:44]
	// 1 -> [01:00:00 am|01:00:00]
	// 103 -> [10:03:00 am|10:03:00]
	// 103344 -> [10:33:44 am|10:33:44]
	private static Date integerToTime(int time, Locale locale) {
		Date date = null;
		String pattern = ((SimpleDateFormat) DateFormat.getTimeInstance(
				DateFormat.SHORT, locale)).toPattern();
		if (pattern.toUpperCase().startsWith("H")) {
			String s = String.valueOf(time);
			pattern = s.length() < 3 ? "HH" : s.length() < 5 ? "HHmm"
					: "HHmmss";
			DateFormat dateformat = getDateFormatCustom(pattern,
					Locale.getDefault(), TimeZone.getDefault());
			try {
				date = dateformat.parse(s);
			} catch (Exception e) {

				displayError("CommonUtil", "integerToTime", e);
			}
		}
		return date;
	}

	/**
	 * Converts a given String into a CDateTime, and returns the CDateTime.
	 * (Only converts the date, not the time. Use {@link
	 * Util.stringToDateWithTime()} instead.)
	 * 
	 * @param s
	 *            a String to decode
	 */
	public static CDateTime stringToCDateTime(String s) {
		CDate d = stringToCDate(s);

		if (d == null)
			return null;
		return new CDateTime(d, TimeZone.getDefault());
	}

	public static CDateTime stringToCDateTime(String s, TimeZone tz) {
		CDate d = stringToCDate(s);
		if (d == null)
			return null;
		return new CDateTime(d, tz);
	}

	/**
	 * Converts a given String into a CDate, and returns the CDate.
	 * 
	 * @param s
	 *            a String to decode
	 */
	/**
	 * Returns the String representation of the date compoenent of a given
	 * CDateTime.
	 * 
	 * @param CDate
	 *            a CDateTime to convert
	 */
	public static String dateToString(CDateTime cDatetime) {
		if (cDatetime == null)
			return "";
		return dateToString(CDate.valueOf(cDatetime, TimeZone.getDefault()));
	}

	public static Vector<Attribute> convertStartupVectorToAtrributeVector(
			Vector<String> attributes) {
		Vector<Attribute> attributesbean = new Vector<Attribute>();
		if (!isEmpty(attributes)) {
			for (int i = 0; i < attributes.size(); i++) {
				Attribute att = new Attribute();
				att.setName(attributes.get(i));
				attributesbean.add(att);
			}

		}
		return attributesbean;
		// TODO Auto-generated method stub

	}

	public static void writeUTF(String _name, ObjectOutput out) {
		// TODO Auto-generated method stub

	}

	public static String readUTF(ObjectInput in) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Vector sort(Collection collection) {
		// TODO Auto-generated method stub
		return SortShell.sort(collection);
	}

	public static Vector<String> convertHolidayCodetoString(Vector<HolidayCode> sData)
			  {
		Vector<String> codeData = new Vector<String>  ();
		// TODO Auto-generated method stub
		if (!isEmpty(sData)) {
			for (int i = 0; i < sData.size(); i++) {
				HolidayCode att = sData.get(i);
			 
				codeData.addElement(att.getHolidayCode());

			}

		}
		return codeData;

	}
	public static Vector<String> convertCurrencyPairtoString(Vector<CurrencyPair> sData)
	  {
Vector<String> codeData = new Vector<String>  ();
// TODO Auto-generated method stub
if (!isEmpty(sData)) {
	for (int i = 0; i < sData.size(); i++) {
		CurrencyPair att = sData.get(i);
	 
		codeData.addElement(att.getPrimary_currency());

	}

}
return codeData;

}
}