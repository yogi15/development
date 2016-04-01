package apps.window.util.propertyUtil;

import java.util.UUID;

import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
 
import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.DefaultProperty;
import com.jidesoft.grid.EditorContext;

public class ValidatedTextProperty extends DefaultProperty {
    public ValidatedTextProperty(String name, String displayName, String category, DocumentListenerFactory documentListenerFactory) {
        setName(name);
        setDisplayName(displayName);
        setCategory(category);
        setType(String.class);
        registerEditor(documentListenerFactory);
    }

    protected void registerEditor(final DocumentListenerFactory documentListenerFactory) {
        final EditorContext editorContext = new EditorContext(getClass().getName() + "_" + UUID.randomUUID().toString());
        setEditorContext(editorContext);
        CellEditorManager.registerEditor(String.class, new CellEditorFactory() {
            public CellEditor create() {
                JTextField field = new JTextField();
                field.getDocument().addDocumentListener(documentListenerFactory.createDocumentListener(field));
                return new DefaultCellEditor(field);
            }

        }, editorContext);
    }

    public static interface DocumentListenerFactory {
        public DocumentListener createDocumentListener(JTextField textField);
    }
}