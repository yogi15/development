package product.amortization;

import java.util.Vector;

import constants.MMConstants;

import beans.Amortization;
import beans.Coupon;
import beans.Flows;
import beans.Product;
import beans.Trade;

public class MMAmortizationFactory {

	public static  MMAmortization getMMAmortizationObject(Amortization amortizationObj,
			Trade trade, Coupon coupon, Product product, long noofMonths, Vector<Flows> flows) {
		
		String amortizationType = amortizationObj.getAmortType();
		
		if (amortizationType.equalsIgnoreCase(MMConstants.MORTGAGEAMORTYPE)) {

			return new MMMortgage(amortizationObj, trade, coupon, noofMonths);

		} /*else if (amortizationType.equalsIgnoreCase("Annual")) {

			return new MMAnnuity(amortizationObj, trade, coupon, noofMonths);

		} else if (amortizationType.equalsIgnoreCase("Equal")) {

			return new MMEqual(amortizationObj, trade, coupon, noofMonths);

		}*/  else if (amortizationType.equalsIgnoreCase(MMConstants.NONEAMORTYPE) || amortizationType.equalsIgnoreCase(MMConstants.SCHEDULEAMORTYPE) 
				|| amortizationType.equalsIgnoreCase(MMConstants.NACOMPOUNDINGTYPE) 
				|| amortizationType.equalsIgnoreCase(MMConstants.SIMPLECOMPOUNDINGAMORTYPE)) {
			
			// here SIMPLECOMPOUNDINGAMORTYPE is for ZC and not for other frequency. Simple compounding for other frequencies the method in MMCashflow
			// directly instantiate an MMCompounding object
			
			return new MMNone(amortizationObj, trade, coupon, product, flows);

		}  

		throw new IllegalArgumentException("No such Amortization type");

	}

}
