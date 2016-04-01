package bo.swift;

import beans.Message;


public interface SwiftToTextInterface {
	 public String getSwiftText(SwiftMessage message,Message boMessage);

		/**
		 * Allow ones to Customize the parsing of the Swift Message, for a given gateway
		 * if this return true, then the parsing will call the parseSwiftText
		 * @param gateway
		 * @return
		 */
		public boolean isParsingCustomized(String gateway);

		/**
		 * Called when isCustomized for the given gateway returns true;
		 * @param swiftMessage
		 * @param swift
		 * @param gateway the gateway
		 */
		public void parseSwiftText(SwiftMessage swiftMessage, String swift, String gateway);

	    /**
	     * Allow ones to Customize the Format check which will be applied on the field of an Incoming Swift.
	     * @see SwiftFormatManager
	     */
	    public SwiftFormatContext getSwiftFormatContext(SwiftMessage swift);

}
