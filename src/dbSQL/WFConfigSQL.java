package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.WFConfig;
import util.commonUTIL;


public class WFConfigSQL {
	
	final static private String DELETE_FROM_WFConfig =
		"DELETE FROM WFConfig where id =? ";
	final static private String INSERT_FROM_WFConfig =
		"INSERT into WFConfig(id,productType,productSubType,Action,orgStatus,currentStatus,Auto,rule,le,users,type,task,groupname,diffUser) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	final static private String UPDATE_FROM_WFConfig =
		"UPDATE WFConfig set productType=?,productSubType=?,Action=?,orgStatus=?,currentStatus=?,Auto=?,rule=?,le=?,users=?,type=?,task=?,groupname=? where id = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(id) DESC_ID FROM WFConfig ";
	final static private String SELECTALL =
		"SELECT id,productType,productSubType,Action,orgStatus,currentStatus,Auto,rule,le,users,type,task,groupname,diffUser FROM WFConfig order by id";
	final static private String SELECT =
		"SELECT title FROM WFConfig where id =  ?";
	 static private String SELECTONE =
		"SELECT id,productType,productSubType,Action,orgStatus,currentStatus,Auto,rule,le,users,type,task,groupname,diffUser FROM WFConfig where id =  " ;
	 private static String whereClause =
		 "Select id,productType,productSubType,Action,orgStatus,currentStatus,Auto,rule,le,users,type,task,groupname,diffUser  FROM WFConfig where ";
	 
	private static String  getUpdateSQL(WFConfig updateWFConfig) {
		 
		 String sql = 	  " update wfconfig set  " + 
		  " id= "+  updateWFConfig.getId() + 
		   ", diffUser= "+  updateWFConfig.getDiffUser() + 
		  ",  productType = '"+ updateWFConfig.getProductType()+ 
		   "', productSubType = '"+ updateWFConfig.getProductSubType()+ 
		   "', Action  = '"+updateWFConfig.getAction() + 
		   "', orgStatus  = '"+  updateWFConfig.getOrgStatus() + 
		  "',  currentStatus   = '"+ updateWFConfig.getCurrentStatus() + 
		   "',  Auto = "+ updateWFConfig.getAuto()+ 
		   ", rule  = '"+ updateWFConfig.getRule()+ 
		   "', le = "+ updateWFConfig.getLe()+ 
		   " ,users = "+ updateWFConfig.getUsers()+ 
		   ", type   = '"+  updateWFConfig.getType() + "' " +
		 ", groupname   = '"+  updateWFConfig.getGroupName() + "',";
          if(updateWFConfig.isTask())
        	  sql = sql +  "  task = '1'";
          else
        	  sql = sql +  "  task = '0'"; 
          
        	  sql = sql  +  " where id = " + updateWFConfig.getId() ;
		 
		 return sql;
	 }
	 
	 
	 
	 public static int save(WFConfig insertWFConfig, Connection con) {
		 try {
             return insert(insertWFConfig, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("WFConfigSQL","save",e);
        	 return 0;
         }
	 }
	 public static boolean update(WFConfig updateWFConfig, Connection con) {
		 try {
             return edit(updateWFConfig, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("WFConfigSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(WFConfig deleteWFConfig, Connection con) {
		 try {
             return remove(deleteWFConfig, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("WFConfigSQL","update",e);
        	 return false;
         }
	 }
	 public static WFConfig selectWFConfig(int id, Connection con) {
		 try {
             return  select(id, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("WFConfigSQL","select",e);
        	 return null;
         }
	 }
	 public static Collection selectALL(Connection con) {
		 try {
             return select(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("WFConfigSQL","selectALL",e);
        	 return null;
         }
	 }
	 
	 public static Collection selectWhere(String whereClause,Connection con) {
		 try {
             return select(whereClause,con);
         }catch(Exception e) {
        	 commonUTIL.displayError("WFConfigSQL","selectALL",e);
        	 return null;
         }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
             return selectMax(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("WFConfigSQL","selectMaxID",e);
        	 return 0;
         }
	 }
	 
	 protected static  boolean edit(WFConfig updateWFConfig, Connection con ) {
		 
	        PreparedStatement stmt = null;
	        String sql = "";
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 sql = getUpdateSQL(updateWFConfig);
			 stmt = dsSQL.newPreparedStatement(con, sql);
	            
			
	           
	            
	        
	            
			 stmt.executeUpdate(sql);
	            con.commit();
	            commonUTIL.display("WFConfigSQL","edit " + sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("WFConfigSQL","edit  " + sql ,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("WFConfigSQL","edit",e);
			}
	        }
	        return true;
	 }
	
	protected static boolean remove(WFConfig deleteWFConfig, Connection con ) {
	
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_WFConfig);
	            stmt.setInt(j++, deleteWFConfig.getId());
	           
	            stmt.executeUpdate();
			    con.commit();
		 } catch (Exception e) {
			 commonUTIL.displayError("WFConfigSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("WFConfigSQL","remove",e);
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
			 commonUTIL.displayError("WFConfigSQL",SELECT_MAX,e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("WFConfigSQL",SELECT_MAX,e);
			}
	        }
	        return j;
	 }
	 
	 protected static int  insert(WFConfig inserWFConfig, Connection con ) {
			
	        PreparedStatement stmt = null;
	        int id = 0;
		 try {
			 id = selectMax(con);
			
			
			 con.setAutoCommit(false);
			
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_WFConfig);
			 stmt.setInt(1,id+1);
			 stmt.setString(2, inserWFConfig.getProductType());
	            stmt.setString(3, inserWFConfig.getProductSubType());
	            stmt.setString(4, inserWFConfig.getAction());
	            stmt.setString(5, inserWFConfig.getOrgStatus());
	            stmt.setString(6, inserWFConfig.getCurrentStatus());
	            stmt.setInt(7, inserWFConfig.getAuto());
	            stmt.setString(8, inserWFConfig.getRule());
	            stmt.setInt(9, inserWFConfig.getLe());
	            stmt.setInt(10, inserWFConfig.getUsers());
	            stmt.setString(11, inserWFConfig.getType());
	           
	            if(inserWFConfig.isTask())
		               stmt.setInt(12, 1);
		            else
		            	stmt.setInt(12, 0);
	            stmt.setString(13, inserWFConfig.getGroupName());
	            stmt.setInt(14, inserWFConfig.getDiffUser());
	            stmt.execute();
			 con.commit();
			 return id;
		 } catch (Exception e) {
			 commonUTIL.displayError("WFConfigSQL",INSERT_FROM_WFConfig,e);
			 return 0;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("WFConfigSQL",INSERT_FROM_WFConfig,e);
			}
	        }
	       
	 }
	 
	 protected static WFConfig select(int wfConfigIn,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        
	        WFConfig wfConfig = new WFConfig();
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + wfConfigIn);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        
	        	 wfConfig.setId(rs.getInt(1));
	        	 wfConfig.setProductType(rs.getString(2));
	        	 wfConfig.setProductSubType(rs.getString(3));
	        	 wfConfig.setAction(rs.getString(4));
	        	 wfConfig.setOrgStatus(rs.getString(5));
	        	 wfConfig.setCurrentStatus(rs.getString(6));
	        	 wfConfig.setAuto(rs.getInt(7));
	        	 wfConfig.setRule(rs.getString(8));
	        	 wfConfig.setLe(rs.getInt(9));
	        	 wfConfig.setUsers(rs.getInt(10));
	        	 wfConfig.setType(rs.getString(11));
	        	
	         int t = rs.getInt(12);
	         if(t == 1)
	        	 wfConfig.setTask(true);
	         wfConfig.setGroupName(rs.getString(13));
	         wfConfig.setDiffUser(rs.getInt(14));
	       return wfConfig;
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("wfConfigSQL","select",e);
			 return wfConfig;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("wfConfigSQL","selectMax",e);
			}
	        }
	        return wfConfig;
	 }

	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector<WFConfig> wfConfigs = new Vector<WFConfig>();
	     
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	     
	      while(rs.next()) {
	   
	    	  WFConfig wfConfig = new WFConfig();
	    	  	wfConfig.setId(rs.getInt(1));
	        	 wfConfig.setProductType(rs.getString(2));
	        	 wfConfig.setProductSubType(rs.getString(3));
	        	 wfConfig.setAction(rs.getString(4));
	        	 wfConfig.setOrgStatus(rs.getString(5));
	        	 wfConfig.setCurrentStatus(rs.getString(6));
	        	 wfConfig.setAuto(rs.getInt(7));
	        	 wfConfig.setRule(rs.getString(8));
	        	 wfConfig.setLe(rs.getInt(9));
	        	 wfConfig.setUsers(rs.getInt(10));
	        	 wfConfig.setType(rs.getString(11));
	        	 wfConfig.setGroupName(rs.getString(12));
	        	 int t = rs.getInt(12);
		         if(t == 1)
		        	 wfConfig.setTask(true);

		         wfConfig.setGroupName(rs.getString(13));
		         wfConfig.setDiffUser(rs.getInt(14));
	        wfConfigs.add(wfConfig);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("wfConfigSQL",SELECTALL,e);
			 return wfConfigs;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("WfConfigSQL",SELECTALL,e);
			}
	     }
	     return wfConfigs;
	 }
	 protected static Collection selectwfConfig(int wfconfid,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector<WFConfig> wfConfigs = new Vector<WFConfig>();
	     
		 try {
			 SELECTONE = SELECTONE + wfconfid;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE );
	      
	      ResultSet rs = stmt.executeQuery();
	    
	      while(rs.next()) {
	    	  WFConfig wfConfig = new WFConfig();
	    	  wfConfig.setId(rs.getInt(1));
	        	 wfConfig.setProductType(rs.getString(2));
	        	 wfConfig.setProductSubType(rs.getString(3));
	        	 wfConfig.setAction(rs.getString(4));
	        	 wfConfig.setOrgStatus(rs.getString(5));
	        	 wfConfig.setCurrentStatus(rs.getString(6));
	        	 wfConfig.setAuto(rs.getInt(7));
	        	 wfConfig.setRule(rs.getString(8));
	        	 wfConfig.setLe(rs.getInt(9));
	        	 wfConfig.setUsers(rs.getInt(10));
	        	 wfConfig.setType(rs.getString(11));
	        	 int t = rs.getInt(12);
		         if(t == 1)
		        	 wfConfig.setTask(true);

		         wfConfig.setGroupName(rs.getString(13));
		         wfConfig.setDiffUser(rs.getInt(14));
		        wfConfigs.add(wfConfig);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("wfConfigSQL","selectwfConfig",e);
			 return wfConfigs;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL","selectMax",e);
			}
	     }
	     return wfConfigs;
	 }
	 
	 protected static Collection select(String sqlClause,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector<WFConfig> wfConfigs = new Vector<WFConfig>();
	     String sql = "";
		 try {
			 con.setAutoCommit(false);
			 sql = whereClause + sqlClause + " order by id desc ";
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	     
	           
	      while(rs.next()) {
	    	  WFConfig wfConfig = new WFConfig();
	    	  wfConfig.setId(rs.getInt(1));
	        	 wfConfig.setProductType(rs.getString(2));
	        	 wfConfig.setProductSubType(rs.getString(3));
	        	 wfConfig.setAction(rs.getString(4));
	        	 wfConfig.setOrgStatus(rs.getString(5));
	        	 wfConfig.setCurrentStatus(rs.getString(6));
	        	 wfConfig.setAuto(rs.getInt(7));
	        	 wfConfig.setRule(rs.getString(8));
	        	 wfConfig.setLe(rs.getInt(9));
	        	 wfConfig.setUsers(rs.getInt(10));
	        	 wfConfig.setType(rs.getString(11));
	        	 int t = rs.getInt(12);
		         if(t == 1)
		        	 wfConfig.setTask(true);

		         wfConfig.setGroupName(rs.getString(13));
		         wfConfig.setDiffUser(rs.getInt(14));
		        wfConfigs.add(wfConfig);
	      
	      }
	      commonUTIL.display("wfConfigSQL","selectWhere "+sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("wfConfigSQL","select " + sql,e);
			 return wfConfigs;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				 commonUTIL.displayError("wfConfigSQL","select " + sql,e);
			}
	     }
	     return wfConfigs;
	 }

}

