package apps.window.reportwindow.jideReport;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.jidesoft.converter.MonthNameConverter;
import com.jidesoft.converter.QuarterNameConverter;
import com.jidesoft.converter.YearNameConverter;
import com.jidesoft.grid.AutoFilterTableHeader;
import com.jidesoft.grid.AutoResizePopupMenuCustomizer;
import com.jidesoft.grid.CalculatedTableModel;
import com.jidesoft.grid.QuickTableFilterField;
import com.jidesoft.grid.SelectTablePopupMenuCustomizer;
import com.jidesoft.grid.SingleColumn;
import com.jidesoft.grid.SortableTable;
import com.jidesoft.grid.TableHeaderPopupMenuInstaller;
import com.jidesoft.grid.TableUtils;
import com.jidesoft.grouper.date.DateMonthGrouper;
import com.jidesoft.grouper.date.DateQuarterGrouper;
import com.jidesoft.grouper.date.DateWeekOfYearGrouper;
import com.jidesoft.grouper.date.DateYearGrouper;
import com.jidesoft.hssf.HssfTableUtils;
import com.jidesoft.pivot.AggregateTable;
import com.jidesoft.pivot.AggregateTableColumnChooserPopupMenuCustomizer;
import com.jidesoft.pivot.AggregateTablePersistenceUtils;
import com.jidesoft.pivot.AggregateTablePopupMenuCustomizer;
import com.jidesoft.pivot.IPivotDataModel;

/**
 * AggregateView controls the look, feel, and behavior of the Aggregate table.
 */
final public class AggregateView implements JideView {

    public static JideView newUi(final CalculatedTableModel calculatedTableModel) {
        return new AggregateView(calculatedTableModel);
    }

    @Override
    public void export(final String fileName, final String sheetName,
            final boolean append) {
        try {
            HssfTableUtils.export(getAggregateTable(), fileName,
                sheetName, append);
        } catch (final IOException excep) {
            excep.printStackTrace();
        }

    }
    private CalculatedTableModel setupProductDetailsCalculatedTableModel(TableModel tableModel) {
        CalculatedTableModel calculatedTableModel = new CalculatedTableModel(tableModel);
         
        return calculatedTableModel;
    }
    /**
     * Constructs a AggregateTable if it does not exist.
     * @return AggregateTable
     */
    public AggregateTable getAggregateTable() {
        if (m_aggregateTable == null) {
        	System.out.println(getCalculatedModel().getColumnCount());
        	int [] column = new int[getCalculatedModel().getColumnCount()];
    		for(int i=0;i<getCalculatedModel().getColumnCount();i++) 
    			column [i] = i;
        	QuickTableFilterField field = new QuickTableFilterField(getCalculatedModel(),column);

            final CalculatedTableModel calculatedTableModel = setupProductDetailsCalculatedTableModel(field.getDisplayTableModel());

            m_aggregateTable = new AggregateTable(getCalculatedModel());

            // * Install table header pop-ups to the AggregateTable
            // * This allows the user to control functionality by
            //   right-clicking on the column header.
            // * Controls: sizing, grouping, display state, subtotals.
            final TableHeaderPopupMenuInstaller installer =
                new TableHeaderPopupMenuInstaller(m_aggregateTable);
            installer.addTableHeaderPopupMenuCustomizer(new AutoResizePopupMenuCustomizer());
            installer.addTableHeaderPopupMenuCustomizer(new AggregateTablePopupMenuCustomizer());
            installer.addTableHeaderPopupMenuCustomizer(new AggregateTableColumnChooserPopupMenuCustomizer());
            installer.addTableHeaderPopupMenuCustomizer(new SelectTablePopupMenuCustomizer());

            final AutoFilterTableHeader header =
                new AutoFilterTableHeader(m_aggregateTable);
            System.out.println(header.getName());
            m_aggregateTable.setTableHeader(header);
            header.setAutoFilterEnabled(true);
            header.setShowFilterName(true);
            header.setShowFilterIcon(true);
            header.setShowSortArrow(true);
            header.setAllowMultipleValues(true);
            header.setAutoscrolls(true);
            m_aggregateTable.setRowSelectionAllowed(true);
            m_aggregateTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            m_aggregateTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    		

            m_aggregateTable.getAggregateTableModel().setShowGrandTotal(true);
            m_aggregateTable.getAggregateTableModel().setSummaryMode(true);
            TableUtils.autoResizeAllColumns(m_aggregateTable);

            // add some spacing between cells to make it easier to read
            m_aggregateTable.getIntercellSpacing();
            final int gapWidth = 2;
            final int gapHeight = 1;
            m_aggregateTable.setIntercellSpacing(new Dimension(gapWidth,
                gapHeight));
            m_aggregateTable.setRowHeight(m_aggregateTable.getRowHeight()
                + gapHeight);
            m_aggregateTable.setColumnSelectionAllowed(true);
           // m_aggregateTable.getColumnCount()
        }
        
        return m_aggregateTable;
    }


    public TableModel getCalculatedModel() {
        return m_calculatedModel;
    }

    public JPanel getJidePanel() {
        if (m_jideTablePanel == null) {
            m_jideTablePanel = new JPanel(new BorderLayout(3, 3));
            m_jideTablePanel.setBorder(BorderFactory.createMatteBorder(10, 10,
                10, 10, Color.white));
            m_jideTablePanel.add(new JScrollPane(getAggregateTable()),
                BorderLayout.CENTER);
            m_jideTablePanel.updateUI();
        }
        return m_jideTablePanel;
    }

    public IPivotDataModel getPivotModel() {
        return getAggregateTable().getAggregateTableModel().getPivotDataModel();
    }


    @Override
    public void loadLayout(final String fileName) {
        try {
            AggregateTablePersistenceUtils.load(getAggregateTable(), fileName);
            getAggregateTable().updateUI();

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
            AggregateTablePersistenceUtils.save(getAggregateTable(), fileName);
        } catch (final ParserConfigurationException excep) {
            excep.printStackTrace();
        } catch (final IOException excep) {
            excep.printStackTrace();
        }
    }

    private AggregateView(final CalculatedTableModel calculatedTableModel) {
        m_calculatedModel = calculatedTableModel;
    }

    private JPanel m_jideTablePanel;

    private AggregateTable m_aggregateTable;

    public final CalculatedTableModel m_calculatedModel;

	@Override
	public SortableTable getTable() {
		// TODO Auto-generated method stub
		return getAggregateTable();
	}
}
