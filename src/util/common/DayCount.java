package util.common;

import java.util.HashMap;
import java.util.Map;


public class DayCount {
	
   final  String d_30_360 = "30_360";
	final  String y_30_360 ="30_360";
	 final public  int D30360 = 1;
    private static Map dayCountsByDays = new HashMap ();
    private static Map  dayCountsByyears = new HashMap ();
    int _dc = 0;
    public DayCount(int i) {
    	_dc = i;
    }
    
   static double dayCount  = 0;
	 static double getDayCount(String dayCountName) {
	
	 double dcount = dayCount;
	 
	 return dcount;
}
	 
	 
	 public double getDayCount(DateU start, DateU end,double amount) {
		 switch (_dc) { 
		 case D30360 :
             return (yearDiff30_360(start, end, amount));
		 default :
			 return 0;
		 }
		
	 }
	 private double yearDiff30_360(DateU start, DateU end) {
		 int y2 = end.getYear();
	        int m2 = end.getMonth();
	        int d2 = end.getDay();
	        int y1 = start.getYear();
	        int m1 = start.getMonth();
	        int d1 = start.getDay();

        if (d1 == 30
                || d1 == 31) {
            if (d2 == 31)
                d2 = 30;
        }
        if (d1 == 31)
            d1 = 30;

        return ((d2 - d1)
                + 30 * (m2 - m1) + 360 * (y2 - y1)) / 360.0;
    }

    static private double yearDiff30_360(DateU start, DateU end, double amount) {
        int y2 = end.getYear();
        int m2 = end.getMonth();
        int d2 = end.getDay();
        int y1 = start.getYear();
        int m1 = start.getMonth();
        int d1 = start.getDay();

        if (d1 == 30
                || d1 == 31) {
            if (d2 == 31)
                d2 = 30;
        }
        if (d1 == 31)
            d1 = 30;

        return ((d2 - d1)
                + 30 * (m2 - m1) + 360 * (y2 - y1))
                * amount / 360.0;
    }

    static private int dayDiff30_360(DateU start, DateU end) {
        int y2 = end.getYear();
        int m2 = end.getMonth();
        int d2 = end.getDay();
        int y1 = start.getYear();
        int m1 = start.getMonth();
        int d1 = start.getDay();

        if (d1 == 30
                || d1 == 31) {
            if (d2 == 31)
                d2 = 30;
        }
        if (d1 == 31)
            d1 = 30;

        return ((d2 - d1)
                + 30 * (m2 - m1) + 360 * (y2 - y1));
    }


}
