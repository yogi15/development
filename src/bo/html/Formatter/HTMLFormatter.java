package bo.html.Formatter;




import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;





import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;

import util.BackOfficeCache;
import util.ClassInstantiateUtil;
import util.ReferenceDataCache;
import util.ShowInBrowser;
import util.commonUTIL;
import util.common.LocalUtil;
import beans.DocumentInfo;
import beans.LeContacts;
import beans.Message;
import beans.Transfer;
import beans.Trade;
import bo.html.GenerateMessageFormatter;
import bo.html.Formatter.Util.FormatterParser;
import bo.message.bomessagehandler.MessageFormat;
import bo.message.bomessagehandler.MessageFormatterUtil;
import bo.swift.SWIFTMessageGenerator;
import bo.swift.Formatter.BaseFormatter;
import bo.transfer.rule.ProductTransferRule;


/**
 * Formats trade and payment messages based on templates in the
 */
public class HTMLFormatter  extends BaseFormatter implements Cloneable {
	
	
	static private final String  HTML_COMMENT_START = "<!--";

    static private final String HTML_COMMENT_END = "-->";

    static private final String KEYWORD_START = HTML_COMMENT_START + "keyword:";

    static private final String KEYWORD_END = HTML_COMMENT_START + "/keyword"
            + HTML_COMMENT_END;

    static protected final String EMPTY_STRING = "";

    static protected HTMLFormatter  _globalCustom = getCustomMessageFormatter();

    transient HTMLFormatter _customFormatter;
    
    public Object clone() throws CloneNotSupportedException {
    	HTMLFormatter m = (HTMLFormatter) super.clone();
      
        m._customFormatter = null;
        return m;
    }
    
    /**
     * this method is called to initialize the MessageFormatter
     *
     */
    public void initParams(Message message, Trade trade, LeContacts sender,
    		LeContacts receiver, Transfer transfer, Vector transferRules,
    		RemoteTrade remotetrade, RemoteBOProcess remoteBO,ProductTransferRule _productTransferRule) {
    	
        super.initParams( message, trade, sender, receiver, transfer,transferRules,
        		remotetrade,remoteBO ,_productTransferRule);

        if (_globalCustom != null
                && !getClass().getName().equals(
                        _globalCustom.getClass().getName())) {
            try {
                _customFormatter = (HTMLFormatter) _globalCustom.clone();
                _customFormatter.initParams(message, trade, sender, receiver, transfer,transferRules,
                		remotetrade,remoteBO ,_productTransferRule);
            } catch (Exception e) {
              
                commonUTIL.displayError("HTMLFormatter", "initParams", e);
            }
        }
    }

    public HTMLFormatter() {
        super();
    }
    
    
    static public void display(Message message,
           RemoteTrade remoteTrade,RemoteReferenceData remoteReference) throws Exception {
        String characterEncoding = getCharacterEncoding(message, remoteTrade);
        String s = format( message, false, remoteTrade,remoteReference);
        if (s != null) {
            String fileName = message.getMessageType() + "_" + message.getId()
                    + "_TradeId_" + message.getTradeId() + "_";
            String ext = null;
            if (message.getFormat().equals("HTML")) {
                ext = "html";
            } else if (message.getFormat().equals("TEXT")) {
                ext = "txt";
            } else if (message.getFormat().equals("XML")) {
                ext = "xml";
            }
            ShowInBrowser.view(s, ext, fileName, characterEncoding);
        }
    }
    
    
    static public String format(Message message,
            boolean newDocument, RemoteTrade remoteTrade,RemoteReferenceData remoteRef)
            throws Exception {
        return format(message, null, newDocument, remoteTrade,remoteRef);
    }
    
    /**
     * Finds the appropriate advice template file and formats the passed
     * BOMessage using that template. If you wish to implement special logic for
     * choosing the right template file for a given trade, see
     * {@link com.calypso.tk.bo.TemplateSelector TemplateSelector}.
     *
     * @return a String, the formatted Message else return null if message could
     *         not be formatted or template is not html based.
     */
    
    private static String format(Message message,Trade trade,
			boolean newDocument, RemoteTrade remoteTrade,RemoteReferenceData remoteRef) {
		// TODO Auto-generated method stub
    	 String templateName = message.getTemplateName();
         if (!message.getFormat().equals("HTML")
                 && !message.getFormat().equals("TEXT")
                 && !message.getFormat().equals("XML")) {
           
             commonUTIL.display("HTMLFormatter", "Not an HTML/TEXT Template " + templateName);
           //  commonUTIL.display("HTMLFormatter", "Invalid FormatType "       + "for MessageFormatter " + message.getFormat());
         }
         if (message.getFormat().equals("XML")) {
            StringBuffer sb =null;// XMLUtil.generate(env, message, dsCon);
             if (sb != null) {
                 return sb.toString();
             } else {
                 return null;
             }
         }
         if (message.getAttribute("Group") != null) {
             return null;//formatGroupMessage(env, message, newDocument, dsCon);
             
         }
         LeContacts sender = null;
         LeContacts receiver = null;
         Transfer transfer = null;
         Vector transferRules = null;
         Vector exceptions = new Vector();
         try {
         if (message.getTradeId() > 0) {
             trade = remoteTrade.selectTrade(message.getTradeId());
             // Rebuild the Trade as it was at the time of the Confirm
             if (trade != null) {
                 //Adding one second
                 if (trade.getVersion() != message.getTradeVersion())
                     trade = null; // remoteTrade.undo(trade,                              message.getTradeVersion());
             }
         }       
          sender =ReferenceDataCache.getLEContact(message.getSenderRole(), null, message.getSenderId(), trade.getProductType(), "Default");
		   receiver =ReferenceDataCache.getLEContact(message.getReceiverRole(), null, message.getReceiverId(), trade.getProductType(), "Default");
		   ProductTransferRule  transferRule =  getTransferRule(trade.getProductType());
		   transferRule.setRemoteTrade(remoteTrade);
			  transferRule.setRefDate(remoteRef);
			  Vector<String> mesError = new Vector<String>();
			  transferRules =   transferRule.generateRules(trade,mesError);
			
			  if (message.getTransferId() > 0) {
	                transfer =BackOfficeCache.getRemoteBO().getTransfer(message.getTransferId());
	                       
	               /* if (message.getXferVersion() != transfer.getVersion()) {
	                    transfer = dsCon.getRemoteBO().undo(transfer,
	                            message.getXferVersion()); */

	        }
			  
			 /* if (transfer.getNettedTransfer()) {
		            TransferArray transfersN = null;
		            
		            transfersN = dsCon.getRemoteBO().getNettedTransfers(
		                                    transfer.getId());
		            transfersN = BOTransfer.undo(transfer,
		                         message.getXferVersion(), transfersN, dsCon);
		            
		            transfer.setUnderlyingTransfers(transfersN);
		        }   
		            } else {
		                transfer = null;
		            }
		        } catch (Exception e) {
		            Log.error(LOG_CATEGORY, e);
		            throw new MessageFormatException(e.getMessage());
		        } */
			  
			  String orig = templateName;
			  if (trade != null) {
		            templateName = MessageFormatterUtil.getSelectedTemplate(trade, message,
		                    templateName);
		        }
			  
			  if (templateName == null || templateName.length() == 0) {
		            try {
						throw new Exception("template " + templateName     + "not found for message " + message.getId());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        if (((templateName.indexOf(".html") == -1 && templateName
		                .indexOf(".txt") == -1) && (templateName.indexOf(".htm") == -1 && templateName
		                .indexOf(".Txt") == -1))) {
		            try {
						throw new Exception("Invalid HTML Template "           + templateName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        InputStream stream = null;
		        if (stream == null) {
		            stream = getTemplate(message, trade, remoteTrade);
		        }

		        if (stream == null) {
		            try {
						throw new Exception("template " + templateName         + "not found for message " + message.getId());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        
		        if (stream == null) {
		            try {
						throw new  Exception("template " + templateName                    + "not found for message " + message.getId());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        HTMLFormatter current =  GenerateMessageFormatter.findMessageFormatter(message, trade);
		        if (current == null) {
		            commonUTIL.display("HMTLFormatter","Message Formatter is null");
		            try {
						throw new Exception("Could not find Valid "                   + "MessageFormatter for " + message.getId() + " "                     + message.getMessageType() + "/" + message.getProductType());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }

		        try {
		            current = (HTMLFormatter) current.clone();
		        } catch (Exception e) {
		        	  commonUTIL.displayError("HMTLFormatter","Message Formatter is null",e);
		        }
		        current.initParams(message, trade, sender, receiver, transfer, transferRules, remoteTrade, BackOfficeCache.getRemoteBO(), transferRule);
		        return current.readAndParseContents(stream, message, trade, sender,
		                receiver, transferRules, transfer, remoteTrade);
		        
		        
         }catch(RemoteException e) {
        	 commonUTIL.displayError("HTMLFormatter", "Formate", e);
         }
		return null;
	}
    public String readAndParseContents(InputStream stream, Message message,
            Trade trade, LeContacts sender, LeContacts rec, Vector transferRules,
            Transfer transfer, RemoteTrade remoteTrade) {
  String m = FormatterParser.parse(this, stream, message, transfer, trade,         sender, rec, transferRules, remoteTrade);
  System.out.println(m);
  return m;
    }
	/**
     * Finds the character encoding that applies to the message. This encoding
     * defaults to Util.ENCODING. Otherwise, we search for the CHARSET element
     * of the META HTML tag. Up to now this method works only for HTML
     * documents.
     */
    static public String getCharacterEncoding(Message message,
           RemoteTrade remoteTrade) {

        InputStream template = getTemplate(message, null, remoteTrade);
        String characterEncoding = commonUTIL.ENCODING;
        if (template != null) {
            InputStreamReader reader = new InputStreamReader(template);
            BufferedReader bufReader = new BufferedReader(reader);
            // Bugzilla 24181 - Commented out the next line for non-English
            // clients
            //characterEncoding = reader.getEncoding();
            boolean foundEncoding = false;
            try {
                String line = bufReader.readLine();
                while (line != null) {
                    String myLine = line.toUpperCase();
                    int metaIndex = myLine.indexOf("<META");
                    if (metaIndex != -1) {
                        int charsetIndex = myLine.indexOf("CHARSET", metaIndex);
                        if (charsetIndex != -1) {
                            charsetIndex = myLine.indexOf("=", charsetIndex);
                            int endIndex = myLine
                                    .indexOf('"', charsetIndex + 1);
                            characterEncoding = myLine.substring(
                                    charsetIndex + 1, endIndex).trim();
                            break;
                        }
                    }
                    line = bufReader.readLine();
                }
            } catch (Exception e) {
                
                commonUTIL.displayError("HTMLFormatter", "getCharacterEncoding", e);
            }
        }

        return characterEncoding;
    }

    static protected InputStream getTemplate(Message message, Trade trade,
            RemoteTrade remoteTrade) {
        if (message == null) {
            return null;
        }
        //Trade trade = null;
        if (trade == null) {
            try {
                if (message.getTradeId() > 0)
                    trade = remoteTrade.selectTrade(
                            message.getTradeId());
            } catch (Exception e) {
                commonUTIL.displayError("HTMLFormatter", "getTemplate", e);
                
            }
        }
        String templateName = message.getTemplateName();
        if (trade != null) {
            templateName = MessageFormatterUtil.getSelectedTemplate(trade, message,
                    templateName);
        } else {
            templateName = MessageFormatterUtil.getSelectedTemplate(message,
                    templateName);
        }
        if (templateName == null || templateName.length() == 0) {
            return null;
        }
        if (((templateName.indexOf(".html") == -1 && templateName
                .indexOf(".txt") == -1) && (templateName.indexOf(".htm") == -1 && templateName
                .indexOf(".Txt") == -1))) {
            return null;
        }
        

        return getInputStream(HTMLFormatter.class.getClassLoader(),
                templateName);
    }
    
    
    static HTMLFormatter getCustomMessageFormatter() {
        String classname = "bo.html.Formatter.Custom.CustomMessageFormatter";
        Class class1 = null;
        try {
        	class1 =   ClassInstantiateUtil.getClass(classname,true);
        	return (HTMLFormatter) class1.newInstance();
        } catch (Exception e) {
        }
        return null;
    }
    static Boolean isLocalesAlreadySetB = null;
    
    
    /**
     * Utility method that will format the JDate, JDatetime or Number given as a parameter
     * according with the BOMessage Localization  
     * 
     * @param message
     * @param o
     * @return
     */
    
    
    public static String parseJDate(Message message, Date date) {
        if (date == null) {
            return "NULL DATE";
        }
        String locs = message.getLanguage();
        Locale loc = LocalUtil.getLocale(locs);
        if (loc == null) {
            if (isLocalesAlreadySetB == null) {
                // Make sure all Locales are available 
                Locale[] locales = Locale.getAvailableLocales(); 
                if (locales != null) { 
                    LocalUtil.setAvailableLocales(locales); 
                }
                isLocalesAlreadySetB = Boolean.TRUE;
                loc = LocalUtil.getLocale(locs);
                if (loc == null) {
                    loc = Locale.getDefault();
                }
            } else {
                loc = Locale.getDefault();
            }
        }
        return commonUTIL.dateToLongString(date, loc);
    }
    
    public String parse(String line, Message message, Trade trade,
    		LeContacts sender, LeContacts rec, Vector transferRules,
            Transfer transfer, Connection con, boolean b,ProductTransferRule productTransferRule) {

        int start = line.indexOf(KEYWORD_START);
        int end = line.indexOf(KEYWORD_END);
        if (start == -1 || end == -1)
            return parseOld(line, message, trade, sender, rec, transferRules,
                    transfer, con,true,productTransferRule);

        String declaration = line.substring(start, end);
        int replaceStart = declaration.indexOf(HTML_COMMENT_END);
        if (replaceStart == -1)
            return parseOld(line, message, trade, sender, rec, transferRules,
                    transfer,con,true,productTransferRule);
        String keyword = declaration.substring(KEYWORD_START.length(),
                replaceStart);

        if (keyword == null || keyword.length() == 0) {
            return parseOld(line, message, trade, sender, rec, transferRules,
                    transfer, con,true,productTransferRule);
        }

        replaceStart += HTML_COMMENT_END.length();

        String replace = parseKeyword(keyword, message, trade, sender, rec,
                transferRules, transfer, con,true,productTransferRule);

        String newLine = line.substring(0, start + replaceStart)
                + replace
                + KEYWORD_END
                + parse(line.substring(end + KEYWORD_END.length()), message,
                        trade, sender, rec, transferRules, transfer, con,true,productTransferRule);

        return newLine;
    }
    
    /**
     * this is almost like super.parse(), except that it adds comments around replaced keywords
     * that allow re-evaluation
     * @deprecated
     */
    public String parseOld(String line, Message message, Trade trade,
            LeContacts sender, LeContacts rec, Vector transferRules,
            Transfer transfer,  Connection con, boolean b,ProductTransferRule productTransferRule) {
        beans.KeywordInfo keywordInfo = getKeyword(line);
        if (keywordInfo == null || keywordInfo.name.length() == 0) {
            return replaceEscapeChar(line);
        }

        int idxStart = keywordInfo.start;
        int idxEnd = keywordInfo.end;
        if (idxStart < 0)
            idxStart = 0;
        if (idxEnd > line.length())
            idxEnd = line.length();

        String replace = parseKeyword(keywordInfo.name, message, trade, sender,
                rec, transferRules, transfer,con,true,productTransferRule);

        boolean doReplace = message.getFormat().equals("HTML");
               // && Defaults.getBooleanProperty(Defaults.KEYWORD_HTML_REPLACE,
                 //       true);
        /**
         * The new line of text is composed of all the text prior to the
         * keyword, the keyword value itself substituted in, and the remainder
         * of the parsed line. This is a recursive method and we begin parsing
         * the remainder of the text at idxEnd+1 since everything prior to that
         * index has already been handled.
         */
        String newLine = line.substring(0, idxStart)
                + (doReplace ? (KEYWORD_START + keywordInfo.name + HTML_COMMENT_END)
                        : "")
                + replace
                + (doReplace ? KEYWORD_END : "")
                + parseOld(line.substring(idxEnd + 1), message, trade, sender,
                        rec, transferRules, transfer,con,true,productTransferRule);
        return newLine;
    }

    
    public String parseKeyword(String keyword_, Message message, Trade trade,LeContacts sender, LeContacts rec, Vector transferRules,Transfer transfer, Connection con, boolean b,ProductTransferRule productTransferRule)  {
        String trimKeyword = keyword_.trim();
        Vector functions = new Vector();
        String rem = getKeywordFunctions(trimKeyword, functions);
        String keyword = getKeywordName(rem);
        Vector params = getKeywordParams(rem);
        
        if (_iteratorObject != null && _iteratorObject instanceof Trade) {
            trade = (Trade)_iteratorObject;
        }
        BaseFormatter applyTo = this;
        Method m = getParseMethod(this, keyword);
        if (m == null && _customFormatter != null) {
            m = getParseMethod(this._customFormatter, keyword);
            if (m != null)
                applyTo = this._customFormatter;
        } if (m != null) {
            applyTo._currentFunctions = functions;
            applyTo._currentKeyword = keyword;
            applyTo._currentKeywordParams = params;
            applyTo._currentLanguage = message.getLanguage();
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
	        String res = null;
            try {
                res = (String) m.invoke(applyTo, objs);
            } catch (Exception e) {
            	commonUTIL.displayError("HTMLFormatter","Keyword (" + keyword + ")" +  " Params " + params + " Method (" + m.getName() + ")" +   " MessageFormatter " +   getClass().getName(),e);
            }
            if (res == null)
                res = ""; // Defined method but no value (but NO ERROR)
            if (_currentFunctions != null) {
                res = applyFunctions(res, message, trade, sender, rec, transferRules, transfer, con,productTransferRule);
            }
            return res;
        } else {
        	 _currentFunctions = functions;
             _currentKeyword = keyword;
             _currentKeywordParams = params;

             String res = null;
             
             /*
              * Try Trade Keyword Attribute
              */
             if (res == null && keyword.indexOf("KEYWORD_") == 0) {
                 res = getTradeKeyword(keyword, trade, con);
             }
             /*
              * Try Cashflow values
              */
             if (res == null && keyword.indexOf("CASHFLOW_") == 0) {
                 res =null;// getCashFlowValue(keyword.substring(9), message,     trade, sender, rec, transferRules, transfer, con);
             }

             /*
              * Try Bond Cashflow values
              */
             if (res == null && keyword.indexOf("BONDCF_") == 0) {
                 res=null;// getBondCashFlowValue(keyword.substring(7), message,                         trade, sender, rec, transferRules, transfer, con);
             }

             /*
              * Try Bond put/call schedule values
              */
             if (res == null && keyword.indexOf("BONDCALLSCH_") == 0) {
                 res=null;// getBondCallScheduleValue(keyword.substring(12), message,                         trade, sender, rec, transferRules, transfer, con);
             }

             /*
              * Try Bond Cashflow values
              */
             if (res == null && keyword.indexOf("BOND_SEC_CODES_") == 0) {
                 res=null;// getBondCashSecCode(keyword.substring(15), message,                         trade, sender, rec, transferRules, transfer, con);
             }
             
             /*
              * Try Performance Swap values
              */
           /*  if (res == null && keyword.indexOf("REFERENCE_ASSET_SEC_CODES_") == 0) {                
                 res = getPerformanceSwapReferenceAssetSecCode(keyword.substring(26), message,
                         trade, sender, rec, transferRules, transfer, con);
             } */
 
             /*
              * * try the getValue Method
              */
             /*  if (res == null && keyword.indexOf("LA_") == 0) {
                 res = getLegalAgreementAdditionalField(keyword, trade, con);
             } 
             
             /*
              * Try Legal Entity Attribute
              */
             if (res == null && keyword.indexOf("LE_") == 0) {
                 res =null;//  getLegalEntityAttribute(keyword, trade, con);
             }
             
             /*
              * Try BOOK Attribute
              */
             if (res == null && keyword.indexOf("BOOK_") == 0) {
                 res=null;//  getBookAttribute(keyword, trade, con);
             }
             
             if (res == null && keyword.indexOf("SDI_") == 0) {
                 res =null;// getSDIAttribute(keyword, trade, message, transfer,                         transferRules, sender, rec, con);
             }
             
             if (res == null && keyword.indexOf("ROLE_") == 0) {
                 res=null;// getTradeRole(keyword, trade, con);
             }
             
             /*
              * Try Registration Attribute
              */
             /*     if (res == null && keyword.indexOf("REGISTR.") == 0) {
                 res = getRegistrAttribute(keyword, trade, message,
                         transfer, transferRules, sender, rec, con);
             } */
 
             if (res == null && keyword.indexOf("ACC_") == 0) {
                 res =null;//  getAccountAttribute(keyword, trade, transfer, con);
             }
             
          /*   if (res == null && keyword.indexOf("TRADEBUNDLE_") == 0) {
                 res = getTradeBundleAttribute(keyword, trade, con);
             }
             
             /*
              * Try from MessageReport column name
              * Note that some of the specific MessageFormatter keywords above 
              * could just be picked up from MessageReport available columns as well  
              
             if (res == null && "REPORT".equals(_currentKeyword) 
                     && !Util.isEmpty(_currentKeywordParams) 
                     && !Util.isEmpty((String)_currentKeywordParams.get(0))
                     && trade != null) {
                 String colName = (String)_currentKeywordParams.get(0);
                 ReportStyle style = ReportStyle.getReportStyle("Message");
                 ReportRow row = new ReportRow(message);
                 row.setProperty(ReportRow.TRANSFER, transfer);
                 row.setProperty(ReportRow.TRADE, trade);
                 row.setProperty(ReportRow.VALUATION_DATETIME, message.getCreationDate());
                 row.setProperty(ReportRow.PRICING_ENV, getPricingEnv());
                 Vector errors = new Vector();
                 res = toLocalizedString(message,  style.getColumnValue(row, colName, errors));
                 if (res == null) res = EMPTY_STRING;
                 if (!Util.isEmpty(errors)) {
                     Log.error(this, Util.collectionToString(errors));
                 }
             } */

             
             if (res == null) {
                 Object object = getValue(keyword, trade);
                 if (object != null)
                     res = object.toString();
             }
             
             if (res != null) {
                 if (_currentFunctions != null) {
                     res = applyFunctions(res, message, trade, sender, rec, transferRules, transfer, con,productTransferRule);
                 }
                 return res;
             }
                 
             /**
              * Check to see if value is in keyword Value table.
              */
             String v = getKeywordValue(keyword);
             if (v != null) return v;
             commonUTIL.display("HTMLFormatter","Method not defined to parse " + keyword);

             if (message.getFormat().equals("HTML"))
                 return displayUndefinedValue(keyword);
             else
                 return "";
         
        }
		
        
    }
    /**
     * Returns the HTML String to display when an undefined keyword
     * value is encountered.  The default behavior is to display
     * the keyword in bold style highlighted in yellow.
     *
     * @param  keyword  String keyword that could not be evaluated
     *                  in document.
     * @return  an HTML display String for the keyword.
     */
    protected String displayUndefinedValue(String keyword) {
        //return "";
        return "<table border=\"0\"><tr><td bgcolor=\"#FF0000\" valign=\"top\"><b>"
                + keyword + "</b></td></tr></table>";
    }
    public String applyFunctions(String value,Message message,Trade trade,LeContacts sender,LeContacts rec, Vector transferRules,Transfer transfer,Connection con,ProductTransferRule productTransferRule) {
		if (_currentFunctions != null) {
			 for (int i = _currentFunctions.size() - 1; i >= 0; i--) {
	                String function = (String)_currentFunctions.elementAt(i);
	                BaseFormatter applyTo = this;
	                Method m = getApplyMethod(this, function);
	                //Trying CutsomMessageFormatter
	                if (m == null && _customFormatter != null) {
	                    m = getApplyMethod(this._customFormatter, function);
	                    if (m != null)
	                        applyTo = this._customFormatter;
	                }
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
							commonUTIL.displayError("HTMLFormatter","Function (" + function + ")" +      " Value " + value +   " Method (" + m.getName() + ")" +   " MessageFormatter " +  getClass().getName(),e);

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
    
		
    /**
     * Utility method that will format the JDate, JDatetime or Number given as a parameter
     * according with the BOMessage Localization  
     * 
     * @param message
     * @param o
     * @return
     */
    public String toLocalizedString(Message message, Object o) {
        if (o == null || commonUTIL.isEmpty(o.toString())) return null;
        String result;
        if (o instanceof Date) {
            result = parseJDate(message, (Date)o);
       // }// else if (o instanceof JDatetime) {
        //    result = parseDatetime(message, (JDatetime)o);
        } else if (o instanceof Number) {
            result =  parseNumber(message, (Number)o);
        } else {
            result = String.valueOf(o);
        }
        return result;
    }
    public String parseITERATOR_CURRENT_COUNT(Message message, Trade trade,
    		LeContacts sender, LeContacts rec, Vector transferRules,
            Transfer transfer, RemoteTrade remoteTrade) {
            return toLocalizedString(message, (_iteratorObject != null) ? _iterator.getCurrentCount() : null);
        }
        
    public String parseBASKET_COMP_NUMBER(Message message, Trade trade,
            LeContacts sender, LeContacts rec, Vector transferRules,
            Transfer transfer,RemoteTrade remoteTrade) {
            String res = null;
          /*  if (trade != null && trade.getProduct() != null) {
                Product underlying = trade.getProduct().getUnderlyingProduct();
                if (underlying instanceof Basket)
                    res = toLocalizedString(message, ((Basket)underlying).size());
                else
                    res = "1";
            } */
            return res;
        }
public boolean display(Message message,DocumentInfo docInfo,RemoteTrade remoteTrade,RemoteReferenceData remoteRef) {
	String s = docInfo.getHTMLDocumentData().toString();
	  String characterEncoding = getCharacterEncoding(message, remoteTrade);
	 if (s != null) {
         String fileName = message.getMessageType() + "_" + message.getId()
                 + "_TradeId_" + message.getTradeId() + "_";
         String ext = null;
         if (message.getFormat().equals("HTML")) {
             ext = "html";
         } else if (message.getFormat().equals("TEXT")) {
             ext = "txt";
         } else if (message.getFormat().equals("XML")) {
             ext = "xml";
         }
         ShowInBrowser.view(s, ext, fileName, characterEncoding);
     }
	return true;
}
static Hashtable<String,ProductTransferRule> rulesCache = new Hashtable<String,ProductTransferRule>();
public static ProductTransferRule  getTransferRule(String productType) {
	  String productTransferrule = "bo.transfer.rule.Generate"  + productType.toUpperCase() + "TransferRule";
	  ProductTransferRule productTransferRule = null;
	  try {
		  productTransferRule = rulesCache.get(productTransferrule);
      	if(productTransferRule == null) {
      Class class1 =    ClassInstantiateUtil.getClass(productTransferrule,true);
      productTransferRule =  (ProductTransferRule) class1.newInstance();
      
      
      rulesCache.put(productTransferrule, productTransferRule);
      }
         //  productWindow = (BondPanel) 
      } catch (Exception e) {
      	commonUTIL.displayError("SwiftGenerator", "getTransferRule <<<<< not able to create Handler ", e);
      }

      return productTransferRule;
}
	public DocumentInfo generate(Message message, Trade trade, Transfer transfer,
			boolean newDocument, RemoteTrade remoteTrade,RemoteReferenceData remoteRef) {
		 String dataH = null;
		 DocumentInfo doc = null;
		try {
			dataH = format(message, newDocument, remoteTrade,remoteRef);
			 if (dataH == null)
		          commonUTIL.display("HTMLFormatter", "Empty Generated Message " + "for message " + message.getId());
			 doc = new DocumentInfo();
			 doc.setHTMLDocumentData(new StringBuffer(dataH));
			 doc.setCharacterEncoding(getCharacterEncoding(message,remoteTrade));
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return doc;
	}
	
}
