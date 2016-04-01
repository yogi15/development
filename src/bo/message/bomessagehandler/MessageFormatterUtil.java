package bo.message.bomessagehandler;

import java.util.Hashtable;
import bo.swift.TemplateSelector;
import util.ClassInstantiateUtil;
import util.commonUTIL;


import beans.DocumentInfo;
import beans.Message;
import beans.Trade;
import bo.html.GenerateMessageFormatter;
import bo.swift.EntityInfo;
public class MessageFormatterUtil {
	public static final String HTML="HTML";
    public static final String TEXT="TEXT";
    public static final String XML="XML";
    public static final String SWIFT="SWIFT";

    public static final String CCIL="CCIL";
    protected static Hashtable _messageGenerator = new Hashtable();
    protected static Hashtable _selectors         = new Hashtable();
    
    public static MessageFormat getFormatter(Message message,Trade trade) {
      DocumentInfo docInfo = null;
      MessageFormat instance = null;
      String name = message.getFormat();
        if (name == null) 
            return null;
        
           
            //    return findMessageFormatter(message);
            	 if (name.equalsIgnoreCase(SWIFT) || name.equalsIgnoreCase(CCIL)) {
            		    
            		 	String classname = "bo.swift." +SWIFT.toUpperCase() + "MessageGenerator";  
            		 	Class class1;
						try {
							class1 = ClassInstantiateUtil.getClass(classname,true);
						
								instance = (MessageFormat) class1.newInstance();
								_messageGenerator.put(classname, instance);
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}catch (InstantiationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
            	 }
            	 if (name.equalsIgnoreCase(HTML)) {
            		 
            		 instance =	GenerateMessageFormatter.findMessageFormatter(message,trade);
            	 }
            	 return instance;
            }
       
        
       

public static MessageFormat getFormatter(String name) {
  DocumentInfo docInfo = null;
  MessageFormat instance = null;
  //String name = message.getFormat();
    if (name == null) 
        return null;
    
       
        //    return findMessageFormatter(message);
        	 if (name.equalsIgnoreCase(SWIFT)) {
        		 	String classname = "bo.swift." +name.toUpperCase() + "MessageGenerator";  
        		 	Class class1;
					try {
						class1 = ClassInstantiateUtil.getClass(classname,true);
					
							instance = (MessageFormat) class1.newInstance();
							_messageGenerator.put(classname, instance);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
        	 }
        	 return instance;
        }

private static TemplateSelectorImpl  __impl = new TemplateSelectorImpl();

static public bo.swift.TemplateSelector getTemplateSelector(Trade trade) {    
    String productType = trade.getProductType();     
return getTemplateSelector(productType);
}


static public bo.swift.TemplateSelector getTemplateSelector(String productType) {    
	bo.swift.TemplateSelector instance = null;	
	if (productType==null)
	    return null;
	// Check in cache
	synchronized(_selectors) { 
	    instance=(bo.swift.TemplateSelector)_selectors.get(productType);
	}
	if(instance != null) return instance;
	String classname = "tk.bo." + productType + "TemplateSelector";
	Class class1 = null;
	try   {
		class1 = ClassInstantiateUtil.getClass(classname,true);
		instance = (bo.swift.TemplateSelector) class1.newInstance();
	}
	catch (Exception e) {}
	if (instance == null)
	    instance = __impl;
	    
	// Put in cache
	synchronized(_selectors) { 
	    _selectors.put(productType, instance);
	}	
	return instance;
   }


public static String getSelectedTemplate(Trade trade, Message message,
		String templateName) {
	// TODO Auto-generated method stub
	bo.swift.TemplateSelector ts= null;
	if (!commonUTIL.isEmpty(message.getTemplateName()) && 
	    message.getTemplateName().indexOf(".selector")>=0) {
	    int idx1 = message.getTemplateName().indexOf(".selector");
	    ts = getTemplateSelector(message.getTemplateName().substring(0,idx1));
	}
	else {
	    ts = getTemplateSelector(trade);
	}
	
	if(ts != null) {
	    templateName=ts.getTemplate(trade,message,templateName);
	}
	return templateName;
    
}




public static String getSelectedTemplate(Message message, String templateName) {
	// TODO Auto-generated method stub
	TemplateSelector ts= getTemplateSelector(message.getProductType());
	if(ts != null) {
	    templateName=ts.getTemplate(null,message,templateName);
	}
	return templateName;
    
}
   
    }
    

class TemplateSelectorImpl implements bo.swift.TemplateSelector {
    public String getTemplate(Trade trade, Message message, String name) {
        return name;
    }
}

	