package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.Fees;
import beans.Task;
import beans.Trade;
import beans.Transfer;

import util.commonUTIL;

public class TaskSQL {
	
	final static private String tableName = "task";
	final static private String DELETE =
		"DELETE FROM " + tableName + "   where id =? ";
	final static private String INSERT =
		"INSERT into " + tableName + "(id,tradeid,productId,tasktype,action,taskDate,status,statusDone,currency,bookno,eventtype,user_name,user_id,task_status,producttype,WFType,transferID,transferVersion,tradeVersion,cpid,nettedConfigid,messageid) values(TASK_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	final static private String UPDATE =
		"UPDATE " + tableName + " set name=?,role=?,status=?  where id = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(id) DESC_ID FROM " + tableName + " ";
	final static private String SELECTALL =
		"SELECT id,tradeid,productId,tasktype,action,taskDate,status,statusDone,currency,bookno,eventtype,user_name,user_id,task_status,producttype,WFType,transferID,transferVersion,tradeVersion,cpid,nettedConfigid,messageid  FROM " + tableName + " order by id ";
	final static private String SELECT =
		"SELECT id,tradeid,productId,tasktype,action,taskDate,status,statusDone.currency,bookno,eventtype,user_name,user_id,task_status,producttype,WFType,transferID,transferVersion,tradeVersion,cpid,nettedConfigid,messageid FROM " + tableName + " where id =  ?";
	 static private String SELECTONE =
		"SELECT id,tradeid,productId,tasktype,action,taskDate,status,statusDone.currency,bookno,eventtype,user_name,user_id,task_status,producttype,WFType,transferID,transferVersion,tradeVersion,cpid,nettedConfigid,messageid FROM " + tableName + " where id =  ";
	 private static String whereClause =
		 "SELECT id,tradeid,productId,tasktype,action,taskDate,status,statusDone,currency,bookno,eventtype,user_name,user_id,task_status,producttype,WFType,transferID,transferVersion,tradeVersion,cpid,nettedConfigid,messageid   FROM " + tableName + " where ";
	 private static String checkStatus = " select id from task where ";
	 
	 private static String getUpdateSQL(Task task) {
	      String updateSQL = "UPDATE Task  set " +
	      		" tradeID= " + task.getTradeID() + 
	      		" ,productId= " + task.getProductID() + 
	      		" ,tasktype= '" + task.getType() + 
	      		"' ,action= '" + task.getAction() + 	
	      		"' ,taskDate= '" + task.getTaskDate() + 	
	      		"',status= '" + task.getStatus() + 	
	      		"',statusDone= '" + task.getStatusDone() + 	
	      		"',currency= '" +task.getCurrency()+ 	
	      			"',bookno= " +task.getBookid()+ 	
	      				",eventtype= '" +task.getEvent_type()+ 	
	      					"',user_name= '" +task.getUser_name()+ 	
	      					"' ,user_id= " +task.getUserid()+ 	
	      					",task_status= '" +task.getTaskstatus()+ 
	      					"',productType ='" +task.getProductType() + 
	      					"',WFType  = '" +task.getWFType() + 
	      					"',transferID = " +task.getTransferID() + 
	      					",transferVersion  = " +task.getTransferVersionID() + 
	      					",tradeVersion = " +task.getTradeVersionID() + 
	      					",cpid = " +task.getCpid() +
	      					",nettedConfigid = " +task.getNettedConfigID() +
	      					",messageid  = " +task.getMessageID() +
	      		"  where id= " + task.getId();
	      return updateSQL;
	     }
	 
	 
	 
	 
	 public static int save(Task insertTask, Connection con) {
		 try {
             return insert(insertTask, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("TaskSQL","save",e);
        	 return 0;
         }
	 }
	 public static boolean update(Task updateTask, Connection con) {
		 try {
             return edit(updateTask, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("TaskSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(Task deleteTask, Connection con) {
		 try {
             return remove(deleteTask, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("TaskSQL","update",e);
        	 return false;
         }
	 }
	 
	 protected static boolean remove(Task deleteTask, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, DELETE);
	            stmt.setInt(j++, deleteTask.getId());
	           
	            stmt.executeUpdate();
	            con.commit();
		 } catch (Exception e) {
			 commonUTIL.displayError("TaskSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TaskSQL","remove",e);
			}
	        }
	        return true;
	 }
	 
	 
	 public static Collection selectwhereforReports(String sql,Connection con) {
		   try {
		          return selectOnWherecClauseReports(sql, con);
		      }catch(Exception e) {
		       commonUTIL.displayError("TaakSQL","selectwhere",e);
		       return null;
		      }
		  }
	 
	 public static Vector selectTask(int TaskId, Connection con) {
		 try {
       return (Vector) select(TaskId, con);
   }catch(Exception e) {
  	 commonUTIL.displayError("TaskSQL","select",e);
  	 return null;
   }
	 }
	 public static Collection selectALL(Connection con) {
		 try {
       return select(con);
   }catch(Exception e) {
  	 commonUTIL.displayError("TaskSQL","selectALL",e);
  	 return null;
   }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
       return selectMax(con);
   }catch(Exception e) {
  	 commonUTIL.displayError("TaskSQL","selectMaxID",e);
  	 return 0;
   }
	 }
	 
	 protected static  boolean edit(Task updateTask, Connection con ) {
		 
	        PreparedStatement stmt = null;
	        String sql = "";
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 sql = getUpdateSQL(updateTask);
			// System.out.println(sql);
			 stmt = con.prepareStatement(sql);
			 stmt.execute();
	           con.commit();
	           commonUTIL.display("TaskSQL",sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("TaskSQL",sql,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TaskSQL",sql,e);
			}
	        }
	        return true;
	 }
	 public static int updateCompletedTradeTask(int tradeID, int version,Connection con) {
			// TODO Auto-generated method stub
			String sql = " update task set task_status = '2' where tradeid = "+tradeID+ "  and tradeVersion = " +version + " and task_status not in ('2') and wftype = 'TRADE'";
			PreparedStatement stmt = null;
			 int j = 0;
					
					 try {
						
						 con.setAutoCommit(false);
						 sql = sql;
						// System.out.println(sql);
						 stmt = con.prepareStatement(sql);
						j = stmt.executeUpdate();
				           con.commit();
				           commonUTIL.display("TaskSQL" , " updateCompletedTradeTask " + sql);
				           return j;
					 } catch (Exception e) {
						 commonUTIL.displayError("TaskSQL", " updateCompletedTradeTask " + sql,e);
						 return j;
				           
				        }
				        finally {
				           try {
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							commonUTIL.displayError("TaskSQL", " updateCompletedTradeTask " + sql,e);
						}
				        }
				    
				}
	 
	 public static int updateCompletedTransferTaskStatus(String status,int tradeid,int transferID, int version,Connection con) {
			// TODO Auto-generated method stub
			String sql = " update task set task_status = '2' where transferid = "+transferID+ "  and transferVersion = " +version + " and task_status not in ('2') ";
			PreparedStatement stmt = null;
			
					int j =0;
					 try {
						 con.setAutoCommit(false);
						 sql = sql;
						// System.out.println(sql);
						 stmt = con.prepareStatement(sql);
						j = stmt.executeUpdate();
				           con.commit();
				           commonUTIL.display("TaskSQL" , " updateCompletedTransferTask " + sql);
				           return j;
				         
					 } catch (Exception e) {
						 commonUTIL.displayError("TaskSQL", " updateCompletedTransferTask " + sql,e);
						 return j;
				           
				        }
				        finally {
				           try {
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							commonUTIL.displayError("TaskSQL", " updateCompletedTransferTask " + sql,e);
						}
				        }
				       
				}
	 public static int updateCompletedTransferTask(int transferID, int version,Connection con) {
			// TODO Auto-generated method stub
			String sql = " update task set task_status = '2' where transferid = "+transferID+ "  and transferVersion = " +version + " and task_status not in ('2') ";
			PreparedStatement stmt = null;
			
					int j =0;
					 try {
						 con.setAutoCommit(false);
						 sql = sql;
						// System.out.println(sql);
						 stmt = con.prepareStatement(sql);
						j = stmt.executeUpdate();
				           con.commit();
				           commonUTIL.display("TaskSQL" , " updateCompletedTransferTask " + sql);
				           return j;
				         
					 } catch (Exception e) {
						 commonUTIL.displayError("TaskSQL", " updateCompletedTransferTask " + sql,e);
						 return j;
				           
				        }
				        finally {
				           try {
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							commonUTIL.displayError("TaskSQL", " updateCompletedTransferTask " + sql,e);
						}
				        }
				       
				}
		public static boolean updateTaskStatus(int id, String status, Connection con) {
			// TODO Auto-generated method stub
			 PreparedStatement stmt = null;
		        String sql = "update task set task_status = '"+status+"' where id = " + id;
			 try {
				 int j = 1;
				 con.setAutoCommit(false);
				 sql = sql;
				 System.out.println(sql);
				 stmt = con.prepareStatement(sql);
				 stmt.execute();
		           con.commit();
		           commonUTIL.display("TaskSQL" , " updateTaskStatus " + sql);
			 } catch (Exception e) {
				 commonUTIL.displayError("TaskSQL",sql,e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("TaskSQL",sql,e);
				}
		        }
		        return true;
		}
		public static boolean updateTaskStatus(int id,int userID,String user_name, String status, Connection con) {
			// TODO Auto-generated method stub
			 PreparedStatement stmt = null;
		        String sql = "update task set task_status = '"+status+"' , user_id = " + userID + ", user_name = '" + user_name + "'  where id = " + id;
			 try {
				 int j = 1;
				 con.setAutoCommit(false);
				 sql = sql;
				 System.out.println(sql);
				 stmt = con.prepareStatement(sql);
				 stmt.execute();
		           con.commit();
		           commonUTIL.display("TaskSQL" , " updateTaskStatus " + sql);
			 } catch (Exception e) {
				 commonUTIL.displayError("TaskSQL",sql,e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("TaskSQL",sql,e);
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
			 commonUTIL.displayError("TaskSQL",SELECT_MAX,e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TaskSQL",SELECT_MAX,e);
			}
	        }
	        return j;
	 }
   
   protected static int insert(Task inserTask, Connection con ) {
			
	        PreparedStatement stmt = null;
	        int id = 0;
		 try {
			 con.setAutoCommit(false);

				//tradeId = selectMax(con) + 1;

				// System.out.println(con.getAutoCommit());
				//int j = 1;
				stmt = dsSQL.newPreparedStatement(con, INSERT);

				// stmt.setInt(1,tradeId);

	            stmt.setInt(1, inserTask.getTradeID());
	            stmt.setInt(2, inserTask.getProductID());
	            stmt.setString(3, inserTask.getType());
	            stmt.setString(4, inserTask.getAction());
	            stmt.setString(5, inserTask.getTaskDate());
	            stmt.setString(6, inserTask.getStatus());
	            stmt.setString(7, inserTask.getStatusDone());
	            stmt.setString(8, inserTask.getCurrency());
	            stmt.setInt(9, inserTask.getBookid());
	            stmt.setString(10, inserTask.getEvent_type());
	            stmt.setString(11, inserTask.getUser_name());
	            stmt.setInt(12, inserTask.getUserid());
	            stmt.setString(13, inserTask.getTaskstatus());
	            stmt.setString(14, inserTask.getProductType());
	            stmt.setString(15, inserTask.getWFType());
	            stmt.setInt(16, inserTask.getTransferID());
	            stmt.setInt(17, inserTask.getTransferVersionID());
	            stmt.setInt(18, inserTask.getTradeVersionID());
	            stmt.setInt(19, inserTask.getCpid());
	            stmt.setInt(20, inserTask.getNettedConfigID());
	            stmt.setInt(21, inserTask.getMessageID());
				if (stmt.executeUpdate() > 0) {

					con.commit();
					id = selectMax(con);
				 
				}
				
	            commonUTIL.display("TaskSQL",INSERT);
	            return id;
	            
		 } catch (Exception e) {
			 commonUTIL.displayError("TaskSQL",INSERT,e);
			 return 0;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TaskSQL","insert",e);
				return 0;
			}
	        }
	  
	 }
	 
	 protected static Collection select(int TaskID,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector Tasks = new Vector();
	        
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + TaskID);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	 Task task = new Task();
		    	 
	        	 task.setId(rs.getInt(1));
			        task.setTradeID(rs.getInt(2));
			        task.setProductID(rs.getInt(3));
			        task.setType(rs.getString(4));
			        task.setAction(rs.getString(5));
			        
			        task.setTaskDate(rs.getString(6));
			        task.setStatus(rs.getString(7));
			        task.setStatusDone(rs.getString(8));
			        task.setCurrency(rs.getString(9));
			        task.setBookid(rs.getInt(10));
			        task.setEvent_type(rs.getString(11));
			        task.setUser_name(rs.getString(12));
			        task.setUserid(rs.getInt(13));
			        task.setTaskstatus(rs.getString(14));
			        task.setProductType(rs.getString(15));
			        task.setWFType(rs.getString(16));
			        task.setTransferID(rs.getInt(17));
			        task.setTransferVersionID(rs.getInt(18));
			       		task.setTradeVersionID(rs.getInt(19))    ;    
			       		task.setCpid(rs.getInt(20)) ;
			       		task.setNettedConfigID(rs.getInt(21)) ;
			       		task.setMessageID(rs.getInt(22)) ;
			        Tasks.add(task);
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("TaskSQL",SELECTONE + TaskID,e);
			 return Tasks;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TaskSQL",SELECTONE + TaskID,e);
			}
	        }
	        return Tasks;
	 }

	 private static Collection selectOnWherecClauseReports(String sqlw, Connection con) {
			// TODO Auto-generated method stub
		  int j = 0;
	      PreparedStatement stmt = null;
	      Vector<Object> Tasks = new Vector();
	      String sql = sqlw;
	   try {
	    con.setAutoCommit(false);
	   
	     commonUTIL.display("TradeSQL", "selectOnWherecClauseReports == reports SQL " + sql);
	    stmt = dsSQL.newPreparedStatement(con, sql);
	    
	       ResultSet rs = stmt.executeQuery();
	       if(rs != null)  {
	    	   Tasks.add(rs);
	    	     TaskReportGenerator generateReport = new TaskReportGenerator();
	    	     Tasks = (Vector) generateReport.generateReportOnSQL(Tasks);
	       }
	   } catch (Exception e) {
		    commonUTIL.displayError("TradeSQL","selectOnWherecClauseReports " + sql,e);
		    return Tasks;
		        
		      }
		      finally {
		         try {
		    stmt.close();
		   } catch (SQLException e) {
		    // TODO Auto-generated catch block
		    commonUTIL.displayError("TradeSQL"," selectOnWherecClauseReports " + sql,e);
		   }
		      }
		      return Tasks;
			}
	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Tasks = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Task task = new Task();
	    	 
	    	  task.setId(rs.getInt(1));
		        task.setTradeID(rs.getInt(2));
		        task.setProductID(rs.getInt(3));
		        task.setType(rs.getString(4));
		        task.setAction(rs.getString(5));
		        
		        task.setTaskDate(rs.getString(6));
		        task.setStatus(rs.getString(7));
		        task.setStatusDone(rs.getString(8));
		        task.setCurrency(rs.getString(9));
		        task.setBookid(rs.getInt(10));
		        task.setEvent_type(rs.getString(11));
		        task.setUser_name(rs.getString(12));
		        task.setUserid(rs.getInt(13));
		        task.setTaskstatus(rs.getString(14));
		        task.setProductType(rs.getString(15));
		        task.setWFType(rs.getString(16));
		        task.setTransferID(rs.getInt(17));
		        task.setTransferVersionID(rs.getInt(18));
	       		task.setTradeVersionID(rs.getInt(19))    ;  
	    		task.setCpid(rs.getInt(20)) ;
	    		task.setNettedConfigID(rs.getInt(21)) ;
	    		task.setMessageID(rs.getInt(22)) ;
		        Tasks.add(task);
		     
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("TaskSQL",SELECTALL ,e);
			 return Tasks;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TaskSQL","SELECTALL",e);
			}
	     }
	     return Tasks;
	 }
	 
	 protected static Collection selectLE(int bookId,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Tasks = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 SELECTONE = SELECTONE + bookId;
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Task task = new Task();
	    	
	    	  task.setId(rs.getInt(1));
		        task.setTradeID(rs.getInt(2));
		        task.setProductID(rs.getInt(3));
		        task.setType(rs.getString(4));
		        task.setAction(rs.getString(5));
		        
		        task.setTaskDate(rs.getString(6));
		        task.setStatus(rs.getString(7));
		        task.setStatusDone(rs.getString(8));
		        task.setCurrency(rs.getString(9));
		        task.setBookid(rs.getInt(10));
		        task.setEvent_type(rs.getString(11));
		        task.setUser_name(rs.getString(12));
		        task.setUserid(rs.getInt(13));
		        task.setTaskstatus(rs.getString(14));
		        task.setProductType(rs.getString(15));
		        task.setWFType(rs.getString(16));
		        task.setTransferID(rs.getInt(17));
		        task.setTransferVersionID(rs.getInt(18));
	       		task.setTradeVersionID(rs.getInt(19))    ;  
	    		task.setCpid(rs.getInt(20)) ;
	    		task.setNettedConfigID(rs.getInt(21)) ;
	    		task.setMessageID(rs.getInt(22)) ;
		        Tasks.add(task);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("TaskSQL","selectTask",e);
			 return Tasks;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TaskSQL","selectMax",e);
			}
	     }
	     return Tasks;
	 }
	
	 protected static Collection selectALLLTasks(Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Tasks = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Task task = new Task();
	    	  task.setId(rs.getInt(1));
		        task.setTradeID(rs.getInt(2));
		        task.setProductID(rs.getInt(3));
		        task.setType(rs.getString(4));
		        task.setAction(rs.getString(5));
		        
		        task.setTaskDate(rs.getString(6));
		        task.setStatus(rs.getString(7));
		        task.setStatusDone(rs.getString(8));
		        task.setCurrency(rs.getString(9));
		        task.setBookid(rs.getInt(10));
		        task.setEvent_type(rs.getString(11));
		        task.setUser_name(rs.getString(12));
		        task.setUserid(rs.getInt(13));
		        task.setTaskstatus(rs.getString(14));
		        task.setProductType(rs.getString(15));
		        task.setWFType(rs.getString(16));
		        task.setTransferID(rs.getInt(17));
		        task.setTransferVersionID(rs.getInt(18));
	       		task.setTradeVersionID(rs.getInt(19))    ;  
	    		task.setCpid(rs.getInt(20)) ;
	    		task.setNettedConfigID(rs.getInt(21)) ;
	    		task.setMessageID(rs.getInt(22)) ;
		        Tasks.add(task);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("TaskSQL",SELECTALL,e);
			 return Tasks;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TaskSQL",SELECTALL,e);
			}
	     }
	     return Tasks;
	 }
	 public static Collection selectTaskWhere(String whereSQL,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Tasks = new Vector();
	     String sql = whereClause + whereSQL;
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con,sql );
	      
	      ResultSet rs = stmt.executeQuery();
	    //  id,tradeid,productId,type,action,taskDate,status,statusDone,currency,bookno,,event_type,user_name,user_id,task_status
	      while(rs.next()) {
	    	  Task task = new Task();
	    	  
	    	  task.setId(rs.getInt(1));
		        task.setTradeID(rs.getInt(2));
		        task.setProductID(rs.getInt(3));
		        task.setType(rs.getString(4));
		        task.setAction(rs.getString(5));
		        
		        task.setTaskDate(rs.getString(6));
		        task.setStatus(rs.getString(7));
		        task.setStatusDone(rs.getString(8));
		        task.setCurrency(rs.getString(9));
		        task.setBookid(rs.getInt(10));
		        task.setEvent_type(rs.getString(11));
		        task.setUser_name(rs.getString(12));
		        task.setUserid(rs.getInt(13));
		        task.setTaskstatus(rs.getString(14));
		        task.setProductType(rs.getString(15));
		        task.setWFType(rs.getString(16));
		        task.setTransferID(rs.getInt(17));
		        task.setTransferVersionID(rs.getInt(18));
	       		task.setTradeVersionID(rs.getInt(19))    ;  
	    		task.setCpid(rs.getInt(20)) ;
	    		task.setNettedConfigID(rs.getInt(21)) ;
	    		task.setMessageID(rs.getInt(22)) ;
		        Tasks.add(task);
	      
	      }
	      commonUTIL.display("TaskSQL"," selectTaskWhere " + whereClause + whereSQL);
		 } catch (Exception e) {
			 commonUTIL.displayError("TaskSQL",whereClause + whereSQL,e);
			 return Tasks;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TaskSQL",whereClause + whereSQL,e);
			}
	     }
	     return Tasks;
	 }

	 public static Task getTaskOnTransferIDandTransferVersion(Transfer oldTransfer,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Task task = new Task();
	     String sqlwhere = "  transferid = "+oldTransfer.getId() + " and tradeid = "+ oldTransfer.getTradeId() + " and  transferversion = " + oldTransfer.getVersion() + " and task_status not in ('2') ";
	     String sql = whereClause + sqlwhere;
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con,sql );
	      
	      ResultSet rs = stmt.executeQuery();
	    //  id,tradeid,productId,type,action,taskDate,status,statusDone,currency,bookno,,event_type,user_name,user_id,task_status
	      while(rs.next()) {
	    	  
	    	  
	    	  task.setId(rs.getInt(1));
		        task.setTradeID(rs.getInt(2));
		        task.setProductID(rs.getInt(3));
		        task.setType(rs.getString(4));
		        task.setAction(rs.getString(5));
		        
		        task.setTaskDate(rs.getString(6));
		        task.setStatus(rs.getString(7));
		        task.setStatusDone(rs.getString(8));
		        task.setCurrency(rs.getString(9));
		        task.setBookid(rs.getInt(10));
		        task.setEvent_type(rs.getString(11));
		        task.setUser_name(rs.getString(12));
		        task.setUserid(rs.getInt(13));
		        task.setTaskstatus(rs.getString(14));
		        task.setProductType(rs.getString(15));
		        task.setWFType(rs.getString(16));
		        task.setTransferID(rs.getInt(17));
		        task.setTransferVersionID(rs.getInt(18));
	       		task.setTradeVersionID(rs.getInt(19))    ;  
	    		task.setCpid(rs.getInt(20)) ;
	    		task.setNettedConfigID(rs.getInt(21)) ;
	    		task.setMessageID(rs.getInt(22)) ;
		       
	      
	      }
	      commonUTIL.display("TaskSQL"," getTaskOnTransferIDandTransferVersion  " +  sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("TaskSQL"," getTaskOnTransferIDandTransferVersion "  + sql,e);
			 return task;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TaskSQL"," getTaskOnTransferIDandTransferVersion "  + sql,e);
			}
	     }
	     return task;
	 }


	public static boolean checkLockOnTrade(Trade trade, Connection con) {
		// TODO Auto-generated method stub
		String checkT = "";
		String sql = "tradeid = " + trade.getId() + "  and  status = '"+ trade.getStatus() + "' and  task_status ='1' and user_id <> " + trade.getUserID();
		PreparedStatement stmt = null;
	    try {
			 con.setAutoCommit(false);
			 checkT = checkStatus + sql;
			 stmt = dsSQL.newPreparedStatement(con, checkT );
			 ResultSet rs = stmt.executeQuery();
			 commonUTIL.display("TaskSQL"," checkLockOnTrade " + checkT);
			 return rs.next();
		} catch (Exception e) {
			 commonUTIL.displayError("TaskSQL"," checkLockOnTrade " + checkT,e);
			 return false;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TaskSQL"," checkLockOnTrade " + checkT,e);
			}
	     }
	    
	      
	
	}




	




	
	 
}
