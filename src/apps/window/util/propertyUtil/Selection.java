package apps.window.util.propertyUtil;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import util.commonUTIL; 

public class Selection<T> implements Serializable	{
	
protected int __auditVersion;
	
    /**
     * wether it should be included or excluded 
     */
    boolean includeExclude;

    /**
     * the list of items
     */
    List<T> items;

    public Selection() {
    }

    public Selection(Selection initialValue) {
        this.includeExclude = initialValue.includeExclude;
        this.items = new ArrayList<T>(initialValue.getItems());
    }

    public boolean isIncludeExclude() {
        return includeExclude;
    }

    public void setIncludeExclude(boolean includeExclude) {
        this.includeExclude = includeExclude;
    }

    public List<T> getItems() {
        if (items == null)
            items = new ArrayList<T>();
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
    
    /**
	 * Writes this Selection to a given ObjectOutput.
	 *
	 * @param out an ObjectOutput
	 */
	public void writeExternal(ObjectOutput out)
	throws IOException	{
		out.writeInt(1);
		out.writeBoolean(includeExclude);
		out.writeObject(items);
	}
	
	/**
	 * Creates this Selection from a given ObjectInput.
	 *
	 * @param in an ObjectInput
	 */
	public void readExternal(ObjectInput in)
	throws IOException ,ClassNotFoundException {
		__auditVersion = in.readInt();
		includeExclude = in.readBoolean();
		items = (List)in.readObject();
	}

    @Override
    public String toString() {
        return (includeExclude ? "Include" : "Exclude") + " " + commonUTIL.collectionToString(items); 
    }
}
