package logAppender;

import org.apache.log4j.Logger;

import constants.logConstants;

public class DebugAppender {
static Logger logger = Logger.getLogger("DEBUGLogger");



	
	public static void printLog(String logType, String msg) {

		switch (logConstants.logTypes.get(logType)) {

		case 1:
			logger.info(msg);
			return;
			
		case 2:
			logger.error(msg);
			
			return;

		}

	}
	

}
