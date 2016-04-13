package apps.window.util.propertyUtil.Date;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import apps.window.util.propertyUtil.Date.Provider.ProviderReferenceDate;
import apps.window.util.propertyUtil.Date.Provider.ProviderTimeZone;

import util.common.CDate;
 
public class PeriodDateFormat extends SimpleDateFormat {
	 private final ProviderReferenceDate referenceDateProvider;
	    private final PeriodAdapter tenorCalculator;
	    private ProviderTimeZone timeZoneProvider;

	    /**
	     * constructs a tenor adapter
	     * @param referenceDateProvider provides the reference date to which tenor must apply
	     * @param tenorAdapter the tenor adapter can be used to provide custom tenor adapters
	     */
	    public PeriodDateFormat(ProviderReferenceDate referenceDateProvider, PeriodAdapter tenorAdapter, ProviderTimeZone timeZoneProvider) {
	        this.referenceDateProvider = referenceDateProvider;
	        this.tenorCalculator = tenorAdapter;
	        this.timeZoneProvider = timeZoneProvider;
	    }

	    public Date parse(String source) throws ParseException {
	        CDate date = UtilFormat.stringToCDate(source);
	        if (date != null) {
	            final Date date1 = date.getDate(timeZoneProvider.getTimeZone());
	            return date1;
	        }
	        
	        Date referenceDate = referenceDateProvider == null ? new Date() : referenceDateProvider.getReferenceDate();
	        CDate calculatedDate = tenorCalculator.applyTenor(CDate.valueOf(referenceDate, timeZoneProvider.getTimeZone()), source);
	        if (calculatedDate == null)
	            return null;
	        return calculatedDate.getDate(TimeZone.getDefault());
	    }

	    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
	        final String stringDate = CDate.valueOf(date).toString();
	        toAppendTo.append(stringDate);
	        return toAppendTo;
	    }
	}



