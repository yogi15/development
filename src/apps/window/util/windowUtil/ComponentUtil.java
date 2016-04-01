package apps.window.util.windowUtil;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import apps.window.util.tableModelUtil.CurrencyDefaultTableModelUtil;
import beans.Book;
import beans.CurrencyDefault;
import beans.Folder;
import beans.LegalEntity;

import com.jidesoft.combobox.TableExComboBox;
import com.jidesoft.combobox.TableExComboBoxSearchable;
import com.jidesoft.grid.SortableTable;

public class ComponentUtil {
	
	private static TableBookModelUtil getBookModelUtil(Vector<Book> bookData) {
		String cols[] = { "Bookid", "Name "  };
		return new TableBookModelUtil(bookData,cols);
	}
	private static TableLegalEntityModelUtil getTraderModelUtil(Vector<LegalEntity> traderData) {
		String traderCol[] = { "ID", "TraderName "  };
		return new TableLegalEntityModelUtil(traderData,traderCol);
	}
	private static TableLegalEntityModelUtil getCPModelUtil(Vector<LegalEntity> traderData) {
		String col[] = { "Id", "Name " };
		return new TableLegalEntityModelUtil(traderData,col);
	}
	private static TableFolderModelUtil getFolderModelUtil(Vector<Folder> folderData) {
		String col[] = { "Id", "Name " };
		return new TableFolderModelUtil(folderData,col);
	}
	
	
	private static TableBookModelUtil getBookModelUtil(Vector<Book> bookData, String col[]) {
		return new TableBookModelUtil(bookData,col);
	}
	private static TableLegalEntityModelUtil getTraderModelUtil(Vector<LegalEntity> traderData, String col[]) {
		return new TableLegalEntityModelUtil(traderData,col);
	}
	private static TableLegalEntityModelUtil getCPModelUtil(Vector<LegalEntity> traderData, String col[]) {
		return new TableLegalEntityModelUtil(traderData,col);
	}
	private static TableFolderModelUtil getFolderModelUtil(Vector<Folder> folderData, String col[]) {
		return new TableFolderModelUtil(folderData,col);
	}
	private static CurrencyDefaultTableModelUtil getCurrencyModelUtil(Vector<CurrencyDefault> currencyDefault, String col[]) {
		return new CurrencyDefaultTableModelUtil(currencyDefault,col );
	}
	public static TableExComboBox getCounterPartyComboBox(Vector<LegalEntity> leData) {
		TableExComboBox counterParty =  new TableExComboBox(getCPModelUtil(leData)) {
				@Override
				public JTable createTable(TableModel model) {
					return new SortableTable(model);
				}				
			};
			counterParty.setEditable(false);
			counterParty.setBorder(null);
			counterParty.setValueColumnIndex(1);
			new TableExComboBoxSearchable(counterParty);
			return counterParty;
	}
	public static TableExComboBox getTraderComboBox(Vector<LegalEntity> leData) {
		TableExComboBox trader =  new TableExComboBox(getCPModelUtil(leData)) {
				@Override
				public JTable createTable(TableModel model) {
					return new SortableTable(model);
				}				
			};
			 
			trader.setEditable(false);
			trader.setBorder(null);
			trader.setValueColumnIndex(1);
			new TableExComboBoxSearchable(trader);
			return trader;
	}
	public static TableExComboBox getBookComboBox(Vector<Book> bookData) {
		TableExComboBox book =  new TableExComboBox(getBookModelUtil(bookData)) {
				@Override
				public JTable createTable(TableModel model) {
					return new SortableTable(model);
				}
			};
			book.setEditable(false);
			book.setBorder(null);
			book.setValueColumnIndex(1);
			new TableExComboBoxSearchable(book);
			return book;
	}
	public static TableExComboBox getFolderComboBox(Vector<Book> folderData) {
		TableExComboBox folder =  new TableExComboBox(getBookModelUtil(folderData)) {
				@Override
				public JTable createTable(TableModel model) {
					return new SortableTable(model);
				}
			};
			folder.setEditable(false);
			folder.setBorder(null);
			folder.setValueColumnIndex(1);
			new TableExComboBoxSearchable(folder);
			return folder;
	}
	
	
	
	public static TableExComboBox getCounterPartyComboBox(Vector<LegalEntity> leData, String col[]) {
		TableExComboBox counterParty =  new TableExComboBox(getCPModelUtil(leData, col)) {
				@Override
				public JTable createTable(TableModel model) {
					return new SortableTable(model);
				}				
			};
			counterParty.setEditable(false);
			counterParty.setBorder(null);
			counterParty.setValueColumnIndex(1);
			new TableExComboBoxSearchable(counterParty);
			return counterParty;
	}
	public static TableExComboBox getTraderComboBox(Vector<LegalEntity> leData, String col[]) {
		TableExComboBox trader =  new TableExComboBox(getCPModelUtil(leData, col)) {
				@Override
				public JTable createTable(TableModel model) {
					return new SortableTable(model);
				}								
			};
			 
			trader.setEditable(false);
			trader.setBorder(null);
			trader.setValueColumnIndex(1);
			new TableExComboBoxSearchable(trader);
			return trader;
	}
	
	public static TableExComboBox getCurrencyPair(Vector<CurrencyDefault>  currencyDefaultData, String col[]) {
		TableExComboBox currencyPair = null;
		if (currencyPair == null) {
			//currencyPair = new TableExComboBox();
		//	currencyPair.setText("CurrencyPair");
			currencyPair = new TableExComboBox(getCurrencyModelUtil(currencyDefaultData, col)) {
				@Override
				protected JTable createTable(TableModel model) {
					return new SortableTable(model);
				}
				
				
			};
			currencyPair.setEditable(false);
			currencyPair.setBorder(null);
			/*currencyPair.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int leid = currencyPair.getSelectedIndex();
					if(leid == 0)
						return;
					//String le = _vectorCp.get(leid);
					currencyPair.setName((String) currencyPair.getSelectedItem());
					
					//System.out.println(counterPary.getName());
					
				}
			});*/
			currencyPair.setValueColumnIndex(0);

			currencyPair.setSelectedIndex(0);
//			
			//counterPary.setSelectedItem("ALCOA INC");
		
			
			new TableExComboBoxSearchable(currencyPair);
			currencyPair.setEditable(false);
			return currencyPair;
		}
		return currencyPair;
		
	}
	public static TableExComboBox getBookComboBox(Vector<Book> bookData, String col[]) {
		TableExComboBox book =  new TableExComboBox(getBookModelUtil(bookData, col)) {
			@Override
				public JTable createTable(TableModel model) {
					return new SortableTable(model);
				}							
			};
			book.setEditable(false);
			book.setBorder(null);
			book.setValueColumnIndex(1);
			new TableExComboBoxSearchable(book);
			return book;
	}
	
	public static TableExComboBox getFolderComboBox(Vector<Book> folderData, String cols[]) {
		TableExComboBox folder =  new TableExComboBox(getBookModelUtil(folderData, cols)) {
				@Override
				public JTable createTable(TableModel model) {
					return new SortableTable(model);
				}							
			};
			folder.setEditable(false);
			folder.setBorder(null);
			folder.setValueColumnIndex(1);
			new TableExComboBoxSearchable(folder);
			return folder;
	}
	
}
