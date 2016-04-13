package apps.window.util.propertyUtil;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
 
import util.common.NumberFormatUtil; 

public class NumberCellRenderer extends DefaultTableCellRenderer {

    /**
     * 
     */
    private static final long serialVersionUID = 2712828402556141094L;

    private boolean isInPercent;

    public NumberCellRenderer() {
    }

    public NumberCellRenderer(boolean isInPercent) {
        this.isInPercent = isInPercent;
    }

    public boolean isInPercent() {
        return isInPercent;
    }

    public void setInPercent(boolean isInPercent) {
        this.isInPercent = isInPercent;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {
        Double numberValue = value == null
                                          ? null
                                          : ((Number) value).doubleValue();
        if (isInPercent()
                && numberValue != null)
            numberValue = numberValue * 100d;
        String s = numberValue == null
                                      ? null : NumberFormatUtil.numberToString(numberValue);
        JLabel rendererComponent = (JLabel) super.getTableCellRendererComponent(table,
                                                                                s,
                                                                                isSelected,
                                                                                hasFocus,
                                                                                row,
                                                                                column);
        rendererComponent.setHorizontalAlignment(SwingConstants.RIGHT);
        return rendererComponent;
    }
}
