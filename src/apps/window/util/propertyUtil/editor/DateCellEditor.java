package src.apps.window.util.propertyUtil.editor;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.JTextField;

import util.commonUTIL;
import util.common.CDate;
import util.common.CDateTime;
 
import apps.window.util.propertyUtil.Date.PeriodAdapter;
import apps.window.util.propertyUtil.Date.PeriodDateModel;
import apps.window.util.propertyUtil.Date.Provider.ProviderHoliday;
import apps.window.util.propertyUtil.Date.Provider.ProviderReferenceDate;
import apps.window.util.propertyUtil.Date.Provider.ProviderTimeZone;

import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.combobox.AbstractComboBox.ComboBoxTextField;

public class DateCellEditor extends com.jidesoft.grid.DateCellEditor {
    /**
     * 
     */
    private static final long serialVersionUID = -1091608530293195698L;
    private JTextField textField;
    private Class<?> dateFlavor;
    private ProviderTimeZone timeZoneProvider;
    private final PeriodDateModel dateModel;
    // The date model's date only format; used if switching between date and
    // date + time
    private final DateFormat modelDateOnlyFormat;

    /**
     * <p>builds a date cell editor with a reference date provider and a custom tenor adapter
     * @param holidaysProvider
     * @param referenceDateProvider
     * @param tenorCalculator
     */
    public DateCellEditor(ProviderHoliday holidaysProvider, ProviderReferenceDate referenceDateProvider,PeriodAdapter tenorCalculator) {
        super(new PeriodDateModel(referenceDateProvider, tenorCalculator, new ProviderTimeZone() {
            public TimeZone getTimeZone() {
                return TimeZone.getDefault();
            }
        }));
        dateModel = (PeriodDateModel)((DateComboBox) getComboBox()).getDateModel();
        modelDateOnlyFormat = dateModel.getDateFormat();

        init(holidaysProvider);
    }

    /**
     * builds a date cell editor with a reference date provider
     * @param holidaysProvider
     * @param referenceDateProvider
     */
    public DateCellEditor(ProviderHoliday holidaysProvider, ProviderReferenceDate referenceDateProvider) {
        super(new PeriodDateModel(referenceDateProvider, new ProviderTimeZone() {
            public TimeZone getTimeZone() {
                return TimeZone.getDefault();
            }
        }));
        dateModel = (PeriodDateModel)((DateComboBox) getComboBox()).getDateModel();
        modelDateOnlyFormat = dateModel.getDateFormat();

        init(holidaysProvider);
    }

    public DateCellEditor(ProviderHoliday holidaysProvider, ProviderReferenceDate referenceDateProvider, ProviderTimeZone timeZoneProvider) {
        super(new PeriodDateModel(referenceDateProvider, timeZoneProvider));
        dateModel = (PeriodDateModel)((DateComboBox) getComboBox()).getDateModel();
        modelDateOnlyFormat = dateModel.getDateFormat();
        this.timeZoneProvider = timeZoneProvider;

        init(holidaysProvider);
    }

    /**
     * builds a simple date cell editor
     * @param holidaysProvider
     */
    public DateCellEditor(ProviderHoliday holidaysProvider) {
        super(new PeriodDateModel(new ProviderTimeZone() {
            public TimeZone getTimeZone() {
                return TimeZone.getDefault();
            }
        }));
        dateModel = (PeriodDateModel)((DateComboBox) getComboBox()).getDateModel();
        modelDateOnlyFormat = dateModel.getDateFormat();

        init(holidaysProvider);
    }

    private void init(ProviderHoliday holidaysProvider) {
        dateFlavor = Date.class;
       /* AppUtil.addDateListener(textField, true, holidaysProvider == null ? new Vector<Object>() :  holidaysProvider.getHolidays());
        final FocusListener[] focusListeners = textField.getFocusListeners();
        for (final FocusListener focusListener : focusListeners)
            if (focusListener.getClass().getName().startsWith("com.calypso.apps.util.AppUtil")) {
                textField.removeFocusListener(focusListener);
            }*/
    }

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
        Calendar adjustedCalendar = Calendar.getInstance(getTimeZone());
        // and set year, month, day in that time zone
        adjustedCalendar.set(year, month, day, 23, 59, 0);
        adjustedCalendar.set(Calendar.MILLISECOND, 0);

        // now get universal time
        Date ret = adjustedCalendar.getTime();


        if (dateFlavor.equals(CDate.class)) { // if you asked for a jdate, make one
            final CDate date = CDate.valueOf(year, month+1, day);
            return date;
        }

        if (dateFlavor.equals(CDateTime.class)) {
            if (isTimeDisplayed()) {
                return new CDateTime(timeInMillis);
            }
            return new CDateTime(CDate.valueOf(day, month + 1, year),
                                 timeZoneProvider.getTimeZone());
        }
        
        if (isTimeDisplayed()) {
            return new Date(timeInMillis);
        }

        return ret;
    }

    private TimeZone getTimeZone() {
        return timeZoneProvider == null ? TimeZone.getDefault() : timeZoneProvider.getTimeZone();
    }

    @SuppressWarnings("serial")
    @Override
    protected DateComboBox createDateComboBox() {
        DateComboBox dateComboBox = new DateComboBox() {
            @Override
            protected JTextField createTextField() {
                textField = new ComboBoxTextField() {
                    @Override
                    public void selectAll() {
                        super.selectAll();
                    }
                };
                return textField;
            }

            @Override
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

    public void setDateFlavor(Class<?> dateFlavor) {
        if (!(dateFlavor.equals(Date.class) || dateFlavor.equals(CDate.class) || dateFlavor.equals(CDateTime.class)))
            throw new InvalidParameterException("dateFlavor must be one of Date.class, CDate.class or CDateTime.class");
        this.dateFlavor = dateFlavor;
    }

    @Override
    public void setTimeDisplayed(boolean timeDisplayed) {
        super.setTimeDisplayed(timeDisplayed);
        DateFormat format;
        if (timeDisplayed) {
            format = commonUTIL.getDatetimeFormatShortMedium();
        } else {
            format = modelDateOnlyFormat;
        }
        dateModel.setDateFormat(format);
    }

}
