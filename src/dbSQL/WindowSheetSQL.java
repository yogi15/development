package dbSQL;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import util.commonUTIL; 
import beans.BaseBean;
import beans.WindowSheet;

public class WindowSheetSQL extends BaseSQL {
	
	final static private String DELETE_FROM_WINDOSHEET =
			"DELETE FROM windowSheet where WindowSheetName =? , FIELDNAME = ? ";
		final static private String INSERT_FROM_windowName =
			"INSERT into windowSheet(WINDOWNAME,FIELDNAME,DATATYPE,ISSTARTUPDATA,STARTUPDATANAME,CUSTOMPANELNAME,DEFAULTVALUE,NULLCHECKED,category,columnSerialNo,beanname,methodname,designType,isChildField,ParentFieldName,IsHierarachicalWindow,childwindowname,mapjavaobject,javaobjectname,iseditable,ishidden,columnsequence,isconditional,configureifelsecondition) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		final static private String UPDATE_FROM_WINDOSHEET =
			"UPDATE windowSheet set le_id=?,WindowSheet_name=? where WindowSheetno = ? ";
		final static private String SELECT_MAX =
			"SELECT MAX(columnSerialNo) SerialNo FROM windowSheet ";
		final static private String SELECTALL =
			"SELECT WINDOWNAME,FIELDNAME,DATATYPE,ISSTARTUPDATA,STARTUPDATANAME,CUSTOMPANELNAME,DEFAULTVALUE,NULLCHECKED,category,columnSerialNo,beanname,methodname,designType,isChildField,ParentFieldName,IsHierarachicalWindow,childwindowname,mapjavaobject,javaobjectname,iseditable,ishidden,columnsequence,isconditional,configureifelsecondition FROM windowSheet order by columnsequence asc ";
		final static private String SELECT =
			"SELECT WINDOWNAME,FIELDNAME,DATATYPE,ISSTARTUPDATA,STARTUPDATANAME,CUSTOMPANELNAME,DEFAULTVALUE,NULLCHECKED,category,columnSerialNo,beanname,methodname,designType,isChildField,ParentFieldName,IsHierarachicalWindow,childwindowname,mapjavaobject,javaobjectname,iseditable,ishidden,columnsequence,isconditional,configureifelsecondition FROM windowSheet where windowName =  ?";
		 static private String SELECTONE =
			"SELECT WINDOWNAME,FIELDNAME,DATATYPE,ISSTARTUPDATA,STARTUPDATANAME,CUSTOMPANELNAME,DEFAULTVALUE,NULLCHECKED,category,columnSerialNo,beanname,methodname,designType,isChildField,ParentFieldName,IsHierarachicalWindow,childwindowname,mapjavaobject,javaobjectname,iseditable,ishidden,columnsequence,isconditional,configureifelsecondition FROM windowSheet where windowName =  " ;
		 static private String SELECTWHERE =
					"SELECT WINDOWNAME,FIELDNAME,DATATYPE,ISSTARTUPDATA,STARTUPDATANAME,CUSTOMPANELNAME,DEFAULTVALUE,NULLCHECKED,category,columnSerialNo,beanname,methodname,designType,isChildField,ParentFieldName,IsHierarachicalWindow,childwindowname ,mapjavaobject,javaobjectname,iseditable,ishidden,columnsequence,isconditional,configureifelsecondition FROM windowSheet where   " ;
		 
		 
		 private static String getUpdateSQL(WindowSheet wSheet) {
		      String updateSQL = "UPDATE windowSheet  set " +
		    		  " WINDOWNAME= '" + wSheet.getWindowName() + "'," +
		    		  " FIELDNAME=  "+getValueWithoutNull(wSheet.getFieldName()) + "," +
		    		  " DESIGNTYPE=  "+getValueWithoutNull(wSheet.getDesignType()) + "," +
		    		  " DATATYPE=  "+getValueWithoutNull(wSheet.getDataType()) + "," +
		    		  " ISSTARTUPDATA= " + wSheet.getIsStartupdata() + "," +
		    		  " STARTUPDATANAME=  "+getValueWithoutNull(wSheet.getStartUpDataName())+ "," +
		    		  " CUSTOMPANELNAME=  "+getValueWithoutNull(wSheet.getDefaultValue()) + "," +
		    		  " DEFAULTVALUE= "+getValueWithoutNull(wSheet.getDefaultValue())+ "," +
		    		  " beanname= "+getValueWithoutNull(wSheet.getBeanName())+ "," +
		    		  " methodName= "+getValueWithoutNull(wSheet.getMethodName())+ "," +
		    		  " category= "+getValueWithoutNull(wSheet.getCategory())+ "," +
		    		  " parentfieldName= "+getValueWithoutNull(wSheet.getParentFieldName())+ "," +
		    		  " childwindowname= "+getValueWithoutNull(wSheet.getChildWindowName())+ "," +
		    		  " javaobjectname= "+getValueWithoutNull(wSheet.getJavaObjectName())+ "," +
		    		  " configureifelsecondition= "+getValueWithoutNull(wSheet.getConfigureIfelseCondition())+ "," +
		    		  " columnsequence  = "+ wSheet.getColunmSequenceNo() + "," +
		    		  " NULLCHECKED=  "; 
		      if(wSheet.isNullChecked()) {
		    	  updateSQL = updateSQL +"'Y'";
		      } else {
		    	  updateSQL = updateSQL +"'N'";
		      }
		      if(wSheet.IsHierarachicalWindow()) {
		    	  updateSQL = updateSQL +" , IsHierarachicalWindow = 'Y'";
		      } else {
		    	  updateSQL = updateSQL +" , IsHierarachicalWindow = 'N'";
		      }
		      if(wSheet.isCondition()) {
		    	  updateSQL = updateSQL +" , isconditional = 'Y'";
		      } else {
		    	  updateSQL = updateSQL +" , isconditional = 'N'";
		      }
		      if(wSheet.isMapJavaObject()) {
		    	  updateSQL = updateSQL +" , mapjavaobject = 'Y'";
		      } else {
		    	  updateSQL = updateSQL +" , mapjavaobject = 'N'";
		      }
		      if(wSheet.isEditable()) {
		    	  updateSQL = updateSQL +" , iseditable = 'Y'";
		      } else {
		    	  updateSQL = updateSQL +" , iseditable = 'N'";
		      }
		      if(wSheet.isHidden()) {
		    	  updateSQL = updateSQL +" , ishidden = 'Y'";
		      } else {
		    	  updateSQL = updateSQL +" , ishidden = 'N'";
		      }
		      if(wSheet.isChildField()) {
		    	  updateSQL = updateSQL +" , isChildField = 'Y'";
		      }else  {
		    	  updateSQL = updateSQL +" , isChildField = 'N'";
		      }
		      updateSQL = updateSQL +"  where columnSerialNo= " + wSheet.getColumnSerialNo() +"";
		      return updateSQL;
		     }
		 
		 private static String getValueWithoutNull(String value) {
			 if(commonUTIL.isEmpty(value))
				 return null;
			 return  "'"+value+"'";
		 }
		 private static String getDeleteSQL(WindowSheet wSheet) {
		      String deleteSQL = "Delete from windowSheet  where " +
		      		" WINDOWNAME= '" + wSheet.getWindowName() + 
		      		"' and  FIELDNAME= '" + wSheet.getFieldName() + 	      		
		      		"'";
		      return deleteSQL;
		     }
		 public static WindowSheet save(WindowSheet insertWindowSheet, Connection con) {
			 try {
	             return insert(insertWindowSheet, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("WindowSheetSQL","save",e);
	        	 return null;
	         }
		 }
		 public static boolean update(WindowSheet updateWindowSheet, Connection con) {
			 try {
	             return edit(updateWindowSheet, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("WindowSheetSQL","update",e);
	        	 return false;
	         }
		 }
		 
		 
		 public static boolean delete(WindowSheet deleteWindowSheet, Connection con) {
			 try {
	             return remove(deleteWindowSheet, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("WindowSheetSQL","update",e);
	        	 return false;
	         }
		 }
		 public static Vector selectWindowSheet(String windowName, Connection con) {
			 try {
	             return  selectOld(windowName, con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("WindowSheetSQL","select",e);
	        	 return null;
	         }
		 }
		 public static Collection selectALL(Connection con) {
			 try {
	             return select(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("WindowSheetSQL","selectALL",e);
	        	 return null;
	         }
		 }
		 
		 public static int selectMaxID(Connection con) {
			 try {
	             return selectMax(con);
	         }catch(Exception e) {
	        	 commonUTIL.displayError("WindowSheetSQL","selectMaxID",e);
	        	 return 0;
	         }
		 }
		 
		 protected static  boolean edit(WindowSheet updateWindowSheet, Connection con ) {
			 
		        PreparedStatement stmt = null;
		        String sql = getUpdateSQL(updateWindowSheet);
			 try {
				 con.setAutoCommit(false);
				 int j = 1;
				 stmt = dsSQL.newPreparedStatement(con, sql);
		            stmt.executeUpdate(sql);
				 con.commit();
				 commonUTIL.display("WindowSheetSQL    ::  edit hj", sql);
			 } catch (Exception e) {
				 commonUTIL.displayError("WindowSheetSQL","edit",e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("WindowSheetSQL","edit",e);
				}
		        }
		        return true;
		 }
		
		protected static boolean remove(WindowSheet deleteWindowSheet, Connection con ) {
		
		        PreparedStatement stmt = null;
		        String sql = getDeleteSQL(deleteWindowSheet);
			 try {
				 int j = 1;
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, sql);
		             
		           
		            stmt.executeUpdate();
				 con.commit();
			 } catch (Exception e) {
				 commonUTIL.displayError("WindowSheetSQL","remove " + sql,e);
				 return false;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("WindowSheetSQL","remove",e);
				}
		        }
		        return true;
		 }

	protected static int selectMax(Connection con ) {
			 
			 int j = 0;
		        PreparedStatement stmt = null;
			 try {
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, SELECT_MAX);
		         
		         ResultSet rs = stmt.executeQuery();
		         while(rs.next())
		         j = rs.getInt("SerialNo");
				 
			 } catch (Exception e) {
				 commonUTIL.displayError("WindowSheetSQL",SELECT_MAX,e);
				 return j;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("WindowSheetSQL",SELECT_MAX,e);
				}
		        }
		        return j;
		 }
		 
		 protected static WindowSheet insert(WindowSheet inserWindowSheet, Connection con ) {
				
		        PreparedStatement stmt = null;
		       boolean flag = true;
			 try {
				 con.setAutoCommit(false);
				 int id = selectMax(con)+1;
				 stmt = dsSQL.newPreparedStatement(con, INSERT_FROM_windowName);
				 stmt.setString(1,inserWindowSheet.getWindowName());
		            stmt.setString(2, inserWindowSheet.getFieldName());
		            stmt.setString(3,inserWindowSheet.getDataType() );
		            stmt.setInt(4,inserWindowSheet.getIsStartupdata() );
		            stmt.setString(5,inserWindowSheet.getStartUpDataName());
		            stmt.setString(6,inserWindowSheet.getCustomPanelName() );
		            stmt.setString(7,inserWindowSheet.getDefaultValue());
		           
		            if(inserWindowSheet.isNullChecked()){
		            stmt.setString(8,"Y" ); 
		            } else {
		            	stmt.setString(8,"N" );
		            }
		            stmt.setString(9,inserWindowSheet.getCategory());
		            stmt.setInt(10,id);
		            stmt.setString(11,inserWindowSheet.getBeanName());
		            stmt.setString(12,inserWindowSheet.getMethodName());
		            stmt.setString(13,inserWindowSheet.getDesignType());
		            if(inserWindowSheet.isChildField()){
			            stmt.setString(14,"Y" ); 
			            } else {
			            	stmt.setString(14,"N" );
			            }
		            stmt.setString(15,inserWindowSheet.getParentFieldName());
		            if(inserWindowSheet.IsHierarachicalWindow()){
			            stmt.setString(16,"Y" ); 
			            } else {
			            	stmt.setString(16,"N" );
			            }
		            stmt.setString(17,inserWindowSheet.getChildWindowName());
		            if(inserWindowSheet.isMapJavaObject()){
			            stmt.setString(18,"Y" ); 
			            } else {
			            	stmt.setString(18,"N" );
			            }
		            stmt.setString(19,inserWindowSheet.getJavaObjectName());
		            if(inserWindowSheet.isEditable()){
			            stmt.setString(20,"Y" ); 
			            } else {
			            	stmt.setString(20,"N" );
			            }
		            if(inserWindowSheet.isHidden()){
			            stmt.setString(21,"Y" ); 
			            } else {
			            	stmt.setString(21,"N" );
			            }
		            stmt.setInt(22,inserWindowSheet.getColunmSequenceNo());
		            if(inserWindowSheet.isCondition()){
			            stmt.setString(23,"Y" ); 
			            } else {
			            	stmt.setString(23,"N" );
			            }
		            stmt.setString(24,inserWindowSheet.getConfigureIfelseCondition());
		            stmt.executeUpdate();
		            con.commit();
		            inserWindowSheet.setColumnSerialNo(id);
		            return inserWindowSheet;
			 } catch (Exception e) {
				 commonUTIL.displayError("WindowSheetSQL",INSERT_FROM_windowName,e);
				 return null;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("WindowSheetSQL",INSERT_FROM_windowName,e);
				}
		        }
		     
		 }
		 
		 protected static Vector selectOld(String WindowSheetIn,Connection con ) {
			 
			 int j = 0;
		        PreparedStatement stmt = null;
		        Vector WindowSheets = new Vector();
		        String sql = SELECTONE + "'" + WindowSheetIn +"' order by columnsequence asc ";
		        
			 try {
				 
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, sql);
		         
		         ResultSet rs = stmt.executeQuery();
		         
		         while(rs.next()) {
		        	  WindowSheet windowSheet = new WindowSheet();
		        	 windowSheet.setWindowName(rs.getString(1));
		        	 windowSheet.setFieldName( rs.getString(2));
		        	 windowSheet.setDataType(rs.getString(3));
		        	 windowSheet.setIsStartupdata(rs.getInt(4));
		        	 windowSheet.setStartUpDataName(rs.getString(5));
		        	 windowSheet.setCustomPanelName( rs.getString(6));
		        	 windowSheet.setDefaultValue(rs.getString(7));
		        if(rs.getString(8).equalsIgnoreCase("Y"))
		        	windowSheet.setNullChecked(true);
		       
		        windowSheet.setCategory(rs.getString(9));
		        windowSheet.setColumnSerialNo(rs.getInt(10));
		        windowSheet.setBeanName(rs.getString(11));
		        windowSheet.setMethodName(rs.getString(12));

		        windowSheet.setDesignType(rs.getString(13));
		        if(rs.getString(14).equalsIgnoreCase("Y"))
		        	windowSheet.setChildField(true);
		        windowSheet.setParentFieldName(rs.getString(15));
		        if(rs.getString(16).equalsIgnoreCase("Y"))
		        	windowSheet.setIsHierarachicalWindow(true);
		        windowSheet.setChildWindowName(rs.getString(17));
		        if(!commonUTIL.isEmpty(rs.getString(18)) && rs.getString(18).equalsIgnoreCase("Y"))
		        	windowSheet.setMapJavaObject(true);
		        windowSheet.setJavaObjectName(rs.getString(19));
		        if(!commonUTIL.isEmpty(rs.getString(20)) && rs.getString(20).equalsIgnoreCase("Y"))
		        	windowSheet.setEditable(true);
		        if(!commonUTIL.isEmpty(rs.getString(21)) && rs.getString(21).equalsIgnoreCase("Y"))
		        	windowSheet.setHidden(true);
		        windowSheet.setColunmSequenceNo(rs.getInt(22));
		        if(!commonUTIL.isEmpty(rs.getString(23)) && rs.getString(23).equalsIgnoreCase("Y"))
		        	windowSheet.setCondition(true); 
		        	windowSheet.setConfigureIfelseCondition(rs.getString(24));
		        WindowSheets.add(windowSheet);
		      
		         
		         }
		         return WindowSheets;
			 } catch (Exception e) {
				 commonUTIL.displayError("WindowSheetSQL","select "+sql,e);
				 return WindowSheets;
		           
		        }
		        finally {
		           try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("WindowSheetSQL","select "+sql,e);
				}
		        
		        }
		 }

		 protected static Collection select(Connection con) { 
			 int j = 0;
		     PreparedStatement stmt = null;
		     Vector WindowSheets = new Vector();
		     
			 try {
				 con.setAutoCommit(false);
				 stmt = dsSQL.newPreparedStatement(con, SELECTALL );
		      
		      ResultSet rs = stmt.executeQuery();
		      
		      while(rs.next()) {
		   
		    	  WindowSheet windowSheet = new WindowSheet();
		        	 windowSheet.setWindowName(rs.getString(1));
		        	 windowSheet.setFieldName( rs.getString(2));
		        	 windowSheet.setDataType(rs.getString(3));
		        	 windowSheet.setIsStartupdata(rs.getInt(4));
		        	 windowSheet.setStartUpDataName(rs.getString(5));
		        	 windowSheet.setCustomPanelName( rs.getString(6));
		        	 windowSheet.setDefaultValue(rs.getString(7));
		        if(rs.getString(8).equalsIgnoreCase("Y"))
		        	windowSheet.setNullChecked(true);
		          
		        windowSheet.setCategory(rs.getString(9));


		        windowSheet.setDesignType(rs.getString(13));
		        if(rs.getString(14).equalsIgnoreCase("Y"))
		        	windowSheet.setChildField(true);
		        windowSheet.setParentFieldName(rs.getString(15));
		        if(rs.getString(16).equalsIgnoreCase("Y"))
		        	windowSheet.setIsHierarachicalWindow(true);
		        windowSheet.setChildWindowName(rs.getString(17));
		        if(!commonUTIL.isEmpty(rs.getString(18)) && rs.getString(18).equalsIgnoreCase("Y"))
		        	windowSheet.setMapJavaObject(true);
		        windowSheet.setJavaObjectName(rs.getString(19));
		        if(!commonUTIL.isEmpty(rs.getString(20)) && rs.getString(20).equalsIgnoreCase("Y"))
		        	windowSheet.setEditable(true);
		        if(!commonUTIL.isEmpty(rs.getString(21)) && rs.getString(21).equalsIgnoreCase("Y"))
		        	windowSheet.setHidden(true);
		        windowSheet.setColunmSequenceNo(rs.getInt(22));
		        if(!commonUTIL.isEmpty(rs.getString(23)) && rs.getString(23).equalsIgnoreCase("Y"))
		        	windowSheet.setCondition(true); 
		        	windowSheet.setConfigureIfelseCondition(rs.getString(24));
		        WindowSheets.add(windowSheet);
		      
		      }
			 } catch (Exception e) {
				 commonUTIL.displayError("WindowSheetSQL",SELECTALL,e);
				 return WindowSheets;
		        
		     }
		     finally {
		        try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("WindowSheetSQL",SELECTALL,e);
				}
		     }
		     return WindowSheets;
		 }
		 
		 public static Collection selectWindowSheetWhere(String sql,Connection con ) {
			 int j = 0;
		     PreparedStatement stmt = null;
		     Vector WindowSheets = new Vector();
		     String sqls = SELECTWHERE + " " + sql + " order by columnsequence asc";
			 try {
				 con.setAutoCommit(false);
				
				 stmt = dsSQL.newPreparedStatement(con, sqls );
		      
		      ResultSet rs = stmt.executeQuery();
		      
		      while(rs.next()) {
		    	  WindowSheet windowSheet = new WindowSheet();
		        	 windowSheet.setWindowName(rs.getString(1));
		        	 windowSheet.setFieldName( rs.getString(2));
		        	 windowSheet.setDataType(rs.getString(3));
		        	 windowSheet.setIsStartupdata(rs.getInt(4));
		        	 windowSheet.setStartUpDataName(rs.getString(5));
		        	 windowSheet.setCustomPanelName( rs.getString(6));
		        	 windowSheet.setDefaultValue(rs.getString(7));
		        	   if(!commonUTIL.isEmpty(rs.getString(8)) &&rs.getString(8).equalsIgnoreCase("Y"))
		        	windowSheet.setNullChecked(true);
		          
		        windowSheet.setCategory(rs.getString(9));
		        windowSheet.setColumnSerialNo(rs.getInt(10));
		        windowSheet.setBeanName(rs.getString(11));
               windowSheet.setMethodName(rs.getString(12));
		        windowSheet.setDesignType(rs.getString(13));
		        if(!commonUTIL.isEmpty(rs.getString(14)) &&rs.getString(14).equalsIgnoreCase("Y"))
		        	windowSheet.setChildField(true);
		        windowSheet.setParentFieldName(rs.getString(15));
		        if(!commonUTIL.isEmpty(rs.getString(16)) && rs.getString(16).equalsIgnoreCase("Y"))
		        	windowSheet.setIsHierarachicalWindow(true);
		        windowSheet.setChildWindowName(rs.getString(17));
		        if(!commonUTIL.isEmpty(rs.getString(18)) && rs.getString(18).equalsIgnoreCase("Y"))
		        	windowSheet.setMapJavaObject(true);
		        windowSheet.setJavaObjectName(rs.getString(19));
		        if(!commonUTIL.isEmpty(rs.getString(20)) && rs.getString(20).equalsIgnoreCase("Y"))
		        	windowSheet.setEditable(true);
		        if(!commonUTIL.isEmpty(rs.getString(21)) && rs.getString(21).equalsIgnoreCase("Y"))
		        	windowSheet.setHidden(true);
		        windowSheet.setColunmSequenceNo(rs.getInt(22));
		        if(!commonUTIL.isEmpty(rs.getString(23)))
		        		if(rs.getString(23).trim().equalsIgnoreCase("Y"))
		        	windowSheet.setCondition(true); 
		        	windowSheet.setConfigureIfelseCondition(rs.getString(24));
		        WindowSheets.add(windowSheet);
		      
		      }
		      commonUTIL.display("WindowSheetSQL","selectWindowSheetWhere  "+sqls);
			 } catch (Exception e) {
				 commonUTIL.displayError("WindowSheetSQL","selectWindowSheetWhere"+sqls,e);
				 return WindowSheets;
		        
		     }
		     finally {
		        try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("WindowSheetSQL","selectWindowSheetWhere"+sqls,e);
				}
		     }
		     return WindowSheets;
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
			return insert((WindowSheet) sql,con);
		}

		@Override
		public boolean updateSQL(BaseBean sql, Connection con) {
			// TODO Auto-generated method stub
			return edit((WindowSheet) sql,con);
		}

		@Override
		public boolean deleteSQL(BaseBean sql, Connection con) {
			// TODO Auto-generated method stub
			return delete((WindowSheet) sql,con);
		}

		@Override
		public BaseBean select(int id, Connection con) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public BaseBean select(String name, Connection con) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Collection selectWhere(String where, Connection con) {
			// TODO Auto-generated method stub
			String sql = SELECTWHERE + where + " order by columnsequence asc";
			 PreparedStatement stmt = null;
		     Vector WindowSheets = new Vector(); try {
				 con.setAutoCommit(false);
				
				 stmt = dsSQL.newPreparedStatement(con, sql );
		      
		      ResultSet rs = stmt.executeQuery();
		      
		      while(rs.next()) {
		    	  WindowSheet windowSheet = new WindowSheet();
		        	 windowSheet.setWindowName(rs.getString(1));
		        	 windowSheet.setFieldName( rs.getString(2));
		        	 windowSheet.setDataType(rs.getString(3));
		        	 windowSheet.setIsStartupdata(rs.getInt(4));
		        	 windowSheet.setStartUpDataName(rs.getString(5));
		        	 windowSheet.setCustomPanelName( rs.getString(6));
		        	 windowSheet.setDefaultValue(rs.getString(7));
		        	   if(!commonUTIL.isEmpty(rs.getString(8)) &&rs.getString(8).equalsIgnoreCase("Y"))
		        	windowSheet.setNullChecked(true);
		          
		        windowSheet.setCategory(rs.getString(9));
		        windowSheet.setColumnSerialNo(rs.getInt(10));

               windowSheet.setMethodName(rs.getString(12));
		        windowSheet.setDesignType(rs.getString(13));
		        if(!commonUTIL.isEmpty(rs.getString(14)) &&rs.getString(14).equalsIgnoreCase("Y"))
		        	windowSheet.setChildField(true);
		        windowSheet.setParentFieldName(rs.getString(15));
		        if(!commonUTIL.isEmpty(rs.getString(16)) && rs.getString(16).equalsIgnoreCase("Y"))
		        	windowSheet.setIsHierarachicalWindow(true);
		        windowSheet.setChildWindowName(rs.getString(17));
		        if(!commonUTIL.isEmpty(rs.getString(18)) && rs.getString(18).equalsIgnoreCase("Y"))
		        	windowSheet.setMapJavaObject(true);
		        windowSheet.setJavaObjectName(rs.getString(19));
		        if(!commonUTIL.isEmpty(rs.getString(20)) && rs.getString(20).equalsIgnoreCase("Y"))
		        	windowSheet.setEditable(true);
		        if(!commonUTIL.isEmpty(rs.getString(21)) && rs.getString(21).equalsIgnoreCase("Y"))
		        	windowSheet.setHidden(true);
		        windowSheet.setColunmSequenceNo(rs.getInt(22));
		        if(!commonUTIL.isEmpty(rs.getString(23)) && rs.getString(21).equalsIgnoreCase("Y"))
		        	windowSheet.setCondition(true); 
		        	windowSheet.setConfigureIfelseCondition(rs.getString(24));
		        WindowSheets.add(windowSheet);
		      
		      }
		      commonUTIL.display("WindowSheetSQL","selectWindowSheetWhere  "+sql);
			 } catch (Exception e) {
				 commonUTIL.displayError("WindowSheetSQL","selectWindowSheetWhere"+sql,e);
				 return WindowSheets;
		        
		     }
		     finally {
		        try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					commonUTIL.displayError("WindowSheetSQL","selectWindowSheetWhere"+sql,e);
				}
		     }
		     return WindowSheets;
		}

		@Override
		public Collection selectALLData(Connection con) {
			// TODO Auto-generated method stub
			return null;
		}
 

		@Override
		public int count(String sql, Connection con) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Collection selectKeyColumnsWithWhere(String columnNames,
				String where, Connection con) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Collection selectKeyColumns(String columnNames, Connection con) {
			// TODO Auto-generated method stub
			return null;
		}
		 
		 
}
