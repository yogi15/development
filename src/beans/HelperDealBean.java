package beans;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import util.commonUTIL;


public class HelperDealBean implements HelperBean {
	
public  DealBean  getBean(String [] record) {
		
		DealBean dealbond = new DealBean();
		
	/*	deal.setLeName(record[0]);
		dealBean.setBookName(record[1]);
		dealBean.setTraderName(record[2]);
		dealBean.setType(record[3]);
		dealBean.setPrice(Double.parseDouble(record[4]));
		dealBean.setTradeAmount(Double.parseDouble(record[5]));
		dealBean.setDeliveryDate(record[6]);
		dealBean.setExternalTradeId(record[7]);
		dealBean.setTradeDate(record[8]);
		dealBean.setIsin(record[9]);
		dealBean.setCommon(record[10]);
		dealBean.setQuantity(Double.parseDouble(record[11]));
	
		dealBean.setCouponAccrualDays(Double.parseDouble(record[12]));
		dealBean.setAccrualRate(Double.parseDouble(record[13]));
		dealBean.setAccrualAmount(record[14]);
		dealBean.setNextCouponDate(record[15]);
		dealBean.setPreviousCouponDate(record[16]);
	
		dealBean.setTradeDirtyPrice(Double.parseDouble(record[17]));
		dealBean.setTradeCleanPrice(Double.parseDouble(record[18]));
		dealBean.setTotalAmount(Double.parseDouble(record[19])); */
		
		dealbond.setMemberName(record[0]);
		dealbond.setMember(record[1]);
		dealbond.setDEALERname(record[2]);
		dealbond.setDealer(record[3]);
		dealbond.setMarket(record[4]);
		dealbond.setSubMarket(record[5]);
		

		dealbond.setOrderNumber(record[6]);
		dealbond.setTradeDate(record[7]);
		dealbond.setTradeTime(record[8]);
		dealbond.setTradeNumber(record[9]);
		dealbond.setTradeType(record[10]);
		dealbond.setSettlementType(record[11]);
		dealbond.setSettlement(record[12]);
		//dealBean.setDate(record[12]);
		dealbond.setISIN(record[13]);
		dealbond.setGenspec(record[14]);
		dealbond.setSecurity(record[15]);
		dealbond.setMaturityDate(record[16]);
		dealbond.setAmount(record[17]);
		dealbond.setFV(record[18]);
		dealbond.setTradePrice(record[19]);
		dealbond.setTradeYield(record[20]);
		dealbond.setTradeAmount(record[21]);
		dealbond.setLastInterest(record[22]);
	//	dealBean.setPaymentDate(record[22]);
		 
		dealbond.setNumberofBrokenPeriodDays(record[23]);	
		dealbond.setAccruedInterest(record[24]);
		dealbond.setSettConsiderationRs(record[25]);
		
		
		return dealbond;
	}

}
