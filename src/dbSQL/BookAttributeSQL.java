package src.dbSQL;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.Attribute;
import beans.BaseBean;
import beans.Book;
import beans.Attribute;
import util.commonUTIL;

public class BookAttributeSQL    implements AttributeProvider {

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
			
			 public static BaseBean saveBookAttribute(Vector<Attribute>  bookAttrVec, Connection con) {
				 try {
		             return insertBookAttribute(bookAttrVec, con);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("insertAttributeSQL","save",e);
		        	 return null;
		         }
			 }
			 public static boolean updateBookAttribute(Vector<Attribute> updateBookAttribute, Connection con) {
				 try {
		             return editBookAttribute(updateBookAttribute, con);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("BookAttributeSQL","update",e);
		        	 return false;
		         }
			 }
			 
			 public static boolean deleteBookAttribute(Vector<Attribute> deleteBookAttrVec, Connection con) {
				 try {
		             return removeBookAttribute(deleteBookAttrVec, con);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("BookAttributeSQL","update",e);
		        	 return false;
		         }
			 }
			 public static Collection<Attribute>  selectBookAttribute(int bookAttributeId, Connection con) {
				 try {
		             return  selectOneBookAttribute(bookAttributeId, con);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("BookAttributeSQL","select",e);
		        	 return null;
		         }
			 }
			 public static Collection<Attribute> selectALLBookAttribute(Connection con) {
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
			 
			 protected static  boolean editBookAttribute(Vector<Attribute> updateAttributeVec, Connection con ) {
				 
				 PreparedStatement stmt = null;
				 String sql = "";
				 
				 int size = updateAttributeVec.size();
				 
				 try {
					 con.setAutoCommit(false);
					 
					 stmt = dsSQL.newPreparedStatement(con, BOOK_ATTRIBUTE_UPDATE_SQL);
					 
					 Attribute bookAttr= null;
					 
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
			
			protected static boolean removeBookAttribute(Vector<Attribute> deleteBookAttrVec, Connection con ) {
			
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
			 
			 protected static BaseBean insertBookAttribute(Vector<Attribute> attributes, Connection con ) {
					
			        PreparedStatement stmt = null;
				 try {
					 con.setAutoCommit(false);
					 stmt = dsSQL.newPreparedStatement(con, INSERT_INTO_BOOK_ATTRIBUTE);
					
					 int size = attributes.size();
					 
					 for (int ii = 0; ii < size; ii++) {
						 
						 stmt.setInt(1,attributes.get(ii).getId());
						 stmt.setString(2,attributes.get(ii).getName());
				         stmt.setString(3,attributes.get(ii).getValue());
				         
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
					 return null;
			           
			        }
			        finally {
			           try {
						stmt.close();
					} catch (SQLException e) {
						
						commonUTIL.displayError("BookAttributeSQL", INSERT_INTO_BOOK_ATTRIBUTE,e);
					}
			        }
			        return (BaseBean) attributes;
			 }
			 
			 protected static Collection<Attribute> selectOneBookAttribute(int bookAttributeId,Connection con ) {

			        PreparedStatement stmt = null;
			        Vector<Attribute> bookAttribute = new Vector<Attribute>();
			        
			        String sql = "";
				 try {
					 
					 sql =  SELECTONE_BOOK_ATTRIBUTE + bookAttributeId;
					 stmt = dsSQL.newPreparedStatement(con, sql);
			         
			         ResultSet rs = stmt.executeQuery();
			        
			         while(rs.next()) {
			        	 
			        	 Attribute BookAttribute = new Attribute();
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

			 protected static Collection<Attribute> selectBookAttribute(Connection con) { 
				
			     PreparedStatement stmt = null;
			     Vector<Attribute> bookAttribute = new Vector<Attribute>();
			     
				 try {
					
					 stmt = dsSQL.newPreparedStatement(con, SELECTALL_BOOK_ATTRIBUTE );
			      
			      ResultSet rs = stmt.executeQuery();
			      
			      while(rs.next()) {
			   
			    	  Attribute BookAttribute = new Attribute();
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
			 
			 public static Collection<Attribute> selectWhereClauseBookAttribute(String sqlw,Connection con ) {

			     PreparedStatement stmt = null;
			     Vector<Attribute> bookAttribute = new Vector<Attribute>();
			     
			     String sql = "";
				 
			     try {
					 sql = SELECTWHERE_BOOK_ATTRIBUTE + sqlw;
					 stmt = dsSQL.newPreparedStatement(con, sql );
			      
			      ResultSet rs = stmt.executeQuery();
			      
			      while(rs.next()) {
			    	
			    	  Attribute BookAttribute = new Attribute();
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
		 
		@Override
		public void saveAttributes(Vector  attributes, Connection con) {
			// TODO Auto-generated method stub
			insertBookAttribute(attributes, con);
		}
		@Override
		public void updateAttributes(Vector attributes, Connection con) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public Vector<Attribute> selectAttributes(int id, Connection con) {
			// TODO Auto-generated method stub
			return null;
		}
 
		 
			 
		 
			
}
