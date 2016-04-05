package src.util.common.holiday;

import java.util.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections.map.LRUMap;
 
public class SynchronizedCachedBusinessDayCal extends  CachedBusinessDayCal {

	  private LRUMap _businessHolidayCache;

	    public SynchronizedCachedBusinessDayCal() {
	        _businessHolidayCache = new LRUMap(CachedBusinessDayCal.HOLIDAY_CACHE_SIZE);
	    }
	    
	    public void clear() {
	        synchronized (_businessHolidayCache) {
	            _businessHolidayCache.clear();
	        }
	    }
	    
	    protected BusinessHolidayCache getBusinessHolidayCache(final Holiday holiday,
	        final Collection<String> codeList) {
	        Set<String> unorderedCodes = new TreeSet<String>(codeList); // this is
	        // faster
	        // than
	        // HashSet
	        // for small
	        // sets
	        BusinessHolidayCache chc = null;
	        synchronized (_businessHolidayCache) {
	            chc = (BusinessHolidayCache) _businessHolidayCache.get(unorderedCodes);
	            if (chc == null) {
	              /*  if (Log.isDebug()) {
	                    if (_businessHolidayCache.isFull()) {
	                        Log.debug(this, "business holiday cache full, max entries="+_businessHolidayCache.size());
	                    }
	                }
	                if (Log.isDebug()) {
	                    Log.debug(this, "no business holiday cache for "
	                        + Arrays.toString(new ArrayList(unorderedCodes).toArray()));
	                } */
	                chc = new BusinessHolidayCacheJulianLong();// BusinessHolidayBoundedCacheJulianBitSet();
	                BusinessDayComputeFactory.make(holiday, unorderedCodes, chc);
	                _businessHolidayCache.put(unorderedCodes, chc);
	               /* if (Log.isDebug()) {
	                    Log.debug(this, "created business holiday cache for "
	                        + Arrays.toString(new ArrayList(unorderedCodes).toArray()));
	                } */
	            }
	        }
	        return chc;
	    }
}