package productPricing.pricingUtil;

import java.util.Collections;
import java.util.Date;
import java.util.Vector;

import constants.CommonConstants;

import productPricing.BONDPricing;
import productPricing.DefaultCashFlow;
import productPricing.Pricer;

import beans.Coupon;
import beans.Flows;
import beans.Product;
import beans.Trade;

import util.HolidayUtil;
import util.commonUTIL;
import util.common.CountDay;
import util.common.DateU;
import util.common.Miscellaneous;
import util.common.Period;

public class BondCashFlow extends DefaultCashFlow {

	Vector<Flows> flows = new Vector<Flows>();
	int CountCashFlows;

	int noOfTotalDays;
	int noofDaysbetweenCashFlow;
	int noCoupons;
	Flows principalFlow = new Flows();
	Flows accuralFlow = null;
	Date startDate;
	Date endDate;

	public void genearteCashFlow(Product product, Coupon coupon, Trade trade,
			Pricer price) {
		// System.out.println(" Frequency  == " + coupon.getCouponFrequency());
		if (coupon.getCouponFrequency().equalsIgnoreCase(CommonConstants.ZCFREQUENCY)) {
			generateZCFlow(product, trade, coupon);
			calculateflowsZC(flows,
					commonUTIL.stringToDate(trade.getDelivertyDate(), true),
					product, coupon, 0, trade, price.getYield(),price);
		} else {
			Period periods = new Period(product.getTenor());

			String tradeSettlement = trade.getDelivertyDate();
			String issueDate = product.getIssueDate();
			String maturityDate = product.getMarturityDate();

			String frequType = "1"
					+ periods.returnFrequencyType(product.getTenor());
			// System.out.println(periods.getCode());
			int f = frequencyUtil.fromString(coupon.getCouponFrequency());
			setFrequencyInYear(frequencyUtil.frequencyInyear(coupon
					.getCouponFrequency()));
			int period = periods.getCode();
			int remainingMonths = Miscellaneous.getCouponRemainingMonths(
					issueDate, maturityDate, frequencyUtil
							.monthsForFrequency(coupon.getCouponFrequency()));
			int noOfCoupons = (period / f) + remainingMonths;
			setCountCashFlows(noOfCoupons);

			generateFlow(getCountCashFlows(),
					commonUTIL.stringToDate(issueDate, true),
					commonUTIL.stringToDate(maturityDate, true), f,
					commonUTIL.stringToDate(tradeSettlement, true),
					coupon.getCouponFrequency(), product, coupon);

			calculateflows(flows,
					commonUTIL.stringToDate(tradeSettlement, true), product,
					coupon, period / f, trade, price.getYield(),price);
		} // System.out.println(period/f);

		// periods.valueOf(tenor).getCode();

	}

	private void calculateflowsZC(Vector<Flows> flows2, Date tradeSettleDate,
			Product product, Coupon coupon, int frequency, Trade trade,
			double yield,Pricer price) {
		/*
		 * double noOfyears = flows2.size(); DateU settleDate =
		 * DateU.valueOf(tradeSettleDate);
		 * 
		 * // to get broken period get first flow. Integer brokenperiod = new
		 * Integer(settleDate.getdayOftheYear());
		 * 
		 * double brokenPeriodByYear = (365 - brokenperiod.doubleValue()) / 365;
		 * double totalNoofYears = (noOfyears - 1) + brokenPeriodByYear; Vector
		 * newFlow = new Vector();
		 * 
		 * double ratePercentage = coupon.getFixedRate() / 100; double rr =
		 * Math.pow((1 + ratePercentage), totalNoofYears); double price =
		 * product.getFaceValue() / rr; Flows lastFlow = (Flows)
		 * flows2.elementAt(flows2.size() - 1); // get the // last // flow //
		 * only // one // flow // to be // shown // in // case // of // Zero //
		 * Coupon Flows principal = new Flows();
		 * principal.setStartDate(lastFlow.getStartDate());
		 * principal.setEndDate(lastFlow.getEndDate());
		 * principal.setNominal(trade.getNominal());
		 * principal.setCouponAmount(trade.getNominal());
		 * principal.setRate(coupon.getFixedRate());
		 * principal.setType("Principal");
		 * 
		 * Flows interest = new Flows();
		 * interest.setStartDate(lastFlow.getStartDate());
		 * interest.setEndDate(lastFlow.getEndDate()); // get the last flow only
		 * // one flow to be shown in // case of Zero Coupon
		 * interest.setCouponAmount(trade.getNominal() - price);
		 * interest.setRate(coupon.getFixedRate());
		 * interest.setType("Interest");
		 * 
		 * flows2.removeAllElements();
		 * 
		 * flows2.addElement(principal); flows2.addElement(interest);
		 * 
		 * setPrincipalFlow(principal); setAccuralFlow(interest);
		 */

		double coupondays = 0;
		double couponAmount = 0;
		CountDay daycount = CountDay.valueOf(coupon.getDayCount());

		double accrualInterest = 0;
		double remainingCoupon = 0;
		double totalCouponAmut = 0;
		double totalDiscountedAmt = 0;

		int payMutiflyingfactor = 0;
		int receiveMutiflyingfactor = 0;

		if (trade.getType().equalsIgnoreCase("BUY")) {

			payMutiflyingfactor = -1;
			receiveMutiflyingfactor = 1;

		} else {

			payMutiflyingfactor = 1;
			receiveMutiflyingfactor = -1;

		}

		for (int i = 0; i < flows2.size(); i++) {

			Flows flow = (Flows) flows.elementAt(i);

			if (i == 0) {
               
				flow.setFlowsdays(0);
				flow.setType("Principal");
				flow.setRate(coupon.getFixedRate());
				flow.setNominal(trade.getTradeAmount());
				flow.setAccuralDays(0);
				// flow(totalAmount);
				double paymentAmount = payMutiflyingfactor
						* Miscellaneous.getPaymentAmount(trade, product, coupon, flows.get(1), daycount);
                 
				flow.setCouponAmount(paymentAmount);
				price.setPrincipal(paymentAmount);

			} else if (i == 1) {

				DateU couponStartDate = DateU.valueOf(flow.getStartDate());
				DateU couponEndDate = DateU.valueOf(flow.getEndDate());

				long accrualsDays = commonUTIL.jodaDiffInDays(
						couponStartDate.getDate(), couponEndDate.getDate());
				double dayCountFactor = daycount.getDayCountFactor(
						couponStartDate, couponEndDate);

				coupondays = daycount.dayDiff(couponStartDate, couponEndDate);

				flow.setFlowsdays(coupondays);
				flow.setRate(coupon.getFixedRate());
				flow.setNominal(trade.getTradeAmount());
				flow.setAccuralDays(accrualsDays);
				flow.setAccuralInterest(accrualInterest);
				flow.setFreqinYear(getFrequencyInYear());

				couponAmount = trade.getTradeAmount() * (flow.getRate() / 100)
						* dayCountFactor;
				couponAmount = couponAmount * receiveMutiflyingfactor;

				flow.setAccuralAmount(couponAmount);
				flow.setCouponAmount(couponAmount);

				flow.setType("Interest");
				remainingCoupon = remainingCoupon + flow.getRemainingCoupon();
				totalCouponAmut = totalCouponAmut + flow.getCouponAmount();

				calculateRateReturnValue(yield, frequency, remainingCoupon,
						flow.getCouponAmount(), flow);
				totalDiscountedAmt = totalDiscountedAmt
						+ flow.getDiscountedAmt();
				setAccuralFlow(flow);

			} else {

				flow.setFlowsdays(0);
				flow.setType("Principal");
				flow.setRate(coupon.getFixedRate());
				flow.setNominal(trade.getTradeAmount());
				flow.setAccuralDays(0);
				// flow(totalAmount);

				couponAmount = trade.getTradeAmount() * receiveMutiflyingfactor;
				flow.setCouponAmount(couponAmount);

			}

		}

	}

	private void calculateflows(Vector<Flows> flows2, Date tradeSettleDate,
			Product product, Coupon coupon, int frequency, Trade trade,
			double yield,Pricer price) {
		// TODO Auto-generated method stub
		DateU datesettle = DateU.valueOf(tradeSettleDate);

		// -DateU couponEndDate = null;
		double coupondays = 0;
		double couponAmount = 0;
		CountDay daycount = CountDay.valueOf(coupon.getDayCount());

		double accrualInterest = 0;
		double remainingCoupon = 0;
		double totalCouponAmut = 0;
		double totalDiscountedAmt = 0;
		
		long accrualsDays = 0;

		int payMutiflyingfactor = 0;
		int receiveMutiflyingfactor = 0;
		// setTradeData(trade, coupon);

		int lag = 0;

		if (trade.getType().equalsIgnoreCase("BUY")) {

			payMutiflyingfactor = -1;
			receiveMutiflyingfactor = 1;

		} else {

			payMutiflyingfactor = 1;
			receiveMutiflyingfactor = -1;

		}

		for (int i = 0; i < flows2.size(); i++) {
			Flows flow = (Flows) flows.elementAt(i);

			if (i == 0) {

				flow.setFlowsdays(0);
				flow.setType("Principal");
				flow.setRate(coupon.getFixedRate());
				flow.setNominal(trade.getTradeAmount());
				flow.setAccuralDays(0);
				// flow(totalAmount);
				double paymentAmount = payMutiflyingfactor
						* Miscellaneous.getPaymentAmount(trade, product, coupon, flows.get(1), daycount);

				flow.setCouponAmount(paymentAmount);
				price.setPrincipal(paymentAmount);
			
			} else if (i == 1) {

				/*-				double accrualsDays = daycount.dayDiff(
				 DateU.valueOf(flow.getStartDate()), datesettle);
				 */
				// datesettle.subractDates(flow.getStartDate());

				/*
				 * - double accrualsDays = daycount.dayDiff(
				 * DateU.valueOf(flow.getStartDate()),
				 * DateU.valueOf(flow.getEndDate()));
				 */

				DateU couponStartDate = DateU.valueOf(flow.getStartDate());
				DateU couponEndDate = DateU.valueOf(flow.getEndDate());

				/*accrualsDays = commonUTIL.jodaDiffInDays(
						couponStartDate.getDate(), couponEndDate.getDate());*/
				
				accrualsDays = daycount.dayDiff(couponStartDate, couponEndDate);
				
				//coupondays = daycount.dayDiff(couponStartDate, couponEndDate); // couponEndDate.subractDates(flow.getStartDate());

				flow.setFlowsdays(accrualsDays);
				flow.setRate(coupon.getFixedRate());
				flow.setNominal(trade.getTradeAmount());
				flow.setAccuralDays(accrualsDays);

				// datesettle = DateU.valueOf(flow.getPaymentDate());

				flow.setUsedCoupon(commonUTIL.roundToZero(accrualsDays
						/ coupondays));
				flow.setRemainingCoupon(commonUTIL
						.roundToZero((coupondays - accrualsDays) / coupondays));
				/*--				flow.setCouponAmount(commonUTIL.roundToZero((trade.getNominal() * (coupon
				 .getFixedRate() / 100)) / getFrequencyInYear()));
				 accrualInterest = (flow.getCouponAmount()
				 * (flow.getRate() / getFrequencyInYear()) * flow
				 .getUsedCoupon());*/
				// -couponAmount = (((flow.getCouponAmount() * (accrualsDays /
				// coupondays)) / product.getQuantity()) * trade.getNominal());
				flow.setAccuralInterest(accrualInterest);
				// -flow.setAccuralAmount(accuralAmount);
				flow.setFreqinYear(getFrequencyInYear());
				/*-				double amt = commonUTIL
				 .roundToZero((trade.getQuantity() * (coupon
				 .getFixedRate() / 100)) / getFrequencyInYear());
				 -couponAmount = (amt * (accrualsDays / coupondays));
				 */
				
				double dayCountFactor = daycount.getDayCountFactor(
						couponStartDate, couponEndDate);
				
				couponAmount = trade.getTradeAmount() * (flow.getRate() / 100)
						* dayCountFactor;

				couponAmount = couponAmount * receiveMutiflyingfactor;

				flow.setAccuralAmount(couponAmount);
				flow.setCouponAmount(couponAmount);

				flow.setType("Interest");
				remainingCoupon = remainingCoupon + flow.getRemainingCoupon();
				totalCouponAmut = totalCouponAmut + flow.getCouponAmount();

				calculateRateReturnValue(yield, frequency, remainingCoupon,
						flow.getCouponAmount(), flow);
				totalDiscountedAmt = totalDiscountedAmt
						+ flow.getDiscountedAmt();
				setAccuralFlow(flow);
				// principalFlow = flow;

			} else if (i < (flows2.size() - 1)) {

				DateU couponStartDate = DateU.valueOf(flow.getStartDate());
				DateU couponEndDate = DateU.valueOf(flow.getEndDate());

				// -int accrualsDays =
				// daycount.dayDiff(DateU.valueOf(flow.getStartDate()),
				// datesettle);
				// datesettle.subractDates(flow.getStartDate());
				// datesettle = DateU.valueOf(flow.getPaymentDate());

				/*accrualsDays = commonUTIL.jodaDiffInDays(
				couponStartDate.getDate(), couponEndDate.getDate());*/
		
		accrualsDays = daycount.dayDiff(couponStartDate, couponEndDate);
				
				flow.setAccuralDays(accrualsDays);
				flow.setRate(coupon.getFixedRate());
				flow.setNominal(trade.getTradeAmount());

				//coupondays = daycount.dayDiff(couponStartDate, couponEndDate); // couponEndDate.subractDates(flow.getStartDate());
																				// couponEndDate.subractDates(flow.getStartDate());
				flow.setFlowsdays(accrualsDays);
				flow.setFreqinYear(getFrequencyInYear());
				flow.setUsedCoupon(commonUTIL.roundToZero(accrualsDays
						/ coupondays));
				flow.setRemainingCoupon(commonUTIL
						.roundToZero((coupondays - accrualsDays) / coupondays));
				/*--				flow.setCouponAmount(commonUTIL.roundToZero((trade.getNominal() * (coupon
				 .getFixedRate() / 100)) / getFrequencyInYear()));
				 accrualInterest = ((flow.getCouponAmount() * (accrualsDays / coupondays)) / trade
				 .getNominal());*/
				// - couponAmount = (((flow.getCouponAmount() * (accrualsDays /
				// coupondays)) / product.getQuantity()) * trade.getNominal());
				flow.setAccuralInterest(accrualInterest);
				
				double dayCountFactor = daycount.getDayCountFactor(
						couponStartDate, couponEndDate);

				couponAmount = trade.getTradeAmount() * (flow.getRate() / 100)
						* dayCountFactor;

				couponAmount = couponAmount * receiveMutiflyingfactor;

				flow.setAccuralAmount(couponAmount);
				flow.setCouponAmount(couponAmount);

				remainingCoupon = remainingCoupon + flow.getRemainingCoupon();
				totalCouponAmut = totalCouponAmut + flow.getCouponAmount();
				calculateRateReturnValue(yield, frequency, remainingCoupon,
						flow.getCouponAmount(), flow);
				totalDiscountedAmt = totalDiscountedAmt
						+ flow.getDiscountedAmt();
				flow.setType("Interest");
				flow.setTotalCouponPrice(totalCouponAmut);
				flow.setTotalDiscountPrice(totalDiscountedAmt);

				/*--				if (i == (flows2.size() - 1)) {
				 flow.setType("Principal");
				 ---				flow.setCouponAmount(flow.getCouponAmount()
				 + trade.getNominal());
				
				 flow.setCouponAmount(trade.getTradeAmount());
				 flow.setTotalCouponPrice(totalCouponAmut);
				 flow.setTotalDiscountPrice(totalDiscountedAmt);
				 principalFlow = flow;
				 }
				 */
			} else {

				flow.setFlowsdays(0);
				flow.setType("Principal");
				flow.setRate(coupon.getFixedRate());
				flow.setNominal(trade.getTradeAmount());
				//Used by Transfer
				flow.setPrincipal(trade.getTradeAmount());
				flow.setAccuralDays(0);
				// flow(totalAmount);

				couponAmount = trade.getTradeAmount() * receiveMutiflyingfactor;

				flow.setCouponAmount(couponAmount);

			}

		}

	}

	private void generateFlow(int noOfCashFlows, Date issueDate,
			Date maturityDate, int frequency, Date tradeSettleDate,
			String freq, Product product, Coupon coupon) {

		DateU date = null;
		DateU tempStartDate = null;

		// CountDay daycount = CountDay.valueOf(coupon.getDayCount());
		String businessDayConvention = coupon.getBusinessDayConvention();
		boolean startaddingFlows = true;

		// Vector<Date> enddates = getEndDates(maturityDate, freq,
		// noOfCashFlows);
		// System.out.println("enddates " + enddates);
		// lag needs to be included in product
		int lag = 0;
		int flowCount = 0;
		Date endDate = maturityDate;

		int no = -1 * frequencyUtil.monthsForFrequency(freq);

		String criteria = frequencyUtil.getCriteria(freq);

		//for (int i = 0; i < noOfCashFlows; i++) {
		while (startaddingFlows) {
			Flows flow = new Flows();

			if (flowCount == 0) {
				flowCount++;
				flow.setEndDate(endDate);

				date = DateU.valueOf(endDate);
				date.deductFrequecyCode(freq);

				flow.setStartDate(date.getDate());

				tempStartDate = DateU.valueOf(flow.getStartDate());

			} else {

				flow.setEndDate(date.getDate());
				System.out.println("EndDate" + date.getDate());
				/*DateU startDateU = new DateU(date.getDate());
				startDateU.deductFrequecyCode(freq);
								
System.out.println("startDateU" + startDateU.getDate());*/
				tempStartDate = DateU.valueOf(commonUTIL.addSubtractDate(
						tempStartDate.getDate(), no, criteria));
				// tempStartDate.deductFrequecyCode(freq);
				//System.out.println("tempStartDate" + tempStartDate.getDate());
				flow.setStartDate(tempStartDate.getDate());

				// tempStartDate = DateU.valueOf( flow.getStartDate() );
			}

			if (commonUTIL.checkGreaterDate(flow.getEndDate(), tradeSettleDate)) {

				if (flows.size() == 0) {
					Flows secondPrincipalFlow = new Flows();
					secondPrincipalFlow.setEndDate(endDate);
					secondPrincipalFlow.setPaymentDate(commonUTIL
							.addSubtractDate(secondPrincipalFlow.getEndDate(),
									lag));
					flows.add(secondPrincipalFlow);

					/*
					 * if ( enddates.size() != 1 ) {
					 * 
					 * //check various condition
					 * flow.setStartDate(enddates.get(enddates.size() - i ));
					 * 
					 * }
					 */

				}
				
				if (!businessDayConvention.equals("NO_ADJUST")) {

					String stringStartDate = HolidayUtil
							.applyBusinessDayConvention(product
									.getIssueCurrency(), commonUTIL
									.dateToString(flow.getStartDate()),
									businessDayConvention);

					flow.setStartDate(commonUTIL.stringToDate(stringStartDate,
							true));

				}

				flow.setPaymentDate(commonUTIL.addSubtractDate(
						flow.getEndDate(), lag));

				// bdc for payment date is always FOLLOWING;
				String paymentDate = HolidayUtil.applyBusinessDayConvention(
						product.getIssueCurrency(),
						commonUTIL.dateToString(flow.getPaymentDate()),
						"FOLLOWING");

				flow.setPaymentDate((commonUTIL.stringToDate(paymentDate, true)));

				date = DateU.valueOf(flow.getStartDate());

				flows.add(flow);
			
			} else {
				startaddingFlows = false;
			}

		}

		Flows firstPrincipalFlow = new Flows();
		firstPrincipalFlow.setEndDate(tradeSettleDate);
		firstPrincipalFlow.setPaymentDate(commonUTIL.addSubtractDate(
				tradeSettleDate, lag));
		flows.add(firstPrincipalFlow);

		Collections.reverse(flows);

	}

	private void generateZCFlow(Product product, Trade trade, Coupon coupon) {
		// TODO Auto-generatedmethod stub

		Date issueDate = commonUTIL.stringToDate(product.getIssueDate(), true);
		Date maturityDate = commonUTIL.stringToDate(product.getMarturityDate(),
				true);
		Date tradeSettleDate = commonUTIL.stringToDate(
				trade.getDelivertyDate(), true);

		String businessDayConvention = coupon.getBusinessDayConvention();
		int lag = 0;

		DateU startDate = null;
		boolean startaddingFlows = false;
		startDate = DateU.valueOf(issueDate);
		// Flows flow[] = new Flows[startDate.yearDiff(maturityDate)];

		/*
		 * Flows flow[] = new Flows[3];
		 * 
		 * for (int i = 0; i < flow.length; i++) {
		 * 
		 * 
		 * Date date12 = startDate.getDate(); Flows Flow = new Flows();
		 * Flow.setStartDate(date12);
		 * 
		 * startDate.addDays(360); Flow.setEndDate(startDate.getDate());
		 * startDate = DateU.valueOf(Flow.getEndDate());
		 * 
		 * if (startaddingFlows) { flows.add(Flow); } else { if
		 * (commonUTIL.between2dates(Flow.getStartDate(), Flow.getEndDate(),
		 * SettlementDate)) { startaddingFlows = true; flows.add(Flow); } }
		 */

		Flows firstPrincipalFlow = new Flows();
		firstPrincipalFlow.setEndDate(tradeSettleDate);
		firstPrincipalFlow.setPaymentDate(commonUTIL.addSubtractDate(
				tradeSettleDate, lag));

		Flows interestFlow = new Flows();
		interestFlow.setStartDate(issueDate);
		interestFlow.setEndDate(maturityDate);

		Flows secondPrincipalFlow = new Flows();
		secondPrincipalFlow.setEndDate(maturityDate);
		secondPrincipalFlow.setPaymentDate(commonUTIL.addSubtractDate(
				secondPrincipalFlow.getEndDate(), lag));

		/*
		 * if (!businessDayConvention.equals("NO_ADJUST")) {
		 * 
		 * String stringStartDate = HolidayUtil.applyBusinessDayConvention(
		 * product.getIssueCurrency(),
		 * commonUTIL.dateToString(interestFlow.getStartDate()),
		 * businessDayConvention);
		 * 
		 * interestFlow.setStartDate(commonUTIL.stringToDate(stringStartDate,
		 * true));
		 * 
		 * }
		 */

		interestFlow.setPaymentDate(commonUTIL.addSubtractDate(
				interestFlow.getEndDate(), lag));

		// bdc for payment date is always FOLLOWING;
		String paymentDate = HolidayUtil.applyBusinessDayConvention(
				product.getIssueCurrency(),
				commonUTIL.dateToString(interestFlow.getPaymentDate()),
				"FOLLOWING");

		interestFlow
				.setPaymentDate((commonUTIL.stringToDate(paymentDate, true)));

		flows.add(firstPrincipalFlow);
		flows.add(interestFlow);
		flows.add(secondPrincipalFlow);

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
