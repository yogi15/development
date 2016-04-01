package logAppender;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import constants.logConstants;

public class ProductAppender {

	static Logger logger = Logger.getLogger(ProductAppender.class);
	

	public static void printLog(String logType, String msg) {

		switch (logConstants.logTypes.get(logType)) {

		case 1:
			logger.error(msg);
			return;
			
		case 2:
			logger.info(msg);
			return;

		}

	}
	
}
