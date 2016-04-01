package dbSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.Limit;
import beans.LimitConfig;

public class LimitSQL {
	
	
	final static private String tableName = "limit";
	
	final static private String DELETE_FROM_LIMIT= "DELETE FROM limit where id =? ";
	
	final static private String INSERT_FROM_message = new StringBuffer("INSERT into limit(")
			.append(" id,  limitConfigId,  limitType, limitDate, limitAvaliableDate, limitexpirydate,limitmax,limitmin,limitWarning,limitTolarance)")
			.append(" values (?,?,?,?,?,?,?,?,?,?) ")
			.toString();
	
	final static private String SELECT_MAX = "SELECT LIMIT_SEQ.NEXTVAL LimitId FROM dual ";
	
	final static private String SELECTALL =  new StringBuffer("SELECT ")
	.append("id,  limitConfigId,  limitType, limitDate, limitAvaliableDate, limitexpirydate,limitmax,limitmin,limitWarning,limitTolarance ")
	.append("FROM limit order by id")
	.toString();
	
	final static private String SELECT = "SELECT limitConfigId FROM limit where id =  ?";
	
	static private String SELECTONE = new StringBuffer("SELECT ")
	.append(" id,  limitConfigId,  limitType, limitDate, limitAvaliableDate, limitexpirydate,limitmax,limitmin,limitWarning,limitTolarance ")
	.append("FROM limit ")
	.append("where id = ")
	.toString();
	
	final static private String SELECTONCONFIG = new StringBuffer("SELECT")
	.append(" id,  limitConfigId,  limitType, limitDate, limitAvaliableDate, limitexpirydate,limitmax,limitmin,limitWarning,limitTolarance  ")
	.append("FROM limit ")
	.append(" where limitConfigId = ")
	.toString();

	final static private String SELECTONWhere= new StringBuffer("SELECT")
	.append(" id,  limitConfigId,  limitType, limitDate, limitAvaliableDate, limitexpirydate,limitmax,limitmin,limitWarning,limitTolarance  ")
	.append("FROM limit ")
	.append(" where  ")
	.toString();
	
	private static String getUpdateSQL(Limit limit) {

		String updateSQL = new StringBuffer("UPDATE ").append(tableName)
				.append(" set id = ").append(limit.getId()).append(",")
				.append(" limitConfigId = ").append(limit.getLimitConfigId()).append(",")
				.append(" limitType = '").append(limit.getLimitType()).append("',")
				.append(" limitDate = '").append(limit.getLimitDate()).append("',")
				.append(" limitAvaliableDate = '").append(limit.getLimitAvaliableDate()).append("',")
				.append(" limitexpirydate = '").append(limit.getLimitExpiryDate()).append(",")	
				.append(" limitmax = ").append(limit.getLimitmax()).append(",")	
				.append(" limitmin = ").append(limit.getLimitmin()).append(",")	
				.append(" limitWarning = ").append(limit.getLimitWarning()).append("'")	
				.append(" limitTolarance = ").append(limit.getLimitTolarance()).append(" ")	
				.toString();
            updateSQL = updateSQL + " id = " + limit.getId();
		System.out.println(updateSQL);

		return updateSQL;

	}

	public static int save(Limit insertMessage, Connection con) {
		try {
			return insert(insertMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitSQL", "save", e);
			return 0;
		}
	}

	public static boolean update(Limit updateMessage, Connection con) {
		try {
			return edit(updateMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitSQL", "update", e);
			return false;
		}
	}

	public static boolean delete(Limit deleteMessage, Connection con) {
		try {
			return remove(deleteMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitSQL", "update", e);
			return false;
		}
	}

	public static Collection selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitSQL", "selectALL", e);
			return null;
		}
	}

	public static int selectMaxID(Connection con) {
		try {
			return selectMax(con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitSQL", "selectMaxID", e);
			return 0;
		}
	}

	protected static boolean edit(Limit updateMessage, Connection con) {

		PreparedStatement stmt = null;
		String sql = "";
		boolean hasEdit = false;
		
		try {

			con.setAutoCommit(false);
			sql = getUpdateSQL(updateMessage);
			stmt = con.prepareStatement(sql);
			
			commonUTIL.display("LimitSQL", "edit " + sql);

			if (stmt.executeUpdate() > 0) {

				con.commit();
				hasEdit =true;

			} 
			
		} catch (Exception e) {
			commonUTIL.displayError("LimitSQL ", "edit " + sql, e);
			return false;

		} finally {
			try {
				con.setAutoCommit(true);
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitSQL", "edit", e);
			}
		}
		return hasEdit;
	}

	protected static boolean remove(Limit deleteMessage, Connection con) {

		PreparedStatement stmt = null;
		boolean hasDeleted = false;
		try {
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_LIMIT);
			stmt.setInt(j++, deleteMessage.getId());

			if (stmt.executeUpdate() > 0) {

				con.commit();
				hasDeleted =true;

			} 

		} catch (Exception e) {
			commonUTIL.displayError("LimitSQL", "remove", e);
			return false;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitSQL", "remove", e);
			}
		}
		return hasDeleted;
	}

	protected static int selectMax(Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		try {

			stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);

			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				j = rs.getInt("LimitId");

		} catch (Exception e) {
			commonUTIL.displayError("LimitSQL", SELECT_MAX, e);
			return j;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LimitSQL", SELECT_MAX, e);
			}
		}
		return j;
	}

	protected static int insert(Limit inserMessage, Connection con) {

		PreparedStatement stmt = null;
		
		boolean isSave = false;
		
		int id = 0;
		
		try {
			
			
			
			con.setAutoCommit(false);
			 id = selectMax(con);
			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_message);
			
			stmt.setInt(1, id);
			stmt.setInt(2, inserMessage.getLimitConfigId());
			stmt.setString(3, inserMessage.getLimitType());
			stmt.setDate(4, commonUTIL.convertStringtoSQLDate(inserMessage.getLimitDate()));
			stmt.setDate(5, commonUTIL.convertStringtoSQLDate(inserMessage.getLimitAvaliableDate()));
			stmt.setDate(6, commonUTIL.convertStringtoSQLDate(inserMessage.getLimitExpiryDate()));
			stmt.setDouble(7, inserMessage.getLimitmax());
			stmt.setDouble(8, inserMessage.getLimitmin());
			stmt.setDouble(9, inserMessage.getLimitWarning());
			stmt.setDouble(10, inserMessage.getLimitTolarance());
			
			if (stmt.executeUpdate() > 0) {

				con.commit();
				isSave = true;
				 commonUTIL.display("LimitSQL", INSERT_FROM_message);
			}

		} catch (Exception e) {
			commonUTIL.displayError("LimitSQL", INSERT_FROM_message, e);
			return 0;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("LimitSQL", INSERT_FROM_message, e);
			}
		}
		return id;
	}

	protected static Limit select(int messageIn, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Vector Messages = new Vector();
		Limit limit = new Limit();
		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTONE + messageIn);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
								
				limit.setId(rs.getInt(1));
				limit.setLimitConfigId(rs.getInt(2));
				limit.setLimitType(rs.getString(3));
				limit.setLimitDate(rs.getString(4));
				limit.setLimitAvaliableDate(rs.getString(5));
				limit.setLimitExpiryDate(rs.getString(6));
				limit.setLimitmax(rs.getDouble(7));
				limit.setLimitmin(rs.getDouble(8));
				limit.setLimitWarning(rs.getDouble(9));
				limit.setLimitTolarance(rs.getDouble(10));
				
				return limit;

			}
			
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "select", e);
			return limit;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitSQL", "selectMax", e);
			}
		}
		return limit;
	}

	protected static Collection select(Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector limitVec = new Vector();

		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTALL);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Limit limit = new Limit();

				limit.setId(rs.getInt(1));
				limit.setLimitConfigId(rs.getInt(2));
				limit.setLimitType(rs.getString(3));
				limit.setLimitDate(rs.getString(4));
				limit.setLimitAvaliableDate(rs.getString(5));
				limit.setLimitExpiryDate(rs.getString(6));
				limit.setLimitmax(rs.getDouble(7));
				limit.setLimitmin(rs.getDouble(8));
				limit.setLimitWarning(rs.getDouble(9));
				limit.setLimitTolarance(rs.getDouble(10));
				
				limitVec.add(limit);

			}
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", SELECTALL, e);
			return limitVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitSQL", SELECTALL, e);
			}
		}
		return limitVec;
	}

	protected static Collection selectmessage(int tradeid, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector limitVec = new Vector();

		try {
			SELECTONE = SELECTONE + tradeid;
			stmt = dsSQL.newPreparedStatement(con, SELECTONE);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				Limit limit = new Limit();

				limit.setId(rs.getInt(1));
				limit.setLimitConfigId(rs.getInt(2));
				limit.setLimitType(rs.getString(3));
				limit.setLimitDate(rs.getString(4));
				limit.setLimitAvaliableDate(rs.getString(5));
				limit.setLimitExpiryDate(rs.getString(6));
				limit.setLimitmax(rs.getDouble(7));
				limit.setLimitmin(rs.getDouble(8));
				limit.setLimitWarning(rs.getDouble(9));
				limit.setLimitTolarance(rs.getDouble(10));
				
				limitVec.add(limit);

			}
		} catch (Exception e) {
			commonUTIL.displayError("LimitSQL", "selectmessage", e);
			return limitVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitSQL", "selectMax", e);
			}
		}
		return limitVec;
	}

	public static Collection selectLimitOnLimitConfigId(int limitConfigid,
			Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		String sql = SELECTONCONFIG + limitConfigid;
		Vector limitVec = new Vector();

		try {
			con.setAutoCommit(true);
			
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Limit limit = new Limit();

				limit.setId(rs.getInt(1));
				limit.setLimitConfigId(rs.getInt(2));
				limit.setLimitType(rs.getString(3));
				limit.setLimitDate(rs.getString(4));
				limit.setLimitAvaliableDate(rs.getString(5));
				limit.setLimitExpiryDate(rs.getString(6));
				limit.setLimitmax(rs.getDouble(7));
				limit.setLimitmin(rs.getDouble(8));
				limit.setLimitWarning(rs.getDouble(9));
				limit.setLimitTolarance(rs.getDouble(10));
				
				limitVec.add(limit);

			}
			commonUTIL.display("LimitSQL", sql);
		} catch (Exception e) {
			commonUTIL.displayError("LimitSQL", "selectLimitOnLimitConfigId " + sql, e);
			return limitVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitSQL", "selectmessageOnProduct",
						e);
			}
		}
		return limitVec;
	}

	public static Collection getLiveLimitsOnLimitConfigID(String limitConfigId,Connection con) {
		// TODO Auto-generated method stub
		String sql = SELECTONWhere + " limitConfigId in ("+limitConfigId+" ) and sysdate between LIMITAVALIABLEDATE and LIMITEXPIRYDATE ";
		Vector limitVec = new Vector();
		PreparedStatement stmt = null;
		
		try {
			con.setAutoCommit(true);
			
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Limit limit = new Limit();

				limit.setId(rs.getInt(1));
				limit.setLimitConfigId(rs.getInt(2));
				limit.setLimitType(rs.getString(3));
				limit.setLimitDate(rs.getString(4));
				limit.setLimitAvaliableDate(rs.getString(5));
				limit.setLimitExpiryDate(rs.getString(6));
				limit.setLimitmax(rs.getDouble(7));
				limit.setLimitmin(rs.getDouble(8));
				limit.setLimitWarning(rs.getDouble(9));
				limit.setLimitTolarance(rs.getDouble(10));
				
				limitVec.add(limit);

			}
			commonUTIL.display("LimitSQL", sql);
		} catch (Exception e) {
			commonUTIL.displayError("LimitSQL", "selectLimitOnLimitConfigId " + sql, e);
			return null;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitSQL", "selectmessageOnProduct",
						e);
			}
		}
		return limitVec;
	}

	public static Vector<LimitConfig> getLimits(int limitConfigID) {
		
		// TODO Auto-generated method stub
		
		return null;
	}

}
