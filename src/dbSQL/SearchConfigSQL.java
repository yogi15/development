package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.BaseBean;
import beans.SearchConfig;

public class SearchConfigSQL extends BaseSQL {

	final static private String INSERT = " insert into SEARCHCONFIG (ID,SEARCHTYPE,ATTRIBUTETYPE,BEANNAME,COLUMNAMES) values (? ,? ,? ,? ,?  )";

	final static private String DELETE = " Delete fromSearchConfig where id = ";

	final static private String SELECTMAX = " SELECT MAX(id) DESC_ID FROM SEARCHCONFIG where ";

	final static private String SELECTALL = "  SELECT ID,SEARCHTYPE,ATTRIBUTETYPE,BEANNAME,COLUMNAMES from SEARCHCONFIG order by"; // please
																																// add
																																// the
																																// columns

	final static private String SELECT = " SELECT ID,SEARCHTYPE,ATTRIBUTETYPE,BEANNAME,COLUMNAMES from SEARCHCONFIG where   ";// please
																															// order
																															// by
																															// add
																															// the
																															// columns

	final static private String SELECTWHERE = "  SELECT ID,SEARCHTYPE,ATTRIBUTETYPE,BEANNAME,COLUMNAMES from SEARCHCONFIG where   ";// please
																																	// order
																																	// by
																																	// add
																																	// the
																																	// columns

	final static private String SELECTCOUNT = " SELECT  count(*) from SEARCHCONFIG where   ";// please
																							// order
																							// by
																							// add
																							// the
																							// columns

	private static String getUpdateSQL(SearchConfig bean) {
		String updateSQL = "UPDATE bean  set " + ",ID = " + bean.getID()

		+ ",SEARCHTYPE = '" + bean.getSearchType() + ",ATTRIBUTETYPE = '"
				+ bean.getSearchAttributes() + ",BEANNAME = '"
				+ bean.getBeanName() + ",COLUMNAMES = '"
				+ bean.getColumnNames() + "' where id = " + bean.getID();

		return updateSQL;
	}
	
	
	protected static boolean remove(SearchConfig deleteSearchConfig,
			Connection con) {

		PreparedStatement stmt = null;
		String deleteSQL = DELETE + deleteSearchConfig.getID();
		try {
			 
			
			con.setAutoCommit(false);
			
			stmt = dsSQL.newPreparedStatement(con, deleteSQL);
		 
			stmt.executeUpdate();
		
			con.commit();

		} catch (Exception e) {
			
			commonUTIL.displayError("SearchConfigSQL", "remove " +e.getMessage(), e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("SearchConfigSQL", "remove" +e.getMessage(), e);
			}
		}
		return true;
	}
	protected static boolean edit(SearchConfig updateSearchConfig, Connection con) {

		PreparedStatement stmt = null;
		String sql = getUpdateSQL(updateSearchConfig);
		try {
			con.setAutoCommit(false);
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, sql);
			stmt.executeUpdate(sql);
			con.commit();
			commonUTIL.display(" SearchConfigSQL ::  edit", sql);
			con.commit();
		} catch (Exception e) {
			commonUTIL.displayError("SearchConfigSQL", "edit", e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL", "Update" + sql + " "+ e.getMessage(), e);
			}
		}
		return true;
	}

	protected static SearchConfig insert(SearchConfig insertBean, Connection con) {

		PreparedStatement stmt = null;

		int id = 0;
		try {
			con.setAutoCommit(false);
			id = selectMax(con) +1;
		 
			stmt = dsSQL.newPreparedStatement(con, INSERT);

			stmt.setInt(1, id);

			stmt.setString(2, insertBean.getSearchType());

			stmt.setString(3, insertBean.getSearchAttributes());

			stmt.setString(4, insertBean.getBeanName());

			stmt.setString(5, insertBean.getColumnNames());

			stmt.executeUpdate();
			con.commit();
			commonUTIL.display("insertBeanSQL", "INSERT   " + INSERT);
		} catch (Exception e) {
			commonUTIL.displayError("insertBeanSQL", INSERT + " "+ e.getMessage(), e);
			return null;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("insertBeanSQL", INSERT + " "+ e.getMessage(), e);
			}
		}
		return insertBean;
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

			stmt.executeUpdate();
			con.commit();
			commonUTIL.display("SearchConfigSQL", "SELECTMAX   " + INSERT);
		} catch (Exception e) {
			commonUTIL.displayError("SearchConfigSQL", SELECTMAX + " "+ e.getMessage(), e);
			return j;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("SearchConfigSQL", SELECTMAX + " "+ e.getMessage(), e);
			}
			return j;
		}
	}

	protected static Collection selectALL(Connection con) {

		PreparedStatement stmt = null;
		Vector<SearchConfig> searchConfig = new Vector<SearchConfig>();
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECTALL);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				SearchConfig scfg = new SearchConfig();

				scfg.setID(rs.getInt(1));

				scfg.setSearchType(rs.getString(2));

				scfg.setSearchAttributes(rs.getString(3));

				scfg.setBeanName(rs.getString(4));

				scfg.setColumnNames(rs.getString(5));

				stmt.executeUpdate();
				con.commit();
				commonUTIL.display("SearchConfigSQL", "SELECT   " + SELECTALL);
			}
		} catch (Exception e) {
			commonUTIL.displayError("SearchConfigSQL", SELECT + " "+ e.getMessage(), e);
			return null;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("SearchConfigSQL", SELECT + " "+ e.getMessage(), e);
			}
		}
		return searchConfig;
	}

	protected static Collection selectW(String sql,Connection con) {
		PreparedStatement stmt = null;
		String wheresql = SELECTWHERE + sql;
		Vector<SearchConfig> searchConfig = new Vector<SearchConfig>();
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, wheresql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				SearchConfig scfg = new SearchConfig();

				scfg.setID(rs.getInt(1));

				scfg.setSearchType(rs.getString(2));

				scfg.setSearchAttributes(rs.getString(3));

				scfg.setBeanName(rs.getString(4));

				scfg.setColumnNames(rs.getString(5));

				stmt.executeUpdate();
				con.commit();
				commonUTIL.display("SearchConfigSQL", "SELECT   " + wheresql);
			}
		} catch (Exception e) {
			commonUTIL.displayError("SearchConfigSQL", SELECT + " "+ e.getMessage(), e);
			return null;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("SearchConfigSQL", SELECT + " "+ e.getMessage(), e);
			}
		}
		return searchConfig;
	}
	protected static SearchConfig selectOne(String sql,Connection con) {
		PreparedStatement stmt = null;
		String wheresql = SELECTWHERE + sql;
		SearchConfig scfg = new SearchConfig();
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, wheresql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				

				scfg.setID(rs.getInt(1));

				scfg.setSearchType(rs.getString(2));

				scfg.setSearchAttributes(rs.getString(3));

				scfg.setBeanName(rs.getString(4));

				scfg.setColumnNames(rs.getString(5));

				stmt.executeUpdate();
				con.commit();
				commonUTIL.display("SearchConfigSQL", "SELECT   " + wheresql);
			}
		} catch (Exception e) {
			commonUTIL.displayError("SearchConfigSQL", SELECT + " "+ e.getMessage(), e);
			return null;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("SearchConfigSQL", SELECT + " "+ e.getMessage(), e);
			}
		}
		return scfg;
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
		return  insert((SearchConfig) sql, con);
	}

	@Override
	public boolean updateSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return edit((SearchConfig) sql, con);
	}

	@Override
	public boolean deleteSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return remove((SearchConfig) sql,con);
	}

	@Override
	public BaseBean select(int id, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseBean select(String name, Connection con) {
		// TODO Auto-generated method stub
		return selectOne(name, con);
	}

	@Override
	public Collection selectWhere(String where, Connection con) {
		// TODO Auto-generated method stub
		return selectW(where, con);
	}

	@Override
	public Collection selectALLData(Connection con) {
		// TODO Auto-generated method stub
		return selectALL(con);
	}

	@Override
	public int count(String sql, Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection selectKeyColumnsWithWhere(String columnNames,
			String where, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection selectKeyColumns(String columnNames, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}
}