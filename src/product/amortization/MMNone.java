package product.amortization;

import java.util.Vector;

import util.commonUTIL;
import util.common.CountDay;
import util.common.DateU;
import util.common.Miscellaneous;
import beans.Amortization;
import beans.Coupon;
import beans.Flows;
import beans.Product;
import beans.Trade;

public class MMNone extends MMAmortization {

	Amortization amortizationObj;
	Trade trade;
	Coupon coupon;
	Product product;
	CountDay daycount;
	Vector<Flows> flows = new Vector<Flows>();
	double interest = 0.0;

	private int flowNo = 1;

	public MMNone(Amortization amortizationObj, Trade trade, Coupon coupon,
			Product product, Vector<Flows> flows) {

		this.amortizationObj = amortizationObj;
		this.trade = trade;
		this.coupon = coupon;
		this.product = product;
		this.flows = flows;

		daycount = CountDay.valueOf(coupon.getDayCount());
		setDayCount(daycount);
		
	}

	public Amortization getAmortizationObj() {
		return amortizationObj;
	}

	public void setAmortizationObj(Amortization amortizationObj) {
		this.amortizationObj = amortizationObj;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}
	
	public CountDay getDayCount() {
		return dayCount;
	}
	
	private void setDayCount(CountDay daycount) {
		this.dayCount =daycount;
	}
	
	public double calculateFixedInterest() {

		Flows flow = (Flows) flows.elementAt(flowNo);

		DateU productStartDate = DateU.valueOf(flow.getStartDate());

		/*
		 * if (commonUTIL.checkGreaterDate(
		 * commonUTIL.stringToDate(product.getIssueDate(), true),
		 * productStartDate.getDate())) {
		 * 
		 * productStartDate = DateU.valueOf(commonUTIL.stringToDate(
		 * product.getIssueDate(), true));
		 * 
		 * }
		 */

		double dayCountFactor = daycount.getDayCountFactor(productStartDate,
				DateU.valueOf(flow.getEndDate()));

		double fixedInterest = trade.getQuantity()
				* (coupon.getFixedRate() / 100) * dayCountFactor;

		return fixedInterest;

	}

	@Override
	public double calculatePayment() {

		interest = 0.0;

		setInterest(interest);

		Flows flow = (Flows) flows.elementAt(flowNo);

		flowNo++;

		DateU productStartDate = DateU.valueOf(flow.getStartDate());

		/*
		 * if (commonUTIL.checkGreaterDate(
		 * commonUTIL.stringToDate(product.getIssueDate(), true),
		 * productStartDate.getDate())) {
		 * 
		 * productStartDate = DateU.valueOf(commonUTIL.stringToDate(
		 * product.getIssueDate(), true));
		 * 
		 * }
		 */

		double dayCountFactor = daycount.getDayCountFactor(productStartDate,
				DateU.valueOf(flow.getEndDate()));

		double couponAmount = trade.getQuantity()
				* (coupon.getFixedRate() / 100) * dayCountFactor;

		return couponAmount;

	}


}
