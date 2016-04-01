import java.awt.Color;
import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.commonUTIL;
import apps.window.operationwindow.jobpanl.TransferPanel;
import apps.window.tradewindow.FXPanels.BasicData;
import apps.window.tradewindow.FXPanels.FunctionalityD;
import apps.window.tradewindow.FXPanels.Swap;
import apps.window.tradewindow.FXPanels.TradeAttributesD;
import apps.window.tradewindow.FXPanels.outRight;
import apps.window.tradewindow.panelWindow.FeesPanel;
import apps.window.tradewindow.panelWindow.SDIPanel;
import apps.window.tradewindow.panelWindow.TaskPanel;
import beans.Product;
import beans.Trade;
import beans.Users;
import dsServices.RemoteBOProcess;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;


//VS4E -- DO NOT REMOVE THIS LINE!
public class FXTradePanelD extends JPanel {

	
	BasicData basicData = new BasicData();
	 public BasicData getBasicData() {
		if (basicData == null) {
			basicData = new BasicData();
			basicData.setBorder(new LineBorder(Color.black, 1, false));
		}
		return basicData;
	}

	public void setBasicData(BasicData basicData) {
		this.basicData = basicData;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getCounterPartyID() {
		return counterPartyID;
	}

	public void setCounterPartyID(int counterPartyID) {
		this.counterPartyID = counterPartyID;
	}

	public int getTraderID() {
		return traderID;
	}

	public void setTraderID(int traderID) {
		this.traderID = traderID;
	}

	public SDIPanel getSdiPanel() {
		return sdiPanel;
	}

	public void setSdiPanel(SDIPanel sdiPanel) {
		this.sdiPanel = sdiPanel;
	}

	public FeesPanel getFeesPanel() {
		return feesPanel;
	}

	public void setFeesPanel(FeesPanel feesPanel) {
		this.feesPanel = feesPanel;
	}

	public TaskPanel getTaskPanel() {
		return taskPanel;
	}

	public void setTaskPanel(TaskPanel taskPanel) {
		this.taskPanel = taskPanel;
	}

	public String getPrimaryCurrency() {
		return primaryCurrency;
	}

	public void setPrimaryCurrency(String primaryCurrency) {
		this.primaryCurrency = primaryCurrency;
	}

	public String getQuotingCurrency() {
		return quotingCurrency;
	}

	public void setQuotingCurrency(String quotingCurrency) {
		this.quotingCurrency = quotingCurrency;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductSubType() {
		return productSubType;
	}

	public void setProductSubType(String productSubType) {
		this.productSubType = productSubType;
	}

	public TransferPanel getTransferPanel() {
		return transferPanel;
	}

	public void setTransferPanel(TransferPanel transferPanel) {
		this.transferPanel = transferPanel;
	}

	public String getCurrencyPair() {
		return currencyPair;
	}

	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public RemoteProduct getRemoteProduct() {
		return remoteProduct;
	}

	public void setRemoteProduct(RemoteProduct remoteProduct) {
		this.remoteProduct = remoteProduct;
	}

	public Swap getSwap() {
		if (swap == null) {
			swap = new Swap();
			swap.setBorder(new LineBorder(Color.black, 1, false));
		}
		return swap;
	}

	public void setSwap(Swap swap) {
		this.swap = swap;
	}

	public outRight getOut() {
		if (out == null) {
			out = new outRight();
			out.setBorder(new LineBorder(Color.black, 1, false));
		}
		return out;
	}

	public void setOut(outRight out) {
		this.out = out;
	}

	public TradeAttributesD getAttributes() {
		if (attributes == null) {
			attributes = new TradeAttributesD();
			attributes.setBorder(new LineBorder(Color.black, 1, false));
		}
		return attributes;
	}

	public void setAttributes(TradeAttributesD attributes) {
		this.attributes = attributes;
	}

	public RemoteBOProcess getRemoteBO() {
		return remoteBO;
	}

	public void setRemoteBO(RemoteBOProcess remoteBO) {
		this.remoteBO = remoteBO;
	}

	public FunctionalityD getFunctionality() {
		if (functionality == null) {
			functionality = new FunctionalityD();
			functionality.setBorder(new LineBorder(Color.black, 1, false));
		}
		return functionality;
	}

	public void setFunctionality(FunctionalityD functionality) {
		this.functionality = functionality;
	}

	public TableCellRenderer getDefaultRenderer() {
		return defaultRenderer;
	}

	public void setDefaultRenderer(TableCellRenderer defaultRenderer) {
		this.defaultRenderer = defaultRenderer;
	}

	public Hashtable getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(Hashtable attributeValue) {
		this.attributeValue = attributeValue;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public RemoteTrade getRemoteTrade() {
		return remoteTrade;
	}

	public void setRemoteTrade(RemoteTrade remoteTrade) {
		this.remoteTrade = remoteTrade;
	}

	public RemoteBOProcess getBoremote() {
		return boremote;
	}

	public void setBoremote(RemoteBOProcess boremote) {
		this.boremote = boremote;
	}

	public RemoteTask getRemoteTask() {
		return remoteTask;
	}

	public void setRemoteTask(RemoteTask remoteTask) {
		this.remoteTask = remoteTask;
	}

	public RemoteReferenceData getRemoteReference() {
		return remoteReference;
	}

	public void setRemoteReference(RemoteReferenceData remoteReference) {
		this.remoteReference = remoteReference;
	}

	public boolean isFavEnableFlag() {
		return favEnableFlag;
	}

	public void setFavEnableFlag(boolean favEnableFlag) {
		this.favEnableFlag = favEnableFlag;
	}

	int bookId =0;
	 int counterPartyID =0;
	 int traderID =0;
	 SDIPanel sdiPanel = null;
	 FeesPanel feesPanel = null;
	 TaskPanel taskPanel = null; 
	 String primaryCurrency = "";
	 String quotingCurrency = "";
	 String productType = "FX";
	 String productSubType = "FXFORWARD";
	 TransferPanel transferPanel;
	 String currencyPair = "";
	 Trade trade = null;
	 Product product = null;
	 RemoteProduct remoteProduct = null;
	 Swap swap = new Swap();
	 outRight out = new outRight();
	 TradeAttributesD attributes = new TradeAttributesD();
	 RemoteBOProcess remoteBO;
	 FunctionalityD functionality = new FunctionalityD();
	 TableCellRenderer defaultRenderer;
	 javax.swing.DefaultComboBoxModel actionstatus = new javax.swing.DefaultComboBoxModel();
	 Hashtable  attributeValue = new Hashtable();
	 DefaultTableModel attributeModel = null;
	 private JTable __table;
	 Users user = null;
	 public static  ServerConnectionUtil de = null;
	 RemoteTrade remoteTrade ;
		RemoteBOProcess boremote;
		RemoteTask remoteTask;
		RemoteReferenceData remoteReference;
		boolean favEnableFlag = false;
	
	
	
	
	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private RollPanel jPanel5;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public FXTradePanelD() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getBasicData(), new Constraints(new Leading(7, 835, 10, 10), new Leading(8, 90, 10, 10)));
		add(getOut(), new Constraints(new Leading(5, 838, 12, 12), new Leading(101, 87, 12, 12)));
		add(getJPanel5(), new Constraints(new Leading(483, 360, 10, 10), new Leading(193, 89, 10, 10)));
		add(getSwap(), new Constraints(new Leading(7, 12, 12), new Leading(193, 89, 12, 12)));
		add(getFunctionality(), new Constraints(new Leading(7, 843, 10, 10), new Leading(294, 10, 10)));
		add(getAttributes(), new Constraints(new Leading(860, 329, 12, 12), new Leading(4, 536, 10, 10)));
		setSize(1201, 561);
	}

	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new RollPanel();
			jPanel5.setBorder(new LineBorder(Color.black, 1, false));
			jPanel5.setLayout(new GroupLayout());
		}
		return jPanel5;
	}

	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setBorder(new LineBorder(Color.black, 1, false));
			jPanel4.setLayout(new GroupLayout());
		}
		return functionality;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setBorder(new LineBorder(Color.black, 1, false));
			jPanel3.setLayout(new GroupLayout());
		}
		return swap;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(new LineBorder(Color.black, 1, false));
			jPanel2.setLayout(new GroupLayout());
		}
		return out;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(new LineBorder(Color.black, 1, false));
			jPanel1.setLayout(new GroupLayout());
		}
		return basicData;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(new LineBorder(Color.black, 1, false));
			jPanel0.setLayout(new GroupLayout());
		}
		return attributes;
	}
	
	public void init() {
		de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
	   	 try {
	   		remoteTrade = (RemoteTrade) de.getRMIService("Trade");
	   		boremote = (RemoteBOProcess) de.getRMIService("BOProcess");
	   		remoteTask = (RemoteTask) de.getRMIService("Task");
	   		remoteReference = (RemoteReferenceData) de.getRMIService("ReferenceData");
	   		remoteProduct = (RemoteProduct) de.getRMIService("Product");
	   		       product = (Product) remoteProduct.selectProductOnType(productType, productSubType);     
	   		    remoteBO = (RemoteBOProcess) de.getRMIService("BOProcess");
	   	//	trade = new Trade();
			//	System.out.println(remoteTrade);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
