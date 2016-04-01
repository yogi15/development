
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import javax.swing.JTabbedPane;

public class TabCloseTest 
  

{
  
        public TabCloseTest()  {
	 }

        protected void setUp() throws Exception
     {
           UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
        }
	protected void tearDown() throws Exception
  
 {  }
  
       
        public void testIcons() throws Exception
        {
                JFrame f = new JFrame("testIcons");
                f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                JTabbedPane tp = new JTabbedPane();
                f.getContentPane().add( tp );
                String[] titles = { "Herkules", "Arnold", "Bruce", "Jean Claude", "Vin", "Steven", "Michael", "Bud", "Silvester", "Mel" };
                for( int i=0; i<10; i++ )
                {   JPanel panel = new JPanel();
                        panel.setBorder(BorderFactory.createTitledBorder(titles[i]));
                        tp.addTab( titles[i], panel );
                        ImageIcon icon = new ImageIcon( TabCloseTest.class.getResource("icons/icon" + i + ".png"));
                        TabCloseIcon tcicon = new TabCloseIcon();
                        CombinedIcon combi = new CombinedIcon( tcicon, icon );
                        tp.setIconAt( i, combi );
                }
		f.pack();
                f.setVisible( true );
//              Thread.sleep( 30000 );
        }
        public void testClosableTabbedPane() throws Exception
        {   JFrame f = new JFrame("testClosableTabbedPane");
                f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                ClosableTabbedPane tp = new ClosableTabbedPane();
                f.getContentPane().add( tp );
                String[] titles = { "Herkules", "Arnold", "Bruce", "Jean Claude", "Vin", "Steven", "Michael", "Bud", "Silvester", "Mel" };
                for( int i=0; i<10; i++ )
                {
                        JPanel panel = new JPanel();
                        panel.setBorder(BorderFactory.createTitledBorder(titles[i]));
                        tp.addTab( titles[i], panel );
                        ImageIcon icon = new ImageIcon(  this.getClass().getResource("/resources/icon/sql.jpg"));
                        tp.setIconAt( i, icon );
                }
                f.pack();
                f.setVisible( true );
//              Thread.sleep( 30000 );
        }
        
        public static void main(String args[]) {
        	TabCloseTest test = new TabCloseTest();
        	try {
				test.testClosableTabbedPane();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
}
 
