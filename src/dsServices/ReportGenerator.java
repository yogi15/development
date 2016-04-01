package dsServices;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import dbSQL.TradeSQL;
import dbSQL.dsSQL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import util.commonUTIL;
public class ReportGenerator  {

	final Vector<String> header = new Vector<String>();

	final static transient SimpleDateFormat FORMAT =new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

	@SuppressWarnings("unchecked")
	final Vector<Class> types = new Vector<Class>();
    
	final Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
	
	public Class<?>[] getDataType() {
        return types.toArray(new Class[0]);
    }
	
	public void setHeader(String headers) {
//	System.out.println("Columns " + headers);
			header.add(headers);
		
	}
	
	public void setType(String classType) {
		//System.out.println("classType " + classType);
		 Class<?> addType;
			try {
				if(classType.equalsIgnoreCase("java.math.BigDecimal")) 
					classType = "java.lang.Double";
			  addType = Class.forName(classType);
				types.add(addType);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("ReportImp", "SetType", e);
			}
			 
			}
	

	
	
	
	
	public Collection generateReportOnSQL(Vector resulstSetvector) throws RemoteException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		int columnCount = 0;
		
		Vector v1 = resulstSetvector;
		if(v1.size() > 0) {
			 rs = (ResultSet) v1.get(0);
			 try {
				ResultSetMetaData mdata = rs.getMetaData();
				columnCount = mdata.getColumnCount();
				for(int i=0;i<columnCount ;i++) {
					System.out.println(mdata.getColumnName(i + 1));
					if(mdata.getColumnName(i + 1).contains("id") || mdata.getColumnName(i + 1).contains("ID")) 
						setType("java.lang.Integer");
					else
					setType(mdata.getColumnClassName(i+1));
					setHeader(mdata.getColumnName(i + 1));
				}
				processtableData(rs,columnCount); 
				v1.removeAllElements();
				v1.add(header);
				v1.add(types);
				v1.add(tableData);
				return v1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("ReportImp", "generateReportOnSQL" , e);
			}
			 
		}
		 return null;
	}

	private void processtableData(ResultSet rs, int columnCount) {
		// TODO Auto-generated method stub
		try {
			while(rs.next()) {
				 final Vector<Object> record = new Vector<Object>();
			for(int i=0;i<columnCount;i++) {
				final Class<?> classType = getDataType()[i];
			//	System.out.println(classType + " at " + i);
				if (classType.equals(Date.class)) {
	                try {
	                    // Sun Mar 13 19:00:00 CDT 2011
	                    // EEE MMM dd HH:mm:ss z yyyy
	                    final Date date = FORMAT.parse(rs.getString(i+1));
	                    record.add(date); // order date
	                } catch (final ParseException e) {
	                    e.printStackTrace();
	                }
		            } else if (classType.equals(String.class)) {
		            	record.add(rs.getString(i+1));
		
		            } else if (classType.equals(double.class)
		                || classType.equals(Double.class)) {
		            	record.add(Double.valueOf(rs.getString(i+1)));
		
		            } else if (classType.equals(int.class)
		                || classType.equals(Integer.class)) {
		            	record.add(Integer.valueOf(rs.getString(i+1)));
		
		            } else if (classType.equals(Math.class)
		                || classType.equals(Math.class)) {
		            	record.add(Integer.valueOf(rs.getString(i+1)));
		
		            } else if (classType.equals(BigDecimal.class)
		                || classType.equals(BigDecimal.class)) {
		            	String value = rs.getString(i+1);
		            	if(value.contains("."))
		            	       record.add(Double.valueOf(rs.getString(i+1)));
		            	else 
		            		 record.add(Integer.valueOf(rs.getString(i+1)));
		
		            } else {
		            	record.add(rs.getString(i+1));
		            }
 			
	}
				tableData.add(record);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("ReportImp", "processtableData" , e);
		}
		
	}

}
