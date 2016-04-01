package beans;

import java.io.Serializable;

import util.ReferenceDataCache;


import beans.Trade;
import beans.Transfer;

public class DefaultPartySDI extends Sdi implements Serializable,Cloneable,Comparable {

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	protected int      _partyId;
    /**
	 * @return the _partyId
	 */
	public int get_partyId() {
		return _partyId;
	}
	/**
	 * @param _partyId the _partyId to set
	 */
	public void set_partyId(int _partyId) {
		this._partyId = _partyId;
	}
	/**
	 * @return the _partyContactType
	 */
	public String get_partyContactType() {
		return _partyContactType;
	}
	/**
	 * @param _partyContactType the _partyContactType to set
	 */
	public void set_partyContactType(String _partyContactType) {
		this._partyContactType = _partyContactType;
		setAgentContacts(_partyContactType);
	}
	/**
	 * @return the _partyCode
	 */
	public String get_partyCode() {
		return _partyCode;
	}
	/**
	 * @param _partyCode the _partyCode to set
	 */
	public void set_partyCode(String _partyCode) {
		this._partyCode = _partyCode;
	}
	/**
	 * @return the _partyAccountName
	 */
	public String get_partyAccountName() {
		return _partyAccountName;
	}
	/**
	 * @param _partyAccountName the _partyAccountName to set
	 */
	public void set_partyAccountName(String _partyAccountName) {
		this._partyAccountName = _partyAccountName;
		setGlName(_partyAccountName);
	}
	/**
	 * @return the _partyLongName
	 */
	public String get_partyLongName() {
		return _partyLongName;
	}
	/**
	 * @param _partyLongName the _partyLongName to set
	 */
	public void set_partyLongName(String _partyLongName) {
		this._partyLongName = _partyLongName;
	}
	/**
	 * @return the _partyRole
	 */
	public String get_partyRole() {
		return _partyRole;
	}
	/**
	 * @param _partyRole the _partyRole to set
	 */
	public void set_partyRole(String _partyRole) {
		this._partyRole = _partyRole;
	}
	/**
	 * @return the _messageToParty
	 */
	public boolean is_messageToParty() {
		return _messageToParty;
	}
	/**
	 * @param _messageToParty the _messageToParty to set
	 */
	public void set_messageToParty(boolean _messageToParty) {
		this._messageToParty = _messageToParty;
	}
	/**
	 * @return the _poId
	 */
	public int get_poId() {
		return _poId;
	}
	/**
	 * @param _poId the _poId to set
	 */
	public void set_poId(int _poId) {
		this._poId = _poId;
	}
	/**
	 * @return the _productType
	 */
	public String get_productType() {
		return _productType;
	}
	/**
	 * @param _productType the _productType to set
	 */
	public void set_productType(String _productType) {
		this._productType = _productType;
	}
	/**
	 * @return the _trade
	 */
	public Trade get_trade() {
		return _trade;
	}
	/**
	 * @param _trade the _trade to set
	 */
	public void set_trade(Trade _trade) {
		this._trade = _trade;
	}
	/**
	 * @return the _transfer
	 */
	public Transfer get_transfer() {
		return _transfer;
	}
	/**
	 * @param _transfer the _transfer to set
	 */
	public void set_transfer(Transfer _transfer) {
		this._transfer = _transfer;
	}
	protected String   _partyContactType;
    protected String   _partyCode;
    protected String   _partyAccountName;
    protected String   _partyLongName;
    protected String   _partyRole;
    protected boolean  _messageToParty;
    transient int      _poId = 0;
    transient String   _productType;
 //   transient JDate    _valDate;
    transient Trade    _trade;
    transient Transfer _transfer;
    
    /**
     * Returns Id of the <code>LegalEntity<code> matching this party,
     * or 0 if it not defined.
     *
     * @return an int, the <code>LegalEntity</code> id for this party.
     */
    final public int getPartyId() {
        return _partyId;
    }
    /**
     * Returns the <code>LegalEntity</code> short name for this party.
     *
     * @return a String, the short name.
     */
    final public String getPartyCode() {
        return _partyCode;
    }
    /**
     * Returns the <code>LegalEntity</code> classification for this party.
     *
     * @return a boolean, the classification.
     */
    final public boolean isFinancial() {
    	if (_partyId == 0) {
    		return false;
    	}
    	return false;
    }
    
    /**
     * Returns the Contact type of the party.
     *
     * @return a String representing the party contact type (Default,...)
     */
    final public String getPartyContactType() {
        return _partyContactType;
    }

    /**
     * Returns the Account name associated with this party, or null if no such
     * information is available.
     *
     * @return String representing the Party's account name, or null if unknown.
     */
    final public String getPartyAccountName() {
        return _partyAccountName;
    }

    /**
     * Returns true if a message must be sent to this party.
     * <p>
     * In some instances multiple parties must be alerted when a specific event
     * occurs, especially when the settlement and delivery instructions for a
     * CounterParty include an agent and one or more intermediaries. This
     * boolean flag determines whether or not a message is sent to the given
     * party.
     *
     * @return true if this Party requires a message sent, false otherwise.
     */
    final public boolean getMessageToParty() {
        return _messageToParty;
    }

    /**
     * Returns the Role of the party (CounterParty, Agent, ...) as defined in
     * <code>LegalEntity</code> class.
     *
     * @return a String representing the role of this party.
     */
    final public String getPartyRole() {
        return _partyRole;
        
    }
    protected LeContacts getLEContact() {
        if (_partyId == 0)
            return null;
        LegalEntity le = ReferenceDataCache.getLegalEntity(getPartyId());
        return getLEContact(le);
    }
    protected LeContacts getLEContact(LegalEntity le) {
        return ReferenceDataCache.getLEContact(getPartyRole(), "", le.getId(), getProductType(), getPartyContactType());
    }

    /**
     * Returns The type of this Party's address code. Valid codes include:
     * SWIFT, EMAIL, FAX, MAIL and TELEX (Defined in
     * com.calypso.tk.refdata.LEContact.)
     *
     * @return a String representing the address type for this Party (i.e.,
     *         SWIFT)
     * @see #getCodeValue()
     * @see com.calypso.tk.refdata
     */
    final public String getCode() {
    	return getMessageType();
    	
    }
    public int getPoId() {
        return _poId;
    }

    public void setProductType(String productType) {
        _productType = productType;
    }

    public String getProductType() {
        return _productType;
    }

    public boolean isUnknown() {
        return false;
    }
    
   
    

    public void setTrade(Trade trade) {
        _trade = trade;
    }
    public void setTransfer(Transfer transfer) {
        _transfer = transfer;
    }
    
}
