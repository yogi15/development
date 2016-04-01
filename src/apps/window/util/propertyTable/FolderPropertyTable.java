package apps.window.util.propertyTable;
import java.beans.PropertyChangeEvent;
import java.util.List;
import beans.Folder;
import com.jidesoft.grid.Property;
public class FolderPropertyTable  extends WindowPropertyTable   {
List< Property> folderProperties = null;
public Folder folder ;
@Override
public void propertyChange(PropertyChangeEvent evt) {
}
public FolderPropertyTable(String name,Folder folder ) {
this.name = name;
setFolder(folder);
}
@Override
public List< Property> addListenerToProperty(List< Property> properties) {
return properties;
}
// add listener to the property
public void addListenerToProperty(final Property property ,final List< Property> properties  ) {
}
/**
* @return the folder
*/
public Folder getFolder() {
return folder;
}
	/**
* @param folder the folder to set
*/
public void setFolder(Folder bean) {
this.folder = bean;
}
}
