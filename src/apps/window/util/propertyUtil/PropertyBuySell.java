package apps.window.util.propertyUtil;
 

import java.awt.Color;
import java.awt.Component;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.CellEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import util.commonUTIL;
 
 
import FutureContract.FutureContractProperty;

import apps.window.util.propertyTable.CustomPropertyTableModel;
import apps.window.util.propertyUtil.Date.DateCellRenderer;

import com.jidesoft.combobox.ListExComboBox;
import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.DefaultProperty;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.grid.FontNameCellEditor;
import com.jidesoft.grid.FormattedTextFieldCellEditor;
import com.jidesoft.grid.ListComboBoxCellEditor;
import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.PropertyTableModel;
 
public class PropertyBuySell extends PropertyEnum<String> {
	private final String BUY = "BUY";
	private final String SELL = "SELL";
	  

	  static HashMap<String, Object> map = new HashMap<String, Object>();
	  static {
	        map.put("BUY choices", "BUY");
	        map.put("SELL choices", "SELL"); 
	    }
	/*  static public PropertyBuySell createAutoCompleteComboBox(
				String name, String description, String category,final Collection values) {
			
			CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
				public CellEditor create() {
					return new AutoCompleteCellEditor(values.toArray());
				}

				 
			}, new EditorContext(name));
		/	PropertyBuySell property = new PropertyBuySell(name, description, String.class,"test");
			property.setEditorContext(new EditorContext(name));
			property.setName(name);
			property.setCategory(category);
			
			return property;
		}*/
	  private static Vector<String> getPropertyValueVec(String domainName) {
			
			Vector<String> propertyValuesVec = new Vector<String>();
			
			if (domainName.equals("Exchange")) {
				
				propertyValuesVec.add("BSE");
				propertyValuesVec.add("NSE");
				propertyValuesVec.add("MCX");
				propertyValuesVec.add("LSE");
				propertyValuesVec.add("BUY");
				propertyValuesVec.add("SELL");
				
			} else if (domainName.equals("SettlementCCY")) {
				
				propertyValuesVec.add("INR");
				propertyValuesVec.add("USD");
				propertyValuesVec.add("GBP");
				propertyValuesVec.add("AUD");
				propertyValuesVec.add("BUY");
				propertyValuesVec.add("SELL");
				
			}
			
			return propertyValuesVec;
		}
	  Property pp;
	  @Override
	    public TableCellRenderer getTableCellRenderer() {
	        CustomRenderer renderer = new CustomRenderer( );
	      
	        return renderer;
	    }
	public PropertyBuySell(String name,  String description, Vector values, String category) {
		 
		super(name,"", values);
		 setType(String.class);
	        setName(name);
	        setDisplayName(name);
	        setCategory(category);
	        EditorContext genericEditorContext = new EditorContext("Generic");
	        genericEditorContext = new EditorContext("Generic", new String[]{"BUY", "SELL"});
	        
	    /*    property.setEditorContext(genericEditorContext);
	        final Vector<String> propertyValuesVec =	getPropertyValueVec("Exchange");
	        CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
				public CellEditor create() {
					return new AutoCompleteCellEditor(propertyValuesVec.toArray());
				}

				 
			}, new EditorContext(name));*/
	        
		 // setEditorContext(genericEditorContext);
	    	 
	    }
	/*public static PropertyBuySell getPropertyValue(String domainName, String category) {

		//FutureContractProperty property = new FutureContractProperty();
		
		Vector<String> propertyValuesVec =	getPropertyValueVec("Exchange");
			
		PropertyBuySell property = PropertyBuySell.createAutoCompleteComboBox(domainName, "Exchange", category,propertyValuesVec);
		
		return property;
	}*/
	String bullSell = "";
    @Override
    public void setValue(Object value) {
    	bullSell =(String) value;
     //   map.put(getFullName(), value);
    }

    @Override
    public String getValue() {
    	
    	 System.out.println(bullSell);
    	 
        return bullSell;
    }
  
    @Override
    public boolean hasValue() {
        return map.get(getFullName()) != null;
    }

    static GenericCellEditor _cellEditor = new GenericCellEditor();

    @Override
    public CellEditor getCellEditor(int column) {
        if (column == 1) {
            return _cellEditor;
        }
        return super.getCellEditor(column);
    }	 
    
    class CustomRenderer extends DefaultTableCellRenderer {
    	  
    	 @Override
    	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	        TableModel tableModel = table.getModel();
    	        String strDate =(String) value;
    	        JLabel rendererComponent = (JLabel) super.getTableCellRendererComponent(table,
    	        		strDate,
                        isSelected,
                        hasFocus,
                        row,
                        column);
    	        final Color COLOR_ERROR = new java.awt.Color(255 ,48 ,48 );
                rendererComponent.setOpaque(true);
                
    	        if (!commonUTIL.isEmpty(strDate) && strDate.equalsIgnoreCase("SELL")) {
                    rendererComponent.setBackground( COLOR_ERROR);
                } else {
                    if (tableModel instanceof CustomPropertyTableModel)
                        ((CustomPropertyTableModel) tableModel).removeCellStyleAt(row);
                    else {
                        if (isSelected) {
                            rendererComponent.setBackground(UIManager.getColor("Table.selectionBackground"));
                            rendererComponent.setForeground(UIManager.getColor("Table.selectionForeground"));
                        } else {
                            rendererComponent.setForeground(UIManager.getColor("Table.foreground"));
                            rendererComponent.setBackground(UIManager.getColor("Table.background"));
                        }
                    }
                }
                return rendererComponent;
    	        
    	        
    	 }
    }
           
    static class GenericCellEditor extends ListComboBoxCellEditor {
        private static final long serialVersionUID = -5327421827562327015L;

        public GenericCellEditor() {
            super(new Object[0]);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            Component component = super.getTableCellEditorComponent(table, value, isSelected, row, column);
            if (component instanceof ListExComboBox && table instanceof PropertyTable && table.getModel() instanceof PropertyTableModel) {
                PropertyTableModel model = (PropertyTableModel) table.getModel();
                Property property = model.getPropertyAt(row);
                EditorContext context = property.getEditorContext();
                if (context != null && context.getUserObject() instanceof String[]) {
                    DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel((String[]) context.getUserObject());
                    comboBoxModel.setSelectedItem(value);
                    ((ListExComboBox) component).setModel(comboBoxModel);
                    ((ListExComboBox) component).setPopupVolatile(true);
                }
            }
            return component;
        }
    }
    
}
