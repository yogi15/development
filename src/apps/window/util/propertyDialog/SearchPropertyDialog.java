package apps.window.util.propertyDialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import apps.window.util.propertyUtil.SearchPropertyWindowWrapper;

public class SearchPropertyDialog extends JDialog {
	JPanel mainPanel = new JPanel();
	 private final int WINDOW_WIDTH = 200;
	    private final int WINDOW_DEPTH = 300;

		JButton okButton = new JButton("Ok");
		JButton clearButton = new JButton("Clear");
		JButton cancelButton = new JButton("Cancel");
		JPanel southPanel = new JPanel();
	  public  SearchPropertyWindowWrapper swrapper = null;
	    private void createLayout() throws Exception {
			   Container contentPane = getContentPane();
			   contentPane.setSize(new java.awt.Dimension(WINDOW_WIDTH,WINDOW_DEPTH));
			   mainPanel.setLayout(new BorderLayout());
			  
			   southPanel.setLayout(new FlowLayout());
			   southPanel.add(okButton);
			   southPanel.add(clearButton);
			   southPanel.add(cancelButton);
			   mainPanel.add(southPanel,BorderLayout.SOUTH);
			   mainPanel.add(swrapper,BorderLayout.CENTER);
			   cancelButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	setVisible(false);
		                dispose();
		            }
		        });
			   
			   
			  
			//   mainPanel.add(sw,BorderLayout.NORTH);
			   contentPane.add( mainPanel,BorderLayout.CENTER); 
	    }
	    protected void init(String searchType){
		       try {
		    	   swrapper =   new SearchPropertyWindowWrapper(searchType);
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
	    public  JButton getCancelButton() { return cancelButton; }
		   public  JButton getOkButton() { return okButton; }
		   public JButton getClearButton() { return clearButton; }

	    public JPanel getMainPanel() { return mainPanel; }
	

}


