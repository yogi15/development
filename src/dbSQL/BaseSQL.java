package dbSQL;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
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
	  public abstract Collection selectKeyColumnsWithWhere(String columnNames,String where,Connection con);
     
	  public abstract Collection selectKeyColumns(String columnNames,Connection con);
	  
	  
	  
	  // this method will go when insert 
	  public static void insertAttributes(BaseBean bean,int entityID,String entityType)  {
		  AttributeContainer attributes = (AttributeContainer) bean;
		 
		 Vector<Attribute> attributeData = attributes.getAttributes();
		 if(!commonUTIL.isEmpty(attributeData)) {
			 
			//	baseSQL.saveAttribute(attributeData, dsSQL.getConn()  );
			 saveAttribute(makeSQLObject(entityType),attributeData, dsSQL.getConn(),entityID);
		 }
	  }
	  public static Collection getAttributes(int id,String entityType)  {
			 
			 
			return selectAttribute(  id,makeSQLObject(entityType), dsSQL.getConn());
		 
}
	  public AttributeContainer deleteAttributes(int entityID,String entityType,String attributeName)  {
		  AttributeContainer attributeData = new AttributeContainer();
		  Vector<Attribute> attributes = new Vector<Attribute>();
		  String sql = " id = "+entityID+ " and ";
		//  AttributeSQL.SelectWhere(entityID,)
		  return attributeData;
	  }
	  public static void updateAttributes(BaseBean bean,String entityType,int entityID)  {
		  AttributeContainer attributes = (AttributeContainer) bean;
			 
			 Vector<Attribute> attributeData = attributes.getAttributes();
			 if(!commonUTIL.isEmpty(attributeData)) {
				 
				//	baseSQL.saveAttribute(attributeData, dsSQL.getConn()  );
				 updateAttribute(makeSQLObject(entityType),attributeData,entityID,dsSQL.getConn());
			 }
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
		static public void saveAttribute(AttributeProvider attributeP,Vector attributes,Connection con,int entityID) {
			attributeP.saveAttributes(attributes, con,entityID);
		}
		static public void updateAttribute(AttributeProvider attributeP,Vector attributes,int entityID,Connection con){
			attributeP.updateAttributes(attributes, entityID, con);
		}
		static public Collection selectAttribute(int id,AttributeProvider attributeP,Connection con){
			return attributeP.selectAttributes( id,con);
		}
		 
		  
		  public static Collection selectOnWherecClauseReports(ResultSet rs) {
			  Vector<Object> reportObject = new Vector();
			  ReportGenerator generateReport = new ReportGenerator();
			  try {
				  if(rs != null) {
					  reportObject.add(rs);
				reportObject = (Vector) generateReport.generateReportOnSQL(reportObject);
				   
				  }
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    return reportObject;
		  }
		
	  
}