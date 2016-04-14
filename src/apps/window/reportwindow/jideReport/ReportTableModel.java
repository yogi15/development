package apps.window.reportwindow.jideReport;

import com.jidesoft.grid.DefaultContextSensitiveTableModel;

// Provides additional functionality for converting the objects based on type
public final class ReportTableModel extends DefaultContextSensitiveTableModel {

    public static ReportTableModel newModel(final Report report) {
        return new ReportTableModel(report);
    }

    @Override
    public Class<?> getColumnClass(final int columnIndex) {
        // For dynamic behavior, the traditional switch has been
        // replaced with a lookup
        if (m_report.getDataType().length > columnIndex) {
            return m_report.getDataType()[columnIndex];
        }
        return super.getColumnClass(columnIndex);
    }


    @Override
    public boolean isCellEditable(final int row, final int column) {
        return false;
    }

    private ReportTableModel(final Report report) {
        super(report.getData(), report.getDataHeader());
        m_report = report;
    }

    private final transient Report m_report;
}
