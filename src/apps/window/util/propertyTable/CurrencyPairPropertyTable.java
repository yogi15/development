package apps.window.util.propertyTable;
import java.beans.PropertyChangeEvent;
import java.util.List;
import beans.CurrencyPair;
import com.jidesoft.grid.Property;
public class CurrencyPairPropertyTable  extends WindowPropertyTable   {
List<Property> currencypairProperties = null;
public CurrencyPair currencypair ;
@Override
public void propertyChange(PropertyChangeEvent evt) {
}
public CurrencyPairPropertyTable(String name,CurrencyPair currencypair ) {
this.name = name;
setCurrencyPair(currencypair);
}
@Override
public List< Property> addListenerToProperty(List<Property> properties) {
return properties;
}
// add listener to the property
public void addListenerToProperty(final Property property ,final List< Property> properties  ) {
}
/**
* @return the currencypair
*/
public CurrencyPair getCurrencyPair() {
return currencypair;
}
	/**
* @param currencypair the currencypair to set
*/
public void setCurrencyPair(CurrencyPair bean) {
this.currencypair = bean;
}
}
