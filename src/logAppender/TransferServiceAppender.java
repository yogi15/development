package logAppender;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import constants.logConstants;

public class TransferServiceAppender {
	
	static Logger logger = Logger.getLogger(
			"TransferServiceLogger");
	
	public static void printLog(String logType, String msg) {

		switch (logTypes.get(logType)) {

		case 1:
			logger.info(msg);
			return;
		case 2:
			logger.debug(msg);			
			return;		
		case 3:
			logger.error(msg);			
			return;

		}

	}
	public static Hashtable<String, Integer> logTypes = new Hashtable<String, Integer>();
	public static final String ERROR = "ERROR";
	public static final String INFO = "INFO";
	public static final String DEBUG = "DEBUG";
	
static {
		
		logTypes.put(ERROR, 3);
		logTypes.put(INFO, 1);
		logTypes.put(DEBUG, 2);
			
	}


}
