package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.AccConfigRule;
import beans.AccConfigRule;
import beans.AccConfigRule;
import beans.AccConfigRule;
import beans.AccConfigRule;

public class AccConfigRuleSQL {
	
	
	
	
	final static private String DELETE_FROM_AccConfigRule =
			"DELETE FROM AccConfigRule where id =? ";
		final static private String INSERT_FROM_AccConfigRule =
			"INSERT into AccConfigRule(id,ruleName,currency,attributes,ruleType,calDate,dayAdj,poid,dailyClosing) values(?,?,?,?,?,?,?,?,?)";
		final static private String UPDATE_FROM_AccConfigRuleT =
			"UPDATE AccConfigRule set le_id=?,AccConfigRule_name=? where AccConfigRuleno = ? ";
		final static private String SELECT_MAX =
			"SELECT MAX(id) DESC_ID FROM AccConfigRule ";
		final static private String SELECTALL =
			"SELECT id,ruleName,currency,attributes,ruleType,calDate,dayAdj,poid,dailyClosing  FROM AccConfigRule order by id";
		final static private String SELECTWHERE =
			"SELECT id,ruleName,currency,attributes,ruleType,calDate,dayAdj,poid,dailyClosing  FROM AccConfigRule where   ?";
		 static private String SELECTONE =
			"SELECT id,ruleName,currency,attributes,ruleType,calDate,dayAdj,poid,dailyClosing  FROM AccConfigRule where id  =  " ;
		 
		 
		 private static String getUpdateSQL(AccConfigRule rule) {
		      String updateSQL = "UPDATE AccConfigRule  set " +
					" ruleName= '" + rule.getRuleName()+ 	
					" ',currency= '" + rule.getCurrency() + 	
					" ',attributes= '" + rule.getAttributes() + 	
					" ',ruleType= '" +rule.getRuleTYpe() + 	
					" ',calDate= '" + rule.getCalDate()+ 	
					" ',dayAdj= '" + rule.getDayAdj()+ 	
		      		"' ,poid= " + rule.getPoID()   + 	
		      		", dailyClosing= " + rule.getDayAdj() + 	
		      		  		" where id= " + rule.getId();
		      return updateSQL;
		     }
		 
		 
		 public static int save(AccConfigRule insertAccConfigRule, Connection con) {
			 try {
	             return insert(insertAccConfigRule, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccConfigRuleSQL","save",e);
	        	 return -1;
	         }
		 }
		 public static boolean update(AccConfigRule updateAccConfigRule, Connection con) {
			 try {
	             return edit(updateAccConfigRule, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccConfigRuleSQL","update",e);
	        	 return false;
	         }
		 }
		 
		 public static boolean delete(AccConfigRule deleteAccConfigRule, Connection con) {
			 try {
	             return remove(deleteAccConfigRule, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccConfigRuleSQL","update",e);
	        	 return false;
	         }
		 }
		 public static AccConfigRule selectAccConfigRule(int AccConfigRuleId, Connection con) {
			 try {
	             return  select(AccConfigRuleId, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccConfigRuleSQL","select",e);
	        	 return null;
	         }
		 }
		 public static Collection selectALL(Connection con) {
			 try {
	             return select(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccConfigRuleSQL","selectALL",e);
	        	 return null;
	         }
		 }
		 
		 private static int selectMaxID(Connection con) {
			 try {
	             return selectMax(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccConfigRuleSQL","selectMaxID",e);
	        	 return 0;
	         }
		 }
		 
		 protected static  boolean edit(AccConfigRule updateAccConfigRule, Connection con ) {
			 
		        PreparedStatement stmt = null;
		        String sql = getUpdateSQL(updateAccConfigRule);
			 try {
				 con.setAutoCommit(false);
				 int j = 1;
				 stmt = dsSQL.newPreparedStatement(con, sql);
		            stmt.executeUpdate(sql);
				 con.commit();
				 commonUTIL.display("AccConfigRuleSQL ::  edit", sql);
			 } catch (Exception e) {
				 commonUTIL.displayError("AccConfigRuleSQL","edit " +  sql,e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccConfigRuleSQL","edit",e);
				}
		        }
		        return true;
		 }
		
		protected static boolean remove(AccConfigRule deleteAccConfigRule, Connection con ) {
		
		        PreparedStatement stmt = null;
			 try {
				 int j = 1;
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_AccConfigRule);
		            stmt.setInt(j++, deleteAccConfigRule.getId());
		           
		            stmt.executeUpdate();
				 con.commit();
			 } catch (Exception e) {
				 commonUTIL.displayError("AccConfigRuleSQL","remove",e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccConfigRuleSQL","remove",e);
				}
		        }
		        return true;
		 }

	private static int selectMax(Connection con ) {
			 
			 int j = 0;
		        PreparedStatement stmt = null;
			 try {
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);
		         
		         ResultSet rs = stmt.executeQuery();
		         while(rs.next())
		         j = rs.getInt("DESC_ID");
				 
			 } catch (Exception e) {
				 commonUTIL.displayError("AccConfigRuleSQL",SELECT_MAX,e);
				 return j;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccConfigRuleSQL",SELECT_MAX,e);
				}
		        }
		        return j;
		 }
	
	protected static int insert(AccConfigRule insertAccConfigRule, Connection con ) {
		
        PreparedStatement stmt = null;
        int id =0;
	 try {
		 con.setAutoCommit(false);
		 id = selectMax(con);
		 id = id +1;
		 int j = 1;
		 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_AccConfigRule);
		 stmt.setInt(1,id);
		
		 stmt.setString(2,insertAccConfigRule.getRuleName());
		 stmt.setString(3,insertAccConfigRule.getCurrency());
		 stmt.setString(4,insertAccConfigRule.getAttributes());
            stmt.setString(5, insertAccConfigRule.getRuleTYpe());
            stmt.setString(6,insertAccConfigRule.getCalDate() );
            stmt.setInt(7, insertAccConfigRule.getDayAdj());
            stmt.setInt(8,insertAccConfigRule.getPoID());
            if(insertAccConfigRule.isDailyClosing())
              stmt.setInt(9, 0);
            else 
            	stmt.setInt(9, 1);
            stmt.executeUpdate();
            con.commit();
            commonUTIL.display("AccConfigRuleSQL",INSERT_FROM_AccConfigRule);
	 } catch (Exception e) {
		 commonUTIL.displayError("AccConfigRuleSQL",INSERT_FROM_AccConfigRule,e);
		 return -1;
           
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("AccConfigRuleSQL",INSERT_FROM_AccConfigRule,e);
		}
        }
        return id;
 }
 
 
 public static AccConfigRule select(int accConfigRuleid,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        AccConfigRule accConfigRule = new AccConfigRule();
	        String sql = SELECTONE;
	        sql = sql + accConfigRuleid;
	    
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql);
	         
	         ResultSet rs = stmt.executeQuery();
	       //  id,ruleName,currency,attributes,ruleType,calDate,dayAdj,poid,dailyClosing
	         while(rs.next()) {
	        	
	       
	        	 accConfigRule.setId(rs.getInt(1));
	        	 accConfigRule.setRuleName(rs.getString(2));
	        	 accConfigRule.setCurrency(rs.getString(3));
	        
	        	 accConfigRule.setAttributes(rs.getString(4));
	     
	        
	        	 accConfigRule.setRuleTYpe(rs.getString(5));
	        	 accConfigRule.setCalDate(rs.getString(6));
	        	 accConfigRule.setDayAdj(rs.getInt(7));
	        	 accConfigRule.setPoID(rs.getInt(8));
	       if( rs.getInt(9) == 0)
	    	   accConfigRule.setDailyClosing(false) ;
	       else 
	    	   
	    	   accConfigRule.setDailyClosing(true) ;
	        
	       
	        
	      
	       
	         
	         }
	         commonUTIL.display("AccConfigRulesSQL"," select " + sql);
	         return accConfigRule;
		 } catch (Exception e) {
			 commonUTIL.displayError("AccConfigRulesSQL","select    " + sql ,e);
			 return accConfigRule;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccConfigRulesSQL","select " + sql ,e);
			}
	        }
	    
	 }
 protected static Collection select(Connection con) { 
	 int j = 0;
     PreparedStatement stmt = null;
     Vector AccConfigRuleData = new Vector();
     
	 try {
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
      
      ResultSet rs = stmt.executeQuery();
      
      while(rs.next()) {
   
    	  AccConfigRule accConfigRule = new AccConfigRule();
    	  accConfigRule.setId(rs.getInt(1));
     	 accConfigRule.setRuleName(rs.getString(2));
     	 accConfigRule.setCurrency(rs.getString(3));
     
     	 accConfigRule.setAttributes(rs.getString(4));
  
     
     	 accConfigRule.setRuleTYpe(rs.getString(5));
     	 accConfigRule.setCalDate(rs.getString(6));
     	 accConfigRule.setDayAdj(rs.getInt(7));
     	 accConfigRule.setPoID(rs.getInt(8));
    if( rs.getInt(9) == 0)
 	   accConfigRule.setDailyClosing(false) ;
    else 
 	   
 	   accConfigRule.setDailyClosing(true) ;
     
	        
	        AccConfigRuleData.addElement(accConfigRule);
      
      }
	 } catch (Exception e) {
		 commonUTIL.displayError("AccConfigRuleSQL",SELECTALL,e);
		 return AccConfigRuleData;
        
     }
     finally {
        try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("AccConfigRuleSQL",SELECTALL,e);
		}
     }
	return AccConfigRuleData;
   
 }
 public static Collection selectWhere(String where,Connection con ) {
	 int j = 0;
     PreparedStatement stmt = null;
     Vector accConfigRules = new Vector();
     String sql = SELECTWHERE;
	 try {
		 con.setAutoCommit(false);
		sql = sql  + where;
		 stmt = dsSQL.newPreparedStatement(con, sql );
      
      ResultSet rs = stmt.executeQuery();
      
      while(rs.next()) {
    	  AccConfigRule accConfigRule = new AccConfigRule();
    	  accConfigRule.setId(rs.getInt(1));
     	 accConfigRule.setRuleName(rs.getString(2));
     	 accConfigRule.setCurrency(rs.getString(3));
     
     	 accConfigRule.setAttributes(rs.getString(4));
  
     
     	 accConfigRule.setRuleTYpe(rs.getString(5));
     	 accConfigRule.setCalDate(rs.getString(6));
     	 accConfigRule.setDayAdj(rs.getInt(7));
     	 accConfigRule.setPoID(rs.getInt(8));
    if( rs.getInt(9) == 0)
 	   accConfigRule.setDailyClosing(false) ;
    else 
 	   
 	   accConfigRule.setDailyClosing(true) ;
     
    accConfigRules.add(accConfigRule);
      }
      commonUTIL.display("AccConfigRuleSQL","selectWhere "+sql);
	 } catch (Exception e) {
		 commonUTIL.displayError("AccConfigRuleSQL","selectWhere ",e);
		 return accConfigRules;
        
     }
     finally {
        try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("AccConfigRuleSQL","selectWhere",e);
		}
     }
	return accConfigRules;
    
 }



}
