package bo.html;

import beans.Message;
import beans.Trade;
import bo.html.Formatter.HTMLFormatter;
import bo.message.bomessagehandler.MessageFormat;



/**
 * Base class for choosing the appropriate MessageFormatter, 
 * given a BOMessage.  
 * <p> 
 * Name your class by adding the suffix "MFSelector" to 
 * the name of the MessageType. For example, a selector for CONFIRM 
 * would be called "ConfirmMFSelector". Place your class in 
 * the tk.bo package.
 * */
public interface MSFSelector {
	/**
    *
    * @param message the Message for whose template you wish to find
    * @return the MessageFormatter instance.
    */
	 public HTMLFormatter getMessageFormatter(Message message,Trade trade);

}
