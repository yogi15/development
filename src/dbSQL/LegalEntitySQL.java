package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.BaseBean;
import beans.CounterParty;
import beans.Product;
import util.commonUTIL;

public class LegalEntitySQL implements BaseSQL {

	final static private String tableName = "LE";
	final static private String DELETE = "DELETE FROM " + tableName
			+ "   where id =? ";
	final static private String INSERT = "INSERT into " + tableName
			+ "(id,name,role,status,attributes,alias, contact, country) values(?,?,?,?,?,?,?,?)";
	final static private String UPDATE = "UPDATE " + tableName
			+ " set name=?,role=?,status=?  where id = ? ";
	final static private String SELECT_MAX = "SELECT MAX(id) DESC_ID FROM "
			+ tableName + " ";
	final static private String SELECTALL = "SELECT id,name,role,status,attributes,alias, contact, country FROM "
			+ tableName + " order by id ";
	final static private String SELECT = "SELECT id,name,role,status,attributes,alias, contact, country  FROM "
			+ tableName + " where id =  ?  ";
	final static private String SELECTWHERE = "SELECT id,name,role,status,attributes,alias, contact, country  FROM "
			+ tableName + " where  ";
	static private String SELECTONE = "SELECT id,name,role,status,attributes,alias, contact, country FROM "
			+ tableName + " where id =  ";
	
	private static String getUpdateSQL(CounterParty le) {
		
		String updateSQL = "UPDATE le  set " 
				+ " name= '" + le.getName()
				+ "',role= '" + le.getRole() 
				+ "',status= '" + le.getstatus()
				+ "',attributes= '" + le.getAttributes() 
				+ "',alias= '" + le.getAlias() 
				+ "', contact= '"+ le.getContact() 
				+ "', country= '" + le.getCountry()
				+ "'  where id= " + le.getId();
		
		return updateSQL;
		
	}

	public static int save(CounterParty insertLegalEntity, Connection con) {
		try {
			return insert(insertLegalEntity, con);
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", "save", e);
			return 0;
		}
	}

	public static boolean update(CounterParty updateLegalEntity, Connection con) {
		try {
			return edit(updateLegalEntity, con);
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", "update", e);
			return false;
		}
	}

	public static boolean delete(CounterParty deleteLegalEntity, Connection con) {
		try {
			return remove(deleteLegalEntity, con);
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", "update", e);
			return false;
		}
	}

	protected static boolean remove(CounterParty deleteLegalEntity,
			Connection con) {

		PreparedStatement stmt = null;
		
		try {
			int j = 1;
			
			con.setAutoCommit(false);
			
			stmt = dsSQL.newPreparedStatement(con, DELETE);
			stmt.setInt(j++, deleteLegalEntity.getId());
			stmt.executeUpdate();
		
			con.commit();

		} catch (Exception e) {
			
			commonUTIL.displayError("LegalEntitySQL", "remove", e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL", "remove", e);
			}
		}
		return true;
	}

	public static Vector selectLegalEntity(int LegalEntityId, Connection con) {
		try {
			return (Vector) selectA(LegalEntityId, con);
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", "select", e);
			return null;
		}
	}

	public static Collection selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", "selectALL", e);
			return null;
		}
	}

	public static int selectMaxID(Connection con) {
		try {
			return selectMax(con);
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", "selectMaxID", e);
			return 0;
		}
	}

	protected static boolean edit(CounterParty updateLegalEntity, Connection con) {

		PreparedStatement stmt = null;
		String sql = getUpdateSQL(updateLegalEntity);
		try {
			con.setAutoCommit(false);
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, sql);
			stmt.executeUpdate(sql);
			con.commit();
			commonUTIL.display("LegalEntitySQL ::  edit", sql);
			con.commit();
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", "edit", e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL", UPDATE, e);
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
			commonUTIL.displayError("LegalEntitySQL", SELECT_MAX, e);
			return j;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL", SELECT_MAX, e);
			}
		}
		return j;
	}

	protected static int insert(CounterParty inserLegalEntity, Connection con) {

		PreparedStatement stmt = null;
		int id = 0;
		try {
			con.setAutoCommit(false);
			id = selectMax(con);
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, INSERT);
						
			stmt.setInt(1, id + 1);
			stmt.setString(2, inserLegalEntity.getName());
			stmt.setString(3, inserLegalEntity.getRole());
			stmt.setString(4, inserLegalEntity.getstatus());
			stmt.setString(5, inserLegalEntity.getAttributes());
			stmt.setString(6, inserLegalEntity.getAlias());
			stmt.setString(7, inserLegalEntity.getContact());
			stmt.setString(8, inserLegalEntity.getCountry());
			
			commonUTIL.display("LESQL", "insert" + INSERT);
			
			stmt.executeUpdate();
			con.commit();
			
		} catch (Exception e) {
			commonUTIL.displayError("LESQL", "insert", e);
			return 0;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LESQL", "insert", e);
			}
		}
		return id + 1;
	}
	protected static CounterParty saveNew(CounterParty inserLegalEntity, Connection con) {

		PreparedStatement stmt = null;
		int id = 0;
		try {
			con.setAutoCommit(false);
			id = selectMax(con) +1;
			int j = id + 1;
			stmt = dsSQL.newPreparedStatement(con, INSERT);
						
			stmt.setInt(1, id);
			stmt.setString(2, inserLegalEntity.getName());
			stmt.setString(3, inserLegalEntity.getRole());
			stmt.setString(4, inserLegalEntity.getstatus());
			stmt.setString(5, inserLegalEntity.getAttributes());
			stmt.setString(6, inserLegalEntity.getAlias());
			stmt.setString(7, inserLegalEntity.getContact());
			stmt.setString(8, inserLegalEntity.getCountry());
			
			commonUTIL.display("LESQL", "insert" + INSERT);
			
			stmt.executeUpdate();
			con.commit();
			inserLegalEntity.setId(id);
			return inserLegalEntity;
		} catch (Exception e) {
			commonUTIL.displayError("LESQL", "insert", e);
			return null;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LESQL", "insert", e);
			}
		} 
	}
	protected static Collection selectA(int LegalEntityID, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Vector LegalEntitys = new Vector();

		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECTONE + LegalEntityID);

			ResultSet rs = stmt.executeQuery();
			 
			while (rs.next()) {
				
				CounterParty LegalEntity = new CounterParty();
				LegalEntity.setId(rs.getInt(1));
				LegalEntity.setName(rs.getString(2));
				LegalEntity.setRole(rs.getString(3));
				LegalEntity.setStatus(rs.getString(4));
				LegalEntity.setAttributes(rs.getString(5));
				LegalEntity.setAlias(rs.getString(6));
				LegalEntity.setContact(rs.getString(7));
				LegalEntity.setCountry(rs.getString(8));

				LegalEntitys.add(LegalEntity);

			}
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL",
					SELECTONE + LegalEntityID, e);
			return LegalEntitys;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL", SELECTONE
						+ LegalEntityID, e);
			}
		}
		return LegalEntitys;
	}

	protected static Collection select(Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector LegalEntitys = new Vector();

		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECTALL);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				CounterParty LegalEntity = new CounterParty();

				LegalEntity.setId(rs.getInt(1));
				LegalEntity.setName(rs.getString(2));
				LegalEntity.setRole(rs.getString(3));
				LegalEntity.setStatus(rs.getString(4));
				LegalEntity.setAttributes(rs.getString(5));
				LegalEntity.setAlias(rs.getString(6));
				LegalEntity.setContact(rs.getString(7));
				LegalEntity.setCountry(rs.getString(8));
				
				LegalEntitys.add(LegalEntity);

			}
			
			commonUTIL.display("LegalEntitySQL", SELECTALL);
			
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", SELECTALL, e);
			return LegalEntitys;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL", "SELECTALL", e);
			}
		}
		return LegalEntitys;
	}

	protected static Collection selectLE(int bookId, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector LegalEntitys = new Vector();

		try {
			con.setAutoCommit(false);
			SELECTONE = SELECTONE + bookId;
			stmt = dsSQL.newPreparedStatement(con, SELECTONE);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				CounterParty LegalEntity = new CounterParty();

				LegalEntity.setId(rs.getInt(1));
				LegalEntity.setName(rs.getString(2));
				LegalEntity.setRole(rs.getString(3));
				LegalEntity.setStatus(rs.getString(4));
				LegalEntity.setAttributes(rs.getString(5));
				LegalEntity.setAlias(rs.getString(6));
				LegalEntity.setContact(rs.getString(7));
				LegalEntity.setCountry(rs.getString(8));
				
				LegalEntitys.add(LegalEntity);

			}
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", "selectLegalEntity", e);
			return LegalEntitys;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL", "selectMax", e);
			}
		}
		return LegalEntitys;
	}

	protected static Collection selectALLLEs(Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector LegalEntitys = new Vector();

		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECTALL);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				CounterParty LegalEntity = new CounterParty();

				LegalEntity.setId(rs.getInt(1));
				LegalEntity.setName(rs.getString(2));
				LegalEntity.setRole(rs.getString(3));
				LegalEntity.setStatus(rs.getString(4));
				LegalEntity.setAttributes(rs.getString(5));
				LegalEntity.setAlias(rs.getString(6));
				LegalEntity.setContact(rs.getString(7));
				LegalEntity.setCountry(rs.getString(8));

				LegalEntitys.add(LegalEntity);

			}
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", SELECTALL, e);
			return LegalEntitys;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL", SELECTALL, e);
			}
		}
		return LegalEntitys;
	}

	public static Collection selectLEOnWhereClause(String sqlw, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector LegalEntitys = new Vector();
		String sql = SELECTWHERE + sqlw + " order by id";
		try {

			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				CounterParty LegalEntity = new CounterParty();

				LegalEntity.setId(rs.getInt(1));
				LegalEntity.setName(rs.getString(2));
				LegalEntity.setRole(rs.getString(3));
				LegalEntity.setStatus(rs.getString(4));
				LegalEntity.setAttributes(rs.getString(5));
				LegalEntity.setAlias(rs.getString(6));
				LegalEntity.setContact(rs.getString(7));
				LegalEntity.setCountry(rs.getString(8));
				
				LegalEntitys.add(LegalEntity);

			}
			commonUTIL.display("LegalEntitySQL", sql);
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", sql, e);
			return LegalEntitys;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL", sql, e);
			}
		}
		return LegalEntitys;
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
		return saveNew((CounterParty)sql,con);
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
		return selectLEOnWhereClause(where,con);
	}

	@Override
	public Collection selectALLData(Connection con) {
		// TODO Auto-generated method stub
		return selectALLLEs(con);
	}

}
