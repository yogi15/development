package apps.window.util.propertyPane.combox;

import java.awt.Component;

import javax.swing.ComboBoxEditor;
import javax.swing.text.JTextComponent;

import com.jidesoft.combobox.AbstractComboBox; 
import com.jidesoft.combobox.ListComboBox;
import com.jidesoft.combobox.ListComboBoxSearchable;
import com.jidesoft.combobox.TableComboBox;
import com.jidesoft.combobox.TableComboBoxSearchable;
import com.jidesoft.combobox.TreeComboBox;
import com.jidesoft.combobox.TreeComboBoxSearchable;
import com.jidesoft.swing.AutoCompletion;
import com.jidesoft.swing.Searchable;

public class AutoCompletionComboBox extends AutoCompletion {

    public AutoCompletionComboBox(ListComboBox exComboBox) {
        super(getTextEditor(exComboBox), new ListComboBoxSearchable(exComboBox));
    }

    public AutoCompletionComboBox(TableComboBox comboBox) {
        super(getTextEditor(comboBox), new TableComboBoxSearchable(comboBox));
    }

    public AutoCompletionComboBox(TreeComboBox comboBox) {
        super(getTextEditor(comboBox), new TreeComboBoxSearchable(comboBox));
    }

    public AutoCompletionComboBox(AbstractComboBox comboBox,
            Searchable searchable) {
        super(getTextEditor(comboBox), searchable);
    }

     

	private static JTextComponent getTextEditor(AbstractComboBox comboBox) {
        if (!comboBox.isEditable()) {
            comboBox.setEditable(true);
        }
        ComboBoxEditor editor = comboBox.getEditor();
        Component editorComponent = editor.getEditorComponent();
        return (JTextComponent) editorComponent;
    }

}