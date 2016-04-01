package productPricing;

import java.util.Date;

import productPricing.pricingUtil.BondCashFlow;
import util.commonUTIL;
import util.common.CountDay;
import util.common.DateU;
import beans.Coupon;
import beans.Flows;
import beans.Product;
import beans.Trade;

public class BONDPricing extends Pricer {
	
    Product product = null;
    Trade trade = null;
	double price = 0.0;
	double dirtyPrice = 0.0;
	double cleanPrice = 1.0;  // going to come market data
	double nominal = 0.0;
	double principal = 0.0;
	double quantity = 0.0;
	double accural = 0.0;
	double pv = 0.0;
	double npv = 0.0;
	double settlementAmount = 0.0;
	double yield = 0.0;
	double dayCount = 0;
	double couponDays =0;
	Flows principalFlow = new Flows();
	BondCashFlow cashFlow = null;
	Coupon coupon = null;
	
	// new variables added
	double accrualAmount = 0.0;
	double couponAmount = 0.0;
	long accrualDays = 0;
	Date nextCouponDate = null;
	Date previousCouponDate = null; 
	double totalAmount = 0.0;
	
	double accrualInterest = 0.0;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDirtyPrice() {
		return dirtyPrice;
	}
	public void setDirtyPrice(double dirtyPrice) {
		this.dirtyPrice = dirtyPrice;
	}
	public double getCleanPrice() {
		return cleanPrice;
	}
	public void setCleanPrice(double cleanPrice) {
		this.cleanPrice = cleanPrice;
	}
	public double getNominal() {
		return nominal;
	}
	
	public double getAccrualAmount() {
		return accrualAmount;
	}

	public void setAccrualAmount(double accrualAmount) {
		this.accrualAmount = accrualAmount;
	}

	public double getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(double couponAmount) {
		this.couponAmount = couponAmount;
	}

	public long getAccrualDays() {
		return accrualDays;
	}

	public void setAccrualDays(long accrualDays) {
		this.accrualDays = accrualDays;
	}

	public Flows getAccuralFlows() {
		return accuralFlows;
	}

	public void setAccuralFlows(Flows accuralFlows) {
		this.accuralFlows = accuralFlows;
	}
	
	public Date getNextCouponDate() {
		return nextCouponDate;
	}

	public void setNextCouponDate(Date nextCouponDate) {
		this.nextCouponDate = nextCouponDate;
	}

	public Date getPreviousCouponDate() {
		return previousCouponDate;
	}

	public void setPreviousCouponDate(Date previousCouponDate) {
		this.previousCouponDate = previousCouponDate;
	}
	
	public double getAccrualInterest() {
		return accrualInterest;
	}

	public void setAccrualInterest(double accrualInterest) {
		this.accrualInterest = accrualInterest;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public void price(Trade trade, Product product, Coupon coupon) {

		setProduct(product);
		setTrade(trade);
		setCoupon(coupon);

		calculateYield(trade.getPrice());
		
	}
	/*
	public void price(Trade trade,Product product,Coupon coupon) {

	
	setProduct(product);
	setTrade(trade);
	
	setCoupon(coupon);
	calculateYield(trade.getPrice());
	BondCashFlow cashFlow = generateCashFlow();
	Flows accrualFlow = cashFlow.getAccuralFlow();
	Flows principalFlow = cashFlow.getPrincipalFlow();
	if(accrualFlow != null) {
	//setPv(principalFlow.getTotalDiscountPrice());
	setAccuralFlow(accrualFlow);
	setPrincipalFlow(principalFlow);
	double accural = accrualFlow.getAccuralInterest();
	
	double accuralcouponamt = ((trade.getQuantity() * (accrualFlow.getRate()/100)))/accrualFlow.getFreqinYear();
	accrualFlow.setCouponAmount(accuralcouponamt);
	accrualFlow.setAccuralAmount(accuralcouponamt * (accrualFlow.getAccuralDays()/accrualFlow.getFlowsdays()) );
	double totalDFamount = principalFlow.getTotalDiscountPrice();
	
	setCleanPrice(trade.getQuantity() * (trade.getPrice()/100));
	setDirtyPrice((getCleanPrice() + accrualFlow.getAccuralAmount()) * 100/trade.getQuantity());
	Double quant = new Double(trade.getQuantity());
	//System.out.println(quant.doubleValue());
	setPrice((accrualFlow.getNominal() + accrualFlow.getDiscountedAmt())  );
	setPrincipal((accrualFlow.getNominal() + accrualFlow.getDiscountedAmt()) * quant.doubleValue() );
	setPv(totalDFamount);
	
	}
		
	}
	
	*/
	
	@Override
	public void setTradeData(DefaultCashFlow bondCashFlow) {

		Trade trade = getTrade();
		Coupon coupon = getCoupon();
		Product product = getProduct();

		CountDay daycount = CountDay.valueOf(coupon.getDayCount());

		String frequency = coupon.getCouponFrequency();
		Flows accrualFlow = bondCashFlow.getAccuralFlow();
		
		Date endDate = accrualFlow.getEndDate();
		setNextCouponDate(endDate);

		Date startDate = accrualFlow.getStartDate();

		DateU productStartDate = DateU.valueOf(startDate);
		DateU tradeSttleDate = DateU.valueOf(commonUTIL.stringToDate(
				trade.getDelivertyDate(), true));

		if (commonUTIL.checkGreaterDate(
				commonUTIL.stringToDate(product.getIssueDate(), true),
				productStartDate.getDate())) {

			productStartDate = DateU.valueOf(commonUTIL.stringToDate(
					product.getIssueDate(), true));

		}
		
		setPreviousCouponDate(productStartDate.getDate());
		System.out.println();
		/*long accrualsDays = commonUTIL.jodaDiffInDays(
		productStartDate.getDate(), tradeSttleDate.getDate());*/
		
		long accrualsDays = daycount.dayDiff(productStartDate, tradeSttleDate);
			
		double dayCountFactor = daycount.getDayCountFactor(productStartDate,
				tradeSttleDate);

		int coupondays = daycount.dayDiff(productStartDate, tradeSttleDate);

		accrualAmount = trade.getTradeAmount() * (coupon.getFixedRate() / 100)
				* dayCountFactor;

		setAccrualDays(accrualsDays);
		setAccrualAmount(accrualAmount);
		setAccrualInterest(coupon.getFixedRate());
		setAccural(accrualAmount);
		setCleanPrice( trade.getPrice() );
		setTotalAmount(trade.getNominal() + accrualAmount);
		setDirtyPrice(getCleanPrice() + accrualAmount);
		
		quantity = trade.getTradeAmount() / product.getFaceValue();
		setQuantity(quantity);
	}
	
	
	public  void setAccuralFlow(Flows accrualFlow) {
		// TODO Auto-generated method stub
		this.accuralFlows = accrualFlow;
	}
	public Flows getAccuralFlow() {
		// TODO Auto-generated method stub
		return accuralFlows;
	}
	
	
	public void setNominal(double nominal) {
		this.nominal = nominal;
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getAccural() {
		return accural;
	}
	public void setAccural(double accural) {
		this.accural = accural;
	}
	public double getPv() {
		return pv;
	}
	public void setPv(double pv) {
		this.pv = pv;
	}
	public double getNpv() {
		return npv;
	}
	public void setNpv(double npv) {
		this.npv = npv;
	}
	public double getSettlementAmount() {
		return settlementAmount;
	}
	public void setSettlementAmount(double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	public double getYield() {
		
		
		
		return yield;
	}
	public void calculateYield(double marketValue) {
		// TODO Auto-generated method stub
		if(marketValue == 0) {
			setYield(0);
			return;
		}
		
		
		double captianGain =  product.getFaceValue() - marketValue;
		DateU dateissue = DateU.valueOf(commonUTIL.stringToDate(product.getIssueDate(),true));
		int noofyeartillMaturity = DateU.yearDiff(commonUTIL.stringToDate(product.getMarturityDate(),true));
		double annualasedCapitalGain = captianGain/noofyeartillMaturity;
		double annualInterest = trade.getNominal() * (coupon.getFixedRate()/100);
		double totalAnnualReturn = annualInterest +  annualasedCapitalGain;
		double yieldOnMarketValue = (totalAnnualReturn/marketValue) * 100;
		double yieldOnParValue = ( totalAnnualReturn/(product.getFaceValue()-annualasedCapitalGain))*100;
		double yield = (yieldOnMarketValue+yieldOnParValue)/2;
		
		setYield(yield);
		
		
	}
	public void setYield(double yield) {
		this.yield = yield;
	}
	public double getDayCount() {
		return dayCount;
	}
	public void setDayCount(double dayCount) {
		this.dayCount = dayCount;
	}
	public double getCouponDays() {
		return couponDays;
	}
	public void setCouponDays(double couponDays) {
		this.couponDays = couponDays;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public BondCashFlow getCashFlow() {
		return cashFlow;
	}
	public void setCashFlow(BondCashFlow cashFlow) {
		this.cashFlow = cashFlow;
	}
	
	
	public BondCashFlow generateCashFlow() {
		cashFlow = new BondCashFlow();
		cashFlow.genearteCashFlow(getProduct(),getCoupon(),getTrade(),this);
		return getCashFlow();
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
	}
	public Flows getPrincipalFlow() {
		return principalFlow;
	}
	public void setPrincipalFlow(Flows principalFlow) {
		this.principalFlow = principalFlow;
	}
	
	
	Flows accuralFlows = null;
	@Override
	public DefaultCashFlow genearteCashFlow() {
		return (BondCashFlow) generateCashFlow();
		
	}
	
	
	
	
	
}
