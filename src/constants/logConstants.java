package constants;

import java.util.Hashtable;

public class logConstants {

	public static Hashtable<String, Integer> logTypes = new Hashtable<String, Integer>();
	public static final String ERROR = "ERROR";
	public static final String INFO = "INFO";
	
	public static final String SUCCESSFULLY_SAVED = "Successfully saved with Id and ISIN no: "; 
	
	public static final String VALID_TRADE_MSG = "Trade valid having product ISIN no. = ";
	public static final String BOOK_ERROR_MSG = "Book not created in system. Book Name: ";
	public static final String LE_ERROR_MSG = "Legal Entity not created in system. Legal Entity Name: ";
	public static final String TRADER_ERROR_MSG = "Trader not created in system. Trader name: ";
	public static final String PRODUCT_ERROR_MSG = "Product not created in the system. ISIN no: ";
	public static final String TRADE_WITH_XL_ROW_ID_NOT_SAVED = "In excel uploader file: Trade not saved with row no: ";
	public static final String PRODUCT_WITH_XL_ROW_ID_NOT_SAVED = "In excel uploader file: Product not saved with row no: ";
	public static final String ERROR_WHILE_SAVING = "There was an error while saving trade. Excel row no: ";

	static {
		
		logTypes.put(ERROR, 2);
		logTypes.put(INFO, 1);
		
	}


}
