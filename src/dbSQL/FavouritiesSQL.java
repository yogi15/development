package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.Favorities;
import util.commonUTIL;

public class FavouritiesSQL {
	
	
	
	final static private String tableName = "Favourities";
	final static private String DELETE =
		"DELETE FROM " + tableName + "   where name =? ";
	final static private String INSERT =
		"INSERT into " + tableName + "(userid,type,typename,typevalue) values(?,?,?,?)";
	final static private String UPDATE =
		"UPDATE " + tableName + " set type=?,typename=?,typevalue=?   where userid = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(id) DESC_ID FROM " + tableName + " ";
	final static private String SELECTALL =
		"SELECT userid,type,typename,typevalue " + tableName + " ";
	final static private String SELECT =
		"SELECT userid,type,typename,typevalue FROM " + tableName + " where userid =  ? and type = ?";
	 static private String SELECTONE =
		"SELECT  userid,type,typename,typevalue  FROM " + tableName + " where   userid =  ?  order by type ";
	 static private String CHECKINSERT  =
				"SELECT  userid,type,typename,typevalue  FROM " + tableName + " where ";
	 static private String SELECTWHERE  =
				"SELECT  userid,type,typename,typevalue  FROM " + tableName + " where " ;
	 
	 public static boolean save(Favorities insertFavorities, Connection con) {
		 try {
             return insert(insertFavorities, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FavoritiesSQL","save",e);
        	 return false;
         }
	 }
	 public static boolean update(Favorities updateFavorities, Connection con) {
		 try {
             return edit(updateFavorities, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FavoritiesSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(Favorities deleteFavorities, Connection con) {
		 try {
             return remove(deleteFavorities, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FavoritiesSQL","update",e);
        	 return false;
         }
	 }
	 
	 protected static boolean remove(Favorities deleteFavorities, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			String  sql = " DELETE FROM Favorities   where userID =" + deleteFavorities.getUserId() + " and value = '"+ deleteFavorities.getType() +"'";
			    stmt = con.prepareStatement(sql);
			            stmt.execute(sql);
			  
			            
			             
			             con.commit();
			             commonUTIL.display("FavoritiesSQL ::  remove", sql);
			    
		 } catch (Exception e) {
			 commonUTIL.displayError("FavoritiesSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FavoritiesSQL ","remove",e);
			}
	        }
	        return true;
	 }
	 public static Collection selectFavorities(Favorities favorities, Connection con) {
		 try {
             return  select(favorities, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FavoritiesSQL ","select",e);
        	 return null;
         }
	 }
	 public static Collection selectALL(Connection con) {
		 try {
             return select(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FavoritiesSQL ","selectALL",e);
        	 return null;
         }
	 }
	 
	 protected static  boolean edit(Favorities updateFavorities, Connection con ) {
		 
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, UPDATE);
	            
			
	           
	            
	            stmt.setString(1, updateFavorities.getType());
	            stmt.setString(2, updateFavorities.getTypeName());
	            stmt.setString(3, updateFavorities.getTypeValue());
	            stmt.setInt(4, updateFavorities.getUserId());
	            
	            
	            stmt.executeUpdate();
	            con.commit();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("FavoritiesSQL ",UPDATE,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FavoritiesSQL",UPDATE,e);
			}
	        }
	        return true;
	 }
	
	 protected static boolean insert(Favorities inserFavorities, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 con.setAutoCommit(false);
			// Vector check  = (Vector) getFavorities(inserFavorities,con);
			// if(check == null) {
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT);
			
			
			  stmt.setString(2, inserFavorities.getType());
	            stmt.setString(3, inserFavorities.getTypeName());
	            stmt.setString(4, inserFavorities.getTypeValue());
	            stmt.setInt(1, inserFavorities.getUserId());
	           
	            commonUTIL.display(" FavoritiesSQL insert ",INSERT);
			 
	            stmt.executeUpdate();
	            con.commit();
			// }		
		 } catch (Exception e) {
			 commonUTIL.displayError("FavoritiesSQL",INSERT,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FavoritiesSQL "," insert",e);
			}
	        }
	        return true;
	 }
	 
	 protected static Collection select(Favorities favorities,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	       Vector values = new Vector();
	       String sql  = "";
		 try {
			 sql = SELECTONE + "'" + favorities.getUserId() + "'";
			con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con,sql) ;
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	
	        	 Favorities Favorities = new Favorities();
	        	 Favorities.setUserId(rs.getInt(1));
	        	 Favorities.setType(rs.getString(1));
	        	 Favorities.setTypeName(rs.getString(2));
	        	 Favorities.setTypeValue(rs.getString(3));
	        
	        values.add(Favorities);
	        
	      
	       
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("FavoritiesSQL",sql,e);
			 return values;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FavoritiesSQL", sql,e);
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
			 sql = SELECTWHERE + sqlw + " order by type ";
			con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con,sql) ;
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	
	        	 Favorities Favorities = new Favorities();
	        	 Favorities.setUserId(rs.getInt(1));
	        	 Favorities.setType(rs.getString(2));
	        	 Favorities.setTypeName(rs.getString(3));
	        	 Favorities.setTypeValue(rs.getString(4));
	        
	        values.add(Favorities);
	        
	      
	       
	         
	         }
	         commonUTIL.display("FavoritiesSQL selectWhere ",sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("FavoritiesSQL selectWhere ",sql,e);
			 return values;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FavoritiesSQL", sql,e);
			}
	        }
	        return values;
	 }

	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Favoritiess = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Favorities Favorities = new Favorities();
	    	
	        	 Favorities.setUserId(rs.getInt(1));
	        	 Favorities.setType(rs.getString(1));
	        	 Favorities.setTypeName(rs.getString(2));
	        	 Favorities.setTypeValue(rs.getString(3));
		        Favoritiess.add(Favorities);
	      
	      }  commonUTIL.display("FavoritiesSQL ",SELECTALL);
	      
		 } catch (Exception e) {
			 commonUTIL.displayError("FavoritiesSQL",SELECTALL,e);
			 return Favoritiess;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FavoritiesSQL ",SELECTALL,e);
			}
	     }
	     return Favoritiess;
	 }
	public static Collection getFavoritiesName(Connection con) {
		// TODO Auto-generated method stub
		int j = 0;
	     PreparedStatement stmt = null;
	     Vector Favoritiess = new Vector();
	     String sql = "select value from Favorities where name = "+ "'InitialData'";
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Favorities Favorities = new Favorities();
	    	//  Favorities.setName(rs.getString(1));
		       
		        Favoritiess.add(Favorities);
	      
	      }
	      
	      commonUTIL.display("getFavoritiesName",sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("getFavoritiesName",sql,e);
			 return Favoritiess;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("getFavoritiesName",sql,e);
			}
	     }
	     return Favoritiess;
	 
	}
	
	public static Collection getFavorities(Favorities fav,Connection con) {
		// TODO Auto-generated method stub
		int j = 0;
	     PreparedStatement stmt = null;
	     Vector Favoritiess = null;
	     String sql = CHECKINSERT +  " userid =  " + fav.getUserId() + " and type='" +fav.getType()+ "'  and typename ='" + fav.getTypeName() + "'  order by type  ";
	    
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con,sql);
				
				
			
	           
	          
	      ResultSet rs = stmt.executeQuery();
	    //  if(rs.next())
	    	 Favoritiess = new Vector();
	      while(rs.next()) {
	    	  Favorities Favorities = new Favorities();
	    //	  Favorities.setName(rs.getString(1));
		       
		        Favoritiess.add(Favorities);
	      
	      }
	      
	      commonUTIL.display(" FavoritiesSQL CHECKINSERT  ",sql);
	      
		 } catch (Exception e) {
			 commonUTIL.displayError("getFavorities",CHECKINSERT ,e);
			 return Favoritiess;
	        
	     }
	    finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("getFavorities",CHECKINSERT,e);
			}
	     }
	     return Favoritiess;
	 
	}
	 
}



