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

import beans.Deal;
import beans.DealBean;
import beans.HelperBean;
import beans.HelperDealBean;




/**
 * This Class handles reading of the CSV File and creates list of beans to be migrated.
 * @author pm.
 *
 */
public class CSVFileHandler implements Runnable {
	

	protected static BufferedReader reader;			
	protected static String delim = ",";			// Delimiter for CSV files.
	protected String fileName;
	protected String productType;	

	/**
	 * Default Constructor which creates Buffered Reader.
	 * 
	 * @param fileName - File Path of the input file.
	 * 
	 */
	public CSVFileHandler(String fileName) {
		this.fileName = fileName;
		
		try {
			this.reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println( "File Not Found. The FilePath + " + fileName
					+ " is incorrect.");
		}
	}
    
	public CSVFileHandler() {
		
	}
	
	
	/**	 
	 * This method creates a bean for every record and adds every bean to a List of Beans.
	 * Which will be returned when EOF is reached.
	 * @param helper - Helper instance of ImportFRATradesHelper.
	 * @return ArrayList<FRABean>
	 */
	public static synchronized  Vector<Deal> read(String fileName,HelperBean helper) {

		
		String currentLine = null;
		String[] record = null;
		
		Vector<Deal> beans = new Vector<Deal>();
		try {
			int  i =0;
		
			reader = new BufferedReader(new FileReader(fileName)); 
			
			// Skipping 1st line.
			while ((currentLine = reader.readLine()) != null) {	
				
				Deal bean = null;
				
				if(i != 0) {
					
					record = currentLine.split(delim);
							
					System.out.println(record);
			        if(record.length > 0) {
					bean = (Deal) helper.getBean(record);
								// Creates the Bean.
					
					if (bean != null) {
						
						beans.add(bean);						// Adding each Bean to BeanList.
					
					} else {
					
						return null;
					
					}
			        }
				}
				i++;
				
			}
			return beans;
		} catch (IOException e) {
			System.out.println( "Cannot read the file.");
			return beans;
		}
		
	}

	public BufferedReader getBufferedReader() {
		return this.reader;
	}
	
	/*public String getDealData(DealBean bean) {
		String deal = "";
		DealBean dealBean = new DealBean();
		deal = deal + bean.getLeName() + ",";
		deal = deal + bean.getBookName() + ",";
		deal = deal + bean.getTraderName() + ",";
		deal = deal + bean.getType() + ",";
		deal = deal + bean.getPrice() + ",";
		deal = deal + bean.getTradeAmount() + ",";
			

		deal = deal + bean.getDeliveryDate() + ",";
		deal = deal + bean.getExternalTradeId() + ",";
		deal = deal + bean.getTradeDate() + ",";
		deal = deal + bean.getIsin() + ",";
		deal = deal + bean.getCommon() + ",";
		deal = deal + bean.getQuantity() + ",";
		deal = deal + bean.getAccrualDays() + ",";
			//dealBean.setDate(record[12]);
		deal = deal + bean.getAccrualRate() + ",";
		deal = deal + bean.getAccrualAmount() + ",";
		deal = deal + bean.getNextCouponDate() + ",";
		deal = deal + bean.getPreviousCouponDate() + ",";
		//	dealBean.setAmount(record[17]);
		deal = deal + bean.getTradeDirtyPrice() + ",";
		deal = deal + bean.getTradeCleanPrice() + ",";
		deal = deal + bean.getTotalAmount() + ",";
				
			
			return deal;
			
			
			
			
	} */
	
	public static void main(String args[]) {
		CSVFileHandler filehander = new CSVFileHandler();
			Thread t = new Thread(filehander);
			t.start();
			
		
		 
		
	}
	public static void writeFile(String s,String name,DealBean bean) {
		/*try {
			SimpleDateFormat formatter;
			Date today = new Date();
		//	Locale
			String pattern = "yyyyMMddhhmmss";
			

			formatter = new SimpleDateFormat(pattern);
			String output = formatter.format(today);
			File file = new File("E:\\deals\\" + output.substring(0, 8) +"_"+bean.getTraderName()+"_Deal Ticket_"+output+".csv");
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
            } */
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for( ; ; ) {
			try {
			DealBean bean = null; //read("E:\\pankaj\\sunrise\\OMS-Data\\CCIL OMS DEALS\\20130122_31285_Deal Ticket_2013012200078732.csv", new HelperDealBean());
		
			String deal = null;// getDealData(bean);
			writeFile(deal, null,bean);
				Thread.sleep(9000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}
}
