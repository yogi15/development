package beans;

import java.io.Serializable;

public class CashPosition 
	implements Serializable {
		double out1amount;
		/**
		 * @return the out1amount
		 */
		public double getOut1amount() {
			return out1amount;
		}
		/**
		 * @param out1amount the out1amount to set
		 */
		public void setOut1amount(double out1amount) {
			this.out1amount = out1amount;
		}
		/**
		 * @return the out2amount
		 */
		public double getOut2amount() {
			return out2amount;
		}
		/**
		 * @param out2amount the out2amount to set
		 */
		public void setOut2amount(double out2amount) {
			this.out2amount = out2amount;
		}
		double out2amount;
		
		int id;
		String tradedesc1;
		String primaryCurr;
		String Currency;
		/**
		 * @return the leg
		 */
		public int getLeg() {
			return leg;
		}
		/**
		 * @param leg the leg to set
		 */
		public void setLeg(int leg) {
			this.leg = leg;
		}
		int leg;
		
		double actualAmt;
		
		/**
		 * @return the actualAmt
		 */
		public double getActualAmt() {
			return actualAmt;
		}
		/**
		 * @param actualAmt the actualAmt to set
		 */
		public void setActualAmt(double actualAmt) {
			this.actualAmt = actualAmt;
		}
		/**
		 * @return the currency
		 */
		public String getCurrency() {
			return Currency;
		}
		/**
		 * @param currency the currency to set
		 */
		public void setCurrency(String currency) {
			Currency = currency;
		}
		/**
		 * @return the primaryCurr
		 */
		public String getPrimaryCurr() {
			return primaryCurr;
		}
		/**
		 * @param primaryCurr the primaryCurr to set
		 */
		public void setPrimaryCurr(String primaryCurr) {
			this.primaryCurr = primaryCurr;
		}
		/**
		 * @return the quotingCurr
		 */
		public String getQuotingCurr() {
			return quotingCurr;
		}
		/**
		 * @param quotingCurr the quotingCurr to set
		 */
		public void setQuotingCurr(String quotingCurr) {
			this.quotingCurr = quotingCurr;
		}
		String quotingCurr;
		public String getTradedesc1() {
			return tradedesc1;
		}
		public void setTradedesc1(String tradedesc1) {
			this.tradedesc1 = tradedesc1;
		}
		public String getFxSwapLegType() {
			return fxSwapLegType;
		}
		public void setFxSwapLegType(String fxSwapLegType) {
			this.fxSwapLegType = fxSwapLegType;
		}
		String fxSwapLegType;
		String tradedesc;
		/**
		 * @return the tradedesc
		 */
		public String getTradedesc() {
			return tradedesc;
		}
		/**
		 * @param tradedesc the tradedesc to set
		 */
		public void setTradedesc(String tradedesc) {
			this.tradedesc = tradedesc;
		}
		double originalTradeAmt;
		int		tradeId;
		int		productId;
		String	settleDate;
		String	tradeDate;
		double	quantity;
		double	openQuantity;
		int		bookId;
		double	price;
		String		type;
		int		positionId;
		String	openpositionDate;
		String	productType;
		String	productSubType;
		double  openNominal;
		double  tradeAmt;
		double  productFV; // this face value of trade product trade which is of bond type (to avoid unneccessary call to product and trade again when openpos is updated).
		
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public double getOriginalTradeAmt() {
			return originalTradeAmt;
		}
		public void setOriginalTradeAmt(double originalTradeAmt) {
			this.originalTradeAmt = originalTradeAmt;
		}
		public double getProductFV() {
			return productFV;
		}
		public void setProductFV(double productFV) {
			this.productFV = productFV;
		}
		public double getTradeAmt() {
			return tradeAmt;
		}
		public void setTradeAmt(double tradeAmt) {
			this.tradeAmt = tradeAmt;
		}
		public double getOpenNominal() {
			return openNominal;
		}
		public void setOpenNominal(double openNominal) {
			this.openNominal = openNominal;
		}
		public double getQuotingAmt() {
			return quotingAmt;
		}
		public void setQuotingAmt(double quotingAmt) {
			this.quotingAmt = quotingAmt;
		}
		double quotingAmt;
		public int getTradeId() {
			return tradeId;
		}
		public void setTradeId(int tradeId) {
			this.tradeId = tradeId;
		}
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		public String getSettleDate() {
			return settleDate;
		}
		public void setSettleDate(String settleDate) {
			this.settleDate = settleDate;
		}
		public String getTradeDate() {
			return tradeDate;
		}
		public void setTradeDate(String tradeDate) {
			this.tradeDate = tradeDate;
		}
		public double getQuantity() {
			return quantity;
		}
		public void setQuantity(double quantity) {
			this.quantity = quantity;
		}
		public double getOpenQuantity() {
			return openQuantity;
		}
		public void setOpenQuantity(double openQuantity) {
			this.openQuantity = openQuantity;
		}
		public int getBookId() {
			return bookId;
		}
		public void setBookId(int bookId) {
			this.bookId = bookId;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getPositionId() {
			return positionId;
		}
		public void setPositionId(int positionId) {
			this.positionId = positionId;
		}
		public String getOpenpositionDate() {
			return openpositionDate;
		}
		public void setOpenpositionDate(String openpositionDate) {
			this.openpositionDate = openpositionDate;
		}
		public String getProductType() {
			return productType;
		}
		public void setProductType(String productType) {
			this.productType = productType;
		}
		public String getProductSubType() {
			return productSubType;
		}
		public void setProductSubType(String productSubType) {
			this.productSubType = productSubType;
		}
		int cpID =0;
		public void setCpID(int cpID) {
			// TODO Auto-generated method stub
			this.cpID = cpID;
			
		}
		public int getCpID() {
			return cpID;
		}
		
	}
