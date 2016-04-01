package beans;

import java.io.Serializable;

public class ProductFXOption extends Product implements Serializable {
int product_id = 0;
 /**
 * @return the product_id
 */
public int getProduct_id() {
	return product_id;
}
/**
 * @param product_id the product_id to set
 */
public void setProduct_id(int product_id) {
	this.product_id = product_id;
}

/**
 * @return the currencyBase
 */
public String getCurrencyBase() {
	return CurrencyBase;
}
/**
 * @param currencyBase the currencyBase to set
 */
public void setCurrencyBase(String currencyBase) {
	CurrencyBase = currencyBase;
}
/**
 * @return the currencyQuote
 */
public String getCurrencyQuote() {
	return CurrencyQuote;
}
/**
 * @param currencyQuote the currencyQuote to set
 */
public void setCurrencyQuote(String currencyQuote) {
	CurrencyQuote = currencyQuote;
}
/**
 * @return the optionStyle
 */
public String getOptionStyle() {
	return OptionStyle;
}
/**
 * @param optionStyle the optionStyle to set
 */
public void setOptionStyle(String optionStyle) {
	OptionStyle = optionStyle;
}
/**
 * @return the exericseType
 */
public String getExericseType() {
	return ExericseType;
}
/**
 * @param exericseType the exericseType to set
 */
public void setExericseType(String exericseType) {
	ExericseType = exericseType;
}
/**
 * @return the optionType
 */
public String getOptionType() {
	return OptionType;
}
/**
 * @param optionType the optionType to set
 */
public void setOptionType(String optionType) {
	OptionType = optionType;
}
/**
 * @return the optionStrike
 */
public double getOptionStrike() {
	return OptionStrike;
}
/**
 * @param optionStrike the optionStrike to set
 */
public void setOptionStrike(double optionStrike) {
	OptionStrike = optionStrike;
}
/**
 * @return the expiryDate
 */
public String getExpiryDate() {
	return ExpiryDate;
}
/**
 * @param expiryDate the expiryDate to set
 */
public void setExpiryDate(String expiryDate) {
	ExpiryDate = expiryDate;
}
/**
 * @return the primaryAmount
 */
public double getPrimaryAmount() {
	return PrimaryAmount;
}
/**
 * @param primaryAmount the primaryAmount to set
 */
public void setPrimaryAmount(double primaryAmount) {
	PrimaryAmount = primaryAmount;
}
/**
 * @return the quotingAmount
 */
public double getQuotingAmount() {
	return QuotingAmount;
}
/**
 * @param quotingAmount the quotingAmount to set
 */
public void setQuotingAmount(double quotingAmount) {
	QuotingAmount = quotingAmount;
}
/**
 * @return the settleDate
 */
public String getSettleDate() {
	return SettleDate;
}
/**
 * @param settleDate the settleDate to set
 */
public void setSettleDate(String settleDate) {
	SettleDate = settleDate;
}
/**
 * @return the firstExDate
 */
public String getFirstExDate() {
	return FirstExDate;
}
/**
 * @param firstExDate the firstExDate to set
 */
public void setFirstExDate(String firstExDate) {
	FirstExDate = firstExDate;
}
/**
 * @return the holidays
 */
public String getHolidays() {
	return Holidays;
}
/**
 * @param holidays the holidays to set
 */
public void setHolidays(String holidays) {
	Holidays = holidays;
}
/**
 * @return the delHolidays
 */
public String getDelHolidays() {
	return DelHolidays;
}
/**
 * @param delHolidays the delHolidays to set
 */
public void setDelHolidays(String delHolidays) {
	DelHolidays = delHolidays;
}
/**
 * @return the optionSide
 */
public String getOptionSide() {
	return OptionSide;
}
/**
 * @param optionSide the optionSide to set
 */
public void setOptionSide(String optionSide) {
	OptionSide = optionSide;
}
/**
 * @return the settlementType
 */
public String getSettlementType() {
	return SettlementType;
}
/**
 * @param settlementType the settlementType to set
 */
public void setSettlementType(String settlementType) {
	SettlementType = settlementType;
}
/**
 * @return the autoExercised
 */
public double getAutoExercised() {
	return AutoExercised;
}
/**
 * @param autoExercised the autoExercised to set
 */
public void setAutoExercised(double autoExercised) {
	AutoExercised = autoExercised;
}
/**
 * @return the expiryTime
 */
public double getExpiryTime() {
	return ExpiryTime;
}
/**
 * @param expiryTime the expiryTime to set
 */
public void setExpiryTime(double expiryTime) {
	ExpiryTime = expiryTime;
}
/**
 * @return the expriy_TimeZone
 */
public String getExpriy_TimeZone() {
	return Expriy_TimeZone;
}
/**
 * @param expriy_TimeZone the expriy_TimeZone to set
 */
public void setExpriy_TimeZone(String expriy_TimeZone) {
	Expriy_TimeZone = expriy_TimeZone;
}
/**
 * @return the settle_Currency
 */
public String getSettle_Currency() {
	return Settle_Currency;
}
/**
 * @param settle_Currency the settle_Currency to set
 */
public void setSettle_Currency(String settle_Currency) {
	Settle_Currency = settle_Currency;
}
/**
 * @return the quanto_Factor
 */
public double getQuanto_Factor() {
	return Quanto_Factor;
}
/**
 * @param quanto_Factor the quanto_Factor to set
 */
public void setQuanto_Factor(double quanto_Factor) {
	Quanto_Factor = quanto_Factor;
}
/**
 * @return the quanto_Currency_Pair
 */
public String getQuanto_Currency_Pair() {
	return Quanto_Currency_Pair;
}
/**
 * @param quanto_Currency_Pair the quanto_Currency_Pair to set
 */
public void setQuanto_Currency_Pair(String quanto_Currency_Pair) {
	Quanto_Currency_Pair = quanto_Currency_Pair;
}
/**
 * @return the exercised_DATE
 */
public String getExercised_DATE() {
	return Exercised_DATE;
}
/**
 * @param exercised_DATE the exercised_DATE to set
 */
public void setExercised_DATE(String exercised_DATE) {
	Exercised_DATE = exercised_DATE;
}
/**
 * @return the fxreset
 */
public double getFxreset() {
	return Fxreset;
}
/**
 * @param fxreset the fxreset to set
 */
public void setFxreset(double fxreset) {
	Fxreset = fxreset;
}
/**
 * @return the spotDate
 */
public String getSpotDate() {
	return SpotDate;
}
/**
 * @param spotDate the spotDate to set
 */
public void setSpotDate(String spotDate) {
	SpotDate = spotDate;
}
/**
 * @return the opt_Cal_Offset
 */
public double getOpt_Cal_Offset() {
	return Opt_Cal_Offset;
}
/**
 * @param opt_Cal_Offset the opt_Cal_Offset to set
 */
public void setOpt_Cal_Offset(double opt_Cal_Offset) {
	Opt_Cal_Offset = opt_Cal_Offset;
}
/**
 * @return the opt_Cal_Bus_B
 */
public double getOpt_Cal_Bus_B() {
	return Opt_Cal_Bus_B;
}
/**
 * @param opt_Cal_Bus_B the opt_Cal_Bus_B to set
 */
public void setOpt_Cal_Bus_B(double opt_Cal_Bus_B) {
	Opt_Cal_Bus_B = opt_Cal_Bus_B;
}
/**
 * @return the calcOffsetDays_B
 */
public double getCalcOffsetDays_B() {
	return CalcOffsetDays_B;
}
/**
 * @param calcOffsetDays_B the calcOffsetDays_B to set
 */
public void setCalcOffsetDays_B(double calcOffsetDays_B) {
	CalcOffsetDays_B = calcOffsetDays_B;
}
/**
 * @return the reset_Date
 */
public String getReset_Date() {
	return Reset_Date;
}
/**
 * @param reset_Date the reset_Date to set
 */
public void setReset_Date(String reset_Date) {
	Reset_Date = reset_Date;
}
/**
 * @return the compo_fx_source
 */
public double getCompo_fx_source() {
	return Compo_fx_source;
}
/**
 * @param compo_fx_source the compo_fx_source to set
 */
public void setCompo_fx_source(double compo_fx_source) {
	Compo_fx_source = compo_fx_source;
}

String  CurrencyBase = null;
 String   CurrencyQuote = null;
 String    OptionStyle = null;
 String    ExericseType= null;
 String    OptionType  = null;
 double  OptionStrike = 0.0;
 String     ExpiryDate  = null;
 double  PrimaryAmount = 0 ;
 double  QuotingAmount = 0 ;
   String     SettleDate  = null;
   String    FirstExDate = null;
   String     Holidays = null;
   String     DelHolidays = null;
   String    OptionSide = null;
   String     SettlementType = null;
   double  AutoExercised = 0;
   double  ExpiryTime = 0;
   String     Expriy_TimeZone = null;
   String     Settle_Currency = null;
   double   Quanto_Factor= 0;
 String  Quanto_Currency_Pair = null;
   String    Exercised_DATE = null;
   double   Fxreset = 0;
 String  SpotDate = null;
   double Opt_Cal_Offset =0 ;
   double    Opt_Cal_Bus_B = 0;
 double    CalcOffsetDays_B = 0;
String   Reset_Date = null;
  double Compo_fx_source  = 0;


}
