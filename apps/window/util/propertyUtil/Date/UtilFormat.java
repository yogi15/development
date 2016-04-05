package apps.window.util.propertyUtil.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

import util.commonUTIL;
import util.common.CDate;
import util.common.CountDay;
import util.common.Frequency;
import util.common.Period;
import util.common.RollDate;
import util.common.UtilDate;
import util.common.holiday.Holiday;
 
/**
 * Utilty class that formats the dates and numbers required by
 * the system.
 */
public class UtilFormat {
	 /**
     * Returns a date (CDate) that results from applying a given tenor to a
     * given starting date.
     * <p>
     * If the starting date is null, applies the tenor to today.
     *
     * @param tenor     a tenor (String)
     * @param startDate a CDate, can be null
     */
    static public  CDate checkYearTerm(String tenor, CDate startDate) {
        if(startDate==null) {
            startDate=CDate.getNow();
        }
        String s=tenor;
        if (commonUTIL.isEmpty(s)) {
            return null;
        }
        String S = s.toUpperCase();
        char firstChar=S.charAt(0);
        if(S.indexOf("Y")>0) {
            int idx = S.indexOf("Y");
            String rs=S.substring(0,idx);
            String yy=checkValDouble(rs);
            if(yy.length() > 0) {
                double nbyd= Double.parseDouble(yy);
                int nbmonths= (int)(nbyd*12);
                //YL LUNAR Year calculation
                if (S.indexOf("L") == idx + 1){
                    nbmonths = (int)nbyd  * 13 *28;
                    CDate d = startDate.addDays(nbmonths);
                    return d;
                }
                CDate d = startDate.addMonths(nbmonths);
                // 'YC'
                if (S.indexOf("C") == idx + 1) {
                    d = UtilDate.getCDSDate(d, Frequency.QUARTERLY);
                } else if (S.indexOf("E") == idx + 1) {
                    d = d.getEOM();
                }
                return d;
            }
            return null;
        }
        if(S.indexOf("M")>0 ) {
            int idx = S.indexOf("M");
            String rs=S.substring(0,idx);
            String mm= checkValInt(rs);
            if(mm.length()>0){
                int nbm= Integer.parseInt(mm);
                CDate d = startDate.addMonths(nbm);
                // 'MC'
                if (S.indexOf("C") == idx + 1) {
                    d = UtilDate.getCDSDate(d, Frequency.QUARTERLY);
                } else if (S.indexOf("E") == idx + 1) {
                    d = d.getEOM();
                }
                return d;
            }
            return null;
        }
        if(S.indexOf("W")>0 ) {
            int idx = S.indexOf("W");
            String rs=S.substring(0,idx);
            String ww= checkValInt(rs);
            if(ww.length() > 0){
                int nbw= Integer.parseInt(ww);
                CDate d = startDate.addDays(7*nbw);
                // 'WC'
                if (S.indexOf("C") == idx + 1) {
                    d = UtilDate.getCDSDate(d, Frequency.QUARTERLY);
                }
                return d;
            }
            return null;
        }
        if(S.indexOf("D")>0 ) {
            int idx = S.indexOf("D");
            String rs=S.substring(0,idx);
            String dd= checkValInt(rs);
            if(dd.length() > 0){
                int nbd= Integer.parseInt(dd);
                CDate d = startDate.addDays(nbd);
                // 'DC'
                if (S.indexOf("C") == idx + 1) {
                    d = UtilDate.getCDSDate(d, Frequency.QUARTERLY);
                }
                return d;
            }
            return null;
        }
        else {
            int month=0;
            switch (firstChar) {
            case  'F': month=1; break;
            case  'G': month=2; break;
            case  'H': month=3; break;
            case  'J': month=4; break;
            case  'K': month=5; break;
            case  'M': month=6; break;
            case  'N': month=7; break;
            case  'Q': month=8; break;
            case  'U': month=9; break;
            case  'V': month=10; break;
            case  'D': month=10; break;
            case  'X': month=11; break;
            case  'Z': month=12; break;
            default: return null;
            }
            int nby=0;
            if (S.length() == 3) {
                int digit1 = (int)S.charAt(1) - '0';
                int digit2 = (int)S.charAt(2) - '0';
                //check that we have digits
                if (((0 <= digit1) && (digit1 <= 9)) &&
                    ((0 <= digit2) && (digit2 <= 9)))
                    nby = digit1 * 10 + digit2;
                else
                    return null;
            }
            else if (S.length() == 2) {
                int dig = (int)S.charAt(1) - '0';
                //check that we have a digit
                if ((0 <= dig) && (dig <= 9))
                    nby = dig;
                else
                    return null;
            }
            else
                return null;
            CDate today= CDate.getNow();
            int thisyear=today.getYear() < 2000 ? today.getYear()-1900 :
                today.getYear()-2000;
            if(nby < thisyear) nby += 100;
            int year=(today.getYear() < 2000 ? 1900 + nby :
                      2000 + nby);
            CDate d= CDate.valueOf(year,month,1);
            d = UtilDate.getIMMDate(d,CDate.WEDNESDAY);
            return d;
        }
    }

    /**
     * Returns a date (CDate) that results from applying a given tenor to a
     * given starting date, using a given set of Holidays.
     * <p>
     * If the starting date is null, applies the tenor to today.
     *
     * @param tenor     a tenor (String)
     * @param startDate a CDate, can be null
     * @param holidays  a Vector containing Holidays
     */
    static public  CDate checkYearTerm(String tenor, CDate startDate,Vector holidays) {
        if(startDate==null) {
            startDate=CDate.getNow();
        }
        String s=tenor;
        if (commonUTIL.isEmpty(s)) {
            return null;
        }
        
        //BZ 43225
        CDate commTenorDate = convertCommDateStringTenorToDate(s);
        if(commTenorDate != null)
            return commTenorDate;
        
        String S = s.toUpperCase();
        char firstChar=S.charAt(0);
        if(S.indexOf("Y")>0) {
            int idx = S.indexOf("Y");
            String rs=S.substring(0,idx);
            String yy=checkValDouble(rs);
            if(yy.length() > 0) {
                double nbyd= Double.parseDouble(yy);
                int nbmonths= (int)(nbyd*12);
                //YL LUNAR Year calculation
                if (S.indexOf("L") == idx + 1){
                    nbmonths = (int)nbyd  * 13 *28;
                    CDate d = startDate.addDays(nbmonths);
                    return d;
                }
                CDate d = startDate.addMonths(nbmonths);
                // 'YC'
                if (S.indexOf("C") == idx + 1) {
                    d = UtilDate.getCDSDate(d, Frequency.QUARTERLY);
                } else if (S.indexOf("E") == idx + 1) {
                    d = getEOMDate(d, holidays);
                }
                return d;
            }
            return null;
        }
        if(S.indexOf("M")>0 ) {
            int idx = S.indexOf("M");
            String rs=S.substring(0,idx);
            String mm= checkValInt(rs);
            if(mm.length()>0){
                int nbm= Integer.parseInt(mm);
                CDate d = startDate.addMonths(nbm);
                // 'MC'
                if (S.indexOf("C") == idx + 1) {
                    d = UtilDate.getCDSDate(d, Frequency.QUARTERLY);
                } else if (S.indexOf("E") == idx + 1) {
                    d = getEOMDate(d, holidays);
                }
                return d;
            }
            return null;
        }
        if(S.indexOf("W")>0 ) {
            int idx = S.indexOf("W");
            String rs=S.substring(0,idx);
            String ww= checkValInt(rs);
            if(ww.length() > 0){
                int nbw= Integer.parseInt(ww);
                CDate d = startDate.addDays(7*nbw);
                // 'WC'
                if (S.indexOf("C") == idx + 1) {
                    d = UtilDate.getCDSDate(d, Frequency.QUARTERLY);
                }
                return d;
            }
            return null;
        }
        if(S.indexOf("D")>0 ) {
            int idx = S.indexOf("D");
            String rs=S.substring(0,idx);
            String dd= checkValInt(rs);
            if(dd.length() > 0){
                int nbd= Integer.parseInt(dd);
                CDate d = startDate.addDays(nbd);
                // 'DC'
                if (S.indexOf("C") == idx + 1) {
                    d = UtilDate.getCDSDate(d, Frequency.QUARTERLY);
                }
                return d;
            }
            return null;
        }
        if (S.indexOf("B")>0){
            int idx = S.indexOf("B");
            String rs=S.substring(0,idx);
            String dd= checkValInt(rs);
            if(dd.length() > 0){
                int nbd= Integer.parseInt(dd);
                CDate d = null;
                if (holidays == null){

                    commonUTIL.display ("FormatUtil","NO Holidays specified");
                    d = startDate.addDays(nbd);
                }else{
                    d = Holiday.getCurrent().
                        addBusinessDays(startDate,holidays,nbd);
                }
                // 'BC'
                if (S.indexOf("C") == idx + 1) {
                    d = UtilDate.getCDSDate(d, Frequency.QUARTERLY);
                }
                return d;
            }
            return null;
        }
        else {
            int month=0;
            switch (firstChar) {
            case  'F': month=1; break;
            case  'G': month=2; break;
            case  'H': month=3; break;
            case  'J': month=4; break;
            case  'K': month=5; break;
            case  'M': month=6; break;
            case  'N': month=7; break;
            case  'Q': month=8; break;
            case  'U': month=9; break;
            case  'V': month=10; break;
            case  'D': month=10; break;
            case  'X': month=11; break;
            case  'Z': month=12; break;
            default: return null;
            }
            int nby=0;
            if (S.length() == 3) {
                int digit1 = (int)S.charAt(1) - '0';
                int digit2 = (int)S.charAt(2) - '0';
                //check that we have digits
                if (((0 <= digit1) && (digit1 <= 9)) &&
                    ((0 <= digit2) && (digit2 <= 9)))
                    nby = digit1 * 10 + digit2;
                else
                    return null;
            }
            else if (S.length() == 2) {
                int dig = (int)S.charAt(1) - '0';
                //check that we have a digit
                if ((0 <= dig) && (dig <= 9))
                    nby = dig;
                else
                    return null;
            }
            else
                return null;
            CDate today= CDate.getNow();
            int thisyear=today.getYear() < 2000 ? today.getYear()-1900 :
                today.getYear()-2000;
            if(nby < thisyear) nby += 100;
            int year=(today.getYear() < 2000 ? 1900 + nby :
                      2000 + nby);
            CDate d= CDate.valueOf(year,month,1);
            d = UtilDate.getIMMDate(d,CDate.WEDNESDAY);
            return d;
        }
    }

    static private CDate getEOMDate(CDate date, Vector holidays) {
        date = date.getEOM();
        util.common.RollDate RollDate = util.common.RollDate.get(util.common.RollDate.S_PRECEDING);
        return RollDate.roll(date, holidays);
    }

    /**
     * Returns a date (CDate) that results from applying a given tenor to a
     * given starting date, using a given set of Holidays.
     * <p>
     * If the starting date is null, applies the tenor to today.
     *
     * @param tenor     a tenor (String)
     * @param startDate a CDate, can be null
     * @param holidays  a Vector containing Holidays
     */
    static public  CDate checkYearTermForFXTenor(String tenor, CDate startDate,Vector holidays) {
        if(startDate==null) {
            startDate=CDate.getNow();
        }
        String s=tenor;
        if (commonUTIL.isEmpty(s)) {
            return null;
        }
        String S = s.toUpperCase();
        char firstChar=S.charAt(0);
        if(S.indexOf("Y")>0) {
            int idx = S.indexOf("Y");
            String rs=S.substring(0,idx);
            String yy=checkValDouble(rs);
            if(yy.length() > 0) {
                double nbyd= Double.parseDouble(yy);
                int nbmonths= (int)(nbyd*12);
                // if the start date is on the last business day of the month, the tenor
                // date needs to be on the last business day of the month also.
                CDate d = null;
                if (holidays != null)
                    d = UtilDate.addMonthsObserveEOM(startDate,nbmonths,holidays);
                else
                    d = UtilDate.addMonthsObserveEOM(startDate,nbmonths);

                if(holidays != null && !Holiday.getCurrent().isBusinessDay(d,holidays)) {
                    RollDate dr = RollDate.valueOf(RollDate.MOD_FOLLOWING);
                    d = dr.roll(d,holidays);
                }

                return d;
            }
            return null;
        }
        if(S.indexOf("M")>0 ) {
            int idx = S.indexOf("M");
            String rs=S.substring(0,idx);
            String mm= checkValInt(rs);
            if(mm.length()>0){
                int nbm= Integer.parseInt(mm);
                // if the start date is on the last business day of the month, then the tenor
                // date needs to be on the last business day of the month also.
                CDate d = null;
                if (holidays != null)
                    d = UtilDate.addMonthsObserveEOM(startDate,nbm,holidays);
                else
                    d = UtilDate.addMonthsObserveEOM(startDate,nbm);
                if(holidays != null && !Holiday.getCurrent().isBusinessDay(d,holidays)) {
                    RollDate dr = RollDate.valueOf(RollDate.MOD_FOLLOWING);
                    d = dr.roll(d,holidays);
                }
                return d;
            }
            return null;
        }
        if(S.indexOf("W")>0 ) {
            int idx = S.indexOf("W");
            String rs=S.substring(0,idx);
            String ww= checkValInt(rs);
            if(ww.length() > 0){
                int nbw= Integer.parseInt(ww);
                CDate d = startDate.addDays(7*nbw);
                if(holidays != null && !Holiday.getCurrent().isBusinessDay(d,holidays)) {
                    RollDate dr = RollDate.valueOf(RollDate.FOLLOWING);
                    d = dr.roll(d,holidays);
                }
                return d;
            }
            return null;
        }
        if(S.indexOf("D")>0 ) {
            int idx = S.indexOf("D");
            String rs=S.substring(0,idx);
            String dd= checkValInt(rs);
            if(dd.length() > 0){
                int nbd= Integer.parseInt(dd);
                CDate d = startDate.addDays(nbd);
                if(holidays != null && !Holiday.getCurrent().isBusinessDay(d,holidays)) {
                    RollDate dr = RollDate.valueOf(RollDate.FOLLOWING);
                    d = dr.roll(d,holidays);
                }
                return d;
            }
            return null;
        }
        if (S.indexOf("B")>0){
            int idx = S.indexOf("B");
            String rs=S.substring(0,idx);
            String dd= checkValInt(rs);
            if(dd.length() > 0){
                int nbd= Integer.parseInt(dd);
                CDate d = null;
                if (holidays == null){
                 commonUTIL.display ("FormatUtil","NO Holidays specified" );
                    d = startDate.addDays(nbd);
                }else{
                    d = Holiday.getCurrent().
                        addBusinessDays(startDate,holidays,nbd);
                }
                return d;
            }
            return null;
        }
        else {
            int month=0;
            switch (firstChar) {
            case  'F': month=1; break;
            case  'G': month=2; break;
            case  'H': month=3; break;
            case  'J': month=4; break;
            case  'K': month=5; break;
            case  'M': month=6; break;
            case  'N': month=7; break;
            case  'Q': month=8; break;
            case  'U': month=9; break;
            case  'V': month=10; break;
            case  'D': month=10; break;
            case  'X': month=11; break;
            case  'Z': month=12; break;
            default: return null;
            }
            int nby=0;
            if (S.length() == 3) {
                int digit1 = (int)S.charAt(1) - '0';
                int digit2 = (int)S.charAt(2) - '0';
                //check that we have digits
                if (((0 <= digit1) && (digit1 <= 9)) &&
                    ((0 <= digit2) && (digit2 <= 9)))
                    nby = digit1 * 10 + digit2;
                else
                    return null;
            }
            else if (S.length() == 2) {
                int dig = (int)S.charAt(1) - '0';
                //check that we have a digit
                if ((0 <= dig) && (dig <= 9))
                    nby = dig;
                else
                    return null;
            }
            else
                return null;
            CDate today= CDate.getNow();
            int thisyear=today.getYear() < 2000 ? today.getYear()-1900 :
                today.getYear()-2000;
            if(nby < thisyear) nby += 100;
            int year=(today.getYear() < 2000 ? 1900 + nby :
                      2000 + nby);
            CDate d= CDate.valueOf(year,month,1);
            d = UtilDate.getIMMDate(d,CDate.WEDNESDAY);
            return d;
        }
    }

    /**
     * Returns a date (CDate) that results from applying a given tenor
     * to today.
     *
     * @param tenor a tenor (String)
     */
    static public CDate checkTermFromToday(String tenor) {
        CDate today= CDate.getNow();
        return checkYearTerm(tenor,today);
    }

    /**
     * Returns a date (CDate) that results from applying a given tenor
     * to today, using a given set of Holidays.
     *
     * @param tenor    a tenor (String)
     * @param holidays a Vector containing Holidays
     */
    static public CDate checkTermFromToday(String tenor,Vector holidays) {
        CDate today= CDate.getNow();
        return checkYearTerm(tenor,today,holidays);
    }

    /**
     * Converts a given String to a date, and returns the date (CDate).
     *
     * @param s a String representation of a date
     */
    static public CDate stringToCDate(String s) {
        return stringToCDate(s, true);
    }
    static public CDate stringToCDate(String s, boolean isLenient) {
        if (s.length()==0)  {
            return null;
        }

        /*
          int idx1=s.indexOf("-");
          if(idx1 < 0) idx1=s.indexOf("/");
          if(idx1 < 0){
          return null;
          }
          int idx2=s.indexOf("-",idx1+1);
          if(idx2 < 0) idx2=s.indexOf("/",idx1+1);
          if(idx2 < 0)  {
          return null;
          }
          String sm=null;
          String sd=null;
          String sy=null;
          CDate jd = CDate.valueOf();
          sy=s.substring(idx2+1);
          if(Locale.getDefault().equals(Locale.US))  {
          sm=s.substring(0,idx1);
          sd=s.substring(idx1+1,idx2);
          }
          else  {
          sd=s.substring(0,idx1);
          sm=s.substring(idx1+1,idx2);
          }
          if(checkValInt(sm).length()==0)  {
          return null;
          }
          int m=Integer.parseInt(sm);
          if(m<1 || m>12) {
          return null;
          }
          //jd.setMonth(m);
          if(checkValInt(sd).length()==0) {
          return null;
          }
          int d=Integer.parseInt(sd);
          if(d<1 || d>31) {
          return null;
          }
          //jd.setDayOfMonth(d);
          if(checkValInt(sy).length()==0) {
          return null;
          }
          int y=Integer.parseInt(sy);
          if(y < 50) y += 2000;
          if((y < 100) && (y >= 50)) y+= 1900;
          jd = CDate.valueOf(y, m, d);
          if((y != jd.getYear()) || (m != jd.getMonth()) ||
          (d != jd.getDayOfMonth())   ) {
          return null;
          }
          return jd;
        */
        CDate d = commonUTIL.stringToCDate(s, isLenient);
        if (d == null){
            DateFormat dateformat=commonUTIL.getDateFormatWithSeparatorPattern
                (Locale.getDefault(),
                 TimeZone.getDefault());
            String pattern = ((SimpleDateFormat)dateformat).toPattern();
            if (pattern.indexOf("y")==0){
                s = CDate.getNow().getYear()+"/"+s;
            }else if (pattern.indexOf("y")
                      > pattern.lastIndexOf("/")){
                s = s+"/"+CDate.getNow().getYear();
            }
            d = commonUTIL.stringToCDate(s, isLenient);
        }
        return d;
    }

    /**
     * Returns a given String if it is a valid integer, or an empty String
     * otherwise.
     *
     * @param s a String representation of an integer
     */
    static public String checkValInt(String s){
        int flag=0;
        String ret = new String("");
        if (s.length()==0) return ret;
        try   {
            flag = Integer.parseInt(s);
            return ret.valueOf(flag);
        }
        catch(NumberFormatException e) {  return "";}
    }

    /**
     * Returns a given String if it is a valid double, or an empty String
     * otherwise.
     *
     * @param s a representation of a double
     */
    static public String checkValDouble(String s){
        double flag=0.0;
        String ret = new String("");
        if (s.length()==0) return ret;
        try {
            flag = Double.parseDouble(s);
            return ret.valueOf(flag);
        }
        catch(NumberFormatException e) { return "";}
    }

    /**
     * Converts a period between two given dates to a Tenor, and returns
     * the Tenor.
     *
     * @param startDate a starting date (CDate)
     * @param tdate     an end date (CDate)
     */
    static public Period convertDate2Tenor(CDate startDate,
                                          CDate tdate) {

        CDate d1, d2;
        if(startDate.before(tdate))         {
            d1 = startDate;
            d2 = tdate;
        }
        else {
            d1 = tdate;
            d2 = startDate;
        }
        int days = CountDay.D_30_360.dayDiff(d1,d2);
        //int days = (int)CDate.diff(d1,d2);
        String s =   days + "D";
        return new Period(s);
    }

    /**
     * BZ 43225.
     * This method tries to deconstruct the date shortcuts specified in the requirements for BZ 43225
     * into valid dates.
     * 
     * The specific cases are: MMMyy(y) [ex. Jan07 ... Dec07], Qnyy [ex. Q107 ... Q407], Calyy [ex. Cal08]
     * String with Cal [Calendar] and year will return the first date of that calendar year.
     * String with Q [quarter] will return the first date of the quarter period for the year.
     * String with MMM [month] abbreviation will return the first date of the month and year.
     * @param tenor String
     * @return CDate
     */
    private static CDate convertCommDateStringTenorToDate(String tenor) {
        if (commonUTIL.isEmpty(tenor)) {
            return null;
        }
        String S = tenor.toUpperCase();

        if(S.length() >= 5) {
            if(S.startsWith(CAL)) {
                String monthStr = S.substring(0, 3);
                String yearStr = S.substring(3);
                String dd= checkValInt(yearStr);
                if(dd.length() > 0){
                    int year = Integer.parseInt(dd);
                    int month = 1;
                    
                    int baseYear = BASE_YEAR;
                    int day = 1;
                    
                    year += baseYear;
                    
                    CDate date = CDate.valueOf(year, month, day);
                    return date;
                }
                
            }
            
            String monthStr = S.substring(0, 3);
            String yearStr = S.substring(3);
            String dd= checkValInt(yearStr);
            if(dd.length() > 0){
                int year = Integer.parseInt(dd);
                int month = 0;
                
                month = getMonth(monthStr);
                
                if(month != 0) {//valid string for month
                    int baseYear = BASE_YEAR;
                    int day = 1;
                    
                    year += baseYear;
                    
                    CDate date = CDate.valueOf(year, month, day);
                    return date;
                }
            }
        }
        
        if(S.length() >= 4) {
            String qtrStr = S.substring(0, 2);
            String yearStr = S.substring(2);
            String dd= checkValInt(yearStr);
            if(dd.length() > 0){
                int year = Integer.parseInt(dd);
                int month = 0;
                
                month = getQtrMonth(qtrStr);
                
                if(month != 0) {//valid string for qtr
                    int baseYear = BASE_YEAR;
                    int day = 1;
                    
                    year += baseYear;
                    
                    CDate date = CDate.valueOf(year, month, day);
                    return date;
                }
            }
        }
        
        return null;//doesn't satisfy any commodity related shortcuts.
    }
    
    private static int getMonth(String monthStr) {
        int month = 0;
        
        if(JAN.equals(monthStr))
            month = CDate.JANUARY;
        else if(FEB.equals(monthStr))
            month = CDate.FEBRUARY;
        else if(MAR.equals(monthStr))
            month = CDate.MARCH;
        else if(APR.equals(monthStr))
            month = CDate.APRIL;
        else if(MAY.equals(monthStr))
            month = CDate.MAY;
        else if(JUN.equals(monthStr))
            month = CDate.JUNE;
        else if(JUL.equals(monthStr))
            month = CDate.JULY;
        else if(AUG.equals(monthStr))
            month = CDate.AUGUST;
        else if(SEP.equals(monthStr))
            month = CDate.SEPTEMBER;
        else if(OCT.equals(monthStr))
            month = CDate.OCTOBER;
        else if(NOV.equals(monthStr))
            month = CDate.NOVEMBER;
        else if(DEC.equals(monthStr))
            month = CDate.DECEMBER;
        
        return month;
    }

    private static int getQtrMonth(String qtrStr) {
        int month = 0;
        
        if(Q1.equals(qtrStr))
            month = CDate.JANUARY;
        else if(Q2.equals(qtrStr))
            month = CDate.APRIL;
        else if(Q3.equals(qtrStr))
            month = CDate.JULY;
        else if(Q4.equals(qtrStr))
            month = CDate.OCTOBER;
        
        return month;
    }
    
    private static String JAN = "JAN";
    private static String FEB = "FEB";
    private static String MAR = "MAR";
    private static String APR = "APR";
    private static String MAY = "MAY";
    private static String JUN = "JUN";
    private static String JUL = "JUL";
    private static String AUG = "AUG";
    private static String SEP = "SEP";
    private static String OCT = "OCT";
    private static String NOV = "NOV";
    private static String DEC = "DEC";

    private static String Q1 = "Q1";
    private static String Q2 = "Q2";
    private static String Q3 = "Q3";
    private static String Q4 = "Q4";
    
    private static String CAL = "CAL";
    
    private static int BASE_YEAR = 2000; //TODO - get this from some env property
    
    /**
     * Test method for internal use only.
     */
    static public void main(String args[]) {
        CDate startDate = CDate.valueOf(2000,7,13);
        CDate endDate = CDate.valueOf(2000,10,13);
       // Log.info("FormatUtil",startDate + " to " + endDate + " Tenor: " + convertDate2Tenor(startDate,endDate));
    }
}


