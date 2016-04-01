package dbSQL;

import beans.Fees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;


public class FeesSQL {
	
	int tradeID = 0;
	String feeType;
	double amount;
    int leID =0;
    String feeDate;
    String startDate;
    String endDate;
    String currency;
    String payrec = "Pay";
	
	final static private String DELETE_FROM_FEES =
			"DELETE FROM FEES where id =? ";
		final static private String INSERT_FROM_Fees =
			"INSERT into FEES(id,tradeID,amount,leid,feeDate,feeType,startDate,endDate,currency,payrec,leRole) values(?,?,?,?,?,?,?,?,?,?,?)";
		
		final static private String SELECT_MAX =
			"SELECT MAX(id) DESC_ID FROM fees ";
		final static private String SELECTALL =
			"SELECT id,tradeID,amount,leid,feeDate,feeType,startDate,endDate,currency,payrec,leRole FROM fees order by id";
		final static private String SELECT =
			"SELECT id,tradeID,amount,leid,feeDate,feeType,startDate,endDate,currency,payrec,leRole FROM fees  where id =  ?";
		 static private String SELECTFROMTRADE =
			"SELECT id,tradeID,amount,leid,feeDate,feeType,startDate,endDate,currency,payrec,leRole FROM fees where tradeid =  " ;
		 
		 
		 private static String getUpdateSQL(Fees fee) {
		      String updateSQL = "UPDATE FEES  set " +
		      		" tradeID= " + fee.getTradeID() + 
		      		" ,amount= " + fee.getAmount() + 
		      		" ,leid= " + fee.getLeID() + 
		      		" ,feeDate= '" + fee.getFeeDate() + 	
		      		"',feeType= '" + fee.getFeeType() + 	
		      		"',startDate= '" + fee.getStartDate() + 	
		      		"',endDate= '" + fee.getEndDate() + 	
		      		"',currency= '" + fee.getCurrency() + 	
		      		"',payrec= '" +fee.getPayrec()+ 	
		      		"',leRole= '" +fee.getLeRole()+ 	
		      		"'  where id= " + fee.getId();
		      return updateSQL;
		     }
		 
		 
		 
		 public static boolean save(Fees insertFees, Connection con) {
			 try {
	             return insert(insertFees, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("FeesSQL","save",e);
	        	 return false;
	         }
		 }
		 public static boolean update(Fees updateFees, Connection con) {
			 try {
	             return edit(updateFees, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("FeesSQL","update",e);
	        	 return false;
	         }
		 }
		 
		 public static boolean delete(Fees deleteFees, Connection con) {
			 try {
	             return remove(deleteFees, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("FeesSQL","update",e);
	        	 return false;
	         }
		 }
		 public static Vector<Fees> selectFees(int FeesId, Connection con) {
			 try {
	             return  select(FeesId, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("FeesSQL","select",e);
	        	 return null;
	         }
		 }
		 public static Collection selectFeesOnTrades(int tradeID, Connection con) {
			 try {
	             return selectFeesOnTrade(tradeID, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("FeesSQL","selectFeesOnTrades",e);
	        	 return null;
	         }
		 }
		 
		 public static int selectMaxID(Connection con) {
			 try {
	             return selectMax(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("FeesSQL","selectMaxID",e);
	        	 return 0;
	         }
		 }
		 
		 protected static  boolean edit(Fees updateFees, Connection con ) {
			 
		        PreparedStatement stmt = null;
		        String sql = getUpdateSQL(updateFees);
			 try {
				 con.setAutoCommit(false);
				 int j = 1;
				 stmt = dsSQL.newPreparedStatement(con, sql);
		            stmt.executeUpdate(sql);
				 con.commit();
				 commonUTIL.display("FeesSQL ::  edit", sql);
			 } catch (Exception e) {
				 commonUTIL.displayError("FeesSQL","edit " + sql,e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("FeesSQL","edit " + sql,e);
				}
		        }
		        return true;
		 }
		
		protected static boolean remove(Fees deleteFees, Connection con ) {
		
		        PreparedStatement stmt = null;
			 try {
				 int j = 1;
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_FEES);
		            stmt.setInt(j++, deleteFees.getId());
		           
		            stmt.executeUpdate();
				 con.commit();
			 } catch (Exception e) {
				 commonUTIL.displayError("FeesSQL","remove",e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("FeesSQL","remove",e);
				}
		        }
		        return true;
		 }

	protected static int selectMax(Connection con ) {
			 
			 int j = 0;
		        PreparedStatement stmt = null;
			 try {
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);
		         
		         ResultSet rs = stmt.executeQuery();
		         while(rs.next())
		         j = rs.getInt("DESC_ID");
				 
			 } catch (Exception e) {
				 commonUTIL.displayError("FeesSQL",SELECT_MAX,e);
				 return j;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("FeesSQL",SELECT_MAX,e);
				}
		        }
		        return j;
		 }
		 

	 protected static boolean insert(Fees inserFees, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 con.setAutoCommit(false);
			 int id = selectMax(con);
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_Fees);
			 stmt.setInt(1,id+1);
		
			 stmt.setInt(2,inserFees.getTradeID());
			 stmt.setDouble(3,inserFees.getAmount());
			 stmt.setInt(4,inserFees.getLeID());
	            stmt.setString(5, inserFees.getFeeDate());
	            stmt.setString(6,inserFees.getFeeType() );
	            stmt.setString(7, inserFees.getStartDate());
	            stmt.setString(8,inserFees.getEndDate());
	            stmt.setString(9, inserFees.getCurrency());
	            stmt.setString(10,inserFees.getPayrec() );
	            stmt.setString(11,inserFees.getLeRole() );
	            stmt.executeUpdate();
	            con.commit();
	            commonUTIL.display("FeesSQL",INSERT_FROM_Fees);
		 } catch (Exception e) {
			 commonUTIL.displayError("FeesSQL",INSERT_FROM_Fees,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FeesSQL",INSERT_FROM_Fees,e);
			}
	        }
	        return true;
	 }
	 
	 protected static Vector<Fees> select(int FeesIn,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector<Fees> Fees = new Vector();
	        String sql = SELECT;
	        sql = sql + FeesIn;
	    
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	
	        Fees fee = new Fees();
	        fee.setId(rs.getInt(1));
	        fee.setTradeID(rs.getInt(2));
	        fee.setAmount(rs.getInt(3));
	        
	        fee.setLeID(rs.getInt(4));
	        fee.setFeeDate(rs.getString(5));
	        
	        fee.setFeeType(rs.getString(6));
	        fee.setStartDate(rs.getString(7));
	        fee.setEndDate(rs.getString(8));
	        fee.setCurrency(rs.getString(9));
	        fee.setPayrec(rs.getString(10));
	        fee.setLeRole(rs.getString(11));
	        Fees.addElement(fee);
	        
	      
	       
	         
	         }
	         commonUTIL.display("FeesSQL"," select " + sql);
	         return Fees;
		 } catch (Exception e) {
			 commonUTIL.displayError("FeesSQL","select    " + sql ,e);
			 return Fees;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FeesSQL","select " + sql ,e);
			}
	        }
	    
	 }

	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Fees = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	   
	    	  Fees fee = new Fees();
		        fee.setId(rs.getInt(1));
		        fee.setTradeID(rs.getInt(2));
		        fee.setAmount(rs.getInt(3));
		        
		        fee.setLeID(rs.getInt(4));
		        fee.setFeeDate(rs.getString(5));
		        
		        fee.setFeeType(rs.getString(6));
		        fee.setStartDate(rs.getString(7));
		        fee.setEndDate(rs.getString(8));
		        fee.setCurrency(rs.getString(9));
		        fee.setPayrec(rs.getString(10));
		        fee.setLeRole(rs.getString(11));
		        Fees.addElement(fee);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("FeesSQL",SELECTALL,e);
			 return Fees;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FeesSQL",SELECTALL,e);
			}
	     }
		return Fees;
	   
	 }
	 protected static Collection selectFeesOnTrade(int tradeID,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Fees = new Vector();
	     String sql = SELECTFROMTRADE;
		 try {
			 con.setAutoCommit(false);
			sql = sql  + tradeID;
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Fees fee = new Fees();
		        fee.setId(rs.getInt(1));
		        fee.setTradeID(rs.getInt(2));
		        fee.setAmount(rs.getInt(3));
		        
		        fee.setLeID(rs.getInt(4));
		        fee.setFeeDate(rs.getString(5));
		        
		        fee.setFeeType(rs.getString(6));
		        fee.setStartDate(rs.getString(7));
		        fee.setEndDate(rs.getString(8));
		        fee.setCurrency(rs.getString(9));
		        fee.setPayrec(rs.getString(10));
		        fee.setLeRole(rs.getString(11));
		        Fees.addElement(fee);
	      
	      }
	      commonUTIL.display("FeesSQL","selectFeesOnTrade "+sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("FeesSQL","selectFeesOnTrade ",e);
			 return Fees;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FeesSQL","selectMax",e);
			}
	     }
		return Fees;
	    
	 }


}
