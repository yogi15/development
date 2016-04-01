package product.amortization;

import beans.Amortization;
import beans.Coupon;
import beans.Trade;


public class MMMortgage extends MMAmortization {
	
	Amortization amortizationObj;
	Trade trade;
	Coupon coupon;
	
	double balanceLoanAmount = 0.0;
	double rateofInterest = 0.0;
	long noofMonths = 0;
	double fixedInterest = 0.0;
	
	double interest = 0.0;
	
	double valueofC = 0.0;
	double cPowerN = 0.0;
	
	public MMMortgage(Amortization amortizationObj,
			Trade trade, Coupon coupon, long noofMonths) {
		
		this.amortizationObj = amortizationObj;
		this.trade = trade;
		this.coupon = coupon;
		this.noofMonths = noofMonths;
		
		rateofInterest = coupon.getFixedRate();
		balanceLoanAmount = trade.getTradeAmount();
		
	}
	
	
	
	public double getInterest() {
		return interest;
	}



	public void setInterest(double interest) {
		this.interest = interest;
	}



	public double getRateofInterest() {
		return rateofInterest;
	}

	public void setRateofInterest(double rateofInterest) {
		this.rateofInterest = rateofInterest;
	}

	public long getNoofMonths() {
		return noofMonths;
	}

	public void setNoofMonths(long noofMonths) {
		this.noofMonths = noofMonths;
	}

	public double calculateFixedInterest() {
				
		if ( fixedInterest == 0.0 ) {
			
			valueofC = (double) Math.round(((rateofInterest/100)/12)*1000000)/1000000;
			
			cPowerN  = Math.pow((1+valueofC), noofMonths);		
			
			fixedInterest =   (double) Math.round(( (balanceLoanAmount * valueofC * cPowerN)/(cPowerN - 1))*100)/100;
			
		}		
		
		return fixedInterest;
		
	}

	@Override
	public double calculatePayment() {
		
		interest = (double) Math.round((balanceLoanAmount * valueofC) * 100) / 100;
		
		setInterest(interest);
		
		double subtractAmt = fixedInterest - interest;

		balanceLoanAmount =  (double) Math.round( (balanceLoanAmount - subtractAmt) * 100 ) /100;
		
		return balanceLoanAmount;
		
	}
	
}
	
