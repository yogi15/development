package beans;

import java.io.Serializable;
import java.util.Vector;


import productPricing.DefaultCashFlow;

import util.common.DateU;

public  class TransferRule implements Serializable,Cloneable {
    int bookId =0;
    String fxSwapType;
    
    @Override

	public Object clone() throws CloneNotSupportedException {

	return super.clone();

	}
    
    /**
	 * @return the payerMethodType
	 */
	public String getPayerMethodType() {
		return payerMethodType;
	}
	/**
	 * @param payerMethodType the payerMethodType to set
	 */
	public void setPayerMethodType(String payerMethodType) {
		this.payerMethodType = payerMethodType;
	}
	/**
	 * @return the receiverMethodType
	 */
	public String getReceiverMethodType() {
		return receiverMethodType;
	}
	/**
	 * @param receiverMethodType the receiverMethodType to set
	 */
	public void setReceiverMethodType(String receiverMethodType) {
		this.receiverMethodType = receiverMethodType;
	}

	String payerMethodType;
    String receiverMethodType;
    
	public String getFxSwapType() {
		return fxSwapType;
	}
	public void setFxSwapType(String fxSwapType) {
		this.fxSwapType = fxSwapType;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getFeeId() {
		return feeId;
	}
	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}
	int feeId = 0;
	
	
	 public int get_tradeId() {
		return _tradeId;
	}
	public void set_tradeId(int _tradeId) {
		this._tradeId = _tradeId;
	}
	public int get_productId() {
		return _productId;
	}
	public void set_productId(int _productId) {
		this._productId = _productId;
	}
	public String get_transferType() {
		return _transferType;
	}
	public void set_transferType(String _transferType) {
		this._transferType = _transferType;
	}
	public String get_productType() {
		return _productType;
	}
	public void set_productType(String _productType) {
		this._productType = _productType;
	}
	public String get_transferCurrency() {
		return _transferCurrency;
	}
	public void set_transferCurrency(String _transferCurrency) {
		this._transferCurrency = _transferCurrency;
	}
	public int get_payerLegalEntityId() {
		return _payerLegalEntityId;
	}
	public void set_payerLegalEntityId(int _payerLegalEntityId) {
		this._payerLegalEntityId = _payerLegalEntityId;
	}
	public String get_payerLegalEntityRole() {
		return _payerLegalEntityRole;
	}
	public void set_payerLegalEntityRole(String _payerLegalEntityRole) {
		this._payerLegalEntityRole = _payerLegalEntityRole;
	}
	public int get_payerSDId() {
		return _payerSDId;
	}
	public void set_payerSDId(int _payerSDId) {
		this._payerSDId = _payerSDId;
	}
	public String get_payerSDStatus() {
		return _payerSDStatus;
	}
	public void set_payerSDStatus(String _payerSDStatus) {
		this._payerSDStatus = _payerSDStatus;
	}
	public String get_settlementCurrency() {
		return _settlementCurrency;
	}
	public void set_settlementCurrency(String _settlementCurrency) {
		this._settlementCurrency = _settlementCurrency;
	}
	public String get_deliveryType() {
		return _deliveryType;
	}
	public void set_deliveryType(String _deliveryType) {
		this._deliveryType = _deliveryType;
	}
	public String get_nettingType() {
		return _nettingType;
	}
	public void set_nettingType(String _nettingType) {
		this._nettingType = _nettingType;
	}
	public String get_payReceive() {
		return _payReceive;
	}
	public void set_payReceive(String _payReceive) {
		this._payReceive = _payReceive;
	}
	public double get_percentage() {
		return _percentage;
	}
	public void set_percentage(double _percentage) {
		this._percentage = _percentage;
	}
	public int get_receiverLegalEntityId() {
		return _receiverLegalEntityId;
	}
	public void set_receiverLegalEntityId(int _receiverLegalEntityId) {
		this._receiverLegalEntityId = _receiverLegalEntityId;
	}
	public String get_receiverLegalEntityRole() {
		return _receiverLegalEntityRole;
	}
	public void set_receiverLegalEntityRole(String _receiverLegalEntityRole) {
		this._receiverLegalEntityRole = _receiverLegalEntityRole;
	}
	public int get_receiverSDId() {
		return _receiverSDId;
	}
	public void set_receiverSDId(int _receiverSDId) {
		this._receiverSDId = _receiverSDId;
	}
	public String get_receiverSDStatus() {
		return _receiverSDStatus;
	}
	public void set_receiverSDStatus(String _receiverSDStatus) {
		this._receiverSDStatus = _receiverSDStatus;
	}
	public String get_settlementMethod() {
		return _settlementMethod;
	}
	public void set_settlementMethod(String _settlementMethod) {
		this._settlementMethod = _settlementMethod;
	}
	public int get_securityId() {
		return _securityId;
	}
	public void set_securityId(int _securityId) {
		this._securityId = _securityId;
	}
	public int get_manualSDId() {
		return _manualSDId;
	}
	public void set_manualSDId(int _manualSDId) {
		this._manualSDId = _manualSDId;
	}
	public int get_intSDIVersion() {
		return _intSDIVersion;
	}
	public void set_intSDIVersion(int _intSDIVersion) {
		this._intSDIVersion = _intSDIVersion;
	}
	public int get_extSDIVersion() {
		return _extSDIVersion;
	}
	public void set_extSDIVersion(int _extSDIVersion) {
		this._extSDIVersion = _extSDIVersion;
	}
	public String get_settleDate() {
		return _settleDate;
	}
	public void set_settleDate(String _settleDate) {
		this._settleDate = _settleDate;
	}
	public double get__transferAmount() {
		return __transferAmount;
	}
	public void set__transferAmount(double __transferAmount) {
		this.__transferAmount = __transferAmount;
	}
	public int get__sdiSecurityId() {
		return __sdiSecurityId;
	}
	public void set__sdiSecurityId(int __sdiSecurityId) {
		this.__sdiSecurityId = __sdiSecurityId;
	}
	public int get__tradeCptyId() {
		return __tradeCptyId;
	}
	public void set__tradeCptyId(int __tradeCptyId) {
		this.__tradeCptyId = __tradeCptyId;
	}
	public Vector get__fullRoute() {
		return __fullRoute;
	}
	public void set__fullRoute(Vector __fullRoute) {
		this.__fullRoute = __fullRoute;
	}
	public Sdi get__sMethod() {
		return __sMethod;
	}
	public void set__sMethod(Sdi __sMethod) {
		this.__sMethod = __sMethod;
	}
	public Vector get__rules() {
		return __rules;
	}
	public void set__rules(Vector __rules) {
		this.__rules = __rules;
	}
	public DefaultCashFlow get__set() {
		return __set;
	}
	public void set__set(DefaultCashFlow __set) {
		this.__set = __set;
	}
	public int get_seqNumber() {
		return _seqNumber;
	}
	public void set_seqNumber(int _seqNumber) {
		this._seqNumber = _seqNumber;
	}
	public int get_nettingMethodId() {
		return _nettingMethodId;
	}
	public void set_nettingMethodId(int _nettingMethodId) {
		this._nettingMethodId = _nettingMethodId;
	}
	protected int     _tradeId;
	    protected int     _productId;
	    protected String  _transferType;
	    protected String  _productType;
	    protected String  _transferCurrency;
	    protected int     _payerLegalEntityId;
	    protected String  _payerLegalEntityRole;
	    protected int     _payerSDId;
	    protected String  _payerSDStatus;
	    protected String  _settlementCurrency;
	    protected String  _deliveryType;
	    protected String  _nettingType;
	    protected String  _payReceive;
	    protected double  _percentage;
	    protected int     _receiverLegalEntityId;
	    protected String  _receiverLegalEntityRole;
	    protected int     _receiverSDId;
	    protected String  _receiverSDStatus;
	    protected String  _settlementMethod;
	    protected int     _securityId;
	    protected int     _manualSDId;
	    protected int     _intSDIVersion;
	    protected int     _extSDIVersion;
	    /**
		 * @return the _receiverAgentID
		 */
		public int get_receiverAgentID() {
			return _receiverAgentID;
		}
		/**
		 * @param _receiverAgentID the _receiverAgentID to set
		 */
		public void set_receiverAgentID(int _receiverAgentID) {
			this._receiverAgentID = _receiverAgentID;
		}
		/**S
		 * @return the _payerAgentID
		 */
		public int get_payerAgentID() {
			return _payerAgentID;
		}
		/**
		 * @param _payerAgentID the _payerAgentID to set
		 */
		public void set_payerAgentID(int _payerAgentID) {
			this._payerAgentID = _payerAgentID;
		}
		protected int     _receiverAgentID;
	    protected int     _payerAgentID;
	    protected String   _settleDate; //NEVER PUT IT IN THE EQUALS METHOD
	    transient protected double __transferAmount;
	    transient protected int __sdiSecurityId;    
	    transient protected int __tradeCptyId;    
	    transient protected Vector  __fullRoute;
	    transient protected Sdi  __sMethod;
	    transient protected Vector __rules;
	    transient protected DefaultCashFlow  __set;
	    protected int       _seqNumber = 0;
	    protected int       _nettingMethodId;


	    /**
		 * SDI status keyword indicating that the Settlement Instructions
		 * have to be assigned.
		 */
		//static final public String TBA = "TBA";
		public String getProcessingOrgSDStatus() {
			// TODO Auto-generated method stub
			return "Default"; // this has understand properly how swift message will get forward if SDI is assigned. 
		}
		/**
		 * SDI status keyword indicating that the Settlement Instructions
		 * have to be assigned.
		 */
		//static final public String TBA = "TBA";
		public Object getCounterPartySDStatus() {
			// TODO Auto-generated method stub
			return "Default"; // this has understand properly how swift message will get forward if SDI is assigned. 
		}

}
