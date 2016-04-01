package productPricing.pricingUtil;

import java.util.Date;
import java.util.Vector;

import beans.Coupon;
import beans.Flows;
import beans.Product;
import beans.Trade;

import util.commonUTIL;
import util.common.DateU;
import util.common.Period;

public class CashFlow {
	
	Vector<Flows>	 flows = new Vector<Flows>();
	int CountCashFlows;
	
	int noOfTotalDays;
	int noofDaysbetweenCashFlow;
	int noCoupons;
	Date startDate;
	Date endDate;
	
	public void genearteCashFlow(Product product,Coupon coupon,Trade trade) 
		 {
		System.out.println(" Frequency  == " + coupon.getCouponFrequency());
		Period periods = new Period(product.getTenor());
		System.out.println(periods.getCode());
		int f = frequencyUtil.fromString(coupon.getCouponFrequency());
		int period = periods.getCode();
		setCountCashFlows(period/f);
		String tradeSettlement = trade.getDelivertyDate();
		String issueDate = product.getIssueDate();
		String maturityDate = product.getMarturityDate();
		generateFlow(getCountCashFlows(),commonUTIL.stringToDate(issueDate,true),commonUTIL.stringToDate(maturityDate,true),f,commonUTIL.stringToDate(tradeSettlement, true));
		calculateflows(flows,commonUTIL.stringToDate(tradeSettlement, true));
		System.out.println(period/f);
    	
	//	periods.valueOf(tenor).getCode();
		
	}

	private void calculateflows(Vector<Flows> flows2, Date tradeSettleDate) {
		// TODO Auto-generated method stub
		DateU datesettle = DateU.valueOf(tradeSettleDate);
		for(int i=0;i<flows2.size();i++) {
			Flows flow = (Flows) flows.elementAt(i);
			if(i == 0) {
				int accrualsDays = datesettle.subractDates(flow.getStartDate());
				flow.setAccuralDays(accrualsDays);
				datesettle = DateU.valueOf(flow.getPaymentDate());
			} else {
				int accrualsDays = datesettle.subractDates(flow.getStartDate());
				flow.setAccuralDays(accrualsDays);
				datesettle = DateU.valueOf(flow.getPaymentDate());
			}
	//	System.out.println(flow.getStartDate() + " " + flow.getEndDate() + " " + flow.getAccuralDays());
		}
		
	}

	private void generateFlow(int noOfCashFlows, Date issueDate,
			Date maturityDate,int frequency,Date tradeDate) {
		// TODO Auto-generated method stub
	
	DateU date = null;
	boolean startaddingFlows = false;
	for(int i=0;i<noOfCashFlows;i++) {
		Flows flow = new Flows();
		
		if(i ==0 ) {
			flow.setStartDate(issueDate);
		    date = DateU.valueOf(flow.getStartDate());
		    
		    date.addDays(frequency);
		    flow.setEndDate(date.getDate());
		    date.addDays(1);
		    flow.setPaymentDate(date.getDate());
		   date = DateU.valueOf(flow.getEndDate());
		  if( commonUTIL.between2dates(flow.getStartDate(), flow.getEndDate(), tradeDate))
			  startaddingFlows = true;
		 
		  
		} else  {
			flow.setStartDate(date.getDate());
			 date = DateU.valueOf(flow.getStartDate());
			 date.addDays(frequency);
			    flow.setEndDate(date.getDate());
			    date.addDays(1);
			    flow.setPaymentDate(date.getDate());
			    date = DateU.valueOf(flow.getEndDate());
			    if( commonUTIL.between2dates(flow.getStartDate(), flow.getEndDate(), tradeDate))
					  startaddingFlows = true;
		}
	if(startaddingFlows)		
		flows.add(flow);
	}
	
		
	}

	public int getCountCashFlows() {
		return CountCashFlows;
	}

	public void setCountCashFlows(int countCashFlows) {
		CountCashFlows = countCashFlows;
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
	
	
	

}


	

