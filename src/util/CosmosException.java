package util;

public class CosmosException extends Exception { 
	
	 Exception ep;

	public CosmosException() {
		
	}
	
	public CosmosException(String message) {
		super(message);
	       
	}
	public CosmosException(Throwable cause) {
		super(cause);
	       
	}  public CosmosException(String message, Throwable cause) {
		super(message, cause);
		 
		
	}
	public CosmosException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
}

	class OtherException extends Exception {
	    OtherException(Exception ex, String exStr) {}
	}

}
