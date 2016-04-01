package productPricing;

public class PricerFactory {
		
	public static  Pricer getProductPricer( String productType ) {
	
		if (productType.equalsIgnoreCase("BOND")) {

			return new BONDPricing();

		} 

		throw new IllegalArgumentException("No such Product type");

	}

}