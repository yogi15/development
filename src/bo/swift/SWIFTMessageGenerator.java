package bo.swift;

import java.util.Hashtable;

import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;

import util.ClassInstantiateUtil;
import util.ReferenceDataCache;
import util.commonUTIL;
import beans.DocumentInfo;
import beans.Message;
import beans.Trade;
import beans.Transfer;
import bo.message.bomessagehandler.MessageFormat;
import bo.swift.SwiftGenerator;
import bo.swift.SwiftMessage;

public class SWIFTMessageGenerator implements MessageFormat {
	
	Hashtable<String,SwiftGenerator> swiftMessageGen = new Hashtable<String,SwiftGenerator>();
	
	public  SwiftGenerator genearteSWIFTMessageFormat(String name,String format) {
		// TODO Auto-generated method stub
		SwiftGenerator _swiftmessageFormat = null;
		String classname = "";
		if(format.equalsIgnoreCase("SWIFT"))
		  classname = "bo." + format.toLowerCase()+  "." +name + "SwiftGenerator";
		if(format.equalsIgnoreCase("CCIL"))
			 classname = "bo." + format.toLowerCase()+  "." +name + "CCILGenerator";
		 try {
			
			 synchronized (swiftMessageGen) {
				 
				 _swiftmessageFormat =  swiftMessageGen.get(classname);
			}
			 if(_swiftmessageFormat == null) {
             Class class1 = ClassInstantiateUtil.getClass(classname,
  					true);
             if(class1 != null)            	 {
             _swiftmessageFormat =  (SwiftGenerator) class1.newInstance();
             swiftMessageGen.put(classname,_swiftmessageFormat);
             }
			 else {
				// name = "MT202";
				 
					if(format.equalsIgnoreCase("SWIFT"))
					  classname = "bo." + format.toLowerCase()+  "." +name + "SwiftGenerator";
					if(format.equalsIgnoreCase("CCIL"))
						 classname = "bo." + format.toLowerCase()+  "." +name + "CCILGenerator";
				class1 = ClassInstantiateUtil.getClass(classname,
		  					true);
				 if(class1 != null)            	  {
		             _swiftmessageFormat =  (SwiftGenerator) class1.newInstance();
		             swiftMessageGen.put(classname,_swiftmessageFormat);
			 
				 }
				 
			 }
			 }
      }
      catch(Exception e) {
      	commonUTIL.displayError("SwiftMessageGenerator", "genearteSWIFTMessage  + Missing handler for Product  W" + classname , e);
      	return null;
      }
		 return _swiftmessageFormat;
	}
	
	
	
	public SwiftMessage generateSWIFMessage(Trade trade,Transfer transfer,Message message,RemoteTrade remoteTrade,RemoteReferenceData remoteRef) {
		
		
		SwiftGenerator format = genearteSWIFTMessageFormat(getTemplateName(message.getMessageType(),message.getTemplateName()),message.getFormat());
		SwiftMessage swiftMessage = null;
		try {
			swiftMessage =  format.generate(message, trade, transfer, remoteTrade,remoteRef);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return swiftMessage;
	}

    private String getTemplateName(String messageType,String templateName) {
    	String template= null;
    	if(messageType.equalsIgnoreCase("RECEIPTMSG")) {
    		template = "MT210";
		} else if (messageType.equalsIgnoreCase("PAYMENTMSG")) {
		
			template = "MT202";
		} else {
		
			template = templateName;
		}
    	return template;
    }

	@Override
	public DocumentInfo generate(Message message, Trade trade,Transfer transfer,boolean newDocument,RemoteTrade remoteTrade,RemoteReferenceData remoteRef) {
		// TODO Auto-generated method stub
		String name = getTemplateName(message.getMessageType(),message.getTemplateName());
		SwiftGenerator swiftGen = genearteSWIFTMessageFormat(name,message.getFormat());
		if(swiftGen == null)
			return null;
		DocumentInfo docInfo = new DocumentInfo();
		try {
			String templateName = getTemplateName(message.getMessageType(), message.getTemplateName());
			message.setTemplateName(templateName);
			
			SwiftMessage swift = swiftGen.generate(message, trade, transfer, remoteTrade,remoteRef);
			System.out.println(swift._gateway);
			docInfo.setSwiftMessage(swift);
			System.out.println(swift.getSwiftText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docInfo;
	}


	

public static String getSelectedTemplate(Trade trade, Message message,
		String templateName) {
	// TODO Auto-generated method stub
	return null;
}




public static String getSelectedTemplate(Message message, String templateName) {
	// TODO Auto-generated method stub
	return null;
}
	
	

	@Override
	public boolean display(Message message, DocumentInfo docInfo,RemoteTrade remoteTrade,RemoteReferenceData remoteRef) {
		// TODO Auto-generated method stub
		return false;
	}



}
