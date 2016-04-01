package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import beans.BaseBean;

import com.jidesoft.grid.PropertyTable;

public class CorePropertyTabe    implements PropertyChangeListener  {

public PropertyTable propertyTable = null;


	 /**
	 * @return the propertyTable
	 */
	public PropertyTable getPropertyTable() {
		return propertyTable;
	}

	/**
	 * @param propertyTable the propertyTable to set
	 */
	public void setPropertyTable(PropertyTable propertyTable) {
		this.propertyTable = propertyTable;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	 public  void setfillValues(BaseBean baseBean) {
		 
	 }

}
