package apps.window.util.propertyUtil.Date;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel; 

import util.commonUTIL;
import util.common.CDate;
import util.common.holiday.Holiday;

import apps.window.util.propertyTable.CustomPropertyTableModel;
import apps.window.util.propertyUtil.Date.Provider.ProviderHoliday;
import apps.window.util.propertyUtil.Date.Provider.ProviderTimeZone;

public class DateCellRenderer  extends DefaultTableCellRenderer {
    /**
     * 
     */
    private static final long serialVersionUID = -4920176133767175989L;
    ProviderHoliday holidaysProvider;
   ProviderTimeZone timeZoneProvider;
    private boolean isTimeDisplayed;

    public DateCellRenderer(ProviderHoliday holidaysProvider) {
        this.holidaysProvider = holidaysProvider;
    }

    public DateCellRenderer(ProviderHoliday holidaysProvider, ProviderTimeZone timeZoneProvider) {
        this.holidaysProvider = holidaysProvider;
        this.timeZoneProvider = timeZoneProvider;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TableModel tableModel = table.getModel();
        if (value != null) {
            Calendar cal = value instanceof Calendar
                                                    ? (Calendar) value
                                                    : makeCalendar((Date) value,
                                                                   getTimeZone());
            CDate date = CDate.valueOf(cal.get(Calendar.YEAR),
                                       cal.get(Calendar.MONTH) + 1,
                                       cal.get(Calendar.DAY_OF_MONTH));
            boolean isBusinessDay = Holiday.getCurrent()
                    .isBusinessDay(date,
                                   holidaysProvider == null
                                                           ? new Vector<Object>()
                                                           : holidaysProvider.getHolidays());
            String strDate;
            if (isTimeDisplayed) {
                DateFormat format = commonUTIL.getDatetimeFormatShortMedium();
                strDate = format.format(cal.getTime());
            } else {
                strDate = date.toString();
            }
            JLabel rendererComponent = (JLabel) super.getTableCellRendererComponent(table,
                                                                                    strDate,
                                                                                    isSelected,
                                                                                    hasFocus,
                                                                                    row,
                                                                                    column);

           final Color COLOR_ERROR = new java.awt.Color(255, 153, 153);
            rendererComponent.setOpaque(true);
            if (!isBusinessDay) {
                rendererComponent.setBackground( COLOR_ERROR);
            } else {
                if (tableModel instanceof CustomPropertyTableModel)
                    ((CustomPropertyTableModel) tableModel).removeCellStyleAt(row);
                else {
                    if (isSelected) {
                        rendererComponent.setBackground(UIManager.getColor("Table.selectionBackground"));
                        rendererComponent.setForeground(UIManager.getColor("Table.selectionForeground"));
                    } else {
                        rendererComponent.setForeground(UIManager.getColor("Table.foreground"));
                        rendererComponent.setBackground(UIManager.getColor("Table.background"));
                    }
                }
            }
            return rendererComponent;
        }
        return super.getTableCellRendererComponent(table,
                                                   null,
                                                   isSelected,
                                                   hasFocus,
                                                   row,
                                                   column);
    }

    private TimeZone getTimeZone() {
        if (timeZoneProvider == null)
            return TimeZone.getDefault();
        else
            return timeZoneProvider.getTimeZone();
    }

    private Calendar makeCalendar(Date date, TimeZone timeZone) {
        Calendar cal = Calendar.getInstance(timeZone);
        cal.setTime(date);
        return cal;
    }

    /**
     * Sets whether time is displayed
     * 
     * @param b
     *            a boolean value, true if time is displayed, false if only the
     *            date is displayed
     */
    public void setTimeDisplayed(boolean b) {
        isTimeDisplayed = b;
    }

    /**
     * Returns whether time is displayed
     * 
     * @return true if time and date are both displayed, false if only the date
     *         is displayed
     */
    public boolean isTimeDisplayed() {
        return isTimeDisplayed;
    }


}
