package beans;

import java.io.Serializable;

public class CurrencyPair implements  BaseBean {
	
	String primary_currency;
	String quoting_currency;
	String pairname;
	int quote_factor =0;
	int bp_factor;
	int rounding =0;
	int pair_pos_ref_b=0;
	int spot_days =0;

	
	public String getPrimary_currency() {
		return primary_currency;
	}
	public void setPrimary_currency(String primary_currency) {
		this.primary_currency = primary_currency;
	}
	public String getQuoting_currency() {
		return quoting_currency;
	}
	public void setQuoting_currency(String quoting_currency) {
		this.quoting_currency = quoting_currency;
	}
	
	public int getRounding() {
		return rounding;
	}
	public void setRounding(int rounding) {
		this.rounding = rounding;
	}
	
	public int getSpot_days() {
		return spot_days;
	}
	public void setSpot_days(int spot_days) {
		this.spot_days = spot_days;
	}
	public String getPairName() {
		return pairname;
	}
	public void setPairName(String pairname) {
		this.pairname = pairname;
	
	}
	
	
	
	@Override
	public Object getPropertyValue(String propertyPaneColumnName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		// TODO Auto-generated method stub
		
	}
	

}
