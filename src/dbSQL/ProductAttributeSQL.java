package dbSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import beans.AttributeContainer;
import beans.Attribute;
import beans.BaseBean;
import beans.Product;
import constants.BeanConstants;
import beans.Attribute;
import util.commonUTIL;

public class ProductAttributeSQL implements AttributeProvider {

	final static private String INSERT_INTO_PRODUCT_ATTRIBUTE = "INSERT into PRODUCTATTRIBUTES(  id, attributeName, attributeValue) values(?,?,?)";

	final static private String SELECTALL_PRODUCT_ATTRIBUTE = "SELECT Productid, attributeName, attributeValue FROM PRODUCTATTRIBUTES order by  id";

	final static private String PRODUCT_ATTRIBUTE_UPDATE_SQL = "UPDATE PRODUCTATTRIBUTES  set " + " attributeName= ? "
			+ ",attributeValue= ?" + " where  id= ?";

	public static BaseBean saveProductAttribute(Vector<Attribute> ProductAttrVec, Connection con, int entityID) {
		try {
			return insertProductAttribute(ProductAttrVec, con, entityID);
		} catch (Exception e) {
			commonUTIL.displayError("insertAttributeSQL", "save", e);
			return null;
		}
	}

	public static boolean updateProductAttribute(Vector<Attribute> updateProductAttribute, int entityID, Connection con) {
		try {
			return editProductAttribute(updateProductAttribute, entityID, con);
		} catch (Exception e) {
			commonUTIL.displayError("ProductAttributeSQL", "update" + e.getMessage(), e);
			return false;
		}
	}

	public static Collection<Attribute> selectALLProductAttribute(int id,Connection con) {
		try {
			return selectProductAttribute(id,con);
		} catch (Exception e) {
			commonUTIL.displayError("ProductAttributeSQL", "selectALL", e);
			return null;
		}
	}

	protected static boolean editProductAttribute(Vector<Attribute> updateAttributeVec, int entityID, Connection con) {

		PreparedStatement stmt = null;
		String sql = "";

		int size = updateAttributeVec.size();

		try {
			con.setAutoCommit(false);

			stmt = dsSQL.newPreparedStatement(con, PRODUCT_ATTRIBUTE_UPDATE_SQL);

			Attribute ProductAttr = null;

			for (int ii = 0; ii < size; ii++) {

				ProductAttr = updateAttributeVec.get(ii);

				stmt.setString(1, ProductAttr.getName());
				stmt.setString(2, ProductAttr.getValue());
				stmt.setInt(3, entityID);

				stmt.addBatch();
			}

			stmt.executeBatch();
			con.commit();

			commonUTIL.display("ProductAttributeSQL ::  editProductAttribute", PRODUCT_ATTRIBUTE_UPDATE_SQL);

		} catch (Exception e) {

			commonUTIL.displayError("ProductAttributeSQL: : editProductAttribute  ", PRODUCT_ATTRIBUTE_UPDATE_SQL, e);

			try {
				con.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			return false;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				commonUTIL.displayError("ProductAttributeSQL", "edit" + sql, e);
			}

		}

		return true;
	}

	protected static BaseBean insertProductAttribute(Vector<Attribute> attributes, Connection con, int entity) {

		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			stmt = dsSQL.newPreparedStatement(con, INSERT_INTO_PRODUCT_ATTRIBUTE);

			int size = attributes.size();

			for (int ii = 0; ii < size; ii++) {

				stmt.setInt(1, entity);
				stmt.setString(2, attributes.get(ii).getName());
				stmt.setString(3, attributes.get(ii).getValue());

				stmt.addBatch();

			}
			stmt.executeBatch();
			con.commit();
			commonUTIL.display("ProductAttributeSQL", "insert " + INSERT_INTO_PRODUCT_ATTRIBUTE);
		} catch (Exception e) {
			commonUTIL.displayError("ProductAttributeSQL", INSERT_INTO_PRODUCT_ATTRIBUTE + e.getMessage(), e);
			try {
				con.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			return null;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {

				commonUTIL.displayError("ProductAttributeSQL", INSERT_INTO_PRODUCT_ATTRIBUTE, e);
			}
		}
		return (BaseBean) attributes;
	}

	protected static Collection<Attribute> selectProductAttribute(int id,Connection con) {

		PreparedStatement stmt = null;
		Vector<Attribute> ProductAttribute = new Vector<Attribute>();
		String sql = SELECTALL_PRODUCT_ATTRIBUTE + id + "  order by  id";
		try {

			stmt = dsSQL.newPreparedStatement(con, sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Attribute productAttribute = new Attribute();
				productAttribute.setId(rs.getInt(1));
				productAttribute.setName(rs.getString(2));
				productAttribute.setValue(rs.getString(3));

				ProductAttribute.add(productAttribute);

			}
			commonUTIL.display("ProductAttributeSQL", "selectWhereClause ");
		} catch (Exception e) {
			commonUTIL.displayError("ProductAttributeSQL", sql, e);
			return ProductAttribute;

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {

				commonUTIL.displayError("ProductAttributeSQL", sql, e);
			}
		}
		return ProductAttribute;
	}

	@Override
	public void saveAttributes(Vector attributes, Connection con, int entityID) {
		// TODO Auto-generated method stub
		insertProductAttribute(attributes, con, entityID);
	}

	@Override
	public void updateAttributes(Vector attributes, int entityID, Connection con) {
		updateProductAttribute(attributes, entityID, con);
	}


	@Override
	public Vector<Attribute> selectAttributes(int id, Connection con) {
		// TODO Auto-generated method stub
		return (Vector<Attribute>) selectALLProductAttribute(id,con);
	}

	

}
