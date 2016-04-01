package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.LimitConfig;

public class LimitConfigSQL {
	int id = 0;
	String config_name = "";
	String filter = "";
	String createTime = "";
	String modifiedTime = "";

final static private String tableName = "limitConfig";
	
	final static private String DELETE_FROM_LIMITUSAGE= "DELETE FROM limitConfig where id =? ";
	
	final static private String INSERT_FROM_message = new StringBuffer("INSERT into limitConfig(")
			.append(" id,  config_name,  limittype, Filtertype,filtervalue, createTime, modifiedTime,parentID)  ")
			.append(" values (?,?,?,?,?,?,?,?) ")
			.toString();
	
	final static private String SELECT_MAX = "SELECT LIMITCONFIG_SEQ.NEXTVAL limitUsageId FROM dual ";
	
	final static private String SELECTALL =  new StringBuffer("SELECT")
	.append(" id,  config_name ")
	.append(" FROM limitConfig group by id ,config_name  order by id")
	.toString();
	
	final static private String SELECTUNIQUE =  new StringBuffer("SELECT")
	.append("  id,  config_name  ")
	.append("  FROM limitConfig where id =  ")
	.toString();
	
	
	final static private String SELECT = "SELECT Id FROM limitConfig where   ";
	final static private String SELECTSQL  = "SELECT id,  config_name, limittype, Filtertype,filtervalue, createTime, modifiedTime,parentID   FROM limitConfig where   ";
	final static private String SELECTLIMITTYPECONFIG = "SELECT  id,  config_name,  Filtertype,filtervalue, createTime, modifiedTime,parentID  FROM limitConfig where   ";
	static private String SELECTONE = new StringBuffer("SELECT ")
	.append(" id,  config_name,  Filtertype,filtervalue, createTime, modifiedTime ")
	.append("FROM limitConfig ")
	.append("where id = ")
	.toString();
	
	
	
	
	
	final static private String SELECTONLIMITCONFIG = new StringBuffer("SELECT")
	.append(" id,  config_name,  Filtertype,filtervalue, createTime, modifiedTime ")
	.append("FROM limitConfig ")
	.append("where config_name = ")
	.toString();

	private static String getUpdateSQL(LimitConfig limitConfig) {

		String updateSQL = new StringBuffer("UPDATE ").append(tableName)
				.append(" set id = ").append(limitConfig.getId()).append(",")
				.append(" config_name = '").append(limitConfig.getConfig_name()).append("',")
				.append(" Filtertype = '").append(limitConfig.getFilterType()).append("',")
				.append(" filtervalue = '").append(limitConfig.getFilterValue()).append("',")
				.append(" createTime = '").append(limitConfig.getCreateTime()).append("',")
				.append(" modifiedTime = '").append(limitConfig.getModifiedTime()).append("'")				
				.toString();

	//	System.out.println(updateSQL);

		return updateSQL;

	}

	public static int save(LimitConfig insertMessage, Connection con) {
		try {
			return insert(insertMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitConfigSQL", "save", e);
			return 0;
		}
	}

	public static boolean update(LimitConfig updateMessage, Connection con) {
		try {
			return edit(updateMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitConfigSQL", "update", e);
			return false;
		}
	}

	public static boolean delete(LimitConfig deleteMessage, Connection con) {
		try {
			return remove(deleteMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitConfigSQL", "update", e);
			return false;
		}
	}

	public static Collection selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitConfigSQL", "selectALL", e);
			return null;
		}
	}

	public static int selectMaxID(Connection con) {
		try {
			return selectMax(con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitConfigSQL", "selectMaxID", e);
			return 0;
		}
	}

	protected static boolean edit(LimitConfig updateMessage, Connection con) {

		PreparedStatement stmt = null;
		String sql = "";
		boolean hasEdit = false;
		
		try {

			con.setAutoCommit(false);
			sql = getUpdateSQL(updateMessage);
			stmt = con.prepareStatement(sql);
			
			commonUTIL.display("LimitConfigSQL", "edit " + sql);

			if (stmt.executeUpdate() > 0) {

				con.commit();
				hasEdit =true;

			} 
			
		} catch (Exception e) {
			commonUTIL.displayError("LimitConfigSQL ", "edit " + sql, e);
			return false;

		} finally {
			try {
				con.setAutoCommit(true);
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitConfigSQL", "edit", e);
			}
		}
		return hasEdit;
	}

	protected static boolean remove(LimitConfig deleteMessage, Connection con) {

		PreparedStatement stmt = null;
		boolean hasDeleted = false;
		try {
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_LIMITUSAGE);
			stmt.setInt(j++, deleteMessage.getId());

			if (stmt.executeUpdate() > 0) {

				con.commit();
				hasDeleted =true;

			} 

		} catch (Exception e) {
			commonUTIL.displayError("LimitConfigSQL", "remove", e);
			return false;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitConfigSQL", "remove", e);
			}
		}
		return hasDeleted;
	}

	public static int selectMax(Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		try {

			stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);

			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				j = rs.getInt("limitUsageId");

		} catch (Exception e) {
			commonUTIL.displayError("LimitConfigSQL", SELECT_MAX, e);
			return j;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
			
				commonUTIL.displayError("LimitConfigSQL", SELECT_MAX, e);
			}
		}
		return j;
	}

	protected static int insert(LimitConfig inserMessage, Connection con) {

		PreparedStatement stmt = null;
		
		boolean isSave = false;
		
		int id = 0;
		
		try {
			 
			commonUTIL.display("LimitConfigSQL", INSERT_FROM_message);
			
			con.setAutoCommit(false);
			
		//	id = selectMax(con);
			
			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_message);
			id = inserMessage.getId();
			stmt.setInt(1, inserMessage.getId());
			stmt.setString(2, inserMessage.getConfig_name());
			stmt.setString(3, inserMessage.getLimitType());
			stmt.setString(4, inserMessage.getFilterType());
			stmt.setString(5, inserMessage.getFilterValue());
			stmt.setString(6, inserMessage.getCreateTime());
			stmt.setString(7, inserMessage.getModifiedTime());
			stmt.setInt(8, inserMessage.getParentID());
						
			if (stmt.executeUpdate() > 0) {

				con.commit();
				isSave = true;

			}
			

		} catch (Exception e) {
			commonUTIL.displayError("LimitConfigSQL", INSERT_FROM_message, e);
			return 0;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("LimitConfigSQL", INSERT_FROM_message, e);
			}
		}
		return id;
	}

	protected static LimitConfig select(int messageIn, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Vector Messages = new Vector();
		LimitConfig limitConfig = new LimitConfig();
		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTONE + messageIn);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
							
				limitConfig.setId(rs.getInt(1));
				limitConfig.setConfig_name(rs.getString(2));
				limitConfig.setFilterType(rs.getString(3));
				limitConfig.setFilterValue(rs.getString(4));
				limitConfig.setCreateTime(rs.getString(5));
				limitConfig.setModifiedTime(rs.getString(6));
				
				return limitConfig;

			}
			
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "select", e);
			return limitConfig;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitConfigSQL", "selectMax", e);
			}
		}
		return limitConfig;
	}

	protected static Collection select(Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector limitConfigVec = new Vector();

		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTALL);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				LimitConfig limitConfig = new LimitConfig();

				
				limitConfig.setId(rs.getInt(1));
				limitConfig.setConfig_name(rs.getString(2));
				
				
				limitConfigVec.add(limitConfig);

			}
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", SELECTALL, e);
			return limitConfigVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitConfigSQL", SELECTALL, e);
			}
		}
		return limitConfigVec;
	}

	protected static Collection selectLimitUsage(int tradeid, Connection con) {
		
		int j = 0;
		PreparedStatement stmt = null;
		Vector limitConfigVec = new Vector();

		try {
			
			SELECTONE = SELECTONE + tradeid;
			stmt = dsSQL.newPreparedStatement(con, SELECTONE);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				LimitConfig limitConfig = new LimitConfig();

				
				limitConfig.setId(rs.getInt(1));
				limitConfig.setConfig_name(rs.getString(2));
				limitConfig.setFilterType(rs.getString(3));
				limitConfig.setFilterValue(rs.getString(4));
				limitConfig.setCreateTime(rs.getString(5));
				limitConfig.setModifiedTime(rs.getString(6));
				
				limitConfigVec.add(limitConfig);

			}
		} catch (Exception e) {
			
			commonUTIL.displayError("LimitConfigSQL", "selectmessage", e);
			return limitConfigVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitConfigSQL", "selectMax", e);
			}
		}
		return limitConfigVec;
	}

	public static Collection selectLimitUsageOnConfigName(String configName,
			Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		String sql = SELECTONLIMITCONFIG + "'" + configName + "'";
		Vector limitConfigVec = new Vector();

		try {
			con.setAutoCommit(true);
			
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				LimitConfig limitConfig = new LimitConfig();

				
				limitConfig.setId(rs.getInt(1));
				limitConfig.setConfig_name(rs.getString(2));
				limitConfig.setFilterType(rs.getString(3));
				limitConfig.setFilterValue(rs.getString(4));
				limitConfig.setCreateTime(rs.getString(5));
				limitConfig.setModifiedTime(rs.getString(6));
				
				limitConfigVec.add(limitConfig);

			}
			
			commonUTIL.display("LimitConfigSQL", sql);
			
		} catch (Exception e) {
			
			commonUTIL.displayError("LimitConfigSQL", "selectLimitUsageOnConfigName " + sql, e);
			return limitConfigVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitConfigSQL", "selectLimitUsageOnConfigName",
						e);
			}
		}
		return limitConfigVec;
	}
	public static Collection getLimitConfigWhere(String where,
			Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		String sql = SELECT +  where ;
		sql = SELECTLIMITTYPECONFIG + " id  in ("+sql+") order by id  ";
		Vector limitConfigVec = new Vector();

		try {
			con.setAutoCommit(true);
			
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				LimitConfig limitConfig = new LimitConfig();

				
				limitConfig.setId(rs.getInt(1));
				limitConfig.setConfig_name(rs.getString(2));
				limitConfig.setFilterType(rs.getString(3));
				limitConfig.setFilterValue(rs.getString(4));
				limitConfig.setCreateTime(rs.getString(5));
				limitConfig.setModifiedTime(rs.getString(6));
				
				limitConfigVec.add(limitConfig);

			}
			
			commonUTIL.display("LimitConfigSQL", sql);
			
		} catch (Exception e) {
			
			commonUTIL.displayError("LimitConfigSQL", "getLimitConfigWhere " + sql, e);
			return limitConfigVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitConfigSQL", "getLimitConfigWhere",
						e);
			}
		}
		return limitConfigVec;
	}
	public static Collection getLimitConfigSQLWhere(String where,
			Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		String sql = SELECTSQL +  where ;
		
		Vector limitConfigVec = new Vector();

		try {
			con.setAutoCommit(true);
			
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				LimitConfig limitConfig = new LimitConfig();

				
				limitConfig.setId(rs.getInt(1));
				limitConfig.setConfig_name(rs.getString(2));
				limitConfig.setLimitType(rs.getString(3));
				limitConfig.setFilterType(rs.getString(4));
				limitConfig.setFilterValue(rs.getString(5));
				limitConfig.setCreateTime(rs.getString(6));
				limitConfig.setModifiedTime(rs.getString(7));
				limitConfig.setParentID(rs.getInt(8));
				limitConfigVec.add(limitConfig);

			}
			
			commonUTIL.display("LimitConfigSQL", sql);
			
		} catch (Exception e) {
			
			commonUTIL.displayError("LimitConfigSQL", "getLimitConfigWhere " + sql, e);
			return limitConfigVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitConfigSQL", "getLimitConfigWhere",
						e);
			}
		}
		return limitConfigVec;
	}

	public static LimitConfig getUniqueLimitConfig(int limitConfigID,Connection con) {
		// TODO Auto-generated method stub
		int j = 0;
		PreparedStatement stmt = null;
		String sql = SELECTUNIQUE +  limitConfigID + " group by id, config_name ";
		LimitConfig limitConfig = new LimitConfig();
		try {
			con.setAutoCommit(true);
			
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				limitConfig.setId(rs.getInt(1));
				limitConfig.setConfig_name(rs.getString(2));
				
		
			}
			
			commonUTIL.display("LimitConfigSQL" , "getUniqueLimitConfig " + sql);
			
		} catch (Exception e) {
			
			commonUTIL.displayError("LimitConfigSQL", "getUniqueLimitConfig  " + sql, e);
			return null;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitConfigSQL", "getLimitConfigWhere",
						e);
			}
		}
		return limitConfig;
	}
		
}
