package bo.account;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Vector;

import productPricing.Pricer;
import util.ClassInstantiateUtil;
import util.commonUTIL;

import dsEventProcessor.EventProcessor;

import beans.AccConfigRule;
import beans.AccEventConfig;
import beans.Trade;

public  class AccountingHandler {
	
	
	static protected Hashtable _methods = new Hashtable();
    static protected Hashtable _methodsNotFound = new Hashtable();
	
    
    
    

	
    static void invokeMethodOnHandler(AccountingHandler accountingHandler,
                                         AccEventConfig eventConfig,
                                         Trade trade,
                                         EventProcessor event,
                                         Vector accountingEvents,
                                         AccConfigRule rule,
                                          Pricer pricer,
                                         Vector exceptions) {
    	Method method =
                getAccountingMethod(accountingHandler,eventConfig, "AccountingHandler");
    	 Object objs[] =  new Object[6];
         objs[0] =  trade;
         objs[1]=   event;
         objs[2] =  eventConfig;
         objs[3] =  accountingEvents;
         objs[4] =  rule;
         objs[5] =  pricer;
        
         try      {
             method.invoke(accountingHandler,objs);
             commonUTIL.display("AccountingHandler","invoke " + trade.getProductType() + " " + eventConfig.getAccEvtType() + "  method");
         } catch (Exception e) {
        	commonUTIL.displayError("AccountingHandler", "invokeMethodOnHandler",e);
        	 }
    	
    }
	
	static protected Method getAccountingMethod(Object object, AccEventConfig eventConfig, String handlerType) {
							
		StringBuffer sb= new StringBuffer();
		sb.append(object.getClass().getName());
		sb.append(eventConfig.getAccEvtType());
		String key= sb.toString();
		Method m=null;
		synchronized(_methodsNotFound) {
					if(_methodsNotFound.get(key) != null) return null;
		}
		synchronized(_methods) {
					m= (Method) _methods.get(key);
					if(m!=null) 
							return m;
		}

//look for Method
		Method methods[]= object.getClass().getMethods();
		String mname = "get"+eventConfig.getAccEvtType();
		for(int i=0;i<methods.length;i++)    {
				if(methods[i].getName().equals(mname) && methods[i].getDeclaringClass().getName().indexOf(handlerType) >= 0) {
							m=methods[i];
							break;
				}
		}
		if(m==null) {
				synchronized(_methodsNotFound) {
						if(_methodsNotFound.put(key,key) != null);
					}
		}
			else {
					synchronized(_methods) {
							if(_methods.put(key,m) != null);
						}
			}
		return m;
	}

	
	
	

}
