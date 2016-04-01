/*
 * CombinedIcon.java
 */



import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

/**
 *
 * @author Herkules
 */
public class CombinedIcon implements Icon
{

    private final Icon mIcon1;
    private final Icon mIcon2;

    private final static int SPACE = 2;
	
	/**
	 * Creates a new instance of CombinedIcon.
	 */
	public CombinedIcon( Icon icon1, Icon icon2 )
	{
		mIcon1 = icon1;
		mIcon2 = icon2;
	}
	
	public void paintIcon(Component c, Graphics g, int x, int y)
	{
		mIcon1.paintIcon(c,g,x,y);
		mIcon2.paintIcon(c,g,x+mIcon1.getIconWidth()+SPACE, y);
	}
	
	public int getIconWidth()
	{
		return mIcon1.getIconWidth() + SPACE + mIcon2.getIconWidth();
	}
	
	public int getIconHeight()
	{
		int h1 = mIcon1.getIconHeight();
		int h2 = mIcon2.getIconHeight();
		return h1>h2 ? h1 : h2;
	}
	
}
