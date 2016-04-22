package apps.window.staticwindow.util; 


 import java.util.Vector;
import apps.window.util.tableModelUtil.TableUtils;import util.cacheUtil.ReferenceDataCache;
import util.commonUTIL;
import apps.window.staticwindow.BasePanel;
import apps.window.referencewindow.BookWindow;
import beans.Book; 
import beans.WindowSheet;
import com.jidesoft.grid.Property;
import constants.CommonConstants;
import constants.BookConstants;
import constants.BeanConstants;
public class BookWindowUtil extends BaseWindowUtil {
 BookWindow bookWindow= null;
Book book = null;
 String bookName;
/**
 * @return the windowName
 */
public String getWindowName() {
	return BookConstants.WINDOW_NAME;
}
	/**
 * @param windowName the windowName to set
 */
public void setWindowName(String bookName) {
	this.bookName = bookName;
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
public void setBook(Book  book) {
	  this.book = book;
}@Override
public boolean validate( ) {
		// TODO Auto-generated method stub
		boolean flag = false;
			return validate(getBook(),BookConstants.WINDOW_NAME);
			}
	@Override
					public Vector<String> fillData(String action) {
				// TODO Auto-generated method stub
			return null;
}
	@Override
public void actionMapper(String action) {
	// TODO Auto-generated method stub
Property prop = bookWindow.propertyTable.getPropertyTable().getSelectedProperty(); 
if(action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
saveAsNewButtonAction();
}
if(action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
newButtonAction();
	}
		if(action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
loadButtonAction();
	}
	if(action.equalsIgnoreCase(BookConstants.SEARCHTEXTBOX)) {
searchTextAction();
	}
if(action.equalsIgnoreCase(CommonConstants.RIGHTSIDECENTERTABLE)) {
	rightSideCenterTableAction();
}
	if(action.equalsIgnoreCase(CommonConstants.DELETEBUTTON)) {
	deleteButtonAction();
}
if(action.equalsIgnoreCase(CommonConstants.SAVEBUTTON)) {
saveButtonAction();
}
if (action.equalsIgnoreCase(CommonConstants.HIERARACHICALTABLE)) {
hierarachicalTableAction();
 }
 if (action.equalsIgnoreCase(BookConstants.LOADALLBOOK)) {
 	loadButtonAction();
 }
	}				
@Override
public void setWindow(BasePanel windowName) {
	// TODO Auto-generated method stub
	bookWindow = (BookWindow)windowName;
setBook(bookWindow.getBook()); 
}
private void hierarachicalTableAction() {
	if (bookWindow.hierarchicalTable.getSelectedRow() != -1) {
			bookWindow.propertyTable.setPropertiesValues(bookWindow.model.getRow(bookWindow.hierarchicalTable.getSelectedRow()));
		bookWindow.setBook(bookWindow.model.getRow(bookWindow.hierarchicalTable.getSelectedRow()));
		setBook(bookWindow.model.getRow(bookWindow.hierarchicalTable.getSelectedRow()));
		}
	}
private void saveButtonAction() {
bookWindow.propertyTable
.setfillValues(book);
setBook((Book) bookWindow.propertyTable.getBean());
//if(validate( )) 
// if(ReferenceDataCache.updateBook(getBook())) {
//if(bookWindow.rightSideCenterTable.getSelectedRow() != -1) {
	 // int i=  TableUtils.getSelectedRowIndex( bookWindow.rightSideCenterTable);
	  //bookWindow.model.udpateValueAt(getBook(), i, 0);
 //}
 //}
}		
private void saveAsNewButtonAction() { 
Book book = new Book();
	bookWindow.propertyTable.setfillValues(book);
	 setBook(book);
//if(validate( )){
// book = ReferenceDataCache.saveBook(book); 
 // bookWindow.model.addRow(getBook());
 // setBook(book);
  //}
}
private void newButtonAction() {
bookWindow.propertyTable.clearPropertyValues();
bookWindow.model.clear();
setBook(null);
	}
private void loadButtonAction() {
	newButtonAction();
String searchText =   bookWindow.bookSearchTextField.getText();
 if(!commonUTIL.isEmpty(searchText)) {
Vector<Book> data = null;//ReferenceDataCache.selectBooks(searchText);
bookWindow.model.clear();
if(!commonUTIL.isEmpty(data)) {
Book firstRecord = data.get(0);
for(int i=0;i<data.size();i++) {
 bookWindow.model.addRow((Book)data.get(i));
				}
				bookWindow.propertyTable.setPropertiesValues(firstRecord);
				 setBook(firstRecord);
		} 
}else {
		Vector<Book> data = (Vector<Book>) ReferenceDataCache.selectALLData(BeanConstants.BOOK);
		if (!commonUTIL.isEmpty(data)) {
bookWindow.model.clear();
			Book firstRecord = data.get(0);
			for (int i = 0; i < data.size(); i++) {
					bookWindow.model.addRow((Book) data.get(i));
				}
				bookWindow.propertyTable.setPropertiesValues(firstRecord);
		setBook(firstRecord);
			}
	 }
}
public void loadData(int id) {
		newButtonAction();
		Vector<Book> data = null;// ReferenceDataCache.getBook(id);
			bookWindow.model.clear();
			if (!commonUTIL.isEmpty(data)) {
				Book firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
			bookWindow.model.addRow((Book) data.get(i));
				}
				bookWindow.propertyTable.setPropertiesValues(firstRecord);
				setBook(firstRecord);
	}
	}
 private void rightSideCenterTableAction() {
	if(bookWindow.rightSideCenterTable.getSelectedRow() != -1) 
		 bookWindow.propertyTable.setPropertiesValues( bookWindow.model.getRow(bookWindow.rightSideCenterTable.getSelectedRow()));
	bookWindow.setBook(bookWindow.model.getRow(bookWindow.rightSideCenterTable .getSelectedRow()));
	setBook(bookWindow.model .getRow(bookWindow.rightSideCenterTable .getSelectedRow()));
}
private void searchTextAction() {
	loadButtonAction();
}
// check Null pointerException.
private void deleteButtonAction() {
	try {
//if(ReferenceDataCache.deleteBook(book)) {
//if( bookWindow.rightSideCenterTable.getSelectedRow() != -1) {
	//bookWindow.model.delRow(bookWindow.rightSideCenterTable.getSelectedRow()); 
	//}
//setBook(null);
 //bookWindow.propertyTable.clearPropertyValues();
//}	
} catch(Exception e) {
commonUTIL.displayError(BookConstants.WINDOW_NAME+"Util" , "deleteButtonAction", e);
}
}
@Override
public void windowstartUpData() {
 

}@Override
public void clearALL() {
}
}