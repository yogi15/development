package apps.window.util.propertyUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer; 

import util.ReferenceDataCache;
import util.commonUTIL;

import util.ComparatorFactory; 
import apps.window.util.propertyPane.combox.AutoCompletionComboBox;
import apps.window.util.propertyPane.editor.celleditor.intellihints.SmartListIntellihints;
   
import com.jidesoft.combobox.ListComboBox;
import com.jidesoft.converter.EnumConverter;
import com.jidesoft.grid.EnumCellEditor;
import com.jidesoft.grid.EnumCellRenderer;
 

public class PropertyEnum <T> extends DefaultPropertyExtended<T> {
	private Style style = Style.CLASSIC;
    private int maxHits = -1;
    private ListResolver listResolver = null;
    private Object[] objects;
    private String[] labels;

    private PropertyEnumCallbacks<T> enumPropertyCallbacks;

    /**
     * @param name
     * @param description
     * @param values {value;displayValue} pairs
     */
    public PropertyEnum(String name, String description, Map<T, String> values) {
        initialize(name, description, values);
    }
    /**
     * @param name
     * @param description
     * @param values {value;displayValue} pairs
     */
    public PropertyEnum(String name, String description, Map<T, String> values, PropertyEnumCallbacks<T> enumPropertyCallbacks) {
        this.enumPropertyCallbacks = enumPropertyCallbacks;
        initialize(name, description, values);
    }
         /**
     * <p>
     * PropertyEnum will offer a list of choice of all the Enum arg possible values.
     * Display value in the List of choices is the Enum.toSwtring() value, while this Property.getValue() returns the selected Enum.
     * <br>
     * The Property value is defaulted using the Enum value given as argument.
     * </p>
     * <p>
     * Example:
     *
     *
     * @param name
     * @param description
     * @param defaultValue an Enum value
     */
    public PropertyEnum(String name, String description, Enum defaultValue) {
        initialize(name, description, toMap(new ArrayList(EnumSet.allOf(defaultValue.getClass())), ComparatorFactory.getStringComparator()), (T)defaultValue);
        setValue(defaultValue);
    }

        /**
    * <p>
    * PropertyEnum will offer a list of choice of all the Enum arg possible values.
    * Display value in the List of choices is the Enum.toSwtring() value, while this Property.getValue() returns the selected Enum.
    * <br>
    * The Property value is defaulted using the Enum value given as argument.
    * </p>
    * <p>
    * Example:
    *
    *
    * @param name
    * @param description
    * @param defaultValue an Enum value
    */
    public PropertyEnum(String name, String description, Enum defaultValue, PropertyEnumCallbacks<T> enumPropertyCallbacks) {
        this.enumPropertyCallbacks = enumPropertyCallbacks;
        initialize(name, description, toMap(new ArrayList(EnumSet.allOf(defaultValue.getClass())), ComparatorFactory.getStringComparator()), (T)defaultValue);
        setValue(defaultValue);
    }

    /**
     * Build a Property choice from a domainName.
     * Property type (what retuns this.getValue()) is a String.
     * Default value is the first item in domain values list.
     * @param name
     * @param domainName
     */
    public PropertyEnum(String name, String domainName) {
        this(name, domainName, (PropertyEnumCallbacks)null);
    }

    /**
     * Build a Property choice from a domainName.
     * Property type (what retuns this.getValue()) is a String.
     * Default value is the first item in domain values list.
     * @param name
     * @param domainName
     */
    public PropertyEnum(String name, String startUpName, PropertyEnumCallbacks<T> enumPropertyCallbacks) {
        this.enumPropertyCallbacks = enumPropertyCallbacks;
        Map<T, String> domains = toMap(new Vector(ReferenceDataCache.getStarupData(startUpName)));
        String description = name + " List of Choices is Built From startUpName Name: " + startUpName;
        initialize(name, description, domains);
        if (!commonUTIL.isEmpty(domains)) setValue(domains.values().iterator().next());
    }

    /**
     * Build a Property choice from a domainName.
     * Property type (what retuns this.getValue()) is a String.
     * @param name
     * @param domainName
     * @param defaultValue   the default selected value
     */
    public PropertyEnum(String name,String domainName, String defaultValue) {
        this(name, domainName);
        setValue(defaultValue);
    }

    protected PropertyEnum() {
        // do nothing: placeholder for Override
    }

    public PropertyEnum(String name, int maxHits, ListResolver listResolver) {
        super();
        setName(name);
        this.style = Style.TEXT_AND_HINTS;
        this.maxHits = maxHits;
        this.listResolver = listResolver;
        setType(String.class);
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * @param name
     * @param description
     * @param values - assuming value.toString() is user friendly
     */
    public PropertyEnum(String name, String description, List<T> values) {
        this(name, description, values, null);
    }

    public PropertyEnum(String name, String description, List<T> values, Comparator<T> comparator) {
        initialize(name, description, toMap(values, comparator), null);
    }

    public PropertyEnum(String name, String description, List<T> values, Comparator<T> comparator, PropertyEnumCallbacks enumPropertyCallbacks) {
        this.enumPropertyCallbacks = enumPropertyCallbacks;
        initialize(name, description, toMap(values, comparator), null);
    }

    private Map<T, String> toMap(List<T> values){
    	return toMap(values, null);
    }

    private Map<T, String> toMap(List<T> values, Comparator<T> comparator) {
        if (commonUTIL.isEmpty(values))
            return Collections.emptyMap();

        Map<T, String> valuesMap = null;

        if(comparator == null){
        	valuesMap = values.get(0) instanceof Comparable ? new TreeMap<T, String>()
                						: new TreeMap<T, String>(ComparatorFactory.getStringComparator());
        }else{
        	valuesMap = new TreeMap<T, String>(comparator);
        }

        for(T value: values)
            valuesMap.put(value, String.valueOf(value));
        return valuesMap;
    }

    protected void initialize(String name, String description, Map<T, String> values) {
        initialize(name, description, values, null);
    }

    protected void initialize(String name, String description, Map<T, String> values,  T defaultValue) {
        if (commonUTIL.isEmpty(values)) {
            setup(name, description, String.class, defaultValue);
            return;
        }
        objects = new Object[values.size()];
        labels = new String[values.size()];
        int i = 0;
        for (T key: values.keySet()) {
            objects[i] = key;
            labels[i++] = values.get(key);
        }

        setup(name, description, objects[0].getClass(), defaultValue);

        if (enumPropertyCallbacks != null)
            enumPropertyCallbacks.initialized(this,values);
    }

    @Override
    public TableCellRenderer getTableCellRenderer() {
        if (labels == null || labels.length == 0)
            return new DefaultTableCellRenderer();
        else
            return new EnumCellRenderer(new EnumConverter("enuProp", objects, labels));
    }

    @SuppressWarnings("unchecked")
    @Override
    public CellEditor getCellEditor() {
        if (listResolver == null
                && (labels == null || labels.length == 0)) {
            return null;
        }
        if (style == Style.CLASSIC) {
            return createEnumCellEditor(new EnumConverter("EnumPRop",
                                                          this.objects,
                                                          this.labels));
        } else if (style == Style.AUTO_COMPLETION) {
            EnumCellEditor ret = createEnumCellEditor(new EnumConverter("EnumPRop",
                                                                        this.objects,
                                                                        this.labels));
             
             new AutoCompletionComboBox((ListComboBox)ret.getComboBox() );
            return ret;
           
        } else {
            if (listResolver == null) {
                return createDefaultTextAndHintsCellEditor(Arrays.asList(labels),
                                                           (List<T>) Arrays.asList(objects));
            } else {
                return createCustomTextAndHintsCellEditor(maxHits, listResolver);
            }
        }
    }

    public void reconfigure(T[] values) {
        Map<T, String> map = new HashMap<T, String>();
        if (values != null)
            for (T t : values)
                map.put(t, t.toString());
        reconfigure(map, values[0]);
    }

    public void reconfigure(List<T> values) {
        Map<T, String> map = toMap(values);
        reconfigure(map, values.size() == 0 ? null : values.get(0));
    }

    public void reconfigure(Map<T, String> values,  T defaultValue) {
        if (values.size() == 0)
            reconfigureEmpty();
        else
            reconfigureWithValues(values, defaultValue);
        if (enumPropertyCallbacks != null)
            enumPropertyCallbacks.reconfigured(this, values);
    }

    private void reconfigureEmpty() {
        objects = new Object[0];
        labels = new String[0];
        setValue(null);
    }

    private void reconfigureWithValues(Map<T, String> values, Object defaultValue) {
        if(getTreeTableModel()!=null && getTreeTableModel().getTableModelListeners()!=null)
            for (TableModelListener listener : getTreeTableModel().getTableModelListeners())
                if (listener instanceof JTable) {
                    JTable table = (JTable) listener;
                    TableCellEditor tableCellEditor = table.getCellEditor();
                    if (tableCellEditor != null)
                        tableCellEditor.stopCellEditing();
                }
        objects = new Object[values.size()];
        labels = new String[values.size()];
        int i = 0;
        for (T key: values.keySet()) {
            objects[i] = key;
            labels[i++] = values.get(key);
        }
        setValue(defaultValue);
    }

    public Object[] getObjects() {
        return objects;
    }

    protected EnumCellRenderer createEnumCellRenderer(EnumConverter converter) {
        return new EnumCellRenderer(converter);
    }

    protected EnumCellEditor createEnumCellEditor(EnumConverter converter) {
        return new EnumCellEditor(converter) ;
    }

    protected DefaultCellEditor createDefaultTextAndHintsCellEditor(final List<String> strings, final List<T> objects) {
       JTextField field = new JTextField();
        return new IntelliHintsCellEditor<T>(field, strings.size(), new ListResolver<T>() {
            public int count(String context) {
                return strings.size();
            }

            public List<T> fetch(String context) {
                return objects;
            }

            public T resolve(String context, String[] options) {
                int index = strings.indexOf(context);
                return index == -1 ? null : objects.get(index);
            }
        });
    }


    protected CellEditor createCustomTextAndHintsCellEditor(int maxHits, ListResolver<T> resolver) {
        JTextField field = new JTextField();
        return new IntelliHintsCellEditor<T>(field, maxHits, resolver);
    } 


    /**
     * specifies a cell ditor style
     */
    public enum Style {
        /**
         * results in classic jide enum cell editor
         */
        CLASSIC,
        /**
         * results in a cell editor implemented as a text field with intelihints on it
         */
        TEXT_AND_HINTS,
        /**
         * Normal combo box dropdown editor with auto completion
         */
        AUTO_COMPLETION;
    }

      private static class IntelliHintsCellEditor<T> extends DefaultCellEditor {
        SmartListIntellihints<T> listIntellihints;
        ListResolver<T> listResolver;

        public IntelliHintsCellEditor(JTextField field, int maxHintCount, ListResolver<T> listResolver) {
            super(field);
            this.listResolver = listResolver;
            listIntellihints = new SmartListIntellihints<T>(field, maxHintCount, listResolver);
        }

        @Override
        public Object getCellEditorValue() {
            String label = (String)super.getCellEditorValue();
            if (listResolver instanceof HintsResolver)
                return ((HintsResolver)listResolver).resolve(label, listIntellihints.getCompletionList());
            // FIXME: there is a ClassCastException here if ListResolver is not typed with String
            return listResolver.resolve(label, listIntellihints.getCompletionList().toArray(new String[0])); 
        }
    }

    public void setListResolver(ListResolver listResolver) {
        this.listResolver = listResolver;
    }

    public int getMaxHits() {
        return maxHits;
    }
    
}