package bo.swift;

import java.sql.Connection;
import java.util.Vector;

import util.commonUTIL;
import beans.LeContacts;
import beans.Message;
import beans.Trade;
import beans.Transfer;
import beans.TransferRule;
import bo.transfer.rule.ProductTransferRule;


public class TransferSwiftGenerator extends SwiftGenerator {
	
	 public static final String REMITTANCE = "REMITTANCE";
	  public static final String CABLE = "CABLE";
	  public static final String CORRESPONDENCE = "CORRESPONDENCE";
	  public static final String LIFTING = "LIFTING";
	  
	
	
	  
	  
	  public String parseVALUE_DATE(Message message,
              Trade trade,
              LeContacts sender,
              LeContacts rec,
              Vector transferRules,
              Transfer transfer,
              String format,
              Connection dsCon,ProductTransferRule productTransferRule) {
		  return null;
	  }
					
	  
	  public String parseDATE_CCY_AMOUNT(Message message,
              Trade trade,
              LeContacts sender,
              LeContacts rec,
              Vector transferRules,
              Transfer transfer,
              String format,
              Connection dsCon,ProductTransferRule productTransferRule) {
		  if (transfer == null) {
		      return EMPTYSTRING;
		    }

		    try {
		      String ccy = transfer.getSettlecurrency();
		      return SwiftUtil.getSwiftDate(commonUTIL.stringToDate(transfer.getDeliveryDate(),true), 6) +
		          ccy +
		          SwiftUtil.getSwiftAmount(transfer.getSettleAmount(), ccy);
		    }
		    catch (Exception e) {
		    commonUTIL.displayError("TransferSwiftGenerator", "parseDATE_CCY_AMOUNT", e);
		      return EMPTYSTRING;
		    }
		  }
		  
	  
	  
	  public String parseCCY_AMOUNT(Message message,
              Trade trade,
              LeContacts sender,
              LeContacts rec,
              Vector transferRules,
              Transfer transfer,
              String format,
              Connection con,ProductTransferRule productTransferRule) {
		  return null;
		  
	  }
	  
	  /**
	   * This field specifies the financial institution of the ordering
	   * customer, when different from the Sender, even if field 50a
	   * contains an IBAN.
	   */
	  public String parseORDERING_INSTITUTION(Message message,
	                                          Trade trade,
	                                          LeContacts sender,
	                                          LeContacts rec,
	                                          Vector transferRules,
	                                          Transfer transfer,
	                                          String format,
	                                          Connection con) {

	    return EMPTYSTRING;
	  }

	  public String parsePO_DELIVERY_AGENT(Message message,
              Trade trade,
              LeContacts sender,
              LeContacts rec,
              Vector transferRules,
              Transfer transfer,
              String format,
              Connection dsCon,ProductTransferRule productTransferRule) {
		  return null;
		  
	  }
	  public String parseCPTY_INTERMEDIARY(Message message,
              Trade trade,
              LeContacts sender,
              LeContacts rec,
              Vector transferRules,
              Transfer transfer,
              String format,
              Connection dsCon,ProductTransferRule productTransferRule) { return null;

	  }
	  public String parseCPTY_INTERMEDIARY2(Message message,
              Trade trade,
              LeContacts sender,
              LeContacts rec,
              Vector transferRules,
              Transfer transfer,
              String format,
              Connection dsCon,ProductTransferRule productTransferRule) { return null;
		  
	  }
	  public boolean hasIntermediary2(Message message,
			    Trade trade,
			    LeContacts sender,
			    LeContacts rec,
			    Vector transferRules,
			    Transfer transfer,
			    Connection dsCon,ProductTransferRule productTransferRule) { return false;
		  
	  }
	  public boolean hasIntermediary(Message message,
			    Trade trade,
			    LeContacts sender,
			    LeContacts rec,
			    Vector transferRules,
			    Transfer transfer,
			    String format,
			    Connection dsCon,ProductTransferRule productTransferRule) { 

		  	if (transfer == null) {
		  		}
		  	return false;
	  }
	  public String parseCPTY_DELIVERY_AGENT(Message message,
              Trade trade,
              LeContacts sender,
              LeContacts rec,
              Vector transferRules,
              Transfer transfer,
              String format,
              Connection dsCon,ProductTransferRule productTransferRule) {return null;
	  }
	  public String parseCPTY_AGENT(Message message,
			  Trade trade,
			  LeContacts sender,
			  LeContacts rec,
			  Vector transferRules,
			  Transfer transfer,
			  String format,
			  Connection dsCon,ProductTransferRule productTransferRule) {return null;


	  }
	  public String parsePO_BENEFICIARY(Message message,
              Trade trade,
              LeContacts sender,
              LeContacts rec,
              Vector transferRules,
              Transfer transfer,
              String format,
              Connection con,ProductTransferRule productTransferRule) {return null;
	  }
	  
	  /**
	   * This field specifies additional information for the Receiver or
	   * other party specified.
	   */
	  public String parseADDITIONAL_INFO(Message message,
	                                     Trade trade,
	                                     LeContacts sender,
	                                     LeContacts rec,
	                                     Vector transferRules,
	                                     Transfer transfer,
	                                     Connection con) {return null;
	  }
	  
	  public String parseAGENT_ACCOUNT(Message message,
              Trade trade,
              LeContacts sender,
              LeContacts rec,
              Vector transferRules,
              Transfer transfer,
              String format,
              Connection con,ProductTransferRule productTransferRule) {
		  return null;
		  
	  }

	  
	  /**
       * This field specifies which party will bear the charges for the transaction.
   * One of the following codes must be used:
   * <p>
   * <ul>
       * <li>BEN - All transaction charges are to be borne by the beneficiary customer.
       * <li>OUR - All transaction charges are to be borne by the ordering customer.
   * <li>SHA - Transaction charges on the Sender's side are to be borne by the ordering customer,
   *           transaction charges on the Receiver's side are to be borne by the beneficiary customer.
   * </ul>
   */
  public String parseDETAILS_CHARGES(Message message,
                                     Trade trade,
                                     LeContacts sender,
                                     LeContacts rec,
                                     Vector transferRules,
                                     Transfer transfer,
                                     String format,
                                     Connection con,ProductTransferRule productTransferRule) {return null;
  }
  
  public boolean isPOBeneficiaryFinancial(Message message,
          Trade trade,
          LeContacts sender,
          LeContacts rec,
          Vector transferRules,
          Transfer transfer,
          Connection con,ProductTransferRule productTransferRule) {return false;
  }
  
  public boolean isCptyBeneficiaryFinancial(Message message,
          Trade trade,
          LeContacts sender,
          LeContacts rec,
          Vector transferRules,
          Transfer transfer,
          String format,
          Connection con,ProductTransferRule productTransferRule) {
	  return false;
  }
  
  public String parseRELATED_REF(Message message,
		   Trade trade,
		   LeContacts sender,
		   LeContacts rec,
		   Vector transferRules,
		   Transfer transfer,
		   String format,
		   Connection dsCon,ProductTransferRule productTransferRule) {
	  if (transfer == null) {
		    message = getMessage(message);
		    trade = getTrade(message, dsCon,productTransferRule);
		    transfer = getTransfer(message, dsCon,productTransferRule);
		}
		String messageRef = message.getAttribute(Message.MESSAGE_REF);
		if (!commonUTIL.isEmpty(messageRef)) {
		    return messageRef;
		}
		
		if (transfer.getTradeId() > 0) {
		    return String.valueOf(transfer.getTradeId());
		}
		else {
		    return String.valueOf(transfer.getId());
		}
  }
  
  public String parseMESSAGE_ID(Message message,
          Trade trade,
          LeContacts sender,
          LeContacts rec,
          Vector transferRules,
          Transfer transfer,
          String format,
          Connection dsCon,ProductTransferRule productTransferRule) {
	  message = getMessage(message);
	  if (message != null) {
	        String attr = message.getAttribute("TAG20");
	        if (!commonUTIL.isEmpty(attr))
	            return attr;
	        return String.valueOf(message.getId());
	    }
	    return EMPTYSTRING;

  }
  
  public String parseMSGGRP_SUM_OF_AMOUNTS(Message message,
          Trade trade,
          LeContacts sender,
          LeContacts rec,
          Vector transferRules,
          Transfer transfer,
          String format,
          Connection con,ProductTransferRule productTransferRule) {
	  return null;
  }
  
  protected double getSumOfAmounts(Message message, Connection con) {
	  return 0;
	  
  }
  public String parseSENDER_CHARGE(Message message,
          Trade trade,
          LeContacts sender,
          LeContacts rec,
          Vector transferRules,
          Transfer transfer,
          String format,
          Connection con,ProductTransferRule productTransferRule) {
	  return null;
	  
  }
 /* protected double getReceiverFees(CustomerTransfer ct, Trade trade) {
	  return 0;
  }
  protected String getReceiverFeesCcy(Message message, Connection con) {
	  return null;
  }
  protected String getReceiverFeesCcy(CustomerTransfer ct, Trade trade) {
	  return null;
  }*/
  public String parseTIME_INDICATION(Message message,
          Trade trade,
          LeContacts sender,
          LeContacts rec,
          Vector transferRules,
          Transfer transfer,
          String format,
          Connection con,ProductTransferRule productTransferRule) {
	  return null;
  }
  public String parseTIME_INDICATION_CLS(Message message,
          Trade trade,
          LeContacts sender,
          LeContacts rec,
          Vector transferRules,
          Transfer transfer,
          String format,
          Connection con,ProductTransferRule productTransferRule) {
	  return null;
  }
	  
  public String parseRECEIVER_CHARGES(Message message,
          Trade trade,
          LeContacts sender,
          LeContacts rec,
          Vector transferRules,
          Transfer transfer,
          String format,
          Connection con,ProductTransferRule productTransferRule) {
	  return null;
  }
  
  public String parseTRADE_KEYWORD_26T(Message message,
          Trade trade,
          LeContacts sender,
          LeContacts rec,
          Vector transferRules,
          Transfer transfer,
          String format,
          Connection con,ProductTransferRule productTransferRule) {
	  return null;
	  
  }
  protected Vector getRules(int ordererId, Trade cloneTrade, Connection dsCon,ProductTransferRule productTransferRule){
	  return null;
	  
  }
  
  
  
  // get understanding from calypso logic what is this. 
  public boolean ordererNotPO(Message message,
          Trade trade,
          LeContacts sender,
          LeContacts rec,
          Vector transferRules,
          Transfer transfer,
          Connection con,ProductTransferRule productTransferRule) {
	  return false;
	  
  }
 public boolean hasTimeIndication(Message message,
                             Trade trade,
                             LeContacts sender,
                             LeContacts rec,
                             Vector transferRules,
                             Transfer transfer,
                             Connection con,ProductTransferRule productTransferRule) {

        if (transfer == null) {
            return false;
        }
        return false;

      
    }
 

 public String parseSEND_BENEFICIARY(Message message,
                                     Trade trade,
                                     LeContacts sender,
                                     LeContacts rec,
                                     Vector transferRules,
                                     Transfer transfer,
                                     String format,
                                     Connection dsCon,ProductTransferRule productTransferRule) {
	 return null;
	 
 }
 public boolean isCoverMessage(Message message,
		  Trade trade,
		  LeContacts sender,
		  LeContacts rec,
		  Vector transferRules,
		  Transfer transfer,
		  Connection dsCon,ProductTransferRule productTransferRule) {
	 if (transfer == null) {
		    //get underlying message from iterator
		    message = getMessage(message);
		    trade = getTrade(message, dsCon,productTransferRule);
		    transfer = getTransfer(message, dsCon,productTransferRule);
		}
		if (transfer == null) return false;

	Vector<TransferRule> rule = transferRules;
		//return SwiftUtil.isCoverMessage(message, rule, dsCon,ProductTransferRule productTransferRule);
		
	 return false;
 }
 protected Transfer getTransfer(Message message, Connection dsCon,ProductTransferRule productTransferRule) {
	// TODO Auto-generated method stub
	return null;
}


protected Trade getTrade(Message message, Connection dsCon,ProductTransferRule productTransferRule) {
	// TODO Auto-generated method stub
	return null;
}




public boolean isCoverMessageRequired(Message message,
		  Trade trade,
		  LeContacts sender,
		  LeContacts rec,
		  Vector transferRules,
		  Transfer transfer,
		  Connection dsCon,ProductTransferRule productTransferRule) {
	 return false;

 }
 public boolean isCoverMessageToInt1(Message message,
		 Trade trade,
		 LeContacts sender,
		 LeContacts rec,
		 Vector transferRules,
		 Transfer transfer,
		 Connection dsCon,ProductTransferRule productTransferRule) {
	 return false;
 }
 
 public boolean isMEPS(Message message,
         Trade trade,
         LeContacts sender,
         LeContacts rec,
         Vector transferRules,
         Transfer transfer,
         Connection con,ProductTransferRule productTransferRule) {
	 return false;
	 
 }
 public boolean isZAR(Message message,
         Trade trade,
         LeContacts sender,
         LeContacts rec,
         Vector transferRules,
         Transfer transfer,
         Connection con,ProductTransferRule productTransferRule) {
	 return false;
	 
 }
 public boolean isTARGET2(Message message,
         Trade trade,
         LeContacts sender,
         LeContacts rec,
         Vector transferRules,
         Transfer transfer,
         Connection con,ProductTransferRule productTransferRule) { return false;

 }
 public boolean isCptySameAsCptyAgent(Message message,
         Trade trade,
         LeContacts sender,
         LeContacts rec,
         Vector transferRules,
         Transfer transfer,
         Connection con,ProductTransferRule productTransferRule) { return false;
 }
 public boolean hasTARGET2Correspondent(Message message,
         Trade trade,
         LeContacts sender,
         LeContacts rec,
         Vector transferRules,
         Transfer transfer,
         Connection con,ProductTransferRule productTransferRule) {
	 return false;
 }
 /**
  * Returns the Trade Id.
  */
 public String parseTRADE_ID(Message message,
                             Trade trade,
                             LeContacts sender,
                             LeContacts rec,
                             Vector transferRules,
                             Transfer transfer,String format,
                             Connection dsCon,ProductTransferRule productTransferRule) { return null;
	 
 }
 
 public String parseACCOUNT_HOLDER(Message message,
         Trade trade,
         LeContacts sender,
         LeContacts rec,
         Vector transferRules,
         Transfer transfer,String format,
         Connection dsCon,ProductTransferRule productTransferRule) {
	 return null;
 }
 public boolean hasMultiAccountsWithAgent(Message message,
	     Trade trade,
	     LeContacts sender,
	     LeContacts rec,
	     Vector transferRules,
	     Transfer transfer,
	     Connection dsCon,ProductTransferRule productTransferRule) {
	 return false;
 }
 
}



