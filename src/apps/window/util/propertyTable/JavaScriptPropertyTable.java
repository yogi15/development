package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.util.List;
 
import beans.JavaScript;
import beans.Template;

import com.jidesoft.grid.Property;

public class JavaScriptPropertyTable  extends WindowPropertyTable {
	
	List< Property> templateProperties = null;
	public JavaScript javaScript ;
	 
	
	public JavaScriptPropertyTable(String name,JavaScript javaScript ) {

		this.name = name;
		setJavaScript(javaScript);
		 
	}
		  
		/**
	 * @return the javaScript
	 */
	public JavaScript getJavaScript() {
		return javaScript;
	}


	/**
	 * @param javaScript the javaScript to set
	 */
	public void setJavaScript(JavaScript javaScript) {
		this.javaScript = javaScript;
	}


		 
	 

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Property> addListenerToProperty(List<Property> properties) {
		// TODO Auto-generated method stub
		return properties;
	}

}
