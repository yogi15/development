package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.Book;
import beans.JTableMapping;

public class JTableMappingSQL {
	
	final static private String DELETE_FROM_practtable =
			"DELETE FROM jtableMapping where objectName  =? ";
		final static private String INSERT_FROM_practtable =
			"INSERT into jtableMapping( objectName,datatype,display_name) values(?,?,?)";
		
		
		final static private String SELECTALL =
			"SELECT  objectName,datatype,display_name FROM jtableMapping order by objectName";
		 
		 
		 static private String SELECTWHERE =
					"SELECT  objectName,datatype,display_name FROM jtableMapping where   " ;
		 
		 public static int save(JTableMapping  insert , Connection con) {
			 try {
	             return insert(insert, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("(JTableMappingSQL","save",e);
	        	 return -1;
	         }
		 }
		 public static Collection selectALL(  Connection con) {
			 try {
	             return select(  con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("(JTableMappingSQL","selectALL",e);
	        	 return null;
	         }
		 }
		private static int insert(JTableMapping insert, Connection con) {
			// TODO Auto-generated method stub
			
			  PreparedStatement stmt = null;
		        int id  = 0;
			 try {
				 con.setAutoCommit(false);
				   
				 
				
				 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_practtable);
				 stmt.setString(1,insert.getObjectName());
		            stmt.setString(2, insert.getDataType());
		            stmt.setString(3,insert.getDisplayName() ); 
		       id =     stmt.executeUpdate();
		            con.commit();
		            return id;
			 } catch (Exception e) {
				 commonUTIL.displayError("JTableMappingSQL",INSERT_FROM_practtable,e);
				 return -1;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("JTableMappingSQL",INSERT_FROM_practtable,e);
				}
		        }
		 
		}
		
		protected static Collection select(Connection con) { 
			 int j = 0;
		     PreparedStatement stmt = null;
		     Vector<JTableMapping> jTableData = new Vector<JTableMapping>();
		     
			 try {
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
		      
		      ResultSet rs = stmt.executeQuery();
		      
		      while(rs.next()) {
		   
		     JTableMapping data = new JTableMapping();
		      
		        data.setObjectName( rs.getString(1));
		        data.setDataType( rs.getString(2));
		        data.setDisplayName( rs.getString(3));
		        
		        jTableData.add(data);
		      
		      }
		      return jTableData;
			 } catch (Exception e) {
				 commonUTIL.displayError("JTableMappingSQL",SELECTALL,e);
				 return null;
		        
		     }
		     finally {
		        try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("JTableMappingSQL",SELECTALL,e);
				}
		     }
	 
		 }
}
