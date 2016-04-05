package src.dbSQL;

import java.sql.Connection;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

import util.ClassInstantiateUtil;
import util.commonUTIL;

import beans.Attribute;
import beans.AttributeContainer;
import beans.BaseBean;

public abstract class BaseSQL {
          
	  public abstract BaseBean insertSQL(String sql,Connection con);
	  public abstract boolean updateSQL(String sql,Connection con);
	  public abstract boolean deleteSQL(String sql,Connection con);

	  public abstract BaseBean insertSQL(BaseBean sql,Connection con);
	  public abstract boolean updateSQL(BaseBean sql,Connection con);
	  public abstract boolean deleteSQL(BaseBean sql,Connection con);
	  public abstract BaseBean select(int id,Connection con);
	  public abstract BaseBean select(String name,Connection con);
	  public abstract Collection selectWhere(String where,Connection con);
	  public abstract Collection selectALLData(Connection con);
	  public abstract int count(String sql,Connection con);
	 
	  
	  
	  // this method will go when insert 
	  public static void insertAttributes(BaseBean bean,int entityID,String entityType)  {
		  AttributeContainer attributes = (AttributeContainer) bean;
		 Vector<Attribute> attributeData = attributes.getAttributes();
		 if(!commonUTIL.isEmpty(attributeData)) {
			 
			//	baseSQL.saveAttribute(attributeData, dsSQL.getConn()  );
			 saveAttribute(makeSQLObject(entityType),attributeData, dsSQL.getConn());
		 }
	  }
	  public AttributeContainer getAttributes(int entityID,String entityType)  {
		  AttributeContainer attributeData = new AttributeContainer();
		  Vector<Attribute> attributes = new Vector<Attribute>();
		  String sql = " id = "+entityID+ " and ";
		//  AttributeSQL.SelectWhere(entityID,)
		  return attributeData;
	  }
	  public AttributeContainer deleteAttributes(int entityID,String entityType,String attributeName)  {
		  AttributeContainer attributeData = new AttributeContainer();
		  Vector<Attribute> attributes = new Vector<Attribute>();
		  String sql = " id = "+entityID+ " and ";
		//  AttributeSQL.SelectWhere(entityID,)
		  return attributeData;
	  }
	  public AttributeContainer updateAttributes(int entityID,String entityType)  {
		  AttributeContainer attributeData = new AttributeContainer();
		  Vector<Attribute> attributes = new Vector<Attribute>();
		  String sql = " id = "+entityID+ " and ";
		//  AttributeSQL.SelectWhere(entityID,)
		  return attributeData;
	  }
	  
	  static public Hashtable<String, AttributeProvider> sqlCache = new Hashtable<String, AttributeProvider>();

		private static AttributeProvider makeSQLObject(String sqlName) {
			AttributeProvider sql = null;
			 
			if (!commonUTIL.isEmpty(sqlName)) {
				String sqlObject = "dbSQL." + sqlName +"AttributeSQL";
				try {
					sql = sqlCache.get(sqlName);
					if (sql == null) {
						Class sqlC = ClassInstantiateUtil
								.getClass(sqlObject, false);
						if (sqlC != null) {
							sql = (AttributeProvider) sqlC.newInstance();
							sqlCache.put(sqlName.trim(), sql);
						}
					}
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("BaseSQL", "makeSQLObject", e);
				}
			}
			return sql;
		}
		static public void saveAttribute(AttributeProvider attributeP,Vector attributes,Connection con) {
			attributeP.saveAttributes(attributes, con);
		}
		static public void updateAttribute(AttributeProvider attributeP,Vector attributes,Connection con) {
			attributeP.updateAttributes(attributes, con);
		}
		static public void selectAttribute(AttributeProvider attributeP,Vector attributes,Connection con) {
			attributeP.updateAttributes(attributes, con);
		}
	  
}