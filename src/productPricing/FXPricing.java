package productPricing;

import productPricing.pricingUtil.BondCashFlow;
import productPricing.pricingUtil.FXCashFlow;
import productPricing.pricingUtil.MMCashFlow;
import beans.Coupon;
import beans.Flows;
import beans.Product;
import beans.Trade;

public class FXPricing extends Pricer {

	
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
		FXCashFlow cashFlow = null;
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
		public Coupon getCoupon() {
			return coupon;
		}
		public void setCoupon(Coupon coupon) {
			this.coupon = coupon;
		}
		
		public FXCashFlow getCashFlow() {
			return cashFlow;
		}
		
	@Override
	public void price(Trade trade, Product product, Coupon coupon) {
		// TODO Auto-generated method stub
		setProduct(product);
		setTrade(trade);
		
		setCoupon(coupon);
	FXCashFlow cashFlow = generateCashFlow();
		
	}
	public FXCashFlow generateCashFlow() {
		cashFlow = new FXCashFlow();
		cashFlow.genearteCashFlow(getProduct(),getCoupon(),getTrade(),this);
		return getCashFlow();
	}
	@Override
	public DefaultCashFlow genearteCashFlow() {
		// TODO Auto-generated method stub
		return  (FXCashFlow) generateCashFlow();
	}
	@Override
	public void setTradeData(DefaultCashFlow flow) {
		// TODO Auto-generated method stub
		
	}

}
