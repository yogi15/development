package bo.swift;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import util.ClassInstantiateUtil;
import util.ReferenceDataCache;
import util.commonUTIL;
import beans.DocumentInfo;
import beans.LeContacts;
import beans.Message;
import beans.Trade;
import beans.Transfer;
import beans.TransferRule;
import bo.message.bomessagehandler.MessageFormat;
import bo.message.bomessagehandler.MessageFormatterUtil;
import bo.swift.Formatter.BaseFormatter;
import bo.swift.Formatter.BaseFormattorContext;
import bo.swift.Formatter.FormatterIterator;
import bo.transfer.rule.ProductTransferRule;

import com.calypso.jaxb.swift.ItemDefType;
import com.calypso.jaxb.swift.ModeSelectionListType;
import com.calypso.jaxb.swift.ModeSelectionType;
import com.calypso.jaxb.swift.SwiftFieldElement;
import com.calypso.jaxb.swift.SwiftFieldOptionElement;
import com.calypso.jaxb.swift.SwiftMsgDefElement;
import com.calypso.jaxb.swift.SwiftSequenceElement;

import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;


public class SwiftGenerator extends BaseFormatter  {
	public static final String extended = "swift.tempaltes";
    public static final String original = "swift.tempaltes";
    protected static final int TEMPLATE_CACHE_MAX_AGE = 1000;
    protected static final boolean CACHE_TEMPLATES = TEMPLATE_CACHE_MAX_AGE > 0;
    static public final char KEYWORD_FUNCT_CHAR = '/';
    static public final char KEYWORD_FIELD_CHAR = '.';
    static public final char KEYWORD_PARAM_CHAR = '#';
    static public final char KEYWORD_CHAR = '|';
    static public final char BS_CHAR = '\\';
    protected ValueTag _tagValue = null;
    protected String   _tagFormat = null; // !6n etc...
    protected static final String SAME_AS_RECEIVER = "LESameAsReceiver";
    protected static final String DIFFERENT_FROM_RECEIVER = "LEDifferentFromReceiver";
    protected static final String SAME_AS_SENDER = "LESameAsSender";
    protected static final String DIFFERENT_FROM_SENDER = "LEDifferentFromSender";

    
    private static ConcurrentHashMap<String, byte[]> _templateCache = new ConcurrentHashMap<String, byte[]>();
    private static HashSet<String> _notFoundCache = new HashSet<String>();
    private static ConcurrentHashMap<String, Long> _templateCachingTimestamp = new ConcurrentHashMap<String, Long>();
    RemoteBOProcess remoteBOProcess;
    
	Hashtable<String,ProductTransferRule> rulesCache = new Hashtable<String,ProductTransferRule>();
	private ProductTransferRule _productTransferRules = null;
	public SwiftMessage generate(
			 Message message, Trade trade, 
			 Transfer transfer,RemoteTrade remoteTrade,RemoteReferenceData remoteRef) throws Exception {
		
		        System.out.println("pppppp");
		        return   generateSwift(message.getTemplateName(),transfer,message,trade,remoteTrade,remoteRef);
				
		
	}

	
	 protected synchronized SwiftMessage generateSwift(String template,Transfer transfer,Message message,Trade trade,RemoteTrade remoteTrade,RemoteReferenceData remoteRef)  {
		
		  if ((message.getFormat()!= null)
	                && ((!message.getFormat().equalsIgnoreCase("SWIFT") && (!message.getFormat().equalsIgnoreCase("CCIL"))))) {
	            commonUTIL.display("SwiftGenerator"," Not a Swift Message : ("  + message.getFormat() + ") for message "  + message.getId());
	            return null;
	        }
		  ProductTransferRule  transferRule =  getTransferRule(trade.getProductType());
		  transferRule.setRemoteTrade(remoteTrade);
		  transferRule.setRefDate(remoteRef);
		  Vector<String> messageError = new Vector<String>();
		  Vector<TransferRule> rules = transferRule.generateRules(trade,messageError);
		  _producttransferRule = transferRule;
		 
		 
		  LeContacts sender =ReferenceDataCache.getLEContact(message.getSenderRole(), null, message.getSenderId(), trade.getProductType(), "Default");
		  LeContacts receiver =ReferenceDataCache.getLEContact(message.getReceiverRole(), null, message.getReceiverId(), trade.getProductType(), "Default");
		 
		  String swiftMTName = getSwiftMTName(template, transfer);
		  /**
	         * Read in the XML definition for this SWIFT message. SwiftMsgDef object
	         * is created by JAXB unmarshalling of XML document.
	         */
	        SwiftMsgDefElement definition = getMessageDefinition(swiftMTName);
	        if (definition == null)
	            return null;

	        if (definition.getCancellationName() != null) {
	            if (message.getLinkId() != 0
	                    && message.getAction().equals("CANCEL")
	                    && hasOriginalMessage(message, remoteBOProcess)) {

	                SwiftMsgDefElement canceldefinition = getMessageDefinition(definition.getCancellationName());

	                if (canceldefinition != null)
	                    definition = canceldefinition;
	            }
	        }
	        _modes = getModes(definition);
	        _message = message;
	        _trade = trade;
	        _transfer = transfer;
	        _sender = sender;
	        _receiver = receiver;
	        _transferRules = rules;
	        _productTransferRules = transferRule;
	        _tagMap = new HashMap();
	        _sequenceFields = new HashMap();
	        _conditionalFields = new Vector();
	        _sortCodeUsed = false;
	        _dataSourceScheme = SwiftUtil.getDataSourceScheme(trade,
	                                                          transfer,
	                                                          message.getSettleDate()); // needs to understand what it return.
	        /**
	         * Processing: This is where we actually generate the SWIFT message.
	         */
	        SwiftMessage swift = createMessage(definition, message);
	        swift.setType(swiftMTName);
	     // for swiftMTNames like MT202XferAgent, to get the proper application
	        // header,
	        // we need this:
	        if (swiftMTName.length() > 5)
	            swift.setType(swiftMTName.substring(0, 5));

	        _swiftMessage = swift;
	        List sequences = definition.getSwiftSequence();
	        for (Iterator iter = sequences.iterator(); iter.hasNext();) {
	            SwiftSequenceElement sequence = (SwiftSequenceElement)iter.next();
	            try {
					generateSequence(swift, sequence);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }                   
	        /**
	         * Post processing: We now need to check fields with hasField condition
	         * and remove them if condition is not meant.
	         */
	        checkConditionalFields(swift);
	        swift.customizeBlock(message, trade, transfer, null);

	        _swiftMessage = null;
	        return swift;
	 }
	 
	 protected boolean isSameBicCode(String bicCode, int contactId, String defaultContactBicCode) {
	        if (commonUTIL.isEmpty(bicCode)) 
	            return false;
	        if (contactId <= 0)
	            return false;
	        LeContacts leC = null;// ReferenceDataCache.get.getlBOCache.getLegalEntityContact(DSConnection.getDefault(), contactId);
	        if (leC != null) {
	            String contactBicCode = leC.getSwift();
	            if (commonUTIL.isEmpty(contactBicCode) && !commonUTIL.isEmpty(defaultContactBicCode)) {
	                contactBicCode = defaultContactBicCode;
	            }
	            if (commonUTIL.isSame(bicCode, contactBicCode))
	                return true;
	        }
	        return false;
	    }

	    protected void removeField(SwiftMessage message, SwiftFieldMessage field) {
	        // We first remove the field directly from the swift message
	        message.getFields().remove(field);
	        /**
	         * Now it may be that the field is actually a sequence. If the sequence
	         * is removed, all of its underlying fields should also be removed.
	         */
	        Vector v = (Vector)_sequenceFields.get(field);
	        if (v == null)
	            return;

	        for (int i = 0; i < v.size(); i++) {
	            message.getFields().remove(v.elementAt(i));
	        }
	    }

	 protected void checkConditionalFields(SwiftMessage message) {
	        if (_conditionalFields == null)
	            return;

	        for (int i = 0; i < _conditionalFields.size(); i++) {
	            CheckEntry entry = (CheckEntry)_conditionalFields.elementAt(i);
	            SwiftFieldMessage field = entry.field;
	            String hasField = entry.hasField;
	            String withValue = entry.withValue;
	            String hasValue = entry.hasValue;

	            // Special Logic - you can specify a post condition in the withvalue
	            // so if you have not defined an hasField but withValue
	            boolean couldBeRemoved = true;
	            if (!commonUTIL.isEmpty(hasValue)) {
	                if (hasValue != null &&
	                    hasValue.equals(SAME_AS_RECEIVER)) {
	                    if ((field.getLegalEntityId() != 0 &&
	                         field.getLegalEntityId() == _message.getReceiverId()) ||
	                        !differentFromReceiverAddressCode(field.getBicCode(), _message.getReceiverAddressCode())) {
	                        // There is no sort code
	                        if (field.getValue().indexOf("//") < 0) {
	                            couldBeRemoved = false;
	                        }
	                    }
	                } else if (hasValue != null &&
	                           hasValue.equals(DIFFERENT_FROM_RECEIVER)) {
	                    if (field.getLegalEntityId() != 0 &&
	                        ((field.getLegalEntityId() != _message.getReceiverId() &&
	                                differentFromReceiverAddressCode(field.getBicCode(), _message.getReceiverAddressCode()))
	                        || (field.getLegalEntityId() == _message.getReceiverId() &&
	                                !isSameBicCode(field.getBicCode(), _message.getReceiverId(), _message.getReceiverAddressCode())))) {
	                        couldBeRemoved = false;
	                    } else {
	                        if (field.getLegalEntityId() != 0
	                                && field.getLegalEntityId() == _message.getReceiverId()) {
	                            // If there is a sort code we consider it is
	                            // different.
	                            if (field.getValue().indexOf("//") >= 0) {
	                                couldBeRemoved = false;
	                            }
	                        }

	                    }
	                }else if (hasValue != null &&
	                		hasValue.equals(SAME_AS_SENDER)) {
	                	if ((field.getLegalEntityId() != 0 &&
	                			field.getLegalEntityId() == _message.getSenderId()) ||
	                			!differentFromSenderAddressCode(field.getBicCode(), _message.getSenderAddressCode())) {
	                		// There is no sort code
	                		if (field.getValue().indexOf("//") < 0) {
	                			couldBeRemoved = false;
	                		}
	                	}
	                }else if (hasValue != null &&
	                		hasValue.equals(DIFFERENT_FROM_SENDER)) {
	                	if (field.getLegalEntityId() != 0 &&
	                			((field.getLegalEntityId() != _message.getSenderId() &&
	                			differentFromSenderAddressCode(field.getBicCode(), _message.getSenderAddressCode()))
						 || (field.getLegalEntityId() == _message.getSenderId() &&
						     !isSameBicCode(field.getBicCode(), _message.getSenderId(), _message.getSenderAddressCode())))) {
	                			couldBeRemoved = false;
	                	} else {
	                        if (field.getLegalEntityId() != 0
	                                && field.getLegalEntityId() == _message.getReceiverId()) {
	                            // If there is a sort code we consider it is
	                            // different.
	                            if (field.getValue().indexOf("//") >= 0) {
	                                couldBeRemoved = false;
	                            }
	                        }

	                	}

	                }
	                if (!commonUTIL.isEmpty(hasValue)) {
	                	if (field.getValue().equals(hasValue)) {
	                		couldBeRemoved = false;
	                	}
	                }
	            }
	           


	            if (commonUTIL.isEmpty(hasField)) {
	            	if (couldBeRemoved)
	            		removeField(message, field);
	            	else
	            		continue;
	            }

	            if (_tagMap.get(hasField) == null) {
	                if (couldBeRemoved) {
	                    removeField(message, field);
	                }
	                continue;
	            } else if (withValue != null
	                    && !withValue.equals(EMPTYSTRING)) {
	                SwiftFieldMessage checkField = (SwiftFieldMessage)_tagMap.get(hasField);
	                if (checkField.getValue().equals(withValue) == false) {
	                    if (couldBeRemoved) {
	                        removeField(message, field);
	                        continue;
	                    }
	                }
	            }
	        }
	 }
	 
	        protected boolean differentFromReceiverAddressCode(String bicCode, String receiverCode) {
	            if (commonUTIL.isEmpty(bicCode) && commonUTIL.isEmpty(receiverCode))
	                return false;

	            if (!commonUTIL.isEmpty(bicCode))
	                return !bicCode.equals(receiverCode);
	            else
	                return !receiverCode.equals(bicCode);
	        }
	        protected boolean differentFromSenderAddressCode(String bicCode, String senderCode) {
	        	return differentFromReceiverAddressCode( bicCode, senderCode);
	        }
	  protected FormatterIterator _iterator;
	  /**
	     * Generates a SWIFT sequence and inserts the fields into the given swift
	     * message.
	     * 
	     * @param swift
	     *            the <code>SwiftMessage</code> in which to add the sequence.
	     * @param sequence
	     *            the Swift sequence definition parsed from an XML template.
	     */
	    protected void generateSequence(SwiftMessage swift,
	                                    SwiftSequenceElement sequence) throws Exception {
	    	 validateSequence(sequence);

	         char status = getStatus(_message.getAction(),
	                                 _modes,
	                                 sequence.getModes());
	         // Sequence should not appear if mode is ' '
	         if (status == ' ')
	             return;

	         // Sequence appears if conditions are met. Those must be
	         // checked to determine whether or not to add this sequence
	         boolean conditional = (status == '+' || status == '-');
	         if (conditional) {
	             if (sequence.getCondition() != null) {
	                 /**
	                  * We must check if the condition is met
	                  */
	                 if (!checkCondition(sequence.getCondition()))
	                     return;
	             }
	             if (sequence.getFilter() != null) {
	                 /**
	                  * We check the related Static Data Filter
	                  */
	               //  if (!checkFilter(sequence.getFilter()))
	                  //   return;
	             }
	             /**
	              * If we're here, the conditions have been met. we adjust the status
	              * to reflect whether or not the field is 'M'andatory or 'O'ptional.
	              */
	             if (status == '-')
	                 status = 'O';
	             else
	                 status = 'M';
	         }
	         
	       
	         /**
	          * Is there an iterator defined for this sequence? If so, we instantiate
	          * it. If not, we use ourselves as an iterator, which is default. What
	          * this means is that we will iterate *exactly once* through the
	          * sequence. That is the expected behavior.
	          */
	         String iteratorS = sequence.getIterator();
	         FormatterIterator iter = null;
	         if (iteratorS != null) {
	             iter = getIterator(iteratorS);
	             // Initialize the iterator with the context information
	             if (!_contexts.empty()) {
	                 _iterator.setPersistentVar(PARENT_FORMATTER_CONTEXT,
	                                            _contexts.peek());
	             }
	             iter.init(_message,
	                       _trade,
	                       _sender,
	                       _receiver,
	                       _transfer,
	                       _transferRules,
	                      null,
	                       null,_productTransferRules);
	         } else {
	             _iterator = null;
	         }
	         
	         /**
	          * Reset these values as they may be used in methods called during
	          * iterative processing.
	          */
	         _iteration = 0;
	         SwiftFieldMessage swiftSequence = null;
	         while (hasNext()) {
	             next();

	             swiftSequence = new SwiftFieldMessage();
	             swiftSequence.setName(sequence.getName());
	             swiftSequence.setStatus((byte)status);
	             swiftSequence.setTAG(sequence.getOpenTag());
	             if (sequence.getCode() != null)
	                 swiftSequence.setValue(sequence.getCode());
	             else
	                 swiftSequence.setValue(EMPTYSTRING);

	             /**
	              * If this sequence is "conditional" on another field in the Swift
	              * Message, we need to keep track of it for processing after the
	              * whole message has been generated.
	              */
	             if (conditional
	                     && (sequence.getHasField() != null || sequence.getHasValue() != null)) {
	                 _conditionalFields.addElement(new CheckEntry(swiftSequence,
	                                                              sequence.getHasField(),
	                                                              sequence.getWithValue(),
	                                                              sequence.getHasValue()));

	             }
	         }
	         /**
	             * Add the field to the message, if it is not empty.
	             */
	            if (swiftSequence.getTAG() != null
	                    || !swiftSequence.getValue().equals(EMPTYSTRING)) {
	                if (_iteration == 1)
	                    addSequence(swift, swiftSequence);
	            }
	            
	            /**
	             * We now iterate over Swift Fields in the sequence.
	             */
	            List items = sequence.getSwiftItem();
	            boolean empty = true;
	            for (Iterator fieldIter = items.iterator(); fieldIter.hasNext();) {
	                JAXBElement<ItemDefType> itemDef = (JAXBElement<ItemDefType>)fieldIter.next();  // JAVA6 change: Use JAXBElement<T>
	                ItemDefType item = itemDef.getValue();
	                if (item instanceof SwiftFieldElement) {
	                    if (generateField(swift,
	                                      swiftSequence,
	                                      (SwiftFieldElement)item))
	                        empty = false;
	                } else if (item instanceof SwiftSequenceElement) {
	                    // Push context variables
	                    BaseFormattorContext c = new BaseFormattorContext();
	                    c.setIterator(_iterator);
	                    c.setIteration(_iteration);
	                    c.setIteratorObject(_iteratorObject);
	                    _contexts.push(c);

	                    // Reset them to default values
	                    generateSequence(swift, (SwiftSequenceElement)item);

	                    // Pop context variables
	                    if (!_contexts.empty()) {
	                        _context = (BaseFormattorContext)_contexts.pop();
	                        _iteration = _context.getIteration();
	                        _iterator = _context.getIterator();
	                        _iteratorObject = _context.getIteratorObject();
	                    }
	                }
	            }
	            
	            if (empty) {
	                swift.getFields().removeElement(swiftSequence);
	            }

	            /**
	             * Add a closing tag, if one is defined and the sequence is not
	             * empty (An empty sequence is one where there are no fields
	             * defined.)
	             */
	            if (!empty
	                    && sequence.getCloseTag() != null) {
	                swiftSequence = new SwiftFieldMessage();
	                swiftSequence.setName(sequence.getName());
	                swiftSequence.setStatus((byte)status);
	                swiftSequence.setTAG(sequence.getCloseTag());
	                if (sequence.getCode() != null)
	                    swiftSequence.setValue(sequence.getCode());
	                else
	                    swiftSequence.setValue(EMPTYSTRING);
	                addSequence(swift, swiftSequence);
	            }

	    }
	    
	    
	   

	    
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

	    /* Store formatter context for nested iterator */
	    protected Stack _contexts = new Stack();
	    

	 /*   protected boolean checkFilter(String name) {

	        StaticDataFilter filter = BOCache.getStaticDataFilter(_dsCon, name);
	        if (filter == null) {
	            Log.error(LOG_CATEGORY,
	                      new Throwable("No Static Data Filter found: "
	                              + name));
	            return true;
	        }

	        if (_transfer != null
	                && _message != null)
	            return filter.accept(_trade, _transfer, _message);
	        else if (_trade != null
	                && _transfer != null)
	            return filter.accept(_trade, _transfer);
	        else if (_trade != null)
	            return filter.accept(_trade);

	        return false;
	    }*/
	    
	    /**
	     *
	     */
	   
	    protected char getStatus(String action, Vector modes, String modeSetting) throws Exception {

	        /**
	         * Some basic error checking.
	         */
	        if (action == null)
	            throw new Exception("Null Action");
	        if (modeSetting.length() != modes.size())
	            throw new Exception("# of modes in tag does not equal modes in ModeSelectionList");

	        int index = modes.indexOf(action.toString());
	        if (index < 0
	                && (action.equals(action) || (action.equals(action))))
	            index = modes.indexOf(action);
	        if (index < 0)
	            throw new Exception("No mode matching action '"
	                    + action + "' in template.");

	        return modeSetting.charAt(index);
	    }
	    /**
	     * Method checks the validity of the defined <code>SwiftSequence</code> to
	     * ensure that all mandatory attributes were set properly in the XML
	     * template document. If not, a <code>MessageFormatException</code> is
	     * thrown with a description of the missing information.
	     * 
	     * @param sequence
	     *            <code>SwiftSequence</code> to be checked for validity
	     * @throws Exception
	     *             if the sequence definition is invalid.
	     */
	    protected static final String EMPTYSTRING = "";
	    protected void validateSequence(SwiftSequenceElement sequence) throws Exception {

	        if (sequence == null)
	            throw new Exception("SwiftSequence is null in XML document.");

	        String name = sequence.getName();
	        if (name == null
	                || name.equals(EMPTYSTRING))
	            throw new Exception("SwiftSequence name tag is mandatory.");
	        if (sequence.getModes() == null)
	            throw new Exception("SwiftSequence \""
	                    + name + "\" modes must be set.");

	    }
	 
	 /**
	     * Creates a new <code>SwiftMessage</code> from the given
	     * <code>BOMessage</code> and definition.
	     * 
	     * @param definition
	     *            The Swift message definition parsed from an XML template.
	     * @param message
	     *            The message to use in generating the SWIFT message.
	     */
	    protected SwiftMessage createMessage(SwiftMsgDefElement definition,
	                                         Message message) {
	        SwiftMessage swift = null;
	        String type = "MT202";//message.getTemplateName();
	        
	        if (type.startsWith("MT") == false)
	            type = definition.getName();
	        swift = new SwiftMessage(message, type); // Message type (MT)?

	        return swift;
	    }
	    /**
	     * returns a Vector of <code>String</code>s representing the modes defined
	     * in the <code>SwiftMsgDef</code> object, parsed from an XML template
	     * document.
	     * 
	     * @param definition
	     *            A <code>SwiftMsgDef</code> Object representing the XML
	     *            template definition.
	     * @return A <code>Vector</code> of Strings listing the modes for which this
	     *         SWIFT XML template is applicable.
	     */
	    protected Vector getModes(SwiftMsgDefElement definition) {
	        Vector modes = new Vector();
	        ModeSelectionListType list = definition.getModeSelectionList();
	        List l = list.getModeSelection();
	        for (Iterator iter = l.iterator(); iter.hasNext();) {
	            ModeSelectionType type = (ModeSelectionType)iter.next();
	            modes.addElement(type.getQualifier());
	        }

	        return modes;
	    }
	 protected boolean hasOriginalMessage(Message message, RemoteBOProcess remoteBOProcess) {

	        Message sent = getOriginalMessage(message, remoteBOProcess);

	        if (sent != null) {
	            if (sent.getExternalB())
	                return false;
	            if (getSwift(sent, remoteBOProcess) != null)
	                return true;
	        }
	        return false;
	    }

	 protected Message getOriginalMessage(Message message, RemoteBOProcess remoteBO) {

	        Message original = null;
	        try {
	        	  original =remoteBO.selectMessageOnLinkid(message.getLinkId());
	        } catch (Exception e) {
	           
	            commonUTIL.displayError("SwiftGenrator", "getOriginalSwift Couldn't find the original messages", e);
	            return null;
	        }

	        return original;
	    }

	    protected SwiftMessage _originalSwift = null;

	    protected SwiftMessage getOriginalSwift(Message message,
	                                            Connection dsCon) {

	        if (_originalSwift != null)
	            return _originalSwift;

	       Message sent = getOriginalMessage(message, remoteBOProcess);

	        if (sent != null) {
	            if (sent.getExternalB())
	                return getIncomingSwift(sent, remoteBOProcess);
	            _originalSwift = getSwift(sent, remoteBOProcess);
	            if (_originalSwift != null)
	                return _originalSwift;
	            try {
	              //  SWIFTFormatter formatter = (SWIFTFormatter)Swif..findSWIFTFormatter(sent);
	              //  _originalSwift = formatter.generateSwift(sent, dsCon);
	                return _originalSwift;
	            } catch (Exception e) {
	               commonUTIL.displayError("SwiftGenrator", "getOriginalSwift", e);
	            }
	        }
	        return null;
	    }
	    protected SwiftMessage getIncomingSwift(Message boMessage,
                RemoteBOProcess remoteBOProcess) {

					if (!boMessage.getExternalB())
						return null;
					_originalSwift = getSwift(boMessage, remoteBOProcess);
						return _originalSwift;
				}
	    
	    /**
	     *
	     */
	    protected boolean isConditionMet(String condition) {

	        if (condition.endsWith("()"))
	            condition = condition.substring(0, condition.length() - 2);

	        Method method = getConditionMethod(this, condition);
	        /**
	         * If a method is not found, perhaps we can query a keyword method.
	         */
	        if (method == null)
	            method = getParseMethod(this, condition);

	        if (method != null) {
	            Object objs[] = new Object[8];
	            objs[0] = _message;
	            objs[1] = _trade;
	            objs[2] = _sender;
	            objs[3] = _receiver;
	            objs[4] = _transferRules; // transfersrules.
	            objs[5] = _transfer;
	            objs[6] = _dsCon;
	            objs[7] = _producttransferRule;
	            Object o = null;
	            try {
	                o = method.invoke(this, objs);
	            } catch (Exception e) {
	               
	                commonUTIL.displayError("SwiftGenerator",  "Condition ("
	                        + condition + ")" + " Method (" + method.getName()
	                        + ")" + " SWIFTFormatter " + getClass().getName(), e);
	            }
	            if (o == null)
	                return false;
	            if (o instanceof Boolean)
	                return ((Boolean)o).booleanValue();
	            String s = o.toString().toUpperCase();
	            if (s.equals("Y")
	                    || s.equals("T") || s.equals("TRUE") || s.equals("YES"))
	                return true;
	            return false;
	        }

	       commonUTIL.display("SwiftGenerator",
	                  "Method not found for condition: "
	                          + condition);
	        return false;
	    }
	    
	    protected boolean checkCondition(String condition) {

	        condition = condition.trim();
	        int index = condition.indexOf(" ");
	        if (index == -1)
	            index = condition.length();

	        String item = condition.substring(0, index);
	        String remainder = condition.substring(index).trim();

	        boolean negate = false;
	        if (item.startsWith("!")) {
	            negate = true;
	            item = item.substring(1);
	        }

	        boolean conditionMet = isConditionMet(item);

	        if (negate)
	            conditionMet = !conditionMet;

	        if (remainder == null
	                || remainder.equals(EMPTYSTRING))
	            return conditionMet;

	        String s = remainder.toUpperCase();
	        if (s.startsWith("AND "))
	            return conditionMet
	                    && checkCondition(remainder.substring(4));
	        else if (s.startsWith("OR "))
	            return conditionMet
	                    || checkCondition(remainder.substring(3));
	        else {
	            commonUTIL.display("SwiftGenerator", "Invalid condition: condition");
	            return false;
	        }

	    }
					
		protected SwiftMessage getSwift(Message boMessage, RemoteBOProcess remoteBOProcess) {
					
					DocumentInfo doc = null;
					try {
					doc = remoteBOProcess.getLatestAdviceDocument(boMessage.getId(), null);
					} catch (Exception e) {
						 commonUTIL.displayError("SwiftGenrator", "getSwift", e);
					doc = null;
					}
					if (doc == null)
					return null;
					
					// We try to generate the swift from the text
					if (doc.getDocument() == null)
					return null;
					
					StringBuffer buff = new StringBuffer(doc.getDocument());
					
					SwiftMessage swift = new SwiftMessage(boMessage);
					if (!swift.parseSwiftText(buff.toString(), boMessage.getMessageGateway()))
					return null;
					return swift;
					}
	 static ConcurrentHashMap<String, SwiftMsgDefElement>_parsedTemplateCache = new ConcurrentHashMap<String, SwiftMsgDefElement>();
	    static ConcurrentHashMap<String, Long> _parsingTimestamp = new ConcurrentHashMap<String, Long>(); 

	 private SwiftMsgDefElement getMessageDefinition(String swiftMTName) {
		// TODO Auto-generated method stub
		 InputStream stream = null;
		  String foundName = null;
		  Vector names = new Vector();
	        names.addElement(swiftMTName);
	        if (swiftMTName.startsWith("MT"))
	            names.addElement("MTx"
	                    + swiftMTName.substring(3));
		 try {
			 for (int i = 0; i < names.size(); i++) {
		            String aName = (String)names.elementAt(i);
		            SwiftMsgDefElement res;
		            if (TEMPLATE_CACHE_MAX_AGE > 0 && (res = _parsedTemplateCache.get(swiftMTName)) != null) {
		                try {
		                    Long parseTime = _parsingTimestamp.get(swiftMTName);
		                    if (parseTime != null && (System.currentTimeMillis()-parseTime) < TEMPLATE_CACHE_MAX_AGE) {
		                        com.calypso.tk.bo.swift.ParsedTemplateElement res2 = (com.calypso.tk.bo.swift.ParsedTemplateElement) res;
		                        Object clone = res2.clone();
		                        return (SwiftMsgDefElement) clone;
		                    }
		                } catch (Exception e) {
		                    commonUTIL.displayError("SwiftGenerator", "Could not clone cached template. It will be re-loaded.",e);
		                }
		            }
		            stream = getFileStream(swiftMTName);
		            if (stream != null) {
		            	foundName = swiftMTName;
		            	break;
		            }
			 }
	            // create a JAXBContext capable of handling classes
	            // generated into the tk.bo.swift.xml package
	            JAXBContext jc = JAXBContext.newInstance("com.calypso.jaxb.swift", getClass().getClassLoader());
	            // create an Unmarshaller
	            Unmarshaller u = jc.createUnmarshaller();
	            Object element = u.unmarshal(stream);
	            JAXBElement<SwiftMsgDefElement> elem = (JAXBElement<SwiftMsgDefElement>) element; // JAVA6 change: Use JAXBElement<T>
				SwiftMsgDefElement msg = elem.getValue();
	            if (TEMPLATE_CACHE_MAX_AGE > 0) {
	                _parsingTimestamp.put(foundName, System.currentTimeMillis());
	                _parsedTemplateCache.put(foundName, msg);
	            }
	            return msg;
		 } catch (JAXBException je) {
			 System.out.println(je);
			 
		 }
		return null;
		
		
	}
	 private static InputStream getStreamViaCache(ClassLoader cl,  String templateName) {
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
	 
	 protected InputStream getFileStream(String name) {
	        ClassLoader cl = getClass().getClassLoader();
	        String templateName = name
	                + ".xml";
	        Vector dirs = new Vector();
	        dirs.addElement("resources/templates/swift/");
	        return getInputStream(cl, dirs, templateName);
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
	 
	 /**
	     * Generates a SWIFT field and adds it (if applicable) to the given sequence
	     * in the given swift message.
	     * 
	     * @param swift
	     *            the <code>SwiftMessage</code> in which to add the field.
	     * @param sequence
	     *            the Swift sequence in which to add this field.
	     * @param field
	     *            the Swift field definition parsed from an XML template.
	     */
	    protected boolean generateField(SwiftMessage swift,
	                                    SwiftFieldMessage sequence,
	                                    SwiftFieldElement field) throws Exception {

	        /**
	         * This method checks that all required tags are set on the field. If
	         * not, a MessageFormatException is thrown.
	         */
	        validateField(field);
	        char status = getStatus(_message.getAction(),_modes,field.getModes());
	        // Field should not appear if mode is ' '
	        if (status == ' ')
	        	return false;

	        // Field appears if conditions are met. Those must be
	        // checked to determine whether or not to add this field
	        	boolean conditional = (status == '+' || status == '-');
	        	 if (conditional) {
	                 if (field.getCondition() != null) {
	                     /**
	                      * We must check if the condition is met
	                      */
	                     if (!checkCondition(field.getCondition()))
	                         return false;
	                 }
	                 if (field.getFilter() != null) {
	                     /**
	                      * We check the related Static Data Filter
	                      */
	                    // if (!checkFilter(field.getFilter()))
	                         return false;
	                 }
	                 /**
	                  * We adjust the status to reflect whether or not the field is
	                  * 'M'andatory or 'O'ptional.
	                  */
	                 if (status == '-')
	                     status = 'O';
	                 else
	                     status = 'M';
	             }
	        	 String tag = field.getTag();
	             SwiftFieldMessage swiftField = new SwiftFieldMessage();
	             swiftField.setName(field.getName());
	             swiftField.setTAG(tag);
	             swiftField.setStatus((byte)status);
	             swiftField.setValue(EMPTYSTRING);

	             String code = field.getCode();
	             if (code == null)
	                 code = EMPTYSTRING;
	             if (!commonUTIL.isEmpty(field.getValue())) {
	                 _tagValue = new ValueTag(tag);
	                 _tagFormat = field.getFormat();
	                 String value = parseValue(field.getValue(), field.getFormat());
	                 _tagValue.setValue(value);
	                 swiftField.setLegalEntityId(_tagValue.getLegalEntityId());
	                 swiftField.setBicCode(_tagValue.getBicCode());
	                 swiftField.setTAG(_tagValue.getTagOption());
	                 swiftField.setValue(code
	                         + _tagValue.getValue());
	                 _tagValue = null;
	             } else if (!commonUTIL.isEmpty(field.getGenerator())) {
	                 swiftField.setValue(generate(field.getGenerator()));
	             } else if (!commonUTIL.isEmpty(field.getTemplate())) {
	                 InputStream stream = getInputStream(getClass().getClassLoader(),
	                                                     field.getTemplate());
	                 if (stream == null) {
	                 } else {
	                     String value = readAndParseContents(stream,
	                                                         _message,
	                                                         _trade,
	                                                         _sender,
	                                                         _receiver,
	                                                         _transferRules,
	                                                         _transfer,
	                                                         _dsCon,_productTransferRules);
	                     swiftField.setValue(value);
	                 }
	             }
	            /* if (_transfer != null) {
	                 if (_message.getReceiverRole() == null
	                         || LegalEntity.isAgent(_message.getReceiverRole())) {
	                     ManualSDI msd = null;
	                     if (_transfer.isManualSDI()) {
	                         msd = BOCache.getManualSDI(_dsCon,
	                                                    _transfer.getManualSDId());

	                         if (msd != null)
	                             SwiftUtil.replaceManualSDISwift(swiftField, msd);
	                     }
	                 }
	             }*/// If the status of the field is optional, we do not
	             // add it if the value is not set.
	             String checkValue = swiftField.getValue();
	             if (status == 'O'
	                     && (commonUTIL.isEmpty(checkValue) || checkValue.equals(code)))
	                 return false;

	             // Else we check it's format
	             setFormat(field, swiftField);

	             /**
	              * If this field is "conditional" on another field in the Swift Message,
	              * we need to keep track of it for processing after the whole message
	              * has been generated.
	              */
	             if (conditional
	                     && (field.getHasField() != null || field.getHasValue() != null)) {
	                 _conditionalFields.addElement(new CheckEntry(swiftField,
	                                                              field.getHasField(),
	                                                              field.getWithValue(),
	                                                              field.getHasValue()));
	             }

	             addField(swift, sequence, swiftField);
	             return true;
	    }
	    /**
	     * Adds the given <code>SwiftFieldMessage</code> field to the
	     * <code>SwiftMessage</code>. The actual addition comes simply by adding to
	     * the message's <code>Vector</code> of fields. However, we must keep track
	     * by mapping the tag back to the field as it may need to be removed later
	     * on if its <b>HasField</b> and/or <b>WithValue</b> conditions are not met.
	     * These conditions are evaluated post-generation of the SWIFT message.
	     */
	    protected void addField(SwiftMessage message,
	                            SwiftFieldMessage sequence,
	                            SwiftFieldMessage field) {
	        String tag = field.getTAG();
	        // Map the tag value back to the field itself
	        _tagMap.put(tag, field);

	        // Add to sequence fields, if appropriate.
	        Vector fields = (Vector)_sequenceFields.get(sequence);
	        if (fields != null) {
	            fields.addElement(field);
	        }
	        // Add the field to the SWIFT message
	        message.getFields().addElement(field);
	    }
	    /**
	     * Extracts the format from the template and attaches it to the message
	     * field.
	     * 
	     * @param t_field
	     *            the field definition from the template
	     * @param m_field
	     *            the field generated for the message
	     */
	    protected void setFormat(SwiftFieldElement t_field,
	                             SwiftFieldMessage m_field) {
	        String m_tag = m_field.getTAG();
	        String t_format = null;
	        List t_options = t_field.getSwiftOption();
	        // First we check if there is a <SwiftFieldOption> that matches
	        if (!commonUTIL.isEmpty(t_options)) {
	            Iterator i = t_options.iterator();
	            while (i.hasNext()) {
	                JAXBElement<SwiftFieldOptionElement> t_option = (JAXBElement<SwiftFieldOptionElement>)i.next(); // JAVA6 change: Use JAXBElement<T>
	                SwiftFieldOptionElement option = t_option.getValue();
	                String regexp = ":\\d+"
	                        + (commonUTIL.isEmpty(option.getLetter())
	                                                             ? ""
	                                                             : option.getLetter())
	                        + ":";
	                if (m_tag.matches(regexp)) {
	                    t_format = option.getFormat();
	                    break;
	                }
	            }
	        }
	        // If nothing else worked, let's take the <SwiftField> element's format
	        // attribute
	        if (t_format == null
	                && t_field.getFormat() != null) {
	            t_format = t_field.getFormat();
	        }
	        m_field.setFormat(t_format);
	    }

	    protected String generate(String generator) {
	    
	    	
	    	MessageFormat format = MessageFormatterUtil.getFormatter(generator);
	    	
	     
	        if (format == null) {
	             commonUTIL.display("SwiftGenerator", "generate generator not found ");
	            return null;
	        }

	        try {
	        	DocumentInfo info = format.generate(_message, _trade, _transfer, true, null, null);
	            SwiftMessage msg = info.getSwiftMessage();

	            return msg.getSwiftText();
	        } catch (Exception t) {
	        	 commonUTIL.displayError("SwiftGenerator", 
	                      "Exception encountered during generation; generator="
	                              + generator,
	                      t);
	        }
	        return null;
	    }
	    
	    protected void setTagValue(ValueTag tagValue) {
	        if (_tagValue != null) {
	            _tagValue.setOption(tagValue.getOption());
	            _tagValue.setValue(tagValue.getValue());
	            _tagValue.setLegalEntityId(tagValue.getLegalEntityId());
	            _tagValue.setBicCode(tagValue.getBicCode());
	            _tagValue.setOverrideOptionB(tagValue.getOverrideOptionB());
	        }
	    }
	    /**
	     * This recursive method parses through a given expression and returns the
	     * resulting String expression value. An expression can contain one or more
	     * embedded keywords. For example, this would be a valid expression:
	     * "Reference |TRADE_ID|-|MESSAGE_ID|"
	     * 
	     * which would result in a value like: "Reference 1402-3901"
	     * 
	     * @param expression
	     * @param format
	     * @return String value derived from the given expression and/or format.
	     */
	    protected String parseValue(String expression, String format) throws Exception {

	        if (commonUTIL.isEmpty(expression))
	            return "";

	        beans.KeywordInfo keywordInfo = getKeyword(expression);
	        if (keywordInfo == null
	                || keywordInfo.name.length() == 0) {
	            return parseKeyword(expression,
	                                format,
	                                _message,
	                                _trade,
	                                _sender,
	                                _receiver,
	                                _transferRules,
	                                _transfer,
	                                _dsCon,_producttransferRule);
	        }
	        int idxStart = keywordInfo.start;
	        int idxEnd = keywordInfo.end;
	        if (idxStart < 0)
	            idxStart = 0;
	        if (idxEnd > expression.length())
	            idxEnd = expression.length();

	        String replace = parseKeyword(keywordInfo.name,
	                                      format,
	                                      _message,
	                                      _trade,
	                                      _sender,
	                                      _receiver,
	                                      _transferRules,
	                                      _transfer,
	                                      _dsCon,_producttransferRule);

	        return expression.substring(0, idxStart)
	                + replace
	                + parseValue(expression.substring(idxEnd + 1), format);
	    }
	    
	    
	    public String parseKeyword(String keyword_,
                String format_,
                Message message,
                Trade trade,
                LeContacts sender,
                LeContacts rec,
                Vector transferRules,
                Transfer transfer,
               Connection con,ProductTransferRule productTransferRule) throws Exception {
							String trimKeyword = keyword_.trim();
							Vector functions = new Vector();
							String rem = getKeywordFunctions(trimKeyword, functions);
							String keyword = getKeywordName(rem);
							Vector params = getKeywordParams(rem);

					BaseFormatter applyTo = this;
					Method m = getParseMethod(this, keyword);
					
					String res = null;
					if (m != null
					 && m.getParameterTypes().length == 9) {
					applyTo._currentFunctions = functions;
					applyTo._currentKeyword = keyword;
					applyTo._currentKeywordParams = params;
					Object objs[] = new Object[9];
					objs[0] = message;
					objs[1] = trade;
					objs[2] = sender;
					objs[3] = rec;
					objs[4] = transferRules;
					objs[5] = transfer;
					objs[6] = format_;
					objs[7] = con;
					objs[8] = productTransferRule;
					try {
					 res = (String)m.invoke(applyTo, objs);
					} catch (InvocationTargetException ite) {
					 Throwable target = ite.getTargetException();
					 commonUTIL.displayError("SwiftGenerator", "parseKeyword Keyword ("
					         + keyword + ")" + " Params " + params + " Method ("
					         + m.getName() + ")" + " MessageFormatter "
					         + applyTo.getClass().getName(), ite);
					 throw new Exception(new Exception(target));
					} catch (Exception e) {
						 commonUTIL.displayError("SwiftGenerator", "parseKeyword Keyword ("
						         + keyword + ")" + " Params " + params + " Method ("
						         + m.getName() + ")" + " MessageFormatter "
						         + applyTo.getClass().getName(), e);
					 throw new  Exception(e);
					}
					if (res == null)
					 res = ""; // Defined method but no value (but NO ERROR)
					} else
					res = super.parseKeyword(keyword_,
					                      message,
					                      trade,
					                      sender,
					                      rec,
					                      transferRules,
					                      transfer,
					                      con,
					                      (_iteratorObject == null),_productTransferRules);
					return res;
}

	    
	    
	    /**
	     * Method checks the validity of the defined <code>SwiftField</code> to
	     * ensure that all mandatory attributes were set properly in the XML
	     * template document. If not, a <code>MessageFormatException</code> is
	     * thrown with a description of the missing information.
	     * 
	     * @param field
	     *            <code>SwiftField</code> to be checked for validity
	     * @throws MessageFormatException
	     *             if the field definition is invalid.
	     */
	    protected void validateField(SwiftFieldElement field) throws Exception {

	        if (field == null)
	            throw new Exception("SwiftField is null in XML document.");

	        String name = field.getName();
	        if (name == null
	                || name.equals(EMPTYSTRING))
	            throw new Exception("SwiftField name tag is mandatory.");
	        if (field.getTag() == null)
	            throw new Exception("SwiftField \""
	                    + name + "\" tag must be set.");
	        if (field.getModes() == null
	                || field.getModes().equals(EMPTYSTRING))
	            throw new Exception("SwiftField \""
	                    + name + "\" modes must be set.");
	    }
	private String getSwiftMTName(String templateName, Transfer transfer) {
		// TODO Auto-generated method stub
		 if (templateName.matches("MT54_[0-9][0-9]")) {
	            if (transfer.getDeliveryType().equals(Transfer.DELIVERYAFTERPAYMENT))
	                return "MT54"
	                        + templateName.charAt(6);
	            return "MT54"
	                    + templateName.charAt(5);
	        }

	        return templateName;
	}
	
		
	    

	protected ProductTransferRule  getTransferRule(String productType) {
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
	protected static final String S_ISDA_SUBTYPE = "ISDA_SUBTYPE";
    protected static final String S_ISDA_VERSION = "ISDA_VERSION";
    
    private ProductTransferRule _producttransferRule = null;
    
    /**
     * The modes defined in the XML Swift message definition. This field
     * contains a Vector of Strings.
     */
    private Vector _modes;
    /**
     * The <code>BOMessage</code> to be used to generate SWIFT message.
     */
    private Message _message;
    /**
     * The <code>Trade</code> associated with <code>BOMessage</code>.
     */
    private Trade _trade;
    /**
     * Who is the sender of this SWIFT message?
     */
    private LeContacts _sender;
    /**
     * Who is the recipient of this SWIFT message?
     */
    private LeContacts _receiver;
   
    private Transfer _transfer;
 
    private Connection _dsCon;
    
    private HashMap _tagMap;
    
    private HashMap _sequenceFields;
    /**
     * An internally used data structure during processing to keep a list of
     * those items to check during post-generation processing. This includes all
     * fields with the <code>hasField</code> attribute present that need to be
     * processed.
     */
    private Vector _conditionalFields;

    /**
     *
     */
    private Vector _transferRules;
    private SwiftMessage _swiftMessage;

    private String _dataSourceScheme;

    private boolean _sortCodeUsed = false;

    static public Vector _sortCodes = commonUTIL.string2Vector("FW,AU,CP,IN,RT");

    public String getDataSourceScheme() {
        return _dataSourceScheme;
    }
    
    /**
     * Adds the given <code>SwiftFieldMessage</code> sequence to the
     * <code>SwiftMessage</code>. The actual addition comes simply by adding to
     * the message's <code>Vector</code> of fields. However, we must keep track
     * by mapping the tag back to the sequence as a sequence (and its underlying
     * fields) may need to be removed later on if its <b>HasField</b> and/or
     * <b>WithValue</b> conditions are not met.
     * <p>
     * This is slightly complicated but such an analysis can only be performed
     * after the whole message has been generated. Hence, we need to keep track
     * so we can traverse again after the generation is complete.
     */
    protected void addSequence(SwiftMessage message, SwiftFieldMessage sequence) {
        String tag = sequence.getTAG();
        /**
         * If the sequence has a tag value, we map it back to the field and
         * create an underlying vector to keep track of the fields associated
         * with this sequence.
         */
        if (tag != null) {
            _tagMap.put(tag, sequence);
            Vector v = (Vector)_sequenceFields.get(sequence);
            if (v == null)
                v = new Vector();
            _sequenceFields.put(sequence, v);
        }
        // Add the sequence field to the SWIFT message
        message.getFields().addElement(sequence);
    }
    
    
    public String parseCPTY_RECEIVING_AGENT(Message message,  Trade trade,LeContacts sender,LeContacts rec, Vector transferRules,
            Transfer transfer,
            String format,
            Connection con,ProductTransferRule productTransferRule) {

    			message = getMessage(message);
    			//trade = getTrade(message, con);
    			//transfer = getTransfer(message, con);

    			return getCPTY_RECEIVING_AGENT(message,trade,sender,rec,transferRules,transfer,format,con,productTransferRule);
     }
    
    
    protected String getCPTY_RECEIVING_AGENT(Message message,Trade trade,LeContacts sender,LeContacts rec, Vector transferRules,Transfer transfer,
             String format,
              Connection con,ProductTransferRule productTransferRule) {
	
   /* String sortCode = SwiftUtil.getCptySortCode(SwiftUtil.AGENT,
                                                trade,
                                                transfer,
                                                message,transferRules,
                                                con,productTransferRule);*/
    String sortCode = SwiftUtil.getCptySortCode("AGENT", trade, transfer, message, transferRules, con, productTransferRule);
    boolean useSortCode = true;
    if (sortCode != null
            && sortCode.length() > 2) {
        String sc = sortCode.substring(0, 2);
        if (_sortCodes.contains(sc)) {
            useSortCode = !_sortCodeUsed;
            _sortCodeUsed = true;
        }
    }

   ValueTag tagValue = SwiftUtil.getCptyTagValue(SwiftUtil.AGENT, trade,
                                                  transfer,
                                                  message,
                                                  true,
                                                  useSortCode,
                                                  transferRules,
                                                  con, productTransferRule);
    setTagValue(tagValue);
    return tagValue.getValue(); 
   
}

    
    
    public String parseCPTY_BENEFICIARY(Message message,
            Trade trade,
            LeContacts sender,
            LeContacts rec,
            Vector transferRules,
            Transfer transfer,
            String format,
            Connection con,ProductTransferRule productTransferRule) {
    		String sortCode = SwiftUtil.getCptySortCode(SwiftUtil.BENEFICIARY, trade, transfer,message,transferRules,con,productTransferRule);
				boolean useSortCode = true;
				if (sortCode != null 				&& sortCode.length() > 2) {
					String sc = sortCode.substring(0, 2);
				if (_sortCodes.contains(sc)) {
					useSortCode = !_sortCodeUsed;
					_sortCodeUsed = true;
				}
				}
				
				ValueTag tagValue = SwiftUtil.getCptyTagValue(SwiftUtil.BENEFICIARY,
                        trade,
                        transfer,
                        message,
                        false,
                        useSortCode,transferRules,
                        con,productTransferRule);
					setTagValue(tagValue);
					return tagValue.getValue();

    }
    
    
    
    
    
    class CheckEntry extends Object {
        public SwiftFieldMessage field;
        public String hasField;
        public String withValue;
        public String hasValue;

        public CheckEntry(SwiftFieldMessage field,
                String hasField,
                String withValue,
                String hasValue) {
            this.field = field;
            this.hasField = hasField;
            this.withValue = withValue;
            this.hasValue = hasValue;
        }
    }

    
	 
}
