package beans;

import java.io.Serializable;
import java.util.Hashtable;

import util.ReferenceDataCache;
import util.commonUTIL;

public class Sdi extends BOObject implements Serializable,Cloneable {
	
	int agentId;
	int cpId;
	int accountID;
	String messageType;
	int id;
	int preferred = 0;
	int interMid1 =0;
	transient   Hashtable<String, String> attributesData = new Hashtable<String, String>();
	
	public void setAttribute(String attributeName, String Values) {
		String att1 = getAttributes();
		att1 = att1 + ";"+attributeName+"="+Values;
		this.attributes = att1;
		// TODO Auto-generated method stub
		
	}
	public String getAttributeValue(String attributesDataName) {
		// TODO Auto-generated method stub
		String attr1 = getAttributes();
		String attributes [] = attr1.split(";");
		String value = "";
		for(int i=0;i<attributes.length;i++) {
		String attribute = 	attributes[i];
		if(attribute.contains(attributesDataName)) {
			value = attribute.substring(attribute.indexOf("=")+1, attribute.length());
			if(!commonUTIL.isEmpty(value))
				 break;
		}
		}
		return value;
		
	}
	/**
	 * @return the interMid1
	 */
	public int getInterMid1() {
		return interMid1;
	}
	/**
	 * @param interMid1 the interMid1 to set
	 */
	public void setInterMid1(int interMid1) {
		this.interMid1 = interMid1;
	}
	/**
	 * @return the interMid2
	 */
	public int getInterMid2() {
		return interMid2;
	}
	/**
	 * @param interMid2 the interMid2 to set
	 */
	public void setInterMid2(int interMid2) {
		this.interMid2 = interMid2;
	}
	/**
	 * @return the interMid1account
	 */
	public int getInterMid1account() {
		return interMid1account;
	}
	/**
	 * @param interMid1account the interMid1account to set
	 */
	public void setInterMid1account(int interMid1account) {
		this.interMid1account = interMid1account;
	}
	/**
	 * @return the interMid2account
	 */
	public int getInterMid2account() {
		return interMid2account;
	}
	/**
	 * @param interMid2account the interMid2account to set
	 */
	public void setInterMid2account(int interMid2account) {
		this.interMid2account = interMid2account;
	}
	/**
	 * @return the interMid1Contact
	 */
	public String getInterMid1Contact() {
		return interMid1Contact;
	}
	/**
	 * @param interMid1Contact the interMid1Contact to set
	 */
	public void setInterMid1Contact(String interMid1Contact) {
		this.interMid1Contact = interMid1Contact;
	}
	/**
	 * @return the interMid2Contact
	 */
	public String getInterMid2Contact() {
		return interMid2Contact;
	}
	/**
	 * @param interMid2Contact the interMid2Contact to set
	 */
	public void setInterMid2Contact(String interMid2Contact) {
		this.interMid2Contact = interMid2Contact;
	}
	/**
	 * @return the interMid1glName
	 */
	public String getInterMid1glName() {
		return interMid1glName;
	}
	/**
	 * @param interMid1glName the interMid1glName to set
	 */
	public void setInterMid1glName(String interMid1glName) {
		this.interMid1glName = interMid1glName;
	}
	/**
	 * @return the interMid2glName
	 */
	public String getInterMid2glName() {
		return interMid2glName;
	}
	/**
	 * @param interMid2glName the interMid2glName to set
	 */
	public void setInterMid2glName(String interMid2glName) {
		this.interMid2glName = interMid2glName;
	}
	int interMid2 =0;
	int interMid1account =0;
	int interMid2account =0;
	String interMid1Contact = "";
	String interMid2Contact ="";
	String glName ="";
	/**
	 * @return the glName
	 */
	public String getGlName() {
		return glName;
	}
	/**
	 * @param glName the glName to set
	 */
	public void setGlName(String glName) {
		this.glName = glName;
	}
	String interMid1glName = "";
	String interMid2glName = "";
	
	
	
			
	/**
	 * @return the preferred
	 */
	public int getPreferred() {
		return preferred;
	}
	/**
	 * @param preferred the preferred to set
	 */
	public void setPreferred(int preferred) {
		this.preferred = preferred;
	}
	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	int priority =0;
	
	
	
	String sdiformat;
	String attributes;
	String leContacts;
	String payrec;
	
	public String getPoContact() {
		return poContact;
	}
	public void setPoContact(String poContact) {
		this.poContact = poContact;
	}
	String cash;
	String currency;
	String products;
	int poId;
	private String key;
	String role;
	SwiftSdi swiftSDI = null;
	int agentSDIid = 0;
	String poContact = "";
	
	public int getAgentSDI(int agentID) {
		return agentSDIid;
	}
	public void setAgentSDI(int agentSDIid) {
		this.agentSDIid = agentSDIid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLeContacts() {
		return leContacts;
	}
	public void setLeContacts(String leContacts) {
		this.leContacts = leContacts;
	}
	public String getAgentContacts() {
		return agentContacts;
	}
	public void setAgentContacts(String agentContacts) {
		this.agentContacts = agentContacts;
	}
	String agentContacts;
	
	public String getSdiformat() {
		return sdiformat;
	}
	public void setSdiformat(String sdiformat) {
		this.sdiformat = sdiformat;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getPayrec() {
		return payrec;
	}
	public void setPayrec(String payrec) {
		this.payrec = payrec;
	}
	public String getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int sdiid) {
		this.id = sdiid;
	}
	
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public int getCpId() {
		return cpId;
	}
	public void setCpId(int cpId) {
		this.cpId = cpId;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public void setsdiformat(String sdiformat) {
		// TODO Auto-generated method stub
		this.sdiformat = sdiformat;
		
	}
	public String getsdiformat() {
	
		// TODO Auto-generated method stub
		return sdiformat;
		
	}
	public int getPoId() {
		return poId;
	}
	public void setPoId(int poId) {
		this.poId = poId;
	}
	
	public void setkey(String keys) {
		// TODO Auto-generated method stub
		this.key = keys;
	}
	public String getkey() {
		// TODO Auto-generated method stub
		return key;
	}
	public String getAttribute(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	Sdi agentSdi = null;
	
	public void setAgentSdi(Sdi agenSdi) {
		// TODO Auto-generated method stub
		this.agentSdi = agenSdi;
	}
	public DefaultPartySDI getAgentSdi() {
		// TODO Auto-generated method stub
		return getPartySdi();
	}
	 /**
     * Returns The type of this Party's address code.  Valid codes include:
     * SWIFT, EMAIL, FAX, MAIL and TELEX (Defined in com.calypso.tk.refdata.LEContact.)
     **/
	public String getCode() {
		// TODO Auto-generated method stub
		return getMessageType(); // used for message formating. 
	}
	String valDate;
	public void setValDate(String settleDate) {
		// TODO Auto-generated method stub
		valDate = settleDate;
	}
	public String getValDate() {
		// TODO Auto-generated method stub
		return valDate;
	}
	
	/**
     * Returns Swift BIC code if this party uses SWIFT address code, E-mail address
     * if address code is of type EMAIL, and so on.
     **/
	public String getCodeValue() {
		// TODO Auto-generated method stub
		String codeV =  ReferenceDataCache.getSwiftValue(getCpId(),getProducts(),getRole(),getMessageType(),getAgentContacts());
		return codeV ;
	}
	public boolean isUnknown() {
		// TODO Auto-generated method stub
		return false;
	}
	
	 /**
     * Returns Swift Long Address of this Party (up to 4 lines of 35
     * characters). This is a SWIFT-compliant String identifying this party in a
     * SWIFT message. The Swift Long address is used to populate the party
     * fields with option D.
     * <p>
     * Given a contact type, a role, a legal entity id return the "Full Name of
     * the Legal entity", "city" The String returned should look like this: Full
     * Name (\n) City (\n)
     *
     * @see com.calypso.tk.bo.swift.SwiftUtil
     * @return a String, the Long Address formatted using the FullName and the
     *         City. If the City is not defined or no contact is found, the code
     *         UNKNOWN is returned.
     */
	public final String getPartyLongName() {
		// TODO Auto-generated method stub
		LeContacts contact = ReferenceDataCache.getPartyContact(getRole(),getLeContacts(),getMessageType(),getProducts(),getCpId());
		if(contact == null)
			return null;
		LegalEntity le = ReferenceDataCache.getLegalEntity(getCpId());
		Country country = ReferenceDataCache.getCountry(contact.getCountry());
		 String[] addressFormat = (country == null) ? null : null;
         String fullAddress = buildMailingAddress(le.getName(), contact, addressFormat);
         return bo.swift.SwiftUtil.formatLongString(fullAddress, Country.ADDRESS_MAX_LINE_NUMBER);
		
	}
	
	String buildMailingAddress(String leName, LeContacts lec, String[] addressFormat) {
		 leName = bo.swift.SwiftUtil.formatLongString(leName.trim(), 4).trim();
	        StringBuffer buffer = new StringBuffer(leName);
	        String mailingAddress = lec.getMailingAddress();
	        boolean mailingAddressB = !commonUTIL.isEmpty(mailingAddress);
	        boolean zipCodeB = !commonUTIL.isEmpty(lec.getZipCode());
	        boolean cityNameB = !commonUTIL.isEmpty(lec.getCity());
	        boolean stateB = !commonUTIL.isEmpty(lec.getState());
	        boolean countryB = !commonUTIL.isEmpty(lec.getCountry());
	        boolean zipFirst = false;
	        if (addressFormat != null) {
	           /* String addressItems[] = new String[addressFormat.length];
	            int nullElementCounter = 0;
	            int nullElementsIndex[] = new int[addressFormat.length];
	            // We use the Address Format supplied by the Country
	            for (int i = 0; i < addressFormat.length; i++) {
	                String element = addressFormat[i];
	                if (Country.isAddressSeparator(element))
	                    addressItems[i] = element;  
	                else if (element.equals(Country.MAIL_CODE) && mailingAddressB)
	                    addressItems[i] =  mailingAddress.trim();
	                else if (element.equals(Country.STATE_CODE) && stateB)
	                    addressItems[i] = lec.getState().trim();
	                else if (element.equals(Country.CITY_CODE) && cityNameB)
	                    addressItems[i] = lec.getCityName().trim();
	                else if (element.equals(Country.ZIPCODE_CODE))
	                    addressItems[i] = lec.getZipCode().trim();
	                else if (element.equals(Country.COUNTRY_CODE))
	                    addressItems[i] = lec.getCountry().trim();
	                else
	                    addressItems[i] = lec.getAddressCode(element);
	                if (addressItems[i] == null) {
	                    nullElementsIndex[nullElementCounter] = i;
	                    nullElementCounter++;
	                } 
	            }
	            // We filter the separators of null elements out
	            // and new lines that surround a null item
	            for (int j = nullElementCounter - 1; j > -1; j--) {
	                int index = nullElementsIndex[j];
	                boolean removeNewLineB = false;
	                if (index + 1 >= addressItems.length || SwiftMessage.END_OF_LINE.equals(addressItems[index + 1])) {
	                    removeNewLineB = true;
	                }
	                for (int i = index - 1; i > -1; i--) {
	                    String item = addressItems[i];
	                    // We keep end of line
	                    if ((removeNewLineB && Country.isAddressSeparator(item)) ||
	                        (Country.isAddressSeparator(item) && !removeNewLineB && !SwiftMessage.END_OF_LINE.equals(item))) {
	                        addressItems[i] = null;
	                    }
	                    else if (!SwiftMessage.END_OF_LINE.equals(item)) {
	                        // This is an item
	                        break;
	                    }
	                }
	            }
	            if (addressItems.length > 0)
	                buffer.append(SwiftMessage.END_OF_LINE);
	            for (int i = 0; i < addressItems.length; i++) {
	                String item = addressItems[i];
	                if (item != null)
	                    buffer.append(item);
	            }*/
	        }
	        else {
	            // We use the default format
	            if (mailingAddressB) {
	                buffer
	                    .append(bo.swift.SwiftMessage.ENDOFLINE)
	                    .append(lec.getMailingAddress().trim());
	            }
	            if (zipCodeB || cityNameB || stateB)
	                buffer.append(bo.swift.SwiftMessage.ENDOFLINE);
	            if (zipFirst && zipCodeB) {
	                buffer
	                    .append(lec.getZipCode().trim())
	                    .append(' ');
	            }
	            if(cityNameB) {
	                buffer
	                    .append(lec.getCity().trim())
	                    .append(' ');
	            }
	            if (stateB) {
	                buffer
	                    .append(lec.getState().trim())
	                    .append(' ');
	            }
	            if(!zipFirst && zipCodeB) {
	                buffer
	                    .append(lec.getZipCode().trim())
	                    .append(' ');
	            }
	            if (countryB && buffer.length() > leName.length()) {
	                buffer
	                    .append(' ')
	                    .append(lec.getCountry().trim());
	            }
	        }
	        return buffer.toString().trim();
	        
		
	}
	public DefaultPartySDI getPartySdi() {
		DefaultPartySDI  agentSdi = new DefaultPartySDI();
		agentSdi.set_partyId(getAgentId());
		agentSdi.setCpId(getAgentId());
		agentSdi.set_messageToParty(isMessageToAgent());
		agentSdi.set_partyAccountName(getGlName());
		agentSdi.set_productType(getProducts());
		agentSdi.set_partyContactType(getAgentContacts());
		agentSdi.set_partyRole("Agent");
		agentSdi.setRole("Agent");
		agentSdi.set_partyCode("SWIFT");
		agentSdi.setMessageType(getMessageType());
		
		
	return agentSdi;
		
		
	}
	public boolean isMessageToAgent() {
		return true;
	}
	public void setTrade(Trade trade) {
		// TODO Auto-generated method stub
		
	}
	public void setTransfer(Transfer transfer) {
		// TODO Auto-generated method stub
		
	}
	public String getPartyCode() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean getDirectRelationship() {
		// TODO Auto-generated method stub
		return false;
	}
	public Sdi getBeneficiary() {
		// TODO Auto-generated method stub
		if(getRole().equalsIgnoreCase("CounterParty")) {
			return this;
		}
		return null;
	}
	public Sdi getIntermediary() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getPartyAccountName() {
		// TODO Auto-generated method stub
		return null;
	}
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
  //  public boolean getMessageToParty(); // needs to understand how to set this flag.
	public boolean getMessageToPary() {
		return false;
	}
	public boolean getMessageToParty() {
		// TODO Auto-generated method stub
		return false;
	}
	public Sdi getIntermediary2() {
		// TODO Auto-generated method stub
		return null;
	}
	 @Override

		public Object clone() throws CloneNotSupportedException {

		return super.clone();

		}
	
	
}
