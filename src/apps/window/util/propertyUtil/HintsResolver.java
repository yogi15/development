package apps.window.util.propertyUtil;

import java.util.List;

import javax.swing.text.JTextComponent;
 
import com.jidesoft.hints.ListDataIntelliHints;

public interface HintsResolver<T> extends ListResolver<T> {
    /** interpreted as a red background color while returned by {@link #count(String context)}. 
     * @see com.calypso.ui.component.table.celleditor.intellihints.SmartListIntellihints */
    public static int EMPTY_COUNT = 0;
    /** interpreted as a yellow background color while returned by {@link #resolve(String context)}. 
     * @see com.calypso.ui.component.table.celleditor.intellihints.SmartListIntellihints */
    public static int NO_COUNT = -1;
    
    /**
     * By default, {@link ListDataIntelliHints#compare(Object context, T o)} only applies "start with" comparison.
     * In some cases, "contains" is preferred; this is the purpose of this ListReslover extension. 
     * @param context
     * @param candidate - a possible choice
     * @return
     */
    boolean compare(Object context, T candidate);
    
    /**
     * <p>implements the logic or user interaction that enables make a choice among the provided options, given the context
     *
     * @param context the string
     * @param options possible choices
     * 
     * @return a choice among the options, determined either by logic or by user interaction
     */
    T resolve(String context, List<T> completionList);

    /**
     * Purpose is to delgate choice to a chooser Dialog 
     * @param field
     * @return
     */
    T delegateChoice(JTextComponent field);    
}

