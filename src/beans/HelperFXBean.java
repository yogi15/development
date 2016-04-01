package beans;

import util.commonUTIL;

public class HelperFXBean implements HelperBean {

	@Override
	public Deal getBean(String[] record) {
		// TODO Auto-generated method stub
		DealFXBean dealBean = new DealFXBean();
		dealBean.setMemberName(record[0]);
		dealBean.setMember(record[1]);
		dealBean.setDEALERname(record[2]);
		dealBean.setDealer(record[3]);
		dealBean.setTradeNumber(record[4]);
		dealBean.setTradeDate(record[5]);
		dealBean.setTradeTime(record[6]);
		dealBean.setTradeType(record[7]);
		dealBean.setMaturityDate(record[8]);
		dealBean.setCurrency(record[9]);
		dealBean.setType(record[10]);
		dealBean.setCurrencyPair(record[11]);
		dealBean.setSpotPrice(record[12]);
		dealBean.setQuantity(record[13]);
		String ispositionB = record[15];
		if(ispositionB.equalsIgnoreCase("YES"))
		      dealBean.setisPositionBased(true);
		else 
			 dealBean.setisPositionBased(false);
		if(record.length > 16) {
		String feesType = record[16];
		if(!commonUTIL.isEmpty(feesType)) {
			FeesUploader fee = new FeesUploader();
			fee.setFeeType(feesType);
			fee.setCurrency(record[17]);
			fee.setAmount(commonUTIL.converStringToDouble(record[18]));
			fee.setFeeDate(record[20]);
			fee.setEndDate(record[20]);
			fee.setStartDate(record[20]);
			fee.setLeCode(record[23]);
			fee.setLeRole("BROKER");
			dealBean.setFees(fee);
			String attribute = "InstrumentType="+record[21] +";";
			attribute = attribute + "BranchName="+record[22] +";";
			if(record.length > 24) {
			String UnderlyingRef = record[24];
			if(!commonUTIL.isEmpty(UnderlyingRef)) {
			attribute = attribute + "UnderlyingRef="+UnderlyingRef +";"; 
			}
			}
			dealBean.setAttributes(attribute);
			
			
		} else {
			if(record.length > 24) {
			String UnderlyingRef = record[24];
			if(!commonUTIL.isEmpty(UnderlyingRef)) {
			dealBean.setAttributes("UnderlyingRef="+UnderlyingRef +";");
			}
			}
		}
		
		}
		
	//	dealBean.setType(record[10]);
		
		return dealBean;
	}

}
