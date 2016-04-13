package util;

import java.util.List;

public class BusinessDayConventionUtil {

	public static String getFollowingDate(List<String> holidayList,
			List<Integer> weekendList, String endDateInString) {

		String followingDate = endDateInString;
		boolean isEndDate = false;

		while (holidayList.contains(followingDate)
				|| checkIfWeekend(followingDate, weekendList)) {

			followingDate = commonUTIL.addSubtractDate(followingDate, 1);

			isEndDate = true;
		}

		if (!isEndDate) {
			return endDateInString;
		} else {
			return followingDate;
		}

	}

	public static String getPrecedingDate(List<String> holidayList,
			List<Integer> weekendList, String endDateInString) {

		String precedingdate = endDateInString;
		boolean isEndDate = false;

		while (holidayList.contains(precedingdate)
				|| checkIfWeekend(precedingdate, weekendList)) {

			precedingdate = commonUTIL.addSubtractDate(precedingdate, -1);

			isEndDate = true;

		}

		if (!isEndDate) {
			return endDateInString;
		} else {
			return precedingdate;
		}

	}

	public static String getModifiedFollowingDate(List<String> holidayList,
			List<Integer> weekendList, String endDateInString) {

		String modifiedFollowingDate = endDateInString;
		boolean isEndDate = false;
		int i = 1;

		while (holidayList.contains(modifiedFollowingDate)
				|| checkIfWeekend(modifiedFollowingDate, weekendList)) {

			if (i == 1) {

				modifiedFollowingDate = commonUTIL.addSubtractDate(
						modifiedFollowingDate, 1);

			}

			if (checkIfNewMonth(modifiedFollowingDate) || i < 0 ) {

								
				if ( i == 1 ) {
					
					i = -1;
					modifiedFollowingDate = endDateInString;
					modifiedFollowingDate = commonUTIL.addSubtractDate(
							modifiedFollowingDate, -1);
					
				} else {
					
					modifiedFollowingDate = commonUTIL.addSubtractDate(
							modifiedFollowingDate, -1);
					
				}
				

			}

			isEndDate = true;

		}

		if (!isEndDate) {
			return endDateInString;
		} else {
			return modifiedFollowingDate;
		}

	}

	private static boolean checkIfNewMonth(String dateInString) {

		// int lastDayofMonth = commonUTIL.getLastDayofMonth(dateInString);

		String[] dateArr = dateInString.split("/");

		if ("01".equals(dateArr[0])) {

			return true;

		} else {

			return false;

		}

	}

	public static boolean checkIfWeekend(String endDate,
			List<Integer> weekendList) {

		Integer day = commonUTIL.getWeekDay(endDate, "dd/MM/yyyy");

		if (weekendList.contains(day)) {
			return true;
		} else {
			return false;
		}
	}
}
