package util.csvReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import beans.DealBean;
import beans.HelperBean;
import beans.HelperDealBean;

public class TradeCsvReader {
	
public static synchronized Vector<DealBean> read( String csvFilePath, HelperDealBean helper ) {
		
	/* 
	  
	ColumnPositionMappingStrategy<DealBean> strat = new ColumnPositionMappingStrategy();
	strat.setType(DealBean.class);
	String[] columns = new String[] {"leName", "bookName", "traderName","type", "price",
			"tradeAmount","deliveryDate","externalTradeId","tradeDate","isin","common","quantity",
			"couponAccrualDays","accrualRate","accrualAmount","nextCouponDate","previousCouponDate",
			"tradeDirtyPrice","tradeCleanPrice","totalAmount"}; // the fields to bind do in your JavaBean
	strat.setColumnMapping(columns);
	
	*/
	
	CSVReader reader = null;
    Vector<DealBean> beans = new Vector<DealBean>();
    String [] record;
        
    try {
		
    	reader = new CSVReader(new FileReader(csvFilePath));
		
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
		
	}      
     
    try {
		
    	while ((record = reader.readNext()) != null) {
			
			DealBean bean = null;

			bean = (DealBean) helper.getBean(record);
			beans.add(bean);
		
		}
		
	} catch (IOException e) {
	
		e.printStackTrace();
		
	}
       
	System.out.println("beans "+ beans.size());
	/*CsvToBean<DealBean> csv = new CsvToBean();
	 
	
	CSVReader csvReader = null;
	try {
		csvReader = new CSVReader(new FileReader(csvFilename));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	List<DealBean> list = csv.parse(strat, csvReader);
	for (Object object : list) {
	    DealBean country = (DealBean) object;
	    System.out.println("---"+country.getBookName());
	}*/
	
	return beans;
}	

	public static void main(String[] args) {
		TradeCsvReader test = new TradeCsvReader();
		//test.read();
	}
}
