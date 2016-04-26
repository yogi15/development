package dbSQL;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.Attribute;
import beans.AttributeContainer;
import beans.BaseBean;
import beans.LEAttribute;
import beans.LeContacts;
import beans.LegalEntity;
import beans.Product;
import constants.BeanConstants;
import util.commonUTIL;

public class LegalEntitySQL extends BaseSQL {

		final static private String tableName = "LE";
	final static private String DELETE = "DELETE FROM " + tableName
			+ "   where id =? ";
	final static private String INSERT = "INSERT into " + tableName
			+ "(id,name,role,status,attributes,alias, contact, country,HOLIDAYCALENDER,TIMEZONE,ACTIVEINACTIVE) values(?,?,?,?,?,?,?,?,?,?,?)";
	final static private String UPDATE = "UPDATE " + tableName
			+ " set name=?,role=?,status=?  where id = ? ";
	final static private String SELECT_MAX = "SELECT MAX(id) DESC_ID FROM "
			+ tableName + " ";
	final static private String SELECTALL = "SELECT id,name,role,status,attributes,alias, contact, country ,HOLIDAYCALENDER,TIMEZONE,ACTIVEINACTIVE FROM "
			+ tableName + "  order by id ";
	final static private String SELECT = "SELECT id,name,role,status,attributes,alias, contact, country ,HOLIDAYCALENDER,TIMEZONE,ACTIVEINACTIVE FROM "
			+ tableName + " where id =  ?  ";
	final static private String SELECTWHERE = "SELECT id,name,role,status,attributes,alias, contact, country ,HOLIDAYCALENDER,TIMEZONE,ACTIVEINACTIVE FROM "
			+ tableName + " where  ";
	static private String SELECTONE = "SELECT id,name,role,status,attributes,alias, contact, country,HOLIDAYCALENDER,TIMEZONE,ACTIVEINACTIVE FROM "
			+ tableName + " where id =  ";
	final static private String SQLCOUNT = new StringBuffer(
			"SELECT count(*) countRows ").append(" FROM Le where  ")
			.toString();
	private static String getUpdateSQL(LegalEntity le) {
		
		String updateSQL = "UPDATE le  set " 
				+ " name= '" + le.getName()
				+ "',role= '" + le.getRole() 
				+ "',status= '" + le.getstatus()
				+ "',attributes= '" + le.getAttributes() 
				+ "',alias= '" + le.getAlias() 
				+ "', contact= '"+ le.getContact() 
				+ "', country= '" + le.getCountry()
				+"',HOLIDAYCALENDER='"+le.getHOLIDAYCALENDER()
				+"',TIMEZONE='"+le.getTIMEZONE()
				+"',ACTIVEINACTIVE='"+le.isACTIVEINACTIVE()
				+ "'  where id= " + le.getId();
		
		return updateSQL;
		
	}
	public static LegalEntity save(LegalEntity insertLegalEntity, Connection con) {
		try {
			return insertLe(insertLegalEntity, con);
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", "save", e);
			return null;
		}
	}

	public static boolean update(LegalEntity updateLegalEntity, Connection con) {
		try {
			return edit(updateLegalEntity, con);
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", "update", e);
			return false;
		}
	}

	public static boolean deleteLe(LegalEntity deleteLegalEntity, Connection con) {
		try {
			return remove(deleteLegalEntity, con);
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", "update", e);
			return false;
		}
	}

	protected static boolean remove(LegalEntity deleteLegalEntity,
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
			return (Vector) select1(LegalEntityId, con);
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

	protected static boolean edit(LegalEntity updateLegalEntity, Connection con) {

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

	protected static LegalEntity insertLe(LegalEntity inserLegalEntity, Connection con) {

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
			stmt.setString(9, inserLegalEntity.getHOLIDAYCALENDER());
			stmt.setString(10, inserLegalEntity.getTIMEZONE());
			stmt.setBoolean(11, inserLegalEntity.isACTIVEINACTIVE());
			stmt.executeUpdate();
			con.commit();
			inserLegalEntity.setId(id);
			insertAttributes(inserLegalEntity.getAttributeContainer(),id,BeanConstants.LEGALENTITY);
			return  inserLegalEntity;
			
			
		} catch (Exception e) {
			 commonUTIL.displayError("LegalEntitySQL",INSERT,e);
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
	protected static LegalEntity saveNew(LegalEntity inserLegalEntity, Connection con) {

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
			stmt.setString(9, inserLegalEntity.getHOLIDAYCALENDER());
			stmt.setString(10, inserLegalEntity.getTIMEZONE());
			stmt.setBoolean(11, inserLegalEntity.isACTIVEINACTIVE());
			stmt.executeUpdate();
			con.commit();
			commonUTIL.display("LESQL", "insert" + INSERT);
			inserLegalEntity.setId(id);
			insertAttributes(inserLegalEntity.getAttributeContainer(),id,BeanConstants.LEGALENTITY);
			
			return inserLegalEntity;
		} catch (Exception e) {
			commonUTIL.displayError("LESQL", "insert",  e);
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
	protected static Collection select1(int LegalEntityID, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Vector LegalEntitys = new Vector();

		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, SELECTONE + LegalEntityID);

			ResultSet rs = stmt.executeQuery();
			 
			while (rs.next()) {
				
				LegalEntity LegalEntity = new LegalEntity();
				LegalEntity.setId(rs.getInt(1));
				LegalEntity.setName(rs.getString(2));
				LegalEntity.setRole(rs.getString(3));
				LegalEntity.setStatus(rs.getString(4));
				LegalEntity.setAttributes(rs.getString(5));
				LegalEntity.setAlias(rs.getString(6));
				LegalEntity.setContact(rs.getString(7));
				LegalEntity.setCountry(rs.getString(8));
				LegalEntity.setHOLIDAYCALENDER(rs.getString(9));
				LegalEntity.setTIMEZONE(rs.getString(10));
				if(rs.getString(11).equalsIgnoreCase("True"))
				{LegalEntity.setACTIVEINACTIVE(rs.getBoolean("True"));}
				if(rs.getString(11).equalsIgnoreCase("false"))
					{LegalEntity.setACTIVEINACTIVE(rs.getBoolean("false"));}
				LegalEntitys.add(LegalEntity);

			}
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL",
					SELECTONE + LegalEntityID
					+ e.getMessage(), e);
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
				
				LegalEntity LegalEntity = new LegalEntity();

				LegalEntity.setId(rs.getInt(1));
				LegalEntity.setName(rs.getString(2));
				LegalEntity.setRole(rs.getString(3));
				LegalEntity.setStatus(rs.getString(4));
				LegalEntity.setAttributes(rs.getString(5));
				LegalEntity.setAlias(rs.getString(6));
				LegalEntity.setContact(rs.getString(7));
				LegalEntity.setCountry(rs.getString(8));
				LegalEntity.setHOLIDAYCALENDER(rs.getString(9));
				LegalEntity.setTIMEZONE(rs.getString(10));
				if(!commonUTIL.isEmpty(rs.getString(11)) && rs.getString(11).equalsIgnoreCase("true"))
				LegalEntity.setACTIVEINACTIVE(true);
				LegalEntity.setACTIVEINACTIVE(false);
				LegalEntitys.add(LegalEntity);

			}
			commonUTIL.display("LegalEntitySQL", SELECTALL);
			
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitySQL", SELECTALL +e.getMessage(), e);
			return LegalEntitys;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL", "SELECTALL" +e.getMessage(), e);
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
				
				LegalEntity LegalEntity = new LegalEntity();

				LegalEntity.setId(rs.getInt(1));
				LegalEntity.setName(rs.getString(2));
				LegalEntity.setRole(rs.getString(3));
				LegalEntity.setStatus(rs.getString(4));
				LegalEntity.setAttributes(rs.getString(5));
				LegalEntity.setAlias(rs.getString(6));
				LegalEntity.setContact(rs.getString(7));
				LegalEntity.setCountry(rs.getString(8));
				LegalEntity.setHOLIDAYCALENDER(rs.getString(9));
				LegalEntity.setTIMEZONE(rs.getString(10));
				LegalEntity.setACTIVEINACTIVE(rs.getBoolean(11));

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
				
				LegalEntity LegalEntity = new LegalEntity();

				LegalEntity.setId(rs.getInt(1));
				LegalEntity.setName(rs.getString(2));
				LegalEntity.setRole(rs.getString(3));
				LegalEntity.setStatus(rs.getString(4));
				LegalEntity.setAttributes(rs.getString(5));
				LegalEntity.setAlias(rs.getString(6));
				LegalEntity.setContact(rs.getString(7));
				LegalEntity.setCountry(rs.getString(8));
				LegalEntity.setHOLIDAYCALENDER(rs.getString(9));
				LegalEntity.setTIMEZONE(rs.getString(10));
				LegalEntity.setACTIVEINACTIVE(rs.getBoolean(11));

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
				
				LegalEntity LegalEntity = new LegalEntity();

				LegalEntity.setId(rs.getInt(1));
				LegalEntity.setName(rs.getString(2));
				LegalEntity.setRole(rs.getString(3));
				LegalEntity.setStatus(rs.getString(4));
				LegalEntity.setAttributes(rs.getString(5));
				LegalEntity.setAlias(rs.getString(6));
				LegalEntity.setContact(rs.getString(7));
				LegalEntity.setCountry(rs.getString(8));
				LegalEntity.setHOLIDAYCALENDER(rs.getString(9));
				LegalEntity.setTIMEZONE(rs.getString(10));
				LegalEntity.setACTIVEINACTIVE(rs.getBoolean(11));

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

	public BaseBean insertSQL(String sql, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateSQL(String sql, Connection con) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteSQL(String sql, Connection con) {
		// TODO Auto-generated method stub
		return false;
	}

	public BaseBean insertSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return (BaseBean) saveNew((LegalEntity)sql,con);
	}

	public boolean updateSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return update((LegalEntity) sql, con);
	}

	public boolean deleteSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		
		return deleteLe((LegalEntity)sql,con);
	}

	public BaseBean select(int id, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseBean select(String name, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection selectWhere(String where, Connection con) {
		// TODO Auto-generated method stub
		return selectLEOnWhereClause(where,con);
	}

	//public Collection selectALLData(Connection con) {
		// TODO Auto-generated method stub
		//return selectALLLEs(con);
//}

	public int count(String sql, Connection con) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		String sql1 = SQLCOUNT + "name =  '"+sql+"'";
		int tem=0;
		
		try {
			con.setAutoCommit(true);

			stmt = dsSQL.newPreparedStatement(con, sql1);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()){
				LegalEntity le = new LegalEntity();
			tem=rs.getInt(1);
			
			}
			return tem;
		} catch (Exception e) {
			commonUTIL.displayError("LegalEntitysqlSql", "selectLEOnWhereClause "
					+ sql1, e);

		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				commonUTIL.displayError("LegalEntitysqlSql",
						"selectLEOnWhereClause", e);
			}
		}
		return 0;

	}


//	public Collection<LegalEntity> selectALL() throws RemoteException {
		// TODO Auto-generated method stub
		//return LegalEntitySQL.selectALL(dsSQL.getConn());
	//}


	public Collection selectALLData(Connection con) {
		// TODO Auto-generated method stub
		return selectALL(con);
	}

	/*public static void main(String args[]) {
		LegalEntity boo = new LegalEntity();
	    boo.setId(106);
		boo.setName("Pppp");
		boo.setRole("xyz");
		boo.setStatus("dwd");
		boo.setAttributes("sss");
		boo.setAlias("ddd");
		boo.setCountry("india");
		boo.setContact("899");
		
		AttributeContainer att = new AttributeContainer();
		att.addAttribute("testing","pppp");
		att.addAttribute("testing1","sdfg");
		boo.setAttributeContainer(att);
		insertLe(boo, dsSQL.getConn());
	}*/
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

