package apps.window.util.propertyUtil;

import java.util.UUID;

import javax.swing.CellEditor;

import util.commonUTIL;
  
import com.jidesoft.converter.ConverterContext;
import com.jidesoft.converter.EnumConverter;
import com.jidesoft.converter.ObjectConverterManager;
import com.jidesoft.grid.BooleanCheckBoxCellEditor;
import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.grid.EnumCellEditor;

public class PropertyBoolean extends  PropertyExtended<Boolean> { private CellEditor _cellEditor = null; // if null, pickup CellEditor from registered CellEditors

public PropertyBoolean(String name, String displayName, String category) {
    this(name, displayName, category, BooleanStyle.CHECKBOX);
    setDefaultValueIfNull(false);
}

public PropertyBoolean(String name, String displayName, String category, String trueLanbel, String falseLabel) {
    setup(name, displayName, category);

    final EnumConverter enumConverter = new EnumConverter(UUID.randomUUID().toString(), Boolean.class, new Object[]{true, false}, new String[] {trueLanbel, falseLabel});
    setConverterContext(enumConverter.getContext());

    ObjectConverterManager.registerConverter(Boolean.class, enumConverter, enumConverter.getContext());
    _cellEditor = new EnumCellEditor(enumConverter);
    setDefaultValueIfNull(false);
}

public PropertyBoolean(String name, String displayName, String category, BooleanStyle style) {
    setup(name, displayName, category);
    setEditorContext(style.getEditorContext());
    setConverterContext(style.getConverterContext());
    setDefaultValueIfNull(false);
}


public static enum BooleanStyle {
    CHECKBOX(BooleanCheckBoxCellEditor.CONTEXT),
    TRUE_FALSE("True", "False"),
    YES_NO("Yes", "No")
    ;
    
    private final EditorContext _editorContext;
    private final ConverterContext _converterContext;
    
    BooleanStyle(EditorContext editorContext) {
        _editorContext = editorContext;
        _converterContext = null;
    }
    
    /* purpose is to register only once commonly used CellEditor and ObjectConverter */
    BooleanStyle(String trueLabel, String falseLabel) {
        final EnumConverter enumConverter = new EnumConverter(trueLabel + falseLabel + "EnumConverter", Boolean.class, new Object[]{true, false}, new String[] {trueLabel, falseLabel});
        _editorContext = new EditorContext(enumConverter.getName());
        ObjectConverterManager.registerConverter(Boolean.class, enumConverter, enumConverter.getContext());
        CellEditorManager.registerEditor(Boolean.class, new CellEditorFactory() {
            public CellEditor create() {
                return new EnumCellEditor(enumConverter);
            }
        }, _editorContext);
        _converterContext = enumConverter.getContext();
    }
    
    public EditorContext getEditorContext() {
        return _editorContext;
    }
    
    public ConverterContext getConverterContext() {
        return _converterContext;
    }        
}

private void setup(String name, String displayName, String category) {
    setDisplayName(displayName);
    setCategory(category);
    setup(name, (commonUTIL.isEmpty(displayName) ? name : displayName) + " true or false choice", Boolean.class, null);
}

/** 
 * @return either a specific CellEditor set to this property 
 * or inhertied lookup into CellEditorManager register is performed 
 */      
@Override
public CellEditor getCellEditor() {
    return _cellEditor != null ? _cellEditor : super.getCellEditor(); 
}

public void setValue(Boolean value) {
    super.setValue(value);
}
}
