package apps.window.util.propertyTable;
import java.beans.PropertyChangeEvent;
import java.util.List;
import beans.Product;
import com.jidesoft.grid.Property;
public class ProductPropertyTable  extends WindowPropertyTable   {
List<Property> productProperties = null;
public Product product ;
@Override
public void propertyChange(PropertyChangeEvent evt) {
}
public ProductPropertyTable(String name,Product product ) {
this.name = name;
setProduct(product);
}
@Override
public List< Property> addListenerToProperty(List<Property> properties) {
return properties;
}
// add listener to the property
public void addListenerToProperty(final Property property ,final List< Property> properties  ) {
}
/**
* @return the product
*/
public Product getProduct() {
return product;
}
	/**
* @param product the product to set
*/
public void setProduct(Product bean) {
this.product = bean;
}
}
