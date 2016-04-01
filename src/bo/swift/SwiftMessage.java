package bo.swift;

import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;


import util.ClassInstantiateUtil;
import util.commonUTIL;

import beans.Message;
import beans.Transfer;
import beans.Trade;

public class SwiftMessage implements Serializable {


    protected String _type;
    public Message getMessage() {
		return _message;
	}
    public String get_type() {
		return _type;
	}
	
	public Message get_message() {
		return _message;
	}
	
	public String get_sender() {
		return _sender;
	}
	
	public String get_receiver() {
		return _receiver;
	}
	
	public String get_gateway() {
		return _gateway;
	}
	
	/**
     * This is the application identifier.
     * Possible values are:
     *     F for FIN (user to user, FIN and FIN service) ,
     *     A for most GPA,
     *     L for certain GPA service messages.
     * We use only F type.
     */
    protected String _application = "F";

    /**
     * This is the service identifier.
     * Possible values include:
     *     01 for all GPA, FIN and user to user messages,
     *     21 for message acknowledgements,
     *     03 for SELECT commands,
     *     ... (see S.W.I.F.T. User Handbook, FIN System Messages)
     */
    protected String _service = "01";
    /**
     * Swift Text as initialy parsed. (should contains headers, body and trailer)
     */
    protected String _swiftText = null;

    /**
     * Four digits that identify the user's session.
     */
    protected String _session = "0000";
    
    protected String _sessionCCIL = "XXXXXXXXX";

    /**
     * Six digits (ISN of the sender or OSN of the receiver).
     */
    protected String _sequence = "000000";

    /**
     * Input/Output identifier (O for output, I for input)
     */

	protected String _inputOutput = "O";
    protected Vector<SwiftFieldMessage> _fields;
    protected Message _message = null;
    protected String _sender;
    protected String _receiver;
    protected String _inputTime; //for output message -HHMM
    public static String ENDOFLINE = "";
    public static String STAGNAME = "@@";
    protected boolean _incoming = false;
    protected String _gateway = null;
    protected static SwiftMessageCustomizer _customizer = null;
    protected static SwiftToTextInterface _swifttoTextCustomizer = null;
    protected String _messagePriority;
    protected String _deliveryMonitoring;
    protected String _obsolescencePeriod;

   
    protected String _mor; // 28 caracters: local date,fulladdress,session and isn
    protected String _outputDate; //receiver date
    protected String _outputTime; //receiver time
    protected String _ackDatetime; //ack/nak date (in tag 177 of block 4)
    protected boolean _acked; //ack or nak (in tag 451 of block 4)
    protected String _nackReason; //nak reason (in tag 405 of block 4 if nak message)
    protected String _originalMur; //nak reason (in tag 405 of block 4 if nak message)
    protected String _outputSession;
    protected String _outputSequence;
    protected String _inputDate;
    protected String _block3 = null;
    protected String _block5 = null;
   
    transient protected HashMap __properties;
    transient protected boolean _lockBlock3 = false;
    static boolean _lookForValidator = false;
    static {
    	 ENDOFLINE = System.getProperty("line.separator");
        try {
        	  String classname = "bo.swift.SwiftMessageCustomizerImpl";
              
                
                     Class class1 = ClassInstantiateUtil.getClass(classname,true);
                     _customizer =  (SwiftMessageCustomizer) class1.newInstance();
              
            
        } catch (Exception e) {
            commonUTIL.display("SWIFTMessage", "'bo.swift.SwiftMessageCustomizerImpl' not found");
        }
        try {
        	String classname = "bo.swift.CustomSwiftTextFormat";
        	 Class class1 = ClassInstantiateUtil.getClass(classname,true);
        	 _swifttoTextCustomizer =  (SwiftToTextInterface) class1.newInstance();
        } catch (Exception e) {
        	  commonUTIL.display("SWIFTMessage", 
                     "'bo.swift.CustomSwiftTextFormat' not found");
        }
    }
	public SwiftMessage(Message message, String type) {
		   this(message, type, "I");
	}
	 public SwiftMessage(Message message, String type, String inputOutput) {
	        _type = type;
	        _inputOutput = inputOutput;
	        _fields = new Vector<SwiftFieldMessage>();
	        if (message != null)
	            init(message);
	    }
	 
	 public SwiftMessage(Message boMessage) {
		// TODO Auto-generated constructor stub
	}
	protected void init(Message message) {

	        _message = message;
		_gateway= message.getMessageGateway();
	        String sender, receiver;

	        if (_customizer != null) {
	            sender = _customizer.getSender(this, message);
	            receiver = _customizer.getReceiver(this, message);
	        } else {
	            sender = SwiftUtil.formatBIC(message.getSenderAddressCode(), true);
	            receiver = SwiftUtil.formatBIC(message.getReceiverAddressCode());
	        }
	        setSender(sender);
	        setReceiver(receiver);
	    }
	 public final void setSender(String s) {
	        _sender = s;
	    }

	    public final void setReceiver(String s) {
	        _receiver = s;
	    }

	    public final void setType(String s) {
	        _type = s;
	    }
	  
		public String getGateway() {
		return _gateway;
	    }
	    public void setGateway(String gateway) { _gateway = gateway;}

	    public final void setFields(Vector v) {
	        _fields = v;
	    }
	
	    public  Vector<SwiftFieldMessage> getFields() {
	        return _fields;
	    }
	    String _ccilApplication = "XXXXXXXXX";
	    public String ccilHeaderDataFormat(String date) {
	    	return SwiftUtil.getCCILDateHeaderFormat(date);
	    }
	    public String getBasicHeaderBlock() {
	        if (_customizer != null) {
	            String block = _customizer.getBasicHeaderBlock(this, _message);
	            if (!commonUTIL.isEmpty(block)) return block;
	        }

	        String sender = null;
	        if (_inputOutput.equals("O"))
	            sender = _receiver;
	        else
	            sender = _sender;
           
	        String ret =  "{" + BASIC_HEADER_BLOCK_IDENTIFIER + _application + _service
	                + sender + _session + (_application != "L" ? _sequence : "")
	                + "}";
	                if(_message.getFormat().equalsIgnoreCase("CCIL")) {
	                	ret =  "{" + BASIC_HEADER_BLOCK_IDENTIFIER + _application + _service + ccilHeaderDataFormat(_message.getMessageDate())
	        	                + sender + _ccilApplication 
	        	                + "}";
	                }
	                return ret;
	       
	    }
	    
	    
	    /**
	     * From 27thMay 2003 getText() method will return the text
	     * which will have Tag Name too. Which is not the actual SwiftText.
	     * To remove the Tag Name from the text, call this method with the
	     * SwiftText. This method will remove the Tag Name from the text.
	     *
	     */
	    public static void stripExtraInfo(StringBuffer buff) {
	        if (buff == null || buff.length() == 0) return;
	        int start = buff.toString().indexOf(S_TAG_NAME);
	        if (start < 0) return;
	        int end = buff.toString().indexOf(S_TAG_NAME, start + 2);
	        while (end >= 0) {
	            buff.delete(start, end + 2);
	            start = buff.toString().indexOf(S_TAG_NAME, start - 1);
	            end = buff.toString().indexOf(S_TAG_NAME, start + 2);
	        }
	    }

	    /**
	     * For a specification of the format of a swift message refer to
	     * the SWIFT documentation.
	     * Please look at stripExtraInfo for more info.
	     * This method does not produce a valid Swift message yet.
	     */
	    public String getSwiftText() {

		if (_swiftText!=null) return _swiftText;

	        if (commonUTIL.isEmpty(_type)) return new String();

	        if (_sender != null) _sender = _sender.trim();
	        if (_receiver != null) _receiver = _receiver.trim();

	        String type = _type;
	        if (_type.length() > 3)
	            type = type.substring(2);
	       
	        String result =
	                getBasicHeaderBlock()
	                + getApplicationHeaderBlock()
	                + getUserHeaderBlock()
	                // Text Block
	                + "{4:" + START_OF_TEXT + getSwiftBody() + END_OF_TEXT + "}"
	                // TrailerBlock {5:{MAC:41720873}{CHK:123456789ABC}}
	                // + "{5:}";
	                + getFinalBlock();
	        return result;
	    }
	    public String getSwiftText(String swiftMethod) {

			if (_swiftText!=null) return _swiftText;

		        if (commonUTIL.isEmpty(_type)) return new String();

		        if (_sender != null) _sender = _sender.trim();
		        if (_receiver != null) _receiver = _receiver.trim();

		        String type = _type;
		        if (_type.length() > 3)
		            type = type.substring(2);

		        String result =
		                getBasicHeaderBlock()
		                + getApplicationHeaderBlock()
		                + getUserHeaderBlock()
		                // Text Block
		                + "{4:" + START_OF_TEXT + getSwiftBody() + END_OF_TEXT + "}"
		                // TrailerBlock {5:{MAC:41720873}{CHK:123456789ABC}}
		                // + "{5:}";
		                + getFinalBlock();
		        return result;
		    }

	    public String getFinalBlock() {
	        if (_customizer != null) {
	            boolean finalBlock = _customizer.isFinalBlockRequired();
	            if (finalBlock) {
	                if (_block5 == null)
	                    return "{5:}";
	                else
	                    return "{" + _block5 + "}";
	            } else {
	                return "";
	            }
	        }
	        if (_block5 == null)
	            return "{5:}";
	        else
	            return "{" + _block5 + "}";
	    }
	    /**
	     * Follows the last field in a message.
	     */
	    public static final String END_OF_TEXT = ENDOFLINE + "-";
	    /**
	     * This separates two fields.
	     */
	    public static final String FIELD_SEPARATOR_WITHIN_TEXT = ENDOFLINE;

	    /**
	     * Precedes the first field in a message.
	     */
	    public static final String START_OF_TEXT = FIELD_SEPARATOR_WITHIN_TEXT;

	    /**
	     * Follows the last field in a message.
	     */
	    public String getSwiftBody() {
	        // Text Block
	        SwiftFieldMessage field;
	        StringBuffer result = new StringBuffer();
	        for (int i = 0; i < _fields.size(); i++) {
	            if (i > 0) {
	                // adds a field separator.
	                result.append(FIELD_SEPARATOR_WITHIN_TEXT);
	            }
	            field = _fields.elementAt(i);
	            result.append(field.getTAG());
	            String value = "";
	            if(null == field.getValue()){
	               commonUTIL.displayError("SwiftUtil", "The field -->' " +field.getName() +"'  has null value, please investigate",null);
	            }
	            else{
	                value = field.getValue();
	            }
	            // BZ 17866 - Make Swift Templates Compliant with character language set.
	            // Special characters need to be replaced by their uppercase equivalent:
	            value = SwiftCharUtil.convert(value);
	            result.append(value.toUpperCase());
	            result.append(S_TAG_NAME + field.getName() + S_TAG_NAME);
	            if (field.getFormat() != null) {
	                result.append(S_TAG_NAME + field.getFormat() + S_TAG_NAME);
	            }
	        }

	        return result.toString();
	    }
	    private String getMessageIdForMUR() {
	    	// if (!Defaults.getBooleanProperty(Defaults.SWIFT_MSG_ID_IN_MUR, false)) return "";  // need to implement this method default config
	    	if (_message == null) return "";
	    	return commonUTIL.lpad(String.valueOf(_message.getId()), NB_DIGIT_FOR_ID, '0');
	        }
	    public void customizePriority(Message message, Trade trade,Transfer transfer, String priority, Connection dsCon) {
	    	StringBuffer sb = new StringBuffer();
			sb.append("{3:");
			sb.append("{113:" + priority + "}");
			sb.append("{108:" + _type + getMessageIdForMUR() + "}");
			if (message!=null && !commonUTIL.isEmpty(message.getTemplateName()) &&
					message.getTemplateName().indexOf("COV")>0) {
					sb.append("{119:COV}");
			}
			sb.append("}");
			_block3 = sb.toString();
		}
	    
	  String _ccilInputer = "XXX";

	    public String getApplicationHeaderBlock() {
	        if (_customizer != null) {
	            String block = _customizer.getApplicationHeaderBlock(this, _message);
	            if (!commonUTIL.isEmpty(block)) return block;
	        }

	        if (_sender != null) _sender = _sender.trim();
	        if (_receiver != null) _receiver = _receiver.trim();

	        String type = _type;
	        if (_type.length() > 3)
	            type = type.substring(2);
	        String receiver = _receiver;
	        if (_inputOutput.equals("O"))
	            receiver = _sender;
	        if (_inputOutput.equals("I")) {
	            if (receiver.length() > 9 && "A".equalsIgnoreCase(receiver.substring(8, 9))) {
	                receiver = receiver.substring(0, 8) + "X" + receiver.substring(9);
	            }
	            if(_message.getFormat().equalsIgnoreCase("CCIL"))
	            	return "{2:" +  type + _ccilInputer + ccilHeaderDataFormat(_message.getMessageDate()) + _message.getAttributeValue("CCILTagCode") +_ccilApplication + "00" + "XXX}";
	            
	            return "{2:" + _inputOutput + type + receiver + "N" + "2" + "020}";
	        } else {
	            return "{2:" + _inputOutput + type + _inputTime +
	                    _inputDate + _sender + _outputSession +
	                    _outputSequence + _outputDate +
	                    _outputTime + _messagePriority + "}";
	        }
	    }

	    public String getUserHeaderBlock() {
	    	if(_message.getFormat().equalsIgnoreCase("CCIL")) // CCIL don't require this tag in header
	    		return "";
	        if (_customizer != null) {
	            String block = _customizer.getUserHeaderBlock(this, _message);
	            if (!commonUTIL.isEmpty(block)) return block;
	        }

	        if (_type == null) return "";

	        if (_block3 == null)
	            return "{3:{108:" + _type + getMessageIdForMUR() + "}}";
	        else
	            if (_block3.startsWith("{"))
	                return _block3;
	            else
	                return "{" + _block3 + "}";
	    }


				public boolean isMT103Plus() {
					if (_type == null || !_type.equals("MT103"))
							return false;
					String  tag57 = null;
					for (int i = 0; i < _fields.size(); i++) {
							SwiftFieldMessage field = _fields.elementAt(i);
							String tag = field.getTAG();
							if (tag.startsWith(":52") && !tag.equals(":52A:"))
								return false;
							if (tag.startsWith(":53") && ! (tag.equals(":53A:") || tag.equals(":53B:")))
								return false;
							if (tag.startsWith(":54") && !tag.equals(":54A:"))
								return false;
							if (tag.startsWith(":55") && !tag.equals(":55A:"))
								return false;
							if (tag.startsWith(":56") && !tag.equals(":56A:"))
								return false;
							if (tag.startsWith(":57")) {
							if (!tag.equals(":57A:"))
								return false;
							tag57 = field.getValue();
							}
							if (tag.startsWith(":51A"))
								return false;
							if (tag.startsWith(":23E:")) {
								String value = field.getValue();
							if (!value.equals("CORT") &&
							      !value.equals("INTC") &&
							      !value.equals("SDVA") &&
							      !value.equals("REPA"))
							  return false;
							}
							if (tag.startsWith(":59")) {
								String value = field.getValue();
								if (!value.startsWith("/")) return false;
									if ((tag57 == null || isBicCountry103Plus(tag57)) &&
											isBicCountry103Plus(_sender) &&
											isBicCountry103Plus(_receiver)) {
										if (!checkIBAN(value))
											return false;
				}
				}
				if (tag.startsWith(":72")) {
					String value = field.getValue();
					if (value.indexOf("REJT") >=0) return false;
					if (value.indexOf("RETN") >=0) return false;
				}
				
				
				}
				return true;
		}
				
				  protected boolean checkIBAN(String value) {
				        if (commonUTIL.isEmpty(value))
				            return false;
				        if (value.startsWith("/"))
				            value = value.substring(1);
				        int index = value.indexOf(ENDOFLINE);
				        if (index > 0) {
				            value = value.substring(0, index);
				        }
				        if (value.length() <= 4)
				            return false;
				        StringBuffer sbIban = new StringBuffer(value.substring(4));
				        sbIban.append(value.substring(0, 4));
				        value = sbIban.toString();

				        try {
				            StringBuilder extendedIban = new StringBuilder(value.length());
				            for(char currentChar : value.toCharArray()){
				                if (currentChar == ' ' || currentChar == '.' || currentChar == '-')
				                    continue;
				                extendedIban.append(Character.digit(currentChar,Character.MAX_RADIX));
				            }

				            return new BigDecimal(extendedIban.toString()).remainder(new BigDecimal(97) ).intValue() == 1;
				        }
				        catch (Exception e) {
				        commonUTIL.displayError("SwiftMessage", "checkIBAN", e);

				        }
				        return false;
				    }

					    
	    static public String x_CHARSET = "[0-9a-zA-Z \\x2D?/:().,'+]"; //"\\x2D" is for the Hyphen
	    public static final String RFDD = "RFDD";
	    public static final String AUTH = "AUTH";

	    static public String ATTR_ACKDATE = "AckDatetime";
	    static public String DOMAIN_NACK = "NackReason";
	    static public String DOMAIN_ACTION = "SwiftMessage.Action";
	    static public String ACK_ACTION_NAME = "ACK";
	    static public String NACK_ACTION_NAME = "NACK";
	    static private int NB_DIGIT_FOR_ID = 9;
	    public static String S_TAG_NAME = "@@";
	    /**
	     * SWIFT messages are encoded using IBM's EBCDIC character set.
	     */
	    public static final String SWIFT_CHARECTER_SET = "EBCDIC-INT";

	    protected static final String BASIC_HEADER_BLOCK_IDENTIFIER = "1:";
	    protected static final String APPLICATION_HEADER_BLOCK_IDENTIFIER = "2:";
	    protected static final String USER_HEADER_BLOCK_IDENTIFIER = "3:";
	    protected static final String TEXT_BLOCK_IDENTIFIER = "4:";
	    protected static final String TRAILER_BLOCK_IDENTIFIER = "5:";
	    static public Set COUNTRIES103PLUS = new HashSet(Arrays.asList("AD","AT","BE","BG","BV","CH","CY","CZ","DE","DK","EE","ES","FI","FR","GB","GF","GI","GP","GR","HU","IE","IS","IT","LI","LT","LU","LV","MC","MQ","MT","NL","NO","PL","PM","PT","RE","RO","SE","SI","SJ","SK","SM","TF","VA"));
	    protected void setHeaderOutputValues(String block1,   String block2) {
							/*
							* Block 1  {1:F01BCITITMMAXXX0012000123}
							* Block 2  {2:O1000840010605BNPAFRPPAXXX00120078960106051051U3}
							*/

	    		//Block 1 parsing
	    		_receiver=setHeaderValuesAndGetEntity(block1);
						
						//Block 2 parsing
						int start2 = 2;
						int length = block2.length();
						if (length >= 3)
						_inputOutput = block2.substring(start2, start2 + 1);
						if (length >= 6)
						_type = "MT" + block2.substring(start2 + 1, start2 + 4);
						if (length >= 10)
						_inputTime = block2.substring(start2 + 4, start2 + 8);
						if (length >= 16)
						_inputDate = block2.substring(start2 + 8, start2 + 14);
						if (length >= 28)
						_sender = block2.substring(start2 + 14, start2 + 26);
						if (length >= 32)
						_outputSession = block2.substring(start2 + 26, start2 + 30);
						if (length >= 38)
						_outputSequence = block2.substring(start2 + 30, start2 + 36);
						if (length >= 44)
						_outputDate = block2.substring(start2 + 36, start2 + 42);
						if (length >= 48)
						_outputTime = block2.substring(start2 + 42, start2 + 46);
						if (length >= 49)
						_messagePriority = block2.substring(start2 + 46, start2 + 47);
						else {
						addFormatIssue("Block 2 is too short for output mode (" + block2.length() + "/49)");
					}
			}
	    public void addProperty(String key, Object value) {
	        if (__properties == null) {
	            __properties = new HashMap();
	        }
	        __properties.put(key, value);
	    }
	    public Object getProperty(String key) {
	        if (__properties == null)
	            return null;
	        return __properties.get(key);
	    }

	    /**
		 * If a Format Issue is detected during the Parsing of the Swift,
		 * we could either throw an Exception, which will prevent the BOMessage from being created
		 * in the system or simply add a FormatIssue on this Swift
		 * This format Issue will prevent the swift from going in the
		 * Indexing / Matching mechanism but will still allow it to be saved.
		 * @param issue
		 */
		public void addFormatIssue(String issue) {
			List<String> formatIssues = (List<String>) getProperty(Message.FORMAT_ISSUE_MSG_ATTR);
			if (formatIssues == null) {
				formatIssues = new Vector<String>();
				addProperty(Message.FORMAT_ISSUE_MSG_ATTR, formatIssues);
			}
			if (!formatIssues.contains(issue))
				formatIssues.add(issue);
		}
	    protected String setHeaderValuesAndGetEntity(String block1) {

	        if (commonUTIL.isEmpty(block1))
	            return null;

	        String entity = null;
	        /*
	         * {1:A01VNDZBET2AXXX0016000006}
	         */
	        //Block 1 parsing
	        int start = 2;
	        int length = block1.length();
	        if (length >= 3)
	            _application = block1.substring(start, start + 1);
	        if (length >= 5)
	            _service = block1.substring(start + 1, start + 3);
	        if (length >= 17)
	            entity = block1.substring(start + 3, start + 15);
	        if (length >= 21)
	            _session = block1.substring(start + 15, start + 19);
	        if (length >= 27)
	            _sequence = block1.substring(start + 19, start + 25);
	        else {
	            addFormatIssue("Block 1 is too short (" + length + "/27)");
	        }

	        return entity;
	    }
	    
	    
	    public final String getAckDatetime() {
	        return _ackDatetime;
	    }
	    public final boolean isAcked() {
	        return _acked;
	    }
	    public final String getNackReasonCode() {
	        return _nackReason;
	    }
	    public final String getOriginalMur() {
	        return _originalMur;
	    }

	    
	    /**
	     * Check if message is an Acknowledgement Message.
	     * <b>This info is contained within the block1 of the message, more exactly the service field
	     * <b>This is coded in position 2 and 3 of block 1.
	     * <ul>
	     * <li>{1:A01VNDZBET2AXXX0016000006} --> 01 - for all GPA, FIN and user to user messages
	     * <li>{1:A21VNDZBET2AXXX0016000006} --> 21 - message acknowledgement
	     * <li/ul
	     * @return true if the message is an Acknowledgement Message. return false otherwise.
	     */
	    public final boolean isAcknowledgementMessage() {
	        return commonUTIL.isSame(_service,"21");
	    }
	    final static public String SWIFT_MSG_ID_IN_MUR = "SWIFT_MSG_ID_IN_MUR";
	    /**
	     * Retrieve the Message Id of the Original Message.
	     * @return the id of the original message.
	     * Returns -1 if the Defaults property SWIFT_MSG_ID_IN_MUR is no set to true or if the message is not an Acknowledgement Message.
	     * @see #isAcknowledgementMessage
	     */
	    public int getMessageIdOriginalMur() {
		if (!isAcknowledgementMessage()) return -1;
		// if (!Defaults.getBooleanProperty(Defaults.SWIFT_MSG_ID_IN_MUR, false)) return -1; // to understand what is this. 
		String mur = getOriginalMur();
		if (mur==null) return -1;
		if (mur.length() < NB_DIGIT_FOR_ID) return -1;
		String msgId = mur.substring(mur.length()-NB_DIGIT_FOR_ID);
		return Integer.parseInt(msgId);
	    }

	    public String getMUR() {
	        if (_block3 == null)
	            return null;
	        int start = _block3.indexOf("{108:");
	        if (start >= 0) {
	            String mur = _block3.substring(start + 5);
	            if (!commonUTIL.isEmpty(mur) && mur.length() > 7) {
	                mur = mur.substring(7);
	                int end = mur.indexOf("}");
	                if (end > 0)
	                    return mur.substring(0, end);
	            }
	        }
	        return null;
	    }
	    
	    

	    private boolean isBicCountry103Plus(String bic) {
	        if (commonUTIL.isEmpty(bic) || bic.length() < 6)
	            return false;
	        return COUNTRIES103PLUS.contains(bic.substring(4,6));
	    }
	    /* need to understandTag for M104RFDD 
	    public boolean isMT104RFDD(BOTransfer transfer) {
	        if (_type == null || !_type.equals("MT104") || transfer == null)
	            return false;
	        try {
	            SettleDeliveryInstruction sdi = DSConnection.getDefault().getRemoteReferenceData().getSettleDeliveryInstruction(transfer.getInternalSettleDeliveryId());
	            return RFDD.equals(sdi.getAttribute(SWIFT_MUG));
	        } catch (RemoteException e) {
	            Log.error(this, e);
	        }
	        return false;
	    } */
	    public static SwiftFormatContext createSwiftContext(SwiftMessage swift) {
	        SwiftFormatContext context = null;
	        if (_swifttoTextCustomizer != null)
	            context = _swifttoTextCustomizer.getSwiftFormatContext(swift);
	        if (context == null)
	            context = new SwiftFormatContext(swift.get_type());
	        return context;
	    }
	    static public String getNameFromTag(String tag, String value, Message message) {
	        if (tag == null || value == null) return "Unknown";

	        if (_customizer != null) {
	            String messageType = (message != null) ? message.getMessageType() : null;
	            String retval = _customizer.getNameFromTag(tag, value, messageType);
	            if (!commonUTIL.isEmpty(retval)) return retval;
	        }

	        String begin = "";
	        if (value.length() > 5)
	            begin = value.substring(0, 5);

	        if (value.equals("GENL") && tag.equals(":16R:")) return "Start Of General Information";
	        if (value.equals("GENL") && tag.equals(":16S:")) return "End Of General Information";
	        if (value.equals("LINK") && tag.equals(":16R:")) return "Start Of Linkage Information";
	        if (value.equals("LINK") && tag.equals(":16S:")) return "End Of Linkage Information";
	        if (value.equals("TRADDET") && tag.equals(":16R:")) return "Start Of Trade Details";
	        if (value.equals("TRADDET") && tag.equals(":16S:")) return "End Of Trade Details";
	        if (value.equals("FIAC") && tag.equals(":16R:")) return "Start Of Financial Instrument Account";
	        if (value.equals("FIAC") && tag.equals(":16S:")) return "End Of Financial Instrument Account";
	        if (value.equals("FIA") && tag.equals(":16R:")) return "Start Of Financial Instrument Attributes";
	        if (value.equals("FIA") && tag.equals(":16S:")) return "End Of Financial Instrument Attributes";

	        if (value.equals("REPO") && tag.equals(":16R:")) return "Start Of Repo Details";
	        if (value.equals("REPO") && tag.equals(":16S:")) return "End Of Repo Details";
	        if (value.equals("SETDET") && tag.equals(":16R:")) return "Start Of Settlement Details";
	        if (value.equals("SETDET") && tag.equals(":16S:")) return "End Of Settlement Details";
	        if (value.equals("SETPRTY") && tag.equals(":16R:")) return "Start Of Settlement Parties";
	        if (value.equals("SETPRTY") && tag.equals(":16S:")) return "End Of Settlement Parties";
	        if (value.equals("CONFPRTY") && tag.equals(":16R:")) return "Start Of Confirmation Parties";
	        if (value.equals("CONFPRTY") && tag.equals(":16S:")) return "End Of Confirmation Parties";
	        if (value.equals("CONFDET") && tag.equals(":16R:")) return "Start Of Confirmation Details";
	        if (value.equals("CONFDET") && tag.equals(":16S:")) return "End Of Confirmation Details";
	        if (value.equals("AMT") && tag.equals(":16R:")) return "Amount";
	        if (value.equals("AMT") && tag.equals(":16S:")) return "Amount";
	        if (value.equals("STAT") && tag.equals(":16R:")) return "Start Of Status Detail";
	        if (value.equals("STAT") && tag.equals(":16S:")) return "End Of Status Detail";
	        if (value.equals("REAS") && tag.equals(":16R:")) return "Start Of Reason Detail";
	        if (value.equals("REAS") && tag.equals(":16S:")) return "End Of Reason Detail";

	        if (begin.equals(":BUYR")) return "Buyer";
	        if (begin.equals(":SELL")) return "Seller";
	        if (begin.equals(":DEAG")) return "Delivery Agent";
	        if (begin.equals(":REAG")) return "Receiving Agent";
	        if (begin.equals(":BUSE")) return "Indicator BUY/SELL";
	        if (begin.equals(":PAYM")) return "Delivery Type";
	        if (begin.equals(":PSET")) return "Place of Settlement";

	        if (tag.equals(":11S:")) return "MT and Date of Original Message(Sent)";
	        if (tag.equals(":12E:")) return "Expiration Style";
	        if (tag.equals(":12F:")) return "Option Style";
	        if (tag.equals(":13D:")) return "Date/Time Indication";
	        if (tag.equals(":14D:")) return "Day Count Fraction";
	        if (tag.equals(":15A:")) return "General Information";
	        if (tag.equals(":15B:")) return "New Sequence";
	        if (tag.equals(":15C:")) return "Start Of Settlement Instruction";
	        if (tag.equals(":15D:")) return "Start Of Settlement Instruction";
	        if (tag.equals(":15E:")) return "New Sequence";
	        if (tag.equals(":15F:")) return "New Sequence";
	        if (tag.equals(":15G:")) return "New Sequence";
	        if (tag.equals(":15H:")) return "New Sequence";
	        if (tag.equals(":17A:")) return "Barrier Indicator";
	        if (tag.equals(":17B:")) return "Standing Instructions";
	        if (tag.equals(":17F:")) return "Non-Deliverable Indicator";
	        if (tag.equals(":17R:")) return "Party A's Role";
	        if (tag.equals(":17V:")) return "Buy Sell Indiacator";
	        if (tag.equals(":19A:")) return "Amount";
	        if (tag.equals(":20:")) return "Transaction Reference Number";
	        if (tag.equals(":20C:")) return "Reference Number";
	        if (tag.equals(":21:")) return "Related Reference";
	        if (tag.equals(":21N:")) return "Contract Number Party A";
	        if (tag.equals(":22A:")) return "Type of Operation";
	        if (tag.equals(":22B:")) return "Event Type";
	        if (tag.equals(":22C:")) return "Common Reference";
	        if (tag.equals(":22F:")) return "Indicator";
	        if (tag.equals(":22G:")) return "Type of Barrier";
	        if (tag.equals(":22H:")) return "Indicator";
	        if (tag.equals(":22J:")) return "Type of Trigger";
	        if (tag.equals(":22K:")) return "Type Of Event";
	        if (tag.equals(":23B:")) return "Bank Operation Code";
	        if (tag.equals(":23C:")) return "Message Function";
	        if (tag.equals(":23E:")) return "Instruction Code";
	        if (tag.equals(":23G:")) return "Message Function";
	        if (tag.equals(":24B:")) return "Reason Code";
	        if (tag.equals(":25:")) return "Account Identification";
	        if (tag.equals(":25D:")) return "Status Code";
	        if (tag.equals(":26F:")) return "Settlement Type";
	        if (tag.equals(":28:")) return "Statement Number/Sequence Number";
	        if (tag.equals(":28C:")) return "Statement Number/Sequence Number";
	        if (tag.equals(":29E:")) return "Expiry Location time";
	        if (tag.equals(":30:")) return "Delivery Date";
	        if (tag.equals(":30F:")) return "Last Day of Next Interest Period";
	        if (tag.equals(":30G:")) return "period Start Date and End Date";
	        if (tag.equals(":30H:")) return "Touch Payment Date";
	        if (tag.equals(":30P:")) return "Maturity Date";
	        if (tag.equals(":30T:")) return "Trade Date";
	        if (tag.equals(":30U:")) return "Date of Trigger Hit";
	        if (tag.equals(":30V:")) return "Value Date";
	        if (tag.equals(":30X:")) return "Due Date";
	        if (tag.equals(":31P:")) return "Date and Place of Trade";
	        if (tag.equals(":32A:")) return "Settlement Amount";
	        if (tag.equals(":32B:")) return "Settlement Amount";
	        if (tag.equals(":32E:")) return "Settlement Currency";
	        if (tag.equals(":32H:")) return "Settlement Amount";
	        if (tag.equals(":32Q:")) return "Currency Pair";
	        if (tag.equals(":33B:")) return "Amount Sold";
	        if (tag.equals(":33E:")) return "PayOut Amount";
	        if (tag.equals(":33T:")) return "Deal Price";
	        if (tag.equals(":34B:")) return "Premuim Amount";
	        if (tag.equals(":34E:")) return "Interest Amount";
	        if (tag.equals(":34F:")) return "Floor Limit Indicator";
	        if (tag.equals(":35A:")) return "Quantity of Securities";
	        if (tag.equals(":35B:")) return "Identification Instrument";
	        if (tag.equals(":36:")) return "Exchange Rate";
	        if (tag.equals(":36B:")) return "Trade Quantity";
	        if (tag.equals(":37G:")) return "Rate";
	        if (tag.equals(":37J:")) return "BarrierLevel";
	        if (tag.equals(":37P:")) return "Lower Trigger Level";
	        if (tag.equals(":37U:")) return "TriggerLevel";
	        if (tag.equals(":38A:")) return "Period Of Notice";
	        if (tag.equals(":38J:")) return "Number Of Days or Months";
	        if (tag.equals(":50:")) return "Ordering Customer";
	        if (tag.equals(":50A:")) return "Ordering Customer";
	        if (tag.equals(":50C:")) return "Ordering Customer";
	        if (tag.equals(":50D:")) return "Ordering Customer";
	        if (tag.equals(":52A:")) return "Ordering Institution";
	        if (tag.equals(":52C:")) return "Ordering Institution";
	        if (tag.equals(":52D:")) return "Ordering Institution";
	        if (tag.equals(":53A:")) return "Delivery Agent";
	        if (tag.equals(":53C:")) return "Delivery Agent";
	        if (tag.equals(":53D:")) return "Delivery Agent";
	        if (tag.equals(":54A:")) return "Delivery Agent";
	        if (tag.equals(":54C:")) return "Delivery Agent";
	        if (tag.equals(":54D:")) return "Delivery Agent";
	        if (tag.equals(":56A:")) return "Intermediary";
	        if (tag.equals(":56C:")) return "Intermediary";
	        if (tag.equals(":56D:")) return "Intermediary";
	        if (tag.equals(":57A:")) return "Receiving Agent";
	        if (tag.equals(":57C:")) return "Receiving Agent";
	        if (tag.equals(":57D:")) return "Receiving Agent";
	        if (tag.equals(":58A:")) return "Beneficiary";
	        if (tag.equals(":58C:")) return "Beneficiary";
	        if (tag.equals(":58D:")) return "Beneficiary";
	        if (tag.equals(":59A:")) return "Beneficiary";
	        if (tag.equals(":59C:")) return "Beneficiary";
	        if (tag.equals(":59D:")) return "Beneficiary";
	        if (tag.equals(":60F:")) return "Opening Balance";
	        if (tag.equals(":60M:")) return "Opening Balance";
	        if (tag.equals(":61:")) return "Statement Line";
	        if (tag.equals(":62F:")) return "Closing Balance (Booked Funds)";
	        if (tag.equals(":62M:")) return "Closing Balance (Booked Funds)";
	        if (tag.equals(":64:")) return "Closing Available Funds (Available Funds)";
	        if (tag.equals(":65:")) return "Forward Available Balance";
	        if (tag.equals(":70D:")) return "Narrative";
	        if (tag.equals(":70E:")) return "Narrative";
	        if (tag.equals(":83C:")) return "Safekeeping Account";
	        if (tag.equals(":72:")) return "Sender to Receiver Information";
	        if (tag.equals(":77H:")) return "Type of Agreement";
	        if (tag.equals(":79:")) return "Narrative";
	        if (tag.equals(":82A:")) return "Party A";
	        if (tag.equals(":82D:")) return "Party A";
	        if (tag.equals(":83C:")) return "Safekeeping Account";
	        if (tag.equals(":83J:")) return "Fund or Beneficiary Customer";
	        if (tag.equals(":85a:")) return "Deliverer's Instructing Party";
	        if (tag.equals(":85A:")) return "Deliverer's Instructing Party";
	        if (tag.equals(":85C:")) return "Deliverer's Instructing Party";
	        if (tag.equals(":85D:")) return "Deliverer's Instructing Party";
	        if (tag.equals(":86:")) return "Information to Account Owner";
	        if (tag.equals(":86A:")) return "Intermediary 2";
	        if (tag.equals(":86C:")) return "Intermediary 2";
	        if (tag.equals(":86D:")) return "Intermediary 2";
	        if (tag.equals(":87a:")) return "Receiver/Deliverer of Securities";
	        if (tag.equals(":87A:")) return "Party B";
	        if (tag.equals(":87D:")) return "Party B";
	        if (tag.equals(":87J:")) return "Party B";
	        if (tag.equals(":88a:")) return "Broker Details";
	        if (tag.equals(":88A:")) return "Broker Details";
	        if (tag.equals(":88C:")) return "Broker Details";
	        if (tag.equals(":88D:")) return "Broker Details";
	        if (tag.equals(":24D:")) return "Dealing Method";
	        if (tag.equals(":29A:")) return "Contact Information";
	        if (tag.equals(":90A:")) return "Deal Price";
	        if (tag.equals(":90C:")) return "Number and Sum of Entries";
	        if (tag.equals(":90D:")) return "Number and Sum of Entries";
	        if (tag.equals(":92A:")) return "Fixed Rate";
	        if (tag.equals(":92C:")) return "Variable Rate";
	        if (tag.equals(":94A:")) return "Scope of Operation";
	        if (tag.equals(":95P:")) return "Agent";
	        if (tag.equals(":97A:")) return "Account Number";
	        if (tag.equals(":98A:")) return "Date";
	        if (tag.equals(":98B:")) return "Date";
	        if (tag.equals(":99B:")) return "Repurchase Call Delay";

	        return "Unknown";

	    }

	    public String getSettlementPlatform() {
	        if (_block3 == null)
	            return null;
	        int start = _block3.indexOf("{103:");
	        if (start >= 0) {
	            String mur = _block3.substring(start + 5);
	            if (!commonUTIL.isEmpty(mur)) {
	                int end = mur.indexOf("}");
	                if (end > 0) {
	                    return mur.substring(0, end);
	                }
	            }
	        }
	        return null;
	    }
	    public String getInputDate() {
	        return _inputDate;
	    }
	    public String getSwiftTextMessage() {
			// TODO Auto-generated method stub
			return null;
		}
		public boolean parseSwiftText(String string, String messageGateway) {
			// TODO Auto-generated method stub
			return false;
		}
		public void customizeBlock(Message message, Trade trade,
				Transfer transfer, Object object) {
			// TODO Auto-generated method stub
			
		}

}
