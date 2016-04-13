package apps.window.util.propertyDialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.util.Comparator;

import javax.swing.JDialog;
import javax.swing.JPanel;

import apps.window.util.propertyUtil.SearchPropertyWindowWrapper;

public class SearchPropertyDialog extends JDialog {
	JPanel mainPanel = new JPanel();
	 private final int WINDOW_WIDTH = 200;
	    private final int WINDOW_DEPTH = 300;
	  public  SearchPropertyWindowWrapper sw = null;
	    private void createLayout() throws Exception {
			   Container contentPane = getContentPane();
			   contentPane.setSize(new java.awt.Dimension(WINDOW_WIDTH,WINDOW_DEPTH));
			   mainPanel.setLayout(new BorderLayout());
			    
			   
			  
			//   mainPanel.add(sw,BorderLayout.NORTH);
			   contentPane.add( sw,BorderLayout.CENTER); 
	    }
	    protected void init(String searchType){
		       try {
		    	   sw =   new SearchPropertyWindowWrapper(searchType);
					createLayout();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	  
		    	 
		       
		   }
	    public SearchPropertyDialog(Frame parent, boolean modal, Comparator comp,
				boolean showFilter, boolean isOrderable) {
			super(parent, modal);
		 
 		 
		}
	    public SearchPropertyDialog(Frame parent, boolean modal, Comparator comp,
				boolean showFilter) {
			this(parent, modal, comp, showFilter, true);
		}
	    public SearchPropertyDialog(Frame parent, boolean modal,String searchType) {
	    	
			this(parent, modal, null, false, true); 
			init(searchType);
	    }

	    public JPanel getMainPanel() { return sw; }
	

}


