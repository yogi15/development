package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.Book;
import util.commonUTIL;


public class BookSQL {
	
	final static private String DELETE_FROM_BOOK =
		"DELETE FROM BOOK where bookno =? ";
	final static private String INSERT_FROM_BOOK =
		"INSERT into BOOK(bookno,le_id,book_name,folderID,attributes) values(?,?,?,?,?)";
	final static private String UPDATE_FROM_BOOK =
		"UPDATE BOOK set le_id=?,book_name=? where bookno = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(bookno) DESC_ID FROM BOOK ";
	final static private String SELECTALL =
		"SELECT bookno,le_id,book_name,folderID,attributes FROM BOOK order by bookno";
	final static private String SELECT =
		"SELECT title FROM BOOK where bookno =  ?";
	 static private String SELECTONE =
		"SELECT bookno,book_name,le_id,folderID,attributes FROM book where bookno =  " ;
	 static private String SELECTWHERE =
				"SELECT bookno,book_name,le_id,folderID,attributes FROM book where   " ;
	 
	 
	 private static String getUpdateSQL(Book book) {
	      String updateSQL = "UPDATE book  set " +
	      		" le_id= " + book.getLe_id() + 
	      		" ,book_name= '" + book.getBook_name() + 	      		
	      		"' ,folderID= " + book.getFolderID() + 	      		
	      		" ,attributes= '" + book.getAttributes() + 	      		
	      		"'  where bookno= " + book.getBookno();
	      return updateSQL;
	     }
	 
	 public static int save(Book insertBook, Connection con) {
		 try {
             return insert(insertBook, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("bookSQL","save",e);
        	 return -1;
         }
	 }
	 public static boolean update(Book updateBook, Connection con) {
		 try {
             return edit(updateBook, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("bookSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(Book deleteBook, Connection con) {
		 try {
             return remove(deleteBook, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("bookSQL","update",e);
        	 return false;
         }
	 }
	 public static Book selectBook(int BookId, Connection con) {
		 try {
             return  select(BookId, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("bookSQL","select",e);
        	 return null;
         }
	 }
	 public static Collection selectALL(Connection con) {
		 try {
             return select(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("bookSQL","selectALL",e);
        	 return null;
         }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
             return selectMax(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("bookSQL","selectMaxID",e);
        	 return 0;
         }
	 }
	 
	 protected static  boolean edit(Book updateBook, Connection con ) {
		 
	        PreparedStatement stmt = null;
	        String sql = getUpdateSQL(updateBook);
		 try {
			 con.setAutoCommit(false);
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, sql);
	            stmt.executeUpdate(sql);
			 con.commit();
			 commonUTIL.display("bookSQL ::  edit", sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("bookSQL","edit",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL","edit",e);
			}
	        }
	        return true;
	 }
	
	protected static boolean remove(Book deleteBook, Connection con ) {
	
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_BOOK);
	            stmt.setInt(j++, deleteBook.getBookno());
	           
	            stmt.executeUpdate();
			 con.commit();
		 } catch (Exception e) {
			 commonUTIL.displayError("bookSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL","remove",e);
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
			 commonUTIL.displayError("bookSQL",SELECT_MAX,e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL",SELECT_MAX,e);
			}
	        }
	        return j;
	 }
	 
	 protected static int insert(Book inserbook, Connection con ) {
			
	        PreparedStatement stmt = null;
	        int id  = 0;
		 try {
			 con.setAutoCommit(false);
			  id = selectMax(con);
			 id = id + 1;
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_BOOK);
			 stmt.setInt(1,id);
	            stmt.setString(3, inserbook.getBook_name());
	            stmt.setInt(2,inserbook.getLe_id() );
	            stmt.setInt(4,inserbook.getFolderID() );
	            stmt.setString(5,inserbook.getAttributes());
	            
	            stmt.executeUpdate();
	            con.commit();
	            return id;
		 } catch (Exception e) {
			 commonUTIL.displayError("bookSQL",INSERT_FROM_BOOK,e);
			 return -1;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL",INSERT_FROM_BOOK,e);
			}
	        }
	     
	 }
	 
	 protected static Book select(int bookIn,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector books = new Vector();
	        Book book = new Book();
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + bookIn);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        
	        book.setBookno(rs.getInt(1));
	        book.setBook_name(rs.getString(2));
	        book.setLe_id(rs.getInt(3));
	        book.setFolderID(rs.getInt(4));
	        book.setAttributes(rs.getString(5));
	       
	        
	      
	       return book;
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("bookSQL","select",e);
			 return book;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL","selectMax",e);
			}
	        }
	        return book;
	 }

	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector books = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	   
	     Book book = new Book();
	        book.setBookno(rs.getInt(1));
	        book.setBook_name(rs.getString(3));
	        book.setLe_id(rs.getInt(2));
	        book.setFolderID(rs.getInt(4));
	        book.setAttributes(rs.getString(5));
	        books.add(book);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("bookSQL",SELECTALL,e);
			 return books;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL",SELECTALL,e);
			}
	     }
	     return books;
	 }
	 protected static Collection selectbook(int bookId,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector books = new Vector();
	     
		 try {
			 con.setAutoCommit(false);
			 SELECTONE = SELECTONE + bookId;
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Book book = new Book();
		        book.setBookno(rs.getInt(1));
		        book.setBook_name(rs.getString(2));
		        book.setLe_id(rs.getInt(3));
		        book.setFolderID(rs.getInt(4));
		        book.setAttributes(rs.getString(5));
	     books.add(book);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("bookSQL","selectbook",e);
			 return books;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL","selectMax",e);
			}
	     }
	     return books;
	 }
	 public static Collection selectbookWhere(String sql,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector books = new Vector();
	     String sqls = SELECTWHERE + " " + sql;
		 try {
			 con.setAutoCommit(false);
			
			 stmt = dsSQL.newPreparedStatement(con, sqls );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Book book = new Book();
		        book.setBookno(rs.getInt(1));
		        book.setBook_name(rs.getString(2));
		        book.setLe_id(rs.getInt(3));
		        book.setFolderID(rs.getInt(4));
		        book.setAttributes(rs.getString(5));
	     books.add(book);
	      
	      }
	      commonUTIL.display("bookSQL","selectbookWhere  "+sqls);
		 } catch (Exception e) {
			 commonUTIL.displayError("bookSQL","selectbookWhere",e);
			 return books;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL","selectbookWhere",e);
			}
	     }
	     return books;
	 }
}
