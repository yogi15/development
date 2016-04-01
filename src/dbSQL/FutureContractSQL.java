package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.FutureProduct;
public class FutureContractSQL {
	
	final static private String tableName = "futurecontract_product";
	final static private String DELETE =
		"DELETE FROM " + tableName + "   where id =? ";
	final static private String INSERT =
		"INSERT into " + tableName + "(PRODUCTID,ID,UNDERLYING_PRODUCTID,UND_BENCHMARK_NAME,QUOTE_TYPE,QUOTE_DECIMALS,CONTRACT_SIZE,LOTS,TICKSIZE,TRADECONTRACT_NO,SETTLEMENT_METHOD,EXPIRY_DATE_RULE,RATE_INDEX_CODE,TIME_ZONE,TIME_MINUTE,LAST_TRADING_RULE,FIRST_DELIVERY_TRADING_RULE,LAST_DELIVERY_TRADING_RULE,FIRST_NOTIFICATION_RULE,LAST_NOTIFICATION_RULE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				//"issueDate,marturityDate,IssuerId,Country,IssuePrice,IssueCurrency,RedemptionPrice,RedemptionCurrency,FaceValue,code,dateddate,tenor ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	
	 private static String getUpdateSQL(FutureProduct product) {
	      String updateSQL = new StringBuffer("UPDATE ")
	      	.append(tableName)
	      	.append(" set productID= ").append(product.getProductID()).append(",")
	      	.append(" id=").append(product.getID()).append(",")
			.append(" underlying_productID=").append(product.getUnderlying_productID()).append(",")
			.append(" und_benchmark_name='").append(product.getUnd_benchmark_name()).append("',")
			.append(" quote_type='").append(product.getQuote_type()).append("',")
			.append(" quote_decimals=").append(product.getQuote_decimals()).append(",")
			.append(" contract_size=").append(product.getContract_size()).append(",")
			.append(" Lots=").append(product.getLots()).append(",")
			.append(" ticksize=").append(product.getTicksize()).append(",")
			.append(" tradecontract_no=").append(product.getTradecontract_no()).append(",")
			.append(" settlement_method='").append(product.getSettlement_method()).append("',")
			.append(" Expiry_Date_Rule='").append(product.getExpiry_Date_Rule()).append("',")
			.append(" Rate_index_code='").append(product.getRate_index_code()).append("',")
			.append(" time_zone='").append(product.getTime_zone()).append("',")
			.append(" time_minute='").append(product.getTime_minute()).append("',")
			.append(" last_Trading_rule='").append(product.getLast_Trading_rule()).append("',")
			.append(" first_delivery_Trading_rule='").append(product.getFirst_delivery_Trading_rule()).append("',")
			.append(" last_delivery_Trading_rule='").append(product.getLast_delivery_Trading_rule()).append("',")
			.append(" first_notification_rule='").append(product.getFirst__notification_rule()).append("',")
			.append(" last_notification_rule='").append(product.getLast_notification_rule()).append("'")
					.append(" where 	id = ").append(product.getID()).toString();
	      System.out.println(updateSQL);
	      return updateSQL;
	     }
	 
	final static private String SELECT_MAX =
		" SELECT FUTURE_PRODUCT_SEQ.NEXTVAL DESC_ID FROM DUAL";
	final static private String SELECTALL =
		"SELECT PRODUCTID,ID,UNDERLYING_PRODUCTID,UND_BENCHMARK_NAME,QUOTE_TYPE,QUOTE_DECIMALS,CONTRACT_SIZE,LOTS,TICKSIZE,TRADECONTRACT_NO,SETTLEMENT_METHOD,EXPIRY_DATE_RULE,RATE_INDEX_CODE,TIME_ZONE,TIME_MINUTE,LAST_TRADING_RULE,FIRST_DELIVERY_TRADING_RULE,LAST_DELIVERY_TRADING_RULE,FIRST_NOTIFICATION_RULE,LAST_NOTIFICATION_RULE  FROM " + tableName + " order by id ";
	final static private String SELECT =
		"SELECT PRODUCTID,ID,UNDERLYING_PRODUCTID,UND_BENCHMARK_NAME,QUOTE_TYPE,QUOTE_DECIMALS,CONTRACT_SIZE,LOTS,TICKSIZE,TRADECONTRACT_NO,SETTLEMENT_METHOD,EXPIRY_DATE_RULE,RATE_INDEX_CODE,TIME_ZONE,TIME_MINUTE,LAST_TRADING_RULE,FIRST_DELIVERY_TRADING_RULE,LAST_DELIVERY_TRADING_RULE,FIRST_NOTIFICATION_RULE,LAST_NOTIFICATION_RULE FROM " + tableName + " where id =  ?";
	 static private String SELECTONE =
		"SELECT PRODUCTID,ID,UNDERLYING_PRODUCTID,UND_BENCHMARK_NAME,QUOTE_TYPE,QUOTE_DECIMALS,CONTRACT_SIZE,LOTS,TICKSIZE,TRADECONTRACT_NO,SETTLEMENT_METHOD,EXPIRY_DATE_RULE,RATE_INDEX_CODE,TIME_ZONE,TIME_MINUTE,LAST_TRADING_RULE,FIRST_DELIVERY_TRADING_RULE,LAST_DELIVERY_TRADING_RULE,FIRST_NOTIFICATION_RULE,LAST_NOTIFICATION_RULE FROM " + tableName + " where id =  ";
	 final static private String SELECTWHRE = 
		 "SELECT PRODUCTID,ID,UNDERLYING_PRODUCTID,UND_BENCHMARK_NAME,QUOTE_TYPE,QUOTE_DECIMALS,CONTRACT_SIZE,LOTS,TICKSIZE,TRADECONTRACT_NO,SETTLEMENT_METHOD,EXPIRY_DATE_RULE,RATE_INDEX_CODE,TIME_ZONE,TIME_MINUTE,LAST_TRADING_RULE,FIRST_DELIVERY_TRADING_RULE,LAST_DELIVERY_TRADING_RULE,FIRST_NOTIFICATION_RULE,LAST_NOTIFICATION_RULE FROM " + tableName + " where   ";
	 final static private String SELECTFROMATTRIBUTES = " select id,attributes FROM " + tableName + " where attributes = '%like" ; // this working only with oralce..  
	final static private String selectExisitingName = " select id,producttype,productname,productshortname from product ";
	final static private String SELECTPROUDCTONQUOTENAME =
			"SELECT PRODUCTID,ID,UNDERLYING_PRODUCTID,UND_BENCHMARK_NAME,QUOTE_TYPE,QUOTE_DECIMALS,CONTRACT_SIZE,LOTS,TICKSIZE,TRADECONTRACT_NO,SETTLEMENT_METHOD,EXPIRY_DATE_RULE,RATE_INDEX_CODE,TIME_ZONE,TIME_MINUTE,LAST_TRADING_RULE,FIRST_DELIVERY_TRADING_RULE,LAST_DELIVERY_TRADING_RULE,FIRST_NOTIFICATION_RULE,LAST_NOTIFICATION_RULE  FROM " + tableName + "  where id  = (select productid from futurecontract where quote_name = '" ;
	
		
	 public static int save(FutureProduct insertProduct, Connection con) {
		 try {
			
			 
             return insert(insertProduct, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FutureProductSQL","save",e);
        	 return 0;
         }
	 }
	 public static boolean update(FutureProduct updateProduct, Connection con) {
		 try {
             return edit(updateProduct, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FutureProductSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(FutureProduct deleteProduct, Connection con) {
		 try {
             return remove(deleteProduct, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FutureProductSQL","update",e);
        	 return false;
         }
	 }
	 
	 protected static boolean remove(FutureProduct deleteProduct, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, DELETE);
	            stmt.setInt(j++, deleteProduct.getID());
	           
	            stmt.executeUpdate();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("FutureProductSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FutureProductSQL","remove",e);
			}
	        }
	        return true;
	 }
	 
	 public static FutureProduct selectProduct(int id, Connection con) {
		 try {
             return  select(id, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FutureProductSQL","select",e);
        	 return null;
         }
	 }
	 
	
	 
	 public static Collection selectALL(Connection con) {
		 try {
             return select(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FutureProductSQL","selectALL",e);
        	 return null;
         }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
             return selectMax(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FutureProductSQL","selectMaxID",e);
        	 return 0;
         }
	 }
	 
	 protected static  boolean edit(FutureProduct updateProduct, Connection con ) {
		 
	        PreparedStatement stmt = null;
		 try {
			
	            
			
	           con.setAutoCommit(false);
	            
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, getUpdateSQL(updateProduct));
  
            /*stmt.setString(1, updateProduct.getProductType());
            stmt.setString(2, updateProduct.getProductname());
            stmt.setString(3, updateProduct.getProdcutShortName());
            stmt.setDouble(4, updateProduct.getQuantity());
            stmt.setString(5, updateProduct.getIssueDate());
            stmt.setString(6, updateProduct.getMarturityDate());
            stmt.setInt(7,    updateProduct.getIssuerId());
            stmt.setString(8, updateProduct.getCountry());
            stmt.setDouble(9, updateProduct.getIssuePrice());
            stmt.setString(10, updateProduct.getIssueCurrency());
            stmt.setDouble(11, updateProduct.getRedemptionPrice());
            stmt.setString(12, updateProduct.getRedemptionCurrency());
            stmt.setDouble(13, updateProduct.getFaceValue());
            stmt.setString(14, updateProduct.getCode());
            stmt.setString(15, updateProduct.getDatedDate());
            stmt.setString(16, updateProduct.getTenor());
            stmt.setString(17, updateProduct.getAttributes());
            stmt.setInt(18, updateProduct.getIsPosition());
            stmt.setInt(19, updateProduct.getId());  
            stmt.setInt(20, updateProduct.getCollateralID());  
            stmt.setString(21, updateProduct.getCallType());  
            stmt.setString(22, updateProduct.getRepoType());  
            */
            stmt.executeUpdate();
            con.commit();
            commonUTIL.display("FutureProductSQL",getUpdateSQL(updateProduct));
	        //stmt.executeUpdate();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("FutureProductSQL","edit",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FutureProductSQL","edit",e);
			}
	        }
	        return true;
	 }
	

	 

      protected static int selectMax(Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);
	         
	         ResultSet rs = stmt.executeQuery();
	         while(rs.next())
	         j = rs.getInt("DESC_ID");
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("FutureProductSQL","selectMax",e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FutureProductSQL","selectMax",e);
			}
	        }
	        return j;
	 }
      
      
      protected static int insert(FutureProduct inserProduct, Connection con ) {
			int id = 0;
	        PreparedStatement stmt = null;
		 try {
			 id = selectMax(con);
		//	 id = id+1;
			 int j = 1;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, INSERT);
			 stmt.setInt(1,inserProduct.getProductID());
		
	            stmt.setInt(2, id);
	            stmt.setInt(3,  inserProduct.getUnderlying_productID());
	            stmt.setString(4, inserProduct.getUnd_benchmark_name());
	            stmt.setString(5, inserProduct.getQuote_type());
	           stmt.setDouble(6, inserProduct.getQuote_decimals());
 	            stmt.setInt(7, inserProduct.getContract_size());
 	            stmt.setInt(8, inserProduct.getLots());
 	           stmt.setInt(9, inserProduct.getTicksize());
 	            stmt.setDouble(10, inserProduct.getTradecontract_no());
 	            stmt.setString(11, inserProduct.getSettlement_method());
 	            stmt.setString(12, inserProduct.getExpiry_Date_Rule());
 	            stmt.setString(13, inserProduct.getRate_index_code());
 	            stmt.setString(14, inserProduct.getTime_zone());
 	            stmt.setString(15, inserProduct.getTime_minute());
 	         stmt.setString(16, inserProduct.getLast_Trading_rule()); 
 	      stmt.setString(17, inserProduct.getFirst_delivery_Trading_rule());
 	   stmt.setString(18, inserProduct.getLast_delivery_Trading_rule());
 	stmt.setString(19, inserProduct.getFirst__notification_rule());
	stmt.setString(20, inserProduct.getLast_notification_rule());
	
	
 	      
	            
	            stmt.executeUpdate();
	            con.commit();
	            commonUTIL.display("FutureProductSQL",INSERT);
	            return id;
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("FutureProductSQL","insert",e);
			 return 0;
	           
	        }
	        finally {
	           try {
				stmt.close();
				return id;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FutureProductSQL","insert",e);
			}
	        }
	        
	 }
      
      protected static FutureProduct select(int productID,Connection con ) {
 		 
 		 int j = 0;
 	        PreparedStatement stmt = null;
 	        FutureProduct product = new FutureProduct();
 	        
 		 try {
 			
 			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + productID);
 	         
 	         ResultSet rs = stmt.executeQuery();
 	         
 	         while(rs.next()) {
 	        
 	     	    product.setProductID(rs.getInt(1));
 		        product.setID(rs.getInt(2));
 		        product.setUnderlying_productID(rs.getInt(3));
 		        product.setUnd_benchmark_name(rs.getString(4));
 		        product.setQuote_type(rs.getString(5));
 		        product.setQuote_decimals(rs.getDouble(6));
 		        product.setContract_size(rs.getInt(7));
 		        product.setLots(rs.getInt(8));
 		        product.setTicksize(rs.getInt(9));
 		        product.setTradecontract_no(rs.getInt(10));
 		        product.setSettlement_method(rs.getString(11));
 		        product.setExpiry_Date_Rule(rs.getString(12));
 		        product.setRate_index_code(rs.getString(13));
 		        product.setTime_zone(rs.getString(14));
 		        product.setTime_minute(rs.getString(15));
 		       
 		        product.setLast_Trading_rule(rs.getString(16));
 		        product.setFirst_delivery_Trading_rule(rs.getString(17));
 		        product.setLast_delivery_Trading_rule(rs.getString(18));
 		        product.setFirst__notification_rule(rs.getString(19));
 		        product.setLast_notification_rule(rs.getString(20));
 		      
 		    
	           
	
 	        commonUTIL.display("FutureProductSQL-----",SELECTONE + productID);
 	        
 	      
 	       
 	         
 	         }
 		 } catch (Exception e) {
 			 commonUTIL.displayError("FutureProductSQL",SELECTONE + productID,e);
 			 return product;
 	           
 	        }
 	        finally {
 	           try {
 				stmt.close();
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				commonUTIL.displayError("FutureProductSQL",SELECTONE + productID,e);
 			}
 	        }
 	        return product;
 	 }

 	 protected static Collection select(Connection con) { 
 		 int j = 0;
 	     PreparedStatement stmt = null;
 	     Vector products = new Vector();
 	     
 		 try {
 			
 			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
 	      
 	      ResultSet rs = stmt.executeQuery();
 	      System.out.println(rs.getMetaData());
 	      while(rs.next()) {
 	    	  
 	    	  FutureProduct product = new FutureProduct();
 	    	  
 	    	 product.setProductID(rs.getInt(1));
		        product.setID(rs.getInt(2));
		        product.setUnderlying_productID(rs.getInt(3));
		        product.setUnd_benchmark_name(rs.getString(4));
		        product.setQuote_type(rs.getString(5));
		        product.setQuote_decimals(rs.getDouble(6));
		        product.setContract_size(rs.getInt(7));
		        product.setLots(rs.getInt(8));
		        product.setTicksize(rs.getInt(9));
		        product.setTradecontract_no(rs.getInt(10));
		        product.setSettlement_method(rs.getString(11));
		        product.setExpiry_Date_Rule(rs.getString(12));
		        product.setRate_index_code(rs.getString(13));
		        product.setTime_zone(rs.getString(14));
		        product.setTime_minute(rs.getString(15));
		       
		        product.setLast_Trading_rule(rs.getString(16));
		        product.setFirst_delivery_Trading_rule(rs.getString(17));
		        product.setLast_delivery_Trading_rule(rs.getString(18));
		        product.setFirst__notification_rule(rs.getString(19));
		        product.setLast_notification_rule(rs.getString(20));

 		        products.add(product);
 		       
 	      }
 	      commonUTIL.display("productSQL",SELECTALL);
 		 } catch (Exception e) {
 			 commonUTIL.displayError("productSQL","select",e);
 			 return products;
 	        
 	     }
 	     finally {
 	        try {
 				stmt.close();
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				commonUTIL.displayError("productSQL","selectMax",e);
 			}
 	     }
 	     return products;
 	 }
 	 
 	 public static Collection selectonWhereClause(String sqlw,Connection con) { 
 		 int j = 0;
 	     PreparedStatement stmt = null;
 	     Vector products = new Vector();
 	     String sql= SELECTWHRE + sqlw;
 		 try {
 			commonUTIL.display("ProdcutSQL ", "selectonWhereClause"+sql);
 			 stmt = dsSQL.newPreparedStatement(con, sql );
 	      
 	      ResultSet rs = stmt.executeQuery();
 	   //   System.out.println(rs.getMetaData());
 	      while(rs.next()) {
 	    	  
 	    	  FutureProduct product = new FutureProduct();
 	    	  
 	    	 product.setProductID(rs.getInt(1));
		        product.setID(rs.getInt(2));
		        product.setUnderlying_productID(rs.getInt(3));
		        product.setUnd_benchmark_name(rs.getString(4));
		        product.setQuote_type(rs.getString(5));
		        product.setQuote_decimals(rs.getDouble(6));
		        product.setContract_size(rs.getInt(7));
		        product.setLots(rs.getInt(8));
		        product.setTicksize(rs.getInt(9));
		        product.setTradecontract_no(rs.getInt(10));
		        product.setSettlement_method(rs.getString(11));
		        product.setExpiry_Date_Rule(rs.getString(12));
		        product.setRate_index_code(rs.getString(13));
		        product.setTime_zone(rs.getString(14));
		        product.setTime_minute(rs.getString(15));
		       
		        product.setLast_Trading_rule(rs.getString(16));
		        product.setFirst_delivery_Trading_rule(rs.getString(17));
		        product.setLast_delivery_Trading_rule(rs.getString(18));
		        product.setFirst__notification_rule(rs.getString(19));
		        product.setLast_notification_rule(rs.getString(20));

 		        products.add(product);
 		       
 	      }
 	      commonUTIL.display("productSQL",sql);
 		 } catch (Exception e) {
 			 commonUTIL.displayError("productSQL","select",e);
 			 return products;
 	        
 	     }
 	     finally {
 	        try {
 				stmt.close();
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				commonUTIL.displayError("productSQL","selectMax",e);
 			}
 	     }
 	     return products;
 	 }
 	 
 	 public static FutureProduct selectonWhereClauseOnProductSubType(String productType,String productSubType,Connection con) { 
 		 int j = 0;
 	     PreparedStatement stmt = null;
 		  FutureProduct product = new FutureProduct();
 	     String sql= SELECTWHRE + " producttype ='"+productType+"' and productname like '"+productSubType+"'";
 		 try {
 			commonUTIL.display("ProdcutSQL ", "selectonWhereClause  "+sql);
 			 stmt = dsSQL.newPreparedStatement(con, sql );
 	      
 	      ResultSet rs = stmt.executeQuery();
 	   //   System.out.println(rs.getMetaData());
 	      while(rs.next()) {
 	    	  
 	    
 	    	  
  	    	 product.setProductID(rs.getInt(1));
 		        product.setID(rs.getInt(2));
 		        product.setUnderlying_productID(rs.getInt(3));
 		        product.setUnd_benchmark_name(rs.getString(4));
 		        product.setQuote_type(rs.getString(5));
 		        product.setQuote_decimals(rs.getDouble(6));
 		        product.setContract_size(rs.getInt(7));
 		        product.setLots(rs.getInt(8));
 		        product.setTicksize(rs.getInt(9));
 		        product.setTradecontract_no(rs.getInt(10));
 		        product.setSettlement_method(rs.getString(11));
 		        product.setExpiry_Date_Rule(rs.getString(12));
 		        product.setRate_index_code(rs.getString(13));
 		        product.setTime_zone(rs.getString(14));
 		        product.setTime_minute(rs.getString(15));
 		       
 		        product.setLast_Trading_rule(rs.getString(16));
 		        product.setFirst_delivery_Trading_rule(rs.getString(17));
 		        product.setLast_delivery_Trading_rule(rs.getString(18));
 		        product.setFirst__notification_rule(rs.getString(19));
 		        product.setLast_notification_rule(rs.getString(20));

 	      }
 	      commonUTIL.display("FutureContractSQL selectonWhereClauseOnProductSubType ",sql);
 		 } catch (Exception e) {
 			 commonUTIL.displayError("FutureContractSQL "," selectonWhereClauseOnProductSubType",e);
 			 return product;
 	        
 	     }
 	     finally {
 	        try {
 				stmt.close();
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				commonUTIL.displayError("FutureContractSQL ","selectonWhereClauseOnProductSubType ",e);
 			}
 	     }
 	     return product;
 	 }
	public static FutureProduct selectFutureProductOnQuoteName(
			String futureContractQuoteName,Connection con) {
		// TODO Auto-generated method stub
		 int j = 0;
 	     PreparedStatement stmt = null;
 		  FutureProduct product = new FutureProduct();
 	     String sql= SELECTPROUDCTONQUOTENAME  + futureContractQuoteName+"')";
 		 try {
 			commonUTIL.display("FutureContractSQL ", "selectFutureProductOnQuoteName  "+sql);
 			 stmt = dsSQL.newPreparedStatement(con, sql );
 	      
 	      ResultSet rs = stmt.executeQuery();
 	   //   System.out.println(rs.getMetaData());
 	      while(rs.next()) {
 	    	  
 	    
 	    	  
  	    	 product.setProductID(rs.getInt(1));
 		        product.setID(rs.getInt(2));
 		        product.setUnderlying_productID(rs.getInt(3));
 		        product.setUnd_benchmark_name(rs.getString(4));
 		        product.setQuote_type(rs.getString(5));
 		        product.setQuote_decimals(rs.getDouble(6));
 		        product.setContract_size(rs.getInt(7));
 		        product.setLots(rs.getInt(8));
 		        product.setTicksize(rs.getInt(9));
 		        product.setTradecontract_no(rs.getInt(10));
 		        product.setSettlement_method(rs.getString(11));
 		        product.setExpiry_Date_Rule(rs.getString(12));
 		        product.setRate_index_code(rs.getString(13));
 		        product.setTime_zone(rs.getString(14));
 		        product.setTime_minute(rs.getString(15));
 		       
 		        product.setLast_Trading_rule(rs.getString(16));
 		        product.setFirst_delivery_Trading_rule(rs.getString(17));
 		        product.setLast_delivery_Trading_rule(rs.getString(18));
 		        product.setFirst__notification_rule(rs.getString(19));
 		        product.setLast_notification_rule(rs.getString(20));

 	      }
 	      commonUTIL.display("FutureContractSQL selectFutureProductOnQuoteName ",sql);
 		 } catch (Exception e) {
 			 commonUTIL.displayError("FutureContractSQL","selectFutureProductOnQuoteName",e);
 			 return product;
 	        
 	     }
 	     finally {
 	        try {
 				stmt.close();
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				commonUTIL.displayError("FutureContractSQL","selectFutureProductOnQuoteName",e);
 			}
 	     }
 	     return product;
	}

}
