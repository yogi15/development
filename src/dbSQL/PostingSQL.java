package dbSQL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;


import beans.Posting;
import util.commonUTIL;

public class PostingSQL {
	
	final static private String tableName = "posting";
	final static private String DELETE =
		"DELETE FROM " + tableName + "   where id =? ";
	final static private String INSERT =
		"INSERT into " + tableName + "(id,tradeID,transferId,sdiId,creditAmount,debitAmount,accEventType,linkId,eventType,debitAccId,creditAccId,ruleName,currency,type,status,BookingDate,CreationDate,EffectiveDate,accEventConfigID,linkTo) values(POSTING_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	final static private String UPDATE =
		"UPDATE " + tableName + " set tradeID=?,transferId=?,sdiId=?,creditAmount=?,debitAmount=?,  where id = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(id) DESC_ID FROM " + tableName + " ";
	final static private String SELECTALL =
		"SELECT id,tradeID,transferId,sdiId,creditAmount,debitAmount,accEventType,linkId,eventType,debitAccId,creditAccId,ruleName,currency,type,status,BookingDate,CreationDate,EffectiveDate,accEventConfigID,linkTO  FROM " + tableName + " ";
	final static private String SELECT =
		"SELECT id,tradeID,transferId,sdiId,creditAmount,debitAmount,accEventType,linkId,eventType,debitAccId,creditAccId,ruleName,currency,type,status,BookingDate,CreationDate,EffectiveDate,accEventConfigID,linkTO   FROM " + tableName + " where id =  ?";
	 static private String SELECTONE =
		"SELECT id,tradeID,transferId,sdiId,creditAmount,debitAmount,accEventType,linkId,eventType,debitAccId,creditAccId,ruleName,currency,type,status,BookingDate,CreationDate,EffectiveDate,accEventConfigID,linkTO  FROM " + tableName + " where id  ";
	 static private String SELECTWHERE =
				"SELECT id,tradeID,transferId,sdiId,creditAmount,debitAmount,accEventType,linkId,eventType,debitAccId,creditAccId,ruleName,currency,type,status,BookingDate,CreationDate,EffectiveDate,accEventConfigID,linkTO  FROM " + tableName + " where  ";
	
	 private static String getUpdateSQL(int linkid,int postingID) {
	      String updateSQL = "UPDATE posting  set " +
	    		    " linkTo= " + linkid + 
		      		
		      		"  where id= " + postingID;
	      return updateSQL;
	     }
	 
	 
	 private static String getUpdateSQL(Posting posting) {
	      String updateSQL = "UPDATE posting  set " +
	    		    " tradeID= " + posting.getTradeID() + 
		      		" ,transferId= " + posting.getTransferId() + 
		      		" ,sdiId= " + posting.getSdiId() + 
		      		" ,creditAmount= " + posting.getCreditAmount() + 
		      		" ,debitAmount= " + posting.getDebitAmount() + 
		      		" ,accEventType= '" + posting.getAccEventType() + 
		      	    "' ,linkId= " + posting.getLinkId() + 
		      		" ,eventType= '" + posting.getEventType() + 
		      		 "' ,debitAccId= " + posting.getDebitAccId() + 
		      		" ,creditAccId= " + posting.getCreditAccId() + 
		      		 ",ruleName= '" + posting.getRuleName() + 
		      		"' ,currency= '" + posting.getCurrency() + 
		      		 "' ,type= '" + posting.getType() + 
		      		"' ,status= '" + posting.getStatus() + 
		      		 "' ,BookingDate= '" + posting.getBookingDate() + 
		      		"',CreationDate= '" + posting.getCreationDate()+ 
		      		"' ,EffectiveDate= '" + posting.getEffectiveDate() + 
		      		"' ,accEventConfigID" + posting.getAccEvtConfigId() + 
		      		"  where id= " + posting.getId();
	      return updateSQL;
	     }
	 
	 public static int save(Posting insertPosting, Connection con) {
		 try {
             return insert(insertPosting, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("PostingSQL","save",e);
        	 return 0;
         }
	 }
	 public static boolean update(Posting updatePosting, Connection con) {
		 try {
             return edit(updatePosting, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("PostingSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(Posting deletePosting, Connection con) {
		 try {
             return remove(deletePosting, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("PostingSQL","update",e);
        	 return false;
         }
	 }
	 
	 protected static boolean remove(Posting deletePosting, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, DELETE);
	            stmt.setInt(j++, deletePosting.getId());
	           
	            stmt.executeUpdate();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("PostingSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("PostingSQL","remove",e);
			}
	        }
	        return true;
	 }
	 
	 
	 public static Vector selectPosting(int PostingId, Connection con) {
		 try {
          return (Vector) select(PostingId, con);
      }catch(Exception e) {
     	 commonUTIL.displayError("PostingSQL","select",e);
     	 return null;
      }
	 }
	 public static Collection selectALL(Connection con) {
		 try {
          return select(con);
      }catch(Exception e) {
     	 commonUTIL.displayError("PostingSQL","selectALL",e);
     	 return null;
      }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
          return selectMax(con);
      }catch(Exception e) {
     	 commonUTIL.displayError("PostingSQL","selectMaxID",e);
     	 return 0;
      }
	 }
	 
	 
	 public static int editlinkToPosting(int linkID, int postingID,Connection con) {
		  PreparedStatement stmt = null;
		  int i=0;
			 try {
				 String sql = getUpdateSQL(linkID,postingID);
				 stmt = dsSQL.newPreparedStatement(con, sql);
				 i = stmt.executeUpdate(sql);
				 con.commit();
				 return i;
				 
			 }catch (Exception e) {
				 commonUTIL.displayError("PostingSQL","edit",e);
				 return -1;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("bookSQL","edit",e);
				}
		        }
	 }
	 protected static  boolean edit(Posting updatePosting, Connection con ) {
		 
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, UPDATE);
	            
			
	           
	            
	            stmt.setInt(1, updatePosting.getId());
                stmt.setInt(2, updatePosting.getTradeID());
	            
	            stmt.setInt(3, updatePosting.getTransferId());
	            stmt.setInt(4, updatePosting.getSdiId());
	            stmt.setDouble(5, updatePosting.getCreditAmount());
	            stmt.setDouble(6, updatePosting.getDebitAmount());
   
	           
	            stmt.executeUpdate();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("PostingSQL","edit",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL","edit",e);
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
			 commonUTIL.displayError("PostingSQL","selectMax",e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("PostingSQL","selectMax",e);
			}
	        }
	        return j;
	 }
      
      protected static int insert(Posting inserPosting, Connection con ) {
			
	        PreparedStatement stmt = null;
	        int id =0;
		 try {
			// id = selectMax(con) + 1;
				// int j = 1;
				stmt = dsSQL.newPreparedStatement(con, INSERT);

				// stmt.setInt(1, id);

				stmt.setInt(1, inserPosting.getTradeID());

				stmt.setInt(2, inserPosting.getTransferId());
				stmt.setInt(3, inserPosting.getSdiId());
				stmt.setDouble(4, inserPosting.getCreditAmount());
				stmt.setDouble(5, inserPosting.getDebitAmount());
				stmt.setString(6, inserPosting.getAccEventType());
				stmt.setInt(7, inserPosting.getLinkId());
				stmt.setString(8, inserPosting.getEventType());

				stmt.setInt(9, inserPosting.getDebitAccId());

				stmt.setInt(10, inserPosting.getCreditAccId());
				stmt.setString(11, inserPosting.getRuleName());
				stmt.setString(12, inserPosting.getCurrency());
				stmt.setString(13, inserPosting.getType());
				stmt.setString(14, inserPosting.getStatus());
				stmt.setString(15, inserPosting.getBookingDate());
				stmt.setString(16, inserPosting.getCreationDate());
				stmt.setString(17, inserPosting.getEffectiveDate());
				stmt.setInt(18, inserPosting.getAccEvtConfigId());
				stmt.setInt(19, 0);

				if (stmt.executeUpdate() > 0) {

					con.commit();
					id = selectMax(con);
				}

				commonUTIL.display("PostingSQL", "insert " + INSERT);

		 } catch (Exception e) {
			 commonUTIL.displayError("PostingSQL","insert " ,e);
			 return 0;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("PostingSQL","insert",e);
			}
	        }
	        return id;
	 }
	 
	 protected static Collection select(int PostingID,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector Postings = new Vector();
	        
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + PostingID);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	
	        	 Posting posting = new Posting();
	        	 posting.setId(rs.getInt(1));
	        	 posting.setTradeID(rs.getInt(2));
	        	 posting.setTransferId(rs.getInt(3));
	        	 posting.setSdiId(rs.getInt(4));
	        	 posting.setCreditAmount(rs.getDouble(5));
			        
	        	 posting.setDebitAmount(rs.getDouble(6));
			      
	        	 posting.setAccEventType(rs.getString(7));
	        	 posting.setLinkId(rs.getInt(8));
	        	 posting.setEventType(rs.getString(9));
	        	 posting.setDebitAccId(rs.getInt(10));
	        	 posting.setCreditAccId(rs.getInt(11));
			        
	        	 posting.setRuleName(rs.getString(12));
	        	 posting.setCurrency(rs.getString(13));
	        	 posting.setType(rs.getString(14));
	        	 posting.setStatus(rs.getString(15));
	        	 posting.setBookingDate(rs.getString(16));
	        	 posting.setCreationDate(rs.getString(17));
			        
	        	 posting.setEffectiveDate(rs.getString(18));
	        	 posting.setAccEvtConfigId(rs.getInt(19));
	        	 posting.setLinkTo(rs.getInt(20));
			        Postings.add(posting);
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("PostingSQL","select",e);
			 return Postings;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("PostingSQL","selectMax",e);
			}
	        }
	        return Postings;
	 }

	
	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Postings = new Vector();
	     
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	 
	    	 
	    	  Posting posting = new Posting();
	        	 posting.setId(rs.getInt(1));
	        	 posting.setTradeID(rs.getInt(2));
	        	 posting.setTransferId(rs.getInt(3));
	        	 posting.setSdiId(rs.getInt(4));
	        	 posting.setCreditAmount(rs.getDouble(5));
			        
	        	 posting.setDebitAmount(rs.getDouble(6));
			      
			      
	        	 posting.setAccEventType(rs.getString(7));
	        	 posting.setLinkId(rs.getInt(8));
	        	 posting.setEventType(rs.getString(9));
	        	 posting.setDebitAccId(rs.getInt(10));
	        	 posting.setCreditAccId(rs.getInt(11));
			        
	        	 posting.setRuleName(rs.getString(12));
	        	 posting.setCurrency(rs.getString(13));
	        	 posting.setType(rs.getString(14));
	        	 posting.setStatus(rs.getString(15));
	        	 posting.setBookingDate(rs.getString(16));
	        	 posting.setCreationDate(rs.getString(17));
			        
	        	 posting.setEffectiveDate(rs.getString(18));
	        	 posting.setAccEvtConfigId(rs.getInt(19));
	        	 posting.setLinkTo(rs.getInt(20));
		        Postings.add(posting);
		     
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("PostingSQL","select",e);
			 return Postings;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("PostingSQL","selectMax",e);
			}
	     }
	     return Postings;
	 }
	 
	 protected static Collection selectbook(int bookId,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Postings = new Vector();
	     
		 try {
			 SELECTONE = SELECTONE + bookId;
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Posting posting = new Posting();
	        	 posting.setId(rs.getInt(1));
	        	 posting.setTradeID(rs.getInt(2));
	        	 posting.setTransferId(rs.getInt(3));
	        	 posting.setSdiId(rs.getInt(4));
	        	 posting.setCreditAmount(rs.getDouble(5));
			        
	        	 posting.setDebitAmount(rs.getDouble(6));
			      
			      
	        	 posting.setAccEventType(rs.getString(7));
	        	 posting.setLinkId(rs.getInt(8));
	        	 posting.setEventType(rs.getString(9));
	        	 posting.setDebitAccId(rs.getInt(10));
	        	 posting.setCreditAccId(rs.getInt(11));
			        
	        	 posting.setRuleName(rs.getString(12));
	        	 posting.setCurrency(rs.getString(13));
	        	 posting.setType(rs.getString(14));
	        	 posting.setStatus(rs.getString(15));
	        	 posting.setBookingDate(rs.getString(16));
	        	 posting.setCreationDate(rs.getString(17));
			        
	        	 posting.setEffectiveDate(rs.getString(18));
	        	 posting.setAccEvtConfigId(rs.getInt(19));
	        	 posting.setLinkTo(rs.getInt(20));
		      
		        Postings.add(posting);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("PostingSQL","selectPosting",e);
			 return Postings;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("PostingSQL","selectMax",e);
			}
	     }
	     return Postings;
	     
	 }
	 public static Collection selectWhere(String where, Connection conn) {
			int j = 0;
		     PreparedStatement stmt = null;
		     Vector Postings = new Vector();
		     String sql = SELECTWHERE;
			 try {
				 sql = sql + where;
				 stmt = dsSQL.newPreparedStatement(conn, sql );
		      
		      ResultSet rs = stmt.executeQuery();
		      
		      while(rs.next()) {
		    	  Posting posting = new Posting();
		        	 posting.setId(rs.getInt(1));
		        	 posting.setTradeID(rs.getInt(2));
		        	 posting.setTransferId(rs.getInt(3));
		        	 posting.setSdiId(rs.getInt(4));
		        	 posting.setCreditAmount(rs.getDouble(5));
				        
		        	 posting.setDebitAmount(rs.getDouble(6));
				      
		        	 posting.setAccEventType(rs.getString(7));
		        	 posting.setLinkId(rs.getInt(8));
		        	 posting.setEventType(rs.getString(9));
		        	 posting.setDebitAccId(rs.getInt(10));
		        	 posting.setCreditAccId(rs.getInt(11));
				        
		        	 posting.setRuleName(rs.getString(12));
		        	 posting.setCurrency(rs.getString(13));
		        	 posting.setType(rs.getString(14));
		        	 posting.setStatus(rs.getString(15));
		        	 posting.setBookingDate(rs.getString(16));
		        	 posting.setCreationDate(rs.getString(17));
				        
		        	 posting.setEffectiveDate(rs.getString(18));
		        	 posting.setAccEvtConfigId(rs.getInt(19));
		        	 posting.setLinkTo(rs.getInt(20));
			      
			        Postings.add(posting);
		      
		      }
		      commonUTIL.display("PostingSQL","selectWhere " + sql );
			 } catch (Exception e) {
				 commonUTIL.displayError("PostingSQL","SELECTWHERE "+sql,e);
				 return Postings;
		        
		     }
		     finally {
		        try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("TransferSQL",SELECTWHERE,e);
				}
		     }
		     return Postings;
			
		}
	 
}
