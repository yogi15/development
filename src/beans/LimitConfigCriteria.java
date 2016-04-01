package beans;

public class LimitConfigCriteria {
	
		int limitConfigID = 0;
		public int getLimitConfigID() {
			return limitConfigID;
		}
		public void setLimitConfigID(int limitConfigID) {
			this.limitConfigID = limitConfigID;
		}
		public int getBookID() {
			return bookID;
		}
		public void setBookID(int bookID) {
			this.bookID = bookID;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getProductType() {
			return productType;
		}
		public void setProductType(String productType) {
			this.productType = productType;
		}
		int bookID= 0;
		String currency;
		String productType;
		
	
	

}
