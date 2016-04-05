package apps.window.util.propertyUtil.Date;

import util.common.CDate;
 
public interface PeriodAdapter {
	/**
	 * this ingerface specifies the generic behavior of an arbitrary tenor adapter
	 */ 
	    /**
	     * calculates a date by applying a tenor to a reference date. If the provided string cannot be parsed to a valid tenor, the reference date should be returned
	     *  
	     * @param date the reference date, to which the tenor will be applied
	     * @param tenor specifications of the tenor that will be applied to the reference date
	     * @return the date that results by applying the specified tenor to the provided reference date
	     */
	    public CDate applyTenor(CDate date, String tenor);

	    /**
	     * calculates the tenor between the two dates. Ex: for default calypso tenor, for a base date being "09/07/2009"
	     * and target date being "10/20/2011" this method should return "2Y 1M 13D"
	     *
	     * @param base the base date
	     * @param target the target date
	     * @return the tenor that separates the two dates
	     */
	    public String reverseTenor(CDate base, CDate target);
	}
