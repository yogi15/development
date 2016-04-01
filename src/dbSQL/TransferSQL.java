package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;


import beans.Trade;
import beans.Transfer;
import util.commonUTIL;

public class TransferSQL {
	
	final static private String tableName = "transfer";
	final static private String DELETE =
		"DELETE FROM " + tableName + "   where id =? ";
	final static private String INSERT =
		"INSERT into " + tableName + "(id,tradeID,productId,amount,eventType,transfertype,transferStatus,settlecurrency,payerCode,payerRole,receiverCode," +
				"receiverRole," +
				"paymentStatus,deliveryDate,valueDate,method,receiverInst,payerInst,attributes,status,action,tradeversionid,linkid,nettedTransferID,nettedConfigID,version,settleAmount,cpID,productType,userid,bookid,quantity) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	final static private String UPDATE =
		"UPDATE " + tableName + " set tradeId=?,productId=?,amount=?,rec_pay=?  where id = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(id) DESC_ID FROM " + tableName + " ";
	final static private String SELECTALL =
		"SELECT id,tradeID,productId,amount,eventType,transfertype,transferStatus,settlecurrency,payerCode,payerRole,receiverCode," +
				"receiverRole," +
				"paymentStatus,deliveryDate,valueDate,method,receiverInst,payerInst,attributes,status,action,tradeversionid,linkid,nettedTransferID,nettedConfigID,version,settleAmount,cpID,productType,userid,bookId,quantity FROM " + tableName + " ";
	final static private String SELECT =
		"SELECT id,tradeID,productId,amount,eventType,transfertype,transferStatus,settlecurrency,payerCode,payerRole,receiverCode," +
				"receiverRole," +
				"paymentStatus,deliveryDate,valueDate,method,receiverInst,payerInst,attributes,status,action,tradeversionid,linkid,nettedTransferID,nettedConfigID,version,settleAmount,cpID,productType,userid,bookId,quantity FROM " + tableName + " where id =  ?";
	 static private String SELECTONE =
		"SELECT id,tradeID,productId,amount,eventType,transfertype,transferStatus,settlecurrency,payerCode,payerRole,receiverCode," +
				"receiverRole," +
				"paymentStatus,deliveryDate,valueDate,method,receiverInst,payerInst,attributes,status,action,tradeversionid,linkid,nettedTransferID,nettedConfigID,version,settleAmount,cpID,productType,userid,bookId,quantity FROM " + tableName + " where id =  ";
	  static private String SELECTWHERE =
			"SELECT id,tradeID,productId,amount,eventType,transfertype,transferStatus,settlecurrency,payerCode,payerRole,receiverCode," +
				"receiverRole," +
				"paymentStatus,deliveryDate,valueDate,method,receiverInst,payerInst,attributes,status,action,tradeversionid,linkid,nettedTransferID,nettedConfigID,version,settleAmount,cpID,productType,userid,bookId,quantity  FROM " + tableName + " where  ";
	  static private String SELECTNettingTransfer =
				"SELECT id,tradeID,productId,amount,eventType,transfertype,transferStatus,settlecurrency,payerCode,payerRole,receiverCode," +
					"receiverRole," +
					"paymentStatus,deliveryDate,valueDate,method,receiverInst,payerInst,attributes,status,action,tradeversionid,linkid,nettedTransferID,nettedConfigID,version,settleAmount,cpID,productType,userid,bookId,quantity  FROM " + tableName + " where   ";
	 	
	  
	  
	  private static String getUpdateSQL(Transfer transfer) {
		  String updateSQL = "UPDATE  transfer set  productId=" +transfer.getProductId() + 
				            ",tradeID="+ transfer.getTradeId() + 
				            ",amount="+ transfer.getAmount() + 
				            ",eventType='"+ transfer.getEventType() + 
				            "',transfertype='"+ transfer.getTransferType() + 
				            "',transferStatus='"+ transfer.getTransferStatus() + 
				            "',settlecurrency='"+ transfer.getSettlecurrency() + 
				            "',payerCode='"+ transfer.getPayerCode() + 
				            "',payerRole='"+ transfer.getPayerRole() + 
				            "',receiverCode='"+ transfer.getReceiverCode() + 
				            "',receiverRole='"+ transfer.getReceiverRole() + 
				            "',paymentStatus='"+ transfer.getPaymentStatus() + 
				            "',deliveryDate='"+ transfer.getDeliveryDate() + 
				            "',receiverInst='"+ transfer.getReceiverInst() + 
				            "',payerInst='"+ transfer.getPayerInst() + 
				            "',valueDate='"+ transfer.getValueDate() + 
				            "',method='"+ transfer.getMethod() + 
				            "',attributes='"+ transfer.getAttributes()  + 
				            "',status='"+ transfer.getStatus()  + 
				            "',action='"+ transfer.getAction()  + 
				            "',tradeversionid="+ transfer.getTradeVersionID() + 
				            ",nettedTransferID="+ transfer.getNettedTransferID() + 
				            ",linkid="+ transfer.getLinkid() + 
				            ",nettedConfigID="+ transfer.getNettedConfigID() + 
				            ",version="+ (transfer.getVersion() + 1) + 
				            ",settleAmount="+ transfer.getSettleAmount() + 
				            ",cpID="+ transfer.getCPid() + 
				            ",producttype='"+ transfer.getProductType() + 
				            "',userid="+ transfer.getUserid() + 
				            ",bookid="+ transfer.getBookId() + 
				             ",quantity="+ transfer.getQuantity() + 
				            " where id = "+ transfer.getId();
	      return updateSQL;
				           
				            
				            
				            
		  
	  }
	
	 public static int save(Transfer insertTransfer, Connection con) {
		 try {
             return insert(insertTransfer, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("TransferSQL","save",e);
        	 return 0;
         }
	 }
	 public static boolean update(Transfer updateTransfer, Connection con) {
		 try {
             return edit(updateTransfer, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("TransferSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(Transfer deleteTransfer, Connection con) {
		 try {
             return remove(deleteTransfer, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("TransferSQL","update",e);
        	 return false;
         }
	 }
	 
	 protected static boolean remove(Transfer deleteTransfer, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, DELETE);
	            stmt.setInt(j++, deleteTransfer.getId());
	           
	            stmt.executeUpdate();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("TransferSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TransferSQL","remove",e);
			}
	        }
	        return true;
	 }
	 
	 
	 public static Vector selectTransfer(int TransferId, Connection con) {
		 try {
          return (Vector) select(TransferId, con);
      }catch(Exception e) {
     	 commonUTIL.displayError("TransferSQL","select",e);
     	 return null;
      }
	 }
	 public static Collection selectALL(Connection con) {
		 try {
          return select(con);
      }catch(Exception e) {
     	 commonUTIL.displayError("TransferSQL","selectALL",e);
     	 return null;
      }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
          return selectMax(con);
      }catch(Exception e) {
     	 commonUTIL.displayError("TransferSQL","selectMaxID",e);
     	 return 0;
      }
	 }
	 
	 protected static  boolean edit(Transfer updateTransfer, Connection con ) {
		 
		 PreparedStatement stmt = null;
         String sql = "";
   try {
    
    int j = 1;
    con.setAutoCommit(false);
   
    sql = getUpdateSQL(updateTransfer);
    stmt = con.prepareStatement(sql);
            stmt.executeUpdate(sql);
  
            
             
             con.commit();
             commonUTIL.display("TransferSQL  ::  edit", sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("TransferSQL","edit",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL","edit",e);
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
			 commonUTIL.displayError("TransferSQL","selectMax",e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TransferSQL","selectMax",e);
			}
	        }
	        return j;
	 }
      
      protected static int insert(Transfer inserTransfer, Connection con ) {
			
	        PreparedStatement stmt = null;
	        int id = 0;
		 try {
			 id = selectMax(con) +1;
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT);
			 commonUTIL.display(" TransferSQL insert ", INSERT);
			
			 stmt.setInt(1,id);
			
	            stmt.setInt(2, inserTransfer.getTradeId());
	            
	            stmt.setInt(3, inserTransfer.getProductId());
	            stmt.setDouble(4, inserTransfer.getAmount());
	            stmt.setString(5, inserTransfer.getEventType());
	            stmt.setString(6, inserTransfer.getTransferType());
	            stmt.setString(7, inserTransfer.getTransferStatus());
	            stmt.setString(8, inserTransfer.getSettlecurrency());
	            stmt.setString(9, inserTransfer.getPayerCode());
	            stmt.setString(10, inserTransfer.getPayerRole());
	            stmt.setString(11, inserTransfer.getReceiverCode());
	            stmt.setString(12, inserTransfer.getReceiverRole());
	            stmt.setString(13, inserTransfer.getPaymentStatus());
	            stmt.setString(14, inserTransfer.getDeliveryDate());
	            stmt.setString(15, inserTransfer.getValueDate());
	            stmt.setString(16, inserTransfer.getMethod());
	            stmt.setString(17, inserTransfer.getReceiverInst());
	            stmt.setString(18, inserTransfer.getPayerInst());
	            
	            stmt.setString(19, inserTransfer.getAttributes());
	            stmt.setString(20, inserTransfer.getStatus());
	            stmt.setString(21, inserTransfer.getAction());
	            stmt.setInt(22, inserTransfer.getTradeVersionID());
	         
	            stmt.setInt(23, inserTransfer.getLinkid());
	            stmt.setInt(24, inserTransfer.getNettedTransferID());
	            stmt.setInt(25, inserTransfer.getNettedConfigID());
	            stmt.setInt(26, 1);
	            stmt.setDouble(27, inserTransfer.getSettleAmount());
	            stmt.setInt(28, inserTransfer.getCPid());
	            stmt.setString(29, inserTransfer.getProductType());
	            stmt.setInt(30, inserTransfer.getUserid());
	            stmt.setInt(31, inserTransfer.getBookId());
	            stmt.setDouble(32, inserTransfer.getQuantity());
	            
	            stmt.executeUpdate();
	         //   System.out.println("TransferSQL ::  insert"+ INSERT);
	            commonUTIL.display("TransferSQL ::  insert", INSERT);
	       //    ..  System.out.println(" inserTransfer " + inserTransfer + " status " + inserTransfer.getId());
	             con.commit();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("TransferSQL","insert",e);
			 return 0;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TransferSQL","insert",e);
			}
	        }
	        return id;
	 }
	 
	 protected static Collection select(int TransferID,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector Transfers = new Vector();
	        
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + TransferID);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	 Transfer transfer = new Transfer();
	        	 transfer.setId(rs.getInt(1));
			        transfer.setTradeId(rs.getInt(2));
			        transfer.setProductId(rs.getInt(3));
			        transfer.setAmount(rs.getDouble(4));
		            transfer.setEventType(rs.getString(5));
		            transfer.setTranserType(rs.getString(6));
		           transfer.setTransferStatus(rs.getString(7));
		          transfer.setSettlecurrency(rs.getString(8));
		             transfer.setPayerCode(rs.getString(9));
		             transfer.setPayerRole(rs.getString(10));
		           transfer.setReceiverCode(rs.getString(11));
		             transfer.setReceiverRole(rs.getString(12));
		             transfer.setPaymentStatus(rs.getString(13));
		             transfer.setDeliveryDate(rs.getString(14));
		            transfer.setValueDate(rs.getString(15));
		            transfer.setMethod(rs.getString(16));
		             transfer.setReceiverInst(rs.getString(17));
		             transfer.setPayerInst(rs.getString(18));
	        	 
		             transfer.setAttributes(rs.getString(19));
			        transfer.setStatus(rs.getString(20));
			        transfer.setAction(rs.getString(21));
			        transfer.setTradeVersionID(rs.getInt(22));
			        transfer.setLinkid(rs.getInt(23));
			       
			       transfer.setNettedTransferID(rs.getInt(24));
			       
			        transfer.setNettedConfigID(rs.getInt(25));
			        transfer.setVersion(rs.getInt(26));
			        transfer.setSettleAmount(rs.getDouble(27));
			        transfer.setCPid(rs.getInt(28));
			        transfer.setProductType(rs.getString(29));
			        transfer.setUserid(rs.getInt(30));
			        transfer.setBookId(rs.getInt(31));
			        transfer.setQuantity(rs.getDouble(32));
			        Transfers.add(transfer);
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("TransferSQL","select",e);
			 return Transfers;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TransferSQL","selectMax",e);
			}
	        }
	        return Transfers;
	 }

	
	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Transfers = new Vector();
	     
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Transfer transfer = new Transfer();
	        	 transfer.setId(rs.getInt(1));
			        transfer.setTradeId(rs.getInt(2));
			        transfer.setProductId(rs.getInt(3));
			        transfer.setAmount(rs.getDouble(4));
		            transfer.setEventType(rs.getString(5));
		            transfer.setTranserType(rs.getString(6));
		           transfer.setTransferStatus(rs.getString(7));
		          transfer.setSettlecurrency(rs.getString(8));
		             transfer.setPayerCode(rs.getString(9));
		             transfer.setPayerRole(rs.getString(10));
		           transfer.setReceiverCode(rs.getString(11));
		             transfer.setReceiverRole(rs.getString(12));
		             transfer.setPaymentStatus(rs.getString(13));
		             transfer.setDeliveryDate(rs.getString(14));
		            transfer.setValueDate(rs.getString(15));
		            transfer.setMethod(rs.getString(16));
		             transfer.setReceiverInst(rs.getString(17));
		             transfer.setPayerInst(rs.getString(18));
		             transfer.setAttributes(rs.getString(19));
		             transfer.setStatus(rs.getString(20));
		             transfer.setAction(rs.getString(21));
		             transfer.setTradeVersionID(rs.getInt(22));
		             transfer.setLinkid(rs.getInt(23));
		             transfer.setNettedTransferID(rs.getInt(24));
				       
				        transfer.setNettedConfigID(rs.getInt(25));
				        transfer.setVersion(rs.getInt(26));
				        transfer.setSettleAmount(rs.getDouble(27));
				        transfer.setCPid(rs.getInt(28));
				        transfer.setProductType(rs.getString(29));
				        transfer.setUserid(rs.getInt(30));
				        transfer.setBookId(rs.getInt(31));
				        transfer.setQuantity(rs.getDouble(32));
		        Transfers.add(transfer);
		     
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("TransferSQL","select",e);
			 return Transfers;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TransferSQL","selectMax",e);
			}
	     }
	     return Transfers;
	 }
	public static Collection selectWhere(String where, Connection conn) {
		int j = 0;
	     PreparedStatement stmt = null;
	     Vector Transfers = new Vector();
	     String sql = SELECTWHERE;
	     sql = sql + where;
		 try {
			
			 stmt = dsSQL.newPreparedStatement(conn, sql);
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Transfer transfer = new Transfer();
	        	 transfer.setId(rs.getInt(1));
			        transfer.setTradeId(rs.getInt(2));
			        transfer.setProductId(rs.getInt(3));
			        transfer.setAmount(rs.getDouble(4));
		            transfer.setEventType(rs.getString(5));
		            transfer.setTranserType(rs.getString(6));
		           transfer.setTransferStatus(rs.getString(7));
		          transfer.setSettlecurrency(rs.getString(8));
		             transfer.setPayerCode(rs.getString(9));
		             transfer.setPayerRole(rs.getString(10));
		           transfer.setReceiverCode(rs.getString(11));
		             transfer.setReceiverRole(rs.getString(12));
		             transfer.setPaymentStatus(rs.getString(13));
		             transfer.setDeliveryDate(rs.getString(14));
		            transfer.setValueDate(rs.getString(15));
		            transfer.setMethod(rs.getString(16));
		             transfer.setReceiverInst(rs.getString(17));
		             transfer.setPayerInst(rs.getString(18));
		             transfer.setAttributes(rs.getString(19));
		             transfer.setStatus(rs.getString(20));
		             transfer.setAction(rs.getString(21));
		             transfer.setTradeVersionID(rs.getInt(22));
		             transfer.setLinkid(rs.getInt(23));
		             transfer.setNettedTransferID(rs.getInt(24));
				       
				        transfer.setNettedConfigID(rs.getInt(25));
				        transfer.setVersion(rs.getInt(26));
				        transfer.setSettleAmount(rs.getDouble(27));
				        transfer.setCPid(rs.getInt(28));
				        transfer.setProductType(rs.getString(29));
				        transfer.setUserid(rs.getInt(30));
				        transfer.setBookId(rs.getInt(31));
				        transfer.setQuantity(rs.getDouble(32));
		        Transfers.add(transfer);
	      
	      }
	      commonUTIL.display("TransferSQL" , "selectWhere " + sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("TransferSQL",SELECTWHERE,e);
			 return Transfers;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TransferSQL",SELECTWHERE,e);
			}
	     }
	     return Transfers;
		
	}
	public static Transfer getSingleTransfer(String where, Connection conn) {
		int j = 0;
	     PreparedStatement stmt = null;
	     Transfer transfer = new Transfer();
	     String sql = SELECTWHERE;
	     sql = sql + where;
		 try {
			
			 stmt = dsSQL.newPreparedStatement(conn, sql);
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	 
	        	 transfer.setId(rs.getInt(1));
			        transfer.setTradeId(rs.getInt(2));
			        transfer.setProductId(rs.getInt(3));
			        transfer.setAmount(rs.getDouble(4));
		            transfer.setEventType(rs.getString(5));
		            transfer.setTranserType(rs.getString(6));
		           transfer.setTransferStatus(rs.getString(7));
		          transfer.setSettlecurrency(rs.getString(8));
		             transfer.setPayerCode(rs.getString(9));
		             transfer.setPayerRole(rs.getString(10));
		           transfer.setReceiverCode(rs.getString(11));
		             transfer.setReceiverRole(rs.getString(12));
		             transfer.setPaymentStatus(rs.getString(13));
		             transfer.setDeliveryDate(rs.getString(14));
		            transfer.setValueDate(rs.getString(15));
		            transfer.setMethod(rs.getString(16));
		             transfer.setReceiverInst(rs.getString(17));
		             transfer.setPayerInst(rs.getString(18));
		             transfer.setAttributes(rs.getString(19));
		             transfer.setStatus(rs.getString(20));
		             transfer.setAction(rs.getString(21));
		             transfer.setTradeVersionID(rs.getInt(22));
		             transfer.setLinkid(rs.getInt(23));
		             transfer.setNettedTransferID(rs.getInt(24));
				       
				        transfer.setNettedConfigID(rs.getInt(25));
				        transfer.setVersion(rs.getInt(26));
				        transfer.setSettleAmount(rs.getDouble(27));
				        transfer.setCPid(rs.getInt(28));
				        transfer.setProductType(rs.getString(29));
				        transfer.setUserid(rs.getInt(30));
				        transfer.setBookId(rs.getInt(31));
				        transfer.setQuantity(rs.getDouble(32));
		         
	      
	      }
	      commonUTIL.display("TransferSQL" , "getSingleTransfer " + sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("TransferSQL","getSingleTransfer" + SELECTWHERE,e);
			 return null;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TransferSQL",SELECTWHERE,e);
			}
	     }
	     return transfer;
		
	}
	public static Collection getTransferOnTrade(int tradeID, Connection conn) {
		// TODO Auto-generated method stub
		int j = 0;
	     PreparedStatement stmt = null;
	     Vector Transfers = new Vector();
	     String sql = SELECTWHERE;
	     sql = sql +  " tradeID = " + tradeID;
	    		  
		 try {
			
			 stmt = dsSQL.newPreparedStatement(conn, sql);
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Transfer transfer = new Transfer();
	        	 transfer.setId(rs.getInt(1));
			        transfer.setTradeId(rs.getInt(2));
			        transfer.setProductId(rs.getInt(3));
			        transfer.setAmount(rs.getDouble(4));
		            transfer.setEventType(rs.getString(5));
		            transfer.setTranserType(rs.getString(6));
		           transfer.setTransferStatus(rs.getString(7));
		          transfer.setSettlecurrency(rs.getString(8));
		             transfer.setPayerCode(rs.getString(9));
		             transfer.setPayerRole(rs.getString(10));
		           transfer.setReceiverCode(rs.getString(11));
		             transfer.setReceiverRole(rs.getString(12));
		             transfer.setPaymentStatus(rs.getString(13));
		             transfer.setDeliveryDate(rs.getString(14));
		            transfer.setValueDate(rs.getString(15));
		            transfer.setMethod(rs.getString(16));
		             transfer.setReceiverInst(rs.getString(17));
		             transfer.setPayerInst(rs.getString(18));
		             transfer.setAttributes(rs.getString(19));
		       		      
		             transfer.setStatus(rs.getString(20));
		             transfer.setAction(rs.getString(21));
		             transfer.setTradeVersionID(rs.getInt(22));
		             transfer.setLinkid(rs.getInt(23));
		             transfer.setNettedTransferID(rs.getInt(24));
				       
				        transfer.setNettedConfigID(rs.getInt(25));
				        transfer.setVersion(rs.getInt(26));
				        transfer.setSettleAmount(rs.getDouble(27));
				        transfer.setCPid(rs.getInt(28));
				        transfer.setProductType(rs.getString(29));
				        transfer.setUserid(rs.getInt(30));
				        transfer.setBookId(rs.getInt(31));
				        transfer.setQuantity(rs.getDouble(32));
		        Transfers.add(transfer);
	      
	      }
	      commonUTIL.display("TransferSQL" , "selectWhere " + sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("TransferSQL",SELECTWHERE,e);
			 return Transfers;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TransferSQL",SELECTWHERE,e);
			}
	     }
	     return Transfers;
	}
	
	 
	 
	public static Collection getNettedTransfer(String nettingSQL, Connection conn) {
		// TODO Auto-generated method stub
		int j = 0;
	     PreparedStatement stmt = null;
	     Vector Transfers = new Vector();
	     String sql = SELECTNettingTransfer + nettingSQL;
	    
	    		  
		 try {
			
			 stmt = dsSQL.newPreparedStatement(conn, sql);
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Transfer transfer = new Transfer();
	        	 transfer.setId(rs.getInt(1));
			        transfer.setTradeId(rs.getInt(2));
			        transfer.setProductId(rs.getInt(3));
			        transfer.setAmount(rs.getDouble(4));
		            transfer.setEventType(rs.getString(5));
		            transfer.setTranserType(rs.getString(6));
		           transfer.setTransferStatus(rs.getString(7));
		          transfer.setSettlecurrency(rs.getString(8));
		             transfer.setPayerCode(rs.getString(9));
		             transfer.setPayerRole(rs.getString(10));
		           transfer.setReceiverCode(rs.getString(11));
		             transfer.setReceiverRole(rs.getString(12));
		             transfer.setPaymentStatus(rs.getString(13));
		             transfer.setDeliveryDate(rs.getString(14));
		            transfer.setValueDate(rs.getString(15));
		            transfer.setMethod(rs.getString(16));
		             transfer.setReceiverInst(rs.getString(17));
		             transfer.setPayerInst(rs.getString(18));
		             transfer.setAttributes(rs.getString(19));
		       		      
		             transfer.setStatus(rs.getString(20));
		             transfer.setAction(rs.getString(21));
		             transfer.setTradeVersionID(rs.getInt(22));
		             transfer.setLinkid(rs.getInt(23));
		             transfer.setNettedTransferID(rs.getInt(24));
				       
				        transfer.setNettedConfigID(rs.getInt(25));
				        transfer.setVersion(rs.getInt(26));
				        transfer.setSettleAmount(rs.getDouble(27));
				        transfer.setCPid(rs.getInt(28));
				        transfer.setProductType(rs.getString(29));
				        transfer.setUserid(rs.getInt(30));
				        transfer.setBookId(rs.getInt(31));
				        transfer.setQuantity(rs.getDouble(32));
		        Transfers.add(transfer);
	      
	      }
	      commonUTIL.display("TransferSQL" , "  getNettedTransfer  selectWhere " + sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("TransferSQL"," getNettedTransfer " + SELECTWHERE,e);
			 return Transfers;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TransferSQL",SELECTWHERE,e);
			}
	     }
	     return Transfers;
	}

	public static Transfer getTransfer(int transferID, Connection conn) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				int j = 0;
			     PreparedStatement stmt = null;
			     Transfer transfer = new Transfer();
			 
			     String sql = SELECTWHERE;
			     sql = sql +  " id = " + transferID;
			     try {
						
					 stmt = dsSQL.newPreparedStatement(conn, sql);
			      
			      ResultSet rs = stmt.executeQuery();
			      
			      while(rs.next()) {
			    	  
			        	 transfer.setId(rs.getInt(1));
					        transfer.setTradeId(rs.getInt(2));
					        transfer.setProductId(rs.getInt(3));
					        transfer.setAmount(rs.getDouble(4));
				            transfer.setEventType(rs.getString(5));
				            transfer.setTranserType(rs.getString(6));
				           transfer.setTransferStatus(rs.getString(7));
				          transfer.setSettlecurrency(rs.getString(8));
				             transfer.setPayerCode(rs.getString(9));
				             transfer.setPayerRole(rs.getString(10));
				           transfer.setReceiverCode(rs.getString(11));
				             transfer.setReceiverRole(rs.getString(12));
				             transfer.setPaymentStatus(rs.getString(13));
				             transfer.setDeliveryDate(rs.getString(14));
				            transfer.setValueDate(rs.getString(15));
				            transfer.setMethod(rs.getString(16));
				             transfer.setReceiverInst(rs.getString(17));
				             transfer.setPayerInst(rs.getString(18));
				             transfer.setAttributes(rs.getString(19));
				       		      
				             transfer.setStatus(rs.getString(20));
				             transfer.setAction(rs.getString(21));
				             transfer.setTradeVersionID(rs.getInt(22));
				             transfer.setLinkid(rs.getInt(23));
				             transfer.setNettedTransferID(rs.getInt(24));
						       
						        transfer.setNettedConfigID(rs.getInt(25));
						        transfer.setVersion(rs.getInt(26));
						        transfer.setSettleAmount(rs.getDouble(27));
						        transfer.setCPid(rs.getInt(28));
						        transfer.setProductType(rs.getString(29));
						        transfer.setUserid(rs.getInt(30));
						        transfer.setBookId(rs.getInt(31));
						        transfer.setQuantity(rs.getDouble(32));
				      
			      
			      }
			      commonUTIL.display("TransferSQL" , "  getNettedTransfer  selectWhere " + sql);
				 } catch (Exception e) {
					 commonUTIL.displayError("TransferSQL"," getNettedTransfer " + SELECTWHERE,e);
					 return null;
			        
			     }
			     finally {
			        try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						commonUTIL.displayError("TransferSQL",SELECTWHERE,e);
					}
			     }
			     return transfer;
			}


}
