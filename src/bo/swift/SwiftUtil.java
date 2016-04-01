package bo.swift;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;

import constants.CommonConstants;

import beans.Account;
import beans.Country;
import beans.ManualProcessSDI;
import beans.Message;
import beans.Sdi;
import beans.Trade;
import beans.Transfer;
import beans.LegalEntity;
import beans.TransferRule;
import util.BackOfficeCache;
import util.ClassInstantiateUtil;
import util.ReferenceDataCache;
import util.commonUTIL;
import util.common.DateU;
import beans.LeContacts;
import bo.message.bomessagehandler.BOMessageHandler;
import bo.swift.bic.BICSwiftData;
import bo.transfer.rule.GenerateFXTransferRule;
import bo.transfer.rule.ProductTransferRule;
public class SwiftUtil {
	static ReferenceDataCache refCache = new ReferenceDataCache();
	
	static Hashtable _hash = new Hashtable();
	static Hashtable _notfound = new Hashtable();
	 static boolean CHECK_SWIFT_BIC_DATA_B = true;
	    static boolean  CHECK_BIC_DATA_B = true;
	static public final int MAX_DIGITS = 15;

	static public final String EMPTY = "";
	static public final String UNKNOWN = "UNKNOWN";
	static public final String NONE = "NONE";
	static public final String NET = "NET";
	static public final String CEDEL_SWIFT = "CEDELULL";
	static public final String EUROCLEAR_SWIFT = "MGTCBEBE";
	static public final String DAKV_SWIFT = "DAKVDEFF";
	static public final String ESPB_SWIFT = "ESPBESMM";
	static public final String MOTI_SWIFT = "MOTIITMM";
	static public final String FED_SWIFT = "FRNYUS33";
	static public final String DTC_SWIFT = "DTCYUS33";
	static public final String BONY_SWIFT = "IRVTUS3N";
	static public final String CREST_SWIFT = "CRSTGB22";
	static public final String CITIATHENA_SWIFT = "CITIGRAA";
	static public final String CITIMILANO_SWIFT = "CITIITM";
	static public final String SICOVAM_SWIFT = "SICVFRPP";
	static public final String VPS_SWIFT = "VPSNNOKK"; // for Norway
	static public final String VPC_SWIFT = "VPCSSESS"; // for Sweden

	//CustomInfo for LegalEntity
		static EntityInfo _customInfo = null;
		static boolean _notFound = false;
	static public final String TAG_57 = "57";
	static public final String TAG_53 = "53";
	static public final String TAG_56 = "56";
	static public final String TAG_56_INT2 = "56_INT2";
	static public final String TAG_86 = "86";
	static public final String TAG_58 = "58";
	static public final String TAG_58A = "58A";
	static public final String TAG_50 = "50";
	static public final String TAG_50a = "50a";
	static public final String TAG_50C = "50C";
	static public final String TAG_52 = "52";
	static public final String TAG_59 = "59";
	static public final String TAG_59A = "59A"; // Can be only 59A(for MT103)
	static public final String TAG_54 = "54";
	static public final String TAG_55 = "55";
	static public final String TAG_95 = "95";
	static public final String TAG_22 = "22";
	static public final String TAG_INTERMEDIARY1 = "INTERMEDIARY1";
	static public final String TAG_INTERMEDIARY2 = "INTERMEDIARY2";
	static public final String TAG_INTERMEDIARY = "INTERMEDIARY";
	static public final String ROLE_BUYR = "BUYR";
	static public final String ROLE_SELL = "SELL";
	static public final String ROLE_DEAG = "DEAG";
	static public final String ROLE_DECU = "DECU";
	static public final String ROLE_DEIX = "DEIX";
	static public final String ROLE_PSET = "PSET";
	static public final String ROLE_REAG = "REAG";
	static public final String ROLE_RECU = "RECU";
	static public final String ROLE_REIX = "REIX";
	static public final String ROLE_SETR = "SETR";
	static public final String ROLE_SETR_DESC = "Type of Settlement Transaction Indicator";
	static public final String PRIR = "PRIR";

	static public final String BENEFICIARY = "BENEFICIARY";
	static public final String BENEFICIARY_NOACCOUNT = "BENEFICIARY_NOACCOUNT";
	static public final String AGENT = "AGENT";
	static public final String AGENT_DIRECT = "AGENT_DIRECT";
	static public final String INTERMEDIARY = "INTERMEDIARY";
	static public final String INTERMEDIARY2 = "INTERMEDIARY2";
	static public final String REMITTER = "REMITTER";

	
	
	static HashMap _attributeType;
	static {
		_attributeType = new HashMap();
		_attributeType.put(BENEFICIARY, "Beneficiary");
		_attributeType.put(BENEFICIARY_NOACCOUNT, "Beneficiary_NoAccount");
		_attributeType.put(AGENT, "Agent");
		_attributeType.put(AGENT_DIRECT, "Agent_Direct");
		_attributeType.put(INTERMEDIARY, "Intermediary");
		_attributeType.put(INTERMEDIARY2, "Intermediary2");
		_attributeType.put(REMITTER, "Remitter");
	}
	static Hashtable _countries;
	static {
		_countries = new Hashtable();
		_countries.put("Buenos Aires", "ARBA");
		_countries.put("Vienna", "ATVI");
		_countries.put("Melbourne", "AUME");
		_countries.put("Sydney", "AUSY");
		_countries.put("Brussels", "BEBR");
		_countries.put("S\u00e3o Paulo", "BRSP");
		_countries.put("Montreal", "CAMO");
		_countries.put("Toronto", "CATO");
		_countries.put("Geneva", "CHGE");
		_countries.put("Zurich", "CHZU");
		_countries.put("Santiago", "CLSA");
		_countries.put("Beijing", "CNBE");
		_countries.put("Prague", "CZPR");
		_countries.put("Frankfurt", "DEFR");
		_countries.put("Copenhagen", "DKCO");
		_countries.put("Tallinn", "EETA");
		_countries.put("Madrid", "ESMA");
		_countries.put("Helsinki", "FIHE");
		_countries.put("Paris", "FRPA");
		_countries.put("London", "GBLO");
		_countries.put("Athens", "GRAT");
		_countries.put("Hong Kong", "HKHK");
		_countries.put("Budapest", "HUBU");
		_countries.put("Jakarta", "IDJA");
		_countries.put("Tel Aviv", "ILTA");
		_countries.put("Milan", "ITMI");
		_countries.put("Rome", "ITRO");
		_countries.put("Tokyo", "JPTO");
		_countries.put("Seoul", "KRSE");
		_countries.put("Beirut", "LBBE");
		_countries.put("Luxembourg", "LULU");
		_countries.put("Kuala Lumpur", "MYKL");
		_countries.put("Mexico City", "MXMC");
		_countries.put("Amsterdam", "NLAM");
		_countries.put("Oslo", "NOOS");
		_countries.put("Auckland", "NZAU");
		_countries.put("Wellington", "NZWE");
		_countries.put("Panama City", "PAPC");
		_countries.put("Manila", "PHMA");
		_countries.put("Warsaw", "PLWA");
		_countries.put("Moscow", "RUMO");
		_countries.put("Riyadh", "SARI");
		_countries.put("Stockholm", "SEST");
		_countries.put("Singapore", "SGSI");
		_countries.put("Bratislava", "SKBR");
		_countries.put("Bangkok", "THBA");
		_countries.put("Ankara", "TRAN");
		_countries.put("Taipei", "TWTA");
		_countries.put("Chicago", "USCH");
		_countries.put("Los Angeles", "USLA");
		_countries.put("New York", "USNY");
		_countries.put("Johannesburg", "ZAJO");
	}
	static Hashtable _financialCenters;
	static {
		_financialCenters = new Hashtable();
		_financialCenters.put("Buenos Aires", "ARBA");
		_financialCenters.put("Vienna","ATVI");
		_financialCenters.put("Melbourne","AUME");
		_financialCenters.put("Sydney", "AUSY");
		_financialCenters.put("Brussels", "BEBR");
		_financialCenters.put("S\u00e3o Paulo","BRSP");
		_financialCenters.put("Montreal", "CAMO");
		_financialCenters.put("Toronto", "CATO");
		_financialCenters.put("Geneva", "CHGE");
		_financialCenters.put("Zurich", "CHZU");
		_financialCenters.put("Santiago","CLSA");
		_financialCenters.put("Beijing","CNBE");
		_financialCenters.put("Prague","CZPR");
		_financialCenters.put("Frankfurt","DEFR");
		_financialCenters.put("Copenhagen","DKCO");
		_financialCenters.put("Tallinn","EETA");
		_financialCenters.put("Madrid","ESMA");
		_financialCenters.put("Target","EUTA");
		_financialCenters.put("Helsinki","FIHE");
		_financialCenters.put("Paris", "FRPA");
		_financialCenters.put("London", "GBLO");
		_financialCenters.put("Athens", "GRAT");
		_financialCenters.put("Hong Kong", "HKHK");
		_financialCenters.put("Budapest", "HUBU");
		_financialCenters.put("Jakarta","IDJA");
		_financialCenters.put("Dublin", "IEDU");
		_financialCenters.put("Tel Aviv", "ILTA");
		_financialCenters.put("Milan","ITMI");
		_financialCenters.put("Rome", "ITRO");
		_financialCenters.put("Tokyo", "JPTO");
		_financialCenters.put("Seoul", "KRSE");
		_financialCenters.put("Beirut", "LBBE");
		_financialCenters.put("Luxembourg", "LULU");
		_financialCenters.put("Kuala Lumpur", "MYKL");
		_financialCenters.put("Mexico City", "MXMC");
		_financialCenters.put("Amsterdam","NLAM");
		_financialCenters.put("Oslo", "NOOS");
		_financialCenters.put("New York Fed", "NYFD");
		_financialCenters.put("New York Stock Exchange", "NYSE");
		_financialCenters.put("Auckland", "NZAU");
		_financialCenters.put("Wellington", "NZWE");
		_financialCenters.put("Panama City", "PAPC");
		_financialCenters.put("Manila", "PHMA");
		_financialCenters.put("Warsaw", "PLWA");
		_financialCenters.put("Lisbon", "PTLI");
		_financialCenters.put("Moscow", "RUMO");
		_financialCenters.put("Riyadh", "SARI");
		_financialCenters.put("Stockholm", "SEST");
		_financialCenters.put("Singapore", "SGSI");
		_financialCenters.put("Bratislava","SKBR");
		_financialCenters.put("Bangkok","THBA");
		_financialCenters.put("Ankara","TRAN");
		_financialCenters.put("Taipei", "TWTA");
		_financialCenters.put("Chicago", "USCH");
		_financialCenters.put("Los Angeles","USLA");
		_financialCenters.put("U.S. Governement Securities","USGS");
		_financialCenters.put("New York", "USNY");
		_financialCenters.put("Johannesburg", "ZAJO");
	}

	/**
	 * Format the BIC code.  If input is 8, characters,
	 * we simply append "AXXX" at the end.
	 * If BIC code is 11 characters, we should insert "A" in
	 * position 8.
	 */
	
	public static String formatBIC(String senderAddressCode, boolean isSender) {
		// TODO Auto-generated method stub
		if (commonUTIL.isEmpty(senderAddressCode)) {
			senderAddressCode = new String();
		}

		String aChar = isSender ? "A" : "X";
		if (senderAddressCode.length() == 8)
			return senderAddressCode + aChar + "XXX";

		if (senderAddressCode.length() == 11)
			return senderAddressCode.substring(0, 8) + aChar + senderAddressCode.substring(8);

		if (senderAddressCode.length() == 12)
			return senderAddressCode;

		for (int i = senderAddressCode.length(); i < 11; i++) senderAddressCode += "X";
		return senderAddressCode;
	}

	public static String formatBIC(String receiverAddressCode) {
		// TODO Auto-generated method stub
		 return formatBIC(receiverAddressCode, false);
	}

	public static String getSwiftCommonReferenceNoRounding(double price,
			String senderAddressCode, String receiver) {
		// TODO Auto-generated method stub
		return getSwiftCommonReference(price, senderAddressCode, receiver, false);
	}

	private static String getSwiftCommonReference(double price,
			String senderAddressCode, String receiver, boolean b) {
		// TODO Auto-generated method stub
		return getSwiftCommonReference(price, 0, senderAddressCode, receiver, b);
	}
	static public String getSwiftCommonReference(double price, int maxPriceLength,
			String senderCode,
			String receiverCode,
			boolean rounding)
	 {
		String commonRef = null;

		commonRef = checkCommonRefInfo(price, senderCode, receiverCode);
		if (commonRef != null) return commonRef;
		/*
          1.New check for letters take precedence
          2.First replace all numbers with some higher char
          3.First 4 chars of code and 7th&8th char of the code
		 */
		StringBuffer senderCodeS = new StringBuffer(senderCode.substring(0,4) +
				senderCode.substring(6,8));
		StringBuffer receiverCodeS = new StringBuffer(receiverCode.substring(0,4) +
				receiverCode.substring(6,8));


		for (int i = 0; i < senderCodeS.length(); i++) {
			String s = senderCodeS.substring(i, i + 1);
			if (commonUTIL.isNumeric(s)) {
				senderCodeS.replace(i, i + 1, new Character((char) ((int) 'z' + Integer.parseInt(s))).toString());
			}
		}
		for (int i = 0; i < receiverCodeS.length(); i++) {
			String s = receiverCodeS.substring(i, i + 1);
			if (commonUTIL.isNumeric(s)) {
				receiverCodeS.replace(i, i + 1, new Character((char) ((int) 'z' + Integer.parseInt(s))).toString());
			}

		}
		String priceString = getSwiftPrice(price, rounding, maxPriceLength).trim();

		if ((senderCodeS.toString()).compareTo(receiverCodeS.toString()) < 0) {
			commonRef = senderCode.trim().substring(0, 4) +
			senderCode.trim().substring(6, 8) +
			priceString +
			receiverCode.trim().substring(0, 4) +
			receiverCode.trim().substring(6, 8);
		}
		else {
			commonRef = receiverCode.trim().substring(0, 4) +
			receiverCode.trim().substring(6, 8) +
			priceString +
			senderCode.trim().substring(0, 4) +
			senderCode.trim().substring(6, 8);
		}

		// commonRef = "CommonReference";
		return commonRef;
	}
	
	static public String checkCommonRefInfo(double price, String senderCode,
			String receiverCode) {
		String commonRef = null;
		String strPrice = commonUTIL.converDoubleToString(price);

		if (strPrice == null || strPrice.trim().length() < 0) {
			commonRef = "Price Incorrect or blank";
			return commonRef;
		}

		commonRef = checkValidCode("Sender", senderCode);
		if (commonRef != null) return commonRef;

		commonRef = checkValidCode("Receiver", receiverCode);
		if (commonRef != null) return commonRef;

		return null;
	}

	static public String checkValidCode(String type, String code) {

		if (code == null || code.trim().length() < 8) {
			return type+" Swift Code must be greater than 8 characters";
		}

		return null;
	}
	static public int getNumberOfIntegerDigit(double amount) {
		if (amount > 0) {
			amount = Math.abs(amount);
		}
		return (int)(Math.log10(amount) + 1);
	}
	
	static public String getSwiftAmount(double amount,
			String ccy,
			int maxlength,
			boolean rounding,
			boolean checkLength, 
			int decimalPlaces) {
		amount = Math.abs(amount);
		if (ccy == null || ccy.equals("")) {
			if (rounding) {
				// remove the Integer number of digit + the decimal point
				int round = maxlength - getNumberOfIntegerDigit(amount) -1;
				if (round < 0) {
					round = 2;
				}
				if (round > decimalPlaces) {
					round = decimalPlaces;
				}
				amount = amount;//RoundingMethod.roundNearest(amount, round);    // code is required for the round 
			}
		}
		else
			amount =amount;//  CurrencyUtil.roundAmount(amount, ccy, decimalPlaces);  // code is required for the round 

		return formatAmount(amount, maxlength, checkLength);
		
	}
	
	private static String formatAmount(double amount, int maxlength,
			boolean checkLength)  {
		try {
			DecimalFormatSymbols sym = new
			DecimalFormatSymbols(Locale.getDefault());
			char SepDec = sym.getDecimalSeparator();
			NumberFormat f = NumberFormat.getInstance(Locale.getDefault());
			DecimalFormat df = (DecimalFormat) f;
			//BZ 25367 -
			//df.setMaximumFractionDigits(10); <-- don't work with 15 digit amount
			df.setMaximumFractionDigits(15);
			df.setGroupingUsed(false);
			df.setDecimalSeparatorAlwaysShown(true);
			String strAmount = (df.format(amount)).replace(SepDec, ',');

			if (strAmount.length() > maxlength) {
//				Log.error(LOG_CATEGORY, new Throwable("SWIFT length exceeded: " + strAmount + " is more than " + maxlength + " characters"));
				if(checkLength) {
					
				commonUTIL.display("SwiftUtil","SWIFT length exceeded: " + strAmount +
						" is more than " + maxlength + " characters");
				throw new NumberFormatException("SWIFT length exceeded: " + strAmount +
						" is more than " + maxlength + " characters");
				} else {
					return strAmount.substring(0,maxlength);
				}
			}
			else
				return strAmount;
		}
		catch (NumberFormatException e) {
			commonUTIL.displayError("SwiftUtil", "formatAmount", e);
			return String.valueOf(amount);
		}
	}
	
	static public String getSwiftAmount(double amount,
			String ccy,
			int maxlength,
			boolean rounding,
			boolean checkLength)
	    {
		amount = Math.abs(amount);
		if (ccy == null || ccy.equals("")) {
			if (rounding) {
				// remove the Integer number of digit + the decimal point
				int round = maxlength - getNumberOfIntegerDigit(amount) -1;
				if (round < 0) {
					round = 2;
				}
				amount = amount;// RoundingMethod.roundNearest(amount, round); // round code to be finished
			}
		}
		else
			amount =amount; // CurrencyUtil.roundAmount(amount, ccy);  // round code to be finished as per currency. 

		return formatAmount(amount, maxlength, checkLength);
	}
	static public String getSwiftPrice(double price, boolean rounding, int maxPriceLength)
			 {
				final boolean checkLength = maxPriceLength == 0;
				String amount = getSwiftAmount(price, null, 13, rounding, checkLength);

				if (maxPriceLength > 0) {
					if (amount.length() > maxPriceLength) {
						amount = amount.substring(0, maxPriceLength);
					}
				}

				String result = null;
				String priceS = "0000";
				if (amount.endsWith(","))
					result = amount.substring(0, amount.indexOf(","));
				else
					result = amount.substring(0, amount.indexOf(",")) +
					amount.substring(amount.lastIndexOf(",") + 1, amount.length());
				// Trailing zeros of amount will be trimmed.
				result = result.replace('0', ' ');
				result = result.trim();
				result = result.replace(' ', '0');

				if (result.length() >= 4) return result.substring(result.length() - 4);

				return priceS.substring(0, (4 - result.length())) + result;

			}

	
	
	
	static public String getSwiftAmount(double amount,
			String ccy,
			int maxlength,
			boolean rounding)
	 {
		return getSwiftAmount(amount, ccy, maxlength, rounding, true);
	}
	
	
	static public String getSwiftAmount(double amount, String ccy)
			 {
				return getSwiftAmount(amount, ccy, MAX_DIGITS);
			}

			static public String getSwiftAmount(double amount,
					String ccy,
					int maxlength)
			 {
				return getSwiftAmount(amount, ccy, maxlength, true);
			}
	
	public static String formatParty(String option,
			Sdi partySdi,
			String productType,
			int poId,
			Trade trade,
			Transfer transfer,
			Message message,
			Connection ds, Object dbCon) {
		return formatParty(option,
				partySdi.getCpId(),
				partySdi.getCode(), // needs to added
				partySdi.getRole(),
				message.getSenderContactType(),
				productType,
				poId,
				trade,
				transfer,
				message,
				ds, dbCon);
	}
	public static String formatParty(String option,
			int partyId,
			String partyName,
			String role,
			String contactType,
			String productType,
			int poId,
			Trade trade,
			Transfer transfer,
			Message message,
		    Connection ds, Object dbCon) {
		return formatParty(option,
				partyId,
				partyName,
				role,
				null,
				contactType,
				productType,
				poId,
				trade,
				transfer,
				message,
				ds, dbCon);

	}
	
	/**
	 * Returns the Party code according to the following arguments. This method is
	 * for example used in the following Swift templates: FXConfirm, FXOption, LoanDeposite,
	 * MoneyMarketInterestPayment for the tags: 82A, 82D, 87A or 87D. It is also
	 * used for Euroclear in tag 95Q.<br>
	 * <ul>
	 * <li>option = A or P, it returns the Swift code
	 * <li>option = Q, it returns the Registration code or the Swift Long Address
	 * {@link com.calypso.tk.bo.swift.SwiftUtil#getSwiftLongAddress} if the Registration
	 * code is null or UNKNOWN
	 * <li>option = D, it returns the Swift Long Address
	 * <li>option = R, it returns the value of the LegalEntityAttribute.CNTP_GSCC_ID (GSCC Member ID) attribute
	 * </ul>
	 * @param option possible values = A, P, Q, D or R
	 */
	public static String formatParty(String option,
			int partyId,
			String partyName,
			String role,
			String identifier,
			String contactType,
			String productType,
			int poId,
			Trade trade,
			Transfer transfer,
			Message message,
			Connection ds, Object dbCon) {


		if (option.equals("A") || option.equals("P")) {
			return getSwiftCode(partyId,
					role,
					contactType,
					productType,
					poId,
					trade, transfer, message,
					ds, dbCon);
		}if (option.equals("D"))
			return getSwiftLongAddress(partyId,
					partyName,
					role,
					contactType,
					productType,
					poId, trade,
					transfer, message, ds, dbCon).trim();
		if (option.equals("R")) {
			String value = UNKNOWN;
			if (!commonUTIL.isEmpty(identifier)) {
				String ref = null;  // this method need tobe implemented getDataSourceSchemeReference(identifier,trade,transfer,message,ds);

				if (ref == null)
					return value;
				else
					return ref;
			}
		}
		return null;
	}

	
	static public String getSwiftCode(int leId,
			String role,
			String contactType,
			String productType,
			int poId,
			Trade trade,
			Transfer transfer,
			Message message,
			Connection ds, Object dbCon) {

		return getAddressCode(leId, role, contactType, productType,
				poId, "SWIFT", trade, transfer, message,
				ds, dbCon);
	}
	
	
	/**
	 * Return the Code of a Party.
	 *
	 * The Code is used to populate the party
	 * fields.
	 *
	 * @param leId        Legal Entity for which to format the Address.
	 * @param role        The role of the Legal Entity.
	 * @param contactType The ContactType to be used.
	 * @param productType The product Type to be used.
	 * @param poId        Processing Org from which the contact is seen.
	 * @param addressType Address Type
	 * @param ds          A DataServer connection.
	 *
	 * @return a String, the Address. If the Address is
	 * not defined or ContactType, the code UNKNOWN is returned.
	 */
	static public String getAddressCode(int leId,
			String role,
			String contactType,
			String productType,
			int poId,
			String addressType,
			Trade trade,
			Transfer transfer,
			Message message,
			Connection ds, Object dbCon) {

		String swiftCode = UNKNOWN;
		if (leId == 0) return swiftCode;
		if (contactType == null) return swiftCode;
		beans.LegalEntity le = refCache.getLegalEntity(leId); // getLegalEntity(ds, dbCon, leId);
		if (le != null) {
			LeContacts  lec = refCache.getLEContact(role,trade.getTradeDate(),le.getId(),trade.getProductType(),contactType);
				//findContact(role, le, contactType,
					//	productType, poId, trade, transfer, message,
						//ds, dbCon);
			if (lec != null) {
				swiftCode = lec.getAddressCode(addressType);
				if (!commonUTIL.isEmpty(swiftCode))
					return swiftCode.trim();
				else
					return UNKNOWN;
			}
		}
		return swiftCode;
	}
	static public String getSwiftCode(int leId,
			String role,
			String contactType,
			String productType,
			int poId,
			Trade trade,
			Transfer transfer,
			Message message,
			Connection ds) {
		return getSwiftCode(leId, role, contactType, productType, poId,
				trade, transfer, message, ds, null);
	}

	public static String getSwiftTradeDate(Trade trade) {
		// TODO Auto-generated method stub
		String tradeDate = trade.getTradeDate().substring(0, 10);
		Date date = commonUTIL.stringToDate(tradeDate.trim(), true);
		DateU dated = DateU.valueOf(date);
		return getSwiftDate(dated, 8);
	
	}
	
	
	public static String getCCILDateHeaderFormat(String messagedate) {
		
		String messageStr = messagedate.substring(0, 18);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String dateTimeStr = null;
		try {
			dateTimeStr = new SimpleDateFormat("yyyyMMdd").format(formatter.parse(messageStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	    
		String timeStr = messageStr.substring(10, 15);
		
		String timeArr[] = timeStr.split(":");
		
		String finalTime = ""; 
			
		for(String time: timeArr) {			
			finalTime = finalTime + time;
		}
				
		return (dateTimeStr + finalTime).trim();
	
		
	}
	/**
	 * Returns a Swift Formatted date as YYMMDD.
	 *
	 * @return a String, a date formatted according to YYMMDD.
	 */
	static public String getSwiftDate2(Date date) {
		return getSwiftDate(date, 6);
	}
	static public String getSwiftDate(String date) {
		String tradeDate = date.substring(0, 10);
		Date dat = commonUTIL.stringToDate(tradeDate.trim(), true);
		DateU dated = DateU.valueOf(dat);
		return getSwiftDate(dated, 8);
	}
	static public String getSwiftDate(DateU date, int length) {
		String s = String.valueOf(date.getYear());
		if (date.getMonth() < 10)
			s += ("0" + date.getMonth());
		else
			s += date.getMonth();
		if (date.getDayOfMonth() < 10)
			s += ("0" + date.getDayOfMonth());
		else
			s += date.getDayOfMonth();

		if (length == 6 && s.length() == 8)
			return s.substring(2); // Return YYMMDD as opposed to YYYYMMDD
		if (length == 4 && s.length() == 8)
			return s.substring(4); // Return MMDD as opposed to YYYYMMDD
		return s;
	}
	/**
	 * Returns a Swift Formatted date either as YYYYMMDD, YYMMDD or MMDD
	 * depending on the given length parameter.
	 *
	 * @return a String, a date formatted according to YYYYMMDD,
	 *                   YYMMDD convention or MMDD , as desired.
	 */
	static public String getSwiftDate(Date date, int length) {
		String s = String.valueOf(date.getYear());
		
		if (date.getMonth() < 10)
			s += ("0" + date.getMonth());
		else
			s += date.getMonth();
		//if (date.getDayOfMonth() < 10) // see how calypso work.
			//s += ("0" + date.getDayOfMonth()); // see how calypso work.
	//	else
		//	s += date.getDayOfMonth(); // see how calypso work

		if (length == 6 && s.length() == 8)
			return s.substring(2); // Return YYMMDD as opposed to YYYYMMDD
		if (length == 4 && s.length() == 8)
			return s.substring(4); // Return MMDD as opposed to YYYYMMDD
		return s;
	}

	/**
	 * Returns a Swift Formatted date.
	 *
	 *
	 * @return a String, a date formatted according to YYYYMMDDHHMMSS convention.
	 */

	static public String getSwiftTimeStamp(java.util.Date date) {
		// 2 formats: authorized: YYMMDD or YYYYMDD
		// a mode should be added format 1 or 2
		// Code need to be changed
		SimpleDateFormat formatter
		= new SimpleDateFormat("yyyyMMddHHmmss");
		formatter.setTimeZone(TimeZone.getDefault());
		return formatter.format(date);
	}
	/**
	 * Retrieves and formats a 53A Swift field.  The 53A SWIFT field
	 * represents the Delivery Agent for the CounterParty or ProcessingOrg.
	 *
	 * The function is able to handle Manual or Regular Settlement
	 * Instructions.
	 *
	 * @param payOrReceive String denoting whether to show payer or receiver
	 * @param trade        Trade. May be NULL.
	 * @param transfer     a Transfer. May be NULL.
	 * @param fieldName    The Field Long Name description
	 * @param poB          Use ProcessingOrg SDI else CounterPartySDI.
	 * @param dsCon        A DataServer Connection.
	 *
	 * @return a SwiftFieldMessage
	 */
	public static SwiftFieldMessage getTAG53(ProductTransferRule prule,String payOrReceive,
			Trade trade,
			Transfer transfer,
			String fieldName,
			boolean poB,
			Message message,
			Vector<TransferRule> rules,
			Connection dsCon) {

	
		return getTAG53(prule,rules, trade, transfer, fieldName, poB, message, dsCon);
	}

	
	static public String addSortCode(String value, String tag,
			Sdi sdi,ValueTag tagValue) {
		if (tag == null || sdi == null) return value;

		String sortCode = null;
		if (tag.equals(TAG_56) || tag.equals(TAG_INTERMEDIARY)
				|| tag.equals(TAG_INTERMEDIARY1)) {
			if (sdi.getAttribute("Intermediary") != null)
				sortCode = sdi.getAttribute("Intermediary");
		}
		else if (tag.equals(TAG_57)) {
			if (sdi.getAttribute("Agent") != null)
				sortCode = sdi.getAttribute("Agent");
		}
		else if (tag.equals(TAG_52) || tag.equals(TAG_58)) {
			if (sdi.getAttribute("Beneficiary") != null)
				sortCode = sdi.getAttribute("Beneficiary");
		}
		if (sortCode != null) {
			if (value == null) value = "";
			if (tagValue != null && tagValue.getOption() != null && tagValue.getOption().equals("D")) {

			}
			else {
				int index = value.indexOf(SwiftMessage.ENDOFLINE);
				if (index > 0) {
					value = value.substring(index + SwiftMessage.ENDOFLINE.length());
					value.trim();
				}
			}
			value = "//" + sortCode +
			SwiftMessage.ENDOFLINE + value;
		}
		return value;
	}
	
	protected static SwiftFieldMessage createField(String tag,
			String option,
			boolean mandatoryB,
			String value,
			String fieldName,
			Sdi party) {
		if (value == null) return null;

		SwiftFieldMessage field = new SwiftFieldMessage(mandatoryB);
		field.setName(fieldName);
		field.setTAG(":" + tag + option + ":");
		field.setValue(value);
		if (party != null) {
			field.setLegalEntityId(party.getCpId());
		}

		return field;
	}
	private static SwiftFieldMessage getTAG53(ProductTransferRule prule,Vector<TransferRule> rules,
			Trade trade, Transfer transfer, String fieldName, boolean poB,
			Message message, Connection dsCon) {
		// TODO Auto-generated method stub
		Sdi sdi = null;
		
		if(rules == null || rules.isEmpty())
			return null;
	//	for(int i=0;i<rules.size();i++) {
		//	TransferRule rule = rules.get(i);
			if(poB) {
				sdi = prule.getSdi("PO");
			} else {
				
								sdi = prule.getSdi("CounterParty");
			}
			  
	//	}
		String option = null;
		String value = null;
		Sdi agentSDI = sdi.getAgentSdi(); // prule.getAgentSdi();
		ValueTag tagValue = getTagValue(agentSDI,
				trade,
				transfer,
				message,
				dsCon,
				null);
		option = tagValue.getOption();
		String deliveryAgent = tagValue.getValue();
		LegalEntity le = refCache.getLegalEntity(agentSDI.getAgentId());
		Sdi intermediarySDI = sdi.getIntermediary();
		String accountName = sdi.getsdiformat();
		if (intermediarySDI != null)
			value = formatAccount(intermediarySDI.getPartyAccountName());
		value = (value == null) ? deliveryAgent :
			value + SwiftMessage.ENDOFLINE + deliveryAgent;
		value = addSortCode(value, TAG_53, agentSDI,tagValue);

		return createField(TAG_53, option, false, value, fieldName,agentSDI);
	}
	protected static ValueTag getTagValue(Sdi sdi,
			Trade trade,
			Transfer transfer,
			Message message,
			Connection ds,
			Object dbCon) {
            return getTagValue(sdi, trade, transfer, message, null, null, ds, dbCon);
	}
	/**
	 * Return the Swift Long Address of a Manual Party SDI.
	 *
	 * The Swift Long Address is used to populate the party
	 * fields with option D.
	 * It returns the Party Long Name defined in the Manual SDI
	 * if not empty.
	 * If empty, and the Manual Party is referenced into the system,
	 * then we look at the SwiftLongAddress.
	 *
	 *
	 * @param msd         Manual Party SDI.
	 * @param role        The role of the Legal Entity.
	 * @param contactType The ContactType to be used.
	 * @param productType The product Type to be used.
	 * @param poId        Processing Org from which the contact is seen.
	 * @param ds          A DataServer connection.
	 *
	 * @return a String, the Long Address formatted using
	 * the FullName and the City. If the City is not defined
	 * or no contact is found, the code UNKNOWN is returned.
	 */

	/**
	 * Returns the Swift Long Address of a given PartySDI.
	 *
	 * The Swift Long Address is used to populate the party
	 * fiels with option D.
	 * If the Party Name is defined in the SDI, it returns the
	 * Party Name as specified.
	 * The String returned should look like this:
	 * Full Name (\n)
	 * City (\n)
	 *
	 * @param sdi a PartySDI
	 * @param productType The product Type to be used.
	 * @param poId        Processing Org from which the contact is seen.
	 * @param ds          A DataServer connection.
	 *
	 * @return a String, the Long Address formatted using
	 * the FullName and the City. If the City is not defined
	 * or no contact is found, the code UNKNOWN is returned.
	 */
	static public String getSwiftLongAddress(Sdi sdi,
			String productType,
			int poId,
			Trade trade,
			Transfer transfer,
			Message message,
		  Connection ds, Object dbCon) {
		String swiftLongCode = sdi.getPartyLongName();
		if (_customInfo == null && !_notFound) {
			try {
				_customInfo = (EntityInfo) getCustomInfoEntity("bo.swift.CustomEntityInfo");//, false);
			}
			catch (Exception e) {
				_notFound = true;
			}
			if (_customInfo == null) _notFound = true;
		}
		if (_customInfo == null) {
			if (swiftLongCode != null)
				return swiftLongCode.trim();
			else
				return swiftLongCode;
		}

		LegalEntity le = refCache.getLegalEntity(sdi.getPoId());
		if (le != null && !commonUTIL.isEmpty(le.getName())) {
			String leName = _customInfo.getRealName(le.getId());
			if (!commonUTIL.isEmpty(leName)) {
				swiftLongCode = formatLongString(leName, 4);
				swiftLongCode = swiftLongCode.trim();
			}

			LeContacts lec =  refCache.getLEContact(sdi.getRole(), le, sdi.getLeContacts(), productType,
					poId, trade, transfer, message, ds, dbCon);
			if (lec != null) {
				String addr = _customInfo.getRealAddress(lec.getId());
				if (!commonUTIL.isEmpty(addr)) {
					swiftLongCode = swiftLongCode + SwiftMessage.ENDOFLINE +
					formatLongString(addr, 4);
					swiftLongCode = swiftLongCode.trim();
				}

				swiftLongCode = formatLongString(swiftLongCode, 4);
			}
		}
		if (swiftLongCode != null)
			swiftLongCode = swiftLongCode.trim();
		return swiftLongCode;
	}

	static public String getSwiftLongAddress(ManualProcessSDI msd,
			String role,
			String contactType,
			String productType,
			int poId,
			Trade trade,
			Transfer transfer,
			Message message,
			Connection ds, Object dbCon) {
		if (msd == null) return UNKNOWN;
		if (!commonUTIL.isEmpty(msd.getPartyLongName()))
			return msd.getPartyLongName().trim();
		int leId = msd.getCpId();
		if (leId == 0) return UNKNOWN;
		return getSwiftLongAddress(leId, null, role, contactType, productType,
				poId, trade, transfer, message, ds, dbCon);
	}
	
	/**
	 * Return the Swift Long Address of a Manual Party SDI.
	 *
	 * The Swift Long Address is used to populate the party
	 * fields with option D.
	 * It returns the Party Long Name defined in the Manual SDI
	 * if not empty.
	 * If empty, and the Manual Party is referenced into the system,
	 * then we look at the SwiftLongAddress.
	 *
	 *
	 * @param msd         Manual Party SDI.
	 * @param role        The role of the Legal Entity.
	 * @param contactType The ContactType to be used.
	 * @param productType The product Type to be used.
	 * @param poId        Processing Org from which the contact is seen.
	 * @param ds          A DataServer connection.
	 *
	 * @return a String, the Long Address formatted using
	 * the FullName and the City. If the City is not defined
	 * or no contact is found, the code UNKNOWN is returned.
	 */
	

	
	/**
	 * Return the Swift Long Address of a Party.
	 *
	 * The Swift Long Address is used to populate the party
	 * fiels with option D.
	 * If the Party Name is defined in the SDI, it returns the
	 * Party Name as specified.
	 * Given a contact type, a role, a legal entity id
	 * return the "Full Name of the Legal entity", "city"
	 * The String returned should look like this:
	 * Full Name (\n)
	 * City (\n)
	 *
	 * @param leId        Legal Entity for which to format the Address.
	 * @param partyName   Party Name as defined in the SDI. May be NULL.
	 * @param role        The role of the Legal Entity.
	 * @param contactType The ContactType to be used.
	 * @param productType The product Type to be used.
	 * @param poId        Processing Org from which the contact is seen.
	 * @param ds          A DataServer connection.
	 *
	 * @return a String, the Long Address formatted using
	 * the FullName and the City. If the City is not defined
	 * or no contact is found, the code UNKNOWN is returned.
	 */
	
	static public String getSwiftLongAddress(int leId,
			String partyName,
			String role,
			String contactType,
			String productType,
			int poId,
			Trade trade,
			Transfer transfer,
			Message message,
			Connection ds, Object dbCon) {
		String swiftLongCode = UNKNOWN;
		if (leId == 0) return swiftLongCode;
		if (contactType == null) return UNKNOWN;
		if (!commonUTIL.isEmpty(partyName)) return partyName;
		LegalEntity le = refCache.getLegalEntity(leId);
		if (_customInfo == null && !_notFound) {
			try {
				_customInfo = (EntityInfo) getCustomInfoEntity("bo.swift.CustomEntityInfo");//, false);
			}
			catch (Exception e) {
				_notFound = true;
			}
			if (_customInfo == null) _notFound = true;
		}
		if (le != null && !commonUTIL.isEmpty(le.getName())) {
			LeContacts lec =
					refCache.getLEContact(role, le, contactType, productType,
						poId, trade, transfer, message, ds, dbCon);
			if (lec != null) {
				//Made change because even if the name is available
				// the previous implemetation used to show UNKNOWN
//				New functionality you can have a real name and address
//				which could be different than
//				what is defined on LegalEntity/Contact for swift purpose
				if (_customInfo != null &&
						!commonUTIL.isEmpty(_customInfo.getRealName(le.getId()))) {
					swiftLongCode = formatLongString
					(_customInfo.getRealName(le.getId()), 4);
					swiftLongCode = swiftLongCode.trim();
				}
				else if (!commonUTIL.isEmpty(le.getName())) {
					swiftLongCode = formatLongString(le.getName().trim(), 4);
					swiftLongCode = swiftLongCode.trim();
				}
				if (_customInfo != null &&
						!commonUTIL.isEmpty(_customInfo.getRealAddress(lec.getId()))) {
					swiftLongCode = swiftLongCode + SwiftMessage.ENDOFLINE +
					formatLongString
					(_customInfo.getRealAddress(lec.getId()), 4);
					swiftLongCode = swiftLongCode.trim();
				}
				else {
					if (!commonUTIL.isEmpty(lec.getMailingAddress())) {
						swiftLongCode = swiftLongCode +
						SwiftMessage.ENDOFLINE +
						lec.getMailingAddress().trim();
						swiftLongCode = swiftLongCode.trim();
					}

					String info = "";
					boolean zipFirst = true;/// Defaults.getBooleanProperty(Defaults.SWIFT_ZIP_FIRST, false);
					if (zipFirst && !commonUTIL.isEmpty(lec.getZipCode())) {
						info = info + lec.getZipCode().trim()+" ";
					}
					if(!commonUTIL.isEmpty(lec.getCity())) {
						info = info + lec.getCity().trim()+" ";
					}
					if (!commonUTIL.isEmpty(lec.getState())) {
						info = info + lec.getState().trim()+" ";
					}
					if(!zipFirst && !commonUTIL.isEmpty(lec.getZipCode())) {
						info = info + lec.getZipCode().trim()+" ";
					}
					if(!commonUTIL.isEmpty(lec.getCountry()) && info.length()>0) {
						info = info + " " + lec.getCountry().trim();
					}

					if (!commonUTIL.isEmpty(info)) {
						swiftLongCode = swiftLongCode
						+ SwiftMessage.ENDOFLINE
						+ info;
					}
				}
				swiftLongCode = formatLongString(swiftLongCode, 4);
			}
		}
		
			commonUTIL.display("SwiftUtil", "LAST => " + swiftLongCode.replace('\n', '#').replace('\r', '|'));
		swiftLongCode = swiftLongCode.trim();
		return swiftLongCode;
	}
	
	// more logic needs to be add when the type is intermidery. 
	protected static ValueTag getTagValue(String type,
			Sdi sdi,
			Trade trade,
			Transfer transfer,
			Message message,
			TransferRule rule,
			boolean useSortCode,
			Connection dsCon) {
		Sdi party = sdi;
		Sdi account = null;
                String   accountName = null;
                Boolean  isCptyFinancialB = null;
            	boolean  isConfirmation  = true;
               if( message.getReceiverRole().equalsIgnoreCase("AGENT")) {
            	   isConfirmation = false;
               }
               if (type.equals(BENEFICIARY)) {
       			party = sdi.getBeneficiary();
       			if (isConfirmation) {
       				//account = sdi.getAgent();
       				account = sdi.getBeneficiary();
       			}
       			else {
       				
       				account = sdi.getAgentSdi();
       			}
       		}
               if (type.equals("AGENT")) {
            	   account = sdi.getAgentSdi();
               }
               if (party == null || party.getId() == 0)
                   return new ValueTag();
             ValueTag tagValue =  getTagValue(party,
                       trade,
                       transfer,
                       message,
                       isCptyFinancialB,
                       accountName,
                       dsCon,
                       null);
               if (tagValue != null && "F".equals(tagValue.getOption()) && !commonUTIL.isEmpty(tagValue.getValue())) {
                   return tagValue;
               }
               String value = null;
               if (account != null && account.getId() != 0)
                   value = formatAccount(account.getPartyAccountName());
               if (!commonUTIL.isEmpty(tagValue.getValue())) {
                   value = (value == null) ? tagValue.getValue() :
                       value + SwiftMessage.ENDOFLINE + tagValue.getValue();
               }
               else {
                   return tagValue;
               }
               if (useSortCode) {
                   String sortCode = getSortCode(sdi, type);
                   if (!commonUTIL.isEmpty(sortCode)) {
                       if (tagValue != null && tagValue.getOption() != null && tagValue.getOption().equals("D")) {
                       }
                       else {
                           int index = value.indexOf(SwiftMessage.ENDOFLINE);
                           if (index > 0) {
                               value = value.substring(index + SwiftMessage.ENDOFLINE.length()).trim();
                           }
                       }
                       value = "//" + sortCode + SwiftMessage.ENDOFLINE + value;
                   }
               }
               tagValue.setValue(value);
               return tagValue;

			
		
	}
	
	protected static ValueTag getTagValue(Sdi sdi,
			Trade trade,
			Transfer transfer,
			Message message,
			Boolean isCptyFinancialB,
                        String partyAccountName,
			Connection ds,
			Object dbCon) {

		if (sdi == null)
			return new ValueTag(null, "D", UNKNOWN,0);

		int poId = 0;
		String productType = null;
		if (trade != null) {
			LegalEntity po =  ReferenceDataCache.getPO(trade.getBookId());
			poId = po.getId();
			productType = trade.getProductType();
		}
		else if (transfer != null) {
			if(transfer.getPayerRole().equalsIgnoreCase("PO"))
			poId = Integer.valueOf(transfer.getPayerCode()).intValue();
			else 
				poId  = Integer.valueOf(transfer.getPayerCode()).intValue();
			productType = transfer.getProductType();
		}
		sdi.setPoId(poId);
		sdi.setProducts(productType);
		if (message != null) {
		    sdi.setValDate(message.getSettleDate());
		}
		sdi.setTrade(trade);
		sdi.setTransfer(transfer);

		String value = null;
		if (sdi instanceof ManualProcessSDI) {
			ManualProcessSDI manualPartySDI = (ManualProcessSDI)sdi;
			value = getSwiftCode(manualPartySDI,
					sdi.getRole(),
					sdi.getLeContacts(),
					productType,
					poId,
					trade,
					transfer,
					message,
					ds,
					dbCon);
			if (value != null)
				value = value.trim();
			if (isValidBIC(value, sdi, productType, poId, trade, transfer, message, ds, dbCon))
				return new ValueTag(null, "A", value, sdi.getCpId(), value);

			value = getSwiftLongAddress(manualPartySDI,
					manualPartySDI.getRole(),
					manualPartySDI.getLeContacts(),
					productType,
					poId,
					trade,
					transfer,
					message,
					ds,
					dbCon);
		}
		else {
			if (sdi.getCode() != null && sdi.getCode().equals("SWIFT"))
				value = sdi.getCodeValue();
			if (value != null)
				value = value.trim();
			if (isValidBIC(value, sdi, productType, poId, trade, transfer, message, ds, dbCon))
				return new ValueTag(null, "A", value, sdi.getCpId(), value);

			value = getSwiftLongAddress(sdi,
					productType,
					poId,
					trade,
					transfer,
					message,
					ds,
					dbCon);
		}
		if (value != null)
			value = value.trim();
		if (isCptyFinancialB == Boolean.FALSE) {
			LegalEntity le = refCache.getLegalEntity(sdi.getCpId());
			// value is Long Address
			LeContacts leC =refCache.getLEContact(le.getRole(), sdi.getValDate(), le.getId(), sdi.getProducts(), sdi.getLeContacts()); //findContact(sdi.getPartyRole(), le, sdi.getPartyContactType(), productType, poId, trade, transfer, message, ds, dbCon);
			String tag50FValue = buildTag50FValue(sdi, partyAccountName, le, leC);
			if (!commonUTIL.isEmpty(tag50FValue))
				// "true" means "Option F" will override
				// default XML tag option
				return new ValueTag(null, "F", tag50FValue, sdi.getCpId(), true);
		}
		return new ValueTag(null, "D", value, sdi.getCpId());
	}
	

	

	public static boolean isValidBIC(String value,Sdi sdi,
			String productType,
			int poId,
			Trade trade,
			Transfer transfer,
			Message message,
			Connection ds, Object dbCon) {
		if (commonUTIL.isEmpty(value))
			return false;

		if (value.equals(UNKNOWN))
			return false;


		value = value.trim();
		if (value.length() < 8) return false;

		// Is the SWIFT code fake or 'connected'
		// this is determined by 8th character = 1 (not connected)
		//BZ 29912
		if (value.charAt(7) == '1') {
			if (isNonConnectedBIC(sdi, productType, poId, trade, transfer, message, ds, dbCon)){
				//Non Connected BIC
				return true;
			} else {
				//Fake BIC
				return false;
			}
		}
		//Valid BIC
		return true;
	}
	
	static public boolean isNonConnectedBIC(Sdi sdi,
			String productType,
			int poId,
			Trade trade,
			Transfer transfer,
			Message message,
			Connection ds, Object dbCon) {
		if (CHECK_BIC_DATA_B) {
			return true;
		}
		// Given a contact type, a role, a legal entity id
		// retrieve the contact record and returns the swift code
		LegalEntity le = refCache.getLegalEntity(sdi.getCpId());
		if (le != null) {
			LeContacts lec =
					refCache.getLEContact(sdi.getRole(), le, sdi.getLeContacts(), productType, poId,
						trade, transfer, message, ds, dbCon);
			if (lec != null) {
			//	return Defaults.string2boolean(lec.getAddressCode("NON_CONNECTED_BIC"), false);
				return false;
			}
		}
		return false;
	}
	
	
	/**
	 * Retrieves and formats a 58 Swift field.  The 58 SWIFT field
	 * represents the Beneficiary for the ProcessingOrg or
	 * CounterParty SDI.
	 *
	 * The function is able to handle Manual or Regular Settlement
	 * Instructions.
	 *
	 * @param trade        Trade. May be NULL.
	 * @param transfer     a Transfer. May be NULL.
	 * @param fieldName    The Field Long Name description
	 * @param poB          Use ProcessingOrg SDI else CounterPartySDI.
	 * @param dsCon        A DataServer Connection.
	 *
	 * @return a SwiftFieldMessage
	 */
	public static SwiftFieldMessage getTAG58(
			GenerateFXTransferRule PRule, String string, Trade trade,
			Transfer transfer, String fieldName, boolean b,Message message, 
			Vector<TransferRule> rules, Connection dsCon) {
		// TODO Auto-generated method stub
		return getTAG58(PRule,trade,transfer,fieldName,b,message,rules,dsCon);
	}
	public static SwiftFieldMessage getTAG58(ProductTransferRule rulep,Trade trade,
			Transfer transfer,
			String fieldName,
			boolean poB,
			Message message,Vector<TransferRule> rules,
			Connection dsCon) {

		return getTAG58(rulep,getPayRec(trade, poB), trade, transfer, fieldName, poB, message,rules, dsCon);
	}
        private static bo.swift.SwiftFieldMessage getTAG58(
			ProductTransferRule rulep, String payRec, Trade trade,
			Transfer transfer, String fieldName, boolean poB, Message message,
			Vector<TransferRule> rules, Connection dsCon) {
		// TODO Auto-generated method stub
        	String ccy = trade.getCurrency();
    		String settle = trade.getTradeDate();
    	TransferRule rule = SwiftUtil.getTransferRule(rules,ccy,"PRINCIPAL",payRec,settle);
    	return getTAG58(rulep,rule, trade, transfer, fieldName, poB, message, rules,dsCon);
		
	}

		private static bo.swift.SwiftFieldMessage getTAG58(
				ProductTransferRule rulep, TransferRule rule, Trade trade,
				Transfer transfer, String fieldName, boolean poB,
				Message message, Vector<TransferRule> rules, Connection dsCon) {
			// TODO Auto-generated method stub
			if (poB) return null;

			if (rule == null) {
				return formatSettlementInstructionNoRule(false,
						TAG_58,
						fieldName);
			}

			String nettingType = null;//  getNettingType(rule, transfer, poB, dsCon); method needs to be implemented.
			if (nettingType != null && !nettingType.equals("CLS"))
				return null;

			if (!checkSdiStatus(rule, poB))
				return null;
			Sdi psdi = null;
			Sdi rsdi = null;
			if(rules == null || rules.isEmpty())
				return null;
			for(int i=0;i<rules.size();i++) {
				TransferRule r = rules.get(i);
				if(poB) {
					psdi = rulep.getSdi(r.get_payerSDId());
				} else {
					rsdi = rulep.getSdi(r.get_receiverSDId());
				}
				  
			}
			Sdi sdi = null;
			if (poB)
				sdi = psdi;
			else
				sdi = rsdi;
			if (sdi == null) return null;
			String option = null;
			String value = null;
			Sdi beneficiary = sdi.getBeneficiary();
			ValueTag tagValue = getTagValue(beneficiary,
					trade,
					transfer,
					message,
					dsCon,
					null);
			option = tagValue.getOption();
			String deliveryAgent = tagValue.getValue();

			Sdi agent = sdi.getAgentSdi();
			Account acc = null;
			if (agent != null)  {
				acc = BackOfficeCache.getAccount(agent.getAccountID());
			}
				value = formatAccount(acc.getAccountName());
			value = (value == null) ? deliveryAgent :
				value + SwiftMessage.ENDOFLINE + deliveryAgent;
			value = addSortCode(value, TAG_58, sdi,tagValue);

			return createField(TAG_58, option, false, value, fieldName,beneficiary);
			
		}

		static String buildTag50FValue(Sdi remitter, String acc, LegalEntity le, LeContacts leC) {
        	String partyId = null;
        	if (remitter instanceof ManualProcessSDI)
        	 partyId = ((ManualProcessSDI)remitter).getIdentifier();
            Vector leAttributes = null;
            if (!remitter.isUnknown()) {
                leAttributes = refCache.getAttributes( le.getId());
            }
            if (commonUTIL.isEmpty(partyId) && leAttributes.isEmpty() && acc == null) {
                return null;
            }
            String dateOfBirth = null;
            String placeOfBirth = null;
            String customerId = null;
            String nationalId = null;
            int counter = 0;
            boolean noPartyIdB = commonUTIL.isEmpty(partyId);
            int nbAttributes = (leAttributes == null) ? 0 : leAttributes.size();
            for (int i = 0; i < nbAttributes; i++) {
                 beans.Attribute leAttribute = (beans.Attribute)leAttributes.get(i);
                String type = leAttribute.getName();
                String value = leAttribute.getValue();
                if (commonUTIL.isEmpty(value))
                    continue;
                value = value.trim();
                if (value.length() == 0) {
                    continue;
                }
                if (type.equalsIgnoreCase("OPTION_F_PARTY_ID")) {
                    partyId = new StringBuffer(value).toString();
                    counter++;
                }
                if (type.equalsIgnoreCase("OptionF_DateOfBirth")) { 
                    dateOfBirth = new StringBuffer("4/").append(value).toString();
                    counter++;
                }
                if (type.equalsIgnoreCase("OptionF_PlaceOfBirth")) {
                    placeOfBirth = new StringBuffer("5/").append(value).toString();
                    counter++;
                }
                if (type.equalsIgnoreCase("OptionF_CustomerId")) {
                    customerId = new StringBuffer("6/").append(value).toString();
                    counter++;
                }
                if (type.equalsIgnoreCase("OptionF_NationalId")) {
                    nationalId = new StringBuffer("7/").append(value).toString();
                    counter++;
                }
                if (counter == 5)
                    break;
            }
            if (noPartyIdB && counter == 0 && acc == null) {
                return null;
            }
            String name = null;
            String address = null;
            String countryCity = null;
            String comment = null;
            StringBuffer buff = new StringBuffer();
            StringBuffer tmpBuff = null;
            int[] remainingLen = null;
            if (!remitter.isUnknown() && leC != null) {
                // 1/Last Name First Name
                if (!commonUTIL.isEmpty(leC.getLeLastName()))
                    buff.append("1/").append(leC.getLeLastName());
                if (!commonUTIL.isEmpty(leC.getLeFirstName())) {
                    if (buff.length() > 0)
                        buff.append(' ');
                    else
                        buff.append("1/");
                    buff.append(leC.getLeFirstName());
                }
                if (buff.length() > 0) {
                    name = buff.toString();
                    buff.setLength(0);
                }
                // 2/Mailing Address
                if (!commonUTIL.isEmpty(leC.getMailingAddress())) {
                    address = leC.getMailingAddress();
                    if (address.length() > 33) {
                        String workingStr = formatLongString(address, 4, 33);
                        StringTokenizer tokenizer = new StringTokenizer(workingStr, SwiftMessage.ENDOFLINE);
                        String token = tokenizer.nextToken();
                        buff.append("2/").append(token);
                        while (tokenizer.hasMoreTokens()) {
                            token = tokenizer.nextToken();
                            buff
                                .append(SwiftMessage.ENDOFLINE)
                                .append("2/")
                                .append(token);
                        }
                    }
                    else {
                        buff.append("2/").append(address).toString();
                    }
                    address = buff.toString();
                    buff.setLength(0);
                }
                // 3/Country/City
                if (!commonUTIL.isEmpty(leC.getCountry())) {
                 Country country  =  refCache.getCountry(leC.getCountry());
                		  String countryISOCODE  = country.getIsocode();
                    if (countryISOCODE != null && !commonUTIL.isEmpty(countryISOCODE))
                        buff.append("3/").append(countryISOCODE);
                }
                if (!commonUTIL.isEmpty(leC.getCity())) {
                    if (buff.length() > 0)
                        buff.append('/');
                    else
                        buff.append("3/");
                    buff.append(leC.getCity());
                }
                if (buff.length() > 0) {
                    countryCity = buff.toString();
                    buff.setLength(0);
                }
                // 8/Comment
                if (!commonUTIL.isEmpty(leC.getComments())) {
                    String tmp = leC.getComments().trim();
                    if (!commonUTIL.isEmpty(tmp))
                        comment = new StringBuffer("8/").append(leC.getComments()).toString();
                }
            }
            else if (remitter.isUnknown() && !commonUTIL.isEmpty(remitter.getPartyLongName())) {
                // 1/Last Name First Name
                if (!commonUTIL.isEmpty(remitter.getPartyCode()))
                    buff.append("1/").append(remitter.getPartyCode());
                if (buff.length() > 0) {
                    name = buff.toString();
                    buff.setLength(0);
                }
                // 2/Address
                address = extractAddressField(remitter.getPartyCode(),
                                              remitter.getPartyLongName(),
                                              ((ManualProcessSDI)remitter).getCountryAndCity(),
                                              true);
                if (!commonUTIL.isEmpty(address)) {
                    if (address.length() > 33) {
                        String workingStr = formatLongString(address, 4, 33);
                        StringTokenizer tokenizer = new StringTokenizer(workingStr, SwiftMessage.ENDOFLINE);
                        String token = tokenizer.nextToken();
                        buff.append("2/").append(token);
                        while (tokenizer.hasMoreTokens()) {
                            token = tokenizer.nextToken();
                            buff
                                .append(SwiftMessage.ENDOFLINE)
                                .append("2/")
                                .append(token);
                        }
                    }
                    else {
                        buff.append("2/").append(address).toString();
                    }
                    address = buff.toString();
                    buff.setLength(0);
                }
                // 3/Country/City
                String countryAndCity = ((ManualProcessSDI)remitter).getCountryAndCity();
                if (!commonUTIL.isEmpty(countryAndCity)) {
                    if (countryAndCity != null)
                        buff.append("3/").append(countryAndCity);
                }
                if (buff.length() > 0) {
                    countryCity = buff.toString();
                    buff.setLength(0);
                }
            }
            // Tag Value
            if (partyId != null) {
                buff.append(partyId);
            }
            else if (acc != null) {
                // No party Id; we take the Party Account.
                // Note that this account will always be empty
                // if the current Swift type is not an MT210
                buff.append(SwiftUtil.formatAccount(acc));
            }
            if (name != null) {
                if (buff.length() > 0)
                    buff.append(SwiftMessage.ENDOFLINE);
                buff.append(name);
            }
            // 2/Mailing Address must be together and 3/Country/City
            if (address != null && countryCity != null) {
                tmpBuff = new StringBuffer(buff);
                remainingLen = new int[1];
                remainingLen[0] = 0;
                if (tmpBuff.length() > 0)
                    tmpBuff.append(SwiftMessage.ENDOFLINE);
                tmpBuff
                    .append(address)
                    .append(SwiftMessage.ENDOFLINE)
                    .append(countryCity);
                String tag50F = SwiftUtil.formatLongString(tmpBuff.toString(), 5, EMPTY, 35, remainingLen);
                if (remainingLen[0] == 0) {
                    // The Address and Country fit in Tag50F, we switch
                    // on tmp buffer by swaping StringBuffers. We would
                    // have stayed on original buffer without Address
                    // and Country otherwise
                    StringBuffer keepBuff = buff;
                    buff = tmpBuff;
                    tmpBuff = keepBuff;
                }
            }
            // 4/DateOfBirth and 5/PlaceOfBirth must be together
            if (dateOfBirth != null && placeOfBirth != null) {
                if (tmpBuff != null) {
                    tmpBuff.setLength(0);
                    tmpBuff.append(buff);
                    remainingLen[0] = 0;
                }
                else {
                    tmpBuff = new StringBuffer(buff);
                    remainingLen = new int[1];
                    remainingLen[0] = 0;
                }
                if (tmpBuff.length() > 0)
                    tmpBuff.append(SwiftMessage.ENDOFLINE);
                tmpBuff
                    .append(dateOfBirth)
                    .append(SwiftMessage.ENDOFLINE)
                    .append(placeOfBirth);
                String tag50F = SwiftUtil.formatLongString(tmpBuff.toString(), 5, EMPTY, 35, remainingLen);
                if (remainingLen[0] == 0) {
                    // The Place and Date of birth fit in Tag50F, we switch
                    // on tmp buffer by swaping StringBuffers. We would
                    // have stayed on original buffer without Place
                    // and Date of Birth otherwise
                    StringBuffer keepBuff = buff;
                    buff = tmpBuff;
                    tmpBuff = keepBuff;
                }
            }
            // 6/CustomerId
            if (customerId != null) {
                if (buff.length() > 0)
                    buff.append(SwiftMessage.ENDOFLINE);
                buff.append(customerId);
            }
            // 7/NationalId
            if (nationalId != null) {
                if (buff.length() > 0)
                    buff.append(SwiftMessage.ENDOFLINE);
                buff.append(nationalId);
            }
            // 8/Comment only if partyId is format 2 (4!a/30x)
            if (partyId != null &&
                comment != null &&
                partyId.length() > 4 &&
                partyId.charAt(4) == '/') {
                boolean format2B = true;
                for (int i = 1; i < 4; i++) {
                    if (!Character.isLetter(partyId.charAt(i))) {
                        format2B = false;
                        break;
                    }
                }
                if (format2B) {
                    if (buff.length() > 0)
                        buff.append(SwiftMessage.ENDOFLINE);
                    buff.append(comment);
                }
            }
            // Format Tag ":50F:" is "5*35x"
            return SwiftUtil.formatLongString(buff.toString(), 5);
        }
	
        static public String formatAccount(String account) {
    		if (!commonUTIL.isEmpty(account))
    			return "/" + account;
    		else
    			return null;
    	}
        /**
	 * Returns a Swift Formatted hour in the following
	 * format:   HHMM
	 *
	 * @return a String, a date formatted according to HHMM convention.
	 */
	static public String getSwiftHour(int time) {
		int hour = (int) (time / 100);
		int minutes = (int) (time % 100);

		String s;
		if (hour < 10)
			s = "0" + hour;
		else
			s = "" + hour;
		if (minutes < 10)
			s += "0" + minutes;
		else
			s += "" + minutes;

		return s;
	}
	
	 public static String extractAddressField(String partyName, String longAddress, String countryAndCity, boolean hasBicCodeB) {
         if (commonUTIL.isEmpty(longAddress)) {
             return EMPTYSTRING;
         }
         String localPartyName;
         if (partyName == null) {
             localPartyName = EMPTYSTRING;
         }
         else {
             localPartyName = partyName.replaceAll("\r\n", "\n");
         }
         String localCountryAndCity;
         if (countryAndCity == null) {
             localCountryAndCity = EMPTYSTRING;
         }
         else {
             localCountryAndCity = countryAndCity.replaceAll("\r\n", "\n");
         }
         String localAddress = longAddress.replaceAll("\r\n", "\n");
         int partyNameLen = localPartyName.length();
         int addressLen = localAddress.length();
         int nameIndex = 0;
         int addressIndex = 0;
         for (; addressIndex < partyNameLen && addressIndex < addressLen;) {
             char nameCharact = localPartyName.charAt(nameIndex);
             char addressCharact = localAddress.charAt(addressIndex);
             if (nameCharact != addressCharact) {
                 if (addressCharact == '\n') {
                     if (nameCharact == ' ') {
                         nameIndex++;
                     }
                     addressIndex++;
                 }
                 else {
                     break;
                 }
             }
             else {
                 nameIndex++;
                 addressIndex++;
             }
         }
         addressIndex++;
         if (addressIndex < addressLen && localAddress.charAt(addressIndex) == '\n') {
             addressIndex++;
         }
         if (addressIndex < addressLen) {
             localAddress = localAddress.substring(addressIndex);
         }
         else {
             localAddress =  null;
         }
         if (!hasBicCodeB) {
             if (!commonUTIL.isEmpty(countryAndCity)) {
                 localCountryAndCity = SwiftUtil.formatLongString(countryAndCity, 1);
             }
         }
         if (!commonUTIL.isEmpty(localAddress) && !commonUTIL.isEmpty(localCountryAndCity)) {
             int countryAndCityIndex = localAddress.lastIndexOf(localCountryAndCity);
             if (countryAndCityIndex == 0) {
                 // After name, we have country and city;
                 // that means no address in long name
                 localAddress = EMPTYSTRING;
             }
             else if (countryAndCityIndex > 1){
                 localAddress = localAddress.substring(0, countryAndCityIndex -1);
             }
             // else { } Country and city not in long name
         }
         return localAddress;
     }

	public static boolean isValidBIC(String bicCode, String bicBranch, List messages) {
		List result = getBicDatas(bicCode, bicBranch);

		if (!result.isEmpty() ) {
			messages.add("BIC = '" + bicCode + bicBranch + "' not found in SWIFT BIC DATA");
			return false;
		}
		return true;
	}
	
	public static boolean isValidBIC(String bicCode, List messages) {
		if (!checkBIC(bicCode, messages))
			return false;

		StringBuffer bicBuff = new StringBuffer();
		StringBuffer branchBuff = new StringBuffer();
		if (!CHECK_SWIFT_BIC_DATA_B && !CHECK_BIC_DATA_B)
			return true;
		getBicCodeAndBranch(bicCode, bicBuff, branchBuff);
		return isValidBIC(bicBuff.toString(), branchBuff.toString(), messages);
	}
	public static List getBicDatas(String bicCode, String bicBranch) {
		try {
			BICSwiftData query = new BICSwiftData(bicCode, bicBranch);
			return (List) BackOfficeCache.getSwiftBICData(query);
		} catch(Exception ex) {
		
			if (commonUTIL.isEmpty(bicCode))
				bicCode = "";
			if (commonUTIL.isEmpty(bicBranch))
				bicBranch = "";

			commonUTIL.displayError("SwiftUtil",  "getBicDatas(): retrieving BIC = '" + bicCode + bicBranch + "' from SWIFT BIC DATA has failed", ex);
		}
		return null;
	}
	public static boolean checkBIC(String bic, List messages) {
		int bicLength = 0;
		if (bic != null)
			bicLength = bic.length();
		if (bicLength != 8 && bicLength != 11 && bicLength !=12) {
			messages.add("Invalid BIC Code '" + bic + "', format should match : \n" +
					"AAAA  BB       CC        (D) (EEE)\n" +
			"Bank  Country  Location  LT  Branch ");
			return false;
		}
		return true;
	}
	public static void getBicCodeAndBranch(String fullBicText, StringBuffer bicCodeBuff, StringBuffer branchBuff) {
		if (commonUTIL.isEmpty(fullBicText))
			return;
		if (fullBicText.length() > 8) {
			bicCodeBuff.append(fullBicText.substring(0, 8));
			if (fullBicText.length() > 11)
				branchBuff.append(fullBicText.substring(9));
			branchBuff.append(fullBicText.substring(8));
		}
		else
			bicCodeBuff.append(fullBicText);
	}

	/**
	 * Returns a split string (n rows of 35 chars max).
	 * Always pass the separate strings if it is something like
	 * name,address,city.
	 * <p>
	 * PLEASE DO NOT PASS them together as one string.
	 * THIS method will not be able to put each of these on the new line
	 * which is required.
	 *
	 * @param initialStr a string to split
	 * @param maxRows    a maximum number of rows to generate
	 *
	 * @return A split string
	 */
	static public String formatLongString(String initialStr, int maxRows) {
		return formatLongString(initialStr, maxRows, EMPTY, 35);
	}

	
	static public String formatLongString(String initialStr, int maxRows, String newLineStart, int maxLineLen) {
		return formatLongString(initialStr, maxRows, newLineStart, maxLineLen, null);
	}

	/**
	 * Returns a split string (n rows of 35 chars max).
	 * Always pass the separate strings if it is something like
	 * name,address,city.
	 * <p>
	 * PLEASE DO NOT PASS them together as one string.
	 * THIS method will not be able to put each of these on the new line
	 * which is required.
	 *
	 * @param initialStr a string to split
	 * @param maxRows    a maximum number of rows to generate
	 * @param maxLineLen a maximum number of characters by row
	 *
	 * @return A split string
	 */
	static public String formatLongString(String initialStr, int maxRows, int maxLineLen) {
		return formatLongString(initialStr, maxRows, EMPTY, maxLineLen, null);
	}

	static public String formatLongString(String initialStr, int maxRows, int maxLineLen, int[] remainingLen) {
		return formatLongString(initialStr, maxRows, EMPTY, maxLineLen, remainingLen);
	}

	static public String formatLongString(String initialStr, int maxRows, String newLineStart, int maxLineLen, int[] remainingLen) {

		if (initialStr == null) return null;

		// strip leading and trailing white space
		initialStr = initialStr.trim();

		// don't process empty string
		if (commonUTIL.isEmpty(initialStr)) {
			return initialStr;
		}

		//removing '\n' is done because when you get text from
		// JTextArea you have '\n' as a new line character
		//Remove that and add SwiftMessage.END_OF_LINE so that you can
		//parse the string easily
		StringBuffer buf = new StringBuffer(initialStr);
		StringBuffer modified = new StringBuffer(initialStr);
		for (int i = 0; i < buf.length(); i++) {
			char c = buf.charAt(i);
			char c1 = '~';
			if (i < buf.length() - 1) {
				c1 = buf.charAt(i + 1);
			}
			if (c == '\r' && c1 == '\n') {
				modified = buf.deleteCharAt(i);
				modified = buf.deleteCharAt(i);
				modified = buf.insert(i, SwiftMessage.ENDOFLINE);
				i += SwiftMessage.ENDOFLINE.length();
			}
			else if (c == '\n') {
				modified = buf.deleteCharAt(i);
				modified = buf.insert(i, SwiftMessage.ENDOFLINE);
				i += SwiftMessage.ENDOFLINE.length();
			}
			else if (c == '\r') {
				modified = buf.deleteCharAt(i);
				modified = buf.insert(i, SwiftMessage.ENDOFLINE);
				i += SwiftMessage.ENDOFLINE.length();
			}
		}
		initialStr = modified.toString();
		//removing some special characters.
		// should remove all occurrences of "%"
		/*
        int indexOfPercent = initialStr.indexOf('%');

        if (indexOfPercent > -1) {
            // We filter '%' characters

            int strlen = initialStr.length();

            int maxCapacity = strlen + 1024;
            char tmp[] = new char[maxCapacity];

            initialStr.getChars(0, strlen, tmp, 0);

            int j = 0;
            for (int i = 0; i < strlen; i++) {
                if (tmp[i] == '%') {
                    if (j + 3 > maxCapacity) {
			Log.error(LOG_CATEGORY, new Throwable("formatLongString.String too long"));
                        break;
                    }
                    tmp[j] = 'P';
                    tmp[j + 1] = 'C';
                    tmp[j + 2] = 'T';
                    j += 3;
                }
                else
                    j++;
            }
            initialStr = new String(tmp, 0, j);
        }
		 */
		initialStr = commonUTIL.replaceString(initialStr, "%", "PCT");

		// now split the string into lines of valid characters

		String workStr = initialStr;
		String ret = "";
		int lines = 0;
		while (lines < maxRows) {

			// leading or trailing whitespace is not allowed
			workStr = workStr.trim();

			// At this point we can assume that workStr neither
			// starts nor ends with a space or a SwiftMessage.END_OF_LINE
			int remainingLength = workStr.length();
			String lineStr = workStr;

			if (remainingLength == 0)
				break;

			
				commonUTIL.display("SwiftUtil","formatLongString.lineStr=>" + lineStr + "<");
				commonUTIL.display("SwiftUtil","formatLongString.workStr=>" + workStr + "<");
				commonUTIL.display("SwiftUtil","formatLongString.ret=>" + ret + "<");
				commonUTIL.display("SwiftUtil","formatLongString.num lines=>" + lines + "<");
			

			// check to see if line contains SwiftMessage.END_OF_LINE
			// if it does then attempt to split the line there
			int isNewLine = lineStr.indexOf(SwiftMessage.ENDOFLINE, 0);
			if (isNewLine > -1) {

				// END_OF_LINE found.
				lineStr = lineStr.substring(0, isNewLine);
				
					commonUTIL.display("SwiftUtil","formatLongString.lineStr after split at EOL=>" + lineStr + "<");
			}

			// if the line is longer than maxLineLen then break it at the first
			// possible opportunity, i.e. at the last occurrence of a sequence
			// of blanks
			int maxCurrentLineLen = maxLineLen;
			String currentLineStart = EMPTY;
			if (lines > 0 && newLineStart != null) {
				maxCurrentLineLen -= newLineStart.length();
				currentLineStart = newLineStart;
			}
			if (lineStr.length() > maxCurrentLineLen) {
				int lineBreak = lineStr.lastIndexOf(' ', maxCurrentLineLen);
				if (lineBreak == -1) {
					// no opportunity to break line so force line length to maxLineLen
					lineBreak = maxCurrentLineLen;
				}
				
					commonUTIL.display("SwiftUtil","formatLongString.line break= " + lineBreak + "<");
				lineStr = lineStr.substring(0, lineBreak);
			}

			// line is ok so add it to the return string and adjust remaining
			// characters
			lineStr = lineStr.trim();
			ret += currentLineStart + lineStr + SwiftMessage.ENDOFLINE;
			workStr = workStr.substring(lineStr.length(), remainingLength);
			lines++;
		}
		// We store the remaining Length if the
		// caller is interested by this info
		if (remainingLen != null)
			remainingLen[0] = workStr.length();

		return ret.trim();
	}
	/**
	 * Return the Swift Code  of a Party.
	 *
	 * The Swift Code is used to populate the party
	 * fiels with option A.
	 *
	 * @param leId        Legal Entity for which to format the Address.
	 * @param role        The role of the Legal Entity.
	 * @param contactType The ContactType to be used.
	 * @param productType The product Type to be used.
	 * @param poId        Processing Org from which the contact is seen.
	 * @param ds          A DataServer connection.
	 *
	 * @return a String, the Swift Address. If the Swift Address is
	 * not defined or ContactType, the code UNKNOWN is returned.
	 */
	

	/**
	 * Return the Swift Code  of a Manual Party SDI.
	 *
	 * The Swift Code is used to populate the party
	 * fiels with option A.
	 *
	 * If the Manual Party SDI has a Swift Code defined in the
	 * Manual Party it returns it first.
	 * Then if the ManualParty is not defined in the sytem as
	 * as a Legal Entity it returns UNKNOWN.
	 * Or if no contact type is defined it returns Unknown.
	 *
	 * @param msd         ManualPartySDI for which to format the Address.
	 * @param role        The role of the Legal Entity.
	 * @param contactType The ContactType to be used.
	 * @param productType The product Type to be used.
	 * @param poId        Processing Org from which the contact is seen.
	 * @param ds          A DataServer connection.
	 *
	 * @return a String, the Swift Address. If the Swift Address is
	 * not defined, the code UNKNOWN is returned.
	 */
	static public String getSwiftCode(ManualProcessSDI msd,
			String role,
			String contactType,
			String productType,
			int poId,
			Trade trade,
			Transfer transfer,
		     Message message,
			Connection ds, Object dbCon) {

		return getAddressCode(msd, role, contactType, productType,
				poId,"SWIFT", trade,
				transfer, message, ds, dbCon);
	}

	/**
	 * Return the Address Code  of a Manual Party SDI.
	 *
	 * The  Code is used to populate the party
	 * fiels with option A.
	 *
	 * If the Manual Party SDI has an Address Code defined in the
	 * Manual Party it returns it first.
	 * Then if the ManualParty is not defined in the sytem as
	 * as a Legal Entity it returns UNKNOWN.
	 * Or if no contact type is defined it returns Unknown.
	 *
	 * @param msd         ManualPartySDI for which to format the Address.
	 * @param role        The role of the Legal Entity.
	 * @param contactType The ContactType to be used.
	 * @param productType The product Type to be used.
	 * @param poId        Processing Org from which the contact is seen.
	 * @param addressType the Type of address
	 * @param ds          A DataServer connection.
	 *
	 * @return a String, the Swift Address. If the Swift Address is
	 * not defined, the code UNKNOWN is returned.
	 */
	static public String getAddressCode(ManualProcessSDI msd,
			String role,
			String contactType,
			String productType,
			int poId,
			String addressType,
			Trade trade,
			Transfer transfer,
			Message message,
			Connection ds, Object dbCon) {

		String swiftCode = UNKNOWN;
		if (msd == null) return swiftCode;
		if (!commonUTIL.isEmpty(msd.getCode())) {
			if (msd.getCode().equals(addressType)) {
				if (!commonUTIL.isEmpty(msd.getCodeValue()))
					return msd.getCodeValue().trim();
			}
		}
		int leId = msd.getCpId();

		if (leId == 0) return swiftCode;
		if (contactType == null) return swiftCode;
		return getAddressCode(leId, role, contactType, productType,
				poId, addressType, trade, transfer, message, ds, dbCon);
	}
	
	
	private static EntityInfo getCustomInfoEntity(String entityInfo) {
		 String classname =entityInfo;
		 EntityInfo _customInfo = null;
	        try {
	          
	               Class class1 = ClassInstantiateUtil.getClass(classname,
	    					true);
	               _customInfo =  (EntityInfo) class1.newInstance();
	        }
	        catch(Exception e) {
	        	commonUTIL.displayError("SwiftUtil", "getBOHandler + Missing handler for Product  W" + classname , e);
	        }
	        return _customInfo;
	}
	/**
	 * Retrieves and formats a 56 Swift field.  The 56 SWIFT field
	 * represents the intermediary for the ProcessingOrg or CounterParty
	 * SDI.
	 *
	 * The function is able to handle Manual or Regular Settlement
	 * Instructions.
	 *
	 * @param payOrReceive Transaction side (PAY or RECEIVE)
	 * @param trade        Trade. May be NULL.
	 * @param transfer     a Transfer. May be NULL.
	 * @param fieldName    The Field Long Name description
	 * @param poB          Use ProcessingOrg SDI else CounterPartySDI.
	 * @param dsCon        A DataServer Connection.
	 *
	 * @return a SwiftFieldMessage
	 */
	public static SwiftFieldMessage getTAG56(String payOrReceive,
			Trade trade,
			Transfer transfer,
			String fieldName,
			boolean poB,
			Message message,
			Connection dsCon) {

	//	TradeTransferRule rule = getTradeTransferRule(trade, transfer, payOrReceive, dsCon,message.getSettleDate());
		return null;//getTAG56(rule, trade, transfer, fieldName, poB, message, dsCon);
	}
	/**
	 * Retrieves and formats a 56 Swift field.  The 56 SWIFT field
	 * represents the intermediary for the ProcessingOrg or CounterParty
	 * SDI.
	 *
	 * The function is able to handle Manual or Regular Settlement
	 * Instructions.
	 *
	 * @param rule         a TradeTransferRule rule
	 * @param trade        Trade. May be NULL.
	 * @param transfer     a Transfer. May be NULL.
	 * @param fieldName    The Field Long Name description
	 * @param poB          Use ProcessingOrg SDI else CounterPartySDI.
	 * @param dsCon        A DataServer Connection.
	 *
	 * @return a SwiftFieldMessage
	 */
	public static SwiftFieldMessage getTAG56(ProductTransferRule prule,
			Trade trade,
			Transfer transfer,
			String fieldName,
			boolean poB,
			Message message,
			Vector<TransferRule> rules,
			Connection dsCon) {

	/*	if (prule == null) {
			return null;//formatSettlementInstructionNoRule(false,TAG_56,fieldName);
		}

		String nettingType = getNettingType(rule, transfer, poB, dsCon);
		if (nettingType != null && !nettingType.equals("CLS"))
			return null;

		if (!checkSdiStatus(rule, poB))
			return null;

		SDI poSdi = getPoSDI(rule, dsCon, null);
		SDI cptySdi = getCptySDI(rule, dsCon, null);
		SDI sdi = null;
		if (poB)
			sdi = poSdi;
		else
			sdi = cptySdi;
		if (sdi == null) return null;

		boolean isDirectRelationship = false;
		if (cptySdi instanceof SettleDeliveryInstruction)
			isDirectRelationship = ((SettleDeliveryInstruction) cptySdi).getDirectRelationship();

		if (isDirectRelationship)
			return null;

		// BZ 23564
		// Enhance Template to take into account cpty having its
		// account at another ctpy having its account at PO
		if (!poB && sdi.getIntermediary() != null) {
			if (poSdi.getBeneficiary().getPartyId() ==
				sdi.getIntermediary().getPartyId())
				return null;
		}

		String option = null;
		String value = null;
		Sdi intermediarySDI = sdi.getIntermediary();
		boolean useFullRoute = false;
		PartySDI account = null;
		if (intermediarySDI == null || intermediarySDI.getPartyId() == 0) {
			intermediarySDI = getPartyFromFullRoute(sdi,rule.getFullRoute(),1,false);
			if (intermediarySDI != null)
				account = getPartyFromFullRoute(sdi,rule.getFullRoute(),2,false);
			useFullRoute = true;

			if (intermediarySDI == null) return null;
		}
		ValueTag tagValue = getTagValue(intermediarySDI,
				trade,
				transfer,
				message,
				dsCon,
				null);
		option = tagValue.getOption();
		String deliveryAgent = tagValue.getValue();

		Sdi intermediary2SDI = sdi.getIntermediary2();
		if (intermediary2SDI != null)
			value = formatAccount(intermediary2SDI.getAccountID());
		if (useFullRoute && account != null) {
			value = formatAccount(account.getPartyAccountName());
		}
		value = (value == null) ? deliveryAgent :
			value + SwiftMessage.ENDOFLINE + deliveryAgent;
		value = addSortCode(value, TAG_56, sdi,tagValue);

		return createField(TAG_56, option, false, value, fieldName,intermediarySDI); */
		return null;
	}
	/**
	 * Retrieves and formats a 57 Swift field.  The 57 SWIFT field
	 * represents the Receiving Agent for the ProcessingOrg or
	 * CounterParty SDI.
	 *
	 * The function is able to handle Manual or Regular Settlement
	 * Instructions.
	 *
	 * @param trade        Trade. May be NULL.
	 * @param transfer     a Transfer. May be NULL.
	 * @param fieldName    The Field Long Name description
	 * @param poB          Use ProcessingOrg SDI else CounterPartySDI.
	 * @param dsCon        A DataServer Connection.
	 *
	 * @return a SwiftFieldMessage
	 */
	public static SwiftFieldMessage getTAG57(ProductTransferRule rule,Trade trade,
			Transfer transfer,
			String fieldName,
			boolean poB,
			Message message,Vector<TransferRule> rules,
			Connection dsCon)
	throws Exception
	{
		return getTAG57(rule,getPayRec(trade, poB), trade, transfer, fieldName, poB, message,rules, dsCon);
	}
	public static SwiftFieldMessage getTAG57(ProductTransferRule rulep,
			String payRec, Trade trade, Transfer transfer, String fieldName,
			boolean poB, Message message, Vector<TransferRule> rules,
			Connection dsCon) {
		// TODO Auto-generated method stub
		String ccy = trade.getCurrency();
		String settle = trade.getDelivertyDate();
	TransferRule rule = SwiftUtil.getTransferRule(rules,ccy,"PRINCIPAL",payRec,settle);
		 return getTAG57(rulep,rule, trade, transfer, fieldName, poB, message, rules,dsCon);
	}

	protected static String getPayRec(Trade trade, boolean usePo) {
		if (trade == null) return null;

		/*Product product = trade.getProduct();

		if (product instanceof FXOption) {
			FXOption fxOption = (FXOption) product;
			if (fxOption.getOptionSide() == FXOption.BUY)
				return BOProductHandler.PAY;
			else
				return BOProductHandler.RECEIVE;
		} */

		if (trade.getQuantity() < 0) {
			if (usePo)
				return "RECEIVE";
			else
				return "PAY";
		}
		else {
			if (usePo)
				return "PAY";
			else
				return "RECEIVE";
		}
	}
	
	
	public static ValueTag getPoTagValue(String type,
			Trade trade,
			Transfer transfer,
			Message message,
			boolean checkSdiNetting,
		    Connection con,ProductTransferRule productTransferRule) {
        return getPoTagValue(type, trade, transfer, message, checkSdiNetting, null, con,productTransferRule);
    }

	public static ValueTag getPoTagValue(String type,
			Trade trade,
			Transfer transfer,
			Message message,
			boolean checkSdiNetting,
			List<TransferRule> transferRules,
			Connection con,ProductTransferRule productTransferRule) {

		if (!type.equals(AGENT) &&
				!type.equals(REMITTER) &&
				!type.equals(BENEFICIARY) &&
				!type.equals(BENEFICIARY_NOACCOUNT) &&
				!type.equals(INTERMEDIARY) &&
				!type.equals(INTERMEDIARY2)) {
			
			commonUTIL.display("SwiftUtil", "getPoTagValue");// -->  Invalid type: " + type)
			
			return new ValueTag();
		}
	/*	TradeTransferRule rule = makeRule(trade, transfer, true, con, message.getSettleDate(), transferRules);
		String nettingType = getNettingType(rule, transfer, true, checkSdiNetting, con);
		if (nettingType != null && !nettingType.equals("CLS")) {
			if (checkSdiNetting)
				return new TagValue(null, "D", NET,0);
			else
				return new TagValue();
		}*/
		
	//	Sdi sdi = get

		return new ValueTag();
	}
	
	 public static String getCptySortCode(String type, Trade trade,Transfer transfer,Message message,Vector transferRules,Connection con,ProductTransferRule productTransferRule) {
		 
								if (!type.equals(AGENT) &&
								!type.equals(REMITTER) &&
								!type.equals(BENEFICIARY) &&
								!type.equals(BENEFICIARY_NOACCOUNT) &&
								!type.equals(INTERMEDIARY) &&
								!type.equals(INTERMEDIARY2)) {
								commonUTIL.display("SwiftUtil", "getCptySortCode Invalid type: " + type);
								return null;
								}
								//TradeTransferRule rule = makeRule(trade, transfer, false, con,message.getSettleDate(), transferRules);
								if(productTransferRule == null)
									return null;

								Sdi sdi = productTransferRule.getSdi("CounterParty");
								if (sdi != null) {
									return getSortCode(sdi, type);
								}
								return null;
	 }
  
	 
	 // this method needs to be implemented properly. 
	 static String getSortCode(Sdi sdi, String type) {
	        String attributeType = (String)_attributeType.get(type);
	        if (attributeType != null) {
	        	
	        } //return sdi.getAttribute(attributeType);
     
     
     return null;
	 }
	 
	 
	 public static ValueTag getCptyTagValue(String type,
				Trade trade,
				Transfer transfer,
				Message message,
				boolean checkSdiNetting,
				boolean useSortCode,
				Vector<TransferRule> transferRules,
				Connection con,ProductTransferRule prule) {

			if (!type.equals(AGENT) &&
			    !type.equals(REMITTER) &&
			    !type.equals(BENEFICIARY) &&
			    !type.equals(BENEFICIARY_NOACCOUNT) &&
			    !type.equals(INTERMEDIARY) &&
			    !type.equals(INTERMEDIARY2)) {
				//Log.error(LOG_CATEGORY, new Throwable("Invalid type: " + type));
				
				commonUTIL.display("SwiftUtil", " getCptyTagValue" + "Invalid type: " + type);
				return new ValueTag();
			}

			TransferRule rule = null;//makeRule(trade, transfer, false, con, message.getSettleDate(), transferRules);
			String nettingType = null;//getNettingType(rule, transfer, false, checkSdiNetting, con);
			if (nettingType != null && !nettingType.equals("CLS")) {
				if (checkSdiNetting)
					return new ValueTag(null, "D", NET,0);
				else
					return new ValueTag();
			}

			Sdi sdi = prule.getSdi("COUNTERPARTY");// getCptySDI(rule, con, null);
			if (sdi == null) return new ValueTag();

			return getTagValue(type, sdi, trade, transfer, message, rule, useSortCode, con);
		}

	 
	/* public TransferRule getTransferRuleonRole(Vector<TransferRule> rules,String role) {
		 if(commonUTIL.isEmpty(rules))
			 return null;
		 TransferRule rule = null;
		 for(int i=0;i<rules.size();i++) {
			 TransferRule r = rules.get(i);
			 if(r.get_)
		 }
		 
	 }*/
	 
	 
	 protected static ValueTag getValueTag(String type,
				Sdi sdi,
				Trade trade,
				Transfer transfer,
				Message message,
				TransferRule rule,
				Connection dsCon) {
			return getTagValue(type, sdi, trade, transfer, message, rule, true, dsCon);
		}
	 
final static String EMPTYSTRING = "";

	/**
	 * Retrieves and formats a 57 Swift field.  The 57 SWIFT field
	 * represents the Receiving Agent for the ProcessingOrg or
	 * CounterParty SDI.
	 *
	 * The function is able to handle Manual or Regular Settlement
	 * Instructions.
	 *
	 * @param payOrReceive payment side
	 * @param trade        Trade. May be NULL.
	 * @param transfer     a Transfer. May be NULL.
	 * @param fieldName    The Field Long Name description
	 * @param poB          Use ProcessingOrg SDI else CounterPartySDI.
	 * @param dsCon        A DataServer Connection.
	 *
	 * @return a SwiftFieldMessage
	 */
	/*public static SwiftFieldMessage getTAG57(ProductTransferRule prule,Vector<TransferRule> rules,String payOrReceive,
			Trade trade,
			Transfer transfer,
			String fieldName,
			boolean poB,
			Message message,
			Connection dsCon)
	throws Exception
	{
		
		return getTAG57(prule,trade,transfer,fieldName,poB,
	}*/

	/**
	 * Retrieves and formats a 57 Swift field.  The 57 SWIFT field
	 * represents the Receiving Agent for the ProcessingOrg or
	 * CounterParty SDI.
	 *
	 * The function is able to handle Manual or Regular Settlement
	 * Instructions.
	 *
	 * @param rule         a TradeTransferRule rule
	 * @param trade        Trade. May be NULL.
	 * @param transfer     a Transfer. May be NULL.
	 * @param fieldName    The Field Long Name description
	 * @param poB          Use ProcessingOrg SDI else CounterPartySDI.
	 * @param dsCon        A DataServer Connection.
	 *
	 * @return a SwiftFieldMessage
	 */
	public static SwiftFieldMessage getTAG57(ProductTransferRule prule,TransferRule rule,
			Trade trade,
			Transfer transfer,
			String fieldName,
			boolean poB,
			Message message,Vector<TransferRule> rules,
			Connection dsCon)
	
	{
		if (rule == null) {
			return formatSettlementInstructionNoRule(true,
					TAG_57,
					fieldName);
		}

		String nettingType = null;//  getNettingType(rule, transfer, poB, true, dsCon);  we need to add this method on netting transfer and rules.
		if (!checkSdiStatus(rule, poB)) {
			if (nettingType == null)
				return createSettlementField(true, TAG_57, fieldName, UNKNOWN);
			else
				return createSettlementField(true, TAG_57, fieldName, NET);
		}

		//Sdi poSdi = getPoSDI(rule, dsCon, null);
		//Sdi cptySdi = getCptySDI(rule, dsCon, null);
		Sdi psdi = prule.getSdi("PO");
		Sdi rsdi = prule.getSdi("CounterParty");
		
		
		Sdi sdi = null;
		if (poB)
			sdi = psdi;
		else
			sdi = rsdi;
		if (sdi == null) return null;

		boolean isDirectRelationship = false;
		if (rsdi instanceof Sdi)
			isDirectRelationship = ((Sdi) rsdi).getDirectRelationship();

		String option = null;
		String value = null;

		Sdi partySDI;
		String acctName = null;
		if (isDirectRelationship) {
			partySDI = sdi.getAgentSdi();
			
		//	Account acc = BackOfficeCache.getAccount(sdi.getAccountID());
			acctName = partySDI.getGlName();
		}
		else {
			partySDI = sdi.getAgentSdi();
			//Account acc = BackOfficeCache.getAccount(partySDI.getAccountID());
			acctName = partySDI.getGlName();//.getAccountName();
		}

		// Enhance Template to take into account cpty having its
		// account at another ctpy having its account at PO
		if (!poB && sdi.getIntermediary() != null) {
			if ((psdi.getBeneficiary()).getCpId() ==
				(sdi.getIntermediary()).getCpId()) {
				partySDI = sdi.getIntermediary();
				//Account acc = BackOfficeCache.getAccount(sdi.getIntermediary().getAccountID());
				acctName =partySDI.getGlName();
			}
		}

		ValueTag tagValue = getTagValue(partySDI,
				trade,
				transfer,
				message,
				dsCon,
				null);
		option = tagValue.getOption();
		String deliveryAgent = tagValue.getValue();

		if (deliveryAgent == null)
			commonUTIL.display("SWiftUtil","TAG57 " + rule.get_payReceive() + ": check long name of agent " +  partySDI.getCpId());

		value = formatAccount(acctName);
		value = (value == null) ? deliveryAgent :
			value + SwiftMessage.ENDOFLINE + deliveryAgent;
		value = addSortCode(value, TAG_57, sdi,tagValue);

		if (nettingType != null && !nettingType.equals("CLS")) {
			/**
			 * SWIFT Doc is not too clear on this, but if we can format
			 * with 'A' option then we should never use 'D' option.  However,
			 * if we do not have an 'A' option, then 'D:NET' is used, typically
			 * to ensure no automatic matching.
			 * BZ 13981
			 */
			boolean useSwiftNetting = false; // Defaults.getBooleanProperty(Defaults.SWIFT_SDI_NETTING, false); we have add this later.
			if (useSwiftNetting || (option != null && option.equals("D"))) {
				return createSettlementField(true, TAG_57, fieldName, NET);
			}
		}

		return createField(TAG_57, option, true, value, fieldName,partySDI);
	}
	
	protected static boolean checkSdiStatus(TransferRule rule,
			boolean poB) {

		if (poB)
			return !rule.getProcessingOrgSDStatus().equals(TBA);
		else
			return !rule.getCounterPartySDStatus().equals(TBA);
	}
	public static SwiftFieldMessage formatSettlementInstructionNoRule(boolean mandatoryB,
			String tag,
			String fieldName) {
		if (!mandatoryB) return null;
		return createSettlementField(mandatoryB, tag, fieldName, UNKNOWN);
	}

	public static SwiftFieldMessage createSettlementField(boolean mandatoryB,
			String tag,
			String fieldName,
			String value) {

		SwiftFieldMessage field = new SwiftFieldMessage();
		if (mandatoryB)
			field.setStatus((byte) 'M');
		else
			field.setStatus((byte) 'O');
		field.setName(fieldName);
		field.setTAG(":" + tag + "D:");
		field.setValue(value);
		return field;
	}
	
	
	
	private static TransferRule getTransferRule(Vector<TransferRule> rules,String ccy, 	String type,
	String payOrReceive,String settleDate) {
		TransferRule ruler = null;
		if(commonUTIL.isEmpty(rules)) 
			return null;
		for(int i=0;i<rules.size();i++) {
			TransferRule rule = rules.get(i);
			String settleDates =  rule.get_settleDate();
			String settleD = commonUTIL.getOnlyDate(settleDate);
			if((rule.get_transferType().equalsIgnoreCase(type)) && (rule.get_payReceive().equalsIgnoreCase(payOrReceive)) && (rule.get_settlementCurrency().equalsIgnoreCase(ccy)) && (settleDates.trim().equalsIgnoreCase(settleD.trim()))) {
				ruler = rule;
				break;
			}
		}
		
		return ruler;
	}
	// this method need to understand properly it return agent id from sdi rules.
	// but understand how calypso work in PSSetid 
	
	static public int getPSETId(Trade trade,
			Transfer transfer,
			String settleDate,
			Connection dsCon) {

	//	SwiftAgent sa = getBridgeAgent(trade,
		//		transfer,
		//		settleDate,
		//		dsCon);

		//if (sa == null)
			return 0;
	//	return sa.getAgentId();
	}

	
	/**
	 * SDI status keyword indicating that the Settlement Instructions
	 * have to be assigned.
	 */
	static final public String TBA = "TBA";

	public static String getDataSourceScheme(Trade trade, Transfer transfer,
			String settleDate) {
		if (transfer == null) return null;
	/*	int psetId = getPSETId(trade,transfer,settleDate);  // return agentid;
		ReferenceDataCache.getAttribute(poid, cpid, attributeType, cpRole)
		if (psetId == 0) return null;
		LegalEntityAttribute lea =
				ReferenceDataCache.getLegalEntityAttribute(ds,
						transfer.getInternalLegalEntityId(),
						psetId,
						"AGENT",
						LegalEntityAttribute.DATA_SOURCE_SCHEME);
		if (lea != null) {
			return lea.getAttributeValue().trim();*/
			return null;
}
	
	/**
	 * Returns if a Cover Message is required.
	 *
	 * If a Cover Message is required, another Payment message
	 * will be generated.
	 *
	 * It returns true if the Intermediary or the Second Intermediary
	 * is set with the Message Flag on the Sdi set to true.
	 * It returns true if the CounterParty Message to Agent is set to true.
	 *
	 * @param poSdi ProcessingOrg Sdi
	 *
	 * @param cptySdi CounterParty Sdi
	 *
	 * @return true if a Cover Message is required.
	 *
	 */
	static public boolean isCoverMessageTo(Sdi poSdi,
			Sdi cptySdi,
			int cptyType,
			Vector<TransferRule> fullRoute) {
		Sdi partyAgent = null;
		if (cptyType == 2) {
		//	party = cptySdi.getIntermediary2();
		//	if (party == null || party.getPartyId() == 0) {
		//		party = getPartyFromFullRoute(cptySdi,fullRoute,2,false);
		//	}
		}else if (cptyType == 1) {
			//party = cptySdi.getIntermediary();
			//if (party == null || party.getPartyId() == 0) {
				//party = getPartyFromFullRoute(cptySdi,fullRoute,1,false);
		//}

		}
		else if (cptyType == 0) {
			partyAgent = cptySdi.getAgentSdi();
		} 
		if (partyAgent == null) return false;
		if (partyAgent.getId() > 0 && partyAgent.getMessageToParty()) {
			if (cptyType == 0) {
				if (cptySdi.getIntermediary() != null) {
					Sdi myParty = cptySdi.getIntermediary();
					if (myParty != null && myParty.getId() > 0 && myParty.getMessageToParty()) {
						return false;
					}
				}
			}
			
		}
		
	
	return false;	
	}
	
	static public boolean isCoverMessageTo(Message message,Sdi poSdi,
			Sdi cptySdi,
			int cptyType,
			Vector<TransferRule> fullRoute) {
		if (message.getAttribute("_RFSMS_") == null)
			return isCoverMessageTo(poSdi,cptySdi,cptyType,fullRoute);
		return false;
		
	}
	
	/**
	 * Returns <code>true</code> if a Cover Message is required,
	 * or <code>false</code> otherwise.
	 *
	 * If a Cover Message is required, another Payment message
	 * will be generated.
	 *
	 * @param rule     a TradeTransferRule rule
	 * @param ds       a DSConnection
	 * @param cptyType a counterparty type
	 */
	static public boolean isCoverMessageTo(Message message,
			ProductTransferRule prule,
			Connection ds,
			Object dbCon,
			int cptyType) {
		Sdi poSdi = prule.getSdi("PO");
		Sdi cptySdi = prule.getSdi("CounterParty");
		Vector fullRoute = prule.getSdis();
		return isCoverMessageTo(message,poSdi,cptySdi,cptyType,fullRoute);
	}
	
	static public boolean isCoverMessage(Message message,
			ProductTransferRule rule,
			Connection dsCon) {
		return isCoverMessage(message, rule, dsCon, null);
	}

	private static boolean isCoverMessage(Message message,
			ProductTransferRule rule, Connection dsCon, Object object) {
		
		// TODO Auto-generated method stub
		Sdi poSdi = rule.getSdi("PO");
		Sdi cptySdi = rule.getSdi("CounterParty");
		Vector fullRoute = rule.getSdis();
		return isCoverMessage(message, poSdi, cptySdi,fullRoute);
		
	}

	private static boolean isCoverMessage(Message message, Sdi poSdi,
			Sdi cptySdi, Vector fullRoute) {
		// TODO Auto-generated method stub
		boolean ignoreMsgFlag = false;
		if (message.getAttribute("_RFSMS_") != null) {
			ignoreMsgFlag = true;
			//Check RFSMS - if 2 RFSMS
			//then coverMessage Required
			String s = message.getAttribute("_RFSMS_");
			if (s != null) {
				Vector v  = commonUTIL.string2Vector(s);
				if (v == null || v.size() < 2)
					return false;
			}
			
		}
		
		if (!isCoverMessageRequired(message,poSdi, cptySdi,fullRoute))
			return false;


		Sdi intermediary2 = cptySdi.getIntermediary2();
		Sdi intermediary = cptySdi.getIntermediary();
		Sdi agent = cptySdi.getAgentSdi();


		return false;
	}

	private static boolean isCoverMessageRequired(Message message, Sdi poSdi,
			Sdi cptySdi, Vector fullRoute) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static String getRate(double rate ) {

		String[] noSplit = String.valueOf(rate).split("\\.");
		String intPart = noSplit[0];
		String decimalPart = noSplit[1];
	
		
		String  rateDec = "";
		String  rateInt = "";
		
		int addInterger = 4;
		
		if (decimalPart.length() > 0) {
			if (!decimalPart.equals("0")) {
				addInterger = addInterger - decimalPart.length();
			} else {
				decimalPart = "";
			}
		}
		
		if (addInterger == 0 || addInterger < 1) {
			rateDec = decimalPart.substring(decimalPart.length() -4);
			intPart = "";
		} else {
			
			rateDec = decimalPart;
			int rateIntLen = intPart.length();
			
			int noofZeros =  addInterger - rateIntLen;
			
			if (noofZeros > 0) {
				
				for(int k =0; k < noofZeros; k++) {
					intPart	 =  "0" + intPart;
				}
			} else {
				intPart = intPart.substring(rateIntLen - addInterger);
			}
						
			
		}
		
		return (intPart+rateDec).trim();
	}
}
