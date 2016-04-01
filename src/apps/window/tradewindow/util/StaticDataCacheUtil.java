package apps.window.tradewindow.util;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Vector;

import util.ReferenceDataCache;
import util.RemoteServiceUtil;
import util.commonUTIL;

import dsServices.RemoteBOProcess;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;

import apps.window.operationwindow.jobpanl.FilterValues;
import beans.Book;
import beans.CurrencyDefault;
import beans.Favorities;
import beans.LegalEntity;
import beans.Product;
import beans.Sdi;

public class StaticDataCacheUtil {
	
	static	Hashtable<String, Vector> bookValues = new Hashtable<String, Vector>();
	static Hashtable<String, Vector> cpValues = new Hashtable<String, Vector>();
   public static final String PRODUCTDATA = "ProductData";
   public static final String DELETE_PRODUCT = "DELETE";
	static Hashtable<String, Vector>  traderValues = new Hashtable<String, Vector>();
	static Hashtable<String, String>  currency = new Hashtable<String, String>();
	static Hashtable<String, CurrencyDefault>  currencyDefault = new Hashtable<String, CurrencyDefault>();
	static Hashtable<String, Vector> dataValues = new Hashtable<String, Vector>();
	static Hashtable<String,Vector> productDefinationData  = new Hashtable<String, Vector>();

	static FilterValues filterValue = null;
	
	static RemoteTrade remoteTrade = null;
	static RemoteBOProcess remoteBO = null;
	static RemoteTask remoteTask = null;
	static RemoteReferenceData remoteReference = null;
	static RemoteProduct remoteProduct = null;
	
	static  {
		
		remoteReference = RemoteServiceUtil.getRemoteReferenceDataService();
		remoteTask = RemoteServiceUtil.getRemoteTaskService();
		remoteTrade = RemoteServiceUtil.getRemoteTradeService();
		remoteBO = RemoteServiceUtil.getRemoteBOProcessService();
		remoteProduct = RemoteServiceUtil.getRemoteProductService();
		filterValue = new FilterValues(remoteReference,remoteTrade,remoteTask,remoteBO);
	 }
	
	public static Vector getProductDefinationData() {
		Vector 	productD = null;
		try {
			productD = productDefinationData.get(PRODUCTDATA);
			if(commonUTIL.isEmpty(productD)) {
			String sql = " producttype ='BOND' and   productname like 'BOND%'";
			productD = (Vector) remoteProduct.selectProductWhereClaus(sql);
			productDefinationData.put(PRODUCTDATA,productD);
			}
			
		}catch(RemoteException e) {
			commonUTIL.displayError("StaticDataCacheUtil", "getProductDefData", e);
			return null;
		}
		return productD;
	}
	public static CurrencyDefault getCurrencyDefault(String currency) {
		CurrencyDefault currecyD = null;
		try {
			currecyD = currencyDefault.get(currency);
			if(currecyD == null) {
			currecyD = remoteReference.selectCurrencyDefault(currency);
			 
			 if(currecyD != null)
			currencyDefault.put(currency,currecyD);
			}
			
		}catch(RemoteException e) {
			commonUTIL.displayError("StaticDataCacheUtil", "getCurrencyDefault", e);
			return null;
		}
		return currecyD ;
	}
	public static synchronized void addProductToCache(Product product,String type) {
		if(!commonUTIL.isEmpty(productDefinationData)) {
			Vector productD = productDefinationData.get(PRODUCTDATA);
			productD = containProductID(productD,product.getId());
			if(type.equalsIgnoreCase(DELETE_PRODUCT)) {
			    productD.add(product); 
			    productDefinationData.put(PRODUCTDATA,productD);
			}
			
			
		}
		
	}
	
	private static Vector  containProductID(Vector productData,int productID) {
		boolean flag = false;
		int productAtID = 0;
		Vector productD = productData;
		for(int i=0;i<productData.size();i++) {
			Product product = (Product) productData.get(i);
			if(product.getId() == productID) {
				flag = true;
				productAtID = i; 
			}
		}
		if(flag) {
			productD.removeElementAt(productAtID);
		}
		return productD;
		 
	}
	private static Vector getDataOnUserFavourities(int userid,String type) {
		Favorities fav = new Favorities();
		fav.setType(type);
			 
		fav.setUserId(userid);
		Vector favData = null;
		if(type.equalsIgnoreCase("Book")) {
			favData = bookValues.get(userid+"_"+type);
			if(favData == null) {
				try {
					favData = (Vector) remoteReference.selectFavourites(fav);
					bookValues.put(userid+"_"+type, favData);
				}   catch(RemoteException e) {
						commonUTIL.displayError("StaticDataCacheUtil", "getDataOnFav", e);
					}
				}
		}
		if(type.equalsIgnoreCase("CounterParty")) {
			favData = cpValues.get(userid+"_"+type);
			if(favData == null) {
				try {
					favData = (Vector) remoteReference.selectFavourites(fav);
					cpValues.put(userid+"_"+type, favData);
				}   catch(RemoteException e) {
						commonUTIL.displayError("StaticDataCacheUtil", "getDataOnFav", e);
					}
				}
		}
		if(type.equalsIgnoreCase("Trader")) {
			favData = traderValues.get(userid+"_"+type);
			if(favData == null) {
				try {
					favData = (Vector) remoteReference.selectFavourites(fav);
					cpValues.put(userid+"_"+type, favData);
				}   catch(RemoteException e) {
						commonUTIL.displayError("StaticDataCacheUtil", "getDataOnFav", e);
					}
				}
		}
		
		return favData;
	
	
	}
	
	public static Vector<LegalEntity>  getUserFavLegalEntity(int userid,String type) {
		Vector<Favorities> favs = getDataOnUserFavourities(userid,type);
		Vector<LegalEntity> legalEntity = new Vector<LegalEntity>();
		for(int i=0;i<favs.size();i++) {
			 Favorities fav = (Favorities) favs.elementAt(i);
			 LegalEntity le = new LegalEntity();
			 le.setName(fav.getTypeName());
			 le.setId(Integer.valueOf(fav.getTypeValue()).intValue());
			
			 legalEntity.addElement(le);
		}
		return legalEntity;
		
	}
	public static Vector<Book> getUserFavBooks(int userid,String type) {
		Vector<Favorities> favs = getDataOnUserFavourities(userid,type);
		Vector<Book> books = new Vector<Book>();
		for(int i=0;i<favs.size();i++) {
			 Favorities fav = (Favorities) favs.elementAt(i);
			 Book book= new Book(); 
			 book.setBook_name(fav.getTypeName());
			 book.setBookno(Integer.valueOf(fav.getTypeValue()).intValue());
			
			 books.addElement(book);
		}
		return books;
		
	}
	
	public static Vector<LegalEntity> getUserFavTrader(int userid,String type) {
		Vector<Favorities> favs = getDataOnUserFavourities(userid,type);
		Vector<LegalEntity> legalEntity = new Vector<LegalEntity>();
		for(int i=0;i<favs.size();i++) {
			 Favorities fav = (Favorities) favs.elementAt(i);
			 LegalEntity le = new LegalEntity();
			 le.setName(fav.getTypeName());
			 le.setId(Integer.valueOf(fav.getTypeValue()).intValue());
			
			 legalEntity.addElement(le);
		}
		return legalEntity;
		
	}
	
	
	public static Vector getDomainValues(String domainName) {
		Vector domainData = null;
		domainData = dataValues.get(domainName);
		if (domainData == null) {

			try {
				domainData = (Vector) remoteReference.getStartUPData(domainName);
			} catch (RemoteException e) {
				
				commonUTIL.display("StaticDataCacheUtil", "getDomainValues");
			}
			if (domainData != null) {
				dataValues.put(domainName, domainData);
			}
		}
		return domainData;
	}
	
	public static Vector getBook() {
		return filterValue.getBooks();
	}
	
	
	
}
