package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.BaseBean;
import beans.LeContacts;
import beans.LegalEntity;

public class LeContactsSQL extends BaseSQL {
	final static private String tableName = "lecontacts";

	final static private String DELETE_FROM_message = "DELETE FROM leContacts where LE_id =? and LE_ROLE =? and CONTACT_TYPE= ? ";

	final static private String INSERT_FROM_message = new StringBuffer(
			"INSERT into leContacts(")
			.append(" id,  LE_ID,  LE_ROLE,  CONTACT_TYPE, LE_FIRSTNAME, LE_LAST_NAME, ")
			.append(" CITY, ZIPCODE, STATE, COUNTRY ,MAILING_ADDRESS1, MAILING_ADDRESS2, EAMILID, ")
			.append(" PHONE, FAX, SWIFT )  ")
			.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)").toString();

	final static private String SELECT_MAX = "SELECT LECONTACTS_SEQ.NEXTVAL AS MAX_ID FROM dual ";

	final static private String SELECTALL = new StringBuffer("SELECT")
			.append(" id,  LE_ID,  LE_ROLE,  CONTACT_TYPE, LE_FIRSTNAME, LE_LAST_NAME, ")
			.append(" CITY, ZIPCODE, STATE, COUNTRY , MAILING_ADDRESS1, MAILING_ADDRESS2, EAMILID, ")
			.append(" PHONE, FAX, SWIFT  ")
			.append("FROM leContacts order by id").toString();

	final static private String SELECT = "SELECT POID FROM leContacts where id =  ?";

	static private String SELECTONE = new StringBuffer("SELECT")
			.append(" id,  LE_ID,  LE_ROLE,  CONTACT_TYPE, LE_FIRSTNAME, LE_LAST_NAME, ")
			.append("CITY, ZIPCODE, STATE, COUNTRY ,  MAILING_ADDRESS1, MAILING_ADDRESS2, EAMILID, ")
			.append(" PHONE, FAX, SWIFT  ")
			.append("FROM leContacts order by id").append("where id = ")
			.toString();

	final static private String SELECTONPO = new StringBuffer("SELECT")
			.append(" id,  LE_ID,  LE_ROLE,  CONTACT_TYPE, LE_FIRSTNAME, LE_LAST_NAME, ")
			.append(" CITY, ZIPCODE, STATE, COUNTRY , MAILING_ADDRESS1, MAILING_ADDRESS2, EAMILID, ")
			.append(" PHONE, FAX, SWIFT  ").append("FROM leContacts ")
			.append("where le_id = ").toString();

	final static private String SELECTWHERE = new StringBuffer("SELECT")
			.append(" id,  LE_ID,  LE_ROLE,  CONTACT_TYPE, LE_FIRSTNAME, LE_LAST_NAME, ")
			.append("CITY, ZIPCODE, STATE, COUNTRY , MAILING_ADDRESS1, MAILING_ADDRESS2, EAMILID, ")
			.append(" PHONE, FAX, SWIFT  ").append("FROM leContacts ")
			.append("where ").toString();

	final static private String SQLCOUNT = new StringBuffer(
			"SELECT count(*) countRows ").append(" FROM leContacts where  ")
			.toString();

	private static String getUpdateSQL(LeContacts leContacts) {

		String updateSQL = new StringBuffer("UPDATE ").append(tableName)
				.append(" set id = ").append(leContacts.getId()).append(",")
				.append(" LE_ID = ").append(leContacts.getLeId()).append(",")
				.append(" LE_ROLE = '").append(leContacts.getLeRole())
				.append("',").append(" CONTACT_TYPE = '")
				.append(leContacts.getContactType()).append("',")
				.append(" LE_FIRSTNAME = '")
				.append(leContacts.getLeFirstName()).append("',")
				.append(" LE_LAST_NAME ='").append(leContacts.getLeLastName())
				.append("',").append(" CITY ='").append(leContacts.getCity())
				.append("',").append(" ZIPCODE = '")
				.append(leContacts.getZipCode()).append("',")
				.append(" STATE = '").append(leContacts.getState())
				.append("',").append(" COUNTRY = '")
				.append(leContacts.getCountry()).append("',")
				.append(" MAILING_ADDRESS1 = '")
				.append(leContacts.getMailingAddress1()).append("',")
				.append(" MAILING_ADDRESS2 = '")
				.append(leContacts.getMailingAddress2()).append("',")
				.append(" EAMILID = '").append(leContacts.getEmailID())
				.append("',").append("  PHONE = '")
				.append(leContacts.getPhone()).append("',")
				.append("  SWIFT = '").append(leContacts.getSwift())
				.append("' WHERE LE_ID = ")
				.append(leContacts.getLeId()).toString();

		// System.out.println(updateSQL);

		return updateSQL;

	}

	public static BaseBean save(LeContacts insertMessage, Connection con) {
		try {
			return insert(insertMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "save", e);
			return null;
		}
	}

	public static boolean update(LeContacts updateMessage, Connection con) {
		try {
			return edit(updateMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "update", e);
			return false;
		}
	}

	public static boolean delete(LeContacts deleteMessage, Connection con) {
		try {
			return remove(deleteMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "update", e);
			return false;
		}
	}

	public static LeContacts selectMessage(int id, Connection con) {
		try {
			return select1(id, con);
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "select", e);
			return null;
		}
	}

	public static Collection selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "selectALL", e);
			return null;
		}
	}

	public static int selectMaxID(Connection con) {
		try {
			return selectMax(con);
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "selectMaxID", e);
			return 0;
		}
	}

	protected static boolean edit(LeContacts updateMessage, Connection con) {

		PreparedStatement stmt = null;
		String sql = "";
		boolean hasEdit = false;

		try {

			con.setAutoCommit(false);
			sql = getUpdateSQL(updateMessage);
			stmt = con.prepareStatement(sql);

			commonUTIL.display("LeContactsSql", sql);

			if (stmt.executeUpdate() > 0) {

				con.commit();
				hasEdit = true;

			}

		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql ", "edit " + sql, e);
			return false;

		} finally {
			try {
				con.setAutoCommit(true);
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LeContactsSql", "edit", e);
			}
		}
		return hasEdit;
	}

	protected static boolean remove(LeContacts deleteMessage, Connection con) {

		PreparedStatement stmt = null;
		boolean hasDeleted = false;
		try {
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_message);
			stmt.setInt(1, deleteMessage.getLeId());
			stmt.setString(2, deleteMessage.getLeRole());
			stmt.setString(3, deleteMessage.getContactType());

			if (stmt.executeUpdate() > 0) {

				con.commit();
				hasDeleted = true;

			}

		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "remove", e);
			return false;

		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LeContactsSql", "remove", e);
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
				j = rs.getInt("MAX_ID");

		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", SELECT_MAX, e);
			return j;

		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LeContactsSql", SELECT_MAX, e);
			}
		}
		return j;
	}

	protected static BaseBean insert(LeContacts inserMessage, Connection con) {

		PreparedStatement stmt = null;
		boolean isSave = false;
		int id = 0;
		try {

			commonUTIL.display("LeContactsSql", INSERT_FROM_message);

			con.setAutoCommit(false);
			id = selectMax(con);
			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_message);

			stmt.setInt(1,inserMessage.getId());
			stmt.setInt(2, inserMessage.getLeId());
			stmt.setString(3, inserMessage.getLeRole());
			stmt.setString(4, inserMessage.getContactType());
			stmt.setString(5, inserMessage.getLeFirstName());
			stmt.setString(6, inserMessage.getLeLastName());
			stmt.setString(7, inserMessage.getCity());
			stmt.setString(8, inserMessage.getZipCode());
			stmt.setString(9, inserMessage.getState());
			stmt.setString(10, inserMessage.getCountry());
			stmt.setString(11, inserMessage.getMailingAddress1());
			stmt.setString(12, inserMessage.getMailingAddress2());
			stmt.setString(13, inserMessage.getEmailID());
			stmt.setString(14, inserMessage.getPhone());
			stmt.setString(15, inserMessage.getFax());
			stmt.setString(16, inserMessage.getSwift());

			if (stmt.executeUpdate() > 0) {

				con.commit();
				isSave = true;

			}

		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", INSERT_FROM_message, e);
			return null;

		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {

				commonUTIL
						.displayError("LeContactsSql", INSERT_FROM_message, e);
			}
		}
		return null;
	}

	protected static LeContacts select1(int id, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Vector Messages = new Vector();
		LeContacts leContacts = new LeContacts();
		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTONE + id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				leContacts.setId(rs.getInt(1));
				leContacts.setLeId(rs.getInt(2));
				leContacts.setLeRole(rs.getString(3));
				leContacts.setContactType(rs.getString(4));
				leContacts.setLeFirstName(rs.getString(5));
				leContacts.setLeLastName(rs.getString(6));
				leContacts.setCity(rs.getString(7));
				leContacts.setZipCode(rs.getString(8));
				leContacts.setState(rs.getString(9));
				leContacts.setCountry(rs.getString(10));
				leContacts.setMailingAddress1(rs.getString(11));
				leContacts.setMailingAddress2(rs.getString(12));
				leContacts.setEmailID(rs.getString(13));
				leContacts.setPhone(rs.getString(14));
				leContacts.setFax(rs.getString(15));
				leContacts.setSwift(rs.getString(16));

				return leContacts;

			}
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "select", e);
			return leContacts;

		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LeContactsSql", "selectMax", e);
			}
		}
		return leContacts;
	}

	protected static Collection select(Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector leContactsVec = new Vector();

		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTALL);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				LeContacts leContacts = new LeContacts();

				leContacts.setId(rs.getInt(1));
				leContacts.setLeId(rs.getInt(2));
				leContacts.setLeRole(rs.getString(3));
				leContacts.setContactType(rs.getString(4));
				leContacts.setLeFirstName(rs.getString(5));
				leContacts.setLeLastName(rs.getString(6));
				leContacts.setCity(rs.getString(7));
				leContacts.setZipCode(rs.getString(8));
				leContacts.setState(rs.getString(9));
				leContacts.setCountry(rs.getString(10));
				leContacts.setMailingAddress1(rs.getString(11));
				leContacts.setMailingAddress2(rs.getString(12));
				leContacts.setEmailID(rs.getString(13));
				leContacts.setPhone(rs.getString(14));
				leContacts.setFax(rs.getString(15));
				leContacts.setSwift(rs.getString(16));
				leContactsVec.add(leContacts);

			}
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", SELECTALL, e);
			return leContactsVec;

		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LeContactsSql", SELECTALL, e);
			}
		}
		return leContactsVec;
	}

	protected static Collection selectmessage(int tradeid, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector leContactsVec = new Vector();

		try {
			SELECTONE = SELECTONE + tradeid;
			stmt = dsSQL.newPreparedStatement(con, SELECTONE);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				LeContacts leContacts = new LeContacts();

				leContacts.setId(rs.getInt(1));
				leContacts.setLeId(rs.getInt(2));
				leContacts.setLeRole(rs.getString(3));
				leContacts.setContactType(rs.getString(4));
				leContacts.setLeFirstName(rs.getString(5));
				leContacts.setLeLastName(rs.getString(6));
				leContacts.setCity(rs.getString(7));
				leContacts.setZipCode(rs.getString(8));
				leContacts.setState(rs.getString(9));
				leContacts.setCountry(rs.getString(10));
				leContacts.setMailingAddress1(rs.getString(11));
				leContacts.setMailingAddress2(rs.getString(12));
				leContacts.setEmailID(rs.getString(13));
				leContacts.setPhone(rs.getString(14));
				leContacts.setFax(rs.getString(15));
				leContacts.setSwift(rs.getString(16));
				leContactsVec.add(leContacts);

			}
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "selectmessage", e);
			return leContactsVec;

		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LeContactsSql", "selectMax", e);
			}
		}
		return leContactsVec;
	}

	public static Collection selectContactsOnLe(int leid, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		String sql = SELECTONPO + leid + " order by id";
		Vector leContactsVec = new Vector();

		try {
			con.setAutoCommit(true);

			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				LeContacts leContacts = new LeContacts();

				leContacts.setId(rs.getInt(1));
				leContacts.setLeId(rs.getInt(2));
				leContacts.setLeRole(rs.getString(3));
				leContacts.setContactType(rs.getString(4));
				leContacts.setLeFirstName(rs.getString(5));
				leContacts.setLeLastName(rs.getString(6));
				leContacts.setCity(rs.getString(7));
				leContacts.setZipCode(rs.getString(8));
				leContacts.setState(rs.getString(9));
				leContacts.setCountry(rs.getString(10));
				leContacts.setMailingAddress1(rs.getString(11));
				leContacts.setMailingAddress2(rs.getString(12));
				leContacts.setEmailID(rs.getString(13));
				leContacts.setPhone(rs.getString(14));
				leContacts.setFax(rs.getString(15));
				leContacts.setSwift(rs.getString(16));
				leContactsVec.add(leContacts);

			}
			commonUTIL.display("LeContactsSql", sql);
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "selectContactsOnLe "
					+ sql, e);
			return leContactsVec;

		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LeContactsSql",
						"selectmessageOnProduct", e);
			}
		}
		return leContactsVec;
	}

	public static Collection selectLEContactOnWhereClause(String sqlw,
			Connection con) {
		int j = 0;

		PreparedStatement stmt = null;

		Vector leContactsVec = new Vector();

		String sql = SELECTWHERE + sqlw + " order by id";

		try {

			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				LeContacts leContacts = new LeContacts();

				leContacts.setId(rs.getInt(1));
				leContacts.setLeId(rs.getInt(2));
				leContacts.setLeRole(rs.getString(3));
				leContacts.setContactType(rs.getString(4));
				leContacts.setLeFirstName(rs.getString(5));
				leContacts.setLeLastName(rs.getString(6));
				leContacts.setCity(rs.getString(7));
				leContacts.setZipCode(rs.getString(8));
				leContacts.setState(rs.getString(9));
				leContacts.setCountry(rs.getString(10));
				leContacts.setMailingAddress1(rs.getString(11));
				leContacts.setMailingAddress2(rs.getString(12));
				leContacts.setEmailID(rs.getString(13));
				leContacts.setPhone(rs.getString(14));
				leContacts.setFax(rs.getString(15));
				leContacts.setSwift(rs.getString(16));
				leContactsVec.add(leContacts);

			}

			commonUTIL.display("LeContactsSql", sql);
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "selectLEOnWhereClause "
					+ sql, e);
			return leContactsVec;

		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LeContactsSql",
						"selectLEOnWhereClause", e);
			}
		}
		return leContactsVec;
	}

	private String leid;

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
		return save((LeContacts) sql, con);
	}

	@Override
	public boolean updateSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return update((LeContacts) sql, con);
	}

	@Override
	public boolean deleteSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return delete((LeContacts) sql, con);
	}

	@Override
	public BaseBean select(int id, Connection con) {
		// TODO Auto-generated method stub
		return selectMessage(id, con);
	}

	@Override
	public BaseBean select(String name, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection selectWhere(String where, Connection con) {
		// TODO Auto-generated method stub
		return selectLEContactOnWhereClause(where, con);
	}

	@Override
	public Collection selectALLData(Connection con) {
		// TODO Auto-generated method stub
		return selectALL(con);
	}

	@Override
	public int count(String sql, Connection con) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		String sql1 = SQLCOUNT + " id = " + sql;
		int tem=0;
		
		try {
			con.setAutoCommit(true);

			stmt = dsSQL.newPreparedStatement(con, sql1);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()){
				LeContacts le = new LeContacts();
			tem=rs.getInt(1);
			
			}
			return tem;
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "selectLEOnWhereClause "
					+ sql1, e);

		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LeContactsSql",
						"selectLEOnWhereClause", e);
			}
		}
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
}
