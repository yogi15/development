package dbSQL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Vector;

import beans.Liquidation;
import util.commonUTIL;


public class LiquidationSQL {
	
	final static private String DELETE_FROM_liquidation =
			
			" DELETE " +
			" FROM 		liquidPos  " +
			" WHERE 	LIQUID =? ";
	
	final static private String INSERT_INTO_liquidation =
			
			" INSERT INTO liquidPos " +
			" ( liquid," +
			"	bookid," +
			"	Productid," +
			"	ftradeid," +
			"	stradeid," +
			"	liddate," +
			"	quantity," +
			"	fprice,	" +
			"	sprice,	" +
			"	positionid," +
			"	tradetype," +
			"	tradeamt, " +
			"	realisedPNL ," +
			"	currency " +
			" )	" +
			" VALUES " +
			"(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
			
	final static private String UPDATE_liquidation =
			
			" UPDATE " +
			"		liquidPos " +
			" set 	" +
			" liquid   = ?,	" +
			" bookid  = ?,	" +
			" Productid  = ?,	" +
			" ftradeid  = ?,	" +
			" stradeid = ?,	" +
			" liddate = ?,	" +
			" quantity = ?,	" +
			" fprice = ?,	" +
			" sprice  = ?,	" +
			" positionid = ?,	" +
			" realisedPNL = ?,	" +
			" tradetype  = ?	" +
			" WHERE					" +
			" LIQUID 	= ?	" ;
	
	
	
    
    static private String getUpdateSQL(Liquidation updateLiquidation) {
    	String updateSQL = " UPDATE liquidPos  set 	" +
    			" liquid   = "+ updateLiquidation.getLiquid()+"," +
    			" bookid  ="+ updateLiquidation.getBookId()+"," +
    			" Productid  ="+ updateLiquidation.getProductId()+"," +
    			" ftradeid  ="+ updateLiquidation.getfTradeId()+"," +
    			" stradeid ="+ updateLiquidation.getsTradeId()+"," +
    			" liddate =to_timestamp('" +updateLiquidation.getLidDate()+"', 'DD/MM/YYYY HH:MI:SS')," +
    			" quantity ="+ updateLiquidation.getQuantity()+"," +
    			" fprice ="+ updateLiquidation.getfPrice()+"," +
    			" sprice  ="+ updateLiquidation.getsPrice()+"," +
    			" positionid ="+ updateLiquidation.getPositionId()+"," +
    			" tradeAmt ="+ updateLiquidation.getTradeAmount()+"," +
    			" tradetype  ='"+ updateLiquidation.getTradeType()+"'" +
    			" currency  ='"+ updateLiquidation.getCurrency() +"'" +
    			" WHERE					" +
    			" LIQUID 	="+ updateLiquidation.getLiquid()  ;
    	return updateSQL;
    }
    
	
	
	final static private String SELECT_MAX =
			" SELECT 			" +
			"		MAX(LIQUID) " +
			"		DESC_ID 	" +
			" FROM 				" +
			"		liquidPos  		";
	
	final static private String SELECTALL =
			
			" SELECT " +
			" liquid," +
			" bookid," +
			" Productid," +
			" ftradeid," +
			" stradeid," +
			" liddate," +
			" quantity," +
			" fprice,	" +
			" sprice," +
			" positionid," +
			"	 tradetype, " +
			"	 tradeAmt , currency " +
			" FROM	" +
			"  liquidPos 	" +
			" ORDER BY	" +
			" LIQUID	";
	
	final static private String SELECT =
			
			" SELECT 			" +
			"		liquid 		" +
			" FROM 				" +
			"		liquidPos  " +
			" where 			" +
			"		liquid =  ?	";
	
	final static private String SELECTONE =
			
		" SELECT  liquid, bookid, Productid, ftradeid,stradeid,liddate, quantity, fprice,sprice,positionid, tradetype,tradeamt,realisedPNL,currency FROM	 liquidPos where ";	
	
	final static private String SELECTONPOSITIONID =
		
		" SELECT  liquid, bookid,Productid,ftradeid,stradeid,liddate,quantity, fprice,sprice, positionid,tradetype,tradeamt,realisedPNL,currency FROM	 liquidPos  where positionid =  ";	
	final static private String REALISED =
		
		" SELECT   sum(quantity) realised,sum(realisedPNL) realisedPNL from LIQUIDPOS where positionid =  " ;
	final static private String PNLREALISED =
			
			" SELECT   sum(realisedpnl) realisedPNL from LIQUIDPOS where positionid =  " ;
	
	final static private String UNDO =
			
			" select min(LIQUID) liqID from LIQUIDPOS where   positionid = ? and ftradeid = ? or stradeid = ?   " ;
		
		
	
	public static boolean save( Liquidation insertLiquid, Connection con ) {
		
		try {
             
			return insert( insertLiquid, con );
         
		} catch ( Exception e ) {
			
        	 commonUTIL.displayError("liquidationSQL","save",e);
        	 return false;
        	 
         }
		
	 }
	
	public static boolean update( Liquidation updateLiquidation, Connection con ) {
		 
		try {
             
			return edit( updateLiquidation, con );
         
		} catch ( Exception e ) {
			
        	 commonUTIL.displayError("liquidationSQL","update",e);
        	 
        	 return false;
        	 
         }
		
	 }
	 
	public static boolean delete( Liquidation deleteLiquidation, Connection con ) {
	
		try {
			
             return remove( deleteLiquidation, con );
         
		} catch( Exception e ) {
        	 
			commonUTIL.displayError("liquidationSQL","update",e);
        	 return false;
        	 
         }
	 
	}
	
	public static Collection selectLiquidation( int LiquidationId, Connection con ) {
		
		try {
			
             return  select( LiquidationId, con );
             
         } catch ( Exception e ) {
        	 
        	 commonUTIL.displayError("liquidationSQL","select",e);
        	 return null;
         
         }
	 }
	
	public static Collection selectALL( Connection con ) {
		
		 try {
             
			 return select( con );
			 
         } catch ( Exception e ) {
        	 
        	 commonUTIL.displayError("liquidationSQL","selectALL",e);
        	 return null;
        	 
         }
		 
	 }
	 
	public static int selectMaxID( Connection con ) {
		
		try {
             return selectMax( con ,"");
             
         } catch( Exception e ) {
        	 
        	 commonUTIL.displayError("liquidationSQL","selectMaxID",e);
        	 return 0;
        	 
         }
	 
	}
	 
	 protected static  boolean edit( Liquidation updateLiquidation, Connection con ) {
		 
		 PreparedStatement stmt = null;
		 String sql = getUpdateSQL(updateLiquidation);
		 try {
			
			 
			stmt = dsSQL.newPreparedStatement(con, sql);  
				
		 
            
        
           int i = stmt.executeUpdate(sql);
			 con.commit();
			 commonUTIL.display("liquidationSQL","edit " + sql);
			 if(i == 0)
				 return true;
			 
		 } catch (Exception e) {
			 
			 commonUTIL.displayError("liquidationSQL","edit",e);
			 return false;
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch ( SQLException e ) {
	    		 
				// TODO Auto-generated catch block
				commonUTIL.displayError("liquidationSQL","edit",e);
			
	    	 }
	        
	     }
	        return true;
	 }
	
	protected static boolean remove( Liquidation deleteLiquidation, Connection con ) {
	
		PreparedStatement stmt = null;
		
		try {
			 
			int j = 1;
			
			stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_liquidation);
	        
			stmt.setInt(j++, deleteLiquidation.getLiquid());
	           
	        stmt.executeUpdate();
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("liquidationSQL","remove",e);
			 
			 return false;
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch (SQLException e) {
				
	    		 // TODO Auto-generated catch block
				commonUTIL.displayError("liquidationSQL","remove",e);
			
	    	 }
	    	 
	     }
		
	     return true;
	 
	}

	protected static int selectMax(Connection con,String where ) {
		 
		int j = 0;
	    
		PreparedStatement stmt = null;
		
	    try {
			String sql = SELECT_MAX + where;
	    	stmt = dsSQL.newPreparedStatement(con, sql);
	         
	         ResultSet rs = stmt.executeQuery();
	        
	         while( rs.next() ) {
	        	 
	        	 j = rs.getInt("DESC_ID");
	        	 
	         }	         
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("liquidationSQL",SELECT_MAX,e);
			 return j;
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch ( SQLException e ) {
	    		 
				// TODO Auto-generated catch block
				commonUTIL.displayError("liquidationSQL",SELECT_MAX,e);
			}
	    	 
	     }
	    
	        return j;
	 }
	
	protected static int selectUNDO(Connection con,int positionID,int tradeID ) {
		 
		int j = 0;
	    
		PreparedStatement stmt = null;
		
	    try {
			
	    	stmt = dsSQL.newPreparedStatement(con, UNDO);
	        stmt.setInt(1, positionID) ;
	        stmt.setInt(2, tradeID) ;
	        stmt.setInt(3, tradeID) ;
	        
	         ResultSet rs = stmt.executeQuery();
	        
	         while( rs.next() ) {
	        	 
	        	 j = rs.getInt("liqID");
	        	 
	         }	         
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("liquidationSQL",UNDO,e);
			 return j;
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch ( SQLException e ) {
	    		 
				// TODO Auto-generated catch block
				commonUTIL.displayError("liquidationSQL",SELECT_MAX,e);
			}
	    	 
	     }
	    
	        return j;
	 }
	 
	 protected static boolean insert(Liquidation inserLiquidation, Connection con ) {
			
	        PreparedStatement stmt = null;
	        Timestamp ts =null;

		 try {
			// ts = Timestamp.valueOf(inserLiquidation.getLidDate());

			int id = selectMax(con,"");
			con.setAutoCommit(false);
			int j = 1;
			
			stmt = dsSQL.newPreparedStatement(con, INSERT_INTO_liquidation);
			stmt.setInt(1,id+1);
			
		//	stmt.setInt(1, 		inserLiquidation.getLiquid());
			stmt.setInt(2, 		inserLiquidation.getBookId());
			stmt.setInt(3, 		inserLiquidation.getProductId());
			stmt.setInt(4, 		inserLiquidation.getfTradeId());
			stmt.setInt(5, 		inserLiquidation.getsTradeId());
			stmt.setTimestamp(6, 	commonUTIL.convertStringtoSQLTimeStamp(inserLiquidation.getLidDate()));
			stmt.setDouble(7, 		inserLiquidation.getQuantity());
			stmt.setDouble(8, 	inserLiquidation.getfPrice());
			stmt.setDouble(9, 	inserLiquidation.getsPrice());
			stmt.setInt(10, 	inserLiquidation.getPositionId());
			stmt.setString(11, 	inserLiquidation.getTradeType());
			stmt.setDouble(12, 	inserLiquidation.getTradeAmount());
			stmt.setDouble(13, 	inserLiquidation.getRealisedPNL());
			stmt.setString(14, 	inserLiquidation.getCurrency());
			//stmt.setInt(1, 		inserLiquidation.getLiquid());
 
            stmt.executeUpdate();
            con.commit();
			
            commonUTIL.display("liquidationSQL",INSERT_INTO_liquidation);
		 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("liquidationSQL",INSERT_INTO_liquidation,e);
			 
			 return false;
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("liquidationSQL",INSERT_INTO_liquidation,e);
			}
	    	 
	     }
	     
		 return true;
	 }
	 
	protected static Collection select( int liquidationIn, Connection con ) {
		 
		int j = 0;
		String sql = SELECTONE + liquidationIn;
	    PreparedStatement stmt = null;
    
	    Vector liquidations = new Vector();
		
	    try {
			
	    	stmt = dsSQL.newPreparedStatement(con, sql);
			 
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Liquidation liquidation = new Liquidation();
				liquidation.setLiquid(rs.getInt(1));
				liquidation.setBookId(rs.getInt(2));
				liquidation.setProductId(rs.getInt(3));
				liquidation.setfTradeId(rs.getInt(4));
				liquidation.setsTradeId(rs.getInt(5));
				liquidation.setLidDate(commonUTIL.getTimeStampToString(rs.getTimestamp(6)));
				liquidation.setQuantity(rs.getDouble(7));
				liquidation.setfPrice(rs.getDouble(8));
				liquidation.setsPrice(rs.getDouble(9));
				liquidation.setPositionId(rs.getInt(10));
				liquidation.setTradeType(rs.getString(11));	  
				liquidation.setTradeAmount(rs.getInt(12));	 
				liquidation.setRealisedPNL(rs.getInt(13));	
				liquidation.setCurrency(rs.getString(14));	
				liquidations.add(liquidation);
	         }
			commonUTIL.display("liquidationSQL","select " + sql);
			
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("liquidationSQL","select",e);
			 return liquidations;
	           
	     } finally {
	       
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch ( SQLException e ) {
	    		 
				// TODO Auto-generated catch block
				commonUTIL.displayError("liquidationSQL","select",e);
			}
	    	 
	     }
	    
	        return liquidations;
	 }

	protected static Collection select(Connection con) { 
		
		int j = 0;
	    
		PreparedStatement stmt = null;
	    
		Vector liquidations = new Vector();
	     
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
			 ResultSet rs = stmt.executeQuery();
	      
		     while(rs.next()) {
		   
		    	Liquidation liquidation = new Liquidation();
  
		    	liquidation.setLiquid(rs.getInt(1));
				liquidation.setBookId(rs.getInt(2));
				liquidation.setProductId(rs.getInt(3));
				liquidation.setfTradeId(rs.getInt(4));
				liquidation.setsTradeId(rs.getInt(5));
				liquidation.setLidDate(commonUTIL.getTimeStampToString(rs.getTimestamp(6)));
				liquidation.setQuantity(rs.getInt(7));
				liquidation.setfPrice(rs.getDouble(8));
				liquidation.setsPrice(rs.getDouble(9));
				liquidation.setPositionId(rs.getInt(10));
				liquidation.setTradeType(rs.getString(11));	          
				liquidation.setTradeAmount(rs.getInt(12));	 
				liquidation.setRealisedPNL(rs.getInt(13));	
				liquidation.setCurrency(rs.getString(14));	
		    	liquidations.add(liquidation);
		      
		      }
		     
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("liquidationSQL",SELECTALL,e);
			 return liquidations;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
			
	        } catch ( SQLException e ) {
				
	        	// TODO Auto-generated catch block
				commonUTIL.displayError("liquidationSQL",SELECTALL,e);
			
	        }
	        
	     }
		 
	     return liquidations;
	 }
	
	protected static Collection selectliquidation(int liquidationId,Connection con ) {
		int j = 0;
	    
		PreparedStatement stmt = null;
	    
		Vector liquidations = new Vector();
	     
		 try {
			 
			 String sql = SELECTONE + liquidationId;
			 
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
			 while(rs.next()) {
				 
		    	Liquidation liquidation = new Liquidation();
		    	  
		    	liquidation.setLiquid(rs.getInt(1));
				liquidation.setBookId(rs.getInt(2));
				liquidation.setProductId(rs.getInt(3));
				liquidation.setfTradeId(rs.getInt(4));
				liquidation.setsTradeId(rs.getInt(5));
				liquidation.setLidDate(commonUTIL.getTimeStampToString(rs.getTimestamp(6)));
				liquidation.setQuantity(rs.getInt(7));
				liquidation.setfPrice(rs.getDouble(8));
				liquidation.setsPrice(rs.getDouble(9));
				liquidation.setPositionId(rs.getInt(10));
				liquidation.setTradeType(rs.getString(11));	 	          
				liquidation.setTradeAmount(rs.getInt(12));	 
				liquidation.setRealisedPNL(rs.getInt(13));	
				liquidation.setCurrency(rs.getString(14));	
		    	liquidations.add(liquidation);
	      
			 }
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("liquidationSQL","selectliquidation",e);
			 return liquidations;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("liquidationSQL","selectMax",e);
				
			}
	     }
		 
	     return liquidations;
	 }

	public static Liquidation getRealisedPosition(int posID,Connection con) {
		// TODO Auto-generated method stub
int j = 0;
	    
		PreparedStatement stmt = null;
	    
		double realised = 0;
	     Liquidation liqRealised = new Liquidation();
		 try {
			 
			 String sql = REALISED + posID;
			 
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
			 while(rs.next()) {
				 
				 liqRealised.setQuantity(rs.getDouble(1));
				 liqRealised.setRealisedPNL(rs.getDouble(2));
			 }
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("liquidationSQL","getRealisedPosition",e);
			 return liqRealised;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("liquidationSQL","getRealisedPosition",e);
				
			}
	     }
		 
	     return liqRealised;
	}
	public static double getPNLRealised(int posID,Connection con) {
		// TODO Auto-generated method stub
int j = 0;
	    
		PreparedStatement stmt = null;
	    
		double realised = 0;
	     Liquidation liqRealised = new Liquidation();
		 try {
			 
			 String sql = PNLREALISED + posID;
			 
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
			 while(rs.next()) {
				 
				
				 realised = rs.getDouble(1);
			 }
			 commonUTIL.display("liquidationSQL"," getPNLRealised " + sql);
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("liquidationSQL","getPNLRealised",e);
			 return realised;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("liquidationSQL","getPNLRealised",e);
				
			}
	     }
		 
	     return realised;
	}
	public static Collection selectOnWhere(String where,Connection con) {
		// TODO Auto-generated method stub
        int j = 0;
	    
		PreparedStatement stmt = null;
	    
		Vector liquidations = new Vector();
	     
		 try {
			 
			 String sql = SELECTONE + where;
			 
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
			 while(rs.next()) {
				 
		    	Liquidation liquidation = new Liquidation();
		    	  
		    	liquidation.setLiquid(rs.getInt(1));
				liquidation.setBookId(rs.getInt(2));
				liquidation.setProductId(rs.getInt(3));
				liquidation.setfTradeId(rs.getInt(4));
				liquidation.setsTradeId(rs.getInt(5));
				liquidation.setLidDate(commonUTIL.getTimeStampToString(rs.getTimestamp(6)));
				liquidation.setQuantity(rs.getInt(7));
				liquidation.setfPrice(rs.getDouble(8));
				liquidation.setsPrice(rs.getDouble(9));
				liquidation.setPositionId(rs.getInt(10));
				liquidation.setTradeType(rs.getString(11));	 	          
				liquidation.setTradeAmount(rs.getInt(12));	 
				liquidation.setRealisedPNL(rs.getInt(13));	
				liquidation.setCurrency(rs.getString(14));	
		    	liquidations.add(liquidation);
	      
			 }
			 commonUTIL.display("liquidationSQL","selectOnWhere "+sql); 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("liquidationSQL","selectOnWhere",e);
			 return liquidations;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("liquidationSQL","selectOnWhere",e);
				
			}
	     }
	     return liquidations;
	}

	public static Vector getLiqPositionForUndo(Connection con,int positionId, int id) {
		// TODO Auto-generated method stub
		
		int fromid = selectUNDO(con, positionId, id);
		if(fromid == 0) 
			return null;
		String where = " where positionid = " + positionId;
		int maxid = selectMax(con,where);
		where = " LIQUID between " + fromid + " and " +   maxid;
	return (Vector) selectOnWhere(where, con);
		
		
	}

	public static void removeLiqPos(String whereClause, Connection conn) {
		// TODO Auto-generated method stub
PreparedStatement stmt = null;
		
		try {
			conn.setAutoCommit(false);
			String sql = "delete from 	liquidPos   WHERE   " + whereClause;
			 
			int j = 1;
			
			stmt = dsSQL.newPreparedStatement(conn, sql);
	        
		
	           
	        stmt.executeUpdate();
	        conn.commit();
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("liquidationSQL","removeLiqPos",e);
			 
			
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch (SQLException e) {
				
	    		 // TODO Auto-generated catch block
				commonUTIL.displayError("liquidationSQL","removeLiqPos",e);
			
	    	 }
	    	 
	     }
		
	    
	}

	public static void save(Vector<Liquidation> collectLiqudiation,
			Connection conn) {
		try {
			  if(collectLiqudiation !=null && collectLiqudiation.size() > 0) {
				  for(int i=0;i<collectLiqudiation.size();i++) {
					  Liquidation liq =  collectLiqudiation.elementAt(i);
					  save(liq, conn);
				  }
				  commonUTIL.display("liquidationSQL","Batch  save Done");
				  
			  }
				}catch(Exception e) {
					commonUTIL.displayError("liquidationSQL","Batch  save", e);
				}
				
		
	}

	public static Collection selectLiquidationOnPosition(int positionID,
			Connection conn) {
		// TODO Auto-generated method stub
int j = 0;
	    
		PreparedStatement stmt = null;
	    
		Vector liquidations = new Vector();
	     
		 try {
			 
			 String sql = SELECTONPOSITIONID + positionID;
			 
			 stmt = dsSQL.newPreparedStatement(conn, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
			 while(rs.next()) {
				 
		    	Liquidation liquidation = new Liquidation();
		    	  
		    	liquidation.setLiquid(rs.getInt(1));
				liquidation.setBookId(rs.getInt(2));
				liquidation.setProductId(rs.getInt(3));
				liquidation.setfTradeId(rs.getInt(4));
				liquidation.setsTradeId(rs.getInt(5));
				liquidation.setLidDate(commonUTIL.getTimeStampToString(rs.getTimestamp(6)));
				liquidation.setQuantity(rs.getInt(7));
				liquidation.setfPrice(rs.getDouble(8));
				liquidation.setsPrice(rs.getDouble(9));
				liquidation.setPositionId(rs.getInt(10));
				liquidation.setTradeType(rs.getString(11));	 	          
				liquidation.setTradeAmount(rs.getInt(12));	 
				liquidation.setRealisedPNL(rs.getInt(13));	
				liquidation.setCurrency(rs.getString(14));	
		    	liquidations.add(liquidation);
	      
			 }
			 commonUTIL.display("liquidationSQL","selectLiquidationOnPosition "+sql); 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("liquidationSQL","selectLiquidationOnPosition",e);
			 return liquidations;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("liquidationSQL","selectOnWhere",e);
				
			}
	     }
	     return liquidations;
	}
	 
}
