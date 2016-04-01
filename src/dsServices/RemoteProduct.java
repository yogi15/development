package dsServices;

import java.rmi.RemoteException;
import java.util.Collection;
import java.rmi.Remote;

import beans.Coupon;
import beans.FutureContract;
import beans.FutureProduct;
import beans.Product;

public interface RemoteProduct  extends Remote {
	
	
	public int saveProduct(Product product,Coupon coupon) throws RemoteException;
	public int saveProduct(Product product) throws RemoteException;
	public boolean updateProduct(Product product) throws RemoteException;
	
	
	
	public Product selectProduct(int productID)throws RemoteException;
	public Collection  selectALLProducts() throws RemoteException;
	public boolean removeProduct(Product product) throws RemoteException;
	public boolean updateProduct(Product product,Coupon coupon) throws RemoteException;
	public int maxProductId() throws RemoteException;
	public Collection getCoupon(int productID) throws RemoteException;
	public  Coupon getCoupon(Product product) throws RemoteException;
	//public boolean saveCoupon(Coupon coupon)throws RemoteException	
	public Collection selectProductWhereClaus(String sql) throws RemoteException;
	public Product selectProductOnType(String productType, String productSubType)
			throws RemoteException;
	
	public int saveFutureProduct(FutureProduct futureProduct) throws RemoteException;
	public FutureProduct selectFutureProduct(int productID)throws RemoteException;
	public Collection getALLFutureProduct() throws RemoteException ;
	public Collection getFutureProduct(String exchangeName) throws RemoteException ;
	public FutureProduct getFutureProductOnQuoteName(String futureContractQuoteName) throws RemoteException ;
	public Collection getFutureProduct(String exchangeName,String currency) throws RemoteException ;
	public Collection getFutureContract(String productName) throws RemoteException ;
	public Collection getFuturetContract(int productID) throws RemoteException ;
	public Collection selectUnderlyingFutureProduct(int productID)
			throws RemoteException;
	public Collection getALLFutureContracts() throws RemoteException;
	public FutureContract getFutureContractQuoteName(String quoteName) throws RemoteException;
	
	public int saveFutureContract(FutureContract futureContract) throws RemoteException;
	
	

}
