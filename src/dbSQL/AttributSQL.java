package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.Attribute;
import beans.Book;
import beans.BookAttribute;
import beans.LEAttribute;
import util.commonUTIL;


public class AttributSQL {
	
	final static private String DELETE_FROM_ATTRIBUTE =
		"DELETE FROM ATTRIBUTE where id =? ";
	final static private String INSERT_FROM_ATTRIBUTE =
		"INSERT into ATTRIBUTE(id,attributeName ,type,attributeValue) values(?,?,?,?)";
	final static private String UPDATE_FROM_ATTRIBUTE =
		"UPDATE ATTRIBUTE set attributeName=?,type=?,attributeValue=? where id = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(id) DESC_ID FROM ATTRIBUTE ";
	final static private String SELECTALL =
		"SELECT id,attributeName ,type,attributeValue FROM ATTRIBUTE order by id";
	final static private String SELECT =
		"SELECT id,attributeName ,type,attributeValue FROM ATTRIBUTE where id =  ?";
	final static private String SELECTONE =
		"SELECT id,attributeName ,type,attributeValue FROM ATTRIBUTE where id =  " ;
	 
	final static private String SELECTWHERE =
		"SELECT id,attributeName ,type,attributeValue FROM ATTRIBUTE where    " ;
	 private static String getUpdateSQL(Attribute attribute) {
	      String updateSQL = "UPDATE ATTRIBUTE  set " +
	      		" id= " + attribute.getId() + 
	      		" ,type= '" + attribute.getType() + 	      		
	      		"' ,attributeName= '" + attribute.getName() + 	      		
	      		"' ,attributeValue= '" + attribute.getValue() + 	      		
	      		"'  where id= " + attribute.getId() +" and type = '"+attribute.getType()+"' and attributeName= '"+attribute.getName()+"'";
	      return updateSQL;
	     }
	 
	 final static private String DELETE_FROM_BOOK_ATTRIBUTE =
			"DELETE FROM BOOKATTRIBUTES where bookid =? ";
		final static private String INSERT_INTO_BOOK_ATTRIBUTE =
			"INSERT into BOOKATTRIBUTES(bookid, attributeName, attributeValue) values(?,?,?)";
		final static private String UPDATE_BOOK_ATTRIBUTE =
			"UPDATE BOOKATTRIBUTES set attributeName=?,attributeValue=? where bookid = ? ";
		final static private String SELECT_MAX_BOOK_ATTRIBUTE =
			"SELECT MAX(bookid) DESC_ID FROM BOOKATTRIBUTES ";
		final static private String SELECTALL_BOOK_ATTRIBUTE =
			"SELECT bookid, attributeName, attributeValue FROM BOOKATTRIBUTES order by bookid";
		final static private String SELECT_BOOK_ATTRIBUTE =
			"SELECT bookid, attributeName, attributeValue FROM BOOKATTRIBUTES where bookid =  ?";
		final static private String SELECTONE_BOOK_ATTRIBUTE =
			"SELECT bookid, attributeName, attributeValue FROM BOOKATTRIBUTES where bookid =  " ;
		 
		final static private String SELECTWHERE_BOOK_ATTRIBUTE =
			"SELECT bookid, attributeName,attributeValue FROM BOOKATTRIBUTES where    " ;
		
		final static private String BOOK_ATTRIBUTE_UPDATE_SQL = "UPDATE BOOKATTRIBUTES  set " +
  		" bookid= ?" +      		
  		",attributeName= ? " +  	      		
  		",attributeValue= ?" + 	      		
  		" where bookid= ?" + 
  		" and attributeName= ?"; 
		
		  final static private String DELETE_FROM_LE_ATTRIBUTE =
				"DELETE FROM LEATTRIBUTE where le_id =? ";
			final static private String INSERT_INTO__LE_ATTRIBUTE =
				"INSERT into LEATTRIBUTE(le_id,attributeName,attributeValue) values(?,?,?)";
			final static private String UPDATE__LE_ATTRIBUTE  =
				"UPDATE LEATTRIBUTE set attributeName=?,type=?,attributeValue=? where le_id = ? ";
			final static private String SELECT_MAX_LE_ATTRIBUTE  =
				"SELECT MAX(le_id) DESC_ID FROM LEATTRIBUTE ";
			final static private String SELECTALL_LE_ATTRIBUTE  =
				"SELECT le_id, attributeName, attributeValue FROM LEATTRIBUTE order by le_id";
			final static private String SELECT_LE_ATTRIBUTE  =
				"SELECT le_id, attributeName, attributeValue FROM LEATTRIBUTE where le_id =  ?";
			final static private String SELECTONE_LE_ATTRIBUTE  =
				"SELECT le_id, attributeName, attributeValue FROM LEATTRIBUTE where le_id =  " ;
			 
			final static private String SELECTWHERE_LE_ATTRIBUTE  =
				"SELECT le_id, attributeName, attributeValue FROM LEATTRIBUTE where    " ;
			
			final static private String LE_ATTRIBUTE_UPDATE_SQL = "UPDATE LEATTRIBUTE  set " +
	  		" le_id= ?" +      		
	  		",attributeName= ? " +  	      		
	  		",attributeValue= ?" + 	      		
	  		" where le_id= ?" + 
	  		" and attributeName= ?"; 
			

	 public static boolean save(Attribute insertAttribute, Connection con) {
		 try {
             return insert(insertAttribute, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("insertAttributeSQL","save",e);
        	 return false;
         }
	 }
	 public static boolean update(Attribute updateAttribute, Connection con) {
		 try {
             return edit(updateAttribute, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AttributeSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(Attribute deleteAttribute, Connection con) {
		 try {
             return remove(deleteAttribute, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AttributeSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static String selectTradeAttributes(String tradeId, Connection con) {
		 try {
             return selectTradeAttributesAsString(tradeId, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("insertAttributeSQL","save",e);
        	 return "";
         }
	 }
	 protected static String selectTradeAttributesAsString(String tradeId, Connection con ) {
		 
		    PreparedStatement stmt = null;
	       String attribute = "";
	       
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + tradeId);
	         
	         ResultSet rs = stmt.executeQuery();
	        
	         while(rs.next()) {
	        	 
	        	 attribute = rs.getString(2) + "=" + rs.getString(4) + ";"+attribute;
	        	
	         }
	         
	         commonUTIL.display("AttributeSQL","SELECTONE "+SELECTONE + tradeId);
	         return  attribute;
	        
		 } catch (Exception e) {
			 commonUTIL.displayError("AttributeSQL","SELECTONE" + tradeId,e);
			 return attribute;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("AttributeSQL","SELECTONE" + tradeId,e);
			}
	        }
	       
	 }
	 public static Collection selectAttribute(int AttributeId, Connection con) {
		 try {
             return  select(AttributeId, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AttributeSQL","select",e);
        	 return null;
         }
	 }
	 public static Collection selectALL(Connection con) {
		 try {
             return select(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AttributeSQL","selectALL",e);
        	 return null;
         }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
             return selectMax(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("AttributeSQL","selectMaxID",e);
        	 return 0;
         }
	 }
	 
	 protected static  boolean edit(Attribute updateAttribute, Connection con ) {
		 
		 PreparedStatement stmt = null;
	        String sql = getUpdateSQL(updateAttribute);
		 try {
			 con.setAutoCommit(false);
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, sql);
	            stmt.executeUpdate(sql);
			 con.commit();
			 commonUTIL.display("AttributeSQL ::  edit", sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("AttributeSQL","edit  " + sql,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("AttributeSQL","edit",e);
			}
	        }
	        return true;
	 }
	
	protected static boolean remove(Attribute deleteAttribute, Connection con ) {
	
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_ATTRIBUTE);
	            stmt.setInt(j++, deleteAttribute.getId());
	           
	            stmt.executeUpdate();
	            commonUTIL.display("AttributeSQL","remove"+ DELETE_FROM_ATTRIBUTE + " "+ deleteAttribute.getId());
		 } catch (Exception e) {
			 commonUTIL.displayError("AttributeSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("AttributeSQL","remove",e);
			}
	        }
	        return true;
	 }

	protected static int selectMax(Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);
	         
	         ResultSet rs = stmt.executeQuery();
	         while(rs.next())
	         j = rs.getInt("DESC_ID");
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("AttributeSQL",SELECT_MAX,e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("AttributeSQL",SELECT_MAX,e);
			}
	        }
	        return j;
	 }
	 
	 protected static boolean insert(Attribute inserAttribute, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			// int id = selectMax(con);
			 int j = 1;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_ATTRIBUTE);
			
			 stmt.setInt(1,inserAttribute.getId());
			 stmt.setString(2,inserAttribute.getName());
			 
	            stmt.setString(3, inserAttribute.getType());
	            stmt.setString(4,inserAttribute.getValue());
	            
	            stmt.executeUpdate();
	            con.commit();
	            commonUTIL.display("AttributeSQL","insert " + INSERT_FROM_ATTRIBUTE);
		 } catch (Exception e) {
			 commonUTIL.displayError("AttributeSQL",INSERT_FROM_ATTRIBUTE,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("bookSQL",INSERT_FROM_ATTRIBUTE,e);
			}
	        }
	        return true;
	 }
	 
	 protected static Collection select(int AttributeIn,Connection con ) {
		 
		    PreparedStatement stmt = null;
	        Vector Attributes = new Vector();
	       
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + AttributeIn);
	         
	         ResultSet rs = stmt.executeQuery();
	        
	         while(rs.next()) {
	        	 
	        	 Attribute Attribute = new Attribute();
	        	 Attribute.setId(rs.getInt(1));
	        	 Attribute.setName(rs.getString(2));
	        	 Attribute.setType(rs.getString(3));
	        	 Attribute.setValue(rs.getString(4));
	        
	        	 Attributes.add(Attribute);
	         
	         }
	         commonUTIL.display("AttributeSQL","SELECTONE "+SELECTONE);
	         return  Attributes;
	        
		 } catch (Exception e) {
			 commonUTIL.displayError("AttributeSQL","select",e);
			 return Attributes;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("AttributeSQL","selectMax",e);
			}
	        }
	       
	 }

	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Attributes = new Vector();
	     
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
   
	    	  Attribute Attribute = new Attribute();
	    	  Attribute.setId(rs.getInt(1));
	    	  Attribute.setName(rs.getString(2));
	    	  Attribute.setType(rs.getString(3));
        	 
	    	  Attribute.setValue(rs.getString(4));
        
	    	  Attributes.add(Attribute);
	      
	      }
	      
	      commonUTIL.display("AttributeSQL","selectWhereClause ");
		 
		 } catch (Exception e) {
			 commonUTIL.displayError("AttributeSQL",SELECTALL,e);
			 return Attributes;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("AttributeSQL",SELECTALL,e);
			}
	     }
	     return Attributes;
	 }
	 public static Collection selectWhereClause(String sqlw,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector Attributes = new Vector();
	     String sql = "";
		 try {
			 sql = SELECTWHERE + sqlw;
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  
	    	  Attribute Attribute = new Attribute();
	    	  Attribute.setId(rs.getInt(1));
	    	  Attribute.setName(rs.getString(2));
	    	  Attribute.setType(rs.getString(3));
	        	 
	    	  Attribute.setValue(rs.getString(4));
	        
	    	  Attributes.add(Attribute);
	    	 
	      }
	      
	      commonUTIL.display("AttributeSQL","selectWhereClause " + sql);
	      
		 } catch (Exception e) {
			 commonUTIL.displayError("AttributeSQL","selectWhereClause " + sql ,e);
			 return Attributes;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("AttributeSQL","selectMax",e);
			}
	     }
	     return Attributes;
	 }
	 
	 public static boolean saveBookAttribute(Vector<BookAttribute> bookAttrVec, Connection con) {
		 try {
             return insertBookAttribute(bookAttrVec, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("insertAttributeSQL","save",e);
        	 return false;
         }
	 }
	 public static boolean updateBookAttribute(Vector<BookAttribute> updateBookAttribute, Connection con) {
		 try {
             return editBookAttribute(updateBookAttribute, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("BookAttributeSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean deleteBookAttribute(Vector<BookAttribute> deleteBookAttrVec, Connection con) {
		 try {
             return removeBookAttribute(deleteBookAttrVec, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("BookAttributeSQL","update",e);
        	 return false;
         }
	 }
	 public static Collection<BookAttribute>  selectBookAttribute(int bookAttributeId, Connection con) {
		 try {
             return  selectOneBookAttribute(bookAttributeId, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("BookAttributeSQL","select",e);
        	 return null;
         }
	 }
	 public static Collection<BookAttribute> selectALLBookAttribute(Connection con) {
		 try {
             return selectBookAttribute(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("BookAttributeSQL","selectALL",e);
        	 return null;
         }
	 }
	 
	 public static int selectMaxBookAttributeID(Connection con) {
		 try {
             return selectMaxBookAttribute(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("BookAttributeSQL","selectMaxID",e);
        	 return 0;
         }
	 }
	 
	 protected static  boolean editBookAttribute(Vector<BookAttribute> updateAttributeVec, Connection con ) {
		 
		 PreparedStatement stmt = null;
		 String sql = "";
		 
		 int size = updateAttributeVec.size();
		 
		 try {
			 con.setAutoCommit(false);
			 
			 stmt = dsSQL.newPreparedStatement(con, BOOK_ATTRIBUTE_UPDATE_SQL);
			 
			 BookAttribute bookAttr= null;
			 
			 for (int ii = 0; ii < size; ii++) {
				 
				 bookAttr = updateAttributeVec.get(ii);
				 
				 stmt.setInt(1,bookAttr.getId());
				 stmt.setString(2,bookAttr.getName());
		         stmt.setString(3,bookAttr.getValue());
		         stmt.setInt(4,bookAttr.getId());
				 stmt.setString(5,bookAttr.getName());				
				 
				 stmt.addBatch();
			 }
			 
			 stmt.executeBatch();
			 con.commit();
			 
			 commonUTIL.display("BookAttributeSQL ::  editBookAttribute", BOOK_ATTRIBUTE_UPDATE_SQL);
			 
		 } catch (Exception e) {
			 
			 commonUTIL.displayError("BookAttributeSQL: : editBookAttribute  " , BOOK_ATTRIBUTE_UPDATE_SQL,e);
			 
			 try {
				con.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
			 commonUTIL.displayError("BookAttributeSQL","edit" + sql,e);
			}
	       
	     }
	        
	     return true;
	 }
	
	protected static boolean removeBookAttribute(Vector<BookAttribute> deleteBookAttrVec, Connection con ) {
	
		PreparedStatement stmt = null;
		
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_BOOK_ATTRIBUTE);
	            
			int size = deleteBookAttrVec.size();
			 
			for (int ii =0; ii < size; ii++) {
				 
				 stmt.setInt(1, deleteBookAttrVec.get(ii).getId());
				 
				 stmt.addBatch();
			 }

			stmt.executeBatch();
			con.commit();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("BookAttributeSQL :: Delete", DELETE_FROM_BOOK_ATTRIBUTE,e);
			 try {
				con.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			 
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("BookAttributeSQL :: Delete",  DELETE_FROM_BOOK_ATTRIBUTE,e);
			}
	        }
	        return true;
	 }

	protected static int selectMaxBookAttribute(Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECT_MAX_BOOK_ATTRIBUTE);
	         
	         ResultSet rs = stmt.executeQuery();
	         while(rs.next())
	         j = rs.getInt("DESC_ID");
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("BookAttributeSQL",SELECT_MAX_BOOK_ATTRIBUTE,e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("BookAttributeSQL",SELECT_MAX_BOOK_ATTRIBUTE,e);
			}
	        }
	        return j;
	 }
	 
	 protected static boolean insertBookAttribute(Vector<BookAttribute> bookAttrVec, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, INSERT_INTO_BOOK_ATTRIBUTE);
			
			 int size = bookAttrVec.size();
			 
			 for (int ii = 0; ii < size; ii++) {
				 
				 stmt.setInt(1,bookAttrVec.get(ii).getId());
				 stmt.setString(2,bookAttrVec.get(ii).getName());
		         stmt.setString(3,bookAttrVec.get(ii).getValue());
		         
		         stmt.addBatch();
		            
			 }
	            stmt.executeBatch();
	            con.commit();
	            commonUTIL.display("BookAttributeSQL","insert " + INSERT_INTO_BOOK_ATTRIBUTE);
		 } catch (Exception e) {
			 commonUTIL.displayError("BookAttributeSQL",INSERT_INTO_BOOK_ATTRIBUTE,e);
			 try {
				con.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("BookAttributeSQL", INSERT_INTO_BOOK_ATTRIBUTE,e);
			}
	        }
	        return true;
	 }
	 
	 protected static Collection<BookAttribute> selectOneBookAttribute(int bookAttributeId,Connection con ) {

	        PreparedStatement stmt = null;
	        Vector<BookAttribute> bookAttribute = new Vector<BookAttribute>();
	        
	        String sql = "";
		 try {
			 
			 sql =  SELECTONE_BOOK_ATTRIBUTE + bookAttributeId;
			 stmt = dsSQL.newPreparedStatement(con, sql);
	         
	         ResultSet rs = stmt.executeQuery();
	        
	         while(rs.next()) {
	        	 
	        	 BookAttribute BookAttribute = new BookAttribute();
	        	 BookAttribute.setId(rs.getInt(1));
	        	 BookAttribute.setName(rs.getString(2));
	        	 BookAttribute.setValue(rs.getString(3));
	        
	      
	        	 bookAttribute.add(BookAttribute);
	         
	         }
	         commonUTIL.display("BookAttributeSQL","SELECTONE_BOOK_ATTRIBUTE "+sql);
	         return  bookAttribute;
	        
		 } catch (Exception e) {
			 commonUTIL.displayError("BookAttributeSQL","SELECTONE_BOOK_ATTRIBUTE "+sql,e);
			 return bookAttribute;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("BookAttributeSQL selectMax",sql,e);
			}
	        }
	       
	 }

	 protected static Collection<BookAttribute> selectBookAttribute(Connection con) { 
		
	     PreparedStatement stmt = null;
	     Vector<BookAttribute> bookAttribute = new Vector<BookAttribute>();
	     
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL_BOOK_ATTRIBUTE );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	   
	    	  BookAttribute BookAttribute = new BookAttribute();
	    	  BookAttribute.setId(rs.getInt(1));
	    	  BookAttribute.setName(rs.getString(2));
	    	  BookAttribute.setValue(rs.getString(3));
	        
	    	  bookAttribute.add(BookAttribute);
	      
	      }
	      commonUTIL.display("BookAttributeSQL","selectWhereClause ");
		 } catch (Exception e) {
			 commonUTIL.displayError("BookAttributeSQL",SELECTALL_BOOK_ATTRIBUTE,e);
			 return bookAttribute;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("BookAttributeSQL",SELECTALL_BOOK_ATTRIBUTE,e);
			}
	     }
	     return bookAttribute;
	 }
	 
	 public static Collection<BookAttribute> selectWhereClauseBookAttribute(String sqlw,Connection con ) {

	     PreparedStatement stmt = null;
	     Vector<BookAttribute> bookAttribute = new Vector<BookAttribute>();
	     
	     String sql = "";
		 
	     try {
			 sql = SELECTWHERE_BOOK_ATTRIBUTE + sqlw;
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	
	    	  BookAttribute BookAttribute = new BookAttribute();
	    	  BookAttribute.setId(rs.getInt(1));
	    	  BookAttribute.setName(rs.getString(2));	        	 
	    	  BookAttribute.setValue(rs.getString(3));
	        
	    	  bookAttribute.add(BookAttribute);
	    	 
	      }
	      
	      commonUTIL.display("BookAttributeSQL","selectWhereClause " + sql);
	      
		 } catch (Exception e) {
			 commonUTIL.displayError("BookAttributeSQL","selectWhereClauseBookAttribute " + sql ,e);
			 return bookAttribute;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("BookAttributeSQL","selectWhereClauseBookAttribute",e);
			}
	     }
	     return bookAttribute;
	 }
	 
	 public static boolean saveLEAttribute(Vector<LEAttribute> leAttrVec , Connection con) {
		 try {
             return insertLEAttribute(leAttrVec, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("insertAttributeSQL","save",e);
        	 return false;
         }
	 }
	 public static boolean updateLEAttribute(Vector<LEAttribute> updateLEAttribute, Connection con) {
		 try {
             return editLEAttribute(updateLEAttribute, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("LEAttributeSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean deleteLEAttribute(Vector<LEAttribute> deleteLEAttributeVec, Connection con) {
		 try {
             return removeLEAttribute(deleteLEAttributeVec, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("LEAttributeSQL","update",e);
        	 return false;
         }
	 }
	 public static Collection<LEAttribute> selectLEAttribute(int leAttributeId, Connection con) {
		 try {
             return  selectOneLEAttribute(leAttributeId, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("LEAttributeSQL","select",e);
        	 return null;
         }
	 }
	 public static Collection<LEAttribute> selectALLLEAttribute(Connection con) {
		 try {
             return selectLEAttribute(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("LEAttributeSQL","selectALL",e);
        	 return null;
         }
	 }
	 
	 public static int selectMaxLEAttributeID(Connection con) {
		 try {
             return selectMaxLEAttribute(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("LEAttributeSQL","selectMaxID",e);
        	 return 0;
         }
	 }
	 
	 protected static  boolean editLEAttribute(Vector<LEAttribute> updateLEAttribute, Connection con ) {
		 
		 PreparedStatement stmt = null;
	        
		 try {
			 con.setAutoCommit(false);
			 
			 stmt = dsSQL.newPreparedStatement(con, LE_ATTRIBUTE_UPDATE_SQL);
			 
			 LEAttribute leAttr= null;
			 
			 int size = updateLEAttribute.size();
			 
			 for (int ii = 0; ii < size; ii++) {
				 
				 leAttr = updateLEAttribute.get(ii);
				 
				 stmt.setInt(1,leAttr.getId());
				 stmt.setString(2,leAttr.getName());
		         stmt.setString(3,leAttr.getValue());
		         stmt.setInt(4,leAttr.getId());
				 stmt.setString(5,leAttr.getName());				
				 
				 stmt.addBatch();
			 }
			 
			 stmt.executeBatch();
			 con.commit();
			 
			 commonUTIL.display("BookAttributeSQL ::  editBookAttribute", BOOK_ATTRIBUTE_UPDATE_SQL);
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("LEAttributeSQL edit", LE_ATTRIBUTE_UPDATE_SQL,e);
			 try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LEAttributeSQL edit",LE_ATTRIBUTE_UPDATE_SQL,e);
			}
	        }
	        return true;
	 }
	
	protected static boolean removeLEAttribute(Vector<LEAttribute> deleteLEAttributeVec, Connection con ) {
	
	     PreparedStatement stmt = null;
		 try {
			 int size = deleteLEAttributeVec.size();
			 
			 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_LE_ATTRIBUTE);
			 
			 for(int ii = 0; ii < size; ii++) {
				 
				 stmt.setInt(1, deleteLEAttributeVec.get(ii).getId());
				 stmt.addBatch();
			 
			 }

	            stmt.executeBatch();
	            con.commit();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("LEAttributeSQL remove",DELETE_FROM_LE_ATTRIBUTE,e);
			 try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("LEAttributeSQL remove", DELETE_FROM_LE_ATTRIBUTE,e);
			}
	        }
	        return true;
	 }

	protected static int selectMaxLEAttribute(Connection con ) {
		 
		 int j = 0;
	     
		 PreparedStatement stmt = null;
		 
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECT_MAX_LE_ATTRIBUTE);
	         
	         ResultSet rs = stmt.executeQuery();
	         while(rs.next())
	         j = rs.getInt("DESC_ID");
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("LEAttributeSQL SELECT_MAX_LE_ATTRIBUTE",SELECT_MAX_LE_ATTRIBUTE,e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LEAttributeSQL SELECT_MAX_LE_ATTRIBUTE",SELECT_MAX_LE_ATTRIBUTE,e);
			}
	        }
	        return j;
	 }
	 
	 protected static boolean insertLEAttribute(Vector<LEAttribute> leAttrVec , Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
		
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, INSERT_INTO__LE_ATTRIBUTE);
			
			 int size = leAttrVec.size();
			 
			 for(int ii = 0; ii < size; ii++) {
				 
				 stmt.setInt(1,leAttrVec.get(ii).getId());
				 stmt.setString(2,leAttrVec.get(ii).getName());
		         stmt.setString(3,leAttrVec.get(ii).getValue());
		         
		         stmt.addBatch();
			 }
	            stmt.executeBatch();
	            con.commit();
	            commonUTIL.display("LEAttributeSQL","insert " + INSERT_INTO__LE_ATTRIBUTE);
		 } catch (Exception e) {
			 commonUTIL.displayError("LEAttributeSQL",INSERT_INTO__LE_ATTRIBUTE,e);
			 try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("bookSQL",INSERT_INTO__LE_ATTRIBUTE,e);
			}
	        }
	        return true;
	 }
	 
	 protected static Collection<LEAttribute> selectOneLEAttribute(int leAttributeId,Connection con ) {

		 PreparedStatement stmt = null;
	     Vector<LEAttribute> leAttribute = new Vector<LEAttribute>();
	        
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE_LE_ATTRIBUTE + leAttributeId);
	         
	         ResultSet rs = stmt.executeQuery();
	        
	         while(rs.next()) {
	        	 
	        	 LEAttribute LEAttribute = new LEAttribute();
	        	 LEAttribute.setId(rs.getInt(1));
	        	 LEAttribute.setName(rs.getString(2));
	        	 LEAttribute.setValue(rs.getString(3));
	        
	        	 leAttribute.add(LEAttribute);
	         
	         }
	         commonUTIL.display("LEAttributeSQL","SELECTONE_LE_ATTRIBUTE "+SELECTONE_LE_ATTRIBUTE);
	         return  leAttribute;
	        
		 } catch (Exception e) {
			 commonUTIL.displayError("LEAttributeSQL","SELECTONE_LE_ATTRIBUTE" + SELECTONE_LE_ATTRIBUTE,e);
			 return leAttribute;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LEAttributeSQL","SELECTONE_LE_ATTRIBUTE" + SELECTONE_LE_ATTRIBUTE,e);
			}
	        }
	       
	 }

	 protected static Collection<LEAttribute> selectLEAttribute(Connection con) { 

	     PreparedStatement stmt = null;
	     Vector<LEAttribute> leAttribute = new Vector<LEAttribute>();
	     
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL_LE_ATTRIBUTE );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	   
	    	  LEAttribute LEAttribute = new LEAttribute();
	    	  LEAttribute.setId(rs.getInt(1));
	    	  LEAttribute.setName(rs.getString(2));
	    	  LEAttribute.setValue(rs.getString(3));
	        
	    	  leAttribute.add(LEAttribute);
	      
	      }
	      commonUTIL.display("LEAttributeSQL","selectWhereClause ");
		 } catch (Exception e) {
			 commonUTIL.displayError("LEAttributeSQL",SELECTALL_LE_ATTRIBUTE,e);
			 return leAttribute;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("LEAttributeSQL",SELECTALL_LE_ATTRIBUTE,e);
			}
	     }
	     return leAttribute;
	 }
	 public static Collection<LEAttribute> selectWhereClauseLEAttribute(String sqlw,Connection con ) {

	     PreparedStatement stmt = null;
	     Vector<LEAttribute> leAttribute = new Vector<LEAttribute>();
	     String sql = "";
		 try {
			 sql = SELECTWHERE_LE_ATTRIBUTE + sqlw;
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  
	    	  LEAttribute LEAttribute = new LEAttribute();
	    	  LEAttribute.setId(rs.getInt(1));
	    	  LEAttribute.setName(rs.getString(2));	        	 
	    	  LEAttribute.setValue(rs.getString(3));
	        
	    	  leAttribute.add(LEAttribute);
	    	 
	      }
	      
	      commonUTIL.display("LEAttributeSQL","SELECTWHERE_LE_ATTRIBUTE " + sql);
	      
		 } catch (Exception e) {
			 commonUTIL.displayError("LEAttributeSQL","SELECTWHERE_LE_ATTRIBUTE " + sql ,e);
			 return leAttribute; 
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("LEAttributeSQL","SELECTWHERE_LE_ATTRIBUTE",e);
			}
	     }
	     return leAttribute;
	 }

}

