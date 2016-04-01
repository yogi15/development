package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.TradeCustomXferRule; 
import beans.TransferRule;

public class TradeCustomXFerRuleSQL {
	
	final static private String tableName = "TRADE_CUSTOMXFER_RULE";
	final static private String DELETE =
		"DELETE FROM " + tableName + "   where trade_id =? ";
	final static private String INSERT =
		"INSERT into " + tableName + "(trade_id,PRODUCT_ID , TRANSFER_TYPE ,  PRODUCT_TYPE,  TRANSFER_CCY,  PAY_RECEIVE , SETTLEMENT_METHOD,  PAYER_ID, "+
	"PAYER_ROLE, PAYER_SDID ,RECEIVER_ID,  RECEIVER_ROLE, RECEIVER_SDID ,   SETTLE_CCY ,   DELIVERY_TYPE ,   SEQ_NUMBER,"+
	"NETTING_METHOD_ID ,  NETTING_TYPE , PAYER_SDSTATUS, RECEIVER_SDSTATUS ,    PERCENTAGE, SECURITY_ID , MANUAL_SDI, "+
	"INT_SDI_VERSION,    EXT_SDI_VERSION ,   SETTLE_DATE,payerAgentID,payerMethod,receiverAgentID,receiverMethod)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	final static private String UPDATE =
		"UPDATE " + tableName + " set tradeId=?,productId=?,amount=?,rec_pay=?  where id = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(id) DESC_ID FROM " + tableName + " ";
	final static private String SELECTALL =
		" select trade_id,PRODUCT_ID , TRANSFER_TYPE ,  PRODUCT_TYPE,  TRANSFER_CCY,  PAY_RECEIVE , SETTLEMENT_METHOD,  PAYER_ID, "+
	"PAYER_ROLE, PAYER_SDID ,RECEIVER_ID,  RECEIVER_ROLE, RECEIVER_SDID ,   SETTLE_CCY ,   DELIVERY_TYPE ,   SEQ_NUMBER,"+
	"NETTING_METHOD_ID ,  NETTING_TYPE , PAYER_SDSTATUS, RECEIVER_SDSTATUS ,    PERCENTAGE, SECURITY_ID , MANUAL_SDI, "+
	"INT_SDI_VERSION,    EXT_SDI_VERSION ,   SETTLE_DATE,,payerAgentID,payerMethod,receiverAgentID,receiverMethod FROM " + tableName + " ";
	final static private String SELECTONTRADE =
			" select trade_id,PRODUCT_ID , TRANSFER_TYPE ,  PRODUCT_TYPE,  TRANSFER_CCY,  PAY_RECEIVE , SETTLEMENT_METHOD,  PAYER_ID, "+
					"PAYER_ROLE, PAYER_SDID ,RECEIVER_ID,  RECEIVER_ROLE, RECEIVER_SDID ,   SETTLE_CCY ,   DELIVERY_TYPE ,   SEQ_NUMBER,"+
					"NETTING_METHOD_ID ,  NETTING_TYPE , PAYER_SDSTATUS, RECEIVER_SDSTATUS ,    PERCENTAGE, SECURITY_ID , MANUAL_SDI, "+
					"INT_SDI_VERSION,    EXT_SDI_VERSION ,   SETTLE_DATE,payerAgentID,payerMethod,receiverAgentID,receiverMethod  FROM " + tableName +" where trade_id =  ";
	
	  
	  private static String getUpdateSQL(TransferRule tradeXRule ) {
		  String updateSQL = "UPDATE " + tableName +" set  productId=" +tradeXRule.get_productId()+ 
				            ",trade_id="+ tradeXRule.get_tradeId() + 
				            
				            ",TRANSFER_TYPE='"+ tradeXRule.get_transferType()+ 
				            "',PRODUCT_TYPE='"+ tradeXRule.get_productType()+ 
				            "',TRANSFER_CCY='"+ tradeXRule.get_transferCurrency() + 
				            "',PAY_RECEIVE='"+ tradeXRule.get_payReceive() + 
				            "',SETTLEMENT_METHOD='"+ tradeXRule.get_settlementMethod() + 
				            "',PAYER_ID='"+ tradeXRule.get_payerLegalEntityId() + 
				            "',PAYER_ROLE='"+ tradeXRule.get_payerLegalEntityRole() + 
				            "',PAYER_SDID='"+ tradeXRule.get_payerSDId() + 
				            "',RECEIVER_ID='"+ tradeXRule.get_receiverLegalEntityId() + 
				            "',RECEIVER_ROLE='"+ tradeXRule.get_receiverLegalEntityRole() + 
				            "',RECEIVER_SDID='"+ tradeXRule.get_receiverSDId() + 
				            "',SETTLE_CCY='"+ tradeXRule.get_settlementCurrency()+ 
				            "',DELIVERY_TYPE='"+ tradeXRule.get_deliveryType()+ 
				            "',SEQ_NUMBER='"+ tradeXRule.get_seqNumber()+ 
				            "',NETTING_METHOD_ID='"+ tradeXRule.get_nettingMethodId() + 
				            "',NETTING_TYPE='"+ tradeXRule.get_nettingType() + 
				            "',PAYER_SDSTATUS='"+ tradeXRule.get_payerSDStatus()  + 
				            "',PERCENTAGE="+ tradeXRule.get_percentage() + 
				            ",SECURITY_ID="+ tradeXRule.get_securityId() + 
				            ",MANUAL_SDI="+ tradeXRule.get_manualSDId() + 
				            ",INT_SDI_VERSION="+ tradeXRule.get_intSDIVersion() + 
				            ",EXT_SDI_VERSION="+ tradeXRule.get_extSDIVersion() + 
				            ",SETTLE_DATE="+ tradeXRule.get_settleDate() + 
				             
				            " where trade_id = "+ tradeXRule.get_tradeId() + " and PAY_RECEIVE = '" +tradeXRule.get_payReceive();
	      return updateSQL;
				           
				            
				            
				            
		  
	  }
	
	 public static int save(TransferRule insertTransfer, Connection con) {
		 try {
			 
             return insert(insertTransfer, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("TradeCustomXFerRuleSQL","save",e);
        	 return 0;
         }
	 }
	 public static boolean update(TransferRule updateTransfer, Connection con) {
		 try {
             return edit(updateTransfer, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("TradeCustomXFerRuleSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(TransferRule deleteTransfer, Connection con) {
		 try {
             return remove(deleteTransfer, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("TradeCustomXFerRuleSQL","update",e);
        	 return false;
         }
	 }
	 
	 protected static boolean remove(TransferRule deleteTransfer, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, DELETE);
	            stmt.setInt(j++, deleteTransfer.get_tradeId());
	           
	            stmt.executeUpdate();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("TradeCustomXFerRuleSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TradeCustomXFerRuleSQL","remove",e);
			}
	        }
	        return true;
	 }
	 
	 
	 public static Vector selectTransfer(int tradeId, Connection con) {
		 try {
          return (Vector) select(tradeId, con);
      }catch(Exception e) {
     	 commonUTIL.displayError("TradeCustomXFerRuleSQL","select",e);
     	 return null;
      }
	 }
	 public static Collection selectALL(Connection con) {
		 try {
          return select(con);
      }catch(Exception e) {
     	 commonUTIL.displayError("TradeCustomXFerRuleSQL","selectALL",e);
     	 return null;
      }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
          return selectMax(con);
      }catch(Exception e) {
     	 commonUTIL.displayError("TradeCustomXFerRuleSQL","selectMaxID",e);
     	 return 0;
      }
	 }
	 
	 protected static  boolean edit(TransferRule updateTransfer, Connection con ) {
		 
		 PreparedStatement stmt = null;
         String sql = "";
   try {
    
    int j = 1;
    con.setAutoCommit(false);
   
    sql = getUpdateSQL(updateTransfer);
    stmt = con.prepareStatement(sql);
            stmt.executeUpdate(sql);
  
            
             
             con.commit();
             commonUTIL.display("TradeCustomXFerRuleSQL  ::  edit", sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("TradeCustomXFerRuleSQL","edit",e);
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
			 commonUTIL.displayError("TradeCustomXFerRuleSQL","selectMax",e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TradeCustomXFerRuleSQL","selectMax",e);
			}
	        }
	        return j;
	 }
      
      protected static int insert(TransferRule insertTradeCustomXferRule, Connection con ) {
			
	        PreparedStatement stmt = null;
	        int id = 0;
		 try {
			 
			 stmt = dsSQL.newPreparedStatement(con, INSERT);
			 commonUTIL.display(" TradeCustomXFerRuleSQL insert ", INSERT);
			 stmt.setInt(1,insertTradeCustomXferRule.get_tradeId());
			
	            stmt.setInt(2, insertTradeCustomXferRule.get_productId());
	            
	            stmt.setString(3, insertTradeCustomXferRule.get_transferType());
	            stmt.setString(4, insertTradeCustomXferRule.get_productType());
	            stmt.setString(5, insertTradeCustomXferRule.get_transferCurrency());
	            stmt.setString(6, insertTradeCustomXferRule.get_payReceive());
	            stmt.setString(7, insertTradeCustomXferRule.get_settlementMethod());
	            stmt.setInt(8, insertTradeCustomXferRule.get_payerLegalEntityId());
	            stmt.setString(9, insertTradeCustomXferRule.get_payerLegalEntityRole());
	            stmt.setInt(10, insertTradeCustomXferRule.get_payerSDId());
	            stmt.setInt(11, insertTradeCustomXferRule.get_receiverLegalEntityId());
	            stmt.setString(12, insertTradeCustomXferRule.get_receiverLegalEntityRole());
	            stmt.setInt(13, insertTradeCustomXferRule.get_receiverSDId());
	            stmt.setString(14, insertTradeCustomXferRule.get_settlementCurrency());
	            stmt.setString(15,"DVP"); // what value to passed need to anaylsis. 
	            stmt.setInt(16, insertTradeCustomXferRule.get_seqNumber());
	            stmt.setInt(17, insertTradeCustomXferRule.get_nettingMethodId());
	            stmt.setString(18, insertTradeCustomXferRule.get_nettingType());
	        	
	            stmt.setString(19, insertTradeCustomXferRule.get_payerSDStatus());
	            stmt.setString(20, insertTradeCustomXferRule.get_receiverSDStatus());
	            stmt.setFloat(21, 0);
	            stmt.setInt(22, insertTradeCustomXferRule.get_securityId());
	         
	            stmt.setInt(23, insertTradeCustomXferRule.get_manualSDId());
	            stmt.setInt(24, insertTradeCustomXferRule.get_intSDIVersion());
	            stmt.setInt(25, insertTradeCustomXferRule.get_extSDIVersion());
	            stmt.setTimestamp(26,commonUTIL.convertStringtoSQLTimeStamp(insertTradeCustomXferRule.get_settleDate()));
	            stmt.setInt(27, insertTradeCustomXferRule.get_payerAgentID());
	            stmt.setString(28, insertTradeCustomXferRule.getPayerMethodType());
	            stmt.setInt(29, insertTradeCustomXferRule.get_receiverAgentID());
	            
	            stmt.setString(30, insertTradeCustomXferRule.getReceiverMethodType());
	            stmt.executeUpdate();
	         //   System.out.println("TradeCustomXFerRuleSQL ::  insert"+ INSERT);
	            commonUTIL.display("TradeCustomXFerRuleSQL ::  insert", INSERT);
	       //    ..  System.out.println(" inserTransfer " + inserTransfer + " status " + inserTransfer.getId());
	             con.commit();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("TradeCustomXFerRuleSQL","insert",e);
			 return 0;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TradeCustomXFerRuleSQL","insert",e);
			}
	        }
	        return id;
	 }
	 
	 protected static Collection select(int tradeID,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector Transfers = new Vector();
	        String sql = SELECTONTRADE + tradeID;
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con,sql);
	         
	         ResultSet rs = stmt.executeQuery();
	          while(rs.next()) {
	        	   /*   trade_id,PRODUCT_ID , TRANSFER_TYPE ,  PRODUCT_TYPE,  TRANSFER_CCY,  PAY_RECEIVE , SETTLEMENT_METHOD,  PAYER_ID, "+
			     	"PAYER_ROLE, PAYER_SDID ,RECEIVER_ID,  RECEIVER_ROLE, RECEIVER_SDID ,   SETTLE_CCY ,   DELIVERY_TYPE ,   SEQ_NUMBER,"+
			     	"NETTING_METHOD_ID ,  NETTING_TYPE , PAYER_SDSTATUS, RECEIVER_SDSTATUS ,    PERCENTAGE, SECURITY_ID , MANUAL_SDI, "+ */
			     	//"INT_SDI_VERSION,    EXT_SDI_VERSION ,   SETTLE_DATE */
			    
	        	 TransferRule tradeCustomXferRule = new TransferRule();
	        	 tradeCustomXferRule.set_tradeId(rs.getInt(1));
			        tradeCustomXferRule.set_productId(rs.getInt(2));
			        tradeCustomXferRule.set_transferType(rs.getString(3));
			        tradeCustomXferRule.set_productType(rs.getString(4));
			        tradeCustomXferRule.set_transferCurrency(rs.getString(5));
		            tradeCustomXferRule.set_payReceive(rs.getString(6));
		            tradeCustomXferRule.set_settlementMethod(rs.getString(7));
		           tradeCustomXferRule.set_payerLegalEntityId(rs.getInt(8));
		          tradeCustomXferRule.set_payerLegalEntityRole(rs.getString(9));
		             tradeCustomXferRule.set_payerSDId(rs.getInt(10));
		             tradeCustomXferRule.set_receiverLegalEntityId(rs.getInt(11));
		           tradeCustomXferRule.set_receiverLegalEntityRole(rs.getString(12));
		             tradeCustomXferRule.set_receiverSDId( rs.getInt(13));
		             tradeCustomXferRule.set_settlementCurrency(rs.getString(14));
		             tradeCustomXferRule.set_deliveryType(rs.getString(15));
		            tradeCustomXferRule.set_seqNumber(rs.getInt(16));
		            tradeCustomXferRule.set_nettingMethodId(rs.getInt(17));
		             tradeCustomXferRule.set_nettingType(rs.getString(18));
		             tradeCustomXferRule.set_payerSDStatus(rs.getString(19));

		             tradeCustomXferRule.set_receiverSDStatus(rs.getString(20));
		             tradeCustomXferRule.set_percentage(rs.getInt(21));
			        tradeCustomXferRule.set_securityId(rs.getInt(22));
			        tradeCustomXferRule.set_manualSDId(rs.getInt(23));
			      
			        tradeCustomXferRule.set_intSDIVersion(rs.getInt(24));
			        tradeCustomXferRule.set_extSDIVersion(rs.getInt(25));
			       
			       tradeCustomXferRule.set_settleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp((26))));
			        
			       tradeCustomXferRule.set_payerAgentID(rs.getInt(27));
				      
			        tradeCustomXferRule.setPayerMethodType(rs.getString(28));
			        tradeCustomXferRule.set_receiverAgentID(rs.getInt(29));
			        tradeCustomXferRule.setReceiverMethodType(rs.getString(30));
			        Transfers.add(tradeCustomXferRule);
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("TradeCustomXFerRuleSQL","select " +sql,e);
			 return Transfers;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TradeCustomXFerRuleSQL","select "+sql,e);
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
	    	  TransferRule tradeCustomXferRule = new TransferRule();
	        	 tradeCustomXferRule.set_tradeId(rs.getInt(1));
			        tradeCustomXferRule.set_productId(rs.getInt(2));
			        tradeCustomXferRule.set_transferType(rs.getString(3));
			        tradeCustomXferRule.set_productType(rs.getString(4));
			        tradeCustomXferRule.set_transferCurrency(rs.getString(5));
		            tradeCustomXferRule.set_payReceive(rs.getString(6));
		            tradeCustomXferRule.set_settlementMethod(rs.getString(7));
		           tradeCustomXferRule.set_payerLegalEntityId(rs.getInt(8));
		          tradeCustomXferRule.set_payerLegalEntityRole(rs.getString(9));
		             tradeCustomXferRule.set_payerSDId(rs.getInt(10));
		             tradeCustomXferRule.set_receiverLegalEntityId(rs.getInt(11));
		           tradeCustomXferRule.set_receiverLegalEntityRole(rs.getString(12));
		             tradeCustomXferRule.set_receiverSDId( rs.getInt(13));
		             tradeCustomXferRule.set_settlementCurrency(rs.getString(14));
		             tradeCustomXferRule.set_deliveryType(rs.getString(15));
		            tradeCustomXferRule.set_seqNumber(rs.getInt(16));
		            tradeCustomXferRule.set_nettingMethodId(rs.getInt(17));
		             tradeCustomXferRule.set_nettingType(rs.getString(18));
		             tradeCustomXferRule.set_payerSDStatus(rs.getString(19));

		             tradeCustomXferRule.set_receiverSDStatus(rs.getString(20));
		             tradeCustomXferRule.set_percentage(rs.getInt(21));
			        tradeCustomXferRule.set_securityId(rs.getInt(22));
			        tradeCustomXferRule.set_manualSDId(rs.getInt(23));
			      
			        tradeCustomXferRule.set_intSDIVersion(rs.getInt(24));
			        tradeCustomXferRule.set_extSDIVersion(rs.getInt(25));
			       
			       tradeCustomXferRule.set_settleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp((26))));
			        
			       
		       
		        Transfers.add(tradeCustomXferRule);
		     
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("TradeCustomXFerRuleSQL","select",e);
			 return Transfers;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TradeCustomXFerRuleSQL","selectMax",e);
			}
	     }
	     return Transfers;
	 }
 


}
