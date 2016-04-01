package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.BicData;

public class BicDataSQL {

	final static private String DELETE_FROM_BicData = "DELETE FROM BicData where id =? ";
	final static private String INSERT_FROM_BicData = "INSERT into BicData(id,currencyPair,currencyHolding,bookid,TransferTOBook) values(?,?,?,?,?)";

	final static private String SELECT_MAX = "SELECT MAX(id) DESC_ID FROM BicData ";
	final static private String SELECTALL = "SELECT BICCODE, BICBRANCH, COMPANY, BRANCH, CITY, SUB_TYPE, ADDRESS, COUNTRY FROM BicData order by BICCODE";
	final static private String SELECT = "SELECT BICCODE, BICBRANCH, COMPANY, BRANCH, CITY, SUB_TYPE, ADDRESS, COUNTRY FROM BicData where BICCODE =  ?";
	static private String SELECTFROMTRADE = "SELECT BICCODE, BICBRANCH, COMPANY, BRANCH, CITY, SUB_TYPE, ADDRESS, COUNTRY FROM BicData"
			+ " FROM BicData where BICCODE =  ";
	static private String SELECTFROMWhere = "SELECT BICCODE, BICBRANCH, COMPANY, BRANCH, CITY, SUB_TYPE, ADDRESS, COUNTRY FROM BicData where";

	private static String getUpdateSQL(BicData bicData) {
		String updateSQL = new StringBuffer("UPDATE BicData  set ")
				.append(" BICCODE= '").append(bicData.getBicCode()).append("'")
				.append(" BICBRANCH= '").append(bicData.getBicBranch()).append("'")
				.append(" COMPANY= '").append(bicData.getCompany()).append("'")
				.append(" BRANCH= '").append(bicData.getBranch()).append("'")
				.append(" CITY= '").append(bicData.getCity()).append("'")
				.append(" SUB_TYPE= '").append(bicData.getSub_type()).append("'")
				.append(" ADDRESS= '").append(bicData.getAddress()).append("'")
				.append(" COUNTRY= '").append(bicData.getCountry()).append("'")
				.append(" where BICCODE= ").append(bicData.getBicCode()).toString();
		return updateSQL;
	}

	public static boolean save(BicData insertBicData, Connection con) {
		try {
			return insert(insertBicData, con);
		} catch (Exception e) {
			commonUTIL.displayError("BicDataSQL", "save", e);
			return false;
		}
	}

	public static boolean update(BicData updateBicData, Connection con) {
		try {
			return edit(updateBicData, con);
		} catch (Exception e) {
			commonUTIL.displayError("BicDataSQL", "update", e);
			return false;
		}
	}

	public static boolean delete(BicData deleteBicData, Connection con) {
		try {
			return remove(deleteBicData, con);
		} catch (Exception e) {
			commonUTIL.displayError("BicDataSQL", "update", e);
			return false;
		}
	}

	public static Vector<BicData> selectBicData(int BicDataId, Connection con) {
		try {
			return select(BicDataId, con);
		} catch (Exception e) {
			commonUTIL.displayError("BicDataSQL", "select", e);
			return null;
		}
	}

	public static int selectMaxID(Connection con) {
		try {
			return selectMax(con);
		} catch (Exception e) {
			commonUTIL.displayError("BicDataSQL", "selectMaxID", e);
			return 0;
		}
	}

	protected static boolean edit(BicData updateBicData, Connection con) {

		PreparedStatement stmt = null;
		
		String sql = getUpdateSQL(updateBicData);
		
		try {
			
			con.setAutoCommit(false);

			stmt = dsSQL.newPreparedStatement(con, sql);
			stmt.executeUpdate(sql);
			
			con.commit();
			
			commonUTIL.display("BicDataSQL ::  edit", sql);
			
		} catch (Exception e) {
			
			commonUTIL.displayError("BicDataSQL", "edit " + sql, e);
			return false;

		} finally {
			
			try {
				
				stmt.close();
				
			} catch (SQLException e) {

				commonUTIL.displayError("BicDataSQL", "edit " + sql, e);
				
			}
		}
		return true;
	}

	protected static boolean remove(BicData deleteBicData, Connection con) {

		PreparedStatement stmt = null;
		
		try {
			int j = 1;
			
			con.setAutoCommit(false);
			
			stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_BicData);
			stmt.setString(j++, deleteBicData.getBicCode());

			stmt.executeUpdate();
			
			con.commit();
		
		} catch (Exception e) {
			
			commonUTIL.displayError("BicDataSQL", "remove", e);
			return false;

		} finally {
			
			try {
				
				stmt.close();
			
			} catch (SQLException e) {

				commonUTIL.displayError("BicDataSQL", "remove", e);
				
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
			
			commonUTIL.displayError("BicDataSQL", SELECT_MAX, e);
			return j;

		} finally {
			
			try {
				
				stmt.close();
				
			} catch (SQLException e) {

				commonUTIL.displayError("BicDataSQL", SELECT_MAX, e);
				
			}
		}
		
		return j;
		
	}

	protected static boolean insert(BicData inserBicData, Connection con) {

		PreparedStatement stmt = null;

		boolean isSaved = false;

		try {
			con.setAutoCommit(false);

			stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_BicData);

			stmt.setString(1, inserBicData.getBicCode());
			stmt.setString(2, inserBicData.getBicBranch());
			stmt.setString(3, inserBicData.getCompany());
			stmt.setString(3, inserBicData.getBranch());
			stmt.setString(4, inserBicData.getCity());
			stmt.setString(5, inserBicData.getSub_type());
			stmt.setString(4, inserBicData.getAddress());
			stmt.setString(5, inserBicData.getCountry());

			stmt.executeUpdate();
			con.commit();

			isSaved = true;

			commonUTIL.display("BicDataSQL", INSERT_FROM_BicData);

		} catch (Exception e) {

			commonUTIL.displayError("BicDataSQL", INSERT_FROM_BicData, e);

		} finally {
			
			try {
				
				stmt.close();
				
			} catch (SQLException e) {
				
				commonUTIL.displayError("BicDataSQL", INSERT_FROM_BicData, e);
				
			}
		}
		
		return isSaved;
	}

	protected static Vector<BicData> select(int bicDataVecIn, Connection con) {

		PreparedStatement stmt = null;
		Vector<BicData> bicDataVec = new Vector<BicData>();
		String sql = SELECT;
		sql = sql + bicDataVecIn;

		try {
			
			con.setAutoCommit(false);
			
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {

				BicData bicDataonfig = new BicData();
				bicDataonfig.setBicCode(rs.getString(1));
				bicDataonfig.setBicBranch(rs.getString(2));
				bicDataonfig.setCompany(rs.getString(3));
				bicDataonfig.setBicBranch(rs.getString(4));
				bicDataonfig.setCity(rs.getString(5));
				bicDataonfig.setSub_type(rs.getString(6));
				bicDataonfig.setAddress(rs.getString(7));
				bicDataonfig.setCountry(rs.getString(8));

				bicDataVec.addElement(bicDataonfig);

			}

			commonUTIL.display("BicDataSQL", " select " + sql);
			
			return bicDataVec;

		} catch (Exception e) {

			commonUTIL.displayError("BicDataSQL", "select    " + sql, e);
			return bicDataVec;

		} finally {
			
			try {
				
				stmt.close();
				
			} catch (SQLException e) {
				
				commonUTIL.displayError("BicDataSQL", "select " + sql, e);
				
			}

		}
	}

	public static Collection select(Connection con) {
		
		PreparedStatement stmt = null;
		
		Vector bicDataVec = new Vector();

		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECTALL);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				BicData bicDataonfig = new BicData();

				bicDataonfig.setBicCode(rs.getString(1));
				bicDataonfig.setBicBranch(rs.getString(2));
				bicDataonfig.setCompany(rs.getString(3));
				bicDataonfig.setBicBranch(rs.getString(4));
				bicDataonfig.setCity(rs.getString(5));
				bicDataonfig.setSub_type(rs.getString(6));
				bicDataonfig.setAddress(rs.getString(7));
				bicDataonfig.setCountry(rs.getString(8));

				bicDataVec.addElement(bicDataonfig);

			}
		} catch (Exception e) {
			commonUTIL.displayError("BicDataSQL", SELECTALL, e);
			return bicDataVec;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("bicDataVecSQL", SELECTALL, e);
			}
		}
		return bicDataVec;

	}

	public static Collection selectALLBicData() {
		
		return null;
	}

	public static Vector selectWhere(String sql, Connection con) {
		
		int j = 0;
		PreparedStatement stmt = null;
		Vector<BicData> bicDatas = new Vector<BicData>();
		String sql1 = SELECTFROMWhere + sql;

		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, sql1);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				BicData bicDataonfig = new BicData();
				bicDataonfig.setBicCode(rs.getString(1));
				bicDataonfig.setBicBranch(rs.getString(2));
				bicDataonfig.setCompany(rs.getString(3));
				bicDataonfig.setBicBranch(rs.getString(4));
				bicDataonfig.setCity(rs.getString(5));
				bicDataonfig.setSub_type(rs.getString(6));
				bicDataonfig.setAddress(rs.getString(7));
				bicDataonfig.setCountry(rs.getString(8));

				bicDatas.add(bicDataonfig);
			}
			
			commonUTIL.display("BicDataSQL", " select " + sql1);
			
			return bicDatas;
			
		} catch (Exception e) {
			
			commonUTIL.displayError("BicDataSQL", "select    " + sql, e);
			return bicDatas;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
		
				commonUTIL.displayError("BicDataSQL", "select " + sql, e);
				
			}

		}
	}

}
