package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.CashPosition;

public class CashPositionSQL {
final static private String DELETE_FROM_openpos =
			
			" DELETE 				" +
			" FROM 		cashposition 	" +
			" WHERE 	TRADEID = ? ";

final static private String GETRealisedOutStandingAmt = 
" select getRealisedOnFirstLeg(id),getRealisedOnSecondLeg(id) from trade where id = " ;

	final static private String INSERT_INTO_openpos =
			
			" INSERT INTO cashposition" +
			" ( tradeid," +
			"	productid," +
			"	settledate," +
			"	tradedate," +
			"	quantity," +
			"	openquantity," +
			"	bookid," +
			"	price," +
			"	type," +
			"	positionid,	" +
			"	openpositionDate," +
			"	producttype," +
			"	productsubtype,	" +
			"	openNominal,	" +
			"	quotingAmt,	" +
			"	tradeAmt,	" +
			"	productFV,	" +
			"	originalTradeAmt,	" +			
			"	tradedesc1," +
			"	fxSwapLegType," +
			"	tradedesc,primaryCurr,QuotingCurr,Currency,actualAmt,cpid,leg," +
			"    id" +
			" )" +
			" VALUES" +
			"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
			
	final static private String distinctProductType = "  select sum(quotingamt) quotingCurr ,sum(openNominal) PrimaryCurr, productsubtype,  concat(concat(productsubtype,'_'),settledate) productsubtype1 from cashposition ";
	
	
    private static String getUpdateSQL( CashPosition updateOpenpos ) {
		String updateOpenPos = " UPDATE  cashposition  set 	" +
				" tradeid ="+ updateOpenpos.getTradeId()+"," +
				" productid ="+ updateOpenpos.getProductId()+"," +
				" settledate =to_date('"+ updateOpenpos.getSettleDate()+"', 'DD/MM/YYYY hh24:mi:ss')," +
				" tradedate  =to_date('"+ updateOpenpos.getTradeDate()+"', 'DD/MM/YYYY hh24:mi:ss')," +
				" quantity  ="+ updateOpenpos.getQuantity()+"," +
				" openquantity  ="+ updateOpenpos.getOpenQuantity()+"," +
				" bookid  ="+ updateOpenpos.getBookId()+"," +
				" price	 ="+ updateOpenpos.getPrice()+"," +
				" type   ='"+ updateOpenpos.getType()+"'," +
				" positionid ="+ updateOpenpos.getPositionId()+"," +
				" openpositionDate ='"+ updateOpenpos.getOpenpositionDate()+"'," +
				" producttype ='"+ updateOpenpos.getProductType()+"'," +
				" productsubtype ='"+ updateOpenpos.getProductSubType() +"', " +
				" openNominal ="+ updateOpenpos.getOpenNominal() +", " +
				" quotingAmt ="+ updateOpenpos.getQuotingAmt() +", " +
				" tradeAmt ="+ updateOpenpos.getTradeAmt() +", " +
				" productFV ="+ updateOpenpos.getProductFV() +", " +
				" originalTradeAmt ="+ updateOpenpos.getOriginalTradeAmt() +", " +
				" id ="+ updateOpenpos.getId() +", " +
				" tradedesc1 ='"+ updateOpenpos.getTradedesc1() +"', " +
				" fxSwaplegType ='"+ updateOpenpos.getFxSwapLegType() +"', " +
				" tradedesc ='"+ updateOpenpos.getTradedesc() +"', " +
				" primaryCurr ='"+ updateOpenpos.getPrimaryCurr().trim() +"', " +
				" quotingCurr ='"+ updateOpenpos.getQuotingCurr().trim() +"', " +
				" currency ='"+ updateOpenpos.getCurrency().trim() +"', " +
				" actualAmt ="+ updateOpenpos.getActualAmt() +", " +
				" leg ="+ updateOpenpos.getLeg() +" ," +
				" amount1out ="+ updateOpenpos.getOut1amount() +", " +
				" amount2out ="+ updateOpenpos.getOut2amount() +" " +
				" WHERE	";
				if(commonUTIL.isEmpty(updateOpenpos.getFxSwapLegType()))
					updateOpenPos = updateOpenPos +  " TRADEID  = "+ updateOpenpos.getTradeId() + " and leg = "+updateOpenpos.getLeg();
				else 
					updateOpenPos = updateOpenPos + " TRADEID  = "+ updateOpenpos.getTradeId() +  " and leg = "+updateOpenpos.getLeg() + " and  fxSwapLegType='"+updateOpenpos.getFxSwapLegType().trim()+"'";
		return updateOpenPos;
	}
	
	
	final static private String SELECT_MAX =
			
			" SELECT 			" +
			"		MAX(LIQUID) " +
			"		DESC_ID 	" +
			" FROM 				" +
			"		LIQUID 		";
	
	final static private String SELECTALL =
			
			 " SELECT  " +
						" tradeid," +
						"  productid," +
						"  settledate," +
						" tradedate," +
						" quantity," +
						" openquantity," +
						" bookid," +
						" price," +
						" positionid," +
						" openpositionDate," +
						" producttype," +
						" productsubtype,openNominal,quotingAmt,tradeAmt,productFV,originalTradeAmt,id,tradedesc1,fxSwapLegType,tradedesc,primaryCurr,QuotingCurr,currency,actualAmt,leg,amount1out,amount2out " +
						" FROM  " +
			"		cashposition "; 
			
	
	final static private String SELECTONKEYS =
			

		" SELECT  " +
			" tradeid," +
			"  productid," +
			"  settledate," +
			" tradedate," +
			" quantity," +
			" openquantity," +
			" bookid," +
			" price," +
			" sign," +
			" positionid," +
			" openpositionDate," +
			" producttype," +
			" productsubtype,openNominal,quotingAmt,tradeAmt,productFV,originalTradeAmt,id,tradedesc1,fxSwapLegType,tradedesc,primaryCurr,QuotingCurr,actualAmt  " +
			" FROM  " +
			"		cashposition " +
			" where " +
			"  ";
	final static private String UNREALISED =
		

		" SELECT  " +
			" tradeid," +
			"  productid," +
			"  settledate," +
			" tradedate," +
			" quantity," +
			" openquantity," +
			" bookid," +
			" price," +
			" sign," +
			" positionid," +
			" openpositionDate," +
			" producttype," +
			" productsubtype,openNominal,quotingAmt,tradeAmt,productFV,originalTradeAmt,id,tradedesc1,fxSwapLegType,tradedesc,primaryCurr,QuotingCurr,actualAmt   " +
			" FROM  " +
			"		cashposition " +
			" where positionid = " +
			"  ";
	
	final static private String SELECTONTRADEID =
			
			" SELECT " +
			" tradeid," +
			"  productid," +
			"  settledate," +
			" tradedate," +
			" quantity," +
			" openquantity," +
			" bookid," +
			" price," +
			" sign," +
			" positionid," +
			" openpositionDate," +
			" producttype," +
			" productsubtype,openNominal,quotingAmt,tradeAmt,productFV,originalTradeAmt,id,tradedesc1,fxSwapLegType,tradedesc,primaryCurr,QuotingCurr,actualAmt   " +
			" FROM  " +
			"		cashposition " +
			" where " +
			"		tradeid = 	";
	
	static private String SELECTONPOSITIONID =
		
		" SELECT " +
		" tradeid," +
		"  productid," +
		"  settledate," +
		" tradedate," +
		" quantity," +
		" openquantity," +
		" bookid," +
		" price," +
		" sign," +
		" positionid," +
		" openpositionDate," +
		" producttype," +
		" productsubtype,openNominal,quotingAmt,tradeAmt,productFV,originalTradeAmt,id,tradedesc1,fxSwapLegType,tradedesc,primaryCurr,QuotingCurr ,actualAmt  " +
		" FROM  " +
		"		cashposition " +
		" where " +
		"		positionid = 	";
	
   static public String WACSQL = " select round(sum(originaltradeamt)/sum(quantity),2) wacRate from cashposition where positionid  = ";
	
	public static boolean save( CashPosition insertLiquid, Connection con ) {
		
		try {
             
			return insert( insertLiquid, con );
         
		} catch ( Exception e ) {
			
        	 commonUTIL.displayError("cashPositionSQL","save",e);
        	 return false;
        	 
         }
		
	 }
	
	public static boolean update( CashPosition updateOpenpos, Connection con ) {
		 
		try {
             
			return edit( updateOpenpos, con );
         
		} catch ( Exception e ) {
			
        	 commonUTIL.displayError("cashPositionSQL","update",e);
        	 
        	 return false;
        	 
         }
		
	 }
	 
	public static boolean delete( CashPosition deleteOpenpos, Connection con ) {
	
		try {
			
             return remove( deleteOpenpos, con );
         
		} catch( Exception e ) {
        	 
			commonUTIL.displayError("cashPositionSQL","update",e);
        	 return false;
        	 
         }
	 
	}
	
	public static CashPosition selectOpenposOnTradeID( int tradeID, Connection con ) {
		
		try {
			
             return  selectONTradeid(tradeID, con);
             
         } catch ( Exception e ) {
        	 
        	 commonUTIL.displayError("cashPositionSQL","selectOpenposOnTradeID",e);
        	 return null;
         
         }
	 }
	
	public static Collection selectOpenPositionOnPositionId(int positionid, Connection con ) {
		
		 try {
             
			 return selectopenposOnPositionId(positionid, con);
			 
         } catch ( Exception e ) {
        	 
        	 commonUTIL.displayError("cashPositionSQL","selectOpenPositionOnPositionId",e);
        	 return null;
        	 
         }
		 
	 }
	 
	public static int selectMaxID( Connection con ) {
		
		try {
			
             return selectMax( con );
             
        } catch( Exception e ) {
        	 
        	 commonUTIL.displayError("cashPositionSQL","selectMaxID",e);
        	 return 0;
        	 
        }
	 
	}
	 
	 protected static  boolean edit( CashPosition updateOpenpos, Connection con ) {
		 
		 PreparedStatement stmt = null;
		String sql = getUpdateSQL(updateOpenpos);
		 try {
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, sql);  
		
		 	int i = stmt.executeUpdate(sql);
			 con.commit();
			 commonUTIL.display("cashPositionSQL","edit " + sql);
			 if(i == 0)
				 return true;
		 } catch (Exception e) {
			 
			 commonUTIL.displayError("cashPositionSQL","edit "+sql,e);
			 return false;
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch ( SQLException e ) {
	    		 
				// TODO Auto-generated catch block
				commonUTIL.displayError("cashPositionSQL","edit",e);
			
	    	 }
	        
	     }
	        return true;
	 }
	
	protected static boolean remove( CashPosition deleteOpenpos, Connection con ) {
	
		PreparedStatement stmt = null;
		
		try {
			 
			int j = 1;
			
			stmt = dsSQL.newPreparedStatement( con, DELETE_FROM_openpos );
	        
			stmt.setInt(j++, deleteOpenpos.getTradeId());
	           
	        stmt.executeUpdate();
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("cashPositionSQL","remove",e);
			 
			 return false;
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch (SQLException e) {
				
	    		 // TODO Auto-generated catch block
				commonUTIL.displayError("cashPositionSQL","remove",e);
			
	    	 }
	    	 
	     }
		
	     return true;
	 
	}

	protected static int selectMax( Connection con ) {
		 
		int j = 0;
	    
		PreparedStatement stmt = null;
		
	    try {
			
	    	stmt = dsSQL.newPreparedStatement( con, SELECT_MAX );
	         
	         ResultSet rs = stmt.executeQuery();
	        
	         while( rs.next() ) {
	        	 
	        	 j = rs.getInt("DESC_ID");
	        	 
	         }	         
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("cashPositionSQL",SELECT_MAX,e);
			 return j;
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch ( SQLException e ) {
	    		 
				// TODO Auto-generated catch block
				commonUTIL.displayError("cashPositionSQL",SELECT_MAX,e);
			}
	    	 
	     }
	    
	        return j;
	 }
	 
	 protected static boolean insert( CashPosition inserOpenpos, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 
			//int id = selectMax(con);
			con.setAutoCommit(false);
			int j = 1;
			
			stmt = dsSQL.newPreparedStatement(con, INSERT_INTO_openpos);
			//stmt.setInt(1,id+1);
			
			stmt.setInt(1, 		inserOpenpos.getTradeId());
            stmt.setInt(2, 		inserOpenpos.getProductId());
            stmt.setTimestamp(3, 	commonUTIL.convertStringtoSQLTimeStamp(inserOpenpos.getSettleDate()));
            stmt.setTimestamp(4, 	commonUTIL.convertStringtoSQLTimeStamp(inserOpenpos.getTradeDate()));
            stmt.setDouble(5, 	inserOpenpos.getQuantity());
            stmt.setDouble(6, 	inserOpenpos.getOpenQuantity());
            stmt.setInt(7, 		inserOpenpos.getBookId());
            stmt.setDouble(8, 	inserOpenpos.getPrice());
            stmt.setString(9, 	inserOpenpos.getType());
            stmt.setInt(10, 	inserOpenpos.getPositionId());
            stmt.setString(11, 	inserOpenpos.getOpenpositionDate());
            stmt.setString(12, 	inserOpenpos.getProductType());
            stmt.setString(13, 	inserOpenpos.getProductSubType());
            stmt.setDouble(14, 	inserOpenpos.getOpenNominal());
            stmt.setDouble(15, 	inserOpenpos.getQuotingAmt());
            stmt.setDouble(16, 	inserOpenpos.getTradeAmt());
            stmt.setDouble(17, 	inserOpenpos.getProductFV());
            stmt.setDouble(18, 	inserOpenpos.getOriginalTradeAmt());
            stmt.setString(19, 	inserOpenpos.getTradedesc1());
            stmt.setString(20, 	inserOpenpos.getFxSwapLegType());
            stmt.setString(21, 	inserOpenpos.getTradedesc());
            stmt.setString(22, 	inserOpenpos.getPrimaryCurr());
            stmt.setString(23, 	inserOpenpos.getQuotingCurr());
            stmt.setString(24, 	inserOpenpos.getCurrency());
            stmt.setDouble(25, 	inserOpenpos.getActualAmt());
            stmt.setDouble(26, 	inserOpenpos.getCpID());
            stmt.setInt(27, 	inserOpenpos.getLeg());
            stmt.setInt(28, 	inserOpenpos.getId());
            stmt.executeUpdate();
            con.commit();
			
            commonUTIL.display("cashPositionSQL",INSERT_INTO_openpos);
		 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("cashPositionSQL",INSERT_INTO_openpos,e);
			 
			 return false;
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("cashPositionSQL",INSERT_INTO_openpos,e);
			}
	    	 
	     }
	     
		 return true;
	 }
	 
	protected static CashPosition selectONTradeid( int tradeID, Connection con ) {
		 
		int j = 0;
	    PreparedStatement stmt = null;
    
	    CashPosition openpos = new CashPosition();
		
	    try {
			String sql = SELECTONTRADEID + tradeID;
	    	stmt = dsSQL.newPreparedStatement(con,sql );
			 
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				openpos.setTradeId(rs.getInt(1));
				openpos.setProductId(rs.getInt(2));
				openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
				openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
				openpos.setQuantity(rs.getInt(5));
				openpos.setOpenQuantity(rs.getDouble(6));
				openpos.setBookId(rs.getInt(7));
				openpos.setPrice(rs.getDouble(8));
				openpos.setType(rs.getString(9));
				openpos.setPositionId(rs.getInt(10));
				openpos.setOpenpositionDate(rs.getString(11));	 
				openpos.setProductType(rs.getString(12));
				openpos.setProductSubType(rs.getString(13));
				openpos.setOpenNominal(rs.getDouble(14));
				openpos.setQuotingAmt(rs.getDouble(15));
				
				openpos.setTradeAmt(rs.getDouble(16));
				openpos.setProductFV(rs.getDouble(17));
				openpos.setOriginalTradeAmt(rs.getDouble(18));
				openpos.setId(rs.getInt(19));
				openpos.setTradedesc1(rs.getString(20));
				openpos.setFxSwapLegType(rs.getString(21));
				openpos.setTradedesc(rs.getString(22));
				openpos.setPrimaryCurr(rs.getString(23));
				openpos.setQuotingCurr(rs.getString(24));
				openpos.setCurrency(rs.getString(25));
				openpos.setActualAmt(rs.getDouble(26));
	         
	         }
			commonUTIL.display("cashPositionSQL","selectONTradeid " + sql);
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("cashPositionSQL","selectONTradeid",e);
			 return openpos;
	           
	     } finally {
	       
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch ( SQLException e ) {
	    		 
				// TODO Auto-generated catch block
				commonUTIL.displayError("cashPositionSQL","selectONTradeid",e);
			}
	    	 
	     }
	    
	        return openpos;
	 }

	protected static Collection selectPositionId(int Positionid,Connection con) { 
		
		int j = 0;
	    
		PreparedStatement stmt = null;
	    
		Vector openposs = new Vector();
	     String sql = SELECTALL + " where positionid = "+ Positionid;
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
		     while(rs.next()) {
		   
		    	 CashPosition openpos = new CashPosition();
  
		    	 openpos.setTradeId(rs.getInt(1));
				 openpos.setProductId(rs.getInt(2));
				 openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
				 openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
				 openpos.setQuantity(rs.getInt(5));
				 openpos.setOpenQuantity(rs.getDouble(6));
				 openpos.setBookId(rs.getInt(7));
				 openpos.setPrice(rs.getDouble(8));
				 openpos.setType(rs.getString(9));
				 openpos.setPositionId(rs.getInt(10));
				 openpos.setOpenpositionDate(rs.getString(11));	 
				 openpos.setProductType(rs.getString(12));
				 openpos.setProductSubType(rs.getString(13));	          
				 openpos.setOpenNominal(rs.getDouble(14));
					openpos.setQuotingAmt(rs.getDouble(15));
					openpos.setTradeAmt(rs.getDouble(16));
					openpos.setProductFV(rs.getDouble(17));
					openpos.setOriginalTradeAmt(rs.getDouble(18));
					openpos.setId(rs.getInt(19));
					openpos.setTradedesc1(rs.getString(20));
					openpos.setFxSwapLegType(rs.getString(21));
					openpos.setTradedesc(rs.getString(22));
					openpos.setPrimaryCurr(rs.getString(23));
					openpos.setQuotingCurr(rs.getString(24));
					openpos.setCurrency(rs.getString(25));
					openpos.setActualAmt(rs.getDouble(26));
		    	 openposs.add(openpos);
		      
		      }
		     
		     
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("cashPositionSQL",SELECTALL,e);
			 return openposs;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
			
	        } catch ( SQLException e ) {
				
	        	// TODO Auto-generated catch block
				commonUTIL.displayError("cashPositionSQL",SELECTALL,e);
			
	        }
	        
	     }
		 
	     return openposs;
	 }
	
	
	// used as normal FIFO method 
	
	protected static Collection selectopenposOnPositionId(int positionid,Connection con ) {
		int j = 0;
	    
		PreparedStatement stmt = null;
	    
		Vector openposs = new Vector();
	     
		 try {
			 
			 String sql  = SELECTALL  + " where positionid = "  + positionid +  " order by id ASC ";
			 
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
			 while(rs.next()) {
				 
		    	 CashPosition openpos = new CashPosition();
		    	  
		    	 openpos.setTradeId(rs.getInt(1));
				 openpos.setProductId(rs.getInt(2));
				 openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
				 openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
				 openpos.setQuantity(rs.getInt(5));
				 openpos.setOpenQuantity(rs.getDouble(6));
				 openpos.setBookId(rs.getInt(7));
				 openpos.setPrice(rs.getDouble(8));
				 openpos.setType(rs.getString(9));
				 openpos.setPositionId(rs.getInt(10));
				 openpos.setOpenpositionDate(rs.getString(11));	 
				 openpos.setProductType(rs.getString(12));
				 openpos.setProductSubType(rs.getString(13));	          
				 openpos.setOpenNominal(rs.getDouble(14));
					openpos.setQuotingAmt(rs.getDouble(15));
					openpos.setTradeAmt(rs.getDouble(16));
					openpos.setProductFV(rs.getDouble(17));
					openpos.setOriginalTradeAmt(rs.getDouble(18));
					openpos.setId(rs.getInt(19));
					openpos.setTradedesc1(rs.getString(20));
					openpos.setFxSwapLegType(rs.getString(21));
					openpos.setTradedesc(rs.getString(22));
					openpos.setPrimaryCurr(rs.getString(23));
					openpos.setQuotingCurr(rs.getString(24));
					openpos.setCurrency(rs.getString(25));
					openpos.setActualAmt(rs.getDouble(26));
		    	 openposs.add(openpos);
	      
			 }
			 commonUTIL.display("cashPositionSQL","selectopenposOnPositionId " + sql);
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("cashPositionSQL","selectopenposOnPositionId",e);
			 return openposs;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("cashPositionSQL","selectMax",e);
				
			}
	     }
		 
	     return openposs;
	 }
	// used as normal LIFO method 
	public static Collection selectopenposOnPositionIddesc(int positionid,Connection con ) {
		int j = 0;
	    
		PreparedStatement stmt = null;
	    
		Vector openposs = new Vector();
	     
		 try {
			 
			 String sql  = SELECTALL  + " where positionid = "  + positionid +  " order by id desc ";
			 
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
			 while(rs.next()) {
				 
		    	 CashPosition openpos = new CashPosition();
		    	  
		    	 openpos.setTradeId(rs.getInt(1));
				 openpos.setProductId(rs.getInt(2));
				 openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
				 openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
				 openpos.setQuantity(rs.getInt(5));
				 openpos.setOpenQuantity(rs.getDouble(6));
				 openpos.setBookId(rs.getInt(7));
				 openpos.setPrice(rs.getDouble(8));
				 openpos.setType(rs.getString(9));
				 openpos.setPositionId(rs.getInt(10));
				 openpos.setOpenpositionDate(rs.getString(11));	 
				 openpos.setProductType(rs.getString(12));
				 openpos.setProductSubType(rs.getString(13));	          
				 openpos.setOpenNominal(rs.getDouble(14));
					openpos.setQuotingAmt(rs.getDouble(15));
					openpos.setTradeAmt(rs.getDouble(16));
					openpos.setProductFV(rs.getDouble(17));
					openpos.setOriginalTradeAmt(rs.getDouble(18));
					openpos.setId(rs.getInt(19));
					openpos.setTradedesc1(rs.getString(20));
					openpos.setFxSwapLegType(rs.getString(21));
					openpos.setTradedesc(rs.getString(22));
					openpos.setPrimaryCurr(rs.getString(23));
					openpos.setQuotingCurr(rs.getString(24));
					openpos.setCurrency(rs.getString(25));
					openpos.setActualAmt(rs.getDouble(26));
		    	 openposs.add(openpos);
	      
			 }
			 commonUTIL.display("cashPositionSQL","selectopenposOnPositionId " + sql);
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("cashPositionSQL","selectopenposOnPositionId",e);
			 return openposs;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("cashPositionSQL","selectMax",e);
				
			}
	     }
		 
	     return openposs;
	 }
	public static Collection selectopenposOnKey(int bookid,int productId,String productSubtype,Connection con ) {
		int j = 0;
	    
		PreparedStatement stmt = null;
	    
		Vector openposs = new Vector();
	     String sql = "";
		 try {
			 
			 sql = SELECTONKEYS + " bookid = " + bookid +" and productid = "+productId  +  " and productsubtype = '"+productSubtype.trim() +" and quantity > 0 ' order by tradeId desc ";
			 
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
			 while(rs.next()) {
				 
		    	 CashPosition openpos = new CashPosition();
		    	  
		    	 openpos.setTradeId(rs.getInt(1));
				 openpos.setProductId(rs.getInt(2));
				 openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
				 openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
				 openpos.setQuantity(rs.getInt(5));
				 openpos.setOpenQuantity(rs.getDouble(6));
				 openpos.setBookId(rs.getInt(7));
				 openpos.setPrice(rs.getDouble(8));
				 openpos.setType(rs.getString(9));
				 openpos.setPositionId(rs.getInt(10));
				 openpos.setOpenpositionDate(rs.getString(11));	 
				 openpos.setProductType(rs.getString(12));
				 openpos.setProductSubType(rs.getString(13));	          
				 openpos.setOpenNominal(rs.getDouble(14));
					openpos.setQuotingAmt(rs.getDouble(15));
					openpos.setTradeAmt(rs.getDouble(16));
					openpos.setProductFV(rs.getDouble(17));
					openpos.setOriginalTradeAmt(rs.getDouble(18));
					openpos.setId(rs.getInt(19));
					openpos.setTradedesc1(rs.getString(20));
					openpos.setFxSwapLegType(rs.getString(21));
					openpos.setTradedesc(rs.getString(22));
					openpos.setPrimaryCurr(rs.getString(23));
					openpos.setQuotingCurr(rs.getString(24));
					openpos.setCurrency(rs.getString(25));
					openpos.setActualAmt(rs.getDouble(26));
		    	 openposs.add(openpos);
	      
			 }
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("cashPositionSQL","selectopenpos",e);
			 return openposs;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("cashPositionSQL","selectMax",e);
				
			}
	     }
		 
	     return openposs;
	 }

	public static Collection getUnRealisedPosition(int posID,Connection con ) {
		// TODO Auto-generated method stub
int j = 0;
	    
		PreparedStatement stmt = null;
	    
		Vector openposs = new Vector();
	     String sql = "";
		 try {
			 
			 sql = UNREALISED + posID + "  and openquantity <> 0 ";
			 
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
			 while(rs.next()) {
				 
		    	 CashPosition openpos = new CashPosition();
		    	  
		    	 openpos.setTradeId(rs.getInt(1));
				 openpos.setProductId(rs.getInt(2));
				 openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
				 openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
				 openpos.setQuantity(rs.getInt(5));
				 openpos.setOpenQuantity(rs.getDouble(6));
				 openpos.setBookId(rs.getInt(7));
				 openpos.setPrice(rs.getDouble(8));
				 openpos.setType(rs.getString(9));
				 openpos.setPositionId(rs.getInt(10));
				 openpos.setOpenpositionDate(rs.getString(11));	 
				 openpos.setProductType(rs.getString(12));
				 openpos.setProductSubType(rs.getString(13));	          
				 openpos.setOpenNominal(rs.getDouble(14));
					openpos.setQuotingAmt(rs.getDouble(15));
					openpos.setTradeAmt(rs.getDouble(16));
					openpos.setProductFV(rs.getDouble(17));
					openpos.setOriginalTradeAmt(rs.getDouble(18));
					openpos.setId(rs.getInt(19));
					openpos.setTradedesc1(rs.getString(20));
					openpos.setFxSwapLegType(rs.getString(21));
					openpos.setTradedesc(rs.getString(22));
					openpos.setPrimaryCurr(rs.getString(23));
					openpos.setQuotingCurr(rs.getString(24));
					openpos.setCurrency(rs.getString(25));
					openpos.setActualAmt(rs.getDouble(26));
		    	 openposs.add(openpos);
	      
			 }
			 commonUTIL.display("cashPositionSQL","getUnRealisedPosition " + sql );
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("cashPositionSQL","getUnRealisedPosition " + sql ,e);
			 return openposs;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("cashPositionSQL","getUnRealisedPosition",e);
				
			}
	     }
		 
	     return openposs;
	}

	public static double getWACRate(int positionID,Connection con) {
		double wacRate = 0.0;
		String sql = WACSQL + positionID;
		PreparedStatement stmt = null;	    
		
		try {
			stmt = dsSQL.newPreparedStatement(con, sql );
	        ResultSet rs = stmt.executeQuery();	      
			 while(rs.next()) {
				 wacRate = rs.getDouble(1);
			 }
			 commonUTIL.display("cashPositionSQL","getWACRate " + sql );			 
		 } catch ( Exception e ) {			 
			 commonUTIL.displayError("cashPositionSQL","getWACRate " + sql ,e);
			 return 0.0;	        
	     } finally {	    	 
	        try {				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {				
				// TODO Auto-generated catch block
				commonUTIL.displayError("cashPositionSQL","getWACRate",e);
				 return 0.0;
			}
	     }
		return wacRate;
	}
	
	public static Collection getOpenPositionWhere(String where,Connection con) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int j = 0;
			    
				PreparedStatement stmt = null;
			    
				Vector openposs = new Vector();
			     String sql = "";
				 try {
					 
					 sql = SELECTALL + " where   "  + where  + " order by id asc ";
					 
					 stmt = dsSQL.newPreparedStatement(con, sql );
			      
					 ResultSet rs = stmt.executeQuery();
			      
					 while(rs.next()) {
						 
				    	 CashPosition openpos = new CashPosition();
				    		
				    	 openpos.setTradeId(rs.getInt(1));
						 openpos.setProductId(rs.getInt(2));
						 openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
						 openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
						 openpos.setQuantity(rs.getInt(5));
						 openpos.setOpenQuantity(rs.getDouble(6));
						 openpos.setBookId(rs.getInt(7));
						 openpos.setPrice(rs.getDouble(8));
						// openpos.setType(rs.getString(9));
						 openpos.setPositionId(rs.getInt(9));
						 openpos.setOpenpositionDate(rs.getString(10));	 
						 openpos.setProductType(rs.getString(11));
						 openpos.setProductSubType(rs.getString(12));	          
						 openpos.setOpenNominal(rs.getDouble(13));
							openpos.setQuotingAmt(rs.getDouble(14));
							openpos.setTradeAmt(rs.getDouble(15));
							openpos.setProductFV(rs.getDouble(16));
							openpos.setOriginalTradeAmt(rs.getDouble(17));
							openpos.setId(rs.getInt(18));
							openpos.setTradedesc1(rs.getString(19));
							openpos.setFxSwapLegType(rs.getString(20));
							openpos.setTradedesc(rs.getString(21));
							openpos.setPrimaryCurr(rs.getString(22));
							openpos.setQuotingCurr(rs.getString(23));
							openpos.setCurrency(rs.getString(24));
							openpos.setActualAmt(rs.getDouble(25));
				    	 openposs.add(openpos);
			      
					 }
					 commonUTIL.display("cashPositionSQL","getOpenPositionWhere " + sql );
					 
				 } catch ( Exception e ) {
					 
					 commonUTIL.displayError("cashPositionSQL","getOpenPositionWhere " + sql ,e);
					 return openposs;
			        
			     } finally {
			    	 
			        try {
						
			        	stmt.close();
			        	
					} catch ( SQLException e ) {
						
						// TODO Auto-generated catch block
						commonUTIL.displayError("cashPositionSQL","getOpenPositionWhere",e);
						
					}
			     }
				 
			     return openposs;
	}
	public static Collection getRealisedCashPositionOnTrade(String where,Connection con) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int j = 0;
			    
				PreparedStatement stmt = null;
			    
				Vector openposs = new Vector();
			     String sql = "";
				 try {
					 
					 sql = where;
					 
					 stmt = dsSQL.newPreparedStatement(con, sql );
			      
					 ResultSet rs = stmt.executeQuery();
			      
					 while(rs.next()) {
						 
				    	 CashPosition openpos = new CashPosition();
				    		
				    	 openpos.setOut1amount(rs.getDouble(1));
				    	 openpos.setOut2amount(rs.getDouble(2));
				    	 openposs.add(openpos);
			      
					 }
					 commonUTIL.display("cashPositionSQL","getRealisedCashPositionOnTrade " + sql );
					 
				 } catch ( Exception e ) {
					 
					 commonUTIL.displayError("cashPositionSQL","getRealisedCashPositionOnTrade " + sql ,e);
					 return openposs;
			        
			     } finally {
			    	 
			        try {
						
			        	stmt.close();
			        	
					} catch ( SQLException e ) {
						
						// TODO Auto-generated catch block
						commonUTIL.displayError("cashPositionSQL","getOpenPositionWhere",e);
						
					}
			     }
				 
			     return openposs;
	}

	public static void update(Vector<CashPosition> openPositionforUpdate,
			Connection conn) {
		// TODO Auto-generated method stub
		try {
	  if(openPositionforUpdate !=null && openPositionforUpdate.size() > 0) {
		  for(int i=0;i<openPositionforUpdate.size();i++) {
			  CashPosition opos =  openPositionforUpdate.elementAt(i);
			  update(opos, conn);
		  }
		  commonUTIL.display("cashPositionSQL","Batchupdate Done");
		  
	  }
		}catch(Exception e) {
			commonUTIL.displayError("cashPositionSQL","Batchupdate", e);
		}
		
	}

	public static Vector getDistinctOpenPositionWhere(
			String distinctproductSubType, Connection conn) {
		// TODO Auto-generated method stub
		String sql = distinctProductType + " where productsubtype =  '" + distinctproductSubType+"' group by productsubtype,settledate";
		  
		PreparedStatement stmt = null;
	    
		Vector openposs = new Vector();
		
	      
		
		 try {
			 
			
			 
			 stmt = dsSQL.newPreparedStatement(conn, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
			 while(rs.next()) {
				 
		    	 CashPosition openpos = new CashPosition();
		    	
					          
				 openpos.setOpenNominal(rs.getDouble("quotingCurr"));
					openpos.setQuotingAmt(rs.getDouble("PrimaryCurr"));
					openpos.setProductType(rs.getString("productsubtype"));
					 openpos.setProductSubType(rs.getString("productsubtype1"));
		    	 openposs.add(openpos);
	      
			 }
			 commonUTIL.display("cashPositionSQL","getOpenPositionWhere " + sql );
			 return openposs;
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("cashPositionSQL","getOpenPositionWhere " + sql ,e);
			 return openposs;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("cashPositionSQL","getOpenPositionWhere",e);
				
			}
	     }
		 
	    
	}

	public static Vector<CashPosition> getForwardOptionCashPositionOnTakeup(int tradeid,Connection con) {
		String sql = SELECTALL + " where tradeid = " + tradeid;
		PreparedStatement stmt = null;
	    		Vector<CashPosition> openposs = new Vector<CashPosition>();
	   
		 try {
			 
			
			 
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
			 while(rs.next()) {
				 
		    	 CashPosition openpos = new CashPosition();
		    	
		    	 openpos.setTradeId(rs.getInt(1));
				 openpos.setProductId(rs.getInt(2));
				 openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
				 openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
				 openpos.setQuantity(rs.getInt(5));
				 openpos.setOpenQuantity(rs.getDouble(6));
				 openpos.setBookId(rs.getInt(7));
				 openpos.setPrice(rs.getDouble(8));
				// openpos.setType(rs.getString(9));
				 openpos.setPositionId(rs.getInt(9));
				 openpos.setOpenpositionDate(rs.getString(10));	 
				 openpos.setProductType(rs.getString(11));
				 openpos.setProductSubType(rs.getString(12));	          
				 openpos.setOpenNominal(rs.getDouble(13));
					openpos.setQuotingAmt(rs.getDouble(14));
					openpos.setTradeAmt(rs.getDouble(15));
					openpos.setProductFV(rs.getDouble(16));
					openpos.setOriginalTradeAmt(rs.getDouble(17));
					openpos.setId(rs.getInt(18));
					openpos.setTradedesc1(rs.getString(19));
					openpos.setFxSwapLegType(rs.getString(20));
					openpos.setTradedesc(rs.getString(21));
					openpos.setPrimaryCurr(rs.getString(22));
					openpos.setQuotingCurr(rs.getString(23));
					openpos.setCurrency(rs.getString(24));
					openpos.setActualAmt(rs.getDouble(25));
					openpos.setLeg(rs.getInt(26));
					openpos.setOut1amount(rs.getDouble(27));
					openpos.setOut2amount(rs.getDouble(28));
		    	 openposs.add(openpos);
	      
			 }
		 }
			 catch(Exception e) {
				 commonUTIL.displayError("cashPositionSQL","getForwardOptionCashPositionOnTakeup " + sql, e);
			 }
			 commonUTIL.display("cashPositionSQL","getForwardOptionCashPositionOnTakeup " + sql );
			 
		return openposs;
	}
	
	public static CashPosition getOpenPositionOnID(int openID,Connection con)  {
		// TODO Auto-generated method stub
		String sql = " id = " + openID;
		Vector v1 = (Vector) getOpenPositionWhere(sql,con);
		return (CashPosition)  v1.elementAt(0);
	}
	public static CashPosition getRealisedAmt(int tradeID,Connection con)  {
		// TODO Auto-generated method stub
		String sql = GETRealisedOutStandingAmt + tradeID;
		Vector v1 = (Vector) getRealisedCashPositionOnTrade(sql,con);
		return (CashPosition)  v1.elementAt(0);
	}
}

