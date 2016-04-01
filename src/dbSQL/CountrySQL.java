package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.Book;
import beans.Country;

public class CountrySQL {
	
	final static private String DELETE_FROM_country =
			"DELETE FROM country where country_id =? ";
		final static private String INSERT_FROM_country =
			"INSERT into country( COUNTRY_ID,ISO_CODE,NAME,TIMEZONE) values(?,?,?)";
		final static private String UPDATE_FROM_BOOK =
			"UPDATE BOOK set le_id=?,book_name=? where bookno = ? ";
		final static private String SELECT_MAX =
			"SELECT MAX(bookno) DESC_ID FROM BOOK ";
		final static private String SELECTALL =
			"SELECT COUNTRY_ID,ISO_CODE,NAME,TIMEZONE FROM country  order by iso_code ";
		final static private String SELECT =
			"SELECT title FROM BOOK where bookno =  ?";
		 static private String SELECTONE =
			"SELECT COUNTRY_ID,ISO_CODE,NAME,TIMEZONE FROM country  where =  " ;
		 static private String SELECTWHERE =
				 "SELECT COUNTRY_ID,ISO_CODE,NAME,TIMEZONE FROM country  where =  " ;
		
		 
		 public static Country selectCountryOnISO(String ISO_CODE,Connection con ) {
			 
			 int j = 0;
		        PreparedStatement stmt = null;
		        Vector<Country> books = new Vector<Country>();
		        Country country = new Country();
		        Book book = new Book();
		        String sql = SELECTONE + " = '" + ISO_CODE +"'"; 
			 try {
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, SELECTONE);
		         
		         ResultSet rs = stmt.executeQuery();
		         
		         while(rs.next()) {
		        
		        	 country.setId(rs.getInt(1));
		        	 country.setIsocode(rs.getString(2));
		        	 country.setName(rs.getString(3));
		         }
		       
		        
		      
		       return country;
		         
		         
			 } catch (Exception e) {
				 commonUTIL.displayError("CountrySQL","SelectWhere ",e);
				 return country;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("CountrySQL","SelectWhere ",e);
				}
		        }
		      
		 }
		 public static Collection select(Connection con) {
			 
			 int j = 0;
		        PreparedStatement stmt = null;
		        Vector<Country> countrys = new Vector<Country>();
		        String sql = SELECTALL;
		     
		       
			 
		         try {
					 con.setAutoCommit(false);
					 stmt = dsSQL.newPreparedStatement(con, SELECTALL);
			         
			         ResultSet rs = stmt.executeQuery();
			         
			         while(rs.next()) {
			          Country country = new Country();
			        	 country.setId(rs.getInt(1));
			        	 country.setIsocode(rs.getString(2));
			        	 country.setName(rs.getString(3));
			        	 countrys.add(country);
			         }
		         } catch (Exception e) {
					 commonUTIL.displayError("CountrySQL","SelectWhere ",e);
					 return countrys;
			           
			        }
			        finally {
			           try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						commonUTIL.displayError("CountrySQL","SelectWhere ",e);
						return null;
					}
			        }
				return countrys;
		 }
		 
		
 public static Country selectISOCODEOnCountry(String CountryName,Connection con ) {
			 
			 int j = 0;
		        PreparedStatement stmt = null;
		        Vector<Country> books = new Vector<Country>();
		        Country country = new Country();
		        Book book = new Book();
		        String sql = SELECTONE + " = '" + country +"'"; 
			 try {
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, SELECTONE);
		         
		         ResultSet rs = stmt.executeQuery();
		         
		         while(rs.next()) {
		        
		        	 country.setId(rs.getInt(1));
		        	 country.setIsocode(rs.getString(2));
		        	 country.setName(rs.getString(3));
		         }
		       
		        
		      
		       return country;
		         
		         
			 } catch (Exception e) {
				 commonUTIL.displayError("CountrySQL","SelectWhere ",e);
				 return country;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("CountrySQL","SelectWhere ",e);
				}
		        }
		      
		 }
		 

}
