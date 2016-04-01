package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.AccTriggerEvent;
public class AccEventTriggerSQL {
	
	
	final static private String DELETE_FROM_AccEvtTrigger =
			"DELETE FROM accevtTrigger where acceventConfigID =? and triggerEventname =? ";
		final static private String INSERT_FROM_AccTriggerEvent =
			"INSERT into accevtTrigger(acceventConfigID,triggerEventname) values(?,?)";
		final static private String UPDATE_FROM_AccTriggerEvent =
			"UPDATE accevtTrigger set le_id=?,AccTriggerEvent_name=? where AccTriggerEventno = ? ";
		final static private String SELECT_MAX =
			"SELECT MAX(id) DESC_ID FROM accevtTrigger ";
		final static private String SELECTALL =
			"SELECT acceventConfigID,accEventConfigID,triggerEventname FROM accevtTrigger order by acceventConfigID";
		final static private String SELECT =
			"SELECT acceventConfigID,accEventConfigID,triggerEventname FROM AccTriggerEvent where acceventConfigID =  ?";
		 static private String SELECTONE =
			"SELECT triggerEventname  FROM accevtTrigger where accEventConfigID =  " ;
		final static private String SELECTACCEVNETCONFIG =
					"SELECT acceventConfigID,triggerEventname  FROM accevtTrigger where accEventConfigID =  " ;
		
		final static private String SELECTACCEVNETCONFIGWHERE =
				"SELECT acceventConfigID,triggerEventname  FROM accevtTrigger where  " ;
		 
		 private static String getUpdateSQL(AccTriggerEvent  accTriggerEvent) {
		      String updateSQL = "UPDATE accevtTrigger  set " +
		      		" acceventConfigID= " + accTriggerEvent.getAccEventConfigID() + 
		      		" ,triggerEventname= '" + accTriggerEvent.getTriggerEvent() + 	      		
		      				"'  where acceventConfigID= " + accTriggerEvent.getId();
		      return updateSQL;
		     }
		 
		 
		 public static int save(AccTriggerEvent insertAccTriggerEvent, Connection con) {
			 try {
	             return insert(insertAccTriggerEvent, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccTriggerEventSQL","save",e);
	        	 return -1;
	         }
		 }
		 public static boolean update(AccTriggerEvent updateAccTriggerEvent, Connection con) {
			 try {
	             return edit(updateAccTriggerEvent, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccTriggerEventSQL","update",e);
	        	 return false;
	         }
		 }
		 
		 public static boolean delete(AccTriggerEvent deleteAccTriggerEvent, Connection con) {
			 try {
	             return remove(deleteAccTriggerEvent, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccTriggerEventSQL","update",e);
	        	 return false;
	         }
		 }
		 public static Collection selectTriggerEvent(int AccTriggerEventId, Connection con) {
			 try {
	             return  selectAccTriggerEvent(AccTriggerEventId, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccTriggerEventSQL","select",e);
	        	 return null;
	         }
		 }
		 public static Collection selectALL(Connection con) {
			 try {
	             return select(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccTriggerEventSQL","selectALL",e);
	        	 return null;
	         }
		 }
		 
		 public static int selectMaxID(Connection con) {
			 try {
	             return selectMax(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccTriggerEventSQL","selectMaxID",e);
	        	 return 0;
	         }
		 }
		 
		 protected static  boolean edit(AccTriggerEvent updateAccTriggerEvent, Connection con ) {
			 
		        PreparedStatement stmt = null;
		        String sql = getUpdateSQL(updateAccTriggerEvent);
			 try {
				 con.setAutoCommit(false);
				 int j = 1;
				 stmt = dsSQL.newPreparedStatement(con, sql);
		            stmt.executeUpdate(sql);
				 con.commit();
				 commonUTIL.display("AccTriggerEventSQL ::  edit", sql);
			 } catch (Exception e) {
				 commonUTIL.displayError("AccTriggerEventSQL","edit",e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccTriggerEventSQL","edit",e);
				}
		        }
		        return true;
		 }
		
		protected static boolean remove(AccTriggerEvent deleteAccTriggerEvent, Connection con ) {
		
		        PreparedStatement stmt = null;
			 try {
				 int j = 1;
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_AccEvtTrigger);
		            stmt.setInt(1, deleteAccTriggerEvent.getAccEventConfigID());
		            stmt.setString(2, deleteAccTriggerEvent.getTriggerEvent());
		           
		            stmt.executeUpdate();
				 con.commit();
			 } catch (Exception e) {
				 commonUTIL.displayError("AccTriggerEventSQL","remove",e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccTriggerEventSQL","remove",e);
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
				 commonUTIL.displayError("AccTriggerEventSQL",SELECT_MAX,e);
				 return j;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccTriggerEventSQL",SELECT_MAX,e);
				}
		        }
		        return j;
		 }
		 
	

	 protected static int insert(AccTriggerEvent inserAccTriggerEvent, Connection con ) {
			
	        PreparedStatement stmt = null;
	        int id  = 0;
		 try {
			 con.setAutoCommit(false);
			//  id = selectMax(con);
			// id = id + 1;
			// int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_AccTriggerEvent);
			// stmt.setInt(1,id);
			
	            stmt.setString(2, inserAccTriggerEvent.getTriggerEvent());
	            stmt.setInt(1,inserAccTriggerEvent.getAccEventConfigID() );
	            
	            
	            stmt.executeUpdate();
	            con.commit();
	            commonUTIL.display("AccTriggerEventSQL"," INSERT_FROM_AccTriggerEvent " + INSERT_FROM_AccTriggerEvent);
	            return id;
		 } catch (Exception e) {
			 commonUTIL.displayError("AccTriggerEventSQL",INSERT_FROM_AccTriggerEvent,e);
			 return -1;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccTriggerEventSQL",INSERT_FROM_AccTriggerEvent,e);
			}
	        }
	     
	 }
	 
	 protected static AccTriggerEvent select(int AccTriggerEventIn,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector AccTriggerEvents = new Vector();
	        AccTriggerEvent AccTriggerEvent = new AccTriggerEvent();
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + AccTriggerEventIn);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	
	        AccTriggerEvent.setId(rs.getInt(1));
	        AccTriggerEvent.setTriggerEvent(rs.getString(3));
	        AccTriggerEvent.setAccEventConfigID(rs.getInt(2));
	      
	       
	        
	      
	       return AccTriggerEvent;
	         
	         }
	         
		 } catch (Exception e) {
			 commonUTIL.displayError("AccTriggerEventSQL","select",e);
			 return AccTriggerEvent;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccTriggerEventSQL","selectMax",e);
			}
	        }
	        return AccTriggerEvent;
	 }

	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector AccTriggerEvents = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	   
	     AccTriggerEvent AccTriggerEvent = new AccTriggerEvent();
	     AccTriggerEvent.setId(rs.getInt(1));
	        AccTriggerEvent.setTriggerEvent(rs.getString(3));
	        AccTriggerEvent.setAccEventConfigID(rs.getInt(2));
	        AccTriggerEvents.add(AccTriggerEvent);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("AccTriggerEventSQL",SELECTALL,e);
			 return AccTriggerEvents;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccTriggerEventSQL",SELECTALL,e);
			}
	     }
	     return AccTriggerEvents;
	 }
	 
	 public static Collection selectAccTriggerEventOnWhere(String where,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     String sql = SELECTACCEVNETCONFIGWHERE + where;
	     Vector<String> accTriggerEvents = new Vector<String>();
		 try {
			 stmt = dsSQL.newPreparedStatement(con, sql );
		      
		      ResultSet rs = stmt.executeQuery();
		      
		      while(rs.next()) {
		    	
		    	
		    	
		    	  accTriggerEvents.addElement(rs.getString(2));
			    	
		      
		      }
		      commonUTIL.display("AccTriggerEventSQL","selectAccTriggerEventOnWhere " + sql);
			 } catch (Exception e) {
				 commonUTIL.displayError("AccTriggerEventSQL","selectAccTriggerEventOnWhere",e);
				 return accTriggerEvents;
		        
		     }
		     finally {
		        try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccTriggerEventSQL","selectAccTriggerEventOnWhere",e);
				}
		     }
		     return accTriggerEvents;
		 }
	 protected static Vector<AccTriggerEvent> selectAccTriggerEvent(int AccTriggerEventId,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector accTriggerEvents = new Vector(); 
	  
	     String sql = SELECTONE;
		 try {
			 
			 con.setAutoCommit(false);
			 sql = sql + AccTriggerEventId;
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  AccTriggerEvent accTriggerEvent = new AccTriggerEvent();
	    	 // accTriggerEvent.setId(rs.getInt(1));
	    	 // accTriggerEvent.setTriggerEvent(rs.getString(1));
	    	 
	    	  accTriggerEvents.add(rs.getString(1));
	      
	      }
	      commonUTIL.display("AccTriggerEventSQL","selectAccTriggerEvent " +sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("AccTriggerEventSQL","selectAccTriggerEvent",e);
			 return accTriggerEvents;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccTriggerEventSQL","selectAccTriggerEvent",e);
			}
	     }
	     return accTriggerEvents;
	 }

}
