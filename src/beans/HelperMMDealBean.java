package beans;

import util.commonUTIL;

public class HelperMMDealBean implements HelperBean {

	// private static final attributeString = "Internal ID=0;External ID=0;Trade
	// Date=06/04/2014;rollOverTO=0;rollOverFrom=0;rollBackTo=0;rollBackFrom=0;ParentID=0;MirrorID=0;B2BID=0;B2BFlag=0;ParitialTo=0;SXccySplitID=0;ParitialFrom=0;XccySplitFrom=0;FXccySplitID=0;OffsetID=0

	@Override
	public Deal getBean(String[] record) {
		// TODO Auto-generated method stub
		DealMMBean dealBean = new DealMMBean();
		Product productBean = new Product();
		Coupon coupon = new Coupon();
	
		dealBean.setCpName(record[0]);
		dealBean.setBookName(record[1]);
		dealBean.setTraderName(record[2]);
		
		String type = record[3].equals("DEPOSIT")?"BUY":"SELL";
		
		dealBean.setType(type);
		dealBean.setMarketPrice(record[5]);
		dealBean.setTradeCurrency(record[6]);
		dealBean.setTradeAmount(record[7]);
		dealBean.setSettleDate(record[8]);
		dealBean.setAmortizationType(record[15]);
		dealBean.setAmortizationStartDate(record[16]);
		dealBean.setAmortizationEndDate(record[17]);
		dealBean.setAmortizationRate(record[18]);
		dealBean.setAmortizationFrequency(record[19]);
		dealBean.setCompoundingType(record[20]);
		dealBean.setCompoundingFrequency(record[21]);
		
		
		coupon.setCouponFrequency(record[11]);
		coupon.setBusinessDayConvention(record[13]);
		coupon.setActivefrom(record[14]);
		coupon.setCouponType(record[4]);
		coupon.setCCY(record[6]);
		coupon.setFixedRate(new Double(record[18]).doubleValue());
		coupon.setDayCount(record[12]);
		
		//product.setQuantity(new Double(nominalnumber).doubleValue());
		//product.setAttributes(amorType);
		productBean.setIssueCurrency(record[6]);
		productBean.setIssueDate(record[8]);
		productBean.setTenor(record[9]);
		productBean.setMarturityDate(record[10]);
		productBean.setProductType("MM");
		productBean.setProdcutShortName("CASH." + record[3]);
		productBean.setProductname(productBean.getProdcutShortName() + "."
				+ coupon.getCouponFrequency() + "." + productBean.getIssueDate()
				+ "." + productBean.getMarturityDate() + "."
				+ productBean.getIssueCurrency() + "."
				+ (coupon.getFixedRate() / 100) + "."
				+ coupon.getCouponType().substring(0, 2));
		
		dealBean.setProductBean(productBean);
		dealBean.setCouponBean(coupon);
			
		return dealBean;
	}

}
