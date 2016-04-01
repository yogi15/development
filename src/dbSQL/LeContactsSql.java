package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.LeContacts;
import beans.LegalEntity;

public class LeContactsSql {
	final static private String tableName = "lecontacts";
	
	final static private String DELETE_FROM_message = "DELETE FROM leContacts where LE_id =? and LE_ROLE =? and CONTACT_CATEGORY= ? ";
	
	final static private String INSERT_FROM_message = new StringBuffer("INSERT into lecontacts(")
			.append(" id,  LE_ID,  LE_ROLE, PO_ID, CONTACT_CATEGORY, LE_FIRSTNAME, LE_LAST_NAME, ")
			.append(" LE_TITLE, CITY, ZIPCODE, STATE, COUNTRY, MAILING_ADDRESS, EAMIL_ADDRESS, ")
			.append(" PHONE, FAX, TELEX, SWIFT, COMMENTS, EFFECTIVE_FROM, EFFECTIVE_TO, EXTERNAL_REF,producttype ) ") 
			.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
			.toString();
	
	final static private String SELECT_MAX = "SELECT LECONTACTS_SEQ.NEXTVAL AS MAX_ID FROM dual ";
	
	final static private String SELECTALL =  new StringBuffer("SELECT")
	.append(" id,  LE_ID,  LE_ROLE, PO_ID, CONTACT_CATEGORY, LE_FIRSTNAME, LE_LAST_NAME, ")
	.append(" LE_TITLE, CITY, ZIPCODE, STATE, COUNTRY, MAILING_ADDRESS, EAMIL_ADDRESS, ")
	.append(" PHONE, FAX, TELEX, SWIFT, COMMENTS, EFFECTIVE_FROM, EFFECTIVE_TO, EXTERNAL_REF,producttype  ") 
	.append("FROM leContacts order by id")
	.toString();
	
	final static private String SELECT = "SELECT POID FROM leContacts where id =  ?";
	
	static private String SELECTONE = new StringBuffer("SELECT")
	.append(" id, LE_ID,  LE_ROLE, PO_ID, CONTACT_CATEGORY, LE_FIRSTNAME, LE_LAST_NAME, ")
	.append(" LE_TITLE, CITY, ZIPCODE, STATE, COUNTRY, MAILING_ADDRESS, EAMIL_ADDRESS, ")
	.append(" PHONE, FAX, TELEX, SWIFT, COMMENTS, EFFECTIVE_FROM, EFFECTIVE_TO, EXTERNAL_REF,producttype  ") 
	.append("FROM leContacts order by id")
	.append("where id = ")
	.toString();
	
	final static private String SELECTONPO = new StringBuffer("SELECT")
	.append(" id,  LE_ID,  LE_ROLE, PO_ID, CONTACT_CATEGORY, LE_FIRSTNAME, LE_LAST_NAME, ")
	.append(" LE_TITLE, CITY, ZIPCODE, STATE, COUNTRY, MAILING_ADDRESS, EAMIL_ADDRESS, ")
	.append(" PHONE, FAX, TELEX, SWIFT, COMMENTS, EFFECTIVE_FROM, EFFECTIVE_TO, EXTERNAL_REF,producttype  ") 
	.append("FROM leContacts ")
	.append("where le_id = ")
	.toString();
	
	final static private String SELECTWHERE = new StringBuffer("SELECT")
	.append(" id,  LE_ID,  LE_ROLE, PO_ID, CONTACT_CATEGORY, LE_FIRSTNAME, LE_LAST_NAME, ")
	.append(" LE_TITLE, CITY, ZIPCODE, STATE, COUNTRY, MAILING_ADDRESS, EAMIL_ADDRESS, ")
	.append(" PHONE, FAX, TELEX, SWIFT, COMMENTS, EFFECTIVE_FROM, EFFECTIVE_TO, EXTERNAL_REF,producttype  ") 
	.append("FROM leContacts ")
	.append("where ")
	.toString();

	
	private static String getUpdateSQL(LeContacts leContacts) {

		String updateSQL = new StringBuffer("UPDATE ").append(tableName)
				.append(" set id = ").append(leContacts.getId()).append(",")
				.append(" LE_ID = ").append(leContacts.getLeId()).append(",")
				.append(" LE_ROLE = '").append(leContacts.getLeRole()).append("',")
				.append(" PO_ID = ").append(leContacts.getPoId()).append(",")
				.append(" CONTACT_CATEGORY = '").append(leContacts.getContactCategory()).append("',")
				.append(" LE_FIRSTNAME = '").append(leContacts.getLeFirstName()).append("',")
				.append(" LE_LAST_NAME ='").append(leContacts.getLeLastName()).append("',")
				.append(" LE_TITLE ='").append(leContacts.getLeTitle()).append("',")
				.append(" CITY ='").append(leContacts.getCity()).append("',")
				.append(" ZIPCODE = '").append(leContacts.getZipCode()).append("',")
				.append(" STATE = '").append(leContacts.getState()).append("',")
				.append(" COUNTRY = '").append(leContacts.getCountry()).append("',")
				.append(" MAILING_ADDRESS = '").append(leContacts.getMailingAddress()).append("',")
				.append(" EAMIL_ADDRESS = '").append(leContacts.getEmailAddresss()).append("',")
				.append("  PHONE = '").append(leContacts.getPhone()).append("',")
				.append(" FAX = '").append(leContacts.getFax()).append("',")
				.append(" TELEX = '").append(leContacts.getTelex()).append("',")
				.append("  SWIFT = '").append(leContacts.getSwift()).append("',")
				.append(" COMMENTS = '").append(leContacts.getComments()).append("',")
				.append(" EFFECTIVE_FROM = '").append(leContacts.getEffectiveFrom()).append("',")
				.append(" EFFECTIVE_TO = '").append(leContacts.getEffectiveTo()).append("',")
				.append(" EXTERNAL_REF = '").append(leContacts.getExternalRef()).append("',")	
				.append(" producttype = '").append(leContacts.getProductType()).append("'")	
				.append(" WHERE LE_ID = ").append(leContacts.getLeId())
				.toString();

		//System.out.println(updateSQL);

		return updateSQL;

	}
	
	

	public static int save(LeContacts insertMessage, Connection con) {
		try {
			return insert(insertMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "save", e);
			return 0;
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

	public static LeContacts selectMessage(int MessageId, Connection con) {
		try {
			return select(MessageId, con);
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
			
			commonUTIL.display("LeContactsSql", "edit " + sql);

			if (stmt.executeUpdate() > 0) {

				con.commit();
				hasEdit =true;

			} 
			
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql ", "edit " + sql, e);
			return false;

		} finally {
			try {
				con.setAutoCommit(true);
				stmt.close();
			//	con.close();
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
			stmt.setString(3, deleteMessage.getContactCategory());

			if (stmt.executeUpdate() > 0) {

				con.commit();
				hasDeleted =true;

			} 

		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "remove", e);
			return false;

		} finally {
			try {
				stmt.close();
			//	con.close();
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
			//	con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LeContactsSql", SELECT_MAX, e);
			}
		}
		return j;
	}

	protected static int insert(LeContacts inserMessage, Connection con) {

		PreparedStatement stmt = null;
		boolean isSave = false;
		int id = 0;
		try {
			
			
			commonUTIL.display("LeContactsSql", INSERT_FROM_message);
			
			con.setAutoCommit(false);
			 id = selectMax(con);
			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_message);
			
			stmt.setInt(1, id);
			stmt.setInt(2, inserMessage.getLeId());
			stmt.setString(3, inserMessage.getLeRole());
			stmt.setInt(4, inserMessage.getPoId());
			stmt.setString(5, inserMessage.getContactCategory());
			stmt.setString(6, inserMessage.getLeFirstName());
			stmt.setString(7, inserMessage.getLeLastName());
			stmt.setString(8, inserMessage.getLeTitle());
			stmt.setString(9, inserMessage.getCity());
			stmt.setString(10, inserMessage.getZipCode());
			stmt.setString(11, inserMessage.getState());
			stmt.setString(12, inserMessage.getCountry());
			stmt.setString(13, inserMessage.getMailingAddress());
			stmt.setString(14, inserMessage.getEmailAddresss());
			stmt.setString(15, inserMessage.getPhone());
			stmt.setString(16, inserMessage.getFax());
			stmt.setString(17, inserMessage.getTelex());
			stmt.setString(18, inserMessage.getSwift());
			stmt.setString(19, inserMessage.getComments());
			stmt.setString(20, inserMessage.getEffectiveFrom());
			stmt.setString(21, inserMessage.getEffectiveTo());
			stmt.setString(22, inserMessage.getExternalRef());
			stmt.setString(23, inserMessage.getProductType());

			if (stmt.executeUpdate() > 0) {

				con.commit();
				isSave = true;

			}

		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", INSERT_FROM_message, e);
			return 0;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("LeContactsSql", INSERT_FROM_message, e);
			}
		}
		return id;
	}

	protected static LeContacts select(int messageIn, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Vector Messages = new Vector();
		LeContacts leContacts = new LeContacts();
		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTONE + messageIn);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				leContacts.setId(rs.getInt(1));
				leContacts.setLeId(rs.getInt(2));
				leContacts.setLeRole(rs.getString(3));
				leContacts.setPoId(rs.getInt(4));
				leContacts.setContactCategory(rs.getString(5));
				leContacts.setLeFirstName(rs.getString(6));
				leContacts.setLeLastName(rs.getString(7));
				leContacts.setLeTitle(rs.getString(8));
				leContacts.setCity(rs.getString(9));
				leContacts.setZipCode(rs.getString(10));
				leContacts.setState(rs.getString(11));
				leContacts.setCountry(rs.getString(12));
				leContacts.setMailingAddress(rs.getString(13));
				leContacts.setEmailAddresss(rs.getString(14));
				leContacts.setPhone(rs.getString(15));
				leContacts.setFax(rs.getString(16));
				leContacts.setTelex(rs.getString(17));
				leContacts.setSwift(rs.getString(18));
				leContacts.setComments(rs.getString(19));
				leContacts.setEffectiveFrom(rs.getString(20));
				leContacts.setEffectiveTo(rs.getString(21));
				leContacts.setExternalRef(rs.getString(22));
				leContacts.setProductType(rs.getString(23));
				

				return leContacts;

			}
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "select", e);
			return leContacts;

		} finally {
			try {
				stmt.close();
				//con.close();
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
				leContacts.setPoId(rs.getInt(4));
				leContacts.setContactCategory(rs.getString(5));
				leContacts.setLeFirstName(rs.getString(6));
				leContacts.setLeLastName(rs.getString(7));
				leContacts.setLeTitle(rs.getString(8));
				leContacts.setCity(rs.getString(9));
				leContacts.setZipCode(rs.getString(10));
				leContacts.setState(rs.getString(11));
				leContacts.setCountry(rs.getString(12));
				leContacts.setMailingAddress(rs.getString(13));
				leContacts.setEmailAddresss(rs.getString(14));
				leContacts.setPhone(rs.getString(15));
				leContacts.setFax(rs.getString(16));
				leContacts.setTelex(rs.getString(17));
				leContacts.setSwift(rs.getString(18));
				leContacts.setComments(rs.getString(19));
				leContacts.setEffectiveFrom(rs.getString(20));
				leContacts.setEffectiveTo(rs.getString(21));
				leContacts.setExternalRef(rs.getString(22));
				leContacts.setProductType(rs.getString(23));
				leContactsVec.add(leContacts);

			}
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", SELECTALL, e);
			return leContactsVec;

		} finally {
			try {
				stmt.close();
				//con.close();
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
				leContacts.setPoId(rs.getInt(4));
				leContacts.setContactCategory(rs.getString(5));
				leContacts.setLeFirstName(rs.getString(6));
				leContacts.setLeLastName(rs.getString(7));
				leContacts.setLeTitle(rs.getString(8));
				leContacts.setCity(rs.getString(9));
				leContacts.setZipCode(rs.getString(10));
				leContacts.setState(rs.getString(11));
				leContacts.setCountry(rs.getString(12));
				leContacts.setMailingAddress(rs.getString(13));
				leContacts.setEmailAddresss(rs.getString(14));
				leContacts.setPhone(rs.getString(15));
				leContacts.setFax(rs.getString(16));
				leContacts.setTelex(rs.getString(17));
				leContacts.setSwift(rs.getString(18));
				leContacts.setComments(rs.getString(19));
				leContacts.setEffectiveFrom(rs.getString(20));
				leContacts.setEffectiveTo(rs.getString(21));
				leContacts.setExternalRef(rs.getString(22));
				leContacts.setProductType(rs.getString(23));
				leContactsVec.add(leContacts);

			}
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "selectmessage", e);
			return leContactsVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LeContactsSql", "selectMax", e);
			}
		}
		return leContactsVec;
	}

	public static Collection selectContactsOnLe(int leid,
			Connection con) {
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
				leContacts.setPoId(rs.getInt(4));
				leContacts.setContactCategory(rs.getString(5));
				leContacts.setLeFirstName(rs.getString(6));
				leContacts.setLeLastName(rs.getString(7));
				leContacts.setLeTitle(rs.getString(8));
				leContacts.setCity(rs.getString(9));
				leContacts.setZipCode(rs.getString(10));
				leContacts.setState(rs.getString(11));
				leContacts.setCountry(rs.getString(12));
				leContacts.setMailingAddress(rs.getString(13));
				leContacts.setEmailAddresss(rs.getString(14));
				leContacts.setPhone(rs.getString(15));
				leContacts.setFax(rs.getString(16));
				leContacts.setTelex(rs.getString(17));
				leContacts.setSwift(rs.getString(18));
				leContacts.setComments(rs.getString(19));
				leContacts.setEffectiveFrom(rs.getString(20));
				leContacts.setEffectiveTo(rs.getString(21));
				leContacts.setExternalRef(rs.getString(22));
				leContacts.setProductType(rs.getString(23));
				leContactsVec.add(leContacts);

			}
			commonUTIL.display("LeContactsSql", sql);
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "selectContactsOnLe " + sql, e);
			return leContactsVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LeContactsSql", "selectmessageOnProduct",
						e);
			}
		}
		return leContactsVec;
	}
	
	public static Collection selectLEContactOnWhereClause(String sqlw, Connection con) {
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
				leContacts.setPoId(rs.getInt(4));
				leContacts.setContactCategory(rs.getString(5));
				leContacts.setLeFirstName(rs.getString(6));
				leContacts.setLeLastName(rs.getString(7));
				leContacts.setLeTitle(rs.getString(8));
				leContacts.setCity(rs.getString(9));
				leContacts.setZipCode(rs.getString(10));
				leContacts.setState(rs.getString(11));
				leContacts.setCountry(rs.getString(12));
				leContacts.setMailingAddress(rs.getString(13));
				leContacts.setEmailAddresss(rs.getString(14));
				leContacts.setPhone(rs.getString(15));
				leContacts.setFax(rs.getString(16));
				leContacts.setTelex(rs.getString(17));
				leContacts.setSwift(rs.getString(18));
				leContacts.setComments(rs.getString(19));
				leContacts.setEffectiveFrom(rs.getString(20));
				leContacts.setEffectiveTo(rs.getString(21));
				leContacts.setExternalRef(rs.getString(22));
				leContacts.setProductType(rs.getString(23));
				
				leContactsVec.add(leContacts);

			}
			
			commonUTIL.display("LeContactsSql", sql);
		} catch (Exception e) {
			commonUTIL.displayError("LeContactsSql", "selectLEOnWhereClause " + sql, e);
			return leContactsVec;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LeContactsSql", "selectLEOnWhereClause", e);
			}
		}
		return leContactsVec;
	}
}
