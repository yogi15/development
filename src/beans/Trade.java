package beans;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import util.commonUTIL;

public class Trade implements Serializable,Cloneable {
	transient   Hashtable<String, String> attributesData = new Hashtable<String, String>();
	int id;
	int productId;
	int b2bid;
	boolean isB2Bflag = false;
	boolean isFXSwap = false;
	boolean isMirrorTrade = false;
	boolean isPositionBased = true;
	boolean customTransferRule = false;
	Vector<Trade> rountingTrades = new Vector<Trade>();
	Product product = null;
	public void setProduct(Product prod) {
		product = prod;
	}
	
	/**
	 * @return the isPositionBased
	 */
	public boolean isPositionBased() {
		return isPositionBased;
	}


    public void clearAttributes() {
        attributes = "";
        setAttribute("TradeDate",commonUTIL.getCurrentDateTime());
    }


	/**
	 * @param isPositionBased the isPositionBased to set
	 */
	public void setPositionBased(boolean isPositionBased) {
		this.isPositionBased = isPositionBased;
	}





	/**
	 * @return the isMirrorTrade
	 */
	public boolean isMirrorTrade() {
		if(getBookId() == getMirrorBookid())
			return true;
		return false;
	}





	/**
	 * @param isMirrorTrade the isMirrorTrade to set
	 */
	private void setMirrorTrade(boolean isMirrorTrade) {
		this.isMirrorTrade = isMirrorTrade;
	}





	/**
	 * @return the isEconomicChanged
	 */
	public boolean isEconomicChanged() {
		return isEconomicChanged;
	}


	
	

	/**
	 * @param isEconomicChanged the isEconomicChanged to set
	 */
	public void setEconomicChanged(boolean isEconomicChanged) {
		this.isEconomicChanged = isEconomicChanged;
	}
	boolean isEconomicChanged = false;
	
	public boolean isFXSwap() {
		if(getTradedesc1().equalsIgnoreCase("FXSWAP"))
			setFXSwap(true);
		return isFXSwap;
	}



	public void setFXSwap(boolean isFXSwap) {
		this.isFXSwap = isFXSwap;
	}



	public boolean isB2Bflag() {
		return isB2Bflag;
	}

   

	public void setB2Bflag(boolean isB2Bflag) {
		this.isB2Bflag = isB2Bflag;
	}
	int cpID;
	String status = "";
	String type = "BUY";
	String tradeDate = "";
	int brokerID ;
	double TradeAmount= 0.0;
	String effectiveDate = "";
	String delivertyDate = "";
	int bookId;
	int xccySPlitid=0;
	int mirrorBookid =0;
	
	int offsetid =0;
	double nominal;
	double quantity;
	String action = "";
	double price;
	String productType = "";
	String amoritizationData = "";
	Amortization amortization;
	int parentID;
	int mirrorID;
	String autoType;	
	double secondTradePrice = 0.0;
	double yield = 0.0;
	String attributes = "";
	String tradedesc;
	int traderID;
	private String tradedesc1;
	int rollTo =0;
	int rollOverTo =0;

	int rollOverFrom =0;
	int rollBackTo =0;
	int rollBackFrom =0;
	double outstanding = 0.0;
	String fxSwapLeg = "";
	public String getFxSwapLeg() {
		return fxSwapLeg;
	}



	public void setFxSwapLeg(String fxSwapLeg) {
		this.fxSwapLeg = fxSwapLeg;
	}



	public double getOutstanding() {
		return outstanding;
	}



	public void setOutstanding(double outstanding) {
		this.outstanding = outstanding;
	}



	public boolean isParitial() {
		return isParitial;
	}



	public void setParitial(boolean isParitial) {
		this.isParitial = isParitial;
	}
	boolean isParitial = false;
	
	public double getSecondTradePrice() {
		return secondTradePrice;
	}



	public void setSecondTradePrice(double secondTradePrice) {
		this.secondTradePrice = secondTradePrice;
	}



	public int getRollOverTo() {
		return rollOverTo;
	}



	public void setRollOverTo(int rollOverTo) {
		this.rollOverTo = rollOverTo;
	}



	public int getRollOverFrom() {
		return rollOverFrom;
	}



	public void setRollOverFrom(int rollOverFrom) {
		this.rollOverFrom = rollOverFrom;
	}



	public int getRollBackTo() {
		return rollBackTo;
	}



	public void setRollBackTo(int rollBackTo) {
		this.rollBackTo = rollBackTo;
	}



	public int getRollBackFrom() {
		return rollBackFrom;
	}



	public void setRollBackFrom(int rollBackFrom) {
		this.rollBackFrom = rollBackFrom;
	}
	public int getRollTo() {
		return rollTo;
	}



	public void setRollTo(int rollTo) {
		this.rollTo = rollTo;
	}



	public int getRollFrom() {
		return rollFrom;
	}



	public void setRollFrom(int rollFrom) {
		this.rollFrom = rollFrom;
	}
	int rollFrom = 0;
	
	public int getParentID() {
		return parentID;
	}
	
	public int getXccySPlitid() {
		return xccySPlitid;
	}



	public void setXccySPlitid(int xccySPlitid) {
		this.xccySPlitid = xccySPlitid;
	}



	public int getOffsetid() {
		return offsetid;
	}



	public void setOffsetid(int offsetid) {
		this.offsetid = offsetid;
	}
	
	public String getAttributes() {
		
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
		
	}
	
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	public int getMirrorID() {
		return mirrorID;
	}
	public void setMirrorID(int mirrorID) {
		this.mirrorID = mirrorID;
	}
	public String getAutoType() {
		return autoType;
	}
	public void setAutoType(String autoType) {
		this.autoType = autoType;
	}
	
	public Amortization getAmortization() {
		return amortization;
	}
	public void setAmortization(Amortization amortization) {
		this.amortization = amortization;
		
	}

	public String getAmoritizationData() {
		return amoritizationData;
	}
	
	public void setAmoritizationData(String amoritizationData) {
		this.amoritizationData = amoritizationData;
		if (amoritizationData == null)
			return;
		if (amoritizationData != null || (!amoritizationData.isEmpty()))
			setAmoritizationDatatoObject(amoritizationData);
	}

	private void setAmoritizationDatatoObject(String amoritizationData2) {
		// TODO Auto-generated method stub
		
		String atttoken [] = amoritizationData2.trim().split(";"); 
		Amortization amortizationObj = new Amortization();

		for (int i = 0; i < atttoken.length; i++) {
			String att = (String) atttoken[i];

			if (att.contains("=")) {
				String attvalue = att.substring(att.indexOf('=') + 1,
						att.length());
				String attnameName = att.substring(0, att.indexOf('='));
				if (attnameName.equalsIgnoreCase("Type")) {
					amortizationObj.setAmortType(attvalue);
				} else if (attnameName.equalsIgnoreCase("startDate")) {
					amortizationObj.setStartDate(attvalue);
				} else if (attnameName.equalsIgnoreCase("endDate")) {
					amortizationObj.setEndDate(attvalue);
				} else if (attnameName.equalsIgnoreCase("rate")) {
					amortizationObj.setRate(attvalue);
				} else if (attnameName.equalsIgnoreCase("openTerm")) {
					if (attvalue.equalsIgnoreCase("true"))
						amortizationObj.setOpen(true);
				} else if (attnameName.equalsIgnoreCase("freq")) {
					amortizationObj.setFreq(attvalue);
				} else if (attnameName.equalsIgnoreCase("Scheduledvalue")) {
					amortizationObj.setScheduledValue(attvalue);
				}  else if (attnameName.equalsIgnoreCase("amortizingtype")) {
					amortizationObj.setAmortization(attvalue);
				}  else if (attnameName.equalsIgnoreCase("amortizationValue")) {
					amortizationObj.setAmortizingFrequency(attvalue);
				} else if (attnameName.equalsIgnoreCase("compoundingFrequency")) {
					amortizationObj.setAmortizingFrequency(attvalue);
				}

			}
		}
		setAmortization(amortizationObj);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCpID() {
		return cpID;
	}
	public void setCpID(int cpID) {
		this.cpID = cpID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public int getBrokerID() {
		return brokerID;
	}
	public void setBrokerID(int brokerID) {
		this.brokerID = brokerID;
	}
	public double getTradeAmount() {
		return TradeAmount;
	}
	public void setTradeAmount(double tradeAmount) {
		TradeAmount = tradeAmount;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public int getB2bid() {
		return b2bid;
	}



	public void setB2bid(int b2bid) {
		this.b2bid = b2bid;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getDelivertyDate() {
		return delivertyDate;
	}
	public void setDelivertyDate(String delivertyDate) {
		this.delivertyDate = delivertyDate;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getVersion() {
		
		// TODO Auto-generated method stub
		return versionID;
	}
	
	public void setVersionID(int versionID) {
		this.versionID = versionID;
	}
public int getUserID() {
		
		// TODO Auto-generated method stub
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	int userID;
	int versionID;
	String currency = "";
	public String getCurrency() {
		// TODO Auto-generated method stub
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
		// TODO Auto-generated method stub
		
	}
	
	
	public String getValues() {
		String values = "";
	
		try {
		 values = "Id="+getId()+
	                ";Currency="+getCurrency()+
	                ";UserID="+getUserID()+
	                ";version="+getVersion()+
                 ";price="+getPrice()+
                  ";action="+getAction().trim()+
                  ";quantity="+getQuantity()+
                  ";book="+getBookId()+
                  ";broker="+getBrokerID()+
                  ";effectivedate="+getEffectiveDate().trim()+
                  ";delieveryDate="+getDelivertyDate().trim()+
                  ";productID="+getProductId()+
                  ";tradedate="+getTradeDate().trim()+
                  ";Nominal="+getNominal() +
                  ";tradeamount= "+getTradeAmount() +
                   ";type="+getType().trim()+
                   ";FarAmt2="+getYield()+
                   ";status="+getStatus().trim()+
	                  ";ProductName="+getTradedesc().trim()+
	                  ";productSubType="+getTradedesc1().trim()+
	                  ";productType="+getProductType().trim()+
	                  ";CpID="+getCpID()+
	                  ";Amoritization="+getAmoritizationData()+
	                  ";FarRate="+getSecondPrice()+
	                  ";TraderID="+getTraderID()+
			  ";isPositionBased="+isPositionBased()+
			   ";isMirrorTrade="+isMirrorTrade()+
			   ";isEconomicChanged="+isEconomicChanged()+
			    ";isFXSwap="+isFXSwap()+
			    ";isB2Bflag="+isB2Bflag()+
			   ";Outstanding="+ getOutstanding()+
			   ";isParitial="+ isParitial()+
			  ";SecondTradePrice="+ getSecondTradePrice()+
			  ";RollOverTo="+ getRollOverTo()+
			  ";RollOverFrom="+ getRollOverFrom()+
			  ";RollBackTo="+ getRollBackTo()+
			  ";RollBackFrom="+ getRollBackFrom()+
			  ";RollTo="+ getRollTo()+
			  ";RollFrom="+ getRollFrom()+
			  ";ParentID="+ getParentID()+
			  ";XccySPlitid="+ getXccySPlitid()+
			  ";Offsetid="+ getOffsetid()+
			  ";MirrorID="+ getMirrorID()+
			  ";AutoType="+ getAutoType()+
			  ";B2bid="+ getB2bid()+
			  ";Price="+ getPrice()+
			  ";MirrorBookid="+ getMirrorBookid()+
			  ";B2bConfig="+getB2bConfig()+
			 ";AllocatedID="+ getAllocatedID()+
			  ";isCustomRuleApply="+isCustomRuleApply();
		}catch(Exception e) {
			commonUTIL.displayError("Trade Object", "getValues  == " + values, e);
			return values;
		}
		
		return values;
	}
	
	public double getYield() {
		// TODO Auto-generated method stub
		return yield;
	}
	
	
	
	
	
	public void setYield(double yield) {
		this.yield = yield;
	}
	public String getTradedesc() {
		// TODO Auto-generated method stub
		return tradedesc;
	}
	public void setTradedesc(String tradedesc) {
		this.tradedesc = tradedesc;
	}
	public String getTradedesc1() {
		// TODO Auto-generated method stub
		return tradedesc1;
	}
	public void setTradedesc1(String tradedesc1) {
		this.tradedesc1 = tradedesc1;
	}
	
	public int getTraderID() {
		return traderID;
	}
	public void setTraderID(int traderID) {
		this.traderID = traderID;
	}
	public double getNominal() {
		return nominal;
	}
	public void setNominal(double nominal) {
		this.nominal = nominal;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	Vector Fees = null;
	public Vector getFees() {
		return Fees;
	}
	public void setFees(Vector fees) {
		Fees = fees;
	}
	public void setSecondPrice(Double doubleValue) {
		// TODO Auto-generated method stub
		this.secondTradePrice = doubleValue;
		
	}
	public double getSecondPrice() {
		return secondTradePrice;
	}
 
	public void  setRountingTrades(Vector<Trade> rountingTrades) {
		this.rountingTrades = rountingTrades;
	}
	public Vector<Trade>   getRountingTrades() {
		return rountingTrades;
	}
	
	
	public Hashtable<String,String> getAttributeH() {
	
	    Hashtable<String,String> attsh = new Hashtable<String,String>();
		String atts [] = attributes.split(";");
		for(int i=0;i<atts.length;i++) {
			String att = atts[i].substring(0, atts[i].indexOf("="));
			String attV = atts[i].substring(atts[i].indexOf("=")+1, atts[i].length());
			attsh.put(att, attV);
		}
		return attsh;
	}
	
	public void  setAttributes(Hashtable<String,String> attriubes) {
		String atts = "";
		Enumeration<String> enums = attriubes.keys();
		while(enums.hasMoreElements()) {
			String key = enums.nextElement();
			String value = attriubes.get(key);
			atts = atts + key +"="+value+";";
		}
				 
		setAttributes(atts);
	    
		
	}
	
	public void setAttribute(String attributeName, String Values) {
		String att1 = getAttributes();
		att1 = att1 + ";"+attributeName+"="+Values;
		this.attributes = att1;
		// TODO Auto-generated method stub
		
	}
	public int getMirrorBookid() {
		return mirrorBookid;
	}



	public void setMirrorBookid(int mirrorBookid) {
		this.mirrorBookid = mirrorBookid;
	}

	public Trade getSwapLeg() {
		Trade swapLeg = new Trade();
		if(!getTradedesc1().equalsIgnoreCase("FXSWAP"))
			return null;
		swapLeg.setId(getId());
		swapLeg.setDelivertyDate(getEffectiveDate());
		swapLeg.setTradeDate(getTradeDate());
		swapLeg.setTraderID(getTraderID());
		swapLeg.setBookId(getBookId());
		swapLeg.setAction(getAction());
		swapLeg.setBrokerID(getBrokerID());
		if(getType().equalsIgnoreCase("BUY/SELL")) {
			swapLeg.setType("SELL");
		} else {
			swapLeg.setType("BUY");
		}
		swapLeg.setTradedesc(getTradedesc());
		swapLeg.setTradedesc1(getTradedesc1());
		swapLeg.setCurrency(getCurrency());
		swapLeg.setStatus(getStatus());
		swapLeg.setAttributes(getAttributes());
		swapLeg.setQuantity(getTradeAmount());
		swapLeg.setNominal(getYield());
		swapLeg.setCpID(getCpID());
		swapLeg.setProductType(getProductType());
		swapLeg.setProductId(getProductId());
		swapLeg.setRollBackFrom(getRollBackFrom());
		swapLeg.setRollBackTo(getRollBackTo());
		swapLeg.setRollOverFrom(getRollOverFrom());
		swapLeg.setRollOverTo(getRollOverTo());
		swapLeg.setPrice(getSecondPrice());
		swapLeg.setVersionID(getVersion());
		swapLeg.setStatus(getStatus());
		swapLeg.setFxSwapLeg("SWAPLEG");
		return swapLeg;
	}
	public Trade getSwapPrimaryLeg() {
		Trade swapprimaryLeg = new Trade();
		if(!getTradedesc1().equalsIgnoreCase("FXSWAP"))
			return null;
		swapprimaryLeg.setId(getId());
		swapprimaryLeg.setDelivertyDate(getDelivertyDate());
		swapprimaryLeg.setTradeDate(getTradeDate());
		swapprimaryLeg.setTraderID(getTraderID());
		swapprimaryLeg.setBookId(getBookId());
		swapprimaryLeg.setAction(getAction());
		swapprimaryLeg.setBrokerID(getBrokerID());
		if(getType().equalsIgnoreCase("BUY/SELL")) {
			swapprimaryLeg.setType("BUY");
		} else {
			swapprimaryLeg.setType("SELL");
		}
		swapprimaryLeg.setTradedesc(getTradedesc());
		swapprimaryLeg.setTradedesc1(getTradedesc1());
		swapprimaryLeg.setCurrency(getCurrency());
		swapprimaryLeg.setStatus(getStatus());
		swapprimaryLeg.setAttributes(getAttributes());
		swapprimaryLeg.setQuantity(getQuantity());
		swapprimaryLeg.setNominal(getNominal());
		swapprimaryLeg.setCpID(getCpID());
		swapprimaryLeg.setProductType(getProductType());
		swapprimaryLeg.setProductId(getProductId());
		swapprimaryLeg.setRollBackFrom(getRollBackFrom());
		swapprimaryLeg.setRollBackTo(getRollBackTo());
		swapprimaryLeg.setRollOverFrom(getRollOverFrom());
		swapprimaryLeg.setRollOverTo(getRollOverTo());
		swapprimaryLeg.setPrice(getPrice());
		swapprimaryLeg.setVersionID(getVersion());
		swapprimaryLeg.setFxSwapLeg("SWAPPRIMARYLEG");
		return swapprimaryLeg;
	}
	@Override

	public Object clone() throws CloneNotSupportedException {

	return super.clone();

	}



	public Product getProduct() {
		// TODO Auto-generated method stub
	//	getProductId();
		return product;
	}

B2BConfig b2bConfig = null;


// b2bConfig added

	public void setB2bConfig(B2BConfig b2bconfig) {
		// TODO Auto-generated method stub
		this.b2bConfig = b2bconfig;
	}
	public B2BConfig getB2bConfig() {
		return b2bConfig;
	}


int allocatedID = 0;
public void setAllocatedID(int allocatedID) {
	// TODO Auto-generated method stub
	this.allocatedID =allocatedID ;
}

	public int getAllocatedID() {
		// TODO Auto-generated method stub
		return allocatedID;
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
Vector<TransferRule> customtransferRules = null;
	/**
 * @return the customtransferRules
 */
public Vector<TransferRule> getCustomtransferRules() {
	return customtransferRules;
}

/**
 * @param customtransferRules the customtransferRules to set
 */
public void setCustomtransferRules(Vector<TransferRule> customtransferRules) {
	this.customtransferRules = customtransferRules;
}

	public void setCustomRuleApply(boolean b) {
		// TODO Auto-generated method stub
		customTransferRule = b;
		
	}
	public boolean isCustomRuleApply() {
		// TODO Auto-generated method stub
		return customTransferRule  ;
		
	}
	
}
