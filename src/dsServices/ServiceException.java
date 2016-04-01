package dsServices;

import comm.ExceptionInvoker;

public class ServiceException extends ExceptionInvoker {

	public ServiceException(ExceptionInvoker ex) {
		super(ex);
		// TODO Auto-generated constructor stub
	}
	
	public ServiceException(String ex) {
		super(ex);
		// TODO Auto-generated constructor stub
	}
	public ServiceException(ExceptionInvoker ex, String msg) { 
		super(ex,msg);
	    }
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
