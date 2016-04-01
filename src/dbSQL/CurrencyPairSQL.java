package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.CurrencyPair;

import util.commonUTIL;

public class CurrencyPairSQL {
	
	
//	select primary_currency,quoting_currency,quote_factor,bp_factor,rounding,pair_pos_ref_b,spot_days from CURRENCYPAIR

	
	
	final static private String DELETE_FROM_CURRENCYPAIR =
	"DELETE FROM CURRENCYPAIR where  ";
final static private String INSERT_FROM_CURRENCYPAIR =
	"INSERT into CURRENCYPAIR(primary_currency,quoting_currency,quote_factor,bp_factor,rounding,pair_pos_ref_b,spot_days) values(?,?,?,?,?,?,?)";
final static private String UPDATE_FROM_CURRENCYPAIR =
	"UPDATE CURRENCYPAIR set primary_currency=?,quoting_currency=?,quote_factor=?,bp_factor=?,rounding=?,pair_pos_ref_b=?,spot_days=? where quoting_currency = ? ";
final static private String SELECT_MAX =
	"SELECT MAX(CurrencyPairno) DESC_ID FROM CURRENCYPAIR ";
final static private String SELECTALL =
	"select primary_currency,quoting_currency,quote_factor,bp_factor,rounding,pair_pos_ref_b,spot_days from CURRENCYPAIR ";
final static private String SELECT =
	"select primary_currency,quoting_currency,quote_factor,bp_factor,rounding,pair_pos_ref_b,spot_days from CURRENCYPAIR where quoting_currency =  ?";
 static private String SELECTONE =
	"select primary_currency,quoting_currency,quote_factor,bp_factor,rounding,pair_pos_ref_b,spot_days from CURRENCYPAIR  where " ;

 
 public static boolean save(CurrencyPair insertCurrencyPair, Connection con) {
	 try {
         return insert(insertCurrencyPair, con);
     }catch(Exception e) {
    	 commonUTIL.displayError("CurrencyPairSQL","save",e);
    	 return false;
     }
 }
 public static CurrencyPair update(CurrencyPair updateCurrencyPair, Connection con) {
	 try {
         return edit(updateCurrencyPair, con);
     }catch(Exception e) {
    	 commonUTIL.displayError("CurrencyPairSQL","update",e);
    	 return null;
     }
 }
 
 public static boolean delete(CurrencyPair deleteCurrencyPair, Connection con) {
	 try {
         return remove(deleteCurrencyPair, con);
     }catch(Exception e) {
    	 commonUTIL.displayError("CurrencyPairSQL","update",e);
    	 return false;
     }
 }
 public static Collection selectALL(Connection con) {
	 try {
         return select(con);
     }catch(Exception e) {
    	 commonUTIL.displayError("CurrencyPairSQL","selectALL",e);
    	 return null;
     }
 }
 
 public static int selectMaxID(Connection con) {
	 try {
         return selectMax(con);
     }catch(Exception e) {
    	 commonUTIL.displayError("CurrencyPairSQL","selectMaxID",e);
    	 return 0;
     }
 }
 
 protected static  CurrencyPair edit(CurrencyPair updateCurrencyPair, Connection con ) {
	 
        PreparedStatement stmt = null;
       
	 try {
		 
		 con.setAutoCommit(false);
		 int j = 1;
		 stmt = dsSQL.newPreparedStatement(con, UPDATE_FROM_CURRENCYPAIR);
            
		
           
            
		 stmt.setString(1,updateCurrencyPair.getPrimary_currency());
		 stmt.setString(2,updateCurrencyPair.getQuoting_currency());
		 stmt.setInt(3,updateCurrencyPair.getQuote_factor());
		 stmt.setInt(4,updateCurrencyPair.getBp_factor());
		 stmt.setInt(5,updateCurrencyPair.getRounding());
		 stmt.setInt(6,updateCurrencyPair.getPair_pos_ref_b());
		 stmt.setInt(7,updateCurrencyPair.getSpot_days());
		 stmt.setString(8,updateCurrencyPair.getPrimary_currency());
            stmt.executeUpdate();
		 con.commit();
		 return updateCurrencyPair;
	 } catch (Exception e) {
		 commonUTIL.displayError("CurrencyPairSQL","edit",e);
		 return null;
           
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("CurrencyPairSQL","edit "+ UPDATE_FROM_CURRENCYPAIR,e);
		}
        }
 }

protected static boolean remove(CurrencyPair deleteCurrencyPair, Connection con ) {

        PreparedStatement stmt = null;
	 try {
		 int j = 1;
		 con.setAutoCommit(false);
	//	 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_CurrencyPair);
      //      stmt.setInt(j++, deleteCurrencyPair.getCurrencyPairno());
           
            stmt.executeUpdate();
		 con.commit();
	 } catch (Exception e) {
		 commonUTIL.displayError("CurrencyPairSQL","remove",e);
		 return false;
           
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("CurrencyPairSQL","remove",e);
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
		 commonUTIL.displayError("CurrencyPairSQL",SELECT_MAX,e);
		 return j;
           
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("CurrencyPairSQL",SELECT_MAX,e);
		}
        }
        return j;
 }
 
 protected static boolean insert(CurrencyPair inserCurrencyPair, Connection con ) {
		
        PreparedStatement stmt = null;
	 try {
		
		 con.setAutoCommit(false);
	//	 int id = selectMax(con);
		 int j = 1;
		 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_CURRENCYPAIR);
		 stmt.setString(1,inserCurrencyPair.getPrimary_currency());
		 stmt.setString(2,inserCurrencyPair.getQuoting_currency());
		 stmt.setInt(3,inserCurrencyPair.getQuote_factor());
		 stmt.setInt(4,inserCurrencyPair.getBp_factor());
		 stmt.setInt(5,inserCurrencyPair.getRounding());
		 stmt.setInt(6,inserCurrencyPair.getPair_pos_ref_b());
		 stmt.setInt(7,inserCurrencyPair.getSpot_days());
          
           
            stmt.executeUpdate();
            con.commit();
	 } catch (Exception e) {
		 commonUTIL.displayError("CurrencyPairSQL",INSERT_FROM_CURRENCYPAIR,e);
		 return false;
           
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("CurrencyPairSQL",INSERT_FROM_CURRENCYPAIR,e);
		}
        }
        return true;
 }
 
 public static CurrencyPair select(String CurrencyPairIn,Connection con ) {
	 
	 int j = 0;
        PreparedStatement stmt = null;
        String sql = null;
   //     Vector CurrencyPairs = new Vector();
        CurrencyPair currencyPair = null; new CurrencyPair();
	 try {
		 con.setAutoCommit(false);
		  sql = SELECTONE + CurrencyPairIn;
		 stmt = dsSQL.newPreparedStatement(con,sql );
         
         ResultSet rs = stmt.executeQuery();
         
         while(rs.next()) {
        	 currencyPair = new CurrencyPair();
        	
        	 currencyPair.setPrimary_currency(rs.getString(1));
        	 currencyPair.setQuoting_currency(rs.getString(2));
        
        	 currencyPair.setQuote_factor(rs.getInt(3));
        	 currencyPair.setBp_factor(rs.getInt(4));
        	 currencyPair.setRounding(rs.getInt(5));
        	 currencyPair.setPair_pos_ref_b(rs.getInt(6));
        	 currencyPair.setSpot_days(rs.getInt(7));
       
        
      
    
         
         }
         return currencyPair;
	 } catch (Exception e) {
		 commonUTIL.displayError("CurrencyPairSQL","select " +sql,e);
		   return currencyPair;
           
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("CurrencyPairSQL","select ",e);
		}
        }
 }

 protected static Collection select(Connection con) { 
	 int j = 0;
     PreparedStatement stmt = null;
     Vector currencyPair = new Vector();
     
	 try {
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
      
      ResultSet rs = stmt.executeQuery();
      
      while(rs.next()) {
   
     CurrencyPair CurrencyPair = new CurrencyPair();
     CurrencyPair.setPrimary_currency(rs.getString(1));
     CurrencyPair.setQuoting_currency(rs.getString(2));
     
     CurrencyPair.setQuote_factor(rs.getInt(3));
     CurrencyPair.setBp_factor(rs.getInt(4));
     CurrencyPair.setRounding(rs.getInt(5));
     CurrencyPair.setPair_pos_ref_b(rs.getInt(6));
     CurrencyPair.setSpot_days(rs.getInt(7));
     currencyPair.add(CurrencyPair);
      
      }
	 } catch (Exception e) {
		 commonUTIL.displayError("CurrencyPairSQL",SELECTALL,e);
		 return currencyPair;
        
     }
     finally {
        try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("CurrencyPairSQL",SELECTALL,e);
		}
     }
     return currencyPair;
 }
 public static Collection selectCurrencyPair(String CurrencyPair,Connection con ) {
	 int j = 0;
     PreparedStatement stmt = null;
     Vector CurrencyPairs = new Vector();
     String sql = SELECTONE + CurrencyPair;
     
	 try {
		 con.setAutoCommit(false);
		
		 stmt = dsSQL.newPreparedStatement(con, sql );
      
      ResultSet rs = stmt.executeQuery();
      
      while(rs.next()) {
    	  CurrencyPair currencyPair = new CurrencyPair();
    	  currencyPair.setPrimary_currency(rs.getString(1));
    	     currencyPair.setQuoting_currency(rs.getString(2));
    	     
    	     currencyPair.setQuote_factor(rs.getInt(3));
    	     currencyPair.setBp_factor(rs.getInt(4));
    	     currencyPair.setRounding(rs.getInt(5));
    	     currencyPair.setPair_pos_ref_b(rs.getInt(6));
    	     currencyPair.setSpot_days(rs.getInt(7));
     CurrencyPairs.add(currencyPair);
      
      }
      commonUTIL.display("CurrencyPairSQL","selectCurrencyPair " + sql);
	 } catch (Exception e) {
		 commonUTIL.displayError("CurrencyPairSQL","selectCurrencyPair " + sql,e);
		 return CurrencyPairs;
        
     }
     finally {
        try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("CurrencyPairSQL","selectMax",e);
		}
     }
     return CurrencyPairs;
 }

}
