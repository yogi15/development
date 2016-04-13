package util.common;

import java.util.Locale;
import java.util.TimeZone;
 
public final class LocalTimeZone {

    Locale _locale;
    TimeZone _timeZone;
    public LocalTimeZone(Locale loc,TimeZone tz) {
	_locale=loc;
	_timeZone=tz;
    }
    public int hashCode() { return _locale.hashCode() ^ _timeZone.hashCode();}
    public boolean equals(Object _o) {
    	LocalTimeZone o=(LocalTimeZone)_o;
	return _locale.equals(o._locale) &&
	    _timeZone.equals(o._timeZone);
    }
}
