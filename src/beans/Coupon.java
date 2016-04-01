package beans;

import java.io.Serializable;

public class Coupon implements Serializable {

	int id =0;
	int productId =0;
	String couponType;
	String tenor;
	String rateIndex = null;
	
	/**
	 * @return the rateIndex
	 */
	public String getRateIndex() {
		return rateIndex;
	}
	/**
	 * @param rateIndex the rateIndex to set
	 */
	public void setRateIndex(String rateIndex) {
		this.rateIndex = rateIndex;
	}
	public String getTenor() {
		return tenor;
	}
	public void setTenor(String tenor) {
		this.tenor = tenor;
	}
	double FixedRate = 0.0;
	String CCY;
	String DayCount;
	String CouponAdjustMentMethod;
	String CouponFrequency;
	String BusinessDayConvention;
	String CouponDate;
	String Rating;
	double Ex_dividend = 0.0;
	int recordDays =0;
	int shutDays =0;
	double AccrualDigits = 0.0;
	double PriceDecimals = 0.0;
	double YieldDecimals= 0.0;
	double NominalDecimals= 0.0;
	String AnnounceDate;
	String AuctionDate;
	String WithholdingTax;
	String ApplyWithholdingTax;
	String WhenIssueBond;
	String TickSize;
	String YieldMethod;
	String QuoteType;
	String Activefrom;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public double getFixedRate() {
		return FixedRate;
	}
	public void setFixedRate(double fixedRate) {
		FixedRate = fixedRate;
	}
	public String getCCY() {
		return CCY;
	}
	public void setCCY(String cCY) {
		CCY = cCY;
	}
	public String getDayCount() {
		return DayCount;
	}
	public void setDayCount(String dayCount) {
		DayCount = dayCount;
	}
	public String getCouponAdjustMentMethod() {
		return CouponAdjustMentMethod;
	}
	public void setCouponAdjustMentMethod(String couponAdjustMentMethod) {
		CouponAdjustMentMethod = couponAdjustMentMethod;
	}
	public String getCouponFrequency() {
		return CouponFrequency;
	}
	public void setCouponFrequency(String couponFrequency) {
		CouponFrequency = couponFrequency;
	}
	public String getBusinessDayConvention() {
		return BusinessDayConvention;
	}
	public void setBusinessDayConvention(String businessDayConvention) {
		BusinessDayConvention = businessDayConvention;
	}
	public String getCouponDate() {
		return CouponDate;
	}
	public void setCouponDate(String couponDate) {
		CouponDate = couponDate;
	}
	public String getRating() {
		return Rating;
	}
	public void setRating(String rating) {
		Rating = rating;
	}
	public double getEx_dividend() {
		return Ex_dividend;
	}
	public void setEx_dividend(double ex_dividend) {
		Ex_dividend = ex_dividend;
	}
	public int getRecordDays() {
		return recordDays;
	}
	public void setRecordDays(int recordDays) {
		this.recordDays = recordDays;
	}
	public int getShutDays() {
		return shutDays;
	}
	public void setShutDays(int shutDays) {
		this.shutDays = shutDays;
	}
	public double getAccrualDigits() {
		return AccrualDigits;
	}
	public void setAccrualDigits(double accrualDigits) {
		AccrualDigits = accrualDigits;
	}
	public double getPriceDecimals() {
		return PriceDecimals;
	}
	public void setPriceDecimals(double priceDecimals) {
		PriceDecimals = priceDecimals;
	}
	public double getYieldDecimals() {
		return YieldDecimals;
	}
	public void setYieldDecimals(double yieldDecimals) {
		YieldDecimals = yieldDecimals;
	}
	public double getNominalDecimals() {
		return NominalDecimals;
	}
	public void setNominalDecimals(double nominalDecimals) {
		NominalDecimals = nominalDecimals;
	}
	public String getAnnounceDate() {
		return AnnounceDate;
	}
	public void setAnnounceDate(String announceDate) {
		AnnounceDate = announceDate;
	}
	public String getAuctionDate() {
		return AuctionDate;
	}
	public void setAuctionDate(String auctionDate) {
		AuctionDate = auctionDate;
	}
	public String getWithholdingTax() {
		return WithholdingTax;
	}
	public void setWithholdingTax(String withholdingTax) {
		WithholdingTax = withholdingTax;
	}
	public String getApplyWithholdingTax() {
		return ApplyWithholdingTax;
	}
	public void setApplyWithholdingTax(String applyWithholdingTax) {
		ApplyWithholdingTax = applyWithholdingTax;
	}
	public String getWhenIssueBond() {
		return WhenIssueBond;
	}
	public void setWhenIssueBond(String whenIssueBond) {
		WhenIssueBond = whenIssueBond;
	}
	public String getTickSize() {
		return TickSize;
	}
	public void setTickSize(String tickSize) {
		TickSize = tickSize;
	}
	public String getYieldMethod() {
		return YieldMethod;
	}
	public void setYieldMethod(String yieldMethod) {
		YieldMethod = yieldMethod;
	}
	public String getQuoteType() {
		return QuoteType;
	}
	public void setQuoteType(String quoteType) {
		QuoteType = quoteType;
	}
	public String getActivefrom() {
		return Activefrom;
	}
	public void setActivefrom(String activefrom) {
		Activefrom = activefrom;
	}
	 
	
	
	
}
