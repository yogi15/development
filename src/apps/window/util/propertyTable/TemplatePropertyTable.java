package apps.window.util.propertyTable;
import java.beans.PropertyChangeEvent;
import java.util.List;
import beans.Template;
import com.jidesoft.grid.Property;
public class TemplatePropertyTable  extends WindowPropertyTable   {
List< Property> templateProperties = null;
public Template template ;
@Override
public void propertyChange(PropertyChangeEvent evt) {
}
public TemplatePropertyTable(String name,Template template ) {
this.name = name;
setTemplate(template);
}
@Override
public List< Property> addListenerToProperty(List< Property> properties) {
return properties;
}
// add listener to the property
public void addListenerToProperty(final Property property ,final List< Property> properties  ) {
}
/**
* @return the template
*/
public Template getTemplate() {
return template;
}
	/**
* @param template the template to set
*/
public void setTemplate(Template bean) {
this.template = bean;
}
}