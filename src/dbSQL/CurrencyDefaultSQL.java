package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import constants.BeanConstants;
import beans.AttributeContainer;
import beans.BaseBean;
import beans.CurrencyDefault;
import beans.CurrencyPair;
import beans.LegalEntity;
import util.commonUTIL;

public class CurrencyDefaultSQL extends BaseSQL {

	final static private String DELETE_FROM_CurrrencyD = "DELETE FROM CURRENCYDEFAULT where currency_code =? ";
	final static private String INSERT_FROM_currencyDefault = "INSERT into CURRENCYDEFAULT(CURRENCY_CODE,ROUNDING, ROUNDING_METHOD, ISO_CODE, COUNTRY, DEFAULT_HOLIDAYS, RATE_INDEX_CODE, "
			+ "DEFAULT_DAY_COUNT, GROUP_LIST, SPOT_DAYS, DEFAULT_TENOR, DESCRIPTION, TIME_ZONE, VERSION_NUM, EXTERNAL_REFERENCE, "
			+ "RATE_DECIMALS, WARNING_THRESHOLD,SETTLEMENT_CUTOFF_TIMEZONE, IS_PRECIOUS_METAL_B, "
			+ "NON_DELIVERABLE_B, CCIL,CLS,BDC,STATUS,SETTLEMENT_CUTOFF_TIME) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	final static private String SELECT_MAX = "SELECT MAX(currencyDefaultno) DESC_ID FROM CURRENCYDEFAULT ";
	final static private String SELECTALL = " SELECT CURRENCY_CODE, ROUNDING, ROUNDING_METHOD, ISO_CODE, COUNTRY, DEFAULT_HOLIDAYS, RATE_INDEX_CODE, "
			+ "DEFAULT_DAY_COUNT, GROUP_LIST, SPOT_DAYS, DEFAULT_TENOR, DESCRIPTION, TIME_ZONE, VERSION_NUM, EXTERNAL_REFERENCE, "
			+ "RATE_DECIMALS, WARNING_THRESHOLD,SETTLEMENT_CUTOFF_TIMEZONE, IS_PRECIOUS_METAL_B, "
			+ " NON_DELIVERABLE_B, CCIL,CLS,BDC,STATUS,SETTLEMENT_CUTOFF_TIME" + " FROM CURRENCYDEFAULT";
	final static private String SELECTWHERE = " SELECT CURRENCY_CODE, ROUNDING, ROUNDING_METHOD, ISO_CODE, COUNTRY, DEFAULT_HOLIDAYS, RATE_INDEX_CODE, "
			+ "DEFAULT_DAY_COUNT, GROUP_LIST, spot_days, DEFAULT_TENOR, DESCRIPTION, TIME_ZONE, VERSION_NUM, EXTERNAL_REFERENCE, "
			+ "RATE_DECIMALS, WARNING_THRESHOLD,SETTLEMENT_CUTOFF_TIMEZONE, IS_PRECIOUS_METAL_B, "
			+ " NON_DELIVERABLE_B,  DAYCOUNT, CURRENCYDECIMAL" + "FROM CURRENCYDEFAULT where currency_code =  ?";
	/*
	 * static private String SELECTONE =
	 * " SELECT currency_code, rounding, rounding_method, iso_code, country, default_holidays, RATE_INDEX_CODE, "
	 * +
	 * "DEFAULT_DAY_COUNT, GROUP_LIST, spot_days, DEFAULT_TENOR, DESCRIPTION, TIME_ZONE, VERSION_NUM, EXTERNAL_REFERENCE, "
	 * +
	 * "RATE_DECIMALS, WARNING_THRESHOLD,SETTLEMENT_CUTOFF_TIME, SETTLEMENT_CUTOFF_TIMEZONE, is_precious_metal_b, "
	 * + " NON_DELIVERABLE_B,  DAYCOUNT, CURRENCYDECIMAL" +
	 * "FROM CURRENCYDEFAULT where CURRENCY_CODE =  ?";
	 */
	final static private String SQLCOUNT = new StringBuffer("SELECT count(*) countRows ")
			.append(" FROM CURRENCYDEFAULT where  ").toString();

	private static String getUpdateCurrencyDefaultSQL(CurrencyDefault updateCurrencyDefault) {

		String updateSQL = new StringBuffer("UPDATE CURRENCYDEFAULT set ")

				.append("ROUNDING =").append(updateCurrencyDefault.getRounding()).append(",ROUNDING_METHOD ='")
				.append(updateCurrencyDefault.getRounding_method()).append("',ISO_CODE='")
				.append(updateCurrencyDefault.getIso_code()).append("',COUNTRY ='")
				.append(updateCurrencyDefault.getCountry()).append("',DEFAULT_HOLIDAYS='")
				.append(updateCurrencyDefault.getDefault_holiday()).append("',RATE_INDEX_CODE ='")
				.append(updateCurrencyDefault.getRate_index_code()).append("',DEFAULT_DAY_COUNT ='")
				.append(updateCurrencyDefault.getDefault_day_count()).append("',GROUP_LIST ='")
				.append(updateCurrencyDefault.getGroupList()).append("',SPOT_DAYS=")
				.append(updateCurrencyDefault.getSpot_days()).append(",DEFAULT_TENOR=")
				.append(updateCurrencyDefault.getDefaultTenor()).append(",DESCRIPTION ='")
				.append(updateCurrencyDefault.getDescription()).append("',TIME_ZONE ='")
				.append(updateCurrencyDefault.getTimeZone()).append("',VERSION_NUM=")
				.append(updateCurrencyDefault.getVersionNumber()).append(",EXTERNAL_REFERENCE='")
				.append(updateCurrencyDefault.getExternalReferences()).append("',RATE_DECIMALS=")
				.append(updateCurrencyDefault.getRateDecimals()).append(",WARNING_THRESHOLD=")
				.append(updateCurrencyDefault.getWarningThreshold()).append(",SETTLEMENT_CUTOFF_TIMEZONE='")
				.append(updateCurrencyDefault.getSettlementCutOffTimeZone()).append("',IS_PRECIOUS_METAL_B=")
				.append(updateCurrencyDefault.getIs_precious_metal_b()).append(",NON_DELIVERABLE_B =")
				.append(updateCurrencyDefault.getNon_deliverable_b()).append(",CCIL='")
				.append(updateCurrencyDefault.getCCIL()).append("',CLS='").append(updateCurrencyDefault.getCLS())
				.append("',BDC='").append(updateCurrencyDefault.getBDC()).append("',STATUS='")
				.append(updateCurrencyDefault.getSTATUS()).append("',SETTLEMENT_CUTOFF_TIME=")
				.append(updateCurrencyDefault.getSettlementCutOffTime()).append("where CURRENCY_CODE='")
				.append(updateCurrencyDefault.getCurrency_code() + "'").toString();

		return updateSQL;
	}

	public static boolean save(CurrencyDefault insertcurrencyDefault, Connection con) {
		try {
			return insert(insertcurrencyDefault, con);
		} catch (Exception e) {
			commonUTIL.displayError("CurrencyDefaultSQL: insert: ", "save", e);
			return false;
		}
	}

	public static boolean update(CurrencyDefault updatecurrencyDefault, Connection con) {
		try {
			return edit(updatecurrencyDefault, con);
		} catch (Exception e) {
			commonUTIL.displayError("CurrencyDefaultSQL: insert: ", "update", e);
			return false;
		}
	}

	public static boolean delete(CurrencyDefault deletecurrencyDefault, Connection con) {
		try {
			return remove(deletecurrencyDefault, con);
		} catch (Exception e) {
			commonUTIL.displayError("CurrencyDefaultSQL: insert: ", "update", e);
			return false;
		}
	}

	/*
	 * public static CurrencyDefault selectcurrencyDefault(String currency_code,
	 * Connection con) { try { return select(currency_code, con);
	 * }catch(Exception e) { commonUTIL.displayError(
	 * "CurrencyDefaultSQL: insert: ","select",e); return null; } }
	 */
	public static Collection<CurrencyDefault> selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("CurrencyDefaultSQL: insert: ", "selectALL", e);
			return null;
		}
	}

	public static int selectMaxID(Connection con) {
		try {
			return selectMax(con);
		} catch (Exception e) {
			commonUTIL.displayError("CurrencyDefaultSQL: insert: ", "selectMaxID", e);
			return 0;
		}
	}

	protected static boolean edit(CurrencyDefault updatecurrencyDefault, Connection con) {

		PreparedStatement stmt = null;
		String sql = "";

		try {
			sql = getUpdateCurrencyDefaultSQL(updatecurrencyDefault);
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, sql);
			stmt.executeUpdate(sql);
			con.commit();

			commonUTIL.display("CurrencyDefaultSQL: edit:", sql);

		} catch (Exception e) {
			commonUTIL.displayError("CurrencyDefaultSQL: insert: " + e.getMessage(), sql, e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("CurrencyDefaultSQL: insert: ", sql, e);
			}
		}
		return true;
	}

	protected static boolean remove(CurrencyDefault deletecurrencyDefault, Connection con) {

		PreparedStatement stmt = null;
		try {

			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_CurrrencyD);
			stmt.setString(1, deletecurrencyDefault.getCurrency_code());

			stmt.executeUpdate();
			con.commit();

			commonUTIL.display("CurrencyDefaultSQL: remove:", DELETE_FROM_CurrrencyD);

		} catch (Exception e) {
			commonUTIL.displayError("CurrencyDefaultSQL: remove: ", DELETE_FROM_CurrrencyD, e);
			return false;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("CurrencyDefaultSQL: insert: ", DELETE_FROM_CurrrencyD, e);
			}
		}
		return true;
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
			commonUTIL.displayError("CurrencyDefaultSQL: insert: ", SELECT_MAX, e);
			return j;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {

				commonUTIL.displayError("CurrencyDefaultSQL: insert: ", SELECT_MAX, e);
			}
		}
		return j;
	}

	protected static boolean insert(CurrencyDefault insercurrencyDefault, Connection con) {
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			int j = 1;

			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_currencyDefault);

			stmt.setString(j++, insercurrencyDefault.getCurrency_code());
			stmt.setDouble(j++, insercurrencyDefault.getRounding());
			stmt.setString(j++, insercurrencyDefault.getRounding_method());
			stmt.setString(j++, insercurrencyDefault.getIso_code());
			stmt.setString(j++, insercurrencyDefault.getCountry());
			stmt.setString(j++, insercurrencyDefault.getDefault_holiday());
			stmt.setString(j++, insercurrencyDefault.getRate_index_code());
			stmt.setString(j++, insercurrencyDefault.getDefault_day_count());
			stmt.setString(j++, insercurrencyDefault.getGroupList());
			stmt.setInt(j++, insercurrencyDefault.getSpot_days());
			stmt.setInt(j++, insercurrencyDefault.getDefaultTenor());
			stmt.setString(j++, insercurrencyDefault.getDescription());
			stmt.setString(j++, insercurrencyDefault.getTimeZone());
			stmt.setInt(j++, insercurrencyDefault.getVersionNumber());
			stmt.setString(j++, insercurrencyDefault.getExternalReferences());
			stmt.setInt(j++, insercurrencyDefault.getRateDecimals());
			stmt.setDouble(j++, insercurrencyDefault.getWarningThreshold());
			stmt.setString(j++, insercurrencyDefault.getSettlementCutOffTimeZone());
			stmt.setInt(j++, insercurrencyDefault.getIs_precious_metal_b());
			stmt.setInt(j++, insercurrencyDefault.getNon_deliverable_b());
			stmt.setString(j++, insercurrencyDefault.getCCIL());
			stmt.setString(j++, insercurrencyDefault.getCLS());
			stmt.setString(j++, insercurrencyDefault.getBDC());
			stmt.setString(j++, insercurrencyDefault.getSTATUS());
			stmt.setString(j++, insercurrencyDefault.getSettlementCutOffTime());

			stmt.executeUpdate();
			con.commit();
			// insertAttributes(inserLegalEntity.getAttributeContainer(),id,BeanConstants.LegalEntity);
			commonUTIL.display("CurrencyDefaultSQL: insert: ", INSERT_FROM_currencyDefault);

		} catch (Exception e) {
			commonUTIL.displayError("CurrencyDefaultSQL: insert: ", INSERT_FROM_currencyDefault, e);
			return false;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("CurrencyDefaultSQL: insert: ", INSERT_FROM_currencyDefault, e);
			}
		}
		return true;
	}

	protected static CurrencyDefault saveNew(CurrencyDefault sql, Connection con) {

		PreparedStatement stmt = null;
		// int id = 0;
		try {
			con.setAutoCommit(false);
			int j = 1;

			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_currencyDefault);

			stmt.setString(j++, sql.getCurrency_code());
			stmt.setDouble(j++, sql.getRounding());
			stmt.setString(j++, sql.getRounding_method());
			stmt.setString(j++, sql.getIso_code());
			stmt.setString(j++, sql.getCountry());
			stmt.setString(j++, sql.getDefault_holiday());
			stmt.setString(j++, sql.getRate_index_code());
			stmt.setString(j++, sql.getDefault_day_count());
			stmt.setString(j++, sql.getGroupList());
			stmt.setInt(j++, sql.getSpot_days());
			stmt.setInt(j++, sql.getDefaultTenor());
			stmt.setString(j++, sql.getDescription());
			stmt.setString(j++, sql.getTimeZone());
			stmt.setInt(j++, sql.getVersionNumber());
			stmt.setString(j++, sql.getExternalReferences());
			stmt.setInt(j++, sql.getRateDecimals());
			stmt.setDouble(j++, sql.getWarningThreshold());
			stmt.setString(j++, sql.getSettlementCutOffTimeZone());
			stmt.setInt(j++, sql.getIs_precious_metal_b());
			stmt.setInt(j++, sql.getNon_deliverable_b());
			stmt.setString(j++, sql.getCCIL());
			stmt.setString(j++, sql.getCLS());
			stmt.setString(j++, sql.getBDC());
			stmt.setString(j++, sql.getSTATUS());
			stmt.setString(j++, sql.getSettlementCutOffTime());

			stmt.executeUpdate();
			con.commit();
			commonUTIL.display("CurrencyDefaultSQL", "insert" + INSERT_FROM_currencyDefault);
			// inserCurrencyDefault.setId(id);
			// insertAttributes(inserCurrencyDefault.getAttributeContainer(),id,BeanConstants.LegalEntity);

			return sql;
		} catch (Exception e) {
			commonUTIL.displayError("CurrencyDefaultSQL", "insert", e);
			return null;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("CurrencyDefaultSQL", "insert", e);
			}
		}
	}
	/*
	 * protected static CurrencyDefault select(String currency_code,Connection
	 * con ) {
	 * 
	 * PreparedStatement stmt = null; CurrencyDefault CurrencyDefault = new
	 * CurrencyDefault();
	 * 
	 * String sql = SELECTONE + "'"+ currency_code +"'"; try { stmt =
	 * dsSQL.newPreparedStatement(con, sql);
	 * 
	 * ResultSet rs = stmt.executeQuery();
	 * 
	 * while(rs.next()) { CurrencyDefault.setCurrency_code(rs.getString(1));
	 * CurrencyDefault.setRounding(rs.getInt(2));
	 * CurrencyDefault.setIso_code(rs.getString(3));
	 * CurrencyDefault.setCountry(rs.getString(4));
	 * CurrencyDefault.setDefault_holiday(rs.getString(5));
	 * CurrencyDefault.setSpot_days(rs.getInt(6));
	 * CurrencyDefault.setIs_precious_metal_b(rs.getInt(7));
	 * CurrencyDefault.setNon_deliverable_b(rs.getInt(8)); }
	 * 
	 * return CurrencyDefault;
	 * 
	 * } catch (Exception e) { commonUTIL.displayError(
	 * "CurrencyDefaultSQL: select: ", sql,e); return CurrencyDefault; } finally
	 * { try { stmt.close(); } catch (SQLException e) { commonUTIL.displayError(
	 * "CurrencyDefaultSQL: select: ", sql,e); } } }
	 */

	protected static Collection<CurrencyDefault> select(Connection con) {
		PreparedStatement stmt = null;
		Vector<CurrencyDefault> cdv = new Vector<CurrencyDefault>();

		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTALL);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				CurrencyDefault cd = new CurrencyDefault();
				cd.setCurrency_code(rs.getString(1));
				cd.setRounding(rs.getInt(2));
				cd.setRounding_method(rs.getString(3));
				cd.setIso_code(rs.getString(4));
				cd.setCountry(rs.getString(5));
				cd.setDefault_holiday(rs.getString(6));
				cd.setRate_index_code(rs.getString(7));
				cd.setDefault_day_count(rs.getString(8));
				cd.setGroupList(rs.getString(9));
				cd.setSpot_days(rs.getInt(10));
				cd.setDefaultTenor(rs.getInt(11));
				cd.setDescription(rs.getString(12));
				cd.setTimeZone(rs.getString(13));
				cd.setVersionNumber(rs.getInt(14));
				cd.setExternalReferences(rs.getString(15));
				cd.setRateDecimals(rs.getInt(16));
				cd.setWarningThreshold(rs.getDouble(17));
				cd.setSettlementCutOffTimeZone(rs.getString(18));
				cd.setIs_precious_metal_b(rs.getInt(19));
				cd.setNon_deliverable_b(rs.getInt(20));
				cd.setCCIL(rs.getString(21));
				cd.setCLS(rs.getString(22));
				cd.setBDC(rs.getString(23));
				cd.setSTATUS(rs.getString(24));
				cd.setSettlementCutOffTime(rs.getString(25));
				cdv.add(cd);

			}

			commonUTIL.display("CurrencyDefaultSQL: select: ", SELECTALL);

		} catch (Exception e) {
			commonUTIL.displayError("CurrencyDefaultSQL: select: ", SELECTALL, e);
			return cdv;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("CurrencyDefaultSQL: select: ", SELECTALL, e);
			}
		}
		return cdv;
	}

	/*
	 * protected static Collection<CurrencyDefault> selectcurrencyDefault(int
	 * currencyDefaultId, Connection con ) {
	 * 
	 * PreparedStatement stmt = null; String sql =""; Vector<CurrencyDefault>
	 * currencyDefaults = new Vector<CurrencyDefault>();
	 * 
	 * try { sql = SELECTONE + "'" + currencyDefaultId +"'"; stmt =
	 * dsSQL.newPreparedStatement(con, sql );
	 * 
	 * ResultSet rs = stmt.executeQuery();
	 * 
	 * while(rs.next()) { CurrencyDefault CurrencyDefault = new
	 * CurrencyDefault(); CurrencyDefault.setCurrency_code(rs.getString(1));
	 * CurrencyDefault.setRounding(rs.getInt(2));
	 * CurrencyDefault.setRounding_method(rs.getString(3));
	 * CurrencyDefault.setIso_code(rs.getString(4));
	 * CurrencyDefault.setCountry(rs.getString(5));
	 * CurrencyDefault.setDefault_holiday(rs.getString(6));
	 * CurrencyDefault.setRate_index_code(rs.getString(7));
	 * CurrencyDefault.setDefault_day_count(rs.getString(8));
	 * CurrencyDefault.setGroupList(rs.getString(9));
	 * CurrencyDefault.setSpot_days(rs.getInt(10));
	 * CurrencyDefault.setDefaultTenor(rs.getInt(11));
	 * CurrencyDefault.setDescription(rs.getString(12));
	 * CurrencyDefault.setTimeZone(rs.getString(13));
	 * CurrencyDefault.setVersionNumber(rs.getInt(14));
	 * CurrencyDefault.setExternalReferences(rs.getString(15));
	 * CurrencyDefault.setRateDecimals(rs.getInt(16));
	 * CurrencyDefault.setWarningThreshold(rs.getDouble(17));
	 * CurrencyDefault.setSettlementCutOffTime(rs.getString(18));
	 * CurrencyDefault.setSettlementCutOffTimeZone(rs.getString(19));
	 * CurrencyDefault.setIs_precious_metal_b(rs.getInt(20));
	 * CurrencyDefault.setNon_deliverable_b(rs.getInt(21));
	 * CurrencyDefault.setDayCount(rs.getString(22));
	 * CurrencyDefault.setCurrencyDecimal(rs.getInt(23));
	 * currencyDefaults.add(CurrencyDefault); }
	 * 
	 * commonUTIL.display("CurrencyDefaultSQL: select: ",sql);
	 * 
	 * } catch (Exception e) { commonUTIL.displayError(
	 * "CurrencyDefaultSQL: insert: ",sql,e); return currencyDefaults; } finally
	 * { try { stmt.close(); } catch (SQLException e) { commonUTIL.displayError(
	 * "CurrencyDefaultSQL: insert: ", sql ,e); } } return currencyDefaults; }
	 */
	public static Collection selectLEOnWhereClause(String sql, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector CurrencyDefault = new Vector();
		String sql1 = SELECTWHERE + "CURRENCY_CODE = '" + sql + "'";
		try {

			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, sql1);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				CurrencyDefault cd = new CurrencyDefault();
				cd.setCurrency_code(rs.getString(1));
				cd.setRounding(rs.getInt(2));
				cd.setRounding_method(rs.getString(3));
				cd.setIso_code(rs.getString(4));
				cd.setCountry(rs.getString(5));
				cd.setDefault_holiday(rs.getString(6));
				cd.setRate_index_code(rs.getString(7));
				cd.setDefault_day_count(rs.getString(8));
				cd.setGroupList(rs.getString(9));
				cd.setSpot_days(rs.getInt(10));
				cd.setDefaultTenor(rs.getInt(11));
				cd.setDescription(rs.getString(12));
				cd.setTimeZone(rs.getString(13));
				cd.setVersionNumber(rs.getInt(14));
				cd.setExternalReferences(rs.getString(15));
				cd.setRateDecimals(rs.getInt(16));
				cd.setWarningThreshold(rs.getDouble(17));

				cd.setSettlementCutOffTimeZone(rs.getString(19));
				cd.setIs_precious_metal_b(rs.getInt(20));
				cd.setNon_deliverable_b(rs.getInt(21));
				cd.setCCIL(rs.getString(22));
				cd.setCLS(rs.getString(22));
				cd.setBDC(rs.getString(22));
				cd.setSTATUS(rs.getString(22));
				cd.setSettlementCutOffTime(rs.getString(18));
				CurrencyDefault.add(cd);

			}
			commonUTIL.display("CurrencyPairSQL", sql1);
		} catch (Exception e) {
			commonUTIL.displayError("CurrencyPairSQL", sql1, e);
			return CurrencyDefault;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL", sql1, e);
			}
		}
		return CurrencyDefault;
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
		return (BaseBean) saveNew((CurrencyDefault) sql, con);
	}

	@Override
	public boolean updateSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return update((CurrencyDefault) sql, con);
	}

	@Override
	public boolean deleteSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return delete((CurrencyDefault) sql, con);
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
		return selectLEOnWhereClause(where, con);
	}

	@Override
	public Collection selectALLData(Connection con) {
		// TODO Auto-generated method stub
		return select(con);
	}

	@Override
	public int count(String sql, Connection con) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		String sql1 = SQLCOUNT + "CURRENCY_CODE = '" + sql + "'";
		int tem = 0;

		try {
			con.setAutoCommit(true);

			stmt = dsSQL.newPreparedStatement(con, sql1);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				CurrencyDefault le = new CurrencyDefault();
				tem = rs.getInt(1);

			}
			return tem;
		} catch (Exception e) {
			commonUTIL.displayError("CurrencyDefaultSQL", "selectLEOnWhereClause " + sql1, e);

		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("CurrencyDefaultSQL", "selectLEOnWhereClause", e);
			}
		}
		// TODO Auto-generated method stub
		return 0;
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

	/*
	 * public static void main(String args[]) { CurrencyDefault boo = new
	 * CurrencyDefault();
	 * 
	 * AttributeContainer att = new AttributeContainer();
	 * att.addAttribute("testing","pppp"); att.addAttribute("testing1","sdfg");
	 * boo.setAttributeContainer(att); insert(boo, dsSQL.getConn()); }
	 */

}
