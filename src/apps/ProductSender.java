package apps;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import logAppender.ProductAppender;
import constants.logConstants;

import productPricing.BONDPricing;
import util.commonUTIL;
import beans.Coupon;
import beans.Product;
import beans.ProductUploaderBean;

public class ProductSender {
	
	private ProductUploaderBean bean = null;

	Hashtable attributeValue = new Hashtable();

	public Product buildProduct(ProductUploaderBean bean) {

		attributeValue = new Hashtable();
		
		String productShortname = "";
		String productType = "";
		
		if (bean == null) {

			commonUTIL.display("ProductUploaderBean ", "Bean is null");
			return null;

		}

		this.bean = bean;

		commonUTIL.display("Starting Deal Feed ", productShortname + ">>>>");

		productShortname = bean.getBondType();		
		productType = bean.getBondClass();
		
		setAttributes("ISIN", bean.getIsin());
		setAttributes("SettlementType", bean.getSettlementType());
		setAttributes("Common", bean.getCommon());

		Product product = new Product();
		
		String productName = getProductShortName(productShortname, productType);
		product.setProductType(productType);

		product.setName(productName);
		product.setProductname(productName);
		product.setProdcutShortName(productShortname);
		product.setQuantity(commonUTIL.replaceCommaInNumber(bean.getTotalIssue()));
		product.setIssueDate(bean.getIssueDate());
		product.setMarturityDate(bean.getMaturityDate());
		product.setIssuerId(bean.getIssuerId());
		product.setCountry(bean.getCountry());
		product.setIssuePrice(bean.getIssuerPrice());
		product.setIssueCurrency(bean.getIssueCurrency());
		product.setRedemptionPrice(Double.parseDouble(bean.getRedemptionPrice()));
		product.setRedemptionCurrency(bean.getRedemptionCurrency());
		product.setFaceValue(Double.parseDouble(bean.getFaceValue()));
		// product.setCode( bean.getCode);
		product.setDatedDate(bean.getDatedDate());
		product.setTenor(bean.getTenor());
		product.setAttributes(getAttributes());
		product.setIsPosition(1);
		
		

		/*
		 * Coupon coupon = new Coupon();
		 * coupon.setCouponType(bean.getCouponType()); coupon.setFixedRate(
		 * Double.parseDouble(bean.getCouponRate()) );
		 * coupon.setCCY(bean.getCouponCurrency());
		 * coupon.setDayCount(bean.getDayCount());
		 * coupon.setCouponAdjustMentMethod(0);
		 * coupon.setCouponFrequency(bean.getCouponFrequency());
		 * coupon.setBusinessDayConvention(bean.getBdc());
		 */

		attributeValue = null;
		commonUTIL.display("End Deal Feed", product.getId() + ">>>>");
		return product;

	}

	public Coupon buildCoupon(ProductUploaderBean bean) {

		Coupon coupon = new Coupon();
		coupon.setCouponType(bean.getCouponType());
		coupon.setFixedRate(Double.parseDouble(bean.getCouponRate()));
		coupon.setCCY(bean.getCouponCurrency());
		coupon.setDayCount(bean.getDayCount());
		coupon.setCouponAdjustMentMethod("");
		coupon.setCouponFrequency(bean.getCouponFrequency());
		coupon.setBusinessDayConvention(bean.getBdc());
		// coupon.setCouponDate(couponDate);
		coupon.setRating("");
		coupon.setEx_dividend(0);
		coupon.setRecordDays(0);
		coupon.setShutDays(0);
		coupon.setPriceDecimals(0);
		coupon.setYieldDecimals(0);
		coupon.setNominalDecimals(0);
		// coupon.setAnnounceDate(announceDate);
		// coupon.setAuctionDate(auctionDate);
		coupon.setWithholdingTax("");
		coupon.setApplyWithholdingTax("");
		// coupon.setWhenIssueBond(whenIssueBond);
		// coupon.setTickSize(tickSize);
		// coupon.setYieldMethod(yieldMethod);
		// coupon.setQuoteType(quoteType);
		coupon.setActivefrom("Adjusted");

		return coupon;
	}

	private String getProductShortName( String productShortname, String productType) {

		return new StringBuffer()
				.append(productType)
				.append(".")
				.append(productShortname)
				.append(".")
				.append(bean.getCouponFrequency())
				.append(".")
				.append(bean.getMaturityDate().substring(3, 5))
				.append(".")
				.append(bean.getMaturityDate().substring(0, 2))
				.append(".")
				.append(bean.getMaturityDate().substring(6,
						bean.getMaturityDate().length()))
				.append(".")
				.append(bean.getTenor())
				.append(".")
				.append(bean.getCountry())
				.append(".")
				.append(bean.getRedemptionCurrency())
				.append(".")
				.append(bean.getMaturityDate().substring(6,
						bean.getMaturityDate().length())).toString();
		
	}
	
	public void setAttributes(String name, String value) {
		attributeValue.put(attributeValue.size() + 1, name + "=" + value + ";");
	}

	public String getAttributes() {
		String attributes = "";
		// Hashtable attributeValue = new Hashtable();
		Collection c = attributeValue.values();
		Iterator itr = c.iterator();
		while (itr.hasNext()) {
			attributes = attributes + (String) itr.next();

		}
		return attributes;
	}
	
	public boolean validateProduct(ProductUploaderBean bean) {
		
		boolean isValid = true;
		
		StringBuffer errorMsg = new StringBuffer(
				"Following data missing for ISIN no. ").append(bean.getIsin());

		if (commonUTIL.isEmpty(bean.getBondClass())) {

			errorMsg.append(" BondClass");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getBondType())) {

			errorMsg.append(" Bondtype");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getIssuer())) {

			errorMsg.append(" Issuer");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getIssueDate())) {

			errorMsg.append(" Issue Date");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getDatedDate())) {

			errorMsg.append(" Dated Date");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getMaturityDate())) {

			errorMsg.append(" Maturity Date");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getTenor())) {

			errorMsg.append(" Tenor");
			isValid = false;
		}
		
		if (commonUTIL.isEmpty(bean.getCountry())) {

			errorMsg.append(" Country");
			isValid = false;
			
		}

		if (bean.getIssuerPrice() < 0 ) {

			errorMsg.append(" Issue Price");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getIssueCurrency())) {

			errorMsg.append(" Issue Currency");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getRedemptionPrice())) {

			errorMsg.append(" Redemption Price");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getRedemptionCurrency())) {

			errorMsg.append(" Redemption Currency");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getTotalIssue())) {

			errorMsg.append(" Total Issue");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getFaceValue())) {

			errorMsg.append(" Face Value");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getIsin())) {

			errorMsg.append(" ISIN");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getSettlementType())) {

			errorMsg.append(" Settlement Type");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getCommon())) {

			errorMsg.append(" Common");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getCouponRate())) {

			errorMsg.append(" Coupon Rate");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getCouponCurrency())) {

			errorMsg.append(" Coupon Currency");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getDayCount())) {

			errorMsg.append(" Day Count");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getCouponFrequency())) {

			errorMsg.append(" Coupon Frequency");
			isValid = false;
			
		}

		if (commonUTIL.isEmpty(bean.getBdc())) {

			errorMsg.append(" Busyness Day Convention");
			isValid = false;
			
		}
		
		if(!isValid) {
			
			String msg = errorMsg.toString();
			ProductAppender.printLog(logConstants.ERROR, msg);
			
		} else {

			String msg = "Product with ISIN no: " + bean.getIsin() + " is valid";
		//	ProductAppender.printLog(logConstants.INFO, msg);
			
		}
		
		return isValid;

	}
}
