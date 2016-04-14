package dbSQL;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import beans.Attribute;
import beans.BaseBean;
import beans.LEAttribute;
import beans.Attribute;
import util.commonUTIL;

public class LegalEntityAttributeSQL implements AttributeProvider {

			final static private String INSERT_INTO_LE_ATTRIBUTE =
				"INSERT into LEATTRIBUTE( id, attributeName, attributeValue) values(?,?,?)";
			final static private String SELECTALL_LE_ATTRIBUTE =
				"SELECT id, attributeName, attributeValue FROM LEATTRIBUTES where id=";
			
			final static private String LE_ATTRIBUTE_UPDATE_SQL = "UPDATE LEATTRIBUTE  set " +
	  		"attributeName= ? " +  	      		
	  		",attributeValue= ?" + 	      		
	  		" where  id= ?";
	  		 
			
			 public static BaseBean saveLEAttribute(Vector<Attribute>  LEAttrVec, Connection con, int entityID) {
				 try {
		             return insertLEAttribute(LEAttrVec, con,entityID);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("insertAttributeSQL","save",e);
		        	 return null;
		         }
			 }
			 public static boolean updateLEAttribute(Vector<Attribute> updateLEAttribute, Connection con, int entityID) {
				 try {
		             return editLEAttribute(updateLEAttribute, con,entityID);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("LEAttributeSQL","update",e);
		        	 return false;
		         }
			 }
			 
			 
			 public static boolean deleteLEAttribute(Vector<LEAttribute> deleteLEAttrVec, Connection con) {
				 try {
		             return deleteLEAttribute(deleteLEAttrVec, con);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("LEAttributeSQL","update",e);
		        	 return false;
		         }
			 }
			 public static Collection<Attribute> selectALLLEAttribute(int id,Connection con) {
				 try {
		             return selectLEAttribute(id,con);
		         }catch(Exception e) {
		        	 commonUTIL.displayError("LEAttributeSQL","selectALL",e);
		        	 return null;
		         }
			 }
			 protected static  boolean editLEAttribute(Vector<Attribute> updateAttributeVec, Connection con, int entityID ) {
				 
				 PreparedStatement stmt = null;
				 String sql = "";
				 
				 int size = updateAttributeVec.size();
				 
				 try {
					 con.setAutoCommit(false);
					 
					 stmt = dsSQL.newPreparedStatement(con, LE_ATTRIBUTE_UPDATE_SQL);
					 
					 Attribute LEAttr= null;
					 
					 for (int ii = 0; ii < size; ii++) {
						 
						 LEAttr = updateAttributeVec.get(ii);
						 
						 stmt.setString(1,LEAttr.getName());
				         stmt.setString(2,LEAttr.getValue());
				         stmt.setInt(3,entityID);
						 
						 stmt.addBatch();
					 }
					 
					 stmt.executeBatch();
					 con.commit();
					 
					 commonUTIL.display("LEAttributeSQL ::  editLEAttribute", LE_ATTRIBUTE_UPDATE_SQL);
					 
				 } catch (Exception e) {
					 
					 commonUTIL.displayError("LEAttributeSQL: : editLEAttribute  " , LE_ATTRIBUTE_UPDATE_SQL,e);
					 
					 try {
						con.rollback();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					 return false;
			           
			        }
			        finally {
			           try {
						stmt.close();
					} catch (SQLException e) {
					 commonUTIL.displayError("LEAttributeSQL","edit" + sql,e);
					}
			       
			     }
			        
			     return true;
			 }
			

			 
			 protected static BaseBean insertLEAttribute(Vector<Attribute> attributes, Connection con, int entityID ) {
					
			        PreparedStatement stmt = null;
				 try {
					 con.setAutoCommit(false);
					 stmt = dsSQL.newPreparedStatement(con, INSERT_INTO_LE_ATTRIBUTE);
					
					 int size = attributes.size();
					 
					 for (int ii = 0; ii < size; ii++) {
						 
						 stmt.setInt(1,entityID);
						 stmt.setString(2,attributes.get(ii).getName());
				         stmt.setString(3,attributes.get(ii).getValue());
				         
				         stmt.addBatch();
				            
					 }
			            stmt.executeBatch();
			            con.commit();
			            commonUTIL.display("LEAttributeSQL","insert " + INSERT_INTO_LE_ATTRIBUTE);
				 } catch (Exception e) {
					 commonUTIL.displayError("LEAttributeSQL",INSERT_INTO_LE_ATTRIBUTE,e);
					 try {
						con.rollback();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					 return null;
			           
			        }
			        finally {
			           try {
						stmt.close();
					} catch (SQLException e) {
						
						commonUTIL.displayError("LEAttributeSQL", INSERT_INTO_LE_ATTRIBUTE,e);
					}
			        }
			        return (BaseBean) attributes;
			 }
			 

			 protected static Collection<Attribute> selectLEAttribute(int id,Connection con) { 
				
			     PreparedStatement stmt = null;
			     Vector<Attribute> LEAttribute = new Vector<Attribute>();
			     String sql =SELECTALL_LE_ATTRIBUTE + id + "  order by  id";

				 try {
					
					 stmt = dsSQL.newPreparedStatement(con, sql );
			      
			      ResultSet rs = stmt.executeQuery();
			      
			      while(rs.next()) {
			   
			    	  Attribute LEAttribute1 = new Attribute();
			    	  LEAttribute1.setId(rs.getInt(1));
			    	  LEAttribute1.setName(rs.getString(2));
			    	  LEAttribute1.setValue(rs.getString(3));
			        
			    	  LEAttribute.add(LEAttribute1);
			      
			      }
			      commonUTIL.display("LEAttributeSQL","selectWhereClause ");
				 } catch (Exception e) {
					 commonUTIL.displayError("LEAttributeSQL",SELECTALL_LE_ATTRIBUTE,e);
					 return LEAttribute;
			        
			     }
			     finally {
			        try {
						stmt.close();
					} catch (SQLException e) {
						
						commonUTIL.displayError("LEAttributeSQL",SELECTALL_LE_ATTRIBUTE,e);
					}
			     }
			     return LEAttribute;
			 }
			 
		
			 @Override
				public void saveAttributes(Vector attributes, Connection con, int entityID) {
					// TODO Auto-generated method stub
					insertLEAttribute(attributes, con, entityID);
				}

				@Override
				public void updateAttributes(Vector attributes, int entityID, Connection con) {
					updateLEAttribute(attributes, con, entityID);
				}

				@Override
				public Vector<Attribute> selectAttributes(int id,Connection con) {
					// TODO Auto-generated method stub

					return (Vector<Attribute>) selectLEAttribute(id,con);
				}
		 
			 
		 
			
}
