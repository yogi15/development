package beans;

import java.io.Serializable;

public class TradeCustomXferRule implements Serializable {
	 
	  int  TRADE_ID  = 0;
	 /**
	 * @return the tRADE_ID
	 */
	public int getTradeID() {
		return TRADE_ID;
	}
	/**
	 * @param tRADE_ID the tRADE_ID to set
	 */
	public void setTradeID(int tRADE_ID) {
		TRADE_ID = tRADE_ID;
	}
	/**
	 * @return the pRODUCT_ID
	 */
	public int getProductID() {
		return PRODUCT_ID;
	}
	/**
	 * @param pRODUCT_ID the pRODUCT_ID to set
	 */
	public void setProductID(int pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}
	/**
	 * @return the tRANSFER_TYPE
	 */
	public String getTransferType() {
		return TRANSFER_TYPE;
	}
	/**
	 * @param tRANSFER_TYPE the tRANSFER_TYPE to set
	 */
	public void setTransferType(String tRANSFER_TYPE) {
		TRANSFER_TYPE = tRANSFER_TYPE;
	}
	/**
	 * @return the pRODUCT_TYPE
	 */
	public String getProductType() {
		return PRODUCT_TYPE;
	}
	/**
	 * @param pRODUCT_TYPE the pRODUCT_TYPE to set
	 */
	public void setProductType(String pRODUCT_TYPE) {
		PRODUCT_TYPE = pRODUCT_TYPE;
	}
	/**
	 * @return the tRANSFER_CCY
	 */
	public String getTransferCCY() {
		return TRANSFER_CCY;
	}
	/**
	 * @param tRANSFER_CCY the tRANSFER_CCY to set
	 */
	public void setTransferCCY(String tRANSFER_CCY) {
		TRANSFER_CCY = tRANSFER_CCY;
	}
	/**
	 * @return the pAY_RECEIVE
	 */
	public String getPayReceive() {
		return PAY_RECEIVE;
	}
	/**
	 * @param pAY_RECEIVE the pAY_RECEIVE to set
	 */
	public void setPayReceive(String pAY_RECEIVE) {
		PAY_RECEIVE = pAY_RECEIVE;
	}
	/**
	 * @return the sETTLEMENT_METHOD
	 */
	public String getSettlementMethod() {
		return SETTLEMENT_METHOD;
	}
	/**
	 * @param sETTLEMENT_METHOD the sETTLEMENT_METHOD to set
	 */
	public void setSettlementMethod(String sETTLEMENT_METHOD) {
		SETTLEMENT_METHOD = sETTLEMENT_METHOD;
	}
	/**
	 * @return the pAYER_ID
	 */
	public int getPayerID() {
		return PAYER_ID;
	}
	/**
	 * @param pAYER_ID the pAYER_ID to set
	 */
	public void setPayerID(int pAYER_ID) {
		PAYER_ID = pAYER_ID;
	}
	/**
	 * @return the pAYER_ROLE
	 */
	public String getPayerRole() {
		return PAYER_ROLE;
	}
	/**
	 * @param pAYER_ROLE the pAYER_ROLE to set
	 */
	public void setPayerRole(String pAYER_ROLE) {
		PAYER_ROLE = pAYER_ROLE;
	}
	/**
	 * @return the pAYER_SDID
	 */
	public int getPayerSID() {
		return PAYER_SDID;
	}
	/**
	 * @param pAYER_SDID the pAYER_SDID to set
	 */
	public void setPayerSID(int pAYER_SDID) {
		PAYER_SDID = pAYER_SDID;
	}
	/**
	 * @return the rECEIVER_ID
	 */
	public int getReceiverID() {
		return RECEIVER_ID;
	}
	/**
	 * @param rECEIVER_ID the rECEIVER_ID to set
	 */
	public void setReceiverID(int rECEIVER_ID) {
		RECEIVER_ID = rECEIVER_ID;
	}
	/**
	 * @return the rECEIVER_ROLE
	 */
	public String getReceiverRole() {
		return RECEIVER_ROLE;
	}
	/**
	 * @param rECEIVER_ROLE the rECEIVER_ROLE to set
	 */
	public void setReceiverRole(String rECEIVER_ROLE) {
		RECEIVER_ROLE = rECEIVER_ROLE;
	}
	/**
	 * @return the rECEIVER_SDID
	 */
	public int getReceiverSDID() {
		return RECEIVER_SDID;
	}
	/**
	 * @param rECEIVER_SDID the rECEIVER_SDID to set
	 */
	public void setReceiverSDID(int rECEIVER_SDID) {
		RECEIVER_SDID = rECEIVER_SDID;
	}
	/**
	 * @return the sETTLE_CCY
	 */
	public String getSettleCCY() {
		return SETTLE_CCY;
	}
	/**
	 * @param sETTLE_CCY the sETTLE_CCY to set
	 */
	public void setSettleCCY(String sETTLE_CCY) {
		SETTLE_CCY = sETTLE_CCY;
	}
	/**
	 * @return the dELIVERY_TYPE
	 */
	public String getDeliveryType() {
		return DELIVERY_TYPE;
	}
	/**
	 * @param dELIVERY_TYPE the dELIVERY_TYPE to set
	 */
	public void setDeliveryType(String dELIVERY_TYPE) {
		DELIVERY_TYPE = dELIVERY_TYPE;
	}
	/**
	 * @return the sEQ_NUMBER
	 */
	public int getSeqNumber() {
		return SEQ_NUMBER;
	}
	/**
	 * @param sEQ_NUMBER the sEQ_NUMBER to set
	 */
	public void setSeqNumber(int sEQ_NUMBER) {
		SEQ_NUMBER = sEQ_NUMBER;
	}
	/**
	 * @return the nETTING_METHOD_ID
	 */
	public int getNettingMethodID() {
		return NETTING_METHOD_ID;
	}
	/**
	 * @param nETTING_METHOD_ID the nETTING_METHOD_ID to set
	 */
	public void setNettingMethodID(int nETTING_METHOD_ID) {
		NETTING_METHOD_ID = nETTING_METHOD_ID;
	}
	/**
	 * @return the nETTING_TYPE
	 */
	public String getNettingType() {
		return NETTING_TYPE;
	}
	/**
	 * @param nETTING_TYPE the nETTING_TYPE to set
	 */
	public void setNettingType(String nETTING_TYPE) {
		NETTING_TYPE = nETTING_TYPE;
	}
	/**
	 * @return the pAYER_SDSTATUS
	 */
	public String getPayerSDStatus() {
		return PAYER_SDSTATUS;
	}
	/**
	 * @param pAYER_SDSTATUS the pAYER_SDSTATUS to set
	 */
	public void setPayerSDStatus(String pAYER_SDSTATUS) {
		PAYER_SDSTATUS = pAYER_SDSTATUS;
	}
	/**
	 * @return the rECEIVER_SDSTATUS
	 */
	public String getReceiverSDStatus() {
		return RECEIVER_SDSTATUS;
	}
	/**
	 * @param rECEIVER_SDSTATUS the rECEIVER_SDSTATUS to set
	 */
	public void setReceiverSDStatus(String rECEIVER_SDSTATUS) {
		RECEIVER_SDSTATUS = rECEIVER_SDSTATUS;
	}
	/**
	 * @return the pERCENTAGE
	 */
	public int getPercentage() {
		return PERCENTAGE;
	}
	/**
	 * @param pERCENTAGE the pERCENTAGE to set
	 */
	public void setPercentage(int pERCENTAGE) {
		PERCENTAGE = pERCENTAGE;
	}
	/**
	 * @return the sECURITY_ID
	 */
	public int getSecurityID() {
		return SECURITY_ID;
	}
	/**
	 * @param sECURITY_ID the sECURITY_ID to set
	 */
	public void setSecurityID(int sECURITY_ID) {
		SECURITY_ID = sECURITY_ID;
	}
	/**
	 * @return the mANUAL_SDI
	 */
	public int getManualSDI() {
		return MANUAL_SDI;
	}
	/**
	 * @param mANUAL_SDI the mANUAL_SDI to set
	 */
	public void setManualSDI(int mANUAL_SDI) {
		MANUAL_SDI = mANUAL_SDI;
	}
	/**
	 * @return the iNT_SDI_VERSION
	 */
	public int getintSDIVersion() {
		return INT_SDI_VERSION;
	}
	/**
	 * @param iNT_SDI_VERSION the iNT_SDI_VERSION to set
	 */
	public void setintSDIVersion(int iNT_SDI_VERSION) {
		INT_SDI_VERSION = iNT_SDI_VERSION;
	}
	/**
	 * @return the eXT_SDI_VERSION
	 */
	public int getExtSDIVersion() {
		return EXT_SDI_VERSION;
	}
	/**
	 * @param eXT_SDI_VERSION the eXT_SDI_VERSION to set
	 */
	public void setExtSDIVersion(int eXT_SDI_VERSION) {
		EXT_SDI_VERSION = eXT_SDI_VERSION;
	}
	/**
	 * @return the sETTLE_DATE
	 */
	public String getSettleDate() {
		return SETTLE_DATE;
	}
	/**
	 * @param sETTLE_DATE the sETTLE_DATE to set
	 */
	public void setSettleDate(String sETTLE_DATE) {
		SETTLE_DATE = sETTLE_DATE;
	}
	int  PRODUCT_ID  = 0;
	 String  TRANSFER_TYPE = null;
	 String  PRODUCT_TYPE= null;
	 String  TRANSFER_CCY= null;
	 String   PAY_RECEIVE = null;
	 String   SETTLEMENT_METHOD = null;
	 int   PAYER_ID  = 0;
	 String   PAYER_ROLE= null;
	 int  PAYER_SDID = 0;
	 int  RECEIVER_ID = 0;
	 String  RECEIVER_ROLE= null;
	 int  RECEIVER_SDID  = 0;
	 String   SETTLE_CCY = null;
	 String   DELIVERY_TYPE = null;
	int    SEQ_NUMBER = 0;
	int    NETTING_METHOD_ID  = 0;
	 String   NETTING_TYPE = null;
	 String   PAYER_SDSTATUS = null;
	 String   RECEIVER_SDSTATUS = null;
	int    PERCENTAGE = 0;
	int   SECURITY_ID = 0;
	int   MANUAL_SDI  = 0;
	int   INT_SDI_VERSION = 0;
	int    EXT_SDI_VERSION = 0;
	String   SETTLE_DATE = null;
	
    public void setTransferRule(Transfer rule) {
    	setTradeID(rule.getTradeId());
    }
}
