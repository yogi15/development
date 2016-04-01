package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.commonUTIL;
import beans.Product;
import beans.ProductFXOption;

public class FXOptionProductSQL {

	
	static String SELECT = "SELECT PRODUCT_ID,CURRENCY_BASE,CURRENCY_QUOTE,OPTION_STYLE,EXERCISE_TYPE,OPTION_TYPE,OPTION_STRIKE," +
			"EXPIRY_DATE,PRIMARY_AMOUNT,QUOTING_AMOUNT,SETTLEDATE,FIRST_EX_DATE,HOLIDAYS,DEL_HOLIDAYS,OPTION_SIDE,SETTLEMENT_TYPE," +
			"AUTO_EXERCISE,EXPIRY_TIME,EXPIRY_TIMEZONE,SETTLE_CURRENCY,QUANTO_FACTOR,QUANTO_CURRENCY_PAIR,EXERCISED_DATE,FXRESET,SPOT_DATE,OPT_CAL_OFFSET,OPT_CAL_BUS_B,CALC_OFFSET_DAYS_B,RESET_DATE,COMPO_FX_SOURCE FROM  FXOPTION_PRODUCT where product_id = "; 
	
	static String insert = "insert into fxoption_product(PRODUCT_ID,CURRENCY_BASE,CURRENCY_QUOTE,OPTION_STYLE,EXERCISE_TYPE,OPTION_TYPE,OPTION_STRIKE,EXPIRY_DATE,PRIMARY_AMOUNT,QUOTING_AMOUNT,SETTLEDATE,FIRST_EX_DATE,HOLIDAYS,DEL_HOLIDAYS,OPTION_SIDE,SETTLEMENT_TYPE,AUTO_EXERCISE,EXPIRY_TIME,EXPIRY_TIMEZONE,SETTLE_CURRENCY,QUANTO_FACTOR,QUANTO_CURRENCY_PAIR,EXERCISED_DATE,FXRESET,SPOT_DATE,OPT_CAL_OFFSET,OPT_CAL_BUS_B,CALC_OFFSET_DAYS_B,RESET_DATE,COMPO_FX_SOURCE ) " +
			"          values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	static String tableName  = "fxoption_product";
	
	private static String getUpdateSQL(ProductFXOption product) {
	      String updateSQL = new StringBuffer("UPDATE ")
	      	.append(tableName)
	      	.append(" set CURRENCY_BASE= '").append(product.getCurrencyBase()).append("',")
	      	.append(" CURRENCY_QUOTE='").append(product.getCurrencyQuote()).append("',")
			.append(" OPTION_STYLE='").append(product.getOptionType()).append("',")
			.append(" EXERCISE_TYPE='").append(product.getExericseType()).append("',")
		//	.append(" issueDate='").append(product.getIssueDate()).append("',")
			.append(" OPTION_TYPE='").append(product.getOptionType()).append("',")
			.append(" OPTION_STRIKE=").append(product.getOptionStrike()).append(",")
			.append(" EXPIRY_DATE='").append(product.getExpiryDate()).append("',")
			.append(" PRIMARY_AMOUNT=").append(product.getPrimaryAmount()).append(",")
			.append(" QUOTING_AMOUNT=").append(product.getQuotingAmount()).append(",")
			.append(" SETTLEDATE='").append(product.getSettleDate()).append("',")
			.append(" FIRST_EX_DATE='").append(product.getFirstExDate()).append("',")
			.append(" HOLIDAYS='").append(product.getHolidays()).append("',")
			.append(" DEL_HOLIDAYS='").append(product.getDelHolidays()).append("',")
			.append(" OPTION_SIDE='").append(product.getOptionSide()).append("',")
			.append(" SETTLEMENT_TYPE='").append(product.getSettlementType()).append("',")
			.append(" AUTO_EXERCISE='").append(product.getAutoExercised()).append("',")
			.append(" EXPIRY_TIME='").append(product.getExpiryTime()).append("',")
			.append(" EXPIRY_TIMEZONE=").append(product.getExpriy_TimeZone()).append(",")
			.append(" SETTLE_CURRENCY='").append(product.getSettle_Currency()).append("',")
			.append(" QUANTO_FACTOR=").append(product.getQuanto_Factor()).append(",")
			.append(" QUANTO_CURRENCY_PAIR='").append(product.getQuanto_Currency_Pair()).append("',")
			.append(" EXERCISED_DATE='").append(product.getExercised_DATE()).append("',")
			.append(" FXRESET=").append(product.getFxreset()).append(",")
			.append(" SPOT_DATE='").append(product.getSpotDate()).append("',")
			.append(" OPT_CAL_OFFSET='").append(product.getOpt_Cal_Offset()).append("',")
			.append(" OPT_CAL_BUS_B='").append(product.getOpt_Cal_Bus_B()).append("',")
			
			.append(" COMPO_FX_SOURCE='").append(product.getCompo_fx_source()).append("',")
			.append(" CALC_OFFSET_DAYS_B='").append(product.getCalcOffsetDays_B()).append("'")
			
			.append("where 	id = ").append(product.getId()).toString();
	      //System.out.println(updateSQL);
	      return updateSQL;
	     }
	
	
	 public static boolean save(ProductFXOption insertProduct, Connection con) {
		 try {
			
				
			 
			 
             return insert(insertProduct, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("ProductFXOptionSQL","save",e);
        	 return false;
         }
	 }
	 
	 public static boolean update(ProductFXOption updateProduct, Connection con) {
		 try {
             return edit(updateProduct, con);
         }catch(Exception e) {
        	 commonUTIL.displayError("ProductSQL","update",e);
        	 return false;
         }
	 }
	 public static ProductFXOption select(int productID,Connection con) {
		 ProductFXOption insertProduct = new ProductFXOption();
		  PreparedStatement stmt = null;
		  String sql = "";
		  try{
			sql = SELECT  + productID;
			  stmt = dsSQL.newPreparedStatement(con, sql );
		         
		         ResultSet rs = stmt.executeQuery();
		         
		         while(rs.next()) {
		        	 insertProduct.setProduct_id(rs.getInt(1));
		             insertProduct.setCurrencyBase(rs.getString(2));
		             insertProduct.setCurrencyQuote(rs.getString(3));
		           insertProduct.setOptionType(rs.getString(4));
		            insertProduct.setExericseType(rs.getString(5));
		             insertProduct.setOptionType(rs.getString(6));
		              insertProduct.setOptionStrike(rs.getDouble(7));
		                insertProduct.setExpiryDate(rs.getString(8));
		                insertProduct.setPrimaryAmount(rs.getDouble(9));
		               insertProduct.setQuotingAmount(rs.getDouble(10));
		               insertProduct.setSettleDate(rs.getString(11));
		                 insertProduct.setFirstExDate(rs.getString(12));
		               insertProduct.setHolidays(rs.getString(13));
		                insertProduct.setDelHolidays(rs.getString(14));
		                insertProduct.setOptionSide(rs.getString(15));
		             insertProduct.setSettlementType(rs.getString(16)); 
		      
		            insertProduct.setAutoExercised(rs.getDouble(17));
		       insertProduct.setExpiryTime(rs.getDouble(18));
		      insertProduct.setExpriy_TimeZone(rs.getString(19));
		     insertProduct.setSettle_Currency(rs.getString(20));
		     insertProduct.setQuanto_Factor(rs.getDouble(21));
		      insertProduct.setQuanto_Currency_Pair(rs.getString(22));
		     insertProduct.setExercised_DATE(rs.getString(23));
		      insertProduct.setFxreset(rs.getDouble(24));
		     insertProduct.setSpotDate(rs.getString(25));
		      insertProduct.setOpt_Cal_Offset(rs.getDouble(26));
		     insertProduct.setOpt_Cal_Bus_B(rs.getDouble(27));
		      insertProduct.setCalcOffsetDays_B(rs.getDouble(28));
		      insertProduct.setReset_Date(rs.getString(29));
		          
		      insertProduct.setCompo_fx_source(rs.getDouble(30));
		         }
		  } catch (Exception e) {
				 commonUTIL.displayError("FXOptionProductSQL",sql,e);
				 return insertProduct;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("FXOptionProductSQL",sql,e);
				}
		        }
		 
		 return insertProduct;
	 }
	private static boolean edit(ProductFXOption updateProduct, Connection con) {
		// TODO Auto-generated method stub
		 PreparedStatement stmt = null;
		 try {
			
	            
			
	           con.setAutoCommit(false);
	            
			 int j = 1;
			 String UPDATE = getUpdateSQL(updateProduct);
			 stmt = dsSQL.newPreparedStatement(con,UPDATE );
			 stmt.executeUpdate();
	            con.commit();
	            commonUTIL.display("FXOptionProductSQL",UPDATE);
		        //stmt.executeUpdate();
				 
			 } catch (Exception e) {
				 commonUTIL.displayError("FXOptionProductSQL","edit",e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("FXOptionProductSQL","edit",e);
				}
		        }
		        return true;
	}


	private static boolean insert(ProductFXOption insertProduct, Connection con) {
		// TODO Auto-generated method stub
		 PreparedStatement stmt = null;
		 try {
			// id = selectMax(con);
			 
			 int j = 1;
			 con.setAutoCommit(false);
			 stmt = dsSQL.newPreparedStatement(con, insert);
			 stmt.setInt(1,insertProduct.getProduct_id());
	            stmt.setString(2, insertProduct.getCurrencyBase());
	            stmt.setString(3,  insertProduct.getCurrencyQuote());
	            stmt.setString(4, insertProduct.getOptionType());
	            stmt.setString(5, insertProduct.getExericseType());
	           stmt.setString(6, insertProduct.getOptionType());
   	            stmt.setDouble(7, insertProduct.getOptionStrike());
   	            stmt.setString(8, insertProduct.getExpiryDate());
   	           stmt.setDouble(9, insertProduct.getPrimaryAmount());
   	            stmt.setDouble(10, insertProduct.getQuotingAmount());
   	            stmt.setString(11, insertProduct.getSettleDate());
   	            stmt.setString(12, insertProduct.getFirstExDate());
   	            stmt.setString(13, insertProduct.getHolidays());
   	            stmt.setString(14, insertProduct.getDelHolidays());
   	            stmt.setString(15, insertProduct.getOptionSide());
   	         stmt.setString(16, insertProduct.getSettlementType()); 
   	  
   	      stmt.setDouble(17, insertProduct.getAutoExercised());
   	   stmt.setDouble(18, insertProduct.getExpiryTime());
   	stmt.setString(19, insertProduct.getExpriy_TimeZone());
 	stmt.setString(20, insertProduct.getSettle_Currency());
 	stmt.setDouble(21, insertProduct.getQuanto_Factor());
 	stmt.setString(22, insertProduct.getQuanto_Currency_Pair());
 	stmt.setString(23, insertProduct.getExercised_DATE());
 	stmt.setDouble(24, insertProduct.getFxreset());
 	stmt.setString(25, insertProduct.getSpotDate());
 	stmt.setDouble(26, insertProduct.getOpt_Cal_Offset());
 	stmt.setDouble(27, insertProduct.getOpt_Cal_Bus_B());
 	stmt.setDouble(28, insertProduct.getCalcOffsetDays_B());
 	stmt.setString(29, insertProduct.getReset_Date());
   	      
 	stmt.setDouble(30, insertProduct.getCompo_fx_source());
   	           
	            stmt.executeUpdate();
	            con.commit();
	            commonUTIL.display("productSQL",insert);
	            return true;
			 
		 } catch (Exception e) {
			 commonUTIL.displayError("PrdocutSQL","insert",e);
			 return false;
	           
	        }
	        finally {
	           try {
				stmt.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("PrdocutSQL","insert",e);
			}
	
}
	}
}
