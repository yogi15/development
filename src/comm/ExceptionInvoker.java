package comm;

import java.io.PrintWriter;
import java.io.Serializable;

public class ExceptionInvoker extends Exception implements Serializable {
	
	ExceptionInvoker _next;
	    Throwable _cause;

	   
	    public ExceptionInvoker(String s) { super(s); }

	   
	    public ExceptionInvoker(ExceptionInvoker ex) { 
		super(ex.getMessage());
		_next = ex;
	    }

	    
	    public ExceptionInvoker(ExceptionInvoker ex, String msg) { 
		super(msg);
		_next = ex;
	    }

	    
	    public ExceptionInvoker(Throwable t) { 
		super(t.getMessage());
		_cause = t;
	    }

	    
	    public ExceptionInvoker(String message, Throwable cause) {
	        super(message, cause);
	    }

	    
	    final public ExceptionInvoker getNext() { return _next;}

	    
	    final public void setNext(ExceptionInvoker ex) {  _next=ex;}

	   
	    public Throwable getRootCause() { return _cause;}

	   
	    public void setRootCause(Throwable t) { _cause = t;}

	    
	    public String getAllMessages() {
		StringBuffer buf = new StringBuffer();
		appendAllMessages(buf);
		return buf.toString();
	    }

	    
	    public void appendAllMessages(StringBuffer buf) {
		// Print my message
		buf.append(getLocalizedMessage());
		// Print my "root cause" message
		if (getRootCause() != null) {
		    buf.append("\nCaused by: ")
			.append(getRootCause().getLocalizedMessage());
		}
		// Recurse to any following CosmosExceptions to print their messages
		if (getNext() != null) {
		    buf.append("\nInner error: ");
		    getNext().appendAllMessages(buf);
		}
	    }

	   
	    public void printStackTrace(PrintWriter writer) {
		// Print my stack trace
		super.printStackTrace(writer);
		// Print my "root cause" stack trace
		if (getRootCause() != null) {
		    writer.println("Caused by: ");
		    getRootCause().printStackTrace(writer);
		}
		// Recurse to any following CosmosExceptions to print their stack traces
		if (getNext() != null) {
		    writer.println("Inner error: ");
		    getNext().printStackTrace(writer);
		}
	    }

}
