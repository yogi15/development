package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import util.commonUTIL;
import beans.AccessFunction;
import beans.AccessWindow;

public class AccessSQL {

	final static private String DELETE_ACCESS_FUNCTION =
		"DELETE FROM accessFunctionWise  where groupname = ? and functionName = ? and windowName = ?";
	final static private String DELETE_ACCESS_FUNCTION_WHERE =
			"DELETE FROM accessFunctionWise  where groupname = ? and windowName = ?";
	final static private String INSERT_ACCESS_FUNCTION =
		"INSERT into accessFunctionWise (groupname, windowName, functionName, isAccessable) values(?,?,?,?)";
	final static private String SELECTALL_FUNCTION =
		"SELECT groupname, windowName, functionName, isAccessable FROM accessFunctionWise order by groupName, windowName, functionName";
	final static private String SELECTWHERE_FUNCTION =
		"SELECT  groupname, windowName, functionName, isAccessable FROM accessFunctionWise where ";
	final static private String SELECTONE_FUNCTION =
		"SELECT  groupname, windowName, functionName, isAccessable FROM accessFunctionWise  where groupname = ?, "
		+ " windowName = ?, functionName = ?  order by groupname, windowname, functionname";
		
	 final static private String DELETE_ACCESS_WINDOW =
		"DELETE FROM accessWindowWise  where groupname = ? and  windowName = ?";
	final static private String INSERT_ACCESS_WINDOW =
		"INSERT into accessWindowWise  (groupname, windowName, isAccessable) values(?,?,?)";
	final static private String SELECTALL_WINDOW =
		"SELECT groupname, windowName, isAccessable FROM accessWindowWise order by groupName, windowName";
	final static private String SELECTWHERE_WINDOW =
		"SELECT  groupname, windowName, isAccessable FROM accessWindowWise where ";
	final static private String SELECTONE_WINDOW =
		"SELECT  groupname, windowName, isAccessable FROM accessWindowWise  where groupname = ?, windowName = ?";
				
	 private static String getUpdateAccessWindowSQL(AccessWindow accessWindow) {
		 
	      String updateSQL = new StringBuffer("UPDATE accessFunctionWise  set ")
								.append(" groupname = ").append(accessWindow.getGroupname())
								.append(", windowName = ").append(accessWindow.getWindowName()) 	
								.append(", isAccessable =").append(accessWindow.getIsAccessable())
								.toString();
	      
	      return updateSQL;
	   }				 
	 private static String getUpdateAccessFuntionSQL(AccessFunction accessFunction) {
		 
	      String updateSQL = new StringBuffer("UPDATE accessFunctionWise  set ")
								.append(" groupname = ").append(accessFunction.getGroupname())
								.append(", windowName = ").append(accessFunction.getWindowName()) 	
								.append(", functionName =").append(accessFunction.getFunctionName()) 	
								.append(", isAccessable =").append(accessFunction.getIsAccessable())
								.toString();
	      
	      return updateSQL;
	   }		 
	 public static int saveAccessFunction(Collection<AccessFunction> insertAccessFunctionVec, Connection con) {
		 try {
             return insertAccessFunction(insertAccessFunctionVec, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","saveAccessFunction",e);
        	 return -1;
         }
	 }
	 public static int saveAccessWindow(Collection<AccessWindow> accessWindowVec, Connection con) {
		 try {
             return insertAccessWindow(accessWindowVec, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","saveAccessWindow",e);
        	 return -1;
         }
	 }
	 public static boolean update(AccessFunction updateAccount, Connection con) {
		 try {
             return edit(updateAccount, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","update",e);
        	 return false;
         }
	 }
	 public static boolean update(AccessWindow accessWindow, Connection con) {
		 try {
             return edit(accessWindow, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","update",e);
        	 return false;
         }
	 }
	 public static boolean deleteAccessFunction(Collection<AccessFunction> accessFunctionvec, Connection con) {
		 try {
             return removeAccessFunction(accessFunctionvec, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","deleteAccessFunction",e);
        	 return false;
         }
	 }
	 public static boolean deleteAllAccessFunction(Collection<AccessFunction> accessFunctionvec, Connection con) {
		 try {
             return removeAllAccessFunction(accessFunctionvec, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","deleteAllAccessFunction",e);
        	 return false;
         }
	 }
	 public static boolean deleteAccessWindow(Collection<AccessWindow> accessWindowVec, Connection con) {
		 try {
             return removeAccessWindow(accessWindowVec, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","deleteAccessWindow",e);
        	 return false;
         }
	 }
	 public static AccessFunction selectAccessFunction(AccessFunction accessFunction, Connection con) {
		 try {
             return  select(accessFunction, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","selectAccessFunction",e);
        	 return null;
         }
	 }	 
	 public static AccessWindow selectAccessWindow(AccessWindow accessWindow, Connection con) {
		 try {
             return  select(accessWindow, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","selectAccessWindow",e);
        	 return null;
         }
	 }
	 public static Collection<AccessFunction> selectAllFunction(Connection con) {
		 try {
             return selectAll_Function(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","selectAllFunction",e);
        	 return Collections.emptyList();
         }
	 }
	 public static Collection<AccessWindow> selectAllWindow(Connection con) {
		 try {
             return selectAll_Window(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","selectAllWindow",e);
        	 return Collections.emptyList();
         }
	 }
	 public static Collection<AccessFunction> selectAccessFunction(String where, Connection con) {
		 try {
             return  selectAccessFunctionWhere(where, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","selectAccessFunction",e);
        	 return Collections.emptyList();
         }
	 }
	 public static Collection<AccessWindow> selectAccessWindow(String where, Connection con) {
		 try {
             return  selectAccessWindowWhere(where, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","selectAccessWindow ",e);
        	 return Collections.emptyList();
         }
	 }
	public static int isAccessFunctionAccessible(AccessFunction accessFunction, Connection con) {
		 try {
             return  isAccessFunction_Accessible(accessFunction, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","isAccessFunctionAccessible",e);
        	 return -1;
         }
	 }	 
	 public static int isAccessWindowAccessible(AccessWindow accessWindow, Connection con) {
		 try {
             return  isAccessWindow_Accessible(accessWindow, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AccessSQL: ","isAccessWindowAccessible",e);
        	 return -1;
         }
	 }	 
	 protected static int isAccessFunction_Accessible(AccessFunction accessFunction, Connection con) {
		 
			int i =-1;
			PreparedStatement stmt = null;
			String sql = SELECTONE_FUNCTION;  

		    try {
		    	
				stmt = dsSQL.newPreparedStatement(con, sql);
				stmt.setString(1, accessFunction.getGroupname());
				stmt.setString(2, accessFunction.getWindowName());
				stmt.setString(3, accessFunction.getFunctionName());
				
		        ResultSet rs = stmt.executeQuery();
		        
		        while(rs.next()) {
		        	i = rs.getInt(4);
		        	break;
	     	    }
		        commonUTIL.display("AccessSQL: ","isAccessFunction_Accessible " + sql);
		    }catch (Exception e) {
				 commonUTIL.displayError("AccessSQL: ","isAccessFunction_Accessible " + sql ,e);
				 return i;	          
			 } finally {
		     	try {
		    	   stmt.close();
				} catch (SQLException e) {
				 commonUTIL.displayError("AccessSQL: ","isAccessFunction_Accessible " + sql ,e);
				}
			 }	 
			 
			 return i;
	 }
	 
	 protected static int isAccessWindow_Accessible(AccessWindow accessWindow, Connection con) {
		 
		int i =-1;
		PreparedStatement stmt = null;
		String sql = SELECTONE_WINDOW;  
			
	    try {
	    	stmt = dsSQL.newPreparedStatement(con, sql);
			stmt.setString(1, accessWindow.getGroupname());
			stmt.setString(2, accessWindow.getWindowName());
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        while(rs.next()) {
	        	i = rs.getInt(3);
	        	break;
     	    }
	        commonUTIL.display("AccessSQL: ","isAccessWindow_Accessible " + sql);
	        
		 } catch (Exception e) {
			 commonUTIL.displayError("AccessSQL: ","isAccessWindow_Accessible " + sql ,e);
			 return i;	          
		 } finally {
	     	try {	    	  
	    	   stmt.close();
			} catch (SQLException e) {
			 commonUTIL.displayError("AccessSQL: ","isAccessWindow_Accessible " + sql ,e);
			}
		 }	 
		 
		 return i;
	 }
	 
	 protected static boolean edit(AccessFunction updateAccessFunction, Connection con ) {
		 
		 PreparedStatement stmt = null;
	     String sql = getUpdateAccessFuntionSQL(updateAccessFunction);
		 try {
			 con.setAutoCommit(false);
			 				 
			 stmt = dsSQL.newPreparedStatement(con, sql);
	         stmt.executeUpdate(sql);
			 con.commit();
			 
			 commonUTIL.display("AccessSQL: ","remove: " + sql);
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("AccessSQL: ","edit " +  sql,e);
			 return false;
	     } finally {
	    	 try {	    		
				stmt.close();
	    	 } catch (SQLException e) {
	    		 commonUTIL.displayError("AccessSQL: ","edit",e);
			}
	    }
	    
		return true;
	 }
		
	 protected static  boolean edit(AccessWindow updateAccessWindow, Connection con ) {
		 
		 PreparedStatement stmt = null;
	     String sql = getUpdateAccessWindowSQL(updateAccessWindow);
		 try {
			 con.setAutoCommit(false);
			 				 
			 stmt = dsSQL.newPreparedStatement(con, sql);
	         stmt.executeUpdate(sql);
			 con.commit();
			 
			 commonUTIL.display("AccessSQL: ","remove: " + sql);
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("AccessSQL: ","edit " +  sql,e);
			 return false;
	     } finally {
	    	 try {	    		
				stmt.close();
	    	 } catch (SQLException e) {
	    		 commonUTIL.displayError("AccessSQL: ","edit",e);
			}
	    }
	    
		return true;
	 }

	protected static boolean removeAccessFunction(Collection<AccessFunction> accessFunctionvec, Connection con ) {
		
		PreparedStatement stmt = null;
		try {		
			con.setAutoCommit(false);
			Iterator<AccessFunction> deleteAccessFunctionItr = accessFunctionvec.iterator();
			
			stmt = dsSQL.newPreparedStatement(con, DELETE_ACCESS_FUNCTION);
			
			while(deleteAccessFunctionItr.hasNext()) {				
				AccessFunction deleteAccessFunction = deleteAccessFunctionItr.next();
				
				stmt.setString(1, deleteAccessFunction.getGroupname());
		        stmt.setString(2, deleteAccessFunction.getFunctionName());
		        stmt.setString(3, deleteAccessFunction.getWindowName());
		        stmt.addBatch();
			}
	        	           
	        stmt.executeBatch();	        
			con.commit();
			
			commonUTIL.display("AccessSQL: ","removeAccessFunction: " + DELETE_ACCESS_FUNCTION);
			
		 } catch (Exception e) {
			 commonUTIL.displayError("AccessSQL: ","removeAccessFunction: " + DELETE_ACCESS_FUNCTION,e);
			 return false;		           
	     } finally {
	    	 try {	    		 
				stmt.close();
	    	 } catch (SQLException e) {
	    		 commonUTIL.displayError("AccessSQL: ","removeAccessFunction: "+ DELETE_ACCESS_FUNCTION,e);
			}		  
	     }
	        return true;
	}
	
	protected static boolean removeAllAccessFunction(Collection<AccessFunction> accessFunctionvec, Connection con ) {
		
		PreparedStatement stmt = null;
		try {		
			con.setAutoCommit(false);
			Iterator<AccessFunction> deleteAccessFunctionItr = accessFunctionvec.iterator();
			
			stmt = dsSQL.newPreparedStatement(con, DELETE_ACCESS_FUNCTION_WHERE);
			
			while(deleteAccessFunctionItr.hasNext()) {				
				AccessFunction deleteAccessFunction = deleteAccessFunctionItr.next();
				
				stmt.setString(1, deleteAccessFunction.getGroupname());
		        stmt.setString(2, deleteAccessFunction.getWindowName());
		        stmt.addBatch();
			}
	        	           
	        stmt.executeBatch();	        
			con.commit();
			
			commonUTIL.display("AccessSQL: ","removeAllAccessFunction: " + DELETE_ACCESS_FUNCTION_WHERE);
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("AccessSQL: ","removeAllAccessFunction: "+ DELETE_ACCESS_FUNCTION_WHERE,e);
			 return false;		           
	     } finally {
	    	 try {	    		
				stmt.close();
	    	 } catch (SQLException e) {
	    		 commonUTIL.displayError("AccessSQL: ","removeAllAccessFunction: "+ DELETE_ACCESS_FUNCTION_WHERE,e);
			}		  
	     }
	        return true;
	}

	protected static boolean removeAccessWindow(Collection<AccessWindow> deleteAccessWindow, Connection con ) {
		
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			
			stmt = dsSQL.newPreparedStatement(con, DELETE_ACCESS_WINDOW);
			
			Iterator<AccessWindow> itr = deleteAccessWindow.iterator();
			
			while (itr.hasNext()) {
				AccessWindow accessWindow = itr.next();
				stmt.setString(1, accessWindow.getGroupname());
			    stmt.setString(2, accessWindow.getWindowName());
			    stmt.addBatch();
			}
			       		           
	        stmt.executeBatch();	        
			con.commit();
			
			commonUTIL.display("AccessSQL: ","removeAccessWindow: " + DELETE_ACCESS_WINDOW);
			
		 } catch (Exception e) {
			 commonUTIL.displayError("AccessSQL: ","removeAccessWindow: "+ DELETE_ACCESS_WINDOW,e);
			 return false;		           
	     } finally {
	    	 try {	    		 
				stmt.close();
	    	 } catch (SQLException e) {
	    		 commonUTIL.displayError("AccessSQL: ","removeAccessWindow:" + DELETE_ACCESS_WINDOW,e);
			}		  
	     }
	        return true;
	}
	 protected static int insertAccessFunction(Collection<AccessFunction> insertAccessFunctionVec, Connection con ) {
			
		 PreparedStatement stmt = null;
	     int id = 1;

		 try {
			 con.setAutoCommit(false);
	
			 stmt = dsSQL.newPreparedStatement(con, INSERT_ACCESS_FUNCTION);
			 
			 Iterator<AccessFunction> itr = insertAccessFunctionVec.iterator();
			 while(itr.hasNext()) {
				 AccessFunction accessFunction = itr.next();
				 stmt.setString(1, accessFunction.getGroupname());		
				 stmt.setString(2, accessFunction.getWindowName());
				 stmt.setString(3, accessFunction.getFunctionName());
				 stmt.setInt(4, accessFunction.getIsAccessable());
				 
				 stmt.addBatch();				 
			 }
							         
			 stmt.executeBatch();
	         con.commit();
	         
	         commonUTIL.display("AccessSQL: ", "insertAccessFunction: "+ INSERT_ACCESS_FUNCTION);
	         
		 } catch (Exception e) {
			 commonUTIL.displayError("AccessSQL: ","insertAccessFunction: "+ INSERT_ACCESS_FUNCTION,e);
			 return -1;
	     } finally {
	    	 try {	    		
				stmt.close();
	    	 } catch (SQLException e) {			
				commonUTIL.displayError("AccessSQL: ","insertAccessFunction: "+ INSERT_ACCESS_FUNCTION,e);
			}
	     }
	     
		 return id;
	 }
	 
	 protected static int insertAccessWindow(Collection<AccessWindow> accessWindowVec, Connection con ) {
			
		 PreparedStatement stmt = null;
	     int id = 1;
	     
		 try {
			 con.setAutoCommit(false);
	
			 stmt = dsSQL.newPreparedStatement(con, INSERT_ACCESS_WINDOW);
			 
			 Iterator<AccessWindow> itr = accessWindowVec.iterator();
				
			 while (itr.hasNext()) {
				 AccessWindow insertAccessWindow = itr.next();
				 
				 stmt.setString(1, insertAccessWindow.getGroupname());		
				 stmt.setString(2, insertAccessWindow.getWindowName());
				 stmt.setInt(3, insertAccessWindow.getIsAccessable());
				 stmt.addBatch();
			 }
				         
			 stmt.executeBatch();
	         con.commit();
	         
	         commonUTIL.display("AccessSQL: ", "insertAccessWindow: " + INSERT_ACCESS_FUNCTION);
	         
		 } catch (Exception e) {
			 commonUTIL.displayError("AccessSQL: ","insertAccessWindow: " + INSERT_ACCESS_WINDOW,e);
			 return -1;
	     } finally {
	    	 try {	    		
				stmt.close();
	    	 } catch (SQLException e) {			
				commonUTIL.displayError("AccessSQL: ","insertAccessWindow: " + INSERT_ACCESS_WINDOW,e);
			}
	     }
	     
		 return id;
	 }

	 protected static AccessFunction select(AccessFunction accessFunction, Connection con) {

        PreparedStatement stmt = null;
	    String sql = SELECTONE_FUNCTION;  
		
	    try {
	    	
			stmt = dsSQL.newPreparedStatement(con, sql);
			stmt.setString(1, accessFunction.getGroupname());
			stmt.setString(2, accessFunction.getWindowName());
			stmt.setString(3, accessFunction.getFunctionName());
			
	        ResultSet rs = stmt.executeQuery();
	        
	        while(rs.next()) {
	        	accessFunction.setGroupname(rs.getString(1));
	        	accessFunction.setWindowName(rs.getString(2));
	        	accessFunction.setFunctionName(rs.getString(3));
	        	accessFunction.setIsAccessable(rs.getInt(4));
     	    }
	       
	        commonUTIL.display("AccessSQL: "," select " + sql);
	        
	        return accessFunction;
		 } catch (Exception e) {
			 commonUTIL.displayError("AccessSQL: ","select    " + sql ,e);
			 return new AccessFunction();	          
	     } finally {
	       try {
	    	   stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("AccessSQL: ","select " + sql ,e);
			}
	    }	    
	 }
		 
	 protected static AccessWindow select(AccessWindow accessWindow, Connection con ) {

	        PreparedStatement stmt = null;
		    String sql = SELECTONE_WINDOW;  
			
		    try {
				stmt = dsSQL.newPreparedStatement(con, sql);
				stmt.setString(1, accessWindow.getGroupname());
				stmt.setString(2, accessWindow.getWindowName());
				
		        ResultSet rs = stmt.executeQuery();
		        
		        while(rs.next()) {
		        	accessWindow.setGroupname(rs.getString(1));
		        	accessWindow.setWindowName(rs.getString(2));
		        	accessWindow.setIsAccessable(rs.getInt(3));
	     	    }
		       
		        commonUTIL.display("AccessSQL: "," select " + sql);
		        
		        return accessWindow;
			 } catch (Exception e) {
				 commonUTIL.displayError("AccessSQL: ","select    " + sql ,e);
				 return new AccessWindow();	          
		     } finally {
		       try {
		    	   stmt.close();
				} catch (SQLException e) {
					commonUTIL.displayError("AccessSQL: ","select " + sql ,e);
				}
		    }	    
		 }

	 protected static Collection<AccessFunction> selectAll_Function(Connection con) { 
		 
	     PreparedStatement stmt = null;
	     Collection<AccessFunction> accessFunctionVec = new Vector<AccessFunction>();
	    
		 try {
			 
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL_FUNCTION );
	      
			 ResultSet rs = stmt.executeQuery();
	      
		      while(rs.next()) {	   
		    	  AccessFunction accessFunction = new AccessFunction();
		    	  accessFunction.setGroupname(rs.getString(1));
		    	  accessFunction.setWindowName(rs.getString(2));
		    	  accessFunction.setFunctionName(rs.getString(3));
		    	  accessFunction.setIsAccessable(rs.getInt(4));
		    	  
		    	  accessFunctionVec.add(accessFunction);	      
		      }
		      
		      commonUTIL.display("AccessSQL: "," selectAll_Function " + SELECTALL_FUNCTION);
		      
		 } catch (Exception e) {
			 commonUTIL.displayError("AccessSQL: ", " selectAll_Function " + SELECTALL_FUNCTION, e);
			 return accessFunctionVec;	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {				
				commonUTIL.displayError("AccessSQL: "," selectAll_Function " +SELECTALL_FUNCTION, e);
			}
	     }
		return accessFunctionVec;
	   
	 }
	 
	 protected static Collection<AccessWindow> selectAll_Window(Connection con) { 
		
	     PreparedStatement stmt = null;
	     Collection<AccessWindow> accessWindowVec = new Vector<AccessWindow>();
	    
		 try {
			 
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL_WINDOW );
	      
			 ResultSet rs = stmt.executeQuery();
	      
		      while(rs.next()) {	   
		    	  AccessWindow accessWindow = new AccessWindow();
		    	  accessWindow.setGroupname(rs.getString(1));
		    	  accessWindow.setWindowName(rs.getString(2));
		    	  accessWindow.setIsAccessable(rs.getInt(3));
		    	  
		    	  accessWindowVec.add(accessWindow);	      
		      }
		      
		      commonUTIL.display("AccessSQL: "," selectAll_Window " + SELECTALL_WINDOW);
	      
		 } catch (Exception e) {
			 commonUTIL.displayError("AccessSQL: ", " selectAll_Window " + SELECTALL_WINDOW, e);
			 return accessWindowVec;	        
	     }
	     finally {
	        try {	  
				stmt.close();
			} catch (SQLException e) {				
				commonUTIL.displayError("AccessSQL: ", " selectAll_Window " + SELECTALL_WINDOW, e);
			}
	     }
		return accessWindowVec;
	   
	 }
	 
	 
	 public static Collection<AccessFunction> selectAccessFunctionWhere(String where, Connection con ) {
	
	     PreparedStatement stmt = null;
	     Collection<AccessFunction> accessFunctionVec = new Vector<AccessFunction>();
	     String sql = SELECTWHERE_FUNCTION + where;
	
		 try {
			 stmt = dsSQL.newPreparedStatement(con,sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
		      while(rs.next()) {
		    	  AccessFunction accessFunction = new AccessFunction();
		    	  accessFunction.setGroupname(rs.getString(1));
		    	  accessFunction.setWindowName(rs.getString(2));
		    	  accessFunction.setFunctionName(rs.getString(3));
		    	  accessFunction.setIsAccessable(rs.getInt(4));
		    	  
		    	  accessFunctionVec.add(accessFunction);
		      }
	      
		      commonUTIL.display("AccessSQL: ","selectAccessFunctionWhere: " +sql);
		      
		 } catch (Exception e) {
			 commonUTIL.displayError("AccessSQL: ","selectAccessFunctionWhere: "+sql,e);
			 return accessFunctionVec;	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("AccessSQL: ","selectAccessFunctionWhere: "+sql,e);
			}
	     }
		return accessFunctionVec;
	    
	 }
	 public static Collection<AccessWindow> selectAccessWindowWhere(String where,Connection con ) {

	     PreparedStatement stmt = null;
	     Collection<AccessWindow> accessWindowVec = new Vector<AccessWindow>();
	     String sql = SELECTWHERE_WINDOW + where;

		 try {
			 
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
		      while(rs.next()) {
		    	  AccessWindow accessWindow = new AccessWindow();
		    	  accessWindow.setGroupname(rs.getString(1));
		    	  accessWindow.setWindowName(rs.getString(2));
		    	  accessWindow.setIsAccessable(rs.getInt(3));
		    	  
		    	  accessWindowVec.add(accessWindow);	       	      
		      }
		      
		      commonUTIL.display("AccessSQL: ","selectAccessWindowWhere "+sql);
		      
		 } catch (Exception e) {
			 commonUTIL.displayError("AccessSQL: ","selectAccessWindowWhere "+sql,e);
			 return accessWindowVec;	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("AccessSQL: ","selectAccessWindowWhere"+sql,e);
			}
	     }
		return accessWindowVec;
	    
	 }


}
