package beans;

import java.io.Serializable;

public class FeedAddress implements Serializable {
	
	
	 static final public String MID="MID";
	    static final public String BID="BID";
	    static final public String ASK="ASK";
	    static final public String OPEN="OPEN";
	    static final public String CLOSE="CLOSE";
	    static final public String HIGH="HIGH";
	    static final public String LOW="LOW";
	    static final public String LAST="LAST";

	    protected String _quoteName;
	    public String get_quoteName() {
			return _quoteName;
		}
		public void set_quoteName(String _quoteName) {
			this._quoteName = _quoteName;
		}
		public String get_quoteType() {
			return _quoteType;
		}
		public void set_quoteType(String _quoteType) {
			this._quoteType = _quoteType;
		}
		public String get_feedName() {
			return _feedName;
		}
		public void set_feedName(String _feedName) {
			this._feedName = _feedName;
		}
		public String get_feedAddress() {
			return _feedAddress;
		}
		public void set_feedAddress(String _feedAddress) {
			this._feedAddress = _feedAddress;
		}
		public String get_feedBidName() {
			return _feedBidName;
		}
		public void set_feedBidName(String _feedBidName) {
			this._feedBidName = _feedBidName;
		}
		public String get_feedAskName() {
			return _feedAskName;
		}
		public void set_feedAskName(String _feedAskName) {
			this._feedAskName = _feedAskName;
		}
		public String get_feedOpenName() {
			return _feedOpenName;
		}
		public void set_feedOpenName(String _feedOpenName) {
			this._feedOpenName = _feedOpenName;
		}
		public String get_feedCloseName() {
			return _feedCloseName;
		}
		public void set_feedCloseName(String _feedCloseName) {
			this._feedCloseName = _feedCloseName;
		}
		public String get_feedHighName() {
			return _feedHighName;
		}
		public void set_feedHighName(String _feedHighName) {
			this._feedHighName = _feedHighName;
		}
		public String get_feedLowName() {
			return _feedLowName;
		}
		public void set_feedLowName(String _feedLowName) {
			this._feedLowName = _feedLowName;
		}
		public String get_feedLastName() {
			return _feedLastName;
		}
		public void set_feedLastName(String _feedLastName) {
			this._feedLastName = _feedLastName;
		}
		public String get_feedDate() {
			return _feedDate;
		}
		public void set_feedDate(String _feedDate) {
			this._feedDate = _feedDate;
		}
	
		public String _quoteType;
		public String _feedName;
		public String _feedAddress;
		public String _feedBidName="BID";
		public String _feedAskName="ASK";
	    public String _feedOpenName="OPEN";
	    public String _feedCloseName="CLOSE";
	    public String _feedHighName="HIGH";
	    public String _feedLowName="LOW";
	    public String _feedLastName="LAST";
	    public String _feedDate="DATE";

}
