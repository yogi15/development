package beans;

import java.io.Serializable;
import java.util.Date;

public class QuoteData implements Serializable {
	
	
		String	envName;
		String type;
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public QuoteData() {
			
		}
		public QuoteData(String envName, String quoteName, String d,
				String Type, double bid, double ask,
				double open, double close) {
			// TODO Auto-generated constructor stub
			this.envName = envName;
			this.quoteName = quoteName;
					this.datetime = d;
					this.type = Type;
					this.bid = bid;
					this.ask = ask;
					this.open = open;
					this.close = close;
			
		}
		public String getEnvName() {
			return envName;
		}
		public void setEnvName(String envName) {
			this.envName = envName;
		}
		public String getQuoteName() {
			return quoteName;
		}
		public void setQuoteName(String quoteName) {
			this.quoteName = quoteName;
		}
		public double getBid() {
			return bid;
		}
		public void setBid(double bid) {
			this.bid = bid;
		}
		public double getAsk() {
			return ask;
		}
		public void setAsk(double ask) {
			this.ask = ask;
		}
		public double getOpen() {
			return open;
		}
		public void setOpen(double open) {
			this.open = open;
		}
		public double getClose() {
			return close;
		}
		public void setClose(double close) {
			this.close = close;
		}
		public double getHigh() {
			return high;
		}
		public void setHigh(double high) {
			this.high = high;
		}
		public double getLow() {
			return low;
		}
		public void setLow(double low) {
			this.low = low;
		}
		public double getLast() {
			return last;
		}
		public void setLast(double last) {
			this.last = last;
		}
		public String getDatetime() {
			return datetime;
		}
		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}
		String		quoteName;
		double	bid;
		double			ask;
		double		open;
			double			close;
			double		high;
			double		low;
			double		last;
			String		datetime;
			

}
