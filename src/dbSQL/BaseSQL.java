package dbSQL;

import java.sql.Connection;
import java.util.Collection;

import beans.BaseBean;

public interface BaseSQL {
          
	  public BaseBean insertSQL(String sql,Connection con);
	  public boolean updateSQL(String sql,Connection con);
	  public boolean deleteSQL(String sql,Connection con);

	  public BaseBean insertSQL(BaseBean sql,Connection con);
	  public boolean updateSQL(BaseBean sql,Connection con);
	  public boolean deleteSQL(BaseBean sql,Connection con);
	  public BaseBean select(int id,Connection con);
	  public BaseBean select(String name,Connection con);
	  public Collection selectWhere(String where,Connection con);
	  public Collection selectALLData(Connection con);
	  
}
