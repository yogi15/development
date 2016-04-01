package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.Task;
import beans.UserJob;
import beans.UserJob;
import beans.UserJobsDetails;

public class UserJobSQL {
	
	final static private String tableName = "userjob";
	final static private String DELETE =
		"DELETE FROM " + tableName + "   where id =? ";
	final static private String INSERT =
		"INSERT into " + tableName + "(id,userid,treeid,treenodename,tabid,type,userjobsql) values(?,?,?,?,?,?,?)";
	final static private String UPDATE =
		"UPDATE " + tableName + " set name=?,role=?,status=?  where id = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(id) DESC_ID FROM " + tableName + " ";
	final static private String SELECTALL =
		"SELECT id,userid,treeid,treenodename,tabid,type,userjobsql  FROM " + tableName + " order by id ";
	final static private String SELECT =
		"SELECT id,userid,treeid,treenodename,tabid,type,userjobsql FROM " + tableName + " where userid =  ?";
	 static private String SELECTONE =
		"SELECT id,userid,treeid,treenodename,tabid,type,userjobsql  FROM " + tableName + " where userid =  ";
	 private static String whereClause =
		 "SELECT id,userid,treeid,treenodename,tabid,type,userjobsql  FROM " + tableName + " where ";
	 
	 private static String getUpdateSQL(UserJob jobs) {
	      String updateSQL = "UPDATE userjob  set " +
	      		  		" id= " + jobs.getId() + 
	      		  		" ,userid= " + jobs.getUserID() + 
	      		  		" ,treeid= " + jobs.getTreeid() + 
	      		  		" ,treenodename= '" + jobs.getTreeNodeName() + 
	      		  		"', tabid= '" + jobs.getTabid() + 
	      		  		"', type= '" + jobs.getType() + 
	      		  		"', userjobsql= '" + jobs.getSql() + 
	      		"'  where id= " + jobs.getId() ;
	      return updateSQL;
	     }

	 
	 public static UserJob save(UserJob insertUserJob, Connection con) {
		 try {
             return insert(insertUserJob, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("UserJobSQL","save",e);
        	 return null;
         }
	 }
	 public static boolean update(UserJob updateUserJob, Connection con) {
		 try {
             return edit(updateUserJob, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("UserJobSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(UserJob deleteUserJob, Connection con) {
		 try {
             return remove(deleteUserJob, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("UserJobSQL","update",e);
        	 return false;
         }
	 }
	 
	 protected static boolean remove(UserJob deleteUserJob, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, DELETE);
	            stmt.setInt(j++, deleteUserJob.getId());
	           
	            stmt.executeUpdate();
	            con.commit();
		 } catch (Exception e) {
			 commonUTIL.displayError("UserJobSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("UserJobSQL","remove",e);
			}
	        }
	        return true;
	 }
	 
	 
	 public static Vector selectUserJob(int UserJobId, Connection con) {
		 try {
       return (Vector) select(UserJobId, con);
   }catch(Exception e) {
  	 commonUTIL.displayError("UserJobSQL","select",e);
  	 return null;
   }
	 }
	 

	 public static Collection selectALL(Connection con) {
		 try {
       return select(con);
   }catch(Exception e) {
  	 commonUTIL.displayError("UserJobSQL","selectALL",e);
  	 return null;
   }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
       return selectMax(con);
   }catch(Exception e) {
  	 commonUTIL.displayError("UserJobSQL","selectMaxID",e);
  	 return 0;
   }
	 }
	 
	 protected static  boolean edit(UserJob updateUserJob, Connection con ) {
		 
		 PreparedStatement stmt = null;
	        String sql = getUpdateSQL(updateUserJob);
		 try {
			 con.setAutoCommit(false);
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, sql);
	            stmt.executeUpdate(sql);
			 con.commit();
			 commonUTIL.display("UserJobSQL ::  edit  ", sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("UserJobSQL","edit  " + sql ,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("UserJobsSQL"," edit ",e);
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
			 commonUTIL.displayError("UserJobSQL",SELECT_MAX,e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("UserJobSQL",SELECT_MAX,e);
			}
	        }
	        return j;
	 }

protected static UserJob insert(UserJob userJob, Connection con ) {
	
    PreparedStatement stmt = null;
 try {
	 con.setAutoCommit(false);
	 int id = selectMax(con);
	 int j = id + 1;
	 stmt = dsSQL.newPreparedStatement(con, INSERT);
	 stmt.setInt(1,j);
	
        stmt.setInt(2, userJob.getUserID());
        stmt.setInt(3, userJob.getTreeid());
        stmt.setString(4, userJob.getTreeNodeName());
        stmt.setInt(5, userJob.getTabid());
        stmt.setString(6, userJob.getType());
        stmt.setString(7, userJob.getSql());
        
        stmt.executeUpdate();
        con.commit();
        commonUTIL.display("UserJobSQL",INSERT);
        userJob.setId(j);
 } catch (Exception e) {
	 commonUTIL.displayError("UserJobSQL",INSERT,e);
	 return null;
       
    }
    finally {
       try {
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		commonUTIL.displayError("UserJobSQL","insert",e);
	}
    }
    return userJob;
}

protected static Collection select(int userid,Connection con ) {
 
 int j = 0;
    PreparedStatement stmt = null;
    Vector jobs = new Vector();
    String sql = SELECTONE + userid;
    
 try {
	 con.setAutoCommit(false);
	 stmt = dsSQL.newPreparedStatement(con, sql);
	// id,userid,treeid,treenodename,tabid
     ResultSet rs = stmt.executeQuery();
    
     while(rs.next()) {
    	 UserJob job = new UserJob();
    	 
    	 job.setId(rs.getInt(1));
    	 job.setUserID(rs.getInt(2));
    	 job.setTreeid(rs.getInt(3));
    	 job.setTreeNodeName(rs.getString(4));
    	 job.setTabid(rs.getInt(5));
    	 job.setType(rs.getString(6));
    	 job.setSql(rs.getString(7));
    	 jobs.add(job);
     
     }
     commonUTIL.display("UserJobSQL " , " select " + sql);
 } catch (Exception e) {
	 commonUTIL.displayError("TaskSQL",SELECTONE + userid ,e);
	 return jobs;
       
    }
    finally {
       try {
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		commonUTIL.displayError("TaskSQL",SELECTONE + userid,e);
	}
    }
    return jobs;
}

public static Collection select(int userid,String type,Connection con ) {
	 
	 int j = 0;
	    PreparedStatement stmt = null;
	    Vector jobs = new Vector();
	    String sql = SELECTONE + userid + " and  type ='" + type+"'";
	    
	 try {
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, sql);
		// id,userid,treeid,treenodename,tabid
	     ResultSet rs = stmt.executeQuery();
	    
	     while(rs.next()) {
	    	 UserJob job = new UserJob();
	    	 
	    	 job.setId(rs.getInt(1));
	    	 job.setUserID(rs.getInt(2));
	    	 job.setTreeid(rs.getInt(3));
	    	 job.setTreeNodeName(rs.getString(4));
	    	 job.setTabid(rs.getInt(5));
	    	 job.setType(rs.getString(6));
	    	 job.setSql(rs.getString(7));
	    	 jobs.add(job);
	     
	     }
	     commonUTIL.display("UserJobSQL " , " select " + sql);
	 } catch (Exception e) {
		 commonUTIL.displayError("TaskSQL",SELECTONE + userid ,e);
		 return jobs;
	       
	    }
	    finally {
	       try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("TaskSQL",SELECTONE + userid,e);
		}
	    }
	    return jobs;
	}


protected static Collection select(Connection con) { 
 int j = 0;
 PreparedStatement stmt = null;
 Vector jobs = new Vector();
 
 try {
	 con.setAutoCommit(false);
	 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
  
  ResultSet rs = stmt.executeQuery();
  
  while(rs.next()) {
	  UserJob job = new UserJob();
 	 
 	 job.setId(rs.getInt(1));
 	 job.setUserID(rs.getInt(2));
 	 job.setTreeid(rs.getInt(3));
 	 job.setTreeNodeName(rs.getString(4));
 	 job.setTabid(rs.getInt(5));
 	 job.setType(rs.getString(6));
 	job.setSql(rs.getString(7));
 	 jobs.add(job);
       
     
  
  }
 } catch (Exception e) {
	 commonUTIL.displayError("TaskSQL",SELECTALL ,e);
	 return jobs;
    
 }
 finally {
    try {
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		commonUTIL.displayError("TaskSQL","SELECTALL",e);
	}
 }
 return jobs;
}


}
