package apps.window.reportwindow.jideReport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.jidesoft.grid.CachedTableModel;
import com.jidesoft.grid.CalculatedTableModel;
import com.jidesoft.grid.SortableTable;
import com.jidesoft.hssf.HssfPivotTableUtils;
import com.jidesoft.pivot.DataTable;
import com.jidesoft.pivot.IPivotDataModel;
import com.jidesoft.pivot.PivotConstants;
import com.jidesoft.pivot.PivotDataModel;
import com.jidesoft.pivot.PivotField;
import com.jidesoft.pivot.PivotTablePane;
import com.jidesoft.pivot.PivotTablePersistenceUtils;

/**
 * PivotView controls the look, feel, and behavior of the Pivot table.
 */
public class PivotView implements JideView {

    public static PivotView newUi(final CalculatedTableModel calculatedTableModel,
            final String[] headerColumns, final String[] rowColumns,
            final String[] dataColumns) {
        String[] header = headerColumns;
        String[] row = rowColumns;
        String[] data = dataColumns;

        if (headerColumns == null) {
            header = PivotView.BLANK;
        }
        if (rowColumns == null) {
            row = PivotView.BLANK;
        }
        if (dataColumns == null) {
            data = PivotView.BLANK;
        }
        return new PivotView(calculatedTableModel, header, row, data);
    }

    @Override
    public void export(final String fileName, final String sheetName,
            final boolean append) {
        try {
            HssfPivotTableUtils.export(getPivotTablePane(), fileName,
                sheetName, append);
        } catch (final IOException excep) {
            excep.printStackTrace();
        }
    }

    public TableModel getCalculatedModel() {
        return m_calculatedModel;
    }

    @Override
    public JPanel getJidePanel() {
        if (m_jidePanel == null) {
            m_jidePanel = new JPanel(new BorderLayout(3, 3));
        //    m_jidePanel.setBorder(BorderFactory.createMatteBorder(10, 10,
          //      10, 10, Color.white));
            m_jidePanel.add(getPivotTablePane(), BorderLayout.CENTER);
            m_jidePanel.updateUI();
        }
        return m_jidePanel;
    }

    @Override
    public IPivotDataModel getPivotModel() {
        return getPivotTablePane().getPivotDataModel();
    }

    public PivotTablePane getPivotTablePane() {
        if (m_pivotTablePane == null) {
            m_pivotTablePane = new PivotTablePane(getPivotDataModel()) {

                @Override
                protected DataTable createDataTable() {
                    final DataTable dataTable = super.createDataTable();
                    dataTable.setModel(new CachedTableModel(
                        dataTable.getModel()));
                    return dataTable;
                }
            };
            m_pivotTablePane.fieldsUpdated();
            m_pivotTablePane.setPlainHeaderTables(true);

            m_pivotTablePane.getDataTable().getTableHeader();
            setLayoutVisible(true);
            m_pivotTablePane.autoResizeAllColumns();
        }
        return m_pivotTablePane;
    }

    @Override
    public void loadLayout(final String fileName) {
        try {
            PivotTablePersistenceUtils.load(getPivotTablePane(), fileName);
            getPivotTablePane().fieldsUpdated();
        } catch (final ParserConfigurationException excep) {
            excep.printStackTrace();
        } catch (final SAXException excep) {
            excep.printStackTrace();
        } catch (final IOException excep) {
            excep.printStackTrace();
        }
    }

    @Override
    public void saveLayout(final String fileName) {
        try {
            PivotTablePersistenceUtils.save(getPivotTablePane(), fileName);
        } catch (final ParserConfigurationException excep) {
            excep.printStackTrace();
        } catch (final IOException excep) {
            excep.printStackTrace();
        }
    }


    void setLayoutVisible(final boolean visable) {
        getPivotTablePane().setRearrangable(visable);
        getPivotTablePane().setRowFieldAreaVisible(visable);
        getPivotTablePane().setRowFieldFilterable(visable);
        getPivotTablePane().setFieldChooserEnabled(visable);
        getPivotTablePane().setFieldChooserFilterFieldVisible(visable);
        getPivotTablePane().setFieldChooserVisible(visable);
        getPivotTablePane().setFilterFieldAreaVisible(visable);
        getPivotTablePane().setDataFieldAreaVisible(visable);
        getPivotTablePane().setDataFieldFilterable(visable);
        getPivotTablePane().setColumnFieldAreaVisible(visable);
        getPivotTablePane().setColumnFieldFilterable(visable);
    }

    private String[] getColumnData() {
        return m_dataColumns;
    }

    private String[] getColumnHeaders() {
        return m_headerColumns;
    }

    private String[] getColumnRows() {
        return m_rowColumns;
    }

    private PivotDataModel getPivotDataModel() {
        if (m_pivotDataModel == null) {
            m_pivotDataModel = new PivotDataModel(getCalculatedModel());

            // ROW (Right columns)
            int index = 1;
            for(final String name : getColumnRows()) {
                final PivotField field = m_pivotDataModel.getField(name);
                if (field != null) {
                    field.setAreaType(PivotConstants.AREA_ROW);
                    field.setAreaIndex(index);
                    field.setTitle(name);
                    index++;
                }
            }

            // COLUMN (Top columns)
            index = 1;
            for(final String name : getColumnHeaders()) {
                final PivotField field = m_pivotDataModel.getField(name);
                if (field != null) {
                    field.setAreaType(PivotConstants.AREA_COLUMN);
                    field.setAreaIndex(index);
                    index++;
                }
            }

            // DATA (Center)
            index = 1;
            for(final String name : getColumnData()) {
                final PivotField field = m_pivotDataModel.getField(name);
                if (field != null) {
                    field.setAreaType(PivotConstants.AREA_DATA);
                    field.setAreaIndex(index);
                    field.setTitle(name);
                    index++;
                }
            }

            m_pivotDataModel.setShowGrandTotalForColumn(true);
            m_pivotDataModel.setShowGrandTotalForRow(true);
            m_pivotDataModel.calculate();
            m_pivotDataModel.setAutoUpdate(true);
        }
        return m_pivotDataModel;
    }

    PivotView(final CalculatedTableModel calculatedTableModel,
            final String[] headerColumns, final String[] rowColumns,
            final String[] dataColumns) {
        m_calculatedModel = calculatedTableModel;
        m_headerColumns = headerColumns;
        m_rowColumns = rowColumns;
        m_dataColumns = dataColumns;
    }

    private PivotDataModel m_pivotDataModel;

    private final String[] m_headerColumns;

    private final String[] m_rowColumns;

    private final String[] m_dataColumns;

    private PivotTablePane m_pivotTablePane;

    private static final String[] BLANK = {};

    JPanel m_jidePanel;

    private final CalculatedTableModel m_calculatedModel;

	@Override
	public SortableTable getTable() {
		// TODO Auto-generated method stub
		return null;
	}
}
