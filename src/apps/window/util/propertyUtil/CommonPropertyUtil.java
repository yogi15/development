package apps.window.util.propertyUtil;
 

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method; 
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator; 
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.CellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;
import javax.swing.text.JTextComponent;

import util.ReferenceDataCache; 
import util.commonUTIL; 

import beans.AttributeContainer; 
 
import com.jidesoft.combobox.ListComboBox;
import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.PropertyTableModel;

import constants.CommonConstants;
 
 
 
import apps.window.util.property.CurrencyDefaultProperty;
import apps.window.util.propertyPane.editor.AutoCompleteCellEditor;
import apps.window.util.propertyTable.PropertyTableBuilder;
import apps.window.util.propertyUtil.PropertyBoolean.BooleanStyle;
import apps.window.util.propertyUtil.editor.PositiveFigureProcessor;
import apps.window.util.propertyUtil.editor.ValueReferenceProvider;

public class CommonPropertyUtil {
	 private static final String  PROP_NEXT_COMPONENT = "nextTable";
	    private static final String  PROP_PREV_COMPONENT = "prevTable";
	    private static final String TAB_ACTION_KEY_SHIFT = "shift-tab";
	    private static final String TAB_ACTION_KEY = "tab";
	static public  Property createAutoCompleteComboBox(
			String name, String description, final Collection values) {
		
		CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
			public CellEditor create() {
				return new AutoCompleteCellEditor(null,values.toArray());
			}
		}, new EditorContext(name));
		 Property property = new CurrencyDefaultProperty(name, description, String.class);
		property.setEditorContext(new EditorContext(name));
		
		
		return property;
	}
	
	
	
	private static String [] getPropertyValueVecfromStartUpData(
			String domainName) {
		// TODO Auto-generated method stub
		Vector<String> currencyData=	ReferenceDataCache.getStarupData(CommonConstants.CURRENCY);
		String []  curr = commonUTIL.convertVectortoSringArray(currencyData);
		return curr;
	}
	
	 public static PropertyLegalEntity createLegalEntityProperty(String name, String displayName, String category, String role) {
	              PropertyLegalEntity leProp = new PropertyLegalEntity(name, category, String.class, "");
	              return leProp;
	 }
	 public static PropertyBook createBookProperty(String name, String displayName, String category ) {
         PropertyBook bookProp = new PropertyBook(name, category, String.class );
         return bookProp;
}
	 public static PropertyBook createFolderProperty(String name, String displayName, String category ) {
         PropertyBook bookProp = new PropertyBook(name, category, String.class );
         return bookProp;
}

	 /**
     * <p>creates a number property that displays its value with the specified precision
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param precision - the number of decimals to be used to display the value
     * @return a number property
     */
    public static PropertyNumber createNumberProperty(String name, String displayName, String category, int precision) {
        return new PropertyNumber(name, displayName, category, precision);
    }

    
    public static PropertyPassword createPassWordProperty(String name, String displayName, String category) {
    	 return new PropertyPassword(name, displayName, category);
    }
    /**
     * <p>creates a number property that displays its value with the specified precision. negative numbers are not accepted.
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param precision - the number of decimals to be used to display the value
     * @return a number property
     */
    public static PropertyNumber createPositiveNumberProperty(String name, String displayName, String category, int precision) {
        final PropertyNumber numberProperty = new PropertyNumber(name, displayName, category, precision);
        numberProperty.setPropertyValueProcessor(new PositiveFigureProcessor());
        return numberProperty;
    }

    /**
     * <p>creates a number property that displays its value with the specified precision. When the users enters a number followed by "%",
     * the property will take a value calculated by applying the specified percent to the value obtained from the {@link   .ui.component.table.celleditor.numbercelleditor.ReferenceValueProvider}
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param precision - the number of decimals to be used to display the value
     * @param referenceValueProvider - provides a reference value
     * @return a number property
     */
    public static PropertyNumber createNumberProperty(String name, String displayName, String category, int precision, ValueReferenceProvider referenceValueProvider) {
        return new PropertyNumber(name, displayName, category, precision, referenceValueProvider);
    }

    /**
     * <p>creates a number property that displays its value with the specified precision. When the users enters a number followed by "%",
     * the property will take a value calculated by applying the specified percent to the value obtained from the specified {@link   .ui.property.NumberProperty}
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param precision - the number of decimals to be used to display the value
     * @param referenceValueProvider - provides a reference value
     * @return a number property
     */
    public static PropertyNumber createNumberProperty(String name, String displayName, String category, int precision, final PropertyNumeric referenceValueProvider) {
        return new PropertyNumber(name, displayName, category, precision, new ValueReferenceProvider() {
            public double getReferenceValue() {
                return referenceValueProvider.getValueAsDouble();
            }
        });
    }
    
    
    
    /**
     * <p>creates a multiple selection list property with a boolean flag that can be used to for example to specify selected values should be included or excluded.
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param domain the list of value among which the user can choose
     * @return an instance of an ExtendedMultipleSelectionListProperty
     */
    public static <T>  PropertyListMultipleSelection createMultipleSelectionListProperty(String name, String displayName, String category, List<T> domain) {
        return new  PropertyListMultipleSelection(name, displayName, category, domain);
    }

    /**
     * <p>creates a multiple selection list property with a boolean flag that can be used to for example to specify selected values should be included or excluded.
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param domain the list of value among which the user can choose
     * @return an instance of an ExtendedMultipleSelectionListProperty
     */
    public static <T>  PropertyListMultipleSelection createMultipleSelectionListProperty(String name, String displayName, String category, T... domain) {
        return new  PropertyListMultipleSelection(name, displayName, category, domain);
    }

    
    
   /* 
    * @param name - the name of the property - identifies the property from a programatic perspective
    * @param displayName - the name that will be used to visually identify the property on screen
    * @param category - the category in which the property belongs
    * @param action - the action that will be used to configure the property
    * @return
    */
   public static PropertyAction makeActionProperty(String name, String displayName, String category, Action action) {
	   PropertyAction actionProperty = new PropertyAction(action);
       actionProperty.setName(name);
       actionProperty.setDisplayName(displayName);
       actionProperty.setCategory(category);
       return actionProperty;
   }
	/**
     * creates a string property
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @return
     */
    public static PropertyString createStringProperty(String name, String displayName, String category) {
        return new PropertyString(name, displayName, category);
    }
	/**
     * <p>creates a boolean property that uses a check box editor / renderer
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @return a boolean property
     */
    public static PropertyBoolean createBooleanProperty(String name, String displayName, String category) {
        return createBooleanProperty(name, displayName, category, BooleanStyle.CHECKBOX);
    }
    
    /**
     * <p>creates a boolean property that uses a specified editor / renderer
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param style includes: { CHECKBOX, TRUE_FALSE, YES_NO } {@link BooleanStyle}
     * @return a boolean property
     */
    public static PropertyBoolean createBooleanProperty(String name, String displayName, String category, BooleanStyle style) {
    	PropertyBoolean booleanProperty = new PropertyBoolean(name, displayName, category, style);
        booleanProperty.setDisplayName(displayName);
        return booleanProperty;
    }    
    /**
     * Creates an integer property that uses a default spinner model
     * 
     * @param name
     *            - the name of the property - identifies the property from a
     *            programatic perspective
     * @param displayName
     *            - the name that will be used to visually identify the property
     *            on screen
     * @param category
     *            - the category in which the property belongs
     * @return a new property, never null
     */
    public static PropertyInteger createIntegerProperty(String name,
                                                      String displayName,
                                                      String category) {
    	PropertyInteger prop = new PropertyInteger();
        prop.setName(name);
        prop.setDisplayName(displayName);
        prop.setCategory(category);
        return prop;
    }

    /**
     * Creates an integer property with a specific default value, minimum value,
     * maximum value, and step.
     * 
     * @param name
     *            - the name of the property - identifies the property from a
     *            programatic perspective
     * @param displayName
     *            - the name that will be used to visually identify the property
     *            on screen
     * @param category
     *            - the category in which the property belongs
     * @param startValue
     *            the initial default value when editing
     * @param minimum
     *            the minimum value
     * @param maximum
     *            the maximum value
     * @param step
     *            the size for incrementing or decrementing the property
     * @return a new property, never null
     */
    public static PropertyInteger createIntegerProperty(String name,
                                                      String displayName,
                                                      String category,
                                                      int startValue,
                                                      int minimum,
                                                      int maximum,
                                                      int step) {
    	PropertyInteger prop = new PropertyInteger(startValue,
                                                   minimum,
                                                   maximum,
                                                   step);
        prop.setName(name);
        prop.setDisplayName(displayName);
        prop.setCategory(category);
        return prop;
    }
    
    /**
     * <p>creates an enum property that takes its domain values by invoking a static getDomain() method on the specified class
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param domainName the name of the cosomo domain in from which possible values will be fetched
     * @return an enum property
     */
    public static <T> PropertyEnum<T> createStartUPDataProperty(String name, String displayName, String category, String domainName) {
    	PropertyEnum<T> enumProperty = new PropertyEnum<T>(name, domainName);
        enumProperty.setStyle(PropertyEnum.Style.CLASSIC);
        enumProperty.setDisplayName(displayName);
        enumProperty.setCategory(category);
        return enumProperty;
    }
    public static <T> PropertyEnum<T> createAttributeProperty(String name, String displayName, String category, String domainName) {
    	PropertyEnum<T> enumProperty = new PropertyEnum<T>(name, domainName);
        enumProperty.setStyle(PropertyEnum.Style.CLASSIC);
        enumProperty.setDisplayName(displayName);
        enumProperty.setCategory(category);
        return enumProperty;
    }

    /**
     * <p>creates an enum property that takes its domain values by invoking a static getDomain() method on the specified class
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param domainName the name of the cosomo domain in from which possible values will be fetched
     * @param style cell editor style, see values in {@link com.cosomo.ui.property.EnumProperty.Style}
     * @return an enum property
     */
    public static <T> PropertyEnum<T> createStartUPDataProperty(String name, String displayName, String category, String domainName, PropertyEnum.Style style) {
    	PropertyEnum<T> enumProperty = new PropertyEnum<T>(name, domainName);
        enumProperty.setStyle(style);
        enumProperty.setDisplayName(displayName);
        enumProperty.setCategory(category);
        return enumProperty;
    }

    /**
     * <p>creates an enum property that takes its domain values by invoking a static getDomain() method on the specified class
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param domainClass the class on which the domain values will be retrieved
     * @return an enum property
     */
    public static <T> PropertyEnum<T> createStartUPDataProperty(String name, String displayName, String category, Class domainClass) {
        return createStartUPDataProperty(name, displayName, category, domainClass, PropertyEnum.Style.CLASSIC);
    }
    /**
     * <p>creates an enum property that takes its domain values by invoking a static getDomain() method on the specified class
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param Vector of startup data  values will be retrieved
     * @return an enum property
     */
    public static <T> PropertyEnum<T> createStartUPDataProperty(String name, String displayName, String category, Vector startUpData) {
        return createStartUPDataProperty(name, displayName, category, startUpData, PropertyEnum.Style.CLASSIC);
    }
    /**
     * <p>creates an enum property that takes its method name starting with get on an object on the specified class
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param Vector of startup data  values will be retrieved
     * @return an enum property
     */
    public static <T> PropertyEnum<T> createObjectMethodNameProperty(String name, String displayName, String category, Vector startUpData) {
        return createObjectMethodNameProperty(name, displayName, category, startUpData, PropertyEnum.Style.CLASSIC);
    }
    /**
     * <p>creates an enum property that takes its beans name starting with get on an object  
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param Vector of startup data  values will be retrieved
     * @return an enum property
     */
    public static <T> PropertyEnum<T> createBeanNameProperty(String name, String displayName, String category, Vector startUpData) {
        return createObjectMethodNameProperty(name, displayName, category, startUpData, PropertyEnum.Style.CLASSIC);
    }
    /**
     * <p>creates an enum property that takes its domain values by invoking a static getDomain() method on the specified class
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param domainClass the class on which the domain values will be retrieved
     * @param style cell editor style, see values in {@link com.cosomo.ui.property.EnumProperty.Style}
     * @return an enum property
     */
    public static <T> PropertyEnum<T> createStartUPDataProperty(String name, String displayName, String category, Class domainClass, PropertyEnum.Style style) {
        final Method method;
        try {
            method = domainClass.getMethod("getDomain");
            if (!List.class.isAssignableFrom(method.getReturnType()))
                throw new IllegalStateException(domainClass.getName() + ".getDomain() does not return a List.");
            Vector values = (Vector) method.invoke(null);
            PropertyEnum<T> enumProperty = new PropertyEnum<T>(name, "", values);
            enumProperty.setStyle(style);
            enumProperty.setDisplayName(displayName);
            enumProperty.setCategory(category);
            return enumProperty;
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(domainClass.getName() + ".getDomain() does not return a List.", e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException("An error occured while invoking " + domainClass.getName() + ".getDomain().", e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(domainClass.getName() + ".getDomain() invocation failed.", e);
        }
    }
    /**
     * <p>creates an enum property that takes its domain values by invoking a static getDomain() method on the specified class
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param Vector from  startup data values will be retrieved
     * @param style cell editor style, see values in {@link cosmos.property.EnumProperty.Style}
     * @return an enum property
     */
    public static <T> PropertyEnum<T> createStartUPDataProperty(String name, String displayName, String category, Vector startUpValues, PropertyEnum.Style style) {
         
            Vector values = startUpValues;
            PropertyEnum<T> enumProperty = new PropertyEnum<T>(name, "", values);
            enumProperty.setStyle(style);
            enumProperty.setDisplayName(displayName);
            enumProperty.setCategory(category);
            return enumProperty;
        
    }
    public static <T> PropertyEnum<T> createTimeZoneProperty(String name, String displayName, String category , PropertyEnum.Style style) {
        
        Vector values = getPropertyValueTimeZone();
        PropertyEnum<T> enumProperty = new PropertyEnum<T>(name, "", values);
        enumProperty.setStyle(style);
        enumProperty.setDisplayName(displayName);
        enumProperty.setCategory(category);
        return enumProperty;
    
}
    public static <T> PropertyEnum<T> createTimeZoneProperty(String name, String displayName, String category  ) {
   	 return createTimeZoneProperty(name,displayName,category, PropertyEnum.Style.AUTO_COMPLETION);
       
    
   } 
 public static <T> PropertyEnum<T> createFolderProperty(String name, String displayName, String category, Vector folderData, PropertyEnum.Style style) {
        
         
        PropertyEnum<T> enumProperty = new PropertyEnum<T>(name, "", folderData);
        enumProperty.setStyle(style);
        enumProperty.setDisplayName(displayName);
        enumProperty.setCategory(category);
        return enumProperty;
    
}
 public static <T> PropertyEnum<T> createFolderProperty(String name, String displayName, String category, Vector folderData ) {
	 return createFolderProperty(name,displayName,category,folderData,PropertyEnum.Style.AUTO_COMPLETION);
    
 
}
    /**
     * <p>creates an enum property that takes its domain values by invoking a static getDomain() method on the specified class
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param Vector from  startup data values will be retrieved
     * @param style cell editor style, see values in {@link cosmos.property.EnumProperty.Style}
     * @return an enum property
     */
    public static <T> PropertyEnum<T> createObjectMethodNameProperty(String name, String displayName, String category, Vector startUpValues, PropertyEnum.Style style) {
         
            Vector values = startUpValues;
            PropertyEnum<T> enumProperty = new PropertyEnum<T>(name, "", values);
            enumProperty.setStyle(style);
            enumProperty.setDisplayName(displayName);
            enumProperty.setCategory(category);
            return enumProperty;
        
    }
    /**
     * <p>creates a property where users can choose among a finite set of values
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param values  values from which the user can choose
     * @return the enum type
     */
    public static <T> PropertyEnum<T> createEnumProperty(String name, String displayName, String category, List<T> values) {
        return createEnumProperty(name, displayName, category, values, PropertyEnum.Style.CLASSIC);
    }

    /**
     * <p>creates a property where users can choose among a finite set of values
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param values  values from which the user can choose
     * @return the enum type
     */
    public static <T> PropertyEnum<T> createEnumProperty(String name, String displayName, String category, List<T> values, PropertyEnumCallbacks<T> callbackHandler) {
        return createEnumProperty(name, displayName, category, values, PropertyEnum.Style.CLASSIC, callbackHandler);
    }
    
    public static PropertyTable createPropertyTable(List<Property> props) {
        PropertyTable propertyTable =createPropertyTable(props, new PropertyTableBuilder() {
            public PropertyTable createPropertyTable() {
                return new DynamicPropertyTable();
            }

			 
        }, false);
        return propertyTable;
    }

    /**
     * <p>creates a property where users can choose among a finite set of values
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param defaultValue - an Enum value, which is the default value of this possible Enum value: EnumSet.allOf(defaultValue.getClass()))
     * @return the enum type
     */
    public static <T> PropertyEnum<T> createEnumProperty(String name, String displayName, String category, Enum defaultValue) {
        PropertyEnum<T> enumProperty = new PropertyEnum<T>(name, "", defaultValue);
        enumProperty.setDisplayName(displayName);
        enumProperty.setCategory(category);
        return enumProperty;
    }

    /**
     * <p>creates a property where users can choose among a finite set of values
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param values  values from which the user can choose
     * @param style cell editor style, see values in {@link com.cosomo.ui.property.PropertyEnum.Style}
     * @return the enum type
     */
    public static <T> PropertyEnum<T> makeEnumProperty(String name, String displayName, String category, 
    												List<T> values, PropertyEnum.Style style, Comparator<T> comparator) {
        PropertyEnum<T> enumProperty = new PropertyEnum<T>(name, "", values, comparator);
        configureEnumProperty(displayName, category, style, enumProperty);
        return enumProperty;
    }
    

public static AttributeProperty createAttributeProperty(
			String attributeName, String attributeName2, String category,
			AttributeContainer attributebean) {
		// TODO Auto-generated method stub
	AttributeProperty attribute = new AttributeProperty(attributeName,category);
	attribute.showProduct(attributebean);
		return attribute;
	}

    /**
     * <p>creates a property where users can choose among a finite set of values
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param values  values from which the user can choose
     * @param style cell editor style, see values in {@link com.cosomo.ui.property.PropertyEnum.Style}
     * @return the enum type
     */
    public static <T> PropertyEnum<T> createEnumProperty(String name, String displayName, String category,
    												List<T> values, PropertyEnum.Style style, Comparator<T> comparator, PropertyEnumCallbacks<T> callbackHandler) {
        PropertyEnum<T> enumProperty = new PropertyEnum<T>(name, "", values, comparator, callbackHandler);
        configureEnumProperty(displayName, category, style, enumProperty);
        return enumProperty;
    }

    private static <T> void configureEnumProperty(String displayName, String category, PropertyEnum.Style style, PropertyEnum<T> enumProperty) {
        enumProperty.setStyle(style);
        enumProperty.setDisplayName(displayName);
        enumProperty.setCategory(category);
    }

    public static <T> PropertyEnum<T> createEnumProperty(String name, String displayName, String category, 
    													List<T> values, PropertyEnum.Style style) {
    	return createEnumProperty(name, displayName, category, values, style, (Comparator<T>)null);
    }
    /**
     * <p>creates a property where users can choose among a finite set of values
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param values  values from which the user can choose
     * @param style cell editor style, see values in {@link com.cosomo.ui.property.EnumProperty.Style}
     * @return the enum type
     */
    public static <T> PropertyEnum<T> createEnumProperty(String name, String displayName, String category, 
    												List<T> values, PropertyEnum.Style style, Comparator<T> comparator) {
    	PropertyEnum<T> enumProperty = new PropertyEnum<T>(name, "", values, comparator);
        configureEnumProperty(displayName, category, style, enumProperty);
        return enumProperty;
    }
    public static <T> PropertyEnum<T> createEnumProperty(String name, String displayName, String category,
    													List<T> values, PropertyEnum.Style style, PropertyEnumCallbacks<T> callbackHandler) {
    	return createEnumProperty(name, displayName, category, values, style, (Comparator<T>)null, callbackHandler);
    }

    /**
     * <p>creates a property where users can choose among a finite set of values
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param values  values from which the user can choose
     * @return the enum type
     */
    public static <T> PropertyEnum<T> createEnumProperty(String name, String displayName, String category, T... values) {
        return createEnumProperty(name, displayName, category, Arrays.asList(values));
    }

    public static <T> PropertyEnum<T> createEnumProperty(String name, String displayName, String category, int maxHits, ListResolver<T> listResolver) {
        PropertyEnum<T> enumProperty = new PropertyEnum<T>(name, maxHits, listResolver);
        enumProperty.setDisplayName(displayName);
        enumProperty.setCategory(category);
        return enumProperty;
    }

    /**
     * <p>creates a property where users can choose among a finite set of values
     *
     * @param name - the name of the property - identifies the property from a programatic perspective
     * @param displayName - the name that will be used to visually identify the property on screen
     * @param category - the category in which the property belongs
     * @param values  values from which the user can choose
     * @param style cell editor style, see values in {@link com.cosomo.ui.property.PropertyEnum.Style}
     * @return the enum type
     */
    public static <T> PropertyEnum<T> createEnumProperty(String name, String displayName, String category, PropertyEnum.Style style, T... values) {
        return createEnumProperty(name, displayName, category, Arrays.asList(values), style);
    }
    public static PropertyTable createPropertyTable(List<Property> props, PropertyTableBuilder builder, boolean showCategories) {
        final PropertyTable propertyTable = builder.createPropertyTable();

        propertyTable.setRowSelectionAllowed(true);
        propertyTable.setColumnSelectionAllowed(true);

        final PropertyTableModel model = new PropertyTableModel(props);
        if (showCategories)
            model.setOrder(PropertyTableModel.CATEGORIZED);
        model.setOrder(PropertyTableModel.UNSORTED);

        PropertyChangeListener listener = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                final int selectedRow = propertyTable.getSelectionModel().getMinSelectionIndex();
                final int selectedCol = propertyTable.getColumnModel().getSelectionModel().getMinSelectionIndex();
                model.refresh();
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        propertyTable.getSelectionModel().setSelectionInterval(selectedRow,  selectedRow);
                        propertyTable.getColumnModel().getSelectionModel().setSelectionInterval(selectedCol,  selectedCol);
                    }
                });
            }
        };
        for (Object o : props) {
            ((Property) o).addPropertyChangeListener(Property.PROPERTY_EDITABLE, listener);
            ((Property) o).addPropertyChangeListener(Property.PROPERTY_HIDDEN, listener); //
            ((Property) o).addPropertyChangeListener(Property.PROPERTY_DISPLAY_NAME, listener);
            ((Property) o).addPropertyChangeListener(Property.PROPERTY_VALUE, listener);
        }

        propertyTable.setModel(model);

        propertyTable.setPaintMarginBackground(false);
        propertyTable.setShowTreeLines(false);
        propertyTable.setShowsRootHandles(false);
        propertyTable.setSurrendersFocusOnKeystroke(true);
        propertyTable.putClientProperty("JTable.autoStartsEdit", Boolean.TRUE);

        ListSelectionListener propertyTableSelectionListener = new ListSelectionListener() {
            Timer timer = new Timer(170, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timer.stop();
                    int selectedRow = propertyTable.getSelectedRow();
                    if (selectedRow == -1)
                        return;
                    initiateComboBoxEditing(selectedRow, propertyTable);
                }
            });

            public void valueChanged(ListSelectionEvent e) {
                Exception exception = new Exception();
                exception.fillInStackTrace();
                StackTraceElement[] stackTraceElements = exception.getStackTrace();
                for (StackTraceElement stackTraceElement : stackTraceElements)
                    if (stackTraceElement.getClassName().equals(JComponent.class.getName()) && stackTraceElement.getMethodName().equals("processMouseEvent"))
                        return; // not interested in selection changes triggered by mouse events
                if (e.getValueIsAdjusting())
                    return;

                if (propertyTable.getSelectedColumn() == 0)
                    return;

                if (timer.isRunning())
                    timer.restart();
                else
                    timer.start();
            }
        };
        propertyTable.getSelectionModel().addListSelectionListener(propertyTableSelectionListener);
        propertyTable.getColumnModel().getSelectionModel().addListSelectionListener(propertyTableSelectionListener);


        Action tabAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                propertyTable.editingStopped(new ChangeEvent(this));
                final int initialSelectedRow = propertyTable.getSelectedRow();
                final int initialSelectedColumn = propertyTable.getSelectedColumn();
                if (initialSelectedColumn == -1 || initialSelectedRow == -1)
                    return;

                int selectedRow = initialSelectedRow;

                if (initialSelectedColumn == 1) {
                    final PropertyTableModel tableModel = propertyTable.getPropertyTableModel();
                    final int rowCount = tableModel.getRowCount();

                    selectedRow++;
                    while (selectedRow < rowCount && (tableModel.getPropertyAt(selectedRow).isCategoryRow() || !tableModel.getPropertyAt(selectedRow).isEditable()))
                        selectedRow ++;
                    if (selectedRow == rowCount) {
                        propertyTable.clearSelection();
                        jumpToNextComponent(propertyTable);
                        return;
                    }
                }

                propertyTable.setRowSelectionInterval(selectedRow, selectedRow);
                propertyTable.setColumnSelectionInterval(1, 1);
            }
        };
        propertyTable.getActionMap().put(TAB_ACTION_KEY, tabAction);
        propertyTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), TAB_ACTION_KEY);
        propertyTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), TAB_ACTION_KEY);

        Action shiftTabAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                propertyTable.editingStopped(new ChangeEvent(this));
                final int initialSelectedRow = propertyTable.getSelectedRow();
                final int initialSelectedColumn = propertyTable.getSelectedColumn();
                int selectedRow = initialSelectedRow;
                if (initialSelectedColumn == 1) {
                    final PropertyTableModel tableModel = propertyTable.getPropertyTableModel();
                    final int rowCount = tableModel.getRowCount();

                    selectedRow--;
                    while (selectedRow > -1 && (tableModel.getPropertyAt(selectedRow).isCategoryRow() || !tableModel.getPropertyAt(selectedRow).isEditable()))
                        selectedRow --;
                    if (selectedRow == -1) {
                        propertyTable.clearSelection();
                        jumpToPreviousComponent(propertyTable);
                        return;
                    }
                }

                propertyTable.setRowSelectionInterval(selectedRow, selectedRow);
                propertyTable.setColumnSelectionInterval(1, 1);
            }
        };
        propertyTable.getActionMap().put(TAB_ACTION_KEY_SHIFT, shiftTabAction);
        propertyTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_MASK), TAB_ACTION_KEY_SHIFT);

        return propertyTable;
    }
    private static void selectPrevProperty(PropertyTable propertyTable) {
        if (propertyTable.getModel().getRowCount() == 0)  {
            jumpToPreviousComponent(propertyTable);
        } else {
            int lastNdx = propertyTable.getRowCount() - 1;
            PropertyTableModel propertyTableModel = propertyTable.getPropertyTableModel();
            while (lastNdx > -1 && ( propertyTableModel.getPropertyAt(lastNdx).isCategoryRow() || !propertyTableModel.getPropertyAt(lastNdx).isEditable()))
                lastNdx --;
            if (lastNdx == -1)
                lastNdx = propertyTable.getRowCount() - 1;
            propertyTable.setRowSelectionInterval(lastNdx, lastNdx);
            propertyTable.setColumnSelectionInterval(1, 1);
            propertyTable.requestFocusInWindow();
        }
    }
    static void jumpToPreviousComponent(JComponent originalComponent) {
        JComponent prevComponent = (JComponent) (originalComponent.getClientProperty(PROP_PREV_COMPONENT));
        if (prevComponent == null) {
            if (originalComponent instanceof PropertyTable)
                selectPrevProperty((PropertyTable) originalComponent);
        } else {
            while (!canReceiveFocus(prevComponent) && prevComponent != originalComponent)
                prevComponent = (JComponent) prevComponent.getClientProperty(PROP_PREV_COMPONENT);
            if (prevComponent instanceof PropertyTable) {
                selectPrevProperty((PropertyTable) prevComponent);
            } else
                prevComponent.requestFocusInWindow();
        }
    }

    private static void jumpToNextComponent(JComponent originalComponent) {
        JComponent nextComponent = (JComponent) (originalComponent.getClientProperty(PROP_NEXT_COMPONENT));
        if (nextComponent == null) {
            if (originalComponent instanceof PropertyTable)
                selectNextProperty((PropertyTable) originalComponent);
        } else {
            while (!canReceiveFocus(nextComponent) && nextComponent != originalComponent)
                nextComponent = (JComponent) nextComponent.getClientProperty(PROP_NEXT_COMPONENT);
            if (nextComponent instanceof PropertyTable)
                selectNextProperty((PropertyTable) nextComponent);
            else
                nextComponent.requestFocusInWindow();
        }
    }
    static  boolean canReceiveFocus(JComponent component) {
        if (component == null)
            return false;
        
        if (!component.isVisible()) {
            return false;
        }
        if (component instanceof PropertyTable)
            return true;
        if (component instanceof JTextComponent) {
            JTextComponent textComponent = (JTextComponent) component;
            if (textComponent.isShowing() && textComponent.isVisible() && textComponent.isEditable() && textComponent.isEnabled())
                return true;
        }
        if (component instanceof JComboBox &&
            component.isEnabled()) {
            return true;
        }
        if (component instanceof AbstractButton &&
            component.isEnabled()) {
            return true;
        }
        return false;
    }
    private static void initiateComboBoxEditing(int selectedRow, PropertyTable propertyTable) {
        PropertyTableModel propertyTableModel = propertyTable.getPropertyTableModel();
        int actualRow = propertyTable.getActualRowAt(selectedRow);
        Property property = propertyTableModel.getPropertyAt(actualRow);

        TableCellEditor editor = property == null ? null : propertyTable.getCellEditor(selectedRow, 1);
        if (editor != null) {
            Component editorComponent = editor.getTableCellEditorComponent(propertyTable, propertyTable.getModel().getValueAt(selectedRow, 1), true, selectedRow, 1);
            if (editorComponent instanceof ListComboBox || editorComponent instanceof JComboBox || editorComponent instanceof JButton) {
                propertyTable.editCellAt(selectedRow, 1);
                if (propertyTable.getEditorComponent() != null)
                    propertyTable.getEditorComponent().requestFocus();
            }
        }
    }
    private static void selectNextProperty(PropertyTable propertyTable) {
        if (propertyTable.getModel().getRowCount() == 0) {
            jumpToNextComponent(propertyTable);
        } else {
            int firstNdx = 0;
            int rowCount = propertyTable.getRowCount();
            PropertyTableModel tableModel = propertyTable.getPropertyTableModel();
            while (firstNdx < rowCount && (tableModel.getPropertyAt(firstNdx).isCategoryRow() || !tableModel.getPropertyAt(firstNdx).isEditable()))
                firstNdx ++;
            if (firstNdx == rowCount)
                firstNdx = 0;
            propertyTable.setRowSelectionInterval(firstNdx, firstNdx);
            propertyTable.setColumnSelectionInterval(1, 1);
            propertyTable.requestFocusInWindow();
        }
    }
    
    public static Vector<String> getPropertyValueTimeZone( ) {
		Vector<String> propertyValuesVec = new Vector<String>();
		String tzs[] = TimeZone.getAvailableIDs();
	    for (int i = 0; i < tzs.length; i++)
	    	propertyValuesVec.add(tzs[i]);
	 //   SortShell.sort(propertyValuesVec);
	   
	    return propertyValuesVec;
	}
     
}
