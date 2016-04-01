package dbSQL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.sql.Connection;

import java.sql.ResultSet;

import java.util.Vector;
import util.commonUTIL;



import beans.Audit;
public class AuditSQL {
	
	final static private String DELETE_FROM_AUDIT =
		"DELETE FROM monitor   ";
	final static private String INSERT_FROM_AUDIT =
		"INSERT into monitor   (changedate,type,id,fieldname,userid,version,tvalue,tattribue) values(?,?,?,?,?,?,?,?)";
	final static private String UPDATE_FROM_AUDIT =
		"UPDATE monitor    set changedate=?,type=?,id=?,fieldname,userid=?,,tattribue where id = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(Auditno) DESC_ID FROM monitor    ";
	final static private String SELECTALL =
		"SELECT changedate,type,id,fieldname,userid,version,tvalue,tattribue  FROM monitor   order by id";
	final static private String SELECT =
		"SELECT changedate,type,id,fieldname,userid,version,tvalue,,tattribue  FROM monitor    where id =  ?";
	final static private String SELECTWhere =
		"SELECT changedate,type,id,fieldname,userid,version,tvalue,tattribue  FROM monitor    where   " ;
	final static private String SELECTlatesttradeauditVerion =
			"SELECT max(version) mversion  FROM monitor    where id = " ;
	 
	 
	 
	 
	 
	 public static boolean save(Audit insertAudit, Connection con) {
		 try {
             return insert(insertAudit, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AuditSQL","save",e);
        	 return false;
         }
	 }
	 public static boolean update(Audit updateAudit, Connection con) {
		 try {
             return edit(updateAudit, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AuditSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(Audit deleteAudit, Connection con) {
		 try {
             return remove(deleteAudit, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AuditSQL","update",e);
        	 return false;
         }
	 }
	 public static Collection selectAudit(int auditId, Connection con) {
		 try {
             return  select(auditId, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AuditSQL","select",e);
        	 return null;
         }
	 }
	 public static Collection selectwhere(String whereSql,Connection con) {
		 try {
             return selectWhere(whereSql, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AuditSQL","selectALL",e);
        	 return null;
         }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
             return selectMax(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AuditSQL","selectMaxID",e);
        	 return 0;
         }
	 }
	 
	 protected static  boolean edit(Audit updateAudit, Connection con ) {
		 
	        PreparedStatement stmt = null;
		 try {
			
		 } catch (Exception e) {
			 commonUTIL.displayError("AuditSQL","edit",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AuditSQL","edit",e);
			}
	        }
	        return true;
	 }
	
	protected static boolean remove(Audit deleteAudit, Connection con ) {
	
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_AUDIT);
	            
	           
	            stmt.executeUpdate();
			 con.commit();
		 } catch (Exception e) {
			 commonUTIL.displayError("AuditSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AuditSQL","remove",e);
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
			 commonUTIL.displayError("AuditSQL",SELECT_MAX,e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AuditSQL",SELECT_MAX,e);
			}
	        }
	        return j;
	 }
	 
	 protected static boolean insert(Audit inserAudit, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 con.setAutoCommit(false);
		//	 int id = selectMax(con);
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_AUDIT);
			
	            
	            
	            stmt.setString(1,inserAudit.getChangeDate());
	            stmt.setString(2,inserAudit.getType());
	            stmt.setInt(3,inserAudit.getTradeid());
	            stmt.setString(4,inserAudit.getFieldname());
	            stmt.setInt(5,inserAudit.getUserid());
	            stmt.setInt(6,inserAudit.getVersion());
	            stmt.setString(7,inserAudit.getValues());
	            stmt.setString(8,inserAudit.getTattribue());
	            stmt.executeUpdate();
	            con.commit();
	            commonUTIL.display("AuditSQL",INSERT_FROM_AUDIT);
		 } catch (Exception e) {
			 commonUTIL.displayError("AuditSQL",INSERT_FROM_AUDIT,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AuditSQL",INSERT_FROM_AUDIT,e);
			}
	        }
	        return true;
	 }
	 
	 

	 protected static Collection select(int tradeid,Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Audits = new Vector();
	     
		 try {
			 String sql = SELECTALL + " where id = " + tradeid;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	   
	     Audit audit = new Audit();
	     audit.setChangeDate(rs.getString(1));
	      audit.setType(rs.getString(2));
	      audit.setTradeid(rs.getInt(3));
	      audit.setFieldname(rs.getString(4));
	      audit.setUserid(rs.getInt(5));
	      audit.setVersion(rs.getInt(6));
	      audit.setValues(rs.getString(7));
	      audit.setTattribue(rs.getString(8));
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("AuditSQL",SELECTALL,e);
			 return Audits;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AuditSQL",SELECTALL,e);
			}
	     }
	     return Audits;
	 }
	 protected static Collection selectWhere(String sqlw,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Audits = new Vector();
	     String sql = "";
		 try {
			 con.setAutoCommit(false);
			 sql = SELECTWhere + sqlw;
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Audit audit = new Audit();
	    	  
		      audit.setChangeDate(rs.getString(1));
		      audit.setType(rs.getString(2));
		      audit.setTradeid(rs.getInt(3));
		      audit.setFieldname(rs.getString(4));
		      audit.setUserid(rs.getInt(5));
		      audit.setVersion(rs.getInt(6));
		      audit.setValues(rs.getString(7));
		      audit.setTattribue(rs.getString(8));
		      
	     Audits.add(audit);
	      
	      }
	      commonUTIL.display("AuditSQL","selectAudit "+ sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("AuditSQL","selectAudit",e);
			 return Audits;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AuditSQL","selectAudit",e);
			}
	     }
	     return Audits;
	 }
	 public static Collection selectLatestTradeVersion(int tradeID,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector latesttrade = null;
	     try{ 
	    	 int latestverions = getLatestTradeVersion(tradeID, con);
	     String sql = " id = " + tradeID + " and version = " + latestverions;
	     latesttrade =  (Vector) selectWhere(sql,con);
	      commonUTIL.display("AuditSQL","selectLatestTradeVersion "+ sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("AuditSQL","selectLatestTradeVersion",e);
			 return latesttrade;
	        
	     }
	     finally {
	        
	     }
	     return latesttrade;
	 }
	 public static Collection selectSpecifiCTradeVersion(int tradeID,int latestVersionID,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector latesttrade = null;
	     try{ 
	    	// int latestverions = getLatestTradeVersion(tradeID, con);
	     String sql = " id = " + tradeID + " and version = " + latestVersionID;
	     latesttrade =  (Vector) selectWhere(sql,con);
	      commonUTIL.display("AuditSQL","selectSpecifiCTradeVersion "+ sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("AuditSQL","selectSpecifiCTradeVersion",e);
			 return latesttrade;
	        
	     }
	     finally {
	        
	     }
	     return latesttrade;
	 }
	 protected static int getLatestTradeVersion(int tradeID,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     
	     String sql = "";
		 try {
			 con.setAutoCommit(false);
			 sql = SELECTlatesttradeauditVerion + tradeID;
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  
		      
	    	  j = rs.getInt("mversion");
	     
	      
	      }
	      commonUTIL.display("AuditSQL","getLatestTradeVersion "+ sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("AuditSQL","getLatestTradeVersion",e);
			 return j;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AuditSQL","selectAudit",e);
			}
	     }
	     return j;
	 }
	 

}



