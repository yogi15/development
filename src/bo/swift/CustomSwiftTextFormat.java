package bo.swift;

import beans.Message;

public class CustomSwiftTextFormat implements SwiftToTextInterface {

	@Override
	public String getSwiftText(SwiftMessage message, Message boMessage) {
		// TODO Auto-generated method stub
		return message.getSwiftTextMessage();
	}

	@Override
	public boolean isParsingCustomized(String gateway) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void parseSwiftText(SwiftMessage swiftMessage, String swift,
			String gateway) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SwiftFormatContext getSwiftFormatContext(SwiftMessage swift) {
		// TODO Auto-generated method stub
		return null;
	}

}
