package apps.window.util.propertyTable;
import java.beans.PropertyChangeEvent;
import java.util.List;
import beans.CurrencyDefault;
import com.jidesoft.grid.Property;
public class CurrencyDefaultPropertyTable  extends WindowPropertyTable   {
List<Property> currencydefaultProperties = null;
public CurrencyDefault currencydefault ;
@Override
public void propertyChange(PropertyChangeEvent evt) {
}
public CurrencyDefaultPropertyTable(String name,CurrencyDefault currencydefault ) {
this.name = name;
setCurrencyDefault(currencydefault);
}
@Override
public List< Property> addListenerToProperty(List<Property> properties) {
return properties;
}
// add listener to the property
public void addListenerToProperty(final Property property ,final List< Property> properties  ) {
}
/**
* @return the currencydefault
*/
public CurrencyDefault getCurrencyDefault() {
return currencydefault;
}
	/**
* @param currencydefault the currencydefault to set
*/
public void setCurrencyDefault(CurrencyDefault bean) {
this.currencydefault = bean;
}
}
