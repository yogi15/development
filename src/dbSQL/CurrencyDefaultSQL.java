package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.CurrencyDefault;
import util.commonUTIL;

public class CurrencyDefaultSQL {
	
	final static private String DELETE_FROM_CurrrencyD =
		"DELETE FROM CURRENCYDEFAULT where currency_code =? ";
	final static private String INSERT_FROM_currencyDefault =
		"INSERT into CURRENCYDEFAULT(currency_code, rounding, rounding_method, iso_code, country, default_holidays, RATE_INDEX_CODE, "
		+ "DEFAULT_DAY_COUNT, GROUP_LIST, spot_days, DEFAULT_TENOR, DESCRIPTION, TIME_ZONE, VERSION_NUM, EXTERNAL_REFERENCE, "
		+ "RATE_DECIMALS, WARNING_THRESHOLD,SETTLEMENT_CUTOFF_TIME, SETTLEMENT_CUTOFF_TIMEZONE, is_precious_metal_b, "
		+ " non_deliverable_b,  DAYCOUNT, CURRENCYDECIMAL) "
		+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	final static private String SELECT_MAX =
		"SELECT MAX(currencyDefaultno) DESC_ID FROM CURRENCYDEFAULT ";
	final static private String SELECTALL =
		" SELECT currency_code, rounding, rounding_method, iso_code, country, default_holidays, RATE_INDEX_CODE, "
		+ "DEFAULT_DAY_COUNT, GROUP_LIST, spot_days, DEFAULT_TENOR, DESCRIPTION, TIME_ZONE, VERSION_NUM, EXTERNAL_REFERENCE, "
		+ "RATE_DECIMALS, WARNING_THRESHOLD,SETTLEMENT_CUTOFF_TIME, SETTLEMENT_CUTOFF_TIMEZONE, is_precious_metal_b, "
		+ " non_deliverable_b,  DAYCOUNT, CURRENCYDECIMAL"
		+ "FROM CURRENCYDEFAULT ";
	/*final static private String SELECT =
		" SELECT currency_code, rounding, rounding_method, iso_code, country, default_holidays, RATE_INDEX_CODE, "
		+ "DEFAULT_DAY_COUNT, GROUP_LIST, spot_days, DEFAULT_TENOR, DESCRIPTION, TIME_ZONE, VERSION_NUM, EXTERNAL_REFERENCE, "
		+ "RATE_DECIMALS, WARNING_THRESHOLD,SETTLEMENT_CUTOFF_TIME, SETTLEMENT_CUTOFF_TIMEZONE, is_precious_metal_b, "
		+ " non_deliverable_b,  DAYCOUNT, CURRENCYDECIMAL"
		+ "FROM CURRENCYDEFAULT where currency_code =  ?";*/
	 static private String SELECTONE =
		 " SELECT currency_code, rounding, rounding_method, iso_code, country, default_holidays, RATE_INDEX_CODE, "
		+ "DEFAULT_DAY_COUNT, GROUP_LIST, spot_days, DEFAULT_TENOR, DESCRIPTION, TIME_ZONE, VERSION_NUM, EXTERNAL_REFERENCE, "
		+ "RATE_DECIMALS, WARNING_THRESHOLD,SETTLEMENT_CUTOFF_TIME, SETTLEMENT_CUTOFF_TIMEZONE, is_precious_metal_b, "
		+ " non_deliverable_b,  DAYCOUNT, CURRENCYDECIMAL"
		+ "FROM CURRENCYDEFAULT where currency_code =  ?";
	  
	 private static String getUpdateCurrencyDefaultSQL(CurrencyDefault updateCurrencyDefault) {
		 
	      String updateSQL = new StringBuffer("UPDATE CURRENCYDEFAULT  set ")
								.append(" currency_code = ").append(updateCurrencyDefault.getCurrency_code())
								.append(", rounding = ").append(updateCurrencyDefault.getRounding())
								.append(", rounding_method = ").append(updateCurrencyDefault.getRounding_method()) 
								.append(", iso_code =").append(updateCurrencyDefault.getIso_code())
								.append(", country = ").append(updateCurrencyDefault.getCountry())
								.append(", default_holidays = ").append(updateCurrencyDefault.getDefault_holiday()) 	
								.append(", RATE_INDEX_CODE =").append(updateCurrencyDefault.getRate_index_code())
								.append(" DEFAULT_DAY_COUNT = ").append(updateCurrencyDefault.getDefault_day_count())
								.append(", GROUP_LIST = ").append(updateCurrencyDefault.getGroupList())
								.append(", spot_days = ").append(updateCurrencyDefault.getSpot_days()) 	
								.append(", DEFAULT_TENOR =").append(updateCurrencyDefault.getDefaultTenor())
								.append(", DESCRIPTION = ").append(updateCurrencyDefault.getDescription())
								.append(", TIME_ZONE = ").append(updateCurrencyDefault.getTimeZone()) 
								.append(", VERSION_NUM = ").append(updateCurrencyDefault.getVersionNumber()) 
								.append(", EXTERNAL_REFERENCE =").append(updateCurrencyDefault.getExternalReferences())
								.append(", RATE_DECIMALS = ").append(updateCurrencyDefault.getRateDecimals())
								.append(", WARNING_THRESHOLD = ").append(updateCurrencyDefault.getWarningThreshold()) 	
								.append(", SETTLEMENT_CUTOFF_TIME =").append(updateCurrencyDefault.getSettlementCutOffTime())
								.append(", SETTLEMENT_CUTOFF_TIMEZONE = ").append(updateCurrencyDefault.getSettlementCutOffTimeZone())
								.append(", is_precious_metal_b = ").append(updateCurrencyDefault.getIs_precious_metal_b()) 	
								.append(", non_deliverable_b =").append(updateCurrencyDefault.getNon_deliverable_b())
								.append(", DAYCOUNT = ").append(updateCurrencyDefault.getDayCount())
								.append(", CURRENCYDECIMAL = ").append(updateCurrencyDefault.getCurrencyDecimal()) 	
								.toString();
	      
	      return updateSQL;
	   }
	 
	 public static boolean save(CurrencyDefault insertcurrencyDefault, Connection con) {
		 try {
             return insert(insertcurrencyDefault, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("CurrencyDefaultSQL: insert: ","save",e);
        	 return false;
         }
	 }
	 public static boolean update(CurrencyDefault updatecurrencyDefault, Connection con) {
		 try {
             return edit(updatecurrencyDefault, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("CurrencyDefaultSQL: insert: ","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(CurrencyDefault deletecurrencyDefault, Connection con) {
		 try {
             return remove(deletecurrencyDefault, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("CurrencyDefaultSQL: insert: ","update",e);
        	 return false;
         }
	 }
	 public static CurrencyDefault selectcurrencyDefault(String  currency_code, Connection con) {
		 try {
             return  select(currency_code, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("CurrencyDefaultSQL: insert: ","select",e);
        	 return null;
         }
	 }
	 public static Collection<CurrencyDefault> selectALL(Connection con) {
		 try {
             return select(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("CurrencyDefaultSQL: insert: ","selectALL",e);
        	 return null;
         }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
             return selectMax(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("CurrencyDefaultSQL: insert: ","selectMaxID",e);
        	 return 0;
         }
	 }
	 
	 protected static  boolean edit(CurrencyDefault updatecurrencyDefault, Connection con ) {
		 
		 PreparedStatement stmt = null;
		 String sql = "";
		 
		 try {
			 sql = getUpdateCurrencyDefaultSQL(updatecurrencyDefault);
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql);                
	            
	         stmt.executeUpdate();
			 con.commit();
			 
			 commonUTIL.display("CurrencyDefaultSQL: edit:", sql);
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("CurrencyDefaultSQL: insert: ",sql,e);
			 return false;
	           
	     } finally {
	    	 try {
				stmt.close();
	         } catch (SQLException e) {					
	        	 commonUTIL.displayError("CurrencyDefaultSQL: insert: ", sql,e);
	         }
	     }
        return true;
	 }
	
	protected static boolean remove(CurrencyDefault deletecurrencyDefault, Connection con ) {
		
		PreparedStatement stmt = null;
		try {
			 
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_CurrrencyD);
	        stmt.setString(1, deletecurrencyDefault.getCurrency_code());
	           
	        stmt.executeUpdate();
			con.commit();
			
			commonUTIL.display("CurrencyDefaultSQL: remove:", DELETE_FROM_CurrrencyD);
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("CurrencyDefaultSQL: remove: ", DELETE_FROM_CurrrencyD,e);
			 return false;
	    } finally {
	    	try {
				stmt.close();
			} catch (SQLException e) {					
				commonUTIL.displayError("CurrencyDefaultSQL: insert: ", DELETE_FROM_CurrrencyD,e);
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
			 commonUTIL.displayError("CurrencyDefaultSQL: insert: ",SELECT_MAX,e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("CurrencyDefaultSQL: insert: ",SELECT_MAX,e);
			}
	        }
	        return j;
	 }
	 
	 protected static boolean insert(CurrencyDefault insercurrencyDefault, Connection con ) {
		 PreparedStatement stmt = null;
		 try {
			 con.setAutoCommit(false);
			 int j = 1;
	
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_currencyDefault);
			 
			 stmt.setString(j++, insercurrencyDefault.getCurrency_code());
			 stmt.setDouble(j++, insercurrencyDefault.getRounding());
	         stmt.setString(j++, insercurrencyDefault.getRounding_method());
	         stmt.setString(j++,insercurrencyDefault.getIso_code() );
	         stmt.setString(j++, insercurrencyDefault.getCountry());
			 stmt.setString(j++, insercurrencyDefault.getDefault_holiday());
	         stmt.setString(j++, insercurrencyDefault.getRate_index_code());
	         stmt.setString(j++,insercurrencyDefault.getDefault_day_count());
	         stmt.setString(j++, insercurrencyDefault.getGroupList());				 
	         stmt.setInt(j++, insercurrencyDefault.getSpot_days());
	         stmt.setInt(j++,insercurrencyDefault.getDefaultTenor());
	         stmt.setString(j++, insercurrencyDefault.getDescription());
			 stmt.setString(j++, insercurrencyDefault.getTimeZone());
	         stmt.setInt(j++, insercurrencyDefault.getVersionNumber());
	         stmt.setString(j++,insercurrencyDefault.getExternalReferences());
	         stmt.setInt(j++, insercurrencyDefault.getRateDecimals());
	         stmt.setDouble(j++, insercurrencyDefault.getWarningThreshold());
	         stmt.setString(j++, insercurrencyDefault.getSettlementCutOffTime());
	         stmt.setString(j++,insercurrencyDefault.getSettlementCutOffTimeZone());
	         stmt.setInt(j++, insercurrencyDefault.getIs_precious_metal_b());
			 stmt.setInt(j++, insercurrencyDefault.getNon_deliverable_b());
	         stmt.setString(j++, insercurrencyDefault.getDayCount());
	         stmt.setInt(j++, insercurrencyDefault.getCurrencyDecimal());
	            
	         stmt.executeUpdate();
	         con.commit();
	         commonUTIL.display("CurrencyDefaultSQL: insert: ", INSERT_FROM_currencyDefault);
	         
		 } catch (Exception e) {
			 commonUTIL.displayError("CurrencyDefaultSQL: insert: ",INSERT_FROM_currencyDefault,e);
			 return false;
	     } finally {
	    	 try {
				stmt.close();
	    	 } catch (SQLException e) {
	    		 commonUTIL.displayError("CurrencyDefaultSQL: insert: ",INSERT_FROM_currencyDefault,e);
			}
	    }
	    return true;
	 }		 
	 protected static CurrencyDefault select(String currency_code,Connection con ) {

	     PreparedStatement stmt = null;
	     CurrencyDefault CurrencyDefault = new CurrencyDefault();
	     
	     String sql =  SELECTONE + "'"+ currency_code +"'";
		 try {			 
			 stmt = dsSQL.newPreparedStatement(con, sql);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {		        
		        CurrencyDefault.setCurrency_code(rs.getString(1));
		        CurrencyDefault.setRounding(rs.getInt(2));
		        CurrencyDefault.setIso_code(rs.getString(3));
		        CurrencyDefault.setCountry(rs.getString(4));
		        CurrencyDefault.setDefault_holiday(rs.getString(5));
		        CurrencyDefault.setSpot_days(rs.getInt(6));
		        CurrencyDefault.setIs_precious_metal_b(rs.getInt(7));
		        CurrencyDefault.setNon_deliverable_b(rs.getInt(8));
	         }
	         
	         return CurrencyDefault;

		 } catch (Exception e) {
			 commonUTIL.displayError("CurrencyDefaultSQL: select: ", sql,e);
			 return CurrencyDefault;		           
	     } finally {
	    	 try {
	    		 stmt.close();
	    	 } catch (SQLException e) {
	    		 commonUTIL.displayError("CurrencyDefaultSQL: select: ", sql,e);
			}
	    }
	 }

	 protected static Collection<CurrencyDefault> select(Connection con) { 
	     PreparedStatement stmt = null;
	     Vector<CurrencyDefault> currencyDefaults = new Vector<CurrencyDefault>();
	     
		 try {

		  stmt = dsSQL.newPreparedStatement(con, SELECTALL);		      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    
	    	CurrencyDefault CurrencyDefault = new CurrencyDefault();
	    	CurrencyDefault.setCurrency_code(rs.getString(1));
	        CurrencyDefault.setRounding(rs.getInt(2));
	        CurrencyDefault.setRounding_method(rs.getString(3));
	        CurrencyDefault.setIso_code(rs.getString(4));
	        CurrencyDefault.setCountry(rs.getString(5));
	        CurrencyDefault.setDefault_holiday(rs.getString(6));
	        CurrencyDefault.setRate_index_code(rs.getString(7));
	        CurrencyDefault.setDefault_day_count(rs.getString(8));
	        CurrencyDefault.setGroupList(rs.getString(9));
	        CurrencyDefault.setSpot_days(rs.getInt(10));
	        CurrencyDefault.setDefaultTenor(rs.getInt(11));
	        CurrencyDefault.setDescription(rs.getString(12));
	        CurrencyDefault.setTimeZone(rs.getString(13));
	        CurrencyDefault.setVersionNumber(rs.getInt(14));		       
	        CurrencyDefault.setExternalReferences(rs.getString(15));
	        CurrencyDefault.setRateDecimals(rs.getInt(16));
	        CurrencyDefault.setWarningThreshold(rs.getDouble(17));
	        CurrencyDefault.setSettlementCutOffTime(rs.getString(18));
	        CurrencyDefault.setSettlementCutOffTimeZone(rs.getString(19));
	        CurrencyDefault.setIs_precious_metal_b(rs.getInt(20));
	        CurrencyDefault.setNon_deliverable_b(rs.getInt(21));
	        CurrencyDefault.setDayCount(rs.getString(22));
	        CurrencyDefault.setCurrencyDecimal(rs.getInt(23));
	        currencyDefaults.add(CurrencyDefault);
	      
	      }
	      
	      commonUTIL.display("CurrencyDefaultSQL: select: ",SELECTALL);
	      
		 } catch (Exception e) {
			 commonUTIL.displayError("CurrencyDefaultSQL: select: ",SELECTALL,e);
			 return currencyDefaults;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {					
				 commonUTIL.displayError("CurrencyDefaultSQL: select: ",SELECTALL,e);
			}
	     }
	     return currencyDefaults;
	 }
	 
	 protected static Collection<CurrencyDefault> selectcurrencyDefault(int currencyDefaultId, Connection con ) {
		
	     PreparedStatement stmt = null;
	     String sql ="";
	     Vector<CurrencyDefault> currencyDefaults = new Vector<CurrencyDefault>();
	     
		 try {
			 sql = SELECTONE + "'" + currencyDefaultId +"'";
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
			 while(rs.next()) {
				 	CurrencyDefault CurrencyDefault = new CurrencyDefault();
			    	CurrencyDefault.setCurrency_code(rs.getString(1));
			        CurrencyDefault.setRounding(rs.getInt(2));
			        CurrencyDefault.setRounding_method(rs.getString(3));
			        CurrencyDefault.setIso_code(rs.getString(4));
			        CurrencyDefault.setCountry(rs.getString(5));
			        CurrencyDefault.setDefault_holiday(rs.getString(6));
			        CurrencyDefault.setRate_index_code(rs.getString(7));
			        CurrencyDefault.setDefault_day_count(rs.getString(8));
			        CurrencyDefault.setGroupList(rs.getString(9));
			        CurrencyDefault.setSpot_days(rs.getInt(10));
			        CurrencyDefault.setDefaultTenor(rs.getInt(11));
			        CurrencyDefault.setDescription(rs.getString(12));
			        CurrencyDefault.setTimeZone(rs.getString(13));
			        CurrencyDefault.setVersionNumber(rs.getInt(14));		       
			        CurrencyDefault.setExternalReferences(rs.getString(15));
			        CurrencyDefault.setRateDecimals(rs.getInt(16));
			        CurrencyDefault.setWarningThreshold(rs.getDouble(17));
			        CurrencyDefault.setSettlementCutOffTime(rs.getString(18));
			        CurrencyDefault.setSettlementCutOffTimeZone(rs.getString(19));
			        CurrencyDefault.setIs_precious_metal_b(rs.getInt(20));
			        CurrencyDefault.setNon_deliverable_b(rs.getInt(21));
			        CurrencyDefault.setDayCount(rs.getString(22));
			        CurrencyDefault.setCurrencyDecimal(rs.getInt(23));
			        currencyDefaults.add(CurrencyDefault);   		      
	         }
		 
			 commonUTIL.display("CurrencyDefaultSQL: select: ",sql);
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("CurrencyDefaultSQL: insert: ",sql,e);
			 return currencyDefaults;		        
	     } finally {
	        try {
				stmt.close();
			} catch (SQLException e) {					
				commonUTIL.displayError("CurrencyDefaultSQL: insert: ", sql ,e);				}
	     }
	     return currencyDefaults;
	 }

		 
}
