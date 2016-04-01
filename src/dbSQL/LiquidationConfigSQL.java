package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.LiquidationConfig;
import beans.LiquidationConfig;
import beans.LiquidationConfig;
import beans.LiquidationConfig;

public class LiquidationConfigSQL {
	
	final static private String DELETE_FROM_LIQCONFIG  =
			"DELETE FROM LiquidationConfig where id =? ";
		final static private String INSERT_FROM_LIQCONFIG =
			"INSERT into LiquidationConfig(id,bookid,liqmethod,datetype,productType,productsubtype) values(?,?,?,?,?,?)";
		final static private String UPDATE_FROM_LIQCONFIG =
			"UPDATE LiquidationConfig set le_id=?,LiquidationConfig_name=? where id  = ? ";
		final static private String SELECT_MAX =
			"SELECT MAX(id) DESC_ID FROM LiquidationConfig ";
		final static private String SELECTALL =
			"SELECT id,bookid,liqmethod,datetype,productType,productsubtype  from LiquidationConfig order by id ";
		final static private String SELECT =
			"SELECT id,bookid,liqmethod,datetype,productType,productsubtype  FROM LiquidationConfig where id =  ?";
		 static private String SELECTONE =
			"SELECTid,bookid,liqmethod,datetype,productType,productsubtype FROM LiquidationConfig where id =  " ;
		final static private String SELECTLIQCONFIG =
					"SELECT id,bookid,liqmethod,datetype,productType,productsubtype   FROM LiquidationConfig where ID =  " ;
		final static private String SELECTWHERE =
		"SELECT id,bookid,liqmethod,datetype,productType,productsubtype  FROM LiquidationConfig where    " ;



		private static String getUpdateSQL(LiquidationConfig  liqConfig) {
		     String updateSQL = "UPDATE LiquidationConfig  set " +
		     		"  id = '" + liqConfig.getId() + 
		     		" ,productType= '" + liqConfig.getProducttype() + 	 
		     		"',  bookid = '" + liqConfig.getBookid() + 
		     		", liqmethod = '" + liqConfig.getLiqmethod()+ 
		     		", productsubtype = '" + liqConfig.getProductsubtype() + 
		     		", datetype = '" + liqConfig.getDatetype() + 
		     		"'  where id = " + liqConfig.getId();
		     return updateSQL;
		    }


public static LiquidationConfig save(LiquidationConfig insertLiquidationConfig, Connection con) {
	 try {
        return insert(insertLiquidationConfig, con);
    }catch(Exception e) {
   	 commonUTIL.displayError("LiquidationConfigSQL","save",e);
   	 return null;
    }
}
public static boolean update(LiquidationConfig updateLiquidationConfig, Connection con) {
	 try {
        return edit(updateLiquidationConfig, con);
    }catch(Exception e) {
   	 commonUTIL.displayError("LiquidationConfigSQL","update",e);
   	 return false;
    }
}

public static boolean delete(LiquidationConfig deleteLiquidationConfig, Connection con) {
	 try {
        return remove(deleteLiquidationConfig, con);
    }catch(Exception e) {
   	 commonUTIL.displayError("LiquidationConfigSQL","update",e);
   	 return false;
    }
}
public static LiquidationConfig selectTriggerEvent(int LiquidationConfigId, Connection con) {
	 try {
        return  selectLiquidationConfig(LiquidationConfigId, con);
    }catch(Exception e) {
   	 commonUTIL.displayError("LiquidationConfigSQL","select",e);
   	 return null;
    }
}
public static Collection selectALL(Connection con) {
	 try {
        return select(con);
    }catch(Exception e) {
   	 commonUTIL.displayError("LiquidationConfigSQL","selectALL",e);
   	 return null;
    }
}

public static int selectMaxID(Connection con) {
	 try {
        return selectMax(con);
    }catch(Exception e) {
   	 commonUTIL.displayError("LiquidationConfigSQL","selectMaxID",e);
   	 return 0;
    }
}

protected static  boolean edit(LiquidationConfig updateLiquidationConfig, Connection con ) {
	 
       PreparedStatement stmt = null;
       String sql = getUpdateSQL(updateLiquidationConfig);
	 try {
		 con.setAutoCommit(false);
		 int j = 1;
		 stmt = dsSQL.newPreparedStatement(con, sql);
           stmt.executeUpdate(sql);
		 con.commit();
		 commonUTIL.display("LiquidationConfigSQL ::  edit", sql);
	 } catch (Exception e) {
		 commonUTIL.displayError("LiquidationConfigSQL","edit",e);
		 return false;
          
       }
       finally {
          try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LiquidationConfigSQL","edit",e);
		}
       }
       return true;
}

protected static boolean remove(LiquidationConfig deleteLiquidationConfig, Connection con ) {

       PreparedStatement stmt = null;
	 try {
		 int j = 1;
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_LIQCONFIG);
           stmt.setInt(j++, deleteLiquidationConfig.getId());
          
           stmt.executeUpdate();
		 con.commit();
	 } catch (Exception e) {
		 commonUTIL.displayError("LiquidationConfigSQL","remove",e);
		 return false;
          
       }
       finally {
          try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LiquidationConfigSQL","remove",e);
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
		 commonUTIL.displayError("LiquidationConfigSQL",SELECT_MAX,e);
		 return j;
          
       }
       finally {
          try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LiquidationConfigSQL",SELECT_MAX,e);
		}
       }
       return j;
}



protected static LiquidationConfig insert(LiquidationConfig inserLiquidationConfig, Connection con ) {
		
      PreparedStatement stmt = null;
      int id  = 0;
	 try {
		 con.setAutoCommit(false);
		  id = selectMax(con);
		 id = id + 1;
		 int j = 1;
		 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_LIQCONFIG);
		 stmt.setInt(1,id);
		
          stmt.setInt(2,  inserLiquidationConfig.getBookid() );
		  stmt.setString(3, inserLiquidationConfig.getLiqmethod());
		  stmt.setString(4,inserLiquidationConfig.getDatetype());
		  stmt.setString(5, inserLiquidationConfig.getProducttype());
		  stmt.setString(6, inserLiquidationConfig.getProductsubtype());
          
          stmt.executeUpdate();
          inserLiquidationConfig.setId(id);
          con.commit();
          commonUTIL.display("LiquidationConfigSQL", "insert "+  INSERT_FROM_LIQCONFIG);
          return inserLiquidationConfig;
	 } catch (Exception e) {
		 commonUTIL.displayError("LiquidationConfigSQL",INSERT_FROM_LIQCONFIG,e);
		 return null;
         
      }
      finally {
         try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LiquidationConfigSQL",INSERT_FROM_LIQCONFIG,e);
		}
      }
   
}

protected static LiquidationConfig select(int LiquidationConfigIn,Connection con ) {
	 
	 int id = 0;
      PreparedStatement stmt = null;
      Vector LiquidationConfigs = new Vector();
      LiquidationConfig LiquidationConfig = new LiquidationConfig();
	 try {
		 con.setAutoCommit(false);
		
		 stmt = dsSQL.newPreparedStatement(con, SELECTONE + LiquidationConfigIn);
       
       ResultSet rs = stmt.executeQuery();
       
       while(rs.next()) {
    	  
   		
    	 
 		  
 		  
    	   LiquidationConfig.setId(rs.getInt(1));
    	   LiquidationConfig.setBookid(rs.getInt(2));
    	   LiquidationConfig.setLiqmethod(rs.getString(3));
    	   LiquidationConfig.setDatetype(rs.getString(4));
    	   LiquidationConfig.setProducttype(rs.getString(5));
    	   LiquidationConfig.setProductsubtype(rs.getString(6));
    	  // LiquidationConfigs.add(LiquidationConfig);
     
      
    
   
       
       }
       return LiquidationConfig;
	 } catch (Exception e) {
		 commonUTIL.displayError("LiquidationConfigSQL","select",e);
		 return LiquidationConfig;
         
      }
      finally {
         try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LiquidationConfigSQL","selectMax",e);
		}
      }
   
}



protected static Collection select(Connection con) { 
	 int j = 0;
   PreparedStatement stmt = null;
   Vector LiquidationConfigs = new Vector();
   
	 try {
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
    
    ResultSet rs = stmt.executeQuery();
    
    while(rs.next()) {
 
   LiquidationConfig LiquidationConfig = new LiquidationConfig();
   LiquidationConfig.setId(rs.getInt(1));
   LiquidationConfig.setBookid(rs.getInt(2));
   LiquidationConfig.setLiqmethod(rs.getString(3));
   LiquidationConfig.setDatetype(rs.getString(4));
   LiquidationConfig.setProducttype(rs.getString(5));
   LiquidationConfig.setProductsubtype(rs.getString(6));
      LiquidationConfigs.add(LiquidationConfig);
    
    }
	 } catch (Exception e) {
		 commonUTIL.displayError("LiquidationConfigSQL",SELECTALL,e);
		 return LiquidationConfigs;
      
   }
   finally {
      try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LiquidationConfigSQL",SELECTALL,e);
		}
   }
   return LiquidationConfigs;
}

public static LiquidationConfig getLiquidationConfigOnWhere(int bookid,String producType,String productSubtype,Connection con ) {
	 int j = 0;
  PreparedStatement stmt = null;
  LiquidationConfig liquidationConfig = null;
  String sql = "";
	 try {
		 sql = SELECTWHERE + " bookid = " + bookid + " and productType ='"+producType+"' and productsubtype = '"+productSubtype+"'";
		 con.setAutoCommit(false);
		
		 stmt = dsSQL.newPreparedStatement(con, sql );
   
   ResultSet rs = stmt.executeQuery();
   
   while(rs.next()) {
	   liquidationConfig = new LiquidationConfig();
	   liquidationConfig.setId(rs.getInt(1));
	   liquidationConfig.setBookid(rs.getInt(2));
	   liquidationConfig.setLiqmethod(rs.getString(3));
	   liquidationConfig.setDatetype(rs.getString(4));
	   liquidationConfig.setProducttype(rs.getString(5));
	   liquidationConfig.setProductsubtype(rs.getString(6));
   	  
   	 
 
   
   }
   commonUTIL.display("LiquidationConfigSQL","selectLiquidationConfigOnWhere " + sql);
	 } catch (Exception e) {
		 commonUTIL.displayError("LiquidationConfigSQL","selectLiquidationConfigOnWhere " + sql,e);
		 return liquidationConfig;
     
  }
  finally {
     try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LiquidationConfigSQL","selectLiquidationConfigOnWhere",e);
		}
  }
  return liquidationConfig;
}



public static Collection selectLiquidationConfigOnWhere(String where,Connection con ) {
	 int j = 0;
   PreparedStatement stmt = null;
   Vector<LiquidationConfig> LiquidationConfigs = new  Vector<LiquidationConfig>();
   String sql = "";
	 try {
		 sql = SELECTWHERE + where;
		 con.setAutoCommit(false);
		
		 stmt = dsSQL.newPreparedStatement(con, sql );
    
    ResultSet rs = stmt.executeQuery();
    
    while(rs.next()) {
    	LiquidationConfig LiquidationConfig = new LiquidationConfig();
    	 LiquidationConfig.setId(rs.getInt(1));
  	   LiquidationConfig.setBookid(rs.getInt(2));
  	   LiquidationConfig.setLiqmethod(rs.getString(3));
  	   LiquidationConfig.setDatetype(rs.getString(4));
  	   LiquidationConfig.setProducttype(rs.getString(5));
  	   LiquidationConfig.setProductsubtype(rs.getString(6));
    	   LiquidationConfigs.addElement(LiquidationConfig);
    	 
  
    
    }
    commonUTIL.display("LiquidationConfigSQL","selectLiquidationConfigOnWhere " + sql);
	 } catch (Exception e) {
		 commonUTIL.displayError("LiquidationConfigSQL","selectLiquidationConfigOnWhere " + sql,e);
		 return LiquidationConfigs;
      
   }
   finally {
      try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LiquidationConfigSQL","selectLiquidationConfigOnWhere",e);
		}
   }
   return LiquidationConfigs;
}



protected static LiquidationConfig selectLiquidationConfig(int LiquidationConfigId,Connection con ) {
	 int j = 0;
   PreparedStatement stmt = null;
   LiquidationConfig LiquidationConfig = new LiquidationConfig();
   
	 try {
		 con.setAutoCommit(false);
		 SELECTONE = SELECTONE + LiquidationConfigId;
		 stmt = dsSQL.newPreparedStatement(con, SELECTONE );
    
    ResultSet rs = stmt.executeQuery();
    
    while(rs.next()) {
    	
    	 LiquidationConfig.setId(rs.getInt(1));
  	   LiquidationConfig.setBookid(rs.getInt(2));
  	   LiquidationConfig.setLiqmethod(rs.getString(3));
  	   LiquidationConfig.setDatetype(rs.getString(4));
  	   LiquidationConfig.setProducttype(rs.getString(5));
  	   LiquidationConfig.setProductsubtype(rs.getString(6));

    	 
  
    
    }
	 } catch (Exception e) {
		 commonUTIL.displayError("LiquidationConfigSQL","selectLiquidationConfig",e);
		 return LiquidationConfig;
      
   }
   finally {
      try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("LiquidationConfigSQL","selectMax",e);
		}
   }
   return LiquidationConfig;
}








}
