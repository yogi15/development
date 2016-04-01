package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.LegalEntity;
import beans.QuoteData;
import util.commonUTIL;

public class QuoteDataSQL {
	

	final static private String DELETE_FROM_quoteData =
		"DELETE FROM quoteData where id =? ";
	final static private String INSERT_FROM_quoteData =
		"INSERT into quoteData(ENVNAME,QUOTENAME,BID,ASK,OPEN,CLOSE,HIGH,LOW,LAST,DATETIME) values(?,?,?,?,?,?,?,?,?,?)";
	final static private String UPDATE_FROM_quoteData =
		"UPDATE quoteData set users=?,tradeID=?,jobDate=?,jobType=?,productType=?,jobStatus=? where id = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(jobno) DESC_ID FROM quoteData ";
	final static private String SELECTALL =
		"SELECT ENVNAME,QUOTENAME,BID,ASK,OPEN,CLOSE,HIGH,LOW,LAST,DATETIME FROM quoteData order by id";
	final static private String SELECT =
		"SELECT title FROM quoteData where id =  ?";
	 static private String SELECTONE =
		"SELECT ENVNAME,QUOTENAME,BID,ASK,OPEN,CLOSE,HIGH,LOW,LAST,DATETIME FROM quoteData where id =  " ;
	 
	 
	 
	
	 
	 
	 
	 
	 protected static int insert(QuoteData quoteData, Connection con ) {
			
	        PreparedStatement stmt = null;
	        int id = 0;
		 try {
			 con.setAutoCommit(false);
			//  id = selectMax(con);
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_quoteData);
			 stmt.setString(1, quoteData.getEnvName());
			
	            stmt.setString(2, quoteData.getQuoteName());
	            stmt.setString(3, commonUTIL.converDoubleToString(quoteData.getBid()));
	            stmt.setString(4, commonUTIL.converDoubleToString(quoteData.getAsk()));
	            stmt.setString(5, commonUTIL.converDoubleToString(quoteData.getOpen()));
	            stmt.setString(6,commonUTIL.converDoubleToString( quoteData.getClose()));
	            stmt.setString(7,commonUTIL.converDoubleToString( quoteData.getHigh()));
	            stmt.setString(8, commonUTIL.converDoubleToString(quoteData.getLow()));
	            stmt.setString(9, commonUTIL.converDoubleToString(quoteData.getLast()));
	            stmt.setString(10, quoteData.getDatetime());
	           
	            stmt.executeUpdate();
	            con.commit();
	            commonUTIL.display("quoteDataSQL" , "insert" + INSERT_FROM_quoteData);
		 } catch (Exception e) {
			 commonUTIL.displayError("quoteDataSQL","insert",e);
			 return 0;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LESQL","insert",e);
			}
	        }
	        return id+1;
	 }
	 
	 protected static Collection select(String whereClause,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector quotes = new Vector();
	        
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + whereClause);
			
	         ResultSet rs = stmt.executeQuery();
	      
	         while(rs.next()) {
	        	 QuoteData quoteData = new QuoteData();
	        	 quoteData.setEnvName(rs.getString(1));
	        	 quoteData.setQuoteName(rs.getString(2));
	        	 quoteData.setBid(commonUTIL.converStringToDouble(rs.getString(3)));
	        	 quoteData.setAsk(commonUTIL.converStringToDouble(rs.getString(4)));
	        	 quoteData.setOpen(commonUTIL.converStringToDouble(rs.getString(5)));
	        	 quoteData.setClose(commonUTIL.converStringToDouble(rs.getString(6)));
	        	 quoteData.setHigh(commonUTIL.converStringToDouble(rs.getString(7)));
	        	 quoteData.setLow(commonUTIL.converStringToDouble(rs.getString(8)));
	        	 quoteData.setLast(commonUTIL.converStringToDouble(rs.getString(9)));
	        	 quoteData.setDatetime(rs.getString(10));
			       		        
			      
	        	 quotes.add(quoteData);
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("QuoteDataSQL",SELECTONE + SELECTONE,e);
			 return quotes;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL",SELECTONE ,e);
			}
	        }
	        return quotes;
	 }

	
	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector LegalEntitys = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  LegalEntity LegalEntity = new LegalEntity();
	    	 
		        LegalEntity.setId(rs.getInt(1));
		        LegalEntity.setName(rs.getString(2));
		        LegalEntity.setRole(rs.getString(3));
		       		        
		        LegalEntity.setStatus(rs.getString(4));
   		     LegalEntity.setAttributes(rs.getString(5));
			        LegalEntity.setAlias(rs.getString(6));
		        LegalEntitys.add(LegalEntity);
		     
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("LegalEntitySQL",SELECTALL ,e);
			 return LegalEntitys;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL","SELECTALL",e);
			}
	     }
	     return LegalEntitys;
	 }
	
	
	
	
	

}
