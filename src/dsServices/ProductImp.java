package dsServices;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import constants.ProductConstants;

import util.commonUTIL;

import dbSQL.CouponSQL;
import dbSQL.FXOptionProductSQL;
import dbSQL.FutureContractSQL;
import dbSQL.FutureContractWiseSQL;
import dbSQL.ProductSQL;
import dbSQL.dsSQL;

import beans.Coupon;
import beans.FutureContract;
import beans.FutureProduct;
import beans.Product;
import beans.ProductFXOption;

public class ProductImp implements RemoteProduct {

	@Override
	public int saveProduct(Product product, Coupon coupon)
			throws RemoteException {
		// TODO Auto-generated method stub

		int i = ProductSQL.save(product, dsSQL.getConn());
		if (i > 0) {
			coupon.setProductId(i);
			CouponSQL.save(coupon, dsSQL.getConn());
		}

		return i;
	}
	@Override
	public Collection getALLFutureProduct()
			throws RemoteException {
		// TODO Auto-generated method stub
        String sql = " producttype = 'FUTURECONTRACT'";
		Vector futureProduct = (Vector) ProductSQL.selectonWhereClause(sql, dsSQL.getConn());
		

		return futureProduct;
	}
	@Override
	public Collection getALLFutureContracts()
			throws RemoteException {
		// TODO Auto-generated method stub
        String sql = " producttype = 'FUTURECONTRACT'";
		Vector futureProduct = (Vector) FutureContractWiseSQL.selectALL(dsSQL.getConn());
		

		return futureProduct;
	}
	@Override
	public Collection selectALLProducts() throws RemoteException {
		// TODO Auto-generated method stub
		return ProductSQL.selectALL(dsSQL.getConn());
	}

	@Override
	public Product selectProduct(int productID) throws RemoteException {
		// TODO Auto-generated method stub
		Product product = ProductSQL.selectProduct(productID, dsSQL.getConn());
		if(product.getProductType().equalsIgnoreCase(ProductConstants.FXOPTION)) {
			product.setFXOptionProduct(getFXOptionProduct(product));
		} else {
		product.setCoupon(getCoupon(product));
		}
		return product;
	}

	private ProductFXOption getFXOptionProduct(Product product) {
		// TODO Auto-generated method stub
		return FXOptionProductSQL.select(product.getId(), dsSQL.getConn());
		
	}
	@Override
	public int maxProductId() throws RemoteException {
		// TODO Auto-generated method stub
		return ProductSQL.selectMaxID(dsSQL.getConn());
	}

	@Override
	public boolean removeProduct(Product product) throws RemoteException {
		// TODO Auto-generated method stub
		return ProductSQL.delete(product, dsSQL.getConn());
	}

	@Override
	public boolean updateProduct(Product product, Coupon coupon)
			throws RemoteException {
		// TODO Auto-generated method stub
		boolean productSaved = false;

		productSaved = ProductSQL.update(product, dsSQL.getConn());

		System.out.println("update " + productSaved);
		if (productSaved) {
			// coupon.setProductId(0);
			CouponSQL.update(coupon, dsSQL.getConn());
		}

		return productSaved;
	}

	@Override
	public Collection getCoupon(int productID) throws RemoteException {
		// TODO Auto-generated method stub
		return CouponSQL.selectcouponOnProduct(productID, dsSQL.getConn());

	}

	@Override
	public Collection selectProductWhereClaus(String sql)
			throws RemoteException {

		return ProductSQL.selectonWhereClause(sql, dsSQL.getConn());
	}

	@Override
	public Product selectProductOnType(String productType, String productSubType)
			throws RemoteException {

		return ProductSQL.selectonWhereClauseOnProductSubType(productType,
				productSubType, dsSQL.getConn());
	}
	
	@Override
	public int saveFutureProduct(FutureProduct futureProduct) throws RemoteException {
		// TODO Auto-generated method stub
		int id = 0;
		Product underlyingProduct = futureProduct.getUnderlyingProduct();
		if(underlyingProduct.getId() == 0) {
			int underlyingID = ProductSQL.save(underlyingProduct, dsSQL.getConn());
			futureProduct.setUnderlying_productID(underlyingID);
			id =  FutureContractSQL.save(futureProduct, dsSQL.getConn());
			
		}
				
	
				    
		return id;
	}

/*	@Override
	public Collection selectALLProducts() throws RemoteException {
		// TODO Auto-generated method stub
		return ProductSQL.selectALL(dsSQL.getConn());
	}*/

	
	@Override
	public FutureProduct selectFutureProduct(int  productID) throws RemoteException {
		// TODO Auto-generated method stub
		return  FutureContractSQL.selectProduct(productID,dsSQL.getConn());
	}
	@Override
	public Collection selectUnderlyingFutureProduct(int  productID) throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " UNDERLYING_PRODUCTID = "+productID;
		return  FutureContractSQL.selectonWhereClause(sql, dsSQL.getConn());
	}
	@Override
	public FutureContract getFutureContractQuoteName(String quoteName)
			throws RemoteException {
		// TODO Auto-generated method stub
		FutureContract futureContract = null;
		String sql = "QUOTE_NAME = '"+quoteName+"'";
		Vector fu = (Vector) FutureContractWiseSQL.selectonWhereClause(sql,dsSQL.getConn());
		if(!commonUTIL.isEmpty(fu)) 
			futureContract = (FutureContract) fu.elementAt(0);
		return futureContract;
		
	}
	@Override
	public int saveFutureContract(FutureContract futureContract)
			throws RemoteException {
		if( getFutureContractQuoteName(futureContract.getQuoteName()) == null)
		   return  FutureContractWiseSQL.save(futureContract, dsSQL.getConn());
		else 
			return -1;
	}
	@Override
	public Collection getFutureProduct(String exchangeName)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection getFutureProduct(String exchangeName,
			String currency) throws RemoteException {
		String sql = "";
				if(!commonUTIL.isEmpty(currency)) 
					sql = " issuecurrency = '"+currency+"' and productname like '%"+exchangeName+"%' and producttype = 'FUTURECONTRACT'";
					else 
						sql = "  productname like '%"+exchangeName+"%' and producttype = 'FUTURECONTRACT'";
		return ProductSQL.selectonWhereClause(sql, dsSQL.getConn());
		// TODO Auto-generated method stub
		
	}
	@Override
	public Collection getFutureContract(String productName)
			throws RemoteException {
		// TODO Auto-generated method stub
	
		return FutureContractWiseSQL.getFutureContractOnProductName(productName, dsSQL.getConn());
	}
	@Override
	public Collection getFuturetContract(int productID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FutureProduct getFutureProductOnQuoteName(
			String futureContractQuoteName) throws RemoteException {
		// TODO Auto-generated method stub
		return FutureContractSQL.selectFutureProductOnQuoteName(futureContractQuoteName, dsSQL.getConn());
	}
	@Override
	public Coupon getCoupon(Product product) throws RemoteException {
		// TODO Auto-generated method stub
		return CouponSQL.getcouponOnProduct(product.getId(), dsSQL.getConn());
	}
	@Override
	public int saveProduct(Product product) throws RemoteException {
		// TODO Auto-generated method stub
		int productID = 0;
		if(product != null && product.getProductSubType().equalsIgnoreCase(ProductConstants.FXOPTION) ) {
			ProductFXOption fxOption = (ProductFXOption) product;
			if(fxOption != null) {
			 
			 
			
				product.setName(ProductConstants.FXOPTION+"."+fxOption.getOptionStyle()+"."+fxOption.getOptionType()+"."+fxOption.getExericseType()+"."+fxOption.getCurrencyBase()+"/"+fxOption.getCurrencyQuote()+"."+fxOption.getExpiryDate());
			 productID = ProductSQL.save(product, dsSQL.getConn());
			if(productID != 0) {
			    fxOption.setProduct_id(productID);
			    if(! FXOptionProductSQL.save(fxOption,dsSQL.getConn())) {
			    	productID = 0;
			    }
			    		
			    	
			}
			}
			
		}
		return productID;
	}
	@Override
	public boolean updateProduct(Product product) throws RemoteException {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(product != null && product.getProductType().equalsIgnoreCase(ProductConstants.FXOPTION) ) {
			ProductFXOption fxOption = (ProductFXOption) product;
			Product prod = new Product();
			prod.setId(fxOption.getId());
			prod.setProductType(ProductConstants.FXOPTION);
			
			prod.setName(ProductConstants.FXOPTION+"."+fxOption.getOptionStyle()+"."+fxOption.getOptionType()+"."+fxOption.getExericseType()+"."+fxOption.getCurrencyBase()+"/"+fxOption.getCurrencyQuote());
			if( ProductSQL.update(prod, dsSQL.getConn())) {
				flag = FXOptionProductSQL.update(fxOption, dsSQL.getConn());
			}
		}
		return flag;
	}
}
