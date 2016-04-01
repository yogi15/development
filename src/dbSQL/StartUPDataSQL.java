package dbSQL;

import beans.StartUPData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;
import util.commonUTIL;
public class StartUPDataSQL {
	
	
	final static private String tableName = "startupData";
	final static private String DELETE =
		"DELETE FROM " + tableName + "   where name =? ";
	final static private String INSERT =
		"INSERT into " + tableName + "(name,value,comments) values(?,?,?)";
	final static private String UPDATE =
		"UPDATE " + tableName + " set name=?,value=?,comments=?   where name = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(id) DESC_ID FROM " + tableName + " ";
	final static private String SELECTALL =
		"SELECT name,value,comments FROM " + tableName + " ";
	final static private String SELECT =
		"SELECT name,value,comments FROM " + tableName + " where name =  ?";
	 static private String SELECTONE =
		"SELECT name,value,comments FROM " + tableName + " where name =  ";
	 static private String SELECTONLYVALUE =
				"SELECT  value  FROM " + tableName + " where name =  ";
	 
	 public static boolean save(StartUPData insertStartUPData, Connection con) {
		 try {
             return insert(insertStartUPData, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("StartUPDataSQL","save",e);
        	 return false;
         }
	 }
	 public static boolean update(StartUPData updateStartUPData, Connection con) {
		 try {
             return edit(updateStartUPData, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("StartUPDataSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(StartUPData deleteStartUPData, Connection con) {
		 try {
             return remove(deleteStartUPData, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("StartUPDataSQL","update",e);
        	 return false;
         }
	 }
	 
	 protected static boolean remove(StartUPData deleteStartUPData, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			String  sql = " DELETE FROM startupData   where name ='" + deleteStartUPData.getName() + "' and value = '"+ deleteStartUPData.getValue() +"'";
			    stmt = con.prepareStatement(sql);
			            stmt.execute(sql);
			  
			            
			             
			             con.commit();
			             commonUTIL.display("StartUPDataSQL ::  remove", sql);
			    
		 } catch (Exception e) {
			 commonUTIL.displayError("StartUPDataSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("StartUPDataSQL","remove",e);
			}
	        }
	        return true;
	 }
	 public static Collection selectStartUPData(String  name, Connection con) {
		 try {
             return  select(name, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("StartUPDataSQL","select",e);
        	 return null;
         }
	 }
	 public static Collection selectALL(Connection con) {
		 try {
             return select(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("StartUPDataSQL","selectALL",e);
        	 return null;
         }
	 }
	 
	 protected static  boolean edit(StartUPData updateStartUPData, Connection con ) {
		 
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, UPDATE);
	            
			
	           
	            
	            stmt.setString(1, updateStartUPData.getName());
	            stmt.setString(2, updateStartUPData.getValue());
	            stmt.setString(3, updateStartUPData.getCommts());
	            
	            
	            stmt.executeUpdate();
	            con.commit();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("StartUPDataSQL",UPDATE,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("StartUPDataSQL",UPDATE,e);
			}
	        }
	        return true;
	 }
	
	 protected static boolean insert(StartUPData inserStartUPData, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 con.setAutoCommit(false);
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT);
			
			
	            stmt.setString(1, inserStartUPData.getName());
	            stmt.setString(2, inserStartUPData.getValue());
	            stmt.setString(3, inserStartUPData.getCommts());
	           
	            commonUTIL.display("insert",INSERT);
	            stmt.executeUpdate();
	            con.commit();
		 } catch (Exception e) {
			 commonUTIL.displayError("StartUPDataSQL",INSERT,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL","insert",e);
			}
	        }
	        return true;
	 }
	 
	 protected static Collection select(String StartUPDataID,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	       Vector values = new Vector();
	        
		 try {
			String sql = SELECTONE + "'" + StartUPDataID.trim() + "'";
			con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con,sql) ;
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        
	        	 StartUPData startUPData = new StartUPData();
	        	 startUPData.setName(rs.getString(1));
	        	 startUPData.setValue(rs.getString(2));
	        	 startUPData.setCommts(rs.getString(3));
	        
	        values.add(startUPData);
	        
	      
	       
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("StartUPDataSQL",SELECTONE + StartUPDataID,e);
			 return values;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("StartUPDataSQL",SELECTONE + StartUPDataID,e);
			}
	        }
	        return values;
	 }

protected static Vector<String> selectOnlyValues(String StartUPDataID,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	       Vector<String> values = new Vector<String>();
	        
		 try {
			String sql = SELECTONLYVALUE + "'" + StartUPDataID.trim() + "'";
			con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con,sql) ;
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        
	        	 StartUPData startUPData = new StartUPData();
	        	 startUPData.setName(rs.getString(1));
	        	 startUPData.setValue(rs.getString(2));
	        	 startUPData.setCommts(rs.getString(3));
	        
	        values.add(rs.getString(1));
	        
	      
	       
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("StartUPDataSQL",SELECTONE + StartUPDataID,e);
			 return values;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("StartUPDataSQL",SELECTONE + StartUPDataID,e);
			}
	        }
	        return values;
	 }

	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector StartUPDatas = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  StartUPData StartUPData = new StartUPData();
	    	  StartUPData.setName(rs.getString(1));
		        StartUPData.setValue(rs.getString(2));
		        StartUPData.setCommts(rs.getString(3));
		        StartUPDatas.add(StartUPData);
	      
	      }  commonUTIL.display("StartUPDataSQL",SELECTALL);
	      
		 } catch (Exception e) {
			 commonUTIL.displayError("StartUPDataSQL",SELECTALL,e);
			 return StartUPDatas;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("StartUPDataSQL",SELECTALL,e);
			}
	     }
	     return StartUPDatas;
	 }
	public static Collection getStartUPDataName(Connection con) {
		// TODO Auto-generated method stub
		int j = 0;
	     PreparedStatement stmt = null;
	     Vector StartUPDatas = new Vector();
	     String sql = "select value from startupData where name = "+ "'InitialData' order by value";
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql );
			 commonUTIL.display("getStartUPDataName",sql);
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  StartUPData StartUPData = new StartUPData();
	    	  StartUPData.setName(rs.getString(1));
		       
		        StartUPDatas.add(StartUPData);
	      
	      }
	      
	      
		 } catch (Exception e) {
			 commonUTIL.displayError("getStartUPDataName",sql,e);
			 return StartUPDatas;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("getStartUPDataName",sql,e);
			}
	     }
	     return StartUPDatas;
	 
	}
	
	public static  Vector<String> getStartUPData(String name,Connection con) {
		// TODO Auto-generated method stub
		 
	     PreparedStatement stmt = null;
	     Vector<String> startupDataValues = new Vector<String>() ;
	     String sql = "select value from startupData where name = "+ "'" + name.trim() +"'";
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  
		       
	    	  startupDataValues.add(rs.getString(1));
	      
	      }
	      
	      commonUTIL.display("getStartUPData",sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("getStartUPData",sql,e);
			 return startupDataValues;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("getStartUPData",sql,e);
			}
	     }
	     return startupDataValues;
	 
	}
	 
}
