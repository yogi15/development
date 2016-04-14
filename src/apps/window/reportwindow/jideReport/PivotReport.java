package apps.window.reportwindow.jideReport;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import beans.Trade;



public class PivotReport implements Report {
 public String [] datatypes;
public  String [] headers;
public  String [][] data12;
 Vector<String> header = new Vector<String>();

@SuppressWarnings("unchecked")
 Vector<Class> types = new Vector<Class>();

 Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();

 static transient SimpleDateFormat FORMAT =new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

	public PivotReport(String csvData) {
		// TODO Auto-generated constructor stub
	}

	public static Report PivotReport(final String csvData) {
    //    if( StringUtils.isBlank(csvData)) {
     //       return new NullReport();
       // }
        return new PivotReport(csvData);
    }
	
	
	@Override
	public Vector<Vector<Object>> getData() {
		// TODO Auto-generated method stub
		return tableData;
	}
	
	public void processData(String s[][]) {
		
	}
	
	public void setdatatype(Vector typesClass) {
		types = typesClass;
	}

	@Override
	public Vector<String> getDataHeader() {
		// TODO Auto-generated method stub
		 return header;
	}

	@Override
	public Class<?>[] getDataType() {
        return types.toArray(new Class[0]);
    }
	
	public void setHeader(Vector headersColumns) {
		header = headersColumns;
	}
	public void setData(Vector records) {
		tableData = records;
	}
	public void setData(String s[][]) {
		int length = s.length;
		// final Vector<Object> lineData = new Vector<Object>();
		for (int r=0; r < s.length; r++) {
		    for (int c=0; c < s[r].length; c++) {
		        System.out.print(" " + s[r][c]);
		    }
		    System.out.println("");
		} 
		
		int columnCnt = 0;
		for (int r=0; r < s.length; r++) {
			 final Vector<Object> lineData = new Vector<Object>();
			 System.out.println( " Row == " + r);
			 columnCnt = 0;
		    for (int c=0; c < s[r].length; c++) {
		    	 	System.out.print( " columns == " + c + s[r][c]);
		    	 	final Class<?> classType = getDataType()[columnCnt];
		    	 				if (classType.equals(Date.class)) {
					                try {
					                    // Sun Mar 13 19:00:00 CDT 2011
					                    // EEE MMM dd HH:mm:ss z yyyy
					                    final Date date = FORMAT.parse(s[r][c]);
					                    lineData.add(date); // order date
					                } catch (final ParseException e) {
					                    e.printStackTrace();
					                }
						            } else if (classType.equals(String.class)) {
						                lineData.add(s[r][c]);
						
						            } else if (classType.equals(double.class)
						                || classType.equals(Double.class)) {
						                lineData.add(Double.valueOf(s[r][c]));
						
						            } else if (classType.equals(int.class)
						                || classType.equals(Integer.class)) {
						                lineData.add(Integer.valueOf(s[r][c]));
						
						            } else if (classType.equals(Math.class)
						                || classType.equals(Math.class)) {
						                lineData.add(Integer.valueOf(s[r][c]));
						
						            } else if (classType.equals(BigDecimal.class)
						                || classType.equals(BigDecimal.class)) {
						                lineData.add(Integer.valueOf(s[r][c]));
						
						            } else {
						                lineData.add(s[r][c]);
						            }
		    	 				columnCnt++;
		    		}
		    tableData.add(lineData);
		
        }
		
		
	}

}
