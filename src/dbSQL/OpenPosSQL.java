package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL;
import beans.Openpos;

public class OpenPosSQL {

	final static private String DELETE_FROM_openpos =
			
			" DELETE 				" +
			" FROM 		Openpos 	" +
			" WHERE 	TRADEID = ? ";

	final static private String INSERT_INTO_openpos =
			
			" INSERT INTO Openpos" +
			" ( tradeid," +
			"	productid," +
			"	settledate," +
			"	tradedate," +
			"	quantity," +
			"	openquantity," +
			"	bookno," +
			"	price," +
			"	sign," +
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
			"	tradedesc,primaryCurr,QuotingCurr," +
			"    id" +
			" )" +
			" VALUES" +
			"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,OPENPOS_SEQ.NEXTVAL)"; 
			
	final static private String distinctProductType = "  select sum(quotingamt) quotingCurr ,sum(openNominal) PrimaryCurr, productsubtype,  concat(concat(productsubtype,'_'),settledate) productsubtype1 from openpos ";
	
	
    private static String getUpdateSQL( Openpos updateOpenpos ) {
		String updateOpenPos = " UPDATE  openpos  set 	" +
				" tradeid ="+ updateOpenpos.getTradeId()+"," +
				" productid ="+ updateOpenpos.getProductId()+"," +
				" settledate =to_date('"+ updateOpenpos.getSettleDate()+"', 'DD/MM/YYYY hh24:mi:ss')," +
				" tradedate  =to_date('"+ updateOpenpos.getTradeDate()+"', 'DD/MM/YYYY hh24:mi:ss')," +
				" quantity  ="+ updateOpenpos.getQuantity()+"," +
				" openquantity  ="+ updateOpenpos.getOpenQuantity()+"," +
				" bookno  ="+ updateOpenpos.getBookId()+"," +
				" price	 ="+ updateOpenpos.getPrice()+"," +
				" sign   ="+ updateOpenpos.getSign()+"," +
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
				" primaryCurr ='"+ updateOpenpos.getPrimaryCurr() +"', " +
				" quotingCurr ='"+ updateOpenpos.getQuotingCurr() +"' " +
				" WHERE	" +
				" TRADEID  = "+ updateOpenpos.getTradeId() + " and id = " +updateOpenpos.getId();
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
			" bookno," +
			" price," +
			" sign," +
			" positionid," +
			" openpositionDate," +
			" producttype," +
			" productsubtype,openNominal,quotingAmt,tradeAmt,productFV,originalTradeAmt,id,tradedesc1,fxSwapLegType,tradedesc,primaryCurr,QuotingCurr " +
			" FROM  " +
			"		Openpos "; 
			
	
	final static private String SELECTONKEYS =
			

		" SELECT  " +
			" tradeid," +
			"  productid," +
			"  settledate," +
			" tradedate," +
			" quantity," +
			" openquantity," +
			" bookno," +
			" price," +
			" sign," +
			" positionid," +
			" openpositionDate," +
			" producttype," +
			" productsubtype,openNominal,quotingAmt,tradeAmt,productFV,originalTradeAmt,id,tradedesc1,fxSwapLegType,tradedesc,primaryCurr,QuotingCurr  " +
			" FROM  " +
			"		Openpos " +
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
			" bookno," +
			" price," +
			" sign," +
			" positionid," +
			" openpositionDate," +
			" producttype," +
			" productsubtype,openNominal,quotingAmt,tradeAmt,productFV,originalTradeAmt,id,tradedesc1,fxSwapLegType,tradedesc,primaryCurr,QuotingCurr   " +
			" FROM  " +
			"		Openpos " +
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
			" bookno," +
			" price," +
			" sign," +
			" positionid," +
			" openpositionDate," +
			" producttype," +
			" productsubtype,openNominal,quotingAmt,tradeAmt,productFV,originalTradeAmt,id,tradedesc1,fxSwapLegType,tradedesc,primaryCurr,QuotingCurr   " +
			" FROM  " +
			"		Openpos " +
			" where " +
			"		tradeid = 	";
final static private String SELECTONTRADEIDFXSWapType =
			
			" SELECT " +
			" tradeid," +
			"  productid," +
			"  settledate," +
			" tradedate," +
			" quantity," +
			" openquantity," +
			" bookno," +
			" price," +
			" sign," +
			" positionid," +
			" openpositionDate," +
			" producttype," +
			" productsubtype,openNominal,quotingAmt,tradeAmt,productFV,originalTradeAmt,id,tradedesc1,fxSwapLegType,tradedesc,primaryCurr,QuotingCurr   " +
			" FROM  " +
			"		Openpos " +
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
		" bookno," +
		" price," +
		" sign," +
		" positionid," +
		" openpositionDate," +
		" producttype," +
		" productsubtype,openNominal,quotingAmt,tradeAmt,productFV,originalTradeAmt,id,tradedesc1,fxSwapLegType,tradedesc,primaryCurr,QuotingCurr   " +
		" FROM  " +
		"		Openpos " +
		" where " +
		"		positionid = 	";
	
   static public String WACSQL = " select round(sum(originaltradeamt)/sum(quantity),2) wacRate from openpos where positionid  = ";
	
	public static boolean save( Openpos insertLiquid, Connection con ) {
		
		try {
             
			return insert( insertLiquid, con );
         
		} catch ( Exception e ) {
			
        	 commonUTIL.displayError("openposSQL","save",e);
        	 return false;
        	 
         }
		
	 }
	
	public static boolean update( Openpos updateOpenpos, Connection con ) {
		 
		try {
             
			return edit( updateOpenpos, con );
         
		} catch ( Exception e ) {
			
        	 commonUTIL.displayError("openposSQL","update",e);
        	 
        	 return false;
        	 
         }
		
	 }
	 
	public static boolean delete( Openpos deleteOpenpos, Connection con ) {
	
		try {
			
             return remove( deleteOpenpos, con );
         
		} catch( Exception e ) {
        	 
			commonUTIL.displayError("openposSQL","update",e);
        	 return false;
        	 
         }
	 
	}
	
	public static Openpos selectOpenposOnTradeID( int tradeID, Connection con ) {
		
		try {
			
             return  selectONTradeid(tradeID, con);
             
         } catch ( Exception e ) {
        	 
        	 commonUTIL.displayError("openposSQL","selectOpenposOnTradeID",e);
        	 return null;
         
         }
	 }
	
	public static Collection selectOpenPositionOnPositionId(int positionid, Connection con ) {
		
		 try {
             
			 return selectopenposOnPositionId(positionid, con);
			 
         } catch ( Exception e ) {
        	 
        	 commonUTIL.displayError("openposSQL","selectOpenPositionOnPositionId",e);
        	 return null;
        	 
         }
		 
	 }
	 
	public static int selectMaxID( Connection con ) {
		
		try {
			
             return selectMax( con );
             
        } catch( Exception e ) {
        	 
        	 commonUTIL.displayError("openposSQL","selectMaxID",e);
        	 return 0;
        	 
        }
	 
	}
	 
	 protected static  boolean edit( Openpos updateOpenpos, Connection con ) {
		 
		 PreparedStatement stmt = null;
		String sql = getUpdateSQL(updateOpenpos);
		 try {
			 con.setAutoCommit(false);
			
			 
			stmt = dsSQL.newPreparedStatement(con, sql);  
		
		 	int i = stmt.executeUpdate(sql);
			 con.commit();
			 commonUTIL.display("openposSQL","edit " + sql);
			 if(i == 0)
				 return true;
		 } catch (Exception e) {
			 
			 commonUTIL.displayError("openposSQL","edit "+sql,e);
			 return false;
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch ( SQLException e ) {
	    		 
				// TODO Auto-generated catch block
				commonUTIL.displayError("openposSQL","edit",e);
			
	    	 }
	        
	     }
	        return true;
	 }
	
	protected static boolean remove( Openpos deleteOpenpos, Connection con ) {
	
		PreparedStatement stmt = null;
		
		try {
			 
			int j = 1;
			
			stmt = dsSQL.newPreparedStatement( con, DELETE_FROM_openpos );
	        
			stmt.setInt(j++, deleteOpenpos.getTradeId());
	           
	        stmt.executeUpdate();
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("openposSQL","remove",e);
			 
			 return false;
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch (SQLException e) {
				
	    		 // TODO Auto-generated catch block
				commonUTIL.displayError("openposSQL","remove",e);
			
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
			 
			 commonUTIL.displayError("openposSQL",SELECT_MAX,e);
			 return j;
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch ( SQLException e ) {
	    		 
				// TODO Auto-generated catch block
				commonUTIL.displayError("openposSQL",SELECT_MAX,e);
			}
	    	 
	     }
	    
	        return j;
	 }
	 
	 protected static boolean insert( Openpos inserOpenpos, Connection con ) {
			
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
            stmt.setDouble(9, 	inserOpenpos.getSign());
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
            stmt.executeUpdate();
            con.commit();
			
            commonUTIL.display("openposSQL",INSERT_INTO_openpos);
		 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("openposSQL",INSERT_INTO_openpos,e);
			 
			 return false;
	           
	     } finally {
	           
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("openposSQL",INSERT_INTO_openpos,e);
			}
	    	 
	     }
	     
		 return true;
	 }
	 
	protected static Openpos selectONTradeid( int tradeID, Connection con ) {
		 
		int j = 0;
	    PreparedStatement stmt = null;
    
	    Openpos openpos = new Openpos();
		
	    try {
			String sql = SELECTONTRADEIDFXSWapType + tradeID ;
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
				openpos.setSign(rs.getInt(9));
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
	         
	         }
			commonUTIL.display("openposSQL","selectONTradeid " + sql);
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("openposSQL","selectONTradeid",e);
			 return openpos;
	           
	     } finally {
	       
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch ( SQLException e ) {
	    		 
				// TODO Auto-generated catch block
				commonUTIL.displayError("openposSQL","selectONTradeid",e);
			}
	    	 
	     }
	    
	        return openpos;
	 }
	protected static Openpos selectONTradeid( int tradeID, String fxswapLegType,Connection con ) {
		 
		int j = 0;
	    PreparedStatement stmt = null;
    
	    Openpos openpos = new Openpos();
		
	    try {
			String sql = SELECTONTRADEIDFXSWapType + tradeID + " fxswaplegtype = '"+fxswapLegType+"'";
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
				openpos.setSign(rs.getInt(9));
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
	         
	         }
			commonUTIL.display("openposSQL","selectONTradeid " + sql);
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("openposSQL","selectONTradeid",e);
			 return openpos;
	           
	     } finally {
	       
	    	 try {
				
	    		 stmt.close();
			
	    	 } catch ( SQLException e ) {
	    		 
				// TODO Auto-generated catch block
				commonUTIL.displayError("openposSQL","selectONTradeid",e);
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
		   
		    	 Openpos openpos = new Openpos();
  
		    	 openpos.setTradeId(rs.getInt(1));
				 openpos.setProductId(rs.getInt(2));
				 openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
				 openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
				 openpos.setQuantity(rs.getInt(5));
				 openpos.setOpenQuantity(rs.getDouble(6));
				 openpos.setBookId(rs.getInt(7));
				 openpos.setPrice(rs.getDouble(8));
				 openpos.setSign(rs.getInt(9));
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
		    	 openposs.add(openpos);
		      
		      }
		     
		     
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("openposSQL",SELECTALL,e);
			 return openposs;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
			
	        } catch ( SQLException e ) {
				
	        	// TODO Auto-generated catch block
				commonUTIL.displayError("openposSQL",SELECTALL,e);
			
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
				 
		    	 Openpos openpos = new Openpos();
		    	  
		    	 openpos.setTradeId(rs.getInt(1));
				 openpos.setProductId(rs.getInt(2));
				 openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
				 openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
				 openpos.setQuantity(rs.getInt(5));
				 openpos.setOpenQuantity(rs.getDouble(6));
				 openpos.setBookId(rs.getInt(7));
				 openpos.setPrice(rs.getDouble(8));
				 openpos.setSign(rs.getInt(9));
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
		    	 openposs.add(openpos);
	      
			 }
			 commonUTIL.display("openposSQL","selectopenposOnPositionId " + sql);
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("openposSQL","selectopenposOnPositionId",e);
			 return openposs;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("openposSQL","selectMax",e);
				
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
				 
		    	 Openpos openpos = new Openpos();
		    	  
		    	 openpos.setTradeId(rs.getInt(1));
				 openpos.setProductId(rs.getInt(2));
				 openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
				 openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
				 openpos.setQuantity(rs.getInt(5));
				 openpos.setOpenQuantity(rs.getDouble(6));
				 openpos.setBookId(rs.getInt(7));
				 openpos.setPrice(rs.getDouble(8));
				 openpos.setSign(rs.getInt(9));
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
		    	 openposs.add(openpos);
	      
			 }
			 commonUTIL.display("openposSQL","selectopenposOnPositionId " + sql);
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("openposSQL","selectopenposOnPositionId",e);
			 return openposs;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("openposSQL","selectMax",e);
				
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
			 
			 sql = SELECTONKEYS + " bookno = " + bookid +" and productid = "+productId  +  " and productsubtype = '"+productSubtype.trim() +" and quantity > 0 ' order by tradeId desc ";
			 
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
			 ResultSet rs = stmt.executeQuery();
	      
			 while(rs.next()) {
				 
		    	 Openpos openpos = new Openpos();
		    	  
		    	 openpos.setTradeId(rs.getInt(1));
				 openpos.setProductId(rs.getInt(2));
				 openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
				 openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
				 openpos.setQuantity(rs.getInt(5));
				 openpos.setOpenQuantity(rs.getDouble(6));
				 openpos.setBookId(rs.getInt(7));
				 openpos.setPrice(rs.getDouble(8));
				 openpos.setSign(rs.getInt(9));
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
		    	 openposs.add(openpos);
	      
			 }
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("openposSQL","selectopenpos",e);
			 return openposs;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("openposSQL","selectMax",e);
				
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
				 
		    	 Openpos openpos = new Openpos();
		    	  
		    	 openpos.setTradeId(rs.getInt(1));
				 openpos.setProductId(rs.getInt(2));
				 openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
				 openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
				 openpos.setQuantity(rs.getInt(5));
				 openpos.setOpenQuantity(rs.getDouble(6));
				 openpos.setBookId(rs.getInt(7));
				 openpos.setPrice(rs.getDouble(8));
				 openpos.setSign(rs.getInt(9));
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
		    	 openposs.add(openpos);
	      
			 }
			 commonUTIL.display("openposSQL","getUnRealisedPosition " + sql );
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("openposSQL","getUnRealisedPosition " + sql ,e);
			 return openposs;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("openposSQL","getUnRealisedPosition",e);
				
			}
	     }
		 
	     return openposs;
	}

	public static double getWACRate(int positionID,Connection con) {
		double wacRate = 0.0;
		String sql = WACSQL + positionID + " and  sign = 1 ";
		PreparedStatement stmt = null;	    
		
		try {
			stmt = dsSQL.newPreparedStatement(con, sql );
	        ResultSet rs = stmt.executeQuery();	      
			 while(rs.next()) {
				 wacRate = rs.getDouble(1);
			 }
			 commonUTIL.display("openposSQL","getWACRate " + sql );			 
		 } catch ( Exception e ) {			 
			 commonUTIL.displayError("openposSQL","getWACRate " + sql ,e);
			 return 0.0;	        
	     } finally {	    	 
	        try {				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {				
				// TODO Auto-generated catch block
				commonUTIL.displayError("openposSQL","getWACRate",e);
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
						 
				    	 Openpos openpos = new Openpos();
				    	  
				    	 openpos.setTradeId(rs.getInt(1));
						 openpos.setProductId(rs.getInt(2));
						 openpos.setSettleDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(3)));
						 openpos.setTradeDate(commonUTIL.convertSQLDatetoString(rs.getTimestamp(4)));
						 openpos.setQuantity(rs.getInt(5));
						 openpos.setOpenQuantity(rs.getDouble(6));
						 openpos.setBookId(rs.getInt(7));
						 openpos.setPrice(rs.getDouble(8));
						 openpos.setSign(rs.getInt(9));
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
				    	 openposs.add(openpos);
			      
					 }
					 commonUTIL.display("openposSQL","getOpenPositionWhere " + sql );
					 
				 } catch ( Exception e ) {
					 
					 commonUTIL.displayError("openposSQL","getOpenPositionWhere " + sql ,e);
					 return openposs;
			        
			     } finally {
			    	 
			        try {
						
			        	stmt.close();
			        	
					} catch ( SQLException e ) {
						
						// TODO Auto-generated catch block
						commonUTIL.displayError("openposSQL","getOpenPositionWhere",e);
						
					}
			     }
				 
			     return openposs;
	}

	public static void update(Vector<Openpos> openPositionforUpdate,
			Connection conn) {
		// TODO Auto-generated method stub
		try {
	  if(openPositionforUpdate !=null && openPositionforUpdate.size() > 0) {
		  for(int i=0;i<openPositionforUpdate.size();i++) {
			  Openpos opos =  openPositionforUpdate.elementAt(i);
			  update(opos, conn);
		  }
		  commonUTIL.display("openposSQL","Batchupdate Done");
		  
	  }
		}catch(Exception e) {
			commonUTIL.displayError("openposSQL","Batchupdate", e);
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
				 
		    	 Openpos openpos = new Openpos();
		    	
					          
				 openpos.setOpenNominal(rs.getDouble("quotingCurr"));
					openpos.setQuotingAmt(rs.getDouble("PrimaryCurr"));
					openpos.setProductType(rs.getString("productsubtype"));
					 openpos.setProductSubType(rs.getString("productsubtype1"));
		    	 openposs.add(openpos);
	      
			 }
			 commonUTIL.display("openposSQL","getOpenPositionWhere " + sql );
			 return openposs;
			 
		 } catch ( Exception e ) {
			 
			 commonUTIL.displayError("openposSQL","getOpenPositionWhere " + sql ,e);
			 return openposs;
	        
	     } finally {
	    	 
	        try {
				
	        	stmt.close();
	        	
			} catch ( SQLException e ) {
				
				// TODO Auto-generated catch block
				commonUTIL.displayError("openposSQL","getOpenPositionWhere",e);
				
			}
	     }
		 
	    
	}

	
	
	public static Openpos getOpenPositionOnID(int openID,Connection con)  {
		// TODO Auto-generated method stub
		String sql = " id = " + openID;
		Vector v1 = (Vector) getOpenPositionWhere(sql,con);
		return (Openpos)  v1.elementAt(0);
	}
	
}

