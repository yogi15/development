package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.AccEventConfig;
import beans.AccEventConfig;
import beans.AccEventConfig;

public class AccEventConfigSQL  {
	
	
	final static private String DELETE_FROM_AccEventConfig  =
	"DELETE FROM AccEventConfig where id =? ";
final static private String INSERT_FROM_AccEventConfig =
	"INSERT into AccEventConfig(id,accEventType,productType,eventType,reversalType) values(?,?,?,?,?)";
final static private String UPDATE_FROM_AccEventConfig =
	"UPDATE AccEventConfig set le_id=?,AccEventConfig_name=? where AccEventConfigno = ? ";
final static private String SELECT_MAX =
	"SELECT MAX(id) DESC_ID FROM AccEventConfig ";
final static private String SELECTALL =
	"SELECT id,accEventType,productType,eventType,reversalType FROM AccEventConfig order by id";
final static private String SELECT =
	"SELECT id,accEventType,productType,eventType,reversalType FROM AccEventConfig where id =  ?";
 static private String SELECTONE =
	"SELECT id,accEventType,productType,eventType,reversalType  FROM AccEventConfig where id =  " ;
final static private String SELECTACCEVNETCONFIG =
			"SELECT id,accEventType,productType,eventType,reversalType  FROM AccEventConfig where accEventConfigID =  " ;
 final static private String checkExistConfig = " select id from AccEventConfig where ";
 
 final static private String SELECTONWHERE  = " select  id,accEventType,productType,eventType,reversalType  from AccEventConfig where ";
 private static String getUpdateSQL(AccEventConfig  accEventConfig) {
      String updateSQL = "UPDATE AccEventConfig  set " +
      		"  accEventType = '" + accEventConfig.getAccEvtType() + 
      		"' ,productType= '" + accEventConfig.getProductType() + 	 
      		"',  eventType = '" + accEventConfig.getPaymentType() + 
      		"', reversalType = '" + accEventConfig.getReversalType() + 
      		"'  where id = " + accEventConfig.getId();
      return updateSQL;
     }
 

 public static int save(AccEventConfig insertAccEventConfig, Connection con) {
	 try {
         return insert(insertAccEventConfig, con);
     }catch(Exception e) {
    	 commonUTIL.displayError("AccEventConfigSQL","save",e);
    	 return -1;
     }
 }
 public static boolean update(AccEventConfig updateAccEventConfig, Connection con) {
	 try {
         return edit(updateAccEventConfig, con);
     }catch(Exception e) {
    	 commonUTIL.displayError("AccEventConfigSQL","update",e);
    	 return false;
     }
 }
 
 public static boolean delete(int deleteAccEventConfig, Connection con) {
	 try {
         return remove(deleteAccEventConfig, con);
     }catch(Exception e) {
    	 commonUTIL.displayError("AccEventConfigSQL","update",e);
    	 return false;
     }
 }
 public static AccEventConfig selectTriggerEvent(int AccEventConfigId, Connection con) {
	 try {
         return  selectAccEventConfig(AccEventConfigId, con);
     }catch(Exception e) {
    	 commonUTIL.displayError("AccEventConfigSQL","select",e);
    	 return null;
     }
 }
 public static Collection selectALL(Connection con) {
	 try {
         return select(con);
     }catch(Exception e) {
    	 commonUTIL.displayError("AccEventConfigSQL","selectALL",e);
    	 return null;
     }
 }
 
 public static int selectMaxID(Connection con) {
	 try {
         return selectMax(con);
     }catch(Exception e) {
    	 commonUTIL.displayError("AccEventConfigSQL","selectMaxID",e);
    	 return 0;
     }
 }
 
 protected static  boolean edit(AccEventConfig updateAccEventConfig, Connection con ) {
	 
        PreparedStatement stmt = null;
        String sql = getUpdateSQL(updateAccEventConfig);
	 try {
		 con.setAutoCommit(false);
		 int j = 1;
		 stmt = dsSQL.newPreparedStatement(con, sql);
            stmt.executeUpdate(sql);
		 con.commit();
		 commonUTIL.display("AccEventConfigSQL ::  edit", sql);
	 } catch (Exception e) {
		 commonUTIL.displayError("AccEventConfigSQL","edit",e);
		 return false;
           
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("AccEventConfigSQL","edit",e);
		}
        }
        return true;
 }

protected static boolean remove(int deleteAccEventConfig, Connection con ) {

        PreparedStatement stmt = null;
	 try {
		 int j = 1;
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_AccEventConfig);
            stmt.setInt(j++,deleteAccEventConfig);
           
            stmt.executeUpdate();
		 con.commit();
	 } catch (Exception e) {
		 commonUTIL.displayError("AccEventConfigSQL","remove",e);
		 return false;
           
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("AccEventConfigSQL","remove",e);
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
		 commonUTIL.displayError("AccEventConfigSQL",SELECT_MAX,e);
		 return j;
           
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("AccEventConfigSQL",SELECT_MAX,e);
		}
        }
        return j;
 }
 



protected static int insert(AccEventConfig inserAccEventConfig, Connection con ) {
		
       PreparedStatement stmt = null;
       int id  = 0;
	 try {
		 con.setAutoCommit(false);
		
			id = selectMax(con);
			id = id + 1;
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_AccEventConfig);
			stmt.setInt(1,id);
		
         	 stmt.setString(2, inserAccEventConfig.getAccEvtType());
         	 stmt.setString(3, inserAccEventConfig.getProductType());
         	 stmt.setString(4, inserAccEventConfig.getPaymentType());
         	 stmt.setString(5, inserAccEventConfig.getReversalType());
           
           
         	 stmt.executeUpdate();
           con.commit();
		
           return id;
	 } catch (Exception e) {
		 commonUTIL.displayError("AccEventConfigSQL",INSERT_FROM_AccEventConfig,e);
		 return -1;
          
       }
       finally {
          try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("AccEventConfigSQL",INSERT_FROM_AccEventConfig,e);
		}
       }
    
}

protected static AccEventConfig select(int AccEventConfigIn,Connection con ) {
	 
	 int j = 0;
       PreparedStatement stmt = null;
       Vector AccEventConfigs = new Vector();
       AccEventConfig accEventConfig = new AccEventConfig();
	 try {
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, SELECTONE + AccEventConfigIn);
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()) {
        	 
        	accEventConfig.setId(rs.getInt(1));
        	accEventConfig.setAccEvtType(rs.getString(2));
        	accEventConfig.setProductType(rs.getString(3));
        	accEventConfig.setPaymentType(rs.getString(4));
        	accEventConfig.setReversalType(rs.getString(5));
     
      
       
     
      return accEventConfig;
        
        }
	 } catch (Exception e) {
		 commonUTIL.displayError("AccEventConfigSQL","select",e);
		 return accEventConfig;
          
       }
       finally {
          try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("AccEventConfigSQL","selectMax",e);
		}
       }
       return accEventConfig;
}

protected static Collection select(Connection con) { 
	 int j = 0;
    PreparedStatement stmt = null;
    Vector AccEventConfigs = new Vector();
    
	 try {
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
     
     ResultSet rs = stmt.executeQuery();
     
     while(rs.next()) {
  
    AccEventConfig accEventConfig = new AccEventConfig();
    accEventConfig.setId(rs.getInt(1));
	accEventConfig.setAccEvtType(rs.getString(2));
	accEventConfig.setProductType(rs.getString(3));
	accEventConfig.setPaymentType(rs.getString(4));
	accEventConfig.setReversalType(rs.getString(5));
       AccEventConfigs.add(accEventConfig);
     
     }
	 } catch (Exception e) {
		 commonUTIL.displayError("AccEventConfigSQL",SELECTALL,e);
		 return AccEventConfigs;
       
    }
    finally {
       try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("AccEventConfigSQL",SELECTALL,e);
		}
    }
    return AccEventConfigs;
}

    

public static int checkExistingConfig(AccEventConfig config,Connection con ) {
	int configId = 0;
	String sql = checkExistConfig + "accEventType = '"+config.getAccEvtType() +"' and productType='"+ config.getProductType() + "'";
	 PreparedStatement stmt = null;
	try {
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, sql );
		 ResultSet rs = stmt.executeQuery();
	     
	     while(rs.next()) {
	    	 configId = rs.getInt(1);
	     }
	     commonUTIL.display("AccEventConfigSQL","checkExistingConfig " + sql);
} catch (Exception e) {
	 commonUTIL.displayError("AccEventConfigSQL","checkExistingConfig",e);
	 return configId;
  
}
finally {
  try {
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		commonUTIL.displayError("AccEventConfigSQL","checkExistingConfig",e);
	}
}
		 
	return configId;
}

public static Collection selectAccEventConfigOnWhere(String where,Connection con ) {
	 int j = 0;
    PreparedStatement stmt = null;
    Vector accEventConfigs = new Vector();
    String sql = SELECTONWHERE + where;
	 try {
		 con.setAutoCommit(false);
		
		 stmt = dsSQL.newPreparedStatement(con, sql );
     
     ResultSet rs = stmt.executeQuery();
     
     while(rs.next()) {
   	  AccEventConfig accEventConfig = new AccEventConfig();
   	 accEventConfig.setId(rs.getInt(1));
 	accEventConfig.setAccEvtType(rs.getString(2));
 	accEventConfig.setProductType(rs.getString(3));
 	accEventConfig.setPaymentType(rs.getString(4));
 	accEventConfig.setReversalType(rs.getString(5));
        accEventConfigs.add(accEventConfig);
   
     
     }
     commonUTIL.display("AccEventConfigSQL","selectAccEventConfigOnWhere " + sql);
	 } catch (Exception e) {
		 commonUTIL.displayError("AccEventConfigSQL","selectAccEventConfigOnWhere",e);
		 return accEventConfigs;
       
    }
    finally {
       try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("AccEventConfigSQL","selectMax",e);
		}
    }
    return accEventConfigs;
}


protected static AccEventConfig selectAccEventConfig(int AccEventConfigId,Connection con ) {
	 int j = 0;
    PreparedStatement stmt = null;
    AccEventConfig AccEventConfig = new AccEventConfig();
    
	 try {
		 con.setAutoCommit(false);
		 SELECTONE = SELECTONE + AccEventConfigId;
		 stmt = dsSQL.newPreparedStatement(con, SELECTONE );
     
     ResultSet rs = stmt.executeQuery();
     
     while(rs.next()) {
   	  AccEventConfig accEventConfig = new AccEventConfig();
   	 accEventConfig.setId(rs.getInt(1));
 	accEventConfig.setAccEvtType(rs.getString(2));
 	accEventConfig.setProductType(rs.getString(3));
 	accEventConfig.setPaymentType(rs.getString(4));
 	accEventConfig.setReversalType(rs.getString(5));
       // AccEventConfigs.add(accEventConfig);
   
     
     }
	 } catch (Exception e) {
		 commonUTIL.displayError("AccEventConfigSQL","selectAccEventConfig",e);
		 return AccEventConfig;
       
    }
    finally {
       try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("AccEventConfigSQL","selectMax",e);
		}
    }
    return AccEventConfig;
}

}
