package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.WindowTableModelMapping;

public class WindowTableModelMappingSQL {

	final static private String DELETE_FROM_windowtablemapping = "DELETE FROM WINDOWTABLEMAPPING  ";
	final static private String SELECT_MAX = "SELECT MAX(id) SerialNo FROM WINDOWTABLEMAPPING ";
	final static private String INSERT_FROM_windowtablemapping = "INSERT into WINDOWTABLEMAPPING(WINDOWNAME,BEANNAME,COLOUMNNAME,METHODNAME,id,columnDisplayName,columnDatatype,customColumnName,customMethodName,isCombox) values(?,?,?,?,?,?,?,?,?,?)";

	final static private String SELECTALL = "SELECT  WINDOWNAME, BEANNAME,COLOUMNNAME,METHODNAME,id,columnDisplayName,columnDatatype,customColumnName,customMethodName,isCombox FROM WindowTableMapping order by COLOUMNNAME";

	static private String SELECTWHERE = "SELECT  WINDOWNAME, BEANNAME,COLOUMNNAME,METHODNAME ,id,columnDisplayName,columnDatatype,customColumnName,customMethodName,isCombox   FROM WINDOWTABLEMAPPING where   ";

	private static String getUpdateSQL(WindowTableModelMapping wtm) {
		String updateSQL = "UPDATE WindowTableMapping  set "
				+ "  WINDOWNAME= '" + wtm.getWindowName() + "' ,BEANNAME= '"
				+ wtm.getBeanName() + "',  COLOUMNNAME= ' "
				+ wtm.getColumnName() + "',METHODNAME= '" + wtm.getMethodName() + "',columnDisplayName= '" + wtm.getColumnDisplayName()
				+ "' "+ ",columnDatatype= '" + wtm.getColumnDataType()+ "',customColumnName= '" + wtm.getCustomColumnName() + "',customMethodName= '" + wtm.getCustomMethodName() +"',";
		if(wtm.IsCombobox()) {
			updateSQL = updateSQL + " isCombox = 'Y'";
		} else {
			updateSQL = updateSQL + " isCombox = 'N'";
		}
		updateSQL = updateSQL +  "  where  id  =  " + wtm.getId();
			 
		return updateSQL;
	}

	private static String getDeleteSQL(WindowTableModelMapping wtm) {
		String deleteSQL = DELETE_FROM_windowtablemapping
				+ " where   id  =  " + wtm.getId();
		return deleteSQL;
	}

	public static boolean update(WindowTableModelMapping updateObj,
			Connection con) {

		try {
			return edit(updateObj, con);
		} catch (Exception e) {
			commonUTIL.displayError("WindowTableModelMapping", "update", e);
			return false;
		}
	}

	protected static boolean edit(WindowTableModelMapping updateObj,
			Connection con) {

		PreparedStatement stmt = null;
		String sql = getUpdateSQL(updateObj);
		try {
			con.setAutoCommit(false);
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, sql);
			stmt.executeUpdate(sql);
			con.commit();
			commonUTIL.display("WindowTableModelMapping ::  edit ", sql);
		} catch (Exception e) {
			commonUTIL
					.displayError("WindowTableModelMapping", "edit " + sql, e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("WindowTableModelMappingSQL", "edit "
						+ sql, e);
			}
		}
		return true;
	}

	public static WindowTableModelMapping save(WindowTableModelMapping insert,
			Connection con) {
		try {
			return insert(insert, con);
		} catch (Exception e) {
			commonUTIL.displayError("(WindowTableModelMappingSQL", "save", e);
			return null;
		}
	}

	public static Collection<WindowTableModelMapping> selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("(WindowTableModelMappingSQL", "selectALL",
					e);
			return null;
		}
	}

	private static WindowTableModelMapping insert(
			WindowTableModelMapping insert, Connection con) {
		PreparedStatement stmt = null;
		int id = selectMax(con) + 1;
		try {
			con.setAutoCommit(false);

			stmt = dsSQL.newPreparedStatement(con,
					INSERT_FROM_windowtablemapping);
			stmt.setString(1, insert.getWindowName());
			stmt.setString(2, insert.getBeanName());
			stmt.setString(3, insert.getColumnName());
			stmt.setString(4, insert.getMethodName());
			stmt.setInt(5, id );
			stmt.setString(6, insert.getColumnDisplayName());
			stmt.setString(7, insert.getColumnDataType());
			stmt.setString(8, insert.getCustomColumnName());
			stmt.setString(9, insert.getCustomMethodName());
			if(insert.IsCombobox()) {
				stmt.setString(10, "Y");
			} else {
				stmt.setString(10, "N");
			}
			stmt.executeUpdate();
			commonUTIL.display("WindowTableModelMappingSQL",
					INSERT_FROM_windowtablemapping);
			con.commit();
			insert.setId(id);
			return insert;
		} catch (Exception e) {
			commonUTIL.displayError("WindowTableModelMappingSQL",
					INSERT_FROM_windowtablemapping, e);
			return null;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("WindowTableModelMappingSQL",
						INSERT_FROM_windowtablemapping, e);
			}
		}

	}

	protected static Collection<WindowTableModelMapping> select(Connection con) {
		PreparedStatement stmt = null;
		Vector<WindowTableModelMapping> jTableData = new Vector<WindowTableModelMapping>();
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECTALL);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				WindowTableModelMapping data = new WindowTableModelMapping();

				data.setWindowName(rs.getString(1));
				data.setBeanName(rs.getString(2));
				data.setColumnName(rs.getString(3));
				data.setMethodName(rs.getString(4));
				data.setId(rs.getInt(5));
				data.setColumnDisplayName(rs.getString(6));
				data.setColumnDataType(rs.getString(7));
				data.setCustomColumnName(rs.getString(8));
				data.setCustomMethodName(rs.getString(9));
				String isComb = rs.getString(10);
				if(!commonUTIL.isEmpty(isComb) && isComb.equalsIgnoreCase("Y")) {
					data.setIsCombobox(true);
				} else {
					data.setIsCombobox(false);
				}
				jTableData.add(data);
			}

			commonUTIL.display("WindowTableModelMapping ::  SELECTALL",
					SELECTALL);
			return jTableData;
		} catch (Exception e) {
			commonUTIL.displayError("WindowTableModelMapping", SELECTALL, e);
			return null;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("WindowTableModelMappingSQL",
						SELECTALL, e);
			}
		}

	}

	protected static int selectMax(Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);

			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				j = rs.getInt("SerialNo");

		} catch (Exception e) {
			commonUTIL
					.displayError("WindowTableModelMappingSQL", SELECT_MAX, e);
			return j;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("WindowTableModelMappingSQL",
						SELECT_MAX, e);
			}
		}
		return j;
	}

	public static boolean delete(WindowTableModelMapping deleteObj,
			Connection con) {

		PreparedStatement stmt = null;
		String deleteSQL = getDeleteSQL(deleteObj);
		try {
			con.setAutoCommit(false);
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, deleteSQL);

			stmt.executeUpdate();
			con.commit();
			commonUTIL.display("WindowTableModelMapping ::  delete", deleteSQL);
		} catch (Exception e) {
			commonUTIL.displayError("WindowTableModelMapping ", "delete "
					+ deleteSQL, e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("WindowTableModelMapping", "delete", e);
			}
		}
		return true;
	}

	public static Collection selectWindowModel(String windowName, Connection con) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		Vector<WindowTableModelMapping> jTableData = new Vector<WindowTableModelMapping>();
		String sql = SELECTWHERE + windowName;
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				WindowTableModelMapping data = new WindowTableModelMapping();

				data.setWindowName(rs.getString(1));
				data.setBeanName(rs.getString(2));
				data.setColumnName(rs.getString(3));
				data.setMethodName(rs.getString(4));
				data.setId(rs.getInt(5));
				data.setColumnDisplayName(rs.getString(6));
				data.setColumnDataType(rs.getString(7));
				data.setCustomColumnName(rs.getString(8));
				data.setCustomMethodName(rs.getString(9));
				String isComb = rs.getString(10);
				if(!commonUTIL.isEmpty(isComb) && isComb.equalsIgnoreCase("Y")) {
					data.setIsCombobox(true);
				} else {
					data.setIsCombobox(false);
				}
				 
				jTableData.add(data);
			}

			commonUTIL.display("WindowTableModelMapping ::  selectWindowModel",
					sql);
			return jTableData;
		} catch (Exception e) {
			commonUTIL.displayError("WindowTableModelMapping", sql, e);
			return null;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL
						.displayError("WindowTableModelMapping", SELECTALL, e);
			}
		}
	}

}
