package bo.html;

import java.util.Hashtable;

import util.ClassInstantiateUtil;



import beans.Message;
import beans.Trade;
import bo.html.Formatter.HTMLFormatter;
import bo.message.bomessagehandler.MessageFormat;

public class GenerateMessageFormatter implements MSFSelector {
	 protected static Hashtable _messageFormatters = new Hashtable();
	    protected static Hashtable _selectors         = new Hashtable();
	
	
	private HTMLFormatter getMessageFormatter(String productType,
			String messageType,Trade trade) {
		// TODO Auto-generated method stub
		HTMLFormatter instance = null;
	        String name = messageType;
	        if(productType.equalsIgnoreCase("FX")) {
	        	name =name+trade.getTradedesc1();
	        }  else {
	        	name = name + productType;
	        }
	        
	        synchronized (_messageFormatters) {
	            instance = (HTMLFormatter) _messageFormatters.get(name);
	        }
	        if (instance != null)
	            return instance;

	        String classname = "bo.html.HTML" + name + "MessageFormat";
	        instance = instantiate(name, classname);
	        if (instance != null) {
	            return instance;
	        }

	        classname = "bo.html.HTML" + productType + "MessageFormat";
	        instance = instantiate(name, classname);

	        return instance;
	         
	}


	public static HTMLFormatter findMessageFormatter(Message message,Trade trade) {
		MSFSelector selector = getMSFSelector(message);
        return selector.getMessageFormatter(message,trade);
    }


	 

	    public MessageFormat getMessageFormatter(String productType) {
	        return getMessageFormatter(productType, "",null);
	    }

	
	private static MSFSelector getMSFSelector(Message message) {
		// TODO Auto-generated method stub
		 MSFSelector instance = null;
	        String name = message.getMessageType();

	        synchronized (_selectors) {
	            instance = (MSFSelector) _selectors.get(name);
	        }
	        if (instance != null)
	            return instance;

	        String classname = "bo." + name + "MFSelector";
	        try {
	        	 Class class1 = ClassInstantiateUtil.getClass(classname,true);
	            instance = (MSFSelector)class1.newInstance();
	        } catch (Exception e) {
	        }
	        if (instance == null)
	            instance = new GenerateMessageFormatter();

	        synchronized (_selectors) {
	            _selectors.put(name, instance);
	        }
	        return instance;
		
		
	}
	 
	 static private HTMLFormatter instantiate(String name, String classname) {
		 HTMLFormatter instance = null;

	        try {
	        	 Class class1 = ClassInstantiateUtil.getClass(classname,true);
		          
	            instance = (HTMLFormatter) class1.newInstance();
	        } catch (Exception e) {
	        }
	        ;

	        if (instance != null) {
	            synchronized (_messageFormatters) {
	                _messageFormatters.put(name, instance);
	            }
	        }
	        return instance;
	    }
	static MessageFormat findMessageFormatter(String productType, String messageType) {
		MessageFormat instance = null;
        String name = messageType + productType;
        synchronized (_messageFormatters) {
            instance = (MessageFormat) _messageFormatters.get(name);
        }
        if (instance != null)
            return instance;

        String classname = "bo." + name + "MessageFormatter";
        instance = instantiate(name, classname);
        if (instance != null) {
            return instance;
        }

        classname = "bo." + productType + "MessageFormatter";
        instance = instantiate(name, classname);

        return instance;
    }


	@Override
	public HTMLFormatter getMessageFormatter(Message message, Trade trade) {
		// TODO Auto-generated method stub
		 String messageType = message.getMessageType();
	        String productType = message.getProductType();
	        return getMessageFormatter(productType, messageType,trade);
	}
	
	



}
