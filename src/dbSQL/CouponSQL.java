package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.Coupon;
import util.commonUTIL;


public class CouponSQL {
	
	final static private String DELETE_FROM_coupon =
		"DELETE FROM coupon where id =? ";
	final static private String INSERT_FROM_coupon =
		"INSERT into coupon(id,productId,couponType,FixedRate,CCY,DayCount,CouponAdjustMentMethod,CouponFrequency,BusinessDayConvention,CouponDate,Rating,Ex_dividend,recordDays,shutDays,AccrualDigits,PriceDecimals,YieldDecimals,NominalDecimals,AnnounceDate,AuctionDate,WithholdingTax,ApplyWithholdingTax,WhenIssueBond,TickSize,YieldMethod, QuoteType,Activefrom,tenor) values(COUPON_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	final static private String UPDATE_FROM_coupon =
		"UPDATE coupon set productId=?,couponType=?,FixedRate=?,CCY=?, DayCount=?," +
		"CouponAdjustMentMethod=?, CouponFrequency=?, BusinessDayConvention=?,CouponDate=?,Rating=?,Ex_dividend=?,recordDays=?,shutDays=?,AccrualDigits=?,PriceDecimals=?,YieldDecimals=?,NominalDecimals=?,AnnounceDate=?,AuctionDate=?,WithholdingTax=?, ApplyWithholdingTax=?, WhenIssueBond=?,TickSize=?,YieldMethod=?, QuoteType=?,Activefrom=? where id = ? ";
	final static private String SELECT_MAX =
		"SELECT MAX(id) DESC_ID FROM coupon ";
	final static private String SELECTALL =
		"SELECT id,productId,couponType,FixedRate,CCY, DayCount,CouponAdjustMentMethod, CouponFrequency, BusinessDayConvention,CouponDate,Rating,Ex_dividend,recordDays,shutDays,AccrualDigits,PriceDecimals,YieldDecimals,NominalDecimals,AnnounceDate,AuctionDate,WithholdingTax, ApplyWithholdingTax, WhenIssueBond,TickSize,YieldMethod, QuoteType,Activefrom,tenor FROM coupon order by id";
	final static private String SELECT =
		"SELECT productId FROM coupon where id =  ?";
	 static private String SELECTONE =
		"SELECT id,productId,couponType,FixedRate,CCY, DayCount,CouponAdjustMentMethod, CouponFrequency, BusinessDayConvention,CouponDate,Rating,Ex_dividend,recordDays,shutDays,AccrualDigits,PriceDecimals,YieldDecimals,NominalDecimals,AnnounceDate,AuctionDate,WithholdingTax, ApplyWithholdingTax, WhenIssueBond,TickSize,YieldMethod, QuoteType,Activefrom,tenor FROM coupon where id =  " ;
	 final static private String SELECTONPRODUCT =
			"SELECT id,productId,couponType,FixedRate,CCY, DayCount,CouponAdjustMentMethod, CouponFrequency, BusinessDayConvention,CouponDate,Rating,Ex_dividend,recordDays,shutDays,AccrualDigits,PriceDecimals,YieldDecimals,NominalDecimals,AnnounceDate,AuctionDate,WithholdingTax, ApplyWithholdingTax, WhenIssueBond,TickSize,YieldMethod, QuoteType,Activefrom,tenor FROM coupon where productid = ";
		
	 private static String getUpdateSQL(Coupon coupon) {
	      String updateSQL = " UPDATE coupon set productId="+ coupon.getProductId() +
	      		" ,couponType= '"+ coupon.getCouponType() + "'" + 
	      		" ,FixedRate= " + coupon.getFixedRate() + " ,CCY='"+coupon.getCCY()+"', DayCount='" + coupon.getDayCount() + "'" +
	      		" ,CouponAdjustMentMethod= '" + coupon.getCouponAdjustMentMethod() + "' ,CouponFrequency='"+coupon.getCouponFrequency()+
	      		"', BusinessDayConvention='" + coupon.getBusinessDayConvention() + "',CouponDate='" + coupon.getCouponDate() + 
	      		"', Rating ='"+ coupon.getRating()  +"',Ex_dividend="+coupon.getEx_dividend()+",recordDays="+coupon.getRecordDays() +  
	            ", shutDays="+ coupon.getShutDays()  +",AccrualDigits="+coupon.getAccrualDigits()+",PriceDecimals="+coupon.getPriceDecimals() +    
	            ", YieldDecimals="+ coupon.getYieldDecimals()  +", NominalDecimals="+coupon.getNominalDecimals()+",AnnounceDate='"+coupon.getAnnounceDate() +    
	            "', AuctionDate='"+ coupon.getAuctionDate()  +"',  WithholdingTax='"+coupon.getWithholdingTax()+"',ApplyWithholdingTax='"+coupon.getApplyWithholdingTax() +    
	            "', WhenIssueBond='"+ coupon.getWhenIssueBond() +"',  TickSize='"+coupon.getTickSize()+"',YieldMethod='"+coupon.getYieldMethod() +  
	            "', QuoteType='"+ coupon.getQuoteType() +"', Activefrom='"+coupon.getActivefrom()+"', tenor='" + coupon.getTenor() + "' " +
	            " where id =  "+coupon.getId();
	      
	      return updateSQL;
	     }
	 
	 
	 public static boolean save(Coupon insertCoupon, Connection con) {
		 try {
             return insert(insertCoupon, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("couponSQL","save",e);
        	 return false;
         }
	 }
	 public static boolean update(Coupon updateCoupon, Connection con) {
		 try {
             return edit(updateCoupon, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("CouponSQL","update",e);
        	 return false;
         }
	 }
	 
	 public static boolean delete(Coupon deleteCoupon, Connection con) {
		 try {
             return remove(deleteCoupon, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("CouponSQL","update",e);
        	 return false;
         }
	 }
	 public static Coupon selectCoupon(int CouponId, Connection con) {
		 try {
             return  select(CouponId, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("CouponSQL","select",e);
        	 return null;
         }
	 }
	 public static Collection selectALL(Connection con) {
		 try {
             return select(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("CouponSQL","selectALL",e);
        	 return null;
         }
	 }
	 
	 public static int selectMaxID(Connection con) {
		 try {
             return selectMax(con);
         }catch(Exception e) {
        	 commonUTIL.displayError("CouponSQL","selectMaxID",e);
        	 return 0;
         }
	 }
	 
	 protected static  boolean edit(Coupon updateCoupon, Connection con ) {
		 
	        PreparedStatement stmt = null;
	        String sql  = "";
		 try {
			 int j = 1;
			 con.setAutoCommit(false);
			 sql = getUpdateSQL(updateCoupon);
			    stmt = con.prepareStatement(sql);
			            stmt.executeUpdate(sql);
			  
			            
			            
	            con.commit();
	            
	          //  stmt.executeUpdate();
	            con.setAutoCommit(true);
	           
	            commonUTIL.display("CouponSQL","edit " + sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("CouponSQL ","edit " +sql,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("CouponSQL","edit",e);
			}
	        }
	        return true;
	 }
	
	protected static boolean remove(Coupon deleteCoupon, Connection con ) {
	
	        PreparedStatement stmt = null;
		 try {
			 int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, DELETE_FROM_coupon);
	            stmt.setInt(j++, deleteCoupon.getId());
	           
	            stmt.executeUpdate();
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("CouponSQL","remove",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("CouponSQL","remove",e);
			}
	        }
	        return true;
	 }

protected static int selectMax(Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);
	         
	         ResultSet rs = stmt.executeQuery();
	         while(rs.next())
	         j = rs.getInt("DESC_ID");
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("CouponSQL",SELECT_MAX,e);
			 return j;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("CouponSQL",SELECT_MAX,e);
			}
	        }
	        return j;
	 }
	 
	 protected static boolean insert(Coupon inserCoupon, Connection con ) {
			
	        PreparedStatement stmt = null;
		 try {
			 int id = selectMax(con);
			 con.setAutoCommit(false);
			 //int j = 1;
			 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_coupon);
			 	
			 	//stmt.setInt(1,id+1);
	            stmt.setInt(1, inserCoupon.getProductId());
	            stmt.setString(2, inserCoupon.getCouponType());
	            stmt.setDouble(3, inserCoupon.getFixedRate());
	            stmt.setString(4, inserCoupon.getCCY());
	            stmt.setString(5, inserCoupon.getDayCount());
	            stmt.setString(6, inserCoupon.getCouponAdjustMentMethod());
	            stmt.setString(7, inserCoupon.getCouponFrequency());
	            stmt.setString(8, inserCoupon.getBusinessDayConvention());
	            stmt.setString(9, inserCoupon.getCouponDate());
	            stmt.setString(10, inserCoupon.getRating());
	            stmt.setDouble(11, inserCoupon.getEx_dividend());
	            stmt.setInt(12, inserCoupon.getRecordDays());
	            stmt.setInt(13, inserCoupon.getShutDays());
	            stmt.setDouble(14, inserCoupon.getAccrualDigits());
	            stmt.setDouble(15, inserCoupon.getPriceDecimals());
	            stmt.setDouble(16, inserCoupon.getYieldDecimals());
	            stmt.setDouble(17, inserCoupon.getNominalDecimals());
	            stmt.setString(18, inserCoupon.getAnnounceDate());
	            stmt.setString(19, inserCoupon.getAuctionDate());
	            stmt.setString(20, inserCoupon.getWithholdingTax());
	            stmt.setString(21, inserCoupon.getApplyWithholdingTax());
	            stmt.setString(22, inserCoupon.getWhenIssueBond());
	            stmt.setString(23, inserCoupon.getTickSize());
	            stmt.setString(24, inserCoupon.getYieldMethod());
	            stmt.setString(25, inserCoupon.getQuoteType());
	            stmt.setString(26, inserCoupon.getActivefrom());
	           
	            
	            stmt.setString(27, inserCoupon.getTenor());
	           
	            
	            if (stmt.executeUpdate() > 0) {

					/*
					 * // getGeneratedKeys() returns result set of keys that were
					 * auto // generatedd ResultSet generatedKeys =
					 * stmt.getGeneratedKeys();
					 * 
					 * // if resultset has data, get the primary key value // of
					 * last inserted record if (null != generatedKeys &&
					 * generatedKeys.next()) {
					 * 
					 * tradeId = generatedKeys.getInt(1); }
					 */
					con.commit();
					 commonUTIL.display("CouponSQL",INSERT_FROM_coupon);
					 return true;

				} else {
					
					return false;
					
				}
	
			
		 } catch (Exception e) {
			 commonUTIL.displayError("CouponSQL",INSERT_FROM_coupon,e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("CouponSQL",INSERT_FROM_coupon,e);
			}
	        }
	        
	 }
	 
	 protected static Coupon select(int couponIn,Connection con ) {
		 
		 int j = 0;
	        PreparedStatement stmt = null;
	        Vector Coupons = new Vector();
	        Coupon coupon = new Coupon();
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE + couponIn);
	         
	         ResultSet rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	        
	        	 
	        	 coupon.setId(rs.getInt(1));
	        	 coupon.setProductId(rs.getInt(2));
	        	 coupon.setCouponType(rs.getString(3));
	        	 coupon.setFixedRate(rs.getDouble(4));
	        	 coupon.setCCY(rs.getString(5));
	        	 coupon.setDayCount(rs.getString(6));
	        	 coupon.setCouponAdjustMentMethod(rs.getString(7));
	        	 coupon.setCouponFrequency(rs.getString(8));
	        	 coupon.setBusinessDayConvention(rs.getString(9));
	        	 coupon.setCouponDate(rs.getString(10));
	        	 coupon.setRating(rs.getString(11));
	        	 coupon.setEx_dividend(rs.getInt(12));
	        	 coupon.setRecordDays(rs.getInt(13));
	        	 coupon.setShutDays(rs.getInt(14));
	        	 coupon.setAccrualDigits(rs.getDouble(15));
	        	 coupon.setPriceDecimals(rs.getDouble(16));
	        	 coupon.setYieldDecimals(rs.getDouble(17));
	        	 coupon.setNominalDecimals(rs.getDouble(18));
	        	 coupon.setAnnounceDate(rs.getString(19));
	        	 coupon.setAuctionDate(rs.getString(20));
	        	 coupon.setWithholdingTax(rs.getString(21));
	        	 coupon.setApplyWithholdingTax(rs.getString(22));
	        	 coupon.setWhenIssueBond(rs.getString(23));
	        	 coupon.setTickSize(rs.getString(24));
	        	 coupon.setYieldMethod(rs.getString(25));
	        	 coupon.setQuoteType(rs.getString(26));
	        	 coupon.setActivefrom(rs.getString(27));
	        	 coupon.setTenor(rs.getString(28));
	        	 
	        
	       
	        
	      
	       return coupon;
	         
	         }
		 } catch (Exception e) {
			 commonUTIL.displayError("couponSQL","select",e);
			 return coupon;
	           
	        }
	        finally {
	           try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("couponSQL","selectMax",e);
			}
	        }
	        return coupon;
	 }

	 protected static Collection select(Connection con) { 
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector coupons = new Vector();
	     
		 try {
			
			 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	   
	    	  Coupon coupon = new Coupon();
	    	  
	    	  
	    	  coupon.setId(rs.getInt(1));
	        	 coupon.setProductId(rs.getInt(2));
	        	 coupon.setCouponType(rs.getString(3));
	        	 coupon.setFixedRate(rs.getInt(4));
	        	 coupon.setCCY(rs.getString(5));
	        	 coupon.setDayCount(rs.getString(6));
	        	 coupon.setCouponAdjustMentMethod(rs.getString(7));
	        	 coupon.setCouponFrequency(rs.getString(8));
	        	 coupon.setBusinessDayConvention(rs.getString(9));
	        	 coupon.setCouponDate(rs.getString(10));
	        	 coupon.setRating(rs.getString(11));
	        	 coupon.setEx_dividend(rs.getInt(12));
	        	 coupon.setRecordDays(rs.getInt(13));
	        	 coupon.setShutDays(rs.getInt(14));
	        	 coupon.setAccrualDigits(rs.getInt(15));
	        	 coupon.setPriceDecimals(rs.getInt(16));
	        	 coupon.setYieldDecimals(rs.getInt(17));
	        	 coupon.setNominalDecimals(rs.getInt(18));
	        	 coupon.setAnnounceDate(rs.getString(19));
	        	 coupon.setAuctionDate(rs.getString(20));
	        	 coupon.setWithholdingTax(rs.getString(21));
	        	 coupon.setApplyWithholdingTax(rs.getString(22));
	        	 coupon.setWhenIssueBond(rs.getString(23));
	        	 coupon.setTickSize(rs.getString(24));
	        	 coupon.setYieldMethod(rs.getString(25));
	        	 coupon.setQuoteType(rs.getString(26));
	        	 coupon.setActivefrom(rs.getString(27));
	        	 coupon.setTenor(rs.getString(28));
	        
	        
	        coupons.add(coupon);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("couponSQL",SELECTALL,e);
			 return coupons;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("couponSQL",SELECTALL,e);
			}
	     }
	     return coupons;
	 }
	 protected static Collection selectcoupon(int couponId,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector coupons = new Vector();
	     
		 try {
			 SELECTONE = SELECTONE + couponId;
			 stmt = dsSQL.newPreparedStatement(con, SELECTONE );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Coupon coupon = new Coupon();
	    	  
	    	  	
	    	  	 coupon.setId(rs.getInt(1));
	        	 coupon.setProductId(rs.getInt(2));
	        	 coupon.setCouponType(rs.getString(3));
	        	 coupon.setFixedRate(rs.getInt(4));
	        	 coupon.setCCY(rs.getString(5));
	        	 coupon.setDayCount(rs.getString(6));
	        	 coupon.setCouponAdjustMentMethod(rs.getString(7));
	        	 coupon.setCouponFrequency(rs.getString(8));
	        	 coupon.setBusinessDayConvention(rs.getString(9));
	        	 coupon.setCouponDate(rs.getString(10));
	        	 coupon.setRating(rs.getString(11));
	        	 coupon.setEx_dividend(rs.getInt(12));
	        	 coupon.setRecordDays(rs.getInt(13));
	        	 coupon.setShutDays(rs.getInt(14));
	        	 coupon.setAccrualDigits(rs.getInt(15));
	        	 coupon.setPriceDecimals(rs.getInt(16));
	        	 coupon.setYieldDecimals(rs.getInt(17));
	        	 coupon.setNominalDecimals(rs.getInt(18));
	        	 coupon.setAnnounceDate(rs.getString(19));
	        	 coupon.setAuctionDate(rs.getString(20));
	        	 coupon.setWithholdingTax(rs.getString(21));
	        	 coupon.setApplyWithholdingTax(rs.getString(22));
	        	 coupon.setWhenIssueBond(rs.getString(23));
	        	 coupon.setTickSize(rs.getString(24));
	        	 coupon.setYieldMethod(rs.getString(25));
	        	 coupon.setQuoteType(rs.getString(26));
	        	 coupon.setActivefrom(rs.getString(27));
	        	 coupon.setTenor(rs.getString(28));
		        
		        coupons.add(coupon);
	      
	      }
		 } catch (Exception e) {
			 commonUTIL.displayError("couponSQL","selectcoupon",e);
			 return coupons;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("couponSQL","selectMax",e);
			}
	     }
	     return coupons;
	 }
	 
	 public static Collection selectcouponOnProduct(int productid,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Vector coupons = new Vector();
	     
		 try {
			 con.setAutoCommit(true);
			 String sql = SELECTONPRODUCT + productid;
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	  Coupon coupon = new Coupon();
	    	  
	    	  	
	    	  	 coupon.setId(rs.getInt(1));
	        	 coupon.setProductId(rs.getInt(2));
	        	 coupon.setCouponType(rs.getString(3));
	        	 coupon.setFixedRate(rs.getDouble(4));
	        	 coupon.setCCY(rs.getString(5));
	        	 coupon.setDayCount(rs.getString(6));
	        	 coupon.setCouponAdjustMentMethod(rs.getString(7));
	        	 coupon.setCouponFrequency(rs.getString(8));
	        	 coupon.setBusinessDayConvention(rs.getString(9));
	        	 coupon.setCouponDate(rs.getString(10));
	        	 coupon.setRating(rs.getString(11));
	        	 coupon.setEx_dividend(rs.getInt(12));
	        	 coupon.setRecordDays(rs.getInt(13));
	        	 coupon.setShutDays(rs.getInt(14));
	        	 coupon.setAccrualDigits(rs.getInt(15));
	        	 coupon.setPriceDecimals(rs.getInt(16));
	        	 coupon.setYieldDecimals(rs.getInt(17));
	        	 coupon.setNominalDecimals(rs.getInt(18));
	        	 coupon.setAnnounceDate(rs.getString(19));
	        	 coupon.setAuctionDate(rs.getString(20));
	        	 coupon.setWithholdingTax(rs.getString(21));
	        	 coupon.setApplyWithholdingTax(rs.getString(22));
	        	 coupon.setWhenIssueBond(rs.getString(23));
	        	 coupon.setTickSize(rs.getString(24));
	        	 coupon.setYieldMethod(rs.getString(25));
	        	 coupon.setQuoteType(rs.getString(26));
	        	 coupon.setActivefrom(rs.getString(27));
	        	 coupon.setTenor(rs.getString(28));
		        
		        coupons.add(coupon);
	      
	      }
	      commonUTIL.display("couponSQL",sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("couponSQL","selectcouponOnProduct",e);
			 return coupons;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("couponSQL","selectcouponOnProduct",e);
			}
	     }
	     return coupons;
	 }
	 public static Coupon getcouponOnProduct(int productId,Connection con ) {
		 int j = 0;
	     PreparedStatement stmt = null;
	     Coupon coupon = new Coupon();
	     
		 try {
			 con.setAutoCommit(true);
			 String sql = SELECTONPRODUCT + productId;
			 stmt = dsSQL.newPreparedStatement(con, sql );
	      
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	    	 // Coupon coupon = new Coupon();
	    	  
	    	  	
	    	  	 coupon.setId(rs.getInt(1));
	        	 coupon.setProductId(rs.getInt(2));
	        	 coupon.setCouponType(rs.getString(3));
	        	 coupon.setFixedRate(rs.getDouble(4));
	        	 coupon.setCCY(rs.getString(5));
	        	 coupon.setDayCount(rs.getString(6));
	        	 coupon.setCouponAdjustMentMethod(rs.getString(7));
	        	 coupon.setCouponFrequency(rs.getString(8));
	        	 coupon.setBusinessDayConvention(rs.getString(9));
	        	 coupon.setCouponDate(rs.getString(10));
	        	 coupon.setRating(rs.getString(11));
	        	 coupon.setEx_dividend(rs.getInt(12));
	        	 coupon.setRecordDays(rs.getInt(13));
	        	 coupon.setShutDays(rs.getInt(14));
	        	 coupon.setAccrualDigits(rs.getInt(15));
	        	 coupon.setPriceDecimals(rs.getInt(16));
	        	 coupon.setYieldDecimals(rs.getInt(17));
	        	 coupon.setNominalDecimals(rs.getInt(18));
	        	 coupon.setAnnounceDate(rs.getString(19));
	        	 coupon.setAuctionDate(rs.getString(20));
	        	 coupon.setWithholdingTax(rs.getString(21));
	        	 coupon.setApplyWithholdingTax(rs.getString(22));
	        	 coupon.setWhenIssueBond(rs.getString(23));
	        	 coupon.setTickSize(rs.getString(24));
	        	 coupon.setYieldMethod(rs.getString(25));
	        	 coupon.setQuoteType(rs.getString(26));
	        	 coupon.setActivefrom(rs.getString(27));
	        	 coupon.setTenor(rs.getString(28));
		        
		       // coupons.add(coupon);
	      
	      }
	      commonUTIL.display("couponSQL",sql);
		 } catch (Exception e) {
			 commonUTIL.displayError("couponSQL","getcouponOnProduct",e);
			 return coupon;
	        
	     }
	     finally {
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("couponSQL","getcouponOnProduct",e);
			}
	     }
	     return coupon;
	 }

}
