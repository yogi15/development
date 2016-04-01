package beans;

import java.io.Serializable;

public class Posting  implements Serializable,Comparable<Posting> {
	
	int Id;
	double creditAmount = 0.0;
	double debitAmount =0.0;
	int tradeID;
	int transferId;
	int sdiId;
	private String accEventType;
	private int linkId;
	private String eventType;
	private int debitAccId;
	private int creditAccId;
	private String ruleName;
	private String currency;
	private String type;
	private String status;
	private String BookingDate;
	private String CreationDate;
	private String EffectiveDate;
	private int AccEvtConfigId;
	private int linkTo;
	
	
	public int getLinkTo() {
		return linkTo;
	}
	public void setLinkTo(int linkTo) {
		this.linkTo = linkTo;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getLinkId() {
		return linkId;
	}
	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}
	public int getDebitAccId() {
		return debitAccId;
	}
	public void setDebitAccId(int debitAccId) {
		this.debitAccId = debitAccId;
	}
	public int getCreditAccId() {
		return creditAccId;
	}
	public void setCreditAccId(int creditAccId) {
		this.creditAccId = creditAccId;
	}
	public String getAccEventType() {
		return accEventType;
	}
	public String getEventType() {
		return eventType;
	}
	public String getRuleName() {
		return ruleName;
	}
	public String getCurrency() {
		return currency;
	}
	public String getType() {
		return type;
	}
	public String getStatus() {
		return status;
	}
	public String getBookingDate() {
		return BookingDate;
	}
	public String getCreationDate() {
		return CreationDate;
	}
	public String getEffectiveDate() {
		return EffectiveDate;
	}
	
	
	
	
	public double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(double creditAmt) {
		this.creditAmount = creditAmt;
	}
	public double getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}
	public int getTradeID() {
		return tradeID;
	}
	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public int getSdiId() {
		return sdiId;
	}
	public void setSdiId(int sdiId) {
		this.sdiId = sdiId;
	}
	public void setAccEventType(String accEventType) {
		this.accEventType = accEventType;
		// TODO Auto-generated method stub
		
	}
	public void setlinkId(int linkid) {
		// TODO Auto-generated method stub
		this.linkId = linkid;
		
	}
	public void setEventType(String eventType) {
		// TODO Auto-generated method stub
		this.eventType= eventType;
		
	}
	public void setDebitAccount(int debitAccId) {
		// TODO Auto-generated method stub
		this.debitAccId = debitAccId;
		
	}
	public void setCreditAccount(int creditAccId) {
		// TODO Auto-generated method stub
		this.creditAccId = creditAccId;
	}
	public void setRuleName(String ruleName) {
		// TODO Auto-generated method stub
		this.ruleName = ruleName;
		
	}
	public void setCurrency(String currency) {
		// TODO Auto-generated method stub
		this.currency = currency;
	}
	public void setType(String type) {
		// TODO Auto-generated method stub
		this.type = type;
	}
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		this.status = status;
	}
	public void setBookingDate(String BookingDate ) {
		// TODO Auto-generated method stub
		this.BookingDate = BookingDate;
	}
	public void setCreationDate(String CreationDate) {
		// TODO Auto-generated method stub
		this.CreationDate = CreationDate;
	}
	public void setEffectiveDate(String EffectiveDate) {
		// TODO Auto-generated method stub
		this.EffectiveDate = EffectiveDate;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Posting) {

			return true;
		} else {
			return false;
		}
	}

	public int comparePostingTrade(Posting posting) {
		// TODO Auto-generated method stub
		int i = 0;// (this.getId()+this.getAccEventType()+this.getBookingDate()+this.getCreationDate()+this.getCreditAccId()+this.getCreditAmount()+this.getCurrency()+this.getDebitAccId()+this.getDebitAmount()+this.getEffectiveDate()+this.getEventType()+this.getLinkId()+this.getRuleName()+this.getSdiId()+this.getStatus()+this.getTradeID()+this.getTransferId()+this.getType()).compareTo((posting.getId()+posting.getAccEventType()+posting.getBookingDate()+posting.getCreationDate()+posting.getCreditAccId()+posting.getCreditAmount()+posting.getCurrency()+this.getDebitAccId()+posting.getDebitAmount()+posting.getEffectiveDate()+posting.getEventType()+posting.getLinkId()+posting.getRuleName()+posting.getSdiId()+posting.getStatus()+posting.getTradeID()+posting.getTransferId()+posting.getType()));
		/*String oldString = this.getId() + this.getAccEventType()
				+ this.getBookingDate() + this.getCreationDate()
				+ this.getCreditAccId() + this.getCreditAmount()
				+ this.getCurrency() + this.getDebitAccId()
				+ this.getDebitAmount() + this.getEffectiveDate()
				+ this.getEventType() + this.getLinkId() + this.getRuleName()
				+ this.getSdiId() + this.getStatus() + this.getTradeID()
				+ this.getTransferId() + this.getType()
				+ this.getAccEvtConfigId() + this.getTransferId();
		String newString = posting.getId() + posting.getAccEventType()
				+ posting.getBookingDate() + posting.getCreationDate()
				+ posting.getCreditAccId() + posting.getCreditAmount()
				+ posting.getCurrency() + this.getDebitAccId()
				+ posting.getDebitAmount() + posting.getEffectiveDate()
				+ posting.getEventType() + posting.getLinkId()
				+ posting.getRuleName() + posting.getSdiId()
				+ posting.getStatus() + posting.getTradeID()
				+ posting.getTransferId() + posting.getType()
				+ posting.getAccEvtConfigId() + posting.getTransferId();*/
		
		String newString = new StringBuffer(posting.getBookingDate())
		 .append(posting.getCreditAmount())
		 .append(posting.getCurrency())
		 .append(posting.getDebitAmount())
		 .append(posting.getEffectiveDate())
		 .toString();

		
		String oldString = new StringBuffer(this.getBookingDate())
								 .append(this.getCreditAmount())
								 .append(this.getCurrency())
								 .append(this.getDebitAmount())
								 .append(this.getEffectiveDate())
								 .toString();
		
		
		System.out.println(" oldString " + oldString);
		System.out.println(" newString " + newString);

		i = oldString.compareTo(newString);
		return i;
	}

	public int comparePostingTransfer(Posting posting) {
		// TODO Auto-generated method stub
		int i = 0;// (this.getId()+this.getAccEventType()+this.getBookingDate()+this.getCreationDate()+this.getCreditAccId()+this.getCreditAmount()+this.getCurrency()+this.getDebitAccId()+this.getDebitAmount()+this.getEffectiveDate()+this.getEventType()+this.getLinkId()+this.getRuleName()+this.getSdiId()+this.getStatus()+this.getTradeID()+this.getTransferId()+this.getType()).compareTo((posting.getId()+posting.getAccEventType()+posting.getBookingDate()+posting.getCreationDate()+posting.getCreditAccId()+posting.getCreditAmount()+posting.getCurrency()+this.getDebitAccId()+posting.getDebitAmount()+posting.getEffectiveDate()+posting.getEventType()+posting.getLinkId()+posting.getRuleName()+posting.getSdiId()+posting.getStatus()+posting.getTradeID()+posting.getTransferId()+posting.getType()));
		/*String oldString = this.getId() + this.getAccEventType()
				+ this.getBookingDate() + this.getCreationDate()
				+ this.getCreditAccId() + this.getCreditAmount()
				+ this.getCurrency() + this.getDebitAccId()
				+ this.getDebitAmount() + this.getEffectiveDate()
				+ this.getLinkId() + this.getRuleName() + this.getSdiId()
				+ this.getStatus() + this.getTradeID() + this.getTransferId()
				+ this.getEventType() + this.getType()
				+ this.getAccEvtConfigId() + this.getTransferId();
		String newString = posting.getId() + posting.getAccEventType()
				+ posting.getBookingDate() + posting.getCreationDate()
				+ posting.getCreditAccId() + posting.getCreditAmount()
				+ posting.getCurrency() + this.getDebitAccId()
				+ posting.getDebitAmount() + posting.getEffectiveDate()
				+ posting.getLinkId() + posting.getRuleName()
				+ posting.getSdiId() + posting.getStatus()
				+ posting.getTradeID() + posting.getTransferId()
				+ posting.getEventType() + posting.getType()
				+ posting.getAccEvtConfigId() + posting.getTransferId();*/

		
		String newString = new StringBuffer(posting.getBookingDate())
		 .append(posting.getCreditAmount())
		 .append(posting.getCurrency())
		 .append(posting.getDebitAmount())
		 .append(posting.getEffectiveDate())
		 .toString();

		
		String oldString = new StringBuffer(this.getBookingDate())
								 .append(this.getCreditAmount())
								 .append(this.getCurrency())
								 .append(this.getDebitAmount())
								 .append(this.getEffectiveDate())
								 .toString();
		
		// System.out.println(" oldString " + oldString );
		// System.out.println(" newString " + newString );

		i = oldString.compareTo(newString);
		return i;
	}
	
	public int comparePremiumDiscountPostingTrade(Posting posting) {
	
		int i = 0;

		
		if ( this.getCreditAmount() != posting.getCreditAmount() || this.getDebitAmount() !=  posting.getDebitAmount() ) {
			
		} else {
			
		}

		return i;
	}
	
	public int comparePremiumDiscountPostingTransfer(Posting posting) {

		int i = 0;

		
		if ( this.getCreditAmount() != posting.getCreditAmount() || this.getDebitAmount() !=  posting.getDebitAmount() ) {
			 
			i = 1;
			 
		} else {
			
			i = 0;
		}

		return i;
	}
	public void setAccEvtConfigId(int accEvtConfigId) {
		// TODO Auto-generated method stub
		this.AccEvtConfigId = accEvtConfigId;
		
	}
	public int getAccEvtConfigId() {
		return AccEvtConfigId;
	}
	@Override
	public int compareTo(Posting o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
