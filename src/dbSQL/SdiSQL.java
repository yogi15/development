package dbSQL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;


import beans.Sdi;
import util.commonUTIL;


public class SdiSQL {
	
	
	final static private String tableName = "SDI";
	final static private String DELETE =
		"DELETE FROM " + tableName + "   where id =? ";
	final static private String INSERT =
		"INSERT into " + tableName + "(id,agentId,cpId,accountID,messageType,sdiformat,poid,attributes,leContacts,agentContacts,payrec,cash,method,products,currency,key,role,PRIORITY,PREFERRED,INTERMEDFID,INTERMEDSID,INTERMEDFCONTACT,INTERMEDSCONTACT,INTERMEDFACCOUNTNAME,INTERMEDSACCOUNTNAME, ACCOUNTNAME, POCONTACTS ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	/*final static private String UPDATE =
		"UPDATE " + tableName + " set agentId=?,cpId=?,accountID=?,messageType=?  where id = ? ";
	*/	
	//		",sdiformat=?,poid=?,attributes=?,leContacts=?,agentContacts=?,payrec=?,cash=?,method=?,products =?,currency=?,key=? where id = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(id) sdiformat_ID FROM " + tableName + " ";
	final static private String SELECTALL =
		"SELECT id,agentId,cpId,accountID,messageType,sdiformat,poid,attributes,leContacts,agentContacts,payrec,cash,method,products,currency,key,role ,PRIORITY,PREFERRED,INTERMEDFID,INTERMEDSID,INTERMEDFCONTACT,INTERMEDSCONTACT,INTERMEDFACCOUNTNAME,INTERMEDSACCOUNTNAME, accountName, pocontacts  FROM " + tableName + " ";
	/*final static private String SELECT =
		"SELECT id,agentId,cpId,accountID,messageType,sdiformat poid,attributes,leContacts,agentContacts,payrec,cash,method,products,currency,key,role,PRIORITY,PREFERRED,INTERMEDFID,INTERMEDSID,INTERMEDFCONTACT,INTERMEDSCONTACT,INTERMEDFACCOUNTNAME,INTERMEDSACCOUNTNAME, accountName, pocontacts    FROM " + tableName + " where id =  ?";*/
	final static private String SELECTWHERE =
		"SELECT id,agentId,cpId,accountID,messageType,sdiformat,poid,attributes,leContacts,agentContacts,payrec,cash,method,products,currency,key,role,PRIORITY,PREFERRED,INTERMEDFID,INTERMEDSID,INTERMEDFCONTACT,INTERMEDSCONTACT,INTERMEDFACCOUNTNAME,INTERMEDSACCOUNTNAME, accountName, pocontacts    FROM " + tableName + " where ";
	 static private String SELECTONE =
		"SELECT id,agentId,cpId,accountID,messageType,sdiformat,poid,attributes,leContacts,agentContacts,payrec,cash,method,products,currency ,key,role,PRIORITY,PREFERRED,INTERMEDFID,INTERMEDSID,INTERMEDFCONTACT,INTERMEDSCONTACT,INTERMEDFACCOUNTNAME,INTERMEDSACCOUNTNAME, accountName, pocontacts   FROM " + tableName + " where id =  ";
	 static private String SELECTSDIKEY =
		"SELECT id,agentId,cpId,accountID,messageType,sdiformat,poid,attributes,leContacts,agentContacts,payrec,cash,method,products,currency ,key,role ,PRIORITY,PREFERRED,INTERMEDFID,INTERMEDFCONTACT,INTERMEDSCONTACT,INTERMEDFCONTACT,INTERMEDFACCOUNTNAME,INTERMEDSACCOUNTNAME, accountName, pocontacts  FROM " + tableName + " where key =  ";
	 private static String getUpdateSQL(Sdi sdi) {
	      String updateSQL = "UPDATE " + tableName + " set " +
	      		" agentId= " + sdi.getAgentId() + 
	      		" ,cpId= " + sdi.getCpId() + 
	      		" ,accountID= " + sdi.getAccountID() + 
	      		" ,messageType= '" + sdi.getMessageType() + 
	      		"',sdiformat = '" + sdi.getsdiformat() + 
	      		"',poid= " + sdi.getPoId() + 
	      		" ,attributes= '" + sdi.getAttributes() + 
	      		"',leContacts= '" + sdi.getLeContacts() + 
	      		"',agentContacts= '" + sdi.getAgentContacts() + 
	      		"',payrec= '" + sdi.getPayrec() + 
	      		"',cash= '" + sdi.getCash() + 
	      		"',method= '" + sdi.getMessageType() + 
	      		"',products= '" + sdi.getProducts() + 
	      		"',currency= '" + sdi.getCurrency() + 
	      		"',key= '" + sdi.getkey() + 
	      		"',role = '" + sdi.getRole() + 
	      		"',ACCOUNTNAME = '" + sdi.getGlName() + 
	      		" ',PRIORITY ='" + sdi.getPriority() + 
	      		" ',PREFERRED ='" + sdi.getPreferred() +
	      		" ',INTERMEDFID ='" + sdi.getInterMid1() +
	      		" ',INTERMEDSID = '" + sdi.getInterMid2()+
	      		" ',INTERMEDFCONTACT ='" + sdi.getInterMid1Contact() + 
	      		" ',INTERMEDSCONTACT ='" + sdi.getInterMid2Contact() +
	      		" ',INTERMEDFACCOUNTNAME ='" + sdi.getInterMid1glName() +
	      		" ',INTERMEDSACCOUNTNAME ='" + sdi.getInterMid2glName() +
	      		"', pocontacts = '" + sdi.getPoContact() +
	      		"'  where id= " + sdi.getId();
	      return updateSQL;
	     }
	
	 public static Sdi save(Sdi insertSdi, Connection con) {
		 try {
             return insert(insertSdi, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("SdiSQL","save",e);
        	 return null;
         }
	 }
	 public static Sdi update(Sdi updateSdi, Connection con) {
		 try {
             return edit(updateSdi, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("SdiSQL","update",e);
        	 return null;
         }
	 }
	 
	 public static boolean delete(Sdi deleteSdi, Connection con) {
		 try {
             return remove(deleteSdi, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("SdiSQL","update",e);
        	 return false;
         }
	 }
	 
	 protected static boolean remove(Sdi deleteSdi, Connection con ) {
			
	     PreparedStatement stmt = null;
		 
	     try {
			 
	    	 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, DELETE);
	         stmt.setInt(j++, deleteSdi.getId());
	           
	         stmt.executeUpdate();
			 
	         commonUTIL.display("SdiSQL", DELETE +  deleteSdi.getId());
	         
		 } catch (Exception e) {
			 
			 commonUTIL.displayError("SdiSQL","remove",e);
			 return false;
	           
	     } finally {	       
	    	 try {			
	    		 stmt.close();
	    	 } catch (SQLException e) {
	    		 commonUTIL.displayError("SdiSQL","remove",e);
			 }	        
	     }
	     
	     return true;
	 }
	 
	 
	 public static Vector<Sdi> selectSDI(int SdiId, Connection con) {
		 try {
          return (Vector<Sdi>) selectSDIOne(SdiId, con);
      }catch(Exception e) {
     	 commonUTIL.displayError("SdiSQL","selectSDI",e);
     	 return null;
      }
	 }
	 public static Collection<Sdi>  selectSDIWhere(String where, Connection con) {
		 try {
          return  SDIWhere(where,  con);
      }catch(Exception e) {
     	 commonUTIL.displayError("SdiSQL","selectSDIWhere",e);
     	 return null;
      }
	 }
	 public static Collection<Sdi>  selectALL(Connection con) {
		 try {
          return select(con);
      }catch(Exception e) {
     	 commonUTIL.displayError("SdiSQL","selectALL",e);
     	 return null;
      }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
          return selectMax(con);
      }catch(Exception e) {
     	 commonUTIL.displayError("SdiSQL","selectMaxID",e);
     	 return 0;
      }
	 }
	 
	 protected static  Sdi edit(Sdi updateSdi, Connection con ) {
		 
	        PreparedStatement stmt = null;
	        String sql = getUpdateSQL(updateSdi);
		 try {
			 //con.setAutoCommit(false);
			 
			 stmt = dsSQL.newPreparedStatement(con, sql);            
              stmt.executeUpdate(sql);
              con.commit();
	         
              commonUTIL.display("SdiSQL  ::  edit", sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("SdiSQL","edit",e);
			 return null;
	           
	        } finally {
	           try {
	        	   stmt.close();
	           } catch (SQLException e) {
	        	   commonUTIL.displayError("SdiSQL  ","edit",e);
	           }
	        }
	        
	        return updateSdi;
	 }
	
	 
	 protected static int selectMax(Connection con ) {
		 
		 int j = 0;
	     PreparedStatement stmt = null;
		 
	     try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);
	         
	         ResultSet rs = stmt.executeQuery();
	         while(rs.next())
	         j = rs.getInt("sdiformat_ID");
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("SdiSQL","selectMax",e);
			 return j;
	           
	        } finally {
	          
	        	try {
	        		stmt.close();
	        	} catch (SQLException e) {
	        		commonUTIL.displayError("SdiSQL","selectMax",e);
	        	}
	        }
	        
	        return j;
	 }
      
      protected static Sdi insert(Sdi inserSdi, Connection con ) {
			
	     PreparedStatement stmt = null;
	     
		 try {
			
			int id = selectMax(con) +1;
			
			stmt = dsSQL.newPreparedStatement(con, INSERT);
			 
			stmt.setInt(1,id);			
	        stmt.setInt(2, inserSdi.getAgentId());	        
            stmt.setInt(3, inserSdi.getCpId());
            stmt.setInt(4, inserSdi.getAccountID());
            stmt.setString(5, inserSdi.getMessageType());
            stmt.setString(6, inserSdi.getsdiformat());
            stmt.setInt(7, inserSdi.getPoId());
            stmt.setString(8, inserSdi.getAttributes());
            stmt.setString(9, inserSdi.getLeContacts());
            stmt.setString(10, inserSdi.getAgentContacts());
            stmt.setString(11, inserSdi.getPayrec());
            stmt.setString(12, inserSdi.getCash());
            stmt.setString(13, inserSdi.getMessageType());
            stmt.setString(14, inserSdi.getProducts());
            stmt.setString(15, inserSdi.getCurrency());
            stmt.setString(16, inserSdi.getkey());
            stmt.setString(17, inserSdi.getRole());
            stmt.setInt(18, inserSdi.getPriority());
            stmt.setInt(19, inserSdi.getPreferred());
            stmt.setInt(20, inserSdi.getInterMid1());
            stmt.setInt(21, inserSdi.getInterMid2());
            stmt.setString(22, inserSdi.getInterMid1Contact());
            stmt.setString(23, inserSdi.getInterMid2Contact());
            stmt.setString(24, inserSdi.getInterMid1glName());
            stmt.setString(25, inserSdi.getInterMid2glName());
            stmt.setString(26, inserSdi.getGlName());
            stmt.setString(27, inserSdi.getPoContact());
            
            stmt.executeUpdate();
		    
            con.commit();
		    
            commonUTIL.display("SdiSQL","insert " +INSERT );
		    
            inserSdi.setId(id);
			  
		 } catch (Exception e) {
			 commonUTIL.displayError("SdiSQL","insert",e);
			 return null;
	           
	     } finally {
	           
	    	 try {
				stmt.close();
	    	 } catch (SQLException e) {
	    		 commonUTIL.displayError("SdiSQL","insert",e);
			 }
	        
	     }
	        return inserSdi;
	 }
	 
	 public static Collection<Sdi>  SDIWhere(String wherecon,Connection con ) {
		 
	     PreparedStatement stmt = null;
	     Vector<Sdi> Sdis = new Vector<Sdi> ();
	     String sql = SELECTWHERE;
	     sql = sql + wherecon;
	        
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, sql);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        	 
				Sdi Sdi = new Sdi();
				 
				Sdi.setId(rs.getInt(1));
				Sdi.setAgentId(rs.getInt(2));
				Sdi.setCpId(rs.getInt(3));
				Sdi.setAccountID(rs.getInt(4));			      		       
				Sdi.setMessageType(rs.getString(5));
				Sdi.setsdiformat(rs.getString(6));
				Sdi.setPoId(rs.getInt(7));
				Sdi.setAttributes(rs.getString(8));
				Sdi.setLeContacts(rs.getString(9));
				Sdi.setAgentContacts(rs.getString(10));
				Sdi.setPayrec(rs.getString(11));
				Sdi.setCash(rs.getString(12));
				Sdi.setMessageType(rs.getString(13));
				Sdi.setProducts(rs.getString(14));
				Sdi.setCurrency(rs.getString(15));
				Sdi.setkey(rs.getString(16));
				Sdi.setRole(rs.getString(17));
				Sdi.setInterMid1(rs.getInt(20));
				Sdi.setInterMid2(rs.getInt(21));
				Sdi.setPriority(rs.getInt(18));
				Sdi.setPreferred(rs.getInt(19));
				Sdi.setInterMid1Contact(rs.getString(22));
				Sdi.setInterMid2Contact(rs.getString(23));
				Sdi.setInterMid1glName(rs.getString(24));
				Sdi.setInterMid2glName(rs.getString(25));
				Sdi.setGlName(rs.getString(26));
				Sdi.setPoContact(rs.getString(27));
				Sdis.add(Sdi);
	         
	         }
	         
	         commonUTIL.display("SdiSQL","select " +sql );
		 
		 } catch (Exception e) {
			 
			 commonUTIL.displayError("SdiSQL","select" + sql,e);
			 return Sdis;
	           
	        } finally {	          
	        	try {	        		
	        		stmt.close();
			   	} catch (SQLException e) {
	        		commonUTIL.displayError("SdiSQL","select",e);			
	        	}
	        }
	        
	        return Sdis;
	 }
     
	 public static boolean SDIKeyWhere(String wherecon,Connection con ) {
		 
        PreparedStatement stmt = null;
        String key = SELECTSDIKEY ;
        key = key + "'" + wherecon +"'";
        boolean exists = false;
		
        try {
			
			 stmt = dsSQL.newPreparedStatement(con,key);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	        if(rs.next()) {
	        	exists =  true;
	         
	         }else {
	        	 exists = false;
	         }
	        
	        commonUTIL.display("SdiSQL","SDIKeyWhere " + key);
		 
		 } catch (Exception e) {
			
			 commonUTIL.displayError("SdiSQL","SDIKeyWhere ",e);
			 return false;
	        
		 } finally {	           
			 try {				
				 stmt.close();
			 } catch (SQLException e) {
				commonUTIL.displayError("SdiSQL","selectMax",e);
			 }
	      }
	      return exists;
	 }


	 protected static Collection<Sdi>  select(Connection con) { 

	     PreparedStatement stmt = null;
	     Vector<Sdi>  Sdis = new Vector<Sdi> ();
	     
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
			 ResultSet rs = stmt.executeQuery();
	      
		      while(rs.next()) {
	
				Sdi Sdi = new Sdi();
				
				Sdi.setId(rs.getInt(1));
				Sdi.setAgentId(rs.getInt(2));
				Sdi.setCpId(rs.getInt(3));
				Sdi.setAccountID(rs.getInt(4));			      		       
				Sdi.setMessageType(rs.getString(5));
				Sdi.setsdiformat(rs.getString(6));
				Sdi.setPoId(rs.getInt(7));
				Sdi.setAttributes(rs.getString(8));
				Sdi.setLeContacts(rs.getString(9));
				Sdi.setAgentContacts(rs.getString(10));
				Sdi.setPayrec(rs.getString(11));
				Sdi.setCash(rs.getString(12));
				Sdi.setMessageType(rs.getString(13));
				Sdi.setProducts(rs.getString(14));
				Sdi.setCurrency(rs.getString(15));
				Sdi.setkey(rs.getString(16));
				Sdi.setRole(rs.getString(17));
				Sdi.setInterMid1(rs.getInt(20));
				Sdi.setInterMid2(rs.getInt(21));
				Sdi.setPriority(rs.getInt(18));
				Sdi.setPreferred(rs.getInt(19));
				Sdi.setInterMid1Contact(rs.getString(22));
				Sdi.setInterMid2Contact(rs.getString(23));
				Sdi.setInterMid1glName(rs.getString(24));
				Sdi.setInterMid2glName(rs.getString(25));
				Sdi.setGlName(rs.getString(26));
				Sdi.setPoContact(rs.getString(27));
				
				Sdis.add(Sdi);
		      
		      }
		 } catch (Exception e) {
			 
			 commonUTIL.displayError("SdiSQL","select",e);
			 return Sdis;
	        
	     } finally {	       
	    	 try {			
	    		 stmt.close();
			 } catch (SQLException e) {
				commonUTIL.displayError("SdiSQL","selectMax",e);
			 }
	     }	     
	     return Sdis;
	 }
	 
	 protected static Collection<Sdi>  selectSDIOne(int sdiId,Connection con ) {
	
	     PreparedStatement stmt = null;
	     Vector<Sdi>  Sdis = new Vector<Sdi> ();
	     
		 try {
			
			 String sql = SELECTONE;
			 sql  = sql + sdiId;
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
		      while(rs.next()) {
		    	  
				Sdi Sdi = new Sdi();
				
				Sdi.setId(rs.getInt(1));
				Sdi.setAgentId(rs.getInt(2));
				Sdi.setCpId(rs.getInt(3));
				Sdi.setAccountID(rs.getInt(4));			      		       
				Sdi.setMessageType(rs.getString(5));
				Sdi.setsdiformat(rs.getString(6));
				Sdi.setPoId(rs.getInt(7));
				Sdi.setAttributes(rs.getString(8));
				Sdi.setLeContacts(rs.getString(9));
				Sdi.setAgentContacts(rs.getString(10));
				Sdi.setPayrec(rs.getString(11));
				Sdi.setCash(rs.getString(12));
				Sdi.setMessageType(rs.getString(13));
				Sdi.setProducts(rs.getString(14));
				Sdi.setCurrency(rs.getString(15));
				Sdi.setkey(rs.getString(16));
				Sdi.setRole(rs.getString(17));
				Sdi.setInterMid1(rs.getInt(20));
				Sdi.setInterMid2(rs.getInt(21));
				Sdi.setPriority(rs.getInt(18));
				Sdi.setPreferred(rs.getInt(19));
				Sdi.setInterMid1Contact(rs.getString(22));
				Sdi.setInterMid2Contact(rs.getString(23));
				Sdi.setInterMid1glName(rs.getString(24));
				Sdi.setInterMid2glName(rs.getString(25));
				Sdi.setGlName(rs.getString(26));
				Sdi.setPoContact(rs.getString(27));
				
				Sdis.add(Sdi);
		      
		      }
		 } catch (Exception e) {
			 
			 commonUTIL.displayError("SdiSQL",SELECTONE,e);
			 return Sdis;
	        
	     } finally {	        
	    	 try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("SdiSQL",SELECTONE,e);
			}
	     }
	     return Sdis;
	 }
	
}
