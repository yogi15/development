package apps.window.reportwindow.jideReport;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;

import com.jidesoft.grid.AutoFilterTableHeader;
import com.jidesoft.grid.SortableTable;
import com.jidesoft.grid.TableUtils;
import com.jidesoft.hssf.HssfTableUtils;
import com.jidesoft.pivot.IPivotDataModel;

public class SortingView   implements JideView {


	public static JideView newUi(final TableModel tableModel) {
        return new SortingView(tableModel);
    }

@Override
public void export(String fileName, String sheetName, boolean append) {
	 try {
         HssfTableUtils.export(getSortTable(), fileName,
             sheetName, append);
     } catch (final IOException excep) {
         excep.printStackTrace();
     }

	
}

@Override
public TableModel getCalculatedModel() {
	// TODO Auto-generated method stub
	return m_tableModel;
}

@Override
public JPanel getJidePanel() {
	 if (m_jideTablePanel == null) {
         m_jideTablePanel = new JPanel(new BorderLayout(3, 3));
         m_jideTablePanel.setBorder(BorderFactory.createMatteBorder(10, 10,
             10, 10, Color.white));
         m_jideTablePanel.add(new JScrollPane(getSortTable()),
             BorderLayout.CENTER);
         m_jideTablePanel.updateUI();
     }
     return m_jideTablePanel;
}



/**
 * Constructs a AggregateTable if it does not exist.
 * @return AggregateTable
 */
public SortableTable getSortTable() {
    if (m_sortableTable == null) {
    	m_sortableTable = new SortableTable(getCalculatedModel());
       
        // * Install table header pop-ups to the AggregateTable
        // * This allows the user to control functionality by
        //   right-clicking on the column header.
        // * Controls: sizing, grouping, display state, subtotals.
        

        final AutoFilterTableHeader header =
            new AutoFilterTableHeader(m_sortableTable);
        m_sortableTable.setTableHeader(header);
        header.setReorderingAllowed(false);
        header.setAutoFilterEnabled(true);
        header.setShowFilterName(true);
        header.setShowFilterIcon(true);
        header.setShowSortArrow(true);
        header.setAllowMultipleValues(true);
        header.setAutoscrolls(true);

    //    m_aggregateTable.getAggregateTableModel().setShowGrandTotal(true);
      //  m_aggregateTable.getAggregateTableModel().setSummaryMode(true);
        TableUtils.autoResizeAllColumns(m_sortableTable);
    TableUtils.autoResizeAllRows(m_sortableTable);
    m_sortableTable.setRowSelectionAllowed(true);
   // m_sortableTable.setco
        // add some spacing between cells to make it easier to read
        
       
        m_sortableTable.getIntercellSpacing();
        final int gapWidth = 2;
        final int gapHeight = 1;
        m_sortableTable.setIntercellSpacing(new Dimension(gapWidth,
            gapHeight));
        m_sortableTable.setRowHeight(m_sortableTable.getRowHeight()
            + gapHeight);
        
        m_sortableTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println( m_sortableTable.getSelectedRow());			
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
    }
    return m_sortableTable;
}


@Override
public IPivotDataModel getPivotModel() {
	// TODO Auto-generated method stub
	
	return null;
}

@Override
public void loadLayout(String fileName) {
	// TODO Auto-generated method stub
	
}

@Override
public void saveLayout(String fileName) {
	// TODO Auto-generated method stub
	
}

SortingView(final TableModel tableModel) {
	m_tableModel = tableModel;
}

private JPanel m_jideTablePanel;

private SortableTable m_sortableTable;

private final TableModel m_tableModel;

@Override
public SortableTable getTable() {
	// TODO Auto-generated method stub
	return  m_sortableTable;
}
}