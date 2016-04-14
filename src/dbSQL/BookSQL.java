package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;

import beans.Attribute;
import beans.AttributeContainer;
import beans.BaseBean;
import beans.Book;
import beans.BookAttribute;
import beans.Coupon;
import beans.LeContacts;
import beans.Product;
import constants.BeanConstants;
import util.commonUTIL;

public class BookSQL extends BaseSQL {

	final static private String DELETE_FROM_BOOK = "DELETE FROM BOOK where BOOKNO =? ";
	final static private String INSERT_FROM_BOOK = "INSERT into BOOK(BOOKNO,LE_ID,BOOK_NAME,FOLDERID,timezone, holidaycode, currency ) values(?,?,?,?)";
	final static private String UPDATE_FROM_BOOK = "UPDATE BOOK set LE_ID=?,BOOK_NAME=? ,timezone, holidaycode, currency where BOOKNO = ? ";
	final static private String SELECT_MAX = "SELECT MAX(bookno) DESC_ID FROM BOOK ";
	final static private String SELECTALL = "SELECT BOOKNO,LE_ID,BOOK_NAME,FOLDERID,HOLIDAYCODE,TIMEZONE,CURRENCY FROM BOOK order by BOOKNO";
	final static private String SELECT = "SELECT title FROM BOOK where bookno =  ?";
	static private String SELECTONE = "SELECT BOOKNO,LE_ID,BOOK_NAME,FOLDERID,timezone, holidaycode, currency  FROM book where BOOKNO =  ";
	static private String SELECTWHERE = "SELECT BOOKNO,LE_ID,BOOK_NAME,FOLDERID,timezone, holidaycode, currency  FROM book where   ";

	final static private String SQLCOUNT = new StringBuffer("SELECT count(*) countRows ").append(" FROM BOOK where  ")
			.toString();

	private static String getUpdateSQL(Book book) {
		String updateSQL = "UPDATE book  set " + " LE_ID= " + book.getLe_id() + " ,BOOK_NAME= '" + book.getBook_name()
				+ "' ,FOLDERID= " + book.getFolderID() + ",timezone='" + book.getTimezone() + "', holidaycode='"
				+ book.getHolidaycode() + "', currency='" + book.getCurrency() + "'  where BOOKNO= " + book.getBookno();
		return updateSQL;
	}

	public static Book save(Book insertBook, Connection con) {
		try {
			return insert(insertBook, con);
		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", "save", e);
			return null;
		}
	}

	public static boolean update(Book updateBook, Connection con) {
		try {
			return edit(updateBook, con);
		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", "update", e);
			return false;
		}
	}

	public static boolean delete(Book deleteBook, Connection con) {
		try {
			return remove(deleteBook, con);
		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", "update", e);
			return false;
		}
	}

	public static Book selectBook(int BookId, Connection con) {
		try {
			return selectOld(BookId, con);
		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", "select", e);
			return null;
		}
	}

	public static Collection selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", "selectALL", e);
			return null;
		}
	}

	public static int selectMaxID(Connection con) {
		try {
			return selectMax(con);
		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", "selectMaxID", e);
			return 0;
		}
	}

	protected static boolean remove(Book deleteBook, Connection con) {

		PreparedStatement stmt = null;
		try {
			int j = 1;
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_BOOK);
			stmt.setInt(j++, deleteBook.getBookno());

			stmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", "remove", e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL", "remove", e);
			}
		}
		return true;
	}

	protected static int selectMax(Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);

			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				j = rs.getInt("DESC_ID");

		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", SELECT_MAX, e);
			return j;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL", SELECT_MAX, e);
			}
		}
		return j;
	}

	protected static Collection select(Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector books = new Vector();

		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECTALL);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Book book = new Book();
				book.setBookno(rs.getInt(1));
				book.setBook_name(rs.getString(3));
				book.setLe_id(rs.getInt(2));
				book.setFolderID(rs.getInt(4));

				book.setTimezone(rs.getString(5));
				book.setHolidaycode(rs.getString(6));
				book.setCurrency(rs.getString(7));
				
				
				Vector<Attribute> p =  (Vector<Attribute>) getAttributes(book.getBookno(), BeanConstants.BOOK);
				AttributeContainer ac = new AttributeContainer();
				ac.setAttributes(p);
				book.setAttributeContainer(ac);
				Iterator<Attribute> i = p.iterator();
				while (i.hasNext()) {
					Attribute p1 = i.next();
					System.out.println(p1.getId() + " " + p1.getName() + " " + p1.getValue());
				}
				
				
				books.add(book);

			}

		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", SELECTALL + e.getMessage(), e);

			return books;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL", SELECTALL, e);
			}
		}
		return books;
	}

	protected static Book insert(Book inserbook, Connection con) {

		PreparedStatement stmt = null;
		int id = 0;
		try {
			con.setAutoCommit(false);
			id = selectMax(con);
			id = id + 1;
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_BOOK);
			stmt.setInt(1, id);
			stmt.setInt(2, inserbook.getLe_id());
			stmt.setString(3, inserbook.getBook_name());
			stmt.setInt(4, inserbook.getFolderID());
			stmt.setString(5, inserbook.getAttributes());
			stmt.setString(6, inserbook.getTimezone());
			stmt.setString(7, inserbook.getHolidaycode());
			stmt.setString(8, inserbook.getCurrency());
			stmt.executeUpdate();
			con.commit();
			inserbook.setBookno(id);
			insertAttributes(inserbook.getAttributeContainer(), id, BeanConstants.BOOK);
			return inserbook;
		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", INSERT_FROM_BOOK, e);
			return null;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL", INSERT_FROM_BOOK, e);
			}
		}

	}

	protected static boolean edit(Book updateBook, Connection con) {

		PreparedStatement stmt = null;
		String sql = getUpdateSQL(updateBook);
		try {
			con.setAutoCommit(false);
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, sql);
			stmt.executeUpdate(sql);
			con.commit();
			commonUTIL.display("bookSQL ::  edit", sql);
			updateAttributes(updateBook.getAttributeContainer(), BeanConstants.BOOK, j);

		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", "edit", e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL", "edit", e);
			}
		}
		return true;
	}

	protected static Book selectOld(int bookIn, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Vector books = new Vector();
		Book book = new Book();
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECTONE + bookIn);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				book.setBookno(rs.getInt(1));
				book.setBook_name(rs.getString(2));
				book.setLe_id(rs.getInt(3));
				book.setFolderID(rs.getInt(4));
				book.setAttributes(rs.getString(5));
				book.setTimezone(rs.getString(6));
				book.setHolidaycode(rs.getString(7));
				book.setCurrency(rs.getString(8));

				return book;

			}
		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", "select", e);
			return book;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL", "selectMax", e);
			}
		}
		return book;
	}

	protected static Collection selectbook(int bookId, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector books = new Vector();

		try {
			con.setAutoCommit(false);
			SELECTONE = SELECTONE + bookId;
			stmt = dsSQL.newPreparedStatement(con, SELECTONE);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBookno(rs.getInt(1));
				book.setBook_name(rs.getString(2));
				book.setLe_id(rs.getInt(3));
				book.setFolderID(rs.getInt(4));
				book.setAttributes(rs.getString(5));
				book.setTimezone(rs.getString(6));
				book.setHolidaycode(rs.getString(7));
				book.setCurrency(rs.getString(8));

				books.add(book);

			}
		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", "selectbook", e);
			return books;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL", "selectMax", e);
			}
		}
		return books;
	}

	public static Collection selectbookWhere(String sql, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector books = new Vector();
		String sqls = SELECTWHERE + " " + sql;
		try {
			con.setAutoCommit(false);

			stmt = dsSQL.newPreparedStatement(con, sqls);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBookno(rs.getInt(1));
				book.setBook_name(rs.getString(2));
				book.setLe_id(rs.getInt(3));
				book.setFolderID(rs.getInt(4));
				book.setAttributes(rs.getString(5));
				books.add(book);

			}
			commonUTIL.display("bookSQL", "selectbookWhere  " + sqls);
		} catch (Exception e) {
			commonUTIL.displayError("bookSQL", "selectbookWhere", e);
			return books;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bookSQL", "selectbookWhere", e);
			}
		}
		return books;
	}

	@Override
	public BaseBean insertSQL(String sql, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSQL(String sql, Connection con) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSQL(String sql, Connection con) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BaseBean insertSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return insert((Book) sql, con);
	}

	@Override
	public boolean updateSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return update((Book) sql, con);
	}

	@Override
	public boolean deleteSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return delete((Book) sql, con);
	}

	@Override
	public BaseBean select(int id, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseBean select(String name, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection selectWhere(String where, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection selectALLData(Connection con) {
		// TODO Auto-generated method stub
		return selectALL(con);
	}

	@Override
	public int count(String sql, Connection con) {

		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		String sql1 = SQLCOUNT + " BOOKID = " + sql;
		int tem = 0;

		try {
			con.setAutoCommit(true);

			stmt = dsSQL.newPreparedStatement(con, sql1);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tem = rs.getInt(1);

			}
			return tem;
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "count " + sql1, e);

		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LeContactsSql", "count", e);
			}
		}
		return 0;
	}

	public static void main(String args[]) {
		Book boo = new Book();
		boo.setBookno(60);
		boo.setBook_name("Pppp");
		boo.setLe_id(3);
		boo.setFolderID(99);

		AttributeContainer att = new AttributeContainer();
		att.addAttribute("mesting", "ppqp");
		boo.setAttributeContainer(att);

		// insert(boo, dsSQL.getConn());
		// edit(boo, dsSQL.getConn());
		select(dsSQL.getConn());
		// JOptionPane.showMessageDialog(null, select( dsSQL.getConn()),
		// "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);

	}

	@Override
	public Collection selectKeyColumnsWithWhere(String columnNames, String where, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection selectKeyColumns(String columnNames, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}
}
