package apps.window.util.propertyUtil;
import java.util.List;

public interface ListResolver<T> {
    /**
     * <p>count the number of potential choices given the provided context
     * @param context the string the user typed in the text field. should be used as key in the query that counts the number of potential choices
     * @return the number of potential matches
     */
    public int count(String context);

    /**
     * <p>returns the number of potential choices given the provided context
     * @param context the string the user typed in the text field. should be used as key in the query that returns potential choices
     * @return a list of potential choices that match the context
     */
    public List<T> fetch(String context);

    /**
     * <p>implements the logic or user interaction that enables make a choice among the provided options, given the context
     *
     * @param context the string
     * @param options possible choices
     * @return a choice among the options, determined either by logic or by user interaction
     */
    public T resolve(String context, String[] options);
}