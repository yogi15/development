package productPricing.pricingUtil;

import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Vector;

import constants.CommonConstants;
import constants.MMConstants;
import constants.ProductConstants;
import constants.TradeConstants;
import product.amortization.MMAmortization;
import product.amortization.MMAmortizationFactory;
import product.amortization.MMCompounding;
import product.amortization.MMSchedule;
import product.amortization.RepoCompounding;
import productPricing.DefaultCashFlow;
import productPricing.Pricer;
import beans.Amortization;
import beans.Coupon;
import beans.Flows;
import beans.Product;
import beans.Trade;
import util.HolidayUtil;
import util.commonUTIL;
import util.common.CountDay;
import util.common.DateU;
import util.common.Frequency;
import util.common.Miscellaneous;
import util.common.Period;

public class MMCashFlow extends DefaultCashFlow {
	Vector<Flows> flows = new Vector<Flows>();
	final static String principal = "PRINCIPAL";
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

		Period periods = null;
		if(!commonUTIL.isEmpty(product.getTenor())) {
			periods = new Period(product.getTenor());
		}
		String issueDate = product.getIssueDate();
		String maturityDate = product.getMarturityDate();

		/*
		 * int f = frequencyUtil.fromString(coupon.getCouponFrequency());
		 * setFrequencyInYear(frequencyUtil.frequencyInyear(coupon
		 * .getCouponFrequency())); int period = periods.getCode();
		 * 
		 * int remainingMonths =
		 * Miscellaneous.getCouponRemainingMonths(issueDate, maturityDate,
		 * frequencyUtil
		 * .frequencyNumberToSubtract(coupon.getCouponFrequency()));
		 * 
		 * setCountCashFlows((period / f) + remainingMonths);
		 */
		String tradeSettlement = trade.getEffectiveDate();

		if (commonUTIL.isEmpty(tradeSettlement)) {
			tradeSettlement = issueDate;
		}

		Amortization amortizationObj = trade.getAmortization();
		
		String amorType = "NA"; 
		if(amortizationObj !=  null) {
			
			amorType = 	amortizationObj.getAmortization();
		
		//System.out.println("amortizationObj.getAmortization() " + amortizationObj.getAmortization());
		} else {
			amortizationObj = new Amortization();
			amortizationObj.setAmortizingFrequency("NA");
			amortizationObj.setAmortType("NA");
			amortizationObj.setStartDate(product.getIssueDate());
			amortizationObj.setEndDate(product.getMarturityDate());
		}
        if(commonUTIL.isEmpty(coupon.getCouponFrequency())) {
        	coupon.setCouponFrequency("ZC");
        }
		if (coupon.getCouponFrequency().equals(CommonConstants.ZCFREQUENCY)) {

			String frequency = amortizationObj.getAmortizingFrequency();
           
			if (frequency.equals(MMConstants.NACOMPOUNDINGTYPE)
					|| frequency.equals("0")) {

				// frequency = coupon.getCouponFrequency();

				generateZCNAFlow(trade, product, coupon);

			} else {

				generateZCSimpleCompoundingFlow(trade, product, coupon,
						frequency);

			}

			calculateflowsZC(flows, product, coupon, trade, amortizationObj);

		} else if (amorType.equalsIgnoreCase(MMConstants.SCHEDULEAMORTYPE)) {

			calculateScheduleflows(flows,
					commonUTIL.stringToDate(tradeSettlement, true), product,
					coupon, trade, price.getYield(), amortizationObj);

		} else if (amorType
				.equalsIgnoreCase(MMConstants.SIMPLECOMPOUNDINGAMORTYPE)) {

			generateCompoundingFlow(commonUTIL.stringToDate(issueDate, true),
					commonUTIL.stringToDate(maturityDate, true),
					commonUTIL.stringToDate(tradeSettlement, true),
					coupon.getCouponFrequency(), product, coupon);
			
			generateCompoundingSubflows(flows,
					commonUTIL.stringToDate(tradeSettlement, true), product,
					coupon, trade, amortizationObj.getAmortizingFrequency());

			calculateCompoundingflows(flows,
					commonUTIL.stringToDate(tradeSettlement, true), product,
					coupon, trade, amortizationObj.getAmortizingFrequency(),
					amorType);

		} else {

			generateFlow(getCountCashFlows(),
					commonUTIL.stringToDate(issueDate, true),
					commonUTIL.stringToDate(maturityDate, true),
					commonUTIL.stringToDate(tradeSettlement, true),
					coupon.getCouponFrequency(), product.getIssueCurrency(), coupon);

			calculateflows(flows, product, coupon, trade, amortizationObj);

		}

	}

	private void generateZCSimpleCompoundingFlow(Trade trade, Product product,
			Coupon coupon, String frequency) {

		DateU date = null;
		DateU tempStartDate = null;

		Date tradeSettleDate = commonUTIL.stringToDate(
				trade.getDelivertyDate(), true);
		Date endDate = commonUTIL
				.stringToDate(product.getMarturityDate(), true);

		String businessDayConvention = coupon.getBusinessDayConvention();

		boolean startaddingFlows = true;

		int lag = 0;
		int flowCount = 0;

		int no = -1 * frequencyUtil.frequencyNumberToSubtract(frequency);

		String criteria = frequencyUtil.getCriteria(frequency);

		while (startaddingFlows) {

			Flows flow = new Flows();

			if (flowCount == 0) {
				// for ZC Simple compounding subflows we set compounding start date and compounding end date
				flowCount++;
				//flow.setEndDate(endDate);
				flow.setCmpdEnddate(endDate);
				date = DateU.valueOf(endDate);
				date.deductFrequecyCode(frequency);

				//flow.setStartDate(date.getDate());
				flow.setCmpdStartDate(date.getDate());
				
				//tempStartDate = DateU.valueOf(flow.getStartDate());
				tempStartDate = DateU.valueOf(flow.getCmpdStartDate());

			} else {
				
				//flow.setEndDate(date.getDate());
				flow.setCmpdEnddate(date.getDate());

				date.deductFrequecyCode(frequency);

				tempStartDate = DateU.valueOf(commonUTIL.addSubtractDate(
						tempStartDate.getDate(), no, criteria));
				
				//flow.setStartDate(tempStartDate.getDate());
				flow.setCmpdStartDate(tempStartDate.getDate());
	
			}

			//if (commonUTIL.checkGreaterDate(flow.getEndDate(), tradeSettleDate)) {
			if (commonUTIL.checkGreaterDate(flow.getCmpdEnddate(), tradeSettleDate)) {
				
				if (flows.size() == 0) {
					Flows secondPrincipalFlow = new Flows();
					secondPrincipalFlow.setEndDate(endDate);
					secondPrincipalFlow.setPaymentDate(commonUTIL
							.addSubtractDate(secondPrincipalFlow.getEndDate(),
									lag));
					flows.add(secondPrincipalFlow);

				}

				if (!businessDayConvention.equals("NO_ADJUST")) {

					/*String stringStartDate = HolidayUtil
							.applyBusinessDayConvention(product
									.getIssueCurrency(), commonUTIL
									.dateToString(flow.getStartDate()),
									businessDayConvention);

					flow.setStartDate(commonUTIL.stringToDate(stringStartDate,
							true));*/
					
					String stringCmpdStartDate = HolidayUtil.applyBusinessDayConvention(product.getIssueCurrency(), 
							commonUTIL.dateToString(flow.getCmpdStartDate()),businessDayConvention);

					/*flow.setStartDate(commonUTIL.stringToDate(stringCmpdStartDate,
					true));*/
					
					flow.setCmpdStartDate(commonUTIL.stringToDate(stringCmpdStartDate,
							true));
					
				}

				/*flow.setPaymentDate(commonUTIL.addSubtractDate(
						flow.getEndDate(), lag));*/
				flow.setPaymentDate(commonUTIL.addSubtractDate(
						flow.getCmpdEnddate(), lag));

				// bdc for payment date is always FOLLOWING;
				String paymentDate = HolidayUtil.applyBusinessDayConvention(
						product.getIssueCurrency(),
						commonUTIL.dateToString(flow.getPaymentDate()),
						"FOLLOWING");

				flow.setPaymentDate((commonUTIL.stringToDate(paymentDate, true)));

				//date = DateU.valueOf(flow.getStartDate());
				date = DateU.valueOf(flow.getCmpdStartDate());
				/*System.out.println("Start Date " + flow.getStartDate());
				System.out.println("CmpdStart Date " + flow.getCmpdStartDate());
				System.out.println("CmpdEnd Date " + flow.getCmpdEnddate());
				*/
				
				flows.add(flow);
				
			} else {
				startaddingFlows = false;
			}

		}
		
/*		Flows interestFlow = new Flows();
		interestFlow.setStartDate(commonUTIL.stringToDate(product.getIssueDate(), true));
		interestFlow.setEndDate(commonUTIL.stringToDate(product.getMarturityDate(), true));
		interestFlow.setPaymentDate(commonUTIL.addSubtractDate(
				tradeSettleDate, lag));
		flows.add(interestFlow);*/
		
		Flows firstPrincipalFlow = new Flows();
		firstPrincipalFlow.setEndDate(tradeSettleDate);
		firstPrincipalFlow.setPaymentDate(commonUTIL.addSubtractDate(
				tradeSettleDate, lag));
		flows.add(firstPrincipalFlow);

		Collections.reverse(flows);

	}

	private void generateZCNAFlow(Trade trade, Product product, Coupon coupon) {

		Date issueDate = commonUTIL.stringToDate(product.getIssueDate(), true);
		Date maturityDate = commonUTIL.stringToDate(product.getMarturityDate(),
				true);
		Date tradeSettleDate = commonUTIL.stringToDate(
				trade.getDelivertyDate(), true);

		String businessDayConvention = coupon.getBusinessDayConvention();
		if(businessDayConvention == null) 
			businessDayConvention = "FOLLOWING";
		int lag = 0;

		DateU startDate = null;
		boolean startaddingFlows = false;
		startDate = DateU.valueOf(issueDate);

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

		if (!businessDayConvention.equals("NO_ADJUST")) {

			String stringStartDate = HolidayUtil.applyBusinessDayConvention(
					product.getIssueCurrency(),
					commonUTIL.dateToString(interestFlow.getStartDate()),
					businessDayConvention);

			interestFlow.setStartDate(commonUTIL.stringToDate(stringStartDate,
					true));

		}

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

	private void calculateflowsZC(Vector<Flows> flows2, Product product,
			Coupon coupon, Trade trade, Amortization amortizationObj) {

		if (amortizationObj.getAmortization().equals(
				MMConstants.SIMPLECOMPOUNDINGAMORTYPE)) {

			double paymentAmount = 0.0;
			double balanceAmount = 0.0;
			double interest = 0.0;
			double accrualDays = 0.0;
			double tradeAmount = trade.getTradeAmount();

			int payMutiflyingfactor = 0;
			int receiveMutiflyingfactor = 0;
			int count = 0;
			
			CountDay daycount = CountDay.valueOf(coupon.getDayCount());
			
			
			if (trade.getType().equalsIgnoreCase(TradeConstants.TRADETYPE_BUY)) {

				payMutiflyingfactor = 1;
				receiveMutiflyingfactor = -1;

			} else {

				payMutiflyingfactor = -1;
				receiveMutiflyingfactor = 1;

			}

			int flowSize = flows2.size();
			int oneLessFlowSize =flowSize - 1;
			
			double fixedRate = coupon.getFixedRate();
			double nominalAmount = trade.getTradeAmount();

			String amortizationtype = amortizationObj.getAmortization();

			MMCompounding mmCompoundingObject = new MMCompounding(
					nominalAmount, coupon);

			for (int i = 0; i < flowSize; i++) {
				// count is initialize to 1 do not make it 0; if zero than it will not match flowSize as compared below
				count ++;
				
				Flows flow = (Flows) flows2.elementAt(i);
				
				if (i == 0) {

					balanceAmount = tradeAmount * payMutiflyingfactor;

					flow.setFlowsdays(0);
					flow.setAmortizationType(amortizationtype);
					flow.setType(ProductConstants.PRINCIPAL);
					flow.setRate(fixedRate);
					flow.setNominal(nominalAmount);
					flow.setAccuralDays(0);
					flow.setCouponAmount(balanceAmount);

				} /*else if (i == 1) {

					balanceAmount = tradeAmount * receiveMutiflyingfactor;
					
					mmCompoundingObject.setCmpdStartDate(flow.getCmpdStartDate());
					mmCompoundingObject.setCmpdEndDate(flow.getCmpdEnddate());
					
					interest = mmCompoundingObject.calculateInterest();
					
					flow.setFlowsdays(0);
					flow.setAmortizationType(amortizationtype);
					flow.setType(ProductConstants.INTEREST);
					flow.setRate(fixedRate);
					flow.setNominal(nominalAmount);
					flow.setAccuralDays(0);
					flow.setCouponAmount(balanceAmount);

				}*/ else if (i < oneLessFlowSize) {
					
					DateU cmpdStartDate = DateU.valueOf(flow.getCmpdStartDate());
					DateU cmpdEndDate = DateU.valueOf(flow.getCmpdEnddate());
					
					mmCompoundingObject.setCmpdStartDate(cmpdStartDate.getDate());
					mmCompoundingObject.setCmpdEndDate(cmpdEndDate.getDate());
				

					/*accrualsDays = commonUTIL.jodaDiffInDays(
							couponStartDate.getDate(), couponEndDate.getDate());*/
					
					accrualDays = daycount.dayDiff(cmpdStartDate, cmpdEndDate);
					
					if (i == 1) {
						
						flow.setStartDate(commonUTIL.stringToDate(product.getIssueDate(), true));
						flow.setEndDate(commonUTIL.stringToDate(product.getMarturityDate(), true));
						
						interest = mmCompoundingObject.calculateInterest();

						flow.setNominal(tradeAmount);
						flow.setInterest(interest);
						//flow.setType(ProductConstants.INTEREST);

						setAccuralFlow(flow);
						
					} else {
						
						balanceAmount = mmCompoundingObject.calculateCompoundAmount();

						interest = mmCompoundingObject.calculateInterest();

						flow.setNominal(balanceAmount);
						flow.setInterest(interest);
						//flow.setType(ProductConstants.COMPOUNDEDINTEREST);
					}
					flow.setType(ProductConstants.COMPOUNDEDINTEREST);
					flow.setAccuralDays(accrualDays);
					flow.setRate(fixedRate);
					
					if (count == oneLessFlowSize) {

						paymentAmount = mmCompoundingObject.getPaymentAmount()  * receiveMutiflyingfactor;;
						flow.setCouponAmount(paymentAmount);
						flow.setAccuralAmount(paymentAmount);

						mmCompoundingObject.setTradeAmount(tradeAmount);
						mmCompoundingObject.setPaymentAmount(0.0);

		
					}

					
/*
					

					flow.setAccuralDays(0);

					
					 * balanceAmount = mmCompoundingObject
					 * .calculateCompoundAmount();
					 

					balanceAmount = tradeAmount;

					interest = mmCompoundingObject.calculateInterest();
					paymentAmount = mmCompoundingObject.calculateCompoundAmount() * receiveMutiflyingfactor;

					flow.setNominal(balanceAmount);
					flow.setInterest(interest);
					flow.setAccuralAmount(paymentAmount);
					flow.setCouponAmount(paymentAmount);

					if (i == 0) {

						setAccuralFlow(flow);
					}

					mmCompoundingObject.setPaymentAmount(0.0);
					mmCompoundingObject.setTradeAmount(tradeAmount);*/

				} else {

					flow.setFlowsdays(0);
					flow.setType(ProductConstants.PRINCIPAL);
					flow.setRate(fixedRate);
					flow.setAccuralDays(0);

					balanceAmount = trade.getTradeAmount();
					double couponAmount =  balanceAmount * receiveMutiflyingfactor;		

					flow.setNominal(balanceAmount);
					// flow.setPaymentAmount(balanceAmount);
					flow.setCouponAmount(couponAmount);
					flow.setAccuralAmount(couponAmount);

				}

			}

		} else {

			calculateflows(flows2, product, coupon, trade, amortizationObj);
		}

	}

	private void generateCompoundingFlow(Date issueDate, Date maturityDate,
			Date tradeSettleDate, String freq, Product product, Coupon coupon) {

		DateU date = null;
		DateU tempStartDate = null;

		String businessDayConvention = coupon.getBusinessDayConvention();
		boolean startaddingFlows = true;

		int lag = 0;
		int flowCount = 0;

		Date endDate = maturityDate;

		int no = -1 * frequencyUtil.frequencyNumberToSubtract(freq);

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
/*				System.out.println("tempStartDate0" + tempStartDate.getDate());
				System.out.println("if " + flow.getStartDate());
				System.out.println("if " + flow.getEndDate());
*/
			} else {

				flowCount = 2;
				System.out.println("date " + date.getDate());
				flow.setEndDate(date.getDate());

				date = DateU.valueOf(endDate);
				// date.deductFrequecyCode(freq);
				// System.out.println("tempStartDate1 " +
				// tempStartDate.getDate());
				tempStartDate = DateU.valueOf(commonUTIL.addSubtractDate(
						flow.getEndDate(), no, criteria));
				// tempStartDate.deductFrequecyCode(freq);
				// System.out.println("tempStartDate2 " +
				// tempStartDate.getDate());
				flow.setStartDate(tempStartDate.getDate());
				System.out.println("else " + flow.getStartDate());
				System.out.println("else " + flow.getEndDate());

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
			String compoundingFrequency) {

		DateU date;
		DateU tempStartDate = null;
		boolean flag = true;
		Vector<Flows> newFlows = new Vector<Flows>();
		int k = 0;

		String businessDayConvention = coupon.getBusinessDayConvention();

		int flowSize = flows2.size();
		int oneLessFlowSize = flowSize - 1;
		for (int i = 0; i < flowSize; i++) {

			Flows flow = (Flows) flows2.elementAt(i);

			if (i == 0) {

				newFlows.add(flow);

			} else if (i < oneLessFlowSize) {

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

						date.deductFrequecyCode(compoundingFrequency);
						subFlow.setCmpdStartDate(date.getDate());
						tempStartDate = DateU.valueOf(subFlow
								.getCmpdStartDate());

					} else {

						date = DateU.valueOf(tempStartDate.getDate());
						date.deductFrequecyCode(compoundingFrequency);
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
					System.out.println("Interest " + subFlow.getStartDate());
					System.out.println("Interest " + subFlow.getEndDate());
					System.out.println("CMPD Start"
							+ subFlow.getCmpdStartDate());
					System.out.println("CMPD End" + subFlow.getCmpdEnddate());

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
				 */
				
				newFlows.add(flow);

			}

		}

		Collections.reverse(newFlows);
		flows.clear();
		flows.addAll(newFlows);
	}

	private void calculateCompoundingflows(Vector<Flows> flows2,
			Date tradeSettleDate, Product product, Coupon coupon, Trade trade,
			String compoundingFrequency, String amortizationtype) {

		double paymentAmount = 0.0;
		double balanceAmount = 0.0;
		double interest = 0.0;
		double tradeAmount = trade.getTradeAmount();

		int j = 0;
		int k = 0;
		int l = 0;
		int payMutiflyingfactor = 0;
		int receiveMutiflyingfactor = 0;
		
		if (trade.getType().equals(TradeConstants.TRADETYPE_BUY)) {
			
			payMutiflyingfactor = 1;
			receiveMutiflyingfactor = -1;
			
		} else {
			
			payMutiflyingfactor = -1;
			receiveMutiflyingfactor = 1;

		}
		
		int noOfCompoundingCashflows = frequencyUtil
				.noOfCompoundingCashflows(compoundingFrequency)
				/ frequencyUtil.noOfCompoundingCashflows(coupon
						.getCouponFrequency());
		int flowSize = flows2.size();
		int oneLessFlowSize =flowSize - 1;

		double fixedRate = coupon.getFixedRate();
		double nominalAmount = trade.getTradeAmount();

		MMCompounding mmCompoundingObject = new MMCompounding(nominalAmount,
				coupon);

		for (int i = 0; i < flowSize; i++) {

			Flows flow = (Flows) flows2.elementAt(i);

			if (i == 0) {

				balanceAmount = tradeAmount * payMutiflyingfactor;

				flow.setFlowsdays(0);
				flow.setAmortizationType(amortizationtype);
				flow.setType(ProductConstants.PRINCIPAL);
				flow.setRate(fixedRate);
				flow.setNominal(nominalAmount);
				flow.setAccuralDays(0);
				flow.setCouponAmount(balanceAmount);

			} else if (i < (flowSize - 1)) {

				j++;
				k++;

				mmCompoundingObject.setCmpdStartDate(flow.getCmpdStartDate());
				mmCompoundingObject.setCmpdEndDate(flow.getCmpdEnddate());

				// flow.setType(ProductConstants.INTEREST);
				flow.setRate(fixedRate);

				flow.setAccuralDays(0);

				if (k != 1) {

					l = i;

					balanceAmount = mmCompoundingObject.calculateCompoundAmount() * receiveMutiflyingfactor;

					interest = mmCompoundingObject.calculateInterest();

					flow.setNominal(balanceAmount);
					flow.setInterest(interest);

				} else {

					interest = mmCompoundingObject.calculateInterest();

					flow.setNominal(tradeAmount);
					flow.setInterest(interest);

					setAccuralFlow(flow);
				}

				if (j == noOfCompoundingCashflows) {

					paymentAmount = mmCompoundingObject.getPaymentAmount();
					flow.setCouponAmount(paymentAmount);
					flow.setAccuralAmount(paymentAmount);

					mmCompoundingObject.setTradeAmount(tradeAmount);
					mmCompoundingObject.setPaymentAmount(0.0);

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

				balanceAmount = trade.getTradeAmount()
						* receiveMutiflyingfactor;

				flow.setNominal(balanceAmount);
				// flow.setPaymentAmount(balanceAmount);
				flow.setCouponAmount(balanceAmount);
				flow.setAccuralAmount(balanceAmount);

			}

		}

	}

	private void calculateScheduleflows(Vector<Flows> flows2,
			Date tradeSettleDate, Product product, Coupon coupon, Trade trade,
			double yield, Amortization amortizationObj) {

		Date startDate;
		Date endDate;
		double interestRate = 0.0;
		double amount = 0.0;
		double balanceAmount = 0.0;
		double outstandingAmount = 0.0;
		double fixedInterest = 0.0;
		double nominalAmount = trade.getTradeAmount();

		int payMutiflyingfactor = 1;
		int receiveMutiflyingfactor = -1;

		String scheduledValue = amortizationObj.getScheduledValue();

		String flowArr[] = scheduledValue.split("\\|");

		String detailsArr[];

		int flowArrLength = flowArr.length;
		int frequencyMonths = frequencyUtil.frequencyNumberToSubtract(coupon
				.getCouponFrequency());
		// first Principal flow

		for (int i = 0; i < flowArrLength; i++) {

			Flows flow = new Flows();

			detailsArr = flowArr[i].split(":");

			startDate = commonUTIL.stringToDate(detailsArr[0], true);
			endDate = commonUTIL.stringToDate(detailsArr[1], true);
			interestRate = Double.parseDouble(detailsArr[2]);
			amount = Double.parseDouble(detailsArr[3]);

			flow.setStartDate(startDate);
			flow.setEndDate(endDate);
			flow.setRate(interestRate);

			if (i == 0) {
				Flows firstPrincipalflow = new Flows();
				firstPrincipalflow
						.setAmortizationType(MMConstants.SCHEDULEAMORTYPE);
				firstPrincipalflow.setNominal(amount);

				balanceAmount = trade.getTradeAmount() * payMutiflyingfactor;
				outstandingAmount = balanceAmount;
				fixedInterest = (double) Math.round(balanceAmount
						* ((interestRate) / 100) * 100) / 100;

				firstPrincipalflow.setType(ProductConstants.PRINCIPAL);
				firstPrincipalflow.setBalanceAmount(balanceAmount);
				firstPrincipalflow.setOutstandingAmount(outstandingAmount
						* payMutiflyingfactor);
				flows.add(firstPrincipalflow);

				outstandingAmount = trade.getTradeAmount();

			}
			/*
			 * System.out.println(detailsArr[0]);
			 * System.out.println(detailsArr[1]);
			 * System.out.println(detailsArr[2]);
			 * System.out.println(detailsArr[3]);
			 */

			flow.setOutstandingAmount(outstandingAmount
					* receiveMutiflyingfactor);
			MMSchedule scheduleObj = new MMSchedule(startDate, endDate,
					interestRate, amount, outstandingAmount, frequencyMonths);

			balanceAmount = scheduleObj.calculatePayment();
			fixedInterest = scheduleObj.getInterest();
			outstandingAmount = balanceAmount;

			flow.setType(ProductConstants.INTEREST);

			flow.setFixedAmountPayment(amount);
			flow.setBalanceAmount(balanceAmount);
			flow.setCouponAmount(balanceAmount);
			flow.setInterest(fixedInterest);
			flow.setRate(interestRate);
			flow.setNominal(nominalAmount);
			flow.setAccuralAmount(fixedInterest);
			flow.setCouponAmount(balanceAmount);
			flow.setFixedInterest(fixedInterest);

			flows.add(flow);
		}

	}

	private void calculateflows(Vector<Flows> flows2, Product product,
			Coupon coupon, Trade trade, Amortization amortizationObj) {

		double balanceAmount = 0.0;
		double outstandingAmount = 0.0;

		int payMutiflyingfactor = 0;
		int receiveMutiflyingfactor = 0;

		long accrualsDays = 0;

		double fixedRate = coupon.getFixedRate();
		double nominalAmount = trade.getQuantity();
		
		if (trade.getType().equalsIgnoreCase(TradeConstants.TRADETYPE_BUY)) {

			payMutiflyingfactor = 1;
			receiveMutiflyingfactor = -1;

		} else {

			payMutiflyingfactor = -1;
			receiveMutiflyingfactor = 1;

		}

		long noofMonths = commonUTIL.diffInMonths(
				commonUTIL.stringToDate(amortizationObj.getStartDate(), true),
				commonUTIL.stringToDate(amortizationObj.getEndDate(), true));

		MMAmortization mmAmortizationObject = MMAmortizationFactory
				.getMMAmortizationObject(amortizationObj, trade, coupon,
						product, noofMonths, flows2);
		
		CountDay daycount = mmAmortizationObject.getDayCount();
		
		int flowSize = flows2.size();
		int oneLessFlowSize =flowSize - 1;
		
		for (int i = 0; i < flowSize; i++) {

			Flows flow = (Flows) flows.elementAt(i);

			if (i == 0) {

				flow.setFlowsdays(0);
				flow.setAmortizationType(amortizationObj.getAmortization());
				flow.setType(ProductConstants.PRINCIPAL);
				flow.setRate(fixedRate);
				flow.setNominal(nominalAmount);
				flow.setAccuralDays(0);

				balanceAmount = trade.getQuantity() * payMutiflyingfactor;
				outstandingAmount = balanceAmount * payMutiflyingfactor;

				// for MM balance loan amount is Payment Amount
				// flow.setPaymentAmount(balanceAmount);
				flow.setOutstandingAmount(balanceAmount);
				flow.setCouponAmount(balanceAmount);
				flow.setBalanceAmount(balanceAmount);

			} else if (i < oneLessFlowSize) {

				// calculateFixedInterest method requires daycount facctor which
				// in turn requires coupon start date and coupon end date
				// which are absent in first and last flow. So the calculateFixedInterest method is
				// used for in between coupons
				double fixedInterest = mmAmortizationObject
						.calculateFixedInterest();

				DateU couponStartDate = DateU.valueOf(flow.getStartDate());
				DateU couponEndDate = DateU.valueOf(flow.getEndDate());

				/*accrualsDays = commonUTIL.jodaDiffInDays(
						couponStartDate.getDate(), couponEndDate.getDate());*/
				accrualsDays = daycount.dayDiff(couponStartDate, couponEndDate);
				
				balanceAmount = mmAmortizationObject.calculatePayment() * receiveMutiflyingfactor;

				flow.setFlowsdays(accrualsDays);
				flow.setRate(fixedRate);
				flow.setNominal(nominalAmount);
				flow.setAccuralDays(accrualsDays);
				flow.setBalanceAmount(balanceAmount);
				// flow.setPaymentAmount(balanceAmount);
				flow.setOutstandingAmount(outstandingAmount
						* receiveMutiflyingfactor);
				flow.setAccuralAmount(fixedInterest);
				flow.setCouponAmount(balanceAmount);
				flow.setInterest(mmAmortizationObject.getInterest());
				flow.setFixedInterest(fixedInterest);

				flow.setType(ProductConstants.INTEREST);

				outstandingAmount = balanceAmount;

				if (i == 1) {

					setAccuralFlow(flow);

				}

			} else {

				flow.setFlowsdays(0);
				flow.setType(ProductConstants.PRINCIPAL);
				flow.setRate(coupon.getFixedRate());
				flow.setNominal(nominalAmount);
				flow.setAccuralDays(0);

				balanceAmount = trade.getQuantity()
						* receiveMutiflyingfactor;

				flow.setBalanceAmount(balanceAmount);
				// flow.setPaymentAmount(balanceAmount);
				flow.setOutstandingAmount(outstandingAmount);
				flow.setCouponAmount(balanceAmount);
				flow.setAccuralAmount(balanceAmount);

			}

		}

	}

	private void generateFlow(int noOfCashFlows, Date issueDate,
			Date maturityDate, Date tradeSettleDate, String freq,
			String issueCurrency, Coupon coupon) {

		//DateU date = null;
		//DateU tempStartDate = null;
		Date endDate = maturityDate;

		int lag = 0;
		//int flowCount = 0;
		int no = -1 * frequencyUtil.frequencyNumberToSubtract(freq);

		String criteria = frequencyUtil.getCriteria(freq);
		String businessDayConvention = coupon.getBusinessDayConvention();
		//boolean startaddingFlows = true;
			
		Flows endPrincipalFlow = new Flows();
		endPrincipalFlow.setEndDate(endDate);
		
		Date firstFlowPayMentDate = commonUTIL.stringToDate(HolidayUtil.applyBusinessDayConvention(issueCurrency, 
				commonUTIL.dateToString(commonUTIL.addSubtractDate(endDate, lag)), 
				businessDayConvention), true);
		
		endPrincipalFlow.setPaymentDate(firstFlowPayMentDate);
		flows.add(endPrincipalFlow);
		
		LinkedHashMap<Date, Date> dateHashTable = CashFlowUtil.getCashFlowDates(tradeSettleDate, endDate, no, criteria,
				businessDayConvention, issueCurrency);
		int i =0;
		for (Date startDate: dateHashTable.keySet()) {
			
			Flows flow = new Flows();
			flow.setStartDate(startDate);
			Date flowEndDate = dateHashTable.get(startDate);
			flow.setEndDate(flowEndDate);
			flow.setPaymentDate(flowEndDate);
			
			if (i == 0) {
				i++;
				Date lastInterestPayMentDate = commonUTIL.stringToDate(HolidayUtil.applyBusinessDayConvention(issueCurrency, 
						commonUTIL.dateToString(commonUTIL.addSubtractDate(flowEndDate, lag)), 
						businessDayConvention), true);
				
				flow.setPaymentDate(lastInterestPayMentDate);
			}
			
			
			flows.add(flow);
		}
		
		Flows firstPrincipalFlow = new Flows();
		firstPrincipalFlow.setEndDate(tradeSettleDate);
		
		Date payMentDate = commonUTIL.stringToDate(HolidayUtil.applyBusinessDayConvention(issueCurrency, 
						commonUTIL.dateToString(commonUTIL.addSubtractDate(tradeSettleDate, lag)), 
						businessDayConvention), true);
						
		firstPrincipalFlow.setPaymentDate(payMentDate);
		flows.add(firstPrincipalFlow);
		
/*		while (startaddingFlows) {

			Flows flow = new Flows();
			flow.setEndDate(endDate);

			date = DateU.valueOf(endDate);
			date.deductFrequecyCode(freq);

			flow.setStartDate(date.getDate());
			
			if (flowCount == 0) {
				flowCount++;
				flow.setEndDate(endDate);

				date = DateU.valueOf(endDate);
				date.deductFrequecyCode(freq);

				flow.setStartDate(date.getDate());
				tempStartDate = DateU.valueOf(flow.getStartDate());
				//System.out.println("tempStartDate0" + tempStartDate.getDate());
				System.out.println("if " + flow.getStartDate());
				System.out.println("if " + flow.getEndDate());
			} else {
				flowCount = 2;
				//System.out.println("date " + date.getDate());
				flow.setEndDate(date.getDate());

				date = DateU.valueOf(endDate);
				tempStartDate = DateU.valueOf(commonUTIL.addSubtractDate(flow.getEndDate(), no, criteria));
				flow.setStartDate(tempStartDate.getDate());
				//System.out.println("else " + flow.getStartDate());
				//System.out.println("else " + flow.getEndDate());

			}

			if (commonUTIL.checkGreaterDate(flow.getEndDate(), tradeSettleDate)) {
			
				if (flows.size() == 0) {
					Flows secondPrincipalFlow = new Flows();
					secondPrincipalFlow.setEndDate(endDate);
					secondPrincipalFlow.setPaymentDate(commonUTIL.addSubtractDate(secondPrincipalFlow.getEndDate(),	lag));
					flows.add(secondPrincipalFlow);

				} else {

					if (commonUTIL.checkGreaterDate(tradeSettleDate,
							flow.getStartDate())) {
						flow.setStartDate(tradeSettleDate);
						startaddingFlows = false;
					}

					if (!businessDayConvention.equals("NO_ADJUST")) {
						String stringStartDate = HolidayUtil
								.applyBusinessDayConvention(product.getIssueCurrency(), 
										commonUTIL.dateToString(flow.getStartDate()), businessDayConvention);

						flow.setStartDate(commonUTIL.stringToDate(stringStartDate, true));
					}

					flow.setPaymentDate(commonUTIL.addSubtractDate(
							flow.getEndDate(), lag));

					// bdc for payment date is always FOLLOWING;
					String paymentDate = HolidayUtil
							.applyBusinessDayConvention(product.getIssueCurrency(), 
									commonUTIL.dateToString(flow.getPaymentDate()),"FOLLOWING");

					flow.setPaymentDate((commonUTIL.stringToDate(paymentDate, true)));
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
		firstPrincipalFlow.setPaymentDate(commonUTIL.addSubtractDate(tradeSettleDate, lag));
		flows.add(firstPrincipalFlow);
*/
		Collections.reverse(flows);
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
