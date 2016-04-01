package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.AccEventLink;
import beans.Account;

public class AccEventLinkSQL {
	
	
	
	
	
	final static private String DELETE_FROM_ACCEVENTLINK =
			"DELETE FROM ACCEVENTLINK where id =? ";
		final static private String INSERT_FROM_ACCEVENTLINK =
			"INSERT into ACCEVENTLINK(id,ruleID,debitAccount,creaditAccount,plusmius,accEvent,debitaccType,creaditaccType) values(?,?,?,?,?,?,?,?)";
		final static private String UPDATE_FROM_ACCEVENTLINK =
			"UPDATE ACCEVENTLINK set le_id=?,ACCEVENTLINK_name=? where ACCEVENTLINKno = ? ";
		final static private String SELECT_MAX =
			"SELECT MAX(id) DESC_ID FROM ACCEVENTLINK ";
		final static private String SELECTALL =
			"SELECT id,ruleID,debitAccount,creaditAccount,plusmius,accEvent,debitaccType,creaditaccType FROM ACCEVENTLINK order by id";
		final static private String SELECTWHERE =
			"SELECT id,ruleID,debitAccount,creaditAccount,plusmius,accEvent,debitaccType,creaditaccType FROM ACCEVENTLINK where    ";
		 static private String SELECTONE =
			"SELECT id,ruleID,debitAccount,creaditAccount,plusmius,accEvent,debitaccType,creaditaccType FROM ACCEVENTLINK where id  =  " ;
		 
		 
		 private static String getUpdateSQL(AccEventLink accEventLink ) {
		      String updateSQL = "UPDATE ACCEVENTLINK  set " +
					" ruleID= '" + accEventLink.getRuleID()+ 	
					" ,debitAccount= '" + accEventLink.getDebitaccount() + 	
					" ,creaditAccount= '" + accEventLink.getCreditaccount() + 	
					" ,plusmius= '" +accEventLink.getPlusmius() + 	
					" ,accEvent= '" + accEventLink.getAccEvent().trim()+ 	
					"',debitaccType= '" + accEventLink.getDebitaccType().trim() + 	
		      		"' ,creaditaccType= " + accEventLink.getCreaditaccType().trim()    + 
		      		"' where id= " + accEventLink.getId();
		      return updateSQL;
		     }
		 

		 
		 public static int save(AccEventLink insertAccEventLink, Connection con) {
			 try {
	             return insert(insertAccEventLink, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccEventLinkSQL","save",e);
	        	 return -1;
	         }
		 }
		 public static boolean update(AccEventLink updateAccEventLink, Connection con) {
			 try {
	             return edit(updateAccEventLink, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccEventLinkSQL","update",e);
	        	 return false;
	         }
		 }
		 
		 public static boolean delete(AccEventLink deleteAccEventLink, Connection con) {
			 try {
	             return remove(deleteAccEventLink, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccEventLinkSQL","update",e);
	        	 return false;
	         }
		 }
		 public static AccEventLink selectAccEventLink(int AccEventLinkId, Connection con) {
			 try {
	             return  select(AccEventLinkId, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccEventLinkSQL","select",e);
	        	 return null;
	         }
		 }
		 public static Collection selectALL(Connection con) {
			 try {
	             return select(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccEventLinkSQL","selectALL",e);
	        	 return null;
	         }
		 }
		 
		 private static int selectMaxID(Connection con) {
			 try {
	             return selectMax(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccEventLinkSQL","selectMaxID",e);
	        	 return 0;
	         }
		 }
		 
		 protected static  boolean edit(AccEventLink updateAccEventLink, Connection con ) {
			 
		        PreparedStatement stmt = null;
		        String sql = getUpdateSQL(updateAccEventLink);
			 try {
				 con.setAutoCommit(false);
				 int j = 1;
				 stmt = dsSQL.newPreparedStatement(con, sql);
		            stmt.executeUpdate(sql);
				 con.commit();
				 commonUTIL.display("AccEventLinkSQL ::  edit", sql);
			 } catch (Exception e) {
				 commonUTIL.displayError("AccEventLinkSQL","edit " +  sql,e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccEventLinkSQL","edit",e);
				}
		        }
		        return true;
		 }
		
		protected static boolean remove(AccEventLink deleteAccEventLink, Connection con ) {
		
		        PreparedStatement stmt = null;
		       String deletSQL = " delete from ACCEVENTLINK where ruleid = " +  deleteAccEventLink.getRuleID() + " and debitaccount = " + deleteAccEventLink.getDebitaccount()  + " and creaditaccount = "+ deleteAccEventLink.getCreditaccount() + " and plusmius = "+ deleteAccEventLink.getPlusmius() + " and debitacctype = '"+deleteAccEventLink.getDebitaccType() + "'  and creaditacctype='"+deleteAccEventLink.getCreaditaccType()+"'";
			 try {
				 int j = 1;
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con,deletSQL);
		         //   stmt.setInt(j++, deleteAccEventLink.getId());
		           
		            stmt.executeUpdate(deletSQL);
				 con.commit();
			 } catch (Exception e) {
				 commonUTIL.displayError("AccEventLinkSQL","remove " + deletSQL,e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccEventLinkSQL","remove",e);
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
				 commonUTIL.displayError("AccEventLinkSQL",SELECT_MAX,e);
				 return j;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccEventLinkSQL",SELECT_MAX,e);
				}
		        }
		        return j;
		 }
	
	 protected static int insert(AccEventLink insertAccEventLink, Connection con ) {
			
	        PreparedStatement stmt = null;
	        int id =0;
		 try {
			 con.setAutoCommit(false);
			 id = selectMax(con);
			 id = id +1;
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_ACCEVENTLINK);
			 stmt.setInt(1,id);
			 
			 stmt.setInt(2,insertAccEventLink.getRuleID());
			 stmt.setInt(3,insertAccEventLink.getDebitaccount());
			 stmt.setInt(4,insertAccEventLink.getCreditaccount());
	            stmt.setInt(5, insertAccEventLink.getPlusmius());
	            stmt.setString(6,insertAccEventLink.getAccEvent() );
	            stmt.setString(7, insertAccEventLink.getDebitaccType());
	            stmt.setString(8,insertAccEventLink.getCreaditaccType());
	          
	            
	            stmt.executeUpdate();
	            con.commit();
	            commonUTIL.display("AccEventLinkSQL",INSERT_FROM_ACCEVENTLINK);
		 } catch (Exception e) {
			 commonUTIL.displayError("AccEventLinkSQL",INSERT_FROM_ACCEVENTLINK,e);
			 return -1;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccEventLinkSQL",INSERT_FROM_ACCEVENTLINK,e);
			}
	        }
	        return id;
	 }
	 
	 

	 protected static AccEventLink select(int accEventLinkID,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        AccEventLink accEventLink = new AccEventLink();
	        String sql = SELECTONE;
	        sql = sql + accEventLinkID;
	    
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql);
	         
	         ResultSet rs = stmt.executeQuery();
	      
	         while(rs.next()) {
	        	
	       
	        	 accEventLink.setId(rs.getInt(1));
	        	 accEventLink.setRuleID(rs.getInt(2));
	        	 accEventLink.setDebitaccount(rs.getInt(3));
	        
	        	 accEventLink.setCreditaccount(rs.getInt(4));
	        	 accEventLink.setPlusmius(rs.getInt(5));
	        
	        	 accEventLink.setAccEvent(rs.getString(6));
	        	 accEventLink.setDebitaccType(rs.getString(7));
	        	 accEventLink.setCreaditaccType(rs.getString(8));
	       
	        
	       
	        
	      
	       
	         
	         }
	         commonUTIL.display("AccEventLinksSQL"," select " + sql);
	         return accEventLink;
		 } catch (Exception e) {
			 commonUTIL.displayError("AccEventLinksSQL","select    " + sql ,e);
			 return accEventLink;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccEventLinksSQL","select " + sql ,e);
			}
	        }
	    
	 }
		 


	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector accEventLinkData = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	   
	    	  AccEventLink accEventLink = new AccEventLink();
	    	  accEventLink.setId(rs.getInt(1));
	        	 accEventLink.setRuleID(rs.getInt(2));
	        	 accEventLink.setDebitaccount(rs.getInt(3));
	        
	        	 accEventLink.setCreditaccount(rs.getInt(4));
	        	 accEventLink.setPlusmius(rs.getInt(5));
	        
	        	 accEventLink.setAccEvent(rs.getString(6));
	        	 accEventLink.setDebitaccType(rs.getString(7));
	        	 accEventLink.setCreaditaccType(rs.getString(8));
		        
	        	 accEventLinkData.addElement(accEventLink);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("AccEventLinkSQL",SELECTALL,e);
			 return accEventLinkData;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccEventLinkSQL",SELECTALL,e);
			}
	     }
		return accEventLinkData;
	   
	 }
	 public static Collection selectWhere(String where,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector AccEventLinks = new Vector();
	     String sql = SELECTWHERE;
	  //   sql = sql + where;
		 try {
			 con.setAutoCommit(false);
			sql = sql  + where;
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  AccEventLink accEventLink = new AccEventLink();
	    	  accEventLink.setId(rs.getInt(1));
	        	 accEventLink.setRuleID(rs.getInt(2));
	        	 accEventLink.setDebitaccount(rs.getInt(3));
	        
	        	 accEventLink.setCreditaccount(rs.getInt(4));
	        	 accEventLink.setPlusmius(rs.getInt(5));
	        
	        	 accEventLink.setAccEvent(rs.getString(6));
	        	 accEventLink.setDebitaccType(rs.getString(7));
	        	 accEventLink.setCreaditaccType(rs.getString(8));
		        
	        	 AccEventLinks.addElement(accEventLink);
	      
	      }
	      commonUTIL.display("AccEventLinkSQL","selectWhere "+sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("AccEventLinkSQL","selectWhere ",e);
			 return AccEventLinks;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccEventLinkSQL","selectWhere",e);
			}
	     }
		return AccEventLinks;
	    
	 }



	public static Collection selectAccEventLinkonRule(int ruleId,
			Connection conn) {
		// TODO Auto-generated method stub
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector AccEventLinks = new Vector();
	     String sql = " SELECT ID,RULEID,DEBITACCOUNT,CREADITACCOUNT,PLUSMIUS,ACCEVENT,DEBITACCTYPE,CREADITACCTYPE FROM ACCEVENTLINK  where ruleid =" + ruleId;
	  //   sql = sql + where;
		 try {
			 conn.setAutoCommit(false);
			sql = sql  + " order by accevent,plusmius ";
			 stmt = dsSQL.newPreparedStatement(conn, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  AccEventLink accEventLink = new AccEventLink();
	    	  	accEventLink.setId(rs.getInt(1));
	        	 accEventLink.setRuleID(rs.getInt(2));
	        	 accEventLink.setDebitaccount(rs.getInt(3));
	        
	        	 accEventLink.setCreditaccount(rs.getInt(4));
	        	 accEventLink.setPlusmius(rs.getInt(5));
	        
	        	 accEventLink.setAccEvent(rs.getString(6));
	        	 accEventLink.setDebitaccType(rs.getString(7));
	        	 accEventLink.setCreaditaccType(rs.getString(8));
		        
	        	 AccEventLinks.addElement(accEventLink);
	      
	      }
	      commonUTIL.display("AccEventLinkSQL","selectAccEventLinkonRule "+sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("AccEventLinkSQL","selectAccEventLinkonRule ",e);
			 return AccEventLinks;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccEventLinkSQL","selectAccEventLinkonRule",e);
			}
	     }
		return AccEventLinks;
	    
	 }
		 

}
