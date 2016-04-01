package productPricing.pricingUtil;

import java.util.Collections;
import java.util.Date;
import java.util.Vector;

import constants.ProductConstants;
import constants.TradeConstants;

import beans.Amortization;
import beans.Coupon;
import beans.Flows;
import beans.Product;
import beans.Trade;
import product.amortization.RepoCompounding;
import productPricing.DefaultCashFlow;
import productPricing.Pricer;
import util.HolidayUtil;
import util.commonUTIL;
import util.common.CountDay;
import util.common.DateU;
import util.common.Miscellaneous;
import util.common.Period;

public class RepoCashFlow extends DefaultCashFlow {

	Vector<Flows> flows = new Vector<Flows>();
	int CountCashFlows;

	int noOfTotalDays;
	int noofDaysbetweenCashFlow;
	int noCoupons;
	Flows principalFlow = new Flows();
	Flows accuralFlow = null;
	Date startDate;
	Date endDate;

	String repoFrequency = "";

	public void genearteCashFlow(Product product, Coupon coupon, Trade trade,
			Pricer price) {

		Period periods = new Period(product.getTenor());

		String tradeSettlement = trade.getDelivertyDate();
		String issueDate = product.getIssueDate();
		String maturityDate = product.getMarturityDate();
		String amortizationData = trade.getAmoritizationData();
		String couponFrequency = coupon.getCouponFrequency();

		repoFrequency = amortizationData.substring(
				amortizationData.indexOf("=") + 1, amortizationData.length());

		generateCompoundingFlow(getCountCashFlows(),
				commonUTIL.stringToDate(issueDate, true),
				commonUTIL.stringToDate(maturityDate, true),
				commonUTIL.stringToDate(tradeSettlement, true),
				couponFrequency, product, coupon);

		generateCompoundingSubflows(flows,
				commonUTIL.stringToDate(tradeSettlement, true), product,
				coupon, trade, price.getYield());

		calculateCompoundingflows(flows,
				commonUTIL.stringToDate(tradeSettlement, true), product,
				coupon, trade, price.getYield());
	} // System.out.println(period/f);

	// periods.valueOf(tenor).getCode();

	private void generateCompoundingFlow(int noOfCashFlows, Date issueDate,
			Date maturityDate, Date tradeSettleDate, String freq,
			Product product, Coupon coupon) {

		DateU date = null;
		DateU tempStartDate = null;

		String businessDayConvention = coupon.getBusinessDayConvention();
		boolean startaddingFlows = true;

		int lag = 0;
		int flowCount = 0;

		Date endDate = maturityDate;

		int no = -1 * frequencyUtil.monthsForFrequency(freq);

		String criteria = frequencyUtil.getCriteria(freq);

		// for (int i = 0; i < noOfCashFlows; i++) {
		while (startaddingFlows) {

			Flows flow = new Flows();

			if (flowCount == 0) {

				flowCount++;

				flow.setEndDate(endDate);

				date = DateU.valueOf(endDate);
				date.deductFrequecyCode(freq);

				flow.setStartDate(date.getDate());
				tempStartDate = DateU.valueOf(flow.getStartDate());
				System.out.println("tempStartDate0" + tempStartDate.getDate());
			} else {

				flowCount = 2;
				System.out.println("date " + date.getDate());
				flow.setEndDate(date.getDate());

				date = DateU.valueOf(endDate);
				// date.deductFrequecyCode(freq);
				System.out.println("tempStartDate1 " + tempStartDate.getDate());
				tempStartDate = DateU.valueOf(commonUTIL.addSubtractDate(
						flow.getEndDate(), no, criteria));
				// tempStartDate.deductFrequecyCode(freq);
				System.out.println("tempStartDate2 " + tempStartDate.getDate());
				flow.setStartDate(tempStartDate.getDate());

			}

			if (commonUTIL.checkGreaterDate(flow.getEndDate(), tradeSettleDate)) {

				if (flows.size() == 0) {
					Flows secondPrincipalFlow = new Flows();
					secondPrincipalFlow.setEndDate(endDate);
					secondPrincipalFlow.setPaymentDate(commonUTIL
							.addSubtractDate(secondPrincipalFlow.getEndDate(),
									lag));
					flows.add(secondPrincipalFlow);

				} else {

					if (commonUTIL.checkGreaterDate(tradeSettleDate,
							flow.getStartDate())) {

						flow.setStartDate(tradeSettleDate);
						startaddingFlows = false;

					}

					if (!businessDayConvention.equals("NO_ADJUST")) {

						String stringStartDate = HolidayUtil
								.applyBusinessDayConvention(product
										.getIssueCurrency(), commonUTIL
										.dateToString(flow.getStartDate()),
										businessDayConvention);

						flow.setStartDate(commonUTIL.stringToDate(
								stringStartDate, true));

					}

					flow.setPaymentDate(commonUTIL.addSubtractDate(
							flow.getEndDate(), lag));

					// bdc for payment date is always FOLLOWING;
					String paymentDate = HolidayUtil
							.applyBusinessDayConvention(product
									.getIssueCurrency(), commonUTIL
									.dateToString(flow.getPaymentDate()),
									"FOLLOWING");

					flow.setPaymentDate((commonUTIL.stringToDate(paymentDate,
							true)));
					flow.setType(ProductConstants.INTEREST);

					if (!flow.getStartDate().equals(flow.getEndDate())) {
						System.out.println("start ---" + flow.getStartDate());
						System.out.println("end ---" + flow.getEndDate());
						flows.add(flow);

					}

				}

				if (flowCount == 1) {

					date = DateU.valueOf(endDate);
				} else {

					date = DateU.valueOf(flow.getStartDate());

				}

			} else {

				startaddingFlows = false;

			}

		}

		Flows firstPrincipalFlow = new Flows();
		firstPrincipalFlow.setEndDate(tradeSettleDate);
		firstPrincipalFlow.setPaymentDate(commonUTIL.addSubtractDate(
				tradeSettleDate, lag));
		flows.add(firstPrincipalFlow);

		// Collections.reverse(flows);

	}

	private void generateCompoundingSubflows(Vector<Flows> flows2,
			Date tradeSettleDate, Product product, Coupon coupon, Trade trade,
			double yield) {

		DateU date;
		DateU tempStartDate = null;
		boolean flag = true;
		Vector<Flows> newFlows = new Vector<Flows>();
		int k = 0;

		String businessDayConvention = coupon.getBusinessDayConvention();

		int flowSize = flows2.size();
		for (int i = 0; i < flowSize; i++) {

			Flows flow = (Flows) flows2.elementAt(i);

			if (i == 0) {

				newFlows.add(flow);

			} else if (i < (flowSize - 1)) {

				flag = true;

				date = DateU.valueOf(flow.getEndDate());

				/*
				 * System.out.println(flow.getStartDate());
				 * System.out.println(flow.getEndDate());
				 * System.out.println(flow.getCmpdStartDate());
				 * System.out.println(flow.getCmpdEnddate());
				 */
				/*
				 * System.out.println(flow.getInterest());
				 * System.out.println(flow.getCouponAmount());
				 */

				k = 0;
				while (flag) {

					Flows subFlow = new Flows();

					subFlow.setCmpdEnddate(date.getDate());

					if (k == 0) {
						k++;

						date.deductFrequecyCode(repoFrequency);
						subFlow.setCmpdStartDate(date.getDate());
						tempStartDate = DateU.valueOf(subFlow
								.getCmpdStartDate());

					} else {

						date = DateU.valueOf(tempStartDate.getDate());
						date.deductFrequecyCode(repoFrequency);
						subFlow.setCmpdStartDate(date.getDate());
						tempStartDate = DateU.valueOf(subFlow
								.getCmpdStartDate());

					}

					if (!businessDayConvention.equals("NO_ADJUST")) {

						String stringStartDate = HolidayUtil
								.applyBusinessDayConvention(product
										.getIssueCurrency(), commonUTIL
										.dateToString(subFlow
												.getCmpdStartDate()),
										businessDayConvention);

						subFlow.setCmpdStartDate(commonUTIL.stringToDate(
								stringStartDate, true));

					}

					if (subFlow.getCmpdStartDate().equals(flow.getStartDate())) {

						flag = false;
						flow.setCmpdStartDate(subFlow.getCmpdStartDate());
						flow.setCmpdEnddate(subFlow.getCmpdEnddate());

					} else {
						subFlow.setType(ProductConstants.COMPOUNDEDINTEREST);
						newFlows.add(subFlow);
					}

					/*
					 * System.out.println("Interest " + subFlow.getStartDate());
					 * System.out.println("Interest " + subFlow.getEndDate());
					 * System.out.println("CMPD Start" +
					 * subFlow.getCmpdStartDate());
					 * System.out.println("CMPD End" +
					 * subFlow.getCmpdEnddate());
					 */

					date = DateU.valueOf(subFlow.getCmpdStartDate());
				}

				newFlows.add(flow);

			} else {

				/*
				 * System.out.println(flow.getStartDate());
				 * System.out.println(flow.getEndDate());
				 * System.out.println(flow.getCmpdStartDate());
				 * System.out.println(flow.getCmpdEnddate());
				 * System.out.println(flow.getInterest());
				 * System.out.println(flow.getCouponAmount());
				 */newFlows.add(flow);

			}

		}

		Collections.reverse(newFlows);
		flows.clear();
		flows.addAll(newFlows);
	}

	private void calculateCompoundingflows(Vector<Flows> flows2,
			Date tradeSettleDate, Product product, Coupon coupon, Trade trade,
			double yield) {

		double paymentAmount = 0.0;
		double balanceAmount = 0.0;
		double principalAmount = 0.0;
		double interest = 0.0;
		double tradeAmount = trade.getTradeAmount();

		int j = 0;
		int k = 0;
		int l = 0;

		int payMutiflyingfactor = 1;
		int receiveMutiflyingfactor = -1;

		int noOfCompoundingCashflows = frequencyUtil.noOfCompoundingCashflows(repoFrequency)
				/ frequencyUtil.noOfCompoundingCashflows(coupon.getCouponFrequency());
		int flowSize = flows2.size();

		double fixedRate = coupon.getFixedRate();
		double nominalAmount = trade.getTradeAmount();
		
		CountDay daycount = CountDay.valueOf(coupon.getDayCount());
		
		RepoCompounding repommCompoundingObject = new RepoCompounding(trade,
				coupon);
		
		if (trade.getType().equalsIgnoreCase(TradeConstants.TRADETYPE_BUY)) {

			payMutiflyingfactor = 1;
			receiveMutiflyingfactor = -1;

		} else {

			payMutiflyingfactor = -1;
			receiveMutiflyingfactor = 1;

		}
		
		for (int i = 0; i < flowSize; i++) {

			Flows flow = (Flows) flows2.elementAt(i);

			if (i == 0) {

				balanceAmount = tradeAmount * payMutiflyingfactor;

				flow.setFlowsdays(0);
				// flow.setAmortizationType(amortizationObj.getAmortType());
				flow.setType(ProductConstants.PRINCIPAL);
				flow.setRate(fixedRate);
				flow.setNominal(nominalAmount);
				flow.setAccuralDays(0);
				flow.setCouponAmount(balanceAmount);

			} else if (i < (flowSize - 1)) {

				j++;
				k++;

				DateU cmpdStartDate = DateU.valueOf(flow.getCmpdStartDate());
				DateU cmpdEndDate = DateU.valueOf(flow.getCmpdEnddate());
				
				repommCompoundingObject.setCmpdStartDate(cmpdStartDate.getDate());
				repommCompoundingObject.setCmpdEndDate(cmpdEndDate.getDate());
				
				double accrualDays = daycount.dayDiff(cmpdStartDate, cmpdEndDate);

				// flow.setType(ProductConstants.INTEREST);
				flow.setRate(fixedRate);

				flow.setAccuralDays(accrualDays);

				if (k != 1) {

					l = i;
					balanceAmount = repommCompoundingObject
							.calculateCompoundAmount();

					interest = repommCompoundingObject.calculateInterest() * receiveMutiflyingfactor;

					flow.setNominal(balanceAmount);
					flow.setInterest(interest);

				} else {

					interest = repommCompoundingObject.calculateInterest() * receiveMutiflyingfactor;

					flow.setNominal(tradeAmount);
					flow.setInterest(interest);

					setAccuralFlow(flow);
				}

				if (j == noOfCompoundingCashflows) {

					paymentAmount = repommCompoundingObject.getPaymentAmount() * receiveMutiflyingfactor;
					flow.setCouponAmount(paymentAmount);
					flow.setAccuralAmount(paymentAmount);

					repommCompoundingObject.setTradeAmount(tradeAmount);
					repommCompoundingObject.setPaymentAmount(0.0);

					Flows interestFlow = (Flows) flows2.elementAt(l);
					interestFlow.setAccuralAmount(paymentAmount);
					interestFlow.setCouponAmount(paymentAmount);

					j = 0;
					k = 0;

				}

			} else {

				flow.setFlowsdays(0);
				flow.setType(ProductConstants.PRINCIPAL);
				flow.setRate(fixedRate);
				flow.setAccuralDays(0);

				principalAmount = trade.getTradeAmount()
						* receiveMutiflyingfactor;

				flow.setNominal(trade.getTradeAmount());
				// flow.setPaymentAmount(balanceAmount);
				flow.setCouponAmount(principalAmount);
				flow.setAccuralAmount(principalAmount);

			}

		}

	}

	public void calculateRateReturnValue(double yield, double frequency,
			double remainingPeriod, double couponAmt, Flows flow) {

		double rate = (yield / 100) / getFrequencyInYear();
		// System.out.println(" Rate == " + "(" +
		// yield+"/"+"100)/"+getFrequencyInYear() + " == "+rate);
		double rr = Math.pow((1 + rate), remainingPeriod);
		// System.out.println(" RR == " + "Math.pow(" + 1+rate+","+
		// remainingPeriod + " = " +rr);
		double discountedAmt = couponAmt / rr;
		// System.out.println(" discountedAmt == " + couponAmt + "/" + rr + " ="
		// + rr);
		flow.setDiscountedAmt(discountedAmt);

	}

	public Vector<Date> getEndDates(Date maturityDate, String frequency,
			int noOfCashFlows) {

		Vector<Date> enddates = new Vector<Date>();

		int no = -1 * frequencyUtil.monthsForFrequency(frequency);

		String criteria = frequencyUtil.getCriteria(frequency);

		enddates.add(maturityDate);

		Date updateDate = maturityDate;

		for (int i = 0; i < noOfCashFlows; i++) {
			System.out.println("i " + i + "----- " + updateDate);
			updateDate = commonUTIL.addSubtractDate(updateDate, no, criteria);

			enddates.add(updateDate);
		}

		return enddates;

	}

	public int getCountCashFlows() {
		return CountCashFlows;
	}

	public void setCountCashFlows(int countCashFlows) {
		CountCashFlows = countCashFlows;
	}

	double frequencyInYear = 0;

	public void setFrequencyInYear(double fy) {
		this.frequencyInYear = fy;

	}

	public double getFrequencyInYear() {
		return frequencyInYear;
	}

	public int getNoOfTotalDays() {
		return noOfTotalDays;
	}

	public void setNoOfTotalDays(int noOfTotalDays) {
		this.noOfTotalDays = noOfTotalDays;
	}

	public int getNoofDaysbetweenCashFlow() {
		return noofDaysbetweenCashFlow;
	}

	public void setNoofDaysbetweenCashFlow(int noofDaysbetweenCashFlow) {
		this.noofDaysbetweenCashFlow = noofDaysbetweenCashFlow;
	}

	public int getNoCoupons() {
		return noCoupons;
	}

	public void setNoCoupons(int noCoupons) {
		this.noCoupons = noCoupons;
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

	public Vector<Flows> getFlows() {
		return flows;
	}

	public void setFlows(Vector<Flows> flows) {
		this.flows = flows;
	}

	public Flows getPrincipalFlow() {
		return principalFlow;
	}

	public void setPrincipalFlow(Flows principalFlow) {
		this.principalFlow = principalFlow;
	}

	public Flows getAccuralFlow() {
		return accuralFlow;
	}

	public void setAccuralFlow(Flows accuralFlow) {
		this.accuralFlow = accuralFlow;
	}

}
