package productPricing;


import productPricing.pricingUtil.RepoCashFlow;
import beans.Coupon;
import beans.Flows;
import beans.Product;
import beans.Trade;

public class REPOPricing  extends Pricer {

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
		RepoCashFlow cashFlow = null;
		Coupon coupon = null;
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
		public void price(Trade trade,Product product,Coupon coupon) {

			
			setProduct(product);
			setTrade(trade);
			
			setCoupon(coupon);
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
		public RepoCashFlow getCashFlow() {
			return cashFlow;
		}
		public void setCashFlow(RepoCashFlow cashFlow) {
			this.cashFlow = cashFlow;
		}
		public RepoCashFlow generateCashFlow() {
			cashFlow = new RepoCashFlow();
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
			// TODO Auto-generated method stub
			return (RepoCashFlow) generateCashFlow();
		}
		@Override
		public void setTradeData(DefaultCashFlow flow) {
			// TODO Auto-generated method stub
			
		}

}
