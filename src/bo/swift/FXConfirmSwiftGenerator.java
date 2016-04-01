package bo.swift;

import java.sql.Connection;
import java.util.Vector;

import javax.swing.plaf.synth.SynthDesktopIconUI;

import constants.MessageConstants;
import constants.SDIConstants;

import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;


import util.ReferenceDataCache;
import util.commonUTIL;

import beans.LeContacts;
import beans.LegalEntity;
import beans.Message;
import beans.Sdi;
import beans.Trade;
import beans.Transfer;
import beans.TransferRule;
import bo.transfer.rule.GenerateFXTransferRule;
import bo.util.SDISelectorUtil;
public class FXConfirmSwiftGenerator extends SwiftGenerator {

	
	
	
	
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

       
        swift = new  SwiftMessage(message, type);
        Vector fields = new Vector();
        swift.setFields(fields);
        SwiftFieldMessage field = null;
      //General Information
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setTAG(":15A:");
        field.setName("General Information");
        field.setValue("");
        fields.addElement(field);
        
        
      //Sender's Reference
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setTAG(":20:");
        field.setName("Sender's Reference");
        field.setValue(String.valueOf(message.getId()));
        fields.addElement(field);
        
      //Related Reference
        if (!message.getSubAction().equals("NEW")) {
                field = new SwiftFieldMessage();
                field.setStatus((byte)'O');
                field.setTAG(":21:");
                field.setName("Related Reference");
                field.setValue(String.valueOf(message.getLinkId()));
                fields.addElement(field);
        } 
        //Type of Operation
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setTAG(":22A:");
        field.setName("Type of Operation");
        if (message.getSubAction().equals("NEW")){
            field.setValue("NEWT");
           // if (trade.getKeywordValue(Trade.EXERCISE_OPTION) != null) {
              //  field.setValue("EXOP");
           // }
        } 
       
        else if (message.getSubAction().equals("AMEND")) {
            field.setValue("AMND");
      
        }  else {
        	field.setValue("CANC");
        }
        //PB - Add support for Exercise of Option.

        fields.addElement(field);
        //Scope of Operation
        field = new SwiftFieldMessage();
        field.setStatus((byte)'O');
        field.setTAG(":94A:");
        field.setName("Scope of Operation");
        String scope="BILA";
       // if (!isMatchingSystem) { // -- need to understand about matching
            if (message.getReceiverId() != trade.getCpID())    {
                if (message.getReceiverRole().equalsIgnoreCase("AGENT"))
                    scope="AGNT";
                else
                    scope="BROK";
            }
       // }
        field.setValue(scope);
        fields.addElement(field);
      //Common Reference
        //To be reviewed according to the Swift Rule
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setTAG(":22C:");
        field.setName("Common Reference");
        String receiver = message.getReceiverAddressCode();
       // if (isMatchingSystem) {
       //     receiver = matchingContact.getSwift(); // this need to be understand properly. 
       // }
        double price = trade.getPrice(); 
        field.setValue(SwiftUtil.getSwiftCommonReferenceNoRounding(price,
                                                         message.getSenderAddressCode(),
                                                         receiver));
        fields.addElement(field);

      //Party A is always the Processing Org
        field = new SwiftFieldMessage();
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
        fields.addElement(field);
        //Party B is always the CounterParty
        field = new SwiftFieldMessage();
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

        if (isMatchingSystem) {
            field = new SwiftFieldMessage();
            field.setStatus((byte)'O');
            field.setTAG(":83J:");
            field.setName("Fund or Beneficiary Customer");
            field.setValue("/NAME/"+cptyMatchingContact.getLeLastName());
            fields.addElement(field);
        }
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
        }
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setTAG(":15B:");
        field.setName("New Sequence");
        field.setValue("");
        fields.addElement(field);
        //Trade Date


        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setTAG(":30T:");
        field.setName("Trade Date");
        field.setValue(SwiftUtil.getSwiftTradeDate(trade));
        fields.addElement(field);
        
      //Value Date
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setTAG(":30V:");
        field.setName("Value Date");
        field.setValue(SwiftUtil.getSwiftDate(trade.getDelivertyDate()));
        fields.addElement(field);
        
      //Exchange Rate
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
        field.setTAG(":36:");
        field.setName("Exchange Rate");
        double rate = trade.getPrice();// FXBasedSWIFTFormatter.round(trade, trade.getPrice()); // this need to check. 
        String rateStr =  commonUTIL.converDoubleToString(rate); // SwiftUtil.getSwiftAmountNoRounding(rate);
        if (rateStr.length() > 12) {
            rateStr = rateStr.substring(0, 12);
        }
        field.setValue(rateStr);
        fields.addElement(field);
        //Mandatory subsequence Amount bought
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
      //  field.setTAG(":32B:"); // required by swift 
        field.setTAG(":32R:");
        field.setName("Amount bought (ValueDate,Currency,Amount)");
        String amountBought;
        String ccy = null;
        boolean usePo=true;
        if (trade.getQuantity() > 0) {
            ccy = trade.getTradedesc().substring(0, 3);
            amountBought = ccy +
                SwiftUtil.getSwiftAmount(trade.getQuantity(),ccy);
            usePo=false;
        }
        else {
            ccy = trade.getTradedesc1().substring(4, 7);
            amountBought = ccy +
                SwiftUtil.getSwiftAmount(trade.getNominal(),ccy);
            usePo=true;
        }

        field.setValue(SwiftUtil.getSwiftDate(trade.getDelivertyDate())+amountBought);
        fields.addElement(field);
        
        field = SwiftUtil.getTAG53(fxTransferRule,"RECEIVE", trade,null,"Delivery Agent",false,message,transferRules,null);
        if (field != null) fields.addElement(field);
      
        
        // this is for intermeidaary let's think this later.
        
       // field = SwiftUtil.getTAG56(fxTransferRule,"PAY", trade,null,"Intermediary",false,message,transferRules,dsCon);
      //  if (field != null) fields.addElement(field);

        field = SwiftUtil.getTAG57(fxTransferRule,"PAY", trade,null,"Receiving Agent",true,message,transferRules,null);
        if (field != null) fields.addElement(field);
        field = new SwiftFieldMessage();
        field.setStatus((byte)'M');
      //  field.setTAG(":33B:"); // required by swift code
        field.setTAG(":33P:");
        field.setName("Amount sold (Currency,Amount)");
        String amountSold;
        if (trade.getQuantity() < 0) {
            ccy = trade.getTradedesc().substring(0, 3);
            amountSold = ccy +
                SwiftUtil.getSwiftAmount(trade.getQuantity(),ccy);
            usePo=true;
        }
        else {
            ccy = trade.getTradedesc().substring(4, 7);
            amountSold = ccy +
                SwiftUtil.getSwiftAmount(trade.getNominal(),ccy);
            usePo=false;
        }
        field.setValue(SwiftUtil.getSwiftDate(trade.getDelivertyDate())+amountSold);
        fields.addElement(field);

        field = SwiftUtil.getTAG53(fxTransferRule,"PAY", trade,null,"Delivery Agent",true,message,transferRules,null);
        if (field != null) fields.addElement(field);

        //field = SwiftUtil.getTAG56(fxTransferRule,"PAY", trade,null,"Intermediary",false,message,transferRules,dsCon);
       // if (field != null) fields.addElement(field);

        field = SwiftUtil.getTAG57(fxTransferRule,"PAY", trade,null,"Receiving Agent",false,message,transferRules,null);
        if (field != null) fields.addElement(field);

        // Tag 58A should only be displayed if beneficiary is not counterparty
       
        Sdi sdi = fxTransferRule.getSdi("CounterParty");
        if (sdi != null &&
            sdi.getBeneficiary() != null &&
            sdi.getBeneficiary().getCpId() != 0 &&
            (sdi.getBeneficiary()).getCpId() != trade.getCpID()) {
            field = SwiftUtil.getTAG58(fxTransferRule,"PAY", trade,null,"Beneficiary Institution",!usePo,message,transferRules,null);
            if (field != null) fields.addElement(field);
        }
       /*if ( !trade.getActiveAlternateDate()(trade.getDelivertyDate())){  // we have understand this scenario
            field = new SwiftFieldMessage();
            field.setStatus((byte)'O');
            field.setTAG(":72A:");
            field.setName("Split Value Date");
            field.setValue(SwiftUtil.getSwiftDate(trade.getDelivertyDate()) +
                           trade.getCurrency());
            fields.addElement(field);
       } */
        
		   return swift;
			}


			

		}