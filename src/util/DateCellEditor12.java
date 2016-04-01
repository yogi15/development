package util;




import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JTextField;

import com.jidesoft.combobox.AbstractComboBox.ComboBoxTextField;
import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.combobox.DateExComboBox;
import com.jidesoft.combobox.DefaultDateModel;
import com.jidesoft.combobox.ExComboBox;



public class DateCellEditor12 extends com.jidesoft.grid.DateCellEditor {
	private ComboBoxTextField textField =null;
	   private DateFormat modelDateOnlyFormat = null;
	   DefaultDateModel model= null;
	
	   @Override
	    public Object getCellEditorValue() {
	        Object value = null;
	        try {
	            value = super.getCellEditorValue();
	        } catch (Exception e1) {
	            // exceptions occur in JIDE code when the date is not recognized by the date format
	            //return null rather than throwing an exception
	            value = null;
	        }
	        Calendar cal;
	        if (value == null)
	            return null;
	            //cal = Calendar.getInstance(timeZoneProvider.getTimeZone());
	        else if (value instanceof Calendar)
	            cal = (Calendar) value;
	        else {
	            cal = Calendar.getInstance();
	            cal.setTime((Date) value);
	        }
	        
	        long timeInMillis = cal.getTimeInMillis();

	        // get year, month, day, as it was typed by the user
	        int day = cal.get(Calendar.DAY_OF_MONTH);
	        int month = cal.get(Calendar.MONTH);
	        int year = cal.get(Calendar.YEAR);

	        // create a calendar with the configured time zone 
	        Calendar adjustedCalendar = Calendar.getInstance( TimeZone.getDefault());
	        // and set year, month, day in that time zone
	        adjustedCalendar.set(year, month, day, 23, 59, 0);
	        adjustedCalendar.set(Calendar.MILLISECOND, 0);

	        // now get universal time
	        Date ret = adjustedCalendar.getTime();


	        
	        if (isTimeDisplayed()) {
	            return new Date(timeInMillis);
	        }

	        return ret;
	    }
   public DateCellEditor12() {
	   super( new DefaultDateModel() {
		   //public TimeZone getTimeZone() {
         //      return TimeZone.getDefault();
           //}
		   
	   });
	   model =  (DefaultDateModel) ((DateExComboBox) getComboBox()).getDateModel();
	   System.out.println("pp"+model.getDateFormat());
       modelDateOnlyFormat = model.getDateFormat();
   }
   @Override
   public void setTimeDisplayed(boolean timeDisplayed) {
       super.setTimeDisplayed(timeDisplayed);
      
       
       model.setDateFormat(new SimpleDateFormat("hh:mm:ss"));
   }
   @Override
   protected DateExComboBox createDateComboBox() {
	   System.out.println("pp");
	   DateExComboBox dateComboBox = new DateExComboBox() {
           protected JTextField createTextField() {
               textField = new ComboBoxTextField() {
                   @Override
                   public void selectAll() {
                       super.selectAll();
                   }
               };
               return textField;
           }

           public void focusGained(FocusEvent e) {
               if (e.getSource() == getEditor().getEditorComponent()) {
                   return;
               }

               if (isEditable() && getEditor() != null && getEditor().getEditorComponent() != null) {
                   getEditor().getEditorComponent().repaint();
                   getEditor().getEditorComponent().requestFocus();
                   //getEditor().selectAll();
               }
           }


       };
       dateComboBox.setTimeDisplayed(isTimeDisplayed());
       return dateComboBox;
   }
}
