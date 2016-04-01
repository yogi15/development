package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.Folder;
import beans.UserJobsDetails;


public class JobDetailsSQL {
	final static private String tableName = "jobDetails";
	final static private String DELETE =
		"DELETE FROM " + tableName + "   where recordid =? ";
	final static private String DELETEJOBS = "DELETE FROM " + tableName + "   where jobid =? ";
	final static private String INSERT =
		"INSERT into " + tableName + "(jobid,recordid,columnName,criteria,colvalues,filterValues,andor) values(?,?,?,?,?,?,?)";
	final static private String UPDATE =
		"UPDATE " + tableName + " set name=?,role=?,status=?  where id = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(id) DESC_ID FROM " + tableName + " ";
	final static private String SELECTALL =
		"SELECT jobid,recordid,columnName,criteria,colvalues,filterValues,andor  FROM " + tableName + " order by jobid,recordid ";
	final static private String SELECT =
		"SELECT jobid,recordid,columnName,criteria,colvalues,filterValues,andor  FROM " + tableName + " where jobid =  ?" ;
	 static private String SELECTONE =
		"SELECT jobid,recordid,columnName,criteria,colvalues,filterValues,andor FROM " + tableName + " where jobid =  ";
	 private static String whereClause =
		 "SELECT jobid,recordid,columnName,criteria,colvalues,filterValues,andor  FROM " + tableName + " where ";
	 
	 private static String getUpdateSQL(UserJobsDetails jobdetails) {
	      String updateSQL = "UPDATE jobDetails  set " +
	      		  		" jobid= " + jobdetails.getJobId() + 
	      		  		" rowid= " + jobdetails.getRowid() + 
	      		  		" criteria= '" + jobdetails.getCriteria() + 
	      		  		"' columnName= '" + jobdetails.getColumnName() + 
	      		  		"' values= '" + jobdetails.getValues() + 
	      		  		"' filterValues= '" + jobdetails.getFilterValues() + 
	      		  		"' andor= '" + jobdetails.getAnd_or()  + 
	      		"'  where jobid= " + jobdetails.getJobId() + " and rowid = " + jobdetails.getRowid();
	      return updateSQL;
	     }
 
	 
	 public static UserJobsDetails save(UserJobsDetails insertUserJobsDetails, Connection con) {
		 try {
             return insert(insertUserJobsDetails, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("UserJobsDetailsSQL","save",e);
        	 return null;
         }
	 }
	 public static boolean update(UserJobsDetails updateUserJobsDetails, Connection con) {
		 try {
             return edit(updateUserJobsDetails, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("UserJobsDetailsSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(UserJobsDetails deleteUserJobsDetails, Connection con) {
		 try {
             return remove(deleteUserJobsDetails, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("UserJobsDetailsSQL","update",e);
        	 return false;
         }
	 }
	 
	 protected static boolean remove(UserJobsDetails deleteUserJobsDetails, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, DELETE);
	            stmt.setInt(j++, deleteUserJobsDetails.getJobId());
	           
	            stmt.executeUpdate();
	            con.commit();
		 } catch (Exception e) {
			 commonUTIL.displayError("UserJobsDetailsSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("UserJobsDetailsSQL","remove",e);
			}
	        }
	        return true;
	 }
	 
	 
	 public static Vector selectUserJobsDetails(int UserJobsDetailsId, Connection con) {
		 try {
       return (Vector) select(UserJobsDetailsId, con);
   }catch(Exception e) {
  	 commonUTIL.displayError("UserJobsDetailsSQL","select",e);
  	 return null;
   }
	 }


	 public static Collection selectALL(Connection con) {
		 try {
       return select(con);
   }catch(Exception e) {
  	 commonUTIL.displayError("UserJobsDetailsSQL","selectALL",e);
  	 return null;
   }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
       return selectMax(con);
   }catch(Exception e) {
  	 commonUTIL.displayError("UserJobsDetailsSQL","selectMaxID",e);
  	 return 0;
   }
	 }
	 
	 protected static  boolean edit(UserJobsDetails updateUserJob, Connection con ) {
		 
		 PreparedStatement stmt = null;
	        String sql = getUpdateSQL(updateUserJob);
		 try {
			 con.setAutoCommit(false);
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, sql);
	            stmt.executeUpdate(sql);
			 con.commit();
			 commonUTIL.display("UserJobsDetailsSQL ::  edit  ", sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("UserJobsDetailsSQL","edit  " + sql ,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("UserJobsDetailsSQL"," edit ",e);
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
			 commonUTIL.displayError("UserJobsDetailsSQL",SELECT_MAX,e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("UserJobsDetailsSQL",SELECT_MAX,e);
			}
	        }
	        return j;
	 }

protected static UserJobsDetails insert(UserJobsDetails userJobsDetails, Connection con ) {
	
    PreparedStatement stmt = null;
 try {
	 con.setAutoCommit(false);
	// int id = selectMax(con);
	 //int j = 1;
	 stmt = dsSQL.newPreparedStatement(con, INSERT);
	 stmt.setInt(1,userJobsDetails.getJobId());
	
        stmt.setInt(2, userJobsDetails.getRowid());
        stmt.setString(3, userJobsDetails.getColumnName());
        stmt.setString(4, userJobsDetails.getCriteria());
        stmt.setString(5, userJobsDetails.getValues());
        
        stmt.setString(6, userJobsDetails.getFilterValues());
        stmt.setString(7, userJobsDetails.getAnd_or());
        stmt.executeUpdate();
        con.commit();
        commonUTIL.display("UserJobsDetailsSQL",INSERT);
        return userJobsDetails;
 } catch (Exception e) {
	 commonUTIL.displayError("UserJobsDetailsSQL",INSERT,e);
	 return null;
       
    }
    finally {
       try {
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		commonUTIL.displayError("UserJobsDetailsSQL","insert",e);
	}
    }
   
}

protected static Collection select(int userid,Connection con ) {
 
 int j = 0;
    PreparedStatement stmt = null;
    Vector jobs = new Vector();
    String sql = SELECTONE + userid +  " order by jobid,recordid ";
    
 try {
	 con.setAutoCommit(false);
	 stmt = dsSQL.newPreparedStatement(con, sql);
	// id,userid,treeid,treenodename,tabid
     ResultSet rs = stmt.executeQuery();
	// id,rowid,columnName,criteria,values

     while(rs.next()) {
    	 UserJobsDetails job = new UserJobsDetails();
    	 
    	 job.setJobId(rs.getInt(1));
    	 job.setRowid(rs.getInt(2));
    	 job.setColumnName(rs.getString(3));
    	 job.setCriteria(rs.getString(4));
    	 job.setValues(rs.getString(5));
    	 job.setFilterValues(rs.getString(6));
    	 job.setAnd_or(rs.getString(7));
	       
	      
    	 jobs.add(job);
     
     }
     commonUTIL.display("UserJobsDetailsSQL " , " select " + sql);
 } catch (Exception e) {
	 commonUTIL.displayError("JobDetailsSQL",SELECTONE + userid ,e);
	 return jobs;
       
    }
    finally {
       try {
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		commonUTIL.displayError("JobDetailsSQL",SELECTONE + userid,e);
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
	  UserJobsDetails job = new UserJobsDetails();
 	 
	
 	 
 	 job.setJobId(rs.getInt(1));
 	 job.setRowid(rs.getInt(2));
 	 job.setColumnName(rs.getString(3));
 	 job.setCriteria(rs.getString(4));
 	 job.setValues(rs.getString(5));
 	job.setFilterValues(rs.getString(6));
 	 job.setAnd_or(rs.getString(7));
 	 jobs.add(job);
       
     
  
  }
 } catch (Exception e) {
	 commonUTIL.displayError("JobDetailsSQL",SELECTALL ,e);
	 return jobs;
    
 }
 finally {
    try {
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		commonUTIL.displayError("JobDetailsSQL","SELECTALL",e);
	}
 }
 return jobs;
}


public static boolean   delete(int jobid, Connection con) {
	// TODO Auto-generated method stub
	  PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, DELETEJOBS);
	            stmt.setInt(j++,  jobid);
	           
	            stmt.executeUpdate();
	            con.commit();
		 } catch (Exception e) {
			 commonUTIL.displayError("UserJobsDetailsSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("UserJobsDetailsSQL","remove",e);
			}
	        }
	        return true;
}


}


