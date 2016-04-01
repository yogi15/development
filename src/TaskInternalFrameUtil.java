import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.LineBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


public class TaskInternalFrameUtil {
	
	private JInternalFrame jInternalFrame0; 
	private JPanel secondpanel;
	private JTree jTree0;
	private JScrollPane jScrollPane0;
	public JInternalFrame getJInternalFrame0() {
		if (jInternalFrame0 == null) {
			jInternalFrame0 = new JInternalFrame();
			
			jInternalFrame0.setBorder(new LineBorder(Color.lightGray, 1, false));
				jInternalFrame0.setVisible(true);
			jInternalFrame0.setLayout(new GroupLayout());

			javax.swing.plaf.InternalFrameUI ifu= jInternalFrame0.getUI();
			((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);
			 
			jInternalFrame0.add(getJToolBar(), new Constraints(new Bilateral(7, 0, 9), new Leading(4, 10, 10)));
			jInternalFrame0.add(getTreePanel(), new Constraints(new Bilateral(0, 0, 9),new Leading(40,10, 10)));
		}
		return jInternalFrame0;
	}
	
	private JPanel getTreePanel() {
		if(secondpanel == null) {
			secondpanel = new JPanel();
		secondpanel.setBorder(new LineBorder(Color.black,1,false));
		//secondpanel.add(getJToolBar(), new Constraints(new Bilateral(2, 0, 9),  new Bilateral(1, 0, 9)));
		secondpanel.add(getJScrollPane0(), new Constraints(new Bilateral(0, 0, 9),new Leading(49, 10, 10)));
		}
		return secondpanel;
	}
    private JToolBar getJToolBar() {
    	TaskToolBarUtil toolBar = new TaskToolBarUtil();
    	return toolBar.getJToolBar0();
    }
    
    private JToolBar getJToolBar1() {
    	TaskToolBarUtil toolBar = new TaskToolBarUtil();
    	return toolBar.getJToolBar1();
    }
    
    private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTree0());
		}
		return jScrollPane0;
	}
    private JTree getJTree0() {
		if (jTree0 == null) {
			jTree0 = new JTree();
		}return jTree0;
    }
}
