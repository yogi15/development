package product.amortization;

import java.util.Date;

public class MMSchedule extends MMAmortization {
	
	Date startDate;
	Date endDate;
	double interestRate = 0.0;
	double fixedInterest = 0.0;
	double interest = 0.0;
	double balanceLoanAmount = 0.0;
	int frequencyMonths = 0;
		
	public MMSchedule(Date startDate, Date endDate, double interestRate, double fixedInterest, double balanceAmount, int frequencyMonths) {
		
		this.startDate = startDate;
		this.endDate = endDate;
		this.interestRate = interestRate;
		this.fixedInterest = fixedInterest;
		this.balanceLoanAmount = balanceAmount;
		this.frequencyMonths = frequencyMonths;
		
	}
	
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	
	
	@Override
	public double calculatePayment() {
		
		interest = (double) Math.round(( (balanceLoanAmount * (interestRate/ 100)) * frequencyMonths/12 ) * 100) / 100;
		
		setInterest(interest);
		
		double subtractAmt = fixedInterest - interest;

		balanceLoanAmount =  (double) Math.round( (balanceLoanAmount - subtractAmt) * 100 ) /100;
		
		return balanceLoanAmount;
	}

	@Override
	public double calculateFixedInterest() {
		// TODO Auto-generated method stub
		return 0;
	}

}
