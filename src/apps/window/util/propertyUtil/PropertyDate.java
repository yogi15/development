package apps.window.util.propertyUtil;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.table.TableCellRenderer;

import util.common.CDate;
import util.common.CDateTime;
 
import apps.window.util.propertyUtil.Date.DateCellRenderer;
import apps.window.util.propertyUtil.Date.PeriodAdapter;
import apps.window.util.propertyUtil.Date.Provider.ProviderHoliday;
import apps.window.util.propertyUtil.Date.Provider.ProviderReferenceDate;
import apps.window.util.propertyUtil.Date.Provider.ProviderTimeZone;
import apps.window.util.propertyUtil.editor.DateCellEditor;

import com.jidesoft.grid.DefaultProperty;
import com.jidesoft.grid.Property;

public class PropertyDate  extends DefaultProperty {

    private static ProviderTimeZone DEFAULT_TIME_ZONE_PROVIDER = new ProviderTimeZone() {
        public TimeZone getTimeZone() {
            return TimeZone.getDefault();
        }
    };

    private ProviderHoliday holidaysProvider;
    private ProviderReferenceDate referenceDateProvider;
    private PropertyTimeZone timeZoneProperty;
    private PeriodAdapter tenorCalculator;
    private boolean isTimeDisplayed;

    public PropertyDate(String name, String displayName, String category, ProviderHoliday holidaysProvider) {
        this(name, displayName, category, holidaysProvider, (ProviderReferenceDate)null);
    }

    public PropertyDate(String name, String displayName, String category, ProviderHoliday holidaysProvider, PropertyTimeZone timeZoneProperty) {
        this(name, displayName, category, holidaysProvider, (ProviderReferenceDate)null);
        installTimeZoneProperty(timeZoneProperty);
    }

    private void installTimeZoneProperty(PropertyTimeZone timeZoneProperty) {
        this.timeZoneProperty = timeZoneProperty;
        timeZoneProperty.addPropertyChangeListener(Property.PROPERTY_VALUE, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                TimeZone oldTZ = TimeZone.getTimeZone((String) evt.getOldValue());
                TimeZone newTZ = TimeZone.getTimeZone((String) evt.getNewValue());
                switchTimeZones(oldTZ, newTZ);
            }
        });
    }

    public PropertyDate(String name, String displayName, String category, ProviderHoliday holidaysProvider, final PropertyDate refDateProperty) {
        this(name, displayName, category, holidaysProvider, new ProviderReferenceDate() {
            public Date getReferenceDate() {
                final Object value = refDateProperty.getValue();
                return value instanceof Date ? (Date) value : ((Calendar) value).getTime();
            }
        });
    }

    public PropertyDate(String name, String displayName, String category, ProviderHoliday holidaysProvider, final PropertyDate refDateProperty, PropertyTimeZone timeZoneProperty) {
        this(name, displayName, category, holidaysProvider, new ProviderReferenceDate() {
            public Date getReferenceDate() {
                final Object value = refDateProperty.getValue();
                return value instanceof Date ? (Date) value : ((Calendar) value).getTime();
            }
        }, timeZoneProperty);
    }

    public PropertyDate(String name, String displayName, String category, ProviderHoliday holidaysProvider, ProviderReferenceDate referenceDateProvider, PropertyTimeZone timeZoneProperty) {
        this.holidaysProvider = holidaysProvider;
        installTimeZoneProperty(timeZoneProperty);
        this.referenceDateProvider = referenceDateProvider == null ? new ProviderReferenceDate() {
            public Date getReferenceDate() {
                return new Date();
            }
        } : referenceDateProvider;

        setType(Date.class);
        setName(name);
        setDisplayName(displayName);
        setCategory(category);
    }

    public PropertyDate(String name, String displayName, String category, ProviderHoliday holidaysProvider, ProviderReferenceDate referenceDateProvider) {
      //  super();
    	this.holidaysProvider = holidaysProvider;
        this.referenceDateProvider = referenceDateProvider == null ? new ProviderReferenceDate() {
            public Date getReferenceDate() {
                return new Date();
            }
        } : referenceDateProvider;

        setType(Date.class);
        setName(name);
        setDisplayName(displayName);
        setCategory(category);
    }

    @Override
    public Object getValue() {
        final Object value = super.getValue();
        if (value instanceof Calendar)
            return ((Calendar) value).getTime();
        return value;
    }

    public CDate getValueAsJDate() {
        CDate value = null;
        if (getValue() instanceof Date)
            value = CDate.valueOf((Date) getValue());
        return value;
    }

    public CDateTime getValueAsJDatetime() {
        Object obj = getValue();
        if (obj instanceof CDateTime) {
            return (CDateTime)obj;
        }
        if (obj instanceof Date) {
            return new CDateTime((Date)obj);
        }
        if (obj instanceof CDate) {
            return new CDateTime((CDate)obj);
        }
        return null;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof CDate) {
            super.setValue(((CDate)value).getDate(TimeZone.getDefault()));
        }
        else if (value instanceof Calendar) {
            super.setValue(((Calendar) value).getTime());
        }
        else if (value instanceof CDateTime) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(((CDateTime) value).getTime());
            super.setValue(cal.getTime());
        }else if (value != null && !(value instanceof Date)) {
            throw new IllegalStateException("On DateProperty you can only set values that are CDate, Date or Calendar. You are trying to set a: " + value.getClass().getName() + " = " + value.toString());
        }
        else
            super.setValue(value);
    }

    /**
     * <p>shifts the current local time from the first TZ to the second. This means that is if the property was set to
     * d/m/y h:m in the initial time zone, this method will make the properties value to be d/m/y h:m in the second time zone    
     *
     * <p>you should use this method in screens where you enter dates which refer to a timezone property. When you change the time zone you should call this method all all date properties in the screen.
     *
     * <p>afther calling this method the time zone provider - if any - should return the new time zone
     *
     * @param initialTimeZone the initial time zone
     * @param newTimeZone the new time zone
     */
    private void switchTimeZones(TimeZone initialTimeZone, TimeZone newTimeZone) {
        final Object value = getValue();
        
        if (value == null)
            return;
        Date crtVal = value instanceof Date ? (Date) value : ((Calendar) value).getTime();

        Calendar initialCal = Calendar.getInstance(initialTimeZone);
        initialCal.setTime(crtVal);

        Calendar finalCalendar = Calendar.getInstance(newTimeZone);
        finalCalendar.set(initialCal.get(Calendar.YEAR), initialCal.get(Calendar.MONTH), initialCal.get(Calendar.DAY_OF_MONTH), initialCal.get(Calendar.HOUR_OF_DAY), initialCal.get(Calendar.MINUTE), initialCal.get(Calendar.SECOND));
        finalCalendar.set(Calendar.MILLISECOND, initialCal.get(Calendar.MILLISECOND));

        setValue(finalCalendar.getTime());
    }

    @Override
    public DateCellEditor getCellEditor() {
        DateCellEditor editor;
        if (tenorCalculator != null) {
            editor = new DateCellEditor(holidaysProvider,
                                        referenceDateProvider,
                                        tenorCalculator);
        } else {
            editor = new DateCellEditor(holidaysProvider,
                                        referenceDateProvider,
                                        getTimeZoneProvider());
        }
        editor.setTimeDisplayed(isTimeDisplayed);
        return editor;
    }

    
    @Override
    public TableCellRenderer getTableCellRenderer() {
        DateCellRenderer renderer = new DateCellRenderer(holidaysProvider,
                                                         getTimeZoneProvider());
        renderer.setTimeDisplayed(isTimeDisplayed);
        return renderer;
    }

    public ProviderHoliday getHolidaysProvider() {
        return holidaysProvider;
    }

    private ProviderTimeZone getTimeZoneProvider() {
        if (timeZoneProperty == null)
            return DEFAULT_TIME_ZONE_PROVIDER;
        else
            return new ProviderTimeZone() {
                public TimeZone getTimeZone() {
                    return timeZoneProperty.getValueAsTimeZone();
                }
            };
    }
        
    public void setTenorCalculator(PeriodAdapter tenorCalculator) {
        this.tenorCalculator = tenorCalculator;
    }
    
    /**
     * Returns whether time should be displayed for the property
     * 
     * @return boolean value
     */
    public boolean isTimeDisplayed() {
        return isTimeDisplayed;
    }

    /**
     * Sets whether time should be displayed for the property and its editors
     * 
     * @param b
     *            the new value
     */
    public void setTimeDisplayed(boolean b) {
        this.isTimeDisplayed = b;
    }


}
