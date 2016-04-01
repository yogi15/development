package util;

import java.util.Comparator;

public class ComparatorStringNonSens implements Comparator {
	
	
	public int compare(Object obj1,Object obj2) {
		String s1=String.valueOf(obj1);
		String s2=String.valueOf(obj2);	
		s1=s1.toLowerCase();
		s2=s2.toLowerCase();
		return s1.compareTo(s2);
	    }

	    /**
	     * Compares specified <code>Object</code> with this
	     * <code>ComparatorStringNonSens</code>.
	     * @param o the <code>Object</code> to be compared to.
	     * @return true if <code>Object</code>s are equals, false
	     * otherwise.
	     */
	    public boolean equals(Object o) {
		if (o.getClass() != getClass()) return false;
		return super.equals(o);
	    }

}
