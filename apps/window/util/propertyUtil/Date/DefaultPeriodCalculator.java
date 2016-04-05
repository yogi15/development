package apps.window.util.propertyUtil.Date;
 

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import util.common.CDate;

public class DefaultPeriodCalculator implements  PeriodAdapter {

	@Override
	public CDate applyTenor(CDate date, String tenor) {
		// TODO Auto-generated method stub
		return UtilFormat.checkYearTerm(tenor, date);
	}

	@Override
	public String reverseTenor(CDate base, CDate target) {
		// TODO Auto-generated method stub
		 if (base == null || target == null)
	            return "";

	        Date start = base.getDate(TimeZone.getDefault());
	        Date end = target.getDate(TimeZone.getDefault());

	        Calendar startCalendar = Calendar.getInstance();
	        startCalendar.setTime(start);
	        Calendar endCalendar = Calendar.getInstance();
	        endCalendar.setTime(end);

	        StringBuffer sb = new StringBuffer();

	        final int endYear = endCalendar.get(Calendar.YEAR);
	        final int startYear = startCalendar.get(Calendar.YEAR);
	        final int endMonth = endCalendar.get(Calendar.MONTH);
	        final int startMonth = startCalendar.get(Calendar.MONTH);
	        final int endDay = endCalendar.get(Calendar.DAY_OF_MONTH);
	        final int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);

	        int diff = endYear - startYear - (endMonth < startMonth || (endMonth == startMonth && endDay < startDay)? 1 : 0);
	        if (diff > 0) {
	            sb.append(diff);
	            sb.append("Y");
	        }

	        diff = endMonth > startMonth ? endMonth - startMonth - (endDay < startDay ? 1 : 0) : endMonth < startMonth? 12 - startMonth + endMonth : endDay < startDay ? 11 : 0;
	        if (diff > 0) {
	            if (sb.length() > 0)
	                sb.append(" ");
	            sb.append(diff);
	            sb.append("M");
	        }

	        diff = endDay >= startDay ? endDay - startDay : startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) - startDay + endDay;
	        if (diff > 0 || sb.length() == 0) {
	            if (sb.length() > 0)
	                sb.append(" ");
	            sb.append(diff);
	            sb.append("D");
	        }

	        return sb.toString();
	    
	}

}
