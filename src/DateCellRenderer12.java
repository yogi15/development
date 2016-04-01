
import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import com.jidesoft.grid.CellStyle;
import com.jidesoft.grid.StyleModel;



public class DateCellRenderer12 extends DefaultTableCellRenderer {

	private boolean isTimeDisplayed;

    public DateCellRenderer12() {
       
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TableModel tableModel = table.getModel();
        JLabel rendererComponent = null;
        if (value != null) {
            Calendar cal = value instanceof Calendar
                                                    ? (Calendar) value
                                                    : makeCalendar((Date) value,
                                                                   getTimeZone());
            
            
            
            Date date = new Date(cal.get(Calendar.YEAR),
                                       cal.get(Calendar.MONTH) + 1,
                                       cal.get(Calendar.DAY_OF_MONTH));
           
            String strDate;
            if (isTimeDisplayed) {
                DateFormat format =new SimpleDateFormat("hh:mm:ss");
                strDate = format.format(cal.getTime());
            } else {
                strDate = date.toString();
            }
            rendererComponent = (JLabel) super.getTableCellRendererComponent(table,
                                                                                    strDate,
                                                                                    isSelected,
                                                                                    hasFocus,
                                                                                    row,
                                                                                    column);
            rendererComponent.setOpaque(true);
            
                if (tableModel instanceof PropertyTableModel12)
                    ((PropertyTableModel12) tableModel).removeCellStyleAt(row);
                else {
                    if (isSelected) {
                        rendererComponent.setBackground(UIManager.getColor("Table.selectionBackground"));
                        rendererComponent.setForeground(UIManager.getColor("Table.selectionForeground"));
                    } else {
                        rendererComponent.setForeground(UIManager.getColor("Table.foreground"));
                        rendererComponent.setBackground(UIManager.getColor("Table.background"));
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
        
            return TimeZone.getDefault();
      
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

