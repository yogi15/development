package marketquotes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

import dsEventProcessor.QuoteEventProcessor;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;
import beans.FeedAddress;
import beans.QuoteData;
import util.commonUTIL;

public class RandomQuotes implements Runnable {
	
	static final public String quotesName = "RANDOM";
	static final public String env = "backOffice";
	static final public String CONFIG_FILE = "ConfigFile";
	private Thread _genThread = null;
    private boolean _generating = false;
    private int _interval = 500;
    private Vector _quotes = new Vector();
    String _configFile = "QuoteTestData.txt";
    static final int _maxCols = 4;
    Hashtable <QuoteData,FeedAddress>  _quotes2feed = new Hashtable<QuoteData,FeedAddress>();
    private boolean _bid = true;
    private boolean _ask = true;
    private boolean _open = true;
    private boolean _close = true;
    private boolean _high = true;
    private boolean _low = true;
    private boolean _last = true;
    Hashtable<String,QuoteData> subcribseList = new Hashtable<String,QuoteData>();
    RemoteTrade remoteTrade = null;
    public static  ServerConnectionUtil de = null;
    
    public RandomQuotes() {
    	try {
    		de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
    		 remoteTrade = (RemoteTrade) de.getRMIService("Trade");
			readQuotes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Override
	public void run() {
    	for( ; ; ) {
    //	System.out.println("ppp");
    	
		for (int i = 0; i < _quotes.size(); i++) {
			 QuoteData qv = getQuoteValueAt(i);
			// System.out.println("PPPP " + qv.getQuoteName());
			 if(quotesInsubcribuseList(qv)) {
				 publicQuoteData(qv);
			 }
		  }
		try {
            Thread.sleep(_interval);
       } catch (InterruptedException e) {
       }
    }
    	
	
	
    
}
    private void publicQuoteData(QuoteData qv) {
		// TODO Auto-generated method stub
    	QuoteEventProcessor quoteEvent = new QuoteEventProcessor();
    	quoteEvent.setFeedname("Random");
    	quoteEvent.setQuoteData(qv);
    	quoteEvent.setEventType("QuoteEvent");
    	quoteEvent.setObjectID(0);
    	quoteEvent.setType(qv.getQuoteName());
    	quoteEvent.setComments("Quote Name "+qv.getQuoteName() + " Quote Type " + qv.getType());
    	quoteEvent.setSavetoDB(false);
    	try {
			remoteTrade.publishnewTrade("TRANS_NEWTRANSFER","TRADE", quoteEvent);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public boolean quotesInsubcribuseList(QuoteData qv) {
    	boolean retVal = false;
    	String quoteSetName = qv.getQuoteName();
    	  Enumeration keys = subcribseList.keys();
    	  while (keys.hasMoreElements()) {
    		  String quoteName = (String) keys.nextElement();
    		  
             QuoteData data1 =subcribseList.get(quoteName);
             System.out.println("SUb == "+ data1.getQuoteName());
              if (data1.getQuoteName().equalsIgnoreCase(quoteSetName)) {
            	  retVal = true;
                  break;
              }
    	  }
    	
    	
    	 return retVal;
    }
    
    public void addQuoteSubsribeList(String quoteName,QuoteData data) {
    	subcribseList.put(quoteName, data);
    }
    
    public void start() {
    	//Thread t = new Thread(this);
    	
	   	 
    	
    	//t.start();
    	
    }
    public void readQuotes() throws IOException {
    	try {
            // reading header...
            BufferedReader br = null;
            String fileName = "demo/" + _configFile;
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
          


            InputStream is = null;
            if ((is = cl.getResourceAsStream(fileName)) != null)
                br = new BufferedReader(new InputStreamReader(is));
            if (is == null || br == null) {
                throw new IOException("Unable to open resource : "
                                        + fileName);
            }            
            br.readLine();
            _interval = Integer.parseInt(br.readLine());
            br.readLine();
            String s = null;
            StringTokenizer st;
            s = br.readLine();
            FeedAddress addr = null;
            while (!commonUTIL.isEmpty(s)) {
            	 Vector v = new Vector();
                 st = new StringTokenizer(s, "|");
                 if (st != null
                         && st.hasMoreTokens()) {
                     // v.add(st.nextToken()); // QuoteSet name
                     String feedAddrField = st.nextToken();
                    // System.out.println(feedAddrField);
                     addr = new FeedAddress();
                     addr.set_feedAddress(feedAddrField);
                     addr.set_quoteType("Price");
                     addr.set_quoteName("BID");
                     addr.set_feedAskName("ASK");
                     addr.set_feedOpenName("OPEN");
                     addr.set_feedCloseName("CLOSE");
                   //  addr.set_feedDate("DATE");
                   //  addr.set_feedCloseName("CLOSE");
                     v.add(addr);
                     v.add(addr.get_quoteName());
                     v.add(st.nextToken()); // type of the quote
                     readPrices(v,st,addr);
                     _quotes.add(v);
                     QuoteData qv = new QuoteData();
                     qv.setEnvName("Random");
                     qv.setQuoteName(feedAddrField);
                     qv.setDatetime(commonUTIL.getCurrentDateInString());
                     _quotes2feed.put(qv, addr);
                     s = br.readLine();
                 }
            }
    	} catch (Throwable t) {
            
				throw new IOException(t.getLocalizedMessage());
			
        } 
    }
    public static double randomIn(double min, double max) {
        return Math.random()
        * Math.abs(max
                   - min) + Math.min(min, max);
    }
    public QuoteData getQuoteValueAt(int pos) {
        Vector v = (Vector) _quotes.elementAt(pos);
        FeedAddress feedAddress = (FeedAddress) v.elementAt(0);
        String d = commonUTIL.dateToString(commonUTIL.getCurrentDate());
        QuoteData qv = null;
        try {
            Map valueMap = new HashMap();
            double bid = 
                randomIn(Double.parseDouble((String) v.elementAt(3)),
                         Double.parseDouble((String) v.elementAt(4)));
            double ask = 
                randomIn(Double.parseDouble((String) v.elementAt(5)),
                         Double.parseDouble((String) v.elementAt(6)));
            double open = 
                randomIn(Double.parseDouble((String) v.elementAt(7)),
                         Double.parseDouble((String) v.elementAt(8)));
            double close = 
                randomIn(Double.parseDouble((String) v.elementAt(9)),
                         Double.parseDouble((String) v.elementAt(10)));
            double high = 
                randomIn(Double.parseDouble((String) v.elementAt(11)),
                         Double.parseDouble((String) v.elementAt(12)));
            double low = 
                randomIn(Double.parseDouble((String) v.elementAt(13)),
                         Double.parseDouble((String) v.elementAt(14)));
            double last = 
                randomIn(Double.parseDouble((String) v.elementAt(15)),
                         Double.parseDouble((String) v.elementAt(16)));
            /*
             * We just want to make sure that the bid is less than the ask, as
             * is only logical in the market.
             */
            if (bid > ask) {
                double temp = ask;
                ask = bid;
                bid = temp;
            }
            valueMap.put(FeedAddress.BID, new Double(bid));
            valueMap.put(FeedAddress.ASK, new Double(ask));
            valueMap.put(FeedAddress.OPEN, new Double(open));
            valueMap.put(FeedAddress.CLOSE, new Double(close));
            valueMap.put(FeedAddress.HIGH, new Double(high));
            valueMap.put(FeedAddress.LOW, new Double(low));
            valueMap.put(FeedAddress.LAST, new Double(last));
            qv = new QuoteData(quotesName,
                                (String) feedAddress.get_feedAddress(),
                                d,
                                (String) v.elementAt(2),
                                ((Double) valueMap.get(feedAddress._feedBidName)).doubleValue(),
                                ((Double) valueMap.get(feedAddress._feedAskName)).doubleValue(),
                                ((Double) valueMap.get(feedAddress._feedOpenName)).doubleValue(),
                                ((Double) valueMap.get(feedAddress._feedCloseName)).doubleValue());
            qv.setHigh(((Double) valueMap.get(feedAddress._feedHighName)).doubleValue());
            qv.setLow(((Double) valueMap.get(feedAddress._feedLowName)).doubleValue());
            qv.setLast(((Double) valueMap.get(feedAddress._feedLastName)).doubleValue());
            FeedAddress addr = (FeedAddress) _quotes2feed.get(qv);
            if (addr != null) { // should always be, but who knows?
                double a = 1.;
                double b = 0;
                qv.setBid(_bid
                          ? (a * qv.getBid() + b) : 0);
                qv.setAsk(_ask
                          ? (a * qv.getAsk() + b) : 0);
                qv.setOpen(_open
                           ? (a * qv.getOpen() + b) : 0);
                qv.setClose(_close
                            ? (a * qv.getClose() + b) : 0);
                qv.setHigh(_high
                           ? (a * qv.getHigh() + b) : 0);
                qv.setLow(_low
                          ? (a* qv.getLow() + b) : 0);
                qv.setLast(_last
                           ? (a * qv.getLast() + b) : 0);
                qv.setEnvName("Random-" + addr.get_feedAddress());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return qv;
    }
    public void readPrices(Vector v, StringTokenizer st, FeedAddress addr) {
        String minAsk = 
            "0", maxAsk = "0", minBid = "0", maxBid = "0";
        String minOpen = 
            "0", maxOpen = "0", minClose = "0", maxClose = "0";
        String minHigh = 
            "0", maxHigh = "0", minLow = "0", maxLow = "0", minLast = "0", maxLast = "0";
        String mapName = null;
        for (int i = 0; i < _maxCols; i++) {
           try {
                mapName = st.nextToken();
                System.out.println(mapName);
                if (mapName.equals(addr.get_feedBidName())) {
                    minBid = st.nextToken();
                    maxBid = st.nextToken();
                } else if (mapName.equals(addr.get_feedAskName())) {
                    minAsk = st.nextToken();
                    maxAsk = st.nextToken();
                } else if (mapName.equals(addr.get_feedOpenName())) {
                    minOpen = st.nextToken();
                    maxOpen = st.nextToken();
                } else if (mapName.equals(addr.get_feedCloseName())) {
                    minClose = st.nextToken();
                    maxClose = st.nextToken();
               } else if (mapName.equals(addr.get_feedHighName())) {
                    minHigh = st.nextToken();
                    maxHigh = st.nextToken();
                } else if (mapName.equals(addr.get_feedLowName())) {
                    minLow = st.nextToken();
                    maxLow = st.nextToken();
                } else if (mapName.equals(addr.get_feedLastName())) {
                    minLast = st.nextToken();
                    maxLast = st.nextToken();
               } else {
                    st.nextToken();
                    st.nextToken();
               }
           } catch (NoSuchElementException e) {
                String error = 
                    "Warning: not enough parameters in Feed config file for: "
                    + addr;
             //   Log.error(this, error);
               // break;
           // }
        }
        }
        v.add(minBid);
        v.add(maxBid);
        v.add(minAsk);
        v.add(maxAsk);
        v.add(minOpen);
        v.add(maxOpen);
        v.add(minClose);
        v.add(maxClose);
        v.add(minHigh);
        v.add(maxHigh);
        v.add(minLow);
        v.add(maxLow);
        v.add(minLast);
        v.add(maxLast);
    }
    
    public static void main(String args[]) {
    	RandomQuotes rand = new RandomQuotes();
    	try {
			rand.readQuotes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
