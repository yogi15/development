package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.MessageConfig;

public class MessageConfigSQL {
	final static private String tableName = "messageConfig";
	final static private String DELETE_FROM_message = "DELETE FROM messageConfig where id =? ";
	
	final static private String INSERT_FROM_message = "INSERT into messageConfig("
			+ " id,  poid,  eventType, productType, productsubtype, receiverrole, formatType, "
			+ " receiverID, templateName, pocontacttype, messageType,Gateway,addressType,receivercontact) " 
			+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	final static private String SELECT_MAX = "SELECT MESSAGECONFIG_SEQ.NEXTVAL  DESC_ID  FROM dual ";
	
	final static private String SELECTALL = "SELECT id,  poid,  eventType, productType, productsubtype, " +
			" receiverrole, formatType, receiverID, templateName, pocontacttype, messageType,Gateway,addressType,receivercontact " +
			" FROM messageConfig order by id";
	final static private String SELECT = "SELECT tradeid FROM messageConfig where id =  ?";
	static private String SELECTONE = "SELECT id,  poid,  eventType, productType, productsubtype, " +
	" receiverrole, formatType, receiverID, templateName, pocontacttype, messageType,Gateway,addressType,receivercontact " +
	" FROM messageConfig order by id";
	final static private String SELECTONPRODUCT = "SELECT id,  poid,  eventType, productType, productsubtype, " +
	" receiverrole, formatType, receiverID, templateName, pocontacttype, messageType,Gateway,addressType,receivercontact " +
	" FROM messageConfig order by id";
	private static final String SELECTWHERE = "SELECT id,  poid,  eventType, productType, productsubtype, " +
	" receiverrole, formatType, receiverID, templateName, pocontacttype, messageType,Gateway,addressType,receivercontact " +
	" FROM messageConfig  where ";

	private static String getUpdateSQL(MessageConfig messasgeconfig) {

		String updateSQL = new StringBuffer("UPDATE ").append(tableName)
				.append(" set id = ").append(messasgeconfig.getId()).append(",")
				.append(" poid = ").append(messasgeconfig.getPoid()).append(",")
				.append(" eventType = '").append(messasgeconfig.getEventType()).append("',")
				.append(" productType = '").append(messasgeconfig.getProductType()).append("',")
				.append(" productsubtype = '").append(messasgeconfig.getProductSubType()).append("',")
				.append(" receiverRole ='").append(messasgeconfig.getReceiverRole()).append("',")
				.append(" formatType ='").append(messasgeconfig.getFormatType()).append("',")
				.append(" receiverID = '").append(messasgeconfig.getReceiverID()).append("',")
				.append(" templateName = '").append(messasgeconfig.getTemplateName()).append("',")
				.append(" pocontacttype = '").append(messasgeconfig.getPoContactType()).append("',")
				.append(" messageType = '").append(messasgeconfig.getMessageType()).append("',")
				.append(" Gateway = '").append(messasgeconfig.getGateWay()).append("',")
				.append(" ADDRESSTYPE = '").append(messasgeconfig.getAddressType()).append("', ")
				.append(" receivercontact = '").append(messasgeconfig.getReceiverContactType()).append("'")
				.toString();

		return updateSQL;

	}

	public static int save(MessageConfig insertMessage, Connection con) {
		try {
			return insert(insertMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "save", e);
			return 0;
		}
	}

	public static boolean update(MessageConfig updateMessage, Connection con) {
		try {
			return edit(updateMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("MessageConfigSQL", "update", e);
			return false;
		}
	}

	public static boolean delete(MessageConfig deleteMessage, Connection con) {
		try {
			return remove(deleteMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("MessageConfigSQL", "update", e);
			return false;
		}
	}

	public static MessageConfig selectMessage(int MessageId, Connection con) {
		try {
			return select(MessageId, con);
		} catch (Exception e) {
			commonUTIL.displayError("MessageConfigSQL", "select", e);
			return null;
		}
	}

	public static Collection selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("MessageConfigSQL", "selectALL", e);
			return null;
		}
	}

	public static int selectMaxID(Connection con) {
		try {
			return selectMax(con);
		} catch (Exception e) {
			commonUTIL.displayError("MessageConfigSQL", "selectMaxID", e);
			return 0;
		}
	}

	protected static boolean edit(MessageConfig updateMessageConfig, Connection con) {

		PreparedStatement stmt = null;
		String sql = "";
		boolean hasEdit = false;
		
		try {

			con.setAutoCommit(false);
			sql = getUpdateSQL(updateMessageConfig);
			stmt = con.prepareStatement(sql);
			
			commonUTIL.display("MessageConfigSQL", "edit " + sql);

			if (stmt.executeUpdate() > 0) {

				con.commit();
				hasEdit =true;

			} 
			
		} catch (Exception e) {
			commonUTIL.displayError("MessageConfigSQL ", "edit " + sql, e);
			return false;

		} finally {
			try {
				con.setAutoCommit(true);
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("MessageConfigSQL", "edit", e);
			}
		}
		return hasEdit;
	}

	protected static boolean remove(MessageConfig deleteMessage, Connection con) {

		PreparedStatement stmt = null;
		boolean hasDeleted = false;
		try {
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_message);
			stmt.setInt(j++, deleteMessage.getId());

			if (stmt.executeUpdate() > 0) {

				con.commit();
				hasDeleted =true;

			} 

		} catch (Exception e) {
			commonUTIL.displayError("MessageConfigSQL", "remove", e);
			return false;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("MessageConfigSQL", "remove", e);
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
			commonUTIL.displayError("MessageConfigSQL", SELECT_MAX, e);
			return j;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("MessageConfigSQL", SELECT_MAX, e);
			}
		}
		return j;
	}

	protected static int insert(MessageConfig inserMessage, Connection con) {

		PreparedStatement stmt = null;
		boolean isSave = false;
		int id = 0;
		try {
			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_message);
			commonUTIL.display("MessageConfigSQL", INSERT_FROM_message);

			//con.setAutoCommit(false);
			id =selectMax(con);
			stmt.setInt(1, id);
			stmt.setInt(2, inserMessage.getPoid());
			stmt.setString(3, inserMessage.getEventType());
			stmt.setString(4, inserMessage.getProductType());
			stmt.setString(5, inserMessage.getProductSubType());
			stmt.setString(6, inserMessage.getReceiverRole());
			stmt.setString(7, inserMessage.getFormatType());
			stmt.setInt(8, inserMessage.getReceiverID());
			stmt.setString(9, inserMessage.getTemplateName());
			stmt.setString(10, inserMessage.getPoContactType());
			stmt.setString(11, inserMessage.getMessageType());
			stmt.setString(12, inserMessage.getGateWay());
			stmt.setString(13, inserMessage.getAddressType());
			stmt.setString(14, inserMessage.getReceiverContactType());
			if (stmt.executeUpdate() > 0) {

				con.commit();
				isSave = true;

			}

		} catch (Exception e) {
			
			commonUTIL.displayError("MessageConfigSQL", INSERT_FROM_message, e);
			return 0;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("MessageConfigSQL", INSERT_FROM_message, e);
			}
		}
		return id;
	}

	protected static MessageConfig select(int messageIn, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Vector Messages = new Vector();
		MessageConfig messasgeconfig = new MessageConfig();
		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTONE + messageIn);

			ResultSet rs = stmt.executeQuery();
			
			
			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_message);
			
			while (rs.next()) {
							
				messasgeconfig.setId(rs.getInt(1));
				messasgeconfig.setPoid(rs.getInt(2));
				messasgeconfig.setEventType(rs.getString(3));
				messasgeconfig.setProductType(rs.getString(4));
				messasgeconfig.setProductSubType(rs.getString(5));
				messasgeconfig.setReceiverRole(rs.getString(6));
				messasgeconfig.setFormatType(rs.getString(7));
				messasgeconfig.setReceiverID(rs.getInt(8));
				messasgeconfig.setTemplateName(rs.getString(9));
				messasgeconfig.setPoContactType(rs.getString(10));
				messasgeconfig.setMessageType(rs.getString(11));
				messasgeconfig.setGateWay(rs.getString(12));
				messasgeconfig.setAddressType(rs.getString(13));
				messasgeconfig.setReceiverContactType(rs.getString(14));
				return messasgeconfig;

			}
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "select", e);
			return messasgeconfig;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("messageSQL", "selectMax", e);
			}
		}
		return messasgeconfig;
	}

	protected static Collection select(Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector messages = new Vector();

		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTALL);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				MessageConfig messasgeconfig = new MessageConfig();

				messasgeconfig.setId(rs.getInt(1));
				messasgeconfig.setPoid(rs.getInt(2));
				messasgeconfig.setEventType(rs.getString(3));
				messasgeconfig.setProductType(rs.getString(4));
				messasgeconfig.setProductSubType(rs.getString(5));
				messasgeconfig.setReceiverRole(rs.getString(6));
				messasgeconfig.setFormatType(rs.getString(7));
				messasgeconfig.setReceiverID(rs.getInt(8));
				messasgeconfig.setTemplateName(rs.getString(9));
				messasgeconfig.setPoContactType(rs.getString(10));
				messasgeconfig.setMessageType(rs.getString(11));
				messasgeconfig.setGateWay(rs.getString(12));
				messasgeconfig.setAddressType(rs.getString(13));
				messasgeconfig.setReceiverContactType(rs.getString(14));
				
				messages.add(messasgeconfig);

			}
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", SELECTALL, e);
			return messages;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("messageSQL", SELECTALL, e);
			}
		}
		return messages;
	}

	protected static Collection selectmessage(int tradeid, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector messages = new Vector();

		try {
			SELECTONE = SELECTONE + tradeid;
			stmt = dsSQL.newPreparedStatement(con, SELECTONE);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				MessageConfig messasgeconfig = new MessageConfig();

				messasgeconfig.setId(rs.getInt(1));
				messasgeconfig.setPoid(rs.getInt(2));
				messasgeconfig.setEventType(rs.getString(3));
				messasgeconfig.setProductType(rs.getString(4));
				messasgeconfig.setProductSubType(rs.getString(5));
				messasgeconfig.setReceiverRole(rs.getString(6));
				messasgeconfig.setFormatType(rs.getString(7));
				messasgeconfig.setReceiverID(rs.getInt(8));
				messasgeconfig.setTemplateName(rs.getString(9));
				messasgeconfig.setPoContactType(rs.getString(10));
				messasgeconfig.setMessageType(rs.getString(11));
				messasgeconfig.setGateWay(rs.getString(12));
				messasgeconfig.setAddressType(rs.getString(13));
				messasgeconfig.setReceiverContactType(rs.getString(14));
				messages.add(messasgeconfig);

			}
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "selectmessage", e);
			return messages;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("messageSQL", "selectMax", e);
			}
		}
		return messages;
	}

	public static Collection selectmessageOnProduct(int messageid,
			Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector messages = new Vector();

		try {
			con.setAutoCommit(true);
			String sql = SELECTONPRODUCT + messageid;
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				MessageConfig messasgeconfig = new MessageConfig();

				messasgeconfig.setId(rs.getInt(1));
				messasgeconfig.setPoid(rs.getInt(2));
				messasgeconfig.setEventType(rs.getString(3));
				messasgeconfig.setProductType(rs.getString(4));
				messasgeconfig.setProductSubType(rs.getString(5));
				messasgeconfig.setReceiverRole(rs.getString(6));
				messasgeconfig.setFormatType(rs.getString(7));
				messasgeconfig.setReceiverID(rs.getInt(8));
				messasgeconfig.setTemplateName(rs.getString(9));
				messasgeconfig.setPoContactType(rs.getString(10));
				messasgeconfig.setMessageType(rs.getString(11));
				messasgeconfig.setGateWay(rs.getString(12));
				messasgeconfig.setAddressType(rs.getString(13));
				messasgeconfig.setReceiverContactType(rs.getString(14));
				messages.add(messasgeconfig);

			}
			commonUTIL.display("messageSQL", sql);
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "selectmessageOnProduct", e);
			return messages;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("messageSQL", "selectmessageOnProduct",
						e);
			}
		}
		return messages;
	}

	public static Collection selectMessageConfigOn(String whereClause, Connection con) {
		// TODO Auto-generated method stub
		String sql = SELECTWHERE + whereClause;
		PreparedStatement stmt = null;
		Vector messages = new Vector();

		try {
			con.setAutoCommit(true);
			
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				MessageConfig messasgeconfig = new MessageConfig();

				messasgeconfig.setId(rs.getInt(1));
				messasgeconfig.setPoid(rs.getInt(2));
				messasgeconfig.setEventType(rs.getString(3));
				messasgeconfig.setProductType(rs.getString(4));
				messasgeconfig.setProductSubType(rs.getString(5));
				messasgeconfig.setReceiverRole(rs.getString(6));
				messasgeconfig.setFormatType(rs.getString(7));
				messasgeconfig.setReceiverID(rs.getInt(8));
				messasgeconfig.setTemplateName(rs.getString(9));
				messasgeconfig.setPoContactType(rs.getString(10));
				messasgeconfig.setMessageType(rs.getString(11));
				messasgeconfig.setGateWay(rs.getString(12));
				messasgeconfig.setAddressType(rs.getString(13));
				messasgeconfig.setReceiverContactType(rs.getString(14));
				messages.add(messasgeconfig);

			}
			commonUTIL.display("messageSQL", sql);
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "selectmessageOnProduct", e);
			return messages;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("messageSQL", "selectmessageOnProduct",
						e);
			}
		}
		return messages;
	}

}
