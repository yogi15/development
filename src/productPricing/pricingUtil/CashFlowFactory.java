package productPricing.pricingUtil;
import productPricing.DefaultCashFlow;

public class CashFlowFactory {
	
	public static  DefaultCashFlow getProductCashflow( String productType ) {
		
		if (productType.equalsIgnoreCase("BOND")) {

			return new BondCashFlow();

		} 

		throw new IllegalArgumentException("No such Product type");

	}
	
}
