package apps.window.util.propertyUtil;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.CellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import util.commonUTIL;
 
import com.jidesoft.grid.DefaultProperty;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.swing.NullPanel;

public class PropertyListMultipleSelection<T> extends DefaultProperty {
    EditorContext editorContext;
    private List<T> domain;
     
    public PropertyListMultipleSelection(String name, String displayName, String category, List<T> domain) {
        this.domain = domain;
        setName(name);
        setDisplayName(displayName);
        setCategory(category);
        setType(List.class);
    }
    
    public PropertyListMultipleSelection( String name, String displayName, String category, T... domain) {
        this(name, displayName, category, Arrays.asList(domain));
    }
    
    @Override
    public TableCellRenderer getTableCellRenderer() {
        return new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            	if(value instanceof String) {
            	List<String> values =new ArrayList<String>();
            	commonUTIL.convertStringToCollection((String) value,values);
            		Selection<String> selection = new Selection<String>();
            		selection.setIncludeExclude(true);
            		selection.setItems(values);
            		final String text = value == null ? null : commonUTIL.collectionToString(((Selection) selection).getItems());
                    final Component rendererComponent = super.getTableCellRendererComponent(table, text, isSelected, hasFocus, row, column);
                    final JCheckBox checkBox = new JCheckBox();
                    checkBox.setSelected(value != null && ((Selection) selection).isIncludeExclude());
                    NullPanel panel = new NullPanel(new BorderLayout());
                    panel.add(checkBox, BorderLayout.WEST);
                    panel.add(rendererComponent, BorderLayout.CENTER);
                    return panel;
            		 
            	}
                final String text = value == null ? null : commonUTIL.collectionToString(((Selection) value).getItems());
                final Component rendererComponent = super.getTableCellRendererComponent(table, text, isSelected, hasFocus, row, column);
                final JCheckBox checkBox = new JCheckBox();
                checkBox.setSelected(value != null && ((Selection) value).isIncludeExclude());
                NullPanel panel = new NullPanel(new BorderLayout());
                panel.add(checkBox, BorderLayout.WEST);
                panel.add(rendererComponent, BorderLayout.CENTER);
                return panel;
            }
        };
    }

    @Override
    public CellEditor getCellEditor() {
        return new MultipleSelectionListCellEditor(domain);
    }
}
    
    


