package apps.window.staticwindow;

import javax.swing.JFrame;

import javax.swing.ComponentInputMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;


import swingUtils.CustomFocusTraversalPolicy;
import util.ClassInstantiateUtil;

public class JFrameStaticWindow  extends JFrame {
	
	
	
	public JFrameStaticWindow(String name) {
		setTitle( "Cosmos " + name + " Window");
		BasePanel staticDataPanel = makeStaticPanel(name);
		//setSize(1200,1500);
		if(staticDataPanel.getHotKeysPanel() != null) {
			 InputMap keyMap = new ComponentInputMap(staticDataPanel.getHotKeysPanel());
			 keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_UP,
			            java.awt.Event.CTRL_MASK), "rightPaneltable_up");
			 keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DOWN,
			            java.awt.Event.CTRL_MASK), "rightPaneltable_down");
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
		add(staticDataPanel);
		
		
	}
	
	
	protected BasePanel makeStaticPanel(String name) {
		
        String className = "apps.window.staticwindow." + name + "Window";
      
        BasePanel productWindow = null;
        try {
        Class class1 =    ClassInstantiateUtil.getClass(className,true);
        productWindow = (BasePanel) class1.newInstance();
        productWindow.setFrame(this);
        
        } catch (Exception e) {
            System.out.println("JFrame Static Window  makeStatic Panel " + e);
        }

        return productWindow;
    }
  //  

}
