package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.Job;
import util.commonUTIL;


public class JobSQL {
	
	final static private String DELETE_FROM_JOB =
		"DELETE FROM JOB where id =? ";
	final static private String INSERT_FROM_JOB =
		"INSERT into JOB(id,users,tradeID,jobDate,jobType,productType,jobStatus) values(?,?,?,?,?,?,?)";
	final static private String UPDATE_FROM_JOB =
		"UPDATE JOB set users=?,tradeID=?,jobDate=?,jobType=?,productType=?,jobStatus=? where id = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(jobno) DESC_ID FROM JOB ";
	final static private String SELECTALL =
		"SELECT id,users,tradeID,jobDate,jobType,productType,jobStatus FROM JOB order by id";
	final static private String SELECT =
		"SELECT title FROM JOB where id =  ?";
	 static private String SELECTONE =
		"SELECT id,users,tradeID,jobDate,jobType,productType,jobStatus FROM JOB where id =  " ;
	 
	 
	 
	 
	 public static boolean save(Job insertJob, Connection con) {
		 try {
             return insert(insertJob, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("JobSQL","save",e);
        	 return false;
         }
	 }
	 public static boolean update(Job updateJob, Connection con) {
		 try {
             return edit(updateJob, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("JobSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(Job deleteJob, Connection con) {
		 try {
             return remove(deleteJob, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("JobSQL","update",e);
        	 return false;
         }
	 }
	 public static Job selectBook(int JobId, Connection con) {
		 try {
             return  select(JobId, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("JobSQL","select",e);
        	 return null;
         }
	 }
	 public static Collection selectALL(Connection con) {
		 try {
             return select(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("JobSQL","selectALL",e);
        	 return null;
         }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
             return selectMax(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("JobSQL","selectMaxID",e);
        	 return 0;
         }
	 }
	 
	 protected static  boolean edit(Job updateJob, Connection con ) {
		 
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, UPDATE_FROM_JOB);
	            
			
	           
	            
	            stmt.setInt(1, updateJob.getId());
	            stmt.setString(2, updateJob.getUsers());
	            stmt.setInt(3, updateJob.getTradeID());
	            stmt.setString(1, updateJob.getJobDate());
	            stmt.setString(2, updateJob.getJobType());
	            stmt.setInt(3, updateJob.getProductType());
	            stmt.setString(3, updateJob.getJobStatus());
	            
	            stmt.executeUpdate();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("JobSQL","edit",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("JobSQL","edit",e);
			}
	        }
	        return true;
	 }
	
	protected static boolean remove(Job deleteJob, Connection con ) {
	
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_JOB);
	            stmt.setInt(j++, deleteJob.getId());
	           
	            stmt.executeUpdate();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("JobSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("JobSQL","remove",e);
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
			 commonUTIL.displayError("JobSQL",SELECT_MAX,e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("JobSQL",SELECT_MAX,e);
			}
	        }
	        return j;
	 }
	 
	 protected static boolean insert(Job inserJob, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 int id = selectMax(con);
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_JOB);
			 stmt.setInt(1,id+1);
	            stmt.setString(2, inserJob.getUsers());
	            stmt.setInt(3,inserJob.getTradeID());
	            stmt.setString(4, inserJob.getJobDate());
	            stmt.setString(5,inserJob.getJobType());
	            stmt.setInt(6, inserJob.getProductType());
	            stmt.setString(7,inserJob.getJobStatus());
	            
	            
	            stmt.executeUpdate();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("JobSQL",INSERT_FROM_JOB,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("JobSQL",INSERT_FROM_JOB,e);
			}
	        }
	        return true;
	 }
	 
	 protected static Job select(int jobIn,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector jobs = new Vector();
	        Job job = new Job();
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + jobIn);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        
	        	 job.setId(rs.getInt(1));
	        	 job.setUsers(rs.getString(2));
	        	 job.setTradeID(rs.getInt(3));
	        	 job.setJobDate(rs.getString(4));
	        	 job.setJobType(rs.getString(5));
	        	 job.setProductType(rs.getInt(6));
	        	 job.setJobStatus(rs.getString(7));
	       
	        
	      
	       return job;
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("jobSQL","select",e);
			 return job;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("jobSQL","selectMax",e);
			}
	        }
	        return job;
	 }

	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector jobs = new Vector();
	     
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	   
	    	  Job job = new Job();
	    	  job.setId(rs.getInt(1));
	        	 job.setUsers(rs.getString(2));
	        	 job.setTradeID(rs.getInt(3));
	        	 job.setJobDate(rs.getString(4));
	        	 job.setJobType(rs.getString(5));
	        	 job.setProductType(rs.getInt(6));
	        	 job.setJobStatus(rs.getString(7));
	    	  jobs.add(job);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("jobSQL",SELECTALL,e);
			 return jobs;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("jobSQL",SELECTALL,e);
			}
	     }
	     return jobs;
	 }
	 protected static Collection selectjob(int jobId,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector jobs = new Vector();
	     
		 try {
			 SELECTONE = SELECTONE + jobId;
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Job job = new Job();
	    	  job.setId(rs.getInt(1));
	        	 job.setUsers(rs.getString(2));
	        	 job.setTradeID(rs.getInt(3));
	        	 job.setJobDate(rs.getString(4));
	        	 job.setJobType(rs.getString(5));
	        	 job.setProductType(rs.getInt(6));
	        	 job.setJobStatus(rs.getString(7));
	        	 jobs.add(job);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("jobSQL","selectjob",e);
			 return jobs;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("jobSQL","selectMax",e);
			}
	     }
	     return jobs;
	 }

}
