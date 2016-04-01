package beans;

import java.io.Serializable;

public class LinkFolder implements Serializable {
	
	int ruleid;
	int id;
	String productType;
	int folderId;
	int sdifilterid;
	public int getRuleid() {
		return ruleid;
	}
	public void setRuleid(int ruleid) {
		this.ruleid = ruleid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public int getFolderId() {
		return folderId;
	}
	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}
	public int getSdifilterid() {
		return sdifilterid;
	}
	public void setSdifilterid(int sdifilterid) {
		this.sdifilterid = sdifilterid;
	}
	
	
	
	
	

}
