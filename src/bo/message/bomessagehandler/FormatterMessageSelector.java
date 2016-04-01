package bo.message.bomessagehandler;

import beans.Message;

public interface FormatterMessageSelector {
	
	 public MessageFormat getMessageFormatter(Message message);
}
