package apps.window.referencewindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ComponentInputMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import com.jidesoft.docking.DefaultDockableHolder;
import com.jidesoft.docking.DefaultDockingManager;
import com.jidesoft.docking.DockContext;
import com.jidesoft.docking.DockableFrame;
import com.jidesoft.icons.JideIconsFactory;

import apps.window.staticwindow.BasePanel;
import marketquotes.FeedListener;
import swingUtils.CustomFocusTraversalPolicy;
import util.ClassInstantiateUtil;
import util.CosmosException;
import util.commonUTIL;

public class JFrameReferenceWindow extends DefaultDockableHolder   {
	
	 FeedListener feedListener = null;
	 private static final String PROFILE_NAME = "Cosmos Reference Data";
	 BasePanel staticDataPanel = null;
	public JFrameReferenceWindow(String name) {
		setTitle("Cosmos " + name + " Window");
		staticDataPanel	= makeProductPanel(name);
		if(staticDataPanel == null) {
			commonUTIL.displayError("JFrameReferenceWindow", name + "Constructor error ", new CosmosException());
			return;
		}
	//	setSize(700,700);
	//	add(staticDataPanel);
		createInternalFrame(name);
		
		if(staticDataPanel.getHotKeysPanel() != null) {
			 InputMap keyMap = new ComponentInputMap(staticDataPanel.getHotKeysPanel());
			    keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
			            java.awt.Event.CTRL_MASK), "action_save");
			    keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,
			            java.awt.Event.CTRL_MASK), "action_new");
			    keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D,
			            java.awt.Event.CTRL_MASK), "action_del");
			    keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A,
			            java.awt.Event.CTRL_MASK), "action_saveasnew");
			    
			   
			    SwingUtilities.replaceUIActionMap(staticDataPanel.getHotKeysPanel(), staticDataPanel.getHotKeysActionMapper());
			    SwingUtilities.replaceUIInputMap(staticDataPanel.getHotKeysPanel(), JComponent.WHEN_IN_FOCUSED_WINDOW,
			            keyMap);
			   
			}
		CustomFocusTraversalPolicy focusPolicy =  new CustomFocusTraversalPolicy(staticDataPanel.getFocusOrderList());
		staticDataPanel.setFocusCycleRoot(true);
		staticDataPanel.setFocusTraversalPolicy(focusPolicy);
		//setSize(50, 500);
		
	}
	
	protected DockableFrame createSampleTaskListFrame() {

		DockableFrame frameT = new DockableFrame("ReferenceData ",JideIconsFactory.getImageIcon(JideIconsFactory.DockableFrame.FRAME1));

		//_sortableTable = new SortableTable(null);
		//JideScrollPane scrollPane = new JideScrollPane(_sortableTable);
		
		frameT.getContentPane().add(staticDataPanel, BorderLayout.NORTH);
		frameT.setInitSide(DockContext.DOCK_SIDE_CENTER);
		frameT.setInitMode(DockContext.STATE_FRAMEDOCKED);
		frameT.setTitle(frameT.getName());
		frameT.setTabTitle(frameT.getName());
		frameT.setAlwaysStayInGroup(true);
		 
		//frameT.setSize(new Dimension(1040, 750));
		//frameT.setPreferredSize(new Dimension(500, 500));
	//	frameT.setMinimumSize(new Dimension(100, 100));
		
		return frameT;
	}

	String referenceWindowName = "";
	public void createInternalFrame(String referenceWindowName) {
		URL url = getImageURL("/resources/icon/sql.jpg");
		this.referenceWindowName = referenceWindowName;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getDockingManager().setUndoLimit(10);
		getDockingManager().addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
				// refreshUndoRedoMenuItems();
			}
		});
		
		getDockingManager().beginLoadLayoutData();

		// _frame.getDockingManager().setFloatable(false);

		getDockingManager().setInitSplitPriority(DefaultDockingManager.SPLIT_SOUTH_NORTH_EAST_WEST);
		getDockingManager().setProfileKey(PROFILE_NAME);
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));
		getDockingManager().addFrame(createLeftSideHideFrame(referenceWindowName));
		getDockingManager().addFrame(createLeftSideCenterFrame(referenceWindowName+"Details"));
		getDockingManager().loadLayoutData();
		 
	//	setMenuBar();
		toFront();
		setVisible(true);
		//SDIWindowNewWindow
		//SDIWindowNewWindow
	}
	protected DockableFrame createDockableFrame(String key, Icon icon) {
		DockableFrame frame = new DockableFrame(key, icon);
		frame.setPreferredSize(new Dimension(200, 200));
	//	frame.add(new JideScrollPane(new JTextArea()));

		return frame;
	}
	private Icon getIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, "");
		}
		return null;

	}
	DockableFrame tempalteframe;
	protected DockableFrame createLeftSideHideFrame(String name) {
		String iconPath = "/resources/icon/report_filter.png";
		Icon icon = getIcon(iconPath); 
		tempalteframe = createDockableFrame(name, icon);
    
		tempalteframe.getContext().setInitMode(DockContext.STATE_AUTOHIDE);
		tempalteframe.getContext().setInitSide(DockContext.DOCK_SIDE_WEST);
		tempalteframe.getContext().setInitIndex(0);
		 
		tempalteframe.add( staticDataPanel.getLeftFrame());
		tempalteframe.setToolTipText(name);
		tempalteframe.setForeground(new Color(160,160,160));
		
		return tempalteframe;
	}
	protected DockableFrame createLeftSideCenterFrame(String name) {
		DockableFrame frameT = new DockableFrame(name,JideIconsFactory.getImageIcon(JideIconsFactory.DockableFrame.FRAME1));

		//_sortableTable = new SortableTable(null);
		//JideScrollPane scrollPane = new JideScrollPane(_sortableTable);
		
		frameT.getContentPane().add(staticDataPanel.getCenterFrame(), BorderLayout.CENTER);
		frameT.setInitSide(DockContext.DOCK_SIDE_CENTER);
		frameT.setInitMode(DockContext.STATE_FRAMEDOCKED);
		frameT.setTitle(frameT.getName());
		frameT.setTabTitle(frameT.getName());
	   
		frameT.setPreferredSize(new Dimension(200, 200));
		frameT.setMinimumSize(new Dimension(100, 100));
		frameT.setForeground(new Color(160,160,160));
		frameT.setBackground(new Color(160,160,160));
		frameT.setTabBackground(new Color(160,160,160));

		frameT.setTabForeground(new Color(160,160,160));
		 
		return frameT;
	}
	public URL getImageURL(String s) {

		return this.getClass().getResource(s);
		
	}
	protected BasePanel makeProductPanel(String name) {
        String productWindowName = "apps.window.referencewindow." + name + "Window";

        BasePanel productWindow = null;
        try {
        Class class1 =    ClassInstantiateUtil.getClass(productWindowName,true);
        return (BasePanel) class1.newInstance();
           //  productWindow = (BondPanel) 
        } catch (Exception e) {
            System.out.println( e);
        }

        return productWindow;
    }
  //  
}
