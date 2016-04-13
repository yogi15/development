package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import constants.CommonConstants;
import util.commonUTIL;
import beans.BaseBean;
import beans.HolidayCode;

public class HolidayCodeSQL extends BaseSQL {

	final static private String TABLEFROM = " From Holiday ";
	final static private String DELETE_FROM_HOLIDAY = "DELETE FROM HOLIDAY where Holidayno =? ";
	final static private String INSERT_FROM_HOLIDAY = "INSERT into HOLIDAY(currency,country,hdate,fweekday,sweekday,holidaycode) values(?,?,?,?,?,?)";
	final static private String UPDATE_FROM_HOLIDAY = "UPDATE HOLIDAY set currency=?,country=?,hdate=?,fweekday=?,sweekday=?  where currency = ? and country=?";
	final static private String SELECT_MAX = "SELECT MAX(Holidayno) DESC_ID FROM HOLIDAY";
	final static private String SELECTALL = "SELECT currency,country,hdate,fweekday,sweekday,holidaycode FROM HOLIDAY order by currency";
	final static private String SELECT = "SELECT currency,country,hdate,fweekday,sweekday,holidaycode FROM HOLIDAY where currency = ? and country=?";
	static private String SELECTONE = "SELECT currency,country,hdate,fweekday,sweekday,holidaycode FROM HOLIDAY where  ";

	private static String getHolidaysOnCurrencyPair(String cp, String date) {
		String sql = "			select getHolidaysonCurrencyPair('" + cp
				+ "',to_timestamp('" + date
				+ "','dd/mm/yyyy')+1) Holidays from dual";
		return sql;
	}

	private static String getupdateSQL(HolidayCode editHoliday) {
		String updateSQL = " update holiday set ";
		updateSQL = updateSQL + " currency = '" + editHoliday.getCurrency()
				+ "', ";
		updateSQL = updateSQL + " country = '" + editHoliday.getCountry()
				+ "', ";
		updateSQL = updateSQL + " hdate = '" + editHoliday.getHdate() + "', ";
		updateSQL = updateSQL + " fweekday = " + editHoliday.getFweekday()
				+ ", ";
		updateSQL = updateSQL + " sweekday = " + editHoliday.getSweekdday()
				+ ", ";
		updateSQL = updateSQL + " holidaycode = "
				+ editHoliday.getHolidayCode() + "  where currency = '"
				+ editHoliday.getCurrency() + "'";
		return updateSQL;

	}

	public static boolean save(HolidayCode insertHoliday, Connection con) {
		try {
			return insert(insertHoliday, con);
		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", "save", e);
			return false;
		}
	}

	public static boolean update(HolidayCode updateHoliday, Connection con) {
		try {
			return edit(updateHoliday, con);
		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", "update", e);
			return false;
		}
	}

	public static boolean delete(HolidayCode deleteHoliday, Connection con) {
		try {
			return remove(deleteHoliday, con);
		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", "update", e);
			return false;
		}
	}

	public static HolidayCode selectHoliday(String currency, Connection con) {
		try {
			return selectOLD(currency, con);
		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", "select", e);
			return null;
		}
	}

	public static Collection selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", "selectALL", e);
			return null;
		}
	}

	public static int selectMaxID(Connection con) {
		try {
			return selectMax(con);
		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", "selectMaxID", e);
			return 0;
		}
	}

	protected static boolean edit(HolidayCode updateHoliday, Connection con) {

		PreparedStatement stmt = null;
		String sql = getupdateSQL(updateHoliday);
		try {

			con.setAutoCommit(false);
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, sql);
			stmt.executeUpdate(sql);
			con.commit();
			commonUTIL.display("HolidaySQL ::  edit", sql);
			// con.commit();
		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", "edit", e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("HolidaySQL", sql, e);
			}
		}
		return true;
	}

	protected static boolean remove(HolidayCode deleteHoliday, Connection con) {

		PreparedStatement stmt = null;
		try {
			int j = 1;
			con.setAutoCommit(false);
			// stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_Holiday);
			// stmt.setInt(j++, deleteHoliday.getHolidayno());

			stmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", "remove", e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("HolidaySQL", "remove", e);
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
			commonUTIL.displayError("HolidaySQL", SELECT_MAX, e);
			return j;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("HolidaySQL", SELECT_MAX, e);
			}
		}
		return j;
	}

	protected static boolean insert(HolidayCode inserHoliday, Connection con) {

		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			int id = selectMax(con);
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_HOLIDAY);
			stmt.setString(1, inserHoliday.getCurrency());
			stmt.setString(2, inserHoliday.getCountry());
			stmt.setString(3, inserHoliday.getHdate());
			stmt.setInt(4, inserHoliday.getFweekday());
			stmt.setInt(5, inserHoliday.getSweekdday());

			stmt.setString(6, inserHoliday.getHolidayCode());
			stmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", INSERT_FROM_HOLIDAY, e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("HolidaySQL", INSERT_FROM_HOLIDAY, e);
			}
		}
		return true;
	}

	protected static HolidayCode selectOLD(String currency, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Vector Holidays = new Vector();
		HolidayCode HolidayCode = new HolidayCode();
		String sql = "";
		try {
			con.setAutoCommit(false);
			sql = SELECTONE + " currency = '" + currency + "'";
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				HolidayCode.setCurrency(rs.getString(1));
				HolidayCode.setCountry(rs.getString(2));
				HolidayCode.setHdate(rs.getString(3));
				HolidayCode.setFweekday(rs.getInt(4));
				HolidayCode.setSweekdday(rs.getInt(5));
				HolidayCode.setHolidayCode(rs.getString(6));
				return HolidayCode;

			}
		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", "select :: " + sql, e);
			return HolidayCode;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("HolidaySQL", "selectMax", e);
			}
		}
		return HolidayCode;
	}

	protected static Collection select(Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector Holidays = new Vector();

		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECTALL);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				HolidayCode HolidayCode = new HolidayCode();

				HolidayCode.setCurrency(rs.getString(1));
				HolidayCode.setCountry(rs.getString(2));
				HolidayCode.setHdate(rs.getString(3));
				HolidayCode.setFweekday(rs.getInt(4));
				HolidayCode.setSweekdday(rs.getInt(5));

				HolidayCode.setHolidayCode(rs.getString(6));
				Holidays.add(HolidayCode);

			}
		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", SELECTALL, e);
			return Holidays;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("HolidaySQL", SELECTALL, e);
			}
		}
		return Holidays;
	}

	protected static Collection selectHoliday(int HolidayId, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector Holidays = new Vector();

		try {
			con.setAutoCommit(false);
			SELECTONE = SELECTONE + HolidayId;
			stmt = dsSQL.newPreparedStatement(con, SELECTONE);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				HolidayCode HolidayCode = new HolidayCode();

				HolidayCode.setCurrency(rs.getString(1));
				HolidayCode.setCountry(rs.getString(2));
				HolidayCode.setHdate(rs.getString(3));
				HolidayCode.setFweekday(rs.getInt(4));
				HolidayCode.setSweekdday(rs.getInt(5));

				HolidayCode.setHolidayCode(rs.getString(6));
				Holidays.add(HolidayCode);

			}
		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", "selectHoliday", e);
			return Holidays;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("HolidaySQL", "selectMax", e);
			}
		}
		return Holidays;
	}

	public static int getHolidaysOnCp(String cp, String date, Connection con) {
		String sql = getHolidaysOnCurrencyPair(cp, date);
		int h = 0;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				h = rs.getInt("Holidays");

		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", sql, e);
			return h;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("HolidaySQL", SELECT_MAX, e);
			}
		}
		return h;

	}

	public static int checkHolidayOrWeekend(String currency, String date,
			Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Vector Holidays = new Vector();
		HolidayCode HolidayCode = new HolidayCode();
		String sql = "";
		try {
			con.setAutoCommit(false);
			sql = SELECTONE
					+ new StringBuffer(" currency = '")
							.append(currency)
							.append("' ")
							.append("and")
							.append("(hdate like ")
							.append("'%"
									+ commonUTIL.stringDateTimeToDate(date,
											CommonConstants.SDF_DATE_FORMAT)
									+ "%' ")
							.append("or ")
							.append("to_char(to_date('"
									+ commonUTIL.stringDateTimeToDate(date,
											CommonConstants.SDF_DATE_FORMAT)
									+ "', 'dd/mm/yyyy'), 'd') = fweekday ")
							.append("or ")
							.append("to_char(to_date('"
									+ commonUTIL.stringDateTimeToDate(date,
											CommonConstants.SDF_DATE_FORMAT)
									+ "', 'dd/mm/yyyy'), 'd') = sweekday ")
							.append(")").toString();

			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();
			commonUTIL.display(" HolidaySQLcheckHolidayOrWeekend", sql);
			if (rs.next()) {

				return 1;

			} else {

				return 0;
			}

		} catch (Exception e) {
			commonUTIL.displayError("HolidaySQL", "select :: " + sql, e);
			return -2;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("HolidaySQL", "select" + sql, e);
			}
		}

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
		return null;
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

	@Override
	public Collection selectWhere(String where, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection selectALLData(Connection con) {
		// TODO Auto-generated method stub
		return null;
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
		String sql = " Select " + columnNames + TABLEFROM;
		PreparedStatement stmt = null;
		Vector<HolidayCode> Holidays = new Vector<HolidayCode>();
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				HolidayCode HolidayCode = new HolidayCode();

				HolidayCode.setHolidayCode(rs.getString(1));
				Holidays.add(HolidayCode);

			}
			commonUTIL.display("HolidayCodeSQL", "selectKeyColumns " +sql);
		} catch (Exception e) {
			commonUTIL.displayError("HolidayCodeSQL", "selectKeyColumns " +sql + e.getMessage(), e);
			return Holidays;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL
						.displayError("HolidayCodeSQL", "selectKeyColumns", e);
			}
		}
		return Holidays;

	}

}
