package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.SerivceManager;

public class ServiceManagerSQL {
	
	
	final static private String DELETE_FROM_Serivce =
			"DELETE FROM Serivce whereid =? ";
		final static private String INSERT_FROM_Serivce  =
			"INSERT into Serivce(id,servicename) values(?,?)";
		final static private String UPDATE_FROM_Users =
			"UPDATE Users set user_name=?,password=?, user_groups = ? where id =?";
		final static private String SELECT_MAX =
			"SELECT MAX(id) DESC_ID FROM Serivce ";
		final static private String SELECTALL =
			"SELECT id,servicename FROM Serivce order by id";
		final static private String SELECT =
			"SELECT  id,servicename FROM Serivce   where id =? ";
		 static private String SELECTONE =
			"SELECT  id,servicename FROM Serivce where id =  " ;
		 
		 private static String getUpdateSQL(SerivceManager service) {
		 String updateSQL = "UPDATE  Serivce set  serivcename='"+ service.getName().trim() +
	     "'  where id = "+ service.getId();
	    return updateSQL;
		 }
		 
		 public static int save(SerivceManager insertService, Connection con) {
			 int id = 0;
			 try {
				 
				 if(insertService.getId() == 0)
	             id = insert(insertService, con); 
				 else 
					 id = update(insertService, con);
				 return id;
				 
	         }catch(Exception e) {
	        	 commonUTIL.displayError("ServiceManagerSQL","save",e);
	        	 return 0;
	         }
		 }
		 public static int update(SerivceManager updatetService, Connection con) {
			 try {
	             return edit(updatetService, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("ServiceManagerSQL","update",e);
	        	 return 0;
	         }
		 }
		 
		 public static boolean delete(int id, Connection con) {
			 try {
	             return remove(id, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("ServiceManagerSQL","update",e);
	        	 return false;
	         }
		 }
		 public static SerivceManager selectUsers(int id, Connection con) {
			 try {
	             return  select(id, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("UsersSQL","select",e);
	        	 return null;
	         }
		 }
		 
		
		 public static Collection selectALL(Connection con) {
			 try {
	             return select(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("UsersSQL","selectALL",e);
	        	 return null;
	         }
		 }
		 
		 public static int selectMaxID(Connection con) {
			 try {
	             return selectMax(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("UsersSQL","selectMaxID",e);
	        	 return 0;
	         }
		 }
		 
		 protected static  int edit(SerivceManager updateUsers, Connection con ) {
			 
		        PreparedStatement stmt = null;
		        int id = 0;
			 try {
				String sql =  getUpdateSQL(updateUsers);
				con.setAutoCommit(false);
				 stmt = con.prepareStatement(sql);
		            stmt.execute(sql);
		             con.commit();
		             commonUTIL.display("UsersSQL ::  edit", sql);
		             return updateUsers.getId();
		             
				 
			 } catch (Exception e) {
				 commonUTIL.displayError("UsersSQL","edit",e);
				 return 0;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("UsersSQL","edit",e);
				}
		        }
		       
		 }
		
		protected static boolean remove(int id, Connection con ) {
		
		        PreparedStatement stmt = null;
			 try {
				 int j = 1;
				 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_Serivce);
		            stmt.setInt(1, id);
		           
		            stmt.executeUpdate();
				 
			 } catch (Exception e) {
				 commonUTIL.displayError("UsersSQL","remove",e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("UsersSQL","remove",e);
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
				 commonUTIL.displayError("UsersSQL",SELECT_MAX,e);
				 return j;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("UsersSQL",SELECT_MAX,e);
				}
		        }
		        return j;
		 }
		 
		 protected static int insert(SerivceManager inserSerivces, Connection con ) {
				
		        PreparedStatement stmt = null;
		        int id = 0;
			 try {
				 
				id = selectMax(con);
				 con.setAutoCommit(false);
				 int j = 1;
				 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_Serivce );
				 stmt.setInt(1,id+1);
		            stmt.setString(2, inserSerivces.getName());
		            
		            
		            stmt.executeUpdate();
		            con.commit();
		            commonUTIL.display("UsersSQL",INSERT_FROM_Serivce);
			 } catch (Exception e) {
				 commonUTIL.displayError("UsersSQL",INSERT_FROM_Serivce,e);
				 return 0;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("UsersSQL",INSERT_FROM_Serivce,e);
				}
		        }
		        return id;
		 }
		 protected static SerivceManager select(int id,Connection con ) {
			 
			 int j = 0;
		        PreparedStatement stmt = null;
		        Vector SerivceManagers = new Vector();
		        SerivceManager serivceManager = new SerivceManager();
			 try {
				
				 stmt = dsSQL.newPreparedStatement(con, SELECTONE + id);
		         
		         ResultSet rs = stmt.executeQuery();
		         
		         while(rs.next()) {
		        	 
		        	 serivceManager.setId(rs.getInt(1));
		        	 serivceManager.setName(rs.getString(2));
		        	 SerivceManagers.add(serivceManager);
		       
		        
		      
		     
		         
		         }
		         return serivceManager;
			 } catch (Exception e) {
				 commonUTIL.displayError("SerivceManagerSQL","select",e);
				 return serivceManager;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("SerivceManagerSQL","selectMax",e);
				}
		        }
		     
		 }
		 protected static Collection selectSerivceManager(int id,Connection con ) {
			 int j = 0;
		     PreparedStatement stmt = null;
		     Vector userss = new Vector();
		     String sql = SELECTONE;
		     Vector SerivceManagers = new Vector();
		        SerivceManager serivceManager = new SerivceManager();
			 try {
				 sql = sql + id;
				 stmt = dsSQL.newPreparedStatement(con, sql );
		      
		      ResultSet rs = stmt.executeQuery();
		      
		      while(rs.next()) {
		    		 serivceManager.setId(rs.getInt(1));
		        	 serivceManager.setName(rs.getString(2));
		        	 SerivceManagers.add(serivceManager);
		      
		      }
			 } catch (Exception e) {
				 commonUTIL.displayError("SerivceManagerSQL","selectSerivceManager",e);
				 return userss;
		        
		     }
		     finally {
		        try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("SerivceManagerSQL","selectSerivceManager",e);
				}
		     }
		     return userss;
		 }

		 protected static Collection select(Connection con ) {
			 int j = 0;
		     PreparedStatement stmt = null;
		     Vector userss = new Vector();
		 
		     Vector SerivceManagers = new Vector();
		        SerivceManager serivceManager = new SerivceManager();
			 try {
				
				 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
		      
		      ResultSet rs = stmt.executeQuery();
		      
		      while(rs.next()) {
		    		 serivceManager.setId(rs.getInt(1));
		        	 serivceManager.setName(rs.getString(2));
		        	 SerivceManagers.add(serivceManager);
		      
		      }
		      return SerivceManagers;
			 } catch (Exception e) {
				 commonUTIL.displayError("SerivceManagerSQL","selectSerivceManager",e);
				 return userss;
		        
		     }
		     finally {
		        try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("SerivceManagerSQL","selectSerivceManager",e);
				}
		     }
		    
		 }
}
