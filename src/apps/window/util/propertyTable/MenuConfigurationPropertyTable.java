package apps.window.util.propertyTable;
import java.beans.PropertyChangeEvent;
import java.util.List;
import beans.MenuConfiguration;
import com.jidesoft.grid.Property;
public class MenuConfigurationPropertyTable  extends WindowPropertyTable   {
List< Property> menuconfigurationProperties = null;
public MenuConfiguration menuconfiguration ;
@Override
public void propertyChange(PropertyChangeEvent evt) {
}
public MenuConfigurationPropertyTable(String name,MenuConfiguration menuconfiguration ) {
this.name = name;
setMenuConfiguration(menuconfiguration);
}
@Override
public List< Property> addListenerToProperty(List< Property> properties) {
return properties;
}
// add listener to the property
public void addListenerToProperty(final Property property ,final List< Property> properties  ) {
}
/**
* @return the menuconfiguration
*/
public MenuConfiguration getMenuConfiguration() {
return menuconfiguration;
}
	/**
* @param menuconfiguration the menuconfiguration to set
*/
public void setMenuConfiguration(MenuConfiguration bean) {
this.menuconfiguration = bean;
}
}
