package beans;

import java.io.Serializable;

public class AccEventConfig implements Serializable {
	
     
     public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getAccEvtType() {
		return accEvtType;
	}
	public void setAccEvtType(String accEvtType) {
		this.accEvtType = accEvtType;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getReversalType() {
		return reversalType;
	}
	public void setReversalType(String reversalType) {
		this.reversalType = reversalType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	String productType;
	String accEvtType;
     String paymentType;
     String reversalType;
     int id;
     

}
