package dbSQL;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import dsEventProcessor.EventProcessor;
import dsEventProcessor.LimitEventProcessor;
import dsEventProcessor.MessageEventProcessor;
import dsEventProcessor.TradeEventProcessor;
import dsEventProcessor.TransferEventProcessor;

import util.commonUTIL;
import beans.Event;
import beans.Event;



public class EventSQL implements Serializable {

	final static private String tableName = "EventController";
	final static private String DELETE =
		"DELETE FROM " + tableName + "   where name =? ";
	final static private String INSERT =
		"INSERT into " + tableName + "(eventID,eventType,type,consumed,publish,objectID,objectVersion,comments,processName,eventDate,eventTime,subscriberlist) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	final static private String UPDATE =
		"UPDATE " + tableName + " set type=?,typename=?,typevalue=?   where userid = ? ";
	final static private String SELECT_MAX =
			  "SELECT EVENT_SEQ.NEXTVAL  DESC_ID FROM  dual";
	final static private String SELECTALL =
		"SELECT eventID,eventType,type,consumed,publish,objectID,objectVersion,comments,processName,eventDate,eventTime,subscriberlist,adminclearMark " + tableName + " ";
	final static private String SELECT =
		"SELECT eventID,eventType,type,consumed,publish,objectID,objectVersion,comments,processName,eventDate,eventTime,subscriberlist,adminclearMark    FROM " + tableName + " where userid =  ? and type = ?";
	 static private String SELECTONE =
		"SELECT eventID,eventType,type,consumed,publish,objectID,objectVersion,comments,processName,eventDate,eventTime,subscriberlist,adminclearMark   FROM " + tableName + " where   userid =  ?  order by type ";
	 static private String CHECKINSERT  =
				"SELECT  eventID,eventType,type,consumed,publish,objectID ,objectVersion,comments,processName,eventDate,eventTime,subscriberlist,adminclearMark   FROM " + tableName + " where userid =   ? and type = ? and typename = ?  order by type ";
	 static private String SELECTWHERE  =
				"SELECT eventID,eventType,type,consumed,publish,objectID,objectVersion,comments,processName,eventDate,eventTime,subscriberlist,adminclearMark   FROM " + tableName + " where " ;
	 
	 private static String getUpdateSQL(EventProcessor evnt) {
		 String updateSQL  = " update EVENTCONTROLLER set subscriberlist =  subscriberlist || '" + evnt.getSubscribeList() + ";' where eventid = "+evnt.getEventid();
		 
		
		 return updateSQL;
					
	 }
	 private static String getAdminUpdateSQL(EventProcessor evnt) {
		 String updateSQL  = " update EVENTCONTROLLER set adminclearMark =  adminclearMark || '" + evnt.getAdminClearedEventType() + ";' where eventid = "+evnt.getEventid();
		 
		
		 return updateSQL;
					
	 }
	 public static EventProcessor save(EventProcessor insertEvent, Connection con) {
		 try {
             return insert(insertEvent, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("EventSQL","save",e);
        	 return null;
         }
	 }
	 public static boolean update(EventProcessor updateEvent, Connection con) {
		 try {
             return edit(updateEvent, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("EventSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(Event deleteEvent, Connection con) {
		 try {
             return remove(deleteEvent, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("EventSQL","update",e);
        	 return false;
         }
	 }
	 
	 protected static boolean remove(Event deleteEvent, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			String  sql = " DELETE FROM Event   where id =" + deleteEvent.getEventID() ;
			    stmt = con.prepareStatement(sql);
			            stmt.execute(sql);
			  
			            
			             
			             con.commit();
			             commonUTIL.display("EventSQL ::  remove", sql);
			    
		 } catch (Exception e) {
			 commonUTIL.displayError("EventSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("EventSQL ","remove",e);
			}
	        }
	        return true;
	 }
	 public static Collection selectEvent(EventProcessor favorities, Connection con) {
		 try {
             return  select(favorities, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("EventSQL ","select",e);
        	 return null;
         }
	 }
	 public static Collection selectALL(Connection con) {
		 try {
             return select(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("EventSQL ","selectALL",e);
        	 return null;
         }
	 }
	 
	 protected static  boolean edit(EventProcessor updateEvent, Connection con ) {
		 
	        PreparedStatement stmt = null;
	        String sql = "";
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 if(updateEvent.isClearedByAdmin())  {
			  sql = getAdminUpdateSQL(updateEvent);
			 } else {
				 sql = getUpdateSQL(updateEvent);
			 }
			 stmt = dsSQL.newPreparedStatement(con, sql);
	            
			 
	           
	            
	       //     stmt.setString(1, updateEvent.getType());
	         //   stmt.setString(2, updateEvent.getTypeName());
	           // stmt.setString(3, updateEvent.getTypeValue());
	            //stmt.setInt(4, updateEvent.getUserId());
	            
	            
	            stmt.executeUpdate();
	            con.commit();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("EventSQL ","edit " + sql,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("EventSQL",UPDATE,e);
			}
	        }
	        return true;
	 }
	
	 protected static EventProcessor insert(EventProcessor inserEvent, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 con.setAutoCommit(false);
		
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT);
			// eventID, eventType,type,tradeID,transferID,consumedFlag,sqlType	
			int id = selectMax(con);
			inserEvent.setEventid(id);
			 stmt.setInt(1,id);
			  stmt.setString(2, inserEvent.getEventType());
	            stmt.setString(3,inserEvent.getType());
	          
	            if(!inserEvent.isConsumed())
	               stmt.setString(4,"False");
	            else
	            	 stmt.setString(4,"True");
	            
	            if(!inserEvent.isPublish())
		               stmt.setString(5,"False");
		            else
		            	 stmt.setString(5,"True");
	            
	            stmt.setInt(6, inserEvent.getObjectID());
	            stmt.setInt(7,  inserEvent.getObjectVersionID());
	           
	            stmt.setString(8,inserEvent.getComments());
	            stmt.setString(9,inserEvent.getProcessName());
	            stmt.setString(10,inserEvent.getOccurrenceDate());
	            stmt.setString(11,inserEvent.getOccurrenceTime());
	           stmt.setString(12,"NONE");
	           // stmt.setString(11,inserEvent.getOccurrenceDate());
	            commonUTIL.display(" EventSQL insert ",INSERT);
			 
	            stmt.executeUpdate();
	            con.commit();
			// }	
		 } catch (Exception e) {
			 commonUTIL.displayError("EventSQL",INSERT,e);
			 return null;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("EventSQL "," insert",e);
			}
	        }
	        return inserEvent;
	 }
	 
	 protected static Collection select(EventProcessor event,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	       Vector<EventProcessor> values = new Vector<EventProcessor>();
	       String sql  = "";
		 try {
			 sql = SELECTONE + "'" + event.getEventid() + "'";
			con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con,sql) ;
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	// eventID, eventType,type,tradeID,transferID,consumedFlag,sqlType	
	      //  	 eventID,eventType,type,consumed,publish,objectID,objectVersion,comments,processName,eventDate,eventTime 
	        	 EventProcessor evt = new EventProcessor();
	        	 evt.setEventid(rs.getInt(1));
	        	 evt.setEventType(rs.getString(2));
	        	 evt.setType(rs.getString(3));
	        	 if(rs.getString(4).equalsIgnoreCase("True")) 
	        	 evt.setPublish(true);
	        	 if(rs.getString(5).equalsIgnoreCase("True")) 
		        	 evt.setConsumed(true);
	        	 evt.setObjectID(rs.getInt(6));
	        	 evt.setObjectVersionID(rs.getInt(7));
	        	 evt.setComments(rs.getString(8));
	        	    	 evt.setProcessName(rs.getString(9));
	        	 evt.setOccrrenceDate(rs.getString(10));
	        	 evt.setOccurrenceTime(rs.getString(11));
	        	 evt.setSubscribableList(rs.getString(12));
	        values.add(evt);
	        
	      
	         }
	         
	         
		 } catch (Exception e) {
			 commonUTIL.displayError("EventSQL",sql,e);
			 return values;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("EventSQL", sql,e);
			}
	        }
	        return values;
	 }

public static Collection selectWhere(String sqlw,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	       Vector values = new Vector();
	       String sql  = "";
		 try {
			 sql = SELECTWHERE + sqlw ;
			con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con,sql) ;
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	
	        	 EventProcessor evt = null; //new EventProcessor();
	        	 if(rs.getString(3).equalsIgnoreCase("Trade")) {
	        		 evt = new TradeEventProcessor();
	        	 }
	        	 if(rs.getString(3).equalsIgnoreCase("Transfer")) {
	        		 evt = new TransferEventProcessor();
	        	 }
	        	 if(rs.getString(3).equalsIgnoreCase("Task")) {
	        		 evt = new TransferEventProcessor();
	        	 }
	        	 if(rs.getString(3).equalsIgnoreCase("Message")) {
	        		 evt = new MessageEventProcessor();
	        	 }
	        	 if(rs.getString(3).equalsIgnoreCase("Limit")) {
	        		 evt = new LimitEventProcessor();
	        	 }
	        	 evt.setEventid(rs.getInt(1));
	        	 evt.setEventType(rs.getString(2));
	        	 evt.setType(rs.getString(3));
	        	 if(rs.getString(4).equalsIgnoreCase("True")) 
	        	 evt.setPublish(true);
	        	 if(rs.getString(5).equalsIgnoreCase("True")) 
		        	 evt.setConsumed(true);
	        	 evt.setObjectID(rs.getInt(6));
	        	 evt.setObjectVersionID(rs.getInt(7));
	        	 evt.setComments(rs.getString(8));
	        	    	 evt.setProcessName(rs.getString(9));
	        	 evt.setOccrrenceDate(rs.getString(10));
	        	 evt.setOccurrenceTime(rs.getString(11));
	        	 evt.setSubscribableList(rs.getString(12));
	        	 
	        values.add(evt);
	        
	      
	       
	         
	         }
	         commonUTIL.display("EventSQL selectWhere ",sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("EventSQL selectWhere ",sql,e);
			 return values;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("EventSQL", sql,e);
			}
	        }
	        return values;
	 }

	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Events = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	 
	    	
	    	  EventProcessor evt = new EventProcessor();
	        	 evt.setEventid(rs.getInt(1));
	        	 evt.setEventType(rs.getString(2));
	        	 evt.setType(rs.getString(3));
	        	 if(rs.getString(4).equalsIgnoreCase("True")) 
	        	 evt.setPublish(true);
	        	 if(rs.getString(5).equalsIgnoreCase("True")) 
		        	 evt.setConsumed(true);
	        	 evt.setObjectID(rs.getInt(6));
	        	 evt.setObjectVersionID(rs.getInt(7));
	        	 evt.setComments(rs.getString(8));
	        	    	 evt.setProcessName(rs.getString(9));
	        	 evt.setOccrrenceDate(rs.getString(10));
	        	 evt.setOccurrenceTime(rs.getString(11));
	        	 evt.setSubscribableList(rs.getString(12));
	        	 Events.add(evt);
	        
	        
	      
	      }  commonUTIL.display("EventSQL ",SELECTALL);
	      
		 } catch (Exception e) {
			 commonUTIL.displayError("EventSQL",SELECTALL,e);
			 return Events;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("EventSQL ",SELECTALL,e);
			}
	     }
	     return Events;
	 }
	 protected static int selectMax(Connection con ) {
		  
		   int j = 0;
		         PreparedStatement stmt = null;
		   try {
		    con.setAutoCommit(false);
//		    System.out.println(con.getAutoCommit());
		    stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);
		    commonUTIL.display("EventSQL ::  selectMax", SELECT_MAX);
		          ResultSet rs = stmt.executeQuery();
		          while(rs.next())
		          j = rs.getInt("DESC_ID");
		   
		   } catch (Exception e) {
		    commonUTIL.displayError("EventSQL",SELECT_MAX,e);
		    return j;
		           
		         }
		         finally {
		            try {
		    stmt.close();
		   } catch (SQLException e) {
		    // TODO Auto-generated catch block
		    commonUTIL.displayError("EventSQL","selectMax",e);
		   }
		         }
		         return j;
		  }
		     
	public static Collection getEventName(Connection con) {
		// TODO Auto-generated method stub
		int j = 0;
	     PreparedStatement stmt = null;
	     Vector Events = new Vector();
	     String sql = "select value from Event where name = "+ "'InitialData'";
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Event Event = new Event();
	    	//  Event.setName(rs.getString(1));
		       
		        Events.add(Event);
	      
	      }
	      
	      commonUTIL.display("getEventName",sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("getEventName",sql,e);
			 return Events;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("getEventName",sql,e);
			}
	     }
	     return Events;
	 
	}
	




}
