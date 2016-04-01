package dbSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.MenuConfiguration;
import util.commonUTIL;

public class MenuConfigSQL {	
	final static private String DELETE_FROM_MenuConfiguration = "DELETE FROM MenuConfig  where id  =? ";

final static private String INSERT_FROM_MenuConfiguration = "INSERT into MenuConfig(WindowName,ParentName,ChildName,Height,width,isParent,MAINMENUNAME,id) values(?,?,?,?,?,?,?,?)";

final static private String UPDATE_FROM_MenuConfiguration = "UPDATE MenuConfig set WindowName=?,ParentName=? ChildName= ?,Height=? where Width=? ";

final static private String SELECTALL = "SELECT   WindowName, ParentName,ChildName,Height,Width,isParent,MAINMENUNAME,id FROM MenuConfig order by id";
final static private String SELECT_MAX =
"SELECT MAX(id) SerialNo FROM MenuConfig ";
static private String SELECTWHERE = "SELECT  WindowName, ParentName,ChildName,Height,Width,isParent,MAINMENUNAME, id  FROM MenuConfig where   ";

private static String getUpdateSQL(MenuConfiguration menuConfiguration) {
	String updateSQL = "UPDATE MenuConfig  set " + " MAINMENUNAME ='"+menuConfiguration.getMainMenuName().trim()+"', WindowName= '"
			+ menuConfiguration.getWindowName() + "',Height= "
			+ menuConfiguration.getHeight() + ",  ChildName= '"
			+ menuConfiguration.getChildMenuName()+ "',Width= "
			+menuConfiguration.getWidth()+ ",ParentName='"
			+menuConfiguration.getParentMenuName() + "'";
			if(menuConfiguration.isParentChild()) {
				updateSQL = updateSQL  + ", isParent=1";
			} else {
				updateSQL = updateSQL  + ", isParent=0";
			}
	 
		 
			updateSQL = updateSQL  +" where  id = "+menuConfiguration.getID()+"";
	return updateSQL;
}

public static boolean update(MenuConfiguration updateObj, Connection con) {

	try {
		return edit(updateObj, con);
	} catch (Exception e) {
		commonUTIL.displayError("MenuConfiguration", "update", e);
		return false;
	}
}

protected static boolean edit(MenuConfiguration updateObj, Connection con) {

	PreparedStatement stmt = null;
	String sql = getUpdateSQL(updateObj);
	try {
		con.setAutoCommit(false);
		int j = 1;
		stmt = dsSQL.newPreparedStatement(con, sql);
		stmt.executeUpdate(sql);
		con.commit();
		commonUTIL.display("MenuConfiguration ::  edit", sql);
	} catch (Exception e) {
		commonUTIL.displayError("MenuConfiguration", "edit", e);
		return false;

	} finally {
		try {
			stmt.close();
		} catch (SQLException e) {
			commonUTIL.displayError("MenuConfiguration", "edit", e);
		}
	}
	return true;
}

public static MenuConfiguration save(MenuConfiguration insert, Connection con) {
	try {
		return insert(insert, con);
	} catch (Exception e) {
		commonUTIL.displayError("(MenuConfigurationSQL", "save", e);
		return null;
	}
}

protected static int selectMax(Connection con ) {
	 
	 int j = 0;
       PreparedStatement stmt = null;
	 try {
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);
        
        ResultSet rs = stmt.executeQuery();
        while(rs.next())
        j = rs.getInt("SerialNo");
		 
	 } catch (Exception e) {
		 commonUTIL.displayError("MenuConfigurationSQL",SELECT_MAX,e);
		 return j;
          
       }
       finally {
          try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("MenuConfigurationSQL",SELECT_MAX,e);
		}
       }
       return j;
}

public static Vector<MenuConfiguration> selectALL(Connection con) {
	try {
		return (Vector<MenuConfiguration>) select(con);
	} catch (Exception e) {
		commonUTIL.displayError("(MenuConfiguration", "selectALL", e);
		return null;
	}
}

private static MenuConfiguration insert(MenuConfiguration insert, Connection con) {
	PreparedStatement stmt = null;
	int id = selectMax(con)+1;
	try {
		con.setAutoCommit(false);

		stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_MenuConfiguration);
		stmt.setString(1, insert.getWindowName());
		stmt.setString(2, insert.getParentMenuName());
		stmt.setString(3, insert.getChildMenuName());
		stmt.setInt(4, insert.getHeight());
		stmt.setInt(5, insert.getWidth());
		if(insert.isParentChild())
		stmt.setInt(6, 1);
		else 
			stmt.setInt(6, 0);
		stmt.setString(7, insert.getMainMenuName());

		stmt.setInt(8,id);
		 stmt.executeUpdate();
		con.commit();
		insert.setID(id);
		
		commonUTIL.display("MenuConfiguration", INSERT_FROM_MenuConfiguration);
		return insert;
	} catch (Exception e) {
		commonUTIL.displayError("MenuConfiguration", INSERT_FROM_MenuConfiguration, e);
		return null;
	} finally {
		try {
			stmt.close();
		} catch (SQLException e) {
			commonUTIL.displayError("MenuConfiguration",
					INSERT_FROM_MenuConfiguration, e);
		}
	}

}

protected static Vector<MenuConfiguration> select(Connection con) {
	PreparedStatement stmt = null;
	Vector<MenuConfiguration> jTableData = new Vector<MenuConfiguration>();
	try {
		con.setAutoCommit(false);
		stmt = dsSQL.newPreparedStatement(con, SELECTALL);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			MenuConfiguration data = new MenuConfiguration();

			data.setWindowName(rs.getString(1));
			data.setParentMenuName(rs.getString(2));
			data.setChildMenuName(rs.getString(3));
			data.setHeight(rs.getInt(4));
			data.setWidth(rs.getInt(5));
			if(rs.getInt(6) == 1)
			data.setIsParentChild(true);
			else 
				data.setIsParentChild(false);
			data.setMainMenuName( rs.getString(7));
			jTableData.add(data);
		}
		return jTableData;
	} catch (Exception e) {
		commonUTIL.displayError("MenuConfiguration", SELECTALL, e);
		return null;
	} finally {
		try {
			stmt.close();
		} catch (SQLException e) {
			commonUTIL.displayError("MenuConfiguration", SELECTALL, e);
		}
	}

}

public static boolean delete(MenuConfiguration deleteObj, Connection con) {

	PreparedStatement stmt = null;
	try {
		con.setAutoCommit(false);
		int j = 1;
		stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_MenuConfiguration);
		stmt.setInt(1,deleteObj.getID() );
		stmt.executeUpdate();
		con.commit();
		commonUTIL.display("MenuConfiguration ::  delete", DELETE_FROM_MenuConfiguration);
	} catch (Exception e) {
		commonUTIL.displayError("MenuConfiguration", "delete", e);
		return false;

	} finally {
		try {
			stmt.close();
		} catch (SQLException e) {
			commonUTIL.displayError("MenuConfiguration", "delete", e);
		}
	}
	return true;
}

	

}
