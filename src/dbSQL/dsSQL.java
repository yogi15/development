package dbSQL;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.Properties;

import javax.swing.JOptionPane;

import util.commonUTIL;



public class dsSQL {
	
	 static String driver  	 = "oracle.jdbc.driver.OracleDriver"; //"sun.jdbc.odbc.JdbcOdbcDriver";
	 static String url     	 = "jdbc:oracle:thin:@localhost:1521:XE";  //jdbc:odbc:LibrarySystem";
	 static String DBUserName 	 = "sbi";
	 static String DBPassword 	 = "sbi";
	static Connection   con = null;
	static Connection conu = null;
	
	 public static String CFG_FILE = "application.properties";
	 public static Connection getConn() {
		return con;
	}

	 public static Connection getupdateConn() {
			return conu;
		}
	protected dsSQL() {}
	static {
		try 	
		{
		
	
			
			Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(CFG_FILE));
    
            driver  = prop.getProperty("driver");
            DBUserName = prop.getProperty("DBUserName");
            DBPassword = prop.getProperty("DBPassword");
            url = prop.getProperty("url");
            Class.forName(driver);
			
		}
		catch(java.lang.ClassNotFoundException ex)
		{
			commonUTIL.displayError("dsSQL"," static block", ex);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("dsSQL"," static block", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("dsSQL"," static block", e);
		}
		try
		{
		 con = DriverManager.getConnection(url,DBUserName ,DBPassword);
		 conu = DriverManager.getConnection(url,DBUserName ,DBPassword);
		 
		}
			catch(Exception ex)
			{
				commonUTIL.displayError("dsSQL"," conn issue .. ", ex);
			}
	}
	
	
	
	
	static public PreparedStatement newPreparedStatement(Connection con, String st) throws SQLException {

        
        PreparedStatement stmt = null;
        try {
        	con.setAutoCommit(false);
            stmt = con.prepareStatement(st);
           
           // con.setTransactionIsolation(level)
        }
        catch (SQLException e) {
        	commonUTIL.displayError(" dsSQL ","newPreparedStatement ",e);
            throw e;
        }
       
        return stmt;
    }
  static public PreparedStatement updatePreparedStatement(Connection con, String st) throws SQLException {

        
        PreparedStatement stmt = null;
        try {
        	conu.setAutoCommit(false);
            stmt = conu.prepareStatement(st,ResultSet.TYPE_SCROLL_SENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
           // con.setTransactionIsolation(level)
        }
        catch (SQLException e) {
        	commonUTIL.displayError(" dsSQL ","newPreparedStatement ",e);
            throw e;
        }
       
        return stmt;
    }
	public static void main(String args[]) {
		dsSQL.getConn();
		
	}

}
