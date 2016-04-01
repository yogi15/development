package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.Folder;
import util.commonUTIL;

public class FolderSQL {

	final static private String DELETE_FROM_FOLDER =
		"DELETE FROM FOLDER where Folderno =? ";
	final static private String INSERT_FROM_FOLDER =
		"INSERT into Folder(Folderno,Folder_name) values(?,?)";
	final static private String UPDATE_FROM_FOLDER =
		"UPDATE Folder set le_id=?,Folder_name=? where Folderno = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(folderno) DESC_ID FROM FOLDER ";
	final static private String SELECTALL =
		"SELECT  Folder_name FROM FOLDER order by FOLDERno";
	final static private String SELECT =
		"SELECT folder_name FROM Folder where Folderno =  ?";
	private static final String SELECTWHERE = "Select Folderno,Folder_name FROM FOLDER  where ";
	 static private String SELECTONE =
		"SELECT Folderno,Folder_name FROM FOLDER where FOLDERno =  " ;
	 
	 
	 private static String getUpdateSQL(Folder Folder) {
	      String updateSQL = "UPDATE Folder  set " +
	      		  		" Folder_name= '" + Folder.getFolder_name() + 	      		
	      		"'  where Folderno= " + Folder.getId();
	      return updateSQL;
	     }
	 
	 public static int save(Folder insertFolder, Connection con) {
		 try {
             return insert(insertFolder, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FolderSQL","save",e);
        	 return -1;
         }
	 }
	 public static boolean update(Folder updateFolder, Connection con) {
		 try {
             return edit(updateFolder, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FolderSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(Folder deleteFolder, Connection con) {
		 try {
             return remove(deleteFolder, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FolderSQL","update",e);
        	 return false;
         }
	 }
	 public static Folder selectFolder(int FolderId, Connection con) {
		 try {
             return  select(FolderId, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FolderSQL","select",e);
        	 return null;
         }
	 }
	 public static Vector<Folder> selectALL(Connection con) {
		 try {
             return select(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FolderSQL","selectALL",e);
        	 return null;
         }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
             return selectMax(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("FolderSQL","selectMaxID",e);
        	 return 0;
         }
	 }
	 
	 protected static  boolean edit(Folder updateFolder, Connection con ) {
		 
	        PreparedStatement stmt = null;
	        String sql = getUpdateSQL(updateFolder);
		 try {
			 con.setAutoCommit(false);
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, sql);
	            stmt.executeUpdate(sql);
			 con.commit();
			 commonUTIL.display("FolderSQL ::  edit  ", sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("FolderSQL","edit  " + sql ,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FolderSQL"," edit ",e);
			}
	        }
	        return true;
	 }
	
	protected static boolean remove(Folder deleteFolder, Connection con ) {
	
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_FOLDER);
	            stmt.setInt(j++, deleteFolder.getId());
	           
	            stmt.executeUpdate();
			 con.commit();
		 } catch (Exception e) {
			 commonUTIL.displayError("FolderSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FolderSQL","remove",e);
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
			 commonUTIL.displayError("FolderSQL",SELECT_MAX,e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FolderSQL",SELECT_MAX,e);
			}
	        }
	        return j;
	 }
	 
	 protected static int insert(Folder inserFolder, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 con.setAutoCommit(false);
			 int id = selectMax(con);
			 id = id +1;
			 int j = 1;
			 String where = " folder_name = '" + inserFolder.getFolder_name() +"'";
			 Vector exists = (Vector) selectWhere(where, con);
			 
			 if(exists.size() > 0) {
				 id = 0;
				
			 } else {
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_FOLDER);
			 stmt.setInt(1,id);
	            stmt.setString(2, inserFolder.getFolder_name());
	        
	            
	            stmt.executeUpdate();
	            con.commit();
			 } 
	            return id;
		 } catch (Exception e) {
			 commonUTIL.displayError("FolderSQL",INSERT_FROM_FOLDER,e);
			 return -1;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FolderSQL",INSERT_FROM_FOLDER,e);
			}
	        }
	       
	 }
	 
	 protected static Folder select(int FolderIn,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector Folders = new Vector();
	        Folder Folder = new Folder();
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + FolderIn);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        
	        Folder.setId(rs.getInt(1));
	        Folder.setFolder_name(rs.getString(2));
	        
	       
	        
	      
	       return Folder;
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("FolderSQL","select",e);
			 return Folder;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FolderSQL","selectMax",e);
			}
	        }
	        return Folder;
	 }

	 protected static  Vector<Folder> select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector<Folder> Folders = new Vector<Folder>();
	  
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	   
	     Folder folder = new Folder();
	      
	     folder.setFolder_name(rs.getString(1));
	     Folders.addElement(folder);
	      
	      }
	      
	      commonUTIL.display("FolderSQL", SELECTALL);
	      return Folders;
		 } catch (Exception e) {
			 commonUTIL.displayError("FolderSQL",SELECTALL,e);
			 return Folders;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FolderSQL",SELECTALL,e);
			}
	     }
	     
	 }
	 protected static Collection selectFolders(int FolderId,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Folders = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 SELECTONE = SELECTONE + FolderId;
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Folder folder = new Folder();
	 	     folder.setId(rs.getInt(1));
	 	     folder.setFolder_name(rs.getString(2));
	 	    Folders.addElement(folder);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("FolderSQL","selectFolder",e);
			 return Folders;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FolderSQL","selectMax",e);
			}
	     }
	     return Folders;
	 }
	 protected static Collection selectWhere(String where,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Folders = new Vector();
	     String sql = SELECTWHERE;
	     System.out.println("" );
	     sql = sql  + where;
	     
		 try {
			 con.setAutoCommit(false);
			 
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Folder folder = new Folder();
	 	     folder.setId(rs.getInt(1));
	 	     folder.setFolder_name(rs.getString(2));
	 	    Folders.addElement(folder);
	      
	      }
	      commonUTIL.display("FolderSQL", "select Where " + sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("FolderSQL","selectFolder",e);
			 return Folders;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("FolderSQL","selectMax",e);
			}
	     }
	     return Folders;
	 }
}


