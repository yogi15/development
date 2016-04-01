package wfManager.tradeRules;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.Vector;

import productPricing.Pricer;
import dsServices.Remote;
import dsServices.RemoteTrade;
import beans.Trade;
import util.commonUTIL;
import util.common.DateU;
import wfManager.TradeRule;




/* this rule check following points. 
 * 
 * 1. is productType FXTakeUP
 * 2. parent trade type must be same of child trade type
 * 3. trade enddate must not be greater the parent end date.
 * 4. trade amt must be within the outstanding amt. 
 * 5. update outstanding amt of parent trade
 * 6. 

*/
public class validTakeUPKeyTradeRule implements TradeRule {

	@Override
	public boolean checkValid(Trade newTrade, Trade oldTrade,
			Vector messageData, RemoteTrade remote,Pricer pricer,Connection   con) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String productType = "FXTAKEUP";
		RemoteTrade remoteTrade = (RemoteTrade) remote;
		if(!newTrade.getTradedesc1().equalsIgnoreCase(productType)) {
			messageData.add(new String("Product Type is not FXTradeUP"));
			return flag;
		}
		try {
			if(newTrade.getAction().equalsIgnoreCase("ROLLBACK") || newTrade.getAction().equalsIgnoreCase("ROLLOVER"))  {
				  messageData.add("ROLLBACK OR ROLLOVER not allowed on FX Take UP");
				 return false;
			 }
			if(newTrade.getAction().equalsIgnoreCase("APPROVED"))  {
				  messageData.add("FXTAKEUP is in APPROVED status");
				 return false;
			 }
			if(newTrade.getParentID() == 0) {
				messageData.add(new String("Trade missing Parent ID"));
				return flag;
			}
			Trade parenttrade = remoteTrade.selectTrade(newTrade.getParentID());
			if(!parenttrade.getStatus().equalsIgnoreCase("APPROVED")) {
				messageData.add(new String("Parent Trade Must be in APPROVED Status"));
				return flag;
			};
			if(!parenttrade.getType().equalsIgnoreCase(newTrade.getType())) {
				messageData.add(new String("Trade Type must match with Parent Trade Type"));
				return flag;
			};
			DateU partentMaturitydate = DateU.valueOf(commonUTIL.stringToDate(parenttrade.getDelivertyDate(), true));
			DateU tradedate = DateU.valueOf(commonUTIL.stringToDate(parenttrade.getTradeDate(),true));
			DateU tradeDeliverydate = DateU.valueOf(commonUTIL.stringToDate(parenttrade.getDelivertyDate(),true));
			DateU takeUPTradeDate = DateU.valueOf(commonUTIL.stringToDate(newTrade.getTradeDate(),true));
			DateU takeUPSettleDate = DateU.valueOf(commonUTIL.stringToDate(newTrade.getTradeDate(),true));
			
			
			if(!commonUTIL.between2dates(tradedate.getDate(), tradeDeliverydate.getDate(), takeUPTradeDate.getDate())) {
				messageData.add(new String("Take-up Trade date(s) has to be within the Start and End dates of the FX Time Option"));
				return flag;
			}
			if(!commonUTIL.between2dates(tradedate.getDate(), tradeDeliverydate.getDate(), takeUPSettleDate.getDate())) {
				messageData.add(new String("Take-up Value date(s) has to be within the Start and End dates of the FX Time Option"));
				return flag;
			}
			//check outstanding 
			
			Trade outStandingAMTONFWD = remoteTrade.getOutStandingBalanceonFWDOption(parenttrade.getId(),newTrade.getId());
			if(outStandingAMTONFWD != null) {
			if(parenttrade.getQuantity() > 0.) {
				double primaryCurr = newTrade.getQuantity() + outStandingAMTONFWD.getQuantity();
				if(primaryCurr > parenttrade.getQuantity()) {
					messageData.add(new String("FX Time Option outStanding Limit exceeds"));
					return flag;
				}
			}
			if(parenttrade.getQuantity() < 0.) {
				double primaryCurr = outStandingAMTONFWD.getQuantity() - newTrade.getQuantity() ;
				if(primaryCurr < parenttrade.getQuantity()) {
					messageData.add(new String("FX Time Option outStanding Limit exceeds"));
					return flag;
				}
			}
			}
		} catch (RemoteException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			commonUTIL.displayError("validTakeUPKeyTradeRule ", "checkValid", e);
			messageData.add(new String("Remote Exception on TakeUPKey" +e));
			return false;
		} catch(Exception e) {
			e.printStackTrace();
			commonUTIL.displayError("validTakeUPKeyTradeRule ", "checkValid", e);
			messageData.add(new String(" Exception on TakeUPKey Rule"+e));
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Trade newTrade, Trade oldTrade, Vector messageData,
			RemoteTrade remoteTrade, Pricer pricer, Connection con) {
		// TODO Auto-generated method stub
		return true;
	}

	

}
