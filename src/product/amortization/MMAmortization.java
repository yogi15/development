package product.amortization;

import util.common.CountDay;

public abstract class MMAmortization {
	
	double interest = 0.0;
	CountDay dayCount = null;
	/*double loanAmount = 0.0;
	double rateofInterest = 0.0;
	long noofMonths = 0;
	double fixedInterest = 0.0;

	
	public MMAmortization  ( double loanAmount, double rateofInterest, long noofMonths, double fixedInterest ) {
		
		this.loanAmount = loanAmount;
		this.rateofInterest = rateofInterest;
		this.noofMonths = noofMonths;
		this.fixedInterest = fixedInterest;
		
	}*/
	
	public abstract double calculatePayment();
	public abstract double calculateFixedInterest();
	
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public CountDay getDayCount() {
		return dayCount;
	}
	
	
	/*public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
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
	public double getFixedInterest() {
		return fixedInterest;
	}
	public void setFixedInterest(double fixedInterest) {
		this.fixedInterest = fixedInterest;
	}
*/
	
	
	
}
