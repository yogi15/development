import java.awt.*;   
import java.awt.event.*;   
import java.util.*;   
import java.util.List;   
import javax.swing.*;   
import javax.swing.plaf.basic.BasicComboBoxRenderer;   
    
public class ComboSelections {   
    
    private JPanel getContent() {   
        // We need to keep track of the selections   
        SelectionManager manager = new SelectionManager();   
        // and make the selection state available to the renderer.   
        MultiRenderer renderer = new MultiRenderer(manager);   
        Object[] items = {   
            "George", "Greta", "Jenny", "Anna", "Pieter", "Antonio", "Susan", "Tom"  
        };   
        manager.setNonSelectables("Greta", "Pieter");   
        DefaultComboBoxModel model = new DefaultComboBoxModel(items);   
        JComboBox combo = new JComboBox(model);   
        combo.addActionListener(manager);   
        combo.setRenderer(renderer);   
        JPanel panel = new JPanel();   
        panel.add(combo);   
        return panel;   
    }   
    
    public static void main(String[] args) {   
        JFrame f = new JFrame();   
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        f.getContentPane().add(new ComboSelections().getContent());   
        f.setSize(300,145);   
        f.setLocation(200,200);   
        f.setVisible(true);   
    }   
}   
    
class SelectionManager implements ActionListener {   
    JComboBox combo = null;   
    List<Object> selectedItems = new ArrayList<Object>();  // j2se 1.5+   
    // List selectedItems = new ArrayList();               // j2se 1.4-   
    List<Object> nonSelectables = new ArrayList<Object>();   
    
    public void actionPerformed(ActionEvent e) {   
        if(combo == null) {   
            combo = (JComboBox)e.getSource();   
        }   
        Object item = combo.getSelectedItem();   
        // Toggle the selection state for item.   
        if(selectedItems.contains(item)) {   
            selectedItems.remove(item);   
        } else if(!nonSelectables.contains(item)) {   
            selectedItems.add(item);   
        }   
    }   
    
    /**  
     * The varargs feature (Object... args) is new in j2se 1.5  
     * You can replace the argument with an array.  
     */  
    public void setNonSelectables(Object... args) {   
        for(int j = 0; j < args.length; j++) {   
            nonSelectables.add(args[j]);   
        }   
    }   
    
    public boolean isSelected(Object item) {   
        return selectedItems.contains(item);   
    }   
}   
    
/** Implementation copied from source code. */  
class MultiRenderer extends BasicComboBoxRenderer {   
    SelectionManager selectionManager;   
    
    public MultiRenderer(SelectionManager sm) {   
        selectionManager = sm;   
    }   
    
    public Component getListCellRendererComponent(JList list,   
                                                  Object value,   
                                                  int index,   
                                                  boolean isSelected,   
                                                  boolean cellHasFocus) {   
        if (selectionManager.isSelected(value)) {   
            setBackground(list.getSelectionBackground());   
            setForeground(list.getSelectionForeground());   
        } else {   
            setBackground(list.getBackground());   
            setForeground(list.getForeground());   
        }   
    
        setFont(list.getFont());   
    
        if (value instanceof Icon) {   
            setIcon((Icon)value);   
        } else {   
            setText((value == null) ? "" : value.toString());   
        }   
        return this;   
    }   
}  
