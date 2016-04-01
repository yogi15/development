package dbSQL;

import beans.B2BConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
public class B2BConfigSQL {
	final static private String DELETE_FROM_B2BConfig =
			"DELETE FROM B2BConfig where id =? ";
		final static private String INSERT_FROM_B2BConfig =
			"INSERT into B2BConfig(id,currencyPair,currencyHolding,bookid,TransferTOBook) values(?,?,?,?,?)";
		
		final static private String SELECT_MAX =
			"SELECT MAX(id) DESC_ID FROM B2BConfig ";
		final static private String SELECTALL =
			"SELECT id,currencyPair,currencyHolding,bookid,TransferTOBook FROM B2BConfig order by id";
		final static private String SELECT =
			"SELECT id,currencyPair,currencyHolding,bookid,TransferTOBook FROM B2BConfig  where id =  ?";
		 static private String SELECTFROMTRADE =
			"SELECT id,currencyPair,currencyHolding,bookid,TransferTOBook  FROM B2BConfig where id =  " ;
		 static private String SELECTFROMWhere =
					"SELECT id,currencyPair,currencyHolding,bookid,TransferTOBook  FROM B2BConfig where   " ;
				 
		 private static String getUpdateSQL(B2BConfig splitC) {
		      String updateSQL = "UPDATE B2BConfig  set " +
		      		" ID= " + splitC.getId() + 
		      		" ,currencyPair= '" + splitC.getCurrencyPair() + 
		      		"' ,currencyHolding= '" + splitC.getHoldingCurrency() + 
		      		"' ,bookid= " + splitC.getBookid() + 	
		      		",TransferTOBook= " + splitC.getTransferBookTo() + 	
		      			
		      		
		      		"  where id= " + splitC.getId();
		      return updateSQL;
		     }
		 
		 public static B2BConfig save(B2BConfig insertB2BConfig, Connection con) {
			 try {
	             return insert(insertB2BConfig, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("B2BConfigSQL","save",e);
	        	 return null;
	         }
		 }
		 public static boolean update(B2BConfig updateB2BConfig, Connection con) {
			 try {
	             return edit(updateB2BConfig, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("B2BConfigSQL","update",e);
	        	 return false;
	         }
		 }
		 
		 public static boolean delete(B2BConfig deleteB2BConfig, Connection con) {
			 try {
	             return remove(deleteB2BConfig, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("B2BConfigSQL","update",e);
	        	 return false;
	         }
		 }
		 public static Vector<B2BConfig> selectB2BConfig(int B2BConfigId, Connection con) {
			 try {
	             return  select(B2BConfigId, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("B2BConfigSQL","select",e);
	        	 return null;
	         }
		 }
		 
		 
		 public static int selectMaxID(Connection con) {
			 try {
	             return selectMax(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("B2BConfigSQL","selectMaxID",e);
	        	 return 0;
	         }
		 }
		 
		 protected static  boolean edit(B2BConfig updateB2BConfig, Connection con ) {
			 
		        PreparedStatement stmt = null;
		        String sql = getUpdateSQL(updateB2BConfig);
			 try {
				 con.setAutoCommit(false);
				 int j = 1;
				 stmt = dsSQL.newPreparedStatement(con, sql);
		            stmt.executeUpdate(sql);
				 con.commit();
				 commonUTIL.display("B2BConfigSQL ::  edit", sql);
			 } catch (Exception e) {
				 commonUTIL.displayError("B2BConfigSQL","edit " + sql,e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("B2BConfigSQL","edit " + sql,e);
				}
		        }
		        return true;
		 }
		
		protected static boolean remove(B2BConfig deleteB2BConfig, Connection con ) {
		
		        PreparedStatement stmt = null;
			 try {
				 int j = 1;
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_B2BConfig);
		            stmt.setInt(j++, deleteB2BConfig.getId());
		           
		            stmt.executeUpdate();
				 con.commit();
			 } catch (Exception e) {
				 commonUTIL.displayError("B2BConfigSQL","remove",e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("B2BConfigSQL","remove",e);
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
				 commonUTIL.displayError("B2BConfigSQL",SELECT_MAX,e);
				 return j;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("B2BConfigSQL",SELECT_MAX,e);
				}
		        }
		        return j;
		 }
		 

	 protected static B2BConfig insert(B2BConfig inserB2BConfig, Connection con ) {
			
	        PreparedStatement stmt = null;
	        B2BConfig newSplitConfig = null;
		 try {
			 con.setAutoCommit(false);
			 int id = selectMax(con);
			 id = id+1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_B2BConfig);
			 stmt.setInt(1,id+1);
			
			 stmt.setString(2,inserB2BConfig.getCurrencyPair());
			 stmt.setString(3,inserB2BConfig.getHoldingCurrency());
			 stmt.setInt(4,inserB2BConfig.getBookid());
	            stmt.setInt(5, inserB2BConfig.getTransferBookTo());
	           
	           
	            stmt.executeUpdate();
	            con.commit();
	            newSplitConfig = inserB2BConfig;
	            newSplitConfig.setId(id);
	            commonUTIL.display("B2BConfigSQL",INSERT_FROM_B2BConfig);
		 } catch (Exception e) {
			 commonUTIL.displayError("B2BConfigSQL",INSERT_FROM_B2BConfig,e);
			 return newSplitConfig;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("B2BConfigSQL",INSERT_FROM_B2BConfig,e);
			}
	        }
	        return newSplitConfig;
	 } 
	 
 protected static Vector<B2BConfig> select(int FeesIn,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector<B2BConfig> Fees = new Vector<B2BConfig>();
	        String sql = SELECT;
	        sql = sql + FeesIn;
	       
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql);
	         
	         ResultSet rs = stmt.executeQuery();
	         while(rs.next()) {
		        	
	        	 B2BConfig splitConfig = new B2BConfig();
	        	 splitConfig.setId(rs.getInt(1));
	        	 splitConfig.setCurrencyPair(rs.getString(2));
	        	 splitConfig.setHoldingCurrency(rs.getString(3));
	 	        
	        	 splitConfig.setBookid(rs.getInt(4));
	        	 splitConfig.setTransferBookTo(rs.getInt(5));
	 	        
	        	
	         }commonUTIL.display("B2BConfigSQL"," select " + sql);
	         return Fees;
		 } catch (Exception e) {
			 commonUTIL.displayError("B2BConfigSQL","select    " + sql ,e);
			 return Fees;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("B2BConfigSQL","select " + sql ,e);
			}
	        
	    
	        }
 }
		 public static Collection select(Connection con) { 
			 int j = 0;
		     PreparedStatement stmt = null;
		     Vector B2BConfig = new Vector();
		     
			 try {
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
		      
		      ResultSet rs = stmt.executeQuery();
		      
		      while(rs.next()) {
		   
		    	  B2BConfig splitConfig = new B2BConfig();
		    	  splitConfig.setId(rs.getInt(1));
		        	 splitConfig.setCurrencyPair(rs.getString(2));
		        	 splitConfig.setHoldingCurrency(rs.getString(3));
		 	        
		        	 splitConfig.setBookid(rs.getInt(4));
		        	 splitConfig.setTransferBookTo(rs.getInt(5));
		 	        
		        	 B2BConfig.addElement(splitConfig);
		      
		      }
			 } catch (Exception e) {
				 commonUTIL.displayError("B2BConfigSQL",SELECTALL,e);
				 return B2BConfig;
		        
		     }
		     finally {
		        try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("FeesSQL",SELECTALL,e);
				}
		     }
			return B2BConfig;
		   
		 }

		public static Collection selectALLB2BConfig() {
			// TODO Auto-generated method stub
			return null;
		}

		public static Vector selectWhere(String sql,Connection con) {
			// TODO Auto-generated method stub
			int j = 0;
	        PreparedStatement stmt = null;
	        Vector<B2BConfig> splitCs = new Vector<B2BConfig>();
	        String sql1 = SELECTFROMWhere + sql;
	      
	    
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql1);
	         
	         ResultSet rs = stmt.executeQuery();
	         while(rs.next()) {
		        	
	        	 B2BConfig splitConfig = new B2BConfig();
	        	 splitConfig.setId(rs.getInt(1));
	        	 splitConfig.setId(rs.getInt(1));
	        	 splitConfig.setCurrencyPair(rs.getString(2));
	        	 splitConfig.setHoldingCurrency(rs.getString(3));
	 	        
	        	 splitConfig.setBookid(rs.getInt(4));
	        	 splitConfig.setTransferBookTo(rs.getInt(5));
	 	        
	        	 splitCs.add(splitConfig);
	         }commonUTIL.display("B2BConfigSQL"," select " + sql1);
	         return splitCs;
		 } catch (Exception e) {
			 commonUTIL.displayError("B2BConfigSQL","select    " + sql ,e);
			 return splitCs;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("B2BConfigSQL","select " + sql ,e);
			}
	        
	    
	        }
		}
		 
		 
 }
 
 


