package src.util.common.holiday;
 
public class BusinessDayComputeFactory {
private static final BusinessDaysCal  instance;
    
    final static public String BUSINESS_DAY_CACHE = "BUSINESS_DAY_CACHE";
    
    static {
        if (!useBusinessDayCache()) {
            instance = BusinessDayCalculatorImp.getInstance();
        } else {
            instance = new SynchronizedCachedBusinessDayCal();
        }
    }
    
    public static BusinessDaysCal getInstance() {
        return instance;
    }
    
    public static boolean useBusinessDayCache() {
        return false;  // getBooleanProperty(BUSINESS_DAY_CACHE, true); 
    }
}
