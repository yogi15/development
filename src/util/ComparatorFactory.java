package util;

import java.util.Comparator;
 

public class ComparatorFactory {
	private static Comparator _nonSenseStrComp = null;

    private static Comparator _strComp = null;
	
    public static Comparator getStringComparator() {
        if (_strComp == null) {
            _strComp = new StringComparatorUtil ();
        }
        return _strComp;
    }
	public static Comparator getNonSenseStringComparator() {
        if (_nonSenseStrComp == null) {
            _nonSenseStrComp = new ComparatorStringNonSens();
        }
        return _nonSenseStrComp;
    }
    
}
