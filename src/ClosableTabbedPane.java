/*
 * ClosableTabbedPane.java
 */



import javax.swing.Icon;
import javax.swing.JTabbedPane;

/**
 *
 * @author Herkules
 */
public class ClosableTabbedPane extends JTabbedPane
{
	
	/**
     * Creates a new instance of ClosableTabbedPane.
     */
	public ClosableTabbedPane()
	{
	}
	
	public void setIconAt(int index, Icon icon)
	{
		CombinedIcon combi = new CombinedIcon( new TabCloseIcon(), icon );
		super.setIconAt( index, combi );
	}
}
