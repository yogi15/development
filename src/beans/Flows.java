package beans;

import java.util.Date;

public class Flows {
	
		
		Date startDate;
		Date endDate;
		Date paymentDate;
		double rate;
		String type;
		double flowsdays;
		double dcfactor;
		double accuralDays;
		double remainingCoupon;
		double usedCoupon;
		double couponAmount;
		double nominal;
		double totalCouponPrice;
		double totalDiscountPrice;
		double freqinYear;
		double principal;
		
		/** for MM Cashflow **/
		
		double interest = 0.0;
		double fixedAmountPayment = 0.0;
		double fixedInterest = 0.0;
		//double paymentAmount = 0.0;
		double outstandingAmount = 0.0;
		double balanceAmount = 0.0;
		String amortizationType = "";
		
		Date cmpdStartDate;
		Date cmpdEnddate;		
						
		public Date getCmpdStartDate() {
			return cmpdStartDate;
		}
		public void setCmpdStartDate(Date cmpdStartDate) {
			this.cmpdStartDate = cmpdStartDate;
		}
		public Date getCmpdEnddate() {
			return cmpdEnddate;
		}
		public void setCmpdEnddate(Date cmpdEnddate) {
			this.cmpdEnddate = cmpdEnddate;
		}
		public double getBalanceAmount() {
			return balanceAmount;
		}
		public void setBalanceAmount(double balanceAmount) {
			this.balanceAmount = balanceAmount;
		}
		public String getAmortizationType() {
			return amortizationType;
		}
		public void setAmortizationType(String amortizationType) {
			this.amortizationType = amortizationType;
		}
		public double getOutstandingAmount() {
			return outstandingAmount;
		}
		public void setOutstandingAmount(double outstandingAmount) {
			this.outstandingAmount = outstandingAmount;
		}
		public double getInterest() {
			return interest;
		}
		public void setInterest(double interest) {
			this.interest = interest;
		}
		public double getFixedAmountPayment() {
			return fixedAmountPayment;
		}
		public void setFixedAmountPayment(double fixedAmountPayment) {
			this.fixedAmountPayment = fixedAmountPayment;
		}
		public double getFixedInterest() {
			return fixedInterest;
		}
		public void setFixedInterest(double fixedInterest) {
			this.fixedInterest = fixedInterest;
		}
		
/*		public double getPaymentAmount() {
			return paymentAmount;
		}
		public void setPaymentAmount(double paymentAmount) {
			this.paymentAmount = paymentAmount;
		}*/
		
		
		/** for MM Cashflow **/
		
		
		public double getPrincipal() {
			return principal;
		}
		public void setPrincipal(double principal) {
			this.principal = principal;
		}
		public double getFreqinYear() {
			return freqinYear;
		}
		public void setFreqinYear(double freqinYear) {
			this.freqinYear = freqinYear;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		public double getRate() {
			return rate;
		}
		public void setRate(double rate) {
			this.rate = rate;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public double getFlowsdays() {
			return flowsdays;
		}
		public void setFlowsdays(double flowsdays) {
			this.flowsdays = flowsdays;
		}
		public double getDcfactor() {
			return dcfactor;
		}
		public void setDcfactor(double dcfactor) {
			this.dcfactor = dcfactor;
		}
		public double getAccuralDays() {
			return accuralDays;
		}
		public void setAccuralDays(double accuralDays) {
			this.accuralDays = accuralDays;
		}
		public Date getPaymentDate() {
			return paymentDate;
		}
		public void setPaymentDate(Date paymentDate) {
			this.paymentDate = paymentDate;
		}
		public double getRemainingCoupon() {
			return remainingCoupon;
		}
		public void setRemainingCoupon(double remainingCoupon) {
			this.remainingCoupon = remainingCoupon;
		}
		public double getUsedCoupon() {
			return usedCoupon;
		}
		public void setUsedCoupon(double usedCoupon) {
			this.usedCoupon = usedCoupon;
		}
		public double getCouponAmount() {
			return couponAmount;
		}
		public void setCouponAmount(double couponAmount) {
			this.couponAmount = couponAmount;
		}
		
		double accuralInterest = 0.0;
		double accuralAmount = 0.0;

		public double getAccuralInterest() {
			return accuralInterest;
		}
		public void setAccuralInterest(double accuralInterest) {
			this.accuralInterest = accuralInterest;
		}
		public double getAccuralAmount() {
			return accuralAmount;
		}
		public void setAccuralAmount(double accuralAmount) {
			this.accuralAmount = accuralAmount;
		}
		public double getNominal() {
			return nominal;
		}
		public void setNominal(double nominal) {
			this.nominal = nominal;
		}
		public void setDiscountedAmt(double discountedAmt) {
			// TODO Auto-generated method stub
			this.discountedAmt = discountedAmt;
			
		}
		public double getDiscountedAmt() {
			// TODO Auto-generated method stub
			return discountedAmt;
		}
		
		double discountedAmt = 0.0;



		public double getTotalCouponPrice() {
			return totalCouponPrice;
		}
		public void setTotalCouponPrice(double totalCouponPrice) {
			this.totalCouponPrice = totalCouponPrice;
		}
		public double getTotalDiscountPrice() {
			return totalDiscountPrice;
		}
		public void setTotalDiscountPrice(double totalDiscountPrice) {
			this.totalDiscountPrice = totalDiscountPrice;
		}
		
		
}
