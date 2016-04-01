package beans;

import java.io.Serializable;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Vector;

import util.RemoteServiceUtil;
import util.commonUTIL;
import util.common.DateU;

public class DateRule implements Serializable {
	public static final String MANUAL_DATE_SCHEDULE = "MANUL";
	String name = "";
	String holidayCurr = "";
	/**
	 * @return the holidayCurr
	 */
	public String getHolidayCurr() {
		return holidayCurr;
	}
	/**
	 * @param holidayCurr the holidayCurr to set
	 */
	public void setHolidayCurr(String holidayCurr) {
		this.holidayCurr = holidayCurr;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	int id;
	
	
	
	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	/**
	 * @return the weekdaysType
	 */
	public String getWeekdaysType() {
		return weekdaysType;
	}
	/**
	 * @param weekdaysType the weekdaysType to set
	 */
	public void setWeekdaysType(String weekdaysType) {
		this.weekdaysType = weekdaysType;
	}
	/**
	 * @return the dateROLL
	 */
	public String getDateROLL() {
		return dateROLL;
	}
	/**
	 * @param dateROLL the dateROLL to set
	 */
	public void setDateROLL(String dateROLL) {
		this.dateROLL = dateROLL;
	}


	String rank;
	String weekdaysType;
	String dateROLL;
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	String type = "";
    /**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}
	/**
	 * @return the end_beg
	 */
	public String getEnd_beg() {
		return end_beg;
	}
	/**
	 * @param end_beg the end_beg to set
	 */
	public void setEnd_beg(String end_beg) {
		this.end_beg = end_beg;
	}
	/**
	 * @return the mon_year_day_week
	 */
	public String getMon_year_day_week() {
		return mon_year_day_week;
	}
	/**
	 * @param mon_year_day_week the mon_year_day_week to set
	 */
	public void setMon_year_day_week(String mon_year_day_week) {
		this.mon_year_day_week = mon_year_day_week;
	}
	/**
	 * @return the dates
	 */
	public Vector<String> getDates() {
		return dates;
	}
	/**
	 * @param dates the dates to set
	 */
	public void setDates(Vector<String> dates) {
		this.dates = dates;
	}
	
	
	String months = "";
	
	/**
	 * @return the months
	 */
	public String getMonths() {
		return months;
	}
	/**
	 * @param months the months to set
	 */
	public void setMonths(String months) {
		this.months = months;
		String mon [] = months.split(",");
		for(int i=0;i<mon.length;i++) {
			monthss.add(new Integer(mon[i]).intValue());
		}
	}

    
	String rollDays =  "";
	/**
	 * @return the rollDays
	 */
	public String getRollDays() {
		return rollDays;
	}
	/**
	 * @param rollDays the rollDays to set
	 */
	public void setRollDays(String rollDays) {
		this.rollDays = rollDays;
	}
	/**
	 * @return the weekDays
	 */
	public String getWeekDays() {
		return weekDays;
	}
	/**
	 * @param weekDays the weekDays to set
	 */
	public void setWeekDays(String weekDays) {
		this.weekDays = weekDays;
	}


	String weekDays = "";	
			
	int day = 0;
    int days = 0;
    String end_beg = "";
    String mon_year_day_week = "";
    Vector<String> dates = new Vector<String>();
    Vector<Integer> monthss = new Vector<Integer>();
    
    private void getMonthsselected(int month,DateU startU,int noOfCycle) {
    	 	int initalM = month;
    	 	Vector<Integer> mon1 = new Vector<Integer>();
    	 	int y1 = startU.get_year();
    	 	for(int i=0;i<monthss.size();i++) {
    	 			if(dates.size() == noOfCycle)
    	 					break;
    	 			int mon = monthss.get(i);
    		  		if(mon >= month) {
    		  				initalM = mon  - initalM;
    		  					if(initalM != 0) 
    		  						startU.addMonths(initalM);
    		  				initalM = startU.getMonth() ;
    		  				String year = Integer.valueOf(startU.getYear()).toString();
    		  				if(!containDuplicateYear(year)) 
    		  						dates.add(commonUTIL.dateToString(startU.getDate()));
    			
    		  		} else {
    		  			if(mon > 0)
    		  			  mon1.add(mon);
    		  		}
    	}
    	 	for(int i=0;i<mon1.size();i++) {
    			int mon = mon1.get(i);
    			int y2 = startU.getYear();
    			int y = startU.getYear();
    			if(y1 == y2) {
    				Date d = startU.getlastDayofYear();
    				startU = DateU.valueOf(d);
    			}
    			if(startU.getMonth() == 12) {
    			startU.addMonths(mon);
    			} else  {
    				startU.addMonths( mon - startU.getMonth());
    			}
    			String year = Integer.valueOf(startU.getYear()).toString();
    			if(!containDuplicateYear(year)) 
    				dates.add(commonUTIL.dateToString(startU.getDate()));
    		}
    	 	addMissingMonths(startU.getMonth(),startU);
    }
    DateU end1U = null;
    private void addMissingMonths(int month,DateU startU) {
    	for(int i=0;i<monthss.size();i++) {
    		int mon = monthss.get(i);
    		if(mon > month) {
    			int actualMon = startU.getMonth();
    			if(mon < end1U.get_month()) {
    			startU.addMonths(mon -actualMon  );
    			String year = Integer.valueOf(startU.getYear()).toString();
    			if(!containDuplicateYear(year)) 
    				
    			dates.add(commonUTIL.dateToString(startU.getDate()));
    			}
    			
    		}
    	}
    }
    	private boolean containDuplicateYear(String year) {
    				boolean flag = false;
    					if(mon_year_day_week.equalsIgnoreCase("YEAR")) {
    						for(int i=0;i<dates.size();i++) {
    							String date = dates.get(i);
    							String yyyy = date.substring(6, date.length());
    							if(yyyy.equalsIgnoreCase(year)) {
    								flag = true;
    								break;
    							}
    						}
    					}
    		return flag;
    	}
    
    	
    	public Vector<String>  generateDates(String startDate,String endDate,int noOfCycle) {
    		Vector<String> dates1 = null;// = new Vector<String>();
    		Date startD = commonUTIL.stringToDate(startDate,true);
    	   	Date endD = commonUTIL.stringToDate(endDate,true);
    	   	DateU startU = DateU.valueOf(startD);
    	   	int month = startU.getMonth();
    	   	DateU endU = DateU.valueOf(endD);
    	   	end1U = endU;
    	   	
    	   	double diff = startU.diff(startU, endU) / 365;
    	   	startU =  DateU.valueOf(startD);
        	if(diff < 1)
    		 getMonthsselected(month,startU,noOfCycle);
        	else			
        		getMonthsselected(month,startU,diff,noOfCycle);
        	dates1 = addToText(dates,noOfCycle);
        	System.out.println("After addToText " + dates1.size());
        	startD = null;
        	endD = null;
        	return dates1;
    	
    }

private Vector<String> addToText(Vector<String> dates,int noOfCycle) {
	boolean sameYear = false;
	Vector<String> datetoDisplay = new Vector<String>();
	System.out.println("In  addToText " + dates.size());
	for(int d=0;d<dates.size();d++) {
		String date = dates.get(d);
		Date dateD = commonUTIL.stringToDate(date,true);
		DateU dateU = DateU.valueOf(dateD);
		if(mon_year_day_week.equalsIgnoreCase("MON")) {
				if(end_beg.equalsIgnoreCase("END")) {
					int day = dateU.getDayOfMonth();
					int lastDay = dateU.getMonthLength();
					dateU.addDays(lastDay-day);
				}
				if(end_beg.equalsIgnoreCase("BEG")) {
					int day = dateU.getDayOfMonth();
					//int lastDay = dateU.getMonthLength();
					dateU.addDays((day-1) * -1);
				}
		} 
		if(mon_year_day_week.equalsIgnoreCase("YEAR")) {
			
			if(end_beg.equalsIgnoreCase("END")) {
				Date dd = dateU.getlastDayofYear();
				dateU = DateU.valueOf(dd);	
				//dateU.addDays(lastDay-day);
			}
			if(end_beg.equalsIgnoreCase("BEG")) {
				int day = dateU.getdayOftheYear();
				int lastDay = dateU.getMonthLength();
				dateU.addDays((day-1) * -1);
			}
		}
		
		dateU.addDays(day);
		dateU.addDays(days);
		// yogesh
		/* add logic for Date Roll
		 * 
		 * 
		 * 
		 * 
		 */
/*if (getDateROLL().equals("PRECEEDING")) {
			
			dateU.addDays(-1);
			
			while (dateU.isWeekEndDay() ) {
				
				dateU.addDays(-1);
				
			}
			
		} else if (getDateROLL().equals("FOLLOWING")) {
			
			dateU.addDays(1);
			
			while (dateU.isWeekEndDay()) {
				
				dateU.addDays(1);
				
			}
			
		} */
		System.out.println(commonUTIL.dateToString(dateU.getDate()));
datetoDisplay.add(commonUTIL.dateToString(dateU.getDate()));
if(d == noOfCycle)
	break;
		//jTextArea0.append(commonUTIL.dateToString(dateU.getDate()));
		//jTextArea0.append("\n");
	}
	dates.clear();
	System.out.println("In  addToText datetoDisplay == " + datetoDisplay.size());
	return datetoDisplay;
}

private void getMonthsselected(int month,DateU startU,double noofYears, int noOfCycle) {
	int mon = month;
	for(int i=0;i<noofYears;i++) {
			getMonthsselected(mon,startU,noOfCycle);				
			int year = startU.getYear();				
			Date d = startU.getlastDayofYear();
			startU = DateU.valueOf(d);		
			startU.addMonths(1);
			//// // System.out.println(startU.getDate());
			mon = 1;
	}
	
	
}

      public static void main(String args[] ) {
    	     try {
				DateRule rule =  RemoteServiceUtil.getRemoteReferenceDataService().getDateRule("Testing1432");
				rule.generateDates("12/06/2013", "12/06/2014", 4);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	  
      }

}
