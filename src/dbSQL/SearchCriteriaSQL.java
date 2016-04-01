package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import beans.Task;

import util.commonUTIL;

public class SearchCriteriaSQL {
	
	 static String selectSearch = "Select name from SearchCriteria";
	 static String selectSearchColumn = "Select name from columnNameSearch where type = ";
	 
	 
	 
	public static Collection selectSearchCriteria( Connection con) {
		try {
				return (Vector) select( con);
			}catch(Exception e) {
					commonUTIL.displayError("SearchCriteriaSQL","selectSearch",e);
					return null;
			}
	}
   
	
	public static Vector selectSearchColumn( String type,Connection con) {
		try {
				return (Vector) select(type, con);
			}catch(Exception e) {
					commonUTIL.displayError("SearchCriteriaSQL"," selectSearchColumn ",e);
					return null;
			}
	}
   
		 private static Vector<String> select(String type, Connection con) {
			    
		        PreparedStatement stmt = null;
		        Vector<String> searchCr = new Vector<String>();
		        String sql = selectSearchColumn + "'" + type + "'";
			 try {
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con,sql );
		         
		         ResultSet rs = stmt.executeQuery();
		         while(rs.next()) {
		        	 searchCr.add(new String(rs.getString(1)));
		         }
		         commonUTIL.display("SearchCriteriaSQL","  selectSearchColumn  "+sql);
			   return searchCr;
			 }catch(Exception e) {
				 commonUTIL.displayError("SearchCriteriaSQL","  selectSearchColumn  "+sql,e);
				 return null;
			 }
		 }
		 
		 private static Vector<String> select( Connection con) {
			    
		        PreparedStatement stmt = null;
		        Vector<String> searchCr = new Vector<String>();
			 try {
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con,selectSearch );
		         
		         ResultSet rs = stmt.executeQuery();
		         while(rs.next()) {
		        	 searchCr.add(new String(rs.getString(1)));
		         }
		         commonUTIL.display("SearchCriteriaSQL","  selectSearchColumn  "+selectSearch);
			   return searchCr;
			 }catch(Exception e) {
				 commonUTIL.displayError("SearchCriteriaSQL","  selectSearch  "+selectSearch,e);
				 return null;
			 }
		 }
}
