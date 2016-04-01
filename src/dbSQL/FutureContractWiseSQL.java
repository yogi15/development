package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.FutureContract;
import beans.FutureContract;
import beans.FutureContract;

public class FutureContractWiseSQL {
	final static private String tableName = "futurecontract";
	final static private String DELETE =
		"DELETE FROM " + tableName + "   where id =? ";
	final static private String INSERT =
		"INSERT into " + tableName + "(ID,PRODUCTID,EXPIRATION_DATE,Last_TRADE_DATE,FIRST_DELIVERY_DATE,LAST_DELIVERY_DATE,FIRST_NOTIFICATION_DATE,LAST_NOTIFICATION_DATE,ATTRIBUTES,CTD,QUOTE_NAME,producttype,prodcutShortName,parentProductid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				//"issueDate,marturityDate,IssuerId,Country,IssuePrice,IssueCurrency,RedemptionPrice,RedemptionCurrency,FaceValue,code,dateddate,tenor ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 private static String getUpdateSQL(FutureContract product) {
	      String updateSQL = new StringBuffer("UPDATE ")
	      	.append(tableName)
	      	.append(" set productID= ").append(product.getProductID()).append(",")
	      	.append(" id=").append(product.getID()).append(",")
			.append(" EXPIRATION_DATE='").append(product.getExpriationDate()).append("',")
			.append(" Last_TRADE_DATE='").append(product.getLastTradeDate()).append("',")
			.append(" FIRST_DELIVERY_DATE='").append(product.getFirstDeliveryDate()).append("',")
			.append(" LAST_DELIVERY_DATE='").append(product.getLastDeliveryDate()).append("',")
			.append(" FIRST_NOTIFICATION_DATE='").append(product.getFirstNotificationDate()).append("',")
			.append(" LAST_NOTIFICATION_DATE='").append(product.getLastNotificationDate()).append("',")
			.append(" ATTRIBUTES='").append(product.getAttributes()).append("',")
			.append(" CTD='").append(product.getCtd()).append("',")
				.append(" QUOTE_NAME='").append(product.getQuoteName()).append("',")
				.append(" producttype='").append(product.getProductType()).append("',")
				.append(" prodcutShortName='").append(product.getQuoteName()).append("',")
				.append(" parentProductid=").append(product.getQuoteName()).append("")
					.append(" where 	id = ").append(product.getID()).toString();
	      System.out.println(updateSQL);
	      return updateSQL;
	     }
	 
	 final static private String SELECT_MAX =
				" SELECT FutureContract_SEQ.NEXTVAL DESC_ID FROM DUAL";
			final static private String SELECTALL =
				"SELECT ID,PRODUCTID,EXPIRATION_DATE,Last_TRADE_DATE,FIRST_DELIVERY_DATE,LAST_DELIVERY_DATE,FIRST_NOTIFICATION_DATE,LAST_NOTIFICATION_DATE,ATTRIBUTES,CTD,QUOTE_NAME,producttype,prodcutShortName,parentProductid  FROM " + tableName + " order by id ";
			final static private String SELECT =
				"SELECT ID,PRODUCTID,EXPIRATION_DATE,Last_TRADE_DATE,FIRST_DELIVERY_DATE,LAST_DELIVERY_DATE,FIRST_NOTIFICATION_DATE,LAST_NOTIFICATION_DATE,ATTRIBUTES,CTD,QUOTE_NAME,producttype,prodcutShortName,parentProductid FROM " + tableName + " where id =  ?";
			 static private String SELECTONE =
				"SELECT ID,PRODUCTID,EXPIRATION_DATE,Last_TRADE_DATE,FIRST_DELIVERY_DATE,LAST_DELIVERY_DATE,FIRST_NOTIFICATION_DATE,LAST_NOTIFICATION_DATE,ATTRIBUTES,CTD,QUOTE_NAME,producttype,prodcutShortName,parentProductid FROM " + tableName + " where id =  ";
			 final static private String SELECTWHRE = 
				 "SELECT ID,PRODUCTID,EXPIRATION_DATE,Last_TRADE_DATE,FIRST_DELIVERY_DATE,LAST_DELIVERY_DATE,FIRST_NOTIFICATION_DATE,LAST_NOTIFICATION_DATE,ATTRIBUTES,CTD,QUOTE_NAME,producttype,prodcutShortName,parentProductid FROM " + tableName + " where   ";
			 final static private String SELECTFROMATTRIBUTES = " select id,attributes FROM " + tableName + " where attributes = '%like" ; // this working only with oralce..  
			final static private String selectExisitingName = " select id,producttype,productname,productshortname from product ";
			final static private String SELECTONPRODUCTNAME =
					"SELECT ID,PRODUCTID,EXPIRATION_DATE,Last_TRADE_DATE,FIRST_DELIVERY_DATE,LAST_DELIVERY_DATE,FIRST_NOTIFICATION_DATE,LAST_NOTIFICATION_DATE,ATTRIBUTES,CTD,QUOTE_NAME,producttype,prodcutShortName,parentProductid   FROM " + tableName + "  where ";
				

			
			 public static int save(FutureContract insertProduct, Connection con) {
				 try {
					
					 
		             return insert(insertProduct, con);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("FutureContractSQL","save",e);
		        	 return 0;
		         }
			 }
			 public static boolean update(FutureContract updateProduct, Connection con) {
				 try {
		             return edit(updateProduct, con);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("FutureContractSQL","update",e);
		        	 return false;
		         }
			 }
			 
			 public static boolean delete(FutureContract deleteProduct, Connection con) {
				 try {
		             return remove(deleteProduct, con);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("FutureContractSQL","update",e);
		        	 return false;
		         }
			 }
			 
			 protected static boolean remove(FutureContract deleteProduct, Connection con ) {
					
			        PreparedStatement stmt = null;
				 try {
					 int j = 1;
					 stmt = dsSQL.newPreparedStatement(con, DELETE);
			            stmt.setInt(j++, deleteProduct.getID());
			           
			            stmt.executeUpdate();
					 
				 } catch (Exception e) {
					 commonUTIL.displayError("FutureContractSQL","remove",e);
					 return false;
			           
			        }
			        finally {
			           try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						commonUTIL.displayError("FutureContractSQL","remove",e);
					}
			        }
			        return true;
			 }
			 
			 public static FutureContract selectProduct(int id, Connection con) {
				 try {
		             return  select(id, con);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("FutureContractSQL","select",e);
		        	 return null;
		         }
			 }
			 
			
			 
			 public static Collection selectALL(Connection con) {
				 try {
		             return select(con);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("FutureContractSQL","selectALL",e);
		        	 return null;
		         }
			 }
			 
			 public static int selectMaxID(Connection con) {
				 try {
		             return selectMax(con);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("FutureContractSQL","selectMaxID",e);
		        	 return 0;
		         }
			 }
			 
			 protected static  boolean edit(FutureContract updateProduct, Connection con ) {
				 
			        PreparedStatement stmt = null;
				 try {
					
			            
					
			           con.setAutoCommit(false);
			            
					 int j = 1;
					 stmt = dsSQL.newPreparedStatement(con, getUpdateSQL(updateProduct));
		  
		           
		            stmt.executeUpdate();
		            con.commit();
		            commonUTIL.display("FutureContractSQL",getUpdateSQL(updateProduct));
			        //stmt.executeUpdate();
					 
				 } catch (Exception e) {
					 commonUTIL.displayError("FutureContractSQL","edit",e);
					 return false;
			           
			        }
			        finally {
			           try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						commonUTIL.displayError("FutureContractSQL","edit",e);
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
					 commonUTIL.displayError("FutureContractSQL","selectMax",e);
					 return j;
			           
			        }
			        finally {
			           try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						commonUTIL.displayError("FutureContractSQL","selectMax",e);
					}
			        }
			        return j;
			 }
		      
		      
		      protected static int insert(FutureContract inserProduct, Connection con ) {
					int id = 0;
			        PreparedStatement stmt = null;
				 try {
					 id = selectMax(con);
				//	 id = id+1;
					 int j = 1;
					 con.setAutoCommit(false);
					 stmt = dsSQL.newPreparedStatement(con, INSERT);
					 stmt.setInt(1,id);
					
			            stmt.setInt(2, inserProduct.getProductID());
			            stmt.setString(3,  inserProduct.getExpriationDate());
			            stmt.setString(4, inserProduct.getLastDeliveryDate());
			            stmt.setString(5, inserProduct.getFirstDeliveryDate());
			           stmt.setString(6, inserProduct.getLastDeliveryDate());
		 	            stmt.setString(7, inserProduct.getFirstNotificationDate());
		 	            stmt.setString(8, inserProduct.getLastNotificationDate());
		 	           stmt.setString(9, inserProduct.getAttributes());
		 	            stmt.setString(10, inserProduct.getCtd());
		 	            stmt.setString(11, inserProduct.getQuoteName());
		 	           stmt.setString(12, inserProduct.getProductType());
		 	          stmt.setString(13, inserProduct.getProductshortname());
		 	         stmt.setInt(14, inserProduct.getParentProductID());
		 	           
			
			
		 	      
			            
			            stmt.executeUpdate();
			            con.commit();
			            commonUTIL.display("FutureContractSQL",INSERT);
			            return id;
					 
				 } catch (Exception e) {
					 commonUTIL.displayError("FutureContractSQL","insert",e);
					 return 0;
			           
			        }
			        finally {
			           try {
						stmt.close();
						return id;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						commonUTIL.displayError("FutureContractSQL","insert",e);
					}
			        }
			        
			 }
		    //  ID,PRODUCTID,EXPIRATION_DATE,FIRST_TRADE_DATE,FIRST_DELIVERY_DATE,LAST_DELIVERY_DATE,FIRST_NOTIFICATION_DATE,LAST_NOTIFICATION_DATE,ATTRIBUTES,CTD,QUOTE
		      protected static FutureContract select(int id,Connection con ) {
		  		 
		  		 int j = 0;
		  	        PreparedStatement stmt = null;
		  	        FutureContract product = new FutureContract();
		  	        
		  		 try {
		  			
		  			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + id);
		  	         
		  	         ResultSet rs = stmt.executeQuery();
		  	         
		  	         while(rs.next()) {
		  	        
		  	     	    product.setID(rs.getInt(1));
		  		        product.setProductID(rs.getInt(2));
		  		        product.setExpriationDate(rs.getString(3));
		  		        product.setLastTradeDate(rs.getString(4));
		  		        product.setFirstDeliveryDate(rs.getString(5));
		  		        product.setLastDeliveryDate(rs.getString(6));
		  		        product.setFirstNotificationDate(rs.getString(7));
		  		        product.setLastNotificationDate(rs.getString(8));
		  		        product.setAttributes(rs.getString(9));
		  		        product.setCtd(rs.getString(10));
		  		        product.setQuoteName(rs.getString(11));
		  		      product.setProductType(rs.getString(12));
		  		    product.setProductshortname(rs.getString(13));
		  		  product.setParentProductID(rs.getInt(14));
		  		       
		  		    
		 	           
		 	
		  	        commonUTIL.display("FutureContractSQL-----",SELECTONE + id);
		  	        
		  	      
		  	       
		  	         
		  	         }
		  		 } catch (Exception e) {
		  			 commonUTIL.displayError("FutureContractSQL",SELECTONE + id,e);
		  			 return product;
		  	           
		  	        }
		  	        finally {
		  	           try {
		  				stmt.close();
		  			} catch (SQLException e) {
		  				// TODO Auto-generated catch block
		  				commonUTIL.displayError("FutureContractSQL",SELECTONE + id,e);
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
		  	    	  
		  	    	  FutureContract product = new FutureContract();
		  	    	  
		  	    	 product.setID(rs.getInt(1));
		  		        product.setProductID(rs.getInt(2));
		  		        product.setExpriationDate(rs.getString(3));
		  		        product.setLastTradeDate(rs.getString(4));
		  		        product.setFirstDeliveryDate(rs.getString(5));
		  		        product.setLastDeliveryDate(rs.getString(6));
		  		        product.setFirstNotificationDate(rs.getString(7));
		  		        product.setLastNotificationDate(rs.getString(8));
		  		        product.setAttributes(rs.getString(9));
		  		        product.setCtd(rs.getString(10));
		  		        product.setQuoteName(rs.getString(11));
		  		      product.setProductType(rs.getString(12));
			  		    product.setProductshortname(rs.getString(13));
			  		  product.setParentProductID(rs.getInt(14));
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
		  	    	  
		  	    	  FutureContract product = new FutureContract();
		  	    	  
		  	    	 product.setID(rs.getInt(1));
		  		        product.setProductID(rs.getInt(2));
		  		        product.setExpriationDate(rs.getString(3));
		  		        product.setLastTradeDate(rs.getString(4));
		  		        product.setFirstDeliveryDate(rs.getString(5));
		  		        product.setLastDeliveryDate(rs.getString(6));
		  		        product.setFirstNotificationDate(rs.getString(7));
		  		        product.setLastNotificationDate(rs.getString(8));
		  		        product.setAttributes(rs.getString(9));
		  		        product.setCtd(rs.getString(10));
		  		        product.setQuoteName(rs.getString(11));
		  		      product.setProductType(rs.getString(12));
			  		    product.setProductshortname(rs.getString(13));
			  		  product.setParentProductID(rs.getInt(14));
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
		  	 
		  	 public static Collection selectonWhereClauseOnProductSubType(String productType,String productSubType,Connection con) { 
		  		 int j = 0;
		  	     PreparedStatement stmt = null;
		  		 Vector<FutureContract> products = new Vector<FutureContract>();
		  	     String sql= SELECTWHRE + " producttype ='"+productType+"' and productname like '"+productSubType+"'";
		  		 try {
		  			commonUTIL.display("ProdcutSQL ", "selectonWhereClause  "+sql);
		  			 stmt = dsSQL.newPreparedStatement(con, sql );
		  	      
		  	      ResultSet rs = stmt.executeQuery();
		  	   //   System.out.println(rs.getMetaData());
		  	      while(rs.next()) {
		  	    	  
		  	    	  FutureContract product = new FutureContract();
		  	    	  
		  	    	 product.setID(rs.getInt(1));
		  		        product.setProductID(rs.getInt(2));
		  		        product.setExpriationDate(rs.getString(3));
		  		        product.setLastTradeDate(rs.getString(4));
		  		        product.setFirstDeliveryDate(rs.getString(5));
		  		        product.setLastDeliveryDate(rs.getString(6));
		  		        product.setFirstNotificationDate(rs.getString(7));
		  		        product.setLastNotificationDate(rs.getString(8));
		  		        product.setAttributes(rs.getString(9));
		  		        product.setCtd(rs.getString(10));
		  		        product.setQuoteName(rs.getString(11));
		  		      product.setProductType(rs.getString(12));
			  		    product.setProductshortname(rs.getString(13));
			  		  product.setParentProductID(rs.getInt(14));
		  		      products.add(product);

		  	      }
		  	      commonUTIL.display("productSQL selectonWhereClauseOnProductSubType ",sql);
		  		 } catch (Exception e) {
		  			 commonUTIL.displayError("productSQL","selectonWhereClauseOnProductSubType",e);
		  			 return products;
		  	        
		  	     }
		  	     finally {
		  	        try {
		  				stmt.close();
		  			} catch (SQLException e) {
		  				// TODO Auto-generated catch block
		  				commonUTIL.displayError("productSQL","selectonWhereClauseOnProductSubType",e);
		  			}
		  	     }
		  	     return products;
		  	 }
			public static Collection getFutureContractOnProductName(
					String productName, Connection con) {
				// TODO Auto-generated method stub
				 PreparedStatement stmt = null;
				 Vector<FutureContract> products = new Vector<FutureContract>();
				String sql = SELECTONPRODUCTNAME + " productid = (select id from FUTURECONTRACT_PRODUCT where underlying_productid = (select id from product where productname = '" + productName + "'))";
				 try {
			  			commonUTIL.display("FutureContractWiseSQL ", "getFutureContractOnProductName  "+sql);
			  			 stmt = dsSQL.newPreparedStatement(con, sql );
			  	      
			  	      ResultSet rs = stmt.executeQuery();
			  	   //   System.out.println(rs.getMetaData());
			  	      while(rs.next()) {
			  	    	  
			  	    
			  	    	 FutureContract product = new FutureContract();
			  	    	 product.setID(rs.getInt(1));
			  		        product.setProductID(rs.getInt(2));
			  		        product.setExpriationDate(rs.getString(3));
			  		        product.setLastTradeDate(rs.getString(4));
			  		        product.setFirstDeliveryDate(rs.getString(5));
			  		        product.setLastDeliveryDate(rs.getString(6));
			  		        product.setFirstNotificationDate(rs.getString(7));
			  		        product.setLastNotificationDate(rs.getString(8));
			  		        product.setAttributes(rs.getString(9));
			  		        product.setCtd(rs.getString(10));
			  		        product.setQuoteName(rs.getString(11));
			  		      product.setProductType(rs.getString(12));
				  		    product.setProductshortname(rs.getString(13));
				  		  product.setParentProductID(rs.getInt(14));
			  		      products.add(product);

			  	      }
			  	      commonUTIL.display("FutureContractWiseSQL getFutureContractOnProductName ",sql);
			  		 } catch (Exception e) {
			  			 commonUTIL.displayError("FutureContractWiseSQL","getFutureContractOnProductName",e);
			  			 return products;
			  	        
			  	     }
			  	     finally {
			  	        try {
			  				stmt.close();
			  			} catch (SQLException e) {
			  				// TODO Auto-generated catch block
			  				commonUTIL.displayError("FutureContractWiseSQL","getFutureContractOnProductName",e);
			  			}
			  	     }
			  	     return products;
			}

			
}
