package marketquotes;

import beans.QuoteData;

public interface FeedListener {
	

	public void listenFeed(QuoteData quoteData);

}
