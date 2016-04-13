package apps.window.util.propertyUtil;

import com.jidesoft.converter.PasswordConverter;
import com.jidesoft.grid.DefaultProperty;
import com.jidesoft.grid.PasswordCellEditor;

public class PropertyPassword extends DefaultProperty {
	

    public PropertyPassword(String name, String displayName, String category) {
        setType(String.class);
        setName(name);
        setDisplayName(displayName);
        setCategory(category);
        setConverterContext(PasswordConverter.CONTEXT);
        setEditorContext(PasswordCellEditor.CONTEXT);
    }
    
    public PropertyPassword(String name, String description) {
        this(name, null, null);
        setDescription(description);
        setConverterContext(PasswordConverter.CONTEXT);
        setEditorContext(PasswordCellEditor.CONTEXT);
    }   

    @Override
    public String getValue() {
        return (String) super.getValue();
    }
	
	

}
