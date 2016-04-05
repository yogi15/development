package src.dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;
import beans.BaseBean;
import beans.PropertyCellStyle;
import util.commonUTIL;

public class PropertyCellStyleSQL extends BaseSQL {

	final static private String INSERT = "insert into propertyCellStyle (COLUMNSERIALNO,WINDOWNAME,FONT,BACKGROUND,FOREGROUND,FONTISBOLD,FONTISITALIC,PROPERTYNAME) values (? ,? ,? ,? ,? ,? ,? ,?  )";

	final static private String DELETE = "Delete frompropertyCellStyle where ";

	final static private String SELECTMAX = "SELECT MAX(id) DESC_ID FROM propertyCellStyle where ;";

	final static private String SELECTALL = "SELECT COLUMNSERIALNO,WINDOWNAME,FONT,BACKGROUND,FOREGROUND,FONTISBOLD,FONTISITALIC,PROPERTYNAME from propertyCellStyle order by"; // please
	// add
	// the
	// columns

	final static private String SELECT = "SELECT COLUMNSERIALNO,WINDOWNAME,FONT,BACKGROUND,FOREGROUND,FONTISBOLD,FONTISITALIC,PROPERTYNAME from propertyCellStyle where";// please
	// order
	// by
	// add
	// the
	// columns

	final static private String SELECTWHERE = "SELECT COLUMNSERIALNO,WINDOWNAME,FONT,BACKGROUND,FOREGROUND,FONTISBOLD,FONTISITALIC,PROPERTYNAME from propertyCellStyle where ";// please
	// order
	// by
	// add
	// the
	// columns

	final static private String SELECTCOUNT = "SELECT  count(*) from propertyCellStyle where ";// please

	// order
	// by
	// add
	// the
	// columns

	private static String getUpdateSQL(PropertyCellStyle bean) {
		String updateSQL = "UPDATE propertyCellStyle  set "
				+ " COLUMNSERIALNO = " + bean.getColumnserialno() + ""

				+ ",WINDOWNAME = '" + bean.getWindowname() + "'"

				+ ",FONT = '" + bean.getFontType() + "'"

				+ ",BACKGROUND = '" + bean.getBackground() + "'"

				+ ",FOREGROUND = '" + bean.getForeground() + "'";
		if (bean.getFontisbold())
			updateSQL = updateSQL + ",FONTISBOLD = 'Y'";
		else
			updateSQL = updateSQL + ",FONTISBOLD = 'N'";
		if (bean.getFontisitalic())
			updateSQL = updateSQL + ",FONTISITALIC = 'Y'";
		else
			updateSQL = updateSQL + ",FONTISITALIC = 'N'";

		updateSQL = updateSQL + ",PROPERTYNAME = '" + bean.getPropertyname()
				+ "' where windowname = '" + bean.getWindowname() + "' and PROPERTYNAME = '"+ bean.getPropertyname()
				+ "'";

		return updateSQL;
	}

	
	protected boolean update(PropertyCellStyle propertyCellStyle,
			Connection con) {
		String updateSQL = getUpdateSQL(propertyCellStyle);
		PreparedStatement stmt = null;
		 
		try {
			con.setAutoCommit(false);
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, updateSQL);
			stmt.executeUpdate(updateSQL);
			con.commit();
			commonUTIL.display("PropertyCellStyleSQL ::  edit", updateSQL);
			 
		} catch (Exception e) {
			commonUTIL.displayError("PropertyCellStyleSQL", "edit", e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("PropertyCellStyleSQL", "update "+updateSQL, e);
			}
		}
		return true; 
		 
	}
	
	protected PropertyCellStyle insert(PropertyCellStyle propertyCellStyle,
			Connection con) {

		PreparedStatement stmt = null;
		boolean updateFlag = false;
		int id = 0;
		try {
			
			String sql = "WindowName = '" + propertyCellStyle.getWindowname()
					+ "' and propertyName = '"
					+ propertyCellStyle.getPropertyname() + "'";
			if (count(sql, con) > 0) {
				updateFlag = true;
                  update(propertyCellStyle,con);
                  return propertyCellStyle;
			} else {
				con.setAutoCommit(false);
				stmt = dsSQL.newPreparedStatement(con, INSERT);

				stmt.setInt(1, propertyCellStyle.getColumnserialno());

				stmt.setString(2, propertyCellStyle.getWindowname());

				stmt.setString(3, propertyCellStyle.getFontType());

				stmt.setString(4, propertyCellStyle.getBackground());

				stmt.setString(5, propertyCellStyle.getForeground());
				if (propertyCellStyle.getFontisbold())
					stmt.setString(6, "Y");
				else
					stmt.setString(6, "N");
				if (propertyCellStyle.getFontisitalic())
					stmt.setString(7, "Y");
				else
					stmt.setString(7, "N");

				stmt.setString(8, propertyCellStyle.getPropertyname());

				commonUTIL.display("propertyCellStyPropertyCellStyleSQL",
						"insert", INSERT);
				stmt.executeUpdate();
				con.commit();
			}
		} catch (Exception e) {
			commonUTIL.displayError("PropertyCellStyleSQL", INSERT, e);
			return null;
		} finally {
			try {
				if(!updateFlag)
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("PropertyCellStyleSQL", INSERT, e);
			}
		}
		return propertyCellStyle;
	}

	protected static int selectMax(Connection con) {
		int j = 0;

		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECTMAX);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				j = rs.getInt("DESC_ID");
			}

			commonUTIL.display("propertyCellStyPropertyCellStyleSQL",
					"SELECTMAX", SELECTMAX);
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			commonUTIL.displayError("PropertyCellStyleSQL", SELECTMAX, e);
			return 0;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("PropertyCellStyleSQL", SELECTMAX, e);
			}
		}
		return j;
	}

	protected static Collection select(Connection con) {

		PreparedStatement stmt = null;
		Vector<PropertyCellStyle> styles = new Vector<PropertyCellStyle>();
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECTALL);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PropertyCellStyle propertyCellStyle = new PropertyCellStyle();

				propertyCellStyle.setColumnserialno(rs.getInt(1));

				propertyCellStyle.setWindowname(rs.getString(2));

				propertyCellStyle.setFontType(rs.getString(3));

				propertyCellStyle.setBackground(rs.getString(4));

				propertyCellStyle.setForeground(rs.getString(5));
				if (rs.getString(6).equalsIgnoreCase("Y"))
					propertyCellStyle.setFontisbold(true);
				if (rs.getString(7).equalsIgnoreCase("Y"))
					propertyCellStyle.setFontisitalic(true);

				propertyCellStyle.setPropertyname(rs.getString(8));
				styles.add(propertyCellStyle);
			}
			commonUTIL.display("propertyCellStyPropertyCellStyleSQL", "insert",
					INSERT);
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			commonUTIL.displayError("PropertyCellStyleSQL", SELECT, e);
			return null;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("PropertyCellStyleSQL", SELECT, e);
			}
		}
		return styles;
	}
	protected static Collection selectW(String sql,Connection con) {

		PreparedStatement stmt = null;
		Vector<PropertyCellStyle> styles = new Vector<PropertyCellStyle>();
		String sqlW = SELECTWHERE + sql;
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, sqlW);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PropertyCellStyle propertyCellStyle = new PropertyCellStyle();

				propertyCellStyle.setColumnserialno(rs.getInt(1));

				propertyCellStyle.setWindowname(rs.getString(2));

				propertyCellStyle.setFontType(rs.getString(3));

				propertyCellStyle.setBackground(rs.getString(4));

				propertyCellStyle.setForeground(rs.getString(5));

				if (rs.getString(6).equalsIgnoreCase("Y"))
					propertyCellStyle.setFontisbold(true);
				if (rs.getString(7).equalsIgnoreCase("Y"))
					propertyCellStyle.setFontisitalic(true);

				propertyCellStyle.setPropertyname(rs.getString(8));
				styles.add(propertyCellStyle);
			}
			commonUTIL.display("propertyCellStyPropertyCellStyleSQL", "SelectWhere",
					sqlW);
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			commonUTIL.displayError("PropertyCellStyleSQL", SELECTALL, e);
			return null;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("PropertyCellStyleSQL", SELECTALL, e);
			}
		}
		return styles;
	}

	protected static Collection selectALL(Connection con) {

		PreparedStatement stmt = null;
		Vector<PropertyCellStyle> styles = new Vector<PropertyCellStyle>();
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECTALL);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PropertyCellStyle propertyCellStyle = new PropertyCellStyle();

				propertyCellStyle.setColumnserialno(rs.getInt(1));

				propertyCellStyle.setWindowname(rs.getString(2));

				propertyCellStyle.setFontType(rs.getString(3));

				propertyCellStyle.setBackground(rs.getString(4));

				propertyCellStyle.setForeground(rs.getString(5));

				if (rs.getString(6).equalsIgnoreCase("Y"))
					propertyCellStyle.setFontisbold(true);
				if (rs.getString(7).equalsIgnoreCase("Y"))
					propertyCellStyle.setFontisitalic(true);

				propertyCellStyle.setPropertyname(rs.getString(8));
				styles.add(propertyCellStyle);
			}
			commonUTIL.display("propertyCellStyPropertyCellStyleSQL", "insert",
					INSERT);
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			commonUTIL.displayError("PropertyCellStyleSQL", SELECTALL, e);
			return null;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("PropertyCellStyleSQL", SELECTALL, e);
			}
		}
		return styles;
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
		return insert((PropertyCellStyle) sql, con);
	}

	@Override
	public boolean updateSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return false;
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

	public Collection selectWhere(String where, Connection con) {
		// TODO Auto-generated method stub
		return selectW(where, con);
	}

	@Override
	public Collection selectALLData(Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String args[]) {
		PropertyCellStyle propertyCellStyle = new PropertyCellStyle();
		propertyCellStyle.setColumnserialno(0);
		propertyCellStyle.setWindowname("Test");

		propertyCellStyle.setPropertyname("Test");
		propertyCellStyle.setBackground("23,23,23");

		propertyCellStyle.setForeground("23,23,23");

		propertyCellStyle.setFontType("etesting,2,34");
		propertyCellStyle.setFontisbold(true);
		propertyCellStyle.setFontisitalic(true);
		System.out.println(getUpdateSQL(propertyCellStyle));
		// insert(propertyCellStyle,dsSQL.getConn());
		// propertyCellStyle.setBackground(background)

	}

	@Override
	public int count(String countSQL, Connection con) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		Vector<PropertyCellStyle> styles = new Vector<PropertyCellStyle>();
		String sqlCount = SELECTCOUNT + countSQL;
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, sqlCount);
			ResultSet rs = stmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0)
				return 1;
			return 0;
		} catch (Exception e) {
			commonUTIL.displayError("PropertyCellStyleSQL", SELECTALL, e);
			return 0;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("PropertyCellStyleSQL", SELECTALL, e);
			}
		}
	}


	 
}