
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;


/**
 * Quickly switch tabs in a <code>JTabbedPane</code> in a mnemonic-like way.
 *
 * @author Herkules
 * @see javax.swing.JTabbedPane
 * @see javax.swing.JPopupMenu
 */
public class TabSwitcher extends JPopupMenu
{
	
	/**
	 * Ctor.
	 */
	public TabSwitcher( final JTabbedPane tp, KeyStroke keystroke )
	{
		tp.registerKeyboardAction( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				configure( tp );
				Rectangle2D rect = tp.getBoundsAt( tp.getSelectedIndex() );
				TabSwitcher.this.show( tp, (int)rect.getX(), (int)rect.getMaxY() );
			}
		}, keystroke, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );
	}
	
	
	/**
	 * configure from a tabbedpane
	 */
	private void configure( final JTabbedPane tp )
	{
		removeAll();
		
		String[] names = getNames( tp );
		char[] mnemonics = computeMnemonics( names );
		
		for( int i = 0; i<names.length; i++)
		{
			final int index = i;
			AbstractAction a = new AbstractAction( names[i] )
			{
				public void actionPerformed(ActionEvent e)
				{
					tp.setSelectedIndex( index );
				}
			};
			a.putValue( Action.MNEMONIC_KEY, new Integer( mnemonics[i] ) );
			a.putValue( Action.SMALL_ICON, tp.getIconAt(i));
			
			JMenuItem item = new JMenuItem( a );
			add( item );
		}
	}
	
	
	/**
	 * retrieve name[] from a TabbedPane
	 */
	private final static String[] getNames( JTabbedPane tp )
	{
		String[] names = new String[ tp.getTabCount() ];
		for ( int i=0; i<names.length; i++ )
		{
			names[i]=tp.getTitleAt( i );
		}
		return names;
	}
	
	
	/**
	 * determine all mnemonics
	 */
	private final char[] computeMnemonics( String[] names )
	{
		char[] mnemonics = new char[ names.length ];
		for( int i = 0; i<names.length; i++ )
		{
			char candidate = (char)0;
			for( int c = 0; c<names[i].length(); c++) // c=character
			{
				candidate = Character.toLowerCase( names[i].charAt(c) );
				for( int p=0; p<i; p++ ) // p=previous
				{
					if( mnemonics[p] == candidate )
					{
						candidate = 0;
						break;
					}
				}
				if ( candidate != 0 )
				{
					break;
				}
			}
			mnemonics[i] = candidate;
		}
		return mnemonics;
	}
	
}
