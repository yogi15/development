package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.AttributeContainer;
import beans.BaseBean;
import beans.Book;
import beans.Coupon;
import beans.Product;
import constants.BeanConstants;
import util.commonUTIL;

public class ProductSQL extends BaseSQL {

	final static private String tableName = "product";
	final static private String DELETE = "DELETE FROM " + tableName + "   where id =? ";
	final static private String INSERT = "INSERT into " + tableName
			+ "(ID,PRODUCTTYPE,PRODUCTNAME,PRODUCTSHORTNAME,QUANTITY,ISSUEDATE,ISSUERID,"
			+ "COUNTRY,ISSUEPRICE,ISSUECURRENCY,REDEMPTIONPRICE,REDEMPTIONCURRENCY,"
			+ "FACEVALUE,CODE,DATEDDATE,TENOR,ATTRIBUTES,ISPOSITIONBASED,COLLATERALID,"
			+ "CALLABLETYPE,REPOTYPE,PRODUCTSUBTYPE,"
			+ "STATUS,EXCHANGE,CUSIP,SEDOL,ISIN,SYMBOL,MATURITYDATE,CURRENCY ) values"
			+ "(?,?,?,?,?,?,?,?,?"
			+ ",?,?,?,?,?,?,?,?,?,"
			+ "?,?,?,?,?,?,?,?,?,?,"
			+ "?,?)";
	// "issueDate,marturityDate,IssuerId,Country,IssuePrice,IssueCurrency,RedemptionPrice,RedemptionCurrency,FaceValue,code,dateddate,tenor
	// ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static String getUpdateSQL(Product product) {
		String updateSQL = new StringBuffer("UPDATE ").append(tableName).append(" set productType= '")
				.append(product.getProductType()).append("',").append(" productName='").append(product.getProductname())
				.append("',").append(" productShortName='").append(product.getProdcutShortName()).append("',")
				.append(" quantity=").append(product.getQuantity()).append(",").append(" issueDate='")
				.append(product.getIssueDate()).append("',").append(" MATURITYDATE='")
				.append(product.getMarturityDate()).append("',").append(" IssuerId=").append(product.getIssuerId())
				.append(",").append(" Country='").append(product.getCountry()).append("',").append(" IssuePrice=")
				.append(product.getIssuePrice()).append(",").append(" IssueCurrency='")
				.append(product.getIssueCurrency()).append("',").append(" RedemptionPrice=")
				.append(product.getRedemptionPrice()).append(",").append(" RedemptionCurrency='")
				.append(product.getRedemptionCurrency()).append("',").append(" FaceValue=")
				.append(product.getFaceValue()).append(",").append(" code='").append(product.getCode()).append("',")
				.append(" Dateddate='").append(product.getDatedDate()).append("',").append(" Tenor='")
				.append(product.getTenor()).append("',").append(" attributes='").append(product.getAttributes())
				.append("',").append(" isPositionBased=").append(product.getIsPosition()).append(",")
				.append(" collateralId=").append(product.getCollateralID()).append(",").append(" callableType='")
				.append(product.getCallType()).append("',").append(" repoType='").append(product.getRepoType())
				.append("',").append(" currency='").append(product.getCurrency()).append("',")
				.append(" productSubType='").append(product.getProductSubType()).append("',")
				.append("STATUS='")
				.append(product.getSTATUS()).append("',").append("EXCHANGE='").append(product.getEXCHANGE())
				.append("',").append("CUSIP='").append(product.getCUSIP()).append("',").append("SEDOL='")
				.append(product.getSEDOL()).append("',").append("ISIN='").append(product.getISIN()).append("',")
				.append("SYMBOL='").append(product.getSYMBOL()).append("' where 	id = ")
				.append(product.getId()).toString();
		// System.out.println(updateSQL);
		return updateSQL;
	}

	final static private String SELECT_MAX = " SELECT PRODUCT_SEQ.NEXTVAL DESC_ID FROM DUAL";
	final static private String SELECTALL = "SELECT ID,PRODUCTTYPE,PRODUCTNAME,PRODUCTSHORTNAME,QUANTITY,ISSUEDATE,ISSUERID,COUNTRY,ISSUEPRICE,ISSUECURRENCY,REDEMPTIONPRICE,REDEMPTIONCURRENCY,FACEVALUE,CODE,DATEDDATE,TENOR,ATTRIBUTES,ISPOSITIONBASED,COLLATERALID,CALLABLETYPE,REPOTYPE,PRODUCTSUBTYPE,STATUS,EXCHANGE,CUSIP,SEDOL,ISIN,SYMBOL,MATURITYDATE,CURRENCY FROM "
			+ tableName + " order by id ";
	final static private String SELECT = "SELECT ID,PRODUCTTYPE,PRODUCTNAME,PRODUCTSHORTNAME,QUANTITY,ISSUEDATE,ISSUERID,COUNTRY,ISSUEPRICE,ISSUECURRENCY,REDEMPTIONPRICE,REDEMPTIONCURRENCY,FACEVALUE,CODE,DATEDDATE,TENOR,ATTRIBUTES,ISPOSITIONBASED,COLLATERALID,CALLABLETYPE,REPOTYPE,PRODUCTSUBTYPE,STATUS,EXCHANGE,CUSIP,SEDOL,ISIN,SYMBOL,MATURITYDATE,CURRENCY FROM "
			+ tableName + " where id =  ?";
	static private String SELECTONE = "SELECT ID,PRODUCTTYPE,PRODUCTNAME,PRODUCTSHORTNAME,QUANTITY,ISSUEDATE,ISSUERID,COUNTRY,ISSUEPRICE,ISSUECURRENCY,REDEMPTIONPRICE,REDEMPTIONCURRENCY,FACEVALUE,CODE,DATEDDATE,TENOR,ATTRIBUTES,ISPOSITIONBASED,COLLATERALID,CALLABLETYPE,REPOTYPE,PRODUCTSUBTYPE,STATUS,EXCHANGE,CUSIP,SEDOL,ISIN,SYMBOL,MATURITYDATE,CURRENCY FROM "
			+ tableName + " where id =  ";
	final static private String SELECTWHRE = "SELECT ID,PRODUCTTYPE,PRODUCTNAME,PRODUCTSHORTNAME,QUANTITY,ISSUEDATE,ISSUERID,COUNTRY,ISSUEPRICE,ISSUECURRENCY,REDEMPTIONPRICE,REDEMPTIONCURRENCY,FACEVALUE,CODE,DATEDDATE,TENOR,ATTRIBUTES,ISPOSITIONBASED,COLLATERALID,CALLABLETYPE,REPOTYPE,PRODUCTSUBTYPE,STATUS,EXCHANGE,CUSIP,SEDOL,ISIN,SYMBOL,MATURITYDATE,CURRENCY FROM "
			+ tableName + " where   ";
	final static private String SELECTFROMATTRIBUTES = " select id,attributes FROM " + tableName
			+ " where attributes = '%like"; // this working only with oralce..
	final static private String selectExisitingName = " select id,producttype,productname,productshortname,productSubType from product ";
	final static private String selectFutureProduct = " SELECT ID,PRODUCTTYPE,PRODUCTNAME,PRODUCTSHORTNAME,QUANTITY,ISSUEDATE,ISSUERID,COUNTRY,ISSUEPRICE,ISSUECURRENCY,REDEMPTIONPRICE,REDEMPTIONCURRENCY,FACEVALUE,CODE,DATEDDATE,TENOR,ATTRIBUTES,ISPOSITIONBASED,COLLATERALID,CALLABLETYPE,REPOTYPE,PRODUCTSUBTYPE,STATUS,EXCHANGE,CUSIP,SEDOL,ISIN,SYMBOL,MATURITYDATE,CURRENCY FROM product where id = (select underlying_productid from FUTURECONTRACT_PRODUCT where id = (select PRODUCTID from futurecontract where id = ";

	public static BaseBean save(Product insertProduct, Connection con) {
		try {
			if (insertProduct.getProductType().equalsIgnoreCase("MM")
					|| insertProduct.getProductType().equalsIgnoreCase("REPO")) {
				return insert(insertProduct, con);
			}
			Product product = selectProduct(insertProduct.getProductname(), con);
			if (product.getId() > 0)
				return null;
			return insert(insertProduct, con);
		} catch (Exception e) {
			commonUTIL.displayError("ProductSQL", "save", e);
			return null;
		}
	}

	public static boolean update(Product updateProduct, Connection con) {
		try {
			return edit(updateProduct, con);
		} catch (Exception e) {
			commonUTIL.displayError("ProductSQL", "update", e);
			return false;
		}
	}

	public static boolean delete(Product deleteProduct, Connection con) {
		try {
			return remove(deleteProduct, con);
		} catch (Exception e) {
			commonUTIL.displayError("ProductSQL", "update", e);
			return false;
		}
	}

	protected static boolean remove(Product deleteProduct, Connection con) {

		PreparedStatement stmt = null;
		try {
			int j = 1;
			stmt = dsSQL.newPreparedStatement(con, DELETE);
			stmt.setInt(j++, deleteProduct.getId());

			stmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			commonUTIL.displayError("productSQL", "remove"+e.getMessage(), e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("productSQL", "remove", e);
			}
		}
		return true;
	}

	public static Product selectProduct(int ProductId, Connection con) {
		try {
			return select1(ProductId, con);
		} catch (Exception e) {
			commonUTIL.displayError("ProductSQL", "select", e);
			return null;
		}
	}

	public static Product selectProductWithCoupons(int ProductId, Connection con) {
		try {
			Product product = select1(ProductId, con);
			product.setCoupon(getCoupon(ProductId, con));
			return product;
		} catch (Exception e) {
			commonUTIL.displayError("ProductSQL", "select", e);
			return null;
		}
	}

	public static Product selectProduct(String productName, Connection con) {
		try {
			return select1(productName, con);
		} catch (Exception e) {
			commonUTIL.displayError("ProductSQL", "select", e);
			return null;
		}
	}

	public static Collection selectALL(Connection con) {
		try {
			return select(con);
		} catch (Exception e) {
			commonUTIL.displayError("ProductSQL", "selectALL", e);
			return null;
		}
	}

	public static int selectMaxID(Connection con) {
		try {
			return selectMax(con);
		} catch (Exception e) {
			commonUTIL.displayError("ProductSQL", "selectMaxID", e);
			return 0;
		}
	}

	protected static boolean edit(Product updateProduct, Connection con) {

		PreparedStatement stmt = null;
		String sql = "";
		try {
			int j = 1;
			con.setAutoCommit(false);
			sql = getUpdateSQL(updateProduct);
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate(sql);

			con.commit();

			// stmt.executeUpdate();
			con.setAutoCommit(true);
			updateAttributes(updateProduct.getAttributeContainer(),BeanConstants.PRODUCT, j);
			commonUTIL.display("CouponSQL", "edit " + sql);
		} catch (Exception e) {
			commonUTIL.displayError("CouponSQL ", "edit " + sql, e);
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("CouponSQL", "edit", e);
			}
		}
		return true;
	}

	protected static Collection select(Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector products = new Vector();
	
		try {
	
			stmt = dsSQL.newPreparedStatement(con, SELECTALL);
	
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs.getMetaData());
			while (rs.next()) {
	
				Product product = new Product();
	
				product.setId(rs.getInt(1));
				product.setProductType(rs.getString(2));
				product.setProductname(rs.getString(3));
				product.setProdcutShortName(rs.getString(4));
				product.setQuantity(rs.getInt(5));
				product.setIssueDate(rs.getString(6));
				product.setIssuerId(rs.getInt(7));
				product.setCountry(rs.getString(8));
				product.setIssuePrice(rs.getDouble(9));
				product.setIssueCurrency(rs.getString(10));
				product.setRedemptionPrice(rs.getDouble(11));
				product.setRedemptionCurrency(rs.getString(12));
				product.setFaceValue(rs.getDouble(13));
				product.setCode(rs.getString(14));
				product.setDatedDate(rs.getString(15));
				product.setTenor(rs.getString(16));
				product.setAttributes(rs.getString(17));
				product.setIsPosition(rs.getInt(18));
				product.setCollateralID(rs.getInt(19));
				product.setCallType(rs.getString(20));
				product.setRepoType(rs.getString(21));
				product.setProductSubType(rs.getString(22));
				product.setSTATUS(rs.getString(23));
				product.setEXCHANGE(rs.getInt(24));
				product.setCUSIP(rs.getString(25));
				product.setSEDOL(rs.getString(26));
				product.setISIN(rs.getString(27));
				product.setSYMBOL(rs.getString(28));
				product.setCurrency(rs.getString(29));
				product.setMarturityDate(rs.getString(30));
				products.add(product);
	
			}
			commonUTIL.display("productSQL", SELECTALL);
		} catch (Exception e) {
			commonUTIL.displayError("productSQL", "select" + e.getMessage(), e);
			return products;
	
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("productSQL", "selectMax" + e.getMessage(), e);
			}
		}
		return products;
	}

	protected static int selectMax(Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		try {

			stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);

			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				j = rs.getInt("DESC_ID");

		} catch (Exception e) {
			commonUTIL.displayError("ProductSQL", "selectMax", e);
			return j;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("ProductSQL", "selectMax", e);
			}
		}
		return j;
	}

	protected static BaseBean insert(Product inserProduct, Connection con) {
		int id = 0;
		PreparedStatement stmt = null;
		try {
			//id = selectMax(con);
			id = id + 2;
			int j = 1;
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, INSERT);

			stmt.setInt(1, inserProduct.getId());

			stmt.setString(2, inserProduct.getProductType());
			stmt.setString(3, inserProduct.getProductname());
			stmt.setString(4, inserProduct.getProdcutShortName());
			stmt.setDouble(5, inserProduct.getQuantity());
			stmt.setString(6, inserProduct.getIssueDate());
			stmt.setString(29, inserProduct.getMarturityDate());
			stmt.setInt(7, inserProduct.getIssuerId());
			stmt.setString(8, inserProduct.getCountry());
			stmt.setDouble(9, inserProduct.getIssuePrice());
			stmt.setString(10, inserProduct.getIssueCurrency());
			stmt.setDouble(11, inserProduct.getRedemptionPrice());
			stmt.setString(12, inserProduct.getRedemptionCurrency());
			stmt.setDouble(13, inserProduct.getFaceValue());
			stmt.setString(14, inserProduct.getCode());
			stmt.setString(15, inserProduct.getDatedDate());
			stmt.setString(16, inserProduct.getTenor());
			stmt.setString(17, inserProduct.getAttributes());
			stmt.setInt(18, inserProduct.getIsPosition());
			stmt.setInt(19, inserProduct.getCollateralID());
			stmt.setString(20, inserProduct.getCallType());
			stmt.setString(21, inserProduct.getRepoType());
			stmt.setString(30, inserProduct.getCurrency());
			stmt.setString(22, inserProduct.getProductSubType());
			stmt.setString(23, inserProduct.getSTATUS());
			stmt.setInt(24, inserProduct.getEXCHANGE());
			stmt.setString(25, inserProduct.getCUSIP());
			stmt.setString(26, inserProduct.getSEDOL());
			stmt.setString(27, inserProduct.getISIN());
			stmt.setString(28, inserProduct.getSYMBOL());
			stmt.executeUpdate();
			con.commit();
			insertAttributes(inserProduct.getAttributeContainer(), id, BeanConstants.PRODUCT);
			commonUTIL.display("productSQL", INSERT);
			return inserProduct;

		} catch (Exception e) {
			commonUTIL.displayError("PrdocutSQL", "insert"+e.getMessage(), e);
	
			return null;

		} finally {
			try {
				stmt.close();
				return inserProduct;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("PrdocutSQL", "insert", e);
			}
		}

	}

	protected static Product select1(int productID, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Product product = new Product();

		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTONE + productID);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				product.setId(rs.getInt(1));
				product.setProductType(rs.getString(2));
				product.setProductname(rs.getString(3));
				product.setProdcutShortName(rs.getString(4));
				product.setQuantity(rs.getInt(5));
				product.setIssueDate(rs.getString(6));
				product.setMarturityDate(rs.getString(7));
				product.setIssuerId(rs.getInt(8));
				product.setCountry(rs.getString(9));
				product.setIssuePrice(rs.getDouble(10));
				product.setIssueCurrency(rs.getString(11));
				product.setRedemptionPrice(rs.getDouble(12));
				product.setRedemptionCurrency(rs.getString(13));
				product.setFaceValue(rs.getDouble(14));
				product.setCode(rs.getString(15));

				product.setDatedDate(rs.getString(16));
				product.setTenor(rs.getString(17));
				product.setAttributes(rs.getString(18));
				product.setIsPosition(rs.getInt(19));
				product.setCollateralID(rs.getInt(20));
				product.setCallType(rs.getString(21));
				product.setRepoType(rs.getString(22));
				product.setCurrency(rs.getString(23));
				product.setProductSubType(rs.getString(24));
				product.setSTATUS(rs.getString(25));
				product.setEXCHANGE(rs.getInt(26));
				product.setCUSIP(rs.getString(27));
				product.setSEDOL(rs.getString(28));
				product.setISIN(rs.getString(29));
				product.setSYMBOL(rs.getString(30));
				commonUTIL.display("productSQL-----", SELECTONE + productID);

			}
		} catch (Exception e) {
			commonUTIL.displayError("ProductSQL", SELECTONE + productID, e);
			return product;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("ProductSQL", SELECTONE + productID, e);
			}
		}
		return product;
	}

	protected static Product selectProductWithCoupon(int productID, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Product product = new Product();

		try {

			stmt = dsSQL.newPreparedStatement(con, SELECTONE + productID);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				product.setId(rs.getInt(1));
				product.setProductType(rs.getString(2));
				product.setProductname(rs.getString(3));
				product.setProdcutShortName(rs.getString(4));
				product.setQuantity(rs.getInt(5));
				product.setIssueDate(rs.getString(6));
				product.setMarturityDate(rs.getString(7));
				product.setIssuerId(rs.getInt(8));
				product.setCountry(rs.getString(9));
				product.setIssuePrice(rs.getDouble(10));
				product.setIssueCurrency(rs.getString(11));
				product.setRedemptionPrice(rs.getDouble(12));
				product.setRedemptionCurrency(rs.getString(13));
				product.setFaceValue(rs.getDouble(14));
				product.setCode(rs.getString(15));

				product.setDatedDate(rs.getString(16));
				product.setTenor(rs.getString(17));
				product.setAttributes(rs.getString(18));
				product.setIsPosition(rs.getInt(19));
				product.setCollateralID(rs.getInt(20));
				product.setCallType(rs.getString(21));
				product.setRepoType(rs.getString(22));
				product.setCurrency(rs.getString(23));
				product.setProductSubType(rs.getString(24));
				product.setSTATUS(rs.getString(25));
				product.setEXCHANGE(rs.getInt(26));
				product.setCUSIP(rs.getString(27));
				product.setSEDOL(rs.getString(28));
				product.setISIN(rs.getString(29));
				product.setSYMBOL(rs.getString(30));
				commonUTIL.display("productSQL-----", SELECTONE + productID);

			}
		} catch (Exception e) {
			commonUTIL.displayError("ProductSQL", SELECTONE + productID, e);
			return product;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("ProductSQL", SELECTONE + productID, e);
			}
		}
		return product;
	}

	protected static Product select1(String productName, Connection con) {

		int j = 0;
		PreparedStatement stmt = null;
		Product product = new Product();
		String sql = "";
		try {
			sql = selectExisitingName + " where productName = '" + productName.trim()
					+ "' order by PRODUCTSHORTNAME, id ";

			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				product.setId(rs.getInt(1));
				product.setProductType(rs.getString(3));
				product.setProductname(rs.getString(2));
				product.setProdcutShortName(rs.getString(4));
				product.setProductSubType(rs.getString(5));

			}
		} catch (Exception e) {
			commonUTIL.displayError("ProductSQL", " select " + sql, e);
			return product;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("ProductSQL", " select " + sql, e);
			}
		}
		return product;
	}

	public static Collection selectonWhereClause(String sqlw, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Vector products = new Vector();
		String sql = SELECTWHRE + sqlw;
		try {
			commonUTIL.display("ProdcutSQL ", "selectonWhereClause" + sql);
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();
			// System.out.println(rs.getMetaData());
			while (rs.next()) {

				Product product = new Product();

				product.setId(rs.getInt(1));
				product.setProductType(rs.getString(2));
				product.setProductname(rs.getString(3));
				product.setProdcutShortName(rs.getString(4));
				product.setQuantity(rs.getInt(5));
				product.setIssueDate(rs.getString(6));
				product.setIssuerId(rs.getInt(7));
				product.setCountry(rs.getString(8));
				product.setIssuePrice(rs.getDouble(9));
				product.setIssueCurrency(rs.getString(10));
				product.setRedemptionPrice(rs.getDouble(11));
				product.setRedemptionCurrency(rs.getString(12));
				product.setFaceValue(rs.getDouble(13));
				product.setCode(rs.getString(14));
				product.setDatedDate(rs.getString(15));
				product.setTenor(rs.getString(16));
				product.setAttributes(rs.getString(17));
				product.setIsPosition(rs.getInt(18));
				product.setCollateralID(rs.getInt(19));
				product.setCallType(rs.getString(20));
				product.setRepoType(rs.getString(21));
				product.setProductSubType(rs.getString(22));
				product.setSTATUS(rs.getString(23));
				product.setEXCHANGE(rs.getInt(24));
				product.setCUSIP(rs.getString(25));
				product.setSEDOL(rs.getString(26));
				product.setISIN(rs.getString(27));
				product.setSYMBOL(rs.getString(28));
				product.setCurrency(rs.getString(29));
				product.setMarturityDate(rs.getString(30));
				product.setCoupon(CouponSQL.getcouponOnProduct(product.getId(), dsSQL.getConn()));
				products.add(product);

			}
			commonUTIL.display("productSQL", sql);
		} catch (Exception e) {
			commonUTIL.displayError("productSQL", "select", e);
			return products;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("productSQL", "selectMax", e);
			}
		}
		return products;
	}

	public static Product selectonWhereClauseOnProductSubType(String productType, String productSubType,
			Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Product product = new Product();
		String sql = SELECTWHRE + " producttype ='" + productType + "' and productname like '" + productSubType + "'";
		try {
			commonUTIL.display("ProdcutSQL ", "selectonWhereClause  " + sql);
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();
			// System.out.println(rs.getMetaData());
			while (rs.next()) {

				product.setId(rs.getInt(1));
				product.setProductType(rs.getString(2));
				product.setProductname(rs.getString(3));
				product.setProdcutShortName(rs.getString(4));
				product.setQuantity(rs.getInt(5));
				product.setIssueDate(rs.getString(6));
				product.setIssuerId(rs.getInt(7));
				product.setCountry(rs.getString(8));
				product.setIssuePrice(rs.getDouble(9));
				product.setIssueCurrency(rs.getString(10));
				product.setRedemptionPrice(rs.getDouble(11));
				product.setRedemptionCurrency(rs.getString(12));
				product.setFaceValue(rs.getDouble(13));
				product.setCode(rs.getString(14));
				product.setDatedDate(rs.getString(15));
				product.setTenor(rs.getString(16));
				product.setAttributes(rs.getString(17));
				product.setIsPosition(rs.getInt(18));
				product.setCollateralID(rs.getInt(19));
				product.setCallType(rs.getString(20));
				product.setRepoType(rs.getString(21));
				product.setProductSubType(rs.getString(22));
				product.setSTATUS(rs.getString(23));
				product.setEXCHANGE(rs.getInt(24));
				product.setCUSIP(rs.getString(25));
				product.setSEDOL(rs.getString(26));
				product.setISIN(rs.getString(27));
				product.setSYMBOL(rs.getString(28));
				product.setCurrency(rs.getString(29));
				product.setMarturityDate(rs.getString(30));
			}
			commonUTIL.display("productSQL selectonWhereClauseOnProductSubType ", sql);
		} catch (Exception e) {
			commonUTIL.displayError("productSQL", "selectonWhereClauseOnProductSubType", e);
			return product;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("productSQL", "selectonWhereClauseOnProductSubType", e);
			}
		}
		return product;
	}

	public static Product getFutureProduct(int productID, Connection con) {
		int j = 0;
		PreparedStatement stmt = null;
		Product product = new Product();
		String sql = selectFutureProduct + productID + "))";
		try {
			// commonUTIL.display("ProdcutSQL ", "getFutureProduct "+sql);
			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();
			// System.out.println(rs.getMetaData());
			while (rs.next()) {

				product.setId(rs.getInt(1));
				product.setProductType(rs.getString(2));
				product.setProductname(rs.getString(3));
				product.setProdcutShortName(rs.getString(4));
				product.setQuantity(rs.getInt(5));
				product.setIssueDate(rs.getString(6));
				product.setIssuerId(rs.getInt(7));
				product.setCountry(rs.getString(8));
				product.setIssuePrice(rs.getDouble(9));
				product.setIssueCurrency(rs.getString(10));
				product.setRedemptionPrice(rs.getDouble(11));
				product.setRedemptionCurrency(rs.getString(12));
				product.setFaceValue(rs.getDouble(13));
				product.setCode(rs.getString(14));
				product.setDatedDate(rs.getString(15));
				product.setTenor(rs.getString(16));
				product.setAttributes(rs.getString(17));
				product.setIsPosition(rs.getInt(18));
				product.setCollateralID(rs.getInt(19));
				product.setCallType(rs.getString(20));
				product.setRepoType(rs.getString(21));
				product.setProductSubType(rs.getString(22));
				product.setSTATUS(rs.getString(23));
				product.setEXCHANGE(rs.getInt(24));
				product.setCUSIP(rs.getString(25));
				product.setSEDOL(rs.getString(26));
				product.setISIN(rs.getString(27));
				product.setSYMBOL(rs.getString(28));
				product.setCurrency(rs.getString(29));
				product.setMarturityDate(rs.getString(30));
			}
			commonUTIL.display("productSQL getFutureProduct ", sql);
		} catch (Exception e) {
			commonUTIL.displayError("productSQL", "getFutureProduct", e);
			return product;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("productSQL", "getFutureProduct", e);
			}
		}
		return product;
	}

	private static Coupon getCoupon(int productID, Connection con) {
		return (Coupon) CouponSQL.getcouponOnProduct(productID, con);
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
	public BaseBean insertSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return save((Product) sql, con);
	}

	@Override
	public boolean updateSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return update((Product) sql, con);
	}

	@Override
	public boolean deleteSQL(BaseBean sql, Connection con) {
		// TODO Auto-generated method stub
		return delete((Product) sql, con);
	}

	@Override
	public BaseBean select(int id, Connection con) {
		// TODO Auto-generated method stub
		return select1(id, con);
	}

	@Override
	public BaseBean select(String name, Connection con) {
		// TODO Auto-generated method stub
		return select1(name, con);
	}

	@Override
	public Collection selectWhere(String where, Connection con) {
		// TODO Auto-generated method stub
		return selectonWhereClause(where, con);
	}

	@Override
	public Collection selectALLData(Connection con) {
		// TODO Auto-generated method stub
		return selectALL(con);
	}

	@Override
	public int count(String sql, Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String args[]) {
		Product pro = new Product();
		

		AttributeContainer att = new AttributeContainer();
		att.addAttribute("justing", "ppqp");
		pro.setAttributeContainer(att);

		//insert(pro, dsSQL.getConn());
		// edit(pro, dsSQL.getConn());
		 select( dsSQL.getConn());
		// JOptionPane.showMessageDialog(null, select( dsSQL.getConn()), "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);

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
