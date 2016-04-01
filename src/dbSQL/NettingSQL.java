package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;

import beans.NettingConfig;

public class NettingSQL {
	
	
	final static private String DELETE_FROM_NETTINGConfig =
			"DELETE FROM NETTINGConfig  where id =? ";
		final static private String INSERT_FROM_NETTINGConfig  =
			"INSERT into NettingConfig(id,leID,productType,currency,effectivedate) values(?,?,?,?,?)";
		final static private String UPDATE_FROM_NETTINGConfig  =
			"UPDATE NettingConfig set le_id=?,NettingConfig_name=? where NettingConfigno = ? ";
		final static private String SELECT_MAX =
			"SELECT MAX(id) DESC_ID FROM NETTINGConfig  ";
		final static private String SELECTALL =
			"SELECT id,leID,productType,currency,effectivedate FROM NETTINGConfig  order by id";
		final static private String SELECTONLEID =
			"SELECT id,leID,productType,currency,effectivedate FROM NETTINGConfig  where leID =  ?";
		private static final String SELECTWHERE = "Select id,leID,productType,currency,effectivedate  FROM NETTINGConfig  where ";
		 static private String SELECTONE =
			"SELECT id,leID,productType,currency,effectivedate FROM NETTINGConfig  where   " ;
			 
		 
		 private static String getUpdateSQL(NettingConfig netConfig) {
		      String updateSQL = "UPDATE NETTINGConfig  set " +
		      		  		" leid = " + netConfig.getLeid() + 	
		      		  		", productType = '" + netConfig.getProductType() + 	
		      		  		"', currency = '" + netConfig.getCurrency() + 	
		      		  		"', effectivedate = '" + netConfig.getEffectiveDate() + 	
		      		"'  where id= " + netConfig.getId();
		      return updateSQL;
		     }
		 
		 public static int save(NettingConfig insertNettingConfig, Connection con) {
			 try {
	             return insert(insertNettingConfig, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("NettingConfigSQL","save",e);
	        	 return -1;
	         }
		 }
		 public static boolean update(NettingConfig updateNettingConfig, Connection con) {
			 try {
	             return edit(updateNettingConfig, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("NettingConfigSQL","update",e);
	        	 return false;
	         }
		 }
		 
		 public static boolean delete(NettingConfig deleteNettingConfig, Connection con) {
			 try {
	             return remove(deleteNettingConfig, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("NettingConfigSQL","update",e);
	        	 return false;
	         }
		 }
		 public static Collection selectNettingConfig(int counterPartyID, Connection con) {
			 try {
	             return  selectOnCounterParty(counterPartyID, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("NettingConfigSQL","select",e);
	        	 return null;
	         }
		 }
		 public static Collection selectWhere(String where,Connection con) {
			 try {
	             return selectWhereClause( where,con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("NettingConfigSQL","selectALL",e);
	        	 return null;
	         }
		 }
		 
		 public static int selectMaxID(Connection con) {
			 try {
	             return selectMax(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("NettingConfigSQL","selectMaxID",e);
	        	 return 0;
	         }
		 }
		 
		 protected static  boolean edit(NettingConfig updateNettingConfig, Connection con ) {
			 
		        PreparedStatement stmt = null;
		        String sql = getUpdateSQL(updateNettingConfig);
			 try {
				 con.setAutoCommit(false);
				 int j = 1;
				 stmt = dsSQL.newPreparedStatement(con, sql);
		            stmt.executeUpdate(sql);
				 con.commit();
				 commonUTIL.display("NettingConfigSQL ::  edit  ", sql);
			 } catch (Exception e) {
				 commonUTIL.displayError("NettingConfigSQL","edit  " + sql ,e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("NettingConfigSQL"," edit ",e);
				}
		        }
		        return true;
		 }
		
		protected static boolean remove(NettingConfig deleteNettingConfig, Connection con ) {
		
		        PreparedStatement stmt = null;
			 try {
				 int j = 1;
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_NETTINGConfig);
		            stmt.setInt(j++, deleteNettingConfig.getId());
		           
		            stmt.executeUpdate();
				 con.commit();
			 } catch (Exception e) {
				 commonUTIL.displayError("NettingConfigSQL","remove",e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("NettingConfigSQL","remove",e);
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
				 commonUTIL.displayError("NettingConfigSQL",SELECT_MAX,e);
				 return j;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("NettingConfigSQL",SELECT_MAX,e);
				}
		        }
		        return j;
		 }
	
	protected static int insert(NettingConfig inserNettingConfig, Connection con ) {
		
        PreparedStatement stmt = null;
        int id = 0;
	 try {
		 con.setAutoCommit(false);
		  id = selectMax(con);
		 id = id +1;
		 int j = 1;
		
		 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_NETTINGConfig);
		 stmt.setInt(1,id);
            stmt.setInt(2, inserNettingConfig.getLeid());
            stmt.setString(3, inserNettingConfig.getProductType());
            stmt.setString(4, inserNettingConfig.getCurrency());
            stmt.setString(5, inserNettingConfig.getEffectiveDate());
        
            
            stmt.executeUpdate();
            con.commit();
            commonUTIL.display("NettingConfigSQL","insert  "+INSERT_FROM_NETTINGConfig);
            return id;
	 } catch (Exception e) {
		 commonUTIL.displayError("NettingConfigSQL",INSERT_FROM_NETTINGConfig,e);
		 return -1;
           
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("NettingConfigSQL",INSERT_FROM_NETTINGConfig,e);
		}
        }
       
 }
	
	
 
 public static Collection selectOnCounterParty(int counterPartyID,Connection con ) {
	 Vector nettingConfigs = new Vector();
	 int j = 0;
        PreparedStatement stmt = null;
       
        String sql = SELECTONLEID; 
	 try {
		 con.setAutoCommit(false);
		 sql = sql + counterPartyID;
		 stmt = dsSQL.newPreparedStatement(con,  sql);
         
         ResultSet rs = stmt.executeQuery();
         
         while(rs.next()) {
        	 NettingConfig netConfig = new NettingConfig();
        	 netConfig.setId(rs.getInt(1));
        	 netConfig.setLeid(rs.getInt(2));
        	 netConfig.setProductType(rs.getString(3));
        	 netConfig.setCurrency(rs.getString(4));
        	 netConfig.setEffectiveDate(rs.getString(5));
        	 nettingConfigs.add(netConfig);
         }
         commonUTIL.display("NettingConfigSQL","selectOnCounterParty  "+sql);
         return nettingConfigs;
         
         
	 } catch (Exception e) {
		 commonUTIL.displayError("NettingConfigSQL","selectOnCounterParty",e);
		 return 		 null;
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("NettingConfigSQL","selectMax",e);
		}
         
        }
 }
 protected static Collection selectWhereClause(String where,Connection con ) {
	 Vector nettingConfigs = new Vector();
	 int j = 0;
        PreparedStatement stmt = null;
       
        String sql = SELECTWHERE; 
	 try {
		 con.setAutoCommit(false);
		 sql = sql + where;
		 stmt = dsSQL.newPreparedStatement(con,  sql);
         
         ResultSet rs = stmt.executeQuery();
         
         while(rs.next()) {
        	 NettingConfig netConfig = new NettingConfig();
        	 netConfig.setId(rs.getInt(1));
        	 netConfig.setLeid(rs.getInt(2));
        	 netConfig.setProductType(rs.getString(3));
        	 netConfig.setCurrency(rs.getString(4));
        	 netConfig.setEffectiveDate(rs.getString(5));
        	 nettingConfigs.add(netConfig);
         }
         commonUTIL.display("NettingConfigSQL","selectWhereClause  "+sql);
         return nettingConfigs;
         
         
	 } catch (Exception e) {
		 commonUTIL.displayError("NettingConfigSQL","selectWhereClause",e);
		 return 		 null;
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("NettingConfigSQL","selectMax",e);
		}
         
        }
 }
 public static Collection selectALL(Connection con ) {
	 Vector nettingConfigs = new Vector();
	 int j = 0;
        PreparedStatement stmt = null;
       
        String sql = SELECTALL; 
	 try {
		 con.setAutoCommit(false);
		// sql = sql + where;
		 stmt = dsSQL.newPreparedStatement(con,  sql);
         
         ResultSet rs = stmt.executeQuery();
         
         while(rs.next()) {
        	 NettingConfig netConfig = new NettingConfig();
        	 netConfig.setId(rs.getInt(1));
        	 netConfig.setLeid(rs.getInt(2));
        	 netConfig.setProductType(rs.getString(3));
        	 netConfig.setCurrency(rs.getString(4));
        	 netConfig.setEffectiveDate(rs.getString(5));
        	 nettingConfigs.add(netConfig);
         }
         commonUTIL.display("NettingConfigSQL","selectALL "+sql);
         return nettingConfigs;
         
         
	 } catch (Exception e) {
		 commonUTIL.displayError("NettingConfigSQL","selectALL ",e);
		 return 		 null;
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("NettingConfigSQL","selectALL",e);
		}
         
        }
 }
 
}

