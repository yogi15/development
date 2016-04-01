package  bo.html.Formatter.Util;


import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;







import util.ClassInstantiateUtil;
import util.commonUTIL;
import util.common.DynUtil;
import dsServices.RemoteBOProcess;
import dsServices.RemoteTrade;

import beans.Trade;
import beans.Transfer;
import beans.LeContacts;
import beans.LegalEntity;
import beans.Message;
import bo.html.Formatter.HTMLFormatter;
import bo.swift.Formatter.BaseFormatter;
import bo.transfer.rule.ProductTransferRule;

/**
 * FormatBuilder provides a facade for the MessageFormatter parser/
 * interpreter.  The idea is that template files for messages can contain 
 * user-directives to include or exclude some data based on properties of 
 * the message, transfer, trade, sender, and receiver.  These directives 
 * are included in <code>&lt;Cosmos&gt;...&lt;/Cosmos&gt; tags which 
 * signals the parser that the commands within should be interpreted.
 * <p>
 * Only one public method is available:  <code>parse</code>, which is called 
 * by the MessageFormatter.  All protected methods are called by either the 
 * lexer or parser to interact with the outside world (i.e., typically to 
 * acquire information about the objects).
 * <p>
 * Execution relies heavily on reflection to find the appropriate 
 * information.  Note that for field information, we must go through the 
 * equivalent "getter" methods instead of querying the fields directly since 
 * they are tagged as <code>protected</code> in their respective class 
 * definitions.  Mostly always, an equivalent public "getter" method is 
 * provided so we use it instead.  Hence, if we try to access Trade.quantity, 
 * a call to the method getQuantity() in the Trade class will be substituted 
 * in.<br>
 * For method access, it's up to the caller to ensure that the right method 
 * name (case-independent) along with the right parameters are provided. Note
 * that very few methods which return a value actually have parameters in the
 * valid objects (BOMessage, BOTransfer, Trade, and LEContact).
 * <p>
 * Any errors encountered in the attempt to get a field or method value will 
 * result in an error message being displayed in the log file and a null 
 * value is returned.  This in turn will most likely trigger a 
 * NullPointerException in the parser, which is caught during comparison
 * (since the only time reflection is called is during interpretation of 
 * conditions in a 'IF' statement) and the condition defaulting to false.  
 * Hence, if directives are written cautiously, faulty code should not affect 
 * the final output of the message format.  Just avoid using the ! (NOT) 
 * operator in conditions and any errors will cause the condition to default
 * to false.
 * 
*/


public class FormatBuilder
	extends Object 
	{
	    private final static String   FORMATTER_PARSER="FormatBuilder";
	    
	    /**
	     * Cache of previously accessed methods.
	     */
	    private static HashMap     _methodMap = new HashMap();
	    /**
	     * used to put together template response document (post-parsing)
	     */
	    private static StringBuffer  _document = new StringBuffer();
	    private static StringBuffer  _line = new StringBuffer();
	    /**
	     * 
	     */
	    private static BaseFormatter  _formatter;
	    private static RemoteTrade _remoteTrade;
	    private static RemoteBOProcess _remoteBOProcess;
	    private static Message _message;
	    private static Trade _trade;
	    private static Transfer _transfer;
	    private static Vector        _transferRules;
	    private static LeContacts _sender;
	    private static LeContacts _receiver;
	    private static ProductTransferRule _productTransferRules;
	    private static int          _stackDepth;
	    /**
	     * flag set to true when a <code>MessageFormatter</code> keyword
	     * is evaluated during the parsing of a conditional statement.
	     */
	    private static boolean      _conditionalEval=false;
	    private static Object       _syncObject = new Object();
	    static ThreadLocal<Stack<StringBuilder>> cosmosBlocksBeingScanned = new ThreadLocal<Stack<StringBuilder>>() {

	        @Override
	        protected Stack<StringBuilder> initialValue() {
	            return new Stack<StringBuilder>();
	        }
	    };
	    /**
	     *  stack to pass collected "&lt;!--cosmos&gt;" input from the scanner to the parser.
	     *  <p>
	     *  This is a stack because the order in which the scanning of "&lt;!--Cosmos&gt;" is completed
	     *  does not necessarily match the order in which they are output. The most simple example is
	     *  a block 
	     *  <pre>
	     *  &lt;!--cosmos&gt;
	     *   include "file2.html";
	     *  &lt;/cosmos--&gt; </pre>
	     *  Due to the marvels of LALR(1) parsing, and how the scanner and parser interact, <em>first</em>
	     *  the scanning of the "&lt;!--cosmos&gt;" block will be completed and <em>then</em> the include
	     *  will be executed.  A second "&lt;!--cosmos&gt;" block in file2 needs to be written before the 
	     *  above block. If several includes like the above are nested, we need a stack to keep the completely 
	     *  scanned blocks until they can be output.
	     *  <p>
	     *  In the long run perhaps we should move all output to the parser. But that's a change that needs extra testing...  
	     */
	    static ThreadLocal<Stack<StringBuilder>> cosmosBlocksCompletelyScanned = new ThreadLocal<Stack<StringBuilder>>() {

	        @Override
	        protected Stack<StringBuilder> initialValue() {
	            return new Stack<StringBuilder>();
	        }
	        
	    };
	    
	    
	    
	    /**
	     * to collect generated document fragments in the scanner.
	     * <p>
	     * "&lt;!--generated&gt;" blocks may be nested, as a result of a "&lt;!--cosmos&gt;" block including a file with another
	     *  "&lt;!--cosmos&gt;" block. But we scan the entire block with all "internal" blocks into a single string.
	     *  For this reason, this is just a ThreadLocal StringBuffer, and not a stack. We use <code>generatedNestingLevel</code>
	     *  to keep track of the nesting level 
	     */
	    static ThreadLocal<StringBuilder> generatedBlockBeingScanned = new ThreadLocal<StringBuilder>() {

	        @Override
	        protected StringBuilder initialValue() {
	            return null;
	        }
	        
	    };
	    
	    /**
	     *  auxiliary integer to keep the nesting level of "&lt;!--generated&gt;" blocks.
	     */
	    static ThreadLocal<Integer> generatedNestingLevel = new ThreadLocal<Integer>() {

	        @Override
	        protected Integer initialValue() {
	            return 0;
	        }
	        
	    };
	    
	    
	    /**
	     * parse through input stream (typically template file)
	     * and interpret directives that are found within
	     * &gt;Cosmos&lt; ... &gt;/Cosmos&lt; tags.  These directives
	     * provide the ability to include subdocuments and/or set
	     * KEYWORDS to default values if certain conditions are met.
	     * <p>
	     * This <em>must</em> be synchronized <em>at least</em> for the reason that it uses 
	     * static variables (e.g. {@link #_line}) during formatting. If we do not
	     * synchronize here, output of different trades will be garbled.
	     * </p> 
	     *
	     * @param  stream  a non-null stream containing template data.
	     * @param  message  message to be formatted.
	     * @param  transfer  transfer related to message (may be null).
	     * @param  trade  trade related to message (may be null).
	     * @param sender  a non-null sender of message.
	     * @param receiver  a non-null receiver of message.
	     * @return String containing template text after first parsing
	     *                phase has completed.
	     */
	    public static synchronized String parse(BaseFormatter formatter,
                InputStream stream,Message message,Transfer transfer,Trade trade,LeContacts sender,LeContacts receiver, Vector transferRules,RemoteTrade remoteTrade) {
	    	_document.setLength(0);
	    	_line.setLength(0);
	    	_formatter = formatter;
	    	_message = message;
	    	_transfer = transfer;
	    	_trade = trade;
	    	_sender = sender;
	    	_receiver = receiver;
	    	_transferRules = transferRules;
	        _remoteTrade = remoteTrade;
	        _stackDepth = 0;
		_conditionalEval = false;
        parse(stream);
    	
    	String retval = _document.toString();
    	    	
    	return retval;
	    	
	    }
	    /**
	     *
	     */
	    protected static void start(String s)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Start: "+s);
	    	s = s.trim();
	    	int length = s.length();
	    	if (s.toUpperCase().endsWith("<!--COSMOS>"))
	    	    s = s.substring(0, length-12);
	    	else if (s.toUpperCase().endsWith("<!--COSMOS:R>"))
	            s = s.substring(0, length-14);
	        else if (s.toUpperCase().endsWith("<COSMOS>"))
	    	    s = s.substring(0, length-9);

		String finalString = _formatter.parse(s,
			                              _message,
			                              _trade,
			                              _sender,
			                              _receiver,
			                              _transferRules,
				                      _transfer,
				                      null,_productTransferRules);    	
	    	_document.append(finalString);
	    }
	    
	    
	   
	    
	    
	    
	    public static void error(String symbol, int line)
	    {
	       commonUTIL.display(FORMATTER_PARSER, "Unable to parse symbol \""+symbol+"\" at or near line "+line+".");
	    }
	    
	    /**
	     * Called by Parser during parsing to include a subdocument during
	     * parsing.
	     * TO DO: if we were to keep track of document names, we could protect
	     * against infinite loops.
	     *
	     */
	    protected static void parseInclude(String s)
	    {
	    	 commonUTIL.display(FORMATTER_PARSER, "Including file: "+s);

	        try {
	            InputStream stream = _formatter.getInputStream(s);    	    
	    	    if (stream != null) {
	    	    	_stackDepth++;
	    	    	parse(stream);
	    	    	_stackDepth--;
	    	    }
	    	}
	    	catch (Exception e) {
	    	    // TO DO: Entry point for request
	    	    // to generate a task exception
	    	    // if an include could not be completed.
	    		 commonUTIL.displayError(FORMATTER_PARSER, "",
	    				 e);
	    	}
	    }
	    
	    
	    /**
	     * for message re-generation:
	     * <p>
	     * Called by Lexer while scanning generated parts of a message document.  Assembles the output generated by <!--Cosmos> code.
	     * Depending on things we will either keep the generated part, or drop it and re-execute the <!--Cosmos> code that generated it.  
	     */
	    protected static void collectGenerated(String s) {
	        generatedBlockBeingScanned.get().append(s);
	    }
	    protected static void startGenerated() {
	        StringBuilder sb = generatedBlockBeingScanned.get();
	        Integer level = generatedNestingLevel.get();
	        
	        if (sb == null && level.equals(0)) {
	            sb = new StringBuilder();
	            generatedBlockBeingScanned.set(sb);
	            generatedNestingLevel.set(level + 1);
	        } else {
	           commonUTIL.display(FORMATTER_PARSER, "nesting level for generated output is "+level+" , buffer is "+sb);
	        }
	    }
	    protected static boolean endGenerated() {
	        StringBuilder sb = generatedBlockBeingScanned.get();
	        Integer level = generatedNestingLevel.get();
	        if (sb != null && level.equals(1)) {
	            sb = null;
	            generatedBlockBeingScanned.set(null);
	        }
	       if (level > 0) {
	           generatedNestingLevel.set(level - 1);
	       } else {
	    	   commonUTIL.display(FORMATTER_PARSER, "nesting level for generated output is "+level);
	       }
	       return level == 1; // true if we leave this mode...
	    }
	    
	    /**
	     * Called by Lexer during parsing.  Line is added to output w/o keyword substitution.
	     */
	    protected static void addOriginalLine(String s) {
	        addLine(null);
	        _document.append(s);
	    }
	    
	    /**
	     * Called by Lexer during parsing.  Assembles the <Cosmos> input in order to copy the to the output
	     * so that a later re-generation will still execute the code.
	     */
	    public static void echoCosmos(String s) {
	        cosmosBlocksBeingScanned.get().peek().append(s);
	    }
	    public static void startCosmosLevel() {
	        StringBuilder sb = new StringBuilder();
	        cosmosBlocksBeingScanned.get().push(sb);
	    }
	    public static void endCosmosLevel() {
	    	 cosmosBlocksCompletelyScanned.get().add( cosmosBlocksBeingScanned.get().pop());
	    }
	    
	    public static void writeCosmosBlock() {
	        Stack<StringBuilder> myStack = cosmosBlocksCompletelyScanned.get();
	        if (myStack.size() > 0) {
	            addOriginalLine("<!--generatedEnd--><!--cosmos:r>"+myStack.peek().toString()+"</cosmos-->");
	            myStack.pop();
	        } else {
	        	 commonUTIL.display(FORMATTER_PARSER, "Internal stack of scanned Cosmos blocks is empty");
	        }
	    }
	    public static void dropCosmosBlock() {
	        Stack<StringBuilder> myStack = cosmosBlocksCompletelyScanned.get();
	        if (myStack.size() > 0) {
	            myStack.pop();
	        } else {
	        	 commonUTIL.display(FORMATTER_PARSER, "Internal stack of scanned Cosmos blocks is empty");
	        }
	    }
	    public static void parse(InputStream stream)
	    {
	    	InputStreamReader fileReader = new InputStreamReader(stream);
	    	
	    	/*BufferedReader reader = 	new BufferedReader(fileReader);
	    	String currentLine = null;
	    	try {
				while ((currentLine = reader.readLine()) != null) {	
			//		System.out.println(currentLine);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} */
		Parser p = new Parser(new Lexer(fileReader));
	 	try {
		    p.parse();
	 		System.out.println(("0000"));
		}
		//catch (EOFException eof) {}
		catch (Exception e) {
		   commonUTIL.displayError(FORMATTER_PARSER, "",e);
		    throw new RuntimeException(e);
		}
	    }
	    public static bo.swift.Formatter.FormatterIterator getIterator(String s)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Getting iterator: "+s);
	    	bo.swift.Formatter.FormatterIterator iter = _formatter.getIterator(s);
	        if (iter != null)
		    iter.init(_message, 
		              _trade, 
		              _sender, 
		              _receiver, 
		              _transfer, 
		              _transferRules, 
		                     _remoteTrade,_remoteBOProcess,_productTransferRules);
		return iter;
	    }
	    
	    /**
	     * Called by Lexer during parsing.  Line is added to output to be sent
	     * back when parsing has completed.
	     */
	    protected static void addLine(String s) {
	        // s == null at end of file
	        if (s == null) {
	            // if the buffer is empty, we are done.
	            if (_line == null || _line.length() == 0) {
	                return;
	            }
	            // else we make it one last line
	            _line.append('\n');
	        } else {
	            _line.append(s);
	            if (s.indexOf('\n') == -1) {
	                return;
	            }
	        }

	        s = _line.toString();
	        _line = new StringBuffer();
	        String finalString = _formatter.parse(s,
	                                              _message,
	                                              _trade,
	                                              _sender,
	                                              _receiver,
	                                              _transferRules,
	                                              _transfer,
	                                              null,_productTransferRules);    	
	        _document.append(finalString);
	       // System.out.println(_document);
	    }   
	    /**
	     * keyword substitution occurs upon newline. Forcing keyword substitution makes
	     * the iterator object available to parse* functions. This is sweet.
	     */
	    protected static void doKeywordSubstitution() {
	        addLine("\n");
	    }

	    /**
	     * Called by Parser during parsing. Map key (typically should be a
	     * String identifier) to a value (typically should be a String also).
	     * i.e., MESSAGE_TYPE = "Unknown Message"
	     */
	    public static void setKeywordValue(Object key, Object value)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Setting keyword value: "+key+" = "+value);
	    	_formatter.setKeywordValue(key.toString(), value.toString());
	    }

	    /**
	     * Called by Parser during parsing if an error has been encountered.
	     * This should provide sufficient information (hopefully) in the log
	     * file for client to determine which directive caused a problem.
	     */
	    
	    public static Object getFunctionCall(String s, Vector args)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Getting Function Call value: "+s);

		String classname = "tk.bo.formatter." + s;
		Class class1 = null;
		try {
		
			class1 = ClassInstantiateUtil.getClass(classname,
  					true);
			FunctionFormat function = (FunctionFormat) class1.newInstance();
			
		    if (function == null) {
		    	commonUTIL.display(FORMATTER_PARSER, "Unable to instantiate Class: "+classname);
		    	return null;
		    }
		    
		    Class c = function.getClass();
		    Method[] methods = c.getDeclaredMethods();
		    Method m = null;
	    	    for (int i=0; i < methods.length; i++) {
	    	    	m = methods[i];
	    	    	if (m.getName().equals("call") &&
	    	    	    m.getParameterTypes() != null && 
	    	    	    m.getParameterTypes().length == 7) {
	    	    	  
	    	    	    Object[] params = new Object[7];
	    	    	    params[0] = _remoteTrade;
	    	    	    params[1] = _message;
	    	    	    params[2] = _transfer;
	    	    	    params[3] = _trade;
	    	    	    params[4] = _sender;
	    	    	    params[5] = _receiver;
	    	    	    params[6] = args;
	    	    	    
	    	    	    commonUTIL.display(FORMATTER_PARSER, "Calling Method "+m.getName()+" on class "+c.toString());
	    	    	    Object val = m.invoke(function, params);
	    	    	    commonUTIL.display(FORMATTER_PARSER, "Return value is: "+val);
	    	    	    return val;
	    	    	}
	    	    }
	    	    
	    	    return null;
	        }
	        catch (Exception e) {
	        	commonUTIL.displayError(FORMATTER_PARSER, "",e);
	            return null;
	        }
	    }
	    /**
	     *
	     * @param s   Name of BOMessage Field whose value is requested
	     * @return  value of BOMessage field if it is found, or null otherwise.
	     */
	    public static Object getMessageField(String s)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Getting message field value: "+s);
	    	if (_message == null) return null;
	    	
	        try {
	            Class c = _message.getClass();
	            Method m = getFieldMethod(c, s);
	            if (m == null)
	                throw new IllegalArgumentException("Message field "+s+" not found.");
	                
	            return m.invoke(_message, DynUtil.EMPTY_ARRAY);
	        }
	        catch (Exception e) {
	        	commonUTIL.displayError(FORMATTER_PARSER, "",e);
	            return null;
	        }
	    }
	    
	    
	    /**
	     *
	     * @param s   Name of BOTransfer Field whose value is requested
	     * @return  value of BOTransfer field if it is found, or null otherwise.
	     */
	    public static Object getTransferField(String s)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Getting transfer field value: "+s);
	    	if (_transfer == null) return null;
	    	
	        try {
	            Class c = _transfer.getClass();
	            Method m = getFieldMethod(c, s);
	            if (m == null)
	                throw new IllegalArgumentException("Transfer field "+s+" not found.");
	                
	            return m.invoke(_transfer, DynUtil.EMPTY_ARRAY);
	        }
	        catch (Exception e) {
	        	commonUTIL.displayError(FORMATTER_PARSER, "",e);
	            return null;
	        }
	    }
	    
	    
	    /**
	     *
	     * @param s   Name of Trade Field whose value is requested
	     * @return  value of Trade field if it is found, or null otherwise.
	     */
	    public static Object getTradeField(String s)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Getting trade field value: "+s);
	    	if (_trade == null) return null;
	    	
	        try {
	            Class c = _trade.getClass();
	            Method m = getFieldMethod(c, s);
	            if (m == null)
	                throw new IllegalArgumentException("Trade field "+s+" not found.");
	                
	            return m.invoke(_trade, DynUtil.EMPTY_ARRAY);
	        }
	        catch (Exception e) {
	        	commonUTIL.displayError(FORMATTER_PARSER, "",e);
	            return null;
	        }
	    }
	    
	    
	    /**
	     *
	     * @param s   Name of LEContact Field whose value is requested
	     * @return  value of LEContact field if it is found, or null otherwise.
	     */
	    public static Object getSenderField(String s)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Getting sender field value: "+s);
	    	if (_sender == null) return null;
	    	
	        try {
	            Class c = _sender.getClass();
	            Method m = getFieldMethod(c, s);
	            if (m == null)
	                throw new IllegalArgumentException("LEContact field "+s+" not found.");
	                
	            return m.invoke(_sender, DynUtil.EMPTY_ARRAY);
	        }
	        catch (Exception e) {
	        	commonUTIL.displayError(FORMATTER_PARSER, "",e);
	            return null;
	        }
	    }

	    /**
	     *
	     * @param s   Name of LEContact Field whose value is requested
	     * @return  value of LEContact field if it is found, or null otherwise.
	     */
	    public static Object getReceiverField(String s)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Getting receiver field value: "+s);
	    	if (_receiver == null) return null;
	    	
	        try {
	            Class c = _receiver.getClass();
	            Method m = getFieldMethod(c, s);
	            if (m == null)
	                throw new IllegalArgumentException("LEContact field "+s+" not found.");
	                
	            return m.invoke(_receiver, DynUtil.EMPTY_ARRAY);
	        }
	        catch (Exception e) {
	        	commonUTIL.displayError(FORMATTER_PARSER, "",e);
	            return null;
	        }
	    }        

	    
	    
	    /**
	     *
	     * @param s   Name of BOMessage Field whose value is requested
	     * @return  value of BOMessage field if it is found, or null otherwise.
	     */
	    public static Object getMessageMethod(String s, Vector args)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Getting message method value: "+s);
	    	if (_message == null) return null;
	    	
	        try {
	            Class c = _message.getClass();
	            Method m = getMethod(c, s, args);
	            if (m == null)
	                throw new IllegalArgumentException("BOMessage method "+s+" not found.");
	            return m.invoke(_message, DynUtil.EMPTY_ARRAY);
	        }
	        catch (Exception e) {
	            commonUTIL.displayError(FORMATTER_PARSER,"", e);
	            return null;
	        }
	    }

	    /**
	     *
	     * @param s   Name of BOTransfer Field whose value is requested
	     * @return  value of BOTransfer field if it is found, or null otherwise.
	     */
	    public static Object getTransferMethod(String s, Vector args)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Getting transfer method value: "+s);
	    	if (_transfer == null) return null;
	    	
	        try {
	            Class c = _transfer.getClass();
	            Method m = getMethod(c, s, args);
	            if (m == null)
	                throw new IllegalArgumentException("BOTransfer method "+s+" not found.");
	                
	            return m.invoke(_transfer, DynUtil.EMPTY_ARRAY);
	        }
	        catch (Exception e) {
	            commonUTIL.displayError(FORMATTER_PARSER, "", e);
	            return null;
	        }
	    }

	    /**
	     *
	     * @param s   Name of Trade Field whose value is requested
	     * @return  value of Trade field if it is found, or null otherwise.
	     */
	    public static Object getTradeMethod(String s, Vector args)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Getting trade method value: "+s);
	    	if (_trade == null) return null;
	    	
	        try {
	            Class c = _trade.getClass();
	            Method m = getMethod(c, s, args);
	            if (m == null)
	                throw new IllegalArgumentException("Trade method "+s+" not found.");
	                
	            Object obj = m.invoke(_trade, DynUtil.EMPTY_ARRAY);
	            return obj;
	        }
	        catch (Exception e) {
	            commonUTIL.displayError(FORMATTER_PARSER, "", e);
	            return null;
	        }
	    }

	    /**
	     *
	     * @param s   Name of LEContact Field whose value is requested
	     * @return  value of LEContact field if it is found, or null otherwise.
	     */
	    public static Object getSenderMethod(String s, Vector args)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Getting sender method value: "+s);
	    	if (_sender == null) return null;
	    	
	        try {
	            Class c = _sender.getClass();
	            Method m = getMethod(c, s, args);
	            if (m == null)
	                throw new IllegalArgumentException("LEContact method "+s+" not found.");
	                
	            return m.invoke(_sender, DynUtil.EMPTY_ARRAY);
	        }
	        catch (Exception e) {
	            commonUTIL.displayError(FORMATTER_PARSER,"", e);
	            return null;
	        }
	    }
	    /**
	     *
	     * @param s   Name of Product Field whose value is requested
	     * @return  value of Product field if it is found, or null otherwise.
	     */
	    public static Object getKeywordValue(String keyword)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Getting keyword value: "+keyword);
	    	if (keyword.equals("STACK_DEPTH"))
	    	    return "" + _stackDepth;
	    	else if (keyword.equals("IS_TOP_LEVEL"))
	    	    return "" + (_stackDepth == 0);
	    	
	    	Object val=null;  
	    	synchronized (_syncObject) {
	    	    _conditionalEval=true;  
	            try {
		        val = _formatter.parseKeyword(keyword,
		                                      _message,
		                                      _trade,
		                                      _sender,
		                                      _receiver,
		                                      _transferRules,
		                                      _transfer,
		                                      null,_productTransferRules);
	            }
	            catch (Exception e) {
	            	commonUTIL.displayError(FORMATTER_PARSER,"", e);
	                val = null;
	            }
	            finally {
	                _conditionalEval=false;
	            }
	        }
	        
	        return val;
	    }
	    /**
	     *
	     * @param s   Name of LEContact Field whose value is requested
	     * @return  value of LEContact field if it is found, or null otherwise.
	     */
	    public static Object getReceiverMethod(String s, Vector args)
	    {
	    	commonUTIL.display(FORMATTER_PARSER, "Getting receiver method value: "+s);
	    	if (_receiver == null) return null;
	    	
	        try {
	            Class c = _receiver.getClass();
	            Method m = getMethod(c, s, args);
	            if (m == null)
	                throw new IllegalArgumentException("LEContact method "+s+" not found.");
	                
	            return m.invoke(_receiver, DynUtil.EMPTY_ARRAY);
	        }
	        catch (Exception e) {
	            commonUTIL.displayError(FORMATTER_PARSER,"", e);
	            return null;
	        }
	    }

	    private static Method getFieldMethod(Class c, String s)
	    {
	    	Method m = null;
	    	String key = c.toString() + "/" + s + "()";
	    	
	    	/*
	    	 * Check the cache
	    	 */
	    	m = (Method) _methodMap.get(key);
	    	if (m != null) return m;
	    	
	    	try {
	    	    Method[] methods = c.getMethods();
	    	    for (int i=0; i < methods.length; i++) {
	    	    	m = methods[i];
	    	    	if (fieldNameMatches(m.getName(), s)) {
	    	            /*
	    	             * Put in cache
	    	             */
	    	    	    _methodMap.put(key, m);
	    	    	    return m;
	    	    	}
	    	    }
	    	    m = null;
	     	}
	        catch (Exception e) {}

	        return m;
	    }
	    private static String split(String s)
	    {
	    	StringBuffer buffer = new StringBuffer();
	    	StringTokenizer tokens = new StringTokenizer(s, "_");
	    	while (tokens.hasMoreTokens()) {
	    	    buffer.append(tokens.nextToken());
	    	}
	    	
	    	return buffer.toString();
	    }
	    private static boolean fieldNameMatches(String methodName, String s)
	    {
	    	String n1 = methodName;
	    	String n2 = "get" + s.trim();
	    	
	    	/**
	    	 * Strip '_' from strings
	    	 */
	    	n1 = split(n1);
	    	n2 = split(n2);
	    	    	
	    	/*
	    	 * Put both to uppercase
	    	 */
	    	n1 = n1.toUpperCase();
	    	n2 = n2.toUpperCase();
	    	
	    	return n1.equals(n2);
	    }

	    private static String getMethodSignature(Vector args)
	    {
	    	StringBuffer buffer = new StringBuffer();
	    	buffer.append("(");
	    	for (int i=0; i < args.size(); i++) {
	    	    if (i > 0) buffer.append (",");
	    	    Class c = args.elementAt(i).getClass();
	    	    buffer.append(c.getName());
	    	}
	    	buffer.append(")");
	    	
	    	return buffer.toString();
	    }
	    private static Method getMethod(Class c, String s, Vector args)
	    {
	    	Method m = null;
	    	String key = c.toString() + "/" + s + getMethodSignature(args);
	    	
	    	/*
	    	 * Check the cache
	    	 */
	    	m = (Method) _methodMap.get(key);
	    	if (m != null) return m;
	    	
	    	try {
	    	    Method[] methods = c.getMethods();
	    	    for (int i=0; i < methods.length; i++) {
	    	    	m = methods[i];
	    	    	if (methodMatches(m, s, args)) {
	    	            /*
	    	             * Put in cache
	    	             */
	    	    	    _methodMap.put(key, m);
	    	    	    return m;
	    	    	}
	    	    }
	    	    m = null;
	     	}
	        catch (Exception e) {}

	        return m;
	    }

	    private static boolean methodMatches(Method m, String s, Vector args)
	    {
	    	String n1 = m.getName().toUpperCase();
	    	String n2 = s.trim().toUpperCase();
	    	
	    	if (n1.equals(n2) == false) return false;
	    	
	    	/**
	    	 * Method names match.  Now we must check that
	    	 * method params match as well.
	    	 */
	    	Class[] paramClasses = m.getParameterTypes();
	    	if (paramClasses.length != args.size()) return false;
	    	
	    	Class c;
	    	for (int i=0; i < paramClasses.length; i++) {
	    	    c = args.elementAt(i).getClass();
	    	    if (c.equals(paramClasses[i]) == false)
	    	        return false;
	    	}
	    	
	    	return true;
	    }
	    
	    /**
	     * returns true is a <code>MessageFormatter</code> keyword is being evaluated
	     * within a conditional statement, false otherwise.
	     * <p>
	     * This permits external code/system to query and determine the context within
	     * which a KEYWORD is being evaluated.  
	     * 
	     *
	     */
	    public static boolean isConditionalEvaluation() {
	    	synchronized (_syncObject) {
	    	    return _conditionalEval;
	        }
	    }
	    public static void main (String[] args) 
	    {
	    	commonUTIL.display("ALL","FormatterParser");
	    	
		String fileName = "com" + File.separator +
		                  "Cosmos" + File.separator +
		                  "templates" + File.separator + 
		                  "tradeticket.html";

		InputStream stream = FormatBuilder.class.getClassLoader().getResourceAsStream(fileName);

	String m = 	FormatBuilder.parse(new HTMLFormatter(),
	                              stream,
	                              new Message(),
	                              new Transfer(),
	                              new Trade(),
	                              new LeContacts(),
	                              new LeContacts(),
	                              new Vector(),
	                             null);
	System.out.println(m);
	   
	    }
}
