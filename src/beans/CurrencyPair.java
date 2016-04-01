package beans;

import java.io.Serializable;

public class CurrencyPair implements Serializable {
	
	String primary_currency;
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
	public int getQuote_factor() {
		return quote_factor;
	}
	public void setQuote_factor(int quote_factor) {
		this.quote_factor = quote_factor;
	}
	public int getBp_factor() {
		return bp_factor;
	}
	public void setBp_factor(int bp_factor) {
		this.bp_factor = bp_factor;
	}
	public int getRounding() {
		return rounding;
	}
	public void setRounding(int rounding) {
		this.rounding = rounding;
	}
	public int getPair_pos_ref_b() {
		return pair_pos_ref_b;
	}
	public void setPair_pos_ref_b(int pair_pos_ref_b) {
		this.pair_pos_ref_b = pair_pos_ref_b;
	}
	public int getSpot_days() {
		return spot_days;
	}
	public void setSpot_days(int spot_days) {
		this.spot_days = spot_days;
	}
	String quoting_currency;
	int quote_factor =0;
	int bp_factor;
	int rounding =0;
	int pair_pos_ref_b=0;
	int spot_days =0;

}
