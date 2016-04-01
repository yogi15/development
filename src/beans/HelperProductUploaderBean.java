package beans;

import util.commonUTIL;

public class HelperProductUploaderBean  implements HelperBean {

	public ProductUploaderBean getBean(String[] record) {
		
		ProductUploaderBean productUploaderBean = new ProductUploaderBean();
		
		//productUploaderBean.setProductId(record[0]);
		productUploaderBean.setBondClass(record[0]);
		productUploaderBean.setBondType(record[1]);
		productUploaderBean.setSecurityType(record[2]);
		productUploaderBean.setIssuerId(Integer.parseInt(record[3]));
		productUploaderBean.setIssueDate(record[4]);
		productUploaderBean.setDatedDate(record[5]);
		productUploaderBean.setMaturityDate(record[6]);
		productUploaderBean.setTenor(record[7]);
		productUploaderBean.setIssuer(record[8]);
		productUploaderBean.setCountry(record[9]);
		productUploaderBean.setIssuePrice(commonUTIL.converStringToDouble(record[10]));
		productUploaderBean.setIssueCurrency(record[11]);
		productUploaderBean.setRedemptionPrice(record[12]);
		productUploaderBean.setRedemptionCurrency(record[13]);
		productUploaderBean.setTotalIssue(record[14]);
		productUploaderBean.setFaceValue(record[15]);
		productUploaderBean.setIsin(record[16]);
		productUploaderBean.setSettlementType(record[17]);
		productUploaderBean.setCommon(record[18]);
		productUploaderBean.setCouponType(record[19]);
		productUploaderBean.setCouponRate(record[20]);
		productUploaderBean.setCouponCurrency(record[21]);
		productUploaderBean.setDayCount(record[22]);
		productUploaderBean.setCouponFrequency(record[23]);
		productUploaderBean.setBdc(record[24]);
				
		return productUploaderBean;
		
	}

}
