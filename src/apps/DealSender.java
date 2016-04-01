package apps;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import constants.CommonConstants;
import constants.logConstants;

import logAppender.ProductAppender;
import logAppender.TradeAppender;

import productPricing.BONDPricing;
import productPricing.pricingUtil.BondCashFlow;
import util.commonUTIL;
import beans.Book;
import beans.Coupon;
import beans.Deal;
import beans.DealBean;
import beans.DealFXBean;
import beans.DealMMBean;
import beans.DealRepoBean;
import beans.Fees;
import beans.FeesUploader;
import beans.HelperBean;
import beans.LegalEntity;
import beans.Product;
import beans.ProductUploaderBean;
import beans.Trade;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;

public class DealSender {

	RemoteTrade remoteTrade;
	RemoteReferenceData remoteRefernce;
	RemoteProduct remoteProduct;

	Hashtable attributeValue = new Hashtable();

	BONDPricing pricing = new BONDPricing();

	public Trade buildTrade(Deal beanB) {
		DealBean bean = (DealBean) beanB;
		if (bean == null) {
			commonUTIL.display("DealSender ", "Bean is null");
			return null;
		}
		commonUTIL
				.display("Starting Deal Feed", bean.getOrderNumber() + ">>>>");
		Trade trade = new Trade();
		attributeValue = new Hashtable();
		int bookid = getBook(bean.getMemberName().trim());
		if (bookid == 0) {
			commonUTIL.display("DealSender",
					"Book Not found " + bean.getMemberName());
			return null;
		}
		int cpid = getLEid(bean.getMember().trim(), 0);

		if (cpid == 0) {
			commonUTIL.display("DealSender",
					"CounterParty Not found " + bean.getMember());
			return null;
		}
		int traderid = getLEid(bean.getDEALERname().trim(), 0);
		if (traderid == 0) {
			commonUTIL.display("DealSender",
					"Trader Not found " + bean.getDEALERname());
			return null;
		}
		Product productid = getProductId("ISIN=" + bean.getISIN().trim());
		if (productid == null) {
			commonUTIL.display("DealSender", "Product  Not found on ISIN "
					+ "ISIN=" + bean.getISIN());
			return null;
		}
		setAttributes("Market", bean.getMarket());
		setAttributes("SubMarket", bean.getSubMarket());
		setAttributes("OrderId", bean.getOrderNumber());
		setAttributes("TradeID", bean.getTradeNumber());
		setAttributes("Genspec", bean.getGenspec());
		setAttributes("Late PaymentInterest", bean.getPaymentDate());
		setAttributes("ISIN", bean.getISIN());
		setAttributes("Trade DateTime",
				bean.getTradeDate() + bean.getTradeTime());
		setAttributes("Sett Consideration (Rs)", bean.getSettConsiderationRs());
		trade.setBookId(bookid);

		trade.setCpID(cpid);
		trade.setTraderID(traderid);
		trade.setProductId(productid.getId());
		trade.setAction("NEW");
		trade.setStatus("NONE");
		trade.setId(0);
		trade.setPrice(new Double(bean.getTradePrice()).doubleValue());
		trade.setYield(new Double(bean.getTradeYield()).doubleValue());
		trade.setTradeAmount(new Double(bean.getTradeAmount()).intValue());
		trade.setProductType(productid.getProdcutShortName());
		trade.setAttributes(getAttributes());
		trade.setType(bean.getTradeType());
		trade.setTradeDate(commonUTIL.getCurrentDateInString());
		trade.setDelivertyDate(bean.getSettlement());
		trade.setEffectiveDate(bean.getSettlement());
		trade.setCurrency("INR");
		trade.setEffectiveDate(trade.getTradeDate());
		trade.setTradedesc(productid.getProductname());
		// System.out.println("ppppp "+productid.getProductname());
		// System.out.println(trade.getTradedesc());
		trade.setTradedesc1(productid.getProdcutShortName());
		trade.setProductType(productid.getProductType());
		trade.setQuantity(getQuantity(trade, productid,
				getCoupon(productid.getId())));
		if (trade.getType().equalsIgnoreCase("BUY"))
			trade.setNominal(trade.getQuantity() * trade.getPrice());
		else
			trade.setNominal((trade.getQuantity() * -1) * trade.getPrice());

		trade.setUserID(7);
		commonUTIL.display("*******************************",
				bean.getTradeType());
		attributeValue = null;
		commonUTIL.display("End Deal Feed", bean.getOrderNumber() + ">>>>");
		return trade;
	}

	public Trade buildFXTrade(Deal Fxbean) {
		DealFXBean bean = (DealFXBean) Fxbean;
		if (bean == null) {
			commonUTIL.display("DealSender ", "Bean is null");
			return null;
		}
		commonUTIL.display("Starting Deal Feed for ", bean.getTradeNumber()
				+ ">>>>");
		Trade trade = new Trade();
		attributeValue = new Hashtable();
		int bookid = getBook(bean.getMemberName().trim());
		if (bookid == 0) {
			commonUTIL.display("DealSender",
					"Book Not found " + bean.getMemberName());
			return null;
		}
		int cpid = getLEid(bean.getMember().trim(), 0);

		if (cpid == 0) {
			commonUTIL.display("DealSender",
					"CounterParty Not found " + bean.getMember());
			return null;
		}
		int traderid = getLEid(bean.getDEALERname().trim(), 0);
		if (traderid == 0) {
			commonUTIL.display("DealSender",
					"Trader Not found " + bean.getDEALERname());
			return null;
		}
		trade.setProductType("FX");
		int productid = getProductID(trade.getProductType(), bean
				.getTradeType().toUpperCase());
		if (productid == 0) {
			// commonUTIL.display("DealSender", "Product  Not found on ISIN " +
			// "ISIN="+bean.getISIN());
			return null;
		}
		trade.setProductId(productid);
		setAttributes("Market", bean.getMarket());
		// setAttributes("SubMarket", bean.getSubMarket());
		setAttributes("External ID", bean.getTradeNumber());
		// setAttributes("External ID",bean.getTradeNumber());
		// setAttributes("Genspec", bean.getGenspec());
		// setAttributes("Late PaymentInterest",bean.getPaymentDate());
		// setAttributes("ISIN",bean.getISIN());
		setAttributes("Trade Date", bean.getTradeDate() + " "+ bean.getTradeTime());
		// setAttributes("Sett Consideration (Rs)",
		// bean.getSettConsiderationRs());
		trade.setBookId(bookid);
		trade.setAttributes(getAttributes());
		trade.setPrice(Double.valueOf(bean.getSpotPrice()).doubleValue());

		trade.setType(bean.getType());
		if (trade.getType().equalsIgnoreCase("BUY")) {
			trade.setQuantity(Double.valueOf(bean.getQuantity()).doubleValue());
			trade.setNominal(trade.getPrice() * (trade.getQuantity() * -1));
		} else {
			trade.setQuantity(Double.valueOf(bean.getQuantity()).doubleValue()
					* -1);
			trade.setNominal(trade.getPrice() * (trade.getQuantity() * -1));
		}
		trade.setTradedesc(bean.getCurrencyPair());
		trade.setCurrency(bean.getCurrency());
		trade.setTradedesc1(bean.getTradeType().toUpperCase());
		trade.setTradeDate(bean.getTradeDate() + " " + bean.getTradeTime());
		trade.setDelivertyDate(bean.getMaturityDate());
		trade.setEffectiveDate(bean.getMaturityDate());
		trade.setCpID(cpid);
		trade.setTraderID(traderid);
	//	trade.setAttributes(bean.getAttributes());
		FeesUploader feeup = bean.getFees();
		if(feeup != null) {
			Vector<Fees> fees = new Vector<Fees>();
			Fees fee = new Fees();
			fee.setAmount(feeup.getAmount());
			fee.setLeRole(feeup.getLeRole());
			int brokerID = getLEid(feeup.getLeCode(),0);
			fee.setLeID(brokerID);
			fee.setFeeType(feeup.getFeeType());
			fee.setStartDate(feeup.getStartDate());
			fee.setEndDate(fee.getEndDate());
			fee.setFeeDate(feeup.getStartDate());
			fee.setCurrency(feeup.getCurrency());
			fees.add(fee);
			trade.setFees(fees);
			
		}
		trade.setAttribute("Trade Date", trade.getTradeDate()); 
		trade.setAttributes(getAttributes());
		trade.setAction("NEW");
		trade.setStatus("NONE");
		trade.setUserID(4);
		// trade.setProductId(productid.getId());
		

		trade.setId(0);
		return trade;
	}

	public Trade buildMMTrade(Deal mmBean) {

		DealMMBean bean = (DealMMBean) mmBean;

		if (bean == null) {
			commonUTIL.display("DealSender ", "Bean is null");
			return null;
		}
		commonUTIL.display("Starting Deal Feed for ",""
				+ ">>>>");
		Trade trade = new Trade();
		
		Product productBean = bean.getProductBean();
		Coupon couponBean = bean.getCouponBean();
		
		attributeValue = new Hashtable();
		int bookid = getBook(bean.getBookName().trim());
		if (bookid == 0) {
			commonUTIL.display("DealSender",
					"Book Not found " + bean.getBookName());
			return null;
		}
		int cpid = getLEid(bean.getCpName().trim(), 0);

		if (cpid == 0) {
			commonUTIL.display("DealSender",
					"CounterParty Not found " + bean.getCpName());
			return null;
		}
		int traderid = getLEid(bean.getTraderName().trim(), 0);
		if (traderid == 0) {
			commonUTIL.display("DealSender",
					"Trader Not found " + bean.getTraderName());
			return null;
		}
		trade.setProductType("MM");
		
		int productid = 0;
		try {
			productid = remoteProduct.saveProduct(productBean, couponBean);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (productid == 0) {
			// commonUTIL.display("DealSender", "Product  Not found on ISIN " +
			// "ISIN="+bean.getISIN());
			return null;
		} else {
			
			trade.setProductId(productid);
			//setAttributes("Market", bean.getMarket());
			// setAttributes("SubMarket", bean.getSubMarket());
			//setAttributes("External ID", bean.getTradeNumber());
			// setAttributes("External ID",bean.getTradeNumber());
			// setAttributes("Genspec", bean.getGenspec());
			// setAttributes("Late PaymentInterest",bean.getPaymentDate());
			// setAttributes("ISIN",bean.getISIN());
			//setAttributes("Trade Date", bean.getTradeDate() + bean.getTradeTime());
			// setAttributes("Sett Consideration (Rs)",
			// bean.getSettConsiderationRs());
			trade.setBookId(bookid);
			
			trade.setPrice(Double.valueOf(bean.getMarketPrice()).doubleValue());
			trade.setTradeAmount(new Double(bean.getTradeAmount()).doubleValue());
			trade.setType(bean.getType());
			
			trade.setTradedesc(productBean.getProductname());
			trade.setCurrency(bean.getTradeCurrency());
			trade.setTradedesc1(productBean.getProdcutShortName());
			// no trade date for MM in excel file so settle date is user and no tradeTime
			//trade.setTradeDate(bean.getTradeDate() + " " + bean.getTradeTime());
			trade.setTradeDate(commonUTIL.getCurrentDateTime(CommonConstants.SDF_DATE_TIME_FORMAT));
			trade.setDelivertyDate(bean.getSettleDate());
			trade.setEffectiveDate(bean.getAmortizationEndDate());
			trade.setCpID(cpid);
			trade.setTraderID(traderid);
			trade.setAction("NEW");
			trade.setStatus("NONE");
			trade.setAttribute("Trade Date", trade.getTradeDate()); 
			trade.setAttributes(getAttributes());
			trade.setAmoritizationData(getAmortizationData(bean));
		}
		
		trade.setId(0);
		return trade;
	}
	
	public Trade buildRepoTrade(Deal repoBean) {

		DealRepoBean bean = (DealRepoBean) repoBean;

		if (bean == null) {
			commonUTIL.display("DealSender ", "Bean is null");
			return null;
		}
		commonUTIL.display("Starting Deal Feed for ",""
				+ ">>>>");
		Trade trade = new Trade();
		
		Product productBean = bean.getProductBean();
		Coupon couponBean = bean.getCouponBean();
		
		attributeValue = new Hashtable();
		int bookid = getBook(bean.getBookName().trim());
		if (bookid == 0) {
			commonUTIL.display("DealSender",
					"Book Not found " + bean.getBookName());
			return null;
		}
		int cpid = getLEid(bean.getCpName().trim(), 0);

		if (cpid == 0) {
			commonUTIL.display("DealSender",
					"CounterParty Not found " + bean.getCpName());
			return null;
		}
		int traderid = getLEid(bean.getTraderName().trim(), 0);
		if (traderid == 0) {
			commonUTIL.display("DealSender",
					"Trader Not found " + bean.getTraderName());
			return null;
		}
		trade.setProductType("REPO");
		
		Product collateralProduct = getProductId("ISIN=" + bean.getCollateralISIN().trim());
		if (collateralProduct == null) {
			commonUTIL.display("DealSender",
					"Collateral Product Not found " + bean.getCollateralISIN());
			return null;
		}
		
		int productid = 0;
		try {
			productid = remoteProduct.saveProduct(productBean, couponBean);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (productid == 0) {
			commonUTIL.display("DealSender", "There was an error while saving a Product, having following " +
					"Collateral ISIN no. ");
			// "ISIN="+bean.getISIN());
			return null;
		} else {
			
			trade.setProductId(productid);
			//setAttributes("Market", bean.getMarket());
			// setAttributes("SubMarket", bean.getSubMarket());
			//setAttributes("External ID", bean.getTradeNumber());
			// setAttributes("External ID",bean.getTradeNumber());
			// setAttributes("Genspec", bean.getGenspec());
			// setAttributes("Late PaymentInterest",bean.getPaymentDate());
			// setAttributes("ISIN",bean.getISIN());
			//setAttributes("Trade Date", bean.getTradeDate() + bean.getTradeTime());
			// setAttributes("Sett Consideration (Rs)",
			// bean.getSettConsiderationRs());
			trade.setBookId(bookid);
			trade.setAttributes(getAttributes());
			trade.setPrice(Double.valueOf(bean.getMarketPrice()).doubleValue());
			trade.setTradeAmount(new Double(bean.getTradeAmount()).doubleValue());
			trade.setType(bean.getType());
			
			trade.setTradedesc(productBean.getProductname());
			trade.setCurrency(bean.getTradeCurrency());
			trade.setTradedesc1(productBean.getProdcutShortName());
			// no trade date for MM in excel file so settle date is user and no tradeTime
			//trade.setTradeDate(bean.getTradeDate() + " " + bean.getTradeTime());
			trade.setTradeDate(bean.getSettleDate());
			trade.setDelivertyDate(bean.getSettleDate());
			trade.setEffectiveDate(bean.getSettleDate());
			trade.setCpID(cpid);
			trade.setTraderID(traderid);
			trade.setAction("NEW");
			trade.setStatus("NONE");
			trade.setAmoritizationData(getAmortizationForRepo(bean));
			
		}
		
		trade.setId(0);
		return trade;
	}
	
	private String getAmortizationForRepo(DealRepoBean bean) {
		
		return new StringBuffer("compundingFrequency=").append(bean.getCompoundingFrequency()).toString();
		
	}
	private String getAmortizationData(DealMMBean bean) {
		//freq=SA;openTerm=false;loanTerm=false;=Mortgage;=QTR
		
		return new StringBuffer("Type=")
					.append(bean.getAmortizationType())
					.append(";startDate=")
					.append(bean.getAmortizationStartDate())
					.append(";endDate=")
					.append(bean.getAmortizationEndDate())
					.append(";rate=")
					.append(bean.getAmortizationRate())
					.append(";freq=")
					.append(bean.getAmortizationFrequency())
					.append(";openTerm=false")
					.append(";loanTerm=false")
					.append(";amortizingtype=")
					.append(bean.getCompoundingType())
					.append(";amortizationValue=")
					.append(bean.getAmortizationFrequency())
					.toString();
					
					
		
		
	}
	private int getBook(String name) {
		// TODO Auto-generated method stub
		int i = 0;
		Book book;
		String sql = " book_name = '" + name.trim() + "'";
		try {

			Vector le = (Vector) remoteRefernce.getBookWhere(sql);
			if (le.size() > 0) {
				i = ((Book) le.elementAt(0)).getBookno();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

	private double getQuantity(Trade trade, Product product, Coupon coupon) {
		double quantity = 0.0;
		pricing.price(trade, product, coupon);
		BondCashFlow cashFlow = pricing.generateCashFlow();
		pricing.setTradeData(cashFlow);

		quantity = pricing.getQuantity();
		return quantity;

	}

	private int getLEid(String name, int id) {

		int i = 0;
		String sql = "  alias = '" + name.trim() + "'";
		try {
			Vector le = (Vector) remoteRefernce.selectLEonWhereClause(sql);
			if (le.size() > 0) {
				i = ((LegalEntity) le.elementAt(0)).getId();
			} else {
				sql = " id = " + id;
				le = (Vector) remoteRefernce.selectLEonWhereClause(sql);
				if (le.size() > 0) {
					i = ((LegalEntity) le.elementAt(0)).getId();
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

	private Product getProductId(String name) {
		Product prod = null;

		String sql = " attributes like '%" + name + "%'";
		try {
			Vector le = (Vector) remoteProduct.selectProductWhereClaus(sql);
			if (le != null && le.size() > 0)
				prod = (Product) le.elementAt(0);
			return prod;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private int getProductID(String productType, String productsubtype) {
		Product prod = null;
		String sql = " producttype =  '" + productType
				+ "'  and productshortname = '" + productsubtype + "'";
		try {
			Vector le = (Vector) remoteProduct.selectProductWhereClaus(sql);
			if (le != null && le.size() > 0)
				prod = (Product) le.elementAt(0);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return prod.getId();
	}

	private Coupon getCoupon(int productID) {
		Coupon cp = null;

		try {
			Vector coup = (Vector) remoteProduct.getCoupon(productID);
			if (coup != null && coup.size() > 0)
				cp = (Coupon) coup.elementAt(0);
			return cp;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void setAttributes(String name, String value) {
		attributeValue.put(attributeValue.size() + 1, name + "=" + value + ";");
	}

	public String getAttributes() {
		String attributes = "";
		// Hashtable attributeValue = new Hashtable();
		Collection c = attributeValue.values();
		Iterator itr = c.iterator();
		while (itr.hasNext()) {
			attributes = attributes + (String) itr.next();

		}
		return attributes;
	}

	public RemoteTrade getRemoteTrade() {
		return remoteTrade;
	}

	public void setRemoteTrade(RemoteTrade remoteTrade) {
		this.remoteTrade = remoteTrade;
	}

	public RemoteReferenceData getRemoteRefernce() {
		return remoteRefernce;
	}

	public void setRemoteRefernce(RemoteReferenceData remoteRefernce) {
		this.remoteRefernce = remoteRefernce;
	}

	public RemoteProduct getRemoteProduct() {
		return remoteProduct;
	}

	public void setRemoteProduct(RemoteProduct remoteProduct) {
		this.remoteProduct = remoteProduct;
	}

	/*
	 * public boolean validateTrade(Deal bean) {
	 * 
	 * 
	 * boolean isTradeValid = true;
	 * 
	 * StringBuffer errorMsg = new StringBuffer(
	 * "Following data missing for ISIN no. "
	 * ).append(bean.getIsin()).append(": ");
	 * 
	 * 
	 * if (bean == null) {
	 * 
	 * errorMsg.append("beanEmpty "); TradeAppender.printLog(logConstants.ERROR,
	 * errorMsg.toString()); isTradeValid = false; return isTradeValid;
	 * 
	 * }
	 * 
	 * if ( bean.getLeName() == "" ) {
	 * 
	 * errorMsg.append("leName "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getBookName() == "" ) {
	 * 
	 * errorMsg.append("bookName "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getTraderName().equals("") ) {
	 * 
	 * errorMsg.append("traderName "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getType().equals("") ) {
	 * 
	 * errorMsg.append("direction "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getPrice() == 0.0 ) {
	 * 
	 * errorMsg.append("marketPrice "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getTradeAmount() == 0.0 ) {
	 * 
	 * errorMsg.append("tradeAmount "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getDeliveryDate().equals("") ) {
	 * 
	 * errorMsg.append("settleDate "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getExternalTradeId() == null ) {
	 * 
	 * errorMsg.append("externalTradeId"); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getTradeDate().equals("") ) {
	 * 
	 * errorMsg.append("tradeDate "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getIsin().equals("") ) {
	 * 
	 * errorMsg.append("isin "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getCommon().equals("") ) {
	 * 
	 * errorMsg.append("common "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getQuantity() == 0.0 ) {
	 * 
	 * errorMsg.append("quantity "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getCouponAccrualDays() == 0.0 ) {
	 * 
	 * errorMsg.append("couponAccrualDays "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getAccrualRate() == 0.0 ) {
	 * 
	 * errorMsg.append("couponAccrualRate "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getAccrualAmount().equals("") ) {
	 * 
	 * errorMsg.append("couponAccrualAmount "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getNextCouponDate().equals("") ) {
	 * 
	 * errorMsg.append("nextCouponDate "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getPreviousCouponDate().equals("")) {
	 * 
	 * errorMsg.append("previousCouponDate "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getTradeDirtyPrice() == 0.0 ) {
	 * 
	 * errorMsg.append("tradeDirtyPrice "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getTradeCleanPrice() == 0.0 ) {
	 * 
	 * errorMsg.append("tradeCleanPrice "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( bean.getTotalAmount() == 0.0 ) {
	 * 
	 * errorMsg.append("totalAmount "); isTradeValid = false;
	 * 
	 * }
	 * 
	 * if ( !isTradeValid ) {
	 * 
	 * TradeAppender.printLog(logConstants.ERROR, errorMsg.toString());
	 * 
	 * }
	 * 
	 * return isTradeValid;
	 * 
	 * }
	 */

}
