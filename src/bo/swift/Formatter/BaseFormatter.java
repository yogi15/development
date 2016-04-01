package bo.swift.Formatter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;





import util.BackOfficeCache;
import util.ClassInstantiateUtil;
import util.ReferenceDataCache;
import util.ReflectionUtil;
import util.commonUTIL;
import util.common.DynUtil;
import util.common.LocalUtil;
import util.common.NumberFormatUtil;



import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import beans.DocumentInfo;
import beans.Fees;
import beans.LeContacts;
import beans.LegalEntity;
import beans.Message;
import beans.Sdi;
import beans.Trade;
import beans.Transfer;
import beans.TransferRule;
import bo.message.bomessagehandler.MessageFormat;
import bo.swift.ValueTag;
import bo.transfer.rule.ProductTransferRule;

public class BaseFormatter extends FormatterIterator implements  MessageFormat {
	
	 public static final String SEPARATOR = "/";
	    public static final String TEMPLATE = "templates";
	    public static final String CALYPSOX = "calypsox";
	    public static final String CALYPSO = "com.calypso";

	    static public final char KEYWORD_FUNCT_CHAR = '/';
	    static public final char KEYWORD_FIELD_CHAR = '.';
	    static public final char KEYWORD_PARAM_CHAR = '#';
	    static public final char KEYWORD_CHAR = '|';
	    static public final char BS_CHAR = '\\';
	   
	    static public final String END_OF_LINE = System.getProperty("line.separator");
	    /**
	     * age limit for parsed templates in the cache, in milliseconds. If set to 0, caching templates is disabled.
	     * Caching templates can reduce CPU use significantly (about 50% for FX).
	     *  
	     * The default is one second, which is good for development environments where templates may be changed often.
	     * In production environments, if template changes are performed while the SenderEngine is down,
	     * a much higher value can be used.
	     */
	    protected static final int TEMPLATE_CACHE_MAX_AGE = 1000; //Defaults.getIntProperty("TEMPLATE_CACHE_MAX_AGE", 1000);
	    protected static final boolean CACHE_TEMPLATES = TEMPLATE_CACHE_MAX_AGE > 0;
	    static protected Hashtable _methods = new Hashtable();
	    public transient Vector _currentFunctions;
	    public transient String _currentKeyword;
	    public transient Vector _currentKeywordParams;
	    public transient String  _currentLanguage = null;

	  //  private Cache _keywordValueCache = CacheClient.makeCache(CacheLimit.CACHE_FORMATTER_KEYWORD_VALUE);
	    private Hashtable _keywordValues = null;
	  //  protected static Cache _cache = null;
	    private static ConcurrentHashMap<String, byte[]> _templateCache = new ConcurrentHashMap<String, byte[]>();
	    private static HashSet<String> _notFoundCache = new HashSet<String>();
	    private static ConcurrentHashMap<String, Long> _templateCachingTimestamp = new ConcurrentHashMap<String, Long>();

	    public String getCurrentKeyword() {
	        return _currentKeyword;
	    }

	    public Vector getCurrentFunctions() {
	        return _currentFunctions;
	    }

	    public Vector getCurrentKeywordParams() {
	        return _currentKeywordParams;
	    }

	    

	    protected FormatterIterator _iterator;

	    /**
	     * Accessor object representing the Object returned by iterator during
	     * the processing of an iterator in a SWIFT sequence.
	     */
	    protected Object _iteratorObject;
	    /**
	     * Iteration count to keep track of which iteration is currently being
	     * processed when using an iterator through a SWIFT sequence.
	     */
	    protected int _iteration;
     RemoteTrade remoteTrade = null;
     RemoteBOProcess remoteBO = null;
	    /* Store formatter context for nested iterator */
	    protected Stack _contexts = new Stack();

	    /* Store the current context */
	    protected BaseFormattorContext _context = null;

	    public BaseFormatter() {
	       
	        _keywordValues = new Hashtable();
	    }
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	 ///////////////////////////////////////////////////////////////////
    ////
    //// Implements FormatterIterator to permit iterating over directives.
    //// Note that default implementation, this code, will only iterate
    //// once and return itself as the iterator object.  This is the
    //// default implementation which equates to no iteration at all.
    ////
    ///////////////////////////////////////////////////////////////////
	@Override
	public void init(Message message, Trade trade, LeContacts sender,
			LeContacts receiver, Transfer transfer, Vector transferRules,
			RemoteTrade remotetrade, RemoteBOProcess remoteBO,ProductTransferRule _productTransferRule) {
		// TODO Auto-generated method stub
		remoteTrade = remotetrade;
		remoteBO = remoteBO;
		 if (_iterator != null) {
			 message = getMessageFromContext(message);
             trade = getTrade(message, trade, remotetrade);
             transfer = getTransfer(message, remoteBO);
             transferRules = getTransferRules(message, trade, remoteBO);
             _iterator.init(message, trade, sender, receiver, transfer, transferRules,remotetrade,remoteBO,_productTransferRule);
                            
		 }
		
	}
	public FormatterIterator getIterator(String name) {
        Vector packageVector = new Vector();
        packageVector.addElement("tk.bo");
        setIterator(name, packageVector);

        return this;
    }

	
	
	
	
    public FormatterIterator getIterator(String name, Vector packageVector) {
        setIterator(name, packageVector);

        return this;
    }
	public FormatterIterator getIterator() {
        return _iterator;
    }

    public Object getIteratorObject() {
        return _iteratorObject;
    }

    public int getIteration() {
        return _iteration;
    }

    public int size() {
        return _iterator == null ? 1 : _iterator.size();
    }
	 private Vector getTransferRules(Message message, Trade trade,
			RemoteBOProcess remoteBO2) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	 
	 

	private Transfer getTransfer(Message message, RemoteBOProcess remoteBO2) {
		// TODO Auto-generated method stub
		return null;
	}

	private Trade getTrade(Message message, Trade trade,
			RemoteTrade remotetrade2) {
		// TODO Auto-generated method stub
		return null;
	}

	 public void setKeywordValue(String name, String value) {
	        _keywordValues.put(name, value);
	    }

	    public String getKeywordValue(String name) {
	        return (String)_keywordValues.get(name);
	    }

	    /**
	     * @param templateName
	     * @return
	     * Please note this uses the <code>static</code> (!) AbstractFormatter.getTamplateDirs()
	     */
	    public InputStream getInputStream(String templateName) {
	        return getInputStream(getClass().getClassLoader(), getTemplateHTMLDirs(),
	                              templateName);
	    }

	    static protected InputStream getInputStream(ClassLoader cl,
	                                                String templateName) {
	    	if(templateName.contains(".html")) 
	    		return getInputStream(cl, getTemplateHTMLDirs(), templateName);
	        return getInputStream(cl, getTemplateDirs(), templateName);
	    }

	    static protected InputStream getInputStream(ClassLoader cl, Vector dirs,
	                                                String templateName) {

	        InputStream stream = null;
	        stream = getStreamViaCache(cl, templateName);
	        if (stream != null) {
	            return stream;
	        }

	        if (dirs == null) {
	            return null;
	        }

	        String fileName = null;
	        String separator = "/";
	        for (int i = 0; i < dirs.size(); i++) {
	            String dirName = (String) dirs.elementAt(i);

	            fileName = dirName + templateName;
	            stream = getStreamViaCache(cl, fileName);
	            if (stream != null) {
	                return stream;
	            }

	            fileName = dirName + separator + templateName;
	            stream = getStreamViaCache(cl, fileName);
	            if (stream != null) {
	                return stream;
	            }

	            fileName = separator + dirName + separator + templateName;
	            stream = getStreamViaCache(cl, fileName);
	            if (stream != null) {
	                return stream;
	            }
	        }

	        commonUTIL.display("AbstractFormatter","Could not find Template: " + templateName);
	        return null;
	    }

	    
	    
	    
	    static protected Method getConditionMethod(Object object, String keyword) {
	        String className = object.getClass().getName();
	        String key = keyword
	                + "_" + className;
	        synchronized (_methods) {
	            Method m = (Method)_methods.get(key);
	            if (m != null)
	                return m;
	        }
	        Method methods[] = object.getClass().getMethods();
	        for (int i = 0; i < methods.length; i++) {
	            String mkey = methods[i].getName()
	                    + "_" + className;
	            synchronized (_methods) {
	                _methods.put(mkey, methods[i]);
	            }
	        }
	        synchronized (_methods) {
	            Method m = (Method)_methods.get(key);
	            if (m != null)
	                return m;
	        }
	        return null;
	    }
	    static private Method getMethod(Object object, String prefix, String keyword) {
	        String pkey = keyword.trim();
	        String mname = prefix + pkey;
	        String className = object.getClass().getName();
	        String key = mname + "_" + className;
	        synchronized (_methods) {
	            Method m = (Method) _methods.get(key);
	            if (m != null) {
	                return m;
	            }
	        }
	        Method methods[] = object.getClass().getMethods();
	        for (int i = 0; i < methods.length; i++) {
	            String mkey = methods[i].getName() +
	                "_" + className;
	            synchronized (_methods) {
	                Method m1 = (Method) _methods.get(mkey);
	                if (m1 != null) {
	                    if (methods[i].getParameterTypes().length >
	                        m1.getParameterTypes().length) {
	                        _methods.put(mkey, methods[i]);
	                    }
	                }
	                else {
	                    _methods.put(mkey, methods[i]);

	                }
	            }
	        }

	        synchronized (_methods) {
	            Method m = (Method) _methods.get(key);
	            if (m != null) {
	                return m;
	            }
	        }
	        return null;
	    }

	   
	   
	    
	    static public String getKeywordFunctions(String keyword, Vector result) {
	        int idxFunct = keyword.indexOf(KEYWORD_FUNCT_CHAR);
	        int idxParam = keyword.indexOf(KEYWORD_PARAM_CHAR);
	        if (idxFunct != -1 && (idxParam == -1 || idxParam > idxFunct)) {
	            String function = keyword.substring(0, idxFunct);
	            result.add(function);
	            return getKeywordFunctions(keyword.substring(idxFunct + 1), result);
	        } else {
	          
	                commonUTIL.display("FormatterParser",
	                          "Keyword Functions: ");// + commonUTIL.vector2String(result));
	            }
	            return keyword;
	        }
	    
	    
	    
	    private static InputStream getStreamViaCache(ClassLoader cl,
	                                                 String templateName) {
	        byte[] bytes;
	        long now = System.currentTimeMillis();
	        Long cacheTimestamp = _templateCachingTimestamp.get(templateName);
	        boolean aged = (CACHE_TEMPLATES && cacheTimestamp != null ? now - cacheTimestamp : 0) >= TEMPLATE_CACHE_MAX_AGE;
	        
	        if (aged || (bytes = _templateCache .get(templateName)) == null) {
	            if (!aged && _notFoundCache .contains(templateName)) return null;
	            InputStream is = cl.getResourceAsStream(templateName);
	            if (is == null) {
	                if (CACHE_TEMPLATES) {
	                    _notFoundCache.add(templateName);
	                    _templateCachingTimestamp.put(templateName, System.currentTimeMillis());
	                }
	                return null;
	            }
	            try {
	                bytes = commonUTIL.readBinaryFile(is);
	                if (CACHE_TEMPLATES) {
	                    _templateCache.put(templateName, bytes);
	                    _templateCachingTimestamp.put(templateName, System.currentTimeMillis());
	                }
	            } catch (IOException e) {
	                // we could not read this... the the caller deal with it
	                return cl.getResourceAsStream(templateName);
	            }
	        }
	        return new ByteArrayInputStream(bytes);
	    }

	    
	    static protected Vector getTemplateHTMLDirs() {
		     //   Vector packages = InstantiateUtil.getPackages();
		        Vector packages = new Vector();
		        packages.addElement("resources/templates/");
		        return packages;
		     /*   if (packages == null) {
		           

		        }
		        Vector v = new Vector();
		        // We iterate over packages from back to front as we want
		        // to look through custom directories before calypso dirs
		        // (see BZ 23777)
		        for (int i = packages.size()-1; i >= 0; i--) {
		            String packageName = (String) packages.elementAt(i);
		            String dir = packageName;
		            int index = dir.indexOf(".");
		            while (index != -1) {
		                dir = dir.substring(0, index) + SEPARATOR + dir.substring(index + 1);
		                index = dir.indexOf(".");
		            }
		            v.addElement(dir + SEPARATOR + TEMPLATE + SEPARATOR);
		        }
		        return v; */
		    }
	    
	    /**
	     * Returns the Trade Id.
	     */
	    public String parseTRADE_ID(Message message,
	                                Trade trade,
	                                LeContacts sender,
	                                LeContacts rec,
	                                Vector transferRules,
	                                Transfer transfer,
	                                Connection dsCon,ProductTransferRule productTransferRule) {
	        return String.valueOf(trade.getId());
	    }
	    
	    public String parseMESSAGE_ACTION(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {

				if (message.getAction().equals("CANCEL")) {
				return "cancel";
				}
				if (message.getAction().equals("AMEND")) {
				return "amend";
				}
				return "APPROVED";
				}
	    public String parsePO_INSTRUCTIONS(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
	    	String agentString = "TO : ";
	    	if(productTransferRule == null ) {
	    		TransferRule transferRule = (TransferRule) transferRules.elementAt(0);
	    	Sdi PO =   transferRule.get__sMethod();
	    	Sdi agent = PO.getAgentSdi();
	    	
	    	LeContacts agentContact = ReferenceDataCache.getLEContact("Agent", null, PO.getAgentId(), trade.getProductType(), agent.getAgentContacts());
	    	LeContacts poContact = ReferenceDataCache.getLEContact("PO", null, agent.getPoId(), trade.getProductType(),"Default");
	    	agentString = agentString + agentContact.getLeFirstName() + ", " +
	    			agentContact.getCity() + "<br />" + " SWIFT CODE : " + agentContact.getSwift()  + "<br />";
	    	agentString = agentString + " Favour Of: " + poContact.getLeFirstName()  + "<br />";
	    	agentString = agentString + " Account Number: " +  BackOfficeCache.getAccount(agent.getAccountID()).getAccountName()  + "<br />";
	    	//PO.getAgentId()
	    	//sgentString = agent.get
	    	}
	    	
	    	return agentString;
	    }

	    /**
	     * Return the Settlement Instruction of the CounterParty.
	     *
	     *
	     * @return a pre-formatted HTML String to display the Settlement
	     * Instructions of the CounterParty.
	     */

	    public String parseCOUNTERPARTY_INSTRUCTIONS(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
	    	String agentString = "TO : ";
	    	if(productTransferRule == null ) {
	    		TransferRule transferRule = (TransferRule) transferRules.elementAt(0);
	    	Sdi PO =   transferRule.get__sMethod();
	    	Sdi agent = PO.getAgentSdi();
	    	
	    	LeContacts agentContact = ReferenceDataCache.getLEContact("Agent", null, PO.getAgentId(), trade.getProductType(), agent.getAgentContacts());
	    	LeContacts cpContact = ReferenceDataCache.getLEContact("CounterParty", null, transferRule.get_receiverLegalEntityId(), trade.getProductType(),"Default");
	    	agentString = agentString + agentContact.getLeFirstName() + ", " +
	    			agentContact.getCity() + "<br />" + " SWIFT CODE : " + agentContact.getSwift()  + "<br />";
	    	agentString = agentString + " Favour Of: " + cpContact.getLeFirstName()  + "<br />";
	    	agentString = agentString + " Account Number: " +  BackOfficeCache.getAccount(agent.getAccountID()).getAccountName()  + "<br />";
}
	    	
	    	return agentString;
	    	//PO.getAgentId()
	    	//sgentString = agent.get
	    	
	    }

	    public String parsePRODUCT_DESCRIPTION(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
	    		return trade.getTradedesc();
}
	    public String parseSENDER_CODE(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
	    		return sender.getLeFirstName();
}
	    
	    public String parseRECEIVER_CODE(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
	    		return rec.getLeFirstName();
}
	    public String parseSETTLE_DATE(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
return String.valueOf(trade.getDelivertyDate());
}
	    
	    /**
	     * Returns the Currency of the Trade
	     *
	     */
	    public String parseTRADE_CURRENCY(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
	        return trade.getCurrency();
	    }
	    
	    public String parseTRADE_ENTEREDUSER(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
return String.valueOf(trade.getUserID());
}

	    
	    public String parseSENDER_ID(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
if (sender == null) {
return null;
}
return String.valueOf(sender.getId());
}

public String parseSENDER_LEID(Message message,
        Trade trade,
        LeContacts sender,
        LeContacts rec,
        Vector transferRules,
        Transfer transfer,
        Connection dsCon,ProductTransferRule productTransferRule) {
if (sender == null) {
return null;
}
return String.valueOf(sender.getLeId());
}

public String parseSENDER_ROLE(Message message,
        Trade trade,
        LeContacts sender,
        LeContacts rec,
        Vector transferRules,
        Transfer transfer,
        Connection dsCon,ProductTransferRule productTransferRule) {
if (sender == null) {
return null;
}
return sender.getLeRole();
}

public String parseSENDER_CONTACTTYPE(Message message,
        Trade trade,
        LeContacts sender,
        LeContacts rec,
        Vector transferRules,
        Transfer transfer,
        Connection dsCon,ProductTransferRule productTransferRule) {
if (sender == null) {
return null;
}
return sender.getContactCategory();
}

public String parseSENDER_CONTACT_NAME(Message message,
        Trade trade,
        LeContacts sender,
        LeContacts rec,
        Vector transferRules,
        Transfer transfer,
        Connection dsCon,ProductTransferRule productTransferRule) {
if (sender == null) {
return null;
}
return sender.getLeFirstName();
}
public String parseSENDER_ADDRESS(Message message,
        Trade trade,
        LeContacts sender,
        LeContacts rec,
        Vector transferRules,
        Transfer transfer,
        Connection dsCon,ProductTransferRule productTransferRule) {
if (sender == null) {
return null;
}
  return  sender.getMailingAddress();
}


public String parseSALUTATION(Message message,
        Trade trade,
        LeContacts sender,
        LeContacts rec,
        Vector transferRules,
        Transfer transfer,
        Connection dsCon,ProductTransferRule productTransferRule) {
String salutation = parseRECEIVER_CONTACT_NAME(message, trade, sender, rec,
                             transferRules, transfer, dsCon,productTransferRule);
// Bugzilla 18960
if (!commonUTIL.isEmpty(salutation)) {
return salutation;
}
return "Sir/Madam";
}
public String parseSENDER_EMAIL(Message message,
        Trade trade,
        LeContacts sender,
        LeContacts rec,
        Vector transferRules,
        Transfer transfer,
        Connection dsCon,ProductTransferRule productTransferRule) {
if (sender == null) {
return null;
}
return sender.getEmailAddresss();
}
				public String parseSENDER_TELEX(Message message,
				        Trade trade,
				        LeContacts sender,
				        LeContacts rec,
				        Vector transferRules,
				        Transfer transfer,
				        Connection dsCon,ProductTransferRule productTransferRule)  {
				if (sender == null) {
				return null;
				}
				
				return sender.getTelex();
				}
				  public String parseSENDER_PHONE(Message message,
					        Trade trade,
					        LeContacts sender,
					        LeContacts rec,
					        Vector transferRules,
					        Transfer transfer,
					        Connection dsCon,ProductTransferRule productTransferRule) {
if (sender == null) {
  return null;
}
return sender.getPhone();
}
				  public String parseSENDER_CITY(Message message,
					        Trade trade,
					        LeContacts sender,
					        LeContacts rec,
					        Vector transferRules,
					        Transfer transfer,
					        Connection dsCon,ProductTransferRule productTransferRule) {
if (sender == null) {
   return null;
}
return sender.getCity();
}
				  

				    public String parseSENDER_ZIPCODE(Message message,
					        Trade trade,
					        LeContacts sender,
					        LeContacts rec,
					        Vector transferRules,
					        Transfer transfer,
					        Connection dsCon,ProductTransferRule productTransferRule) {
				        if (sender == null) {
				            return null;
				        }
				       
				        return sender.getZipCode();
				    }
				    
				   
				    
				    
				    
				    public String parseTRADE_SELLER_NAME(Message message,
					        Trade trade,
					        LeContacts sender,
					        LeContacts rec,
					        Vector transferRules,
					        Transfer transfer,
					        Connection dsCon,ProductTransferRule productTransferRule) {
				      if(trade.getType().equalsIgnoreCase("BUY")) {
				    	  LegalEntity cp = ReferenceDataCache.getLegalEntity(trade.getCpID());
					       
					       return cp.getName();
				    	 
				      }
				      LegalEntity po = ReferenceDataCache.getPO(trade.getBookId());
			    	  return po.getName();
				    }
				    public String parseTRADE_BUYER_NAME(Message message,
					        Trade trade,
					        LeContacts sender,
					        LeContacts rec,
					        Vector transferRules,
					        Transfer transfer,
					        Connection dsCon,ProductTransferRule productTransferRule) {
				    	 if(trade.getType().equalsIgnoreCase("BUY")) {
					    	  LegalEntity po = ReferenceDataCache.getPO(trade.getBookId());
					    	  return po.getName();
					      }
					      LegalEntity cp = ReferenceDataCache.getLegalEntity(trade.getCpID());
					       
					       return cp.getName();
				       
				    }
				    public String parseSENDER_STATE(Message message,
					        Trade trade,
					        LeContacts sender,
					        LeContacts rec,
					        Vector transferRules,
					        Transfer transfer,
					        Connection dsCon,ProductTransferRule productTransferRule) {
if (sender == null) {
    return null;
} 
return sender.getState();
}
				    public String parseSENDER_COUNTRY(Message message,
					        Trade trade,
					        LeContacts sender,
					        LeContacts rec,
					        Vector transferRules,
					        Transfer transfer,
					        Connection dsCon,ProductTransferRule productTransferRule) {

if (sender == null) {
  return null;
}
return sender.getCountry();
}
				    
				    
				    

				    
				    public String parseRECEIVER_ID(Message message,
			                Trade trade,
			                LeContacts sender,
			                LeContacts rec,
			                Vector transferRules,
			                Transfer transfer,
			                Connection dsCon,ProductTransferRule productTransferRule) {
			if (sender == null) {
			return null;
			}
			return String.valueOf(rec.getId());
			}

			public String parseRECEIVER_LEID(Message message,
			        Trade trade,
			        LeContacts sender,
			        LeContacts rec,
			        Vector transferRules,
			        Transfer transfer,
			        Connection dsCon,ProductTransferRule productTransferRule) {
			if (sender == null) {
			return null;
			}
			return String.valueOf(rec.getLeId());
			}

			public String parseRECEIVER_ROLE(Message message,
			        Trade trade,
			        LeContacts sender,
			        LeContacts rec,
			        Vector transferRules,
			        Transfer transfer,
			        Connection dsCon,ProductTransferRule productTransferRule) {
			if (sender == null) {
			return null;
			}
			return rec.getLeRole();
			}

			public String parseRECEIVER_CONTACTTYPE(Message message,
			        Trade trade,
			        LeContacts sender,
			        LeContacts rec,
			        Vector transferRules,
			        Transfer transfer,
			        Connection dsCon,ProductTransferRule productTransferRule) {
			if (rec == null) {
			return null;
			}
			return rec.getContactCategory();
			}

			public String parseRECEIVER_CONTACT_NAME(Message message,
			        Trade trade,
			        LeContacts sender,
			        LeContacts rec,
			        Vector transferRules,
			        Transfer transfer,
			        Connection dsCon,ProductTransferRule productTransferRule) {
			if (sender == null) {
			return null;
			}
			return rec.getLeFirstName();
			}
			public String parseRECEIVER_ADDRESS(Message message,
			        Trade trade,
			        LeContacts sender,
			        LeContacts rec,
			        Vector transferRules,
			        Transfer transfer,
			        Connection dsCon,ProductTransferRule productTransferRule) {
			if (sender == null) {
			return null;
			}
			  return  rec.getMailingAddress();
			}

			public String parseRECEIVER_EMAIL(Message message,
			        Trade trade,
			        LeContacts sender,
			        LeContacts rec,
			        Vector transferRules,
			        Transfer transfer,
			        Connection dsCon,ProductTransferRule productTransferRule) {
			if (sender == null) {
			return null;
			}
			return rec.getEmailAddresss();
			}
							public String parseRECEIVER_TELEX(Message message,
							        Trade trade,
							        LeContacts sender,
							        LeContacts rec,
							        Vector transferRules,
							        Transfer transfer,
							        Connection dsCon,ProductTransferRule productTransferRule)  {
							if (sender == null) {
							return null;
							}
							
							return sender.getTelex();
							}
							  public String parseRECEIVER_PHONE(Message message,
								        Trade trade,
								        LeContacts sender,
								        LeContacts rec,
								        Vector transferRules,
								        Transfer transfer,
								        Connection dsCon,ProductTransferRule productTransferRule) {
			if (sender == null) {
			  return null;
			}
			return rec.getPhone();
			}
							  public String parseRECEIVER_CITY(Message message,
								        Trade trade,
								        LeContacts sender,
								        LeContacts rec,
								        Vector transferRules,
								        Transfer transfer,
								        Connection dsCon,ProductTransferRule productTransferRule) {
			if (sender == null) {
			   return null;
			}
			return rec.getCity();
			}
							  

							    public String parseRECEIVER_ZIPCODE(Message message,
								        Trade trade,
								        LeContacts sender,
								        LeContacts rec,
								        Vector transferRules,
								        Transfer transfer,
								        Connection dsCon,ProductTransferRule productTransferRule) {
							        if (sender == null) {
							            return null;
							        }
							       
							        return sender.getZipCode();
							    }
							    public String parseRECEIVER_STATE(Message message,
								        Trade trade,
								        LeContacts sender,
								        LeContacts rec,
								        Vector transferRules,
								        Transfer transfer,
								        Connection dsCon,ProductTransferRule productTransferRule) {
			if (sender == null) {
			    return null;
			} 
			return rec.getState();
			}
							    public String parseRECEIVER_COUNTRY(Message message,
								        Trade trade,
								        LeContacts sender,
								        LeContacts rec,
								        Vector transferRules,
								        Transfer transfer,
								        Connection dsCon,ProductTransferRule productTransferRule) {

			if (sender == null) {
			  return null;
			}
			return rec.getCountry();
			}
				    
				    
							    public String parseRECEIVER_FULL_NAME(Message message,
								        Trade trade,
								        LeContacts sender,
								        LeContacts rec,
								        Vector transferRules,
								        Transfer transfer,
								        Connection dsCon,ProductTransferRule productTransferRule) {
      return rec.getLeFirstName() ;
  }
							    public String parseSENDER_FULL_NAME(Message message,
								        Trade trade,
								        LeContacts sender,
								        LeContacts rec,
								        Vector transferRules,
								        Transfer transfer,
								        Connection dsCon,ProductTransferRule productTransferRule) {
      return sender.getLeFirstName();
  }
							   
							    public String parseRECEIVER_SWIFT(Message message,
								        Trade trade,
								        LeContacts sender,
								        LeContacts rec,
								        Vector transferRules,
								        Transfer transfer,
								        Connection dsCon,ProductTransferRule productTransferRule) {
	       
	        return rec.getSwift();
	    }
							    public String parseSENDER_SWIFT(Message message,
								        Trade trade,
								        LeContacts sender,
								        LeContacts rec,
								        Vector transferRules,
								        Transfer transfer,
								        Connection dsCon,ProductTransferRule productTransferRule) {
	       
	        return sender.getSwift();
	    }
							    
							    /*
							     ** Message KeyWords
							     */
							     public String parseMESSAGE_ID(Message message,
									        Trade trade,
									        LeContacts sender,
									        LeContacts rec,
									        Vector transferRules,
									        Transfer transfer,
									        Connection dsCon,ProductTransferRule productTransferRule) {

							         return String.valueOf(message.getId());
							     }

							     public String parseMESSAGE_TRADEID(Message message,
									        Trade trade,
									        LeContacts sender,
									        LeContacts rec,
									        Vector transferRules,
									        Transfer transfer,
									        Connection dsCon,ProductTransferRule productTransferRule) {

							         return String.valueOf(message.getTradeId());
							     }
							    
							    
							    
	    /**
	     * Returns the Settlement Currency of the Trade
	     *
	     */
	    public String parseTRADE_SETTLECCY(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
	        return trade.getCurrency();
	    }

	    
	    /**
	     * Returns the quantity of the Trade.
	     *
	     */
	    public String parseTRADE_QUANTITY(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {

	        double quantity = Math.abs(trade.getQuantity());
	        return numberToString(quantity);
	    }

	    public String parseTRADE_PRICE(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {

	        double price = Math.abs(trade.getPrice());
	        return NumberFormatUtil.numberToString(price);
	    }
	    
	    
	    /**
	     * Returns the Product Type linked to the Trade.
	     *
	     */
	    final public String parsePRODUCT_TYPE(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
	    	if(trade.getProductType().equalsIgnoreCase("FX")) 
	    		return trade.getTradedesc1();
	        return trade.getProductType();
	    }
	    public String parseMESSAGE_TYPE(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {

return message.getMessageType();
}
	    public String parseDATE(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {

return commonUTIL.getCurrentDateTime();
}
	    public String parseADDITIONAL_FIXED_AMOUNT(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule)
	    {
	    	LegalEntity po = ReferenceDataCache.getPO(trade.getBookId());
	        if (trade.getFees() == null || trade.getFees().size() == 0)
	            return "";
	        Vector datas = new Vector();
	        boolean first_time = true;
	        for (int i = 0; i < trade.getFees().size(); i++) {
	            Fees fee = (Fees) trade.getFees().elementAt(i);
	           
	          //  if (fee.getLeID() != po.getId()
	            //            && fee.getLeID() != trade.getCpID())
	              //                            continue;
	            if (fee.getFeeType().equals("PREMIUM"))
	                continue;
	            if (first_time) {
	                datas.addElement("<b><u>Additional Fixed Amount</u></b>");
	                datas.addElement("");
	                datas.addElement("");
	            }
	            
	            first_time = false;
	            datas.addElement("Fixed Rate Payer");
	            datas.addElement(":");

	            if (fee.getAmount() > 0)
	                datas.addElement((ReferenceDataCache.getLegalEntity(trade.getCpID())).getName());
	            else {
	                datas.addElement(po.getName());
	            }
	            datas.addElement("Fixed Rate Amount");
	            datas.addElement(":");
	            datas.addElement(commonUTIL.converDoubleToString(fee.getAmount()));
	            datas.addElement("Fixed Rate Payment Date");
	            datas.addElement(":");
	            datas.addElement( fee.getFeeDate());
	        }
	        if (datas.size() == 0)
	            return "";
	        String data[][] = new String[datas.size() / 3][3];
	        for (int i = 0; i < datas.size(); i = i + 3) {
	            data[i / 3][0] = (String) datas.elementAt(i);
	            data[i / 3][1] = (String) datas.elementAt(i + 1);
	            data[i / 3][2] = (String) datas.elementAt(i + 2);
	        }
	        String htmlString = array2Table(data);
	        return htmlString;
	    }
	    static public String array2Table(String data[][]) {        
	        return array2Table(data, 2, null);
	    }
	    
	    static public String array2Table(String data[][], int fontSize, int colWidth[]) {
	        int cols = data[0].length;
	        int rows = data.length;
	        StringBuffer buffer = new StringBuffer();
	        buffer.append("<br />");
	        buffer.append("<table>");
	        //generate all the other rows
	        for (int i = 0; i < rows; i++) {
	            buffer.append("<tr>");
	            for (int j = 0; j < cols; j++) {
	            	int width = 0;
	            	if ((colWidth!=null)&&(colWidth.length>j))
	            		width = colWidth[j];
	                buffer.append("<td valign=\"top\" width=\""+width+"%\">");
	                buffer.append("<font size=\""+fontSize+"\">");
	                buffer.append(data[i][j]);
	                buffer.append("</font>");
	                buffer.append("</td>");
	            }
	            buffer.append("</tr>");
	        }
	        buffer.append("</table>");
	        return buffer.toString();
	    }

	    public String parseSENDER_TITLE(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {

return sender.getLeTitle();
}
	    public String parseRECEIVER_TITLE(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {

return sender.getLeTitle();
}
	    /**
	     * Returns the Product Id linked to the Trade.
	     *
	     */
	    public String parsePRODUCT_ID(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
	        return String.valueOf(trade.getProductId());
	    }
	    /**
	     * Returns the Trade Date.
	     *
	     */
	    public String parseTRADE_DATE(Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
	        return  trade.getTradeDate();//(//parseJDate(message,	                           JDate.valueOf(trade.getTradeDate(), trade.getBook().getLocation())));
	        		
	    }
	    public static String parseJDate(Message message, Date date) {
	        if (date == null) {
	            return "NULL DATE";
	        }
	        String locs = message.getLanguage();
	        Locale loc = LocalUtil.getLocale(locs);
	        loc = Locale.getDefault();
	      
	        
	        return commonUTIL.dateToLongString(date, loc);
	    }
	    static protected Vector getTemplateDirs() {
	     //   Vector packages = InstantiateUtil.getPackages();
	        Vector packages = new Vector();
	        packages.addElement("resources/templates/swift/");
	        return packages;
	     /*   if (packages == null) {
	           

	        }
	        Vector v = new Vector();
	        // We iterate over packages from back to front as we want
	        // to look through custom directories before calypso dirs
	        // (see BZ 23777)
	        for (int i = packages.size()-1; i >= 0; i--) {
	            String packageName = (String) packages.elementAt(i);
	            String dir = packageName;
	            int index = dir.indexOf(".");
	            while (index != -1) {
	                dir = dir.substring(0, index) + SEPARATOR + dir.substring(index + 1);
	                index = dir.indexOf(".");
	            }
	            v.addElement(dir + SEPARATOR + TEMPLATE + SEPARATOR);
	        }
	        return v; */
	    }
	    public String readAndParseContents(InputStream stream, Message message,
                Trade trade, LeContacts sender,
                LeContacts rec, Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {

				StringBuffer output = new StringBuffer();
				
				try {
				InputStreamReader reader = new InputStreamReader(stream);
				BufferedReader bufReader = new BufferedReader(reader);
				try {
				StringBuffer buffer = new StringBuffer();
				String line = bufReader.readLine();
				while (line != null) {
				buffer.append(line + END_OF_LINE);
				line = bufReader.readLine();
				}
				output.append(parse(buffer.toString(),
				             message,
				             trade,
				             sender,
				             rec,
				             transferRules,
				             transfer,
				             dsCon,productTransferRule));
				}
				catch (Exception e) {
				commonUTIL.displayError("BaseFormatter", "readAndParseContents", e);
				}
				}
				catch (Exception e) {
					commonUTIL.displayError("BaseFormatter", "readAndParseContents", e);
				output = null;
				return null;
				}
				return output.toString();
				}
	    public String parse(String line,
                Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
                Connection dsCon,ProductTransferRule productTransferRule) {
	    				beans.KeywordInfo keywordInfo = getKeyword(line);
					if (keywordInfo == null || keywordInfo.name.length() == 0) {
					    return replaceEscapeChar(line);
					}
					
					int idxStart = keywordInfo.start;
					int idxEnd = keywordInfo.end;
					if (idxStart < 0) {
					    idxStart = 0;
					}
					if (idxEnd > line.length()) {
					    idxEnd = line.length();
					
					}
					String replace = parseKeyword(keywordInfo.name, message, trade, sender, rec, transferRules, transfer, dsCon,productTransferRule);
					
					String newLine = line.substring(0, idxStart) +
					    replace +
					    parse(line.substring(idxEnd + 1), message, trade,
					          sender, rec, transferRules, transfer, dsCon,productTransferRule);
					return newLine;
					}
					
					

					
					 static public String replaceEscapeChar(String line) {
					        StringBuffer sb = new StringBuffer();
					        char ar[] = line.toCharArray();
					        int l = ar.length;
					        int i = 0;
					        while (i < l) {
					            if (ar[i] == '\\' && (i < l - 1) && ar[i + 1] == '|') {
					                sb.append( (char) '|');
					                i++;
					                i++;
					            }
					            else {
					                sb.append( (char) ar[i]);
					                i++;
					            }
					        }
					        return sb.toString();
					    }

					    /**
					     * @deprecated Use {@link #getParseMethod(Object,String)} instead.
					     */
					    static protected Method getMethod(Object object, String keyword) {
					        return getParseMethod(object, keyword);
					    }

					    static protected Method getParseMethod(Object object, String keyword) {
					        return getMethod(object, "parse", keyword);
					    }

					    static protected Method getApplyMethod(Object object, String keyword) {
					        return getMethod(object, "apply", keyword);
					    }

					   
	@Override
	public boolean display(Message message, DocumentInfo docInfo,
			RemoteTrade remoteTrade, RemoteReferenceData remoteRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DocumentInfo generate(Message message, Trade trade,
			Transfer transfer, boolean newDocument, RemoteTrade remoteTrade,
			RemoteReferenceData remoteRef) {
		// TODO Auto-generated method stub
		return null;
	} 
	static public beans.KeywordInfo getKeyword(String line) {

        
           commonUTIL.display("BaseFormatter", " KeywordInfo --- >  Looking for Keyword IN (" + line + ")");
        

        int l = line.length();
        int idxStart = line.indexOf(KEYWORD_CHAR);
        if (idxStart == -1) {
            return null;
        }
        if (idxStart > 0 &&
            line.charAt(idxStart - 1) == BS_CHAR) {
            return getKeyword(line.substring(idxStart + 1));
        }
        int idxEnd = line.indexOf(KEYWORD_CHAR, idxStart + 1);
        if (idxEnd == -1) {
            return null;
        }
        while (idxEnd > 0 && line.charAt(idxEnd - 1) == BS_CHAR) {
            idxEnd = line.indexOf(KEYWORD_CHAR, idxEnd + 1);
            if (idxEnd == -1) {
                idxEnd = l;
                break;
            }
        }
    //    if (Log.isCategoryLogged(Log.OLD_TRACE)) {
        commonUTIL.display("getKeyword", "Found Keyword  (" +
                     line.substring(idxStart + 1, idxEnd) + ")");

      //  }
        String name = line.substring(idxStart + 1, idxEnd);
        return new beans.KeywordInfo(name, idxStart, idxEnd);
    }
	static public String getKeywordName(String keyword) {
        int idxEnd = keyword.indexOf(KEYWORD_PARAM_CHAR);
        if (idxEnd != -1) {
            return keyword.substring(0, idxEnd);
        }
        else {
            return keyword;
        }
    }
    static public Vector getKeywordParams(String keyword) {
        Vector params = new Vector();
        int idxStart = keyword.indexOf(KEYWORD_PARAM_CHAR);
        if (idxStart == -1) {
            return params;
        }
        int idxEnd;
        int l = keyword.length();
        while (idxStart < l) {
            idxEnd = keyword.indexOf(KEYWORD_PARAM_CHAR, idxStart + 1);
            if (idxEnd == -1) {
                params.addElement(keyword.substring(idxStart + 1));
                break;
            }
            params.addElement(keyword.substring(idxStart + 1, idxEnd));
            idxStart = idxEnd;
        }

      //  if (Log.isCategoryLogged("FormatterParser")) {
           
            commonUTIL.display("SwiftGenerator", "FormatterParser");
     //   }
        return params;
    }
	private void setIterator(String name, Vector packageVector) {
        FormatterIterator iter = null;
        Collection params = null;
        for (int i = 0; i < packageVector.size(); i++) {
            params = getKeywordParams(name);
            if (params.size() > 0) {
                name = getKeywordName(name);
            }
            String fullName = packageVector.elementAt(i) + "." + name;
            /*
             * Standard iterator naming convention suggests that the
             * iterator implementation resides in given package
             * with a name ending with Iterator.  Hence, if the given
             * name is 'CashFlow', then we look for a class in the package named
             * <code>CashFlowIterator</code>.
             */
            try {
                iter = (FormatterIterator) getInstance(fullName + "Iterator");
            }
            catch (Exception e) {}
            if (iter == null) {
                // Next, we try to simply look for the named file itself.  Perhaps the
                // template mistakenly includes the Iterator suffix or the naming
                // convention is not maintained in our code
                try {
                    iter = (FormatterIterator)getInstance(fullName);
                }
                catch (Exception e) {}
                if (iter == null) {
                    try {
                        // Lastly, we search for iterator in external package not
                        // typically included and/or the full class name is given
                        // in iterator name
                        iter = (FormatterIterator)getInstance(name);
                    }
                    catch (Exception e) {}
                }
            }
            if (iter != null) {
                break;
            }
        }
        if (iter == null) {
     //       Log.error(this, "Cannot instantiate FormatterIterator Iterator: " + name);
            commonUTIL.display("SwiftGenerator", "SetIterator");
        }

        // save context in a stack
        // Object tmp = null;
        // Don't push contact here. see SWIFTFormatter.generateSequence
        // FormatterContext c = new FormatterContext();
        // if (_iterator != null){
        // c.setIterator(_iterator);
        // c.setIteration(_iteration);
        // c.setIteratorObject(_iteratorObject);
        //}
        //c.setIteration(_iteration); // need to save _iteration for the case
        // of no iteration.
        //_contexts.push(c);

        iter.setParameters(params);
        _iterator = iter;
        _iteration = 0;
    }
	boolean USE_MESSAGE_AMOUNT_FORMAT_B = true;
	protected Locale getMessageLocale() {

        if (!USE_MESSAGE_AMOUNT_FORMAT_B)
            return null;


        Locale locale = null;

        Vector params = getCurrentKeywordParams();
    if (!commonUTIL.isEmpty(params)) {
        for (int i = 0; i < params.size();i++) {
        String myString = (String)params.get(i);
        //Ignore rounding
        if (myString.indexOf("ROUNDING") >=0) continue;
        if (!commonUTIL.isEmpty(myString)) {
            Locale le =  LocalUtil.getLocale(myString);
            if (le != null) return null;
        }
        }
    }
    String localeStr = _currentLanguage;
    if (!commonUTIL.isEmpty(localeStr)) {
        return LocalUtil.getLocale(localeStr);
    }
        return null;
    }
	 protected int getRoundingUnit() {

		    Vector params = getCurrentKeywordParams();
		    if (!commonUTIL.isEmpty(params)) {
		        for (int i = 0; i < params.size();i++) {
		        String roundingStr = (String)params.get(i);
		        //Ignore rounding
		        if (roundingStr.indexOf("ROUNDING") <0) continue;
		        String ss = roundingStr.substring(8);
		        return Integer.parseInt(ss);
		        }
		    }
		        return -1;
		    }
	 
	 protected String numberToString(double number) {

	        Locale locale = getMessageLocale();
	        if (locale == null) {
	        int ii = getRoundingUnit();
	        if (ii == -1)
	        return NumberFormatUtil.numberToString(number);
	        else
	        return NumberFormatUtil.numberToString(number,ii);
	    }
	        else {
	        int ii = getRoundingUnit();
	        if (ii == -1)
	        return NumberFormatUtil.numberToString(number, locale);
	        else
	        return NumberFormatUtil.numberToString(number, ii, locale);
	    }
	    }

	 static public Object getValue(String keyword, Object argObj) {
	        int idx = keyword.indexOf(KEYWORD_FIELD_CHAR);
	        if (idx == -1) {
	            return null;
	        }
	        //parse all X.Y.Z
	        int idxStart = 0;
	        int idxEnd;
	        int l = keyword.length();
	        Vector v = new Vector();
	        while (idxStart < l) {
	            idxEnd = keyword.indexOf(KEYWORD_FIELD_CHAR, idxStart);
	            if (idxEnd == -1) {
	                v.addElement(keyword.substring(idxStart));
	                idxStart = l; //stop the loop
	            }
	            else {
	                v.addElement(keyword.substring(idxStart, idxEnd));
	                idxStart = idxEnd + 1;
	            }
	        }
	        String first = (String) v.elementAt(0);
	        Object obj = argObj;
	        for (int ii = 1; ii < v.size(); ii++) {
	            String fname = (String) v.elementAt(ii);
	            fname = fname.toLowerCase();
	            Field[] fields = ReflectionUtil.getAllFields(obj);
	            for (int i = 0; i < fields.length; i++) {
	                String name = DynUtil.convertFieldName(fields[i]);
	                name = name.toLowerCase();
	                if (name.equals(fname)) {
	                    obj = DynUtil.get(obj, fields[i]);
	                    if (obj == null) {
	                        return null;
	                    }
	                    break;
	                }
	            }
	        }
	        return obj;
	    }

	    static public Object getValue(String keyword, Trade trade) {
	        int idx = keyword.indexOf(KEYWORD_FIELD_CHAR);
	        if (idx == -1) {
	            return null;
	        }
	        //parse all X.Y.Z
	        int idxStart = 0;
	        int idxEnd;
	        int l = keyword.length();
	        Vector v = new Vector();
	        while (idxStart < l) {
	            idxEnd = keyword.indexOf(KEYWORD_FIELD_CHAR, idxStart);
	            if (idxEnd == -1) {
	                v.addElement(keyword.substring(idxStart));
	                idxStart = l; //stop the loop
	            }
	            else {
	                v.addElement(keyword.substring(idxStart, idxEnd));
	                idxStart = idxEnd + 1;
	            }
	        }
	        String first = (String) v.elementAt(0);
	        Object obj = trade;
	        if (!first.equals("Trade")) {
	            obj = trade.getProduct();
	        }
	        for (int ii = 1; ii < v.size(); ii++) {
	            String fname = (String) v.elementAt(ii);
	            fname = fname.toLowerCase();

	            
	            commonUTIL.display("BaseFormatter", "Field name = " + fname);

	            Field[] fields = DynUtil.getAllFields(obj);
	            for (int i = 0; i < fields.length; i++) {
	                String name = DynUtil.convertFieldName(fields[i]);
	                name = name.toLowerCase();
	                commonUTIL.display("BaseFormatter", "Class Name:"
                            + obj.getClass().getName()
                            + " Field= " + name);
	              

	                if (name.equals(fname)) {
	                  
	                    commonUTIL.display("BaseFormatter",  "Name found :" + name);
	                    obj = DynUtil.get(obj, fields[i]);
	                    if (obj == null) {
	                        return null;
	                    }
	                    break;
	                }
	            }
	        }
	        return obj;
	    }

	private Object getInstance(String name) {
    	Object object = null;
    	if(name != null) {
    		  Class class1;
			try {
				class1 = ClassInstantiateUtil.getClass(name,
							true);
				
					return class1.newInstance();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		 
    	}
    	return object;
    }
	  protected ValueTag  _tagValue = null;
	 protected void setTagValue(ValueTag tagValue) {
	        if (_tagValue != null) {
	            _tagValue.setOption(tagValue.getOption());
	            _tagValue.setValue(tagValue.getValue());
	            _tagValue.setLegalEntityId(tagValue.getLegalEntityId());
	            _tagValue.setBicCode(tagValue.getBicCode());
	            _tagValue.setOverrideOptionB(tagValue.getOverrideOptionB());
	        }
	    }

	  protected Message getMessage(Message message) {
	        Object iterobj = getIteratorObject();
	        Message iteratorMessage = message;
	        if ((iterobj != null) && (iterobj instanceof Message)){
	            iteratorMessage = (Message) iterobj;
	        }
	        return iteratorMessage;
	    }

	    protected Message getMessageFromContext(Message message) {
	        BaseFormattorContext c = null;
	        if (!_contexts.empty()){
	            c = (BaseFormattorContext) _contexts.peek();
	        }
	        Object iterobj = null;
	        if (c != null){
	            iterobj = c.getIteratorObject();
	        }

	        Message iteratorMessage = message;
	        if ((iterobj != null) && (iterobj instanceof Message)){
	            iteratorMessage = (Message) iterobj;
	        }
	        return iteratorMessage;
	    }
	    /**
	     * Returns the keyword functions to apply by default for the given keyword.
	     * By default, there is none. But children classes can override this method
	     * to change that.
	     *
	     * @param keyword the current keyword
	     * @return the default keyword functions for that keyword
	     */
	    public Vector getDefaultKeywordFunctions(String keyword) {
	        return new Vector();
	    }
	    
	    public Object getKey(String keyword, Message message,Trade trade,Transfer transfer) {
	    		int messageId = 0, messageVersion = 0, tradeId = 0, transferId = 0;
	    		if (message != null) {
	    				messageId = message.getId();
	    					messageVersion = message.getVersion();
	    		}
	    		if (trade != null) {
	    				tradeId = trade.getId();
	    		}
	    		if (transfer != null) {
	    			transferId = transfer.getId();
	    		}
	    		return keyword + "/" + messageId + "/" + messageVersion + "/" + tradeId + "/" + transferId;
	    }

	public String parseKeyword(String keyword_, Message message, Trade trade,LeContacts sender, LeContacts rec, Vector transferRules,Transfer transfer, Connection con, boolean b,ProductTransferRule productTransferRule) {
		// TODO Auto-generated method stub
		  			String trimKeyword = keyword_.trim();
		  			Vector functions = getDefaultKeywordFunctions(trimKeyword);
		  			if (functions == null) {
		  					functions = new Vector();
		  			}
		  			String rem = getKeywordFunctions(trimKeyword, functions);
		  			String keyword = getKeywordName(rem);
		  			Vector params = getKeywordParams(rem);
	        
		  			Object key = getKey(keyword_, message, trade, transfer);
		  			String val = null;
		  			//if (useCache) {
		  				//   val = (String) _keywordValueCache.get(key);
		  			//}
		  			if (val != null) {
		  					return val;
		  			}
		  			_currentFunctions = functions;
		  			_currentKeyword = keyword;
		  			_currentKeywordParams = params;
		  			if (message != null)
		  					_currentLanguage = message.getLanguage();
		  					Object objs[] = new Object[8];
					        objs[0] = message;
					        objs[1] = trade;
					        objs[2] = sender;
					        objs[3] = rec;
					        objs[4] = transferRules;
					        objs[5] = transfer;
					    //    objs[6] = message.getFormat();
					        objs[6] = con;
					        objs[7] = productTransferRule;
					        objs = getKeywordParams(objs);
					        message = (Message)objs[0];
					        trade = (Trade)objs[1];
					        sender = (LeContacts)objs[2];
					        rec = (LeContacts)objs[3];
					        transferRules = (Vector)objs[4];
					        transfer = (Transfer)objs[5];
					        con = (Connection)objs[6];
					        productTransferRule =  (ProductTransferRule)objs[7];
					        Method m = getParseMethod(this, keyword);
					        String res = null;

					        if (m != null) {
					        	try {
					        		res = (String) m.invoke(this, objs);
					        	}
					        	catch (Exception e) {
					        			commonUTIL.displayError("BaseFormatter","Keyword (" + keyword + ")" +  " Params " + params + " Method (" + m.getName() + ")" +   " MessageFormatter " +   getClass().getName(),e);
	              
					        	}
					        }
					        if (res == null) {
					        		res = ""; // Defined method but no value (but NO ERROR)
					        }
					        if (_currentFunctions != null) {
					        	res = applyFunctions(res, message, trade, sender, rec, transferRules, transfer, con,productTransferRule);
					        }
					        
					        return res;

	}
		public String applyFunctions(String value,Message message,Trade trade,LeContacts sender,LeContacts rec, Vector transferRules,Transfer transfer,Connection con,ProductTransferRule productTransferRule) {
				if (_currentFunctions != null) {
						// We apply functions from the right to the left. This way
						// TRIM/UPPERCASE/TRADE_ID eq. applyTRIM(applyUPPERCASE(parseTRADE_ID(...)))
						for (int i = _currentFunctions.size() - 1; i >= 0; i--) {
								String function = (String)_currentFunctions.elementAt(i);
								Method m = getApplyMethod(this, function);
								if (m == null) {
									break;
								}
								Object objs[] = new Object[9];
								objs[0] = value;
								objs[1] = message;
								objs[2] = trade;
								objs[3] = sender;
								objs[4] = rec;
								objs[5] = transferRules;
								objs[6] = transfer;
								objs[7] = con;
								objs[8] = productTransferRule;
								String res = null;
								try {
								res = (String) m.invoke(this, objs);
							}
								catch (Exception e) {
										commonUTIL.displayError("BaseFormatter","Function (" + function + ")" +      " Value " + value +   " Method (" + m.getName() + ")" +   " MessageFormatter " +  getClass().getName(),e);

										break;
								}
								if (res == null) {
									value = ""; // Defined method but no value (but NO ERROR)
								} else {
									value = res;
								}
						}
				}
				return value;
}
		
		static public String name ="BaseFormatter";
		 /**
	     * Format a Numer (Double, Integer, etc.) as a Localized String, according with BOMessage Locale  
	     * @param message
	     * @param amount
	     * @return
	     */
	    public String parseNumber(Message message, Number amount) {
	        if (amount == null) {
	            return "NULL AMOUNT";
	        }
	        String locs = message.getLanguage();
	        Locale loc = LocalUtil.getLocale(locs);
	        if (loc == null) {
	            loc = Locale.getDefault();
	        }
	        return NumberFormatUtil.numberToString(amount.doubleValue(), loc);
	    }    
	    
	    
	    static public String         getTradeKeyword(String keyword, Trade trade, Connection dsCon) {
        int l = new String("KEYWORD").length();
        keyword = keyword.substring(l + 1, keyword.length());
        commonUTIL.display(name, "getTradeKeyword (" + keyword + ")");
       
        String s = trade.getAttributes();
        if (s != null) {
            return s;
        }
        else {
            return "  ";
        }

    }
	/**
     * This method is called before processing each keyword.
     * It gives the opportunity to the formatter to substitute
     * the parameters that will be used (e.g. replace the transfer
     * by the netted transfer, the message by it's linked message, etc.).
     * 
     * Typically the behavior of this method is driven by the keyword parameters
     * (accessible from <code>_currentKeywordParams</code>).
     * 
     * The values in <code>objs</code> are:
     * <ol>
     *  <liOMessage</li>
     *  <li>Trade</li>
     *  <li>LeContacts (sender)</li>
     *  <li>LeContacts (receiver)</li>
     *  <li>Vector of transfer rules</li>
     *  <li>Transfer</li>
     *  <li>Connection</li>
     * </ol>
     * 
     * Note that the values must not be altered without proper cloning.
     * 
     * @param objs the default parameters
     * @return the actual parameters
     */
    public Object[] getKeywordParams(Object[] objs) {
        return objs;
    }

	public String parseKeyword(String keyword_,
            Message message,
            Trade trade,
            LeContacts sender,
            LeContacts rec,
            Vector transferRules,
            Transfer transfer,
            Connection con,ProductTransferRule productTransferRule) {
return parseKeyword(keyword_, message, trade, sender, rec,
         transferRules, transfer, con, true,productTransferRule);
}

		
	public void initParams(Message message, Trade trade, LeContacts sender,
			LeContacts receiver, Transfer transfer, Vector transferRules,
			RemoteTrade remotetrade2, RemoteBOProcess remoteBO2,
			ProductTransferRule _productTransferRule) {
		// TODO Auto-generated method stub
		
	}

}
