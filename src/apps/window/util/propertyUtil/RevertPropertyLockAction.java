package apps.window.util.propertyUtil;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
  

public class RevertPropertyLockAction extends AbstractAction {
    private DuplicateProperty managedProperty;

    public RevertPropertyLockAction(DuplicateProperty propertyProxy) {
        super(propertyProxy.isLocked() ? "Unlock" : "Lock");
        this.managedProperty = propertyProxy;
    }

    public void actionPerformed(ActionEvent e) {
        managedProperty.setLocked(!managedProperty.isLocked());
        managedProperty.getTreeTableModel().refresh();
    }
}