package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import constants.BeanConstants;
import beans.BaseBean;
import beans.CurrencyDefault;
import beans.CurrencyPair;
import beans.LegalEntity;
import util.commonUTIL;

public class CurrencyPairSQL extends BaseSQL  {
	
	
//	select primary_currency,quoting_currency,quote_factor,bp_factor,rounding,pair_pos_ref_b,spot_days from CURRENCYPAIR

	
	
	final static private String DELETE_FROM_CURRENCYPAIR =
	"DELETE FROM CURRENCYPAIR where primary_currency=?";
final static private String INSERT =
	"INSERT into CURRENCYPAIR(primary_currency,quoting_currency,rounding,pairname,spot_days) values(?,?,?,?,?)";
final static private String UPDATE =
	"UPDATE CURRENCYPAIR set quoting_currency=?,rounding=?,pairname=?,spot_days=? where primary_currency= ? ";
final static private String SELECT_MAX =
	"SELECT MAX(CurrencyPairno) DESC_ID FROM CURRENCYPAIR ";
final static private String SELECTALL =
	"select primary_currency,quoting_currency,rounding,pairname,spot_days from CURRENCYPAIR ";
final static private String SELECT =
	"select primary_currency,quoting_currency,pairname,rounding,spot_days from CURRENCYPAIR where quoting_currency =  ?";
 static private String SELECTWHERE =
	"select primary_currency,quoting_currency,pairname,rounding,spot_days from CURRENCYPAIR  where primary_currency=?" ;
 
 final static private String selectKeyColumns = "select primary_currency,quoting_currency order by primary_currency";
 

 final static private String SQLCOUNT = new StringBuffer(
			"SELECT count(*) countRows ").append(" FROM CURRENCYPAIR where  ")
			.toString();
 

	private static String getUpdateCurrencyPairSQL(CurrencyPair c) {
		 String updateSQL = new StringBuffer("UPDATE CURRENCYPAIR  set ")
					
					.append("quoting_currency='").append(c.getQuoting_currency())
					.append("',rounding =").append(c.getRounding()) 
					.append(",pairname='").append(c.getPairName())
					.append("',spot_days=").append(c.getSpot_days())
					 .append("where primary_currency='").append(c.getPrimary_currency()+"'").toString() ;
		return updateSQL;
	}
		/*String updateSQL = "UPDATE c  set " 
				+"'quoting_currency='"+c.getQuoting_currency() 
				+"',rounding='"+ c.getRounding()
				+"',pairname='" +c.getPairName()
				+"',spot_days='"+c.getSpot_days() 
			     +"'where primary_currency=" + c.getPrimary_currency();
		
		return updateSQL;*/
		
	
 
 
 public static CurrencyPair save(CurrencyPair insertCurrencyPair, Connection con) {
	 try {
         return (CurrencyPair) insert(insertCurrencyPair, con);
     }catch(Exception e) {
    	 commonUTIL.displayError("CurrencyPairSQL","save",e);
    	 return null;
     }
 }
 public static boolean update(CurrencyPair updateCurrencyPair, Connection con) {
	 try {
         return edit(updateCurrencyPair, con);
     }catch(Exception e) {
    	 commonUTIL.displayError("CurrencyPairSQL","update",e);
    	 return false;
     }
 }
 
 public static boolean delete(CurrencyPair deleteCurrencyPair, Connection con) {
	 try {
         return remove(deleteCurrencyPair, con);
     }catch(Exception e) {
    	 commonUTIL.displayError("CurrencyPairSQL","update",e);
    	 return false;
     }
 }
 public static Collection selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("CurrencyPairSQL", "select", e);
			return null;
		}
	}
 
 public static int selectMaxID(Connection con) {
	 try {
         return selectMax(con);
     }catch(Exception e) {
    	 commonUTIL.displayError("CurrencyPairSQL","selectMaxID",e);
    	 return 0;
     }
 }
 
 protected static  boolean edit(CurrencyPair updatecurrencyPair, Connection con ) {
	 
	 PreparedStatement stmt = null;
	 String sql = "";
	 
	 try {
		 sql = getUpdateCurrencyPairSQL(updatecurrencyPair);
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, sql); 
         stmt.executeUpdate(sql);
		 con.commit();
		 
		 commonUTIL.display("CurrencyPairSQL: edit:", sql);
		 
	 } catch (Exception e) {
		 commonUTIL.displayError("CurrencyPairSQL: insert: "+e.getMessage(),sql,e);
		 return false;
           
     } finally {
    	 try {
			stmt.close();
         } catch (SQLException e) {					
        	 commonUTIL.displayError("CurrencyPairSQL: insert: ", sql,e);
         }
     }
    return true;
 }


 protected static boolean remove(CurrencyPair deletecurrencyPair, Connection con ) {
		
		PreparedStatement stmt = null;
		try {
			 
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_CURRENCYPAIR);
	        stmt.setString(1, deletecurrencyPair.getPrimary_currency());
	           
	        stmt.executeUpdate();
			con.commit();
			
			commonUTIL.display("CurrencyPairSQL: remove:", DELETE_FROM_CURRENCYPAIR);
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("CurrencyPairSQL: remove: ", DELETE_FROM_CURRENCYPAIR,e);
			 return false;
	    } finally {
	    	try {
				stmt.close();
			} catch (SQLException e) {					
				commonUTIL.displayError("CurrencyPairSQL: insert: ", DELETE_FROM_CURRENCYPAIR,e);
			}
     }
     return true;
	 }


protected static int selectMax(Connection con ) {
	 
	 int j = 0;
        PreparedStatement stmt = null;
	 try {
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);
         
         ResultSet rs = stmt.executeQuery();
         while(rs.next())
         j = rs.getInt("DESC_ID");
		 
	 } catch (Exception e) {
		 commonUTIL.displayError("CurrencyPairSQL",SELECT_MAX,e);
		 return j;
           
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("CurrencyPairSQL",SELECT_MAX,e);
		}
        }
        return j;
 }
 
 protected static BaseBean insert(CurrencyPair inserCurrencyPair, Connection con ) {
		PreparedStatement stmt = null;
	
	 try {
		 con.setAutoCommit(false);
		   int j = 1;
			stmt = dsSQL.newPreparedStatement(con, INSERT);
		 con.setAutoCommit(false);
	//	 int id = selectMax(con);
				 
		 stmt.setString(1,inserCurrencyPair.getPrimary_currency());
		 stmt.setString(2,inserCurrencyPair.getQuoting_currency());
         stmt.setInt(3,inserCurrencyPair.getRounding());
		 stmt.setString(4,inserCurrencyPair.getPairName());
		 stmt.setInt(5,inserCurrencyPair.getSpot_days());
          
           
            stmt.executeUpdate();
            con.commit();
	 } catch (Exception e) {
		 commonUTIL.displayError("CurrencyPairSQL",INSERT,e);
		 return null;
           
        }
        finally {
           try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("CurrencyPairSQL",INSERT,e);
		}
        }
        return null;
 }
 protected static CurrencyPair saveNew(CurrencyPair inserCurrencyPair, Connection con) {

		PreparedStatement stmt = null;
		//int version_num = 0;
		try {
			con.setAutoCommit(false);
			//version_num = selectMax(con) +1;
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, INSERT);
					
			stmt.setString(j++, inserCurrencyPair.getPrimary_currency());
			 stmt.setString(j++, inserCurrencyPair.getQuoting_currency());
	         stmt.setInt(j++, inserCurrencyPair.getRounding());
	         stmt.setString(j++,inserCurrencyPair.getPairName() );
	         stmt.setInt(j++,inserCurrencyPair.getSpot_days() );
		
			
			
			stmt.executeUpdate();
			con.commit();
			commonUTIL.display("CurrencyPairSQL", "insert" + INSERT);
		
			return inserCurrencyPair;
		} catch (Exception e) {
			commonUTIL.displayError("CurrencyPairSQL", "insert",  e);
			return null;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("CurrencyPairSQL", "insert", e);
			}
		} 
	}
 
 protected static Collection select(Connection con) { 
	 int j = 0;
     PreparedStatement stmt = null;
     Vector currencyPair = new Vector();
     
	 try {
		 con.setAutoCommit(false);
		 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
      
      ResultSet rs = stmt.executeQuery();
      
      while(rs.next()) {
   
     CurrencyPair CurrencyPair = new CurrencyPair();
     CurrencyPair.setPrimary_currency(rs.getString(1));
     CurrencyPair.setQuoting_currency(rs.getString(2));
       
     CurrencyPair.setRounding(rs.getInt(3));
     CurrencyPair.setPairName(rs.getString(4));
     CurrencyPair.setSpot_days(rs.getInt(5));
     currencyPair.add(CurrencyPair);
      
      }
	 } catch (Exception e) {
		 commonUTIL.displayError("CurrencyPairSQL",SELECTALL,e);
		 return currencyPair;
        
     }
     finally {
        try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("CurrencyPairSQL",SELECTALL,e);
		}
     }
     return currencyPair;
 }
 protected static Collection<CurrencyPair> select1 (Connection con) {
	 PreparedStatement stmt = null;
     Vector<CurrencyPair> cdv = new Vector<CurrencyPair>();
     
	 try {

	  stmt = dsSQL.newPreparedStatement(con, SELECTALL);		      
      ResultSet rs = stmt.executeQuery();
      
      while(rs.next()) {
    	  CurrencyPair cp = new CurrencyPair();
    	  cp.setPrimary_currency(rs.getString(1));
    	  cp.setQuoting_currency(rs.getString(2));
          cp.setRounding(rs.getInt(5));
    	  cp.setPairName(rs.getString(6));
    	  cp.setSpot_days(rs.getInt(7));
    	  cdv.add(cp);
      
      }
      commonUTIL.display("CurrencyPairSQL","selectCurrencyPair " + SELECTALL);
	 } catch (Exception e) {
		 commonUTIL.displayError("CurrencyPairSQL","selectCurrencyPair " + SELECTALL,e);
		 
		 return cdv;
        
     }
     finally {
        try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("CurrencyPairSQL","selectCurrencyPair" + SELECTALL ,e);
		}
     }
     return cdv;
 }
 
 public static Collection selectLEOnWhereClause(String sql, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector CurrencyPair = new Vector();
		String sql1 = SELECTWHERE +  "primary_currency = '"+ sql+"'";
		try {

			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, sql1);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				CurrencyPair cp = new CurrencyPair();

				cp.setPrimary_currency(rs.getString(1));
		    	  cp.setQuoting_currency(rs.getString(2));
		          cp.setRounding(rs.getInt(5));
		    	  cp.setPairName(rs.getString(6));
		    	  cp.setSpot_days(rs.getInt(7));
		    	  CurrencyPair.add(cp);
		      

			}
			commonUTIL.display("CurrencyPairSQL", sql1);
		} catch (Exception e) {
			commonUTIL.displayError("CurrencyPairSQL", sql1, e);
			return CurrencyPair;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("LegalEntitySQL", sql1, e);
			}
		}
		return CurrencyPair;
	}
 

	public BaseBean insertSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return  (BaseBean) save((CurrencyPair)sql,con);
	}

	public boolean updateSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return update((CurrencyPair) sql, con);
	}

	public boolean deleteSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		
		return delete((CurrencyPair)sql,con);
	}

 
	public Collection selectALLData(Connection con) {
		// TODO Auto-generated method stub
		return select(con);

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
	public int count(String sql, Connection con) {
		// TODO Auto-generated method stub
				PreparedStatement stmt = null;
				String sql1 = SQLCOUNT + "primary_currency = '"+sql+"'";
				int tem=0;
				
				try {
					con.setAutoCommit(true);

					stmt = dsSQL.newPreparedStatement(con, sql1);

					ResultSet rs = stmt.executeQuery();
					if (rs.next()){
						CurrencyPair le = new CurrencyPair();
					tem=rs.getInt(1);
					
					}
					return tem;
				} catch (Exception e) {
					commonUTIL.displayError("CurrencyPairSQL", "selectLEOnWhereClause "
							+ sql1, e);

				} finally {
					try {
						stmt.close();
						// con.close();
					} catch (SQLException e) {
						commonUTIL.displayError("CurrencyPairSQL",
								"selectLEOnWhereClause", e);
					}
				}
		// TODO Auto-generated method stub
		return 0;
	}


   public static Vector< CurrencyPair> selectALLCurrencyPairWithConcat( Connection con) {
	   String sql = " select primary_currency || '/' || quoting_currency primary_currency from currencypair order by primary_currency desc";
	   PreparedStatement stmt = null;
		Vector< CurrencyPair> currencyPair = new Vector< CurrencyPair>();
	   try {

			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				CurrencyPair cp = new CurrencyPair();

				cp.setPrimary_currency(rs.getString(1));
		    	  
				currencyPair.add(cp);
		      

			}
			commonUTIL.display("CurrencyPairSQL", "selectALLCurrencyPairWithConcat " + sql);
			return currencyPair;
		} catch (Exception e) {
			commonUTIL.displayError("CurrencyPairSQL", sql, e);
			return currencyPair;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("CurrencyPairSQL ","selectALLCurrencyPairWithConcat " +  sql, e);
			}
		}
	   
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
