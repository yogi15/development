package dsEventProcessor;

import java.io.Serializable;

import beans.Liquidation;

public class LiquidationEventProcessor extends EventProcessor  implements Serializable {
	Liquidation liquidation;
	
    int bookID;
    /**
	 * @return the tradeID
	 */
	

    public void setTradeID(int tradeID) {
    	this.tradeID = tradeID;
    }
	/**
	 * @param tradeID the tradeID to set
	 */
   
	/**
	 * @return the bookID
	 */
    public int getBookID() {
		return bookID;
	}

	/**
	 * @param bookID the bookID to set
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	/**
	 * @return the productID
	 */
	public int getProductID() {
		return productID;
	}

	/**
	 * @param productID the productID to set
	 */
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}
	/**
	 * @return the positionID
	 */
	public int getPositionID() {
		return positionID;
	}
	int positionID;
    int productID;
    
	public Liquidation getLiquidation() {
		return liquidation;
	}

	public void setLiquidation(Liquidation liquidation) {
		this.liquidation = liquidation;
	}
	
	
	
	


}
