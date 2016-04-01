package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.Position;
import beans.Trade;

public class PositionSQL {
	final static private String tableName = "Position";
	final static private String DELETE_FROM_position =

	" DELETE 					" + " FROM 		POSITION 		" + " WHERE 	POSITIONID = ? 	";

	final static private String newPositionID = " select POSITION_SEQ.NEXTVAL  from dual";

	final static private String INSERT_INTO_position =

	" INSERT INTO Position " + " ( sell_amount," + "buy_amount,"
			+ "sell_quantity," + "buy_quantity," + "avg_price," + "unrealized,"
			+ "realized," + "bookno," + "productId," + "productsubtype,"
			+ "producttype," + "primCurrency," + "amount," + "nominal,"
			+ "realisedPNL," +  "tradedate," + "settledate," + "positionId" 
			+ " )	" + " VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static String getReturnStringDateFormat(String date) {
		if (commonUTIL.isEmpty(date)) {
			return "''";
		} else {
			return "to_date('" + date + "', 'DD/MM/YYYY')";
		}
	}

	private static String getUpdateSQL(Position updatePosition) {
		String UPDATE_position = " UPDATE 	position  set " + " sell_amount= "
				+ updatePosition.getSell_amount() + " , " + " buy_amount= "
				+ updatePosition.getBuy_amount() + " , " + "  sell_quantity= "
				+ updatePosition.getSell_quantity() + " , "
				+ " buy_quantity=  " + updatePosition.getBuy_quantity() + " , "
				+ " avg_price=  " + updatePosition.getAvg_price() + " , "
				+ " unrealized = " + updatePosition.getUnrealized() + " , "
				+ " realized=  " + updatePosition.getRealized() + " , "
				+ " bookno= " + updatePosition.getBookId() + " , "
				+ " productId=  " + updatePosition.getProductId() + " , "
				+ " positionId=  " + updatePosition.getPositionId() + " ,"
				+ " primCurrency=  '" + updatePosition.getPrimaryCurr() + "', "
				+ " productsubtype=  '" + updatePosition.getProductsubType()
				+ "', " + " producttype=  '" + updatePosition.getProductType()
				+ "', " + " amount=  " + updatePosition.getAmount() + ", "
				+ " nominal=  " + updatePosition.getNominal() + ", "
				+ " realisedPNL=  " + updatePosition.getRealizedPNL() + " "
			//	+ " tradedate=  "
			//	+ getReturnStringDateFormat(updatePosition.getTradeDate())
				+ ", " + " settledate=  "
				+ getReturnStringDateFormat(updatePosition.getSettleDate())
				+ " " + " WHERE	 " + " positionId=  "
				+ updatePosition.getPositionId();
		return UPDATE_position;
	}

	final static private String SELECT_MAX =

	"select POSITION_SEQ.NEXTVAL  DESC_ID  from dual ";

	final static private String SELECTALL =

	" SELECT 	sell_amount, buy_amount,sell_quantity,buy_quantity,	avg_price,unrealized,realized,bookno,productId,	positionId,productsubtype,productTYpe,primCurrency,amount,nominal,realisedPNL,tradedate,settledate	FROM Position order by 	positionid	 ";

	final static private String SELECTWHERE =

	" SELECT 	sell_amount, buy_amount,sell_quantity,buy_quantity,	avg_price,unrealized,realized,bookno,productId,	positionId,productsubtype,productTYpe,primCurrency,amount,nominal,realisedPNL,tradedate,settledate		FROM Position where ";

	final static private String SELECT =

	" SELECT 				" + "		positionId 		" + " FROM 					" + "		Position 		"
			+ " where 				" + "		positionId =  ?	";

	static private String SELECTONE =

	" SELECT  "
			+ " sell_amount,"
			+ "buy_amount,	"
			+ "sell_quantity,"
			+ " buy_quantity,"
			+ " avg_price,"
			+ " unrealized,	"
			+ " realized,"
			+ " bookno,"
			+ " productId,"
			+ " positionId,productsubtype,productTYpe,primCurrency,amount,nominal,realisedPNL,tradedate,settledate	 "
			+ " FROM	" + "  Position	" + " where  " + "		positionId =  	";

	static private String SELECTONKEYS =

	" SELECT "
			+ " sell_amount,"
			+ "buy_amount,	"
			+ "sell_quantity,"
			+ " buy_quantity,"
			+ " avg_price,"
			+ " unrealized,	"
			+ " realized,"
			+ " bookno,"
			+ " productId,"
			+ " positionId,productsubtype,productTYpe,primCurrency,amount,nominal,realisedPNL,tradedate,settledate	 "
			+ " FROM	" + "  Position	" + " where ";
	static private String SELECTONFXPOS =

	" SELECT "
			+ " sell_amount,"
			+ "buy_amount,	"
			+ "sell_quantity,"
			+ " buy_quantity,"
			+ " avg_price,"
			+ " unrealized,	"
			+ " realized,"
			+ " bookno,"
			+ " productId,"
			+ " positionId,"
			+ " productsubtype,productTYpe,primCurrency,amount,nominal,realisedPNL,tradedate,settledate	"
			+ " FROM	" + "  Position	" + " where productsubtype  like '";

	static private String SELECTPOSITIONONCURRECYSETTLEDATE =

	" SELECT  "
			+ " sell_amount,"
			+ "buy_amount,	"
			+ "sell_quantity,"
			+ " buy_quantity,"
			+ " avg_price,"
			+ " unrealized,	"
			+ " realized,"
			+ " bookno,"
			+ " productId,"
			+ " positionId,productsubtype,productTYpe,primCurrency,amount,nominal,realisedPNL,tradedate,settledate "
			+ " FROM	" + "  Position	" + " where  ";
		//	+ " FROM	" + "  Position	" + " where  " + "		settledate   	";

	public static Position save(Position insertLiquid, Connection con) {
		Position pos = null;
		try {

			pos = insert(insertLiquid, con);

		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "save", e);
			return null;

		}
		return pos;

	}

	public static boolean update(Position updatePosition, Connection con) {

		try {

			return edit(updatePosition, con);

		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "update", e);

			return false;

		}

	}

	public static boolean delete(Position deletePosition, Connection con) {

		try {

			return remove(deletePosition, con);

		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "update", e);
			return false;

		}

	}

	public static Position selectPosition(int PositionId, Connection con) {

		try {

			return select(PositionId, con);

		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "select", e);
			return null;

		}
	}

	public static Collection selectALL(Connection con) {

		try {

			return select(con);

		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "selectALL", e);
			return null;

		}

	}

	public static int selectMaxID(Connection con) {

		try {

			return selectMax(con);

		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "selectMaxID", e);
			return 0;

		}

	}

	protected static boolean edit(Position updatePosition, Connection con) {

		PreparedStatement stmt = null;
		String updateSQL = "";
		try {
			con.setAutoCommit(false);
			int j = 1;
			updateSQL = getUpdateSQL(updatePosition);
			stmt = dsSQL.newPreparedStatement(con, updateSQL);
			int i = stmt.executeUpdate(updateSQL);

			con.commit();
			commonUTIL.display("positionSQL", "edit " + updateSQL);
			if (i == 0)
				return true;

		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "edit " + updateSQL, e);
			return false;

		} finally {

			try {

				stmt.close();

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				commonUTIL.displayError("positionSQL", "edit", e);

			}

		}
		return true;
	}

	protected static boolean remove(Position deletePosition, Connection con) {

		PreparedStatement stmt = null;

		try {

			int j = 1;

			stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_position);

			stmt.setInt(j++, deletePosition.getPositionId());

			stmt.executeUpdate();

		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "remove", e);

			return false;

		} finally {

			try {

				stmt.close();

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				commonUTIL.displayError("positionSQL", "remove", e);

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

			while (rs.next()) {

				j = rs.getInt("DESC_ID");

			}

		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", SELECT_MAX, e);
			return j;

		} finally {

			try {

				stmt.close();

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				commonUTIL.displayError("positionSQL", SELECT_MAX, e);
			}

		}

		return j;
	}

	protected static Position insert(Position inserPosition, Connection con) {

		PreparedStatement stmt = null;
		int positionID = 0;
		try {

			con.setAutoCommit(false);
			// System.out.println(con.getAutoCommit());
			Position position = selectPosition(inserPosition.getPositionId(),
					con);
			if ((position != null) && (position.getPositionId()) > 0) {
				update(inserPosition, con);
				positionID = inserPosition.getPositionId();
				inserPosition.setPositionId(positionID);
			} else {
				con.setAutoCommit(false);

				positionID = selectMax(con);
				inserPosition.setPositionId(positionID);

				con.setAutoCommit(false);
				int j = 1;

				stmt = dsSQL.newPreparedStatement(con, INSERT_INTO_position);
				// stmt.setInt(1,id+1);

				stmt.setDouble(1, inserPosition.getSell_amount());
				stmt.setDouble(2, inserPosition.getBuy_amount());
				stmt.setDouble(3, inserPosition.getSell_quantity());
				stmt.setDouble(4, inserPosition.getBuy_quantity());
				stmt.setDouble(5, inserPosition.getAvg_price());
				stmt.setDouble(6, inserPosition.getUnrealized());
				stmt.setDouble(7, inserPosition.getRealized());
				stmt.setInt(8, inserPosition.getBookId());
				stmt.setInt(9, inserPosition.getProductId());
				stmt.setString(10, inserPosition.getProductsubType());
				stmt.setString(11, inserPosition.getProductType());
				stmt.setString(12, inserPosition.getPrimaryCurr());
				stmt.setDouble(13, inserPosition.getAmount());
				stmt.setDouble(14, inserPosition.getNominal());
				stmt.setDouble(15, inserPosition.getRealizedPNL());
			
				stmt.setDate(16, commonUTIL
						.convertStringtoSQLDate(inserPosition.getTradeDate()));
				stmt.setDate(17, commonUTIL.convertStringtoSQLDate(inserPosition.getSettleDate()));
				stmt.setInt(18, inserPosition.getPositionId());
				stmt.executeUpdate();
				con.commit();

				commonUTIL.display("positionSQL", INSERT_INTO_position);
			}

		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", INSERT_INTO_position, e);

			return null;

		} finally {

			try {

				stmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("positionSQL", INSERT_INTO_position, e);
			}

		}

		return inserPosition;
	}

	protected static Position select(int positionIn, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;

		Position position = new Position();
		String sql = SELECTONE + positionIn;
		try {

			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				position.setSell_amount(rs.getDouble(1));
				position.setBuy_amount(rs.getDouble(2));
				position.setSell_quantity(rs.getDouble(3));
				position.setBuy_quantity(rs.getDouble(4));
				position.setAvg_price(rs.getDouble(5));
				position.setUnrealized(rs.getDouble(6));
				position.setRealized(rs.getDouble(7));
				position.setBookId(rs.getInt(8));
				position.setProductId(rs.getInt(9));
				position.setPositionId(rs.getInt(10));
				position.setProductsubType(rs.getString(11));
				position.setProductType(rs.getString(12));
				position.setPrimaryCurr(rs.getString(13));
				position.setAmount(rs.getDouble(14));
				position.setNominal(rs.getDouble(15));
				position.setRealizedPNL(rs.getDouble(16));
				position.setTradeDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(17)));
				position.setSettleDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(18)));
				
			}
			commonUTIL.display("positionSQL", "select " + sql);
		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "select", e);
			return position;

		} finally {

			try {

				stmt.close();

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				commonUTIL.displayError("positionSQL", "selectMax", e);
			}

		}

		return position;
	}

	protected static Collection select(Connection con) {

		int j = 0;

		PreparedStatement stmt = null;

		Vector positions = new Vector();

		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTALL);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Position position = new Position();

				position.setSell_amount(rs.getDouble(1));
				position.setBuy_amount(rs.getDouble(2));
				position.setSell_quantity(rs.getDouble(3));
				position.setBuy_quantity(rs.getDouble(4));
				position.setAvg_price(rs.getDouble(5));
				position.setUnrealized(rs.getDouble(6));
				position.setRealized(rs.getDouble(7));
				position.setBookId(rs.getInt(8));
				position.setProductId(rs.getInt(9));
				position.setPositionId(rs.getInt(10));
				position.setProductsubType(rs.getString(11));
				position.setProductType(rs.getString(12));
				position.setPrimaryCurr(rs.getString(13));
				position.setAmount(rs.getDouble(14));
				position.setNominal(rs.getDouble(15));
				position.setRealizedPNL(rs.getDouble(16));
				position.setTradeDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(17)));
				position.setSettleDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(18)));
				
				positions.add(position);

			}

		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", SELECTALL, e);
			return positions;

		} finally {

			try {

				stmt.close();

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				commonUTIL.displayError("positionSQL", SELECTALL, e);

			}

		}

		return positions;
	}

	protected static Collection selectposition(int positionId, Connection con) {
		int j = 0;

		PreparedStatement stmt = null;

		Vector positions = new Vector();

		try {

			SELECTONE = SELECTONE + positionId;

			stmt = dsSQL.newPreparedStatement(con, SELECTONE);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Position position = new Position();

				position.setSell_amount(rs.getDouble(1));
				position.setBuy_amount(rs.getDouble(2));
				position.setSell_quantity(rs.getDouble(3));
				position.setBuy_quantity(rs.getDouble(4));
				position.setAvg_price(rs.getDouble(5));
				position.setUnrealized(rs.getDouble(6));
				position.setRealized(rs.getDouble(7));
				position.setBookId(rs.getInt(8));
				position.setProductId(rs.getInt(9));
				position.setPositionId(rs.getInt(10));
				position.setProductsubType(rs.getString(11));
				position.setProductType(rs.getString(12));
				position.setPrimaryCurr(rs.getString(13));
				position.setAmount(rs.getDouble(14));
				position.setNominal(rs.getDouble(15));
				position.setRealizedPNL(rs.getDouble(16));
				position.setTradeDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(17)));
				position.setSettleDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(18)));
				
				positions.add(position);

			}

		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "selectposition", e);
			return positions;

		} finally {

			try {

				stmt.close();

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				commonUTIL.displayError("positionSQL", "selectMax", e);

			}
		}

		return positions;
	}

	public static Vector<Position> getPositionONWhere(String whereSQL,
			Connection con) {
		int j = 0;

		PreparedStatement stmt = null;

		Vector<Position> positions = new Vector<Position>();
		String sql = SELECTWHERE + whereSQL;

		try {
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Position position = new Position();

				position.setSell_amount(rs.getDouble(1));
				position.setBuy_amount(rs.getDouble(2));
				position.setSell_quantity(rs.getDouble(3));
				position.setBuy_quantity(rs.getDouble(4));
				position.setAvg_price(rs.getDouble(5));
				position.setUnrealized(rs.getDouble(6));
				position.setRealized(rs.getDouble(7));
				position.setBookId(rs.getInt(8));
				position.setProductId(rs.getInt(9));
				position.setPositionId(rs.getInt(10));
				position.setProductsubType(rs.getString(11));
				position.setProductType(rs.getString(12));
				position.setPrimaryCurr(rs.getString(13));
				position.setAmount(rs.getDouble(14));
				position.setNominal(rs.getDouble(15));
				position.setRealizedPNL(rs.getDouble(16));
				position.setTradeDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(17)));
				position.setSettleDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(18)));
				
				positions.add(position);

			}
			commonUTIL.display("positionSQL", "getPositionONWhere  == " + sql);

		} catch (Exception e) {

			commonUTIL.displayError("positionSQL",
					"getPositionONWhere  " + sql, e);
			return positions;

		} finally {

			try {

				stmt.close();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				commonUTIL.displayError("positionSQL", "whereSQL", e);

			}
		}

		return positions;

	}

	public static Position selectopenposOnFXKey(int bookid, int productId,
			String productSubtype, Connection con) {
		int j = 0;

		PreparedStatement stmt = null;

		Position position = null;
		String sql = "";
		try {

			sql = SELECTONKEYS + " bookno = " + bookid
					+ "  and productsubtype = '" + productSubtype.trim()
					+ "'  ";

			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				position = new Position();

				position.setSell_amount(rs.getDouble(1));
				position.setBuy_amount(rs.getDouble(2));
				position.setSell_quantity(rs.getDouble(3));
				position.setBuy_quantity(rs.getDouble(4));
				position.setAvg_price(rs.getDouble(5));
				position.setUnrealized(rs.getDouble(6));
				position.setRealized(rs.getDouble(7));
				position.setBookId(rs.getInt(8));
				position.setProductId(rs.getInt(9));
				position.setPositionId(rs.getInt(10));
				position.setProductType(rs.getString(11));
				position.setProductsubType(rs.getString(12));
				position.setPrimaryCurr(rs.getString(13));
				position.setAmount(rs.getDouble(14));
				position.setNominal(rs.getDouble(15));
				position.setRealizedPNL(rs.getDouble(16));
				position.setTradeDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(17)));
				position.setSettleDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(18)));
				// positions.add(position);

			}
			commonUTIL.display("positionSQL", "selectopenposOnKey  == " + sql);
		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "selectopenposOnKey", e);
			return position;

		} finally {

			try {

				stmt.close();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				commonUTIL.displayError("positionSQL", "selectopenposOnKey", e);

			}
		}

		return position;
	}

	public static Position selectopenposOnKey(int bookid, int productId,
			String productSubtype, Connection con) {
		int j = 0;

		PreparedStatement stmt = null;

		Position position = null;
		String sql = "";
		try {

			sql = SELECTONKEYS + " bookno = " + bookid + " and productid = "
					+ productId + " and productsubtype = '"
					+ productSubtype.trim() + "'  ";

			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				position = new Position();

				position.setSell_amount(rs.getDouble(1));
				position.setBuy_amount(rs.getDouble(2));
				position.setSell_quantity(rs.getDouble(3));
				position.setBuy_quantity(rs.getDouble(4));
				position.setAvg_price(rs.getDouble(5));
				position.setUnrealized(rs.getDouble(6));
				position.setRealized(rs.getDouble(7));
				position.setBookId(rs.getInt(8));
				position.setProductId(rs.getInt(9));
				position.setPositionId(rs.getInt(10));				
				position.setProductsubType(rs.getString(11));
				position.setProductType(rs.getString(12));
				position.setPrimaryCurr(rs.getString(13));
				position.setAmount(rs.getDouble(14));
				position.setNominal(rs.getDouble(15));
				position.setRealizedPNL(rs.getDouble(16));
				position.setTradeDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(17)));
				position.setSettleDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(18)));
				// positions.add(position);

			}
			commonUTIL.display("positionSQL", "selectopenposOnKey  == " + sql);
		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "selectopenposOnKey", e);
			return position;

		} finally {

			try {

				stmt.close();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				commonUTIL.displayError("positionSQL", "selectopenposOnKey", e);

			}
		}

		return position;
	}

	public static Collection selectFXPositions(String productSubtype,
			int bookid, Connection con) {
		int j = 0;

		PreparedStatement stmt = null;

		Vector positions = new Vector();
		String sql = "";
		try {

			sql = SELECTONFXPOS + productSubtype + "%' and bookno = " + bookid;

			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Position position = new Position();

				position.setSell_amount(rs.getDouble(1));
				position.setBuy_amount(rs.getDouble(2));
				position.setSell_quantity(rs.getDouble(3));
				position.setBuy_quantity(rs.getDouble(4));
				position.setAvg_price(rs.getDouble(5));
				position.setUnrealized(rs.getDouble(6));
				position.setRealized(rs.getDouble(7));
				position.setBookId(rs.getInt(8));
				position.setProductId(rs.getInt(9));
				position.setPositionId(rs.getInt(10));
				position.setProductsubType(rs.getString(11));
				position.setProductType(rs.getString(12));
				position.setPrimaryCurr(rs.getString(13));
				position.setAmount(rs.getDouble(14));
				position.setNominal(rs.getDouble(15));
				position.setRealizedPNL(rs.getDouble(16));
				position.setTradeDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(17)));
				position.setSettleDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(18)));
				positions.add(position);

			}
			commonUTIL.display("positionSQL", "selectFXPositions  == " + sql);
		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "selectFXPositions  ", e);
			return positions;

		} finally {

			try {

				stmt.close();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				commonUTIL.displayError("positionSQL", "selectopenposOnKey", e);

			}
		}

		return positions;
	}

	public static Position selectFXPositionOnSettleDateCurrencyBookKey(String tradeDate,
			int bookid, String currency, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector positions = new Vector();
		Position position = null;
		String sql = "";
		try {
			sql = SELECTPOSITIONONCURRECYSETTLEDATE + "tradedate = to_date('"+ tradeDate.trim()+ "', 'dd/MM/YYYY')   and  productSubtype = '"+ currency +"' and bookno = " + bookid;
			//sql = SELECTPOSITIONONCURRECYSETTLEDATE + " productSubtype = '"+ currency +"' and bookno = " + bookid;
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				position = new Position();
			

				position.setSell_amount(rs.getDouble(1));
				position.setBuy_amount(rs.getDouble(2));
				position.setSell_quantity(rs.getDouble(3));
				position.setBuy_quantity(rs.getDouble(4));
				position.setAvg_price(rs.getDouble(5));
				position.setUnrealized(rs.getDouble(6));
				position.setRealized(rs.getDouble(7));
				position.setBookId(rs.getInt(8));
				position.setProductId(rs.getInt(9));
				position.setPositionId(rs.getInt(10));
				position.setProductsubType(rs.getString(11));
				position.setProductType(rs.getString(12));
				position.setPrimaryCurr(rs.getString(13));
				position.setAmount(rs.getDouble(14));
				position.setNominal(rs.getDouble(15));
				position.setRealizedPNL(rs.getDouble(16));
				position.setTradeDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(17)));
				position.setSettleDate(commonUTIL.dateToString(rs
						.getDate(18)));
				positions.add(position);

			}
			commonUTIL.display("positionSQL", "selectFXPositionOnSettleDateCurrencyBookKey  == " + sql);
		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "selectFXPositionOnSettleDateCurrencyBookKey  ", e);
			return position;

		} finally {

			try {

				stmt.close();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				commonUTIL.displayError("positionSQL", "selectFXPositionOnSettleDateCurrencyBookKey", e);

			}
		}

		return position;
	}

	public static Collection selectFXPositionOnSettleDateCurrencyKey(String settleDate,
		 String currency, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector positions = new Vector();
		String sql = "";
		try {
			sql = SELECTPOSITIONONCURRECYSETTLEDATE  + " = to_date('"+ settleDate.trim()+ "', 'dd/MM/YYYY')   and  productSubtype = '"+ currency +"'";

			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Position position = new Position();

				position.setSell_amount(rs.getDouble(1));
				position.setBuy_amount(rs.getDouble(2));
				position.setSell_quantity(rs.getDouble(3));
				position.setBuy_quantity(rs.getDouble(4));
				position.setAvg_price(rs.getDouble(5));
				position.setUnrealized(rs.getDouble(6));
				position.setRealized(rs.getDouble(7));
				position.setBookId(rs.getInt(8));
				position.setProductId(rs.getInt(9));
				position.setPositionId(rs.getInt(10));
				position.setProductsubType(rs.getString(11));
				position.setProductType(rs.getString(12));
				position.setPrimaryCurr(rs.getString(13));
				position.setAmount(rs.getDouble(14));
				position.setNominal(rs.getDouble(15));
				position.setRealizedPNL(rs.getDouble(16));
				position.setTradeDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(17)));
				position.setSettleDate(commonUTIL.convertSQLDatetoString(rs
						.getTimestamp(18)));
				positions.add(position);

			}
			commonUTIL.display("positionSQL", "selectFXPositions  == " + sql);
		} catch (Exception e) {

			commonUTIL.displayError("positionSQL", "selectFXPositions  ", e);
			return positions;

		} finally {

			try {

				stmt.close();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				commonUTIL.displayError("positionSQL", "selectopenposOnKey", e);

			}
		}

		return positions;
	}
	public static Collection selectFXPositionOnSettleDateBookKey(String settleDate,
			 int bookid, Connection con) {
			int j = 0;
			PreparedStatement stmt = null;
			Vector positions = new Vector();
			String sql = "";
			try {
				sql = SELECTPOSITIONONCURRECYSETTLEDATE  + " = to_date('"+ settleDate.trim()+ "', 'dd/MM/YYYY')   and bookno = " + bookid;

				stmt = dsSQL.newPreparedStatement(con, sql);

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {

					Position position = new Position();

					position.setSell_amount(rs.getDouble(1));
					position.setBuy_amount(rs.getDouble(2));
					position.setSell_quantity(rs.getDouble(3));
					position.setBuy_quantity(rs.getDouble(4));
					position.setAvg_price(rs.getDouble(5));
					position.setUnrealized(rs.getDouble(6));
					position.setRealized(rs.getDouble(7));
					position.setBookId(rs.getInt(8));
					position.setProductId(rs.getInt(9));
					position.setPositionId(rs.getInt(10));
					position.setProductsubType(rs.getString(11));
					position.setProductType(rs.getString(12));
					position.setPrimaryCurr(rs.getString(13));
					position.setAmount(rs.getDouble(14));
					position.setNominal(rs.getDouble(15));
					position.setRealizedPNL(rs.getDouble(16));
					position.setTradeDate(commonUTIL.convertSQLDatetoString(rs
							.getTimestamp(17)));
					position.setSettleDate(commonUTIL.convertSQLDatetoString(rs
							.getTimestamp(18)));
					positions.add(position);

				}
				commonUTIL.display("positionSQL", "selectFXPositions  == " + sql);
			} catch (Exception e) {

				commonUTIL.displayError("positionSQL", "selectFXPositions  ", e);
				return positions;

			} finally {

				try {

					stmt.close();

				} catch (SQLException e) {

					// TODO Auto-generated catch block

					commonUTIL.displayError("positionSQL", "selectopenposOnKey", e);

				}
			}

			return positions;
		}
	
	
}
