package apps.window.reportwindow.jideReport;


import javax.swing.JPanel;
import javax.swing.table.TableModel;

import com.jidesoft.grid.SortableTable;
import com.jidesoft.pivot.IPivotDataModel;

public interface JideView {

    void export(String fileName, String sheetName,
            boolean append);

    JPanel getJidePanel();

    IPivotDataModel getPivotModel();

    void loadLayout(String fileName);

    void saveLayout(String fileName);
    SortableTable getTable();
    TableModel getCalculatedModel();

}