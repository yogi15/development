package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.DateRule;
import beans.DateRule;
import beans.DateRule;
import beans.LimitConfig;

public class DateRuleSQL  {
	
	  
final static private String tableName = "DateRule";
	
	final static private String DELETE_FROM_LIMITUSAGE= "DELETE FROM DateRule where id =? ";
	
	final static private String INSERT_FROM_message = new StringBuffer("INSERT into DateRule(")
			.append(" ID,type,dateRollType,day,days,mon_year_day_week,end_beg,rank,weekdaysType,months,name,holiday)  ")
			.append(" values (?,?,?,?,?,?,?,?,?,?,?,?) ")
			.toString();
	
	final static private String SELECT_MAX = "SELECT DateRule_SEQ.NEXTVAL Id FROM dual ";
	
	final static private String SELECTALL =  new StringBuffer("SELECT ")
	.append( "  ID,type,dateRollType,day,days,mon_year_day_week,end_beg,rank,weekdaysType,months,name,holiday ")
	.append(" FROM DateRule  order by id")
	.toString();
	
	final static private String SELECTUNIQUE =  new StringBuffer("SELECT")
	.append("  id,  config_name  ")
	.append("  FROM DateRule where id =  ")
	.toString();
	final static private String SELECTONTYPE =  new StringBuffer("SELECT")
	.append("  id,  config_name  ")
	.append("  FROM DateRule where  type =  " )
	.toString();
	
	final static private String SELECT = "SELECT Id FROM DateRule where   ";
	final static private String SELECTSQL  = "SELECT ID, type,  dateRollType,day,days,mon_year_day_week,end_beg,rank,weekdaysType,months,name,holiday  FROM DateRule where   ";
	final static private String SELECTDATERULECONFIG = "SELECT ID, type,  dateRollType,day,days,mon_year_day_week,end_beg,rank,weekdaysType,months,name,holiday  FROM DateRule where   ";
	static private String SELECTONE = new StringBuffer("SELECT ")
	.append(" ID, type,  dateRollType,day,days,mon_year_day_week,end_beg,rank,weekdaysType,months,name,holiday ")
	.append("FROM DateRule ")
	.append("where id = ")
	.toString();
	static private String SELECTONEONNAME = new StringBuffer("SELECT ")
	.append(" ID, type,  dateRollType,day,days,mon_year_day_week,end_beg,rank,weekdaysType,months,name,holiday ")
	.append("FROM DateRule ")
	.append("where name = ")
	.toString();
	
	
	
	
	final static private String SELECTONDateRule = new StringBuffer("SELECT")
	.append(" ID, type,  dateRollType,day,days,mon_year_day_week,end_beg,rank,weekdaysType,months,name,holiday ")
	.append("FROM DateRule ")
	.append("where name = ")
	.toString();

	private static String getUpdateSQL(DateRule dateRule) {

		String updateSQL = new StringBuffer("UPDATE ").append(tableName)
				.append(" set id = ").append(dateRule.getId()).append(",")
				.append(" type = '").append(dateRule.getType()).append("',")
				.append(" end_beg = '").append(dateRule.getEnd_beg()).append("',")
				.append(" mon_year_day_week = '").append(dateRule.getMon_year_day_week()).append("',")
				.append(" months = '").append(dateRule.getMonths()).append("',")
				.append(" day = ").append(dateRule.getDay()).append(",")	
				.append(" days = ").append(dateRule.getDays()).append(",")		
				.append(" dateRollType = '").append(dateRule.getDateROLL()).append("',")	
				.append(" weekdaysType = '").append(dateRule.getWeekdaysType()).append("',")	
				.append(" rank = '").append(dateRule.getRank()).append("',")	
				.append(" holiday = '").append(dateRule.getHolidayCurr()).append("'")	
				.append(" where id = ").append(dateRule.getId()).append("")	
			//	.append(" modifiedTime = '").append(dateRule.getDateROLL()).append("'")	
				.toString();

	//	System.out.println(updateSQL);

		return updateSQL;

	}
	
	public static int save(DateRule insertMessage, Connection con) {
		try {
			return insert(insertMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("DateRuleSQL", "save", e);
			return 0;
		}
	}

	public static boolean update(DateRule updateMessage, Connection con) {
		try {
			return edit(updateMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("DateRuleSQL", "update", e);
			return false;
		}
	}

	public static boolean delete(DateRule deleteMessage, Connection con) {
		try {
			return remove(deleteMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("DateRuleSQL", "update", e);
			return false;
		}
	}

	public static Collection selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("DateRuleSQL", "selectALL", e);
			return null;
		}
	}

	private static Collection select(Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int selectMaxID(Connection con) {
		try {
			return selectMax(con);
		} catch (Exception e) {
			commonUTIL.displayError("DateRuleSQL", "selectMaxID", e);
			return 0;
		}
	}

	protected static boolean edit(DateRule updateMessage, Connection con) {

		PreparedStatement stmt = null;
		String sql = "";
		boolean hasEdit = false;
		int id = 0;
		try {

			con.setAutoCommit(false);
			sql = getUpdateSQL(updateMessage);
			stmt = con.prepareStatement(sql);
			
			commonUTIL.display("DateRuleSQL", "edit " + sql);

			if (stmt.executeUpdate() > 0) {

				con.commit();
				hasEdit =true;

			} 
			
		} catch (Exception e) {
			commonUTIL.displayError("DateRuleSQL ", "edit " + sql, e);
			return false;

		} finally {
			try {
				con.setAutoCommit(true);
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("DateRuleSQL", "edit", e);
			}
		}
		return hasEdit;
	}

	protected static boolean remove(DateRule deleteMessage, Connection con) {

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
			commonUTIL.displayError("DateRuleSQL", "remove", e);
			return false;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("DateRuleSQL", "remove", e);
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
				j = rs.getInt("Id");

		} catch (Exception e) {
			commonUTIL.displayError("DateRuleSQL", SELECT_MAX, e);
			return j;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
			
				commonUTIL.displayError("DateRuleSQL", SELECT_MAX, e);
			}
		}
		return j;
	}
	
	protected static int insert(DateRule inserMessage, Connection con) {

		PreparedStatement stmt = null;
		
		boolean isSave = false;
		
		int id = 0;
		
		try {
			 
			commonUTIL.display("DateRuleSQL", INSERT_FROM_message);
			
			con.setAutoCommit(false);
			
			id = selectMax(con);
		
			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_message);
			//id = inserMessage.getId();
			stmt.setInt(1, id);
			stmt.setString(2, inserMessage.getType());
			stmt.setString(3, inserMessage.getDateROLL());
			stmt.setInt(4, inserMessage.getDay());
			stmt.setInt(5, inserMessage.getDays());
			stmt.setString(6, inserMessage.getMon_year_day_week());
			stmt.setString(7, inserMessage.getEnd_beg());
			stmt.setString(8, inserMessage.getRank());
			stmt.setString(9, inserMessage.getWeekdaysType());
			stmt.setString(10, inserMessage.getMonths());
			stmt.setString(11, inserMessage.getName());
			stmt.setString(12, inserMessage.getHolidayCurr());
						
			if (stmt.executeUpdate() > 0) {

				con.commit();
				isSave = true;

			}
			

		} catch (Exception e) {
			commonUTIL.displayError("DateRuleSQL", INSERT_FROM_message, e);
			return 0;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("DateRuleSQL", INSERT_FROM_message, e);
			}
		}
		return id;
	}

	
	public static DateRule select(int id, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Vector Messages = new Vector();
		DateRule dateRule = new DateRule();
		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTONE + id);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
							
				dateRule.setId(rs.getInt(1));
				dateRule.setType(rs.getString(2));
				dateRule.setDateROLL(rs.getString(3));
				dateRule.setDay(rs.getInt(4));
				dateRule.setDays(rs.getInt(5));
				dateRule.setMon_year_day_week(rs.getString(6));
				dateRule.setEnd_beg(rs.getString(7));
				dateRule.setRank(rs.getString(8));
				dateRule.setWeekdaysType(rs.getString(9));
				dateRule.setMonths(rs.getString(10));
				dateRule.setName(rs.getString(11));
				dateRule.setHolidayCurr(rs.getString(12));
			

			}
			
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "select", e);
			return dateRule;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("DateRuleSQL", "selectMax", e);
			}
		}
		return dateRule;
	}

	public static Vector getSelectDateRules(Connection conn) {
		// TODO Auto-generated method stub
	int j = 0;
		PreparedStatement stmt = null;
		Vector<DateRule> dateRules = new Vector<DateRule>();
		
		try {

			stmt = dsSQL.newPreparedStatement(conn,SELECTALL);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				 DateRule dateRule = new DateRule();
				 dateRule.setId(rs.getInt(1));
				 dateRule.setType(rs.getString(2));
				 dateRule.setDateROLL(rs.getString(3));
				dateRule.setDay(rs.getInt(4));
				dateRule.setDays(rs.getInt(5));
				dateRule.setMon_year_day_week(rs.getString(6));
				dateRule.setEnd_beg(rs.getString(7));
				dateRule.setRank(rs.getString(8));
				dateRule.setWeekdaysType(rs.getString(9));
				dateRule.setMonths(rs.getString(10));
				dateRule.setName(rs.getString(11));
				dateRule.setHolidayCurr(rs.getString(12));
				dateRules.add(dateRule);

			}
			
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "select", e);
			return dateRules;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("DateRuleSQL", "selectMax", e);
			}
		}
		return dateRules;
	}

	public static DateRule select(String dateRuleName, Connection conn) {
		// TODO Auto-generated method stub
		int j = 0;
		PreparedStatement stmt = null;
		Vector Messages = new Vector();
		DateRule dateRule = new DateRule();
		String sql = SELECTONEONNAME + "'"+dateRuleName+"'";
		try {

			stmt = dsSQL.newPreparedStatement(conn, sql);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
							
				dateRule.setId(rs.getInt(1));
				dateRule.setType(rs.getString(2));
				dateRule.setDateROLL(rs.getString(3));
				dateRule.setDay(rs.getInt(4));
				dateRule.setDays(rs.getInt(5));
				dateRule.setMon_year_day_week(rs.getString(6));
				dateRule.setEnd_beg(rs.getString(7));
				dateRule.setRank(rs.getString(8));
				dateRule.setWeekdaysType(rs.getString(9));
				dateRule.setMonths(rs.getString(10));
				dateRule.setName(rs.getString(11));
				dateRule.setHolidayCurr(rs.getString(12));
			

			}
			commonUTIL.display("DateRuleSQL", sql);
		} catch (Exception e) {
			commonUTIL.displayError("DateRuleSQL", "select", e);
			return dateRule;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("DateRuleSQL", "selectMax", e);
			}
		}
		return dateRule;
	}

}
