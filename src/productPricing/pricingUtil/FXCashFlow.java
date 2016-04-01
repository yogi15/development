package productPricing.pricingUtil;

import java.util.Date;
import java.util.Vector;

import beans.Coupon;
import beans.Flows;
import beans.Product;
import beans.Trade;
import productPricing.DefaultCashFlow;
import productPricing.Pricer;

public class FXCashFlow extends DefaultCashFlow {

	Vector<Flows>	 flows = new Vector<Flows>();
	int CountCashFlows;
	
	int noOfTotalDays;
	int noofDaysbetweenCashFlow;
	int noCoupons;
	Flows principalFlow = new Flows();
	Flows accuralFlow = null;
	Date startDate;
	Date endDate;
	
	public void genearteCashFlow(Product product,Coupon coupon,Trade trade,Pricer price) 
	 {
		
	 }
	
	private void calculateflowsZC(Vector<Flows> flows2, Date tradeSettleDate,Product product,Coupon coupon,int frequency,Trade trade,double yield) {
		double noOfyears = flows2.size();
	}
}
