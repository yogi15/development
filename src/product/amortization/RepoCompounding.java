package product.amortization;

import java.util.Date;
import java.util.Vector;

import util.common.CountDay;
import util.common.DateU;
import beans.Amortization;
import beans.Coupon;
import beans.Flows;
import beans.Product;
import beans.Trade;

public class RepoCompounding extends MMAmortization {

	Trade trade;
	Coupon coupon;
	CountDay daycount;
	
	double interest = 0.0;
	double couponAmount = 0.0;
	double tradeAmount = 0.0;
	double paymentAmount = 0.0;
	
	Date cmpdStartDate;
	Date cmpdEndDate;
	
	private int flowNo = 1;

	public RepoCompounding(Trade trade, Coupon coupon) {

		this.trade = trade;
		this.coupon = coupon;

		tradeAmount = trade.getTradeAmount();
		daycount = CountDay.valueOf(coupon.getDayCount());

	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	
	public Date getCmpdStartDate() {
		return cmpdStartDate;
	}

	public void setCmpdStartDate(Date cmpdStartDate) {
		this.cmpdStartDate = cmpdStartDate;
	}

	public Date getCmpdEndDate() {
		return cmpdEndDate;
	}

	public void setCmpdEndDate(Date cmpdEndDate) {
		this.cmpdEndDate = cmpdEndDate;
	}
	
	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	@Override
	public double calculatePayment() {

		//calculateInterest();

		return 0;
		
	}

	@Override
	public double calculateFixedInterest() {

		return 0;

	}

	public double calculateInterest() {

		double dayCountFactor = daycount.getDayCountFactor(
				DateU.valueOf(cmpdStartDate),
				DateU.valueOf(cmpdEndDate));

		interest = (double) Math.round(tradeAmount * (coupon.getFixedRate() / 100) * dayCountFactor *100)/100;
		
		paymentAmount = paymentAmount + interest;
		
		setInterest(interest);
		
		return interest;
	}
	
	public double calculateCompoundAmount() {

		tradeAmount = tradeAmount + getInterest();

		return tradeAmount;

	}
	
}
