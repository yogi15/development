package beans;

import java.io.Serializable;
import java.util.Date;


import org.apache.velocity.runtime.directive.Parse;

import apps.window.util.propertyUtil.Selection;
import constants.CounterPartyConstants;
import constants.CurrencyDefaultConstants;
import util.commonUTIL;
import util.common.CDate;



public class CurrencyDefault implements BaseBean {


	private static final long serialVersionUID = -31434619123380538L;

	
	String currency_code;
	double rounding = 0.0;
	String rounding_method;
	String iso_code;
	String country;
	String default_holiday;
	String rate_index_code;
	String default_day_count;
	String groupList;
	int spot_days = 0;
	int defaultTenor = 0;
	String description;
	String timeZone;
	int versionNumber = 0;
	String externalReferences;
	int rateDecimals = 0;
	double warningThreshold = 0.0;
	String settlementCutOffTime;
	String settlementCutOffTimeZone;
	int is_precious_metal_b = 0;
	int non_deliverable_b = 0;
	String dayCount;
	int currencyDecimal = 0;
	String CCIL;
	String CLS;
	String BDC;
	String STATUS;
	AttributeContainer attributeContainer = null;

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public double getRounding() {
		return rounding;
	}

	public void setRounding(double rounding) {
		this.rounding = rounding;
	}

	public String getRounding_method() {
		return rounding_method;
	}

	public void setRounding_method(String rounding_method) {
		this.rounding_method = rounding_method;
	}

	public String getIso_code() {
		return iso_code;
	}

	public void setIso_code(String iso_code) {
		this.iso_code = iso_code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDefault_holiday() {
		return default_holiday;
	}

	public void setDefault_holiday(String default_holiday) {
		this.default_holiday = default_holiday;
	}

	public String getRate_index_code() {
		return rate_index_code;
	}

	public void setRate_index_code(String rate_index_code) {
		this.rate_index_code = rate_index_code;
	}

	public String getDefault_day_count() {
		return default_day_count;
	}

	public void setDefault_day_count(String default_day_count) {
		this.default_day_count = default_day_count;
	}

	public String getGroupList() {
		return groupList;
	}

	public void setGroupList(String groupList) {
		this.groupList = groupList;
	}

	public int getSpot_days() {
		return spot_days;
	}

	public void setSpot_days(int spot_days) {
		this.spot_days = spot_days;
	}

	public int getDefaultTenor() {
		return defaultTenor;
	}

	public void setDefaultTenor(int defaultTenor) {
		this.defaultTenor = defaultTenor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public int getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getExternalReferences() {
		return externalReferences;
	}

	public void setExternalReferences(String externalReferences) {
		this.externalReferences = externalReferences;
	}

	public int getRateDecimals() {
		return rateDecimals;
	}

	public void setRateDecimals(int rateDecimals) {
		this.rateDecimals = rateDecimals;
	}

	public double getWarningThreshold() {
		return warningThreshold;
	}

	public void setWarningThreshold(double warningThreshold) {
		this.warningThreshold = warningThreshold;
	}

	public String getSettlementCutOffTime() {
		return settlementCutOffTime;
	}

	public void setSettlementCutOffTime(String string) {
		this.settlementCutOffTime = string;
	}

	public String getSettlementCutOffTimeZone() {
		return settlementCutOffTimeZone;
	}

	public void setSettlementCutOffTimeZone(String settlementCutOffTimeZone) {
		this.settlementCutOffTimeZone = settlementCutOffTimeZone;
	}

	public int getIs_precious_metal_b() {
		return is_precious_metal_b;
	}

	public void setIs_precious_metal_b(int is_precious_metal_b) {
		this.is_precious_metal_b = is_precious_metal_b;
	}

	public int getNon_deliverable_b() {
		return non_deliverable_b;
	}

	public void setNon_deliverable_b(int non_deliverable_b) {
		this.non_deliverable_b = non_deliverable_b;
	}

	public String getDayCount() {
		return dayCount;
	}

	public void setDayCount(String dayCount) {
		this.dayCount = dayCount;
	}

	public int getCurrencyDecimal() {
		return currencyDecimal;
	}

	public void setCurrencyDecimal(int currencyDecimal) {
		this.currencyDecimal = currencyDecimal;
	}

	public String getCCIL() {
		return CCIL;
	}

	public void setCCIL(String CCIL) {
		this.CCIL = CCIL;
	}

	public String getCLS() {
		return CLS;
	}

	public void setCLS(String CLS) {
		this.CLS = CLS;
	}

	public String getBDC() {
		return BDC;
	}

	public void setBDC(String BDC) {
		this.BDC = BDC;

	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String STATUS) {
		this.STATUS = STATUS;
	}

	public String getAttributeValue(String attributeDataName) {

		String attr1 = getAttributes();
		String attributes[] = attr1.split(";");
		String value = "";

		for (int i = 0; i < attributes.length; i++) {
			String attribute = attributes[i];

			if (attribute.contains(attributeDataName)) {
				value = attribute.substring(attribute.indexOf("=") + 1, attribute.length());
				if (!commonUTIL.isEmpty(value))
					break;
			}
		}

		return value;

	}

	private String getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPropertyValue(String propertyPaneColumnName) {
		Object obj = null;
		/*
		 * if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.
		 * CURRENCY_CODE)) { return obj = getCurrency_code(); }
		 */
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.ROUNDING)) {
			return obj = getRounding();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.ISO_CODE)) {
			return obj = getIso_code();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.COUNTRY)) {
			return obj = getCountry();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.HOLIDAY)) {
			return obj = getDefault_holiday();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.SPOTDAYS)) {
			return obj = getSpot_days();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.RATEDECIMALS)) {
			return obj = getRateDecimals();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.SETTLEMENTCUTOFF)) {
			return obj = getSettlementCutOffTime();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.PRECIOUSMETAL)) {
			return obj = getIs_precious_metal_b();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.NON_DELIVERABLE)) {
			return obj = getNon_deliverable_b();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.CCIL)) {
			return obj = getCCIL();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.CLS)) {
			return obj = getCLS();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.BUSINESSDAYCONVENTON)) {
			return obj = getBDC();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.STATUS)) {
			return obj = getSTATUS();
		}
		return obj;
	}

	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		/*
		 * if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.
		 * CURRENCY_CODE)) { setCurrency_code((String) object); }
		 */

		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.ROUNDING)) {
			Object a = object;
			int v = 0;
			if (a instanceof Integer) {
				v = ((Integer) object).intValue();
				setRounding(v);
			}
			else
			{
				v=Integer.parseInt((String) a);
				setRounding(v);
			}
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.ISO_CODE)) {
			setIso_code((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.COUNTRY)) {
			setCountry((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.HOLIDAY)) {
			setDefault_holiday((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.SPOTDAYS)) {
			Object a = object;
			int v = 0;
			if (a instanceof Integer) {
				v = ((Integer) object).intValue();
				setSpot_days(v);
			}
			else
			{
				v=Integer.parseInt((String) a);
				setSpot_days(v);
			}

			
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.RATEDECIMALS)) {
			
			Object a = object;
			int v = 0;
			if (a instanceof Integer) {
				v = ((Integer) object).intValue();
				setRateDecimals(v);
			}
			else
			{
				v=Integer.parseInt((String) a);
				setRateDecimals(v);
			}
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.SETTLEMENTCUTOFF)) {

			CDate cdate = CDate.valueOf((java.util.Date) object);
			setSettlementCutOffTime((cdate).toSQLString() + " ");
		}

		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.PRECIOUSMETAL)) {
			setIs_precious_metal_b((Integer) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.NON_DELIVERABLE)) {
			setNon_deliverable_b((Integer) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.CCIL)) {
			setCCIL((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.CLS)) {
			setCLS((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.BUSINESSDAYCONVENTON)) {
			setBDC((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(CurrencyDefaultConstants.STATUS)) {
			setSTATUS((String) object);
		}
		/*
		 * public AttributeContainer getAttributeContainer() { return
		 * attributeContainer; } public void
		 * setAttributeContainer(AttributeContainer attributeContainer) {
		 * this.attributeContainer = attributeContainer; }
		 */}

}
