package limit.util;
import java.util.Vector;

import util.commonUTIL;

import beans.LimitConfig;
import beans.LimitConfigCriteria;
import beans.Trade;

public class LimitUtil {

	public static String  filterLimitsConfigIDs(Vector<LimitConfig> limitConfigs,Trade trade) {
		// TODO Auto-generated method stub
		Vector<LimitConfigCriteria> lc = new  Vector<LimitConfigCriteria>();
		if(commonUTIL.isEmpty(limitConfigs))
			return null;
		int configID = ((LimitConfig) limitConfigs.elementAt(0)).getId();
		LimitConfigCriteria configCreiteria = null;
		for(int i=0;i<limitConfigs.size();i++) {
			LimitConfig limitConfig =  limitConfigs.get(i);
			if(i ==0 ) {
				configCreiteria = new LimitConfigCriteria();
				
				configCreiteria.setLimitConfigID(limitConfig.getId());
				setCriteriaValue(configCreiteria,limitConfig);
			} else {
				if(limitConfig.getId() == configID) {
					setCriteriaValue(configCreiteria,limitConfig);
				} else {
					lc.add(configCreiteria);
					configCreiteria  = new LimitConfigCriteria();
					configID = limitConfig.getId();
					configCreiteria.setLimitConfigID(limitConfig.getId());
					setCriteriaValue(configCreiteria,limitConfig);
					
				}
			}
			
			
		}
		if(configCreiteria != null)
			lc.add(configCreiteria);
		return getLimitConfigIDs(lc,trade);
		
	}
	
	
	private static String getLimitConfigIDs(Vector<LimitConfigCriteria> limitCriteria,Trade trade) {
		String limitConfigId = "";
		if(commonUTIL.isEmpty(limitCriteria))
			return null;
		for(int i=0;i<limitCriteria.size();i++) {
			LimitConfigCriteria lc = limitCriteria.get(i);
			if((lc.getBookID() == 0) && commonUTIL.isEmpty(lc.getCurrency()) && commonUTIL.isEmpty(lc.getProductType())) {
				limitConfigId = limitConfigId + Integer.valueOf(lc.getLimitConfigID()) +",";
				
					
			}else {
				boolean flag = true;
						if(lc.getBookID() != 0) {
							if(lc.getBookID() == trade.getBookId()) 
								flag = true;
							 else 
								flag = false;
							
						}
				if(flag)	
						if(!commonUTIL.isEmpty(lc.getCurrency())) {
								if(lc.getCurrency().equalsIgnoreCase(trade.getCurrency())) 
										flag = true;
								else 
										flag = false;
						}
				if(flag)
				if(!commonUTIL.isEmpty(lc.getProductType())) {
					if(lc.getProductType().equalsIgnoreCase(trade.getProductType())) 
						flag = true;
					else 
						flag = false;
				}
				if(flag)
					limitConfigId = limitConfigId + Integer.valueOf(lc.getLimitConfigID()) +",";
			}
			
		}
		if(commonUTIL.isEmpty(limitConfigId))
			return null;
		return limitConfigId.substring(0, limitConfigId.length() -1);
	}
	
	
	private static void setCriteriaValue(LimitConfigCriteria configCreiteria,LimitConfig limitConfig) {
		if(limitConfig.getFilterType().equalsIgnoreCase("Currency")) {
			if(commonUTIL.isEmpty(limitConfig.getFilterValue()))
				return;
			configCreiteria.setCurrency(limitConfig.getFilterValue());
			return;
		}
		if(limitConfig.getFilterType().equalsIgnoreCase("Book")) {
			if(commonUTIL.isEmpty(limitConfig.getFilterValue()))
				return;
			int bookid = commonUTIL.converStringToInteger(limitConfig.getFilterValue());
			configCreiteria.setBookID(bookid);
			return;
		}
		if(limitConfig.getFilterType().equalsIgnoreCase("ProductType")) {
			if(commonUTIL.isEmpty(limitConfig.getFilterValue()))
				return;
			configCreiteria.setProductType(limitConfig.getFilterValue());
			return;
		}
		
		
	}
	
	
	
	
	
	

}
