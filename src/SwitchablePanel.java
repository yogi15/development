/*
 * SwitchablePanel.java
 *
 * Created on 5. Mai 2004, 22:27
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author  Herkules
 */
public class SwitchablePanel extends JComponent
{
	private			JComponent			mSwitched;
	private final	SplitterButton		mSplitter	= new SplitterButton();
	private			boolean				mExpanded	= true;

	/**
	 * Display a special form of a button.
	 */
	private class SplitterButton extends JButton
	{
		private final	int			THICK			= 9;
		
		SplitterButton()
		{
			this.setFocusable( false );
			this.setPreferredSize( new Dimension( THICK, THICK ) );
			this.setBorder( null );
			this.setBackground( Color.GRAY );
		}
		
		protected void paintComponent( Graphics g )
		{
			Rectangle r = g.getClipBounds();
			g.setColor( getBackground() );
			g.clearRect( r.x, r.y, r.width, r.height );
			g.setColor( Color.BLACK );

			if ( r.width > r.height )
			{
				g.drawLine( r.x, r.height/3, r.x + r.width, r.height/3 );
				g.drawLine( r.x, r.height/3*2, r.x + r.width, r.height/3*2 );
			}
			else
			{
				g.drawLine( r.width/3, r.y, r.width/3, r.y + r.height );
				g.drawLine( r.width/3*2, r.y, r.width/3*2, r.y + r.height );				
			}
		}		
	}
	
	
	/** 
	 * Creates a new instance of SwitchablePanel 
	 */
	public SwitchablePanel()
	{
		initSwing();
		setSwitched( null );
		setSwitcherPosition( BorderLayout.NORTH );
	}
	

	/** 
	 * Creates a new instance of SwitchablePanel 
	 */
	public SwitchablePanel( JComponent switched, Object borderlayoutposition )
	{
		initSwing();
		setSwitched( switched );
		setSwitcherPosition( borderlayoutposition );
	}

	
	/**
	 * Set mnemonic to do switch by keyboard shortcut.
	 */
	public void setMnemonic( char mnemonic )
	{
		mSplitter.setMnemonic( mnemonic );
	}
	
	
	/**
	 * Set the state.
	 */
	public void setExpanded( boolean flag )
	{
		mExpanded = flag;
		if ( null != mSwitched )
		{
			mSwitched.setVisible( mExpanded );
		}
		revalidate();
	}

	
	/**
	 *
	 */
	public boolean isExpanded()
	{
		return mExpanded;
	}
	
	
	/**
	 *
	 */
	public void setSwitched( JComponent switched )
	{
		if ( null != mSwitched )
		{
			this.remove( mSwitched );
		}
		mSwitched = switched;
		
		if( null != mSwitched )
		{
			this.add( mSwitched, BorderLayout.CENTER );
		}
	}

	/**
	 *
	 */
	public void setSwitcherPosition( Object borderlayoutposition )
	{
		if(		borderlayoutposition.equals( BorderLayout.EAST  ) 
			||	borderlayoutposition.equals( BorderLayout.WEST  ) 
			||	borderlayoutposition.equals( BorderLayout.NORTH ) 
			||	borderlayoutposition.equals( BorderLayout.SOUTH ) )
		{
			this.add( mSplitter, borderlayoutposition );		
			
		}
		else
		{
			throw new IllegalArgumentException( "Layout has to be BorderLayout.[EAST|WEST|NORTH|SOUTH]" );
		}
	}

	/**
	 * setup Swing content
	 */
	private final void initSwing()
	{
		this.setLayout( new BorderLayout() );

		mSplitter.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent evt )
			{
				setExpanded( ! isExpanded() );
			}
		});

	}
	
}
