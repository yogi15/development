package apps.window.util.propertyTable;
import java.beans.PropertyChangeEvent;
import java.util.List;
import beans.LeContacts;
import com.jidesoft.grid.Property;
public class ContactPropertyTable  extends WindowPropertyTable   {
List<Property> contactProperties = null;
public LeContacts contact ;
@Override
public void propertyChange(PropertyChangeEvent evt) {
}
public ContactPropertyTable(String name,LeContacts contact ) {
this.name = name;
setLeContacts(contact);
}
@Override
public List< Property> addListenerToProperty(List<Property> properties) {
return properties;
}
// add listener to the property
public void addListenerToProperty(final Property property ,final List< Property> properties  ) {
}
/**
* @return the contact
*/
public LeContacts getLeContacts() {
return contact;
}
	/**
* @param contact the contact to set
*/
public void setLeContacts(LeContacts bean) {
this.contact = bean;
}
}
