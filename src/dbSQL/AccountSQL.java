package dbSQL;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;

import beans.Account;


public class AccountSQL  { 
	
	
	final static private String DELETE_FROM_ACCOUNT =
			"DELETE FROM Account where id =? ";
		final static private String INSERT_FROM_ACCOUNT =
			"INSERT into account(id,accountName,currency,accountdesc,attributes,type,closingdate,createdate,parentID,poid,leid) values(?,?,?,?,?,?,?,?,?,?,?)";
		final static private String UPDATE_FROM_ACCOUNT =
			"UPDATE Account set le_id=?,Account_name=? where Accountno = ? ";
		final static private String SELECT_MAX =
			"SELECT MAX(id) DESC_ID FROM ACCOUNT ";
		final static private String SELECTALL =
			"SELECT id,accountName,currency,accountdesc,attributes,type,closingdate,createdate,parentID,poid,leid  FROM account order by id";
		final static private String SELECTWHERE =
			"SELECT id,accountName,currency,accountdesc,attributes,type,closingdate,createdate,parentID,poid,leid  FROM account where    ";
		 static private String SELECTONE =
			"SELECT id,accountName,currency,accountdesc,attributes,type,closingdate,createdate,parentID,poid,leid  FROM account where id  =  " ;
		 
		 static private String SELECTTB =
					" select a.id,a.accountName parent,b.id,b.accountName child, b.parentid  from account a,account b where a.id(+)  = b.parentid  and b.parentid >=0 order by b.parentid " ;
		 
		 private static String getUpdateSQL(Account account) {
		      String updateSQL = "UPDATE account  set " +
					" accountName= '" + account.getAccountName()+ 	
					" ',currency= '" + account.getCurrency() + 	
					" ',accountdesc= '" + account.getDesc() + 	
					" ',attributes= '" + account.getAttributes() + 	
					" ',type= '" +account.getType() + 	
					" ',closingdate= '" + account.getClosingDate()+ 	
					" ',createdate= '" + account.getCreationDate() + 	
		      		"' ,parentID= " + account.getParentID()    + 	
		      		", poid= " + account.getPoid() + 	
		      		",leid= " + account.getCpId() + 	
		      		" where id= " + account.getId();
		      return updateSQL;
		     }
		 
		 
		 public static int save(Account insertAccount, Connection con) {
			 try {
	             return insert(insertAccount, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccountSQL","save",e);
	        	 return -1;
	         }
		 }
		 public static boolean update(Account updateAccount, Connection con) {
			 try {
	             return edit(updateAccount, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccountSQL","update",e);
	        	 return false;
	         }
		 }
		 
		 public static boolean delete(Account deleteAccount, Connection con) {
			 try {
	             return remove(deleteAccount, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccountSQL","update",e);
	        	 return false;
	         }
		 }
		 public static Account selectAccount(int AccountId, Connection con) {
			 try {
	             return  select(AccountId, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccountSQL","select",e);
	        	 return null;
	         }
		 }
		 public static Collection selectALL(Connection con) {
			 try {
	             return select(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccountSQL","selectALL",e);
	        	 return null;
	         }
		 }
		 
		 private static int selectMaxID(Connection con) {
			 try {
	             return selectMax(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("AccountSQL","selectMaxID",e);
	        	 return 0;
	         }
		 }
		 
		 protected static  boolean edit(Account updateAccount, Connection con ) {
			 
		        PreparedStatement stmt = null;
		        String sql = getUpdateSQL(updateAccount);
			 try {
				 con.setAutoCommit(false);
				 int j = 1;
				 stmt = dsSQL.newPreparedStatement(con, sql);
		            stmt.executeUpdate(sql);
				 con.commit();
				 commonUTIL.display("AccountSQL ::  edit", sql);
			 } catch (Exception e) {
				 commonUTIL.displayError("AccountSQL","edit " +  sql,e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccountSQL","edit",e);
				}
		        }
		        return true;
		 }
		
		protected static boolean remove(Account deleteAccount, Connection con ) {
		
		        PreparedStatement stmt = null;
			 try {
				 int j = 1;
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_ACCOUNT);
		            stmt.setInt(j++, deleteAccount.getId());
		           
		            stmt.executeUpdate();
				 con.commit();
			 } catch (Exception e) {
				 commonUTIL.displayError("AccountSQL","remove",e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccountSQL","remove",e);
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
				 commonUTIL.displayError("AccountSQL",SELECT_MAX,e);
				 return j;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("AccountSQL",SELECT_MAX,e);
				}
		        }
		        return j;
		 }
	
	 protected static int insert(Account insertAccount, Connection con ) {
			
	        PreparedStatement stmt = null;
	        int id =0;
		 try {
			 con.setAutoCommit(false);
			 id = selectMax(con);
			 id = id +1;
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_ACCOUNT);
			 stmt.setInt(1,id);
			
			 stmt.setString(2,insertAccount.getAccountName());
			 stmt.setString(3,insertAccount.getCurrency());
			 stmt.setString(4,insertAccount.getDesc());
	            stmt.setString(5, insertAccount.getAttributes());
	            stmt.setString(6,insertAccount.getType() );
	            stmt.setString(7, insertAccount.getClosingDate());
	            stmt.setString(8,insertAccount.getCreationDate());
	            stmt.setInt(9, insertAccount.getParentID());
	            stmt.setInt(10,insertAccount.getPoid() );
	            stmt.setInt(11,insertAccount.getCpId());
	            
	            stmt.executeUpdate();
	            con.commit();
	            commonUTIL.display("AccountSQL",INSERT_FROM_ACCOUNT);
		 } catch (Exception e) {
			 commonUTIL.displayError("AccountSQL",INSERT_FROM_ACCOUNT,e);
			 return -1;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccountSQL",INSERT_FROM_ACCOUNT,e);
			}
	        }
	        return id;
	 }
	 
	 

	 protected static Account select(int accoutID,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Account account = new Account();
	        String sql = SELECTONE;
	        sql = sql + accoutID  + "  order by id ";
	    
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	
	       
	        account.setId(rs.getInt(1));
	        account.setAccountName(rs.getString(2));
	        account.setCurrency(rs.getString(3));
	        
	        account.setDesc(rs.getString(4));
	        account.setAttributes(rs.getString(5));
	        
	        account.setType(rs.getString(6));
	        account.setClosingDate(rs.getString(7));
	        account.setCreationDate(rs.getString(8));
	        account.setParentID(rs.getInt(9));
	        account.setPoid(rs.getInt(10));
	        account.setCpId(rs.getInt(11));
	        
	       
	        
	      
	       
	         
	         }
	         commonUTIL.display("AccountsSQL"," select " + sql);
	         return account;
		 } catch (Exception e) {
			 commonUTIL.displayError("AccountsSQL","select    " + sql ,e);
			 return account;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccountsSQL","select " + sql ,e);
			}
	        }
	    
	 }
		 


	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector accountData = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	   
	    	  Account account = new Account();
		        account.setId(rs.getInt(1));
		        account.setAccountName(rs.getString(2));
		        account.setCurrency(rs.getString(3));
		        
		        account.setDesc(rs.getString(4));
		        account.setAttributes(rs.getString(5));
		        
		        account.setType(rs.getString(6));
		        account.setClosingDate(rs.getString(7));
		        account.setCreationDate(rs.getString(8));
		        account.setParentID(rs.getInt(9));
		        account.setPoid(rs.getInt(10));
		        account.setCpId(rs.getInt(11));
		        
		        accountData.addElement(account);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("AccountSQL",SELECTALL,e);
			 return accountData;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccountSQL",SELECTALL,e);
			}
	     }
		return accountData;
	   
	 }
	 
	 
	 public static Collection getTB(Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector accounts = new Vector();
	     String sql = SELECTWHERE;
	  //   sql = sql + where;
		 try {
			
			sql = SELECTTB;
			 stmt = dsSQL.newPreparedStatement(con,sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Account account = new Account();
	    	//  a.id parentID ,a.accountName parentAccName ,b.id childID ,b.accountName childAccName
		        account.setId(rs.getInt(3));
		        account.setAccountName(rs.getString(4));
		       
		        
		        account.setDesc(rs.getString(2));
		        
		        account.setParentID(rs.getInt(5));
		       
		        accounts.add(account);
	      
	      }
	      commonUTIL.display("AccountSQL","SELECTTB  "+sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("AccountSQL","SELECTTB  ",e);
			 return accounts;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccountSQL","selectWhere",e);
			}
	     }
		return accounts;
	    
	 }
	 public static Collection selectWhere(String where,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector accounts = new Vector();
	     String sql = SELECTWHERE;
	  //   sql = sql + where;
		 try {
			 con.setAutoCommit(false);
			sql = sql  + where + "  order by id ";
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Account account = new Account();
		        account.setId(rs.getInt(1));
		        account.setAccountName(rs.getString(2));
		        account.setCurrency(rs.getString(3));
		        
		        account.setDesc(rs.getString(4));
		        account.setAttributes(rs.getString(5));
		        
		        account.setType(rs.getString(6));
		        account.setClosingDate(rs.getString(7));
		        account.setCreationDate(rs.getString(8));
		        account.setParentID(rs.getInt(9));
		        account.setPoid(rs.getInt(10));
		        account.setCpId(rs.getInt(11));
		        accounts.add(account);
	      
	      }
	      commonUTIL.display("AccountSQL","selectWhere "+sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("AccountSQL","selectWhere ",e);
			 return accounts;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("AccountSQL","selectWhere",e);
			}
	     }
		return accounts;
	    
	 }


}
