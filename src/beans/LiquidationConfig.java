package beans;

import java.io.Serializable;

public class LiquidationConfig implements Serializable {

	
	int id;

	int bookid;
	String liqmethod;
	String datetype;
	String producttype;
	String productsubtype;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getLiqmethod() {
		return liqmethod;
	}
	public void setLiqmethod(String liqmethod) {
		this.liqmethod = liqmethod;
	}
	public String getDatetype() {
		return datetype;
	}
	public void setDatetype(String datetype) {
		this.datetype = datetype;
	}
	public String getProducttype() {
		return producttype;
	}
	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	public String getProductsubtype() {
		return productsubtype;
	}
	public void setProductsubtype(String productsubtype) {
		this.productsubtype = productsubtype;
	}
	boolean isLiquidationApplicable = true;
	/**
	 * @return the isLiquidationApplicable
	 */
	public boolean isAvaliableLiquidation() {
		return isLiquidationApplicable;
	}
	public void setAvaliableLiquidation(boolean b) {
		// TODO Auto-generated method stub
		isLiquidationApplicable  = b;
		
	}
	
	
}
