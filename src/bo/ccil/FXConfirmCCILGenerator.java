package bo.ccil;

import java.util.Vector;

import constants.MessageConstants;
import constants.SDIConstants;

import util.ReferenceDataCache;
import util.commonUTIL;
import beans.LeContacts;
import beans.LegalEntity;
import beans.Message;
import beans.Sdi;
import beans.Trade;
import beans.Transfer;
import beans.TransferRule;
import bo.swift.SwiftFieldMessage;
import bo.swift.SwiftGenerator;
import bo.swift.SwiftMessage;
import bo.swift.SwiftUtil;
import bo.transfer.rule.GenerateFXTransferRule;
import bo.util.SDISelectorUtil;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;

public class FXConfirmCCILGenerator  extends SwiftGenerator {	
	
	@Override
	public SwiftMessage generate(Message message, Trade trade,
			Transfer transfer, RemoteTrade remoteTrade,RemoteReferenceData remoteRef) throws Exception {
		// TODO Auto-generated method stub
		SwiftMessage swift = null;
		ReferenceDataCache refeCache = null;
		if (trade == null) {
	          commonUTIL.display("FXConfirmSwiftGenerator", "Trade is null FX SWIFT Confirm: null Trade.");
		      return null;
	}
		GenerateFXTransferRule fxTransferRule =   new GenerateFXTransferRule();
		
		
		
		fxTransferRule.setRemoteTrade(remoteTrade);
		fxTransferRule.setRefDate(remoteRef);

		Vector<String> errorMessage = new Vector<String>();
		Vector<TransferRule> transferRules =  fxTransferRule.generateRules(trade,errorMessage);
		String type = MessageConstants.MT300;
		refeCache = ReferenceDataCache.getSingleInstatnce();
		LegalEntity po = refeCache.getPO(trade.getBookId());
		 Sdi poPerferedSdi = SDISelectorUtil.getSdi(SDIConstants.PO,po.getId() ,trade.getCurrency(),trade.getProductType(),MessageConstants.SWIFT,0);
		 Sdi cpPerferedSdis = SDISelectorUtil.getSdi(SDIConstants.COUNTERPARY,trade.getCpID(),trade.getCurrency(),trade.getProductType(),MessageConstants.SWIFT,0);
		
		 fxTransferRule.setPOSdi(poPerferedSdi);
		 
			fxTransferRule.setCounterPartySDI(cpPerferedSdis);
		 
		
		String senderMessageCode = ReferenceDataCache.getSenderMessageCode(message.getSenderRole(),po.getId(),trade.getProductType(),message.getAddressType(),message.getSenderContactType());
		String receiverMessageCode = ReferenceDataCache.getSenderMessageCode(message.getReceiverRole(),message.getReceiverId(),trade.getProductType(),message.getAddressType(),message.getReceiverContactType());
		message.setSenderAddressCode(senderMessageCode);
		message.setReceiverAddressCode(receiverMessageCode);
	    int poId = po.getId();
	    
	    String matchingSystem = null;// SwiftUtil.getMatchingSystem(trade, ds, message);  add this method un swiftutil.
	    LeContacts cptyMatchingContact = null;
	    boolean isMatchingSystem = (matchingSystem != null);
	 // Message Intitialization
	    
	    LeContacts cpDefaultContact =  ReferenceDataCache.getLEContact("CounterParty", null, trade.getCpID(),
	    		trade.getProductType(), "Default"); 
	    LeContacts poDefaultContact =  ReferenceDataCache.getLEContact("PO", null, poId,
	    		trade.getProductType(), "Default");
       
	    String ccilHeaderTag = poPerferedSdi.getAttributeValue("CCILTagCode");
	    if(!commonUTIL.isEmpty(ccilHeaderTag))
	       message.setAttributes("CCILTagCode", ccilHeaderTag);
	       
	    
	    
        swift = new  SwiftMessage(message, type);
        Vector fields = new Vector();
        swift.setFields(fields);
        SwiftFieldMessage field = null;
     
     // transaction Reference no
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setTAG(":20:");
        field.setName("Transaction Reference");
        field.setValue(String.valueOf(trade.getId()));
        fields.addElement(field);


		// :21: Related Reference
		field = new SwiftFieldMessage();
		field.setStatus((byte) 'M');
		field.setTAG(":21:");
		field.setName("Related Reference");
		if (message.getSubAction().equals("NEW")) {
			field.setValue("NEWT");
		}

		else if (message.getSubAction().equals("AMEND")) {
			field.setValue("AMND");

		} else {
			field.setValue("CANC");
		}

		fields.addElement(field);

		
		
	//>>>>>>>>>>>>:22:Common Reference Number( As Explained In Page 1)
        //To be reviewed according to the Swift Rule
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setTAG(":22:");
        field.setName("Common Reference");
        String poBicCode = poDefaultContact.getSwift(); 
        String cpBicCode = cpDefaultContact.getSwift();
        String location = ReferenceDataCache.getParty(trade.getCpID()).getAttributeValue("Location").substring(0, 2);
        String price = SwiftUtil.getRate(trade.getPrice());         
        field.setValue(
        		poBicCode.substring(0, 4)+poBicCode.substring(6, 8)
        		+price
        		+cpBicCode.substring(0, 4)+cpBicCode.substring(6, 8)
        		);
        fields.addElement(field);
        
    //Date Contract Agreed ?
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setTAG(":30:");
        field.setName("Date Contract Agreed");
        field.setValue(SwiftUtil.getSwiftTradeDate(trade));
        fields.addElement(field);
		
	//Exchange Rate 
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setTAG(":36:");
        field.setName("Exchange Rate");
        field.setValue(String.valueOf(trade.getPrice()));
        fields.addElement(field);
	
	 //>>>>>>>>>>>>: 72:/Sender to Receiver Information ( Mandatory to be sent to sent to CCIL)
        // this is taking now contactType which is saved in LeContact
        //however this has to be CCIL ccontact Type
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setTAG(":72:/");
        field.setName("Sender to Receiver Information");
		
		LeContacts cpCCILContact =  ReferenceDataCache.getLEContact("CounterParty", null, trade.getCpID(),
		    		trade.getProductType(), "CCIL"); 
		LeContacts poCCILContact =  ReferenceDataCache.getLEContact("PO", null, poId,
		    		trade.getProductType(), "CCIL");
		
		String cpCCIL = cpCCILContact.getSwift();
		String poCCIL = poCCILContact.getSwift();
		
        field.setValue(poCCIL+cpCCIL);
        fields.addElement(field);
	 
	//    :32R:Value Date(8n)/Currency Code(3a)/Amount Bought(15 Number)
		field = new SwiftFieldMessage();
		field.setStatus((byte) 'M');
		// field.setTAG(":32B:"); // required by swift
		field.setTAG(":32R:");
		field.setName("Amount Sold (ValueDate,Currency,Amount)");
		String amountBought;
		String ccy = null;
		boolean usePo = true;
		if (trade.getQuantity() > 0) {
			ccy = trade.getTradedesc().substring(0, 3);
			amountBought = ccy
					+ SwiftUtil.getSwiftAmount(trade.getQuantity(), ccy);
			usePo = false;
		} else {
			ccy = trade.getTradedesc1().substring(4, 7);
			amountBought = ccy
					+ SwiftUtil.getSwiftAmount(trade.getNominal(), ccy);
			usePo = true;
		}

		field.setValue(SwiftUtil.getSwiftDate(trade.getDelivertyDate()) + amountBought);
		fields.addElement(field);
	
	//>>>>>>>>>>>>: 57A:
	
		 LeContacts payPoSWIFTContacts =  ReferenceDataCache.getLEContact("Agent", null, poPerferedSdi.getAgentId(),
		    		trade.getProductType(), "Default");
		 
		 String  payPoSWIFT = payPoSWIFTContacts.getSwift();
		 
		field = new SwiftFieldMessage();
		field.setStatus((byte) 'M');
		field.setTAG(":57A:");
		field.setName("Account with Institution");		
		field.setValue(payPoSWIFT);
		fields.addElement(field);

	
	//:33P:Value Date(8n)/Currency Code(3a)/Amount Bought(15 Number)
		field = new SwiftFieldMessage();
		field.setStatus((byte) 'M');
		field.setTAG(":33P:");
		field.setName("Amount Bought (Currency,Amount)");
		String amountSold;
		if (trade.getQuantity() < 0) {
			ccy = trade.getTradedesc().substring(0, 3);
			amountSold = ccy
					+ SwiftUtil.getSwiftAmount(trade.getQuantity(), ccy);
			usePo = true;
		} else {
			ccy = trade.getTradedesc().substring(4, 7);
			amountSold = ccy
					+ SwiftUtil.getSwiftAmount(trade.getNominal(), ccy);
			usePo = false;
		}
		field.setValue(SwiftUtil.getSwiftDate(trade.getDelivertyDate())
				+ amountSold);
		fields.addElement(field);
		
	    //>>>>>>>>>>>>: 57A: Account with The Institution Correspondent Bank Swift Code (Already Submitted to CCIL during Admission).


      //Party A is always the Processing Org
/*        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setName("Party A");
        String party = SwiftUtil.formatParty("A", po.getId(),
                                             null,"PO",
                                             message.getSenderContactType(),
                                             trade.getProductType(),
                                             po.getId(),
                                             trade,transfer,message,
                                             null,null);
        if (party.equals(SwiftUtil.UNKNOWN)) {
            party = SwiftUtil.formatParty("D",
                                          po.getId(),
                                          null,
                                          "PO",
                                          message.getSenderContactType(),
                                          trade.getProductType(),
                                          po.getId(),
                                          trade,transfer,message,
                                          null,null);
            field.setTAG(":82D:");
            field.setValue(party);
        }
        else {

            field.setTAG(":82A:");
            field.setValue(party);
        }
        fields.addElement(field);*/
        
        //Party B is always the CounterParty
   /*     field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setName("Party B");
        String tag;
        if (isMatchingSystem) {
            tag = ":87J:";
            party = "/ABIC/UKWN/NAME/" + cptyMatchingContact.getSwift();
        }
        else {
            tag = ":87A:";
            party = SwiftUtil.formatParty("A",
                                          trade.getCpID(),
                                          null,
                                          "CounterParty", // cp role in trade
                                          message.getReceiverContactType(),
                                          trade.getProductType(),
                                          po.getId(),
                                          trade,
                                          transfer,
                                          message,
                                          null,null);
            if (party.equals(SwiftUtil.UNKNOWN)) {
                tag = ":87D:";
                party = SwiftUtil.formatParty("D",
                								trade.getCpID(),
                                              null,
                                              "CounterParty",  // cp role in trade
                                              message.getReceiverContactType(),
                                              trade.getProductType(),
                                              po.getId(),
                                              trade,
                                              transfer,
                                              message,
                                              null,null);
            }
        } field.setTAG(tag);
        field.setValue(party);
        fields.addElement(field);

        LegalEntity cp = ReferenceDataCache.getLegalEntity(trade.getCpID());
        if (cp.getRole().equals("FUND")) {
            field = new SwiftFieldMessage();
            field.setStatus((byte)'O');
            field.setTAG(":83D:");
            field.setName("Fund or Beneficiary Customer");
          
           String attributeValue =
                             ReferenceDataCache.getAttribute( po.getId(),cp.getId(), "FUND LONG NAME", "CP");
                                                
            if (attributeValue != null) {
                field.setValue(attributeValue);
                fields.addElement(field);

            }
            else {
                field.setValue(cp.getName());
                fields.addElement(field);

            }
        }*/
		
		
        //field = SwiftUtil.getTAG56(fxTransferRule,"PAY", trade,null,"Intermediary",false,message,transferRules,dsCon);
       // if (field != null) fields.addElement(field); "PO|"+trade.getCurrency())
		 Sdi recSdi = SDISelectorUtil.getPreferredSdiOnly("PO|"+trade.getTradedesc().substring(0, 3)
				 +"|"+trade.getProductType()+
				 "|"+po.getId());
		 
		 LeContacts recPoSWIFTContacts =  ReferenceDataCache.getLEContact("Agent", null, recSdi.getAgentId(),
		    		trade.getProductType(), "Default");
		 String  recPoSWIFT = recPoSWIFTContacts.getSwift();
        
		field = new SwiftFieldMessage();
		field.setStatus((byte) 'M');
		field.setTAG(":57A:");
		field.setName("Account with Institution");		
		field.setValue(recPoSWIFT);
		fields.addElement(field);

        
		   return swift;
			}


			

		}