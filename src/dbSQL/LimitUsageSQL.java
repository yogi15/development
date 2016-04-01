package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.Limit;
import beans.LimitUsage;

public class  LimitUsageSQL{

final static private String tableName = "limitUsage";
	
	final static private String DELETE_FROM_LIMITUSAGE= "DELETE FROM limitUsage where id =? ";
	
	final static private String INSERT_FROM_message = new StringBuffer("INSERT into limitUsage (")
			.append(" limitId,  limitConfigId,  startdate, enddate, amount_used, comments, Tenor, tradeId) ")
			.append(" values (?,?,?,?,?,?,?,?) ")
			.toString();
	
	final static private String SELECT_MAX = "SELECT max(limitId) FROM " + tableName;
	
	final static private String SELECTALL =  new StringBuffer("SELECT")
	.append(" limitId,  limitConfigId,  startdate, enddate, amount_used, comments, Tenor, tradeId ")
	.append("FROM limitUsage order by limitId")
	.toString();
	
	final static private String SELECT = "SELECT limitConfigId FROM limitUsage where id =  ?";
	
	static private String SELECTONE = new StringBuffer("SELECT")
	.append(" limitId,  limitConfigId,  startdate, enddate, amount_used, comments, Tenor, tradeId ")
	.append("FROM limitUsage ")
	.append("where limitId = ")
	.toString();
	
	final static private String SELECTONLIMITCONFIG = new StringBuffer("SELECT")
	.append(" limitId,  limitConfigId,  startdate, enddate, amount_used, comments, Tenor, tradeId ")
	.append("FROM limitUsage ")
	.append("where limitConfigId = ")
	.toString();

	final static private String selectLimitUsage = new StringBuffer("SELECT")
	.append(" sum(amount_used) limitUsed ")
	.append("FROM limitUsage ")
	.append("where  ")
	.toString();
	
	
	private static String getUpdateSQL(LimitUsage limitUsage) {

		String updateSQL = new StringBuffer("UPDATE ").append(tableName)
				.append(" set limitId = ").append(limitUsage.getLimitId()).append(",")
				.append(" limitConfigId = ").append(limitUsage.getLimitConfigId()).append(",")
				.append(" startdate = '").append(limitUsage.getStartdate()).append("',")
				.append(" enddate = '").append(limitUsage.getEnddate()).append("',")
				.append(" amount_used = '").append(limitUsage.getAmount_used()).append("',")
				.append(" comments = '").append(limitUsage.getComments()).append("',")					
				.append(" Tenor = '").append(limitUsage.getTenor()).append("',")
				.append(" tradeId = ").append(limitUsage.getTradeId())
				.toString();

		System.out.println(updateSQL);

		return updateSQL;

	}

	public static boolean save(LimitUsage insertMessage, Connection con) {
		try {
			return insert(insertMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitUsageSQL", "save", e);
			return false;
		}
	}

	public static boolean update(LimitUsage updateMessage, Connection con) {
		try {
			return edit(updateMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitUsageSQL", "update", e);
			return false;
		}
	}

	public static boolean delete(LimitUsage deleteMessage, Connection con) {
		try {
			return remove(deleteMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitUsageSQL", "update", e);
			return false;
		}
	}

	public static Collection selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitUsageSQL", "selectALL", e);
			return null;
		}
	}

	public static int selectMaxID(Connection con) {
		try {
			return selectMax(con);
		} catch (Exception e) {
			commonUTIL.displayError("LimitUsageSQL", "selectMaxID", e);
			return 0;
		}
	}

	protected static boolean edit(LimitUsage updateMessage, Connection con) {

		PreparedStatement stmt = null;
		String sql = "";
		boolean hasEdit = false;
		
		try {

			con.setAutoCommit(false);
			sql = getUpdateSQL(updateMessage);
			stmt = con.prepareStatement(sql);
			
			commonUTIL.display("LimitUsageSQL", "edit " + sql);

			if (stmt.executeUpdate() > 0) {

				con.commit();
				hasEdit =true;

			} 
			
		} catch (Exception e) {
			commonUTIL.displayError("LimitUsageSQL ", "edit " + sql, e);
			return false;

		} finally {
			try {
				con.setAutoCommit(true);
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitUsageSQL", "edit", e);
			}
		}
		return hasEdit;
	}

	protected static boolean remove(LimitUsage deleteMessage, Connection con) {

		PreparedStatement stmt = null;
		boolean hasDeleted = false;
		try {
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_LIMITUSAGE);
			stmt.setInt(j++, deleteMessage.getLimitId());

			if (stmt.executeUpdate() > 0) {

				con.commit();
				hasDeleted =true;

			} 

		} catch (Exception e) {
			commonUTIL.displayError("LimitUsageSQL", "remove", e);
			return false;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitUsageSQL", "remove", e);
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
				j = rs.getInt("DESC_ID");

		} catch (Exception e) {
			commonUTIL.displayError("LimitUsageSQL", SELECT_MAX, e);
			return j;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LimitUsageSQL", SELECT_MAX, e);
			}
		}
		return j;
	}

	protected static boolean insert(LimitUsage inserMessage, Connection con) {

		PreparedStatement stmt = null;
		
		boolean isSave = false;
		
		int id = 0;
		
		try {
					
			commonUTIL.display("LimitUsageSQL", INSERT_FROM_message);
			
			con.setAutoCommit(false);
	//		id = selectMax(con);
			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_message);
			
			stmt.setInt(1, inserMessage.getLimitId());
			stmt.setInt(2, inserMessage.getLimitConfigId());
			stmt.setString(3, inserMessage.getStartdate());
			stmt.setString(4, inserMessage.getEnddate());
			stmt.setDouble(5, inserMessage.getAmount_used());
			stmt.setString(6, inserMessage.getComments());
			stmt.setString(7, inserMessage.getTenor());
			stmt.setInt(8, inserMessage.getTradeId());
			
			 stmt.executeUpdate();
	          //  con.commit();

				con.commit();
				isSave = true;

			
			

		} catch (Exception e) {
			commonUTIL.displayError("LimitUsageSQL", INSERT_FROM_message, e);
			return isSave;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("LimitUsageSQL", INSERT_FROM_message, e);
			}
		}
		return isSave;
	}

	protected static LimitUsage select(int messageIn, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Vector Messages = new Vector();
		LimitUsage limitUsage = new LimitUsage();
		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTONE + messageIn);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
								
				limitUsage.setLimitId(rs.getInt(1));
				limitUsage.setLimitConfigId(rs.getInt(2));
				limitUsage.setStartdate(rs.getString(3));
				limitUsage.setEnddate(rs.getString(4));
				limitUsage.setAmount_used(rs.getDouble(5));
				limitUsage.setComments(rs.getString(6));
				limitUsage.setTenor(rs.getString(7));
				limitUsage.setTradeId(rs.getInt(8));
				
				return limitUsage;

			}
			
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "select", e);
			return limitUsage;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitUsageSQL", "selectMax", e);
			}
		}
		return limitUsage;
	}

	protected static Collection select(Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector limitUsageVec = new Vector();

		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTALL);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				LimitUsage limitUsage = new LimitUsage();

				limitUsage.setLimitId(rs.getInt(1));
				limitUsage.setLimitConfigId(rs.getInt(2));
				limitUsage.setStartdate(rs.getString(3));
				limitUsage.setEnddate(rs.getString(4));
				limitUsage.setAmount_used(rs.getDouble(5));
				limitUsage.setComments(rs.getString(6));
				limitUsage.setTenor(rs.getString(7));
				limitUsage.setTradeId(rs.getInt(8));
				
				limitUsageVec.add(limitUsage);

			}
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", SELECTALL, e);
			return limitUsageVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitUsageSQL", SELECTALL, e);
			}
		}
		return limitUsageVec;
	}

	protected static Collection selectLimitUsage(int tradeid, Connection con) {
		
		int j = 0;
		PreparedStatement stmt = null;
		Vector limitUsageVec = new Vector();

		try {
			
			SELECTONE = SELECTONE + tradeid;
			stmt = dsSQL.newPreparedStatement(con, SELECTONE);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				LimitUsage limitUsage = new LimitUsage();

				limitUsage.setLimitId(rs.getInt(1));
				limitUsage.setLimitConfigId(rs.getInt(2));
				limitUsage.setStartdate(rs.getString(3));
				limitUsage.setEnddate(rs.getString(4));
				limitUsage.setAmount_used(rs.getDouble(5));
				limitUsage.setComments(rs.getString(6));
				limitUsage.setTenor(rs.getString(7));
				limitUsage.setTradeId(rs.getInt(8));
				
				limitUsageVec.add(limitUsage);

			}
		} catch (Exception e) {
			
			commonUTIL.displayError("LimitUsageSQL", "selectmessage", e);
			return limitUsageVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitUsageSQL", "selectMax", e);
			}
		}
		return limitUsageVec;
	}

	public static Collection selectLimitUsageOnLimitId(int limitusageId,
			Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		String sql = SELECTONLIMITCONFIG + limitusageId + " order by id";
		Vector limitUsageVec = new Vector();

		try {
			con.setAutoCommit(true);
			
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				LimitUsage limitUsage = new LimitUsage();

				limitUsage.setLimitId(rs.getInt(1));
				limitUsage.setLimitConfigId(rs.getInt(2));
				limitUsage.setStartdate(rs.getString(3));
				limitUsage.setEnddate(rs.getString(4));
				limitUsage.setAmount_used(rs.getDouble(5));
				limitUsage.setComments(rs.getString(6));
				limitUsage.setTenor(rs.getString(7));
				limitUsage.setTradeId(rs.getInt(8));
				
				limitUsageVec.add(limitUsage);

			}
			
			commonUTIL.display("LimitUsageSQL", sql);
			
		} catch (Exception e) {
			
			commonUTIL.displayError("LimitUsageSQL", "selectLimitUsageOnLimitId " + sql, e);
			return limitUsageVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LimitUsageSQL", "selectLimitUsageOnLimitId",
						e);
			}
		}
		return limitUsageVec;
	}

	public static LimitUsage getLimitUsage(Limit limit, Connection conn) {
	
			int j = 0;
			PreparedStatement stmt = null;
			String sql = selectLimitUsage  + " limitid =  " + limit.getId() + " and limitConfigid = " + limit.getLimitConfigId(); 
			Vector limitUsageVec = new Vector();
			LimitUsage limitUsage = new LimitUsage();
			try {
				conn.setAutoCommit(true);
				
				stmt = dsSQL.newPreparedStatement(conn, sql);

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					
					

					
					limitUsage.setAmount_used(rs.getDouble("limitUsed"));
					
					
					
				}
				
				commonUTIL.display("LimitUsageSQL", sql);
				
			} catch (Exception e) {
				
				commonUTIL.displayError("LimitUsageSQL", "selectLimitUsageOnLimitId " + sql, e);
				return null;

			} finally {
				try {
					stmt.close();
					//con.close();
				} catch (SQLException e) {
					commonUTIL.displayError("LimitUsageSQL", "selectLimitUsageOnLimitId",
							e);
				}
			}
			return limitUsage;
		}

	}

