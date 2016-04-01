package apps.window.util.propertyUtil;

import com.jidesoft.grid.DefaultProperty;

public class PropertyString extends DefaultProperty {
    public PropertyString(String name, String displayName, String category) {
        setType(String.class);
        setName(name);
        setDisplayName(displayName);
        setCategory(category);
    }
    
    public PropertyString(String name, String description) {
        this(name, null, null);
        setDescription(description);
    }   

    @Override
    public String getValue() {
        return (String) super.getValue();
    }
}
