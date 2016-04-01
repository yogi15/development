package util;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import util.common.CountDay;

import beans.Holiday;

public class HolidayUtil {

	public static String applyBusinessDayConvention(String currency, String endDate,
			String businessDayConvention) {

		Hashtable<String, List> holidayDetails = getHolidayList(currency,
				endDate);

		String finalEndDate = findEndDate(holidayDetails.get("holidayList"),
				holidayDetails.get("weekendList"), endDate,
				businessDayConvention);

		return finalEndDate;
	}

	private static String findEndDate(List<String> holidayList,
			List<Integer> weekendList, String endDate,
			String businessDayConvention) {

		String returnDate = endDate;

		if (businessDayConvention.equals("PRECEEDING")) {

			returnDate = BusinessDayConventionUtil.getPrecedingDate(
					holidayList, weekendList, returnDate);

		} else if (businessDayConvention.equals("FOLLOWING")) {

			returnDate = BusinessDayConventionUtil.getFollowingDate(
					holidayList, weekendList, returnDate);

		} else if (businessDayConvention.equals("MOD_FOLLOW")) {

			returnDate = BusinessDayConventionUtil.getModifiedFollowingDate(
					holidayList, weekendList, returnDate);

		}

		return returnDate;
	}

	private static Hashtable<String, List> getHolidayList(String currency,
			String Date) {

		Hashtable<String, List> holidayDetails = new Hashtable<String, List>();
		List<String> holidayList = new ArrayList<String>();
		List<Integer> weekendList = new ArrayList<Integer>();

		Holiday holiday = new Holiday();

		Holiday holiday2 = new Holiday();
		holiday.setCurrency(currency);

		try {
			holiday2 = (Holiday) RemoteServiceUtil.getRemoteReferenceDataService().selectHoliday(holiday);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		String holidays = holiday2.getHdate();

		weekendList.add(holiday2.getFweekday());
		weekendList.add(holiday2.getSweekdday());

		holidayList = Arrays.asList(holidays.split("\\s*,\\s*"));

		holidayDetails.put("holidayList", holidayList);
		holidayDetails.put("weekendList", weekendList);

		return holidayDetails;

	}

}
