package apps.window.util.propertyTable;
import java.beans.PropertyChangeEvent;
import java.util.List;
import beans.LegalEntity;
import com.jidesoft.grid.Property;
public class CounterPartyPropertyTable  extends WindowPropertyTable   {
List<Property> counterpartyProperties = null;
public LegalEntity counterparty ;
@Override
public void propertyChange(PropertyChangeEvent evt) {
}
public CounterPartyPropertyTable(String name,LegalEntity counterparty ) {
this.name = name;
setLegalEntity(counterparty);
}
@Override
public List< Property> addListenerToProperty(List<Property> properties) {
return properties;
}
// add listener to the property
public void addListenerToProperty(final Property property ,final List< Property> properties  ) {
}
/**
* @return the counterparty
*/
public LegalEntity getLegalEntity() {
return counterparty;
}
	/**
* @param counterparty the counterparty to set
*/
public void setLegalEntity(LegalEntity bean) {
this.counterparty = bean;
}
}
