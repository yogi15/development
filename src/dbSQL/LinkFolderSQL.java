package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.LinkFolder;
import beans.LinkFolder;

public class LinkFolderSQL {
	
	
	
	
	final static private String DELETE_FROM_LINKFOLDER  =
	"DELETE FROM LINKFOLDER where id =? ";
final static private String INSERT_FROM_LINKFOLDER =
	"INSERT into LINKFOLDER(id,ruleid,productType,folderId,sdifilterid) values(?,?,?,?,?)";
final static private String UPDATE_FROM_LINKFOLDER =
	"UPDATE LINKFOLDER set le_id=?,LINKFOLDER_name=? where id  = ? ";
final static private String SELECT_MAX =
	"SELECT MAX(id) DESC_ID FROM LINKFOLDER ";
final static private String SELECTALL =
	"SELECT id,ruleid,productType,folderId,sdifilterid FROM LINKFOLDER order by folderId,ruleid,productType";
final static private String SELECT =
	"SELECT id,ruleid,productType,folderId,sdifilterid FROM LINKFOLDER where id =  ?";
 static private String SELECTONE =
	"SELECT id,ruleid,productType,folderId,sdifilterid FROM LINKFOLDER where id =  " ;
final static private String SELECTLINKFOLDER =
			"SELECT id,ruleid,productType,folderId,sdifilterid  FROM LINKFOLDER where ID =  " ;
final static private String SELECTWHERE =
"SELECT id,ruleid,productType,folderId,sdifilterid  FROM LINKFOLDER where    " ;



private static String getUpdateSQL(LinkFolder  linkFolder) {
     String updateSQL = "UPDATE LINKFOLDER  set " +
     		"  ruleid = '" + linkFolder.getRuleid() + 
     		" ,productType= '" + linkFolder.getProductType() + 	 
     		"',  folderId = '" + linkFolder.getFolderId() + 
     		", sdifilterid = '" + linkFolder.getSdifilterid() + 
     		"'  where id = " + linkFolder.getId();
     return updateSQL;
    }





public static int save(LinkFolder insertLinkFolder, Connection con) {
	 try {
        return insert(insertLinkFolder, con);
    }catch(Exception e) {
   	 commonUTIL.displayError("LinkFolderSQL","save",e);
   	 return -1;
    }
}
public static boolean update(LinkFolder updateLinkFolder, Connection con) {
	 try {
        return edit(updateLinkFolder, con);
    }catch(Exception e) {
   	 commonUTIL.displayError("LinkFolderSQL","update",e);
   	 return false;
    }
}

public static boolean delete(LinkFolder deleteLinkFolder, Connection con) {
	 try {
        return remove(deleteLinkFolder, con);
    }catch(Exception e) {
   	 commonUTIL.displayError("LinkFolderSQL","update",e);
   	 return false;
    }
}
public static LinkFolder selectTriggerEvent(int LinkFolderId, Connection con) {
	 try {
        return  selectLinkFolder(LinkFolderId, con);
    }catch(Exception e) {
   	 commonUTIL.displayError("LinkFolderSQL","select",e);
   	 return null;
    }
}
public static Collection selectALL(Connection con) {
	 try {
        return select(con);
    }catch(Exception e) {
   	 commonUTIL.displayError("LinkFolderSQL","selectALL",e);
   	 return null;
    }
}

public static int selectMaxID(Connection con) {
	 try {
        return selectMax(con);
    }catch(Exception e) {
   	 commonUTIL.displayError("LinkFolderSQL","selectMaxID",e);
   	 return 0;
    }
}

protected static  boolean edit(LinkFolder updateLinkFolder, Connection con ) {
	 
       PreparedStatement stmt = null;
       String sql = getUpdateSQL(updateLinkFolder);
	 try {
		 con.setAutoCommit(false);
		 int j = 1;
		 stmt = dsSQL.newPreparedStatement(con, sql);
           stmt.executeUpdate(sql);
		 con.commit();
		 commonUTIL.display("LinkFolderSQL ::  edit", sql);
	 } catch (Exception e) {
		 commonUTIL.displayError("LinkFolderSQL","edit",e);
		 return false;
          
       }
       finally {
          try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LinkFolderSQL","edit",e);
		}
       }
       return true;
}

protected static boolean remove(LinkFolder deleteLinkFolder, Connection con ) {

       PreparedStatement stmt = null;
	 try {
		 int j = 1;
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_LINKFOLDER);
           stmt.setInt(j++, deleteLinkFolder.getId());
          
           stmt.executeUpdate();
		 con.commit();
	 } catch (Exception e) {
		 commonUTIL.displayError("LinkFolderSQL","remove",e);
		 return false;
          
       }
       finally {
          try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LinkFolderSQL","remove",e);
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
		 commonUTIL.displayError("LinkFolderSQL",SELECT_MAX,e);
		 return j;
          
       }
       finally {
          try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LinkFolderSQL",SELECT_MAX,e);
		}
       }
       return j;
}




protected static int insert(LinkFolder inserLinkFolder, Connection con ) {
		
      PreparedStatement stmt = null;
      int id  = 0;
	 try {
		 con.setAutoCommit(false);
		  id = selectMax(con);
		 id = id + 1;
		 int j = 1;
		 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_LINKFOLDER);
		 stmt.setInt(1,id);
		
          stmt.setInt(2,  inserLinkFolder.getRuleid() );
		  stmt.setString(3, inserLinkFolder.getProductType());
		  stmt.setInt(4,inserLinkFolder.getFolderId());
		  stmt.setInt(5, inserLinkFolder.getSdifilterid());
          
          
          stmt.executeUpdate();
          con.commit();
          commonUTIL.display("LinkFolderSQL", "insert "+  INSERT_FROM_LINKFOLDER);
          return id;
	 } catch (Exception e) {
		 commonUTIL.displayError("LinkFolderSQL",INSERT_FROM_LINKFOLDER,e);
		 return -1;
         
      }
      finally {
         try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LinkFolderSQL",INSERT_FROM_LINKFOLDER,e);
		}
      }
   
}

protected static LinkFolder select(int LinkFolderIn,Connection con ) {
	 
	 int j = 0;
      PreparedStatement stmt = null;
      Vector LinkFolders = new Vector();
      LinkFolder linkFolder = new LinkFolder();
	 try {
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, SELECTONE + LinkFolderIn);
       
       ResultSet rs = stmt.executeQuery();
       
       while(rs.next()) {
       	 
    	   linkFolder.setId(rs.getInt(1));
    	   linkFolder.setFolderId(rs.getInt(4));
    	   linkFolder.setProductType(rs.getString(3));
    	   linkFolder.setRuleid(rs.getInt(2));
    	   linkFolder.setSdifilterid(rs.getInt(5));
    
     
      
    
   
       
       }
       return linkFolder;
	 } catch (Exception e) {
		 commonUTIL.displayError("LinkFolderSQL","select",e);
		 return linkFolder;
         
      }
      finally {
         try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LinkFolderSQL","selectMax",e);
		}
      }
   
}

protected static Collection select(Connection con) { 
	 int j = 0;
   PreparedStatement stmt = null;
   Vector LinkFolders = new Vector();
   
	 try {
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
    
    ResultSet rs = stmt.executeQuery();
    
    while(rs.next()) {
 
   LinkFolder linkFolder = new LinkFolder();
 
   linkFolder.setId(rs.getInt(1));
   linkFolder.setFolderId(rs.getInt(4));
   linkFolder.setProductType(rs.getString(3));
   linkFolder.setRuleid(rs.getInt(2));
   linkFolder.setSdifilterid(rs.getInt(5));


      LinkFolders.add(linkFolder);
    
    }
	 } catch (Exception e) {
		 commonUTIL.displayError("LinkFolderSQL",SELECTALL,e);
		 return LinkFolders;
      
   }
   finally {
      try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LinkFolderSQL",SELECTALL,e);
		}
   }
   return LinkFolders;
}



public static Collection selectLinkFolderOnWhere(String where,Connection con ) {
	 int j = 0;
   PreparedStatement stmt = null;
   Vector<LinkFolder> linkFolders = new  Vector<LinkFolder>();
   String sql = "";
	 try {
		 sql = SELECTWHERE + where;
		 con.setAutoCommit(false);
		
		 stmt = dsSQL.newPreparedStatement(con, sql );
    
    ResultSet rs = stmt.executeQuery();
    
    while(rs.next()) {
    	LinkFolder linkFolder = new LinkFolder();
    	linkFolder.setId(rs.getInt(1));
    	   linkFolder.setFolderId(rs.getInt(4));
    	   linkFolder.setProductType(rs.getString(3));
    	   linkFolder.setRuleid(rs.getInt(2));
    	   linkFolder.setSdifilterid(rs.getInt(5));
    	   linkFolders.addElement(linkFolder);
    	 
  
    
    }
    commonUTIL.display("LinkFolderSQL","selectLinkFolderOnWhere " + sql);
	 } catch (Exception e) {
		 commonUTIL.displayError("LinkFolderSQL","selectLinkFolderOnWhere " + sql,e);
		 return linkFolders;
      
   }
   finally {
      try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LinkFolderSQL","selectLinkFolderOnWhere",e);
		}
   }
   return linkFolders;
}



protected static LinkFolder selectLinkFolder(int LinkFolderId,Connection con ) {
	 int j = 0;
   PreparedStatement stmt = null;
   LinkFolder linkFolder = new LinkFolder();
   
	 try {
		 con.setAutoCommit(false);
		 SELECTONE = SELECTONE + LinkFolderId;
		 stmt = dsSQL.newPreparedStatement(con, SELECTONE );
    
    ResultSet rs = stmt.executeQuery();
    
    while(rs.next()) {
    	
    	linkFolder.setId(rs.getInt(1));
    	   linkFolder.setFolderId(rs.getInt(4));
    	   linkFolder.setProductType(rs.getString(3));
    	   linkFolder.setRuleid(rs.getInt(2));
    	   linkFolder.setSdifilterid(rs.getInt(5));

    	 
  
    
    }
	 } catch (Exception e) {
		 commonUTIL.displayError("LinkFolderSQL","selectLinkFolder",e);
		 return linkFolder;
      
   }
   finally {
      try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LinkFolderSQL","selectMax",e);
		}
   }
   return linkFolder;
}

}



