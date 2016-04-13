package util.common;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import util.commonUTIL;
import util.common.holiday.Holiday;

import beans.HolidayCode;

import dsServices.RemoteReferenceData;

public class HolidayRule {
	
	
	static String FOLLOWING = "FOLLOWING";
	 static String END_MONTH = "END_MONTH";
	 static String IMM_MON = "IMM_MON";
	 static String IMM_WED = "IMM_WED";
	 static String IMM_FOLLOW = "IMM_FOLLOW";
	 static String MOD_PRECED = "MOD_PRECED";
	 static String MOD_SUCC = "MOD_SUCC";
	 static String NO_CHANGE = "NO_CHANGE";
	 
	 
	 
	 static String ADJUSTED = "ADJUSTED";
	 static String UNADJUSTED = "UNADJUSTED";
	 
	 HolidayRule instance = null;
	 RemoteReferenceData ref = null;
	 Vector holidayCode = null;
	 
	 
	 
	 private  HolidayRule() {
		 if(instance == null) {
			 instance = new HolidayRule();
			 getHolidays();
		 }
		 
		 
	 }
	 
	 
	 public void getHolidays() {
		 try {
			 holidayCode = (Vector) ref.selectALLHolidays();
			 
		 }catch(RemoteException e) {
			 
		 }
	 }
	 
	 
	 public HolidayCode getHolidayOnCode(String code) {
		 HolidayCode holiday = null;
		 Iterator<HolidayCode> holi = holidayCode.iterator();
		 while(holi.hasNext()) {
			 HolidayCode hd = holi.next();
			if(hd.getCurrency().equalsIgnoreCase(code)) {
				holiday = hd;
				break;
			}
		 }
		 return holiday;
	 }
	 
	 public int checkHolidayExists(HolidayCode holi,DateU date,Period period) {
		 boolean flag = false;
		 String holidays [] = getHolidayArray(holi.getHdate());
		 int counter = 0;
		 for(int i=0;i<holidays.length;i++) {
			 Date dateh = commonUTIL.stringToDate(holidays[i], false);
			 if(checkHolidayexistsinDates(dateh,date,period.getName())) {
				 counter++;
			 }
		 }
		 return counter;
		 
	 }
     
	 
	 public boolean isHoliday(HolidayCode holi,DateU date,Period period) {
		 
		 boolean flag = false;
		 String holidays [] = getHolidayArray(holi.getHdate());
		
		 for(int i=0;i<holidays.length;i++) {
			 Date dateh = commonUTIL.stringToDate(holidays[i], false);
			 if(checkHolidayexistsinDates(dateh,date,period.getName())) {
				flag = true;
				
			 }
		 }
		 return flag;
		 
	 }

	private String[] getHolidayArray(String hdate) {
		// TODO Auto-generated method stub
		String holidayDates [] = null;
		if(!commonUTIL.isEmpty(hdate))  {
			holidayDates = hdate.split(",");
		}
		return holidayDates;
	}
	 
	private boolean checkHolidayexistsinDates(Date date,DateU tdate,String freq) {
		Date startDate = tdate.getDate();
		tdate.convertFrequecyCode(freq);
		return commonUTIL.between2dates(startDate, tdate.getDate(), date);
		
	}
	 
	 
	 
	 

}
