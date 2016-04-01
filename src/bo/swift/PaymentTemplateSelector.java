package bo.swift;

import util.commonUTIL;


import beans.Message;
import beans.Trade;

public class PaymentTemplateSelector implements TemplateSelector {
	
	  protected String getType() { return "MT202"; }
	    protected String getTypeCOV() { return "MT202"; }

	@Override
	public String getTemplate(Trade trade, Message message, String name) {
		// TODO Auto-generated method stub
		  String templateName = null;

	        if(message == null || commonUTIL.isEmpty(message.getMessageType())){
	           commonUTIL.display("PaymentTemplateSelector","Null Message");
	            return templateName;
	        }
	        return null;
	}

}
