package bo.swift;

import beans.Message;

public interface SwiftMessageCustomizer {
	
	 public String getBasicHeaderBlock(SwiftMessage swiftMessage, Message message);
	    public String getApplicationHeaderBlock(SwiftMessage swiftMessage, Message message);
	    public String getUserHeaderBlock(SwiftMessage swiftMessage, Message message);
	    public String getSender(SwiftMessage swiftMessage, Message message);
	    public String getReceiver(SwiftMessage swiftMessage, Message message);
	    /**
	     * Should return a String description for the given tag and value.  Note that if
	     * the customizer does not wish to override a default value, it should return null
	     * since returning any valid String will be construed as a specific override value.
	     *
	     * @param tag   the tag identifer (i.e., ':21:', ':54A:')
	     * @param value the value of the swift field.
	     * @return  a String description for the tag-value pair, or null if no override.
	     */
	    public String getNameFromTag(String tag, String value, String messageType);
	    /**
	     * Should return true if the final block "{5:}" has to be
	     * appended at the swift message and should return false
	     * to exclude it from the swift message.
	     * @return a boolean
	     */
	    public boolean isFinalBlockRequired();

}
