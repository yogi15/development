package beans;

public class SwiftSdi {
	Sdi sdi;
	int partyID;
	/**
     * Returns Id of the <code>LegalEntity<code> matching this party,
     * or 0 if it not defined.
     *
     * @return an int, the <code>LegalEntity</code> id for this party.
     */
	public int getPartyID() {
		return partyID;
	}

	public void setPartyID(int partyID) {
		this.partyID = partyID;
	}
	/**
     * Returns the <code>LegalEntity</code> short name for this party.
     *
     * @return a String, the short name.
     */
	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
	 /**
     * Returns the Role of the party (CounterParty, Agent, ...) as defined
     * in <code>LegalEntity</code> class.
     *
     * @return a String representing the role of this party.
     */
	public String getPartyRole() {
		return partyRole;
	}

	public void setPartyRole(String partyRole) {
		this.partyRole = partyRole;
	}
	 /**
     * Returns the Contact type of the party.
     *
     * @return a String representing the party contact type (Default,...)
     */
	public String getPartyContactType() {
		return partyContactType;
	}
	
	
	public void setPartyContactType(String partyContactType) {
		this.partyContactType = partyContactType;
	}
	 /**
     * Returns the Account name associated with this party, or null if
     * no such information is available.
     *
     * @return String representing the Party's account name, or null if unknown.
     */
	public String getPartyAccountName() {
		return partyAccountName;
	}

	public void setPartyAccountName(String partyAccountName) {
		this.partyAccountName = partyAccountName;
	}
	 /**
     * Returns Swift Long Address of this Party (up to 4 lines of 35 characters).
     * This is a SWIFT-compliant String identifying this party in
     * a SWIFT message.  The Swift Long address is used to populate the
     * party fields with option D.
     * <p>
     * Given a contact type, a role, a legal entity id
     * return the "Full Name of the Legal entity", "city"
     * The String returned should look like this:
     * Full Name (\n)
     * City (\n)
     *
     * @see com.calypso.tk.bo.swift.SwiftUtil
     * @return a String, the Long Address formatted using
     * the FullName and the City. If the City is not defined
     * or no contact is found, the code UNKNOWN is returned.
     */
	public String getPartyLongName() {
		return partyLongName;
	}

	public void setPartyLongName(String partyLongName) {
		this.partyLongName = partyLongName;
	}
	/**
     * Returns The type of this Party's address code.  Valid codes include:
     * SWIFT, EMAIL, FAX, MAIL and TELEX (Defined in com.calypso.tk.refdata.LEContact.)
     *
     * @return a String representing the address type for this Party (i.e., SWIFT)
     * @see #getCodeValue()
     * @see com.calypso.tk.refdata
     */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	 /**
     * Returns Swift BIC code if this party uses SWIFT address code, E-mail address
     * if address code is of type EMAIL, and so on.
     *
     * @return a String value associated with this Party's address type.
     * @see #getCode()
     */
	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	

	String partyCode;
	String partyRole;
	String partyContactType;
	String partyAccountName;
	String partyLongName;
	String code;
	String codeValue;
	boolean messageToParty;
	 /**
     * Returns true if a message must be sent to this party.
     * <p>
     * In some instances multiple parties must be alerted when a specific event occurs,
     * especially when the settlement and delivery instructions for a CounterParty include
     * an agent and one or more intermediaries.  This boolean flag determines whether or
     * not a message is sent to the given party.
     *
     * @return  true if this Party requires a message sent, false otherwise.
     */
	public boolean isMessageToParty() {
		return messageToParty;
	}

	public void setMessageToParty(boolean messageToParty) {
		this.messageToParty = messageToParty;
	}

	public Sdi getSdi() {
		return sdi;
	}

	public void setSdi(Sdi sdi) {
		this.sdi = sdi;
	}
	

}
