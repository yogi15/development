package dsEventProcessor;

import beans.QuoteData;

public class QuoteEventProcessor extends EventProcessor {
	
	
	String Feedname;
	public String getFeedname() {
		return Feedname;
	}
	public void setFeedname(String feedname) {
		Feedname = feedname;
	}
	public QuoteData getQuoteData() {
		return quoteData;
	}
	public void setQuoteData(QuoteData quoteData) {
		this.quoteData = quoteData;
	}
	QuoteData quoteData;
	
	
	

}
