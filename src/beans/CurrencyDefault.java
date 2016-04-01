package beans;

import java.io.Serializable;

public class CurrencyDefault  implements Serializable {
	
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
	int is_precious_metal_b =0;
	int non_deliverable_b =0;
	String dayCount;
	int currencyDecimal= 0;
	
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
	public void setSettlementCutOffTime(String settlementCutOffTime) {
		this.settlementCutOffTime = settlementCutOffTime;
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
}
