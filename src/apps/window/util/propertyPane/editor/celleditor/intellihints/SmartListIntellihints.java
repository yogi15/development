package apps.window.util.propertyPane.editor.celleditor.intellihints;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Collections;

import javax.swing.JList;
import javax.swing.text.JTextComponent;

import apps.window.util.propertyUtil.ListResolver;

import com.jidesoft.hints.ListDataIntelliHints;

public class SmartListIntellihints<T> extends ListDataIntelliHints<T> {
    private int max;
    private ListResolver listProvider;
    private boolean autorefresh;

    public SmartListIntellihints(JTextComponent textField, int max, ListResolver<T> listProvider) {
        super(textField, Collections.<T>emptyList());
        this.max = max;
        this.listProvider = listProvider;
    }

    @Override
    public boolean updateHints(Object context) {
        int listSize = listProvider.count((String) context);
        if (listSize == -1) {
            getTextComponent().setBackground(Color.RED);
            getTextComponent().setForeground(Color.BLACK);
        }
        else if (listSize == -2) {
            getTextComponent().setBackground(Color.WHITE);
            getTextComponent().setForeground(Color.RED);
        }
        else if (listSize <= max) {
            getTextComponent().setBackground(Color.WHITE);
            getTextComponent().setForeground(Color.BLACK);
            setCompletionList(listProvider.fetch((String) context));
        } else {
            getTextComponent().setBackground(Color.YELLOW);
            getTextComponent().setForeground(Color.BLACK);
            setCompletionList(Collections.<T>emptyList());
        }

        return super.updateHints(context);
    }

    protected JList createList() {
        return new JList() {
            @Override
            public int getVisibleRowCount() {
                int size = getModel().getSize();
                return size < super.getVisibleRowCount() ? size : super.getVisibleRowCount();
            }

            @Override
            public Dimension getPreferredScrollableViewportSize() {
                if (getModel().getSize() == 0) {
                    return new Dimension(0, 0);
                }
                else {
                    final Dimension dimension = super.getPreferredScrollableViewportSize();
                    if (dimension.height < 100)
                        dimension.height = 100;
                    if (dimension.width < 80)
                        dimension.width = 80;
                    return dimension;
                }
            }
        };
    }
}
