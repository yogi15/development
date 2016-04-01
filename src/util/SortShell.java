package util;


import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 * Sorts objects based on user-specified criteria.
 */
public class SortShell
{
    /**
	 * Returns a new <code>Vector</code> with its elements sorted.
	 *
	 * @param collection
	 *            the collection to be sorted
	 * @return a new sorted <code>Vector</code>
	 */
    static public <T extends Comparable<? super T>> Vector<T> sort(Collection<T> collection)
    {
    	Vector<T> v = new Vector<T>(collection);
    	Collections.sort(v);
    	return v;
    }

    /**
	 * Returns a new <code>Vector</code> with its elements sorted.
	 *
	 * @param collection
	 *            the collection to be sorted
	 * @param comp
	 *            The compartor to use for comparing instances.
	 * @return a new sorted <code>Vector</code>
	 */
    static public <T> Vector<T> sort(Collection<T> collection, Comparator<? super T> comp)
    {
    	Vector<T> v = new Vector<T>(collection);
    	Collections.sort(v, comp);
    	return v;
    }
}