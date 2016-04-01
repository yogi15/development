package util.csvReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import au.com.bytecode.opencsv.CSVReader;
import beans.DealBean;
import beans.HelperDealBean;
import beans.HelperProductUploaderBean;
import beans.ProductUploaderBean;




/**
 * This Class handles reading of the CSV File and creates list of beans to be migrated.
 * @author pm.
 *
 */
public class CSVProductFileHandler {
	

	protected static BufferedReader reader;			
	protected static final String delim = ",";			// Delimiter for CSV files.
	protected String fileName;
	protected String productType;	

	/**
	 * Default Constructor which creates Buffered Reader.
	 * 
	 * @param fileName - File Path of the input file.
	 * 
	 */
	public CSVProductFileHandler(String fileName) {
		this.fileName = fileName;
		
		try {
			this.reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println( "File Not Found. The FilePath + " + fileName
					+ " is incorrect.");
		}
	}
    
	public CSVProductFileHandler() {
		
	}
	
	
	/**	 
	 * This method creates a bean for every record and adds every bean to a List of Beans.
	 * Which will be returned when EOF is reached.
	 * @param helper - Helper instance of ImportFRATradesHelper.
	 * @return ArrayList<FRABean>
	 */
	public static synchronized  Vector<ProductUploaderBean> read(String csvFilePath,HelperProductUploaderBean helper) {

		
		String currentLine = null;
		String[] record = null;
		
		Vector<ProductUploaderBean> beans = new Vector<ProductUploaderBean>();
		try {
			int  i =0;
		//	this.reader.readLine(); 
			reader = new BufferedReader(new FileReader(csvFilePath)); 
			// Skipping 1st line.
			while ((currentLine = reader.readLine()) != null) {	
				ProductUploaderBean bean = new ProductUploaderBean();
				// Until file ends.
				if(i != 0) {
				record = currentLine.split(delim);
			//	System.out.println(record[24]);
				
			System.out.println(record);
			
					bean = helper.getBean(record);							// Creates the Bean.
					if (bean != null) {
						beans.add(bean);						// Adding each Bean to BeanList.
					} else {
						return null;
					}
			
				}
				i++;
				
			}
		
	/*	CSVReader reader = null;
	 //   Vector<ProductUploaderBean> beans = new Vector<ProductUploaderBean>();
	  //  String [] record;
	    try {
			
	    	reader = new CSVReader(new FileReader(csvFilePath));
			
		} catch (IOException e) {
			System.out.println( "Cannot read the file.");
			return beans;
		}   
	     
	    try {
			
	    	while ((record = reader.readNext()) != null) {
				
	    		ProductUploaderBean bean = null;

				bean = (ProductUploaderBean) helper.getBean(record);
				beans.add(bean);
			
			}*/
			
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}
			return beans;
		
	}

	public BufferedReader getBufferedReader() {
		return this.reader;
	}
	
	/*public String getDealData(DealBean bean) {
		String deal = "";
		
		DealBean dealBean = new DealBean();
		deal = deal + bean.getMemberName() + ",";
		deal = deal + bean.getMember() + ",";
		deal = deal + bean.getDEALERname() + ",";
		deal = deal + bean.getDealer() + ",";
		deal = deal + bean.getMarket() + ",";
		deal = deal + bean.getSubMarket() + ",";
			

		deal = deal + bean.getOrderNumber() + ",";
		deal = deal + bean.getTradeDate() + ",";
		deal = deal + bean.getTradeTime() + ",";
		deal = deal + bean.getTradeNumber() + ",";
		deal = deal + bean.getTradeType() + ",";
		deal = deal + bean.getSettlementType() + ",";
		deal = deal + bean.getSettlement() + ",";
			//dealBean.setDate(record[12]);
		deal = deal + bean.getISIN() + ",";
		deal = deal + bean.getGenspec() + ",";
		deal = deal + bean.getSecurity() + ",";
		deal = deal + bean.getMaturityDate() + ",";
		//	dealBean.setAmount(record[17]);
		deal = deal + bean.getFV() + ",";
		deal = deal + bean.getTradePrice() + ",";
		deal = deal + bean.getTradeYield() + ",";
		deal = deal + bean.getTradeAmount() + ",";
		deal = deal + bean.getLastInterest() + ",";
		//	dealBean.setPaymentDate(record[22]);
			 
		deal = deal + bean.getNumberofBrokenPeriodDays() + ",";
		deal = deal + bean.getAccruedInterest() + ",";
		deal = deal + bean.getSettConsiderationRs() + ",";
		
		return deal;
			
	}
	*/
	
	public static void main(String args[]) {
		CSVFileHandler filehander = new CSVFileHandler();
			Thread t = new Thread(filehander);
			t.start();
			
	}
	
	public static void writeFile(String s,String name,DealBean bean) {
		try {
			SimpleDateFormat formatter;
			Date today = new Date();
		//	Locale
			String pattern = "yyyyMMddhhmmss";
			

			formatter = new SimpleDateFormat(pattern);
			String output = formatter.format(today);
			File file = null;//new File("E:\\deals\\" + output.substring(0, 8) +"_"+bean.getId()+"_Deal Ticket_"+output+".csv");
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(s);
			bw.close();
 


			
		}catch (Exception e) {
            System.out.println("Exception : " + e);
            //Log.error(this, e);
            } 
	}

	//@Override
/*	public void run() {
		// TODO Auto-generated method stub
		for( ; ; ) {
			try {
			DealBean bean = null; //read("E:\\pankaj\\sunrise\\OMS-Data\\CCIL OMS DEALS\\20130122_31285_Deal Ticket_2013012200078732.csv", new HelperDealBean());
		
			String deal = getDealData(bean);
			writeFile(deal, null,bean);
				Thread.sleep(9000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}*/
}
