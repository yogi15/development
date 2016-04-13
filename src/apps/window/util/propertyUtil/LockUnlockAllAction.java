package apps.window.util.propertyUtil;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction; 
import com.jidesoft.grid.PropertyTableModel;

public class LockUnlockAllAction extends AbstractAction {
    private boolean lock;
    private PropertyTableModel propertyTableModel;
    private List properties;

    LockUnlockAllAction(boolean lock, PropertyTableModel propertyTableModel) {
        super(lock ? "Lock all" : "Unlock all");
        this.lock = lock;
        this.propertyTableModel = propertyTableModel;
        this.properties = propertyTableModel.getOriginalProperties();
    }

    public void actionPerformed(ActionEvent e) {
        for (Object property : properties) {
            if (property instanceof DuplicateProperty)
                ((DuplicateProperty) property).setLocked(lock);

        }
        propertyTableModel.refresh();
    }
}