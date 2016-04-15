package apps.window.reportwindow.util;

import java.util.Date;
import java.util.Hashtable;
import java.util.StringTokenizer;

import util.commonUTIL;

import beans.Book;

public class ReportSQLGenerator {
	 static Hashtable<String,String> tableNames = new   Hashtable<String,String>();
	 static {
	 tableNames.put("Trade", "Trade");
	 tableNames.put("Transfer", "Transfer");
	 tableNames.put("CashPosition", "Openpos");
	 tableNames.put("Posting", "Posting");
	 tableNames.put("Product", "Product");
	 tableNames.put("Coupon", "Coupon");
	 tableNames.put("Book", "Book");
	 tableNames.put("Le", "Le");
	 tableNames.put("Trader", "Le");
	 tableNames.put("Cashposition", "Cashposition");
	 tableNames.put("PNL", "Liquidpos");
	 tableNames.put("Fees", "Fees");
	 tableNames.put("TradeAttribute", "TradeAttribute");
	 }
	public String createBookProductSQL(String productType,
			String productSubType, String bookid, String le, String traderID,
			String currency) {
		// TODO Auto-generated method stub
		String sqlWhere = "";
		String productWhere = "";
		String book = "";
		String curre = "";
		String trader = "";
		String legalEntity = "";
		if (productType.length() > 0 && productType != null) {
			productWhere = productWhere + " trade.productType= '" + productType
					+ "'";
			if (productSubType.length() > 0 && productSubType != null) {
				productWhere = productWhere + " and  trade.tradedesc1 ='"
						+ productSubType + "'";
			}
		}
		sqlWhere = productWhere;
		if (bookid.length() > 0 && bookid != null) {
			book = " trade.bookid in(" + bookid + ")";
			if (sqlWhere.length() > 0) {
				sqlWhere = sqlWhere + " and " + book;
			} else {
				sqlWhere = book;
			}

		}

		if (le.length() > 0 && le != null) {
			legalEntity = " trade.cpid in(" + le + ")";
			if (sqlWhere.length() > 0) {
				sqlWhere = sqlWhere + " and " + legalEntity;
			} else {
				sqlWhere = legalEntity;
			}
		}
		if (traderID.length() > 0 && bookid != null) {
			trader = " trade.traderID in(" + traderID + ")";
			if (sqlWhere.length() > 0) {
				sqlWhere = sqlWhere + " and " + trader;
			} else {
				sqlWhere = trader;
			}
		}

		if (currency != null && currency.length() > 0) {

			curre = " trade.currency in('" + genearateIn(currency) + "')";
			if (sqlWhere.length() > 0) {
				sqlWhere = sqlWhere + " and " + curre;
			} else {
				sqlWhere = curre;
			}

		}
		return sqlWhere;
	}

	private String genearateIn(String value) {
		// TODO Auto-generated method stub
		String curr = "";
		if (!value.contains(","))
			return value;
		if (value.length() > 0) {
			String values[] = value.split(",");
			for (int i = 0; i < values.length; i++) {
				curr = curr + values[i] + "','";
			}
			curr = curr.substring(0, curr.length() - 3);
		}
		return curr;
	}

	private StringTokenizer StringTokenizer(String values) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getjoinSQL(String tablename, String reportType) {
	//	String reportType = tableNames.get(reportType1);
		if(reportType.equalsIgnoreCase("Cashposition") || reportType.equalsIgnoreCase("PNL")  || reportType.equalsIgnoreCase("CashLedgerPosition") ) {  // this is a bug rethink of logic.
			reportType = "trade";
		}
		// TODO Auto-generated method stub
		String sqlJoins = "";
		String coupon = " coupon c";
		boolean attachWhere = false;

		if (tablename.contains("product")) {
			attachWhere = true;
			sqlJoins = reportType + ".productid = product.id ";
		}
		
		if (tablename.contains("book")) {
			attachWhere = true;
			if (sqlJoins.length() > 0)
				sqlJoins = sqlJoins + " and " + reportType
						+ ".bookid = book.bookno ";
			else
				sqlJoins = reportType + ".bookid = book.bookno ";
		}
		if (tablename.contains("Fees")) {
			attachWhere = true;
			if (sqlJoins.length() > 0)
				sqlJoins = sqlJoins + " and " + reportType
						+ ".id = Fees.tradeid (+) ";
			else
				sqlJoins = reportType + ".id = Fees.tradeid (+) ";
		}
		if (tablename.contains("legalEntity")) {
			attachWhere = true;
			if (sqlJoins.length() > 0)
				sqlJoins = sqlJoins + " and " + reportType
						+ ".cpid = legalEntity.id ";
			else
				sqlJoins = reportType + ".cpid = legalEntity.id ";
		}
		if (tablename.contains("Trader")) {
			attachWhere = true;
			if (sqlJoins.length() > 0)
				sqlJoins = sqlJoins + " and " + reportType
						+ ".traderid = Trader.id ";
			else
				sqlJoins = reportType + ".traderid = Trader.id ";
		}
		if (tablename.contains("coupon")) {
			attachWhere = true;
			if (sqlJoins.length() > 0)
				sqlJoins = sqlJoins + " and " + reportType
						+ ".productid = coupon.productid ";
			else
				sqlJoins = reportType + ".productid = coupon.productid ";
		}
		if ((tablename.contains("transfer"))
				&& (!reportType.equalsIgnoreCase("Transfer"))) {
			attachWhere = true;
			if (sqlJoins.length() > 0)
				sqlJoins = sqlJoins + " and " + reportType
						+ ".id = transfer.tradeid ";
			else
				sqlJoins = reportType + ".id = transfer.tradeid ";

		}
		
		if ((tablename.contains("Cashposition"))
				&& (!reportType.equalsIgnoreCase("Cashposition"))) {
			attachWhere = true;
			if (sqlJoins.length() > 0)
				sqlJoins = sqlJoins + " and " + reportType
						+ ".id = cashposition.tradeid ";
			else
				sqlJoins = reportType + ".id = cashposition.tradeid ";

		}
		if ((tablename.contains("PNL")))
				 {
			attachWhere = true;
			if (sqlJoins.length() > 0)
				sqlJoins = sqlJoins + " and " + reportType
						+ ".id = PNL.stradeid ";
			else
				sqlJoins = reportType + ".id = PNL.stradeid ";

		}
		/* if(tablename.contains("ATTRIBUTE")) {
			 attachWhere = true;
				if (sqlJoins.length() > 0)
					sqlJoins = sqlJoins + " and " + reportType
							+ ".id = attribute.id ";
				else
					sqlJoins = reportType + ".id = attribute.id ";
	        } */
		
		//@ yogesh
		// 04/02/2015
		//where part was generating like transfer.is = trade.id. if is added to generate
		// reportType + ".tradeid = trade.id
		if ((tablename.contains("trade"))
				&& (!reportType.equalsIgnoreCase("Trade"))) {
			attachWhere = true;
			if (sqlJoins.length() > 0) {
				sqlJoins = sqlJoins + " and " + reportType + ".id = trade.id ";
			} else if (!reportType.equalsIgnoreCase("Trade")) {
				sqlJoins = reportType + ".tradeid = trade.id ";
			} else {
				sqlJoins = reportType + ".id = trade.id ";
			}		
		}
		if (attachWhere)
			return " where " + sqlJoins;
       
		return sqlJoins;

	}

	public String getDatesWhereClause(Date startDate, Date endDate,
			String columnName) {
		String dateWhereClause = "";
		dateWhereClause = columnName + " between  to_date('"
				+ commonUTIL.getDateFormat(startDate).trim()
				+ "', 'dd/MM/YYYY')";
		dateWhereClause = dateWhereClause + " and to_date('"
				+ commonUTIL.getDateFormat(endDate).trim() + "', 'dd/MM/YYYY')";

		return dateWhereClause;

	}

	public String getSQLTables(String tablename) {
		// TODO Auto-generated method stub
		String tableName = "";
		if (tablename.contains("Trade.") ) {
			tableName = " Trade trade ";

		}
		/*if (tablename.contains("Attributes.")) {
			if (tableName.length() > 0) {
				tableName = tableName + ", ATTRIBUTE  attribute ";

			} else {
				tableName = tableName + " ATTRIBUTE  attribute ";
			}
			

		} */
		if (tablename.contains("Product.")) {
			if (tableName.length() > 0) {
				tableName = tableName + ", Product product ";

			} else {
				tableName = tableName + "Product product ";
			}

		}
		if (tablename.contains("Fees.")) {
			if (tableName.length() > 0) {
				tableName = tableName + ", Fees fees ";

			} else {
				tableName = tableName + "Fees fees ";
			}

		}
		if (tablename.contains("Book.")) {
			if (tableName.length() > 0) {
				tableName = tableName + ", Book book ";
			} else {
				tableName = tableName + " Book book ";
			}
		}
		if (tablename.contains("Trader.")) {
			if (tableName.length() > 0) {
				tableName = tableName + ",Le Trader";
			} else {
				tableName = tableName + " Le Trader ";
			}
		}
		if (tablename.contains("LegalEntity.")) {
			if (tableName.length() > 0) {
				tableName = tableName + ",Le legalEntity";
			} else {
				tableName = tableName + " Le legalEntity ";
			}
		}
		if (tablename.contains("Coupon.")) {
			if (tableName.length() > 0) {
				tableName = tableName + ",Coupon coupon ";
			} else {
				tableName = tableName + " Coupon coupon ";
			}
		}
		if (tablename.contains("Transfer.")) {
			if (tableName.length() > 0) {
				tableName = tableName + ",Transfer transfer ";
			} else {
				tableName = tableName + " Transfer transfer ";
			}
		}
		if (tablename.contains("Cashposition.")) {
			if (tableName.length() > 0) {
				tableName = tableName + ",Cashposition cashposition ";
			} else {
				tableName = tableName + " Cashposition cashposition ";
			}
		}
		if (tablename.contains("Posting.")) {
			if (tableName.length() > 0) {
				tableName = tableName + ",Posting posting ";
			} else {
				tableName = tableName + " Posting posting ";
			}
		}
		if (tablename.contains("PNL.")) {
			if (tableName.length() > 0) {
				tableName = tableName + ", Liquidpos  PNL ";
			} else {
				tableName = tableName + " Liquidpos  PNL ";
			}
		}
		return tableName;

	}

	
	// Generate Columns where Popup is closed.
	
	public String getSQLColumns(Object[] obj) {
		
		String SQLcolumnsName = "";
		String columnName = "";
		
		for (int i = 0; i < obj.length; i++) {
			
			columnName = ReportColumns.getColumnName((String) obj[i]);
			// this to get columns from hashtable
			
			if (!commonUTIL.isEmpty(columnName)) {
				
				SQLcolumnsName = SQLcolumnsName
				+columnName  + ",";
				
			}
						
		}
			
		if (SQLcolumnsName.trim().length() > 0) {
			SQLcolumnsName = SQLcolumnsName.substring(0,
					SQLcolumnsName.length() - 1);

		}
		return SQLcolumnsName;
	}

}
