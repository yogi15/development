package productPricing;

import productPricing.pricingUtil.BondCashFlow;
import beans.Coupon;
import beans.Flows;
import beans.Product;
import beans.Trade;

public class BONDZCPricing extends BONDPricing {
	
	
	    Product product = null;
	    Trade trade = null;
		double price = 0.0;
		double principal = 0.0;
		double quantity = 0.0;
		double settlementAmount = 0.0;
		double yield = 0.0;
		double dayCount = 0;
		double couponDays =0;
		Flows principalFlow = new Flows();
		BondCashFlow cashFlow = null;
		Coupon coupon = null;
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
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
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
		public double getSettlementAmount() {
			return settlementAmount;
		}
		public void setSettlementAmount(double settlementAmount) {
			this.settlementAmount = settlementAmount;
		}
		public double getYield() {
			return yield;
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
		public Flows getPrincipalFlow() {
			return principalFlow;
		}
		public void setPrincipalFlow(Flows principalFlow) {
			this.principalFlow = principalFlow;
		}
		public BondCashFlow getCashFlow() {
			return cashFlow;
		}
		public void setCashFlow(BondCashFlow cashFlow) {
			this.cashFlow = cashFlow;
		}
		public Coupon getCoupon() {
			return coupon;
		}
		public void setCoupon(Coupon coupon) {
			this.coupon = coupon;
		}
		public void price(Trade trade,Product product,Coupon coupon) {
			setProduct(product);
			setTrade(trade);
			setCoupon(coupon);
			BondCashFlow cashFlow = generateCashFlow();
			setCashFlow(cashFlow);
			Flows accrualFlow = cashFlow.getAccuralFlow();
			Flows principalFlow = cashFlow.getPrincipalFlow();
			//setPv(principalFlow.getTotalDiscountPrice());
			setAccuralFlow(accrualFlow);
			setPrincipalFlow(principalFlow);
			setPrice(principalFlow.getCouponAmount() - accrualFlow.getCouponAmount());
			Double quant = new Double(trade.getQuantity());
			//System.out.println(quant.doubleValue());
		
			setPrincipal(getPrice() * quant.doubleValue() );
			setPv(getPrice());
		}
		public BondCashFlow generateCashFlow() {
			cashFlow = new BondCashFlow();
			cashFlow.genearteCashFlow(getProduct(),getCoupon(),getTrade(),this);
			return getCashFlow();
		}

}
