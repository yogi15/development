package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.Attribute;
import beans.BaseBean;
import beans.Book;
import beans.BookAttribute;
import constants.BeanConstants;
import beans.Attribute;
import util.commonUTIL;

public class BookAttributeSQL implements AttributeProvider {

	final static private String INSERT_INTO_BOOK_ATTRIBUTE = "INSERT into BOOKATTRIBUTES(  id, attributeName, attributeValue) values(?,?,?)";

	final static private String SELECTALL_BOOK_ATTRIBUTE = "SELECT id, attributeName, attributeValue FROM BOOKATTRIBUTES where id = "  ;

	final static private String BOOK_ATTRIBUTE_UPDATE_SQL = "UPDATE BOOKATTRIBUTES  set " + " attributeName= ? "
			+ ",attributeValue= ?" + " where  id= ?";

	public static BaseBean saveBookAttribute(Vector<Attribute> bookAttrVec, Connection con, int entityID) {
		try {
			return insertBookAttribute(bookAttrVec, con, entityID);
		} catch (Exception e) {
			commonUTIL.displayError("insertAttributeSQL", "save", e);
			return null;
		}
	}

	public static boolean updateBookAttribute(Vector<Attribute> updateBookAttribute, int entityID, Connection con) {
		try {
			return editBookAttribute(updateBookAttribute, entityID, con);
		} catch (Exception e) {
			commonUTIL.displayError("BookAttributeSQL", "update" + e.getMessage(), e);
			return false;
		}
	}

	public static Collection<Attribute> selectALLBookAttribute(int id,Connection con) {
		try {
			return selectBookAttribute(  id,con);
		} catch (Exception e) {
			commonUTIL.displayError("BookAttributeSQL", "selectALL", e);
			return null;
		}
	}

	protected static boolean editBookAttribute(Vector<Attribute> updateAttributeVec, int entityID, Connection con) {

		PreparedStatement stmt = null;
		String sql = "";

		int size = updateAttributeVec.size();

		try {
			con.setAutoCommit(false);

			stmt = dsSQL.newPreparedStatement(con, BOOK_ATTRIBUTE_UPDATE_SQL);

			Attribute bookAttr = null;

			for (int ii = 0; ii < size; ii++) {

				bookAttr = updateAttributeVec.get(ii);

				stmt.setString(1, bookAttr.getName());
				stmt.setString(2, bookAttr.getValue());
				stmt.setInt(3, entityID);

				stmt.addBatch();
			}

			stmt.executeBatch();
			con.commit();

			commonUTIL.display("BookAttributeSQL ::  editBookAttribute", BOOK_ATTRIBUTE_UPDATE_SQL);

		} catch (Exception e) {

			commonUTIL.displayError("BookAttributeSQL: : editBookAttribute  ", BOOK_ATTRIBUTE_UPDATE_SQL, e);

			try {
				con.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("BookAttributeSQL", "edit" + sql, e);
			}

		}

		return true;
	}

	protected static BaseBean insertBookAttribute(Vector<Attribute> attributes, Connection con, int entity) {

		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, INSERT_INTO_BOOK_ATTRIBUTE);

			int size = attributes.size();

			for (int ii = 0; ii < size; ii++) {

				stmt.setInt(1, entity);
				stmt.setString(2, attributes.get(ii).getName());
				stmt.setString(3, attributes.get(ii).getValue());

				stmt.addBatch();

			}
			stmt.executeBatch();
			con.commit();
			commonUTIL.display("BookAttributeSQL", "insert " + INSERT_INTO_BOOK_ATTRIBUTE);
		} catch (Exception e) {
			commonUTIL.displayError("BookAttributeSQL", INSERT_INTO_BOOK_ATTRIBUTE + e.getMessage(), e);
			try {
				con.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			return null;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {

				commonUTIL.displayError("BookAttributeSQL", INSERT_INTO_BOOK_ATTRIBUTE, e);
			}
		}
		return (BaseBean) attributes;
	}

	protected static Collection<Attribute> selectBookAttribute(int id,Connection con) {

		PreparedStatement stmt = null;
		Vector<Attribute> bookAttribute = new Vector<Attribute>();
          String sql =SELECTALL_BOOK_ATTRIBUTE + id + "  order by  id";
		try {

			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Attribute BookAttribute = new Attribute();
				BookAttribute.setId(rs.getInt(1));
				BookAttribute.setName(rs.getString(2));
				BookAttribute.setValue(rs.getString(3));

				bookAttribute.add(BookAttribute);

			}
			commonUTIL.display("BookAttributeSQL", "selectWhereClause ");
		} catch (Exception e) {
			commonUTIL.displayError("BookAttributeSQL", SELECTALL_BOOK_ATTRIBUTE, e);
			return bookAttribute;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {

				commonUTIL.displayError("BookAttributeSQL", SELECTALL_BOOK_ATTRIBUTE, e);
			}
		}
		return bookAttribute;
	}

	@Override
	public void saveAttributes(Vector attributes, Connection con, int entityID) {
		// TODO Auto-generated method stub
		insertBookAttribute(attributes, con, entityID);
	}

	@Override
	public void updateAttributes(Vector attributes, int entityID, Connection con) {
		updateBookAttribute(attributes, entityID, con);
	}

	@Override
	public Vector<Attribute> selectAttributes(int id,Connection con) {
		// TODO Auto-generated method stub

		return (Vector<Attribute>) selectALLBookAttribute(id,con);
	}

	
	

}
