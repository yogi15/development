package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.Users;
import util.commonUTIL;


public class UsersSQL {
	
	final static private String DELETE_FROM_Users =
		"DELETE FROM Users where user_groups =? ";
	final static private String INSERT_FROM_Users =
		"INSERT into Users(id,user_groups,user_name,password, salt,attributes) values(?,?,?,?, ?,?)";
	final static private String UPDATE_FROM_Users =
		"UPDATE Users set user_name=?,password=?, user_groups = ?,attributes=? where id =?";
	final static private String SELECT_MAX =
		"SELECT MAX(id) DESC_ID FROM Users ";
	final static private String SELECTALL =
		"SELECT id,user_name,user_groups,password,attributes FROM Users order by user_groups";
	final static private String SELECT =
		"SELECT id,user_name,user_groups,password,attributes  FROM Users where id =? ";
	 static private String SELECTONE =
		"SELECT id,user_name,user_groups,password,attributes  FROM Users where id =  " ;
	 static private String SELECTUSERPASSWORD =
			"SELECT id,user_name,user_groups,password,attributes  FROM Users where " ;
	 static private String SELECTSALT =
			"SELECT salt  FROM Users where USER_NAME LIKE " ;
	 
	 private static String getUpdateSQL(Users user) {
	 String updateSQL = "UPDATE  Users set  user_name='"+ user.getUser_name().trim() +
     "',user_groups='"+ user.getUser_groups() +"', password='" + user.getPassword().trim() +
     "',attributes='" + user.getAttributes().trim() +
     "'  where id = "+ user.getId();
    return updateSQL;
   }
	 
	 public static int save(Users insertUsers, String salt, Connection con) {
		 int id = 0;
		 try {
			 
			 if(insertUsers.getId() == 0)
             id = insert(insertUsers, salt, con); 
			 else 
				 id = update(insertUsers, con);
			 return id;
			 
         }catch(Exception e) {
        	 commonUTIL.displayError("UsersSQL","save",e);
        	 return 0;
         }
	 }
	 public static int update(Users updateUsers, Connection con) {
		 try {
             return edit(updateUsers, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("UsersSQL","update",e);
        	 return 0;
         }
	 }
	 
	 public static boolean delete(Users deleteUsers, Connection con) {
		 try {
             return remove(deleteUsers, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("UsersSQL","update",e);
        	 return false;
         }
	 }
	 public static Users selectUsers(int UsersId, Connection con) {
		 try {
             return  select(UsersId, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("UsersSQL","select",e);
        	 return null;
         }
	 }
	 public static Users selectUsers(String username,String password, Connection con) {
		 try {
             return  select(username, password,con);
         }catch(Exception e) {
        	 commonUTIL.displayError("UsersSQL","select",e);
        	 return null;
         }
	 }
	 public static Users selectUsers(String username,String password, String group,Connection con) {
		 try {
             return  select(username, password,group,con);
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
	 
	 public static String  getSalt(Users user, Connection con) {

		 int j = 0;
	     PreparedStatement stmt = null;
	     String salt = "";
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTSALT + "'" + user.getUser_name() +"'");
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	 salt = rs.getString(1);
       
	         }
	         
	        
		 } catch (Exception e) {
			 commonUTIL.displayError("usersSQL","select",e);
			 
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("usersSQL","selectMax",e);
			}
	        }
	        return salt;
	 }
	 
	 protected static  int edit(Users updateUsers, Connection con ) {
		 
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
	
	protected static boolean remove(Users deleteUsers, Connection con ) {
	
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_Users);
	            stmt.setString(j++, deleteUsers.getUser_groups());
	           
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
	 
	 protected static int insert(Users inserUsers, String salt, Connection con ) {
			
	        PreparedStatement stmt = null;
	        int id = 0;
		 try {
			 
			id = selectMax(con) + 1;
			 con.setAutoCommit(false);
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_Users);
			 
			 stmt.setInt(1,id+1);
			 stmt.setString(2,inserUsers.getUser_groups() );  
			 stmt.setString(3, inserUsers.getUser_name());
	         stmt.setString(4,inserUsers.getPassword());
	         stmt.setString(5, salt);
	         stmt.setString(6,inserUsers.getAttributes());
	            
	         stmt.executeUpdate();
	         con.commit();
	         commonUTIL.display("UsersSQL",INSERT_FROM_Users);
		 
		 } catch (Exception e) {
			 commonUTIL.displayError("UsersSQL",INSERT_FROM_Users,e);
			 return 0;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("UsersSQL",INSERT_FROM_Users,e);
			}
	        }
	        return id;
	 }
	 
	 protected static Users select(int usersIn,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector userss = new Vector();
	        Users users = new Users();
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + usersIn);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	 users.setId(rs.getInt(1));
	        	 users.setUser_name(rs.getString(2));
	        	 users.setUser_groups(rs.getString(3));
	        	 users.setPassword(rs.getString(4));
	        	 users.setAttributes(rs.getString(5));
	        
	      
	       return users;
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("usersSQL","select",e);
			 return users;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("usersSQL","selectMax",e);
			}
	        }
	        return users;
	 }
protected static Users select(String  username,String password,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	       
	        Users users = null;
		 try {
		//	String sql = SELECTUSERPASSWORD + " user_name = '" + username + "' and password = " + "'"  + password + "'";
			 String sql = SELECTUSERPASSWORD + " user_name = '" + username + "'";
				
			System.out.println(sql);
			 stmt = dsSQL.newPreparedStatement(con, sql);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	 users  = new Users();
	        	 users.setId(rs.getInt(1));
	        	 users.setUser_name(rs.getString(2));
	        	 users.setUser_groups(rs.getString(3));
	        	 users.setPassword(rs.getString(4));
	        	 users.setAttributes(rs.getString(5));
	        
	      
	       return users;
	         
	         }
	         commonUTIL.display("usersSQL",SELECTUSERPASSWORD);
		 } catch (Exception e) {
			 commonUTIL.displayError("usersSQL",SELECTUSERPASSWORD,e);
			 return users;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("usersSQL","selectMax",e);
			}
	        }
	        return users;
	 }
protected static Users select(String  username,String password,String groupname,Connection con ) {
	 
	 int j = 0;
       PreparedStatement stmt = null;
       Vector userss = new Vector();
       Users users = null;
	 try {
		//String sql = SELECTUSERPASSWORD + " user_name = '" + username.trim() + "' and password = " + "'"  + password.trim() + "' and user_groups = '" + groupname + "'";
		 String sql = SELECTUSERPASSWORD + " user_name = '" + username.trim() + "' and user_groups like '%" + groupname + "%'";
		// System.out.println(sql);
		 stmt = dsSQL.newPreparedStatement(con, sql);
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()) {
        	users = 	new Users();
       	 users.setId(rs.getInt(1));
       	 users.setUser_name(rs.getString(2));
       	 users.setUser_groups(rs.getString(3));
       	 users.setPassword(rs.getString(4));
       	 users.setAttributes(rs.getString(5));
       
     
      return users;
        
        }
        commonUTIL.display("usersSQL",SELECTUSERPASSWORD);
	 } catch (Exception e) {
		 commonUTIL.displayError("usersSQL",SELECTUSERPASSWORD,e);
		 return users;
          
       }
       finally {
          try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("usersSQL","selectMax",e);
		}
       }
       return users;
}

	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector userss = new Vector();
	     
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	   
	    	Users users = new Users();
	    	users.setId(rs.getInt(1));
	    	users.setUser_name(rs.getString(2));
	        users.setUser_groups(rs.getString(3));
	        users.setPassword(rs.getString(4));
	        users.setAttributes(rs.getString(5));
	        userss.add(users);
	      
	      }
	      commonUTIL.display("usersSQL",SELECTALL);
		 } catch (Exception e) {
			 commonUTIL.displayError("usersSQL",SELECTALL,e);
			 return userss;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("usersSQL",SELECTALL,e);
			}
	     }
	     return userss;
	 }
	 protected static Collection selectusers(int bookId,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector userss = new Vector();
	     
		 try {
			 SELECTONE = SELECTONE + bookId;
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Users users = new Users();
	    	  users.setUser_groups(rs.getString(1));
		        users.setUser_name(rs.getString(2));
		        users.setPassword(rs.getString(3));
		        users.setAttributes(rs.getString(5));
		        userss.add(users);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("usersSQL","selectbook",e);
			 return userss;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("usersSQL","selectMax",e);
			}
	     }
	     return userss;
	 }

}

