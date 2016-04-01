package util;

public class StringComparatorUtil implements  java.util.Comparator {

	public  int compare(Object obj1, Object obj2) {
		if(obj1 == null)  return -1;
		if(obj2 == null) return 1;
		String s1 = obj1.toString();
		String s2 = obj2.toString();
		if(s1 == null) return -1;
		if(s2 ==null) return 1;
		return( s1.compareTo(s2));
	}


	public boolean equals (Object obj) {
		return false;
	}
}