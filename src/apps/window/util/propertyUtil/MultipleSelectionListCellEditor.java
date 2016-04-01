package apps.window.util.propertyUtil;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
 
import apps.window.util.windowUtil.MultiSelectionComboxBox; 
import com.jidesoft.swing.NullPanel;
 
 
public class MultipleSelectionListCellEditor extends AbstractCellEditor implements TableCellEditor {
    ArrayList domain;
    private  PanelEditor panel;
    
    
    MultipleSelectionListCellEditor (List domain) {
        this.domain = new ArrayList(domain);
    }

    public Object getCellEditorValue() {
        return panel.getValue();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        panel = new PanelEditor((Selection) value);
        return panel;
    }
    
    private class PanelEditor extends NullPanel {
    	 private JCheckBox checkBox;
    	  private MultiSelectionComboxBox dualListComboBox;
         public PanelEditor(final Selection value) {
             super(new BorderLayout());


             checkBox = new JCheckBox();
             checkBox.setSelected(value != null && value.isIncludeExclude());

                 dualListComboBox = new MultiSelectionComboxBox(domain, value == null ? null : value.getItems()) {
                 @Override
                 public void setSelectedItem(Object anObject) {
                     super.setSelectedItem(anObject);
                     stopCellEditing();
                 }
             };

             add(checkBox, BorderLayout.WEST);
             add(dualListComboBox, BorderLayout.CENTER);
         }

         public Selection getValue() {
             Selection value = new Selection();
             Object selectedItem = dualListComboBox.getSelectedItem();
             value.setItems(selectedItem == null ? Collections.emptyList() : (List) selectedItem);
             value.setIncludeExclude(checkBox.isSelected());
             return value;
         }
     }

}