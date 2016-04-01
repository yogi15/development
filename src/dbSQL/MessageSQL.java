package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.Message;

public class MessageSQL {
	final static private String tableName = "message";
	final static private String DELETE_FROM_message = "DELETE FROM message where id =? ";
	
	final static private String INSERT_FROM_message = "INSERT into message("
			+ " id,  tradeid,  transferid, messagetype, sendername, senderRole, receiverName, "
			+ " receiverRole, tradeversion, transferversion, action, status, addresstype, templateName, "
			+ " linkid,messagedate, tradedate, messagegateway, productsubtype,eventtype,triggerON,productid,attributes,format,producttype,senderContactType,receiverContactType,senderID,receiverID,messConfigID,subAction,userid,isUpdateBeforeSend" + ") " 
			+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	final static private String SELECT_MAX = "SELECT MESSAGE_SEQ.NEXTVAL DESC_ID FROM dual ";
	
	final static private String SELECTALL = "SELECT id, tradeid, transferid, messageType, sendername, senderRole, " 
			+ " receiverName, receiverRole, tradeversion, "
			+ " transferversion, action, status, addresstype, templateName, linkid, messagedate, tradedate,"
			+ " messagegateway, productsubtype,eventtype,triggerON,productid,attributes,format,producttype,senderContactType,receiverContactType,senderID,receiverID,messConfigID,subAction,userid,isUpdateBeforeSend FROM message order by id";
	final static private String SELECT = "SELECT tradeid FROM message where id =  ?";
	static private String SELECTONE = "SELECT id, tradeid, transferid, messageType, sendername, senderRole, " 
		+ " receiverName, receiverRole, tradeversion, "
		+ " transferversion, action, status, addresstype, templateName, linkid, messagedate, tradedate,"
		+ " messagegateway, productsubtype,eventtype,triggerON,productid,attributes,format,producttype,senderContactType,receiverContactType,senderID,receiverID,messConfigID,subAction,userid,isUpdateBeforeSend FROM message where id = ";
	final static private String SELECTONPRODUCT = "SELECT id, tradeid, transferid, messageType, sendername, senderRole, " 
		+ " receiverName, receiverRole, tradeversion, "
		+ " transferversion, action, status, addresstype, templateName, linkid, messagedate, tradedate,"
		+ " messagegateway, productsubtype,eventtype,triggerON,productid,attributes,format,producttype,senderContactType,receiverContactType,senderID,receiverID,messConfigID,subAction,userid,isUpdateBeforeSend  FROM message order by id";

	
	final static private String SELECTWHERE = "SELECT id, tradeid, transferid, messageType, sendername, senderRole, " 
			+ " receiverName, receiverRole, tradeversion, "
			+ " transferversion, action, status, addresstype, templateName, linkid, messagedate, tradedate,"
			+ " messagegateway, productsubtype,eventtype,triggerON,productid,attributes,format,producttype,senderContactType,receiverContactType,senderID,receiverID,messConfigID,subAction,userid,isUpdateBeforeSend  FROM message  where ";
	
	
	private static String getUpdateSQL(Message message) {

		String updateSQL = new StringBuffer("UPDATE ").append(tableName)
				.append(" set id = ").append(message.getId()).append(",")
				.append(" tradeid = ").append(message.getTradeId()).append(",")
				.append(" transferid = ").append(message.getTransferId()).append(",")
				.append(" messageType = '").append(message.getMessageType()).append("',")
				.append(" sendername = '").append(message.getSenderName()).append("',")
				.append(" senderRole = '").append(message.getSenderRole()).append("',")
				.append(" receiverName ='").append(message.getReceiverName()).append("',")
				.append(" receiverRole ='").append(message.getReceiverRole()).append("',")
				.append(" tradeversion =").append(message.getTradeVersion()).append(",")
				.append(" transferversion = ").append(message.getTransferVersion()).append(",")
				.append(" action = '").append(message.getAction()).append("',")
				.append(" status = '").append(message.getStatus()).append("',")
				.append(" addresstype = '").append(message.getAddressType()).append("',")
				.append(" templateName = '").append(message.getTemplateName()).append("',")
				.append(" linkid = ").append(message.getLinkId()).append(",")
				//.append(" messagedate = '").append("to_date('" +message.getMessageDate()+"', 'DD/MM/YYYY hh24:mi:ss')").append("',")
				//.append(" tradedate = '").append("to_date('" +message.getTradeDate()+"', 'DD/MM/YYYY hh24:mi:ss')").append("',")
				.append("  messagegateway = '").append(message.getMessageGateway()).append("',")
				.append(" productsubtype = '").append(message.getProductSubType()).append("',")
				.append(" eventtype = '").append(message.getEventType()).append("',")
				.append(" triggerON = '").append(message.getTriggerON()).append("',")
				.append(" productid = ").append(message.getproductID()).append(",")
				.append(" attributes = '").append(message.getAttributes()).append("',")
				.append(" format = '").append(message.getFormat()).append("',")
				.append(" producttype = '").append(message.getProductType()).append("',")
				.append(" senderContactType = '").append(message.getSenderContactType()).append("',")
				.append(" receiverContactType = '").append(message.getReceiverContactType()).append("',")
				.append(" senderID = ").append(message.getSenderId()).append(",")
				.append(" receiverID = ").append(message.getReceiverId()).append(",")
				.append(" messConfigID = ").append(message.getMessageConfigID()).append(",")
				.append(" subAction = '").append(message.getSubAction()).append("',")
				.append(" userid = ").append(message.getUserID()).append(",")
				.append(" isUpdateBeforeSend = '").append(message.getUpdateBeforeSend()).append("'")
				
				.toString();
	//	updateSQL = updateSQL + " and  messagedate = to_date('" + message.getMessageDate() +"', 'DD/MM/YYYY hh24:mi:ss')";
	//	updateSQL = updateSQL + " and tradedate = to_date('" + message.getTradeDate() +"', 'DD/MM/YYYY hh24:mi:ss')";
        updateSQL = updateSQL + " where id = "+message.getId();
		//System.out.println(updateSQL);

		return updateSQL;

	}

	public static Message save(Message insertMessage, Connection con) {
		try {
			return insert(insertMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "save", e);
			return null;
		}
	}

	public static Message update(Message updateMessage, Connection con) {
		try {
			return edit(updateMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("MessageSQL", "update", e);
			return null;
		}
	}

	public static boolean delete(Message deleteMessage, Connection con) {
		try {
			return remove(deleteMessage, con);
		} catch (Exception e) {
			commonUTIL.displayError("MessageSQL", "update", e);
			return false;
		}
	}

	public static Message selectMessage(int MessageId, Connection con) {
		try {
			return select(MessageId, con);
		} catch (Exception e) {
			commonUTIL.displayError("MessageSQL", "select", e);
			return null;
		}
	}

	public static Collection selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("MessageSQL", "selectALL", e);
			return null;
		}
	}

	public static int selectMaxID(Connection con) {
		try {
			return selectMax(con);
		} catch (Exception e) {
			commonUTIL.displayError("MessageSQL", "selectMaxID", e);
			return 0;
		}
	}

	protected static Message edit(Message updateMessage, Connection con) {

		PreparedStatement stmt = null;
		String sql = "";
		boolean hasEdit = false;
		Message updateMess = null;
		try {

		//	con.setAutoCommit(false);
			sql = getUpdateSQL(updateMessage);
			stmt = con.prepareStatement(sql);
			
			commonUTIL.display("MessageSQL", "edit " + sql);

			if (stmt.executeUpdate() > 0) {

				con.commit();
				updateMess = selectMessage(updateMessage.getId(), con);
				hasEdit =true;

			} 
			
		} catch (Exception e) {
			commonUTIL.displayError("MessageSQL ", "edit " + sql, e);
			return null;

		} finally {
			try {
				con.setAutoCommit(true);
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("MessageSQL", "edit", e);
			}
		}
		return updateMess;
	}

	protected static boolean remove(Message deleteMessage, Connection con) {

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
			commonUTIL.displayError("MessageSQL", "remove", e);
			return false;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("MessageSQL", "remove", e);
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
			commonUTIL.displayError("MessageSQL", SELECT_MAX, e);
			return j;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("MessageSQL", SELECT_MAX, e);
			}
		}
		return j;
	}

	protected static Message insert(Message inserMessage, Connection con) {

		PreparedStatement stmt = null;
		boolean isSave = false;
		Message newMessage = null;
		
		try {
			int id = selectMax(con);
			commonUTIL.display("MessageSQL", INSERT_FROM_message);
			
			con.setAutoCommit(false);
	
			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_message);
			
			stmt.setInt(1, id);
			stmt.setInt(2, inserMessage.getTradeId());
			stmt.setInt(3, inserMessage.getTransferId());
			stmt.setString(4, inserMessage.getMessageType());
			stmt.setString(5, inserMessage.getSenderName());
			stmt.setString(6, inserMessage.getSenderRole());
			stmt.setString(7, inserMessage.getReceiverName());
			stmt.setString(8, inserMessage.getReceiverRole());
			stmt.setInt(9, inserMessage.getTradeVersion());
			stmt.setInt(10, inserMessage.getTransferVersion());
			stmt.setString(11, inserMessage.getAction());
			stmt.setString(12, inserMessage.getStatus());
			stmt.setString(13, inserMessage.getAddressType());
			stmt.setString(14, inserMessage.getTemplateName());
			stmt.setInt(15, inserMessage.getLinkId());
			stmt.setTimestamp(16, commonUTIL.getStringToTimestamp(inserMessage.getMessageDate()));
			stmt.setTimestamp(17, commonUTIL.getStringToTimestamp(inserMessage.getTradeDate()));
			stmt.setString(18, inserMessage.getMessageGateway());
			stmt.setString(19, inserMessage.getProductSubType());
			stmt.setString(20, inserMessage.getEventType());
			stmt.setString(21, inserMessage.getTriggerON());
			stmt.setInt(22, inserMessage.getproductID());
			stmt.setString(23, inserMessage.getAttributes());
			stmt.setString(24, inserMessage.getFormat());
			stmt.setString(25, inserMessage.getProductType());
			stmt.setString(26, inserMessage.getSenderContactType());
			stmt.setString(27, inserMessage.getReceiverContactType());
			stmt.setInt(28, inserMessage.getSenderId());
			stmt.setInt(29, inserMessage.getReceiverId());
			stmt.setInt(30, inserMessage.getMessageConfigID());
			stmt.setString(31, inserMessage.getSubAction());
			stmt.setInt(32, inserMessage.getUserID());
			stmt.setString(33, "FALSE");  // first insert is going to false always. 
			newMessage = inserMessage;
			if (stmt.executeUpdate() > 0) {
				newMessage = inserMessage;
				newMessage.setId(id);
				con.commit();
				isSave = true;

			}

		} catch (Exception e) {
			
			commonUTIL.displayError("MessageSQL", INSERT_FROM_message, e);

		} finally {
			try {
				stmt.close();
		//		con.close();
			} catch (SQLException e) {
				
				commonUTIL.displayError("MessageSQL", INSERT_FROM_message, e);
			}
		}
		return 	newMessage;
	}

	public static Vector<Message> insert(Vector<Message> mess,Connection con) {
		Vector<Message> messages = new Vector<Message>();
		if(mess == null || mess.isEmpty()) 
			return null;
	//	con.setTransactionIsolation(arg0)
		for(int i=0;i<mess.size();i++) {
			Message newMess = mess.get(i);
			int taskID = newMess.getTaskID();  // i don't want to save task in db. 
			newMess = insert(newMess,con);
			newMess.setTaskID(taskID); 
			if(newMess != null)
			messages.add(newMess);
		}
		return messages;
	}
	public static Vector<Message> update(Vector<Message> mess,Connection con) {
		Vector<Message> messages = new Vector<Message>();
		if(mess == null || mess.isEmpty()) 
			return null;
	//	con.setTransactionIsolation(arg0)
		for(int i=0;i<mess.size();i++) {
			Message newMess = mess.get(i);
			newMess = update(newMess,con);
			if(newMess != null)
			messages.add(newMess);
		}
		return messages;
	}
	protected static Message select(int messageIn, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Vector Messages = new Vector();
		Message message = new Message();
		String sql = "";
		try {
			sql = SELECTONE + messageIn + "   order by id ";
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
							
				message.setId(rs.getInt(1));
				message.setTradeId(rs.getInt(2));
				message.setTransferId(rs.getInt(3));
				message.setMessageType(rs.getString(4));
				message.setSenderName(rs.getString(5));
				message.setSenderRole(rs.getString(6));
				message.setReceiverName(rs.getString(7));
				message.setReceiverRole(rs.getString(8));
				message.setTradeVersion(rs.getInt(9));
				message.setTransferVersion(rs.getInt(10));
				message.setAction(rs.getString(11));
				message.setStatus(rs.getString(12));
				message.setAddressType(rs.getString(13));
				message.setTemplateName(rs.getString(14));
				message.setLinkId(rs.getInt(15));
				message.setMessageDate(rs.getString(16));
				message.setTradeDate(rs.getString(17));
				message.setMessageGateway(rs.getString(18));
				message.setProductSubType(rs.getString(19));
				message.setEventType(rs.getString(20));
				message.setTriggerON(rs.getString(21));
				message.setproductID(rs.getInt(22));
				message.setAttributes(rs.getString(23));
				message.setFormat(rs.getString(24));
				message.setProductType(rs.getString(25));
				message.setSenderContactType(rs.getString(26));
				message.setReceiverContactType(rs.getString(27));
				message.setSenderId(rs.getInt(28));
				message.setReceiverId(rs.getInt(29));
				message.setMessageConfigID(rs.getInt(30));
				message.setSubAction(rs.getString(31));
				message.setUserID(rs.getInt(32));
				message.setUpdateBeforeSend(rs.getString(33));
				return message;

			}
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "select " + sql, e);
			return message;

		} finally {
			try {
				stmt.close();
			//	con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("messageSQL", "selectMax", e);
			}
		}
		return message;
	}

	protected static Collection select(Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector messages = new Vector();

		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTALL);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Message message = new Message();

				message.setId(rs.getInt(1));
				message.setTradeId(rs.getInt(2));
				message.setTransferId(rs.getInt(3));
				message.setMessageType(rs.getString(4));
				message.setSenderName(rs.getString(5));
				message.setSenderRole(rs.getString(6));
				message.setReceiverName(rs.getString(7));
				message.setReceiverRole(rs.getString(8));
				message.setTradeVersion(rs.getInt(9));
				message.setTransferVersion(rs.getInt(10));
				message.setAction(rs.getString(11));
				message.setStatus(rs.getString(12));
				message.setAddressType(rs.getString(13));
				message.setTemplateName(rs.getString(14));
				message.setLinkId(rs.getInt(15));
				message.setMessageDate(rs.getString(16));
				message.setTradeDate(rs.getString(17));
				message.setMessageGateway(rs.getString(18));
				message.setProductSubType(rs.getString(19));
				message.setEventType(rs.getString(20));
				message.setTriggerON(rs.getString(21));
				message.setproductID(rs.getInt(22));
				message.setAttributes(rs.getString(23));
				message.setFormat(rs.getString(24));
				message.setProductType(rs.getString(25));
				message.setSenderContactType(rs.getString(26));
				message.setReceiverContactType(rs.getString(27));
				message.setSenderId(rs.getInt(28));
				message.setReceiverId(rs.getInt(29));
				message.setMessageConfigID(rs.getInt(30));
				message.setSubAction(rs.getString(31));
				message.setUserID(rs.getInt(32));
				message.setUpdateBeforeSend(rs.getString(33));
				messages.add(message);

			}
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", SELECTALL, e);
			return messages;

		} finally {
			try {
				stmt.close();
			//	con.close();
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
				Message message = new Message();

				message.setId(rs.getInt(1));
				message.setTradeId(rs.getInt(2));
				message.setTransferId(rs.getInt(3));
				message.setMessageType(rs.getString(4));
				message.setSenderName(rs.getString(5));
				message.setSenderRole(rs.getString(6));
				message.setReceiverName(rs.getString(7));
				message.setReceiverRole(rs.getString(8));
				message.setTradeVersion(rs.getInt(9));
				message.setTransferVersion(rs.getInt(10));
				message.setAction(rs.getString(11));
				message.setStatus(rs.getString(12));
				message.setAddressType(rs.getString(13));
				message.setTemplateName(rs.getString(14));
				message.setLinkId(rs.getInt(15));
				message.setMessageDate(rs.getString(16));
				message.setTradeDate(rs.getString(17));
				message.setMessageGateway(rs.getString(18));
				message.setProductSubType(rs.getString(19));
				message.setEventType(rs.getString(20));
				message.setTriggerON(rs.getString(21));
				message.setproductID(rs.getInt(22));
				message.setAttributes(rs.getString(23));
				message.setFormat(rs.getString(24));
				message.setProductType(rs.getString(25));
				message.setSenderContactType(rs.getString(26));
				message.setReceiverContactType(rs.getString(27));
				message.setSenderId(rs.getInt(28));
				message.setReceiverId(rs.getInt(29));
				message.setMessageConfigID(rs.getInt(30));
				message.setSubAction(rs.getString(31));
				message.setUserID(rs.getInt(32));
				message.setUpdateBeforeSend(rs.getString(33));
				messages.add(message);

			}
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "selectmessage  " + SELECTONE, e);
			return messages;

		} finally {
			try {
				stmt.close();
				//con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("messageSQL", "selectmessage " + SELECTONE, e);
			}
		}
		return messages;
	}

	public static Collection selectmessageOnProduct(int messageid,
			Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector messages = new Vector();
		String sql = "";
		try {
			con.setAutoCommit(true);
			sql  = SELECTONPRODUCT + messageid;
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Message message = new Message();

				message.setId(rs.getInt(1));
				message.setTradeId(rs.getInt(2));
				message.setTransferId(rs.getInt(3));
				message.setMessageType(rs.getString(4));
				message.setSenderName(rs.getString(5));
				message.setSenderRole(rs.getString(6));
				message.setReceiverName(rs.getString(7));
				message.setReceiverRole(rs.getString(8));
				message.setTradeVersion(rs.getInt(9));
				message.setTransferVersion(rs.getInt(10));
				message.setAction(rs.getString(11));
				message.setStatus(rs.getString(12));
				message.setAddressType(rs.getString(13));
				message.setTemplateName(rs.getString(14));
				message.setLinkId(rs.getInt(15));
				message.setMessageDate(rs.getString(16));
				message.setTradeDate(rs.getString(17));
				message.setMessageGateway(rs.getString(18));
				message.setProductSubType(rs.getString(19));
				message.setEventType(rs.getString(20));
				message.setTriggerON(rs.getString(21));
				message.setproductID(rs.getInt(22));
				message.setAttributes(rs.getString(23));
				message.setFormat(rs.getString(24));
				message.setProductType(rs.getString(25));
				message.setSenderContactType(rs.getString(26));
				message.setReceiverContactType(rs.getString(27));
				message.setSenderId(rs.getInt(28));
				message.setReceiverId(rs.getInt(29));
				message.setMessageConfigID(rs.getInt(30));
				message.setSubAction(rs.getString(31));
				message.setUserID(rs.getInt(32));
				message.setUpdateBeforeSend(rs.getString(33));
				messages.add(message);

			}
			commonUTIL.display("messageSQL", sql);
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "selectmessageOnProduct " +sql, e);
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
    
	
	public static Collection getMessageOnWhere(String where ,
			Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector messages = new Vector();
		String sql = "";
		try {
			con.setAutoCommit(true);
			sql = SELECTWHERE  + where;
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Message message = new Message();

				message.setId(rs.getInt(1));
				message.setTradeId(rs.getInt(2));
				message.setTransferId(rs.getInt(3));
				message.setMessageType(rs.getString(4));
				message.setSenderName(rs.getString(5));
				message.setSenderRole(rs.getString(6));
				message.setReceiverName(rs.getString(7));
				message.setReceiverRole(rs.getString(8));
				message.setTradeVersion(rs.getInt(9));
				message.setTransferVersion(rs.getInt(10));
				message.setAction(rs.getString(11));
				message.setStatus(rs.getString(12));
				message.setAddressType(rs.getString(13));
				message.setTemplateName(rs.getString(14));
				message.setLinkId(rs.getInt(15));
				message.setMessageDate(rs.getString(16));
				message.setTradeDate(rs.getString(17));
				message.setMessageGateway(rs.getString(18));
				message.setProductSubType(rs.getString(19));
				message.setEventType(rs.getString(20));
				message.setTriggerON(rs.getString(21));
				message.setproductID(rs.getInt(22));
				message.setAttributes(rs.getString(23));
				message.setFormat(rs.getString(24));
				message.setProductType(rs.getString(25));
				message.setSenderContactType(rs.getString(26));
				message.setReceiverContactType(rs.getString(27));
				message.setSenderId(rs.getInt(28));
				message.setReceiverId(rs.getInt(29));
				message.setMessageConfigID(rs.getInt(30));
				message.setSubAction(rs.getString(31));
				message.setUserID(rs.getInt(32));
				message.setUpdateBeforeSend(rs.getString(33));
				messages.add(message);

			}
			commonUTIL.display("messageSQL", sql);
		} catch (Exception e) {
			commonUTIL.displayError("messageSQL", "selectmessageOnProduct " + sql, e);
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
