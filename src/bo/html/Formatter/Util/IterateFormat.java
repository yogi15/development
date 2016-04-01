package bo.html.Formatter.Util;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import dsServices.RemoteTrade;

import beans.LeContacts;
import beans.Message;
import beans.Trade;
import beans.Transfer;

public abstract class   IterateFormat  implements Iterator {
	
	    // Use for nested Iterator to propagate the previous iterator on the new one
	    public static final String PARENT_FORMATTER_CONTEXT = "PARENT_FORMATTER_CONTEXT";

	    int _count=0;
	    private Vector _iteratorSet = null;
	    private Hashtable persistentVars = new Hashtable();
	    private Collection _parameters = null;
	    /**
	     *
	     * @param message  the <code>BOMessage</code> to be used to generate SWIFT message.
	     * @param trade    the <code>Trade</code> associated with <code>BOMessage</code>.
	     * @param sender   Who is the sender of this SWIFT message?
	     * @param receiver Who is the recipient of this SWIFT message?
	     * @param transfer the <code>BOTransfer</code> associated with this message.
	     * @param transferRules  a <code>Vector</code> of transfer rules.
	     * @param dsCon    DataServer connection to retrieve any additional info.
	     */
	    public abstract void init(Message message,
	                     Trade trade,
	                     LeContacts sender,
	                     LeContacts receiver,
	                     Transfer transfer,
	                     Vector transferRules,
	                     RemoteTrade remoteTrade);

	    public int size() {
	        if (_iteratorSet == null) return 0;
	        return _iteratorSet.size();
	    }

	    public boolean hasNext() {
	        if (_iteratorSet == null)
	            return (_count <= 0);
	        else
	            return (_count < _iteratorSet.size());
	    }

	    public Object next() {
	        Object obj=null;
	        if (_iteratorSet != null) {
	            obj = _iteratorSet.elementAt(_count);
	        }
	        _count++;
	        return obj;
	    }

	    public Vector getIteratorSet() {
	        return _iteratorSet;
	    }

	    public void setIteratorSet(Vector iteratorSet) {
	        _iteratorSet = iteratorSet;
	    }

	    public Object getPersistentVar(Object key) {
	        return persistentVars.get(key);
	    }

	    public void setPersistentVar(Object key, Object value) {
	        persistentVars.put(key, value);
	    }

	    public int getCurrentCount() {
			return _count;
	    }

	    public Collection getParameters() {
			return _parameters;
		}

		public void setParameters(Collection params) {
			_parameters = params;
	    }

	}



