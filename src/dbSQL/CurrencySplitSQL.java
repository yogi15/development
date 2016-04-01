package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.CurrencySplitConfig;

public class CurrencySplitSQL {
	
	String currencyPair = "";
	String currencyToSplit = "";
	int  bookid;
	int firstSpotBook;
	int secondSpotBook;
	String firstCurrencySplit;
	String secondCurrencySPlit;
	int id;
	
	final static private String DELETE_FROM_CurrencySplitConfig =
			"DELETE FROM CurrencySplitConfig where id =? ";
		final static private String INSERT_FROM_CurrencySplitConfig =
			"INSERT into CurrencySplitConfig(id,currencyPair,currencyToSplit,bookid,firstCurrencySplit,secondCurrencySPlit,firstSpotBook,secondSpotBook) values(?,?,?,?,?,?,?,?)";
		
		final static private String SELECT_MAX =
			"SELECT MAX(id) DESC_ID FROM CurrencySplitConfig ";
		final static private String SELECTALL =
			"SELECT id,currencyPair,currencyToSplit,bookid,firstCurrencySplit,secondCurrencySPlit,firstSpotBook,secondSpotBook FROM CurrencySplitConfig order by id";
		final static private String SELECT =
			"SELECT id,currencyPair,currencyToSplit,bookid,firstCurrencySplit,secondCurrencySPlit,firstSpotBook,secondSpotBook FROM CurrencySplitConfig  where id =  ?";
		 static private String SELECTFROMTRADE =
			"SELECT id,currencyPair,currencyToSplit,bookid,firstCurrencySplit,secondCurrencySPlit,firstSpotBook,secondSpotBook  FROM CurrencySplitConfig where id =  " ;
		 static private String SELECTFROMWhere =
					"SELECT id,currencyPair,currencyToSplit,bookid,firstCurrencySplit,secondCurrencySPlit,firstSpotBook,secondSpotBook  FROM CurrencySplitConfig where   " ;
				 
		 private static String getUpdateSQL(CurrencySplitConfig splitC) {
		      String updateSQL = "UPDATE CurrencySplitConfig  set " +
		      		" ID= " + splitC.getId() + 
		      		" ,currencyPair= '" + splitC.getCurrencyPair() + 
		      		"' ,currencyToSplit= '" + splitC.getCurrencyToSplit() + 
		      		"' ,bookid= " + splitC.getBookid() + 	
		      		",firstCurrencySplit= '" + splitC.getFirstCurrencySplit() + 	
		      		"',secondCurrencySPlit= '" + splitC.getSecondCurrencySPlit() + 	
		      		"',firstSpotBook= " + splitC.getFirstSpotBook() + 	
		      		",secondSpotBook= " +   splitC.getSecondSpotBook() + 	
		      		
		      		"  where id= " + splitC.getId();
		      return updateSQL;
		     }
		 
		 public static CurrencySplitConfig save(CurrencySplitConfig insertCurrencySplitConfig, Connection con) {
			 try {
	             return insert(insertCurrencySplitConfig, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("CurrencySplitConfigSQL","save",e);
	        	 return null;
	         }
		 }
		 public static boolean update(CurrencySplitConfig updateCurrencySplitConfig, Connection con) {
			 try {
	             return edit(updateCurrencySplitConfig, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("CurrencySplitConfigSQL","update",e);
	        	 return false;
	         }
		 }
		 
		 public static boolean delete(CurrencySplitConfig deleteCurrencySplitConfig, Connection con) {
			 try {
	             return remove(deleteCurrencySplitConfig, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("CurrencySplitConfigSQL","update",e);
	        	 return false;
	         }
		 }
		 public static Vector<CurrencySplitConfig> selectCurrencySplitConfig(int CurrencySplitConfigId, Connection con) {
			 try {
	             return  select(CurrencySplitConfigId, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("CurrencySplitConfigSQL","select",e);
	        	 return null;
	         }
		 }
		 
		 
		 public static int selectMaxID(Connection con) {
			 try {
	             return selectMax(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("CurrencySplitConfigSQL","selectMaxID",e);
	        	 return 0;
	         }
		 }
		 
		 protected static  boolean edit(CurrencySplitConfig updateCurrencySplitConfig, Connection con ) {
			 
		        PreparedStatement stmt = null;
		        String sql = getUpdateSQL(updateCurrencySplitConfig);
			 try {
				 con.setAutoCommit(false);
				 int j = 1;
				 stmt = dsSQL.newPreparedStatement(con, sql);
		            stmt.executeUpdate(sql);
				 con.commit();
				 commonUTIL.display("CurrencySplitConfigSQL ::  edit", sql);
			 } catch (Exception e) {
				 commonUTIL.displayError("CurrencySplitConfigSQL","edit " + sql,e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("CurrencySplitConfigSQL","edit " + sql,e);
				}
		        }
		        return true;
		 }
		
		protected static boolean remove(CurrencySplitConfig deleteCurrencySplitConfig, Connection con ) {
		
		        PreparedStatement stmt = null;
			 try {
				 int j = 1;
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_CurrencySplitConfig);
		            stmt.setInt(j++, deleteCurrencySplitConfig.getId());
		           
		            stmt.executeUpdate();
				 con.commit();
			 } catch (Exception e) {
				 commonUTIL.displayError("CurrencySplitConfigSQL","remove",e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("CurrencySplitConfigSQL","remove",e);
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
				 commonUTIL.displayError("CurrencySplitConfigSQL",SELECT_MAX,e);
				 return j;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("CurrencySplitConfigSQL",SELECT_MAX,e);
				}
		        }
		        return j;
		 }
		 

	 protected static CurrencySplitConfig insert(CurrencySplitConfig inserCurrencySplitConfig, Connection con ) {
			
	        PreparedStatement stmt = null;
	        CurrencySplitConfig newSplitConfig = null;
		 try {
			 con.setAutoCommit(false);
			 int id = selectMax(con);
			 id = id+1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_CurrencySplitConfig);
			 stmt.setInt(1,id+1);
		
			 stmt.setString(2,inserCurrencySplitConfig.getCurrencyPair());
			 stmt.setString(3,inserCurrencySplitConfig.getCurrencyToSplit());
			 stmt.setInt(4,inserCurrencySplitConfig.getBookid());
	            stmt.setString(5, inserCurrencySplitConfig.getFirstCurrencySplit());
	            stmt.setString(6,inserCurrencySplitConfig.getSecondCurrencySPlit() );
	            stmt.setInt(7, inserCurrencySplitConfig.getFirstSpotBook());
	            stmt.setInt(8, inserCurrencySplitConfig.getSecondSpotBook());
	           
	            stmt.executeUpdate();
	            con.commit();
	            newSplitConfig = inserCurrencySplitConfig;
	            newSplitConfig.setId(id);
	            commonUTIL.display("CurrencySplitConfigSQL",INSERT_FROM_CurrencySplitConfig);
		 } catch (Exception e) {
			 commonUTIL.displayError("CurrencySplitConfigSQL",INSERT_FROM_CurrencySplitConfig,e);
			 return newSplitConfig;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("CurrencySplitConfigSQL",INSERT_FROM_CurrencySplitConfig,e);
			}
	        }
	        return newSplitConfig;
	 } 
	 
 protected static Vector<CurrencySplitConfig> select(int FeesIn,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector<CurrencySplitConfig> Fees = new Vector<CurrencySplitConfig>();
	        String sql = SELECT;
	        sql = sql + FeesIn;
	    
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql);
	         
	         ResultSet rs = stmt.executeQuery();
	         while(rs.next()) {
		        	
	        	 CurrencySplitConfig splitConfig = new CurrencySplitConfig();
	        	 splitConfig.setId(rs.getInt(1));
	        	 splitConfig.setCurrencyPair(rs.getString(2));
	        	 splitConfig.setCurrencyToSplit(rs.getString(3));
	 	        
	        	 splitConfig.setBookid(rs.getInt(4));
	        	 splitConfig.setFirstCurrencySplit(rs.getString(5));
	 	        
	        	 splitConfig.setSecondCurrencySPlit(rs.getString(6));
	        	 splitConfig.setFirstSpotBook(rs.getInt(7));
	        	 splitConfig.setSecondSpotBook(rs.getInt(8));
	         }commonUTIL.display("CurrencySplitConfigSQL"," select " + sql);
	         return Fees;
		 } catch (Exception e) {
			 commonUTIL.displayError("CurrencySplitConfigSQL","select    " + sql ,e);
			 return Fees;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("CurrencySplitConfigSQL","select " + sql ,e);
			}
	        
	    
	        }
 }
		 public static Collection select(Connection con) { 
			 int j = 0;
		     PreparedStatement stmt = null;
		     Vector CurrencySplitConfig = new Vector();
		     
			 try {
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
		      
		      ResultSet rs = stmt.executeQuery();
		      
		      while(rs.next()) {
		   
		    	  CurrencySplitConfig splitConfig = new CurrencySplitConfig();
		        	 splitConfig.setId(rs.getInt(1));
		        	 splitConfig.setCurrencyPair(rs.getString(2));
		        	 splitConfig.setCurrencyToSplit(rs.getString(3));
		 	        
		        	 splitConfig.setBookid(rs.getInt(4));
		        	 splitConfig.setFirstCurrencySplit(rs.getString(5));
		 	        
		        	 splitConfig.setSecondCurrencySPlit(rs.getString(6));
		        	 splitConfig.setFirstSpotBook(rs.getInt(7));
		        	 splitConfig.setSecondSpotBook(rs.getInt(8));
		        	 CurrencySplitConfig.addElement(splitConfig);
		      
		      }
			 } catch (Exception e) {
				 commonUTIL.displayError("CurrencySplitConfigSQL",SELECTALL,e);
				 return CurrencySplitConfig;
		        
		     }
		     finally {
		        try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("FeesSQL",SELECTALL,e);
				}
		     }
			return CurrencySplitConfig;
		   
		 }

		public static Collection selectALLCurrencySplitConfig() {
			// TODO Auto-generated method stub
			return null;
		}

		public static Vector selectWhere(String sql,Connection con) {
			// TODO Auto-generated method stub
			int j = 0;
	        PreparedStatement stmt = null;
	        Vector<CurrencySplitConfig> splitCs = new Vector<CurrencySplitConfig>();
	        String sql1 = SELECTFROMWhere + sql;
	      
	    
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql1);
	         
	         ResultSet rs = stmt.executeQuery();
	         while(rs.next()) {
		        	
	        	 CurrencySplitConfig splitConfig = new CurrencySplitConfig();
	        	 splitConfig.setId(rs.getInt(1));
	        	 splitConfig.setCurrencyPair(rs.getString(2));
	        	 splitConfig.setCurrencyToSplit(rs.getString(3));
	 	        
	        	 splitConfig.setBookid(rs.getInt(4));
	        	 splitConfig.setFirstCurrencySplit(rs.getString(5));
	 	        
	        	 splitConfig.setSecondCurrencySPlit(rs.getString(6));
	        	 splitConfig.setFirstSpotBook(rs.getInt(7));
	        	 splitConfig.setSecondSpotBook(rs.getInt(8));
	        	 splitCs.add(splitConfig);
	         }commonUTIL.display("CurrencySplitConfigSQL"," select " + sql1);
	         return splitCs;
		 } catch (Exception e) {
			 commonUTIL.displayError("CurrencySplitConfigSQL","select    " + sql ,e);
			 return splitCs;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("CurrencySplitConfigSQL","select " + sql ,e);
			}
	        
	    
	        }
		}
		 
		 
 }
 
 


