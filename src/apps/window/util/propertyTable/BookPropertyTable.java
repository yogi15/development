package apps.window.util.propertyTable;
import java.beans.PropertyChangeEvent;
import java.util.List;
import beans.Book;
import com.jidesoft.grid.Property;
public class BookPropertyTable  extends WindowPropertyTable   {
List< Property> bookProperties = null;
public Book book ;
@Override
public void propertyChange(PropertyChangeEvent evt) {
}
public BookPropertyTable(String name,Book book ) {
this.name = name;
setBook(book);
}
@Override
public List< Property> addListenerToProperty(List< Property> properties) {
return properties;
}
// add listener to the property
public void addListenerToProperty(final Property property ,final List< Property> properties  ) {
}
/**
* @return the book
*/
public Book getBook() {
return book;
}
	/**
* @param book the book to set
*/
public void setBook(Book bean) {
this.book = bean;
}
}
